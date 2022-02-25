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
import org.entityc.compiler.model.domain.MTDomain;
import org.entityc.compiler.model.domain.MTNamed;
import org.entityc.compiler.model.entity.MTAttribute;
import org.entityc.compiler.model.entity.MTEntity;
import org.entityc.compiler.model.entity.MTEnum;
import org.entityc.compiler.model.entity.MTEnumItem;
import org.entityc.compiler.model.entity.MTRelationship;
import org.entityc.compiler.model.foundation.MFObject;
import org.entityc.compiler.model.tagging.MTTagDef;
import org.entityc.compiler.transform.template.tree.FTTemplate;
import org.entityc.compiler.transform.template.tree.FTTransformSession;
import org.entityc.compiler.transform.template.tree.expression.FTExpression;
import org.entityc.compiler.util.ECLog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class FTSortFilter extends FTFilter {

    public FTSortFilter() {
        super(null, "sort",
              "Given a collection of objects, this will return a collection that is sorted by name. This is "
              + "useful when generating code that is consistent each time in terms of the order of objects in "
              + "the output.");
        this.addCollectionInputType(MTDomain.class);
        this.addCollectionInputType(MTAttribute.class);
        this.addCollectionInputType(MTDEAttribute.class);
        this.addCollectionInputType(MTEntity.class);
        this.addCollectionInputType(MTDEntity.class);
        this.addCollectionInputType(MTModule.class);
        this.addCollectionInputType(MTEnum.class);
        this.addCollectionInputType(MTEnumItem.class);
        this.addCollectionInputType(MTRelationship.class);
        this.addCollectionInputType(MTDERelationship.class);
        this.addCollectionInputType(MTTagDef.class);
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
        outputList.sort(new Comparator<MFObject>() {
            @Override
            public int compare(MFObject o1, MFObject o2) {
                if (o1 instanceof MTEnumItem) {
                    return Integer.valueOf(((MTEnumItem) o1).getValue()).compareTo(
                        Integer.valueOf(((MTEnumItem) o2).getValue()));
                }
                else if (o1 instanceof MTNamed) {
                    return ((MTNamed) o1).getName().compareTo(((MTNamed) o2).getName());
                }
                return 0;
            }
        });
        return outputList;
    }
}
