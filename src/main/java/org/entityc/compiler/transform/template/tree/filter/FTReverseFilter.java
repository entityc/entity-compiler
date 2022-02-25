/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template.tree.filter;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.model.MTModule;
import org.entityc.compiler.model.domain.MTDEAttribute;
import org.entityc.compiler.model.domain.MTDERelationship;
import org.entityc.compiler.model.domain.MTDEntity;
import org.entityc.compiler.model.entity.MTAttribute;
import org.entityc.compiler.model.entity.MTEntity;
import org.entityc.compiler.model.entity.MTEnum;
import org.entityc.compiler.model.entity.MTEnumItem;
import org.entityc.compiler.model.entity.MTRelationship;
import org.entityc.compiler.model.foundation.MFObject;
import org.entityc.compiler.transform.template.tree.FTTemplate;
import org.entityc.compiler.transform.template.tree.FTTransformSession;
import org.entityc.compiler.transform.template.tree.expression.FTExpression;
import org.entityc.compiler.util.ECLog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class FTReverseFilter extends FTFilter {

    public FTReverseFilter() {
        super(null, "reverse",
              "Given a collection of objects, this will return a collection that has the reverse order of the input.");
        this.addCollectionInputType(MTAttribute.class);
        this.addCollectionInputType(MTDEAttribute.class);
        this.addCollectionInputType(MTEntity.class);
        this.addCollectionInputType(MTDEntity.class);
        this.addCollectionInputType(MTModule.class);
        this.addCollectionInputType(MTEnum.class);
        this.addCollectionInputType(MTEnumItem.class);
        this.addCollectionInputType(MTRelationship.class);
        this.addCollectionInputType(MTDERelationship.class);
        this.addCollectionInputType(FTTemplate.class);
    }

    @Override
    public Object filter(ParserRuleContext ctx, FTTransformSession session, Object input, List<FTExpression> paramValues, Map<String, Object> options) {
        checkInput(ctx, input, paramValues, options);

        if (input == null) {
            ECLog.logFatal(ctx, "Cannot sort a null list.");
        }
        Collection<MFObject> inputList  = (Collection) input;
        ArrayList<MFObject>  outputList = new ArrayList<>(inputList);
        Collections.reverse(outputList);
        return outputList;
    }
}
