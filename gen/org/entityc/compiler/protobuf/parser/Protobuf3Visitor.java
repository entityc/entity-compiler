// Generated from /Users/bob/Development/Entity-Compiler/src/java/org/entityc/compiler/protobuf/Protobuf3.g4 by ANTLR 4.9
package org.entityc.compiler.protobuf.parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link Protobuf3Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface Protobuf3Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link Protobuf3Parser#proto}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProto(Protobuf3Parser.ProtoContext ctx);
	/**
	 * Visit a parse tree produced by {@link Protobuf3Parser#syntax}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSyntax(Protobuf3Parser.SyntaxContext ctx);
	/**
	 * Visit a parse tree produced by {@link Protobuf3Parser#importStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportStatement(Protobuf3Parser.ImportStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Protobuf3Parser#packageStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackageStatement(Protobuf3Parser.PackageStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Protobuf3Parser#option}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOption(Protobuf3Parser.OptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Protobuf3Parser#optionName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOptionName(Protobuf3Parser.OptionNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link Protobuf3Parser#topLevelDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTopLevelDef(Protobuf3Parser.TopLevelDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link Protobuf3Parser#message}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMessage(Protobuf3Parser.MessageContext ctx);
	/**
	 * Visit a parse tree produced by {@link Protobuf3Parser#messageBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMessageBody(Protobuf3Parser.MessageBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link Protobuf3Parser#enumDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumDefinition(Protobuf3Parser.EnumDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Protobuf3Parser#enumBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumBody(Protobuf3Parser.EnumBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link Protobuf3Parser#enumField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumField(Protobuf3Parser.EnumFieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link Protobuf3Parser#enumValueOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumValueOption(Protobuf3Parser.EnumValueOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Protobuf3Parser#service}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitService(Protobuf3Parser.ServiceContext ctx);
	/**
	 * Visit a parse tree produced by {@link Protobuf3Parser#rpc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpc(Protobuf3Parser.RpcContext ctx);
	/**
	 * Visit a parse tree produced by {@link Protobuf3Parser#reserved}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReserved(Protobuf3Parser.ReservedContext ctx);
	/**
	 * Visit a parse tree produced by {@link Protobuf3Parser#ranges}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRanges(Protobuf3Parser.RangesContext ctx);
	/**
	 * Visit a parse tree produced by {@link Protobuf3Parser#range}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRange(Protobuf3Parser.RangeContext ctx);
	/**
	 * Visit a parse tree produced by {@link Protobuf3Parser#fieldNames}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldNames(Protobuf3Parser.FieldNamesContext ctx);
	/**
	 * Visit a parse tree produced by {@link Protobuf3Parser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(Protobuf3Parser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link Protobuf3Parser#fieldNumber}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldNumber(Protobuf3Parser.FieldNumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link Protobuf3Parser#field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField(Protobuf3Parser.FieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link Protobuf3Parser#fieldOptions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldOptions(Protobuf3Parser.FieldOptionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link Protobuf3Parser#ecFieldData}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEcFieldData(Protobuf3Parser.EcFieldDataContext ctx);
	/**
	 * Visit a parse tree produced by {@link Protobuf3Parser#fieldOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldOption(Protobuf3Parser.FieldOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Protobuf3Parser#oneof}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOneof(Protobuf3Parser.OneofContext ctx);
	/**
	 * Visit a parse tree produced by {@link Protobuf3Parser#oneofField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOneofField(Protobuf3Parser.OneofFieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link Protobuf3Parser#mapField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMapField(Protobuf3Parser.MapFieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link Protobuf3Parser#keyType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyType(Protobuf3Parser.KeyTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link Protobuf3Parser#fullIdent}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFullIdent(Protobuf3Parser.FullIdentContext ctx);
	/**
	 * Visit a parse tree produced by {@link Protobuf3Parser#messageName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMessageName(Protobuf3Parser.MessageNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link Protobuf3Parser#enumName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumName(Protobuf3Parser.EnumNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link Protobuf3Parser#messageOrEnumName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMessageOrEnumName(Protobuf3Parser.MessageOrEnumNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link Protobuf3Parser#fieldName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldName(Protobuf3Parser.FieldNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link Protobuf3Parser#oneofName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOneofName(Protobuf3Parser.OneofNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link Protobuf3Parser#mapName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMapName(Protobuf3Parser.MapNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link Protobuf3Parser#serviceName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitServiceName(Protobuf3Parser.ServiceNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link Protobuf3Parser#rpcName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpcName(Protobuf3Parser.RpcNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link Protobuf3Parser#messageType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMessageType(Protobuf3Parser.MessageTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link Protobuf3Parser#messageOrEnumType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMessageOrEnumType(Protobuf3Parser.MessageOrEnumTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link Protobuf3Parser#emptyStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyStatement(Protobuf3Parser.EmptyStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Protobuf3Parser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(Protobuf3Parser.ConstantContext ctx);
}