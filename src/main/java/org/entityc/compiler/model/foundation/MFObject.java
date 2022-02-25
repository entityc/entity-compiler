/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.foundation;

import org.entityc.compiler.model.entity.MTAttribute;
import org.entityc.compiler.transform.template.tree.FTNode;
import org.entityc.compiler.transform.template.tree.FTTransformSession;
import org.entityc.compiler.util.ECLog;
import org.entityc.compiler.util.ECStringUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

/**
 * A runtime object is something that can be used by a transform or template to access its data or methods.
 */
public class MFObject {

    public Object getValueByName(String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Method method = null;
        try {
            method = this.getClass().getMethod(name);
        } catch (NoSuchMethodException e) {
            // if this doesn't exist, try again as a "get" method.
        }

        if (method != null && method.getReturnType().equals(Void.TYPE)) {
            ECLog.logFatal("The method " + this.getClass().getSimpleName() + "." + method.getName()
                           + "() cannot be called as a field.");
        }

        if (method == null) {
            name   = "get" + ECStringUtil.Capitalize(name);
            method = this.getClass().getMethod(name);
        }

        // if it still doesn't exist, let it throw NoSuchMethodException

        Object value = method.invoke(this);

        // we only want to deal with one integer type so cast Integer as a Long
        if (value instanceof Integer) {
            value = Long.valueOf(((Number) value).longValue());
        }

        return value;
    }

    public Object callMethod(FTNode sourceNode, FTTransformSession session, String methodName, Collection<Object> args) throws IllegalAccessException {
        //ECLog.logInfo("Invoking method: " + methodName + " with " + args.length + " arguments.");

        Method  method             = null;
        boolean addSessionArgument = false;
        Class[] classes            = null;
        for (boolean withSession = true; ; withSession = false) {
            classes = new Class[args.size() + (withSession ?
                                               1 :
                                               0)];
            if (withSession) {
                classes[0] = FTTransformSession.class;
            }
            int i = 0;
            for (Object arg : args) {
                classes[i++ + (withSession ?
                               1 :
                               0)] = arg.getClass();
            }

            // find a method that matches by assignable method parameters
            for (Method possibleMethod : this.getClass().getMethods()) {
                if (!possibleMethod.getName().equals(methodName)) {
                    continue;
                }

                Class[] parameterTypes = possibleMethod.getParameterTypes();
                if (parameterTypes.length != classes.length) {
                    continue;
                }
                boolean parameterTypeMatch = true;
                for (i = 0; i < parameterTypes.length; i++) {
                    if (!parameterTypes[i].isAssignableFrom(classes[i])) {
                        parameterTypeMatch = false;
                        break;
                    }
                }
                if (parameterTypeMatch) {
                    method             = possibleMethod;
                    addSessionArgument = withSession;
                    break;
                }
            }
            if (method != null || !withSession) {
                break;
            }
        }
        if (method == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(methodName);
            sb.append("(");
            int i = 0;
            for (Object arg : args) {
                if (i != 0) {
                    sb.append(", ");
                }
                sb.append(classes[i].getSimpleName());
                i++;
            }
            sb.append(")");
            ECLog.logFatal(sourceNode, "On class " + this.getClass().getSimpleName() + " unable to find method: " + sb);
        }
        Object value = null;
        int extraIndex = (addSessionArgument ?
                          1 :
                          0);
        Object[] fullArgs = new Object[args.size() + extraIndex];
        if (addSessionArgument) {
            fullArgs[0] = session;
        }
        int i = 0;
        for (Object arg : args) {
            fullArgs[i + extraIndex] = arg;
            i++;
        }
        try {
            value = method.invoke(this, fullArgs);
            // we only want to deal with one integer type so cast Integer as a Long
            if (value instanceof Integer) {
                value = Long.valueOf(((Number) value).longValue());
            }
        } catch (InvocationTargetException e) {
            ArrayList<String> argsAsStrings = new ArrayList<>();
            for (Object arg : args) {
                argsAsStrings.add(arg.toString());
            }
            ECLog.logError("InvocationTargetException: " + e.getCause());
            if (this instanceof MTAttribute) {
                ECLog.logFatal(
                    "The attribute " + ((MTAttribute) this).getName() + " does not support method " + methodName
                    + "(\"" + String.join("\", \"", argsAsStrings) + "\") that has " + args.size() + " argument" + (
                        args.size() != 1 ?
                        "s" :
                        "") + ".");
            }
            else {
                ECLog.logFatal("This object of class " + this.getClass().getSimpleName() + " does not support method "
                               + methodName + "(\"" + String.join("\", \"", argsAsStrings) + "\") that has "
                               + args.size() + " argument" + (args.size() != 1 ?
                                                              "s" :
                                                              "") + ".");
            }
        }
        return value;
    }
}
