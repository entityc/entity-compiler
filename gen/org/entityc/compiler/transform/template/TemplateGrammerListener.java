// Generated from /Users/bob/Development/Entity-Compiler/src/java/org/entityc/compiler/transform/template/TemplateGrammer.g4 by ANTLR 4.9
package org.entityc.compiler.transform.template;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TemplateGrammer}.
 */
public interface TemplateGrammerListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#template}.
	 * @param ctx the parse tree
	 */
	void enterTemplate(TemplateGrammer.TemplateContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#template}.
	 * @param ctx the parse tree
	 */
	void exitTemplate(TemplateGrammer.TemplateContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#chunk}.
	 * @param ctx the parse tree
	 */
	void enterChunk(TemplateGrammer.ChunkContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#chunk}.
	 * @param ctx the parse tree
	 */
	void exitChunk(TemplateGrammer.ChunkContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#other}.
	 * @param ctx the parse tree
	 */
	void enterOther(TemplateGrammer.OtherContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#other}.
	 * @param ctx the parse tree
	 */
	void exitOther(TemplateGrammer.OtherContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(TemplateGrammer.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(TemplateGrammer.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#instruction}.
	 * @param ctx the parse tree
	 */
	void enterInstruction(TemplateGrammer.InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#instruction}.
	 * @param ctx the parse tree
	 */
	void exitInstruction(TemplateGrammer.InstructionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(TemplateGrammer.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(TemplateGrammer.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#blockEnd}.
	 * @param ctx the parse tree
	 */
	void enterBlockEnd(TemplateGrammer.BlockEndContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#blockEnd}.
	 * @param ctx the parse tree
	 */
	void exitBlockEnd(TemplateGrammer.BlockEndContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#descriptionTag}.
	 * @param ctx the parse tree
	 */
	void enterDescriptionTag(TemplateGrammer.DescriptionTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#descriptionTag}.
	 * @param ctx the parse tree
	 */
	void exitDescriptionTag(TemplateGrammer.DescriptionTagContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#nodeDescription}.
	 * @param ctx the parse tree
	 */
	void enterNodeDescription(TemplateGrammer.NodeDescriptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#nodeDescription}.
	 * @param ctx the parse tree
	 */
	void exitNodeDescription(TemplateGrammer.NodeDescriptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#languageTag}.
	 * @param ctx the parse tree
	 */
	void enterLanguageTag(TemplateGrammer.LanguageTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#languageTag}.
	 * @param ctx the parse tree
	 */
	void exitLanguageTag(TemplateGrammer.LanguageTagContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#domainTag}.
	 * @param ctx the parse tree
	 */
	void enterDomainTag(TemplateGrammer.DomainTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#domainTag}.
	 * @param ctx the parse tree
	 */
	void exitDomainTag(TemplateGrammer.DomainTagContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#versionTag}.
	 * @param ctx the parse tree
	 */
	void enterVersionTag(TemplateGrammer.VersionTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#versionTag}.
	 * @param ctx the parse tree
	 */
	void exitVersionTag(TemplateGrammer.VersionTagContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#importTag}.
	 * @param ctx the parse tree
	 */
	void enterImportTag(TemplateGrammer.ImportTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#importTag}.
	 * @param ctx the parse tree
	 */
	void exitImportTag(TemplateGrammer.ImportTagContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#installTag}.
	 * @param ctx the parse tree
	 */
	void enterInstallTag(TemplateGrammer.InstallTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#installTag}.
	 * @param ctx the parse tree
	 */
	void exitInstallTag(TemplateGrammer.InstallTagContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#fileTag}.
	 * @param ctx the parse tree
	 */
	void enterFileTag(TemplateGrammer.FileTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#fileTag}.
	 * @param ctx the parse tree
	 */
	void exitFileTag(TemplateGrammer.FileTagContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#fileArg}.
	 * @param ctx the parse tree
	 */
	void enterFileArg(TemplateGrammer.FileArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#fileArg}.
	 * @param ctx the parse tree
	 */
	void exitFileArg(TemplateGrammer.FileArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#loadTag}.
	 * @param ctx the parse tree
	 */
	void enterLoadTag(TemplateGrammer.LoadTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#loadTag}.
	 * @param ctx the parse tree
	 */
	void exitLoadTag(TemplateGrammer.LoadTagContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#functionDeclTag}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDeclTag(TemplateGrammer.FunctionDeclTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#functionDeclTag}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDeclTag(TemplateGrammer.FunctionDeclTagContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#functionDeclArgList}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDeclArgList(TemplateGrammer.FunctionDeclArgListContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#functionDeclArgList}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDeclArgList(TemplateGrammer.FunctionDeclArgListContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#functionDeclArg}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDeclArg(TemplateGrammer.FunctionDeclArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#functionDeclArg}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDeclArg(TemplateGrammer.FunctionDeclArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#callTag}.
	 * @param ctx the parse tree
	 */
	void enterCallTag(TemplateGrammer.CallTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#callTag}.
	 * @param ctx the parse tree
	 */
	void exitCallTag(TemplateGrammer.CallTagContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#callArg}.
	 * @param ctx the parse tree
	 */
	void enterCallArg(TemplateGrammer.CallArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#callArg}.
	 * @param ctx the parse tree
	 */
	void exitCallArg(TemplateGrammer.CallArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#inputCallArgList}.
	 * @param ctx the parse tree
	 */
	void enterInputCallArgList(TemplateGrammer.InputCallArgListContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#inputCallArgList}.
	 * @param ctx the parse tree
	 */
	void exitInputCallArgList(TemplateGrammer.InputCallArgListContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#outputCallArgList}.
	 * @param ctx the parse tree
	 */
	void enterOutputCallArgList(TemplateGrammer.OutputCallArgListContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#outputCallArgList}.
	 * @param ctx the parse tree
	 */
	void exitOutputCallArgList(TemplateGrammer.OutputCallArgListContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#returnTag}.
	 * @param ctx the parse tree
	 */
	void enterReturnTag(TemplateGrammer.ReturnTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#returnTag}.
	 * @param ctx the parse tree
	 */
	void exitReturnTag(TemplateGrammer.ReturnTagContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#foreachTag}.
	 * @param ctx the parse tree
	 */
	void enterForeachTag(TemplateGrammer.ForeachTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#foreachTag}.
	 * @param ctx the parse tree
	 */
	void exitForeachTag(TemplateGrammer.ForeachTagContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#breakTag}.
	 * @param ctx the parse tree
	 */
	void enterBreakTag(TemplateGrammer.BreakTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#breakTag}.
	 * @param ctx the parse tree
	 */
	void exitBreakTag(TemplateGrammer.BreakTagContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#exitTag}.
	 * @param ctx the parse tree
	 */
	void enterExitTag(TemplateGrammer.ExitTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#exitTag}.
	 * @param ctx the parse tree
	 */
	void exitExitTag(TemplateGrammer.ExitTagContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#continueTag}.
	 * @param ctx the parse tree
	 */
	void enterContinueTag(TemplateGrammer.ContinueTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#continueTag}.
	 * @param ctx the parse tree
	 */
	void exitContinueTag(TemplateGrammer.ContinueTagContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#ifTag}.
	 * @param ctx the parse tree
	 */
	void enterIfTag(TemplateGrammer.IfTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#ifTag}.
	 * @param ctx the parse tree
	 */
	void exitIfTag(TemplateGrammer.IfTagContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#elseifTag}.
	 * @param ctx the parse tree
	 */
	void enterElseifTag(TemplateGrammer.ElseifTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#elseifTag}.
	 * @param ctx the parse tree
	 */
	void exitElseifTag(TemplateGrammer.ElseifTagContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#elseTag}.
	 * @param ctx the parse tree
	 */
	void enterElseTag(TemplateGrammer.ElseTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#elseTag}.
	 * @param ctx the parse tree
	 */
	void exitElseTag(TemplateGrammer.ElseTagContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#switchTag}.
	 * @param ctx the parse tree
	 */
	void enterSwitchTag(TemplateGrammer.SwitchTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#switchTag}.
	 * @param ctx the parse tree
	 */
	void exitSwitchTag(TemplateGrammer.SwitchTagContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#caseArg}.
	 * @param ctx the parse tree
	 */
	void enterCaseArg(TemplateGrammer.CaseArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#caseArg}.
	 * @param ctx the parse tree
	 */
	void exitCaseArg(TemplateGrammer.CaseArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#caseTag}.
	 * @param ctx the parse tree
	 */
	void enterCaseTag(TemplateGrammer.CaseTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#caseTag}.
	 * @param ctx the parse tree
	 */
	void exitCaseTag(TemplateGrammer.CaseTagContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#defaultTag}.
	 * @param ctx the parse tree
	 */
	void enterDefaultTag(TemplateGrammer.DefaultTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#defaultTag}.
	 * @param ctx the parse tree
	 */
	void exitDefaultTag(TemplateGrammer.DefaultTagContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#captureTag}.
	 * @param ctx the parse tree
	 */
	void enterCaptureTag(TemplateGrammer.CaptureTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#captureTag}.
	 * @param ctx the parse tree
	 */
	void exitCaptureTag(TemplateGrammer.CaptureTagContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#receiverTag}.
	 * @param ctx the parse tree
	 */
	void enterReceiverTag(TemplateGrammer.ReceiverTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#receiverTag}.
	 * @param ctx the parse tree
	 */
	void exitReceiverTag(TemplateGrammer.ReceiverTagContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#sendTag}.
	 * @param ctx the parse tree
	 */
	void enterSendTag(TemplateGrammer.SendTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#sendTag}.
	 * @param ctx the parse tree
	 */
	void exitSendTag(TemplateGrammer.SendTagContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#preserveTag}.
	 * @param ctx the parse tree
	 */
	void enterPreserveTag(TemplateGrammer.PreserveTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#preserveTag}.
	 * @param ctx the parse tree
	 */
	void exitPreserveTag(TemplateGrammer.PreserveTagContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#logTag}.
	 * @param ctx the parse tree
	 */
	void enterLogTag(TemplateGrammer.LogTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#logTag}.
	 * @param ctx the parse tree
	 */
	void exitLogTag(TemplateGrammer.LogTagContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#letTag}.
	 * @param ctx the parse tree
	 */
	void enterLetTag(TemplateGrammer.LetTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#letTag}.
	 * @param ctx the parse tree
	 */
	void exitLetTag(TemplateGrammer.LetTagContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#doTag}.
	 * @param ctx the parse tree
	 */
	void enterDoTag(TemplateGrammer.DoTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#doTag}.
	 * @param ctx the parse tree
	 */
	void exitDoTag(TemplateGrammer.DoTagContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#variableTag}.
	 * @param ctx the parse tree
	 */
	void enterVariableTag(TemplateGrammer.VariableTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#variableTag}.
	 * @param ctx the parse tree
	 */
	void exitVariableTag(TemplateGrammer.VariableTagContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#methodCall}.
	 * @param ctx the parse tree
	 */
	void enterMethodCall(TemplateGrammer.MethodCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#methodCall}.
	 * @param ctx the parse tree
	 */
	void exitMethodCall(TemplateGrammer.MethodCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(TemplateGrammer.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(TemplateGrammer.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList(TemplateGrammer.ExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList(TemplateGrammer.ExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#arraySpecifier}.
	 * @param ctx the parse tree
	 */
	void enterArraySpecifier(TemplateGrammer.ArraySpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#arraySpecifier}.
	 * @param ctx the parse tree
	 */
	void exitArraySpecifier(TemplateGrammer.ArraySpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#filterParamExpression}.
	 * @param ctx the parse tree
	 */
	void enterFilterParamExpression(TemplateGrammer.FilterParamExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#filterParamExpression}.
	 * @param ctx the parse tree
	 */
	void exitFilterParamExpression(TemplateGrammer.FilterParamExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(TemplateGrammer.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(TemplateGrammer.PrimaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(TemplateGrammer.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(TemplateGrammer.ConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#typeType}.
	 * @param ctx the parse tree
	 */
	void enterTypeType(TemplateGrammer.TypeTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#typeType}.
	 * @param ctx the parse tree
	 */
	void exitTypeType(TemplateGrammer.TypeTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#primitiveType}.
	 * @param ctx the parse tree
	 */
	void enterPrimitiveType(TemplateGrammer.PrimitiveTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#primitiveType}.
	 * @param ctx the parse tree
	 */
	void exitPrimitiveType(TemplateGrammer.PrimitiveTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#builtinType}.
	 * @param ctx the parse tree
	 */
	void enterBuiltinType(TemplateGrammer.BuiltinTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#builtinType}.
	 * @param ctx the parse tree
	 */
	void exitBuiltinType(TemplateGrammer.BuiltinTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#invocationConstant}.
	 * @param ctx the parse tree
	 */
	void enterInvocationConstant(TemplateGrammer.InvocationConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#invocationConstant}.
	 * @param ctx the parse tree
	 */
	void exitInvocationConstant(TemplateGrammer.InvocationConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#filter}.
	 * @param ctx the parse tree
	 */
	void enterFilter(TemplateGrammer.FilterContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#filter}.
	 * @param ctx the parse tree
	 */
	void exitFilter(TemplateGrammer.FilterContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#filterOption}.
	 * @param ctx the parse tree
	 */
	void enterFilterOption(TemplateGrammer.FilterOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#filterOption}.
	 * @param ctx the parse tree
	 */
	void exitFilterOption(TemplateGrammer.FilterOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#namespaceIdent}.
	 * @param ctx the parse tree
	 */
	void enterNamespaceIdent(TemplateGrammer.NamespaceIdentContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#namespaceIdent}.
	 * @param ctx the parse tree
	 */
	void exitNamespaceIdent(TemplateGrammer.NamespaceIdentContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#namespaceIdentList}.
	 * @param ctx the parse tree
	 */
	void enterNamespaceIdentList(TemplateGrammer.NamespaceIdentListContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#namespaceIdentList}.
	 * @param ctx the parse tree
	 */
	void exitNamespaceIdentList(TemplateGrammer.NamespaceIdentListContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#publisherTag}.
	 * @param ctx the parse tree
	 */
	void enterPublisherTag(TemplateGrammer.PublisherTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#publisherTag}.
	 * @param ctx the parse tree
	 */
	void exitPublisherTag(TemplateGrammer.PublisherTagContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#authorOption}.
	 * @param ctx the parse tree
	 */
	void enterAuthorOption(TemplateGrammer.AuthorOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#authorOption}.
	 * @param ctx the parse tree
	 */
	void exitAuthorOption(TemplateGrammer.AuthorOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#authorTag}.
	 * @param ctx the parse tree
	 */
	void enterAuthorTag(TemplateGrammer.AuthorTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#authorTag}.
	 * @param ctx the parse tree
	 */
	void exitAuthorTag(TemplateGrammer.AuthorTagContext ctx);
	/**
	 * Enter a parse tree produced by {@link TemplateGrammer#outletTag}.
	 * @param ctx the parse tree
	 */
	void enterOutletTag(TemplateGrammer.OutletTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link TemplateGrammer#outletTag}.
	 * @param ctx the parse tree
	 */
	void exitOutletTag(TemplateGrammer.OutletTagContext ctx);
}