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
import java.util.List;
import java.util.Map;

public class FTAppendFilter extends FTFilter {

    public FTAppendFilter() {
        super(null, "append", "Appends two lists together to form a new list.");
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
        this.addFilterParam(new FTFilterParam("otherList",
                                              "The list to append to the input of this filter. Both should be valid Collection types."));
    }

    @Override
    public Object filter(ParserRuleContext ctx, FTTransformSession session, Object input, List<FTExpression> paramValues, Map<String, Object> options) {
        checkInput(ctx, input, paramValues, options);

        if (input == null) {
            ECLog.logFatal(ctx, "Cannot sort a null list.");
        }
        Collection<MFObject> inputList            = (Collection) input;
        ArrayList<MFObject>  outputList           = new ArrayList<>(inputList);
        FTExpression         appendExpression     = paramValues.get(0);
        Object               exptectingListObject = appendExpression.getValue(session);
        if (!(exptectingListObject instanceof Collection)) {
            ECLog.logFatal(appendExpression,
                           "Append filter can only append a list to another list. You are trying to append a type of: "
                           + exptectingListObject.getClass().getSimpleName());
        }
        Collection<MFObject> list = (Collection<MFObject>) exptectingListObject;
        if (list.size() > 0) {
            boolean matchesInput = false;
            Object  item         = ((Collection) list).iterator().next();
            if (inputList.size() == 0) {
                if (item instanceof MFObject) {
                    matchesInput = true;
                }
            }
            else {
                Object inputItem = ((Collection) input).iterator().next();
                if (item.getClass().equals(inputItem.getClass())) {
                    matchesInput = true;
                }
            }
            if (!matchesInput) {
                ECLog.logFatal(appendExpression,
                               "Append filter can only append a list of something to a list of the same thing.");
            }
            outputList.addAll(list);
        }

        return outputList;
    }
}
