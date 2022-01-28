/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.doc;

import org.entityc.compiler.EntityCompiler;
import org.entityc.compiler.doc.annotation.ModelClass;
import org.entityc.compiler.doc.annotation.ModelClassType;
import org.entityc.compiler.doc.annotation.ModelMethod;
import org.entityc.compiler.doc.annotation.ModelMethodCategory;
import org.entityc.compiler.doc.annotation.ModelMethodParameter;
import org.entityc.compiler.doc.annotation.TemplateInstruction;
import org.entityc.compiler.doc.annotation.TemplateInstructionArgument;
import org.entityc.compiler.doc.annotation.TemplateInstructionCategory;
import org.entityc.compiler.doc.annotation.TemplateInstructionUsage;
import org.entityc.compiler.doc.meta.ModelClassMeta;
import org.entityc.compiler.doc.meta.ModelClassMethodMeta;
import org.entityc.compiler.doc.meta.ModelClassMethodParameterMeta;
import org.entityc.compiler.doc.meta.TemplateInstructionArgumentMeta;
import org.entityc.compiler.doc.meta.TemplateInstructionMeta;
import org.entityc.compiler.doc.meta.TemplateInstructionUsageMeta;
import org.entityc.compiler.model.foundation.MFObject;
import org.entityc.compiler.transform.template.tree.FTContainerNode;
import org.entityc.compiler.transform.template.tree.filter.FTFilter;
import org.entityc.compiler.transform.template.tree.filter.FTFilterManager;
import org.entityc.compiler.util.ECLog;
import org.entityc.compiler.util.ECStringUtil;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.MethodParameterScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DocumentationManager extends MFObject {

    private HashMap<String, ModelClassMeta>          modelClassMetaHashMap      = new HashMap<>();
    private HashMap<TemplateInstructionCategory,
        List<TemplateInstructionMeta>>               instructionMetasByCategory = new HashMap<>();
    private HashMap<String, TemplateInstructionMeta> instructionMetasByName     = new HashMap<>();

    public void build() {

        if (!modelClassMetaHashMap.isEmpty()) {
            return;
        }

        String basePackage = EntityCompiler.class.getPackage().getName();
        Reflections reflections = new Reflections(basePackage, new SubTypesScanner(), new TypeAnnotationsScanner(),
                                                  new MethodAnnotationsScanner(), new MethodParameterScanner());

        Set<Class<?>> modelClasses =
            reflections.getTypesAnnotatedWith(ModelClass.class);

        // classes
        for (Class modelClass : modelClasses) {
            for (Annotation a : modelClass.getAnnotationsByType(ModelClass.class)) {
                modelClassMetaHashMap.put(modelClass.getSimpleName(),
                                          new ModelClassMeta(((ModelClass) a).type(),
                                                             modelClass.getSimpleName(),
                                                             ((ModelClass) a).description()));
            }
        }
        // methods
        Reflections classReflections = new Reflections(basePackage, new MethodAnnotationsScanner());
        for (Method method : classReflections.getMethodsAnnotatedWith(ModelMethod.class)) {
            Class          declaringClass = method.getDeclaringClass();
            ModelClassMeta classMeta      = modelClassMetaHashMap.get(declaringClass.getSimpleName());
            if (classMeta == null) {
                ECLog.logFatal("The class of method \"" + method.getName() + "\" has not been annotated.");
            }
            boolean property   = method.getParameterTypes().length == 0;
            String  methodName = method.getName();
            if (methodName.length() > 3 && methodName.startsWith("get") && Character.isUpperCase(
                methodName.charAt(3))) {
                methodName = ECStringUtil.Uncapitalize(methodName.substring(3));
            }
            ModelClassMethodMeta methodMeta = new ModelClassMethodMeta(classMeta, method.getAnnotation(
                ModelMethod.class).category(),
                                                                       property, methodName,
                                                                       method.getAnnotation(
                                                                           ModelMethod.class).description(),
                                                                       ECStringUtil.GetClassNameFromFullName(
                                                                           method.getReturnType().getTypeName()));
            classMeta.addMethod(methodMeta);
            Parameter[] parameters = method.getParameters();
            for (int i = 0; i < method.getParameterCount(); i++) {
                Parameter            parameter   = parameters[i];
                ModelMethodParameter annotation  = parameter.getAnnotation(ModelMethodParameter.class);
                String               description = "";
                if (annotation != null) {
                    description = annotation.description();
                }
                methodMeta.addParameter(
                    new ModelClassMethodParameterMeta(methodMeta, parameter.getType(), parameter.getName(),
                                                      description));
            }
        }

        Set<Class<?>> templateInstructions =
            reflections.getTypesAnnotatedWith(TemplateInstruction.class);
        Set<Constructor> constructors = reflections.getConstructorsWithAnyParamAnnotated(
            TemplateInstructionArgument.class);
        Set<Method> methods = reflections.getMethodsWithAnyParamAnnotated(
            TemplateInstructionArgument.class);
        List<Executable> constructorsAndMethods = new ArrayList<>();
        constructorsAndMethods.addAll(constructors);
        constructorsAndMethods.addAll(methods);

        for (Class templateInstructionClass : templateInstructions) {
            List<TemplateInstructionUsageMeta> usages = new ArrayList<>();
            for (Annotation a : templateInstructionClass.getAnnotationsByType(TemplateInstructionUsage.class)) {
                TemplateInstructionUsage usage = (TemplateInstructionUsage) a;
                TemplateInstructionUsageMeta usageMeta = new TemplateInstructionUsageMeta(usage.title(), usage.usage(),
                                                                                          usage.description());
                usages.add(usageMeta);
            }
            for (Annotation a : templateInstructionClass.getAnnotationsByType(TemplateInstruction.class)) {
                TemplateInstruction instruction = (TemplateInstruction) a;

                TemplateInstructionUsageMeta usage;
                if (!instruction.fullUsage().isEmpty()) {
                    usage = new TemplateInstructionUsageMeta(instruction.fullUsage());
                    usage.setFullUsage(true);
                }
                else {
                    usage = new TemplateInstructionUsageMeta(instruction.usage());
                }
                TemplateInstructionMeta instructionMeta = new TemplateInstructionMeta(instruction.category(),
                                                                                      instruction.name(),
                                                                                      instruction.summary(),
                                                                                      instruction.description(),
                                                                                      usage,
                                                                                      instruction.contents(),
                                                                                      instruction.seeAlso());
                instructionMeta.setBlockType(FTContainerNode.class.isAssignableFrom(templateInstructionClass));
                addInstructionMeta(instructionMeta);
                instructionMeta.addUsages(usages);

                if (constructors.size() == 0) {
                    continue;
                }

                for (Executable constructorOrMethod : constructorsAndMethods) {
                    if (constructorOrMethod.getDeclaringClass().equals(templateInstructionClass)) {

                        Annotation[][] allAnnotations = constructorOrMethod.getParameterAnnotations();
                        for (int i = 0; i < constructorOrMethod.getParameterCount(); i++) {
                            for (Annotation argAnnotation : allAnnotations[i]) {
                                if (argAnnotation.annotationType().equals(TemplateInstructionArgument.class)) {
                                    TemplateInstructionArgument instructionArgument = (TemplateInstructionArgument) argAnnotation;
                                    TemplateInstructionArgumentMeta argumentMeta = new TemplateInstructionArgumentMeta(
                                        constructorOrMethod.getParameterTypes()[i],
                                        constructorOrMethod.getParameters()[i].getName(),
                                        instructionArgument.description(), instructionArgument.optional(),
                                        instructionArgument.keyword());
                                    instructionMeta.addArgument(argumentMeta);
                                }
                            }
                        }
                    }
                }
            }
        }
        // Manually add these since they are not defined by classes
        TemplateInstructionMeta languageInstruction =
            new TemplateInstructionMeta(TemplateInstructionCategory.CONTEXT,
                                        "language",
                                        "Defines the programming language associated with the template text and resulting output.",
                                        "Defines the programming language associated with the template text and resulting output.",
                                        new TemplateInstructionUsageMeta(
                                            "`language *name*"),
                                        "", new String[]{});
        languageInstruction.addArgument(
            new TemplateInstructionArgumentMeta(String.class, "name", "The name of the language.", false, false));
        addInstructionMeta(languageInstruction);
        TemplateInstructionMeta domainInstruction =
            new TemplateInstructionMeta(TemplateInstructionCategory.CONTEXT,
                                        "domain",
                                        "Specifies the default domain name for this template.",
                                        "Specifies the default domain name for this template. When this is specified, filtering with this domain can be done without specifying this domain.",
                                        new TemplateInstructionUsageMeta(
                                            "`domain *name*"),
                                        "", new String[]{});
        domainInstruction.addArgument(
            new TemplateInstructionArgumentMeta(String.class, "name", "The name of the domain.", false, false));
        addInstructionMeta(domainInstruction);
        // sort each one
        for (List<TemplateInstructionMeta> instructionMetas : instructionMetasByCategory.values()) {
            instructionMetas.sort(new Comparator<TemplateInstructionMeta>() {
                @Override
                public int compare(TemplateInstructionMeta o1, TemplateInstructionMeta o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
        }
        resolveSeeAlso();
    }

    private void addInstructionMeta(TemplateInstructionMeta instructionMeta) {
        instructionMetasByName.put(instructionMeta.getName(), instructionMeta);
        List<TemplateInstructionMeta> list = instructionMetasByCategory.get(instructionMeta.getCategory());
        if (list == null) {
            list = new ArrayList<>();
            instructionMetasByCategory.put(instructionMeta.getCategory(), list);
        }
        list.add(instructionMeta);
    }

    private void resolveSeeAlso() {
        for (TemplateInstructionMeta instructionMeta : instructionMetasByName.values()) {
            instructionMeta.resolveSeeAlso(instructionMetasByName);
        }
    }

    public List<TemplateInstructionCategory> instructionCategories() {
        return TemplateInstructionCategory.getCategories();
    }

    public List<TemplateInstructionMeta> instructionsForCategory(TemplateInstructionCategory category) {
        return instructionMetasByCategory.get(category);
    }

    public boolean hasInstructionsForCategory(TemplateInstructionCategory category) {
        return instructionMetasByCategory.containsKey(category) && !instructionMetasByCategory.get(category).isEmpty();
    }

    public List<ModelClassType> modelClassTypes() {
        return ModelClassType.getTypes();
    }

    public int modelClassCount(ModelClassType type) {
        return modelClasses(type).size();
    }

    public List<String> modelClasses(ModelClassType type) {
        Set<String> modelClassesOfType = new HashSet<>();
        for (String className : modelClassMetaHashMap.keySet()) {
            ModelClassMeta classMeta = modelClassMetaHashMap.get(className);
            if (classMeta.getType() == type) {
                modelClassesOfType.add(className);
            }
        }
        return modelClassesOfType.stream().sorted(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        }).collect(Collectors.toList());
    }

    public ModelClassMeta modelClassMeta(String className) {
        if (modelClassMetaHashMap.containsKey(className)) {
            ModelClassMeta meta = modelClassMetaHashMap.get(className);
            return meta;
        }
        return null;
    }

    public List<ModelMethodCategory> getMethodCategories() {
        return ModelMethodCategory.getCategories();
    }

    public int methodCategoryCount() {
        return ModelMethodCategory.getCategories().size();
    }

    public List<FTFilter> filters() {
        return FTFilterManager.getInstance().getFilters();
    }
}
