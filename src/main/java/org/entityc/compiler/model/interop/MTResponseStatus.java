/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.interop;

import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.model.entity.MTTemplateSupport;
import org.entityc.compiler.model.expression.MTExpression;
import org.entityc.compiler.model.visitor.MTVisitor;
import org.entityc.compiler.util.ECLog;
import org.entityc.compiler.util.HTTPResponseStatus;
import org.antlr.v4.runtime.ParserRuleContext;

import static java.lang.System.exit;

public class MTResponseStatus extends MTNode implements MTTemplateSupport {

    private final HTTPResponseStatus status;
    private       MTExpression       conditionExpression;

    public MTResponseStatus(ParserRuleContext ctx, String httpStatusName) {
        super(ctx);
        HTTPResponseStatus responseStatus = null;
        if (Character.isDigit(httpStatusName.charAt(0))) {
            this.status = HTTPResponseStatus.FindByCode(Integer.valueOf(httpStatusName));
        } else {
            this.status = HTTPResponseStatus.FindByName(httpStatusName);
        }
        if (this.status == null) {
            ECLog.logFatal("Unknown status code: " + httpStatusName);
            exit(1);
        }
    }

    public MTResponseStatus(ParserRuleContext ctx, Integer statusCode) {
        super(ctx);
        this.status = HTTPResponseStatus.FindByCode(statusCode);
        if (this.status == null) {
            ECLog.logFatal("Unknown status code: " + statusCode);
        }
    }

    public MTExpression getConditionExpression() {
        return conditionExpression;
    }

    public void setConditionExpression(MTExpression conditionExpression) {
        this.conditionExpression = conditionExpression;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    public Integer getCode() {
        return status != null ? status.getCode() : null;
    }

    public String getName() {
        return status != null ? status.name() : null;
    }

    public String getTitle() {
        return status != null ? status.getTitle() : null;
    }

    public boolean resolveReferences(MTSpace space, int pass) {
        boolean needsAnotherPass = conditionExpression != null && conditionExpression.resolveReferences(space, pass);
        return needsAnotherPass;
    }
}
