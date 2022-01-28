// Generated from /Users/bob/Development/Entity-Compiler/src/java/org/entityc/compiler/EntityLanguage.g4 by ANTLR 4.9
package org.entityc.compiler;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link EntityLanguageParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface EntityLanguageVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(EntityLanguageParser.IdContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#ident}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdent(EntityLanguageParser.IdentContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#macro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMacro(EntityLanguageParser.MacroContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#objid}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjid(EntityLanguageParser.ObjidContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#parExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParExpression(EntityLanguageParser.ParExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#expressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionList(EntityLanguageParser.ExpressionListContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#methodCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodCall(EntityLanguageParser.MethodCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(EntityLanguageParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary(EntityLanguageParser.PrimaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(EntityLanguageParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#typeType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeType(EntityLanguageParser.TypeTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#primitiveType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveType(EntityLanguageParser.PrimitiveTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#root}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoot(EntityLanguageParser.RootContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#descriptionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDescriptionStatement(EntityLanguageParser.DescriptionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#tagStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTagStatement(EntityLanguageParser.TagStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#tagItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTagItem(EntityLanguageParser.TagItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#module}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModule(EntityLanguageParser.ModuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#moduleBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModuleBody(EntityLanguageParser.ModuleBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#entity}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEntity(EntityLanguageParser.EntityContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#entityDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEntityDecl(EntityLanguageParser.EntityDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#entityName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEntityName(EntityLanguageParser.EntityNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#entityTemplateDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEntityTemplateDecl(EntityLanguageParser.EntityTemplateDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#entityBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEntityBody(EntityLanguageParser.EntityBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#primarykeySingle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimarykeySingle(EntityLanguageParser.PrimarykeySingleContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#primarykeyMultiple}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimarykeyMultiple(EntityLanguageParser.PrimarykeyMultipleContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#attributesDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttributesDecl(EntityLanguageParser.AttributesDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#attributes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttributes(EntityLanguageParser.AttributesContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#attributesBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttributesBody(EntityLanguageParser.AttributesBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#attribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttribute(EntityLanguageParser.AttributeContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#attributeBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttributeBody(EntityLanguageParser.AttributeBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#attributeName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttributeName(EntityLanguageParser.AttributeNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#contentType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContentType(EntityLanguageParser.ContentTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#attributeConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttributeConstraint(EntityLanguageParser.AttributeConstraintContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#attributeConstraintBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttributeConstraintBody(EntityLanguageParser.AttributeConstraintBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#relationships}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationships(EntityLanguageParser.RelationshipsContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#relationshipsBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationshipsBody(EntityLanguageParser.RelationshipsBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#relationshipStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationshipStatement(EntityLanguageParser.RelationshipStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#relationshipTemplateArg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationshipTemplateArg(EntityLanguageParser.RelationshipTemplateArgContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#relationshipTemplateAs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationshipTemplateAs(EntityLanguageParser.RelationshipTemplateAsContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#relationshipReverseName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationshipReverseName(EntityLanguageParser.RelationshipReverseNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#relationshipIdName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationshipIdName(EntityLanguageParser.RelationshipIdNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#relationshipBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationshipBody(EntityLanguageParser.RelationshipBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#view}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitView(EntityLanguageParser.ViewContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#viewBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitViewBlock(EntityLanguageParser.ViewBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#viewAttributes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitViewAttributes(EntityLanguageParser.ViewAttributesContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#viewAttributesBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitViewAttributesBlock(EntityLanguageParser.ViewAttributesBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#viewRelationships}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitViewRelationships(EntityLanguageParser.ViewRelationshipsContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#viewRelationshipsBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitViewRelationshipsBlock(EntityLanguageParser.ViewRelationshipsBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#viewAttributeInclude}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitViewAttributeInclude(EntityLanguageParser.ViewAttributeIncludeContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#viewAttributeExclude}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitViewAttributeExclude(EntityLanguageParser.ViewAttributeExcludeContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#viewRelationshipInclude}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitViewRelationshipInclude(EntityLanguageParser.ViewRelationshipIncludeContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#viewRelationshipExclude}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitViewRelationshipExclude(EntityLanguageParser.ViewRelationshipExcludeContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#viewTaggedListItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitViewTaggedListItem(EntityLanguageParser.ViewTaggedListItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#viewTaggedList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitViewTaggedList(EntityLanguageParser.ViewTaggedListContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#viewIdentifierList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitViewIdentifierList(EntityLanguageParser.ViewIdentifierListContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#enumStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumStatement(EntityLanguageParser.EnumStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#enumItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumItem(EntityLanguageParser.EnumItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#enumItemBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumItemBody(EntityLanguageParser.EnumItemBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#typedefStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypedefStatement(EntityLanguageParser.TypedefStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#typedefBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypedefBody(EntityLanguageParser.TypedefBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#bitfield}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitfield(EntityLanguageParser.BitfieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#bitCount}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitCount(EntityLanguageParser.BitCountContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domain}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomain(EntityLanguageParser.DomainContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainBody(EntityLanguageParser.DomainBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#namespaceIdent}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamespaceIdent(EntityLanguageParser.NamespaceIdentContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainNamespace}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainNamespace(EntityLanguageParser.DomainNamespaceContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainFlattenSecondaryEntities}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainFlattenSecondaryEntities(EntityLanguageParser.DomainFlattenSecondaryEntitiesContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainEnum}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainEnum(EntityLanguageParser.DomainEnumContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainEnumBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainEnumBody(EntityLanguageParser.DomainEnumBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainEnumItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainEnumItem(EntityLanguageParser.DomainEnumItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainEnumItemBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainEnumItemBody(EntityLanguageParser.DomainEnumItemBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainEnumItemRenameTo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainEnumItemRenameTo(EntityLanguageParser.DomainEnumItemRenameToContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainEnumNamingItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainEnumNamingItem(EntityLanguageParser.DomainEnumNamingItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainEnumNamingBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainEnumNamingBody(EntityLanguageParser.DomainEnumNamingBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainTagging}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainTagging(EntityLanguageParser.DomainTaggingContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainTaggingTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainTaggingTag(EntityLanguageParser.DomainTaggingTagContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainTaggingTagValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainTaggingTagValue(EntityLanguageParser.DomainTaggingTagValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainTaggingTagValueType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainTaggingTagValueType(EntityLanguageParser.DomainTaggingTagValueTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainNaming}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainNaming(EntityLanguageParser.DomainNamingContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#namingClass}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamingClass(EntityLanguageParser.NamingClassContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainNamingBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainNamingBody(EntityLanguageParser.DomainNamingBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainNamingMethod}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainNamingMethod(EntityLanguageParser.DomainNamingMethodContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainNamingPrefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainNamingPrefix(EntityLanguageParser.DomainNamingPrefixContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainNamingSuffix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainNamingSuffix(EntityLanguageParser.DomainNamingSuffixContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainNamingPrimaryKey}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainNamingPrimaryKey(EntityLanguageParser.DomainNamingPrimaryKeyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainNamingWithUnits}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainNamingWithUnits(EntityLanguageParser.DomainNamingWithUnitsContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainNamingWithoutUnits}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainNamingWithoutUnits(EntityLanguageParser.DomainNamingWithoutUnitsContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainApplyTemplate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainApplyTemplate(EntityLanguageParser.DomainApplyTemplateContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainModuleApplyTemplate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainModuleApplyTemplate(EntityLanguageParser.DomainModuleApplyTemplateContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainEntityApplyTemplate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainEntityApplyTemplate(EntityLanguageParser.DomainEntityApplyTemplateContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainApplyTemplateBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainApplyTemplateBody(EntityLanguageParser.DomainApplyTemplateBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#defaultTemplateConfig}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefaultTemplateConfig(EntityLanguageParser.DefaultTemplateConfigContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#templateConfig}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplateConfig(EntityLanguageParser.TemplateConfigContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#jsonObj}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonObj(EntityLanguageParser.JsonObjContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#jsonPair}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonPair(EntityLanguageParser.JsonPairContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#jsonArr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonArr(EntityLanguageParser.JsonArrContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#jsonValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonValue(EntityLanguageParser.JsonValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainModule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainModule(EntityLanguageParser.DomainModuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainModuleBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainModuleBody(EntityLanguageParser.DomainModuleBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainView}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainView(EntityLanguageParser.DomainViewContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainEntity}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainEntity(EntityLanguageParser.DomainEntityContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainEntityBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainEntityBody(EntityLanguageParser.DomainEntityBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainEntityNamespace}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainEntityNamespace(EntityLanguageParser.DomainEntityNamespaceContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainAttributes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainAttributes(EntityLanguageParser.DomainAttributesContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainAttributesBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainAttributesBody(EntityLanguageParser.DomainAttributesBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainAttributeExclude}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainAttributeExclude(EntityLanguageParser.DomainAttributeExcludeContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainAttributeAdd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainAttributeAdd(EntityLanguageParser.DomainAttributeAddContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainAttributesRenameTo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainAttributesRenameTo(EntityLanguageParser.DomainAttributesRenameToContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainAttributesRenameAppendPrepend}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainAttributesRenameAppendPrepend(EntityLanguageParser.DomainAttributesRenameAppendPrependContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainAttributeReplaces}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainAttributeReplaces(EntityLanguageParser.DomainAttributeReplacesContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#replacesBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReplacesBody(EntityLanguageParser.ReplacesBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainIncludeEntities}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainIncludeEntities(EntityLanguageParser.DomainIncludeEntitiesContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainExcludeEntities}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainExcludeEntities(EntityLanguageParser.DomainExcludeEntitiesContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainVirtualAttribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainVirtualAttribute(EntityLanguageParser.DomainVirtualAttributeContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainAttribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainAttribute(EntityLanguageParser.DomainAttributeContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainAttributeBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainAttributeBody(EntityLanguageParser.DomainAttributeBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainRelationships}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainRelationships(EntityLanguageParser.DomainRelationshipsContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainRelationshipsBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainRelationshipsBody(EntityLanguageParser.DomainRelationshipsBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainRelationship}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainRelationship(EntityLanguageParser.DomainRelationshipContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainRelationshipBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainRelationshipBody(EntityLanguageParser.DomainRelationshipBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#abstractInterfaceStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAbstractInterfaceStatement(EntityLanguageParser.AbstractInterfaceStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#interfaceBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceBody(EntityLanguageParser.InterfaceBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#interfaceType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceType(EntityLanguageParser.InterfaceTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#operation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperation(EntityLanguageParser.OperationContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#operationBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperationBody(EntityLanguageParser.OperationBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#operationConfig}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperationConfig(EntityLanguageParser.OperationConfigContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#operationConfigBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperationConfigBlock(EntityLanguageParser.OperationConfigBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#operationConfigContext}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperationConfigContext(EntityLanguageParser.OperationConfigContextContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#operationConfigContextType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperationConfigContextType(EntityLanguageParser.OperationConfigContextTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#operationConfigArgument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperationConfigArgument(EntityLanguageParser.OperationConfigArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#operationConfigArgumentType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperationConfigArgumentType(EntityLanguageParser.OperationConfigArgumentTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#operationContextBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperationContextBlock(EntityLanguageParser.OperationContextBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#operationArgumentBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperationArgumentBlock(EntityLanguageParser.OperationArgumentBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#operationRequestMethod}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperationRequestMethod(EntityLanguageParser.OperationRequestMethodContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#operationRequestMethodBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperationRequestMethodBlock(EntityLanguageParser.OperationRequestMethodBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#operationRequest}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperationRequest(EntityLanguageParser.OperationRequestContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#operationRequestBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperationRequestBlock(EntityLanguageParser.OperationRequestBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#operationRequestEndpoint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperationRequestEndpoint(EntityLanguageParser.OperationRequestEndpointContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#operationRequestEndpointBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperationRequestEndpointBlock(EntityLanguageParser.OperationRequestEndpointBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#operationRequestEndpointParam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperationRequestEndpointParam(EntityLanguageParser.OperationRequestEndpointParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#operationRequestEndpointParamBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperationRequestEndpointParamBlock(EntityLanguageParser.OperationRequestEndpointParamBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#operationRequestBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperationRequestBody(EntityLanguageParser.OperationRequestBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#operationRequestBodyBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperationRequestBodyBlock(EntityLanguageParser.OperationRequestBodyBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#operationBodyContentType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperationBodyContentType(EntityLanguageParser.OperationBodyContentTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#operationBodyView}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperationBodyView(EntityLanguageParser.OperationBodyViewContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#operationBodyDomain}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperationBodyDomain(EntityLanguageParser.OperationBodyDomainContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#operationResponse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperationResponse(EntityLanguageParser.OperationResponseContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#operationResponseBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperationResponseBlock(EntityLanguageParser.OperationResponseBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#operationRequestStatusExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperationRequestStatusExpression(EntityLanguageParser.OperationRequestStatusExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#operationResponseStatus}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperationResponseStatus(EntityLanguageParser.OperationResponseStatusContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#operationResponseStatusBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperationResponseStatusBlock(EntityLanguageParser.OperationResponseStatusBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#operationResponseBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperationResponseBody(EntityLanguageParser.OperationResponseBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#operationResponseBodyBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperationResponseBodyBlock(EntityLanguageParser.OperationResponseBodyBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainInterfaceStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainInterfaceStatement(EntityLanguageParser.DomainInterfaceStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainInterfaceBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainInterfaceBody(EntityLanguageParser.DomainInterfaceBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainOperation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainOperation(EntityLanguageParser.DomainOperationContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#extendingOperationBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExtendingOperationBody(EntityLanguageParser.ExtendingOperationBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#extendingOperationConfig}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExtendingOperationConfig(EntityLanguageParser.ExtendingOperationConfigContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#extendingOperationConfigBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExtendingOperationConfigBlock(EntityLanguageParser.ExtendingOperationConfigBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#extendingOperationAssignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExtendingOperationAssignment(EntityLanguageParser.ExtendingOperationAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#domainOperationBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainOperationBody(EntityLanguageParser.DomainOperationBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#configuration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConfiguration(EntityLanguageParser.ConfigurationContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#configurationBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConfigurationBody(EntityLanguageParser.ConfigurationBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#formatting}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormatting(EntityLanguageParser.FormattingContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#formattingBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormattingBody(EntityLanguageParser.FormattingBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#formatStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormatStatement(EntityLanguageParser.FormatStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#directory}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDirectory(EntityLanguageParser.DirectoryContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#outputBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOutputBody(EntityLanguageParser.OutputBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#outputPath}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOutputPath(EntityLanguageParser.OutputPathContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#templates}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplates(EntityLanguageParser.TemplatesContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#templatesBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplatesBody(EntityLanguageParser.TemplatesBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#template}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplate(EntityLanguageParser.TemplateContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#templateBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplateBody(EntityLanguageParser.TemplateBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#templatesImport}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplatesImport(EntityLanguageParser.TemplatesImportContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#outputSpec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOutputSpec(EntityLanguageParser.OutputSpecContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#transform}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTransform(EntityLanguageParser.TransformContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#transformBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTransformBody(EntityLanguageParser.TransformBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#repository}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepository(EntityLanguageParser.RepositoryContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#repositoryBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepositoryBody(EntityLanguageParser.RepositoryBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#repositoryType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepositoryType(EntityLanguageParser.RepositoryTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#repositoryOrganization}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepositoryOrganization(EntityLanguageParser.RepositoryOrganizationContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#repositoryName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepositoryName(EntityLanguageParser.RepositoryNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#repositoryPath}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepositoryPath(EntityLanguageParser.RepositoryPathContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#repositoryTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepositoryTag(EntityLanguageParser.RepositoryTagContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#space}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpace(EntityLanguageParser.SpaceContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#spaceBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpaceBody(EntityLanguageParser.SpaceBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#spaceNamespace}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpaceNamespace(EntityLanguageParser.SpaceNamespaceContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#spaceMetadata}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpaceMetadata(EntityLanguageParser.SpaceMetadataContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#spaceImport}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpaceImport(EntityLanguageParser.SpaceImportContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#spaceInclude}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpaceInclude(EntityLanguageParser.SpaceIncludeContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#spaceIncludeBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpaceIncludeBody(EntityLanguageParser.SpaceIncludeBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#spaceIncludeImportEnum}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpaceIncludeImportEnum(EntityLanguageParser.SpaceIncludeImportEnumContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#spaceIncludeImportEntity}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpaceIncludeImportEntity(EntityLanguageParser.SpaceIncludeImportEntityContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#idList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdList(EntityLanguageParser.IdListContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#protoc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProtoc(EntityLanguageParser.ProtocContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#protocBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProtocBody(EntityLanguageParser.ProtocBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#protocLanguage}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProtocLanguage(EntityLanguageParser.ProtocLanguageContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(EntityLanguageParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#attributeQualifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttributeQualifier(EntityLanguageParser.AttributeQualifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#units}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnits(EntityLanguageParser.UnitsContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#unitsBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnitsBody(EntityLanguageParser.UnitsBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#unitDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnitDefinition(EntityLanguageParser.UnitDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#unitDefinitionBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnitDefinitionBody(EntityLanguageParser.UnitDefinitionBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#unitDefinitionField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnitDefinitionField(EntityLanguageParser.UnitDefinitionFieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#language}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLanguage(EntityLanguageParser.LanguageContext ctx);
	/**
	 * Visit a parse tree produced by the {@code languageTypes}
	 * labeled alternative in {@link EntityLanguageParser#languageBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLanguageTypes(EntityLanguageParser.LanguageTypesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code languageSelf}
	 * labeled alternative in {@link EntityLanguageParser#languageBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLanguageSelf(EntityLanguageParser.LanguageSelfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code languageComments}
	 * labeled alternative in {@link EntityLanguageParser#languageBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLanguageComments(EntityLanguageParser.LanguageCommentsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code languageOperators}
	 * labeled alternative in {@link EntityLanguageParser#languageBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLanguageOperators(EntityLanguageParser.LanguageOperatorsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code languageKeywords}
	 * labeled alternative in {@link EntityLanguageParser#languageBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLanguageKeywords(EntityLanguageParser.LanguageKeywordsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code languageFunctions}
	 * labeled alternative in {@link EntityLanguageParser#languageBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLanguageFunctions(EntityLanguageParser.LanguageFunctionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#typesBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypesBody(EntityLanguageParser.TypesBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#languageType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLanguageType(EntityLanguageParser.LanguageTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#commentsBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommentsBody(EntityLanguageParser.CommentsBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#functionsBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionsBody(EntityLanguageParser.FunctionsBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#operatorsBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperatorsBody(EntityLanguageParser.OperatorsBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntityLanguageParser#version}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVersion(EntityLanguageParser.VersionContext ctx);
}