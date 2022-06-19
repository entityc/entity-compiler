/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.transform.template;

import org.entityc.compiler.model.MTNamespace;
import org.entityc.compiler.transform.template.tree.FTBaseVisitor;
import org.entityc.compiler.transform.template.tree.FTBreak;
import org.entityc.compiler.transform.template.tree.FTCall;
import org.entityc.compiler.transform.template.tree.FTCapture;
import org.entityc.compiler.transform.template.tree.FTCase;
import org.entityc.compiler.transform.template.tree.FTContinue;
import org.entityc.compiler.transform.template.tree.FTDo;
import org.entityc.compiler.transform.template.tree.FTElse;
import org.entityc.compiler.transform.template.tree.FTElseIf;
import org.entityc.compiler.transform.template.tree.FTExit;
import org.entityc.compiler.transform.template.tree.FTExpressionTag;
import org.entityc.compiler.transform.template.tree.FTFile;
import org.entityc.compiler.transform.template.tree.FTForeach;
import org.entityc.compiler.transform.template.tree.FTFunction;
import org.entityc.compiler.transform.template.tree.FTFunctionArgument;
import org.entityc.compiler.transform.template.tree.FTIf;
import org.entityc.compiler.transform.template.tree.FTInstall;
import org.entityc.compiler.transform.template.tree.FTLet;
import org.entityc.compiler.transform.template.tree.FTLoad;
import org.entityc.compiler.transform.template.tree.FTLog;
import org.entityc.compiler.transform.template.tree.FTOutlet;
import org.entityc.compiler.transform.template.tree.FTPreserve;
import org.entityc.compiler.transform.template.tree.FTAuthor;
import org.entityc.compiler.transform.template.tree.FTPublisher;
import org.entityc.compiler.transform.template.tree.FTReceive;
import org.entityc.compiler.transform.template.tree.FTReturn;
import org.entityc.compiler.transform.template.tree.FTSend;
import org.entityc.compiler.transform.template.tree.FTSource;
import org.entityc.compiler.transform.template.tree.FTSwitch;
import org.entityc.compiler.transform.template.tree.FTTemplate;
import org.entityc.compiler.transform.template.tree.expression.FTArray;
import org.entityc.compiler.transform.template.tree.expression.FTConstant;
import org.entityc.compiler.transform.template.tree.expression.FTExpression;
import org.entityc.compiler.transform.template.tree.expression.FTFilterExpression;
import org.entityc.compiler.transform.template.tree.expression.FTGlobalConstant;
import org.entityc.compiler.transform.template.tree.expression.FTMethodCall;
import org.entityc.compiler.transform.template.tree.expression.FTNamedElement;
import org.entityc.compiler.transform.template.tree.expression.FTOperand;
import org.entityc.compiler.transform.template.tree.expression.FTOperation;
import org.entityc.compiler.transform.template.tree.filter.FTFilter;
import org.entityc.compiler.util.ECLog;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class TemplatePublishing extends FTBaseVisitor {

    private       Mode                              mode         = Mode.IndexPublishers;
    private final Map<String, HashSet<FTPublisher>> publisherMap = new HashMap<>();

    public TemplatePublishing() {
        super(null);
    }

    public void setTemplate(FTTemplate template) {
        super.template = template;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    @Override
    public void visitCall(FTCall call) {

    }

    @Override
    public void visitFile(FTFile ftFile) {
    }

    @Override
    public void visitFunction(FTFunction ftFunction) {

    }

    @Override
    public void visitFunctionArgument(FTFunctionArgument ftFunctionArgument) {

    }

    @Override
    public void visitPreserve(FTPreserve ftPreserve) {

    }

    @Override
    public void visitTemplate(FTTemplate template) {
        //ECLog.logInfo(template, "TEMPLATE >>>>>>>: " + template.getName());

    }

    @Override
    public void visitFilterExpression(FTFilterExpression filterExpression) {

    }

    @Override
    public void visitMethodCall(FTMethodCall methodCall) {

    }

    @Override
    public void visitTemplateEnd(FTTemplate template) {
    }

    @Override
    public void visitPublisher(FTPublisher publisher) {
        if (mode == Mode.IndexPublishers) {
            HashSet<FTPublisher> publishers;
            String               namespaceStr = publisher.getNamespace().toString();
            if (publisherMap.containsKey(namespaceStr)) {
                publishers = publisherMap.get(namespaceStr);
            } else {
                publishers = new HashSet<>();
            }
            publishers.add(publisher);
            publisherMap.put(namespaceStr, publishers);
            //ECLog.logInfo(publisher, "Indexed publisher: " + publisher.getNamespace().toString());
        }
    }

    @Override
    public void visitAuthor(FTAuthor author) {
        if (mode == Mode.PairAuthorsToOutlets) {
            String outletName = author.getOutletName();
            if (outletName != null) {
                for (MTNamespace fullPublisherNamespace : author.getFullNamespaces()) {
                    String               publisherName = fullPublisherNamespace.toString();
                    HashSet<FTPublisher> publishers    = publisherMap.get(publisherName);
                    if (publishers == null) {
                        ECLog.logFatal(author, "Not able to find publisher: " + publisherName);
                    }
                    for (FTPublisher publisher : publishers) {
                        List<FTOutlet> outlets = publisher.getOutletsByName(outletName);
                        if (outlets != null) {
                            for (FTOutlet outlet : outlets) {
                                if (outlet == null) {
                                    ECLog.logFatal(publisher,
                                                   "Not able to find outlet \"" + outletName + "\" on publisher: "
                                                   + publisherName);
                                }
                                author.setOutlet(outlet);
                                outlet.addAuthor(author);
//                ECLog.logInfo(author, "FOUND publish submission to publisher: " + author.getPublisherNamespace().toString() + " outlet: " + outletName);
                            }
                        } else {
                            ECLog.logWarning(publisher,
                                             "Publisher " + publisherName + " does not have outlet named " + outletName
                                             + ". Ignoring author.");
                        }
                    }
                }
            }
        }
    }

    @Override
    public void visitAuthorEnd(FTAuthor author) {

    }

    @Override
    public void visitOutlet(FTOutlet outlet) {
        //ECLog.logInfo(outlet, "Found outlet: " + outlet.getName());
    }

    @Override
    public void visitSource(FTSource source) {

    }

    @Override
    public void visitBreak(FTBreak ftBreak) {

    }

    @Override
    public void visitCapture(FTCapture capture) {

    }

    @Override
    public void visitCase(FTCase ftCase) {

    }

    @Override
    public void visitContinue(FTContinue ftContinue) {

    }

    @Override
    public void visitDo(FTDo ftDo) {

    }

    @Override
    public void visitElse(FTElse ftElse) {

    }

    @Override
    public void visitElseIf(FTElseIf ftElseIf) {

    }

    @Override
    public void visitExit(FTExit exit) {

    }

    @Override
    public void visitExpressionTag(FTExpressionTag expressionTag) {

    }

    @Override
    public void visitForeach(FTForeach ftForeach) {

    }

    @Override
    public void visitIf(FTIf ftIf) {

    }

    @Override
    public void visitInstall(FTInstall install) {

    }

    @Override
    public void visitLet(FTLet let) {

    }

    @Override
    public void visitLoad(FTLoad load) {

    }

    @Override
    public void visitLog(FTLog log) {

    }

    @Override
    public void visitReceive(FTReceive receive) {

    }

    @Override
    public void visitReturn(FTReturn ftReturn) {

    }

    @Override
    public void visitSend(FTSend send) {

    }

    @Override
    public void visitSwitch(FTSwitch ftSwitch) {

    }

    @Override
    public void visitArray(FTArray array) {

    }

    @Override
    public void visitConstant(FTConstant constant) {

    }

    @Override
    public void visitExpression(FTExpression expression) {

    }

    @Override
    public void visitGlobalConstant(FTGlobalConstant globalConstant) {

    }

    @Override
    public void visitNamedElement(FTNamedElement namedElement) {

    }

    @Override
    public void visitOperand(FTOperand operand) {

    }

    @Override
    public void visitOperation(FTOperation operation) {

    }

    @Override
    public void visitFilter(FTFilter filter) {

    }

    public enum Mode {
        IndexPublishers,
        PairAuthorsToOutlets
    }
}
