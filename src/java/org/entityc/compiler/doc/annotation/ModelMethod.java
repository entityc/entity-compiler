/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.doc.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.entityc.compiler.doc.annotation.ModelMethodCategory.OTHER;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {java.lang.annotation.ElementType.METHOD})
public @interface ModelMethod {

    ModelMethodCategory category() default OTHER;
    String description() default "";
}
