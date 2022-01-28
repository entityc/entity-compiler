// Generated from /Users/bob/Development/Entity-Compiler/src/java/org/entityc/compiler/transform/template/TemplateGrammer.g4 by ANTLR 4.9
package org.entityc.compiler.transform.template;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TemplateGrammer}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TemplateGrammerVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#template}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplate(TemplateGrammer.TemplateContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#chunk}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChunk(TemplateGrammer.ChunkContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#other}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOther(TemplateGrammer.OtherContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(TemplateGrammer.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruction(TemplateGrammer.InstructionContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(TemplateGrammer.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#blockEnd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockEnd(TemplateGrammer.BlockEndContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#descriptionTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDescriptionTag(TemplateGrammer.DescriptionTagContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#nodeDescription}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNodeDescription(TemplateGrammer.NodeDescriptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#languageTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLanguageTag(TemplateGrammer.LanguageTagContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#domainTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainTag(TemplateGrammer.DomainTagContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#versionTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVersionTag(TemplateGrammer.VersionTagContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#importTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportTag(TemplateGrammer.ImportTagContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#installTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstallTag(TemplateGrammer.InstallTagContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#fileTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFileTag(TemplateGrammer.FileTagContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#fileArg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFileArg(TemplateGrammer.FileArgContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#loadTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoadTag(TemplateGrammer.LoadTagContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#functionDeclTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDeclTag(TemplateGrammer.FunctionDeclTagContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#functionDeclArgList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDeclArgList(TemplateGrammer.FunctionDeclArgListContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#functionDeclArg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDeclArg(TemplateGrammer.FunctionDeclArgContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#callTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallTag(TemplateGrammer.CallTagContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#callArg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallArg(TemplateGrammer.CallArgContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#inputCallArgList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInputCallArgList(TemplateGrammer.InputCallArgListContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#outputCallArgList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOutputCallArgList(TemplateGrammer.OutputCallArgListContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#returnTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnTag(TemplateGrammer.ReturnTagContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#foreachTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForeachTag(TemplateGrammer.ForeachTagContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#breakTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakTag(TemplateGrammer.BreakTagContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#exitTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExitTag(TemplateGrammer.ExitTagContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#continueTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueTag(TemplateGrammer.ContinueTagContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#ifTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfTag(TemplateGrammer.IfTagContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#elseifTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseifTag(TemplateGrammer.ElseifTagContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#elseTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseTag(TemplateGrammer.ElseTagContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#switchTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchTag(TemplateGrammer.SwitchTagContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#caseArg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseArg(TemplateGrammer.CaseArgContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#caseTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseTag(TemplateGrammer.CaseTagContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#defaultTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefaultTag(TemplateGrammer.DefaultTagContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#captureTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaptureTag(TemplateGrammer.CaptureTagContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#receiverTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReceiverTag(TemplateGrammer.ReceiverTagContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#sendTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSendTag(TemplateGrammer.SendTagContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#preserveTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPreserveTag(TemplateGrammer.PreserveTagContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#logTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogTag(TemplateGrammer.LogTagContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#letTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetTag(TemplateGrammer.LetTagContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#doTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoTag(TemplateGrammer.DoTagContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#variableTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableTag(TemplateGrammer.VariableTagContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#methodCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodCall(TemplateGrammer.MethodCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(TemplateGrammer.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#expressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionList(TemplateGrammer.ExpressionListContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#arraySpecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArraySpecifier(TemplateGrammer.ArraySpecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#filterParamExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilterParamExpression(TemplateGrammer.FilterParamExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary(TemplateGrammer.PrimaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(TemplateGrammer.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#typeType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeType(TemplateGrammer.TypeTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#primitiveType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveType(TemplateGrammer.PrimitiveTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#builtinType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBuiltinType(TemplateGrammer.BuiltinTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#invocationConstant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInvocationConstant(TemplateGrammer.InvocationConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#filter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilter(TemplateGrammer.FilterContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#filterOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilterOption(TemplateGrammer.FilterOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#namespaceIdent}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamespaceIdent(TemplateGrammer.NamespaceIdentContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#namespaceIdentList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamespaceIdentList(TemplateGrammer.NamespaceIdentListContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#publisherTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPublisherTag(TemplateGrammer.PublisherTagContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#authorOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAuthorOption(TemplateGrammer.AuthorOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#authorTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAuthorTag(TemplateGrammer.AuthorTagContext ctx);
	/**
	 * Visit a parse tree produced by {@link TemplateGrammer#outletTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOutletTag(TemplateGrammer.OutletTagContext ctx);
}