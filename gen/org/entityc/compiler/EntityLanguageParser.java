// Generated from /Users/bob/Development/Entity-Compiler/src/java/org/entityc/compiler/EntityLanguage.g4 by ANTLR 4.9
package org.entityc.compiler;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class EntityLanguageParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		MODULE=32, ENTITY=33, PRIMARYKEY=34, ATTRIBUTE=35, UNIQUE=36, CREATION=37, 
		MODIFICATION=38, REQUIRED=39, OPTIONAL=40, PARENT=41, ORDERED=42, SEQUENTIAL=43, 
		VIRTUAL=44, EXTERN=45, TRANSIENT=46, PRIMARY=47, SECONDARY=48, FLATTEN=49, 
		RELATIONSHIPS=50, RELATIONSHIP=51, ONE=52, MANY=53, TOONE=54, TOMANY=55, 
		AS=56, ENTITIES=57, ENUM=58, ENUMITEM=59, TYPEDEF=60, UNUSED=61, CONFIGURATION=62, 
		FORMATTING=63, FORMAT=64, OUTPUT=65, DIRECTORY=66, TRANSFORM=67, TEMPLATE=68, 
		TEMPLATES=69, CONTEXTUAL=70, PATH=71, ENDPOINT=72, INHERITED=73, REPOSITORY=74, 
		PROTOC=75, TAG=76, VALUE=77, SPACE=78, IMPORT=79, FROM=80, ORGANIZATION=81, 
		NAME=82, NULLABLE=83, LANGUAGE=84, VERSION=85, TYPES=86, REF=87, SELF=88, 
		COMMENTS=89, LINE=90, BLOCK_START=91, BLOCK_END=92, OPERATORS=93, KEYWORDS=94, 
		FUNCTIONS=95, CONTENT_TYPE=96, CONSTRAINT=97, HINTS=98, SHORT=99, LONG=100, 
		HUMAN=101, READABLE=102, IDENTIFICATION=103, DOMAIN=104, ATTRIBUTES=105, 
		REPLACES=106, METHOD=107, PREFIX=108, SUFFIX=109, RENAME=110, TAGGING=111, 
		STARTSWITH=112, NAMING=113, NAMESPACE=114, IN=115, WITH=116, WITHOUT=117, 
		READ=118, WRITE=119, WHEN=120, REQUIRES=121, ROLE=122, USER=123, IF=124, 
		APPLY=125, UNITS=126, ABBR=127, MULTIPLIER=128, VIEWS=129, DEFAULT=130, 
		VIEW=131, INCLUDE=132, EXCLUDE=133, DISTILL=134, NOPARENTS=135, ADD=136, 
		AND=137, TAGGED=138, ABSTRACT=139, EXTENDS=140, INHERITS=141, INTERFACE=142, 
		CONFIG=143, METADATA=144, CONTEXT=145, ARGUMENT=146, OPERATION=147, REQUEST=148, 
		RESPONSE=149, BODY=150, QUERY=151, PARAM=152, STATUS=153, CUSTOM=154, 
		TYPE=155, TO=156, PREPEND=157, APPEND=158, BOOLEAN_TYPE=159, INT32_TYPE=160, 
		INT64_TYPE=161, BYTE_TYPE=162, UUID_TYPE=163, FLOAT_TYPE=164, DOUBLE_TYPE=165, 
		STRING_TYPE=166, DATE_TYPE=167, ASSET_TYPE=168, ARRAY=169, DESCRIPTION=170, 
		TAGS=171, INTEGER=172, MACRO_START=173, STRING=174, VERSIONNUM=175, FLOAT=176, 
		ID=177, IDX=178, LETTER=179, LINE_COMMENT=180, COMMENT=181, WS=182, ERRCHAR=183;
	public static final int
		RULE_id = 0, RULE_ident = 1, RULE_macro = 2, RULE_objid = 3, RULE_parExpression = 4, 
		RULE_expressionList = 5, RULE_methodCall = 6, RULE_expression = 7, RULE_primary = 8, 
		RULE_constant = 9, RULE_typeType = 10, RULE_primitiveType = 11, RULE_root = 12, 
		RULE_descriptionStatement = 13, RULE_tagStatement = 14, RULE_tagItem = 15, 
		RULE_module = 16, RULE_moduleBody = 17, RULE_entity = 18, RULE_entityDecl = 19, 
		RULE_entityName = 20, RULE_entityTemplateDecl = 21, RULE_entityBody = 22, 
		RULE_primarykeySingle = 23, RULE_primarykeyMultiple = 24, RULE_attributesDecl = 25, 
		RULE_attributes = 26, RULE_attributesBody = 27, RULE_attribute = 28, RULE_attributeBody = 29, 
		RULE_attributeName = 30, RULE_contentType = 31, RULE_attributeConstraint = 32, 
		RULE_attributeConstraintBody = 33, RULE_relationships = 34, RULE_relationshipsBody = 35, 
		RULE_relationshipStatement = 36, RULE_relationshipTemplateArg = 37, RULE_relationshipTemplateAs = 38, 
		RULE_relationshipReverseName = 39, RULE_relationshipIdName = 40, RULE_relationshipBody = 41, 
		RULE_view = 42, RULE_viewBlock = 43, RULE_viewAttributes = 44, RULE_viewAttributesBlock = 45, 
		RULE_viewRelationships = 46, RULE_viewRelationshipsBlock = 47, RULE_viewAttributeInclude = 48, 
		RULE_viewAttributeExclude = 49, RULE_viewRelationshipInclude = 50, RULE_viewRelationshipExclude = 51, 
		RULE_viewTaggedListItem = 52, RULE_viewTaggedList = 53, RULE_viewIdentifierList = 54, 
		RULE_enumStatement = 55, RULE_enumItem = 56, RULE_enumItemBody = 57, RULE_typedefStatement = 58, 
		RULE_typedefBody = 59, RULE_bitfield = 60, RULE_bitCount = 61, RULE_domain = 62, 
		RULE_domainBody = 63, RULE_namespaceIdent = 64, RULE_domainNamespace = 65, 
		RULE_domainFlattenSecondaryEntities = 66, RULE_domainEnum = 67, RULE_domainEnumBody = 68, 
		RULE_domainEnumItem = 69, RULE_domainEnumItemBody = 70, RULE_domainEnumItemRenameTo = 71, 
		RULE_domainEnumNamingItem = 72, RULE_domainEnumNamingBody = 73, RULE_domainTagging = 74, 
		RULE_domainTaggingTag = 75, RULE_domainTaggingTagValue = 76, RULE_domainTaggingTagValueType = 77, 
		RULE_domainNaming = 78, RULE_namingClass = 79, RULE_domainNamingBody = 80, 
		RULE_domainNamingMethod = 81, RULE_domainNamingPrefix = 82, RULE_domainNamingSuffix = 83, 
		RULE_domainNamingPrimaryKey = 84, RULE_domainNamingWithUnits = 85, RULE_domainNamingWithoutUnits = 86, 
		RULE_domainApplyTemplate = 87, RULE_domainModuleApplyTemplate = 88, RULE_domainEntityApplyTemplate = 89, 
		RULE_domainApplyTemplateBody = 90, RULE_defaultTemplateConfig = 91, RULE_templateConfig = 92, 
		RULE_jsonObj = 93, RULE_jsonPair = 94, RULE_jsonArr = 95, RULE_jsonValue = 96, 
		RULE_domainModule = 97, RULE_domainModuleBody = 98, RULE_domainView = 99, 
		RULE_domainEntity = 100, RULE_domainEntityBody = 101, RULE_domainEntityNamespace = 102, 
		RULE_domainAttributes = 103, RULE_domainAttributesBody = 104, RULE_domainAttributeExclude = 105, 
		RULE_domainAttributeAdd = 106, RULE_domainAttributesRenameTo = 107, RULE_domainAttributesRenameAppendPrepend = 108, 
		RULE_domainAttributeReplaces = 109, RULE_replacesBody = 110, RULE_domainIncludeEntities = 111, 
		RULE_domainExcludeEntities = 112, RULE_domainVirtualAttribute = 113, RULE_domainAttribute = 114, 
		RULE_domainAttributeBody = 115, RULE_domainRelationships = 116, RULE_domainRelationshipsBody = 117, 
		RULE_domainRelationship = 118, RULE_domainRelationshipBody = 119, RULE_abstractInterfaceStatement = 120, 
		RULE_interfaceBody = 121, RULE_interfaceType = 122, RULE_operation = 123, 
		RULE_operationBody = 124, RULE_operationConfig = 125, RULE_operationConfigBlock = 126, 
		RULE_operationConfigContext = 127, RULE_operationConfigContextType = 128, 
		RULE_operationConfigArgument = 129, RULE_operationConfigArgumentType = 130, 
		RULE_operationContextBlock = 131, RULE_operationArgumentBlock = 132, RULE_operationRequestMethod = 133, 
		RULE_operationRequestMethodBlock = 134, RULE_operationRequest = 135, RULE_operationRequestBlock = 136, 
		RULE_operationRequestEndpoint = 137, RULE_operationRequestEndpointBlock = 138, 
		RULE_operationRequestEndpointParam = 139, RULE_operationRequestEndpointParamBlock = 140, 
		RULE_operationRequestBody = 141, RULE_operationRequestBodyBlock = 142, 
		RULE_operationBodyContentType = 143, RULE_operationBodyView = 144, RULE_operationBodyDomain = 145, 
		RULE_operationResponse = 146, RULE_operationResponseBlock = 147, RULE_operationRequestStatusExpression = 148, 
		RULE_operationResponseStatus = 149, RULE_operationResponseStatusBlock = 150, 
		RULE_operationResponseBody = 151, RULE_operationResponseBodyBlock = 152, 
		RULE_domainInterfaceStatement = 153, RULE_domainInterfaceBody = 154, RULE_domainOperation = 155, 
		RULE_extendingOperationBody = 156, RULE_extendingOperationConfig = 157, 
		RULE_extendingOperationConfigBlock = 158, RULE_extendingOperationAssignment = 159, 
		RULE_domainOperationBody = 160, RULE_configuration = 161, RULE_configurationBody = 162, 
		RULE_formatting = 163, RULE_formattingBody = 164, RULE_formatStatement = 165, 
		RULE_directory = 166, RULE_outputBody = 167, RULE_outputPath = 168, RULE_templates = 169, 
		RULE_templatesBody = 170, RULE_template = 171, RULE_templateBody = 172, 
		RULE_templatesImport = 173, RULE_outputSpec = 174, RULE_transform = 175, 
		RULE_transformBody = 176, RULE_repository = 177, RULE_repositoryBody = 178, 
		RULE_repositoryType = 179, RULE_repositoryOrganization = 180, RULE_repositoryName = 181, 
		RULE_repositoryPath = 182, RULE_repositoryTag = 183, RULE_space = 184, 
		RULE_spaceBody = 185, RULE_spaceNamespace = 186, RULE_spaceMetadata = 187, 
		RULE_spaceImport = 188, RULE_spaceInclude = 189, RULE_spaceIncludeBody = 190, 
		RULE_spaceIncludeImportEnum = 191, RULE_spaceIncludeImportEntity = 192, 
		RULE_idList = 193, RULE_protoc = 194, RULE_protocBody = 195, RULE_protocLanguage = 196, 
		RULE_type = 197, RULE_attributeQualifier = 198, RULE_units = 199, RULE_unitsBody = 200, 
		RULE_unitDefinition = 201, RULE_unitDefinitionBody = 202, RULE_unitDefinitionField = 203, 
		RULE_language = 204, RULE_languageBody = 205, RULE_typesBody = 206, RULE_languageType = 207, 
		RULE_commentsBody = 208, RULE_functionsBody = 209, RULE_operatorsBody = 210, 
		RULE_version = 211;
	private static String[] makeRuleNames() {
		return new String[] {
			"id", "ident", "macro", "objid", "parExpression", "expressionList", "methodCall", 
			"expression", "primary", "constant", "typeType", "primitiveType", "root", 
			"descriptionStatement", "tagStatement", "tagItem", "module", "moduleBody", 
			"entity", "entityDecl", "entityName", "entityTemplateDecl", "entityBody", 
			"primarykeySingle", "primarykeyMultiple", "attributesDecl", "attributes", 
			"attributesBody", "attribute", "attributeBody", "attributeName", "contentType", 
			"attributeConstraint", "attributeConstraintBody", "relationships", "relationshipsBody", 
			"relationshipStatement", "relationshipTemplateArg", "relationshipTemplateAs", 
			"relationshipReverseName", "relationshipIdName", "relationshipBody", 
			"view", "viewBlock", "viewAttributes", "viewAttributesBlock", "viewRelationships", 
			"viewRelationshipsBlock", "viewAttributeInclude", "viewAttributeExclude", 
			"viewRelationshipInclude", "viewRelationshipExclude", "viewTaggedListItem", 
			"viewTaggedList", "viewIdentifierList", "enumStatement", "enumItem", 
			"enumItemBody", "typedefStatement", "typedefBody", "bitfield", "bitCount", 
			"domain", "domainBody", "namespaceIdent", "domainNamespace", "domainFlattenSecondaryEntities", 
			"domainEnum", "domainEnumBody", "domainEnumItem", "domainEnumItemBody", 
			"domainEnumItemRenameTo", "domainEnumNamingItem", "domainEnumNamingBody", 
			"domainTagging", "domainTaggingTag", "domainTaggingTagValue", "domainTaggingTagValueType", 
			"domainNaming", "namingClass", "domainNamingBody", "domainNamingMethod", 
			"domainNamingPrefix", "domainNamingSuffix", "domainNamingPrimaryKey", 
			"domainNamingWithUnits", "domainNamingWithoutUnits", "domainApplyTemplate", 
			"domainModuleApplyTemplate", "domainEntityApplyTemplate", "domainApplyTemplateBody", 
			"defaultTemplateConfig", "templateConfig", "jsonObj", "jsonPair", "jsonArr", 
			"jsonValue", "domainModule", "domainModuleBody", "domainView", "domainEntity", 
			"domainEntityBody", "domainEntityNamespace", "domainAttributes", "domainAttributesBody", 
			"domainAttributeExclude", "domainAttributeAdd", "domainAttributesRenameTo", 
			"domainAttributesRenameAppendPrepend", "domainAttributeReplaces", "replacesBody", 
			"domainIncludeEntities", "domainExcludeEntities", "domainVirtualAttribute", 
			"domainAttribute", "domainAttributeBody", "domainRelationships", "domainRelationshipsBody", 
			"domainRelationship", "domainRelationshipBody", "abstractInterfaceStatement", 
			"interfaceBody", "interfaceType", "operation", "operationBody", "operationConfig", 
			"operationConfigBlock", "operationConfigContext", "operationConfigContextType", 
			"operationConfigArgument", "operationConfigArgumentType", "operationContextBlock", 
			"operationArgumentBlock", "operationRequestMethod", "operationRequestMethodBlock", 
			"operationRequest", "operationRequestBlock", "operationRequestEndpoint", 
			"operationRequestEndpointBlock", "operationRequestEndpointParam", "operationRequestEndpointParamBlock", 
			"operationRequestBody", "operationRequestBodyBlock", "operationBodyContentType", 
			"operationBodyView", "operationBodyDomain", "operationResponse", "operationResponseBlock", 
			"operationRequestStatusExpression", "operationResponseStatus", "operationResponseStatusBlock", 
			"operationResponseBody", "operationResponseBodyBlock", "domainInterfaceStatement", 
			"domainInterfaceBody", "domainOperation", "extendingOperationBody", "extendingOperationConfig", 
			"extendingOperationConfigBlock", "extendingOperationAssignment", "domainOperationBody", 
			"configuration", "configurationBody", "formatting", "formattingBody", 
			"formatStatement", "directory", "outputBody", "outputPath", "templates", 
			"templatesBody", "template", "templateBody", "templatesImport", "outputSpec", 
			"transform", "transformBody", "repository", "repositoryBody", "repositoryType", 
			"repositoryOrganization", "repositoryName", "repositoryPath", "repositoryTag", 
			"space", "spaceBody", "spaceNamespace", "spaceMetadata", "spaceImport", 
			"spaceInclude", "spaceIncludeBody", "spaceIncludeImportEnum", "spaceIncludeImportEntity", 
			"idList", "protoc", "protocBody", "protocLanguage", "type", "attributeQualifier", 
			"units", "unitsBody", "unitDefinition", "unitDefinitionBody", "unitDefinitionField", 
			"language", "languageBody", "typesBody", "languageType", "commentsBody", 
			"functionsBody", "operatorsBody", "version"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "':'", "')'", "'.'", "'('", "','", "'['", "']'", "'+'", "'-'", 
			"'~'", "'!'", "'/'", "'%'", "'<='", "'>='", "'>'", "'<'", "'=='", "'!='", 
			"'&'", "'^'", "'|'", "'&&'", "'||'", "'?'", "'true'", "'false'", "'='", 
			"'{'", "'}'", "'null'", "'module'", "'entity'", "'primarykey'", "'attribute'", 
			"'unique'", "'creation'", "'modification'", "'required'", "'optional'", 
			"'parent'", "'ordered'", "'sequential'", "'virtual'", "'extern'", "'transient'", 
			"'primary'", "'secondary'", "'flatten'", "'relationships'", "'relationship'", 
			"'one'", "'many'", "'to-one'", "'to-many'", "'as'", "'entities'", "'enum'", 
			"'enumItem'", "'typedef'", "'unused'", "'configuration'", "'formatting'", 
			"'format'", "'output'", "'directory'", "'transform'", "'template'", "'templates'", 
			"'contextual'", "'path'", "'endpoint'", "'inherited'", "'repository'", 
			"'protoc'", "'tag'", "'value'", "'space'", "'import'", "'from'", "'organization'", 
			"'name'", "'nullable'", "'language'", "'version'", "'types'", "'*'", 
			"'self'", "'comments'", "'line'", "'blockStart'", "'blockEnd'", "'operators'", 
			"'keywords'", "'functions'", "'contentType'", "'constraint'", "'hints'", 
			"'short'", "'long'", "'human'", "'readable'", "'identification'", "'domain'", 
			"'attributes'", "'replaces'", "'method'", "'prefix'", "'suffix'", "'rename'", 
			"'tagging'", "'startsWith'", "'naming'", "'namespace'", "'in'", "'with'", 
			"'without'", "'read'", "'write'", "'when'", "'requires'", "'role'", "'user'", 
			"'if'", "'apply'", "'units'", "'abbr'", "'multiplier'", "'views'", "'default'", 
			"'view'", "'include'", "'exclude'", "'distill'", "'noparents'", "'add'", 
			"'and'", "'tagged'", "'abstract'", "'extends'", "'inherits'", "'interface'", 
			"'config'", "'metadata'", "'context'", "'argument'", "'operation'", "'request'", 
			"'response'", "'body'", "'query'", "'param'", "'status'", "'custom'", 
			"'type'", "'to'", "'prepend'", "'append'", "'boolean'", "'int32'", "'int64'", 
			"'byte'", "'uuid'", "'float'", "'double'", "'string'", "'date'", "'asset'", 
			"'array'", "'D'", "'T'", null, "'$('"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, "MODULE", "ENTITY", "PRIMARYKEY", 
			"ATTRIBUTE", "UNIQUE", "CREATION", "MODIFICATION", "REQUIRED", "OPTIONAL", 
			"PARENT", "ORDERED", "SEQUENTIAL", "VIRTUAL", "EXTERN", "TRANSIENT", 
			"PRIMARY", "SECONDARY", "FLATTEN", "RELATIONSHIPS", "RELATIONSHIP", "ONE", 
			"MANY", "TOONE", "TOMANY", "AS", "ENTITIES", "ENUM", "ENUMITEM", "TYPEDEF", 
			"UNUSED", "CONFIGURATION", "FORMATTING", "FORMAT", "OUTPUT", "DIRECTORY", 
			"TRANSFORM", "TEMPLATE", "TEMPLATES", "CONTEXTUAL", "PATH", "ENDPOINT", 
			"INHERITED", "REPOSITORY", "PROTOC", "TAG", "VALUE", "SPACE", "IMPORT", 
			"FROM", "ORGANIZATION", "NAME", "NULLABLE", "LANGUAGE", "VERSION", "TYPES", 
			"REF", "SELF", "COMMENTS", "LINE", "BLOCK_START", "BLOCK_END", "OPERATORS", 
			"KEYWORDS", "FUNCTIONS", "CONTENT_TYPE", "CONSTRAINT", "HINTS", "SHORT", 
			"LONG", "HUMAN", "READABLE", "IDENTIFICATION", "DOMAIN", "ATTRIBUTES", 
			"REPLACES", "METHOD", "PREFIX", "SUFFIX", "RENAME", "TAGGING", "STARTSWITH", 
			"NAMING", "NAMESPACE", "IN", "WITH", "WITHOUT", "READ", "WRITE", "WHEN", 
			"REQUIRES", "ROLE", "USER", "IF", "APPLY", "UNITS", "ABBR", "MULTIPLIER", 
			"VIEWS", "DEFAULT", "VIEW", "INCLUDE", "EXCLUDE", "DISTILL", "NOPARENTS", 
			"ADD", "AND", "TAGGED", "ABSTRACT", "EXTENDS", "INHERITS", "INTERFACE", 
			"CONFIG", "METADATA", "CONTEXT", "ARGUMENT", "OPERATION", "REQUEST", 
			"RESPONSE", "BODY", "QUERY", "PARAM", "STATUS", "CUSTOM", "TYPE", "TO", 
			"PREPEND", "APPEND", "BOOLEAN_TYPE", "INT32_TYPE", "INT64_TYPE", "BYTE_TYPE", 
			"UUID_TYPE", "FLOAT_TYPE", "DOUBLE_TYPE", "STRING_TYPE", "DATE_TYPE", 
			"ASSET_TYPE", "ARRAY", "DESCRIPTION", "TAGS", "INTEGER", "MACRO_START", 
			"STRING", "VERSIONNUM", "FLOAT", "ID", "IDX", "LETTER", "LINE_COMMENT", 
			"COMMENT", "WS", "ERRCHAR"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "EntityLanguage.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public EntityLanguageParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class IdContext extends ParserRuleContext {
		public MacroContext macro() {
			return getRuleContext(MacroContext.class,0);
		}
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public IdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdContext id() throws RecognitionException {
		IdContext _localctx = new IdContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_id);
		try {
			setState(426);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MACRO_START:
				enterOuterAlt(_localctx, 1);
				{
				setState(424);
				macro();
				}
				break;
			case MODULE:
			case ENTITY:
			case PRIMARYKEY:
			case ATTRIBUTE:
			case UNIQUE:
			case CREATION:
			case MODIFICATION:
			case PARENT:
			case ORDERED:
			case SEQUENTIAL:
			case EXTERN:
			case TRANSIENT:
			case PRIMARY:
			case SECONDARY:
			case FLATTEN:
			case RELATIONSHIPS:
			case RELATIONSHIP:
			case ENTITIES:
			case ENUM:
			case ENUMITEM:
			case TYPEDEF:
			case UNUSED:
			case CONFIGURATION:
			case FORMATTING:
			case FORMAT:
			case OUTPUT:
			case DIRECTORY:
			case TRANSFORM:
			case TEMPLATE:
			case PATH:
			case ENDPOINT:
			case INHERITED:
			case REPOSITORY:
			case TAG:
			case VALUE:
			case SPACE:
			case IMPORT:
			case FROM:
			case ORGANIZATION:
			case NAME:
			case NULLABLE:
			case LANGUAGE:
			case VERSION:
			case SELF:
			case OPERATORS:
			case KEYWORDS:
			case SHORT:
			case LONG:
			case HUMAN:
			case READABLE:
			case IDENTIFICATION:
			case DOMAIN:
			case ATTRIBUTES:
			case REPLACES:
			case PREFIX:
			case SUFFIX:
			case RENAME:
			case TAGGING:
			case NAMESPACE:
			case READ:
			case WRITE:
			case WHEN:
			case REQUIRES:
			case ROLE:
			case USER:
			case IF:
			case APPLY:
			case DEFAULT:
			case VIEW:
			case INCLUDE:
			case EXCLUDE:
			case AND:
			case ABSTRACT:
			case EXTENDS:
			case INTERFACE:
			case CONFIG:
			case METADATA:
			case CONTEXT:
			case ARGUMENT:
			case OPERATION:
			case QUERY:
			case PARAM:
			case STATUS:
			case CUSTOM:
			case TYPE:
			case TO:
			case PREPEND:
			case APPEND:
			case BOOLEAN_TYPE:
			case INT32_TYPE:
			case INT64_TYPE:
			case BYTE_TYPE:
			case UUID_TYPE:
			case FLOAT_TYPE:
			case DOUBLE_TYPE:
			case STRING_TYPE:
			case DATE_TYPE:
			case ASSET_TYPE:
			case ARRAY:
			case DESCRIPTION:
			case TAGS:
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(425);
				ident();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(EntityLanguageParser.ID, 0); }
		public TerminalNode BOOLEAN_TYPE() { return getToken(EntityLanguageParser.BOOLEAN_TYPE, 0); }
		public TerminalNode INT32_TYPE() { return getToken(EntityLanguageParser.INT32_TYPE, 0); }
		public TerminalNode INT64_TYPE() { return getToken(EntityLanguageParser.INT64_TYPE, 0); }
		public TerminalNode FLOAT_TYPE() { return getToken(EntityLanguageParser.FLOAT_TYPE, 0); }
		public TerminalNode DOUBLE_TYPE() { return getToken(EntityLanguageParser.DOUBLE_TYPE, 0); }
		public TerminalNode UUID_TYPE() { return getToken(EntityLanguageParser.UUID_TYPE, 0); }
		public TerminalNode BYTE_TYPE() { return getToken(EntityLanguageParser.BYTE_TYPE, 0); }
		public TerminalNode STRING_TYPE() { return getToken(EntityLanguageParser.STRING_TYPE, 0); }
		public TerminalNode DATE_TYPE() { return getToken(EntityLanguageParser.DATE_TYPE, 0); }
		public TerminalNode ASSET_TYPE() { return getToken(EntityLanguageParser.ASSET_TYPE, 0); }
		public TerminalNode SELF() { return getToken(EntityLanguageParser.SELF, 0); }
		public TerminalNode EXTERN() { return getToken(EntityLanguageParser.EXTERN, 0); }
		public TerminalNode CONFIGURATION() { return getToken(EntityLanguageParser.CONFIGURATION, 0); }
		public TerminalNode OUTPUT() { return getToken(EntityLanguageParser.OUTPUT, 0); }
		public TerminalNode DIRECTORY() { return getToken(EntityLanguageParser.DIRECTORY, 0); }
		public TerminalNode TEMPLATE() { return getToken(EntityLanguageParser.TEMPLATE, 0); }
		public TerminalNode TRANSFORM() { return getToken(EntityLanguageParser.TRANSFORM, 0); }
		public TerminalNode PATH() { return getToken(EntityLanguageParser.PATH, 0); }
		public TerminalNode INHERITED() { return getToken(EntityLanguageParser.INHERITED, 0); }
		public TerminalNode NULLABLE() { return getToken(EntityLanguageParser.NULLABLE, 0); }
		public TerminalNode PRIMARYKEY() { return getToken(EntityLanguageParser.PRIMARYKEY, 0); }
		public TerminalNode UNIQUE() { return getToken(EntityLanguageParser.UNIQUE, 0); }
		public TerminalNode CREATION() { return getToken(EntityLanguageParser.CREATION, 0); }
		public TerminalNode MODIFICATION() { return getToken(EntityLanguageParser.MODIFICATION, 0); }
		public TerminalNode SEQUENTIAL() { return getToken(EntityLanguageParser.SEQUENTIAL, 0); }
		public TerminalNode PARENT() { return getToken(EntityLanguageParser.PARENT, 0); }
		public TerminalNode ORDERED() { return getToken(EntityLanguageParser.ORDERED, 0); }
		public TerminalNode PRIMARY() { return getToken(EntityLanguageParser.PRIMARY, 0); }
		public TerminalNode SECONDARY() { return getToken(EntityLanguageParser.SECONDARY, 0); }
		public TerminalNode FLATTEN() { return getToken(EntityLanguageParser.FLATTEN, 0); }
		public TerminalNode TRANSIENT() { return getToken(EntityLanguageParser.TRANSIENT, 0); }
		public TerminalNode RELATIONSHIPS() { return getToken(EntityLanguageParser.RELATIONSHIPS, 0); }
		public TerminalNode RELATIONSHIP() { return getToken(EntityLanguageParser.RELATIONSHIP, 0); }
		public TerminalNode LANGUAGE() { return getToken(EntityLanguageParser.LANGUAGE, 0); }
		public TerminalNode TYPEDEF() { return getToken(EntityLanguageParser.TYPEDEF, 0); }
		public TerminalNode UNUSED() { return getToken(EntityLanguageParser.UNUSED, 0); }
		public TerminalNode DEFAULT() { return getToken(EntityLanguageParser.DEFAULT, 0); }
		public TerminalNode EXTENDS() { return getToken(EntityLanguageParser.EXTENDS, 0); }
		public TerminalNode NAMESPACE() { return getToken(EntityLanguageParser.NAMESPACE, 0); }
		public TerminalNode ENTITIES() { return getToken(EntityLanguageParser.ENTITIES, 0); }
		public TerminalNode MODULE() { return getToken(EntityLanguageParser.MODULE, 0); }
		public TerminalNode IF() { return getToken(EntityLanguageParser.IF, 0); }
		public TerminalNode OPERATORS() { return getToken(EntityLanguageParser.OPERATORS, 0); }
		public TerminalNode KEYWORDS() { return getToken(EntityLanguageParser.KEYWORDS, 0); }
		public TerminalNode ABSTRACT() { return getToken(EntityLanguageParser.ABSTRACT, 0); }
		public TerminalNode REPOSITORY() { return getToken(EntityLanguageParser.REPOSITORY, 0); }
		public TerminalNode TAG() { return getToken(EntityLanguageParser.TAG, 0); }
		public TerminalNode SPACE() { return getToken(EntityLanguageParser.SPACE, 0); }
		public TerminalNode IMPORT() { return getToken(EntityLanguageParser.IMPORT, 0); }
		public TerminalNode FROM() { return getToken(EntityLanguageParser.FROM, 0); }
		public TerminalNode AND() { return getToken(EntityLanguageParser.AND, 0); }
		public TerminalNode TAGGING() { return getToken(EntityLanguageParser.TAGGING, 0); }
		public TerminalNode VALUE() { return getToken(EntityLanguageParser.VALUE, 0); }
		public TerminalNode ENTITY() { return getToken(EntityLanguageParser.ENTITY, 0); }
		public TerminalNode ATTRIBUTE() { return getToken(EntityLanguageParser.ATTRIBUTE, 0); }
		public TerminalNode ENUM() { return getToken(EntityLanguageParser.ENUM, 0); }
		public TerminalNode ENUMITEM() { return getToken(EntityLanguageParser.ENUMITEM, 0); }
		public TerminalNode DOMAIN() { return getToken(EntityLanguageParser.DOMAIN, 0); }
		public TerminalNode RENAME() { return getToken(EntityLanguageParser.RENAME, 0); }
		public TerminalNode TO() { return getToken(EntityLanguageParser.TO, 0); }
		public TerminalNode PREPEND() { return getToken(EntityLanguageParser.PREPEND, 0); }
		public TerminalNode APPEND() { return getToken(EntityLanguageParser.APPEND, 0); }
		public TerminalNode VIEW() { return getToken(EntityLanguageParser.VIEW, 0); }
		public TerminalNode INCLUDE() { return getToken(EntityLanguageParser.INCLUDE, 0); }
		public TerminalNode EXCLUDE() { return getToken(EntityLanguageParser.EXCLUDE, 0); }
		public TerminalNode ATTRIBUTES() { return getToken(EntityLanguageParser.ATTRIBUTES, 0); }
		public TerminalNode REPLACES() { return getToken(EntityLanguageParser.REPLACES, 0); }
		public TerminalNode PREFIX() { return getToken(EntityLanguageParser.PREFIX, 0); }
		public TerminalNode SUFFIX() { return getToken(EntityLanguageParser.SUFFIX, 0); }
		public TerminalNode VERSION() { return getToken(EntityLanguageParser.VERSION, 0); }
		public TerminalNode SHORT() { return getToken(EntityLanguageParser.SHORT, 0); }
		public TerminalNode LONG() { return getToken(EntityLanguageParser.LONG, 0); }
		public TerminalNode INTERFACE() { return getToken(EntityLanguageParser.INTERFACE, 0); }
		public TerminalNode OPERATION() { return getToken(EntityLanguageParser.OPERATION, 0); }
		public TerminalNode QUERY() { return getToken(EntityLanguageParser.QUERY, 0); }
		public TerminalNode STATUS() { return getToken(EntityLanguageParser.STATUS, 0); }
		public TerminalNode CUSTOM() { return getToken(EntityLanguageParser.CUSTOM, 0); }
		public TerminalNode PARAM() { return getToken(EntityLanguageParser.PARAM, 0); }
		public TerminalNode TYPE() { return getToken(EntityLanguageParser.TYPE, 0); }
		public TerminalNode ENDPOINT() { return getToken(EntityLanguageParser.ENDPOINT, 0); }
		public TerminalNode CONFIG() { return getToken(EntityLanguageParser.CONFIG, 0); }
		public TerminalNode CONTEXT() { return getToken(EntityLanguageParser.CONTEXT, 0); }
		public TerminalNode ARGUMENT() { return getToken(EntityLanguageParser.ARGUMENT, 0); }
		public TerminalNode HUMAN() { return getToken(EntityLanguageParser.HUMAN, 0); }
		public TerminalNode READABLE() { return getToken(EntityLanguageParser.READABLE, 0); }
		public TerminalNode IDENTIFICATION() { return getToken(EntityLanguageParser.IDENTIFICATION, 0); }
		public TerminalNode NAME() { return getToken(EntityLanguageParser.NAME, 0); }
		public TerminalNode ORGANIZATION() { return getToken(EntityLanguageParser.ORGANIZATION, 0); }
		public TerminalNode REQUIRES() { return getToken(EntityLanguageParser.REQUIRES, 0); }
		public TerminalNode ROLE() { return getToken(EntityLanguageParser.ROLE, 0); }
		public TerminalNode READ() { return getToken(EntityLanguageParser.READ, 0); }
		public TerminalNode WRITE() { return getToken(EntityLanguageParser.WRITE, 0); }
		public TerminalNode WHEN() { return getToken(EntityLanguageParser.WHEN, 0); }
		public TerminalNode USER() { return getToken(EntityLanguageParser.USER, 0); }
		public TerminalNode ARRAY() { return getToken(EntityLanguageParser.ARRAY, 0); }
		public TerminalNode APPLY() { return getToken(EntityLanguageParser.APPLY, 0); }
		public TerminalNode DESCRIPTION() { return getToken(EntityLanguageParser.DESCRIPTION, 0); }
		public TerminalNode TAGS() { return getToken(EntityLanguageParser.TAGS, 0); }
		public TerminalNode METADATA() { return getToken(EntityLanguageParser.METADATA, 0); }
		public TerminalNode FORMATTING() { return getToken(EntityLanguageParser.FORMATTING, 0); }
		public TerminalNode FORMAT() { return getToken(EntityLanguageParser.FORMAT, 0); }
		public IdentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ident; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitIdent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentContext ident() throws RecognitionException {
		IdentContext _localctx = new IdentContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_ident);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(428);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MODULE) | (1L << ENTITY) | (1L << PRIMARYKEY) | (1L << ATTRIBUTE) | (1L << UNIQUE) | (1L << CREATION) | (1L << MODIFICATION) | (1L << PARENT) | (1L << ORDERED) | (1L << SEQUENTIAL) | (1L << EXTERN) | (1L << TRANSIENT) | (1L << PRIMARY) | (1L << SECONDARY) | (1L << FLATTEN) | (1L << RELATIONSHIPS) | (1L << RELATIONSHIP) | (1L << ENTITIES) | (1L << ENUM) | (1L << ENUMITEM) | (1L << TYPEDEF) | (1L << UNUSED) | (1L << CONFIGURATION) | (1L << FORMATTING))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (FORMAT - 64)) | (1L << (OUTPUT - 64)) | (1L << (DIRECTORY - 64)) | (1L << (TRANSFORM - 64)) | (1L << (TEMPLATE - 64)) | (1L << (PATH - 64)) | (1L << (ENDPOINT - 64)) | (1L << (INHERITED - 64)) | (1L << (REPOSITORY - 64)) | (1L << (TAG - 64)) | (1L << (VALUE - 64)) | (1L << (SPACE - 64)) | (1L << (IMPORT - 64)) | (1L << (FROM - 64)) | (1L << (ORGANIZATION - 64)) | (1L << (NAME - 64)) | (1L << (NULLABLE - 64)) | (1L << (LANGUAGE - 64)) | (1L << (VERSION - 64)) | (1L << (SELF - 64)) | (1L << (OPERATORS - 64)) | (1L << (KEYWORDS - 64)) | (1L << (SHORT - 64)) | (1L << (LONG - 64)) | (1L << (HUMAN - 64)) | (1L << (READABLE - 64)) | (1L << (IDENTIFICATION - 64)) | (1L << (DOMAIN - 64)) | (1L << (ATTRIBUTES - 64)) | (1L << (REPLACES - 64)) | (1L << (PREFIX - 64)) | (1L << (SUFFIX - 64)) | (1L << (RENAME - 64)) | (1L << (TAGGING - 64)) | (1L << (NAMESPACE - 64)) | (1L << (READ - 64)) | (1L << (WRITE - 64)) | (1L << (WHEN - 64)) | (1L << (REQUIRES - 64)) | (1L << (ROLE - 64)) | (1L << (USER - 64)) | (1L << (IF - 64)) | (1L << (APPLY - 64)))) != 0) || ((((_la - 130)) & ~0x3f) == 0 && ((1L << (_la - 130)) & ((1L << (DEFAULT - 130)) | (1L << (VIEW - 130)) | (1L << (INCLUDE - 130)) | (1L << (EXCLUDE - 130)) | (1L << (AND - 130)) | (1L << (ABSTRACT - 130)) | (1L << (EXTENDS - 130)) | (1L << (INTERFACE - 130)) | (1L << (CONFIG - 130)) | (1L << (METADATA - 130)) | (1L << (CONTEXT - 130)) | (1L << (ARGUMENT - 130)) | (1L << (OPERATION - 130)) | (1L << (QUERY - 130)) | (1L << (PARAM - 130)) | (1L << (STATUS - 130)) | (1L << (CUSTOM - 130)) | (1L << (TYPE - 130)) | (1L << (TO - 130)) | (1L << (PREPEND - 130)) | (1L << (APPEND - 130)) | (1L << (BOOLEAN_TYPE - 130)) | (1L << (INT32_TYPE - 130)) | (1L << (INT64_TYPE - 130)) | (1L << (BYTE_TYPE - 130)) | (1L << (UUID_TYPE - 130)) | (1L << (FLOAT_TYPE - 130)) | (1L << (DOUBLE_TYPE - 130)) | (1L << (STRING_TYPE - 130)) | (1L << (DATE_TYPE - 130)) | (1L << (ASSET_TYPE - 130)) | (1L << (ARRAY - 130)) | (1L << (DESCRIPTION - 130)) | (1L << (TAGS - 130)) | (1L << (ID - 130)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MacroContext extends ParserRuleContext {
		public TerminalNode MACRO_START() { return getToken(EntityLanguageParser.MACRO_START, 0); }
		public List<IdentContext> ident() {
			return getRuleContexts(IdentContext.class);
		}
		public IdentContext ident(int i) {
			return getRuleContext(IdentContext.class,i);
		}
		public TerminalNode STRING() { return getToken(EntityLanguageParser.STRING, 0); }
		public MacroContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_macro; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitMacro(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MacroContext macro() throws RecognitionException {
		MacroContext _localctx = new MacroContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_macro);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(430);
			match(MACRO_START);
			setState(431);
			ident();
			setState(437);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(432);
				match(T__0);
				setState(435);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case MODULE:
				case ENTITY:
				case PRIMARYKEY:
				case ATTRIBUTE:
				case UNIQUE:
				case CREATION:
				case MODIFICATION:
				case PARENT:
				case ORDERED:
				case SEQUENTIAL:
				case EXTERN:
				case TRANSIENT:
				case PRIMARY:
				case SECONDARY:
				case FLATTEN:
				case RELATIONSHIPS:
				case RELATIONSHIP:
				case ENTITIES:
				case ENUM:
				case ENUMITEM:
				case TYPEDEF:
				case UNUSED:
				case CONFIGURATION:
				case FORMATTING:
				case FORMAT:
				case OUTPUT:
				case DIRECTORY:
				case TRANSFORM:
				case TEMPLATE:
				case PATH:
				case ENDPOINT:
				case INHERITED:
				case REPOSITORY:
				case TAG:
				case VALUE:
				case SPACE:
				case IMPORT:
				case FROM:
				case ORGANIZATION:
				case NAME:
				case NULLABLE:
				case LANGUAGE:
				case VERSION:
				case SELF:
				case OPERATORS:
				case KEYWORDS:
				case SHORT:
				case LONG:
				case HUMAN:
				case READABLE:
				case IDENTIFICATION:
				case DOMAIN:
				case ATTRIBUTES:
				case REPLACES:
				case PREFIX:
				case SUFFIX:
				case RENAME:
				case TAGGING:
				case NAMESPACE:
				case READ:
				case WRITE:
				case WHEN:
				case REQUIRES:
				case ROLE:
				case USER:
				case IF:
				case APPLY:
				case DEFAULT:
				case VIEW:
				case INCLUDE:
				case EXCLUDE:
				case AND:
				case ABSTRACT:
				case EXTENDS:
				case INTERFACE:
				case CONFIG:
				case METADATA:
				case CONTEXT:
				case ARGUMENT:
				case OPERATION:
				case QUERY:
				case PARAM:
				case STATUS:
				case CUSTOM:
				case TYPE:
				case TO:
				case PREPEND:
				case APPEND:
				case BOOLEAN_TYPE:
				case INT32_TYPE:
				case INT64_TYPE:
				case BYTE_TYPE:
				case UUID_TYPE:
				case FLOAT_TYPE:
				case DOUBLE_TYPE:
				case STRING_TYPE:
				case DATE_TYPE:
				case ASSET_TYPE:
				case ARRAY:
				case DESCRIPTION:
				case TAGS:
				case ID:
					{
					setState(433);
					ident();
					}
					break;
				case STRING:
					{
					setState(434);
					match(STRING);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
			}

			setState(439);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ObjidContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(EntityLanguageParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(EntityLanguageParser.ID, i);
		}
		public ObjidContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objid; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitObjid(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjidContext objid() throws RecognitionException {
		ObjidContext _localctx = new ObjidContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_objid);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(441);
			match(ID);
			setState(446);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(442);
				match(T__2);
				setState(443);
				match(ID);
				}
				}
				setState(448);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParExpressionContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parExpression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitParExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParExpressionContext parExpression() throws RecognitionException {
		ParExpressionContext _localctx = new ParExpressionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_parExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(449);
			match(T__3);
			setState(450);
			expression(0);
			setState(451);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExpressionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitExpressionList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionListContext expressionList() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_expressionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(453);
			expression(0);
			setState(458);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(454);
				match(T__4);
				setState(455);
				expression(0);
				}
				}
				setState(460);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodCallContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(EntityLanguageParser.ID, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public MethodCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodCall; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitMethodCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodCallContext methodCall() throws RecognitionException {
		MethodCallContext _localctx = new MethodCallContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_methodCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(461);
			match(ID);
			setState(462);
			match(T__3);
			setState(464);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__25) | (1L << T__26) | (1L << MODULE) | (1L << ENTITY) | (1L << PRIMARYKEY) | (1L << ATTRIBUTE) | (1L << UNIQUE) | (1L << CREATION) | (1L << MODIFICATION) | (1L << PARENT) | (1L << ORDERED) | (1L << SEQUENTIAL) | (1L << EXTERN) | (1L << TRANSIENT) | (1L << PRIMARY) | (1L << SECONDARY) | (1L << FLATTEN) | (1L << RELATIONSHIPS) | (1L << RELATIONSHIP) | (1L << ENTITIES) | (1L << ENUM) | (1L << ENUMITEM) | (1L << TYPEDEF) | (1L << UNUSED) | (1L << CONFIGURATION) | (1L << FORMATTING))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (FORMAT - 64)) | (1L << (OUTPUT - 64)) | (1L << (DIRECTORY - 64)) | (1L << (TRANSFORM - 64)) | (1L << (TEMPLATE - 64)) | (1L << (PATH - 64)) | (1L << (ENDPOINT - 64)) | (1L << (INHERITED - 64)) | (1L << (REPOSITORY - 64)) | (1L << (TAG - 64)) | (1L << (VALUE - 64)) | (1L << (SPACE - 64)) | (1L << (IMPORT - 64)) | (1L << (FROM - 64)) | (1L << (ORGANIZATION - 64)) | (1L << (NAME - 64)) | (1L << (NULLABLE - 64)) | (1L << (LANGUAGE - 64)) | (1L << (VERSION - 64)) | (1L << (SELF - 64)) | (1L << (OPERATORS - 64)) | (1L << (KEYWORDS - 64)) | (1L << (SHORT - 64)) | (1L << (LONG - 64)) | (1L << (HUMAN - 64)) | (1L << (READABLE - 64)) | (1L << (IDENTIFICATION - 64)) | (1L << (DOMAIN - 64)) | (1L << (ATTRIBUTES - 64)) | (1L << (REPLACES - 64)) | (1L << (PREFIX - 64)) | (1L << (SUFFIX - 64)) | (1L << (RENAME - 64)) | (1L << (TAGGING - 64)) | (1L << (NAMESPACE - 64)) | (1L << (READ - 64)) | (1L << (WRITE - 64)) | (1L << (WHEN - 64)) | (1L << (REQUIRES - 64)) | (1L << (ROLE - 64)) | (1L << (USER - 64)) | (1L << (IF - 64)) | (1L << (APPLY - 64)))) != 0) || ((((_la - 130)) & ~0x3f) == 0 && ((1L << (_la - 130)) & ((1L << (DEFAULT - 130)) | (1L << (VIEW - 130)) | (1L << (INCLUDE - 130)) | (1L << (EXCLUDE - 130)) | (1L << (AND - 130)) | (1L << (ABSTRACT - 130)) | (1L << (EXTENDS - 130)) | (1L << (INTERFACE - 130)) | (1L << (CONFIG - 130)) | (1L << (METADATA - 130)) | (1L << (CONTEXT - 130)) | (1L << (ARGUMENT - 130)) | (1L << (OPERATION - 130)) | (1L << (QUERY - 130)) | (1L << (PARAM - 130)) | (1L << (STATUS - 130)) | (1L << (CUSTOM - 130)) | (1L << (TYPE - 130)) | (1L << (TO - 130)) | (1L << (PREPEND - 130)) | (1L << (APPEND - 130)) | (1L << (BOOLEAN_TYPE - 130)) | (1L << (INT32_TYPE - 130)) | (1L << (INT64_TYPE - 130)) | (1L << (BYTE_TYPE - 130)) | (1L << (UUID_TYPE - 130)) | (1L << (FLOAT_TYPE - 130)) | (1L << (DOUBLE_TYPE - 130)) | (1L << (STRING_TYPE - 130)) | (1L << (DATE_TYPE - 130)) | (1L << (ASSET_TYPE - 130)) | (1L << (ARRAY - 130)) | (1L << (DESCRIPTION - 130)) | (1L << (TAGS - 130)) | (1L << (INTEGER - 130)) | (1L << (STRING - 130)) | (1L << (FLOAT - 130)) | (1L << (ID - 130)))) != 0)) {
				{
				setState(463);
				expressionList();
				}
			}

			setState(466);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public Token prefix;
		public Token bop;
		public MethodCallContext methodCall() {
			return getRuleContext(MethodCallContext.class,0);
		}
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode REF() { return getToken(EntityLanguageParser.REF, 0); }
		public TerminalNode ID() { return getToken(EntityLanguageParser.ID, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(480);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(469);
				methodCall();
				}
				break;
			case 2:
				{
				setState(470);
				primary();
				}
				break;
			case 3:
				{
				setState(471);
				match(T__3);
				setState(472);
				typeType();
				setState(473);
				match(T__1);
				setState(474);
				expression(13);
				}
				break;
			case 4:
				{
				setState(476);
				((ExpressionContext)_localctx).prefix = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__7 || _la==T__8) ) {
					((ExpressionContext)_localctx).prefix = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(477);
				expression(12);
				}
				break;
			case 5:
				{
				setState(478);
				((ExpressionContext)_localctx).prefix = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__9 || _la==T__10) ) {
					((ExpressionContext)_localctx).prefix = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(479);
				expression(11);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(529);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(527);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(482);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(483);
						((ExpressionContext)_localctx).bop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__11 || _la==T__12 || _la==REF) ) {
							((ExpressionContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(484);
						expression(11);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(485);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(486);
						((ExpressionContext)_localctx).bop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__7 || _la==T__8) ) {
							((ExpressionContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(487);
						expression(10);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(488);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(489);
						((ExpressionContext)_localctx).bop = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16))) != 0)) ) {
							((ExpressionContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(490);
						expression(9);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(491);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(492);
						((ExpressionContext)_localctx).bop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__17 || _la==T__18) ) {
							((ExpressionContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(493);
						expression(8);
						}
						break;
					case 5:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(494);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(495);
						((ExpressionContext)_localctx).bop = match(T__19);
						setState(496);
						expression(7);
						}
						break;
					case 6:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(497);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(498);
						((ExpressionContext)_localctx).bop = match(T__20);
						setState(499);
						expression(6);
						}
						break;
					case 7:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(500);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(501);
						((ExpressionContext)_localctx).bop = match(T__21);
						setState(502);
						expression(5);
						}
						break;
					case 8:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(503);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(504);
						((ExpressionContext)_localctx).bop = match(T__22);
						setState(505);
						expression(4);
						}
						break;
					case 9:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(506);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(507);
						((ExpressionContext)_localctx).bop = match(T__23);
						setState(508);
						expression(3);
						}
						break;
					case 10:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(509);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(510);
						((ExpressionContext)_localctx).bop = match(T__24);
						setState(511);
						expression(0);
						setState(512);
						match(T__0);
						setState(513);
						expression(0);
						setState(514);
						expression(1);
						}
						break;
					case 11:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(516);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(517);
						((ExpressionContext)_localctx).bop = match(T__2);
						setState(520);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
						case 1:
							{
							setState(518);
							match(ID);
							}
							break;
						case 2:
							{
							setState(519);
							methodCall();
							}
							break;
						}
						}
						break;
					case 12:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(522);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(523);
						match(T__5);
						setState(524);
						expression(0);
						setState(525);
						match(T__6);
						}
						break;
					}
					} 
				}
				setState(531);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class PrimaryContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitPrimary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_primary);
		try {
			setState(538);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
				enterOuterAlt(_localctx, 1);
				{
				setState(532);
				match(T__3);
				setState(533);
				expression(0);
				setState(534);
				match(T__1);
				}
				break;
			case T__25:
			case T__26:
			case INTEGER:
			case STRING:
			case FLOAT:
				enterOuterAlt(_localctx, 2);
				{
				setState(536);
				constant();
				}
				break;
			case MODULE:
			case ENTITY:
			case PRIMARYKEY:
			case ATTRIBUTE:
			case UNIQUE:
			case CREATION:
			case MODIFICATION:
			case PARENT:
			case ORDERED:
			case SEQUENTIAL:
			case EXTERN:
			case TRANSIENT:
			case PRIMARY:
			case SECONDARY:
			case FLATTEN:
			case RELATIONSHIPS:
			case RELATIONSHIP:
			case ENTITIES:
			case ENUM:
			case ENUMITEM:
			case TYPEDEF:
			case UNUSED:
			case CONFIGURATION:
			case FORMATTING:
			case FORMAT:
			case OUTPUT:
			case DIRECTORY:
			case TRANSFORM:
			case TEMPLATE:
			case PATH:
			case ENDPOINT:
			case INHERITED:
			case REPOSITORY:
			case TAG:
			case VALUE:
			case SPACE:
			case IMPORT:
			case FROM:
			case ORGANIZATION:
			case NAME:
			case NULLABLE:
			case LANGUAGE:
			case VERSION:
			case SELF:
			case OPERATORS:
			case KEYWORDS:
			case SHORT:
			case LONG:
			case HUMAN:
			case READABLE:
			case IDENTIFICATION:
			case DOMAIN:
			case ATTRIBUTES:
			case REPLACES:
			case PREFIX:
			case SUFFIX:
			case RENAME:
			case TAGGING:
			case NAMESPACE:
			case READ:
			case WRITE:
			case WHEN:
			case REQUIRES:
			case ROLE:
			case USER:
			case IF:
			case APPLY:
			case DEFAULT:
			case VIEW:
			case INCLUDE:
			case EXCLUDE:
			case AND:
			case ABSTRACT:
			case EXTENDS:
			case INTERFACE:
			case CONFIG:
			case METADATA:
			case CONTEXT:
			case ARGUMENT:
			case OPERATION:
			case QUERY:
			case PARAM:
			case STATUS:
			case CUSTOM:
			case TYPE:
			case TO:
			case PREPEND:
			case APPEND:
			case BOOLEAN_TYPE:
			case INT32_TYPE:
			case INT64_TYPE:
			case BYTE_TYPE:
			case UUID_TYPE:
			case FLOAT_TYPE:
			case DOUBLE_TYPE:
			case STRING_TYPE:
			case DATE_TYPE:
			case ASSET_TYPE:
			case ARRAY:
			case DESCRIPTION:
			case TAGS:
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(537);
				ident();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstantContext extends ParserRuleContext {
		public Token BOOLEAN;
		public TerminalNode INTEGER() { return getToken(EntityLanguageParser.INTEGER, 0); }
		public TerminalNode FLOAT() { return getToken(EntityLanguageParser.FLOAT, 0); }
		public TerminalNode STRING() { return getToken(EntityLanguageParser.STRING, 0); }
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_constant);
		int _la;
		try {
			setState(544);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTEGER:
				enterOuterAlt(_localctx, 1);
				{
				setState(540);
				match(INTEGER);
				}
				break;
			case FLOAT:
				enterOuterAlt(_localctx, 2);
				{
				setState(541);
				match(FLOAT);
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(542);
				match(STRING);
				}
				break;
			case T__25:
			case T__26:
				enterOuterAlt(_localctx, 4);
				{
				setState(543);
				((ConstantContext)_localctx).BOOLEAN = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__25 || _la==T__26) ) {
					((ConstantContext)_localctx).BOOLEAN = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeTypeContext extends ParserRuleContext {
		public PrimitiveTypeContext primitiveType() {
			return getRuleContext(PrimitiveTypeContext.class,0);
		}
		public TypeTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitTypeType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeTypeContext typeType() throws RecognitionException {
		TypeTypeContext _localctx = new TypeTypeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_typeType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(546);
			primitiveType();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrimitiveTypeContext extends ParserRuleContext {
		public TerminalNode BOOLEAN_TYPE() { return getToken(EntityLanguageParser.BOOLEAN_TYPE, 0); }
		public TerminalNode INT32_TYPE() { return getToken(EntityLanguageParser.INT32_TYPE, 0); }
		public TerminalNode INT64_TYPE() { return getToken(EntityLanguageParser.INT64_TYPE, 0); }
		public TerminalNode FLOAT_TYPE() { return getToken(EntityLanguageParser.FLOAT_TYPE, 0); }
		public TerminalNode DOUBLE_TYPE() { return getToken(EntityLanguageParser.DOUBLE_TYPE, 0); }
		public TerminalNode STRING_TYPE() { return getToken(EntityLanguageParser.STRING_TYPE, 0); }
		public TerminalNode BYTE_TYPE() { return getToken(EntityLanguageParser.BYTE_TYPE, 0); }
		public TerminalNode INTEGER() { return getToken(EntityLanguageParser.INTEGER, 0); }
		public PrimitiveTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primitiveType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitPrimitiveType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimitiveTypeContext primitiveType() throws RecognitionException {
		PrimitiveTypeContext _localctx = new PrimitiveTypeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_primitiveType);
		int _la;
		try {
			setState(560);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOLEAN_TYPE:
				enterOuterAlt(_localctx, 1);
				{
				setState(548);
				match(BOOLEAN_TYPE);
				}
				break;
			case INT32_TYPE:
				enterOuterAlt(_localctx, 2);
				{
				setState(549);
				match(INT32_TYPE);
				}
				break;
			case INT64_TYPE:
				enterOuterAlt(_localctx, 3);
				{
				setState(550);
				match(INT64_TYPE);
				}
				break;
			case FLOAT_TYPE:
				enterOuterAlt(_localctx, 4);
				{
				setState(551);
				match(FLOAT_TYPE);
				}
				break;
			case DOUBLE_TYPE:
				enterOuterAlt(_localctx, 5);
				{
				setState(552);
				match(DOUBLE_TYPE);
				}
				break;
			case STRING_TYPE:
				enterOuterAlt(_localctx, 6);
				{
				setState(553);
				match(STRING_TYPE);
				}
				break;
			case BYTE_TYPE:
				enterOuterAlt(_localctx, 7);
				{
				setState(554);
				match(BYTE_TYPE);
				setState(555);
				match(T__5);
				setState(557);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==INTEGER) {
					{
					setState(556);
					match(INTEGER);
					}
				}

				setState(559);
				match(T__6);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RootContext extends ParserRuleContext {
		public List<SpaceContext> space() {
			return getRuleContexts(SpaceContext.class);
		}
		public SpaceContext space(int i) {
			return getRuleContext(SpaceContext.class,i);
		}
		public List<ModuleContext> module() {
			return getRuleContexts(ModuleContext.class);
		}
		public ModuleContext module(int i) {
			return getRuleContext(ModuleContext.class,i);
		}
		public List<EntityContext> entity() {
			return getRuleContexts(EntityContext.class);
		}
		public EntityContext entity(int i) {
			return getRuleContext(EntityContext.class,i);
		}
		public List<EnumStatementContext> enumStatement() {
			return getRuleContexts(EnumStatementContext.class);
		}
		public EnumStatementContext enumStatement(int i) {
			return getRuleContext(EnumStatementContext.class,i);
		}
		public List<DomainContext> domain() {
			return getRuleContexts(DomainContext.class);
		}
		public DomainContext domain(int i) {
			return getRuleContext(DomainContext.class,i);
		}
		public List<ConfigurationContext> configuration() {
			return getRuleContexts(ConfigurationContext.class);
		}
		public ConfigurationContext configuration(int i) {
			return getRuleContext(ConfigurationContext.class,i);
		}
		public List<UnitsContext> units() {
			return getRuleContexts(UnitsContext.class);
		}
		public UnitsContext units(int i) {
			return getRuleContext(UnitsContext.class,i);
		}
		public List<LanguageContext> language() {
			return getRuleContexts(LanguageContext.class);
		}
		public LanguageContext language(int i) {
			return getRuleContext(LanguageContext.class,i);
		}
		public List<AbstractInterfaceStatementContext> abstractInterfaceStatement() {
			return getRuleContexts(AbstractInterfaceStatementContext.class);
		}
		public AbstractInterfaceStatementContext abstractInterfaceStatement(int i) {
			return getRuleContext(AbstractInterfaceStatementContext.class,i);
		}
		public List<TypedefStatementContext> typedefStatement() {
			return getRuleContexts(TypedefStatementContext.class);
		}
		public TypedefStatementContext typedefStatement(int i) {
			return getRuleContext(TypedefStatementContext.class,i);
		}
		public List<FormattingContext> formatting() {
			return getRuleContexts(FormattingContext.class);
		}
		public FormattingContext formatting(int i) {
			return getRuleContext(FormattingContext.class,i);
		}
		public RootContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_root; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitRoot(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RootContext root() throws RecognitionException {
		RootContext _localctx = new RootContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_root);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(573); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(573);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
				case 1:
					{
					setState(562);
					space();
					}
					break;
				case 2:
					{
					setState(563);
					module();
					}
					break;
				case 3:
					{
					setState(564);
					entity();
					}
					break;
				case 4:
					{
					setState(565);
					enumStatement();
					}
					break;
				case 5:
					{
					setState(566);
					domain();
					}
					break;
				case 6:
					{
					setState(567);
					configuration();
					}
					break;
				case 7:
					{
					setState(568);
					units();
					}
					break;
				case 8:
					{
					setState(569);
					language();
					}
					break;
				case 9:
					{
					setState(570);
					abstractInterfaceStatement();
					}
					break;
				case 10:
					{
					setState(571);
					typedefStatement();
					}
					break;
				case 11:
					{
					setState(572);
					formatting();
					}
					break;
				}
				}
				setState(575); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( ((((_la - 32)) & ~0x3f) == 0 && ((1L << (_la - 32)) & ((1L << (MODULE - 32)) | (1L << (ENTITY - 32)) | (1L << (EXTERN - 32)) | (1L << (TRANSIENT - 32)) | (1L << (PRIMARY - 32)) | (1L << (SECONDARY - 32)) | (1L << (ENUM - 32)) | (1L << (TYPEDEF - 32)) | (1L << (CONFIGURATION - 32)) | (1L << (FORMATTING - 32)) | (1L << (SPACE - 32)) | (1L << (LANGUAGE - 32)))) != 0) || ((((_la - 104)) & ~0x3f) == 0 && ((1L << (_la - 104)) & ((1L << (DOMAIN - 104)) | (1L << (UNITS - 104)) | (1L << (INTERFACE - 104)))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DescriptionStatementContext extends ParserRuleContext {
		public TerminalNode DESCRIPTION() { return getToken(EntityLanguageParser.DESCRIPTION, 0); }
		public TerminalNode STRING() { return getToken(EntityLanguageParser.STRING, 0); }
		public List<IdContext> id() {
			return getRuleContexts(IdContext.class);
		}
		public IdContext id(int i) {
			return getRuleContext(IdContext.class,i);
		}
		public DescriptionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_descriptionStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDescriptionStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DescriptionStatementContext descriptionStatement() throws RecognitionException {
		DescriptionStatementContext _localctx = new DescriptionStatementContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_descriptionStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(577);
			match(DESCRIPTION);
			setState(586);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MODULE) | (1L << ENTITY) | (1L << PRIMARYKEY) | (1L << ATTRIBUTE) | (1L << UNIQUE) | (1L << CREATION) | (1L << MODIFICATION) | (1L << PARENT) | (1L << ORDERED) | (1L << SEQUENTIAL) | (1L << EXTERN) | (1L << TRANSIENT) | (1L << PRIMARY) | (1L << SECONDARY) | (1L << FLATTEN) | (1L << RELATIONSHIPS) | (1L << RELATIONSHIP) | (1L << ENTITIES) | (1L << ENUM) | (1L << ENUMITEM) | (1L << TYPEDEF) | (1L << UNUSED) | (1L << CONFIGURATION) | (1L << FORMATTING))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (FORMAT - 64)) | (1L << (OUTPUT - 64)) | (1L << (DIRECTORY - 64)) | (1L << (TRANSFORM - 64)) | (1L << (TEMPLATE - 64)) | (1L << (PATH - 64)) | (1L << (ENDPOINT - 64)) | (1L << (INHERITED - 64)) | (1L << (REPOSITORY - 64)) | (1L << (TAG - 64)) | (1L << (VALUE - 64)) | (1L << (SPACE - 64)) | (1L << (IMPORT - 64)) | (1L << (FROM - 64)) | (1L << (ORGANIZATION - 64)) | (1L << (NAME - 64)) | (1L << (NULLABLE - 64)) | (1L << (LANGUAGE - 64)) | (1L << (VERSION - 64)) | (1L << (SELF - 64)) | (1L << (OPERATORS - 64)) | (1L << (KEYWORDS - 64)) | (1L << (SHORT - 64)) | (1L << (LONG - 64)) | (1L << (HUMAN - 64)) | (1L << (READABLE - 64)) | (1L << (IDENTIFICATION - 64)) | (1L << (DOMAIN - 64)) | (1L << (ATTRIBUTES - 64)) | (1L << (REPLACES - 64)) | (1L << (PREFIX - 64)) | (1L << (SUFFIX - 64)) | (1L << (RENAME - 64)) | (1L << (TAGGING - 64)) | (1L << (NAMESPACE - 64)) | (1L << (READ - 64)) | (1L << (WRITE - 64)) | (1L << (WHEN - 64)) | (1L << (REQUIRES - 64)) | (1L << (ROLE - 64)) | (1L << (USER - 64)) | (1L << (IF - 64)) | (1L << (APPLY - 64)))) != 0) || ((((_la - 130)) & ~0x3f) == 0 && ((1L << (_la - 130)) & ((1L << (DEFAULT - 130)) | (1L << (VIEW - 130)) | (1L << (INCLUDE - 130)) | (1L << (EXCLUDE - 130)) | (1L << (AND - 130)) | (1L << (ABSTRACT - 130)) | (1L << (EXTENDS - 130)) | (1L << (INTERFACE - 130)) | (1L << (CONFIG - 130)) | (1L << (METADATA - 130)) | (1L << (CONTEXT - 130)) | (1L << (ARGUMENT - 130)) | (1L << (OPERATION - 130)) | (1L << (QUERY - 130)) | (1L << (PARAM - 130)) | (1L << (STATUS - 130)) | (1L << (CUSTOM - 130)) | (1L << (TYPE - 130)) | (1L << (TO - 130)) | (1L << (PREPEND - 130)) | (1L << (APPEND - 130)) | (1L << (BOOLEAN_TYPE - 130)) | (1L << (INT32_TYPE - 130)) | (1L << (INT64_TYPE - 130)) | (1L << (BYTE_TYPE - 130)) | (1L << (UUID_TYPE - 130)) | (1L << (FLOAT_TYPE - 130)) | (1L << (DOUBLE_TYPE - 130)) | (1L << (STRING_TYPE - 130)) | (1L << (DATE_TYPE - 130)) | (1L << (ASSET_TYPE - 130)) | (1L << (ARRAY - 130)) | (1L << (DESCRIPTION - 130)) | (1L << (TAGS - 130)) | (1L << (MACRO_START - 130)) | (1L << (ID - 130)))) != 0)) {
				{
				setState(578);
				id();
				setState(583);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__4) {
					{
					{
					setState(579);
					match(T__4);
					setState(580);
					id();
					}
					}
					setState(585);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(588);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TagStatementContext extends ParserRuleContext {
		public TerminalNode TAGS() { return getToken(EntityLanguageParser.TAGS, 0); }
		public List<TagItemContext> tagItem() {
			return getRuleContexts(TagItemContext.class);
		}
		public TagItemContext tagItem(int i) {
			return getRuleContext(TagItemContext.class,i);
		}
		public TagStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tagStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitTagStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TagStatementContext tagStatement() throws RecognitionException {
		TagStatementContext _localctx = new TagStatementContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_tagStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(590);
			match(TAGS);
			setState(591);
			tagItem();
			setState(596);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(592);
				match(T__4);
				setState(593);
				tagItem();
				}
				}
				setState(598);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TagItemContext extends ParserRuleContext {
		public List<TerminalNode> STRING() { return getTokens(EntityLanguageParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(EntityLanguageParser.STRING, i);
		}
		public TagItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tagItem; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitTagItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TagItemContext tagItem() throws RecognitionException {
		TagItemContext _localctx = new TagItemContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_tagItem);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(599);
			match(STRING);
			setState(602);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__27) {
				{
				setState(600);
				match(T__27);
				setState(601);
				match(STRING);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ModuleContext extends ParserRuleContext {
		public TerminalNode MODULE() { return getToken(EntityLanguageParser.MODULE, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public ModuleBodyContext moduleBody() {
			return getRuleContext(ModuleBodyContext.class,0);
		}
		public ModuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_module; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitModule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModuleContext module() throws RecognitionException {
		ModuleContext _localctx = new ModuleContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_module);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(604);
			match(MODULE);
			setState(605);
			id();
			setState(606);
			match(T__28);
			setState(607);
			moduleBody();
			setState(608);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ModuleBodyContext extends ParserRuleContext {
		public List<DescriptionStatementContext> descriptionStatement() {
			return getRuleContexts(DescriptionStatementContext.class);
		}
		public DescriptionStatementContext descriptionStatement(int i) {
			return getRuleContext(DescriptionStatementContext.class,i);
		}
		public List<TagStatementContext> tagStatement() {
			return getRuleContexts(TagStatementContext.class);
		}
		public TagStatementContext tagStatement(int i) {
			return getRuleContext(TagStatementContext.class,i);
		}
		public List<EntityContext> entity() {
			return getRuleContexts(EntityContext.class);
		}
		public EntityContext entity(int i) {
			return getRuleContext(EntityContext.class,i);
		}
		public List<EnumStatementContext> enumStatement() {
			return getRuleContexts(EnumStatementContext.class);
		}
		public EnumStatementContext enumStatement(int i) {
			return getRuleContext(EnumStatementContext.class,i);
		}
		public List<AbstractInterfaceStatementContext> abstractInterfaceStatement() {
			return getRuleContexts(AbstractInterfaceStatementContext.class);
		}
		public AbstractInterfaceStatementContext abstractInterfaceStatement(int i) {
			return getRuleContext(AbstractInterfaceStatementContext.class,i);
		}
		public List<TypedefStatementContext> typedefStatement() {
			return getRuleContexts(TypedefStatementContext.class);
		}
		public TypedefStatementContext typedefStatement(int i) {
			return getRuleContext(TypedefStatementContext.class,i);
		}
		public ModuleBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_moduleBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitModuleBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModuleBodyContext moduleBody() throws RecognitionException {
		ModuleBodyContext _localctx = new ModuleBodyContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_moduleBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(616); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(616);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
				case 1:
					{
					setState(610);
					descriptionStatement();
					}
					break;
				case 2:
					{
					setState(611);
					tagStatement();
					}
					break;
				case 3:
					{
					setState(612);
					entity();
					}
					break;
				case 4:
					{
					setState(613);
					enumStatement();
					}
					break;
				case 5:
					{
					setState(614);
					abstractInterfaceStatement();
					}
					break;
				case 6:
					{
					setState(615);
					typedefStatement();
					}
					break;
				}
				}
				setState(618); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ENTITY) | (1L << EXTERN) | (1L << TRANSIENT) | (1L << PRIMARY) | (1L << SECONDARY) | (1L << ENUM) | (1L << TYPEDEF))) != 0) || ((((_la - 142)) & ~0x3f) == 0 && ((1L << (_la - 142)) & ((1L << (INTERFACE - 142)) | (1L << (DESCRIPTION - 142)) | (1L << (TAGS - 142)))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EntityContext extends ParserRuleContext {
		public EntityDeclContext entityDecl() {
			return getRuleContext(EntityDeclContext.class,0);
		}
		public EntityBodyContext entityBody() {
			return getRuleContext(EntityBodyContext.class,0);
		}
		public EntityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entity; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitEntity(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EntityContext entity() throws RecognitionException {
		EntityContext _localctx = new EntityContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_entity);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(620);
			entityDecl();
			setState(621);
			match(T__28);
			setState(622);
			entityBody();
			setState(623);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EntityDeclContext extends ParserRuleContext {
		public TerminalNode ENTITY() { return getToken(EntityLanguageParser.ENTITY, 0); }
		public EntityNameContext entityName() {
			return getRuleContext(EntityNameContext.class,0);
		}
		public EntityTemplateDeclContext entityTemplateDecl() {
			return getRuleContext(EntityTemplateDeclContext.class,0);
		}
		public TerminalNode EXTERN() { return getToken(EntityLanguageParser.EXTERN, 0); }
		public TerminalNode TRANSIENT() { return getToken(EntityLanguageParser.TRANSIENT, 0); }
		public TerminalNode INHERITS() { return getToken(EntityLanguageParser.INHERITS, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TerminalNode PRIMARY() { return getToken(EntityLanguageParser.PRIMARY, 0); }
		public TerminalNode SECONDARY() { return getToken(EntityLanguageParser.SECONDARY, 0); }
		public EntityDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entityDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitEntityDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EntityDeclContext entityDecl() throws RecognitionException {
		EntityDeclContext _localctx = new EntityDeclContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_entityDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(626);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EXTERN) {
				{
				setState(625);
				match(EXTERN);
				}
			}

			setState(629);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==TRANSIENT) {
				{
				setState(628);
				match(TRANSIENT);
				}
			}

			setState(632);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PRIMARY || _la==SECONDARY) {
				{
				setState(631);
				_la = _input.LA(1);
				if ( !(_la==PRIMARY || _la==SECONDARY) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(634);
			match(ENTITY);
			setState(637);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				{
				setState(635);
				entityName();
				}
				break;
			case 2:
				{
				setState(636);
				entityTemplateDecl();
				}
				break;
			}
			setState(641);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INHERITS) {
				{
				setState(639);
				match(INHERITS);
				setState(640);
				id();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EntityNameContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public EntityNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entityName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitEntityName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EntityNameContext entityName() throws RecognitionException {
		EntityNameContext _localctx = new EntityNameContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_entityName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(643);
			id();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EntityTemplateDeclContext extends ParserRuleContext {
		public List<IdContext> id() {
			return getRuleContexts(IdContext.class);
		}
		public IdContext id(int i) {
			return getRuleContext(IdContext.class,i);
		}
		public EntityTemplateDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entityTemplateDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitEntityTemplateDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EntityTemplateDeclContext entityTemplateDecl() throws RecognitionException {
		EntityTemplateDeclContext _localctx = new EntityTemplateDeclContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_entityTemplateDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(645);
			id();
			setState(646);
			match(T__16);
			setState(647);
			id();
			setState(652);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(648);
				match(T__4);
				setState(649);
				id();
				}
				}
				setState(654);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(655);
			match(T__15);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EntityBodyContext extends ParserRuleContext {
		public List<PrimarykeySingleContext> primarykeySingle() {
			return getRuleContexts(PrimarykeySingleContext.class);
		}
		public PrimarykeySingleContext primarykeySingle(int i) {
			return getRuleContext(PrimarykeySingleContext.class,i);
		}
		public List<PrimarykeyMultipleContext> primarykeyMultiple() {
			return getRuleContexts(PrimarykeyMultipleContext.class);
		}
		public PrimarykeyMultipleContext primarykeyMultiple(int i) {
			return getRuleContext(PrimarykeyMultipleContext.class,i);
		}
		public List<AttributesContext> attributes() {
			return getRuleContexts(AttributesContext.class);
		}
		public AttributesContext attributes(int i) {
			return getRuleContext(AttributesContext.class,i);
		}
		public List<DescriptionStatementContext> descriptionStatement() {
			return getRuleContexts(DescriptionStatementContext.class);
		}
		public DescriptionStatementContext descriptionStatement(int i) {
			return getRuleContext(DescriptionStatementContext.class,i);
		}
		public List<TagStatementContext> tagStatement() {
			return getRuleContexts(TagStatementContext.class);
		}
		public TagStatementContext tagStatement(int i) {
			return getRuleContext(TagStatementContext.class,i);
		}
		public List<RelationshipsContext> relationships() {
			return getRuleContexts(RelationshipsContext.class);
		}
		public RelationshipsContext relationships(int i) {
			return getRuleContext(RelationshipsContext.class,i);
		}
		public List<EnumStatementContext> enumStatement() {
			return getRuleContexts(EnumStatementContext.class);
		}
		public EnumStatementContext enumStatement(int i) {
			return getRuleContext(EnumStatementContext.class,i);
		}
		public EntityBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entityBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitEntityBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EntityBodyContext entityBody() throws RecognitionException {
		EntityBodyContext _localctx = new EntityBodyContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_entityBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(666);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PRIMARYKEY) | (1L << EXTERN) | (1L << RELATIONSHIPS) | (1L << ENUM))) != 0) || _la==ATTRIBUTES || _la==DESCRIPTION || _la==TAGS) {
				{
				setState(664);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
				case 1:
					{
					setState(657);
					primarykeySingle();
					}
					break;
				case 2:
					{
					setState(658);
					primarykeyMultiple();
					}
					break;
				case 3:
					{
					setState(659);
					attributes();
					}
					break;
				case 4:
					{
					setState(660);
					descriptionStatement();
					}
					break;
				case 5:
					{
					setState(661);
					tagStatement();
					}
					break;
				case 6:
					{
					setState(662);
					relationships();
					}
					break;
				case 7:
					{
					setState(663);
					enumStatement();
					}
					break;
				}
				}
				setState(668);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrimarykeySingleContext extends ParserRuleContext {
		public TerminalNode PRIMARYKEY() { return getToken(EntityLanguageParser.PRIMARYKEY, 0); }
		public AttributeContext attribute() {
			return getRuleContext(AttributeContext.class,0);
		}
		public PrimarykeySingleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primarykeySingle; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitPrimarykeySingle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimarykeySingleContext primarykeySingle() throws RecognitionException {
		PrimarykeySingleContext _localctx = new PrimarykeySingleContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_primarykeySingle);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(669);
			match(PRIMARYKEY);
			setState(670);
			attribute();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrimarykeyMultipleContext extends ParserRuleContext {
		public TerminalNode PRIMARYKEY() { return getToken(EntityLanguageParser.PRIMARYKEY, 0); }
		public AttributesBodyContext attributesBody() {
			return getRuleContext(AttributesBodyContext.class,0);
		}
		public PrimarykeyMultipleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primarykeyMultiple; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitPrimarykeyMultiple(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimarykeyMultipleContext primarykeyMultiple() throws RecognitionException {
		PrimarykeyMultipleContext _localctx = new PrimarykeyMultipleContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_primarykeyMultiple);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(672);
			match(PRIMARYKEY);
			setState(673);
			match(T__28);
			setState(674);
			attributesBody();
			setState(675);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttributesDeclContext extends ParserRuleContext {
		public TerminalNode ATTRIBUTES() { return getToken(EntityLanguageParser.ATTRIBUTES, 0); }
		public List<IdContext> id() {
			return getRuleContexts(IdContext.class);
		}
		public IdContext id(int i) {
			return getRuleContext(IdContext.class,i);
		}
		public AttributesDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributesDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitAttributesDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttributesDeclContext attributesDecl() throws RecognitionException {
		AttributesDeclContext _localctx = new AttributesDeclContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_attributesDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(677);
			match(ATTRIBUTES);
			setState(681);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MODULE) | (1L << ENTITY) | (1L << PRIMARYKEY) | (1L << ATTRIBUTE) | (1L << UNIQUE) | (1L << CREATION) | (1L << MODIFICATION) | (1L << PARENT) | (1L << ORDERED) | (1L << SEQUENTIAL) | (1L << EXTERN) | (1L << TRANSIENT) | (1L << PRIMARY) | (1L << SECONDARY) | (1L << FLATTEN) | (1L << RELATIONSHIPS) | (1L << RELATIONSHIP) | (1L << ENTITIES) | (1L << ENUM) | (1L << ENUMITEM) | (1L << TYPEDEF) | (1L << UNUSED) | (1L << CONFIGURATION) | (1L << FORMATTING))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (FORMAT - 64)) | (1L << (OUTPUT - 64)) | (1L << (DIRECTORY - 64)) | (1L << (TRANSFORM - 64)) | (1L << (TEMPLATE - 64)) | (1L << (PATH - 64)) | (1L << (ENDPOINT - 64)) | (1L << (INHERITED - 64)) | (1L << (REPOSITORY - 64)) | (1L << (TAG - 64)) | (1L << (VALUE - 64)) | (1L << (SPACE - 64)) | (1L << (IMPORT - 64)) | (1L << (FROM - 64)) | (1L << (ORGANIZATION - 64)) | (1L << (NAME - 64)) | (1L << (NULLABLE - 64)) | (1L << (LANGUAGE - 64)) | (1L << (VERSION - 64)) | (1L << (SELF - 64)) | (1L << (OPERATORS - 64)) | (1L << (KEYWORDS - 64)) | (1L << (SHORT - 64)) | (1L << (LONG - 64)) | (1L << (HUMAN - 64)) | (1L << (READABLE - 64)) | (1L << (IDENTIFICATION - 64)) | (1L << (DOMAIN - 64)) | (1L << (ATTRIBUTES - 64)) | (1L << (REPLACES - 64)) | (1L << (PREFIX - 64)) | (1L << (SUFFIX - 64)) | (1L << (RENAME - 64)) | (1L << (TAGGING - 64)) | (1L << (NAMESPACE - 64)) | (1L << (READ - 64)) | (1L << (WRITE - 64)) | (1L << (WHEN - 64)) | (1L << (REQUIRES - 64)) | (1L << (ROLE - 64)) | (1L << (USER - 64)) | (1L << (IF - 64)) | (1L << (APPLY - 64)))) != 0) || ((((_la - 130)) & ~0x3f) == 0 && ((1L << (_la - 130)) & ((1L << (DEFAULT - 130)) | (1L << (VIEW - 130)) | (1L << (INCLUDE - 130)) | (1L << (EXCLUDE - 130)) | (1L << (AND - 130)) | (1L << (ABSTRACT - 130)) | (1L << (EXTENDS - 130)) | (1L << (INTERFACE - 130)) | (1L << (CONFIG - 130)) | (1L << (METADATA - 130)) | (1L << (CONTEXT - 130)) | (1L << (ARGUMENT - 130)) | (1L << (OPERATION - 130)) | (1L << (QUERY - 130)) | (1L << (PARAM - 130)) | (1L << (STATUS - 130)) | (1L << (CUSTOM - 130)) | (1L << (TYPE - 130)) | (1L << (TO - 130)) | (1L << (PREPEND - 130)) | (1L << (APPEND - 130)) | (1L << (BOOLEAN_TYPE - 130)) | (1L << (INT32_TYPE - 130)) | (1L << (INT64_TYPE - 130)) | (1L << (BYTE_TYPE - 130)) | (1L << (UUID_TYPE - 130)) | (1L << (FLOAT_TYPE - 130)) | (1L << (DOUBLE_TYPE - 130)) | (1L << (STRING_TYPE - 130)) | (1L << (DATE_TYPE - 130)) | (1L << (ASSET_TYPE - 130)) | (1L << (ARRAY - 130)) | (1L << (DESCRIPTION - 130)) | (1L << (TAGS - 130)) | (1L << (MACRO_START - 130)) | (1L << (ID - 130)))) != 0)) {
				{
				{
				setState(678);
				id();
				}
				}
				setState(683);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttributesContext extends ParserRuleContext {
		public AttributesDeclContext attributesDecl() {
			return getRuleContext(AttributesDeclContext.class,0);
		}
		public AttributesBodyContext attributesBody() {
			return getRuleContext(AttributesBodyContext.class,0);
		}
		public AttributesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributes; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitAttributes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttributesContext attributes() throws RecognitionException {
		AttributesContext _localctx = new AttributesContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_attributes);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(684);
			attributesDecl();
			setState(685);
			match(T__28);
			setState(686);
			attributesBody();
			setState(687);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttributesBodyContext extends ParserRuleContext {
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public AttributesBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributesBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitAttributesBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttributesBodyContext attributesBody() throws RecognitionException {
		AttributesBodyContext _localctx = new AttributesBodyContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_attributesBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(692);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MODULE) | (1L << ENTITY) | (1L << PRIMARYKEY) | (1L << ATTRIBUTE) | (1L << UNIQUE) | (1L << CREATION) | (1L << MODIFICATION) | (1L << OPTIONAL) | (1L << PARENT) | (1L << ORDERED) | (1L << SEQUENTIAL) | (1L << VIRTUAL) | (1L << EXTERN) | (1L << TRANSIENT) | (1L << PRIMARY) | (1L << SECONDARY) | (1L << FLATTEN) | (1L << RELATIONSHIPS) | (1L << RELATIONSHIP) | (1L << MANY) | (1L << ENTITIES) | (1L << ENUM) | (1L << ENUMITEM) | (1L << TYPEDEF) | (1L << UNUSED) | (1L << CONFIGURATION) | (1L << FORMATTING))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (FORMAT - 64)) | (1L << (OUTPUT - 64)) | (1L << (DIRECTORY - 64)) | (1L << (TRANSFORM - 64)) | (1L << (TEMPLATE - 64)) | (1L << (PATH - 64)) | (1L << (ENDPOINT - 64)) | (1L << (INHERITED - 64)) | (1L << (REPOSITORY - 64)) | (1L << (TAG - 64)) | (1L << (VALUE - 64)) | (1L << (SPACE - 64)) | (1L << (IMPORT - 64)) | (1L << (FROM - 64)) | (1L << (ORGANIZATION - 64)) | (1L << (NAME - 64)) | (1L << (NULLABLE - 64)) | (1L << (LANGUAGE - 64)) | (1L << (VERSION - 64)) | (1L << (SELF - 64)) | (1L << (OPERATORS - 64)) | (1L << (KEYWORDS - 64)) | (1L << (SHORT - 64)) | (1L << (LONG - 64)) | (1L << (HUMAN - 64)) | (1L << (READABLE - 64)) | (1L << (IDENTIFICATION - 64)) | (1L << (DOMAIN - 64)) | (1L << (ATTRIBUTES - 64)) | (1L << (REPLACES - 64)) | (1L << (PREFIX - 64)) | (1L << (SUFFIX - 64)) | (1L << (RENAME - 64)) | (1L << (TAGGING - 64)) | (1L << (NAMESPACE - 64)) | (1L << (READ - 64)) | (1L << (WRITE - 64)) | (1L << (WHEN - 64)) | (1L << (REQUIRES - 64)) | (1L << (ROLE - 64)) | (1L << (USER - 64)) | (1L << (IF - 64)) | (1L << (APPLY - 64)))) != 0) || ((((_la - 130)) & ~0x3f) == 0 && ((1L << (_la - 130)) & ((1L << (DEFAULT - 130)) | (1L << (VIEW - 130)) | (1L << (INCLUDE - 130)) | (1L << (EXCLUDE - 130)) | (1L << (AND - 130)) | (1L << (ABSTRACT - 130)) | (1L << (EXTENDS - 130)) | (1L << (INTERFACE - 130)) | (1L << (CONFIG - 130)) | (1L << (METADATA - 130)) | (1L << (CONTEXT - 130)) | (1L << (ARGUMENT - 130)) | (1L << (OPERATION - 130)) | (1L << (QUERY - 130)) | (1L << (PARAM - 130)) | (1L << (STATUS - 130)) | (1L << (CUSTOM - 130)) | (1L << (TYPE - 130)) | (1L << (TO - 130)) | (1L << (PREPEND - 130)) | (1L << (APPEND - 130)) | (1L << (BOOLEAN_TYPE - 130)) | (1L << (INT32_TYPE - 130)) | (1L << (INT64_TYPE - 130)) | (1L << (BYTE_TYPE - 130)) | (1L << (UUID_TYPE - 130)) | (1L << (FLOAT_TYPE - 130)) | (1L << (DOUBLE_TYPE - 130)) | (1L << (STRING_TYPE - 130)) | (1L << (DATE_TYPE - 130)) | (1L << (ASSET_TYPE - 130)) | (1L << (ARRAY - 130)) | (1L << (DESCRIPTION - 130)) | (1L << (TAGS - 130)) | (1L << (MACRO_START - 130)) | (1L << (ID - 130)))) != 0)) {
				{
				{
				setState(689);
				attribute();
				}
				}
				setState(694);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttributeContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<IdContext> id() {
			return getRuleContexts(IdContext.class);
		}
		public IdContext id(int i) {
			return getRuleContext(IdContext.class,i);
		}
		public List<AttributeQualifierContext> attributeQualifier() {
			return getRuleContexts(AttributeQualifierContext.class);
		}
		public AttributeQualifierContext attributeQualifier(int i) {
			return getRuleContext(AttributeQualifierContext.class,i);
		}
		public TerminalNode IN() { return getToken(EntityLanguageParser.IN, 0); }
		public AttributeBodyContext attributeBody() {
			return getRuleContext(AttributeBodyContext.class,0);
		}
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public AttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitAttribute(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttributeContext attribute() throws RecognitionException {
		AttributeContext _localctx = new AttributeContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_attribute);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(698);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(695);
					attributeQualifier();
					}
					} 
				}
				setState(700);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
			}
			setState(701);
			type();
			setState(702);
			id();
			setState(705);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IN) {
				{
				setState(703);
				match(IN);
				setState(704);
				id();
				}
			}

			setState(712);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__27) {
				{
				setState(707);
				match(T__27);
				setState(710);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__25:
				case T__26:
				case INTEGER:
				case STRING:
				case FLOAT:
					{
					setState(708);
					constant();
					}
					break;
				case MODULE:
				case ENTITY:
				case PRIMARYKEY:
				case ATTRIBUTE:
				case UNIQUE:
				case CREATION:
				case MODIFICATION:
				case PARENT:
				case ORDERED:
				case SEQUENTIAL:
				case EXTERN:
				case TRANSIENT:
				case PRIMARY:
				case SECONDARY:
				case FLATTEN:
				case RELATIONSHIPS:
				case RELATIONSHIP:
				case ENTITIES:
				case ENUM:
				case ENUMITEM:
				case TYPEDEF:
				case UNUSED:
				case CONFIGURATION:
				case FORMATTING:
				case FORMAT:
				case OUTPUT:
				case DIRECTORY:
				case TRANSFORM:
				case TEMPLATE:
				case PATH:
				case ENDPOINT:
				case INHERITED:
				case REPOSITORY:
				case TAG:
				case VALUE:
				case SPACE:
				case IMPORT:
				case FROM:
				case ORGANIZATION:
				case NAME:
				case NULLABLE:
				case LANGUAGE:
				case VERSION:
				case SELF:
				case OPERATORS:
				case KEYWORDS:
				case SHORT:
				case LONG:
				case HUMAN:
				case READABLE:
				case IDENTIFICATION:
				case DOMAIN:
				case ATTRIBUTES:
				case REPLACES:
				case PREFIX:
				case SUFFIX:
				case RENAME:
				case TAGGING:
				case NAMESPACE:
				case READ:
				case WRITE:
				case WHEN:
				case REQUIRES:
				case ROLE:
				case USER:
				case IF:
				case APPLY:
				case DEFAULT:
				case VIEW:
				case INCLUDE:
				case EXCLUDE:
				case AND:
				case ABSTRACT:
				case EXTENDS:
				case INTERFACE:
				case CONFIG:
				case METADATA:
				case CONTEXT:
				case ARGUMENT:
				case OPERATION:
				case QUERY:
				case PARAM:
				case STATUS:
				case CUSTOM:
				case TYPE:
				case TO:
				case PREPEND:
				case APPEND:
				case BOOLEAN_TYPE:
				case INT32_TYPE:
				case INT64_TYPE:
				case BYTE_TYPE:
				case UUID_TYPE:
				case FLOAT_TYPE:
				case DOUBLE_TYPE:
				case STRING_TYPE:
				case DATE_TYPE:
				case ASSET_TYPE:
				case ARRAY:
				case DESCRIPTION:
				case TAGS:
				case MACRO_START:
				case ID:
					{
					setState(709);
					id();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
			}

			setState(718);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__28) {
				{
				setState(714);
				match(T__28);
				setState(715);
				attributeBody();
				setState(716);
				match(T__29);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttributeBodyContext extends ParserRuleContext {
		public List<DescriptionStatementContext> descriptionStatement() {
			return getRuleContexts(DescriptionStatementContext.class);
		}
		public DescriptionStatementContext descriptionStatement(int i) {
			return getRuleContext(DescriptionStatementContext.class,i);
		}
		public List<TagStatementContext> tagStatement() {
			return getRuleContexts(TagStatementContext.class);
		}
		public TagStatementContext tagStatement(int i) {
			return getRuleContext(TagStatementContext.class,i);
		}
		public List<ContentTypeContext> contentType() {
			return getRuleContexts(ContentTypeContext.class);
		}
		public ContentTypeContext contentType(int i) {
			return getRuleContext(ContentTypeContext.class,i);
		}
		public List<BitfieldContext> bitfield() {
			return getRuleContexts(BitfieldContext.class);
		}
		public BitfieldContext bitfield(int i) {
			return getRuleContext(BitfieldContext.class,i);
		}
		public List<AttributeConstraintContext> attributeConstraint() {
			return getRuleContexts(AttributeConstraintContext.class);
		}
		public AttributeConstraintContext attributeConstraint(int i) {
			return getRuleContext(AttributeConstraintContext.class,i);
		}
		public AttributeBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributeBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitAttributeBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttributeBodyContext attributeBody() throws RecognitionException {
		AttributeBodyContext _localctx = new AttributeBodyContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_attributeBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(727);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3 || _la==CONTENT_TYPE || _la==CONSTRAINT || _la==DESCRIPTION || _la==TAGS) {
				{
				setState(725);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case DESCRIPTION:
					{
					setState(720);
					descriptionStatement();
					}
					break;
				case TAGS:
					{
					setState(721);
					tagStatement();
					}
					break;
				case CONTENT_TYPE:
					{
					setState(722);
					contentType();
					}
					break;
				case T__3:
					{
					setState(723);
					bitfield();
					}
					break;
				case CONSTRAINT:
					{
					setState(724);
					attributeConstraint();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(729);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttributeNameContext extends ParserRuleContext {
		public List<IdContext> id() {
			return getRuleContexts(IdContext.class);
		}
		public IdContext id(int i) {
			return getRuleContext(IdContext.class,i);
		}
		public AttributeNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributeName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitAttributeName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttributeNameContext attributeName() throws RecognitionException {
		AttributeNameContext _localctx = new AttributeNameContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_attributeName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(730);
			id();
			setState(735);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(731);
				match(T__2);
				setState(732);
				id();
				}
				}
				setState(737);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ContentTypeContext extends ParserRuleContext {
		public TerminalNode CONTENT_TYPE() { return getToken(EntityLanguageParser.CONTENT_TYPE, 0); }
		public TerminalNode STRING() { return getToken(EntityLanguageParser.STRING, 0); }
		public ContentTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_contentType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitContentType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContentTypeContext contentType() throws RecognitionException {
		ContentTypeContext _localctx = new ContentTypeContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_contentType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(738);
			match(CONTENT_TYPE);
			setState(739);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttributeConstraintContext extends ParserRuleContext {
		public TerminalNode CONSTRAINT() { return getToken(EntityLanguageParser.CONSTRAINT, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public AttributeConstraintBodyContext attributeConstraintBody() {
			return getRuleContext(AttributeConstraintBodyContext.class,0);
		}
		public AttributeConstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributeConstraint; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitAttributeConstraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttributeConstraintContext attributeConstraint() throws RecognitionException {
		AttributeConstraintContext _localctx = new AttributeConstraintContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_attributeConstraint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(741);
			match(CONSTRAINT);
			setState(742);
			id();
			setState(743);
			match(T__28);
			setState(744);
			attributeConstraintBody();
			setState(745);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttributeConstraintBodyContext extends ParserRuleContext {
		public List<DescriptionStatementContext> descriptionStatement() {
			return getRuleContexts(DescriptionStatementContext.class);
		}
		public DescriptionStatementContext descriptionStatement(int i) {
			return getRuleContext(DescriptionStatementContext.class,i);
		}
		public List<TagStatementContext> tagStatement() {
			return getRuleContexts(TagStatementContext.class);
		}
		public TagStatementContext tagStatement(int i) {
			return getRuleContext(TagStatementContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public AttributeConstraintBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributeConstraintBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitAttributeConstraintBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttributeConstraintBodyContext attributeConstraintBody() throws RecognitionException {
		AttributeConstraintBodyContext _localctx = new AttributeConstraintBodyContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_attributeConstraintBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(752);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__25) | (1L << T__26) | (1L << MODULE) | (1L << ENTITY) | (1L << PRIMARYKEY) | (1L << ATTRIBUTE) | (1L << UNIQUE) | (1L << CREATION) | (1L << MODIFICATION) | (1L << PARENT) | (1L << ORDERED) | (1L << SEQUENTIAL) | (1L << EXTERN) | (1L << TRANSIENT) | (1L << PRIMARY) | (1L << SECONDARY) | (1L << FLATTEN) | (1L << RELATIONSHIPS) | (1L << RELATIONSHIP) | (1L << ENTITIES) | (1L << ENUM) | (1L << ENUMITEM) | (1L << TYPEDEF) | (1L << UNUSED) | (1L << CONFIGURATION) | (1L << FORMATTING))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (FORMAT - 64)) | (1L << (OUTPUT - 64)) | (1L << (DIRECTORY - 64)) | (1L << (TRANSFORM - 64)) | (1L << (TEMPLATE - 64)) | (1L << (PATH - 64)) | (1L << (ENDPOINT - 64)) | (1L << (INHERITED - 64)) | (1L << (REPOSITORY - 64)) | (1L << (TAG - 64)) | (1L << (VALUE - 64)) | (1L << (SPACE - 64)) | (1L << (IMPORT - 64)) | (1L << (FROM - 64)) | (1L << (ORGANIZATION - 64)) | (1L << (NAME - 64)) | (1L << (NULLABLE - 64)) | (1L << (LANGUAGE - 64)) | (1L << (VERSION - 64)) | (1L << (SELF - 64)) | (1L << (OPERATORS - 64)) | (1L << (KEYWORDS - 64)) | (1L << (SHORT - 64)) | (1L << (LONG - 64)) | (1L << (HUMAN - 64)) | (1L << (READABLE - 64)) | (1L << (IDENTIFICATION - 64)) | (1L << (DOMAIN - 64)) | (1L << (ATTRIBUTES - 64)) | (1L << (REPLACES - 64)) | (1L << (PREFIX - 64)) | (1L << (SUFFIX - 64)) | (1L << (RENAME - 64)) | (1L << (TAGGING - 64)) | (1L << (NAMESPACE - 64)) | (1L << (READ - 64)) | (1L << (WRITE - 64)) | (1L << (WHEN - 64)) | (1L << (REQUIRES - 64)) | (1L << (ROLE - 64)) | (1L << (USER - 64)) | (1L << (IF - 64)) | (1L << (APPLY - 64)))) != 0) || ((((_la - 130)) & ~0x3f) == 0 && ((1L << (_la - 130)) & ((1L << (DEFAULT - 130)) | (1L << (VIEW - 130)) | (1L << (INCLUDE - 130)) | (1L << (EXCLUDE - 130)) | (1L << (AND - 130)) | (1L << (ABSTRACT - 130)) | (1L << (EXTENDS - 130)) | (1L << (INTERFACE - 130)) | (1L << (CONFIG - 130)) | (1L << (METADATA - 130)) | (1L << (CONTEXT - 130)) | (1L << (ARGUMENT - 130)) | (1L << (OPERATION - 130)) | (1L << (QUERY - 130)) | (1L << (PARAM - 130)) | (1L << (STATUS - 130)) | (1L << (CUSTOM - 130)) | (1L << (TYPE - 130)) | (1L << (TO - 130)) | (1L << (PREPEND - 130)) | (1L << (APPEND - 130)) | (1L << (BOOLEAN_TYPE - 130)) | (1L << (INT32_TYPE - 130)) | (1L << (INT64_TYPE - 130)) | (1L << (BYTE_TYPE - 130)) | (1L << (UUID_TYPE - 130)) | (1L << (FLOAT_TYPE - 130)) | (1L << (DOUBLE_TYPE - 130)) | (1L << (STRING_TYPE - 130)) | (1L << (DATE_TYPE - 130)) | (1L << (ASSET_TYPE - 130)) | (1L << (ARRAY - 130)) | (1L << (DESCRIPTION - 130)) | (1L << (TAGS - 130)) | (1L << (INTEGER - 130)) | (1L << (STRING - 130)) | (1L << (FLOAT - 130)) | (1L << (ID - 130)))) != 0)) {
				{
				setState(750);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
				case 1:
					{
					setState(747);
					descriptionStatement();
					}
					break;
				case 2:
					{
					setState(748);
					tagStatement();
					}
					break;
				case 3:
					{
					setState(749);
					expression(0);
					}
					break;
				}
				}
				setState(754);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelationshipsContext extends ParserRuleContext {
		public TerminalNode RELATIONSHIPS() { return getToken(EntityLanguageParser.RELATIONSHIPS, 0); }
		public RelationshipsBodyContext relationshipsBody() {
			return getRuleContext(RelationshipsBodyContext.class,0);
		}
		public RelationshipsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationships; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitRelationships(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationshipsContext relationships() throws RecognitionException {
		RelationshipsContext _localctx = new RelationshipsContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_relationships);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(755);
			match(RELATIONSHIPS);
			setState(756);
			match(T__28);
			setState(757);
			relationshipsBody();
			setState(758);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelationshipsBodyContext extends ParserRuleContext {
		public List<RelationshipStatementContext> relationshipStatement() {
			return getRuleContexts(RelationshipStatementContext.class);
		}
		public RelationshipStatementContext relationshipStatement(int i) {
			return getRuleContext(RelationshipStatementContext.class,i);
		}
		public RelationshipsBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationshipsBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitRelationshipsBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationshipsBodyContext relationshipsBody() throws RecognitionException {
		RelationshipsBodyContext _localctx = new RelationshipsBodyContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_relationshipsBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(763);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << REQUIRED) | (1L << OPTIONAL) | (1L << PARENT) | (1L << ONE) | (1L << MANY))) != 0)) {
				{
				{
				setState(760);
				relationshipStatement();
				}
				}
				setState(765);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelationshipStatementContext extends ParserRuleContext {
		public TerminalNode MANY() { return getToken(EntityLanguageParser.MANY, 0); }
		public RelationshipTemplateAsContext relationshipTemplateAs() {
			return getRuleContext(RelationshipTemplateAsContext.class,0);
		}
		public List<IdContext> id() {
			return getRuleContexts(IdContext.class);
		}
		public IdContext id(int i) {
			return getRuleContext(IdContext.class,i);
		}
		public RelationshipBodyContext relationshipBody() {
			return getRuleContext(RelationshipBodyContext.class,0);
		}
		public TerminalNode ONE() { return getToken(EntityLanguageParser.ONE, 0); }
		public TerminalNode PARENT() { return getToken(EntityLanguageParser.PARENT, 0); }
		public RelationshipReverseNameContext relationshipReverseName() {
			return getRuleContext(RelationshipReverseNameContext.class,0);
		}
		public RelationshipIdNameContext relationshipIdName() {
			return getRuleContext(RelationshipIdNameContext.class,0);
		}
		public TerminalNode OPTIONAL() { return getToken(EntityLanguageParser.OPTIONAL, 0); }
		public TerminalNode REQUIRED() { return getToken(EntityLanguageParser.REQUIRED, 0); }
		public RelationshipStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationshipStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitRelationshipStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationshipStatementContext relationshipStatement() throws RecognitionException {
		RelationshipStatementContext _localctx = new RelationshipStatementContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_relationshipStatement);
		int _la;
		try {
			setState(801);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(766);
				match(MANY);
				setState(767);
				relationshipTemplateAs();
				setState(768);
				id();
				setState(770);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
				case 1:
					{
					setState(769);
					id();
					}
					break;
				}
				setState(776);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__28) {
					{
					setState(772);
					match(T__28);
					setState(773);
					relationshipBody();
					setState(774);
					match(T__29);
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(779);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==REQUIRED || _la==OPTIONAL) {
					{
					setState(778);
					_la = _input.LA(1);
					if ( !(_la==REQUIRED || _la==OPTIONAL) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
				}

				setState(782);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PARENT) {
					{
					setState(781);
					match(PARENT);
					}
				}

				setState(784);
				_la = _input.LA(1);
				if ( !(_la==ONE || _la==MANY) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(785);
				id();
				setState(787);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
				case 1:
					{
					setState(786);
					relationshipReverseName();
					}
					break;
				}
				setState(790);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
				case 1:
					{
					setState(789);
					id();
					}
					break;
				}
				setState(793);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__3) {
					{
					setState(792);
					relationshipIdName();
					}
				}

				setState(799);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__28) {
					{
					setState(795);
					match(T__28);
					setState(796);
					relationshipBody();
					setState(797);
					match(T__29);
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelationshipTemplateArgContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TerminalNode UNIQUE() { return getToken(EntityLanguageParser.UNIQUE, 0); }
		public RelationshipTemplateArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationshipTemplateArg; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitRelationshipTemplateArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationshipTemplateArgContext relationshipTemplateArg() throws RecognitionException {
		RelationshipTemplateArgContext _localctx = new RelationshipTemplateArgContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_relationshipTemplateArg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(804);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
			case 1:
				{
				setState(803);
				match(UNIQUE);
				}
				break;
			}
			setState(806);
			id();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelationshipTemplateAsContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public List<RelationshipTemplateArgContext> relationshipTemplateArg() {
			return getRuleContexts(RelationshipTemplateArgContext.class);
		}
		public RelationshipTemplateArgContext relationshipTemplateArg(int i) {
			return getRuleContext(RelationshipTemplateArgContext.class,i);
		}
		public TerminalNode AS() { return getToken(EntityLanguageParser.AS, 0); }
		public RelationshipTemplateAsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationshipTemplateAs; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitRelationshipTemplateAs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationshipTemplateAsContext relationshipTemplateAs() throws RecognitionException {
		RelationshipTemplateAsContext _localctx = new RelationshipTemplateAsContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_relationshipTemplateAs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(808);
			id();
			setState(809);
			match(T__16);
			setState(810);
			relationshipTemplateArg();
			setState(815);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(811);
				match(T__4);
				setState(812);
				relationshipTemplateArg();
				}
				}
				setState(817);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(818);
			match(T__15);
			setState(819);
			match(AS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelationshipReverseNameContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public RelationshipReverseNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationshipReverseName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitRelationshipReverseName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationshipReverseNameContext relationshipReverseName() throws RecognitionException {
		RelationshipReverseNameContext _localctx = new RelationshipReverseNameContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_relationshipReverseName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(821);
			match(T__3);
			setState(822);
			id();
			setState(823);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelationshipIdNameContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public RelationshipIdNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationshipIdName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitRelationshipIdName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationshipIdNameContext relationshipIdName() throws RecognitionException {
		RelationshipIdNameContext _localctx = new RelationshipIdNameContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_relationshipIdName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(825);
			match(T__3);
			setState(826);
			id();
			setState(827);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelationshipBodyContext extends ParserRuleContext {
		public List<DescriptionStatementContext> descriptionStatement() {
			return getRuleContexts(DescriptionStatementContext.class);
		}
		public DescriptionStatementContext descriptionStatement(int i) {
			return getRuleContext(DescriptionStatementContext.class,i);
		}
		public List<TagStatementContext> tagStatement() {
			return getRuleContexts(TagStatementContext.class);
		}
		public TagStatementContext tagStatement(int i) {
			return getRuleContext(TagStatementContext.class,i);
		}
		public RelationshipBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationshipBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitRelationshipBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationshipBodyContext relationshipBody() throws RecognitionException {
		RelationshipBodyContext _localctx = new RelationshipBodyContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_relationshipBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(833);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DESCRIPTION || _la==TAGS) {
				{
				setState(831);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case DESCRIPTION:
					{
					setState(829);
					descriptionStatement();
					}
					break;
				case TAGS:
					{
					setState(830);
					tagStatement();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(835);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ViewContext extends ParserRuleContext {
		public TerminalNode VIEW() { return getToken(EntityLanguageParser.VIEW, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public ViewBlockContext viewBlock() {
			return getRuleContext(ViewBlockContext.class,0);
		}
		public TerminalNode DEFAULT() { return getToken(EntityLanguageParser.DEFAULT, 0); }
		public ViewContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_view; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitView(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ViewContext view() throws RecognitionException {
		ViewContext _localctx = new ViewContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_view);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(837);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DEFAULT) {
				{
				setState(836);
				match(DEFAULT);
				}
			}

			setState(839);
			match(VIEW);
			setState(840);
			id();
			setState(841);
			match(T__28);
			setState(842);
			viewBlock();
			setState(843);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ViewBlockContext extends ParserRuleContext {
		public List<DescriptionStatementContext> descriptionStatement() {
			return getRuleContexts(DescriptionStatementContext.class);
		}
		public DescriptionStatementContext descriptionStatement(int i) {
			return getRuleContext(DescriptionStatementContext.class,i);
		}
		public List<TagStatementContext> tagStatement() {
			return getRuleContexts(TagStatementContext.class);
		}
		public TagStatementContext tagStatement(int i) {
			return getRuleContext(TagStatementContext.class,i);
		}
		public List<TerminalNode> PRIMARYKEY() { return getTokens(EntityLanguageParser.PRIMARYKEY); }
		public TerminalNode PRIMARYKEY(int i) {
			return getToken(EntityLanguageParser.PRIMARYKEY, i);
		}
		public List<ViewAttributesContext> viewAttributes() {
			return getRuleContexts(ViewAttributesContext.class);
		}
		public ViewAttributesContext viewAttributes(int i) {
			return getRuleContext(ViewAttributesContext.class,i);
		}
		public List<ViewRelationshipsContext> viewRelationships() {
			return getRuleContexts(ViewRelationshipsContext.class);
		}
		public ViewRelationshipsContext viewRelationships(int i) {
			return getRuleContext(ViewRelationshipsContext.class,i);
		}
		public List<TerminalNode> INCLUDE() { return getTokens(EntityLanguageParser.INCLUDE); }
		public TerminalNode INCLUDE(int i) {
			return getToken(EntityLanguageParser.INCLUDE, i);
		}
		public List<TerminalNode> EXCLUDE() { return getTokens(EntityLanguageParser.EXCLUDE); }
		public TerminalNode EXCLUDE(int i) {
			return getToken(EntityLanguageParser.EXCLUDE, i);
		}
		public ViewBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_viewBlock; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitViewBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ViewBlockContext viewBlock() throws RecognitionException {
		ViewBlockContext _localctx = new ViewBlockContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_viewBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(853);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==RELATIONSHIPS || _la==ATTRIBUTES || ((((_la - 132)) & ~0x3f) == 0 && ((1L << (_la - 132)) & ((1L << (INCLUDE - 132)) | (1L << (EXCLUDE - 132)) | (1L << (DESCRIPTION - 132)) | (1L << (TAGS - 132)))) != 0)) {
				{
				setState(851);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case DESCRIPTION:
					{
					setState(845);
					descriptionStatement();
					}
					break;
				case TAGS:
					{
					setState(846);
					tagStatement();
					}
					break;
				case INCLUDE:
				case EXCLUDE:
					{
					setState(847);
					_la = _input.LA(1);
					if ( !(_la==INCLUDE || _la==EXCLUDE) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(848);
					match(PRIMARYKEY);
					}
					break;
				case ATTRIBUTES:
					{
					setState(849);
					viewAttributes();
					}
					break;
				case RELATIONSHIPS:
					{
					setState(850);
					viewRelationships();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(855);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ViewAttributesContext extends ParserRuleContext {
		public TerminalNode ATTRIBUTES() { return getToken(EntityLanguageParser.ATTRIBUTES, 0); }
		public ViewAttributesBlockContext viewAttributesBlock() {
			return getRuleContext(ViewAttributesBlockContext.class,0);
		}
		public ViewAttributesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_viewAttributes; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitViewAttributes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ViewAttributesContext viewAttributes() throws RecognitionException {
		ViewAttributesContext _localctx = new ViewAttributesContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_viewAttributes);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(856);
			match(ATTRIBUTES);
			setState(857);
			match(T__28);
			setState(858);
			viewAttributesBlock();
			setState(859);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ViewAttributesBlockContext extends ParserRuleContext {
		public List<ViewAttributeIncludeContext> viewAttributeInclude() {
			return getRuleContexts(ViewAttributeIncludeContext.class);
		}
		public ViewAttributeIncludeContext viewAttributeInclude(int i) {
			return getRuleContext(ViewAttributeIncludeContext.class,i);
		}
		public List<ViewAttributeExcludeContext> viewAttributeExclude() {
			return getRuleContexts(ViewAttributeExcludeContext.class);
		}
		public ViewAttributeExcludeContext viewAttributeExclude(int i) {
			return getRuleContext(ViewAttributeExcludeContext.class,i);
		}
		public ViewAttributesBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_viewAttributesBlock; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitViewAttributesBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ViewAttributesBlockContext viewAttributesBlock() throws RecognitionException {
		ViewAttributesBlockContext _localctx = new ViewAttributesBlockContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_viewAttributesBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(865);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==INCLUDE || _la==EXCLUDE) {
				{
				setState(863);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case INCLUDE:
					{
					setState(861);
					viewAttributeInclude();
					}
					break;
				case EXCLUDE:
					{
					setState(862);
					viewAttributeExclude();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(867);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ViewRelationshipsContext extends ParserRuleContext {
		public TerminalNode RELATIONSHIPS() { return getToken(EntityLanguageParser.RELATIONSHIPS, 0); }
		public ViewRelationshipsBlockContext viewRelationshipsBlock() {
			return getRuleContext(ViewRelationshipsBlockContext.class,0);
		}
		public ViewRelationshipsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_viewRelationships; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitViewRelationships(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ViewRelationshipsContext viewRelationships() throws RecognitionException {
		ViewRelationshipsContext _localctx = new ViewRelationshipsContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_viewRelationships);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(868);
			match(RELATIONSHIPS);
			setState(869);
			match(T__28);
			setState(870);
			viewRelationshipsBlock();
			setState(871);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ViewRelationshipsBlockContext extends ParserRuleContext {
		public List<ViewRelationshipIncludeContext> viewRelationshipInclude() {
			return getRuleContexts(ViewRelationshipIncludeContext.class);
		}
		public ViewRelationshipIncludeContext viewRelationshipInclude(int i) {
			return getRuleContext(ViewRelationshipIncludeContext.class,i);
		}
		public List<ViewRelationshipExcludeContext> viewRelationshipExclude() {
			return getRuleContexts(ViewRelationshipExcludeContext.class);
		}
		public ViewRelationshipExcludeContext viewRelationshipExclude(int i) {
			return getRuleContext(ViewRelationshipExcludeContext.class,i);
		}
		public ViewRelationshipsBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_viewRelationshipsBlock; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitViewRelationshipsBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ViewRelationshipsBlockContext viewRelationshipsBlock() throws RecognitionException {
		ViewRelationshipsBlockContext _localctx = new ViewRelationshipsBlockContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_viewRelationshipsBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(877);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==INCLUDE || _la==EXCLUDE) {
				{
				setState(875);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case INCLUDE:
					{
					setState(873);
					viewRelationshipInclude();
					}
					break;
				case EXCLUDE:
					{
					setState(874);
					viewRelationshipExclude();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(879);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ViewAttributeIncludeContext extends ParserRuleContext {
		public TerminalNode INCLUDE() { return getToken(EntityLanguageParser.INCLUDE, 0); }
		public ViewIdentifierListContext viewIdentifierList() {
			return getRuleContext(ViewIdentifierListContext.class,0);
		}
		public TerminalNode ARRAY() { return getToken(EntityLanguageParser.ARRAY, 0); }
		public TerminalNode CREATION() { return getToken(EntityLanguageParser.CREATION, 0); }
		public TerminalNode MODIFICATION() { return getToken(EntityLanguageParser.MODIFICATION, 0); }
		public TerminalNode TAGGED() { return getToken(EntityLanguageParser.TAGGED, 0); }
		public ViewTaggedListContext viewTaggedList() {
			return getRuleContext(ViewTaggedListContext.class,0);
		}
		public TerminalNode SECONDARY() { return getToken(EntityLanguageParser.SECONDARY, 0); }
		public TerminalNode ENTITIES() { return getToken(EntityLanguageParser.ENTITIES, 0); }
		public ViewAttributeIncludeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_viewAttributeInclude; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitViewAttributeInclude(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ViewAttributeIncludeContext viewAttributeInclude() throws RecognitionException {
		ViewAttributeIncludeContext _localctx = new ViewAttributeIncludeContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_viewAttributeInclude);
		int _la;
		try {
			setState(893);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,63,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(880);
				match(INCLUDE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(881);
				match(INCLUDE);
				setState(882);
				viewIdentifierList();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(883);
				match(INCLUDE);
				setState(884);
				_la = _input.LA(1);
				if ( !(_la==CREATION || _la==MODIFICATION || _la==ARRAY) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(885);
				match(INCLUDE);
				setState(886);
				match(TAGGED);
				setState(887);
				viewTaggedList();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(888);
				match(INCLUDE);
				setState(889);
				match(SECONDARY);
				setState(890);
				match(ENTITIES);
				setState(891);
				match(TAGGED);
				setState(892);
				viewTaggedList();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ViewAttributeExcludeContext extends ParserRuleContext {
		public TerminalNode EXCLUDE() { return getToken(EntityLanguageParser.EXCLUDE, 0); }
		public ViewIdentifierListContext viewIdentifierList() {
			return getRuleContext(ViewIdentifierListContext.class,0);
		}
		public TerminalNode ARRAY() { return getToken(EntityLanguageParser.ARRAY, 0); }
		public TerminalNode CREATION() { return getToken(EntityLanguageParser.CREATION, 0); }
		public TerminalNode MODIFICATION() { return getToken(EntityLanguageParser.MODIFICATION, 0); }
		public TerminalNode TAGGED() { return getToken(EntityLanguageParser.TAGGED, 0); }
		public ViewTaggedListContext viewTaggedList() {
			return getRuleContext(ViewTaggedListContext.class,0);
		}
		public TerminalNode SECONDARY() { return getToken(EntityLanguageParser.SECONDARY, 0); }
		public TerminalNode ENTITIES() { return getToken(EntityLanguageParser.ENTITIES, 0); }
		public ViewAttributeExcludeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_viewAttributeExclude; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitViewAttributeExclude(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ViewAttributeExcludeContext viewAttributeExclude() throws RecognitionException {
		ViewAttributeExcludeContext _localctx = new ViewAttributeExcludeContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_viewAttributeExclude);
		int _la;
		try {
			setState(908);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,64,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(895);
				match(EXCLUDE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(896);
				match(EXCLUDE);
				setState(897);
				viewIdentifierList();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(898);
				match(EXCLUDE);
				setState(899);
				_la = _input.LA(1);
				if ( !(_la==CREATION || _la==MODIFICATION || _la==ARRAY) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(900);
				match(EXCLUDE);
				setState(901);
				match(TAGGED);
				setState(902);
				viewTaggedList();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(903);
				match(EXCLUDE);
				setState(904);
				match(SECONDARY);
				setState(905);
				match(ENTITIES);
				setState(906);
				match(TAGGED);
				setState(907);
				viewTaggedList();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ViewRelationshipIncludeContext extends ParserRuleContext {
		public TerminalNode INCLUDE() { return getToken(EntityLanguageParser.INCLUDE, 0); }
		public TerminalNode TOONE() { return getToken(EntityLanguageParser.TOONE, 0); }
		public TerminalNode TOMANY() { return getToken(EntityLanguageParser.TOMANY, 0); }
		public TerminalNode PARENT() { return getToken(EntityLanguageParser.PARENT, 0); }
		public TerminalNode ENTITY() { return getToken(EntityLanguageParser.ENTITY, 0); }
		public List<IdContext> id() {
			return getRuleContexts(IdContext.class);
		}
		public IdContext id(int i) {
			return getRuleContext(IdContext.class,i);
		}
		public TerminalNode AS() { return getToken(EntityLanguageParser.AS, 0); }
		public TerminalNode WITH() { return getToken(EntityLanguageParser.WITH, 0); }
		public TerminalNode PRIMARYKEY() { return getToken(EntityLanguageParser.PRIMARYKEY, 0); }
		public TerminalNode VIEW() { return getToken(EntityLanguageParser.VIEW, 0); }
		public TerminalNode TAGGED() { return getToken(EntityLanguageParser.TAGGED, 0); }
		public ViewTaggedListContext viewTaggedList() {
			return getRuleContext(ViewTaggedListContext.class,0);
		}
		public ViewRelationshipIncludeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_viewRelationshipInclude; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitViewRelationshipInclude(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ViewRelationshipIncludeContext viewRelationshipInclude() throws RecognitionException {
		ViewRelationshipIncludeContext _localctx = new ViewRelationshipIncludeContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_viewRelationshipInclude);
		int _la;
		try {
			setState(933);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,70,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(910);
				match(INCLUDE);
				setState(911);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PARENT) | (1L << TOONE) | (1L << TOMANY))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(913);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,65,_ctx) ) {
				case 1:
					{
					setState(912);
					match(ENTITY);
					}
					break;
				}
				setState(916);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,66,_ctx) ) {
				case 1:
					{
					setState(915);
					id();
					}
					break;
				}
				setState(920);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AS) {
					{
					setState(918);
					match(AS);
					setState(919);
					id();
					}
				}

				setState(928);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WITH) {
					{
					setState(922);
					match(WITH);
					setState(926);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case PRIMARYKEY:
						{
						setState(923);
						match(PRIMARYKEY);
						}
						break;
					case VIEW:
						{
						setState(924);
						match(VIEW);
						setState(925);
						id();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(930);
				match(INCLUDE);
				setState(931);
				match(TAGGED);
				setState(932);
				viewTaggedList();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ViewRelationshipExcludeContext extends ParserRuleContext {
		public TerminalNode EXCLUDE() { return getToken(EntityLanguageParser.EXCLUDE, 0); }
		public TerminalNode TOONE() { return getToken(EntityLanguageParser.TOONE, 0); }
		public TerminalNode TOMANY() { return getToken(EntityLanguageParser.TOMANY, 0); }
		public TerminalNode PARENT() { return getToken(EntityLanguageParser.PARENT, 0); }
		public TerminalNode ENTITY() { return getToken(EntityLanguageParser.ENTITY, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TerminalNode TAGGED() { return getToken(EntityLanguageParser.TAGGED, 0); }
		public ViewTaggedListContext viewTaggedList() {
			return getRuleContext(ViewTaggedListContext.class,0);
		}
		public ViewRelationshipExcludeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_viewRelationshipExclude; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitViewRelationshipExclude(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ViewRelationshipExcludeContext viewRelationshipExclude() throws RecognitionException {
		ViewRelationshipExcludeContext _localctx = new ViewRelationshipExcludeContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_viewRelationshipExclude);
		int _la;
		try {
			setState(947);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,73,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(935);
				match(EXCLUDE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(936);
				match(EXCLUDE);
				setState(937);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PARENT) | (1L << TOONE) | (1L << TOMANY))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(939);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,71,_ctx) ) {
				case 1:
					{
					setState(938);
					match(ENTITY);
					}
					break;
				}
				setState(942);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,72,_ctx) ) {
				case 1:
					{
					setState(941);
					id();
					}
					break;
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(944);
				match(EXCLUDE);
				setState(945);
				match(TAGGED);
				setState(946);
				viewTaggedList();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ViewTaggedListItemContext extends ParserRuleContext {
		public List<TerminalNode> STRING() { return getTokens(EntityLanguageParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(EntityLanguageParser.STRING, i);
		}
		public List<TerminalNode> AND() { return getTokens(EntityLanguageParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(EntityLanguageParser.AND, i);
		}
		public ViewTaggedListItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_viewTaggedListItem; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitViewTaggedListItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ViewTaggedListItemContext viewTaggedListItem() throws RecognitionException {
		ViewTaggedListItemContext _localctx = new ViewTaggedListItemContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_viewTaggedListItem);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(949);
			match(STRING);
			setState(954);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(950);
				match(AND);
				setState(951);
				match(STRING);
				}
				}
				setState(956);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ViewTaggedListContext extends ParserRuleContext {
		public List<ViewTaggedListItemContext> viewTaggedListItem() {
			return getRuleContexts(ViewTaggedListItemContext.class);
		}
		public ViewTaggedListItemContext viewTaggedListItem(int i) {
			return getRuleContext(ViewTaggedListItemContext.class,i);
		}
		public ViewTaggedListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_viewTaggedList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitViewTaggedList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ViewTaggedListContext viewTaggedList() throws RecognitionException {
		ViewTaggedListContext _localctx = new ViewTaggedListContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_viewTaggedList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(957);
			viewTaggedListItem();
			setState(962);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(958);
				match(T__4);
				setState(959);
				viewTaggedListItem();
				}
				}
				setState(964);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ViewIdentifierListContext extends ParserRuleContext {
		public List<IdContext> id() {
			return getRuleContexts(IdContext.class);
		}
		public IdContext id(int i) {
			return getRuleContext(IdContext.class,i);
		}
		public ViewIdentifierListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_viewIdentifierList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitViewIdentifierList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ViewIdentifierListContext viewIdentifierList() throws RecognitionException {
		ViewIdentifierListContext _localctx = new ViewIdentifierListContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_viewIdentifierList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(965);
			match(T__3);
			setState(966);
			id();
			setState(971);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(967);
				match(T__4);
				setState(968);
				id();
				}
				}
				setState(973);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(974);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnumStatementContext extends ParserRuleContext {
		public TerminalNode ENUM() { return getToken(EntityLanguageParser.ENUM, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TerminalNode EXTERN() { return getToken(EntityLanguageParser.EXTERN, 0); }
		public List<EnumItemContext> enumItem() {
			return getRuleContexts(EnumItemContext.class);
		}
		public EnumItemContext enumItem(int i) {
			return getRuleContext(EnumItemContext.class,i);
		}
		public List<DescriptionStatementContext> descriptionStatement() {
			return getRuleContexts(DescriptionStatementContext.class);
		}
		public DescriptionStatementContext descriptionStatement(int i) {
			return getRuleContext(DescriptionStatementContext.class,i);
		}
		public List<TagStatementContext> tagStatement() {
			return getRuleContexts(TagStatementContext.class);
		}
		public TagStatementContext tagStatement(int i) {
			return getRuleContext(TagStatementContext.class,i);
		}
		public EnumStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitEnumStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnumStatementContext enumStatement() throws RecognitionException {
		EnumStatementContext _localctx = new EnumStatementContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_enumStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(977);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EXTERN) {
				{
				setState(976);
				match(EXTERN);
				}
			}

			setState(979);
			match(ENUM);
			setState(980);
			id();
			setState(981);
			match(T__28);
			setState(985); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(985);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,78,_ctx) ) {
				case 1:
					{
					setState(982);
					enumItem();
					}
					break;
				case 2:
					{
					setState(983);
					descriptionStatement();
					}
					break;
				case 3:
					{
					setState(984);
					tagStatement();
					}
					break;
				}
				}
				setState(987); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MODULE) | (1L << ENTITY) | (1L << PRIMARYKEY) | (1L << ATTRIBUTE) | (1L << UNIQUE) | (1L << CREATION) | (1L << MODIFICATION) | (1L << PARENT) | (1L << ORDERED) | (1L << SEQUENTIAL) | (1L << EXTERN) | (1L << TRANSIENT) | (1L << PRIMARY) | (1L << SECONDARY) | (1L << FLATTEN) | (1L << RELATIONSHIPS) | (1L << RELATIONSHIP) | (1L << ENTITIES) | (1L << ENUM) | (1L << ENUMITEM) | (1L << TYPEDEF) | (1L << UNUSED) | (1L << CONFIGURATION) | (1L << FORMATTING))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (FORMAT - 64)) | (1L << (OUTPUT - 64)) | (1L << (DIRECTORY - 64)) | (1L << (TRANSFORM - 64)) | (1L << (TEMPLATE - 64)) | (1L << (PATH - 64)) | (1L << (ENDPOINT - 64)) | (1L << (INHERITED - 64)) | (1L << (REPOSITORY - 64)) | (1L << (TAG - 64)) | (1L << (VALUE - 64)) | (1L << (SPACE - 64)) | (1L << (IMPORT - 64)) | (1L << (FROM - 64)) | (1L << (ORGANIZATION - 64)) | (1L << (NAME - 64)) | (1L << (NULLABLE - 64)) | (1L << (LANGUAGE - 64)) | (1L << (VERSION - 64)) | (1L << (SELF - 64)) | (1L << (OPERATORS - 64)) | (1L << (KEYWORDS - 64)) | (1L << (SHORT - 64)) | (1L << (LONG - 64)) | (1L << (HUMAN - 64)) | (1L << (READABLE - 64)) | (1L << (IDENTIFICATION - 64)) | (1L << (DOMAIN - 64)) | (1L << (ATTRIBUTES - 64)) | (1L << (REPLACES - 64)) | (1L << (PREFIX - 64)) | (1L << (SUFFIX - 64)) | (1L << (RENAME - 64)) | (1L << (TAGGING - 64)) | (1L << (NAMESPACE - 64)) | (1L << (READ - 64)) | (1L << (WRITE - 64)) | (1L << (WHEN - 64)) | (1L << (REQUIRES - 64)) | (1L << (ROLE - 64)) | (1L << (USER - 64)) | (1L << (IF - 64)) | (1L << (APPLY - 64)))) != 0) || ((((_la - 130)) & ~0x3f) == 0 && ((1L << (_la - 130)) & ((1L << (DEFAULT - 130)) | (1L << (VIEW - 130)) | (1L << (INCLUDE - 130)) | (1L << (EXCLUDE - 130)) | (1L << (AND - 130)) | (1L << (ABSTRACT - 130)) | (1L << (EXTENDS - 130)) | (1L << (INTERFACE - 130)) | (1L << (CONFIG - 130)) | (1L << (METADATA - 130)) | (1L << (CONTEXT - 130)) | (1L << (ARGUMENT - 130)) | (1L << (OPERATION - 130)) | (1L << (QUERY - 130)) | (1L << (PARAM - 130)) | (1L << (STATUS - 130)) | (1L << (CUSTOM - 130)) | (1L << (TYPE - 130)) | (1L << (TO - 130)) | (1L << (PREPEND - 130)) | (1L << (APPEND - 130)) | (1L << (BOOLEAN_TYPE - 130)) | (1L << (INT32_TYPE - 130)) | (1L << (INT64_TYPE - 130)) | (1L << (BYTE_TYPE - 130)) | (1L << (UUID_TYPE - 130)) | (1L << (FLOAT_TYPE - 130)) | (1L << (DOUBLE_TYPE - 130)) | (1L << (STRING_TYPE - 130)) | (1L << (DATE_TYPE - 130)) | (1L << (ASSET_TYPE - 130)) | (1L << (ARRAY - 130)) | (1L << (DESCRIPTION - 130)) | (1L << (TAGS - 130)) | (1L << (MACRO_START - 130)) | (1L << (ID - 130)))) != 0) );
			setState(989);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnumItemContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TerminalNode INTEGER() { return getToken(EntityLanguageParser.INTEGER, 0); }
		public EnumItemBodyContext enumItemBody() {
			return getRuleContext(EnumItemBodyContext.class,0);
		}
		public EnumItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumItem; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitEnumItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnumItemContext enumItem() throws RecognitionException {
		EnumItemContext _localctx = new EnumItemContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_enumItem);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(991);
			id();
			setState(992);
			match(T__27);
			setState(993);
			match(INTEGER);
			setState(998);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__28) {
				{
				setState(994);
				match(T__28);
				setState(995);
				enumItemBody();
				setState(996);
				match(T__29);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnumItemBodyContext extends ParserRuleContext {
		public List<DescriptionStatementContext> descriptionStatement() {
			return getRuleContexts(DescriptionStatementContext.class);
		}
		public DescriptionStatementContext descriptionStatement(int i) {
			return getRuleContext(DescriptionStatementContext.class,i);
		}
		public List<TagStatementContext> tagStatement() {
			return getRuleContexts(TagStatementContext.class);
		}
		public TagStatementContext tagStatement(int i) {
			return getRuleContext(TagStatementContext.class,i);
		}
		public EnumItemBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumItemBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitEnumItemBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnumItemBodyContext enumItemBody() throws RecognitionException {
		EnumItemBodyContext _localctx = new EnumItemBodyContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_enumItemBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1004);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DESCRIPTION || _la==TAGS) {
				{
				setState(1002);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case DESCRIPTION:
					{
					setState(1000);
					descriptionStatement();
					}
					break;
				case TAGS:
					{
					setState(1001);
					tagStatement();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(1006);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypedefStatementContext extends ParserRuleContext {
		public TerminalNode TYPEDEF() { return getToken(EntityLanguageParser.TYPEDEF, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TypedefBodyContext typedefBody() {
			return getRuleContext(TypedefBodyContext.class,0);
		}
		public TerminalNode INT32_TYPE() { return getToken(EntityLanguageParser.INT32_TYPE, 0); }
		public TerminalNode INT64_TYPE() { return getToken(EntityLanguageParser.INT64_TYPE, 0); }
		public TypedefStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typedefStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitTypedefStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypedefStatementContext typedefStatement() throws RecognitionException {
		TypedefStatementContext _localctx = new TypedefStatementContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_typedefStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1007);
			match(TYPEDEF);
			setState(1008);
			_la = _input.LA(1);
			if ( !(_la==INT32_TYPE || _la==INT64_TYPE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(1009);
			id();
			setState(1010);
			match(T__28);
			setState(1011);
			typedefBody();
			setState(1012);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypedefBodyContext extends ParserRuleContext {
		public List<BitfieldContext> bitfield() {
			return getRuleContexts(BitfieldContext.class);
		}
		public BitfieldContext bitfield(int i) {
			return getRuleContext(BitfieldContext.class,i);
		}
		public List<DescriptionStatementContext> descriptionStatement() {
			return getRuleContexts(DescriptionStatementContext.class);
		}
		public DescriptionStatementContext descriptionStatement(int i) {
			return getRuleContext(DescriptionStatementContext.class,i);
		}
		public List<TagStatementContext> tagStatement() {
			return getRuleContexts(TagStatementContext.class);
		}
		public TagStatementContext tagStatement(int i) {
			return getRuleContext(TagStatementContext.class,i);
		}
		public TypedefBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typedefBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitTypedefBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypedefBodyContext typedefBody() throws RecognitionException {
		TypedefBodyContext _localctx = new TypedefBodyContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_typedefBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1017); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(1017);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__3:
					{
					setState(1014);
					bitfield();
					}
					break;
				case DESCRIPTION:
					{
					setState(1015);
					descriptionStatement();
					}
					break;
				case TAGS:
					{
					setState(1016);
					tagStatement();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(1019); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__3 || _la==DESCRIPTION || _la==TAGS );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BitfieldContext extends ParserRuleContext {
		public BitCountContext bitCount() {
			return getRuleContext(BitCountContext.class,0);
		}
		public TerminalNode UNUSED() { return getToken(EntityLanguageParser.UNUSED, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public List<DescriptionStatementContext> descriptionStatement() {
			return getRuleContexts(DescriptionStatementContext.class);
		}
		public DescriptionStatementContext descriptionStatement(int i) {
			return getRuleContext(DescriptionStatementContext.class,i);
		}
		public BitfieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bitfield; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitBitfield(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BitfieldContext bitfield() throws RecognitionException {
		BitfieldContext _localctx = new BitfieldContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_bitfield);
		int _la;
		try {
			setState(1036);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,87,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1021);
				bitCount();
				setState(1022);
				match(UNUSED);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1024);
				bitCount();
				setState(1025);
				id();
				setState(1034);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__28) {
					{
					setState(1026);
					match(T__28);
					setState(1028); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(1027);
						descriptionStatement();
						}
						}
						setState(1030); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==DESCRIPTION );
					setState(1032);
					match(T__29);
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BitCountContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(EntityLanguageParser.INTEGER, 0); }
		public BitCountContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bitCount; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitBitCount(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BitCountContext bitCount() throws RecognitionException {
		BitCountContext _localctx = new BitCountContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_bitCount);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1038);
			match(T__3);
			setState(1039);
			match(INTEGER);
			setState(1040);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainContext extends ParserRuleContext {
		public TerminalNode DOMAIN() { return getToken(EntityLanguageParser.DOMAIN, 0); }
		public List<TerminalNode> ID() { return getTokens(EntityLanguageParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(EntityLanguageParser.ID, i);
		}
		public DomainBodyContext domainBody() {
			return getRuleContext(DomainBodyContext.class,0);
		}
		public TerminalNode EXTENDS() { return getToken(EntityLanguageParser.EXTENDS, 0); }
		public DomainContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domain; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomain(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainContext domain() throws RecognitionException {
		DomainContext _localctx = new DomainContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_domain);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1042);
			match(DOMAIN);
			setState(1043);
			match(ID);
			setState(1049);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EXTENDS:
				{
				{
				setState(1044);
				match(EXTENDS);
				setState(1045);
				match(ID);
				}
				}
				break;
			case T__3:
				{
				{
				setState(1046);
				match(T__3);
				setState(1047);
				match(ID);
				setState(1048);
				match(T__1);
				}
				}
				break;
			case T__28:
				break;
			default:
				break;
			}
			setState(1051);
			match(T__28);
			setState(1052);
			domainBody();
			setState(1053);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainBodyContext extends ParserRuleContext {
		public List<DescriptionStatementContext> descriptionStatement() {
			return getRuleContexts(DescriptionStatementContext.class);
		}
		public DescriptionStatementContext descriptionStatement(int i) {
			return getRuleContext(DescriptionStatementContext.class,i);
		}
		public List<TagStatementContext> tagStatement() {
			return getRuleContexts(TagStatementContext.class);
		}
		public TagStatementContext tagStatement(int i) {
			return getRuleContext(TagStatementContext.class,i);
		}
		public List<DomainTaggingContext> domainTagging() {
			return getRuleContexts(DomainTaggingContext.class);
		}
		public DomainTaggingContext domainTagging(int i) {
			return getRuleContext(DomainTaggingContext.class,i);
		}
		public List<DomainNamingContext> domainNaming() {
			return getRuleContexts(DomainNamingContext.class);
		}
		public DomainNamingContext domainNaming(int i) {
			return getRuleContext(DomainNamingContext.class,i);
		}
		public List<DomainEntityContext> domainEntity() {
			return getRuleContexts(DomainEntityContext.class);
		}
		public DomainEntityContext domainEntity(int i) {
			return getRuleContext(DomainEntityContext.class,i);
		}
		public List<DomainEnumContext> domainEnum() {
			return getRuleContexts(DomainEnumContext.class);
		}
		public DomainEnumContext domainEnum(int i) {
			return getRuleContext(DomainEnumContext.class,i);
		}
		public List<DomainViewContext> domainView() {
			return getRuleContexts(DomainViewContext.class);
		}
		public DomainViewContext domainView(int i) {
			return getRuleContext(DomainViewContext.class,i);
		}
		public List<DomainModuleContext> domainModule() {
			return getRuleContexts(DomainModuleContext.class);
		}
		public DomainModuleContext domainModule(int i) {
			return getRuleContext(DomainModuleContext.class,i);
		}
		public List<DomainNamespaceContext> domainNamespace() {
			return getRuleContexts(DomainNamespaceContext.class);
		}
		public DomainNamespaceContext domainNamespace(int i) {
			return getRuleContext(DomainNamespaceContext.class,i);
		}
		public List<DomainAttributesContext> domainAttributes() {
			return getRuleContexts(DomainAttributesContext.class);
		}
		public DomainAttributesContext domainAttributes(int i) {
			return getRuleContext(DomainAttributesContext.class,i);
		}
		public List<DomainFlattenSecondaryEntitiesContext> domainFlattenSecondaryEntities() {
			return getRuleContexts(DomainFlattenSecondaryEntitiesContext.class);
		}
		public DomainFlattenSecondaryEntitiesContext domainFlattenSecondaryEntities(int i) {
			return getRuleContext(DomainFlattenSecondaryEntitiesContext.class,i);
		}
		public List<DomainIncludeEntitiesContext> domainIncludeEntities() {
			return getRuleContexts(DomainIncludeEntitiesContext.class);
		}
		public DomainIncludeEntitiesContext domainIncludeEntities(int i) {
			return getRuleContext(DomainIncludeEntitiesContext.class,i);
		}
		public List<DomainExcludeEntitiesContext> domainExcludeEntities() {
			return getRuleContexts(DomainExcludeEntitiesContext.class);
		}
		public DomainExcludeEntitiesContext domainExcludeEntities(int i) {
			return getRuleContext(DomainExcludeEntitiesContext.class,i);
		}
		public List<DomainApplyTemplateContext> domainApplyTemplate() {
			return getRuleContexts(DomainApplyTemplateContext.class);
		}
		public DomainApplyTemplateContext domainApplyTemplate(int i) {
			return getRuleContext(DomainApplyTemplateContext.class,i);
		}
		public DomainBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainBodyContext domainBody() throws RecognitionException {
		DomainBodyContext _localctx = new DomainBodyContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_domainBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1071);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MODULE) | (1L << ENTITY) | (1L << FLATTEN) | (1L << ENUM))) != 0) || ((((_la - 105)) & ~0x3f) == 0 && ((1L << (_la - 105)) & ((1L << (ATTRIBUTES - 105)) | (1L << (TAGGING - 105)) | (1L << (NAMING - 105)) | (1L << (NAMESPACE - 105)) | (1L << (APPLY - 105)) | (1L << (DEFAULT - 105)) | (1L << (VIEW - 105)) | (1L << (INCLUDE - 105)) | (1L << (EXCLUDE - 105)))) != 0) || _la==DESCRIPTION || _la==TAGS) {
				{
				setState(1069);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,89,_ctx) ) {
				case 1:
					{
					setState(1055);
					descriptionStatement();
					}
					break;
				case 2:
					{
					setState(1056);
					tagStatement();
					}
					break;
				case 3:
					{
					setState(1057);
					domainTagging();
					}
					break;
				case 4:
					{
					setState(1058);
					domainNaming();
					}
					break;
				case 5:
					{
					setState(1059);
					domainEntity();
					}
					break;
				case 6:
					{
					setState(1060);
					domainEnum();
					}
					break;
				case 7:
					{
					setState(1061);
					domainView();
					}
					break;
				case 8:
					{
					setState(1062);
					domainModule();
					}
					break;
				case 9:
					{
					setState(1063);
					domainNamespace();
					}
					break;
				case 10:
					{
					setState(1064);
					domainAttributes();
					}
					break;
				case 11:
					{
					setState(1065);
					domainFlattenSecondaryEntities();
					}
					break;
				case 12:
					{
					setState(1066);
					domainIncludeEntities();
					}
					break;
				case 13:
					{
					setState(1067);
					domainExcludeEntities();
					}
					break;
				case 14:
					{
					setState(1068);
					domainApplyTemplate();
					}
					break;
				}
				}
				setState(1073);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NamespaceIdentContext extends ParserRuleContext {
		public List<IdContext> id() {
			return getRuleContexts(IdContext.class);
		}
		public IdContext id(int i) {
			return getRuleContext(IdContext.class,i);
		}
		public NamespaceIdentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namespaceIdent; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitNamespaceIdent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NamespaceIdentContext namespaceIdent() throws RecognitionException {
		NamespaceIdentContext _localctx = new NamespaceIdentContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_namespaceIdent);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1074);
			id();
			setState(1079);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(1075);
				match(T__2);
				setState(1076);
				id();
				}
				}
				setState(1081);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainNamespaceContext extends ParserRuleContext {
		public TerminalNode NAMESPACE() { return getToken(EntityLanguageParser.NAMESPACE, 0); }
		public NamespaceIdentContext namespaceIdent() {
			return getRuleContext(NamespaceIdentContext.class,0);
		}
		public TerminalNode SPACE() { return getToken(EntityLanguageParser.SPACE, 0); }
		public DomainNamespaceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainNamespace; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainNamespace(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainNamespaceContext domainNamespace() throws RecognitionException {
		DomainNamespaceContext _localctx = new DomainNamespaceContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_domainNamespace);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1082);
			match(NAMESPACE);
			setState(1084);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,92,_ctx) ) {
			case 1:
				{
				setState(1083);
				match(SPACE);
				}
				break;
			}
			setState(1086);
			namespaceIdent();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainFlattenSecondaryEntitiesContext extends ParserRuleContext {
		public TerminalNode FLATTEN() { return getToken(EntityLanguageParser.FLATTEN, 0); }
		public TerminalNode SECONDARY() { return getToken(EntityLanguageParser.SECONDARY, 0); }
		public TerminalNode ENTITIES() { return getToken(EntityLanguageParser.ENTITIES, 0); }
		public DomainFlattenSecondaryEntitiesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainFlattenSecondaryEntities; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainFlattenSecondaryEntities(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainFlattenSecondaryEntitiesContext domainFlattenSecondaryEntities() throws RecognitionException {
		DomainFlattenSecondaryEntitiesContext _localctx = new DomainFlattenSecondaryEntitiesContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_domainFlattenSecondaryEntities);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1088);
			match(FLATTEN);
			setState(1089);
			match(SECONDARY);
			setState(1090);
			match(ENTITIES);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainEnumContext extends ParserRuleContext {
		public TerminalNode ENUM() { return getToken(EntityLanguageParser.ENUM, 0); }
		public TerminalNode ID() { return getToken(EntityLanguageParser.ID, 0); }
		public DomainEnumBodyContext domainEnumBody() {
			return getRuleContext(DomainEnumBodyContext.class,0);
		}
		public DomainEnumContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainEnum; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainEnum(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainEnumContext domainEnum() throws RecognitionException {
		DomainEnumContext _localctx = new DomainEnumContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_domainEnum);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1092);
			match(ENUM);
			setState(1093);
			match(ID);
			setState(1094);
			match(T__28);
			setState(1095);
			domainEnumBody();
			setState(1096);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainEnumBodyContext extends ParserRuleContext {
		public List<DescriptionStatementContext> descriptionStatement() {
			return getRuleContexts(DescriptionStatementContext.class);
		}
		public DescriptionStatementContext descriptionStatement(int i) {
			return getRuleContext(DescriptionStatementContext.class,i);
		}
		public List<TagStatementContext> tagStatement() {
			return getRuleContexts(TagStatementContext.class);
		}
		public TagStatementContext tagStatement(int i) {
			return getRuleContext(TagStatementContext.class,i);
		}
		public List<DomainEnumNamingItemContext> domainEnumNamingItem() {
			return getRuleContexts(DomainEnumNamingItemContext.class);
		}
		public DomainEnumNamingItemContext domainEnumNamingItem(int i) {
			return getRuleContext(DomainEnumNamingItemContext.class,i);
		}
		public List<DomainEnumItemContext> domainEnumItem() {
			return getRuleContexts(DomainEnumItemContext.class);
		}
		public DomainEnumItemContext domainEnumItem(int i) {
			return getRuleContext(DomainEnumItemContext.class,i);
		}
		public List<DomainEnumItemRenameToContext> domainEnumItemRenameTo() {
			return getRuleContexts(DomainEnumItemRenameToContext.class);
		}
		public DomainEnumItemRenameToContext domainEnumItemRenameTo(int i) {
			return getRuleContext(DomainEnumItemRenameToContext.class,i);
		}
		public DomainEnumBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainEnumBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainEnumBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainEnumBodyContext domainEnumBody() throws RecognitionException {
		DomainEnumBodyContext _localctx = new DomainEnumBodyContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_domainEnumBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1105);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 110)) & ~0x3f) == 0 && ((1L << (_la - 110)) & ((1L << (RENAME - 110)) | (1L << (NAMING - 110)) | (1L << (DESCRIPTION - 110)) | (1L << (TAGS - 110)))) != 0) || _la==ID) {
				{
				setState(1103);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case DESCRIPTION:
					{
					setState(1098);
					descriptionStatement();
					}
					break;
				case TAGS:
					{
					setState(1099);
					tagStatement();
					}
					break;
				case NAMING:
					{
					setState(1100);
					domainEnumNamingItem();
					}
					break;
				case ID:
					{
					setState(1101);
					domainEnumItem();
					}
					break;
				case RENAME:
					{
					setState(1102);
					domainEnumItemRenameTo();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(1107);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainEnumItemContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(EntityLanguageParser.ID, 0); }
		public DomainEnumItemBodyContext domainEnumItemBody() {
			return getRuleContext(DomainEnumItemBodyContext.class,0);
		}
		public DomainEnumItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainEnumItem; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainEnumItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainEnumItemContext domainEnumItem() throws RecognitionException {
		DomainEnumItemContext _localctx = new DomainEnumItemContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_domainEnumItem);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1108);
			match(ID);
			setState(1109);
			match(T__28);
			setState(1110);
			domainEnumItemBody();
			setState(1111);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainEnumItemBodyContext extends ParserRuleContext {
		public List<DescriptionStatementContext> descriptionStatement() {
			return getRuleContexts(DescriptionStatementContext.class);
		}
		public DescriptionStatementContext descriptionStatement(int i) {
			return getRuleContext(DescriptionStatementContext.class,i);
		}
		public List<TagStatementContext> tagStatement() {
			return getRuleContexts(TagStatementContext.class);
		}
		public TagStatementContext tagStatement(int i) {
			return getRuleContext(TagStatementContext.class,i);
		}
		public DomainEnumItemBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainEnumItemBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainEnumItemBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainEnumItemBodyContext domainEnumItemBody() throws RecognitionException {
		DomainEnumItemBodyContext _localctx = new DomainEnumItemBodyContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_domainEnumItemBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1117);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DESCRIPTION || _la==TAGS) {
				{
				setState(1115);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case DESCRIPTION:
					{
					setState(1113);
					descriptionStatement();
					}
					break;
				case TAGS:
					{
					setState(1114);
					tagStatement();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(1119);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainEnumItemRenameToContext extends ParserRuleContext {
		public TerminalNode RENAME() { return getToken(EntityLanguageParser.RENAME, 0); }
		public List<IdContext> id() {
			return getRuleContexts(IdContext.class);
		}
		public IdContext id(int i) {
			return getRuleContext(IdContext.class,i);
		}
		public TerminalNode TO() { return getToken(EntityLanguageParser.TO, 0); }
		public DomainEnumItemRenameToContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainEnumItemRenameTo; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainEnumItemRenameTo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainEnumItemRenameToContext domainEnumItemRenameTo() throws RecognitionException {
		DomainEnumItemRenameToContext _localctx = new DomainEnumItemRenameToContext(_ctx, getState());
		enterRule(_localctx, 142, RULE_domainEnumItemRenameTo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1120);
			match(RENAME);
			setState(1121);
			id();
			setState(1123);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,97,_ctx) ) {
			case 1:
				{
				setState(1122);
				match(TO);
				}
				break;
			}
			setState(1125);
			id();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainEnumNamingItemContext extends ParserRuleContext {
		public TerminalNode NAMING() { return getToken(EntityLanguageParser.NAMING, 0); }
		public TerminalNode ENUMITEM() { return getToken(EntityLanguageParser.ENUMITEM, 0); }
		public DomainEnumNamingBodyContext domainEnumNamingBody() {
			return getRuleContext(DomainEnumNamingBodyContext.class,0);
		}
		public DomainEnumNamingItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainEnumNamingItem; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainEnumNamingItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainEnumNamingItemContext domainEnumNamingItem() throws RecognitionException {
		DomainEnumNamingItemContext _localctx = new DomainEnumNamingItemContext(_ctx, getState());
		enterRule(_localctx, 144, RULE_domainEnumNamingItem);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1127);
			match(NAMING);
			setState(1128);
			match(ENUMITEM);
			setState(1129);
			match(T__28);
			setState(1130);
			domainEnumNamingBody();
			setState(1131);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainEnumNamingBodyContext extends ParserRuleContext {
		public List<DomainNamingMethodContext> domainNamingMethod() {
			return getRuleContexts(DomainNamingMethodContext.class);
		}
		public DomainNamingMethodContext domainNamingMethod(int i) {
			return getRuleContext(DomainNamingMethodContext.class,i);
		}
		public List<DomainNamingPrefixContext> domainNamingPrefix() {
			return getRuleContexts(DomainNamingPrefixContext.class);
		}
		public DomainNamingPrefixContext domainNamingPrefix(int i) {
			return getRuleContext(DomainNamingPrefixContext.class,i);
		}
		public List<DomainNamingSuffixContext> domainNamingSuffix() {
			return getRuleContexts(DomainNamingSuffixContext.class);
		}
		public DomainNamingSuffixContext domainNamingSuffix(int i) {
			return getRuleContext(DomainNamingSuffixContext.class,i);
		}
		public DomainEnumNamingBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainEnumNamingBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainEnumNamingBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainEnumNamingBodyContext domainEnumNamingBody() throws RecognitionException {
		DomainEnumNamingBodyContext _localctx = new DomainEnumNamingBodyContext(_ctx, getState());
		enterRule(_localctx, 146, RULE_domainEnumNamingBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1138);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 107)) & ~0x3f) == 0 && ((1L << (_la - 107)) & ((1L << (METHOD - 107)) | (1L << (PREFIX - 107)) | (1L << (SUFFIX - 107)))) != 0)) {
				{
				setState(1136);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case METHOD:
					{
					setState(1133);
					domainNamingMethod();
					}
					break;
				case PREFIX:
					{
					setState(1134);
					domainNamingPrefix();
					}
					break;
				case SUFFIX:
					{
					setState(1135);
					domainNamingSuffix();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(1140);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainTaggingContext extends ParserRuleContext {
		public TerminalNode TAGGING() { return getToken(EntityLanguageParser.TAGGING, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public List<DomainTaggingTagContext> domainTaggingTag() {
			return getRuleContexts(DomainTaggingTagContext.class);
		}
		public DomainTaggingTagContext domainTaggingTag(int i) {
			return getRuleContext(DomainTaggingTagContext.class,i);
		}
		public DomainTaggingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainTagging; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainTagging(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainTaggingContext domainTagging() throws RecognitionException {
		DomainTaggingContext _localctx = new DomainTaggingContext(_ctx, getState());
		enterRule(_localctx, 148, RULE_domainTagging);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1141);
			match(TAGGING);
			setState(1142);
			id();
			setState(1143);
			match(T__28);
			setState(1147);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TAG) {
				{
				{
				setState(1144);
				domainTaggingTag();
				}
				}
				setState(1149);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1150);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainTaggingTagContext extends ParserRuleContext {
		public TerminalNode TAG() { return getToken(EntityLanguageParser.TAG, 0); }
		public TerminalNode STRING() { return getToken(EntityLanguageParser.STRING, 0); }
		public TerminalNode STARTSWITH() { return getToken(EntityLanguageParser.STARTSWITH, 0); }
		public List<DescriptionStatementContext> descriptionStatement() {
			return getRuleContexts(DescriptionStatementContext.class);
		}
		public DescriptionStatementContext descriptionStatement(int i) {
			return getRuleContext(DescriptionStatementContext.class,i);
		}
		public List<TagStatementContext> tagStatement() {
			return getRuleContexts(TagStatementContext.class);
		}
		public TagStatementContext tagStatement(int i) {
			return getRuleContext(TagStatementContext.class,i);
		}
		public List<DomainTaggingTagValueContext> domainTaggingTagValue() {
			return getRuleContexts(DomainTaggingTagValueContext.class);
		}
		public DomainTaggingTagValueContext domainTaggingTagValue(int i) {
			return getRuleContext(DomainTaggingTagValueContext.class,i);
		}
		public DomainTaggingTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainTaggingTag; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainTaggingTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainTaggingTagContext domainTaggingTag() throws RecognitionException {
		DomainTaggingTagContext _localctx = new DomainTaggingTagContext(_ctx, getState());
		enterRule(_localctx, 150, RULE_domainTaggingTag);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1152);
			match(TAG);
			setState(1154);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STARTSWITH) {
				{
				setState(1153);
				match(STARTSWITH);
				}
			}

			setState(1156);
			match(STRING);
			setState(1157);
			match(T__28);
			setState(1163);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VALUE || _la==DESCRIPTION || _la==TAGS) {
				{
				setState(1161);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case DESCRIPTION:
					{
					setState(1158);
					descriptionStatement();
					}
					break;
				case TAGS:
					{
					setState(1159);
					tagStatement();
					}
					break;
				case VALUE:
					{
					setState(1160);
					domainTaggingTagValue();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(1165);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1166);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainTaggingTagValueContext extends ParserRuleContext {
		public TerminalNode VALUE() { return getToken(EntityLanguageParser.VALUE, 0); }
		public DomainTaggingTagValueTypeContext domainTaggingTagValueType() {
			return getRuleContext(DomainTaggingTagValueTypeContext.class,0);
		}
		public List<DescriptionStatementContext> descriptionStatement() {
			return getRuleContexts(DescriptionStatementContext.class);
		}
		public DescriptionStatementContext descriptionStatement(int i) {
			return getRuleContext(DescriptionStatementContext.class,i);
		}
		public List<TagStatementContext> tagStatement() {
			return getRuleContexts(TagStatementContext.class);
		}
		public TagStatementContext tagStatement(int i) {
			return getRuleContext(TagStatementContext.class,i);
		}
		public DomainTaggingTagValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainTaggingTagValue; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainTaggingTagValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainTaggingTagValueContext domainTaggingTagValue() throws RecognitionException {
		DomainTaggingTagValueContext _localctx = new DomainTaggingTagValueContext(_ctx, getState());
		enterRule(_localctx, 152, RULE_domainTaggingTagValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1168);
			match(VALUE);
			setState(1169);
			domainTaggingTagValueType();
			setState(1170);
			match(T__28);
			setState(1175);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DESCRIPTION || _la==TAGS) {
				{
				setState(1173);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case DESCRIPTION:
					{
					setState(1171);
					descriptionStatement();
					}
					break;
				case TAGS:
					{
					setState(1172);
					tagStatement();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(1177);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1178);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainTaggingTagValueTypeContext extends ParserRuleContext {
		public TerminalNode INT32_TYPE() { return getToken(EntityLanguageParser.INT32_TYPE, 0); }
		public TerminalNode INT64_TYPE() { return getToken(EntityLanguageParser.INT64_TYPE, 0); }
		public TerminalNode FLOAT_TYPE() { return getToken(EntityLanguageParser.FLOAT_TYPE, 0); }
		public TerminalNode DOUBLE_TYPE() { return getToken(EntityLanguageParser.DOUBLE_TYPE, 0); }
		public TerminalNode BOOLEAN_TYPE() { return getToken(EntityLanguageParser.BOOLEAN_TYPE, 0); }
		public TerminalNode STRING_TYPE() { return getToken(EntityLanguageParser.STRING_TYPE, 0); }
		public DomainTaggingTagValueTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainTaggingTagValueType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainTaggingTagValueType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainTaggingTagValueTypeContext domainTaggingTagValueType() throws RecognitionException {
		DomainTaggingTagValueTypeContext _localctx = new DomainTaggingTagValueTypeContext(_ctx, getState());
		enterRule(_localctx, 154, RULE_domainTaggingTagValueType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1180);
			_la = _input.LA(1);
			if ( !(((((_la - 159)) & ~0x3f) == 0 && ((1L << (_la - 159)) & ((1L << (BOOLEAN_TYPE - 159)) | (1L << (INT32_TYPE - 159)) | (1L << (INT64_TYPE - 159)) | (1L << (FLOAT_TYPE - 159)) | (1L << (DOUBLE_TYPE - 159)) | (1L << (STRING_TYPE - 159)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainNamingContext extends ParserRuleContext {
		public TerminalNode NAMING() { return getToken(EntityLanguageParser.NAMING, 0); }
		public List<NamingClassContext> namingClass() {
			return getRuleContexts(NamingClassContext.class);
		}
		public NamingClassContext namingClass(int i) {
			return getRuleContext(NamingClassContext.class,i);
		}
		public DomainNamingBodyContext domainNamingBody() {
			return getRuleContext(DomainNamingBodyContext.class,0);
		}
		public DomainNamingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainNaming; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainNaming(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainNamingContext domainNaming() throws RecognitionException {
		DomainNamingContext _localctx = new DomainNamingContext(_ctx, getState());
		enterRule(_localctx, 156, RULE_domainNaming);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1182);
			match(NAMING);
			setState(1183);
			namingClass();
			setState(1188);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(1184);
				match(T__4);
				setState(1185);
				namingClass();
				}
				}
				setState(1190);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1191);
			match(T__28);
			setState(1192);
			domainNamingBody();
			setState(1193);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NamingClassContext extends ParserRuleContext {
		public TerminalNode SPACE() { return getToken(EntityLanguageParser.SPACE, 0); }
		public TerminalNode MODULE() { return getToken(EntityLanguageParser.MODULE, 0); }
		public TerminalNode ENTITY() { return getToken(EntityLanguageParser.ENTITY, 0); }
		public TerminalNode ATTRIBUTE() { return getToken(EntityLanguageParser.ATTRIBUTE, 0); }
		public TerminalNode RELATIONSHIP() { return getToken(EntityLanguageParser.RELATIONSHIP, 0); }
		public TerminalNode ENUM() { return getToken(EntityLanguageParser.ENUM, 0); }
		public TerminalNode ENUMITEM() { return getToken(EntityLanguageParser.ENUMITEM, 0); }
		public TerminalNode TYPEDEF() { return getToken(EntityLanguageParser.TYPEDEF, 0); }
		public NamingClassContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namingClass; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitNamingClass(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NamingClassContext namingClass() throws RecognitionException {
		NamingClassContext _localctx = new NamingClassContext(_ctx, getState());
		enterRule(_localctx, 158, RULE_namingClass);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1196);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 32)) & ~0x3f) == 0 && ((1L << (_la - 32)) & ((1L << (MODULE - 32)) | (1L << (ENTITY - 32)) | (1L << (ATTRIBUTE - 32)) | (1L << (RELATIONSHIP - 32)) | (1L << (ENUM - 32)) | (1L << (ENUMITEM - 32)) | (1L << (TYPEDEF - 32)) | (1L << (SPACE - 32)))) != 0)) {
				{
				setState(1195);
				_la = _input.LA(1);
				if ( !(((((_la - 32)) & ~0x3f) == 0 && ((1L << (_la - 32)) & ((1L << (MODULE - 32)) | (1L << (ENTITY - 32)) | (1L << (ATTRIBUTE - 32)) | (1L << (RELATIONSHIP - 32)) | (1L << (ENUM - 32)) | (1L << (ENUMITEM - 32)) | (1L << (TYPEDEF - 32)) | (1L << (SPACE - 32)))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainNamingBodyContext extends ParserRuleContext {
		public List<DomainNamingMethodContext> domainNamingMethod() {
			return getRuleContexts(DomainNamingMethodContext.class);
		}
		public DomainNamingMethodContext domainNamingMethod(int i) {
			return getRuleContext(DomainNamingMethodContext.class,i);
		}
		public List<DomainNamingPrefixContext> domainNamingPrefix() {
			return getRuleContexts(DomainNamingPrefixContext.class);
		}
		public DomainNamingPrefixContext domainNamingPrefix(int i) {
			return getRuleContext(DomainNamingPrefixContext.class,i);
		}
		public List<DomainNamingSuffixContext> domainNamingSuffix() {
			return getRuleContexts(DomainNamingSuffixContext.class);
		}
		public DomainNamingSuffixContext domainNamingSuffix(int i) {
			return getRuleContext(DomainNamingSuffixContext.class,i);
		}
		public List<DomainNamingPrimaryKeyContext> domainNamingPrimaryKey() {
			return getRuleContexts(DomainNamingPrimaryKeyContext.class);
		}
		public DomainNamingPrimaryKeyContext domainNamingPrimaryKey(int i) {
			return getRuleContext(DomainNamingPrimaryKeyContext.class,i);
		}
		public List<DomainNamingWithUnitsContext> domainNamingWithUnits() {
			return getRuleContexts(DomainNamingWithUnitsContext.class);
		}
		public DomainNamingWithUnitsContext domainNamingWithUnits(int i) {
			return getRuleContext(DomainNamingWithUnitsContext.class,i);
		}
		public List<DomainNamingWithoutUnitsContext> domainNamingWithoutUnits() {
			return getRuleContexts(DomainNamingWithoutUnitsContext.class);
		}
		public DomainNamingWithoutUnitsContext domainNamingWithoutUnits(int i) {
			return getRuleContext(DomainNamingWithoutUnitsContext.class,i);
		}
		public DomainNamingBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainNamingBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainNamingBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainNamingBodyContext domainNamingBody() throws RecognitionException {
		DomainNamingBodyContext _localctx = new DomainNamingBodyContext(_ctx, getState());
		enterRule(_localctx, 160, RULE_domainNamingBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1206);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PRIMARYKEY || ((((_la - 107)) & ~0x3f) == 0 && ((1L << (_la - 107)) & ((1L << (METHOD - 107)) | (1L << (PREFIX - 107)) | (1L << (SUFFIX - 107)) | (1L << (WITH - 107)) | (1L << (WITHOUT - 107)))) != 0)) {
				{
				setState(1204);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case METHOD:
					{
					setState(1198);
					domainNamingMethod();
					}
					break;
				case PREFIX:
					{
					setState(1199);
					domainNamingPrefix();
					}
					break;
				case SUFFIX:
					{
					setState(1200);
					domainNamingSuffix();
					}
					break;
				case PRIMARYKEY:
					{
					setState(1201);
					domainNamingPrimaryKey();
					}
					break;
				case WITH:
					{
					setState(1202);
					domainNamingWithUnits();
					}
					break;
				case WITHOUT:
					{
					setState(1203);
					domainNamingWithoutUnits();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(1208);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainNamingMethodContext extends ParserRuleContext {
		public TerminalNode METHOD() { return getToken(EntityLanguageParser.METHOD, 0); }
		public TerminalNode ID() { return getToken(EntityLanguageParser.ID, 0); }
		public DomainNamingMethodContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainNamingMethod; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainNamingMethod(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainNamingMethodContext domainNamingMethod() throws RecognitionException {
		DomainNamingMethodContext _localctx = new DomainNamingMethodContext(_ctx, getState());
		enterRule(_localctx, 162, RULE_domainNamingMethod);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1209);
			match(METHOD);
			setState(1210);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainNamingPrefixContext extends ParserRuleContext {
		public TerminalNode PREFIX() { return getToken(EntityLanguageParser.PREFIX, 0); }
		public TerminalNode STRING() { return getToken(EntityLanguageParser.STRING, 0); }
		public DomainNamingPrefixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainNamingPrefix; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainNamingPrefix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainNamingPrefixContext domainNamingPrefix() throws RecognitionException {
		DomainNamingPrefixContext _localctx = new DomainNamingPrefixContext(_ctx, getState());
		enterRule(_localctx, 164, RULE_domainNamingPrefix);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1212);
			match(PREFIX);
			setState(1213);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainNamingSuffixContext extends ParserRuleContext {
		public TerminalNode SUFFIX() { return getToken(EntityLanguageParser.SUFFIX, 0); }
		public TerminalNode STRING() { return getToken(EntityLanguageParser.STRING, 0); }
		public DomainNamingSuffixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainNamingSuffix; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainNamingSuffix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainNamingSuffixContext domainNamingSuffix() throws RecognitionException {
		DomainNamingSuffixContext _localctx = new DomainNamingSuffixContext(_ctx, getState());
		enterRule(_localctx, 166, RULE_domainNamingSuffix);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1215);
			match(SUFFIX);
			setState(1216);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainNamingPrimaryKeyContext extends ParserRuleContext {
		public TerminalNode PRIMARYKEY() { return getToken(EntityLanguageParser.PRIMARYKEY, 0); }
		public TerminalNode ID() { return getToken(EntityLanguageParser.ID, 0); }
		public DomainNamingPrimaryKeyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainNamingPrimaryKey; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainNamingPrimaryKey(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainNamingPrimaryKeyContext domainNamingPrimaryKey() throws RecognitionException {
		DomainNamingPrimaryKeyContext _localctx = new DomainNamingPrimaryKeyContext(_ctx, getState());
		enterRule(_localctx, 168, RULE_domainNamingPrimaryKey);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1218);
			match(PRIMARYKEY);
			setState(1219);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainNamingWithUnitsContext extends ParserRuleContext {
		public TerminalNode WITH() { return getToken(EntityLanguageParser.WITH, 0); }
		public TerminalNode UNITS() { return getToken(EntityLanguageParser.UNITS, 0); }
		public DomainNamingWithUnitsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainNamingWithUnits; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainNamingWithUnits(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainNamingWithUnitsContext domainNamingWithUnits() throws RecognitionException {
		DomainNamingWithUnitsContext _localctx = new DomainNamingWithUnitsContext(_ctx, getState());
		enterRule(_localctx, 170, RULE_domainNamingWithUnits);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1221);
			match(WITH);
			setState(1222);
			match(UNITS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainNamingWithoutUnitsContext extends ParserRuleContext {
		public TerminalNode WITHOUT() { return getToken(EntityLanguageParser.WITHOUT, 0); }
		public TerminalNode UNITS() { return getToken(EntityLanguageParser.UNITS, 0); }
		public DomainNamingWithoutUnitsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainNamingWithoutUnits; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainNamingWithoutUnits(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainNamingWithoutUnitsContext domainNamingWithoutUnits() throws RecognitionException {
		DomainNamingWithoutUnitsContext _localctx = new DomainNamingWithoutUnitsContext(_ctx, getState());
		enterRule(_localctx, 172, RULE_domainNamingWithoutUnits);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1224);
			match(WITHOUT);
			setState(1225);
			match(UNITS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainApplyTemplateContext extends ParserRuleContext {
		public TerminalNode APPLY() { return getToken(EntityLanguageParser.APPLY, 0); }
		public TerminalNode TEMPLATE() { return getToken(EntityLanguageParser.TEMPLATE, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public DomainApplyTemplateBodyContext domainApplyTemplateBody() {
			return getRuleContext(DomainApplyTemplateBodyContext.class,0);
		}
		public TerminalNode DEFAULT() { return getToken(EntityLanguageParser.DEFAULT, 0); }
		public DomainApplyTemplateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainApplyTemplate; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainApplyTemplate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainApplyTemplateContext domainApplyTemplate() throws RecognitionException {
		DomainApplyTemplateContext _localctx = new DomainApplyTemplateContext(_ctx, getState());
		enterRule(_localctx, 174, RULE_domainApplyTemplate);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1228);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DEFAULT) {
				{
				setState(1227);
				match(DEFAULT);
				}
			}

			setState(1230);
			match(APPLY);
			setState(1231);
			match(TEMPLATE);
			setState(1232);
			id();
			setState(1233);
			match(T__28);
			setState(1234);
			domainApplyTemplateBody();
			setState(1235);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainModuleApplyTemplateContext extends ParserRuleContext {
		public TerminalNode APPLY() { return getToken(EntityLanguageParser.APPLY, 0); }
		public TerminalNode TEMPLATE() { return getToken(EntityLanguageParser.TEMPLATE, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public DomainApplyTemplateBodyContext domainApplyTemplateBody() {
			return getRuleContext(DomainApplyTemplateBodyContext.class,0);
		}
		public TerminalNode DEFAULT() { return getToken(EntityLanguageParser.DEFAULT, 0); }
		public DomainModuleApplyTemplateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainModuleApplyTemplate; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainModuleApplyTemplate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainModuleApplyTemplateContext domainModuleApplyTemplate() throws RecognitionException {
		DomainModuleApplyTemplateContext _localctx = new DomainModuleApplyTemplateContext(_ctx, getState());
		enterRule(_localctx, 176, RULE_domainModuleApplyTemplate);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1238);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DEFAULT) {
				{
				setState(1237);
				match(DEFAULT);
				}
			}

			setState(1240);
			match(APPLY);
			setState(1241);
			match(TEMPLATE);
			setState(1242);
			id();
			setState(1243);
			match(T__28);
			setState(1244);
			domainApplyTemplateBody();
			setState(1245);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainEntityApplyTemplateContext extends ParserRuleContext {
		public TerminalNode APPLY() { return getToken(EntityLanguageParser.APPLY, 0); }
		public TerminalNode TEMPLATE() { return getToken(EntityLanguageParser.TEMPLATE, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public DomainApplyTemplateBodyContext domainApplyTemplateBody() {
			return getRuleContext(DomainApplyTemplateBodyContext.class,0);
		}
		public DomainEntityApplyTemplateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainEntityApplyTemplate; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainEntityApplyTemplate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainEntityApplyTemplateContext domainEntityApplyTemplate() throws RecognitionException {
		DomainEntityApplyTemplateContext _localctx = new DomainEntityApplyTemplateContext(_ctx, getState());
		enterRule(_localctx, 178, RULE_domainEntityApplyTemplate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1247);
			match(APPLY);
			setState(1248);
			match(TEMPLATE);
			setState(1249);
			id();
			setState(1250);
			match(T__28);
			setState(1251);
			domainApplyTemplateBody();
			setState(1252);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainApplyTemplateBodyContext extends ParserRuleContext {
		public List<DescriptionStatementContext> descriptionStatement() {
			return getRuleContexts(DescriptionStatementContext.class);
		}
		public DescriptionStatementContext descriptionStatement(int i) {
			return getRuleContext(DescriptionStatementContext.class,i);
		}
		public List<TagStatementContext> tagStatement() {
			return getRuleContexts(TagStatementContext.class);
		}
		public TagStatementContext tagStatement(int i) {
			return getRuleContext(TagStatementContext.class,i);
		}
		public List<TemplateConfigContext> templateConfig() {
			return getRuleContexts(TemplateConfigContext.class);
		}
		public TemplateConfigContext templateConfig(int i) {
			return getRuleContext(TemplateConfigContext.class,i);
		}
		public DomainApplyTemplateBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainApplyTemplateBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainApplyTemplateBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainApplyTemplateBodyContext domainApplyTemplateBody() throws RecognitionException {
		DomainApplyTemplateBodyContext _localctx = new DomainApplyTemplateBodyContext(_ctx, getState());
		enterRule(_localctx, 180, RULE_domainApplyTemplateBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1259);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 143)) & ~0x3f) == 0 && ((1L << (_la - 143)) & ((1L << (CONFIG - 143)) | (1L << (DESCRIPTION - 143)) | (1L << (TAGS - 143)))) != 0)) {
				{
				setState(1257);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case DESCRIPTION:
					{
					setState(1254);
					descriptionStatement();
					}
					break;
				case TAGS:
					{
					setState(1255);
					tagStatement();
					}
					break;
				case CONFIG:
					{
					setState(1256);
					templateConfig();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(1261);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefaultTemplateConfigContext extends ParserRuleContext {
		public TerminalNode DEFAULT() { return getToken(EntityLanguageParser.DEFAULT, 0); }
		public TerminalNode CONFIG() { return getToken(EntityLanguageParser.CONFIG, 0); }
		public JsonObjContext jsonObj() {
			return getRuleContext(JsonObjContext.class,0);
		}
		public DefaultTemplateConfigContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultTemplateConfig; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDefaultTemplateConfig(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefaultTemplateConfigContext defaultTemplateConfig() throws RecognitionException {
		DefaultTemplateConfigContext _localctx = new DefaultTemplateConfigContext(_ctx, getState());
		enterRule(_localctx, 182, RULE_defaultTemplateConfig);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1262);
			match(DEFAULT);
			setState(1263);
			match(CONFIG);
			setState(1264);
			jsonObj();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TemplateConfigContext extends ParserRuleContext {
		public TerminalNode CONFIG() { return getToken(EntityLanguageParser.CONFIG, 0); }
		public JsonObjContext jsonObj() {
			return getRuleContext(JsonObjContext.class,0);
		}
		public TemplateConfigContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_templateConfig; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitTemplateConfig(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TemplateConfigContext templateConfig() throws RecognitionException {
		TemplateConfigContext _localctx = new TemplateConfigContext(_ctx, getState());
		enterRule(_localctx, 184, RULE_templateConfig);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1266);
			match(CONFIG);
			setState(1267);
			jsonObj();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class JsonObjContext extends ParserRuleContext {
		public List<JsonPairContext> jsonPair() {
			return getRuleContexts(JsonPairContext.class);
		}
		public JsonPairContext jsonPair(int i) {
			return getRuleContext(JsonPairContext.class,i);
		}
		public JsonObjContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonObj; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitJsonObj(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JsonObjContext jsonObj() throws RecognitionException {
		JsonObjContext _localctx = new JsonObjContext(_ctx, getState());
		enterRule(_localctx, 186, RULE_jsonObj);
		int _la;
		try {
			int _alt;
			setState(1285);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,116,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1269);
				match(T__28);
				setState(1270);
				jsonPair();
				setState(1275);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,114,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1271);
						match(T__4);
						setState(1272);
						jsonPair();
						}
						} 
					}
					setState(1277);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,114,_ctx);
				}
				setState(1279);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__4) {
					{
					setState(1278);
					match(T__4);
					}
				}

				setState(1281);
				match(T__29);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1283);
				match(T__28);
				setState(1284);
				match(T__29);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class JsonPairContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(EntityLanguageParser.STRING, 0); }
		public JsonValueContext jsonValue() {
			return getRuleContext(JsonValueContext.class,0);
		}
		public JsonPairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonPair; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitJsonPair(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JsonPairContext jsonPair() throws RecognitionException {
		JsonPairContext _localctx = new JsonPairContext(_ctx, getState());
		enterRule(_localctx, 188, RULE_jsonPair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1287);
			match(STRING);
			setState(1288);
			match(T__0);
			setState(1289);
			jsonValue();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class JsonArrContext extends ParserRuleContext {
		public List<JsonValueContext> jsonValue() {
			return getRuleContexts(JsonValueContext.class);
		}
		public JsonValueContext jsonValue(int i) {
			return getRuleContext(JsonValueContext.class,i);
		}
		public JsonArrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonArr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitJsonArr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JsonArrContext jsonArr() throws RecognitionException {
		JsonArrContext _localctx = new JsonArrContext(_ctx, getState());
		enterRule(_localctx, 190, RULE_jsonArr);
		int _la;
		try {
			int _alt;
			setState(1307);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,119,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1291);
				match(T__5);
				setState(1292);
				jsonValue();
				setState(1297);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,117,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1293);
						match(T__4);
						setState(1294);
						jsonValue();
						}
						} 
					}
					setState(1299);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,117,_ctx);
				}
				setState(1301);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__4) {
					{
					setState(1300);
					match(T__4);
					}
				}

				setState(1303);
				match(T__6);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1305);
				match(T__5);
				setState(1306);
				match(T__6);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class JsonValueContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(EntityLanguageParser.STRING, 0); }
		public TerminalNode INTEGER() { return getToken(EntityLanguageParser.INTEGER, 0); }
		public TerminalNode FLOAT() { return getToken(EntityLanguageParser.FLOAT, 0); }
		public JsonObjContext jsonObj() {
			return getRuleContext(JsonObjContext.class,0);
		}
		public JsonArrContext jsonArr() {
			return getRuleContext(JsonArrContext.class,0);
		}
		public JsonValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonValue; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitJsonValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JsonValueContext jsonValue() throws RecognitionException {
		JsonValueContext _localctx = new JsonValueContext(_ctx, getState());
		enterRule(_localctx, 192, RULE_jsonValue);
		try {
			setState(1317);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(1309);
				match(STRING);
				}
				break;
			case INTEGER:
				enterOuterAlt(_localctx, 2);
				{
				setState(1310);
				match(INTEGER);
				}
				break;
			case FLOAT:
				enterOuterAlt(_localctx, 3);
				{
				setState(1311);
				match(FLOAT);
				}
				break;
			case T__28:
				enterOuterAlt(_localctx, 4);
				{
				setState(1312);
				jsonObj();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1313);
				jsonArr();
				}
				break;
			case T__25:
				enterOuterAlt(_localctx, 6);
				{
				setState(1314);
				match(T__25);
				}
				break;
			case T__26:
				enterOuterAlt(_localctx, 7);
				{
				setState(1315);
				match(T__26);
				}
				break;
			case T__30:
				enterOuterAlt(_localctx, 8);
				{
				setState(1316);
				match(T__30);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainModuleContext extends ParserRuleContext {
		public TerminalNode MODULE() { return getToken(EntityLanguageParser.MODULE, 0); }
		public TerminalNode ID() { return getToken(EntityLanguageParser.ID, 0); }
		public DomainModuleBodyContext domainModuleBody() {
			return getRuleContext(DomainModuleBodyContext.class,0);
		}
		public DomainModuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainModule; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainModule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainModuleContext domainModule() throws RecognitionException {
		DomainModuleContext _localctx = new DomainModuleContext(_ctx, getState());
		enterRule(_localctx, 194, RULE_domainModule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1319);
			match(MODULE);
			setState(1320);
			match(ID);
			setState(1321);
			match(T__28);
			setState(1322);
			domainModuleBody();
			setState(1323);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainModuleBodyContext extends ParserRuleContext {
		public List<DescriptionStatementContext> descriptionStatement() {
			return getRuleContexts(DescriptionStatementContext.class);
		}
		public DescriptionStatementContext descriptionStatement(int i) {
			return getRuleContext(DescriptionStatementContext.class,i);
		}
		public List<TagStatementContext> tagStatement() {
			return getRuleContexts(TagStatementContext.class);
		}
		public TagStatementContext tagStatement(int i) {
			return getRuleContext(TagStatementContext.class,i);
		}
		public List<DomainModuleApplyTemplateContext> domainModuleApplyTemplate() {
			return getRuleContexts(DomainModuleApplyTemplateContext.class);
		}
		public DomainModuleApplyTemplateContext domainModuleApplyTemplate(int i) {
			return getRuleContext(DomainModuleApplyTemplateContext.class,i);
		}
		public List<DomainEntityContext> domainEntity() {
			return getRuleContexts(DomainEntityContext.class);
		}
		public DomainEntityContext domainEntity(int i) {
			return getRuleContext(DomainEntityContext.class,i);
		}
		public DomainModuleBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainModuleBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainModuleBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainModuleBodyContext domainModuleBody() throws RecognitionException {
		DomainModuleBodyContext _localctx = new DomainModuleBodyContext(_ctx, getState());
		enterRule(_localctx, 196, RULE_domainModuleBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1331);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ENTITY || ((((_la - 125)) & ~0x3f) == 0 && ((1L << (_la - 125)) & ((1L << (APPLY - 125)) | (1L << (DEFAULT - 125)) | (1L << (DESCRIPTION - 125)) | (1L << (TAGS - 125)))) != 0)) {
				{
				setState(1329);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case DESCRIPTION:
					{
					setState(1325);
					descriptionStatement();
					}
					break;
				case TAGS:
					{
					setState(1326);
					tagStatement();
					}
					break;
				case APPLY:
				case DEFAULT:
					{
					setState(1327);
					domainModuleApplyTemplate();
					}
					break;
				case ENTITY:
					{
					setState(1328);
					domainEntity();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(1333);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainViewContext extends ParserRuleContext {
		public ViewContext view() {
			return getRuleContext(ViewContext.class,0);
		}
		public DomainViewContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainView; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainView(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainViewContext domainView() throws RecognitionException {
		DomainViewContext _localctx = new DomainViewContext(_ctx, getState());
		enterRule(_localctx, 198, RULE_domainView);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1334);
			view();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainEntityContext extends ParserRuleContext {
		public TerminalNode ENTITY() { return getToken(EntityLanguageParser.ENTITY, 0); }
		public List<TerminalNode> ID() { return getTokens(EntityLanguageParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(EntityLanguageParser.ID, i);
		}
		public TerminalNode RENAME() { return getToken(EntityLanguageParser.RENAME, 0); }
		public DomainEntityBodyContext domainEntityBody() {
			return getRuleContext(DomainEntityBodyContext.class,0);
		}
		public DomainEntityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainEntity; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainEntity(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainEntityContext domainEntity() throws RecognitionException {
		DomainEntityContext _localctx = new DomainEntityContext(_ctx, getState());
		enterRule(_localctx, 200, RULE_domainEntity);
		int _la;
		try {
			setState(1350);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,124,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1336);
				match(ENTITY);
				setState(1337);
				match(ID);
				setState(1338);
				match(RENAME);
				setState(1339);
				match(ID);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1340);
				match(ENTITY);
				setState(1341);
				match(ID);
				setState(1344);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==RENAME) {
					{
					setState(1342);
					match(RENAME);
					setState(1343);
					match(ID);
					}
				}

				setState(1346);
				match(T__28);
				setState(1347);
				domainEntityBody();
				setState(1348);
				match(T__29);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainEntityBodyContext extends ParserRuleContext {
		public List<DescriptionStatementContext> descriptionStatement() {
			return getRuleContexts(DescriptionStatementContext.class);
		}
		public DescriptionStatementContext descriptionStatement(int i) {
			return getRuleContext(DescriptionStatementContext.class,i);
		}
		public List<TagStatementContext> tagStatement() {
			return getRuleContexts(TagStatementContext.class);
		}
		public TagStatementContext tagStatement(int i) {
			return getRuleContext(TagStatementContext.class,i);
		}
		public List<DomainEntityNamespaceContext> domainEntityNamespace() {
			return getRuleContexts(DomainEntityNamespaceContext.class);
		}
		public DomainEntityNamespaceContext domainEntityNamespace(int i) {
			return getRuleContext(DomainEntityNamespaceContext.class,i);
		}
		public List<DomainAttributesContext> domainAttributes() {
			return getRuleContexts(DomainAttributesContext.class);
		}
		public DomainAttributesContext domainAttributes(int i) {
			return getRuleContext(DomainAttributesContext.class,i);
		}
		public List<DomainRelationshipsContext> domainRelationships() {
			return getRuleContexts(DomainRelationshipsContext.class);
		}
		public DomainRelationshipsContext domainRelationships(int i) {
			return getRuleContext(DomainRelationshipsContext.class,i);
		}
		public List<ViewContext> view() {
			return getRuleContexts(ViewContext.class);
		}
		public ViewContext view(int i) {
			return getRuleContext(ViewContext.class,i);
		}
		public List<DomainInterfaceStatementContext> domainInterfaceStatement() {
			return getRuleContexts(DomainInterfaceStatementContext.class);
		}
		public DomainInterfaceStatementContext domainInterfaceStatement(int i) {
			return getRuleContext(DomainInterfaceStatementContext.class,i);
		}
		public List<DomainEntityApplyTemplateContext> domainEntityApplyTemplate() {
			return getRuleContexts(DomainEntityApplyTemplateContext.class);
		}
		public DomainEntityApplyTemplateContext domainEntityApplyTemplate(int i) {
			return getRuleContext(DomainEntityApplyTemplateContext.class,i);
		}
		public DomainEntityBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainEntityBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainEntityBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainEntityBodyContext domainEntityBody() throws RecognitionException {
		DomainEntityBodyContext _localctx = new DomainEntityBodyContext(_ctx, getState());
		enterRule(_localctx, 202, RULE_domainEntityBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1362);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==RELATIONSHIPS || _la==ATTRIBUTES || ((((_la - 114)) & ~0x3f) == 0 && ((1L << (_la - 114)) & ((1L << (NAMESPACE - 114)) | (1L << (APPLY - 114)) | (1L << (DEFAULT - 114)) | (1L << (VIEW - 114)) | (1L << (INTERFACE - 114)) | (1L << (DESCRIPTION - 114)) | (1L << (TAGS - 114)))) != 0)) {
				{
				setState(1360);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case DESCRIPTION:
					{
					setState(1352);
					descriptionStatement();
					}
					break;
				case TAGS:
					{
					setState(1353);
					tagStatement();
					}
					break;
				case NAMESPACE:
					{
					setState(1354);
					domainEntityNamespace();
					}
					break;
				case ATTRIBUTES:
					{
					setState(1355);
					domainAttributes();
					}
					break;
				case RELATIONSHIPS:
					{
					setState(1356);
					domainRelationships();
					}
					break;
				case DEFAULT:
				case VIEW:
					{
					setState(1357);
					view();
					}
					break;
				case INTERFACE:
					{
					setState(1358);
					domainInterfaceStatement();
					}
					break;
				case APPLY:
					{
					setState(1359);
					domainEntityApplyTemplate();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(1364);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainEntityNamespaceContext extends ParserRuleContext {
		public TerminalNode NAMESPACE() { return getToken(EntityLanguageParser.NAMESPACE, 0); }
		public NamespaceIdentContext namespaceIdent() {
			return getRuleContext(NamespaceIdentContext.class,0);
		}
		public DomainEntityNamespaceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainEntityNamespace; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainEntityNamespace(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainEntityNamespaceContext domainEntityNamespace() throws RecognitionException {
		DomainEntityNamespaceContext _localctx = new DomainEntityNamespaceContext(_ctx, getState());
		enterRule(_localctx, 204, RULE_domainEntityNamespace);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1365);
			match(NAMESPACE);
			setState(1366);
			namespaceIdent();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainAttributesContext extends ParserRuleContext {
		public TerminalNode ATTRIBUTES() { return getToken(EntityLanguageParser.ATTRIBUTES, 0); }
		public DomainAttributesBodyContext domainAttributesBody() {
			return getRuleContext(DomainAttributesBodyContext.class,0);
		}
		public DomainAttributesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainAttributes; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainAttributes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainAttributesContext domainAttributes() throws RecognitionException {
		DomainAttributesContext _localctx = new DomainAttributesContext(_ctx, getState());
		enterRule(_localctx, 206, RULE_domainAttributes);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1368);
			match(ATTRIBUTES);
			setState(1369);
			match(T__28);
			setState(1370);
			domainAttributesBody();
			setState(1371);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainAttributesBodyContext extends ParserRuleContext {
		public List<DomainAttributesRenameToContext> domainAttributesRenameTo() {
			return getRuleContexts(DomainAttributesRenameToContext.class);
		}
		public DomainAttributesRenameToContext domainAttributesRenameTo(int i) {
			return getRuleContext(DomainAttributesRenameToContext.class,i);
		}
		public List<DomainAttributesRenameAppendPrependContext> domainAttributesRenameAppendPrepend() {
			return getRuleContexts(DomainAttributesRenameAppendPrependContext.class);
		}
		public DomainAttributesRenameAppendPrependContext domainAttributesRenameAppendPrepend(int i) {
			return getRuleContext(DomainAttributesRenameAppendPrependContext.class,i);
		}
		public List<DomainAttributeReplacesContext> domainAttributeReplaces() {
			return getRuleContexts(DomainAttributeReplacesContext.class);
		}
		public DomainAttributeReplacesContext domainAttributeReplaces(int i) {
			return getRuleContext(DomainAttributeReplacesContext.class,i);
		}
		public List<DomainAttributeExcludeContext> domainAttributeExclude() {
			return getRuleContexts(DomainAttributeExcludeContext.class);
		}
		public DomainAttributeExcludeContext domainAttributeExclude(int i) {
			return getRuleContext(DomainAttributeExcludeContext.class,i);
		}
		public List<DomainAttributeAddContext> domainAttributeAdd() {
			return getRuleContexts(DomainAttributeAddContext.class);
		}
		public DomainAttributeAddContext domainAttributeAdd(int i) {
			return getRuleContext(DomainAttributeAddContext.class,i);
		}
		public List<DomainVirtualAttributeContext> domainVirtualAttribute() {
			return getRuleContexts(DomainVirtualAttributeContext.class);
		}
		public DomainVirtualAttributeContext domainVirtualAttribute(int i) {
			return getRuleContext(DomainVirtualAttributeContext.class,i);
		}
		public List<DomainAttributeContext> domainAttribute() {
			return getRuleContexts(DomainAttributeContext.class);
		}
		public DomainAttributeContext domainAttribute(int i) {
			return getRuleContext(DomainAttributeContext.class,i);
		}
		public DomainAttributesBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainAttributesBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainAttributesBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainAttributesBodyContext domainAttributesBody() throws RecognitionException {
		DomainAttributesBodyContext _localctx = new DomainAttributesBodyContext(_ctx, getState());
		enterRule(_localctx, 208, RULE_domainAttributesBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1382);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MODULE) | (1L << ENTITY) | (1L << PRIMARYKEY) | (1L << ATTRIBUTE) | (1L << UNIQUE) | (1L << CREATION) | (1L << MODIFICATION) | (1L << OPTIONAL) | (1L << PARENT) | (1L << ORDERED) | (1L << SEQUENTIAL) | (1L << VIRTUAL) | (1L << EXTERN) | (1L << TRANSIENT) | (1L << PRIMARY) | (1L << SECONDARY) | (1L << FLATTEN) | (1L << RELATIONSHIPS) | (1L << RELATIONSHIP) | (1L << MANY) | (1L << ENTITIES) | (1L << ENUM) | (1L << ENUMITEM) | (1L << TYPEDEF) | (1L << UNUSED) | (1L << CONFIGURATION) | (1L << FORMATTING))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (FORMAT - 64)) | (1L << (OUTPUT - 64)) | (1L << (DIRECTORY - 64)) | (1L << (TRANSFORM - 64)) | (1L << (TEMPLATE - 64)) | (1L << (PATH - 64)) | (1L << (ENDPOINT - 64)) | (1L << (INHERITED - 64)) | (1L << (REPOSITORY - 64)) | (1L << (TAG - 64)) | (1L << (VALUE - 64)) | (1L << (SPACE - 64)) | (1L << (IMPORT - 64)) | (1L << (FROM - 64)) | (1L << (ORGANIZATION - 64)) | (1L << (NAME - 64)) | (1L << (NULLABLE - 64)) | (1L << (LANGUAGE - 64)) | (1L << (VERSION - 64)) | (1L << (SELF - 64)) | (1L << (OPERATORS - 64)) | (1L << (KEYWORDS - 64)) | (1L << (SHORT - 64)) | (1L << (LONG - 64)) | (1L << (HUMAN - 64)) | (1L << (READABLE - 64)) | (1L << (IDENTIFICATION - 64)) | (1L << (DOMAIN - 64)) | (1L << (ATTRIBUTES - 64)) | (1L << (REPLACES - 64)) | (1L << (PREFIX - 64)) | (1L << (SUFFIX - 64)) | (1L << (RENAME - 64)) | (1L << (TAGGING - 64)) | (1L << (NAMESPACE - 64)) | (1L << (READ - 64)) | (1L << (WRITE - 64)) | (1L << (WHEN - 64)) | (1L << (REQUIRES - 64)) | (1L << (ROLE - 64)) | (1L << (USER - 64)) | (1L << (IF - 64)) | (1L << (APPLY - 64)))) != 0) || ((((_la - 130)) & ~0x3f) == 0 && ((1L << (_la - 130)) & ((1L << (DEFAULT - 130)) | (1L << (VIEW - 130)) | (1L << (INCLUDE - 130)) | (1L << (EXCLUDE - 130)) | (1L << (ADD - 130)) | (1L << (AND - 130)) | (1L << (ABSTRACT - 130)) | (1L << (EXTENDS - 130)) | (1L << (INTERFACE - 130)) | (1L << (CONFIG - 130)) | (1L << (METADATA - 130)) | (1L << (CONTEXT - 130)) | (1L << (ARGUMENT - 130)) | (1L << (OPERATION - 130)) | (1L << (QUERY - 130)) | (1L << (PARAM - 130)) | (1L << (STATUS - 130)) | (1L << (CUSTOM - 130)) | (1L << (TYPE - 130)) | (1L << (TO - 130)) | (1L << (PREPEND - 130)) | (1L << (APPEND - 130)) | (1L << (BOOLEAN_TYPE - 130)) | (1L << (INT32_TYPE - 130)) | (1L << (INT64_TYPE - 130)) | (1L << (BYTE_TYPE - 130)) | (1L << (UUID_TYPE - 130)) | (1L << (FLOAT_TYPE - 130)) | (1L << (DOUBLE_TYPE - 130)) | (1L << (STRING_TYPE - 130)) | (1L << (DATE_TYPE - 130)) | (1L << (ASSET_TYPE - 130)) | (1L << (ARRAY - 130)) | (1L << (DESCRIPTION - 130)) | (1L << (TAGS - 130)) | (1L << (MACRO_START - 130)) | (1L << (ID - 130)))) != 0)) {
				{
				setState(1380);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,127,_ctx) ) {
				case 1:
					{
					setState(1373);
					domainAttributesRenameTo();
					}
					break;
				case 2:
					{
					setState(1374);
					domainAttributesRenameAppendPrepend();
					}
					break;
				case 3:
					{
					setState(1375);
					domainAttributeReplaces();
					}
					break;
				case 4:
					{
					setState(1376);
					domainAttributeExclude();
					}
					break;
				case 5:
					{
					setState(1377);
					domainAttributeAdd();
					}
					break;
				case 6:
					{
					setState(1378);
					domainVirtualAttribute();
					}
					break;
				case 7:
					{
					setState(1379);
					domainAttribute();
					}
					break;
				}
				}
				setState(1384);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainAttributeExcludeContext extends ParserRuleContext {
		public TerminalNode EXCLUDE() { return getToken(EntityLanguageParser.EXCLUDE, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public DomainAttributeExcludeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainAttributeExclude; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainAttributeExclude(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainAttributeExcludeContext domainAttributeExclude() throws RecognitionException {
		DomainAttributeExcludeContext _localctx = new DomainAttributeExcludeContext(_ctx, getState());
		enterRule(_localctx, 210, RULE_domainAttributeExclude);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1385);
			match(EXCLUDE);
			setState(1386);
			id();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainAttributeAddContext extends ParserRuleContext {
		public TerminalNode ADD() { return getToken(EntityLanguageParser.ADD, 0); }
		public AttributeContext attribute() {
			return getRuleContext(AttributeContext.class,0);
		}
		public DomainAttributeAddContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainAttributeAdd; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainAttributeAdd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainAttributeAddContext domainAttributeAdd() throws RecognitionException {
		DomainAttributeAddContext _localctx = new DomainAttributeAddContext(_ctx, getState());
		enterRule(_localctx, 212, RULE_domainAttributeAdd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1388);
			match(ADD);
			setState(1389);
			attribute();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainAttributesRenameToContext extends ParserRuleContext {
		public TerminalNode RENAME() { return getToken(EntityLanguageParser.RENAME, 0); }
		public List<IdContext> id() {
			return getRuleContexts(IdContext.class);
		}
		public IdContext id(int i) {
			return getRuleContext(IdContext.class,i);
		}
		public TerminalNode TO() { return getToken(EntityLanguageParser.TO, 0); }
		public DomainAttributesRenameToContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainAttributesRenameTo; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainAttributesRenameTo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainAttributesRenameToContext domainAttributesRenameTo() throws RecognitionException {
		DomainAttributesRenameToContext _localctx = new DomainAttributesRenameToContext(_ctx, getState());
		enterRule(_localctx, 214, RULE_domainAttributesRenameTo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1391);
			match(RENAME);
			setState(1392);
			id();
			setState(1394);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,129,_ctx) ) {
			case 1:
				{
				setState(1393);
				match(TO);
				}
				break;
			}
			setState(1396);
			id();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainAttributesRenameAppendPrependContext extends ParserRuleContext {
		public TerminalNode RENAME() { return getToken(EntityLanguageParser.RENAME, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TerminalNode PREPEND() { return getToken(EntityLanguageParser.PREPEND, 0); }
		public TerminalNode APPEND() { return getToken(EntityLanguageParser.APPEND, 0); }
		public TerminalNode ENTITY() { return getToken(EntityLanguageParser.ENTITY, 0); }
		public TerminalNode DOMAIN() { return getToken(EntityLanguageParser.DOMAIN, 0); }
		public DomainAttributesRenameAppendPrependContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainAttributesRenameAppendPrepend; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainAttributesRenameAppendPrepend(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainAttributesRenameAppendPrependContext domainAttributesRenameAppendPrepend() throws RecognitionException {
		DomainAttributesRenameAppendPrependContext _localctx = new DomainAttributesRenameAppendPrependContext(_ctx, getState());
		enterRule(_localctx, 216, RULE_domainAttributesRenameAppendPrepend);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1398);
			match(RENAME);
			setState(1399);
			id();
			setState(1400);
			_la = _input.LA(1);
			if ( !(_la==PREPEND || _la==APPEND) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(1401);
			_la = _input.LA(1);
			if ( !(_la==ENTITY || _la==DOMAIN) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainAttributeReplacesContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<IdContext> id() {
			return getRuleContexts(IdContext.class);
		}
		public IdContext id(int i) {
			return getRuleContext(IdContext.class,i);
		}
		public TerminalNode REPLACES() { return getToken(EntityLanguageParser.REPLACES, 0); }
		public ReplacesBodyContext replacesBody() {
			return getRuleContext(ReplacesBodyContext.class,0);
		}
		public List<AttributeQualifierContext> attributeQualifier() {
			return getRuleContexts(AttributeQualifierContext.class);
		}
		public AttributeQualifierContext attributeQualifier(int i) {
			return getRuleContext(AttributeQualifierContext.class,i);
		}
		public TerminalNode IN() { return getToken(EntityLanguageParser.IN, 0); }
		public AttributeBodyContext attributeBody() {
			return getRuleContext(AttributeBodyContext.class,0);
		}
		public DomainAttributeReplacesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainAttributeReplaces; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainAttributeReplaces(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainAttributeReplacesContext domainAttributeReplaces() throws RecognitionException {
		DomainAttributeReplacesContext _localctx = new DomainAttributeReplacesContext(_ctx, getState());
		enterRule(_localctx, 218, RULE_domainAttributeReplaces);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1406);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,130,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1403);
					attributeQualifier();
					}
					} 
				}
				setState(1408);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,130,_ctx);
			}
			setState(1409);
			type();
			setState(1410);
			id();
			setState(1413);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IN) {
				{
				setState(1411);
				match(IN);
				setState(1412);
				id();
				}
			}

			setState(1415);
			match(REPLACES);
			setState(1416);
			match(T__28);
			setState(1417);
			replacesBody();
			setState(1418);
			match(T__29);
			setState(1423);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__28) {
				{
				setState(1419);
				match(T__28);
				setState(1420);
				attributeBody();
				setState(1421);
				match(T__29);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReplacesBodyContext extends ParserRuleContext {
		public List<BitfieldContext> bitfield() {
			return getRuleContexts(BitfieldContext.class);
		}
		public BitfieldContext bitfield(int i) {
			return getRuleContext(BitfieldContext.class,i);
		}
		public ReplacesBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_replacesBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitReplacesBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReplacesBodyContext replacesBody() throws RecognitionException {
		ReplacesBodyContext _localctx = new ReplacesBodyContext(_ctx, getState());
		enterRule(_localctx, 220, RULE_replacesBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1428);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(1425);
				bitfield();
				}
				}
				setState(1430);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainIncludeEntitiesContext extends ParserRuleContext {
		public TerminalNode INCLUDE() { return getToken(EntityLanguageParser.INCLUDE, 0); }
		public TerminalNode ENTITIES() { return getToken(EntityLanguageParser.ENTITIES, 0); }
		public List<IdContext> id() {
			return getRuleContexts(IdContext.class);
		}
		public IdContext id(int i) {
			return getRuleContext(IdContext.class,i);
		}
		public DescriptionStatementContext descriptionStatement() {
			return getRuleContext(DescriptionStatementContext.class,0);
		}
		public DomainIncludeEntitiesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainIncludeEntities; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainIncludeEntities(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainIncludeEntitiesContext domainIncludeEntities() throws RecognitionException {
		DomainIncludeEntitiesContext _localctx = new DomainIncludeEntitiesContext(_ctx, getState());
		enterRule(_localctx, 222, RULE_domainIncludeEntities);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1431);
			match(INCLUDE);
			setState(1432);
			match(ENTITIES);
			setState(1433);
			match(T__3);
			setState(1434);
			id();
			setState(1439);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(1435);
				match(T__4);
				setState(1436);
				id();
				}
				}
				setState(1441);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1442);
			match(T__1);
			setState(1447);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__28) {
				{
				setState(1443);
				match(T__28);
				setState(1444);
				descriptionStatement();
				setState(1445);
				match(T__29);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainExcludeEntitiesContext extends ParserRuleContext {
		public TerminalNode EXCLUDE() { return getToken(EntityLanguageParser.EXCLUDE, 0); }
		public TerminalNode ENTITIES() { return getToken(EntityLanguageParser.ENTITIES, 0); }
		public List<IdContext> id() {
			return getRuleContexts(IdContext.class);
		}
		public IdContext id(int i) {
			return getRuleContext(IdContext.class,i);
		}
		public DescriptionStatementContext descriptionStatement() {
			return getRuleContext(DescriptionStatementContext.class,0);
		}
		public DomainExcludeEntitiesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainExcludeEntities; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainExcludeEntities(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainExcludeEntitiesContext domainExcludeEntities() throws RecognitionException {
		DomainExcludeEntitiesContext _localctx = new DomainExcludeEntitiesContext(_ctx, getState());
		enterRule(_localctx, 224, RULE_domainExcludeEntities);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1449);
			match(EXCLUDE);
			setState(1450);
			match(ENTITIES);
			setState(1451);
			match(T__3);
			setState(1452);
			id();
			setState(1457);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(1453);
				match(T__4);
				setState(1454);
				id();
				}
				}
				setState(1459);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1460);
			match(T__1);
			setState(1465);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__28) {
				{
				setState(1461);
				match(T__28);
				setState(1462);
				descriptionStatement();
				setState(1463);
				match(T__29);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainVirtualAttributeContext extends ParserRuleContext {
		public TerminalNode VIRTUAL() { return getToken(EntityLanguageParser.VIRTUAL, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public DomainAttributeBodyContext domainAttributeBody() {
			return getRuleContext(DomainAttributeBodyContext.class,0);
		}
		public TerminalNode OPTIONAL() { return getToken(EntityLanguageParser.OPTIONAL, 0); }
		public DomainVirtualAttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainVirtualAttribute; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainVirtualAttribute(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainVirtualAttributeContext domainVirtualAttribute() throws RecognitionException {
		DomainVirtualAttributeContext _localctx = new DomainVirtualAttributeContext(_ctx, getState());
		enterRule(_localctx, 226, RULE_domainVirtualAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1468);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPTIONAL) {
				{
				setState(1467);
				match(OPTIONAL);
				}
			}

			setState(1470);
			match(VIRTUAL);
			setState(1471);
			type();
			setState(1472);
			id();
			setState(1473);
			match(T__28);
			setState(1474);
			domainAttributeBody();
			setState(1475);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainAttributeContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public DomainAttributeBodyContext domainAttributeBody() {
			return getRuleContext(DomainAttributeBodyContext.class,0);
		}
		public DomainAttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainAttribute; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainAttribute(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainAttributeContext domainAttribute() throws RecognitionException {
		DomainAttributeContext _localctx = new DomainAttributeContext(_ctx, getState());
		enterRule(_localctx, 228, RULE_domainAttribute);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1477);
			id();
			setState(1478);
			match(T__28);
			setState(1479);
			domainAttributeBody();
			setState(1480);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainAttributeBodyContext extends ParserRuleContext {
		public List<DescriptionStatementContext> descriptionStatement() {
			return getRuleContexts(DescriptionStatementContext.class);
		}
		public DescriptionStatementContext descriptionStatement(int i) {
			return getRuleContext(DescriptionStatementContext.class,i);
		}
		public List<TagStatementContext> tagStatement() {
			return getRuleContexts(TagStatementContext.class);
		}
		public TagStatementContext tagStatement(int i) {
			return getRuleContext(TagStatementContext.class,i);
		}
		public DomainAttributeBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainAttributeBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainAttributeBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainAttributeBodyContext domainAttributeBody() throws RecognitionException {
		DomainAttributeBodyContext _localctx = new DomainAttributeBodyContext(_ctx, getState());
		enterRule(_localctx, 230, RULE_domainAttributeBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1486);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DESCRIPTION || _la==TAGS) {
				{
				setState(1484);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case DESCRIPTION:
					{
					setState(1482);
					descriptionStatement();
					}
					break;
				case TAGS:
					{
					setState(1483);
					tagStatement();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(1488);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainRelationshipsContext extends ParserRuleContext {
		public TerminalNode RELATIONSHIPS() { return getToken(EntityLanguageParser.RELATIONSHIPS, 0); }
		public DomainRelationshipsBodyContext domainRelationshipsBody() {
			return getRuleContext(DomainRelationshipsBodyContext.class,0);
		}
		public DomainRelationshipsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainRelationships; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainRelationships(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainRelationshipsContext domainRelationships() throws RecognitionException {
		DomainRelationshipsContext _localctx = new DomainRelationshipsContext(_ctx, getState());
		enterRule(_localctx, 232, RULE_domainRelationships);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1489);
			match(RELATIONSHIPS);
			setState(1490);
			match(T__28);
			setState(1491);
			domainRelationshipsBody();
			setState(1492);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainRelationshipsBodyContext extends ParserRuleContext {
		public List<DomainRelationshipContext> domainRelationship() {
			return getRuleContexts(DomainRelationshipContext.class);
		}
		public DomainRelationshipContext domainRelationship(int i) {
			return getRuleContext(DomainRelationshipContext.class,i);
		}
		public DomainRelationshipsBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainRelationshipsBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainRelationshipsBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainRelationshipsBodyContext domainRelationshipsBody() throws RecognitionException {
		DomainRelationshipsBodyContext _localctx = new DomainRelationshipsBodyContext(_ctx, getState());
		enterRule(_localctx, 234, RULE_domainRelationshipsBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1497);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MODULE) | (1L << ENTITY) | (1L << PRIMARYKEY) | (1L << ATTRIBUTE) | (1L << UNIQUE) | (1L << CREATION) | (1L << MODIFICATION) | (1L << PARENT) | (1L << ORDERED) | (1L << SEQUENTIAL) | (1L << EXTERN) | (1L << TRANSIENT) | (1L << PRIMARY) | (1L << SECONDARY) | (1L << FLATTEN) | (1L << RELATIONSHIPS) | (1L << RELATIONSHIP) | (1L << ENTITIES) | (1L << ENUM) | (1L << ENUMITEM) | (1L << TYPEDEF) | (1L << UNUSED) | (1L << CONFIGURATION) | (1L << FORMATTING))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (FORMAT - 64)) | (1L << (OUTPUT - 64)) | (1L << (DIRECTORY - 64)) | (1L << (TRANSFORM - 64)) | (1L << (TEMPLATE - 64)) | (1L << (PATH - 64)) | (1L << (ENDPOINT - 64)) | (1L << (INHERITED - 64)) | (1L << (REPOSITORY - 64)) | (1L << (TAG - 64)) | (1L << (VALUE - 64)) | (1L << (SPACE - 64)) | (1L << (IMPORT - 64)) | (1L << (FROM - 64)) | (1L << (ORGANIZATION - 64)) | (1L << (NAME - 64)) | (1L << (NULLABLE - 64)) | (1L << (LANGUAGE - 64)) | (1L << (VERSION - 64)) | (1L << (SELF - 64)) | (1L << (OPERATORS - 64)) | (1L << (KEYWORDS - 64)) | (1L << (SHORT - 64)) | (1L << (LONG - 64)) | (1L << (HUMAN - 64)) | (1L << (READABLE - 64)) | (1L << (IDENTIFICATION - 64)) | (1L << (DOMAIN - 64)) | (1L << (ATTRIBUTES - 64)) | (1L << (REPLACES - 64)) | (1L << (PREFIX - 64)) | (1L << (SUFFIX - 64)) | (1L << (RENAME - 64)) | (1L << (TAGGING - 64)) | (1L << (NAMESPACE - 64)) | (1L << (READ - 64)) | (1L << (WRITE - 64)) | (1L << (WHEN - 64)) | (1L << (REQUIRES - 64)) | (1L << (ROLE - 64)) | (1L << (USER - 64)) | (1L << (IF - 64)) | (1L << (APPLY - 64)))) != 0) || ((((_la - 130)) & ~0x3f) == 0 && ((1L << (_la - 130)) & ((1L << (DEFAULT - 130)) | (1L << (VIEW - 130)) | (1L << (INCLUDE - 130)) | (1L << (EXCLUDE - 130)) | (1L << (AND - 130)) | (1L << (ABSTRACT - 130)) | (1L << (EXTENDS - 130)) | (1L << (INTERFACE - 130)) | (1L << (CONFIG - 130)) | (1L << (METADATA - 130)) | (1L << (CONTEXT - 130)) | (1L << (ARGUMENT - 130)) | (1L << (OPERATION - 130)) | (1L << (QUERY - 130)) | (1L << (PARAM - 130)) | (1L << (STATUS - 130)) | (1L << (CUSTOM - 130)) | (1L << (TYPE - 130)) | (1L << (TO - 130)) | (1L << (PREPEND - 130)) | (1L << (APPEND - 130)) | (1L << (BOOLEAN_TYPE - 130)) | (1L << (INT32_TYPE - 130)) | (1L << (INT64_TYPE - 130)) | (1L << (BYTE_TYPE - 130)) | (1L << (UUID_TYPE - 130)) | (1L << (FLOAT_TYPE - 130)) | (1L << (DOUBLE_TYPE - 130)) | (1L << (STRING_TYPE - 130)) | (1L << (DATE_TYPE - 130)) | (1L << (ASSET_TYPE - 130)) | (1L << (ARRAY - 130)) | (1L << (DESCRIPTION - 130)) | (1L << (TAGS - 130)) | (1L << (MACRO_START - 130)) | (1L << (ID - 130)))) != 0)) {
				{
				{
				setState(1494);
				domainRelationship();
				}
				}
				setState(1499);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainRelationshipContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public DomainRelationshipBodyContext domainRelationshipBody() {
			return getRuleContext(DomainRelationshipBodyContext.class,0);
		}
		public TerminalNode ENTITY() { return getToken(EntityLanguageParser.ENTITY, 0); }
		public DomainRelationshipContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainRelationship; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainRelationship(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainRelationshipContext domainRelationship() throws RecognitionException {
		DomainRelationshipContext _localctx = new DomainRelationshipContext(_ctx, getState());
		enterRule(_localctx, 236, RULE_domainRelationship);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1501);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,142,_ctx) ) {
			case 1:
				{
				setState(1500);
				match(ENTITY);
				}
				break;
			}
			setState(1503);
			id();
			setState(1504);
			match(T__28);
			setState(1505);
			domainRelationshipBody();
			setState(1506);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainRelationshipBodyContext extends ParserRuleContext {
		public List<DescriptionStatementContext> descriptionStatement() {
			return getRuleContexts(DescriptionStatementContext.class);
		}
		public DescriptionStatementContext descriptionStatement(int i) {
			return getRuleContext(DescriptionStatementContext.class,i);
		}
		public List<TagStatementContext> tagStatement() {
			return getRuleContexts(TagStatementContext.class);
		}
		public TagStatementContext tagStatement(int i) {
			return getRuleContext(TagStatementContext.class,i);
		}
		public DomainRelationshipBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainRelationshipBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainRelationshipBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainRelationshipBodyContext domainRelationshipBody() throws RecognitionException {
		DomainRelationshipBodyContext _localctx = new DomainRelationshipBodyContext(_ctx, getState());
		enterRule(_localctx, 238, RULE_domainRelationshipBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1512);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DESCRIPTION || _la==TAGS) {
				{
				setState(1510);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case DESCRIPTION:
					{
					setState(1508);
					descriptionStatement();
					}
					break;
				case TAGS:
					{
					setState(1509);
					tagStatement();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(1514);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AbstractInterfaceStatementContext extends ParserRuleContext {
		public TerminalNode INTERFACE() { return getToken(EntityLanguageParser.INTERFACE, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public InterfaceBodyContext interfaceBody() {
			return getRuleContext(InterfaceBodyContext.class,0);
		}
		public AbstractInterfaceStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_abstractInterfaceStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitAbstractInterfaceStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AbstractInterfaceStatementContext abstractInterfaceStatement() throws RecognitionException {
		AbstractInterfaceStatementContext _localctx = new AbstractInterfaceStatementContext(_ctx, getState());
		enterRule(_localctx, 240, RULE_abstractInterfaceStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1515);
			match(INTERFACE);
			setState(1516);
			id();
			setState(1517);
			match(T__28);
			setState(1518);
			interfaceBody();
			setState(1519);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InterfaceBodyContext extends ParserRuleContext {
		public List<DescriptionStatementContext> descriptionStatement() {
			return getRuleContexts(DescriptionStatementContext.class);
		}
		public DescriptionStatementContext descriptionStatement(int i) {
			return getRuleContext(DescriptionStatementContext.class,i);
		}
		public List<InterfaceTypeContext> interfaceType() {
			return getRuleContexts(InterfaceTypeContext.class);
		}
		public InterfaceTypeContext interfaceType(int i) {
			return getRuleContext(InterfaceTypeContext.class,i);
		}
		public List<OperationContext> operation() {
			return getRuleContexts(OperationContext.class);
		}
		public OperationContext operation(int i) {
			return getRuleContext(OperationContext.class,i);
		}
		public InterfaceBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitInterfaceBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InterfaceBodyContext interfaceBody() throws RecognitionException {
		InterfaceBodyContext _localctx = new InterfaceBodyContext(_ctx, getState());
		enterRule(_localctx, 242, RULE_interfaceBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1526);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 139)) & ~0x3f) == 0 && ((1L << (_la - 139)) & ((1L << (ABSTRACT - 139)) | (1L << (TYPE - 139)) | (1L << (DESCRIPTION - 139)))) != 0)) {
				{
				setState(1524);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case DESCRIPTION:
					{
					setState(1521);
					descriptionStatement();
					}
					break;
				case TYPE:
					{
					setState(1522);
					interfaceType();
					}
					break;
				case ABSTRACT:
					{
					setState(1523);
					operation();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(1528);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InterfaceTypeContext extends ParserRuleContext {
		public TerminalNode TYPE() { return getToken(EntityLanguageParser.TYPE, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public InterfaceTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitInterfaceType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InterfaceTypeContext interfaceType() throws RecognitionException {
		InterfaceTypeContext _localctx = new InterfaceTypeContext(_ctx, getState());
		enterRule(_localctx, 244, RULE_interfaceType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1529);
			match(TYPE);
			setState(1530);
			id();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationContext extends ParserRuleContext {
		public TerminalNode ABSTRACT() { return getToken(EntityLanguageParser.ABSTRACT, 0); }
		public TerminalNode OPERATION() { return getToken(EntityLanguageParser.OPERATION, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public OperationBodyContext operationBody() {
			return getRuleContext(OperationBodyContext.class,0);
		}
		public OperationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operation; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitOperation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationContext operation() throws RecognitionException {
		OperationContext _localctx = new OperationContext(_ctx, getState());
		enterRule(_localctx, 246, RULE_operation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1532);
			match(ABSTRACT);
			setState(1533);
			match(OPERATION);
			setState(1534);
			id();
			setState(1535);
			match(T__28);
			setState(1536);
			operationBody();
			setState(1537);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationBodyContext extends ParserRuleContext {
		public List<DescriptionStatementContext> descriptionStatement() {
			return getRuleContexts(DescriptionStatementContext.class);
		}
		public DescriptionStatementContext descriptionStatement(int i) {
			return getRuleContext(DescriptionStatementContext.class,i);
		}
		public List<OperationRequestContext> operationRequest() {
			return getRuleContexts(OperationRequestContext.class);
		}
		public OperationRequestContext operationRequest(int i) {
			return getRuleContext(OperationRequestContext.class,i);
		}
		public List<OperationResponseContext> operationResponse() {
			return getRuleContexts(OperationResponseContext.class);
		}
		public OperationResponseContext operationResponse(int i) {
			return getRuleContext(OperationResponseContext.class,i);
		}
		public List<OperationConfigContext> operationConfig() {
			return getRuleContexts(OperationConfigContext.class);
		}
		public OperationConfigContext operationConfig(int i) {
			return getRuleContext(OperationConfigContext.class,i);
		}
		public OperationBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitOperationBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationBodyContext operationBody() throws RecognitionException {
		OperationBodyContext _localctx = new OperationBodyContext(_ctx, getState());
		enterRule(_localctx, 248, RULE_operationBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1545);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 143)) & ~0x3f) == 0 && ((1L << (_la - 143)) & ((1L << (CONFIG - 143)) | (1L << (REQUEST - 143)) | (1L << (RESPONSE - 143)) | (1L << (DESCRIPTION - 143)))) != 0)) {
				{
				setState(1543);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case DESCRIPTION:
					{
					setState(1539);
					descriptionStatement();
					}
					break;
				case REQUEST:
					{
					setState(1540);
					operationRequest();
					}
					break;
				case RESPONSE:
					{
					setState(1541);
					operationResponse();
					}
					break;
				case CONFIG:
					{
					setState(1542);
					operationConfig();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(1547);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationConfigContext extends ParserRuleContext {
		public TerminalNode CONFIG() { return getToken(EntityLanguageParser.CONFIG, 0); }
		public OperationConfigBlockContext operationConfigBlock() {
			return getRuleContext(OperationConfigBlockContext.class,0);
		}
		public OperationConfigContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationConfig; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitOperationConfig(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationConfigContext operationConfig() throws RecognitionException {
		OperationConfigContext _localctx = new OperationConfigContext(_ctx, getState());
		enterRule(_localctx, 250, RULE_operationConfig);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1548);
			match(CONFIG);
			setState(1549);
			match(T__28);
			setState(1550);
			operationConfigBlock();
			setState(1551);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationConfigBlockContext extends ParserRuleContext {
		public List<OperationConfigContextContext> operationConfigContext() {
			return getRuleContexts(OperationConfigContextContext.class);
		}
		public OperationConfigContextContext operationConfigContext(int i) {
			return getRuleContext(OperationConfigContextContext.class,i);
		}
		public List<OperationConfigArgumentContext> operationConfigArgument() {
			return getRuleContexts(OperationConfigArgumentContext.class);
		}
		public OperationConfigArgumentContext operationConfigArgument(int i) {
			return getRuleContext(OperationConfigArgumentContext.class,i);
		}
		public OperationConfigBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationConfigBlock; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitOperationConfigBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationConfigBlockContext operationConfigBlock() throws RecognitionException {
		OperationConfigBlockContext _localctx = new OperationConfigBlockContext(_ctx, getState());
		enterRule(_localctx, 252, RULE_operationConfigBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1557);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CONTEXT || _la==ARGUMENT) {
				{
				setState(1555);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case CONTEXT:
					{
					setState(1553);
					operationConfigContext();
					}
					break;
				case ARGUMENT:
					{
					setState(1554);
					operationConfigArgument();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(1559);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationConfigContextContext extends ParserRuleContext {
		public TerminalNode CONTEXT() { return getToken(EntityLanguageParser.CONTEXT, 0); }
		public OperationConfigContextTypeContext operationConfigContextType() {
			return getRuleContext(OperationConfigContextTypeContext.class,0);
		}
		public List<IdContext> id() {
			return getRuleContexts(IdContext.class);
		}
		public IdContext id(int i) {
			return getRuleContext(IdContext.class,i);
		}
		public OperationContextBlockContext operationContextBlock() {
			return getRuleContext(OperationContextBlockContext.class,0);
		}
		public OperationConfigContextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationConfigContext; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitOperationConfigContext(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationConfigContextContext operationConfigContext() throws RecognitionException {
		OperationConfigContextContext _localctx = new OperationConfigContextContext(_ctx, getState());
		enterRule(_localctx, 254, RULE_operationConfigContext);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1560);
			match(CONTEXT);
			setState(1561);
			operationConfigContextType();
			setState(1562);
			id();
			setState(1565);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__27) {
				{
				setState(1563);
				match(T__27);
				setState(1564);
				id();
				}
			}

			setState(1571);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__28) {
				{
				setState(1567);
				match(T__28);
				setState(1568);
				operationContextBlock();
				setState(1569);
				match(T__29);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationConfigContextTypeContext extends ParserRuleContext {
		public TerminalNode DOMAIN() { return getToken(EntityLanguageParser.DOMAIN, 0); }
		public TerminalNode ENTITY() { return getToken(EntityLanguageParser.ENTITY, 0); }
		public OperationConfigContextTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationConfigContextType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitOperationConfigContextType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationConfigContextTypeContext operationConfigContextType() throws RecognitionException {
		OperationConfigContextTypeContext _localctx = new OperationConfigContextTypeContext(_ctx, getState());
		enterRule(_localctx, 256, RULE_operationConfigContextType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1573);
			_la = _input.LA(1);
			if ( !(_la==ENTITY || _la==DOMAIN) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationConfigArgumentContext extends ParserRuleContext {
		public TerminalNode ARGUMENT() { return getToken(EntityLanguageParser.ARGUMENT, 0); }
		public OperationConfigArgumentTypeContext operationConfigArgumentType() {
			return getRuleContext(OperationConfigArgumentTypeContext.class,0);
		}
		public List<IdContext> id() {
			return getRuleContexts(IdContext.class);
		}
		public IdContext id(int i) {
			return getRuleContext(IdContext.class,i);
		}
		public OperationArgumentBlockContext operationArgumentBlock() {
			return getRuleContext(OperationArgumentBlockContext.class,0);
		}
		public OperationConfigArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationConfigArgument; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitOperationConfigArgument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationConfigArgumentContext operationConfigArgument() throws RecognitionException {
		OperationConfigArgumentContext _localctx = new OperationConfigArgumentContext(_ctx, getState());
		enterRule(_localctx, 258, RULE_operationConfigArgument);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1575);
			match(ARGUMENT);
			setState(1576);
			operationConfigArgumentType();
			setState(1577);
			id();
			setState(1580);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__27) {
				{
				setState(1578);
				match(T__27);
				setState(1579);
				id();
				}
			}

			setState(1586);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__28) {
				{
				setState(1582);
				match(T__28);
				setState(1583);
				operationArgumentBlock();
				setState(1584);
				match(T__29);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationConfigArgumentTypeContext extends ParserRuleContext {
		public TerminalNode DOMAIN() { return getToken(EntityLanguageParser.DOMAIN, 0); }
		public TerminalNode ENTITY() { return getToken(EntityLanguageParser.ENTITY, 0); }
		public TerminalNode ATTRIBUTE() { return getToken(EntityLanguageParser.ATTRIBUTE, 0); }
		public TerminalNode VIEW() { return getToken(EntityLanguageParser.VIEW, 0); }
		public OperationConfigArgumentTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationConfigArgumentType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitOperationConfigArgumentType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationConfigArgumentTypeContext operationConfigArgumentType() throws RecognitionException {
		OperationConfigArgumentTypeContext _localctx = new OperationConfigArgumentTypeContext(_ctx, getState());
		enterRule(_localctx, 260, RULE_operationConfigArgumentType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1588);
			_la = _input.LA(1);
			if ( !(_la==ENTITY || _la==ATTRIBUTE || _la==DOMAIN || _la==VIEW) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationContextBlockContext extends ParserRuleContext {
		public List<DescriptionStatementContext> descriptionStatement() {
			return getRuleContexts(DescriptionStatementContext.class);
		}
		public DescriptionStatementContext descriptionStatement(int i) {
			return getRuleContext(DescriptionStatementContext.class,i);
		}
		public OperationContextBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationContextBlock; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitOperationContextBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationContextBlockContext operationContextBlock() throws RecognitionException {
		OperationContextBlockContext _localctx = new OperationContextBlockContext(_ctx, getState());
		enterRule(_localctx, 262, RULE_operationContextBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1593);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DESCRIPTION) {
				{
				{
				setState(1590);
				descriptionStatement();
				}
				}
				setState(1595);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationArgumentBlockContext extends ParserRuleContext {
		public List<DescriptionStatementContext> descriptionStatement() {
			return getRuleContexts(DescriptionStatementContext.class);
		}
		public DescriptionStatementContext descriptionStatement(int i) {
			return getRuleContext(DescriptionStatementContext.class,i);
		}
		public OperationArgumentBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationArgumentBlock; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitOperationArgumentBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationArgumentBlockContext operationArgumentBlock() throws RecognitionException {
		OperationArgumentBlockContext _localctx = new OperationArgumentBlockContext(_ctx, getState());
		enterRule(_localctx, 264, RULE_operationArgumentBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1599);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DESCRIPTION) {
				{
				{
				setState(1596);
				descriptionStatement();
				}
				}
				setState(1601);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationRequestMethodContext extends ParserRuleContext {
		public TerminalNode METHOD() { return getToken(EntityLanguageParser.METHOD, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public OperationRequestMethodBlockContext operationRequestMethodBlock() {
			return getRuleContext(OperationRequestMethodBlockContext.class,0);
		}
		public OperationRequestMethodContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationRequestMethod; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitOperationRequestMethod(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationRequestMethodContext operationRequestMethod() throws RecognitionException {
		OperationRequestMethodContext _localctx = new OperationRequestMethodContext(_ctx, getState());
		enterRule(_localctx, 266, RULE_operationRequestMethod);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1602);
			match(METHOD);
			setState(1603);
			id();
			setState(1608);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__28) {
				{
				setState(1604);
				match(T__28);
				setState(1605);
				operationRequestMethodBlock();
				setState(1606);
				match(T__29);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationRequestMethodBlockContext extends ParserRuleContext {
		public List<DescriptionStatementContext> descriptionStatement() {
			return getRuleContexts(DescriptionStatementContext.class);
		}
		public DescriptionStatementContext descriptionStatement(int i) {
			return getRuleContext(DescriptionStatementContext.class,i);
		}
		public OperationRequestMethodBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationRequestMethodBlock; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitOperationRequestMethodBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationRequestMethodBlockContext operationRequestMethodBlock() throws RecognitionException {
		OperationRequestMethodBlockContext _localctx = new OperationRequestMethodBlockContext(_ctx, getState());
		enterRule(_localctx, 268, RULE_operationRequestMethodBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1613);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DESCRIPTION) {
				{
				{
				setState(1610);
				descriptionStatement();
				}
				}
				setState(1615);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationRequestContext extends ParserRuleContext {
		public TerminalNode REQUEST() { return getToken(EntityLanguageParser.REQUEST, 0); }
		public OperationRequestBlockContext operationRequestBlock() {
			return getRuleContext(OperationRequestBlockContext.class,0);
		}
		public OperationRequestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationRequest; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitOperationRequest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationRequestContext operationRequest() throws RecognitionException {
		OperationRequestContext _localctx = new OperationRequestContext(_ctx, getState());
		enterRule(_localctx, 270, RULE_operationRequest);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1616);
			match(REQUEST);
			setState(1617);
			match(T__28);
			setState(1618);
			operationRequestBlock();
			setState(1619);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationRequestBlockContext extends ParserRuleContext {
		public List<DescriptionStatementContext> descriptionStatement() {
			return getRuleContexts(DescriptionStatementContext.class);
		}
		public DescriptionStatementContext descriptionStatement(int i) {
			return getRuleContext(DescriptionStatementContext.class,i);
		}
		public List<OperationRequestMethodContext> operationRequestMethod() {
			return getRuleContexts(OperationRequestMethodContext.class);
		}
		public OperationRequestMethodContext operationRequestMethod(int i) {
			return getRuleContext(OperationRequestMethodContext.class,i);
		}
		public List<OperationRequestEndpointContext> operationRequestEndpoint() {
			return getRuleContexts(OperationRequestEndpointContext.class);
		}
		public OperationRequestEndpointContext operationRequestEndpoint(int i) {
			return getRuleContext(OperationRequestEndpointContext.class,i);
		}
		public List<OperationRequestBodyContext> operationRequestBody() {
			return getRuleContexts(OperationRequestBodyContext.class);
		}
		public OperationRequestBodyContext operationRequestBody(int i) {
			return getRuleContext(OperationRequestBodyContext.class,i);
		}
		public OperationRequestBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationRequestBlock; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitOperationRequestBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationRequestBlockContext operationRequestBlock() throws RecognitionException {
		OperationRequestBlockContext _localctx = new OperationRequestBlockContext(_ctx, getState());
		enterRule(_localctx, 272, RULE_operationRequestBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1627);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ENDPOINT || _la==METHOD || _la==BODY || _la==DESCRIPTION) {
				{
				setState(1625);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case DESCRIPTION:
					{
					setState(1621);
					descriptionStatement();
					}
					break;
				case METHOD:
					{
					setState(1622);
					operationRequestMethod();
					}
					break;
				case ENDPOINT:
					{
					setState(1623);
					operationRequestEndpoint();
					}
					break;
				case BODY:
					{
					setState(1624);
					operationRequestBody();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(1629);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationRequestEndpointContext extends ParserRuleContext {
		public TerminalNode ENDPOINT() { return getToken(EntityLanguageParser.ENDPOINT, 0); }
		public TerminalNode STRING() { return getToken(EntityLanguageParser.STRING, 0); }
		public TerminalNode INHERITED() { return getToken(EntityLanguageParser.INHERITED, 0); }
		public OperationRequestEndpointBlockContext operationRequestEndpointBlock() {
			return getRuleContext(OperationRequestEndpointBlockContext.class,0);
		}
		public OperationRequestEndpointContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationRequestEndpoint; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitOperationRequestEndpoint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationRequestEndpointContext operationRequestEndpoint() throws RecognitionException {
		OperationRequestEndpointContext _localctx = new OperationRequestEndpointContext(_ctx, getState());
		enterRule(_localctx, 274, RULE_operationRequestEndpoint);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1630);
			match(ENDPOINT);
			setState(1631);
			_la = _input.LA(1);
			if ( !(_la==INHERITED || _la==STRING) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(1636);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__28) {
				{
				setState(1632);
				match(T__28);
				setState(1633);
				operationRequestEndpointBlock();
				setState(1634);
				match(T__29);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationRequestEndpointBlockContext extends ParserRuleContext {
		public List<DescriptionStatementContext> descriptionStatement() {
			return getRuleContexts(DescriptionStatementContext.class);
		}
		public DescriptionStatementContext descriptionStatement(int i) {
			return getRuleContext(DescriptionStatementContext.class,i);
		}
		public List<OperationRequestEndpointParamContext> operationRequestEndpointParam() {
			return getRuleContexts(OperationRequestEndpointParamContext.class);
		}
		public OperationRequestEndpointParamContext operationRequestEndpointParam(int i) {
			return getRuleContext(OperationRequestEndpointParamContext.class,i);
		}
		public OperationRequestEndpointBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationRequestEndpointBlock; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitOperationRequestEndpointBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationRequestEndpointBlockContext operationRequestEndpointBlock() throws RecognitionException {
		OperationRequestEndpointBlockContext _localctx = new OperationRequestEndpointBlockContext(_ctx, getState());
		enterRule(_localctx, 276, RULE_operationRequestEndpointBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1642);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 151)) & ~0x3f) == 0 && ((1L << (_la - 151)) & ((1L << (QUERY - 151)) | (1L << (PARAM - 151)) | (1L << (DESCRIPTION - 151)))) != 0)) {
				{
				setState(1640);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case DESCRIPTION:
					{
					setState(1638);
					descriptionStatement();
					}
					break;
				case QUERY:
				case PARAM:
					{
					setState(1639);
					operationRequestEndpointParam();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(1644);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationRequestEndpointParamContext extends ParserRuleContext {
		public TerminalNode PARAM() { return getToken(EntityLanguageParser.PARAM, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TerminalNode QUERY() { return getToken(EntityLanguageParser.QUERY, 0); }
		public OperationRequestEndpointParamBlockContext operationRequestEndpointParamBlock() {
			return getRuleContext(OperationRequestEndpointParamBlockContext.class,0);
		}
		public OperationRequestEndpointParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationRequestEndpointParam; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitOperationRequestEndpointParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationRequestEndpointParamContext operationRequestEndpointParam() throws RecognitionException {
		OperationRequestEndpointParamContext _localctx = new OperationRequestEndpointParamContext(_ctx, getState());
		enterRule(_localctx, 278, RULE_operationRequestEndpointParam);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1646);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==QUERY) {
				{
				setState(1645);
				match(QUERY);
				}
			}

			setState(1648);
			match(PARAM);
			setState(1649);
			type();
			setState(1650);
			id();
			setState(1655);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__28) {
				{
				setState(1651);
				match(T__28);
				setState(1652);
				operationRequestEndpointParamBlock();
				setState(1653);
				match(T__29);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationRequestEndpointParamBlockContext extends ParserRuleContext {
		public List<DescriptionStatementContext> descriptionStatement() {
			return getRuleContexts(DescriptionStatementContext.class);
		}
		public DescriptionStatementContext descriptionStatement(int i) {
			return getRuleContext(DescriptionStatementContext.class,i);
		}
		public OperationRequestEndpointParamBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationRequestEndpointParamBlock; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitOperationRequestEndpointParamBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationRequestEndpointParamBlockContext operationRequestEndpointParamBlock() throws RecognitionException {
		OperationRequestEndpointParamBlockContext _localctx = new OperationRequestEndpointParamBlockContext(_ctx, getState());
		enterRule(_localctx, 280, RULE_operationRequestEndpointParamBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1660);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DESCRIPTION) {
				{
				{
				setState(1657);
				descriptionStatement();
				}
				}
				setState(1662);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationRequestBodyContext extends ParserRuleContext {
		public TerminalNode BODY() { return getToken(EntityLanguageParser.BODY, 0); }
		public OperationRequestBodyBlockContext operationRequestBodyBlock() {
			return getRuleContext(OperationRequestBodyBlockContext.class,0);
		}
		public OperationRequestBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationRequestBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitOperationRequestBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationRequestBodyContext operationRequestBody() throws RecognitionException {
		OperationRequestBodyContext _localctx = new OperationRequestBodyContext(_ctx, getState());
		enterRule(_localctx, 282, RULE_operationRequestBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1663);
			match(BODY);
			setState(1668);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__28) {
				{
				setState(1664);
				match(T__28);
				setState(1665);
				operationRequestBodyBlock();
				setState(1666);
				match(T__29);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationRequestBodyBlockContext extends ParserRuleContext {
		public List<DescriptionStatementContext> descriptionStatement() {
			return getRuleContexts(DescriptionStatementContext.class);
		}
		public DescriptionStatementContext descriptionStatement(int i) {
			return getRuleContext(DescriptionStatementContext.class,i);
		}
		public List<OperationBodyContentTypeContext> operationBodyContentType() {
			return getRuleContexts(OperationBodyContentTypeContext.class);
		}
		public OperationBodyContentTypeContext operationBodyContentType(int i) {
			return getRuleContext(OperationBodyContentTypeContext.class,i);
		}
		public List<OperationBodyViewContext> operationBodyView() {
			return getRuleContexts(OperationBodyViewContext.class);
		}
		public OperationBodyViewContext operationBodyView(int i) {
			return getRuleContext(OperationBodyViewContext.class,i);
		}
		public List<OperationBodyDomainContext> operationBodyDomain() {
			return getRuleContexts(OperationBodyDomainContext.class);
		}
		public OperationBodyDomainContext operationBodyDomain(int i) {
			return getRuleContext(OperationBodyDomainContext.class,i);
		}
		public OperationRequestBodyBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationRequestBodyBlock; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitOperationRequestBodyBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationRequestBodyBlockContext operationRequestBodyBlock() throws RecognitionException {
		OperationRequestBodyBlockContext _localctx = new OperationRequestBodyBlockContext(_ctx, getState());
		enterRule(_localctx, 284, RULE_operationRequestBodyBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1676);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 96)) & ~0x3f) == 0 && ((1L << (_la - 96)) & ((1L << (CONTENT_TYPE - 96)) | (1L << (DOMAIN - 96)) | (1L << (VIEW - 96)))) != 0) || _la==DESCRIPTION) {
				{
				setState(1674);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case DESCRIPTION:
					{
					setState(1670);
					descriptionStatement();
					}
					break;
				case CONTENT_TYPE:
					{
					setState(1671);
					operationBodyContentType();
					}
					break;
				case VIEW:
					{
					setState(1672);
					operationBodyView();
					}
					break;
				case DOMAIN:
					{
					setState(1673);
					operationBodyDomain();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(1678);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationBodyContentTypeContext extends ParserRuleContext {
		public TerminalNode CONTENT_TYPE() { return getToken(EntityLanguageParser.CONTENT_TYPE, 0); }
		public TerminalNode STRING() { return getToken(EntityLanguageParser.STRING, 0); }
		public OperationBodyContentTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationBodyContentType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitOperationBodyContentType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationBodyContentTypeContext operationBodyContentType() throws RecognitionException {
		OperationBodyContentTypeContext _localctx = new OperationBodyContentTypeContext(_ctx, getState());
		enterRule(_localctx, 286, RULE_operationBodyContentType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1679);
			match(CONTENT_TYPE);
			setState(1680);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationBodyViewContext extends ParserRuleContext {
		public TerminalNode VIEW() { return getToken(EntityLanguageParser.VIEW, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public OperationBodyViewContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationBodyView; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitOperationBodyView(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationBodyViewContext operationBodyView() throws RecognitionException {
		OperationBodyViewContext _localctx = new OperationBodyViewContext(_ctx, getState());
		enterRule(_localctx, 288, RULE_operationBodyView);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1682);
			match(VIEW);
			setState(1683);
			id();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationBodyDomainContext extends ParserRuleContext {
		public TerminalNode DOMAIN() { return getToken(EntityLanguageParser.DOMAIN, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public OperationBodyDomainContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationBodyDomain; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitOperationBodyDomain(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationBodyDomainContext operationBodyDomain() throws RecognitionException {
		OperationBodyDomainContext _localctx = new OperationBodyDomainContext(_ctx, getState());
		enterRule(_localctx, 290, RULE_operationBodyDomain);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1685);
			match(DOMAIN);
			setState(1686);
			id();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationResponseContext extends ParserRuleContext {
		public TerminalNode RESPONSE() { return getToken(EntityLanguageParser.RESPONSE, 0); }
		public OperationResponseBlockContext operationResponseBlock() {
			return getRuleContext(OperationResponseBlockContext.class,0);
		}
		public OperationResponseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationResponse; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitOperationResponse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationResponseContext operationResponse() throws RecognitionException {
		OperationResponseContext _localctx = new OperationResponseContext(_ctx, getState());
		enterRule(_localctx, 292, RULE_operationResponse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1688);
			match(RESPONSE);
			setState(1689);
			match(T__28);
			setState(1690);
			operationResponseBlock();
			setState(1691);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationResponseBlockContext extends ParserRuleContext {
		public List<DescriptionStatementContext> descriptionStatement() {
			return getRuleContexts(DescriptionStatementContext.class);
		}
		public DescriptionStatementContext descriptionStatement(int i) {
			return getRuleContext(DescriptionStatementContext.class,i);
		}
		public List<OperationResponseBodyContext> operationResponseBody() {
			return getRuleContexts(OperationResponseBodyContext.class);
		}
		public OperationResponseBodyContext operationResponseBody(int i) {
			return getRuleContext(OperationResponseBodyContext.class,i);
		}
		public List<OperationResponseStatusContext> operationResponseStatus() {
			return getRuleContexts(OperationResponseStatusContext.class);
		}
		public OperationResponseStatusContext operationResponseStatus(int i) {
			return getRuleContext(OperationResponseStatusContext.class,i);
		}
		public OperationResponseBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationResponseBlock; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitOperationResponseBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationResponseBlockContext operationResponseBlock() throws RecognitionException {
		OperationResponseBlockContext _localctx = new OperationResponseBlockContext(_ctx, getState());
		enterRule(_localctx, 294, RULE_operationResponseBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1698);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 150)) & ~0x3f) == 0 && ((1L << (_la - 150)) & ((1L << (BODY - 150)) | (1L << (STATUS - 150)) | (1L << (DESCRIPTION - 150)))) != 0)) {
				{
				setState(1696);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case DESCRIPTION:
					{
					setState(1693);
					descriptionStatement();
					}
					break;
				case BODY:
					{
					setState(1694);
					operationResponseBody();
					}
					break;
				case STATUS:
					{
					setState(1695);
					operationResponseStatus();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(1700);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationRequestStatusExpressionContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(EntityLanguageParser.IF, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public OperationRequestStatusExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationRequestStatusExpression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitOperationRequestStatusExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationRequestStatusExpressionContext operationRequestStatusExpression() throws RecognitionException {
		OperationRequestStatusExpressionContext _localctx = new OperationRequestStatusExpressionContext(_ctx, getState());
		enterRule(_localctx, 296, RULE_operationRequestStatusExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1701);
			match(IF);
			setState(1702);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationResponseStatusContext extends ParserRuleContext {
		public TerminalNode STATUS() { return getToken(EntityLanguageParser.STATUS, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TerminalNode INTEGER() { return getToken(EntityLanguageParser.INTEGER, 0); }
		public OperationRequestStatusExpressionContext operationRequestStatusExpression() {
			return getRuleContext(OperationRequestStatusExpressionContext.class,0);
		}
		public OperationResponseStatusBlockContext operationResponseStatusBlock() {
			return getRuleContext(OperationResponseStatusBlockContext.class,0);
		}
		public OperationResponseStatusContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationResponseStatus; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitOperationResponseStatus(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationResponseStatusContext operationResponseStatus() throws RecognitionException {
		OperationResponseStatusContext _localctx = new OperationResponseStatusContext(_ctx, getState());
		enterRule(_localctx, 298, RULE_operationResponseStatus);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1704);
			match(STATUS);
			setState(1707);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MODULE:
			case ENTITY:
			case PRIMARYKEY:
			case ATTRIBUTE:
			case UNIQUE:
			case CREATION:
			case MODIFICATION:
			case PARENT:
			case ORDERED:
			case SEQUENTIAL:
			case EXTERN:
			case TRANSIENT:
			case PRIMARY:
			case SECONDARY:
			case FLATTEN:
			case RELATIONSHIPS:
			case RELATIONSHIP:
			case ENTITIES:
			case ENUM:
			case ENUMITEM:
			case TYPEDEF:
			case UNUSED:
			case CONFIGURATION:
			case FORMATTING:
			case FORMAT:
			case OUTPUT:
			case DIRECTORY:
			case TRANSFORM:
			case TEMPLATE:
			case PATH:
			case ENDPOINT:
			case INHERITED:
			case REPOSITORY:
			case TAG:
			case VALUE:
			case SPACE:
			case IMPORT:
			case FROM:
			case ORGANIZATION:
			case NAME:
			case NULLABLE:
			case LANGUAGE:
			case VERSION:
			case SELF:
			case OPERATORS:
			case KEYWORDS:
			case SHORT:
			case LONG:
			case HUMAN:
			case READABLE:
			case IDENTIFICATION:
			case DOMAIN:
			case ATTRIBUTES:
			case REPLACES:
			case PREFIX:
			case SUFFIX:
			case RENAME:
			case TAGGING:
			case NAMESPACE:
			case READ:
			case WRITE:
			case WHEN:
			case REQUIRES:
			case ROLE:
			case USER:
			case IF:
			case APPLY:
			case DEFAULT:
			case VIEW:
			case INCLUDE:
			case EXCLUDE:
			case AND:
			case ABSTRACT:
			case EXTENDS:
			case INTERFACE:
			case CONFIG:
			case METADATA:
			case CONTEXT:
			case ARGUMENT:
			case OPERATION:
			case QUERY:
			case PARAM:
			case STATUS:
			case CUSTOM:
			case TYPE:
			case TO:
			case PREPEND:
			case APPEND:
			case BOOLEAN_TYPE:
			case INT32_TYPE:
			case INT64_TYPE:
			case BYTE_TYPE:
			case UUID_TYPE:
			case FLOAT_TYPE:
			case DOUBLE_TYPE:
			case STRING_TYPE:
			case DATE_TYPE:
			case ASSET_TYPE:
			case ARRAY:
			case DESCRIPTION:
			case TAGS:
			case MACRO_START:
			case ID:
				{
				setState(1705);
				id();
				}
				break;
			case INTEGER:
				{
				setState(1706);
				match(INTEGER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1710);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IF) {
				{
				setState(1709);
				operationRequestStatusExpression();
				}
			}

			setState(1716);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__28) {
				{
				setState(1712);
				match(T__28);
				setState(1713);
				operationResponseStatusBlock();
				setState(1714);
				match(T__29);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationResponseStatusBlockContext extends ParserRuleContext {
		public List<DescriptionStatementContext> descriptionStatement() {
			return getRuleContexts(DescriptionStatementContext.class);
		}
		public DescriptionStatementContext descriptionStatement(int i) {
			return getRuleContext(DescriptionStatementContext.class,i);
		}
		public OperationResponseStatusBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationResponseStatusBlock; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitOperationResponseStatusBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationResponseStatusBlockContext operationResponseStatusBlock() throws RecognitionException {
		OperationResponseStatusBlockContext _localctx = new OperationResponseStatusBlockContext(_ctx, getState());
		enterRule(_localctx, 300, RULE_operationResponseStatusBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1721);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DESCRIPTION) {
				{
				{
				setState(1718);
				descriptionStatement();
				}
				}
				setState(1723);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationResponseBodyContext extends ParserRuleContext {
		public TerminalNode BODY() { return getToken(EntityLanguageParser.BODY, 0); }
		public OperationResponseBodyBlockContext operationResponseBodyBlock() {
			return getRuleContext(OperationResponseBodyBlockContext.class,0);
		}
		public OperationResponseBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationResponseBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitOperationResponseBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationResponseBodyContext operationResponseBody() throws RecognitionException {
		OperationResponseBodyContext _localctx = new OperationResponseBodyContext(_ctx, getState());
		enterRule(_localctx, 302, RULE_operationResponseBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1724);
			match(BODY);
			setState(1729);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__28) {
				{
				setState(1725);
				match(T__28);
				setState(1726);
				operationResponseBodyBlock();
				setState(1727);
				match(T__29);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationResponseBodyBlockContext extends ParserRuleContext {
		public List<DescriptionStatementContext> descriptionStatement() {
			return getRuleContexts(DescriptionStatementContext.class);
		}
		public DescriptionStatementContext descriptionStatement(int i) {
			return getRuleContext(DescriptionStatementContext.class,i);
		}
		public List<OperationBodyContentTypeContext> operationBodyContentType() {
			return getRuleContexts(OperationBodyContentTypeContext.class);
		}
		public OperationBodyContentTypeContext operationBodyContentType(int i) {
			return getRuleContext(OperationBodyContentTypeContext.class,i);
		}
		public List<OperationBodyViewContext> operationBodyView() {
			return getRuleContexts(OperationBodyViewContext.class);
		}
		public OperationBodyViewContext operationBodyView(int i) {
			return getRuleContext(OperationBodyViewContext.class,i);
		}
		public List<OperationBodyDomainContext> operationBodyDomain() {
			return getRuleContexts(OperationBodyDomainContext.class);
		}
		public OperationBodyDomainContext operationBodyDomain(int i) {
			return getRuleContext(OperationBodyDomainContext.class,i);
		}
		public OperationResponseBodyBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationResponseBodyBlock; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitOperationResponseBodyBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationResponseBodyBlockContext operationResponseBodyBlock() throws RecognitionException {
		OperationResponseBodyBlockContext _localctx = new OperationResponseBodyBlockContext(_ctx, getState());
		enterRule(_localctx, 304, RULE_operationResponseBodyBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1737);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 96)) & ~0x3f) == 0 && ((1L << (_la - 96)) & ((1L << (CONTENT_TYPE - 96)) | (1L << (DOMAIN - 96)) | (1L << (VIEW - 96)))) != 0) || _la==DESCRIPTION) {
				{
				setState(1735);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case DESCRIPTION:
					{
					setState(1731);
					descriptionStatement();
					}
					break;
				case CONTENT_TYPE:
					{
					setState(1732);
					operationBodyContentType();
					}
					break;
				case VIEW:
					{
					setState(1733);
					operationBodyView();
					}
					break;
				case DOMAIN:
					{
					setState(1734);
					operationBodyDomain();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(1739);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainInterfaceStatementContext extends ParserRuleContext {
		public TerminalNode INTERFACE() { return getToken(EntityLanguageParser.INTERFACE, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public DomainInterfaceBodyContext domainInterfaceBody() {
			return getRuleContext(DomainInterfaceBodyContext.class,0);
		}
		public DomainInterfaceStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainInterfaceStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainInterfaceStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainInterfaceStatementContext domainInterfaceStatement() throws RecognitionException {
		DomainInterfaceStatementContext _localctx = new DomainInterfaceStatementContext(_ctx, getState());
		enterRule(_localctx, 306, RULE_domainInterfaceStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1740);
			match(INTERFACE);
			setState(1741);
			id();
			setState(1746);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__28) {
				{
				setState(1742);
				match(T__28);
				setState(1743);
				domainInterfaceBody();
				setState(1744);
				match(T__29);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainInterfaceBodyContext extends ParserRuleContext {
		public List<DescriptionStatementContext> descriptionStatement() {
			return getRuleContexts(DescriptionStatementContext.class);
		}
		public DescriptionStatementContext descriptionStatement(int i) {
			return getRuleContext(DescriptionStatementContext.class,i);
		}
		public List<DomainOperationContext> domainOperation() {
			return getRuleContexts(DomainOperationContext.class);
		}
		public DomainOperationContext domainOperation(int i) {
			return getRuleContext(DomainOperationContext.class,i);
		}
		public DomainInterfaceBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainInterfaceBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainInterfaceBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainInterfaceBodyContext domainInterfaceBody() throws RecognitionException {
		DomainInterfaceBodyContext _localctx = new DomainInterfaceBodyContext(_ctx, getState());
		enterRule(_localctx, 308, RULE_domainInterfaceBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1752);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OPERATION || _la==DESCRIPTION) {
				{
				setState(1750);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case DESCRIPTION:
					{
					setState(1748);
					descriptionStatement();
					}
					break;
				case OPERATION:
					{
					setState(1749);
					domainOperation();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(1754);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainOperationContext extends ParserRuleContext {
		public TerminalNode OPERATION() { return getToken(EntityLanguageParser.OPERATION, 0); }
		public List<IdContext> id() {
			return getRuleContexts(IdContext.class);
		}
		public IdContext id(int i) {
			return getRuleContext(IdContext.class,i);
		}
		public DomainOperationBodyContext domainOperationBody() {
			return getRuleContext(DomainOperationBodyContext.class,0);
		}
		public TerminalNode EXTENDS() { return getToken(EntityLanguageParser.EXTENDS, 0); }
		public ExtendingOperationBodyContext extendingOperationBody() {
			return getRuleContext(ExtendingOperationBodyContext.class,0);
		}
		public DomainOperationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainOperation; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainOperation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainOperationContext domainOperation() throws RecognitionException {
		DomainOperationContext _localctx = new DomainOperationContext(_ctx, getState());
		enterRule(_localctx, 310, RULE_domainOperation);
		try {
			setState(1769);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,182,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1755);
				match(OPERATION);
				setState(1756);
				id();
				setState(1757);
				match(T__28);
				setState(1758);
				domainOperationBody();
				setState(1759);
				match(T__29);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1761);
				match(OPERATION);
				setState(1762);
				id();
				setState(1763);
				match(EXTENDS);
				setState(1764);
				id();
				setState(1765);
				match(T__28);
				setState(1766);
				extendingOperationBody();
				setState(1767);
				match(T__29);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExtendingOperationBodyContext extends ParserRuleContext {
		public List<DescriptionStatementContext> descriptionStatement() {
			return getRuleContexts(DescriptionStatementContext.class);
		}
		public DescriptionStatementContext descriptionStatement(int i) {
			return getRuleContext(DescriptionStatementContext.class,i);
		}
		public List<ExtendingOperationConfigContext> extendingOperationConfig() {
			return getRuleContexts(ExtendingOperationConfigContext.class);
		}
		public ExtendingOperationConfigContext extendingOperationConfig(int i) {
			return getRuleContext(ExtendingOperationConfigContext.class,i);
		}
		public List<OperationRequestContext> operationRequest() {
			return getRuleContexts(OperationRequestContext.class);
		}
		public OperationRequestContext operationRequest(int i) {
			return getRuleContext(OperationRequestContext.class,i);
		}
		public List<OperationResponseContext> operationResponse() {
			return getRuleContexts(OperationResponseContext.class);
		}
		public OperationResponseContext operationResponse(int i) {
			return getRuleContext(OperationResponseContext.class,i);
		}
		public ExtendingOperationBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_extendingOperationBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitExtendingOperationBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExtendingOperationBodyContext extendingOperationBody() throws RecognitionException {
		ExtendingOperationBodyContext _localctx = new ExtendingOperationBodyContext(_ctx, getState());
		enterRule(_localctx, 312, RULE_extendingOperationBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1777);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 143)) & ~0x3f) == 0 && ((1L << (_la - 143)) & ((1L << (CONFIG - 143)) | (1L << (REQUEST - 143)) | (1L << (RESPONSE - 143)) | (1L << (DESCRIPTION - 143)))) != 0)) {
				{
				setState(1775);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case DESCRIPTION:
					{
					setState(1771);
					descriptionStatement();
					}
					break;
				case CONFIG:
					{
					setState(1772);
					extendingOperationConfig();
					}
					break;
				case REQUEST:
					{
					setState(1773);
					operationRequest();
					}
					break;
				case RESPONSE:
					{
					setState(1774);
					operationResponse();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(1779);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExtendingOperationConfigContext extends ParserRuleContext {
		public TerminalNode CONFIG() { return getToken(EntityLanguageParser.CONFIG, 0); }
		public ExtendingOperationConfigBlockContext extendingOperationConfigBlock() {
			return getRuleContext(ExtendingOperationConfigBlockContext.class,0);
		}
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public ExtendingOperationConfigContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_extendingOperationConfig; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitExtendingOperationConfig(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExtendingOperationConfigContext extendingOperationConfig() throws RecognitionException {
		ExtendingOperationConfigContext _localctx = new ExtendingOperationConfigContext(_ctx, getState());
		enterRule(_localctx, 314, RULE_extendingOperationConfig);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1780);
			match(CONFIG);
			setState(1782);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MODULE) | (1L << ENTITY) | (1L << PRIMARYKEY) | (1L << ATTRIBUTE) | (1L << UNIQUE) | (1L << CREATION) | (1L << MODIFICATION) | (1L << PARENT) | (1L << ORDERED) | (1L << SEQUENTIAL) | (1L << EXTERN) | (1L << TRANSIENT) | (1L << PRIMARY) | (1L << SECONDARY) | (1L << FLATTEN) | (1L << RELATIONSHIPS) | (1L << RELATIONSHIP) | (1L << ENTITIES) | (1L << ENUM) | (1L << ENUMITEM) | (1L << TYPEDEF) | (1L << UNUSED) | (1L << CONFIGURATION) | (1L << FORMATTING))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (FORMAT - 64)) | (1L << (OUTPUT - 64)) | (1L << (DIRECTORY - 64)) | (1L << (TRANSFORM - 64)) | (1L << (TEMPLATE - 64)) | (1L << (PATH - 64)) | (1L << (ENDPOINT - 64)) | (1L << (INHERITED - 64)) | (1L << (REPOSITORY - 64)) | (1L << (TAG - 64)) | (1L << (VALUE - 64)) | (1L << (SPACE - 64)) | (1L << (IMPORT - 64)) | (1L << (FROM - 64)) | (1L << (ORGANIZATION - 64)) | (1L << (NAME - 64)) | (1L << (NULLABLE - 64)) | (1L << (LANGUAGE - 64)) | (1L << (VERSION - 64)) | (1L << (SELF - 64)) | (1L << (OPERATORS - 64)) | (1L << (KEYWORDS - 64)) | (1L << (SHORT - 64)) | (1L << (LONG - 64)) | (1L << (HUMAN - 64)) | (1L << (READABLE - 64)) | (1L << (IDENTIFICATION - 64)) | (1L << (DOMAIN - 64)) | (1L << (ATTRIBUTES - 64)) | (1L << (REPLACES - 64)) | (1L << (PREFIX - 64)) | (1L << (SUFFIX - 64)) | (1L << (RENAME - 64)) | (1L << (TAGGING - 64)) | (1L << (NAMESPACE - 64)) | (1L << (READ - 64)) | (1L << (WRITE - 64)) | (1L << (WHEN - 64)) | (1L << (REQUIRES - 64)) | (1L << (ROLE - 64)) | (1L << (USER - 64)) | (1L << (IF - 64)) | (1L << (APPLY - 64)))) != 0) || ((((_la - 130)) & ~0x3f) == 0 && ((1L << (_la - 130)) & ((1L << (DEFAULT - 130)) | (1L << (VIEW - 130)) | (1L << (INCLUDE - 130)) | (1L << (EXCLUDE - 130)) | (1L << (AND - 130)) | (1L << (ABSTRACT - 130)) | (1L << (EXTENDS - 130)) | (1L << (INTERFACE - 130)) | (1L << (CONFIG - 130)) | (1L << (METADATA - 130)) | (1L << (CONTEXT - 130)) | (1L << (ARGUMENT - 130)) | (1L << (OPERATION - 130)) | (1L << (QUERY - 130)) | (1L << (PARAM - 130)) | (1L << (STATUS - 130)) | (1L << (CUSTOM - 130)) | (1L << (TYPE - 130)) | (1L << (TO - 130)) | (1L << (PREPEND - 130)) | (1L << (APPEND - 130)) | (1L << (BOOLEAN_TYPE - 130)) | (1L << (INT32_TYPE - 130)) | (1L << (INT64_TYPE - 130)) | (1L << (BYTE_TYPE - 130)) | (1L << (UUID_TYPE - 130)) | (1L << (FLOAT_TYPE - 130)) | (1L << (DOUBLE_TYPE - 130)) | (1L << (STRING_TYPE - 130)) | (1L << (DATE_TYPE - 130)) | (1L << (ASSET_TYPE - 130)) | (1L << (ARRAY - 130)) | (1L << (DESCRIPTION - 130)) | (1L << (TAGS - 130)) | (1L << (MACRO_START - 130)) | (1L << (ID - 130)))) != 0)) {
				{
				setState(1781);
				id();
				}
			}

			setState(1784);
			match(T__28);
			setState(1785);
			extendingOperationConfigBlock();
			setState(1786);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExtendingOperationConfigBlockContext extends ParserRuleContext {
		public List<ExtendingOperationAssignmentContext> extendingOperationAssignment() {
			return getRuleContexts(ExtendingOperationAssignmentContext.class);
		}
		public ExtendingOperationAssignmentContext extendingOperationAssignment(int i) {
			return getRuleContext(ExtendingOperationAssignmentContext.class,i);
		}
		public ExtendingOperationConfigBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_extendingOperationConfigBlock; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitExtendingOperationConfigBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExtendingOperationConfigBlockContext extendingOperationConfigBlock() throws RecognitionException {
		ExtendingOperationConfigBlockContext _localctx = new ExtendingOperationConfigBlockContext(_ctx, getState());
		enterRule(_localctx, 316, RULE_extendingOperationConfigBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1791);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MODULE) | (1L << ENTITY) | (1L << PRIMARYKEY) | (1L << ATTRIBUTE) | (1L << UNIQUE) | (1L << CREATION) | (1L << MODIFICATION) | (1L << PARENT) | (1L << ORDERED) | (1L << SEQUENTIAL) | (1L << EXTERN) | (1L << TRANSIENT) | (1L << PRIMARY) | (1L << SECONDARY) | (1L << FLATTEN) | (1L << RELATIONSHIPS) | (1L << RELATIONSHIP) | (1L << ENTITIES) | (1L << ENUM) | (1L << ENUMITEM) | (1L << TYPEDEF) | (1L << UNUSED) | (1L << CONFIGURATION) | (1L << FORMATTING))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (FORMAT - 64)) | (1L << (OUTPUT - 64)) | (1L << (DIRECTORY - 64)) | (1L << (TRANSFORM - 64)) | (1L << (TEMPLATE - 64)) | (1L << (PATH - 64)) | (1L << (ENDPOINT - 64)) | (1L << (INHERITED - 64)) | (1L << (REPOSITORY - 64)) | (1L << (TAG - 64)) | (1L << (VALUE - 64)) | (1L << (SPACE - 64)) | (1L << (IMPORT - 64)) | (1L << (FROM - 64)) | (1L << (ORGANIZATION - 64)) | (1L << (NAME - 64)) | (1L << (NULLABLE - 64)) | (1L << (LANGUAGE - 64)) | (1L << (VERSION - 64)) | (1L << (SELF - 64)) | (1L << (OPERATORS - 64)) | (1L << (KEYWORDS - 64)) | (1L << (SHORT - 64)) | (1L << (LONG - 64)) | (1L << (HUMAN - 64)) | (1L << (READABLE - 64)) | (1L << (IDENTIFICATION - 64)) | (1L << (DOMAIN - 64)) | (1L << (ATTRIBUTES - 64)) | (1L << (REPLACES - 64)) | (1L << (PREFIX - 64)) | (1L << (SUFFIX - 64)) | (1L << (RENAME - 64)) | (1L << (TAGGING - 64)) | (1L << (NAMESPACE - 64)) | (1L << (READ - 64)) | (1L << (WRITE - 64)) | (1L << (WHEN - 64)) | (1L << (REQUIRES - 64)) | (1L << (ROLE - 64)) | (1L << (USER - 64)) | (1L << (IF - 64)) | (1L << (APPLY - 64)))) != 0) || ((((_la - 130)) & ~0x3f) == 0 && ((1L << (_la - 130)) & ((1L << (DEFAULT - 130)) | (1L << (VIEW - 130)) | (1L << (INCLUDE - 130)) | (1L << (EXCLUDE - 130)) | (1L << (AND - 130)) | (1L << (ABSTRACT - 130)) | (1L << (EXTENDS - 130)) | (1L << (INTERFACE - 130)) | (1L << (CONFIG - 130)) | (1L << (METADATA - 130)) | (1L << (CONTEXT - 130)) | (1L << (ARGUMENT - 130)) | (1L << (OPERATION - 130)) | (1L << (QUERY - 130)) | (1L << (PARAM - 130)) | (1L << (STATUS - 130)) | (1L << (CUSTOM - 130)) | (1L << (TYPE - 130)) | (1L << (TO - 130)) | (1L << (PREPEND - 130)) | (1L << (APPEND - 130)) | (1L << (BOOLEAN_TYPE - 130)) | (1L << (INT32_TYPE - 130)) | (1L << (INT64_TYPE - 130)) | (1L << (BYTE_TYPE - 130)) | (1L << (UUID_TYPE - 130)) | (1L << (FLOAT_TYPE - 130)) | (1L << (DOUBLE_TYPE - 130)) | (1L << (STRING_TYPE - 130)) | (1L << (DATE_TYPE - 130)) | (1L << (ASSET_TYPE - 130)) | (1L << (ARRAY - 130)) | (1L << (DESCRIPTION - 130)) | (1L << (TAGS - 130)) | (1L << (MACRO_START - 130)) | (1L << (ID - 130)))) != 0)) {
				{
				{
				setState(1788);
				extendingOperationAssignment();
				}
				}
				setState(1793);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExtendingOperationAssignmentContext extends ParserRuleContext {
		public List<IdContext> id() {
			return getRuleContexts(IdContext.class);
		}
		public IdContext id(int i) {
			return getRuleContext(IdContext.class,i);
		}
		public TerminalNode STRING() { return getToken(EntityLanguageParser.STRING, 0); }
		public TerminalNode INTEGER() { return getToken(EntityLanguageParser.INTEGER, 0); }
		public TerminalNode FLOAT() { return getToken(EntityLanguageParser.FLOAT, 0); }
		public DescriptionStatementContext descriptionStatement() {
			return getRuleContext(DescriptionStatementContext.class,0);
		}
		public ExtendingOperationAssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_extendingOperationAssignment; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitExtendingOperationAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExtendingOperationAssignmentContext extendingOperationAssignment() throws RecognitionException {
		ExtendingOperationAssignmentContext _localctx = new ExtendingOperationAssignmentContext(_ctx, getState());
		enterRule(_localctx, 318, RULE_extendingOperationAssignment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1794);
			id();
			setState(1795);
			match(T__27);
			setState(1800);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MODULE:
			case ENTITY:
			case PRIMARYKEY:
			case ATTRIBUTE:
			case UNIQUE:
			case CREATION:
			case MODIFICATION:
			case PARENT:
			case ORDERED:
			case SEQUENTIAL:
			case EXTERN:
			case TRANSIENT:
			case PRIMARY:
			case SECONDARY:
			case FLATTEN:
			case RELATIONSHIPS:
			case RELATIONSHIP:
			case ENTITIES:
			case ENUM:
			case ENUMITEM:
			case TYPEDEF:
			case UNUSED:
			case CONFIGURATION:
			case FORMATTING:
			case FORMAT:
			case OUTPUT:
			case DIRECTORY:
			case TRANSFORM:
			case TEMPLATE:
			case PATH:
			case ENDPOINT:
			case INHERITED:
			case REPOSITORY:
			case TAG:
			case VALUE:
			case SPACE:
			case IMPORT:
			case FROM:
			case ORGANIZATION:
			case NAME:
			case NULLABLE:
			case LANGUAGE:
			case VERSION:
			case SELF:
			case OPERATORS:
			case KEYWORDS:
			case SHORT:
			case LONG:
			case HUMAN:
			case READABLE:
			case IDENTIFICATION:
			case DOMAIN:
			case ATTRIBUTES:
			case REPLACES:
			case PREFIX:
			case SUFFIX:
			case RENAME:
			case TAGGING:
			case NAMESPACE:
			case READ:
			case WRITE:
			case WHEN:
			case REQUIRES:
			case ROLE:
			case USER:
			case IF:
			case APPLY:
			case DEFAULT:
			case VIEW:
			case INCLUDE:
			case EXCLUDE:
			case AND:
			case ABSTRACT:
			case EXTENDS:
			case INTERFACE:
			case CONFIG:
			case METADATA:
			case CONTEXT:
			case ARGUMENT:
			case OPERATION:
			case QUERY:
			case PARAM:
			case STATUS:
			case CUSTOM:
			case TYPE:
			case TO:
			case PREPEND:
			case APPEND:
			case BOOLEAN_TYPE:
			case INT32_TYPE:
			case INT64_TYPE:
			case BYTE_TYPE:
			case UUID_TYPE:
			case FLOAT_TYPE:
			case DOUBLE_TYPE:
			case STRING_TYPE:
			case DATE_TYPE:
			case ASSET_TYPE:
			case ARRAY:
			case DESCRIPTION:
			case TAGS:
			case MACRO_START:
			case ID:
				{
				setState(1796);
				id();
				}
				break;
			case STRING:
				{
				setState(1797);
				match(STRING);
				}
				break;
			case INTEGER:
				{
				setState(1798);
				match(INTEGER);
				}
				break;
			case FLOAT:
				{
				setState(1799);
				match(FLOAT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1806);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__28) {
				{
				setState(1802);
				match(T__28);
				setState(1803);
				descriptionStatement();
				setState(1804);
				match(T__29);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainOperationBodyContext extends ParserRuleContext {
		public List<DescriptionStatementContext> descriptionStatement() {
			return getRuleContexts(DescriptionStatementContext.class);
		}
		public DescriptionStatementContext descriptionStatement(int i) {
			return getRuleContext(DescriptionStatementContext.class,i);
		}
		public List<OperationRequestContext> operationRequest() {
			return getRuleContexts(OperationRequestContext.class);
		}
		public OperationRequestContext operationRequest(int i) {
			return getRuleContext(OperationRequestContext.class,i);
		}
		public List<OperationResponseContext> operationResponse() {
			return getRuleContexts(OperationResponseContext.class);
		}
		public OperationResponseContext operationResponse(int i) {
			return getRuleContext(OperationResponseContext.class,i);
		}
		public DomainOperationBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainOperationBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDomainOperationBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainOperationBodyContext domainOperationBody() throws RecognitionException {
		DomainOperationBodyContext _localctx = new DomainOperationBodyContext(_ctx, getState());
		enterRule(_localctx, 320, RULE_domainOperationBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1813);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 148)) & ~0x3f) == 0 && ((1L << (_la - 148)) & ((1L << (REQUEST - 148)) | (1L << (RESPONSE - 148)) | (1L << (DESCRIPTION - 148)))) != 0)) {
				{
				setState(1811);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case DESCRIPTION:
					{
					setState(1808);
					descriptionStatement();
					}
					break;
				case REQUEST:
					{
					setState(1809);
					operationRequest();
					}
					break;
				case RESPONSE:
					{
					setState(1810);
					operationResponse();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(1815);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConfigurationContext extends ParserRuleContext {
		public TerminalNode CONFIGURATION() { return getToken(EntityLanguageParser.CONFIGURATION, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public ConfigurationBodyContext configurationBody() {
			return getRuleContext(ConfigurationBodyContext.class,0);
		}
		public ConfigurationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_configuration; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitConfiguration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConfigurationContext configuration() throws RecognitionException {
		ConfigurationContext _localctx = new ConfigurationContext(_ctx, getState());
		enterRule(_localctx, 322, RULE_configuration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1816);
			match(CONFIGURATION);
			setState(1817);
			id();
			setState(1818);
			match(T__28);
			setState(1819);
			configurationBody();
			setState(1820);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConfigurationBodyContext extends ParserRuleContext {
		public List<DirectoryContext> directory() {
			return getRuleContexts(DirectoryContext.class);
		}
		public DirectoryContext directory(int i) {
			return getRuleContext(DirectoryContext.class,i);
		}
		public List<TemplatesContext> templates() {
			return getRuleContexts(TemplatesContext.class);
		}
		public TemplatesContext templates(int i) {
			return getRuleContext(TemplatesContext.class,i);
		}
		public List<TransformContext> transform() {
			return getRuleContexts(TransformContext.class);
		}
		public TransformContext transform(int i) {
			return getRuleContext(TransformContext.class,i);
		}
		public List<ProtocContext> protoc() {
			return getRuleContexts(ProtocContext.class);
		}
		public ProtocContext protoc(int i) {
			return getRuleContext(ProtocContext.class,i);
		}
		public List<RepositoryContext> repository() {
			return getRuleContexts(RepositoryContext.class);
		}
		public RepositoryContext repository(int i) {
			return getRuleContext(RepositoryContext.class,i);
		}
		public ConfigurationBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_configurationBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitConfigurationBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConfigurationBodyContext configurationBody() throws RecognitionException {
		ConfigurationBodyContext _localctx = new ConfigurationBodyContext(_ctx, getState());
		enterRule(_localctx, 324, RULE_configurationBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1829);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (OUTPUT - 65)) | (1L << (DIRECTORY - 65)) | (1L << (TRANSFORM - 65)) | (1L << (TEMPLATES - 65)) | (1L << (REPOSITORY - 65)) | (1L << (PROTOC - 65)))) != 0)) {
				{
				setState(1827);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case OUTPUT:
				case DIRECTORY:
					{
					setState(1822);
					directory();
					}
					break;
				case TEMPLATES:
					{
					setState(1823);
					templates();
					}
					break;
				case TRANSFORM:
					{
					setState(1824);
					transform();
					}
					break;
				case PROTOC:
					{
					setState(1825);
					protoc();
					}
					break;
				case REPOSITORY:
					{
					setState(1826);
					repository();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(1831);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormattingContext extends ParserRuleContext {
		public TerminalNode FORMATTING() { return getToken(EntityLanguageParser.FORMATTING, 0); }
		public FormattingBodyContext formattingBody() {
			return getRuleContext(FormattingBodyContext.class,0);
		}
		public FormattingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formatting; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitFormatting(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormattingContext formatting() throws RecognitionException {
		FormattingContext _localctx = new FormattingContext(_ctx, getState());
		enterRule(_localctx, 326, RULE_formatting);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1832);
			match(FORMATTING);
			setState(1833);
			match(T__28);
			setState(1834);
			formattingBody();
			setState(1835);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormattingBodyContext extends ParserRuleContext {
		public List<FormatStatementContext> formatStatement() {
			return getRuleContexts(FormatStatementContext.class);
		}
		public FormatStatementContext formatStatement(int i) {
			return getRuleContext(FormatStatementContext.class,i);
		}
		public FormattingBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formattingBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitFormattingBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormattingBodyContext formattingBody() throws RecognitionException {
		FormattingBodyContext _localctx = new FormattingBodyContext(_ctx, getState());
		enterRule(_localctx, 328, RULE_formattingBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1840);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FORMAT) {
				{
				{
				setState(1837);
				formatStatement();
				}
				}
				setState(1842);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormatStatementContext extends ParserRuleContext {
		public TerminalNode FORMAT() { return getToken(EntityLanguageParser.FORMAT, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public JsonObjContext jsonObj() {
			return getRuleContext(JsonObjContext.class,0);
		}
		public FormatStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formatStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitFormatStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormatStatementContext formatStatement() throws RecognitionException {
		FormatStatementContext _localctx = new FormatStatementContext(_ctx, getState());
		enterRule(_localctx, 330, RULE_formatStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1843);
			match(FORMAT);
			setState(1844);
			id();
			setState(1845);
			jsonObj();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DirectoryContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public OutputBodyContext outputBody() {
			return getRuleContext(OutputBodyContext.class,0);
		}
		public TerminalNode OUTPUT() { return getToken(EntityLanguageParser.OUTPUT, 0); }
		public TerminalNode DIRECTORY() { return getToken(EntityLanguageParser.DIRECTORY, 0); }
		public DirectoryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_directory; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitDirectory(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DirectoryContext directory() throws RecognitionException {
		DirectoryContext _localctx = new DirectoryContext(_ctx, getState());
		enterRule(_localctx, 332, RULE_directory);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1847);
			_la = _input.LA(1);
			if ( !(_la==OUTPUT || _la==DIRECTORY) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(1848);
			id();
			setState(1849);
			match(T__28);
			setState(1850);
			outputBody();
			setState(1851);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OutputBodyContext extends ParserRuleContext {
		public List<OutputPathContext> outputPath() {
			return getRuleContexts(OutputPathContext.class);
		}
		public OutputPathContext outputPath(int i) {
			return getRuleContext(OutputPathContext.class,i);
		}
		public List<DescriptionStatementContext> descriptionStatement() {
			return getRuleContexts(DescriptionStatementContext.class);
		}
		public DescriptionStatementContext descriptionStatement(int i) {
			return getRuleContext(DescriptionStatementContext.class,i);
		}
		public OutputBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_outputBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitOutputBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OutputBodyContext outputBody() throws RecognitionException {
		OutputBodyContext _localctx = new OutputBodyContext(_ctx, getState());
		enterRule(_localctx, 334, RULE_outputBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1857);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PATH || _la==DESCRIPTION) {
				{
				setState(1855);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case PATH:
					{
					setState(1853);
					outputPath();
					}
					break;
				case DESCRIPTION:
					{
					setState(1854);
					descriptionStatement();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(1859);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OutputPathContext extends ParserRuleContext {
		public TerminalNode PATH() { return getToken(EntityLanguageParser.PATH, 0); }
		public TerminalNode STRING() { return getToken(EntityLanguageParser.STRING, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public OutputPathContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_outputPath; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitOutputPath(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OutputPathContext outputPath() throws RecognitionException {
		OutputPathContext _localctx = new OutputPathContext(_ctx, getState());
		enterRule(_localctx, 336, RULE_outputPath);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1860);
			match(PATH);
			setState(1863);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				{
				setState(1861);
				match(STRING);
				}
				break;
			case MODULE:
			case ENTITY:
			case PRIMARYKEY:
			case ATTRIBUTE:
			case UNIQUE:
			case CREATION:
			case MODIFICATION:
			case PARENT:
			case ORDERED:
			case SEQUENTIAL:
			case EXTERN:
			case TRANSIENT:
			case PRIMARY:
			case SECONDARY:
			case FLATTEN:
			case RELATIONSHIPS:
			case RELATIONSHIP:
			case ENTITIES:
			case ENUM:
			case ENUMITEM:
			case TYPEDEF:
			case UNUSED:
			case CONFIGURATION:
			case FORMATTING:
			case FORMAT:
			case OUTPUT:
			case DIRECTORY:
			case TRANSFORM:
			case TEMPLATE:
			case PATH:
			case ENDPOINT:
			case INHERITED:
			case REPOSITORY:
			case TAG:
			case VALUE:
			case SPACE:
			case IMPORT:
			case FROM:
			case ORGANIZATION:
			case NAME:
			case NULLABLE:
			case LANGUAGE:
			case VERSION:
			case SELF:
			case OPERATORS:
			case KEYWORDS:
			case SHORT:
			case LONG:
			case HUMAN:
			case READABLE:
			case IDENTIFICATION:
			case DOMAIN:
			case ATTRIBUTES:
			case REPLACES:
			case PREFIX:
			case SUFFIX:
			case RENAME:
			case TAGGING:
			case NAMESPACE:
			case READ:
			case WRITE:
			case WHEN:
			case REQUIRES:
			case ROLE:
			case USER:
			case IF:
			case APPLY:
			case DEFAULT:
			case VIEW:
			case INCLUDE:
			case EXCLUDE:
			case AND:
			case ABSTRACT:
			case EXTENDS:
			case INTERFACE:
			case CONFIG:
			case METADATA:
			case CONTEXT:
			case ARGUMENT:
			case OPERATION:
			case QUERY:
			case PARAM:
			case STATUS:
			case CUSTOM:
			case TYPE:
			case TO:
			case PREPEND:
			case APPEND:
			case BOOLEAN_TYPE:
			case INT32_TYPE:
			case INT64_TYPE:
			case BYTE_TYPE:
			case UUID_TYPE:
			case FLOAT_TYPE:
			case DOUBLE_TYPE:
			case STRING_TYPE:
			case DATE_TYPE:
			case ASSET_TYPE:
			case ARRAY:
			case DESCRIPTION:
			case TAGS:
			case MACRO_START:
			case ID:
				{
				setState(1862);
				id();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TemplatesContext extends ParserRuleContext {
		public TerminalNode TEMPLATES() { return getToken(EntityLanguageParser.TEMPLATES, 0); }
		public TemplatesBodyContext templatesBody() {
			return getRuleContext(TemplatesBodyContext.class,0);
		}
		public TemplatesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_templates; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitTemplates(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TemplatesContext templates() throws RecognitionException {
		TemplatesContext _localctx = new TemplatesContext(_ctx, getState());
		enterRule(_localctx, 338, RULE_templates);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1865);
			match(TEMPLATES);
			setState(1866);
			match(T__28);
			setState(1867);
			templatesBody();
			setState(1868);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TemplatesBodyContext extends ParserRuleContext {
		public TemplatesImportContext templatesImport() {
			return getRuleContext(TemplatesImportContext.class,0);
		}
		public DefaultTemplateConfigContext defaultTemplateConfig() {
			return getRuleContext(DefaultTemplateConfigContext.class,0);
		}
		public List<TemplateContext> template() {
			return getRuleContexts(TemplateContext.class);
		}
		public TemplateContext template(int i) {
			return getRuleContext(TemplateContext.class,i);
		}
		public TemplatesBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_templatesBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitTemplatesBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TemplatesBodyContext templatesBody() throws RecognitionException {
		TemplatesBodyContext _localctx = new TemplatesBodyContext(_ctx, getState());
		enterRule(_localctx, 340, RULE_templatesBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1871);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IMPORT) {
				{
				setState(1870);
				templatesImport();
				}
			}

			setState(1874);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DEFAULT) {
				{
				setState(1873);
				defaultTemplateConfig();
				}
			}

			setState(1879);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TEMPLATE || _la==CONTEXTUAL) {
				{
				{
				setState(1876);
				template();
				}
				}
				setState(1881);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TemplateContext extends ParserRuleContext {
		public TerminalNode TEMPLATE() { return getToken(EntityLanguageParser.TEMPLATE, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TemplateBodyContext templateBody() {
			return getRuleContext(TemplateBodyContext.class,0);
		}
		public TerminalNode CONTEXTUAL() { return getToken(EntityLanguageParser.CONTEXTUAL, 0); }
		public TerminalNode IN() { return getToken(EntityLanguageParser.IN, 0); }
		public TerminalNode STRING() { return getToken(EntityLanguageParser.STRING, 0); }
		public TemplateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_template; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitTemplate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TemplateContext template() throws RecognitionException {
		TemplateContext _localctx = new TemplateContext(_ctx, getState());
		enterRule(_localctx, 342, RULE_template);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1883);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CONTEXTUAL) {
				{
				setState(1882);
				match(CONTEXTUAL);
				}
			}

			setState(1885);
			match(TEMPLATE);
			setState(1886);
			id();
			setState(1889);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IN) {
				{
				setState(1887);
				match(IN);
				setState(1888);
				match(STRING);
				}
			}

			setState(1891);
			match(T__28);
			setState(1892);
			templateBody();
			setState(1893);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TemplateBodyContext extends ParserRuleContext {
		public List<DescriptionStatementContext> descriptionStatement() {
			return getRuleContexts(DescriptionStatementContext.class);
		}
		public DescriptionStatementContext descriptionStatement(int i) {
			return getRuleContext(DescriptionStatementContext.class,i);
		}
		public List<TagStatementContext> tagStatement() {
			return getRuleContexts(TagStatementContext.class);
		}
		public TagStatementContext tagStatement(int i) {
			return getRuleContext(TagStatementContext.class,i);
		}
		public List<OutputSpecContext> outputSpec() {
			return getRuleContexts(OutputSpecContext.class);
		}
		public OutputSpecContext outputSpec(int i) {
			return getRuleContext(OutputSpecContext.class,i);
		}
		public List<TemplateConfigContext> templateConfig() {
			return getRuleContexts(TemplateConfigContext.class);
		}
		public TemplateConfigContext templateConfig(int i) {
			return getRuleContext(TemplateConfigContext.class,i);
		}
		public TemplateBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_templateBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitTemplateBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TemplateBodyContext templateBody() throws RecognitionException {
		TemplateBodyContext _localctx = new TemplateBodyContext(_ctx, getState());
		enterRule(_localctx, 344, RULE_templateBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1901);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OUTPUT || ((((_la - 143)) & ~0x3f) == 0 && ((1L << (_la - 143)) & ((1L << (CONFIG - 143)) | (1L << (DESCRIPTION - 143)) | (1L << (TAGS - 143)))) != 0)) {
				{
				setState(1899);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case DESCRIPTION:
					{
					setState(1895);
					descriptionStatement();
					}
					break;
				case TAGS:
					{
					setState(1896);
					tagStatement();
					}
					break;
				case OUTPUT:
					{
					setState(1897);
					outputSpec();
					}
					break;
				case CONFIG:
					{
					setState(1898);
					templateConfig();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(1903);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TemplatesImportContext extends ParserRuleContext {
		public TerminalNode IMPORT() { return getToken(EntityLanguageParser.IMPORT, 0); }
		public TerminalNode FROM() { return getToken(EntityLanguageParser.FROM, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TemplatesImportContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_templatesImport; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitTemplatesImport(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TemplatesImportContext templatesImport() throws RecognitionException {
		TemplatesImportContext _localctx = new TemplatesImportContext(_ctx, getState());
		enterRule(_localctx, 346, RULE_templatesImport);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1904);
			match(IMPORT);
			setState(1905);
			match(FROM);
			setState(1906);
			id();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OutputSpecContext extends ParserRuleContext {
		public TerminalNode OUTPUT() { return getToken(EntityLanguageParser.OUTPUT, 0); }
		public TerminalNode PRIMARY() { return getToken(EntityLanguageParser.PRIMARY, 0); }
		public List<IdContext> id() {
			return getRuleContexts(IdContext.class);
		}
		public IdContext id(int i) {
			return getRuleContext(IdContext.class,i);
		}
		public OutputSpecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_outputSpec; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitOutputSpec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OutputSpecContext outputSpec() throws RecognitionException {
		OutputSpecContext _localctx = new OutputSpecContext(_ctx, getState());
		enterRule(_localctx, 348, RULE_outputSpec);
		try {
			setState(1915);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,204,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1908);
				match(OUTPUT);
				setState(1909);
				match(PRIMARY);
				setState(1910);
				id();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1911);
				match(OUTPUT);
				setState(1912);
				id();
				setState(1913);
				id();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TransformContext extends ParserRuleContext {
		public TerminalNode TRANSFORM() { return getToken(EntityLanguageParser.TRANSFORM, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TransformBodyContext transformBody() {
			return getRuleContext(TransformBodyContext.class,0);
		}
		public TransformContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_transform; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitTransform(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TransformContext transform() throws RecognitionException {
		TransformContext _localctx = new TransformContext(_ctx, getState());
		enterRule(_localctx, 350, RULE_transform);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1917);
			match(TRANSFORM);
			setState(1918);
			id();
			setState(1919);
			match(T__28);
			setState(1920);
			transformBody();
			setState(1921);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TransformBodyContext extends ParserRuleContext {
		public List<OutputSpecContext> outputSpec() {
			return getRuleContexts(OutputSpecContext.class);
		}
		public OutputSpecContext outputSpec(int i) {
			return getRuleContext(OutputSpecContext.class,i);
		}
		public TransformBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_transformBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitTransformBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TransformBodyContext transformBody() throws RecognitionException {
		TransformBodyContext _localctx = new TransformBodyContext(_ctx, getState());
		enterRule(_localctx, 352, RULE_transformBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1926);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OUTPUT) {
				{
				{
				setState(1923);
				outputSpec();
				}
				}
				setState(1928);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RepositoryContext extends ParserRuleContext {
		public TerminalNode REPOSITORY() { return getToken(EntityLanguageParser.REPOSITORY, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public RepositoryBodyContext repositoryBody() {
			return getRuleContext(RepositoryBodyContext.class,0);
		}
		public RepositoryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_repository; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitRepository(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RepositoryContext repository() throws RecognitionException {
		RepositoryContext _localctx = new RepositoryContext(_ctx, getState());
		enterRule(_localctx, 354, RULE_repository);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1929);
			match(REPOSITORY);
			setState(1930);
			id();
			setState(1931);
			match(T__28);
			setState(1932);
			repositoryBody();
			setState(1933);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RepositoryBodyContext extends ParserRuleContext {
		public List<DescriptionStatementContext> descriptionStatement() {
			return getRuleContexts(DescriptionStatementContext.class);
		}
		public DescriptionStatementContext descriptionStatement(int i) {
			return getRuleContext(DescriptionStatementContext.class,i);
		}
		public List<RepositoryTypeContext> repositoryType() {
			return getRuleContexts(RepositoryTypeContext.class);
		}
		public RepositoryTypeContext repositoryType(int i) {
			return getRuleContext(RepositoryTypeContext.class,i);
		}
		public List<RepositoryOrganizationContext> repositoryOrganization() {
			return getRuleContexts(RepositoryOrganizationContext.class);
		}
		public RepositoryOrganizationContext repositoryOrganization(int i) {
			return getRuleContext(RepositoryOrganizationContext.class,i);
		}
		public List<RepositoryNameContext> repositoryName() {
			return getRuleContexts(RepositoryNameContext.class);
		}
		public RepositoryNameContext repositoryName(int i) {
			return getRuleContext(RepositoryNameContext.class,i);
		}
		public List<RepositoryPathContext> repositoryPath() {
			return getRuleContexts(RepositoryPathContext.class);
		}
		public RepositoryPathContext repositoryPath(int i) {
			return getRuleContext(RepositoryPathContext.class,i);
		}
		public List<RepositoryTagContext> repositoryTag() {
			return getRuleContexts(RepositoryTagContext.class);
		}
		public RepositoryTagContext repositoryTag(int i) {
			return getRuleContext(RepositoryTagContext.class,i);
		}
		public RepositoryBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_repositoryBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitRepositoryBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RepositoryBodyContext repositoryBody() throws RecognitionException {
		RepositoryBodyContext _localctx = new RepositoryBodyContext(_ctx, getState());
		enterRule(_localctx, 356, RULE_repositoryBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1943);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 71)) & ~0x3f) == 0 && ((1L << (_la - 71)) & ((1L << (PATH - 71)) | (1L << (TAG - 71)) | (1L << (ORGANIZATION - 71)) | (1L << (NAME - 71)))) != 0) || _la==TYPE || _la==DESCRIPTION) {
				{
				setState(1941);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case DESCRIPTION:
					{
					setState(1935);
					descriptionStatement();
					}
					break;
				case TYPE:
					{
					setState(1936);
					repositoryType();
					}
					break;
				case ORGANIZATION:
					{
					setState(1937);
					repositoryOrganization();
					}
					break;
				case NAME:
					{
					setState(1938);
					repositoryName();
					}
					break;
				case PATH:
					{
					setState(1939);
					repositoryPath();
					}
					break;
				case TAG:
					{
					setState(1940);
					repositoryTag();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(1945);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RepositoryTypeContext extends ParserRuleContext {
		public TerminalNode TYPE() { return getToken(EntityLanguageParser.TYPE, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public RepositoryTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_repositoryType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitRepositoryType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RepositoryTypeContext repositoryType() throws RecognitionException {
		RepositoryTypeContext _localctx = new RepositoryTypeContext(_ctx, getState());
		enterRule(_localctx, 358, RULE_repositoryType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1946);
			match(TYPE);
			setState(1947);
			id();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RepositoryOrganizationContext extends ParserRuleContext {
		public TerminalNode ORGANIZATION() { return getToken(EntityLanguageParser.ORGANIZATION, 0); }
		public TerminalNode STRING() { return getToken(EntityLanguageParser.STRING, 0); }
		public RepositoryOrganizationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_repositoryOrganization; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitRepositoryOrganization(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RepositoryOrganizationContext repositoryOrganization() throws RecognitionException {
		RepositoryOrganizationContext _localctx = new RepositoryOrganizationContext(_ctx, getState());
		enterRule(_localctx, 360, RULE_repositoryOrganization);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1949);
			match(ORGANIZATION);
			setState(1950);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RepositoryNameContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(EntityLanguageParser.NAME, 0); }
		public TerminalNode STRING() { return getToken(EntityLanguageParser.STRING, 0); }
		public RepositoryNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_repositoryName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitRepositoryName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RepositoryNameContext repositoryName() throws RecognitionException {
		RepositoryNameContext _localctx = new RepositoryNameContext(_ctx, getState());
		enterRule(_localctx, 362, RULE_repositoryName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1952);
			match(NAME);
			setState(1953);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RepositoryPathContext extends ParserRuleContext {
		public TerminalNode PATH() { return getToken(EntityLanguageParser.PATH, 0); }
		public TerminalNode STRING() { return getToken(EntityLanguageParser.STRING, 0); }
		public RepositoryPathContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_repositoryPath; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitRepositoryPath(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RepositoryPathContext repositoryPath() throws RecognitionException {
		RepositoryPathContext _localctx = new RepositoryPathContext(_ctx, getState());
		enterRule(_localctx, 364, RULE_repositoryPath);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1955);
			match(PATH);
			setState(1956);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RepositoryTagContext extends ParserRuleContext {
		public TerminalNode TAG() { return getToken(EntityLanguageParser.TAG, 0); }
		public TerminalNode STRING() { return getToken(EntityLanguageParser.STRING, 0); }
		public RepositoryTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_repositoryTag; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitRepositoryTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RepositoryTagContext repositoryTag() throws RecognitionException {
		RepositoryTagContext _localctx = new RepositoryTagContext(_ctx, getState());
		enterRule(_localctx, 366, RULE_repositoryTag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1958);
			match(TAG);
			setState(1959);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SpaceContext extends ParserRuleContext {
		public TerminalNode SPACE() { return getToken(EntityLanguageParser.SPACE, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public SpaceBodyContext spaceBody() {
			return getRuleContext(SpaceBodyContext.class,0);
		}
		public SpaceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_space; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitSpace(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SpaceContext space() throws RecognitionException {
		SpaceContext _localctx = new SpaceContext(_ctx, getState());
		enterRule(_localctx, 368, RULE_space);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1961);
			match(SPACE);
			setState(1962);
			id();
			setState(1963);
			match(T__28);
			setState(1964);
			spaceBody();
			setState(1965);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SpaceBodyContext extends ParserRuleContext {
		public List<DescriptionStatementContext> descriptionStatement() {
			return getRuleContexts(DescriptionStatementContext.class);
		}
		public DescriptionStatementContext descriptionStatement(int i) {
			return getRuleContext(DescriptionStatementContext.class,i);
		}
		public List<TagStatementContext> tagStatement() {
			return getRuleContexts(TagStatementContext.class);
		}
		public TagStatementContext tagStatement(int i) {
			return getRuleContext(TagStatementContext.class,i);
		}
		public List<SpaceNamespaceContext> spaceNamespace() {
			return getRuleContexts(SpaceNamespaceContext.class);
		}
		public SpaceNamespaceContext spaceNamespace(int i) {
			return getRuleContext(SpaceNamespaceContext.class,i);
		}
		public List<SpaceMetadataContext> spaceMetadata() {
			return getRuleContexts(SpaceMetadataContext.class);
		}
		public SpaceMetadataContext spaceMetadata(int i) {
			return getRuleContext(SpaceMetadataContext.class,i);
		}
		public List<SpaceImportContext> spaceImport() {
			return getRuleContexts(SpaceImportContext.class);
		}
		public SpaceImportContext spaceImport(int i) {
			return getRuleContext(SpaceImportContext.class,i);
		}
		public List<SpaceIncludeContext> spaceInclude() {
			return getRuleContexts(SpaceIncludeContext.class);
		}
		public SpaceIncludeContext spaceInclude(int i) {
			return getRuleContext(SpaceIncludeContext.class,i);
		}
		public List<RepositoryContext> repository() {
			return getRuleContexts(RepositoryContext.class);
		}
		public RepositoryContext repository(int i) {
			return getRuleContext(RepositoryContext.class,i);
		}
		public SpaceBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_spaceBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitSpaceBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SpaceBodyContext spaceBody() throws RecognitionException {
		SpaceBodyContext _localctx = new SpaceBodyContext(_ctx, getState());
		enterRule(_localctx, 370, RULE_spaceBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1976);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 74)) & ~0x3f) == 0 && ((1L << (_la - 74)) & ((1L << (REPOSITORY - 74)) | (1L << (IMPORT - 74)) | (1L << (NAMESPACE - 74)) | (1L << (INCLUDE - 74)))) != 0) || ((((_la - 144)) & ~0x3f) == 0 && ((1L << (_la - 144)) & ((1L << (METADATA - 144)) | (1L << (DESCRIPTION - 144)) | (1L << (TAGS - 144)))) != 0)) {
				{
				setState(1974);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case DESCRIPTION:
					{
					setState(1967);
					descriptionStatement();
					}
					break;
				case TAGS:
					{
					setState(1968);
					tagStatement();
					}
					break;
				case NAMESPACE:
					{
					setState(1969);
					spaceNamespace();
					}
					break;
				case METADATA:
					{
					setState(1970);
					spaceMetadata();
					}
					break;
				case IMPORT:
					{
					setState(1971);
					spaceImport();
					}
					break;
				case INCLUDE:
					{
					setState(1972);
					spaceInclude();
					}
					break;
				case REPOSITORY:
					{
					setState(1973);
					repository();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(1978);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SpaceNamespaceContext extends ParserRuleContext {
		public TerminalNode NAMESPACE() { return getToken(EntityLanguageParser.NAMESPACE, 0); }
		public NamespaceIdentContext namespaceIdent() {
			return getRuleContext(NamespaceIdentContext.class,0);
		}
		public SpaceNamespaceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_spaceNamespace; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitSpaceNamespace(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SpaceNamespaceContext spaceNamespace() throws RecognitionException {
		SpaceNamespaceContext _localctx = new SpaceNamespaceContext(_ctx, getState());
		enterRule(_localctx, 372, RULE_spaceNamespace);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1979);
			match(NAMESPACE);
			setState(1980);
			namespaceIdent();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SpaceMetadataContext extends ParserRuleContext {
		public TerminalNode METADATA() { return getToken(EntityLanguageParser.METADATA, 0); }
		public JsonObjContext jsonObj() {
			return getRuleContext(JsonObjContext.class,0);
		}
		public SpaceMetadataContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_spaceMetadata; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitSpaceMetadata(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SpaceMetadataContext spaceMetadata() throws RecognitionException {
		SpaceMetadataContext _localctx = new SpaceMetadataContext(_ctx, getState());
		enterRule(_localctx, 374, RULE_spaceMetadata);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1982);
			match(METADATA);
			setState(1983);
			jsonObj();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SpaceImportContext extends ParserRuleContext {
		public TerminalNode IMPORT() { return getToken(EntityLanguageParser.IMPORT, 0); }
		public IdListContext idList() {
			return getRuleContext(IdListContext.class,0);
		}
		public TerminalNode FROM() { return getToken(EntityLanguageParser.FROM, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public SpaceImportContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_spaceImport; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitSpaceImport(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SpaceImportContext spaceImport() throws RecognitionException {
		SpaceImportContext _localctx = new SpaceImportContext(_ctx, getState());
		enterRule(_localctx, 376, RULE_spaceImport);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1985);
			match(IMPORT);
			setState(1986);
			idList();
			setState(1987);
			match(FROM);
			setState(1988);
			id();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SpaceIncludeContext extends ParserRuleContext {
		public TerminalNode INCLUDE() { return getToken(EntityLanguageParser.INCLUDE, 0); }
		public IdListContext idList() {
			return getRuleContext(IdListContext.class,0);
		}
		public TerminalNode FROM() { return getToken(EntityLanguageParser.FROM, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public SpaceIncludeBodyContext spaceIncludeBody() {
			return getRuleContext(SpaceIncludeBodyContext.class,0);
		}
		public SpaceIncludeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_spaceInclude; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitSpaceInclude(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SpaceIncludeContext spaceInclude() throws RecognitionException {
		SpaceIncludeContext _localctx = new SpaceIncludeContext(_ctx, getState());
		enterRule(_localctx, 378, RULE_spaceInclude);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1990);
			match(INCLUDE);
			setState(1991);
			idList();
			setState(1992);
			match(FROM);
			setState(1993);
			id();
			setState(1998);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__28) {
				{
				setState(1994);
				match(T__28);
				setState(1995);
				spaceIncludeBody();
				setState(1996);
				match(T__29);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SpaceIncludeBodyContext extends ParserRuleContext {
		public List<SpaceIncludeImportEnumContext> spaceIncludeImportEnum() {
			return getRuleContexts(SpaceIncludeImportEnumContext.class);
		}
		public SpaceIncludeImportEnumContext spaceIncludeImportEnum(int i) {
			return getRuleContext(SpaceIncludeImportEnumContext.class,i);
		}
		public List<SpaceIncludeImportEntityContext> spaceIncludeImportEntity() {
			return getRuleContexts(SpaceIncludeImportEntityContext.class);
		}
		public SpaceIncludeImportEntityContext spaceIncludeImportEntity(int i) {
			return getRuleContext(SpaceIncludeImportEntityContext.class,i);
		}
		public SpaceIncludeBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_spaceIncludeBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitSpaceIncludeBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SpaceIncludeBodyContext spaceIncludeBody() throws RecognitionException {
		SpaceIncludeBodyContext _localctx = new SpaceIncludeBodyContext(_ctx, getState());
		enterRule(_localctx, 380, RULE_spaceIncludeBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2004);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IMPORT) {
				{
				setState(2002);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,211,_ctx) ) {
				case 1:
					{
					setState(2000);
					spaceIncludeImportEnum();
					}
					break;
				case 2:
					{
					setState(2001);
					spaceIncludeImportEntity();
					}
					break;
				}
				}
				setState(2006);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SpaceIncludeImportEnumContext extends ParserRuleContext {
		public TerminalNode IMPORT() { return getToken(EntityLanguageParser.IMPORT, 0); }
		public TerminalNode ENUM() { return getToken(EntityLanguageParser.ENUM, 0); }
		public IdListContext idList() {
			return getRuleContext(IdListContext.class,0);
		}
		public SpaceIncludeImportEnumContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_spaceIncludeImportEnum; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitSpaceIncludeImportEnum(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SpaceIncludeImportEnumContext spaceIncludeImportEnum() throws RecognitionException {
		SpaceIncludeImportEnumContext _localctx = new SpaceIncludeImportEnumContext(_ctx, getState());
		enterRule(_localctx, 382, RULE_spaceIncludeImportEnum);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2007);
			match(IMPORT);
			setState(2008);
			match(ENUM);
			setState(2009);
			idList();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SpaceIncludeImportEntityContext extends ParserRuleContext {
		public TerminalNode IMPORT() { return getToken(EntityLanguageParser.IMPORT, 0); }
		public TerminalNode ENTITY() { return getToken(EntityLanguageParser.ENTITY, 0); }
		public IdListContext idList() {
			return getRuleContext(IdListContext.class,0);
		}
		public SpaceIncludeImportEntityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_spaceIncludeImportEntity; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitSpaceIncludeImportEntity(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SpaceIncludeImportEntityContext spaceIncludeImportEntity() throws RecognitionException {
		SpaceIncludeImportEntityContext _localctx = new SpaceIncludeImportEntityContext(_ctx, getState());
		enterRule(_localctx, 384, RULE_spaceIncludeImportEntity);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2011);
			match(IMPORT);
			setState(2012);
			match(ENTITY);
			setState(2013);
			idList();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdListContext extends ParserRuleContext {
		public List<IdContext> id() {
			return getRuleContexts(IdContext.class);
		}
		public IdContext id(int i) {
			return getRuleContext(IdContext.class,i);
		}
		public IdListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_idList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitIdList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdListContext idList() throws RecognitionException {
		IdListContext _localctx = new IdListContext(_ctx, getState());
		enterRule(_localctx, 386, RULE_idList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2015);
			id();
			setState(2020);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(2016);
				match(T__4);
				setState(2017);
				id();
				}
				}
				setState(2022);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProtocContext extends ParserRuleContext {
		public TerminalNode PROTOC() { return getToken(EntityLanguageParser.PROTOC, 0); }
		public ProtocBodyContext protocBody() {
			return getRuleContext(ProtocBodyContext.class,0);
		}
		public ProtocContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_protoc; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitProtoc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProtocContext protoc() throws RecognitionException {
		ProtocContext _localctx = new ProtocContext(_ctx, getState());
		enterRule(_localctx, 388, RULE_protoc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2023);
			match(PROTOC);
			setState(2024);
			match(T__28);
			setState(2025);
			protocBody();
			setState(2026);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProtocBodyContext extends ParserRuleContext {
		public List<SpaceImportContext> spaceImport() {
			return getRuleContexts(SpaceImportContext.class);
		}
		public SpaceImportContext spaceImport(int i) {
			return getRuleContext(SpaceImportContext.class,i);
		}
		public List<SpaceIncludeContext> spaceInclude() {
			return getRuleContexts(SpaceIncludeContext.class);
		}
		public SpaceIncludeContext spaceInclude(int i) {
			return getRuleContext(SpaceIncludeContext.class,i);
		}
		public List<OutputSpecContext> outputSpec() {
			return getRuleContexts(OutputSpecContext.class);
		}
		public OutputSpecContext outputSpec(int i) {
			return getRuleContext(OutputSpecContext.class,i);
		}
		public List<ProtocLanguageContext> protocLanguage() {
			return getRuleContexts(ProtocLanguageContext.class);
		}
		public ProtocLanguageContext protocLanguage(int i) {
			return getRuleContext(ProtocLanguageContext.class,i);
		}
		public ProtocBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_protocBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitProtocBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProtocBodyContext protocBody() throws RecognitionException {
		ProtocBodyContext _localctx = new ProtocBodyContext(_ctx, getState());
		enterRule(_localctx, 390, RULE_protocBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2034);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (OUTPUT - 65)) | (1L << (IMPORT - 65)) | (1L << (LANGUAGE - 65)))) != 0) || _la==INCLUDE) {
				{
				setState(2032);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case IMPORT:
					{
					setState(2028);
					spaceImport();
					}
					break;
				case INCLUDE:
					{
					setState(2029);
					spaceInclude();
					}
					break;
				case OUTPUT:
					{
					setState(2030);
					outputSpec();
					}
					break;
				case LANGUAGE:
					{
					setState(2031);
					protocLanguage();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(2036);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProtocLanguageContext extends ParserRuleContext {
		public TerminalNode LANGUAGE() { return getToken(EntityLanguageParser.LANGUAGE, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public ProtocLanguageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_protocLanguage; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitProtocLanguage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProtocLanguageContext protocLanguage() throws RecognitionException {
		ProtocLanguageContext _localctx = new ProtocLanguageContext(_ctx, getState());
		enterRule(_localctx, 392, RULE_protocLanguage);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2037);
			match(LANGUAGE);
			setState(2038);
			id();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode INT32_TYPE() { return getToken(EntityLanguageParser.INT32_TYPE, 0); }
		public TerminalNode INT64_TYPE() { return getToken(EntityLanguageParser.INT64_TYPE, 0); }
		public TerminalNode FLOAT_TYPE() { return getToken(EntityLanguageParser.FLOAT_TYPE, 0); }
		public TerminalNode DOUBLE_TYPE() { return getToken(EntityLanguageParser.DOUBLE_TYPE, 0); }
		public TerminalNode BOOLEAN_TYPE() { return getToken(EntityLanguageParser.BOOLEAN_TYPE, 0); }
		public TerminalNode DATE_TYPE() { return getToken(EntityLanguageParser.DATE_TYPE, 0); }
		public TerminalNode STRING_TYPE() { return getToken(EntityLanguageParser.STRING_TYPE, 0); }
		public TerminalNode UUID_TYPE() { return getToken(EntityLanguageParser.UUID_TYPE, 0); }
		public TerminalNode ASSET_TYPE() { return getToken(EntityLanguageParser.ASSET_TYPE, 0); }
		public TerminalNode BYTE_TYPE() { return getToken(EntityLanguageParser.BYTE_TYPE, 0); }
		public TerminalNode INTEGER() { return getToken(EntityLanguageParser.INTEGER, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 394, RULE_type);
		int _la;
		try {
			setState(2056);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,217,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2040);
				match(INT32_TYPE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2041);
				match(INT64_TYPE);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(2042);
				match(FLOAT_TYPE);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(2043);
				match(DOUBLE_TYPE);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(2044);
				match(BOOLEAN_TYPE);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(2045);
				match(DATE_TYPE);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(2046);
				match(STRING_TYPE);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(2047);
				match(UUID_TYPE);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(2048);
				match(ASSET_TYPE);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(2049);
				match(BYTE_TYPE);
				setState(2050);
				match(T__5);
				setState(2052);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==INTEGER) {
					{
					setState(2051);
					match(INTEGER);
					}
				}

				setState(2054);
				match(T__6);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(2055);
				id();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttributeQualifierContext extends ParserRuleContext {
		public TerminalNode UNIQUE() { return getToken(EntityLanguageParser.UNIQUE, 0); }
		public TerminalNode OPTIONAL() { return getToken(EntityLanguageParser.OPTIONAL, 0); }
		public TerminalNode CREATION() { return getToken(EntityLanguageParser.CREATION, 0); }
		public TerminalNode MODIFICATION() { return getToken(EntityLanguageParser.MODIFICATION, 0); }
		public TerminalNode MANY() { return getToken(EntityLanguageParser.MANY, 0); }
		public TerminalNode ORDERED() { return getToken(EntityLanguageParser.ORDERED, 0); }
		public TerminalNode PARENT() { return getToken(EntityLanguageParser.PARENT, 0); }
		public TerminalNode SEQUENTIAL() { return getToken(EntityLanguageParser.SEQUENTIAL, 0); }
		public TerminalNode VIRTUAL() { return getToken(EntityLanguageParser.VIRTUAL, 0); }
		public AttributeQualifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributeQualifier; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitAttributeQualifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttributeQualifierContext attributeQualifier() throws RecognitionException {
		AttributeQualifierContext _localctx = new AttributeQualifierContext(_ctx, getState());
		enterRule(_localctx, 396, RULE_attributeQualifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2058);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << UNIQUE) | (1L << CREATION) | (1L << MODIFICATION) | (1L << OPTIONAL) | (1L << PARENT) | (1L << ORDERED) | (1L << SEQUENTIAL) | (1L << VIRTUAL) | (1L << MANY))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnitsContext extends ParserRuleContext {
		public TerminalNode UNITS() { return getToken(EntityLanguageParser.UNITS, 0); }
		public UnitsBodyContext unitsBody() {
			return getRuleContext(UnitsBodyContext.class,0);
		}
		public UnitsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_units; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitUnits(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnitsContext units() throws RecognitionException {
		UnitsContext _localctx = new UnitsContext(_ctx, getState());
		enterRule(_localctx, 398, RULE_units);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2060);
			match(UNITS);
			setState(2061);
			match(T__28);
			setState(2062);
			unitsBody();
			setState(2063);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnitsBodyContext extends ParserRuleContext {
		public List<UnitDefinitionContext> unitDefinition() {
			return getRuleContexts(UnitDefinitionContext.class);
		}
		public UnitDefinitionContext unitDefinition(int i) {
			return getRuleContext(UnitDefinitionContext.class,i);
		}
		public UnitsBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unitsBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitUnitsBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnitsBodyContext unitsBody() throws RecognitionException {
		UnitsBodyContext _localctx = new UnitsBodyContext(_ctx, getState());
		enterRule(_localctx, 400, RULE_unitsBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2068);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MODULE) | (1L << ENTITY) | (1L << PRIMARYKEY) | (1L << ATTRIBUTE) | (1L << UNIQUE) | (1L << CREATION) | (1L << MODIFICATION) | (1L << PARENT) | (1L << ORDERED) | (1L << SEQUENTIAL) | (1L << EXTERN) | (1L << TRANSIENT) | (1L << PRIMARY) | (1L << SECONDARY) | (1L << FLATTEN) | (1L << RELATIONSHIPS) | (1L << RELATIONSHIP) | (1L << ENTITIES) | (1L << ENUM) | (1L << ENUMITEM) | (1L << TYPEDEF) | (1L << UNUSED) | (1L << CONFIGURATION) | (1L << FORMATTING))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (FORMAT - 64)) | (1L << (OUTPUT - 64)) | (1L << (DIRECTORY - 64)) | (1L << (TRANSFORM - 64)) | (1L << (TEMPLATE - 64)) | (1L << (PATH - 64)) | (1L << (ENDPOINT - 64)) | (1L << (INHERITED - 64)) | (1L << (REPOSITORY - 64)) | (1L << (TAG - 64)) | (1L << (VALUE - 64)) | (1L << (SPACE - 64)) | (1L << (IMPORT - 64)) | (1L << (FROM - 64)) | (1L << (ORGANIZATION - 64)) | (1L << (NAME - 64)) | (1L << (NULLABLE - 64)) | (1L << (LANGUAGE - 64)) | (1L << (VERSION - 64)) | (1L << (SELF - 64)) | (1L << (OPERATORS - 64)) | (1L << (KEYWORDS - 64)) | (1L << (SHORT - 64)) | (1L << (LONG - 64)) | (1L << (HUMAN - 64)) | (1L << (READABLE - 64)) | (1L << (IDENTIFICATION - 64)) | (1L << (DOMAIN - 64)) | (1L << (ATTRIBUTES - 64)) | (1L << (REPLACES - 64)) | (1L << (PREFIX - 64)) | (1L << (SUFFIX - 64)) | (1L << (RENAME - 64)) | (1L << (TAGGING - 64)) | (1L << (NAMESPACE - 64)) | (1L << (READ - 64)) | (1L << (WRITE - 64)) | (1L << (WHEN - 64)) | (1L << (REQUIRES - 64)) | (1L << (ROLE - 64)) | (1L << (USER - 64)) | (1L << (IF - 64)) | (1L << (APPLY - 64)))) != 0) || ((((_la - 130)) & ~0x3f) == 0 && ((1L << (_la - 130)) & ((1L << (DEFAULT - 130)) | (1L << (VIEW - 130)) | (1L << (INCLUDE - 130)) | (1L << (EXCLUDE - 130)) | (1L << (AND - 130)) | (1L << (ABSTRACT - 130)) | (1L << (EXTENDS - 130)) | (1L << (INTERFACE - 130)) | (1L << (CONFIG - 130)) | (1L << (METADATA - 130)) | (1L << (CONTEXT - 130)) | (1L << (ARGUMENT - 130)) | (1L << (OPERATION - 130)) | (1L << (QUERY - 130)) | (1L << (PARAM - 130)) | (1L << (STATUS - 130)) | (1L << (CUSTOM - 130)) | (1L << (TYPE - 130)) | (1L << (TO - 130)) | (1L << (PREPEND - 130)) | (1L << (APPEND - 130)) | (1L << (BOOLEAN_TYPE - 130)) | (1L << (INT32_TYPE - 130)) | (1L << (INT64_TYPE - 130)) | (1L << (BYTE_TYPE - 130)) | (1L << (UUID_TYPE - 130)) | (1L << (FLOAT_TYPE - 130)) | (1L << (DOUBLE_TYPE - 130)) | (1L << (STRING_TYPE - 130)) | (1L << (DATE_TYPE - 130)) | (1L << (ASSET_TYPE - 130)) | (1L << (ARRAY - 130)) | (1L << (DESCRIPTION - 130)) | (1L << (TAGS - 130)) | (1L << (MACRO_START - 130)) | (1L << (ID - 130)))) != 0)) {
				{
				{
				setState(2065);
				unitDefinition();
				}
				}
				setState(2070);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnitDefinitionContext extends ParserRuleContext {
		public List<IdContext> id() {
			return getRuleContexts(IdContext.class);
		}
		public IdContext id(int i) {
			return getRuleContext(IdContext.class,i);
		}
		public UnitDefinitionBodyContext unitDefinitionBody() {
			return getRuleContext(UnitDefinitionBodyContext.class,0);
		}
		public TerminalNode EXTENDS() { return getToken(EntityLanguageParser.EXTENDS, 0); }
		public UnitDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unitDefinition; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitUnitDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnitDefinitionContext unitDefinition() throws RecognitionException {
		UnitDefinitionContext _localctx = new UnitDefinitionContext(_ctx, getState());
		enterRule(_localctx, 402, RULE_unitDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2071);
			id();
			setState(2074);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(2072);
				match(EXTENDS);
				setState(2073);
				id();
				}
			}

			setState(2076);
			match(T__28);
			setState(2077);
			unitDefinitionBody();
			setState(2078);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnitDefinitionBodyContext extends ParserRuleContext {
		public List<DescriptionStatementContext> descriptionStatement() {
			return getRuleContexts(DescriptionStatementContext.class);
		}
		public DescriptionStatementContext descriptionStatement(int i) {
			return getRuleContext(DescriptionStatementContext.class,i);
		}
		public List<UnitDefinitionFieldContext> unitDefinitionField() {
			return getRuleContexts(UnitDefinitionFieldContext.class);
		}
		public UnitDefinitionFieldContext unitDefinitionField(int i) {
			return getRuleContext(UnitDefinitionFieldContext.class,i);
		}
		public UnitDefinitionBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unitDefinitionBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitUnitDefinitionBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnitDefinitionBodyContext unitDefinitionBody() throws RecognitionException {
		UnitDefinitionBodyContext _localctx = new UnitDefinitionBodyContext(_ctx, getState());
		enterRule(_localctx, 404, RULE_unitDefinitionBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2084);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 127)) & ~0x3f) == 0 && ((1L << (_la - 127)) & ((1L << (ABBR - 127)) | (1L << (MULTIPLIER - 127)) | (1L << (DESCRIPTION - 127)))) != 0)) {
				{
				setState(2082);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case DESCRIPTION:
					{
					setState(2080);
					descriptionStatement();
					}
					break;
				case ABBR:
				case MULTIPLIER:
					{
					setState(2081);
					unitDefinitionField();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(2086);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnitDefinitionFieldContext extends ParserRuleContext {
		public TerminalNode ABBR() { return getToken(EntityLanguageParser.ABBR, 0); }
		public TerminalNode STRING() { return getToken(EntityLanguageParser.STRING, 0); }
		public TerminalNode MULTIPLIER() { return getToken(EntityLanguageParser.MULTIPLIER, 0); }
		public TerminalNode FLOAT() { return getToken(EntityLanguageParser.FLOAT, 0); }
		public UnitDefinitionFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unitDefinitionField; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitUnitDefinitionField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnitDefinitionFieldContext unitDefinitionField() throws RecognitionException {
		UnitDefinitionFieldContext _localctx = new UnitDefinitionFieldContext(_ctx, getState());
		enterRule(_localctx, 406, RULE_unitDefinitionField);
		try {
			setState(2091);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ABBR:
				enterOuterAlt(_localctx, 1);
				{
				setState(2087);
				match(ABBR);
				setState(2088);
				match(STRING);
				}
				break;
			case MULTIPLIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(2089);
				match(MULTIPLIER);
				setState(2090);
				match(FLOAT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LanguageContext extends ParserRuleContext {
		public TerminalNode LANGUAGE() { return getToken(EntityLanguageParser.LANGUAGE, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public List<LanguageBodyContext> languageBody() {
			return getRuleContexts(LanguageBodyContext.class);
		}
		public LanguageBodyContext languageBody(int i) {
			return getRuleContext(LanguageBodyContext.class,i);
		}
		public LanguageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_language; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitLanguage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LanguageContext language() throws RecognitionException {
		LanguageContext _localctx = new LanguageContext(_ctx, getState());
		enterRule(_localctx, 408, RULE_language);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2093);
			match(LANGUAGE);
			setState(2094);
			id();
			setState(2095);
			match(T__28);
			setState(2099);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 86)) & ~0x3f) == 0 && ((1L << (_la - 86)) & ((1L << (TYPES - 86)) | (1L << (SELF - 86)) | (1L << (COMMENTS - 86)) | (1L << (OPERATORS - 86)) | (1L << (KEYWORDS - 86)) | (1L << (FUNCTIONS - 86)))) != 0)) {
				{
				{
				setState(2096);
				languageBody();
				}
				}
				setState(2101);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(2102);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LanguageBodyContext extends ParserRuleContext {
		public LanguageBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_languageBody; }
	 
		public LanguageBodyContext() { }
		public void copyFrom(LanguageBodyContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class LanguageFunctionsContext extends LanguageBodyContext {
		public TerminalNode FUNCTIONS() { return getToken(EntityLanguageParser.FUNCTIONS, 0); }
		public List<FunctionsBodyContext> functionsBody() {
			return getRuleContexts(FunctionsBodyContext.class);
		}
		public FunctionsBodyContext functionsBody(int i) {
			return getRuleContext(FunctionsBodyContext.class,i);
		}
		public LanguageFunctionsContext(LanguageBodyContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitLanguageFunctions(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LanguageSelfContext extends LanguageBodyContext {
		public TerminalNode SELF() { return getToken(EntityLanguageParser.SELF, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public LanguageSelfContext(LanguageBodyContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitLanguageSelf(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LanguageTypesContext extends LanguageBodyContext {
		public TerminalNode TYPES() { return getToken(EntityLanguageParser.TYPES, 0); }
		public TypesBodyContext typesBody() {
			return getRuleContext(TypesBodyContext.class,0);
		}
		public LanguageTypesContext(LanguageBodyContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitLanguageTypes(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LanguageOperatorsContext extends LanguageBodyContext {
		public TerminalNode OPERATORS() { return getToken(EntityLanguageParser.OPERATORS, 0); }
		public List<OperatorsBodyContext> operatorsBody() {
			return getRuleContexts(OperatorsBodyContext.class);
		}
		public OperatorsBodyContext operatorsBody(int i) {
			return getRuleContext(OperatorsBodyContext.class,i);
		}
		public LanguageOperatorsContext(LanguageBodyContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitLanguageOperators(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LanguageCommentsContext extends LanguageBodyContext {
		public TerminalNode COMMENTS() { return getToken(EntityLanguageParser.COMMENTS, 0); }
		public List<CommentsBodyContext> commentsBody() {
			return getRuleContexts(CommentsBodyContext.class);
		}
		public CommentsBodyContext commentsBody(int i) {
			return getRuleContext(CommentsBodyContext.class,i);
		}
		public LanguageCommentsContext(LanguageBodyContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitLanguageComments(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LanguageKeywordsContext extends LanguageBodyContext {
		public TerminalNode KEYWORDS() { return getToken(EntityLanguageParser.KEYWORDS, 0); }
		public List<IdContext> id() {
			return getRuleContexts(IdContext.class);
		}
		public IdContext id(int i) {
			return getRuleContext(IdContext.class,i);
		}
		public LanguageKeywordsContext(LanguageBodyContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitLanguageKeywords(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LanguageBodyContext languageBody() throws RecognitionException {
		LanguageBodyContext _localctx = new LanguageBodyContext(_ctx, getState());
		enterRule(_localctx, 410, RULE_languageBody);
		int _la;
		try {
			setState(2147);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYPES:
				_localctx = new LanguageTypesContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(2104);
				match(TYPES);
				setState(2105);
				match(T__28);
				setState(2106);
				typesBody();
				setState(2107);
				match(T__29);
				}
				}
				break;
			case SELF:
				_localctx = new LanguageSelfContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(2109);
				match(SELF);
				setState(2110);
				id();
				}
				}
				break;
			case COMMENTS:
				_localctx = new LanguageCommentsContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(2111);
				match(COMMENTS);
				setState(2112);
				match(T__28);
				setState(2116);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((((_la - 90)) & ~0x3f) == 0 && ((1L << (_la - 90)) & ((1L << (LINE - 90)) | (1L << (BLOCK_START - 90)) | (1L << (BLOCK_END - 90)))) != 0)) {
					{
					{
					setState(2113);
					commentsBody();
					}
					}
					setState(2118);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(2119);
				match(T__29);
				}
				}
				break;
			case OPERATORS:
				_localctx = new LanguageOperatorsContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				{
				setState(2120);
				match(OPERATORS);
				setState(2121);
				match(T__28);
				setState(2125);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MODULE) | (1L << ENTITY) | (1L << PRIMARYKEY) | (1L << ATTRIBUTE) | (1L << UNIQUE) | (1L << CREATION) | (1L << MODIFICATION) | (1L << PARENT) | (1L << ORDERED) | (1L << SEQUENTIAL) | (1L << EXTERN) | (1L << TRANSIENT) | (1L << PRIMARY) | (1L << SECONDARY) | (1L << FLATTEN) | (1L << RELATIONSHIPS) | (1L << RELATIONSHIP) | (1L << ENTITIES) | (1L << ENUM) | (1L << ENUMITEM) | (1L << TYPEDEF) | (1L << UNUSED) | (1L << CONFIGURATION) | (1L << FORMATTING))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (FORMAT - 64)) | (1L << (OUTPUT - 64)) | (1L << (DIRECTORY - 64)) | (1L << (TRANSFORM - 64)) | (1L << (TEMPLATE - 64)) | (1L << (PATH - 64)) | (1L << (ENDPOINT - 64)) | (1L << (INHERITED - 64)) | (1L << (REPOSITORY - 64)) | (1L << (TAG - 64)) | (1L << (VALUE - 64)) | (1L << (SPACE - 64)) | (1L << (IMPORT - 64)) | (1L << (FROM - 64)) | (1L << (ORGANIZATION - 64)) | (1L << (NAME - 64)) | (1L << (NULLABLE - 64)) | (1L << (LANGUAGE - 64)) | (1L << (VERSION - 64)) | (1L << (SELF - 64)) | (1L << (OPERATORS - 64)) | (1L << (KEYWORDS - 64)) | (1L << (SHORT - 64)) | (1L << (LONG - 64)) | (1L << (HUMAN - 64)) | (1L << (READABLE - 64)) | (1L << (IDENTIFICATION - 64)) | (1L << (DOMAIN - 64)) | (1L << (ATTRIBUTES - 64)) | (1L << (REPLACES - 64)) | (1L << (PREFIX - 64)) | (1L << (SUFFIX - 64)) | (1L << (RENAME - 64)) | (1L << (TAGGING - 64)) | (1L << (NAMESPACE - 64)) | (1L << (READ - 64)) | (1L << (WRITE - 64)) | (1L << (WHEN - 64)) | (1L << (REQUIRES - 64)) | (1L << (ROLE - 64)) | (1L << (USER - 64)) | (1L << (IF - 64)) | (1L << (APPLY - 64)))) != 0) || ((((_la - 130)) & ~0x3f) == 0 && ((1L << (_la - 130)) & ((1L << (DEFAULT - 130)) | (1L << (VIEW - 130)) | (1L << (INCLUDE - 130)) | (1L << (EXCLUDE - 130)) | (1L << (AND - 130)) | (1L << (ABSTRACT - 130)) | (1L << (EXTENDS - 130)) | (1L << (INTERFACE - 130)) | (1L << (CONFIG - 130)) | (1L << (METADATA - 130)) | (1L << (CONTEXT - 130)) | (1L << (ARGUMENT - 130)) | (1L << (OPERATION - 130)) | (1L << (QUERY - 130)) | (1L << (PARAM - 130)) | (1L << (STATUS - 130)) | (1L << (CUSTOM - 130)) | (1L << (TYPE - 130)) | (1L << (TO - 130)) | (1L << (PREPEND - 130)) | (1L << (APPEND - 130)) | (1L << (BOOLEAN_TYPE - 130)) | (1L << (INT32_TYPE - 130)) | (1L << (INT64_TYPE - 130)) | (1L << (BYTE_TYPE - 130)) | (1L << (UUID_TYPE - 130)) | (1L << (FLOAT_TYPE - 130)) | (1L << (DOUBLE_TYPE - 130)) | (1L << (STRING_TYPE - 130)) | (1L << (DATE_TYPE - 130)) | (1L << (ASSET_TYPE - 130)) | (1L << (ARRAY - 130)) | (1L << (DESCRIPTION - 130)) | (1L << (TAGS - 130)) | (1L << (MACRO_START - 130)) | (1L << (ID - 130)))) != 0)) {
					{
					{
					setState(2122);
					operatorsBody();
					}
					}
					setState(2127);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(2128);
				match(T__29);
				}
				}
				break;
			case KEYWORDS:
				_localctx = new LanguageKeywordsContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				{
				setState(2129);
				match(KEYWORDS);
				setState(2130);
				match(T__28);
				setState(2134);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MODULE) | (1L << ENTITY) | (1L << PRIMARYKEY) | (1L << ATTRIBUTE) | (1L << UNIQUE) | (1L << CREATION) | (1L << MODIFICATION) | (1L << PARENT) | (1L << ORDERED) | (1L << SEQUENTIAL) | (1L << EXTERN) | (1L << TRANSIENT) | (1L << PRIMARY) | (1L << SECONDARY) | (1L << FLATTEN) | (1L << RELATIONSHIPS) | (1L << RELATIONSHIP) | (1L << ENTITIES) | (1L << ENUM) | (1L << ENUMITEM) | (1L << TYPEDEF) | (1L << UNUSED) | (1L << CONFIGURATION) | (1L << FORMATTING))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (FORMAT - 64)) | (1L << (OUTPUT - 64)) | (1L << (DIRECTORY - 64)) | (1L << (TRANSFORM - 64)) | (1L << (TEMPLATE - 64)) | (1L << (PATH - 64)) | (1L << (ENDPOINT - 64)) | (1L << (INHERITED - 64)) | (1L << (REPOSITORY - 64)) | (1L << (TAG - 64)) | (1L << (VALUE - 64)) | (1L << (SPACE - 64)) | (1L << (IMPORT - 64)) | (1L << (FROM - 64)) | (1L << (ORGANIZATION - 64)) | (1L << (NAME - 64)) | (1L << (NULLABLE - 64)) | (1L << (LANGUAGE - 64)) | (1L << (VERSION - 64)) | (1L << (SELF - 64)) | (1L << (OPERATORS - 64)) | (1L << (KEYWORDS - 64)) | (1L << (SHORT - 64)) | (1L << (LONG - 64)) | (1L << (HUMAN - 64)) | (1L << (READABLE - 64)) | (1L << (IDENTIFICATION - 64)) | (1L << (DOMAIN - 64)) | (1L << (ATTRIBUTES - 64)) | (1L << (REPLACES - 64)) | (1L << (PREFIX - 64)) | (1L << (SUFFIX - 64)) | (1L << (RENAME - 64)) | (1L << (TAGGING - 64)) | (1L << (NAMESPACE - 64)) | (1L << (READ - 64)) | (1L << (WRITE - 64)) | (1L << (WHEN - 64)) | (1L << (REQUIRES - 64)) | (1L << (ROLE - 64)) | (1L << (USER - 64)) | (1L << (IF - 64)) | (1L << (APPLY - 64)))) != 0) || ((((_la - 130)) & ~0x3f) == 0 && ((1L << (_la - 130)) & ((1L << (DEFAULT - 130)) | (1L << (VIEW - 130)) | (1L << (INCLUDE - 130)) | (1L << (EXCLUDE - 130)) | (1L << (AND - 130)) | (1L << (ABSTRACT - 130)) | (1L << (EXTENDS - 130)) | (1L << (INTERFACE - 130)) | (1L << (CONFIG - 130)) | (1L << (METADATA - 130)) | (1L << (CONTEXT - 130)) | (1L << (ARGUMENT - 130)) | (1L << (OPERATION - 130)) | (1L << (QUERY - 130)) | (1L << (PARAM - 130)) | (1L << (STATUS - 130)) | (1L << (CUSTOM - 130)) | (1L << (TYPE - 130)) | (1L << (TO - 130)) | (1L << (PREPEND - 130)) | (1L << (APPEND - 130)) | (1L << (BOOLEAN_TYPE - 130)) | (1L << (INT32_TYPE - 130)) | (1L << (INT64_TYPE - 130)) | (1L << (BYTE_TYPE - 130)) | (1L << (UUID_TYPE - 130)) | (1L << (FLOAT_TYPE - 130)) | (1L << (DOUBLE_TYPE - 130)) | (1L << (STRING_TYPE - 130)) | (1L << (DATE_TYPE - 130)) | (1L << (ASSET_TYPE - 130)) | (1L << (ARRAY - 130)) | (1L << (DESCRIPTION - 130)) | (1L << (TAGS - 130)) | (1L << (MACRO_START - 130)) | (1L << (ID - 130)))) != 0)) {
					{
					{
					setState(2131);
					id();
					}
					}
					setState(2136);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(2137);
				match(T__29);
				}
				}
				break;
			case FUNCTIONS:
				_localctx = new LanguageFunctionsContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				{
				setState(2138);
				match(FUNCTIONS);
				setState(2139);
				match(T__28);
				setState(2143);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MODULE) | (1L << ENTITY) | (1L << PRIMARYKEY) | (1L << ATTRIBUTE) | (1L << UNIQUE) | (1L << CREATION) | (1L << MODIFICATION) | (1L << PARENT) | (1L << ORDERED) | (1L << SEQUENTIAL) | (1L << EXTERN) | (1L << TRANSIENT) | (1L << PRIMARY) | (1L << SECONDARY) | (1L << FLATTEN) | (1L << RELATIONSHIPS) | (1L << RELATIONSHIP) | (1L << ENTITIES) | (1L << ENUM) | (1L << ENUMITEM) | (1L << TYPEDEF) | (1L << UNUSED) | (1L << CONFIGURATION) | (1L << FORMATTING))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (FORMAT - 64)) | (1L << (OUTPUT - 64)) | (1L << (DIRECTORY - 64)) | (1L << (TRANSFORM - 64)) | (1L << (TEMPLATE - 64)) | (1L << (PATH - 64)) | (1L << (ENDPOINT - 64)) | (1L << (INHERITED - 64)) | (1L << (REPOSITORY - 64)) | (1L << (TAG - 64)) | (1L << (VALUE - 64)) | (1L << (SPACE - 64)) | (1L << (IMPORT - 64)) | (1L << (FROM - 64)) | (1L << (ORGANIZATION - 64)) | (1L << (NAME - 64)) | (1L << (NULLABLE - 64)) | (1L << (LANGUAGE - 64)) | (1L << (VERSION - 64)) | (1L << (SELF - 64)) | (1L << (OPERATORS - 64)) | (1L << (KEYWORDS - 64)) | (1L << (SHORT - 64)) | (1L << (LONG - 64)) | (1L << (HUMAN - 64)) | (1L << (READABLE - 64)) | (1L << (IDENTIFICATION - 64)) | (1L << (DOMAIN - 64)) | (1L << (ATTRIBUTES - 64)) | (1L << (REPLACES - 64)) | (1L << (PREFIX - 64)) | (1L << (SUFFIX - 64)) | (1L << (RENAME - 64)) | (1L << (TAGGING - 64)) | (1L << (NAMESPACE - 64)) | (1L << (READ - 64)) | (1L << (WRITE - 64)) | (1L << (WHEN - 64)) | (1L << (REQUIRES - 64)) | (1L << (ROLE - 64)) | (1L << (USER - 64)) | (1L << (IF - 64)) | (1L << (APPLY - 64)))) != 0) || ((((_la - 130)) & ~0x3f) == 0 && ((1L << (_la - 130)) & ((1L << (DEFAULT - 130)) | (1L << (VIEW - 130)) | (1L << (INCLUDE - 130)) | (1L << (EXCLUDE - 130)) | (1L << (AND - 130)) | (1L << (ABSTRACT - 130)) | (1L << (EXTENDS - 130)) | (1L << (INTERFACE - 130)) | (1L << (CONFIG - 130)) | (1L << (METADATA - 130)) | (1L << (CONTEXT - 130)) | (1L << (ARGUMENT - 130)) | (1L << (OPERATION - 130)) | (1L << (QUERY - 130)) | (1L << (PARAM - 130)) | (1L << (STATUS - 130)) | (1L << (CUSTOM - 130)) | (1L << (TYPE - 130)) | (1L << (TO - 130)) | (1L << (PREPEND - 130)) | (1L << (APPEND - 130)) | (1L << (BOOLEAN_TYPE - 130)) | (1L << (INT32_TYPE - 130)) | (1L << (INT64_TYPE - 130)) | (1L << (BYTE_TYPE - 130)) | (1L << (UUID_TYPE - 130)) | (1L << (FLOAT_TYPE - 130)) | (1L << (DOUBLE_TYPE - 130)) | (1L << (STRING_TYPE - 130)) | (1L << (DATE_TYPE - 130)) | (1L << (ASSET_TYPE - 130)) | (1L << (ARRAY - 130)) | (1L << (DESCRIPTION - 130)) | (1L << (TAGS - 130)) | (1L << (MACRO_START - 130)) | (1L << (ID - 130)))) != 0)) {
					{
					{
					setState(2140);
					functionsBody();
					}
					}
					setState(2145);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(2146);
				match(T__29);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypesBodyContext extends ParserRuleContext {
		public List<LanguageTypeContext> languageType() {
			return getRuleContexts(LanguageTypeContext.class);
		}
		public LanguageTypeContext languageType(int i) {
			return getRuleContext(LanguageTypeContext.class,i);
		}
		public TypesBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typesBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitTypesBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypesBodyContext typesBody() throws RecognitionException {
		TypesBodyContext _localctx = new TypesBodyContext(_ctx, getState());
		enterRule(_localctx, 412, RULE_typesBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2152);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MODULE) | (1L << ENTITY) | (1L << PRIMARYKEY) | (1L << ATTRIBUTE) | (1L << UNIQUE) | (1L << CREATION) | (1L << MODIFICATION) | (1L << PARENT) | (1L << ORDERED) | (1L << SEQUENTIAL) | (1L << EXTERN) | (1L << TRANSIENT) | (1L << PRIMARY) | (1L << SECONDARY) | (1L << FLATTEN) | (1L << RELATIONSHIPS) | (1L << RELATIONSHIP) | (1L << ENTITIES) | (1L << ENUM) | (1L << ENUMITEM) | (1L << TYPEDEF) | (1L << UNUSED) | (1L << CONFIGURATION) | (1L << FORMATTING))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (FORMAT - 64)) | (1L << (OUTPUT - 64)) | (1L << (DIRECTORY - 64)) | (1L << (TRANSFORM - 64)) | (1L << (TEMPLATE - 64)) | (1L << (PATH - 64)) | (1L << (ENDPOINT - 64)) | (1L << (INHERITED - 64)) | (1L << (REPOSITORY - 64)) | (1L << (TAG - 64)) | (1L << (VALUE - 64)) | (1L << (SPACE - 64)) | (1L << (IMPORT - 64)) | (1L << (FROM - 64)) | (1L << (ORGANIZATION - 64)) | (1L << (NAME - 64)) | (1L << (NULLABLE - 64)) | (1L << (LANGUAGE - 64)) | (1L << (VERSION - 64)) | (1L << (SELF - 64)) | (1L << (OPERATORS - 64)) | (1L << (KEYWORDS - 64)) | (1L << (SHORT - 64)) | (1L << (LONG - 64)) | (1L << (HUMAN - 64)) | (1L << (READABLE - 64)) | (1L << (IDENTIFICATION - 64)) | (1L << (DOMAIN - 64)) | (1L << (ATTRIBUTES - 64)) | (1L << (REPLACES - 64)) | (1L << (PREFIX - 64)) | (1L << (SUFFIX - 64)) | (1L << (RENAME - 64)) | (1L << (TAGGING - 64)) | (1L << (NAMESPACE - 64)) | (1L << (READ - 64)) | (1L << (WRITE - 64)) | (1L << (WHEN - 64)) | (1L << (REQUIRES - 64)) | (1L << (ROLE - 64)) | (1L << (USER - 64)) | (1L << (IF - 64)) | (1L << (APPLY - 64)))) != 0) || ((((_la - 130)) & ~0x3f) == 0 && ((1L << (_la - 130)) & ((1L << (DEFAULT - 130)) | (1L << (VIEW - 130)) | (1L << (INCLUDE - 130)) | (1L << (EXCLUDE - 130)) | (1L << (AND - 130)) | (1L << (ABSTRACT - 130)) | (1L << (EXTENDS - 130)) | (1L << (INTERFACE - 130)) | (1L << (CONFIG - 130)) | (1L << (METADATA - 130)) | (1L << (CONTEXT - 130)) | (1L << (ARGUMENT - 130)) | (1L << (OPERATION - 130)) | (1L << (QUERY - 130)) | (1L << (PARAM - 130)) | (1L << (STATUS - 130)) | (1L << (CUSTOM - 130)) | (1L << (TYPE - 130)) | (1L << (TO - 130)) | (1L << (PREPEND - 130)) | (1L << (APPEND - 130)) | (1L << (BOOLEAN_TYPE - 130)) | (1L << (INT32_TYPE - 130)) | (1L << (INT64_TYPE - 130)) | (1L << (BYTE_TYPE - 130)) | (1L << (UUID_TYPE - 130)) | (1L << (FLOAT_TYPE - 130)) | (1L << (DOUBLE_TYPE - 130)) | (1L << (STRING_TYPE - 130)) | (1L << (DATE_TYPE - 130)) | (1L << (ASSET_TYPE - 130)) | (1L << (ARRAY - 130)) | (1L << (DESCRIPTION - 130)) | (1L << (TAGS - 130)) | (1L << (MACRO_START - 130)) | (1L << (ID - 130)))) != 0)) {
				{
				{
				setState(2149);
				languageType();
				}
				}
				setState(2154);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LanguageTypeContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TerminalNode REF() { return getToken(EntityLanguageParser.REF, 0); }
		public TerminalNode NULLABLE() { return getToken(EntityLanguageParser.NULLABLE, 0); }
		public TerminalNode STRING() { return getToken(EntityLanguageParser.STRING, 0); }
		public LanguageTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_languageType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitLanguageType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LanguageTypeContext languageType() throws RecognitionException {
		LanguageTypeContext _localctx = new LanguageTypeContext(_ctx, getState());
		enterRule(_localctx, 414, RULE_languageType);
		int _la;
		try {
			setState(2168);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,233,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2155);
				type();
				setState(2156);
				id();
				setState(2158);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==REF) {
					{
					setState(2157);
					match(REF);
					}
				}

				setState(2161);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,231,_ctx) ) {
				case 1:
					{
					setState(2160);
					match(NULLABLE);
					}
					break;
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2163);
				type();
				setState(2164);
				match(STRING);
				setState(2166);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,232,_ctx) ) {
				case 1:
					{
					setState(2165);
					match(NULLABLE);
					}
					break;
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CommentsBodyContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(EntityLanguageParser.STRING, 0); }
		public TerminalNode LINE() { return getToken(EntityLanguageParser.LINE, 0); }
		public TerminalNode BLOCK_START() { return getToken(EntityLanguageParser.BLOCK_START, 0); }
		public TerminalNode BLOCK_END() { return getToken(EntityLanguageParser.BLOCK_END, 0); }
		public CommentsBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_commentsBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitCommentsBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CommentsBodyContext commentsBody() throws RecognitionException {
		CommentsBodyContext _localctx = new CommentsBodyContext(_ctx, getState());
		enterRule(_localctx, 416, RULE_commentsBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2170);
			_la = _input.LA(1);
			if ( !(((((_la - 90)) & ~0x3f) == 0 && ((1L << (_la - 90)) & ((1L << (LINE - 90)) | (1L << (BLOCK_START - 90)) | (1L << (BLOCK_END - 90)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(2171);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionsBodyContext extends ParserRuleContext {
		public List<IdContext> id() {
			return getRuleContexts(IdContext.class);
		}
		public IdContext id(int i) {
			return getRuleContext(IdContext.class,i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public TerminalNode STRING() { return getToken(EntityLanguageParser.STRING, 0); }
		public FunctionsBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionsBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitFunctionsBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionsBodyContext functionsBody() throws RecognitionException {
		FunctionsBodyContext _localctx = new FunctionsBodyContext(_ctx, getState());
		enterRule(_localctx, 418, RULE_functionsBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2173);
			id();
			setState(2174);
			match(T__3);
			setState(2175);
			type();
			setState(2176);
			id();
			setState(2183);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(2177);
				match(T__4);
				setState(2178);
				type();
				setState(2179);
				id();
				}
				}
				setState(2185);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(2186);
			match(T__1);
			setState(2187);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperatorsBodyContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public List<TerminalNode> STRING() { return getTokens(EntityLanguageParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(EntityLanguageParser.STRING, i);
		}
		public OperatorsBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operatorsBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitOperatorsBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperatorsBodyContext operatorsBody() throws RecognitionException {
		OperatorsBodyContext _localctx = new OperatorsBodyContext(_ctx, getState());
		enterRule(_localctx, 420, RULE_operatorsBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2189);
			id();
			setState(2193);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==STRING) {
				{
				{
				setState(2190);
				match(STRING);
				}
				}
				setState(2195);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VersionContext extends ParserRuleContext {
		public TerminalNode VERSION() { return getToken(EntityLanguageParser.VERSION, 0); }
		public TerminalNode VERSIONNUM() { return getToken(EntityLanguageParser.VERSIONNUM, 0); }
		public VersionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_version; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EntityLanguageVisitor ) return ((EntityLanguageVisitor<? extends T>)visitor).visitVersion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VersionContext version() throws RecognitionException {
		VersionContext _localctx = new VersionContext(_ctx, getState());
		enterRule(_localctx, 422, RULE_version);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2196);
			match(VERSION);
			setState(2197);
			match(VERSIONNUM);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 7:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 10);
		case 1:
			return precpred(_ctx, 9);
		case 2:
			return precpred(_ctx, 8);
		case 3:
			return precpred(_ctx, 7);
		case 4:
			return precpred(_ctx, 6);
		case 5:
			return precpred(_ctx, 5);
		case 6:
			return precpred(_ctx, 4);
		case 7:
			return precpred(_ctx, 3);
		case 8:
			return precpred(_ctx, 2);
		case 9:
			return precpred(_ctx, 1);
		case 10:
			return precpred(_ctx, 15);
		case 11:
			return precpred(_ctx, 14);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\u00b9\u089a\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\4T\tT"+
		"\4U\tU\4V\tV\4W\tW\4X\tX\4Y\tY\4Z\tZ\4[\t[\4\\\t\\\4]\t]\4^\t^\4_\t_\4"+
		"`\t`\4a\ta\4b\tb\4c\tc\4d\td\4e\te\4f\tf\4g\tg\4h\th\4i\ti\4j\tj\4k\t"+
		"k\4l\tl\4m\tm\4n\tn\4o\to\4p\tp\4q\tq\4r\tr\4s\ts\4t\tt\4u\tu\4v\tv\4"+
		"w\tw\4x\tx\4y\ty\4z\tz\4{\t{\4|\t|\4}\t}\4~\t~\4\177\t\177\4\u0080\t\u0080"+
		"\4\u0081\t\u0081\4\u0082\t\u0082\4\u0083\t\u0083\4\u0084\t\u0084\4\u0085"+
		"\t\u0085\4\u0086\t\u0086\4\u0087\t\u0087\4\u0088\t\u0088\4\u0089\t\u0089"+
		"\4\u008a\t\u008a\4\u008b\t\u008b\4\u008c\t\u008c\4\u008d\t\u008d\4\u008e"+
		"\t\u008e\4\u008f\t\u008f\4\u0090\t\u0090\4\u0091\t\u0091\4\u0092\t\u0092"+
		"\4\u0093\t\u0093\4\u0094\t\u0094\4\u0095\t\u0095\4\u0096\t\u0096\4\u0097"+
		"\t\u0097\4\u0098\t\u0098\4\u0099\t\u0099\4\u009a\t\u009a\4\u009b\t\u009b"+
		"\4\u009c\t\u009c\4\u009d\t\u009d\4\u009e\t\u009e\4\u009f\t\u009f\4\u00a0"+
		"\t\u00a0\4\u00a1\t\u00a1\4\u00a2\t\u00a2\4\u00a3\t\u00a3\4\u00a4\t\u00a4"+
		"\4\u00a5\t\u00a5\4\u00a6\t\u00a6\4\u00a7\t\u00a7\4\u00a8\t\u00a8\4\u00a9"+
		"\t\u00a9\4\u00aa\t\u00aa\4\u00ab\t\u00ab\4\u00ac\t\u00ac\4\u00ad\t\u00ad"+
		"\4\u00ae\t\u00ae\4\u00af\t\u00af\4\u00b0\t\u00b0\4\u00b1\t\u00b1\4\u00b2"+
		"\t\u00b2\4\u00b3\t\u00b3\4\u00b4\t\u00b4\4\u00b5\t\u00b5\4\u00b6\t\u00b6"+
		"\4\u00b7\t\u00b7\4\u00b8\t\u00b8\4\u00b9\t\u00b9\4\u00ba\t\u00ba\4\u00bb"+
		"\t\u00bb\4\u00bc\t\u00bc\4\u00bd\t\u00bd\4\u00be\t\u00be\4\u00bf\t\u00bf"+
		"\4\u00c0\t\u00c0\4\u00c1\t\u00c1\4\u00c2\t\u00c2\4\u00c3\t\u00c3\4\u00c4"+
		"\t\u00c4\4\u00c5\t\u00c5\4\u00c6\t\u00c6\4\u00c7\t\u00c7\4\u00c8\t\u00c8"+
		"\4\u00c9\t\u00c9\4\u00ca\t\u00ca\4\u00cb\t\u00cb\4\u00cc\t\u00cc\4\u00cd"+
		"\t\u00cd\4\u00ce\t\u00ce\4\u00cf\t\u00cf\4\u00d0\t\u00d0\4\u00d1\t\u00d1"+
		"\4\u00d2\t\u00d2\4\u00d3\t\u00d3\4\u00d4\t\u00d4\4\u00d5\t\u00d5\3\2\3"+
		"\2\5\2\u01ad\n\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\5\4\u01b6\n\4\5\4\u01b8\n"+
		"\4\3\4\3\4\3\5\3\5\3\5\7\5\u01bf\n\5\f\5\16\5\u01c2\13\5\3\6\3\6\3\6\3"+
		"\6\3\7\3\7\3\7\7\7\u01cb\n\7\f\7\16\7\u01ce\13\7\3\b\3\b\3\b\5\b\u01d3"+
		"\n\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u01e3"+
		"\n\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\5\t\u020b\n\t\3\t\3\t\3\t\3\t\3\t\7\t\u0212\n\t\f\t\16"+
		"\t\u0215\13\t\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u021d\n\n\3\13\3\13\3\13\3\13"+
		"\5\13\u0223\n\13\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u0230"+
		"\n\r\3\r\5\r\u0233\n\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\6\16\u0240\n\16\r\16\16\16\u0241\3\17\3\17\3\17\3\17\7\17\u0248"+
		"\n\17\f\17\16\17\u024b\13\17\5\17\u024d\n\17\3\17\3\17\3\20\3\20\3\20"+
		"\3\20\7\20\u0255\n\20\f\20\16\20\u0258\13\20\3\21\3\21\3\21\5\21\u025d"+
		"\n\21\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\6\23"+
		"\u026b\n\23\r\23\16\23\u026c\3\24\3\24\3\24\3\24\3\24\3\25\5\25\u0275"+
		"\n\25\3\25\5\25\u0278\n\25\3\25\5\25\u027b\n\25\3\25\3\25\3\25\5\25\u0280"+
		"\n\25\3\25\3\25\5\25\u0284\n\25\3\26\3\26\3\27\3\27\3\27\3\27\3\27\7\27"+
		"\u028d\n\27\f\27\16\27\u0290\13\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\7\30\u029b\n\30\f\30\16\30\u029e\13\30\3\31\3\31\3\31\3\32"+
		"\3\32\3\32\3\32\3\32\3\33\3\33\7\33\u02aa\n\33\f\33\16\33\u02ad\13\33"+
		"\3\34\3\34\3\34\3\34\3\34\3\35\7\35\u02b5\n\35\f\35\16\35\u02b8\13\35"+
		"\3\36\7\36\u02bb\n\36\f\36\16\36\u02be\13\36\3\36\3\36\3\36\3\36\5\36"+
		"\u02c4\n\36\3\36\3\36\3\36\5\36\u02c9\n\36\5\36\u02cb\n\36\3\36\3\36\3"+
		"\36\3\36\5\36\u02d1\n\36\3\37\3\37\3\37\3\37\3\37\7\37\u02d8\n\37\f\37"+
		"\16\37\u02db\13\37\3 \3 \3 \7 \u02e0\n \f \16 \u02e3\13 \3!\3!\3!\3\""+
		"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\7#\u02f1\n#\f#\16#\u02f4\13#\3$\3$\3$\3"+
		"$\3$\3%\7%\u02fc\n%\f%\16%\u02ff\13%\3&\3&\3&\3&\5&\u0305\n&\3&\3&\3&"+
		"\3&\5&\u030b\n&\3&\5&\u030e\n&\3&\5&\u0311\n&\3&\3&\3&\5&\u0316\n&\3&"+
		"\5&\u0319\n&\3&\5&\u031c\n&\3&\3&\3&\3&\5&\u0322\n&\5&\u0324\n&\3\'\5"+
		"\'\u0327\n\'\3\'\3\'\3(\3(\3(\3(\3(\7(\u0330\n(\f(\16(\u0333\13(\3(\3"+
		"(\3(\3)\3)\3)\3)\3*\3*\3*\3*\3+\3+\7+\u0342\n+\f+\16+\u0345\13+\3,\5,"+
		"\u0348\n,\3,\3,\3,\3,\3,\3,\3-\3-\3-\3-\3-\3-\7-\u0356\n-\f-\16-\u0359"+
		"\13-\3.\3.\3.\3.\3.\3/\3/\7/\u0362\n/\f/\16/\u0365\13/\3\60\3\60\3\60"+
		"\3\60\3\60\3\61\3\61\7\61\u036e\n\61\f\61\16\61\u0371\13\61\3\62\3\62"+
		"\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\5\62\u0380\n\62"+
		"\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\5\63"+
		"\u038f\n\63\3\64\3\64\3\64\5\64\u0394\n\64\3\64\5\64\u0397\n\64\3\64\3"+
		"\64\5\64\u039b\n\64\3\64\3\64\3\64\3\64\5\64\u03a1\n\64\5\64\u03a3\n\64"+
		"\3\64\3\64\3\64\5\64\u03a8\n\64\3\65\3\65\3\65\3\65\5\65\u03ae\n\65\3"+
		"\65\5\65\u03b1\n\65\3\65\3\65\3\65\5\65\u03b6\n\65\3\66\3\66\3\66\7\66"+
		"\u03bb\n\66\f\66\16\66\u03be\13\66\3\67\3\67\3\67\7\67\u03c3\n\67\f\67"+
		"\16\67\u03c6\13\67\38\38\38\38\78\u03cc\n8\f8\168\u03cf\138\38\38\39\5"+
		"9\u03d4\n9\39\39\39\39\39\39\69\u03dc\n9\r9\169\u03dd\39\39\3:\3:\3:\3"+
		":\3:\3:\3:\5:\u03e9\n:\3;\3;\7;\u03ed\n;\f;\16;\u03f0\13;\3<\3<\3<\3<"+
		"\3<\3<\3<\3=\3=\3=\6=\u03fc\n=\r=\16=\u03fd\3>\3>\3>\3>\3>\3>\3>\6>\u0407"+
		"\n>\r>\16>\u0408\3>\3>\5>\u040d\n>\5>\u040f\n>\3?\3?\3?\3?\3@\3@\3@\3"+
		"@\3@\3@\3@\5@\u041c\n@\3@\3@\3@\3@\3A\3A\3A\3A\3A\3A\3A\3A\3A\3A\3A\3"+
		"A\3A\3A\7A\u0430\nA\fA\16A\u0433\13A\3B\3B\3B\7B\u0438\nB\fB\16B\u043b"+
		"\13B\3C\3C\5C\u043f\nC\3C\3C\3D\3D\3D\3D\3E\3E\3E\3E\3E\3E\3F\3F\3F\3"+
		"F\3F\7F\u0452\nF\fF\16F\u0455\13F\3G\3G\3G\3G\3G\3H\3H\7H\u045e\nH\fH"+
		"\16H\u0461\13H\3I\3I\3I\5I\u0466\nI\3I\3I\3J\3J\3J\3J\3J\3J\3K\3K\3K\7"+
		"K\u0473\nK\fK\16K\u0476\13K\3L\3L\3L\3L\7L\u047c\nL\fL\16L\u047f\13L\3"+
		"L\3L\3M\3M\5M\u0485\nM\3M\3M\3M\3M\3M\7M\u048c\nM\fM\16M\u048f\13M\3M"+
		"\3M\3N\3N\3N\3N\3N\7N\u0498\nN\fN\16N\u049b\13N\3N\3N\3O\3O\3P\3P\3P\3"+
		"P\7P\u04a5\nP\fP\16P\u04a8\13P\3P\3P\3P\3P\3Q\5Q\u04af\nQ\3R\3R\3R\3R"+
		"\3R\3R\7R\u04b7\nR\fR\16R\u04ba\13R\3S\3S\3S\3T\3T\3T\3U\3U\3U\3V\3V\3"+
		"V\3W\3W\3W\3X\3X\3X\3Y\5Y\u04cf\nY\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Z\5Z\u04d9\n"+
		"Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3[\3[\3[\3[\3[\3[\3[\3\\\3\\\3\\\7\\\u04ec\n\\"+
		"\f\\\16\\\u04ef\13\\\3]\3]\3]\3]\3^\3^\3^\3_\3_\3_\3_\7_\u04fc\n_\f_\16"+
		"_\u04ff\13_\3_\5_\u0502\n_\3_\3_\3_\3_\5_\u0508\n_\3`\3`\3`\3`\3a\3a\3"+
		"a\3a\7a\u0512\na\fa\16a\u0515\13a\3a\5a\u0518\na\3a\3a\3a\3a\5a\u051e"+
		"\na\3b\3b\3b\3b\3b\3b\3b\3b\5b\u0528\nb\3c\3c\3c\3c\3c\3c\3d\3d\3d\3d"+
		"\7d\u0534\nd\fd\16d\u0537\13d\3e\3e\3f\3f\3f\3f\3f\3f\3f\3f\5f\u0543\n"+
		"f\3f\3f\3f\3f\5f\u0549\nf\3g\3g\3g\3g\3g\3g\3g\3g\7g\u0553\ng\fg\16g\u0556"+
		"\13g\3h\3h\3h\3i\3i\3i\3i\3i\3j\3j\3j\3j\3j\3j\3j\7j\u0567\nj\fj\16j\u056a"+
		"\13j\3k\3k\3k\3l\3l\3l\3m\3m\3m\5m\u0575\nm\3m\3m\3n\3n\3n\3n\3n\3o\7"+
		"o\u057f\no\fo\16o\u0582\13o\3o\3o\3o\3o\5o\u0588\no\3o\3o\3o\3o\3o\3o"+
		"\3o\3o\5o\u0592\no\3p\7p\u0595\np\fp\16p\u0598\13p\3q\3q\3q\3q\3q\3q\7"+
		"q\u05a0\nq\fq\16q\u05a3\13q\3q\3q\3q\3q\3q\5q\u05aa\nq\3r\3r\3r\3r\3r"+
		"\3r\7r\u05b2\nr\fr\16r\u05b5\13r\3r\3r\3r\3r\3r\5r\u05bc\nr\3s\5s\u05bf"+
		"\ns\3s\3s\3s\3s\3s\3s\3s\3t\3t\3t\3t\3t\3u\3u\7u\u05cf\nu\fu\16u\u05d2"+
		"\13u\3v\3v\3v\3v\3v\3w\7w\u05da\nw\fw\16w\u05dd\13w\3x\5x\u05e0\nx\3x"+
		"\3x\3x\3x\3x\3y\3y\7y\u05e9\ny\fy\16y\u05ec\13y\3z\3z\3z\3z\3z\3z\3{\3"+
		"{\3{\7{\u05f7\n{\f{\16{\u05fa\13{\3|\3|\3|\3}\3}\3}\3}\3}\3}\3}\3~\3~"+
		"\3~\3~\7~\u060a\n~\f~\16~\u060d\13~\3\177\3\177\3\177\3\177\3\177\3\u0080"+
		"\3\u0080\7\u0080\u0616\n\u0080\f\u0080\16\u0080\u0619\13\u0080\3\u0081"+
		"\3\u0081\3\u0081\3\u0081\3\u0081\5\u0081\u0620\n\u0081\3\u0081\3\u0081"+
		"\3\u0081\3\u0081\5\u0081\u0626\n\u0081\3\u0082\3\u0082\3\u0083\3\u0083"+
		"\3\u0083\3\u0083\3\u0083\5\u0083\u062f\n\u0083\3\u0083\3\u0083\3\u0083"+
		"\3\u0083\5\u0083\u0635\n\u0083\3\u0084\3\u0084\3\u0085\7\u0085\u063a\n"+
		"\u0085\f\u0085\16\u0085\u063d\13\u0085\3\u0086\7\u0086\u0640\n\u0086\f"+
		"\u0086\16\u0086\u0643\13\u0086\3\u0087\3\u0087\3\u0087\3\u0087\3\u0087"+
		"\3\u0087\5\u0087\u064b\n\u0087\3\u0088\7\u0088\u064e\n\u0088\f\u0088\16"+
		"\u0088\u0651\13\u0088\3\u0089\3\u0089\3\u0089\3\u0089\3\u0089\3\u008a"+
		"\3\u008a\3\u008a\3\u008a\7\u008a\u065c\n\u008a\f\u008a\16\u008a\u065f"+
		"\13\u008a\3\u008b\3\u008b\3\u008b\3\u008b\3\u008b\3\u008b\5\u008b\u0667"+
		"\n\u008b\3\u008c\3\u008c\7\u008c\u066b\n\u008c\f\u008c\16\u008c\u066e"+
		"\13\u008c\3\u008d\5\u008d\u0671\n\u008d\3\u008d\3\u008d\3\u008d\3\u008d"+
		"\3\u008d\3\u008d\3\u008d\5\u008d\u067a\n\u008d\3\u008e\7\u008e\u067d\n"+
		"\u008e\f\u008e\16\u008e\u0680\13\u008e\3\u008f\3\u008f\3\u008f\3\u008f"+
		"\3\u008f\5\u008f\u0687\n\u008f\3\u0090\3\u0090\3\u0090\3\u0090\7\u0090"+
		"\u068d\n\u0090\f\u0090\16\u0090\u0690\13\u0090\3\u0091\3\u0091\3\u0091"+
		"\3\u0092\3\u0092\3\u0092\3\u0093\3\u0093\3\u0093\3\u0094\3\u0094\3\u0094"+
		"\3\u0094\3\u0094\3\u0095\3\u0095\3\u0095\7\u0095\u06a3\n\u0095\f\u0095"+
		"\16\u0095\u06a6\13\u0095\3\u0096\3\u0096\3\u0096\3\u0097\3\u0097\3\u0097"+
		"\5\u0097\u06ae\n\u0097\3\u0097\5\u0097\u06b1\n\u0097\3\u0097\3\u0097\3"+
		"\u0097\3\u0097\5\u0097\u06b7\n\u0097\3\u0098\7\u0098\u06ba\n\u0098\f\u0098"+
		"\16\u0098\u06bd\13\u0098\3\u0099\3\u0099\3\u0099\3\u0099\3\u0099\5\u0099"+
		"\u06c4\n\u0099\3\u009a\3\u009a\3\u009a\3\u009a\7\u009a\u06ca\n\u009a\f"+
		"\u009a\16\u009a\u06cd\13\u009a\3\u009b\3\u009b\3\u009b\3\u009b\3\u009b"+
		"\3\u009b\5\u009b\u06d5\n\u009b\3\u009c\3\u009c\7\u009c\u06d9\n\u009c\f"+
		"\u009c\16\u009c\u06dc\13\u009c\3\u009d\3\u009d\3\u009d\3\u009d\3\u009d"+
		"\3\u009d\3\u009d\3\u009d\3\u009d\3\u009d\3\u009d\3\u009d\3\u009d\3\u009d"+
		"\5\u009d\u06ec\n\u009d\3\u009e\3\u009e\3\u009e\3\u009e\7\u009e\u06f2\n"+
		"\u009e\f\u009e\16\u009e\u06f5\13\u009e\3\u009f\3\u009f\5\u009f\u06f9\n"+
		"\u009f\3\u009f\3\u009f\3\u009f\3\u009f\3\u00a0\7\u00a0\u0700\n\u00a0\f"+
		"\u00a0\16\u00a0\u0703\13\u00a0\3\u00a1\3\u00a1\3\u00a1\3\u00a1\3\u00a1"+
		"\3\u00a1\5\u00a1\u070b\n\u00a1\3\u00a1\3\u00a1\3\u00a1\3\u00a1\5\u00a1"+
		"\u0711\n\u00a1\3\u00a2\3\u00a2\3\u00a2\7\u00a2\u0716\n\u00a2\f\u00a2\16"+
		"\u00a2\u0719\13\u00a2\3\u00a3\3\u00a3\3\u00a3\3\u00a3\3\u00a3\3\u00a3"+
		"\3\u00a4\3\u00a4\3\u00a4\3\u00a4\3\u00a4\7\u00a4\u0726\n\u00a4\f\u00a4"+
		"\16\u00a4\u0729\13\u00a4\3\u00a5\3\u00a5\3\u00a5\3\u00a5\3\u00a5\3\u00a6"+
		"\7\u00a6\u0731\n\u00a6\f\u00a6\16\u00a6\u0734\13\u00a6\3\u00a7\3\u00a7"+
		"\3\u00a7\3\u00a7\3\u00a8\3\u00a8\3\u00a8\3\u00a8\3\u00a8\3\u00a8\3\u00a9"+
		"\3\u00a9\7\u00a9\u0742\n\u00a9\f\u00a9\16\u00a9\u0745\13\u00a9\3\u00aa"+
		"\3\u00aa\3\u00aa\5\u00aa\u074a\n\u00aa\3\u00ab\3\u00ab\3\u00ab\3\u00ab"+
		"\3\u00ab\3\u00ac\5\u00ac\u0752\n\u00ac\3\u00ac\5\u00ac\u0755\n\u00ac\3"+
		"\u00ac\7\u00ac\u0758\n\u00ac\f\u00ac\16\u00ac\u075b\13\u00ac\3\u00ad\5"+
		"\u00ad\u075e\n\u00ad\3\u00ad\3\u00ad\3\u00ad\3\u00ad\5\u00ad\u0764\n\u00ad"+
		"\3\u00ad\3\u00ad\3\u00ad\3\u00ad\3\u00ae\3\u00ae\3\u00ae\3\u00ae\7\u00ae"+
		"\u076e\n\u00ae\f\u00ae\16\u00ae\u0771\13\u00ae\3\u00af\3\u00af\3\u00af"+
		"\3\u00af\3\u00b0\3\u00b0\3\u00b0\3\u00b0\3\u00b0\3\u00b0\3\u00b0\5\u00b0"+
		"\u077e\n\u00b0\3\u00b1\3\u00b1\3\u00b1\3\u00b1\3\u00b1\3\u00b1\3\u00b2"+
		"\7\u00b2\u0787\n\u00b2\f\u00b2\16\u00b2\u078a\13\u00b2\3\u00b3\3\u00b3"+
		"\3\u00b3\3\u00b3\3\u00b3\3\u00b3\3\u00b4\3\u00b4\3\u00b4\3\u00b4\3\u00b4"+
		"\3\u00b4\7\u00b4\u0798\n\u00b4\f\u00b4\16\u00b4\u079b\13\u00b4\3\u00b5"+
		"\3\u00b5\3\u00b5\3\u00b6\3\u00b6\3\u00b6\3\u00b7\3\u00b7\3\u00b7\3\u00b8"+
		"\3\u00b8\3\u00b8\3\u00b9\3\u00b9\3\u00b9\3\u00ba\3\u00ba\3\u00ba\3\u00ba"+
		"\3\u00ba\3\u00ba\3\u00bb\3\u00bb\3\u00bb\3\u00bb\3\u00bb\3\u00bb\3\u00bb"+
		"\7\u00bb\u07b9\n\u00bb\f\u00bb\16\u00bb\u07bc\13\u00bb\3\u00bc\3\u00bc"+
		"\3\u00bc\3\u00bd\3\u00bd\3\u00bd\3\u00be\3\u00be\3\u00be\3\u00be\3\u00be"+
		"\3\u00bf\3\u00bf\3\u00bf\3\u00bf\3\u00bf\3\u00bf\3\u00bf\3\u00bf\5\u00bf"+
		"\u07d1\n\u00bf\3\u00c0\3\u00c0\7\u00c0\u07d5\n\u00c0\f\u00c0\16\u00c0"+
		"\u07d8\13\u00c0\3\u00c1\3\u00c1\3\u00c1\3\u00c1\3\u00c2\3\u00c2\3\u00c2"+
		"\3\u00c2\3\u00c3\3\u00c3\3\u00c3\7\u00c3\u07e5\n\u00c3\f\u00c3\16\u00c3"+
		"\u07e8\13\u00c3\3\u00c4\3\u00c4\3\u00c4\3\u00c4\3\u00c4\3\u00c5\3\u00c5"+
		"\3\u00c5\3\u00c5\7\u00c5\u07f3\n\u00c5\f\u00c5\16\u00c5\u07f6\13\u00c5"+
		"\3\u00c6\3\u00c6\3\u00c6\3\u00c7\3\u00c7\3\u00c7\3\u00c7\3\u00c7\3\u00c7"+
		"\3\u00c7\3\u00c7\3\u00c7\3\u00c7\3\u00c7\3\u00c7\5\u00c7\u0807\n\u00c7"+
		"\3\u00c7\3\u00c7\5\u00c7\u080b\n\u00c7\3\u00c8\3\u00c8\3\u00c9\3\u00c9"+
		"\3\u00c9\3\u00c9\3\u00c9\3\u00ca\7\u00ca\u0815\n\u00ca\f\u00ca\16\u00ca"+
		"\u0818\13\u00ca\3\u00cb\3\u00cb\3\u00cb\5\u00cb\u081d\n\u00cb\3\u00cb"+
		"\3\u00cb\3\u00cb\3\u00cb\3\u00cc\3\u00cc\7\u00cc\u0825\n\u00cc\f\u00cc"+
		"\16\u00cc\u0828\13\u00cc\3\u00cd\3\u00cd\3\u00cd\3\u00cd\5\u00cd\u082e"+
		"\n\u00cd\3\u00ce\3\u00ce\3\u00ce\3\u00ce\7\u00ce\u0834\n\u00ce\f\u00ce"+
		"\16\u00ce\u0837\13\u00ce\3\u00ce\3\u00ce\3\u00cf\3\u00cf\3\u00cf\3\u00cf"+
		"\3\u00cf\3\u00cf\3\u00cf\3\u00cf\3\u00cf\3\u00cf\7\u00cf\u0845\n\u00cf"+
		"\f\u00cf\16\u00cf\u0848\13\u00cf\3\u00cf\3\u00cf\3\u00cf\3\u00cf\7\u00cf"+
		"\u084e\n\u00cf\f\u00cf\16\u00cf\u0851\13\u00cf\3\u00cf\3\u00cf\3\u00cf"+
		"\3\u00cf\7\u00cf\u0857\n\u00cf\f\u00cf\16\u00cf\u085a\13\u00cf\3\u00cf"+
		"\3\u00cf\3\u00cf\3\u00cf\7\u00cf\u0860\n\u00cf\f\u00cf\16\u00cf\u0863"+
		"\13\u00cf\3\u00cf\5\u00cf\u0866\n\u00cf\3\u00d0\7\u00d0\u0869\n\u00d0"+
		"\f\u00d0\16\u00d0\u086c\13\u00d0\3\u00d1\3\u00d1\3\u00d1\5\u00d1\u0871"+
		"\n\u00d1\3\u00d1\5\u00d1\u0874\n\u00d1\3\u00d1\3\u00d1\3\u00d1\5\u00d1"+
		"\u0879\n\u00d1\5\u00d1\u087b\n\u00d1\3\u00d2\3\u00d2\3\u00d2\3\u00d3\3"+
		"\u00d3\3\u00d3\3\u00d3\3\u00d3\3\u00d3\3\u00d3\3\u00d3\7\u00d3\u0888\n"+
		"\u00d3\f\u00d3\16\u00d3\u088b\13\u00d3\3\u00d3\3\u00d3\3\u00d3\3\u00d4"+
		"\3\u00d4\7\u00d4\u0892\n\u00d4\f\u00d4\16\u00d4\u0895\13\u00d4\3\u00d5"+
		"\3\u00d5\3\u00d5\3\u00d5\2\3\20\u00d6\2\4\6\b\n\f\16\20\22\24\26\30\32"+
		"\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080"+
		"\u0082\u0084\u0086\u0088\u008a\u008c\u008e\u0090\u0092\u0094\u0096\u0098"+
		"\u009a\u009c\u009e\u00a0\u00a2\u00a4\u00a6\u00a8\u00aa\u00ac\u00ae\u00b0"+
		"\u00b2\u00b4\u00b6\u00b8\u00ba\u00bc\u00be\u00c0\u00c2\u00c4\u00c6\u00c8"+
		"\u00ca\u00cc\u00ce\u00d0\u00d2\u00d4\u00d6\u00d8\u00da\u00dc\u00de\u00e0"+
		"\u00e2\u00e4\u00e6\u00e8\u00ea\u00ec\u00ee\u00f0\u00f2\u00f4\u00f6\u00f8"+
		"\u00fa\u00fc\u00fe\u0100\u0102\u0104\u0106\u0108\u010a\u010c\u010e\u0110"+
		"\u0112\u0114\u0116\u0118\u011a\u011c\u011e\u0120\u0122\u0124\u0126\u0128"+
		"\u012a\u012c\u012e\u0130\u0132\u0134\u0136\u0138\u013a\u013c\u013e\u0140"+
		"\u0142\u0144\u0146\u0148\u014a\u014c\u014e\u0150\u0152\u0154\u0156\u0158"+
		"\u015a\u015c\u015e\u0160\u0162\u0164\u0166\u0168\u016a\u016c\u016e\u0170"+
		"\u0172\u0174\u0176\u0178\u017a\u017c\u017e\u0180\u0182\u0184\u0186\u0188"+
		"\u018a\u018c\u018e\u0190\u0192\u0194\u0196\u0198\u019a\u019c\u019e\u01a0"+
		"\u01a2\u01a4\u01a6\u01a8\2\31\24\2\"(+-/\65;FILNWZZ_`elnqttx\177\u0084"+
		"\u0087\u008b\u008b\u008d\u008e\u0090\u0095\u0099\u00ad\u00b3\u00b3\3\2"+
		"\n\13\3\2\f\r\4\2\16\17YY\3\2\20\23\3\2\24\25\3\2\34\35\3\2\61\62\3\2"+
		")*\3\2\66\67\3\2\u0086\u0087\4\2\'(\u00ab\u00ab\4\2++89\3\2\u00a2\u00a3"+
		"\4\2\u00a1\u00a3\u00a6\u00a8\7\2\"#%%\65\65<>PP\3\2\u009f\u00a0\4\2##"+
		"jj\6\2##%%jj\u0085\u0085\4\2KK\u00b0\u00b0\3\2CD\5\2&(*.\67\67\3\2\\^"+
		"\2\u093e\2\u01ac\3\2\2\2\4\u01ae\3\2\2\2\6\u01b0\3\2\2\2\b\u01bb\3\2\2"+
		"\2\n\u01c3\3\2\2\2\f\u01c7\3\2\2\2\16\u01cf\3\2\2\2\20\u01e2\3\2\2\2\22"+
		"\u021c\3\2\2\2\24\u0222\3\2\2\2\26\u0224\3\2\2\2\30\u0232\3\2\2\2\32\u023f"+
		"\3\2\2\2\34\u0243\3\2\2\2\36\u0250\3\2\2\2 \u0259\3\2\2\2\"\u025e\3\2"+
		"\2\2$\u026a\3\2\2\2&\u026e\3\2\2\2(\u0274\3\2\2\2*\u0285\3\2\2\2,\u0287"+
		"\3\2\2\2.\u029c\3\2\2\2\60\u029f\3\2\2\2\62\u02a2\3\2\2\2\64\u02a7\3\2"+
		"\2\2\66\u02ae\3\2\2\28\u02b6\3\2\2\2:\u02bc\3\2\2\2<\u02d9\3\2\2\2>\u02dc"+
		"\3\2\2\2@\u02e4\3\2\2\2B\u02e7\3\2\2\2D\u02f2\3\2\2\2F\u02f5\3\2\2\2H"+
		"\u02fd\3\2\2\2J\u0323\3\2\2\2L\u0326\3\2\2\2N\u032a\3\2\2\2P\u0337\3\2"+
		"\2\2R\u033b\3\2\2\2T\u0343\3\2\2\2V\u0347\3\2\2\2X\u0357\3\2\2\2Z\u035a"+
		"\3\2\2\2\\\u0363\3\2\2\2^\u0366\3\2\2\2`\u036f\3\2\2\2b\u037f\3\2\2\2"+
		"d\u038e\3\2\2\2f\u03a7\3\2\2\2h\u03b5\3\2\2\2j\u03b7\3\2\2\2l\u03bf\3"+
		"\2\2\2n\u03c7\3\2\2\2p\u03d3\3\2\2\2r\u03e1\3\2\2\2t\u03ee\3\2\2\2v\u03f1"+
		"\3\2\2\2x\u03fb\3\2\2\2z\u040e\3\2\2\2|\u0410\3\2\2\2~\u0414\3\2\2\2\u0080"+
		"\u0431\3\2\2\2\u0082\u0434\3\2\2\2\u0084\u043c\3\2\2\2\u0086\u0442\3\2"+
		"\2\2\u0088\u0446\3\2\2\2\u008a\u0453\3\2\2\2\u008c\u0456\3\2\2\2\u008e"+
		"\u045f\3\2\2\2\u0090\u0462\3\2\2\2\u0092\u0469\3\2\2\2\u0094\u0474\3\2"+
		"\2\2\u0096\u0477\3\2\2\2\u0098\u0482\3\2\2\2\u009a\u0492\3\2\2\2\u009c"+
		"\u049e\3\2\2\2\u009e\u04a0\3\2\2\2\u00a0\u04ae\3\2\2\2\u00a2\u04b8\3\2"+
		"\2\2\u00a4\u04bb\3\2\2\2\u00a6\u04be\3\2\2\2\u00a8\u04c1\3\2\2\2\u00aa"+
		"\u04c4\3\2\2\2\u00ac\u04c7\3\2\2\2\u00ae\u04ca\3\2\2\2\u00b0\u04ce\3\2"+
		"\2\2\u00b2\u04d8\3\2\2\2\u00b4\u04e1\3\2\2\2\u00b6\u04ed\3\2\2\2\u00b8"+
		"\u04f0\3\2\2\2\u00ba\u04f4\3\2\2\2\u00bc\u0507\3\2\2\2\u00be\u0509\3\2"+
		"\2\2\u00c0\u051d\3\2\2\2\u00c2\u0527\3\2\2\2\u00c4\u0529\3\2\2\2\u00c6"+
		"\u0535\3\2\2\2\u00c8\u0538\3\2\2\2\u00ca\u0548\3\2\2\2\u00cc\u0554\3\2"+
		"\2\2\u00ce\u0557\3\2\2\2\u00d0\u055a\3\2\2\2\u00d2\u0568\3\2\2\2\u00d4"+
		"\u056b\3\2\2\2\u00d6\u056e\3\2\2\2\u00d8\u0571\3\2\2\2\u00da\u0578\3\2"+
		"\2\2\u00dc\u0580\3\2\2\2\u00de\u0596\3\2\2\2\u00e0\u0599\3\2\2\2\u00e2"+
		"\u05ab\3\2\2\2\u00e4\u05be\3\2\2\2\u00e6\u05c7\3\2\2\2\u00e8\u05d0\3\2"+
		"\2\2\u00ea\u05d3\3\2\2\2\u00ec\u05db\3\2\2\2\u00ee\u05df\3\2\2\2\u00f0"+
		"\u05ea\3\2\2\2\u00f2\u05ed\3\2\2\2\u00f4\u05f8\3\2\2\2\u00f6\u05fb\3\2"+
		"\2\2\u00f8\u05fe\3\2\2\2\u00fa\u060b\3\2\2\2\u00fc\u060e\3\2\2\2\u00fe"+
		"\u0617\3\2\2\2\u0100\u061a\3\2\2\2\u0102\u0627\3\2\2\2\u0104\u0629\3\2"+
		"\2\2\u0106\u0636\3\2\2\2\u0108\u063b\3\2\2\2\u010a\u0641\3\2\2\2\u010c"+
		"\u0644\3\2\2\2\u010e\u064f\3\2\2\2\u0110\u0652\3\2\2\2\u0112\u065d\3\2"+
		"\2\2\u0114\u0660\3\2\2\2\u0116\u066c\3\2\2\2\u0118\u0670\3\2\2\2\u011a"+
		"\u067e\3\2\2\2\u011c\u0681\3\2\2\2\u011e\u068e\3\2\2\2\u0120\u0691\3\2"+
		"\2\2\u0122\u0694\3\2\2\2\u0124\u0697\3\2\2\2\u0126\u069a\3\2\2\2\u0128"+
		"\u06a4\3\2\2\2\u012a\u06a7\3\2\2\2\u012c\u06aa\3\2\2\2\u012e\u06bb\3\2"+
		"\2\2\u0130\u06be\3\2\2\2\u0132\u06cb\3\2\2\2\u0134\u06ce\3\2\2\2\u0136"+
		"\u06da\3\2\2\2\u0138\u06eb\3\2\2\2\u013a\u06f3\3\2\2\2\u013c\u06f6\3\2"+
		"\2\2\u013e\u0701\3\2\2\2\u0140\u0704\3\2\2\2\u0142\u0717\3\2\2\2\u0144"+
		"\u071a\3\2\2\2\u0146\u0727\3\2\2\2\u0148\u072a\3\2\2\2\u014a\u0732\3\2"+
		"\2\2\u014c\u0735\3\2\2\2\u014e\u0739\3\2\2\2\u0150\u0743\3\2\2\2\u0152"+
		"\u0746\3\2\2\2\u0154\u074b\3\2\2\2\u0156\u0751\3\2\2\2\u0158\u075d\3\2"+
		"\2\2\u015a\u076f\3\2\2\2\u015c\u0772\3\2\2\2\u015e\u077d\3\2\2\2\u0160"+
		"\u077f\3\2\2\2\u0162\u0788\3\2\2\2\u0164\u078b\3\2\2\2\u0166\u0799\3\2"+
		"\2\2\u0168\u079c\3\2\2\2\u016a\u079f\3\2\2\2\u016c\u07a2\3\2\2\2\u016e"+
		"\u07a5\3\2\2\2\u0170\u07a8\3\2\2\2\u0172\u07ab\3\2\2\2\u0174\u07ba\3\2"+
		"\2\2\u0176\u07bd\3\2\2\2\u0178\u07c0\3\2\2\2\u017a\u07c3\3\2\2\2\u017c"+
		"\u07c8\3\2\2\2\u017e\u07d6\3\2\2\2\u0180\u07d9\3\2\2\2\u0182\u07dd\3\2"+
		"\2\2\u0184\u07e1\3\2\2\2\u0186\u07e9\3\2\2\2\u0188\u07f4\3\2\2\2\u018a"+
		"\u07f7\3\2\2\2\u018c\u080a\3\2\2\2\u018e\u080c\3\2\2\2\u0190\u080e\3\2"+
		"\2\2\u0192\u0816\3\2\2\2\u0194\u0819\3\2\2\2\u0196\u0826\3\2\2\2\u0198"+
		"\u082d\3\2\2\2\u019a\u082f\3\2\2\2\u019c\u0865\3\2\2\2\u019e\u086a\3\2"+
		"\2\2\u01a0\u087a\3\2\2\2\u01a2\u087c\3\2\2\2\u01a4\u087f\3\2\2\2\u01a6"+
		"\u088f\3\2\2\2\u01a8\u0896\3\2\2\2\u01aa\u01ad\5\6\4\2\u01ab\u01ad\5\4"+
		"\3\2\u01ac\u01aa\3\2\2\2\u01ac\u01ab\3\2\2\2\u01ad\3\3\2\2\2\u01ae\u01af"+
		"\t\2\2\2\u01af\5\3\2\2\2\u01b0\u01b1\7\u00af\2\2\u01b1\u01b7\5\4\3\2\u01b2"+
		"\u01b5\7\3\2\2\u01b3\u01b6\5\4\3\2\u01b4\u01b6\7\u00b0\2\2\u01b5\u01b3"+
		"\3\2\2\2\u01b5\u01b4\3\2\2\2\u01b6\u01b8\3\2\2\2\u01b7\u01b2\3\2\2\2\u01b7"+
		"\u01b8\3\2\2\2\u01b8\u01b9\3\2\2\2\u01b9\u01ba\7\4\2\2\u01ba\7\3\2\2\2"+
		"\u01bb\u01c0\7\u00b3\2\2\u01bc\u01bd\7\5\2\2\u01bd\u01bf\7\u00b3\2\2\u01be"+
		"\u01bc\3\2\2\2\u01bf\u01c2\3\2\2\2\u01c0\u01be\3\2\2\2\u01c0\u01c1\3\2"+
		"\2\2\u01c1\t\3\2\2\2\u01c2\u01c0\3\2\2\2\u01c3\u01c4\7\6\2\2\u01c4\u01c5"+
		"\5\20\t\2\u01c5\u01c6\7\4\2\2\u01c6\13\3\2\2\2\u01c7\u01cc\5\20\t\2\u01c8"+
		"\u01c9\7\7\2\2\u01c9\u01cb\5\20\t\2\u01ca\u01c8\3\2\2\2\u01cb\u01ce\3"+
		"\2\2\2\u01cc\u01ca\3\2\2\2\u01cc\u01cd\3\2\2\2\u01cd\r\3\2\2\2\u01ce\u01cc"+
		"\3\2\2\2\u01cf\u01d0\7\u00b3\2\2\u01d0\u01d2\7\6\2\2\u01d1\u01d3\5\f\7"+
		"\2\u01d2\u01d1\3\2\2\2\u01d2\u01d3\3\2\2\2\u01d3\u01d4\3\2\2\2\u01d4\u01d5"+
		"\7\4\2\2\u01d5\17\3\2\2\2\u01d6\u01d7\b\t\1\2\u01d7\u01e3\5\16\b\2\u01d8"+
		"\u01e3\5\22\n\2\u01d9\u01da\7\6\2\2\u01da\u01db\5\26\f\2\u01db\u01dc\7"+
		"\4\2\2\u01dc\u01dd\5\20\t\17\u01dd\u01e3\3\2\2\2\u01de\u01df\t\3\2\2\u01df"+
		"\u01e3\5\20\t\16\u01e0\u01e1\t\4\2\2\u01e1\u01e3\5\20\t\r\u01e2\u01d6"+
		"\3\2\2\2\u01e2\u01d8\3\2\2\2\u01e2\u01d9\3\2\2\2\u01e2\u01de\3\2\2\2\u01e2"+
		"\u01e0\3\2\2\2\u01e3\u0213\3\2\2\2\u01e4\u01e5\f\f\2\2\u01e5\u01e6\t\5"+
		"\2\2\u01e6\u0212\5\20\t\r\u01e7\u01e8\f\13\2\2\u01e8\u01e9\t\3\2\2\u01e9"+
		"\u0212\5\20\t\f\u01ea\u01eb\f\n\2\2\u01eb\u01ec\t\6\2\2\u01ec\u0212\5"+
		"\20\t\13\u01ed\u01ee\f\t\2\2\u01ee\u01ef\t\7\2\2\u01ef\u0212\5\20\t\n"+
		"\u01f0\u01f1\f\b\2\2\u01f1\u01f2\7\26\2\2\u01f2\u0212\5\20\t\t\u01f3\u01f4"+
		"\f\7\2\2\u01f4\u01f5\7\27\2\2\u01f5\u0212\5\20\t\b\u01f6\u01f7\f\6\2\2"+
		"\u01f7\u01f8\7\30\2\2\u01f8\u0212\5\20\t\7\u01f9\u01fa\f\5\2\2\u01fa\u01fb"+
		"\7\31\2\2\u01fb\u0212\5\20\t\6\u01fc\u01fd\f\4\2\2\u01fd\u01fe\7\32\2"+
		"\2\u01fe\u0212\5\20\t\5\u01ff\u0200\f\3\2\2\u0200\u0201\7\33\2\2\u0201"+
		"\u0202\5\20\t\2\u0202\u0203\7\3\2\2\u0203\u0204\5\20\t\2\u0204\u0205\5"+
		"\20\t\3\u0205\u0212\3\2\2\2\u0206\u0207\f\21\2\2\u0207\u020a\7\5\2\2\u0208"+
		"\u020b\7\u00b3\2\2\u0209\u020b\5\16\b\2\u020a\u0208\3\2\2\2\u020a\u0209"+
		"\3\2\2\2\u020b\u0212\3\2\2\2\u020c\u020d\f\20\2\2\u020d\u020e\7\b\2\2"+
		"\u020e\u020f\5\20\t\2\u020f\u0210\7\t\2\2\u0210\u0212\3\2\2\2\u0211\u01e4"+
		"\3\2\2\2\u0211\u01e7\3\2\2\2\u0211\u01ea\3\2\2\2\u0211\u01ed\3\2\2\2\u0211"+
		"\u01f0\3\2\2\2\u0211\u01f3\3\2\2\2\u0211\u01f6\3\2\2\2\u0211\u01f9\3\2"+
		"\2\2\u0211\u01fc\3\2\2\2\u0211\u01ff\3\2\2\2\u0211\u0206\3\2\2\2\u0211"+
		"\u020c\3\2\2\2\u0212\u0215\3\2\2\2\u0213\u0211\3\2\2\2\u0213\u0214\3\2"+
		"\2\2\u0214\21\3\2\2\2\u0215\u0213\3\2\2\2\u0216\u0217\7\6\2\2\u0217\u0218"+
		"\5\20\t\2\u0218\u0219\7\4\2\2\u0219\u021d\3\2\2\2\u021a\u021d\5\24\13"+
		"\2\u021b\u021d\5\4\3\2\u021c\u0216\3\2\2\2\u021c\u021a\3\2\2\2\u021c\u021b"+
		"\3\2\2\2\u021d\23\3\2\2\2\u021e\u0223\7\u00ae\2\2\u021f\u0223\7\u00b2"+
		"\2\2\u0220\u0223\7\u00b0\2\2\u0221\u0223\t\b\2\2\u0222\u021e\3\2\2\2\u0222"+
		"\u021f\3\2\2\2\u0222\u0220\3\2\2\2\u0222\u0221\3\2\2\2\u0223\25\3\2\2"+
		"\2\u0224\u0225\5\30\r\2\u0225\27\3\2\2\2\u0226\u0233\7\u00a1\2\2\u0227"+
		"\u0233\7\u00a2\2\2\u0228\u0233\7\u00a3\2\2\u0229\u0233\7\u00a6\2\2\u022a"+
		"\u0233\7\u00a7\2\2\u022b\u0233\7\u00a8\2\2\u022c\u022d\7\u00a4\2\2\u022d"+
		"\u022f\7\b\2\2\u022e\u0230\7\u00ae\2\2\u022f\u022e\3\2\2\2\u022f\u0230"+
		"\3\2\2\2\u0230\u0231\3\2\2\2\u0231\u0233\7\t\2\2\u0232\u0226\3\2\2\2\u0232"+
		"\u0227\3\2\2\2\u0232\u0228\3\2\2\2\u0232\u0229\3\2\2\2\u0232\u022a\3\2"+
		"\2\2\u0232\u022b\3\2\2\2\u0232\u022c\3\2\2\2\u0233\31\3\2\2\2\u0234\u0240"+
		"\5\u0172\u00ba\2\u0235\u0240\5\"\22\2\u0236\u0240\5&\24\2\u0237\u0240"+
		"\5p9\2\u0238\u0240\5~@\2\u0239\u0240\5\u0144\u00a3\2\u023a\u0240\5\u0190"+
		"\u00c9\2\u023b\u0240\5\u019a\u00ce\2\u023c\u0240\5\u00f2z\2\u023d\u0240"+
		"\5v<\2\u023e\u0240\5\u0148\u00a5\2\u023f\u0234\3\2\2\2\u023f\u0235\3\2"+
		"\2\2\u023f\u0236\3\2\2\2\u023f\u0237\3\2\2\2\u023f\u0238\3\2\2\2\u023f"+
		"\u0239\3\2\2\2\u023f\u023a\3\2\2\2\u023f\u023b\3\2\2\2\u023f\u023c\3\2"+
		"\2\2\u023f\u023d\3\2\2\2\u023f\u023e\3\2\2\2\u0240\u0241\3\2\2\2\u0241"+
		"\u023f\3\2\2\2\u0241\u0242\3\2\2\2\u0242\33\3\2\2\2\u0243\u024c\7\u00ac"+
		"\2\2\u0244\u0249\5\2\2\2\u0245\u0246\7\7\2\2\u0246\u0248\5\2\2\2\u0247"+
		"\u0245\3\2\2\2\u0248\u024b\3\2\2\2\u0249\u0247\3\2\2\2\u0249\u024a\3\2"+
		"\2\2\u024a\u024d\3\2\2\2\u024b\u0249\3\2\2\2\u024c\u0244\3\2\2\2\u024c"+
		"\u024d\3\2\2\2\u024d\u024e\3\2\2\2\u024e\u024f\7\u00b0\2\2\u024f\35\3"+
		"\2\2\2\u0250\u0251\7\u00ad\2\2\u0251\u0256\5 \21\2\u0252\u0253\7\7\2\2"+
		"\u0253\u0255\5 \21\2\u0254\u0252\3\2\2\2\u0255\u0258\3\2\2\2\u0256\u0254"+
		"\3\2\2\2\u0256\u0257\3\2\2\2\u0257\37\3\2\2\2\u0258\u0256\3\2\2\2\u0259"+
		"\u025c\7\u00b0\2\2\u025a\u025b\7\36\2\2\u025b\u025d\7\u00b0\2\2\u025c"+
		"\u025a\3\2\2\2\u025c\u025d\3\2\2\2\u025d!\3\2\2\2\u025e\u025f\7\"\2\2"+
		"\u025f\u0260\5\2\2\2\u0260\u0261\7\37\2\2\u0261\u0262\5$\23\2\u0262\u0263"+
		"\7 \2\2\u0263#\3\2\2\2\u0264\u026b\5\34\17\2\u0265\u026b\5\36\20\2\u0266"+
		"\u026b\5&\24\2\u0267\u026b\5p9\2\u0268\u026b\5\u00f2z\2\u0269\u026b\5"+
		"v<\2\u026a\u0264\3\2\2\2\u026a\u0265\3\2\2\2\u026a\u0266\3\2\2\2\u026a"+
		"\u0267\3\2\2\2\u026a\u0268\3\2\2\2\u026a\u0269\3\2\2\2\u026b\u026c\3\2"+
		"\2\2\u026c\u026a\3\2\2\2\u026c\u026d\3\2\2\2\u026d%\3\2\2\2\u026e\u026f"+
		"\5(\25\2\u026f\u0270\7\37\2\2\u0270\u0271\5.\30\2\u0271\u0272\7 \2\2\u0272"+
		"\'\3\2\2\2\u0273\u0275\7/\2\2\u0274\u0273\3\2\2\2\u0274\u0275\3\2\2\2"+
		"\u0275\u0277\3\2\2\2\u0276\u0278\7\60\2\2\u0277\u0276\3\2\2\2\u0277\u0278"+
		"\3\2\2\2\u0278\u027a\3\2\2\2\u0279\u027b\t\t\2\2\u027a\u0279\3\2\2\2\u027a"+
		"\u027b\3\2\2\2\u027b\u027c\3\2\2\2\u027c\u027f\7#\2\2\u027d\u0280\5*\26"+
		"\2\u027e\u0280\5,\27\2\u027f\u027d\3\2\2\2\u027f\u027e\3\2\2\2\u0280\u0283"+
		"\3\2\2\2\u0281\u0282\7\u008f\2\2\u0282\u0284\5\2\2\2\u0283\u0281\3\2\2"+
		"\2\u0283\u0284\3\2\2\2\u0284)\3\2\2\2\u0285\u0286\5\2\2\2\u0286+\3\2\2"+
		"\2\u0287\u0288\5\2\2\2\u0288\u0289\7\23\2\2\u0289\u028e\5\2\2\2\u028a"+
		"\u028b\7\7\2\2\u028b\u028d\5\2\2\2\u028c\u028a\3\2\2\2\u028d\u0290\3\2"+
		"\2\2\u028e\u028c\3\2\2\2\u028e\u028f\3\2\2\2\u028f\u0291\3\2\2\2\u0290"+
		"\u028e\3\2\2\2\u0291\u0292\7\22\2\2\u0292-\3\2\2\2\u0293\u029b\5\60\31"+
		"\2\u0294\u029b\5\62\32\2\u0295\u029b\5\66\34\2\u0296\u029b\5\34\17\2\u0297"+
		"\u029b\5\36\20\2\u0298\u029b\5F$\2\u0299\u029b\5p9\2\u029a\u0293\3\2\2"+
		"\2\u029a\u0294\3\2\2\2\u029a\u0295\3\2\2\2\u029a\u0296\3\2\2\2\u029a\u0297"+
		"\3\2\2\2\u029a\u0298\3\2\2\2\u029a\u0299\3\2\2\2\u029b\u029e\3\2\2\2\u029c"+
		"\u029a\3\2\2\2\u029c\u029d\3\2\2\2\u029d/\3\2\2\2\u029e\u029c\3\2\2\2"+
		"\u029f\u02a0\7$\2\2\u02a0\u02a1\5:\36\2\u02a1\61\3\2\2\2\u02a2\u02a3\7"+
		"$\2\2\u02a3\u02a4\7\37\2\2\u02a4\u02a5\58\35\2\u02a5\u02a6\7 \2\2\u02a6"+
		"\63\3\2\2\2\u02a7\u02ab\7k\2\2\u02a8\u02aa\5\2\2\2\u02a9\u02a8\3\2\2\2"+
		"\u02aa\u02ad\3\2\2\2\u02ab\u02a9\3\2\2\2\u02ab\u02ac\3\2\2\2\u02ac\65"+
		"\3\2\2\2\u02ad\u02ab\3\2\2\2\u02ae\u02af\5\64\33\2\u02af\u02b0\7\37\2"+
		"\2\u02b0\u02b1\58\35\2\u02b1\u02b2\7 \2\2\u02b2\67\3\2\2\2\u02b3\u02b5"+
		"\5:\36\2\u02b4\u02b3\3\2\2\2\u02b5\u02b8\3\2\2\2\u02b6\u02b4\3\2\2\2\u02b6"+
		"\u02b7\3\2\2\2\u02b79\3\2\2\2\u02b8\u02b6\3\2\2\2\u02b9\u02bb\5\u018e"+
		"\u00c8\2\u02ba\u02b9\3\2\2\2\u02bb\u02be\3\2\2\2\u02bc\u02ba\3\2\2\2\u02bc"+
		"\u02bd\3\2\2\2\u02bd\u02bf\3\2\2\2\u02be\u02bc\3\2\2\2\u02bf\u02c0\5\u018c"+
		"\u00c7\2\u02c0\u02c3\5\2\2\2\u02c1\u02c2\7u\2\2\u02c2\u02c4\5\2\2\2\u02c3"+
		"\u02c1\3\2\2\2\u02c3\u02c4\3\2\2\2\u02c4\u02ca\3\2\2\2\u02c5\u02c8\7\36"+
		"\2\2\u02c6\u02c9\5\24\13\2\u02c7\u02c9\5\2\2\2\u02c8\u02c6\3\2\2\2\u02c8"+
		"\u02c7\3\2\2\2\u02c9\u02cb\3\2\2\2\u02ca\u02c5\3\2\2\2\u02ca\u02cb\3\2"+
		"\2\2\u02cb\u02d0\3\2\2\2\u02cc\u02cd\7\37\2\2\u02cd\u02ce\5<\37\2\u02ce"+
		"\u02cf\7 \2\2\u02cf\u02d1\3\2\2\2\u02d0\u02cc\3\2\2\2\u02d0\u02d1\3\2"+
		"\2\2\u02d1;\3\2\2\2\u02d2\u02d8\5\34\17\2\u02d3\u02d8\5\36\20\2\u02d4"+
		"\u02d8\5@!\2\u02d5\u02d8\5z>\2\u02d6\u02d8\5B\"\2\u02d7\u02d2\3\2\2\2"+
		"\u02d7\u02d3\3\2\2\2\u02d7\u02d4\3\2\2\2\u02d7\u02d5\3\2\2\2\u02d7\u02d6"+
		"\3\2\2\2\u02d8\u02db\3\2\2\2\u02d9\u02d7\3\2\2\2\u02d9\u02da\3\2\2\2\u02da"+
		"=\3\2\2\2\u02db\u02d9\3\2\2\2\u02dc\u02e1\5\2\2\2\u02dd\u02de\7\5\2\2"+
		"\u02de\u02e0\5\2\2\2\u02df\u02dd\3\2\2\2\u02e0\u02e3\3\2\2\2\u02e1\u02df"+
		"\3\2\2\2\u02e1\u02e2\3\2\2\2\u02e2?\3\2\2\2\u02e3\u02e1\3\2\2\2\u02e4"+
		"\u02e5\7b\2\2\u02e5\u02e6\7\u00b0\2\2\u02e6A\3\2\2\2\u02e7\u02e8\7c\2"+
		"\2\u02e8\u02e9\5\2\2\2\u02e9\u02ea\7\37\2\2\u02ea\u02eb\5D#\2\u02eb\u02ec"+
		"\7 \2\2\u02ecC\3\2\2\2\u02ed\u02f1\5\34\17\2\u02ee\u02f1\5\36\20\2\u02ef"+
		"\u02f1\5\20\t\2\u02f0\u02ed\3\2\2\2\u02f0\u02ee\3\2\2\2\u02f0\u02ef\3"+
		"\2\2\2\u02f1\u02f4\3\2\2\2\u02f2\u02f0\3\2\2\2\u02f2\u02f3\3\2\2\2\u02f3"+
		"E\3\2\2\2\u02f4\u02f2\3\2\2\2\u02f5\u02f6\7\64\2\2\u02f6\u02f7\7\37\2"+
		"\2\u02f7\u02f8\5H%\2\u02f8\u02f9\7 \2\2\u02f9G\3\2\2\2\u02fa\u02fc\5J"+
		"&\2\u02fb\u02fa\3\2\2\2\u02fc\u02ff\3\2\2\2\u02fd\u02fb\3\2\2\2\u02fd"+
		"\u02fe\3\2\2\2\u02feI\3\2\2\2\u02ff\u02fd\3\2\2\2\u0300\u0301\7\67\2\2"+
		"\u0301\u0302\5N(\2\u0302\u0304\5\2\2\2\u0303\u0305\5\2\2\2\u0304\u0303"+
		"\3\2\2\2\u0304\u0305\3\2\2\2\u0305\u030a\3\2\2\2\u0306\u0307\7\37\2\2"+
		"\u0307\u0308\5T+\2\u0308\u0309\7 \2\2\u0309\u030b\3\2\2\2\u030a\u0306"+
		"\3\2\2\2\u030a\u030b\3\2\2\2\u030b\u0324\3\2\2\2\u030c\u030e\t\n\2\2\u030d"+
		"\u030c\3\2\2\2\u030d\u030e\3\2\2\2\u030e\u0310\3\2\2\2\u030f\u0311\7+"+
		"\2\2\u0310\u030f\3\2\2\2\u0310\u0311\3\2\2\2\u0311\u0312\3\2\2\2\u0312"+
		"\u0313\t\13\2\2\u0313\u0315\5\2\2\2\u0314\u0316\5P)\2\u0315\u0314\3\2"+
		"\2\2\u0315\u0316\3\2\2\2\u0316\u0318\3\2\2\2\u0317\u0319\5\2\2\2\u0318"+
		"\u0317\3\2\2\2\u0318\u0319\3\2\2\2\u0319\u031b\3\2\2\2\u031a\u031c\5R"+
		"*\2\u031b\u031a\3\2\2\2\u031b\u031c\3\2\2\2\u031c\u0321\3\2\2\2\u031d"+
		"\u031e\7\37\2\2\u031e\u031f\5T+\2\u031f\u0320\7 \2\2\u0320\u0322\3\2\2"+
		"\2\u0321\u031d\3\2\2\2\u0321\u0322\3\2\2\2\u0322\u0324\3\2\2\2\u0323\u0300"+
		"\3\2\2\2\u0323\u030d\3\2\2\2\u0324K\3\2\2\2\u0325\u0327\7&\2\2\u0326\u0325"+
		"\3\2\2\2\u0326\u0327\3\2\2\2\u0327\u0328\3\2\2\2\u0328\u0329\5\2\2\2\u0329"+
		"M\3\2\2\2\u032a\u032b\5\2\2\2\u032b\u032c\7\23\2\2\u032c\u0331\5L\'\2"+
		"\u032d\u032e\7\7\2\2\u032e\u0330\5L\'\2\u032f\u032d\3\2\2\2\u0330\u0333"+
		"\3\2\2\2\u0331\u032f\3\2\2\2\u0331\u0332\3\2\2\2\u0332\u0334\3\2\2\2\u0333"+
		"\u0331\3\2\2\2\u0334\u0335\7\22\2\2\u0335\u0336\7:\2\2\u0336O\3\2\2\2"+
		"\u0337\u0338\7\6\2\2\u0338\u0339\5\2\2\2\u0339\u033a\7\4\2\2\u033aQ\3"+
		"\2\2\2\u033b\u033c\7\6\2\2\u033c\u033d\5\2\2\2\u033d\u033e\7\4\2\2\u033e"+
		"S\3\2\2\2\u033f\u0342\5\34\17\2\u0340\u0342\5\36\20\2\u0341\u033f\3\2"+
		"\2\2\u0341\u0340\3\2\2\2\u0342\u0345\3\2\2\2\u0343\u0341\3\2\2\2\u0343"+
		"\u0344\3\2\2\2\u0344U\3\2\2\2\u0345\u0343\3\2\2\2\u0346\u0348\7\u0084"+
		"\2\2\u0347\u0346\3\2\2\2\u0347\u0348\3\2\2\2\u0348\u0349\3\2\2\2\u0349"+
		"\u034a\7\u0085\2\2\u034a\u034b\5\2\2\2\u034b\u034c\7\37\2\2\u034c\u034d"+
		"\5X-\2\u034d\u034e\7 \2\2\u034eW\3\2\2\2\u034f\u0356\5\34\17\2\u0350\u0356"+
		"\5\36\20\2\u0351\u0352\t\f\2\2\u0352\u0356\7$\2\2\u0353\u0356\5Z.\2\u0354"+
		"\u0356\5^\60\2\u0355\u034f\3\2\2\2\u0355\u0350\3\2\2\2\u0355\u0351\3\2"+
		"\2\2\u0355\u0353\3\2\2\2\u0355\u0354\3\2\2\2\u0356\u0359\3\2\2\2\u0357"+
		"\u0355\3\2\2\2\u0357\u0358\3\2\2\2\u0358Y\3\2\2\2\u0359\u0357\3\2\2\2"+
		"\u035a\u035b\7k\2\2\u035b\u035c\7\37\2\2\u035c\u035d\5\\/\2\u035d\u035e"+
		"\7 \2\2\u035e[\3\2\2\2\u035f\u0362\5b\62\2\u0360\u0362\5d\63\2\u0361\u035f"+
		"\3\2\2\2\u0361\u0360\3\2\2\2\u0362\u0365\3\2\2\2\u0363\u0361\3\2\2\2\u0363"+
		"\u0364\3\2\2\2\u0364]\3\2\2\2\u0365\u0363\3\2\2\2\u0366\u0367\7\64\2\2"+
		"\u0367\u0368\7\37\2\2\u0368\u0369\5`\61\2\u0369\u036a\7 \2\2\u036a_\3"+
		"\2\2\2\u036b\u036e\5f\64\2\u036c\u036e\5h\65\2\u036d\u036b\3\2\2\2\u036d"+
		"\u036c\3\2\2\2\u036e\u0371\3\2\2\2\u036f\u036d\3\2\2\2\u036f\u0370\3\2"+
		"\2\2\u0370a\3\2\2\2\u0371\u036f\3\2\2\2\u0372\u0380\7\u0086\2\2\u0373"+
		"\u0374\7\u0086\2\2\u0374\u0380\5n8\2\u0375\u0376\7\u0086\2\2\u0376\u0380"+
		"\t\r\2\2\u0377\u0378\7\u0086\2\2\u0378\u0379\7\u008c\2\2\u0379\u0380\5"+
		"l\67\2\u037a\u037b\7\u0086\2\2\u037b\u037c\7\62\2\2\u037c\u037d\7;\2\2"+
		"\u037d\u037e\7\u008c\2\2\u037e\u0380\5l\67\2\u037f\u0372\3\2\2\2\u037f"+
		"\u0373\3\2\2\2\u037f\u0375\3\2\2\2\u037f\u0377\3\2\2\2\u037f\u037a\3\2"+
		"\2\2\u0380c\3\2\2\2\u0381\u038f\7\u0087\2\2\u0382\u0383\7\u0087\2\2\u0383"+
		"\u038f\5n8\2\u0384\u0385\7\u0087\2\2\u0385\u038f\t\r\2\2\u0386\u0387\7"+
		"\u0087\2\2\u0387\u0388\7\u008c\2\2\u0388\u038f\5l\67\2\u0389\u038a\7\u0087"+
		"\2\2\u038a\u038b\7\62\2\2\u038b\u038c\7;\2\2\u038c\u038d\7\u008c\2\2\u038d"+
		"\u038f\5l\67\2\u038e\u0381\3\2\2\2\u038e\u0382\3\2\2\2\u038e\u0384\3\2"+
		"\2\2\u038e\u0386\3\2\2\2\u038e\u0389\3\2\2\2\u038fe\3\2\2\2\u0390\u0391"+
		"\7\u0086\2\2\u0391\u0393\t\16\2\2\u0392\u0394\7#\2\2\u0393\u0392\3\2\2"+
		"\2\u0393\u0394\3\2\2\2\u0394\u0396\3\2\2\2\u0395\u0397\5\2\2\2\u0396\u0395"+
		"\3\2\2\2\u0396\u0397\3\2\2\2\u0397\u039a\3\2\2\2\u0398\u0399\7:\2\2\u0399"+
		"\u039b\5\2\2\2\u039a\u0398\3\2\2\2\u039a\u039b\3\2\2\2\u039b\u03a2\3\2"+
		"\2\2\u039c\u03a0\7v\2\2\u039d\u03a1\7$\2\2\u039e\u039f\7\u0085\2\2\u039f"+
		"\u03a1\5\2\2\2\u03a0\u039d\3\2\2\2\u03a0\u039e\3\2\2\2\u03a1\u03a3\3\2"+
		"\2\2\u03a2\u039c\3\2\2\2\u03a2\u03a3\3\2\2\2\u03a3\u03a8\3\2\2\2\u03a4"+
		"\u03a5\7\u0086\2\2\u03a5\u03a6\7\u008c\2\2\u03a6\u03a8\5l\67\2\u03a7\u0390"+
		"\3\2\2\2\u03a7\u03a4\3\2\2\2\u03a8g\3\2\2\2\u03a9\u03b6\7\u0087\2\2\u03aa"+
		"\u03ab\7\u0087\2\2\u03ab\u03ad\t\16\2\2\u03ac\u03ae\7#\2\2\u03ad\u03ac"+
		"\3\2\2\2\u03ad\u03ae\3\2\2\2\u03ae\u03b0\3\2\2\2\u03af\u03b1\5\2\2\2\u03b0"+
		"\u03af\3\2\2\2\u03b0\u03b1\3\2\2\2\u03b1\u03b6\3\2\2\2\u03b2\u03b3\7\u0087"+
		"\2\2\u03b3\u03b4\7\u008c\2\2\u03b4\u03b6\5l\67\2\u03b5\u03a9\3\2\2\2\u03b5"+
		"\u03aa\3\2\2\2\u03b5\u03b2\3\2\2\2\u03b6i\3\2\2\2\u03b7\u03bc\7\u00b0"+
		"\2\2\u03b8\u03b9\7\u008b\2\2\u03b9\u03bb\7\u00b0\2\2\u03ba\u03b8\3\2\2"+
		"\2\u03bb\u03be\3\2\2\2\u03bc\u03ba\3\2\2\2\u03bc\u03bd\3\2\2\2\u03bdk"+
		"\3\2\2\2\u03be\u03bc\3\2\2\2\u03bf\u03c4\5j\66\2\u03c0\u03c1\7\7\2\2\u03c1"+
		"\u03c3\5j\66\2\u03c2\u03c0\3\2\2\2\u03c3\u03c6\3\2\2\2\u03c4\u03c2\3\2"+
		"\2\2\u03c4\u03c5\3\2\2\2\u03c5m\3\2\2\2\u03c6\u03c4\3\2\2\2\u03c7\u03c8"+
		"\7\6\2\2\u03c8\u03cd\5\2\2\2\u03c9\u03ca\7\7\2\2\u03ca\u03cc\5\2\2\2\u03cb"+
		"\u03c9\3\2\2\2\u03cc\u03cf\3\2\2\2\u03cd\u03cb\3\2\2\2\u03cd\u03ce\3\2"+
		"\2\2\u03ce\u03d0\3\2\2\2\u03cf\u03cd\3\2\2\2\u03d0\u03d1\7\4\2\2\u03d1"+
		"o\3\2\2\2\u03d2\u03d4\7/\2\2\u03d3\u03d2\3\2\2\2\u03d3\u03d4\3\2\2\2\u03d4"+
		"\u03d5\3\2\2\2\u03d5\u03d6\7<\2\2\u03d6\u03d7\5\2\2\2\u03d7\u03db\7\37"+
		"\2\2\u03d8\u03dc\5r:\2\u03d9\u03dc\5\34\17\2\u03da\u03dc\5\36\20\2\u03db"+
		"\u03d8\3\2\2\2\u03db\u03d9\3\2\2\2\u03db\u03da\3\2\2\2\u03dc\u03dd\3\2"+
		"\2\2\u03dd\u03db\3\2\2\2\u03dd\u03de\3\2\2\2\u03de\u03df\3\2\2\2\u03df"+
		"\u03e0\7 \2\2\u03e0q\3\2\2\2\u03e1\u03e2\5\2\2\2\u03e2\u03e3\7\36\2\2"+
		"\u03e3\u03e8\7\u00ae\2\2\u03e4\u03e5\7\37\2\2\u03e5\u03e6\5t;\2\u03e6"+
		"\u03e7\7 \2\2\u03e7\u03e9\3\2\2\2\u03e8\u03e4\3\2\2\2\u03e8\u03e9\3\2"+
		"\2\2\u03e9s\3\2\2\2\u03ea\u03ed\5\34\17\2\u03eb\u03ed\5\36\20\2\u03ec"+
		"\u03ea\3\2\2\2\u03ec\u03eb\3\2\2\2\u03ed\u03f0\3\2\2\2\u03ee\u03ec\3\2"+
		"\2\2\u03ee\u03ef\3\2\2\2\u03efu\3\2\2\2\u03f0\u03ee\3\2\2\2\u03f1\u03f2"+
		"\7>\2\2\u03f2\u03f3\t\17\2\2\u03f3\u03f4\5\2\2\2\u03f4\u03f5\7\37\2\2"+
		"\u03f5\u03f6\5x=\2\u03f6\u03f7\7 \2\2\u03f7w\3\2\2\2\u03f8\u03fc\5z>\2"+
		"\u03f9\u03fc\5\34\17\2\u03fa\u03fc\5\36\20\2\u03fb\u03f8\3\2\2\2\u03fb"+
		"\u03f9\3\2\2\2\u03fb\u03fa\3\2\2\2\u03fc\u03fd\3\2\2\2\u03fd\u03fb\3\2"+
		"\2\2\u03fd\u03fe\3\2\2\2\u03fey\3\2\2\2\u03ff\u0400\5|?\2\u0400\u0401"+
		"\7?\2\2\u0401\u040f\3\2\2\2\u0402\u0403\5|?\2\u0403\u040c\5\2\2\2\u0404"+
		"\u0406\7\37\2\2\u0405\u0407\5\34\17\2\u0406\u0405\3\2\2\2\u0407\u0408"+
		"\3\2\2\2\u0408\u0406\3\2\2\2\u0408\u0409\3\2\2\2\u0409\u040a\3\2\2\2\u040a"+
		"\u040b\7 \2\2\u040b\u040d\3\2\2\2\u040c\u0404\3\2\2\2\u040c\u040d\3\2"+
		"\2\2\u040d\u040f\3\2\2\2\u040e\u03ff\3\2\2\2\u040e\u0402\3\2\2\2\u040f"+
		"{\3\2\2\2\u0410\u0411\7\6\2\2\u0411\u0412\7\u00ae\2\2\u0412\u0413\7\4"+
		"\2\2\u0413}\3\2\2\2\u0414\u0415\7j\2\2\u0415\u041b\7\u00b3\2\2\u0416\u0417"+
		"\7\u008e\2\2\u0417\u041c\7\u00b3\2\2\u0418\u0419\7\6\2\2\u0419\u041a\7"+
		"\u00b3\2\2\u041a\u041c\7\4\2\2\u041b\u0416\3\2\2\2\u041b\u0418\3\2\2\2"+
		"\u041b\u041c\3\2\2\2\u041c\u041d\3\2\2\2\u041d\u041e\7\37\2\2\u041e\u041f"+
		"\5\u0080A\2\u041f\u0420\7 \2\2\u0420\177\3\2\2\2\u0421\u0430\5\34\17\2"+
		"\u0422\u0430\5\36\20\2\u0423\u0430\5\u0096L\2\u0424\u0430\5\u009eP\2\u0425"+
		"\u0430\5\u00caf\2\u0426\u0430\5\u0088E\2\u0427\u0430\5\u00c8e\2\u0428"+
		"\u0430\5\u00c4c\2\u0429\u0430\5\u0084C\2\u042a\u0430\5\u00d0i\2\u042b"+
		"\u0430\5\u0086D\2\u042c\u0430\5\u00e0q\2\u042d\u0430\5\u00e2r\2\u042e"+
		"\u0430\5\u00b0Y\2\u042f\u0421\3\2\2\2\u042f\u0422\3\2\2\2\u042f\u0423"+
		"\3\2\2\2\u042f\u0424\3\2\2\2\u042f\u0425\3\2\2\2\u042f\u0426\3\2\2\2\u042f"+
		"\u0427\3\2\2\2\u042f\u0428\3\2\2\2\u042f\u0429\3\2\2\2\u042f\u042a\3\2"+
		"\2\2\u042f\u042b\3\2\2\2\u042f\u042c\3\2\2\2\u042f\u042d\3\2\2\2\u042f"+
		"\u042e\3\2\2\2\u0430\u0433\3\2\2\2\u0431\u042f\3\2\2\2\u0431\u0432\3\2"+
		"\2\2\u0432\u0081\3\2\2\2\u0433\u0431\3\2\2\2\u0434\u0439\5\2\2\2\u0435"+
		"\u0436\7\5\2\2\u0436\u0438\5\2\2\2\u0437\u0435\3\2\2\2\u0438\u043b\3\2"+
		"\2\2\u0439\u0437\3\2\2\2\u0439\u043a\3\2\2\2\u043a\u0083\3\2\2\2\u043b"+
		"\u0439\3\2\2\2\u043c\u043e\7t\2\2\u043d\u043f\7P\2\2\u043e\u043d\3\2\2"+
		"\2\u043e\u043f\3\2\2\2\u043f\u0440\3\2\2\2\u0440\u0441\5\u0082B\2\u0441"+
		"\u0085\3\2\2\2\u0442\u0443\7\63\2\2\u0443\u0444\7\62\2\2\u0444\u0445\7"+
		";\2\2\u0445\u0087\3\2\2\2\u0446\u0447\7<\2\2\u0447\u0448\7\u00b3\2\2\u0448"+
		"\u0449\7\37\2\2\u0449\u044a\5\u008aF\2\u044a\u044b\7 \2\2\u044b\u0089"+
		"\3\2\2\2\u044c\u0452\5\34\17\2\u044d\u0452\5\36\20\2\u044e\u0452\5\u0092"+
		"J\2\u044f\u0452\5\u008cG\2\u0450\u0452\5\u0090I\2\u0451\u044c\3\2\2\2"+
		"\u0451\u044d\3\2\2\2\u0451\u044e\3\2\2\2\u0451\u044f\3\2\2\2\u0451\u0450"+
		"\3\2\2\2\u0452\u0455\3\2\2\2\u0453\u0451\3\2\2\2\u0453\u0454\3\2\2\2\u0454"+
		"\u008b\3\2\2\2\u0455\u0453\3\2\2\2\u0456\u0457\7\u00b3\2\2\u0457\u0458"+
		"\7\37\2\2\u0458\u0459\5\u008eH\2\u0459\u045a\7 \2\2\u045a\u008d\3\2\2"+
		"\2\u045b\u045e\5\34\17\2\u045c\u045e\5\36\20\2\u045d\u045b\3\2\2\2\u045d"+
		"\u045c\3\2\2\2\u045e\u0461\3\2\2\2\u045f\u045d\3\2\2\2\u045f\u0460\3\2"+
		"\2\2\u0460\u008f\3\2\2\2\u0461\u045f\3\2\2\2\u0462\u0463\7p\2\2\u0463"+
		"\u0465\5\2\2\2\u0464\u0466\7\u009e\2\2\u0465\u0464\3\2\2\2\u0465\u0466"+
		"\3\2\2\2\u0466\u0467\3\2\2\2\u0467\u0468\5\2\2\2\u0468\u0091\3\2\2\2\u0469"+
		"\u046a\7s\2\2\u046a\u046b\7=\2\2\u046b\u046c\7\37\2\2\u046c\u046d\5\u0094"+
		"K\2\u046d\u046e\7 \2\2\u046e\u0093\3\2\2\2\u046f\u0473\5\u00a4S\2\u0470"+
		"\u0473\5\u00a6T\2\u0471\u0473\5\u00a8U\2\u0472\u046f\3\2\2\2\u0472\u0470"+
		"\3\2\2\2\u0472\u0471\3\2\2\2\u0473\u0476\3\2\2\2\u0474\u0472\3\2\2\2\u0474"+
		"\u0475\3\2\2\2\u0475\u0095\3\2\2\2\u0476\u0474\3\2\2\2\u0477\u0478\7q"+
		"\2\2\u0478\u0479\5\2\2\2\u0479\u047d\7\37\2\2\u047a\u047c\5\u0098M\2\u047b"+
		"\u047a\3\2\2\2\u047c\u047f\3\2\2\2\u047d\u047b\3\2\2\2\u047d\u047e\3\2"+
		"\2\2\u047e\u0480\3\2\2\2\u047f\u047d\3\2\2\2\u0480\u0481\7 \2\2\u0481"+
		"\u0097\3\2\2\2\u0482\u0484\7N\2\2\u0483\u0485\7r\2\2\u0484\u0483\3\2\2"+
		"\2\u0484\u0485\3\2\2\2\u0485\u0486\3\2\2\2\u0486\u0487\7\u00b0\2\2\u0487"+
		"\u048d\7\37\2\2\u0488\u048c\5\34\17\2\u0489\u048c\5\36\20\2\u048a\u048c"+
		"\5\u009aN\2\u048b\u0488\3\2\2\2\u048b\u0489\3\2\2\2\u048b\u048a\3\2\2"+
		"\2\u048c\u048f\3\2\2\2\u048d\u048b\3\2\2\2\u048d\u048e\3\2\2\2\u048e\u0490"+
		"\3\2\2\2\u048f\u048d\3\2\2\2\u0490\u0491\7 \2\2\u0491\u0099\3\2\2\2\u0492"+
		"\u0493\7O\2\2\u0493\u0494\5\u009cO\2\u0494\u0499\7\37\2\2\u0495\u0498"+
		"\5\34\17\2\u0496\u0498\5\36\20\2\u0497\u0495\3\2\2\2\u0497\u0496\3\2\2"+
		"\2\u0498\u049b\3\2\2\2\u0499\u0497\3\2\2\2\u0499\u049a\3\2\2\2\u049a\u049c"+
		"\3\2\2\2\u049b\u0499\3\2\2\2\u049c\u049d\7 \2\2\u049d\u009b\3\2\2\2\u049e"+
		"\u049f\t\20\2\2\u049f\u009d\3\2\2\2\u04a0\u04a1\7s\2\2\u04a1\u04a6\5\u00a0"+
		"Q\2\u04a2\u04a3\7\7\2\2\u04a3\u04a5\5\u00a0Q\2\u04a4\u04a2\3\2\2\2\u04a5"+
		"\u04a8\3\2\2\2\u04a6\u04a4\3\2\2\2\u04a6\u04a7\3\2\2\2\u04a7\u04a9\3\2"+
		"\2\2\u04a8\u04a6\3\2\2\2\u04a9\u04aa\7\37\2\2\u04aa\u04ab\5\u00a2R\2\u04ab"+
		"\u04ac\7 \2\2\u04ac\u009f\3\2\2\2\u04ad\u04af\t\21\2\2\u04ae\u04ad\3\2"+
		"\2\2\u04ae\u04af\3\2\2\2\u04af\u00a1\3\2\2\2\u04b0\u04b7\5\u00a4S\2\u04b1"+
		"\u04b7\5\u00a6T\2\u04b2\u04b7\5\u00a8U\2\u04b3\u04b7\5\u00aaV\2\u04b4"+
		"\u04b7\5\u00acW\2\u04b5\u04b7\5\u00aeX\2\u04b6\u04b0\3\2\2\2\u04b6\u04b1"+
		"\3\2\2\2\u04b6\u04b2\3\2\2\2\u04b6\u04b3\3\2\2\2\u04b6\u04b4\3\2\2\2\u04b6"+
		"\u04b5\3\2\2\2\u04b7\u04ba\3\2\2\2\u04b8\u04b6\3\2\2\2\u04b8\u04b9\3\2"+
		"\2\2\u04b9\u00a3\3\2\2\2\u04ba\u04b8\3\2\2\2\u04bb\u04bc\7m\2\2\u04bc"+
		"\u04bd\7\u00b3\2\2\u04bd\u00a5\3\2\2\2\u04be\u04bf\7n\2\2\u04bf\u04c0"+
		"\7\u00b0\2\2\u04c0\u00a7\3\2\2\2\u04c1\u04c2\7o\2\2\u04c2\u04c3\7\u00b0"+
		"\2\2\u04c3\u00a9\3\2\2\2\u04c4\u04c5\7$\2\2\u04c5\u04c6\7\u00b3\2\2\u04c6"+
		"\u00ab\3\2\2\2\u04c7\u04c8\7v\2\2\u04c8\u04c9\7\u0080\2\2\u04c9\u00ad"+
		"\3\2\2\2\u04ca\u04cb\7w\2\2\u04cb\u04cc\7\u0080\2\2\u04cc\u00af\3\2\2"+
		"\2\u04cd\u04cf\7\u0084\2\2\u04ce\u04cd\3\2\2\2\u04ce\u04cf\3\2\2\2\u04cf"+
		"\u04d0\3\2\2\2\u04d0\u04d1\7\177\2\2\u04d1\u04d2\7F\2\2\u04d2\u04d3\5"+
		"\2\2\2\u04d3\u04d4\7\37\2\2\u04d4\u04d5\5\u00b6\\\2\u04d5\u04d6\7 \2\2"+
		"\u04d6\u00b1\3\2\2\2\u04d7\u04d9\7\u0084\2\2\u04d8\u04d7\3\2\2\2\u04d8"+
		"\u04d9\3\2\2\2\u04d9\u04da\3\2\2\2\u04da\u04db\7\177\2\2\u04db\u04dc\7"+
		"F\2\2\u04dc\u04dd\5\2\2\2\u04dd\u04de\7\37\2\2\u04de\u04df\5\u00b6\\\2"+
		"\u04df\u04e0\7 \2\2\u04e0\u00b3\3\2\2\2\u04e1\u04e2\7\177\2\2\u04e2\u04e3"+
		"\7F\2\2\u04e3\u04e4\5\2\2\2\u04e4\u04e5\7\37\2\2\u04e5\u04e6\5\u00b6\\"+
		"\2\u04e6\u04e7\7 \2\2\u04e7\u00b5\3\2\2\2\u04e8\u04ec\5\34\17\2\u04e9"+
		"\u04ec\5\36\20\2\u04ea\u04ec\5\u00ba^\2\u04eb\u04e8\3\2\2\2\u04eb\u04e9"+
		"\3\2\2\2\u04eb\u04ea\3\2\2\2\u04ec\u04ef\3\2\2\2\u04ed\u04eb\3\2\2\2\u04ed"+
		"\u04ee\3\2\2\2\u04ee\u00b7\3\2\2\2\u04ef\u04ed\3\2\2\2\u04f0\u04f1\7\u0084"+
		"\2\2\u04f1\u04f2\7\u0091\2\2\u04f2\u04f3\5\u00bc_\2\u04f3\u00b9\3\2\2"+
		"\2\u04f4\u04f5\7\u0091\2\2\u04f5\u04f6\5\u00bc_\2\u04f6\u00bb\3\2\2\2"+
		"\u04f7\u04f8\7\37\2\2\u04f8\u04fd\5\u00be`\2\u04f9\u04fa\7\7\2\2\u04fa"+
		"\u04fc\5\u00be`\2\u04fb\u04f9\3\2\2\2\u04fc\u04ff\3\2\2\2\u04fd\u04fb"+
		"\3\2\2\2\u04fd\u04fe\3\2\2\2\u04fe\u0501\3\2\2\2\u04ff\u04fd\3\2\2\2\u0500"+
		"\u0502\7\7\2\2\u0501\u0500\3\2\2\2\u0501\u0502\3\2\2\2\u0502\u0503\3\2"+
		"\2\2\u0503\u0504\7 \2\2\u0504\u0508\3\2\2\2\u0505\u0506\7\37\2\2\u0506"+
		"\u0508\7 \2\2\u0507\u04f7\3\2\2\2\u0507\u0505\3\2\2\2\u0508\u00bd\3\2"+
		"\2\2\u0509\u050a\7\u00b0\2\2\u050a\u050b\7\3\2\2\u050b\u050c\5\u00c2b"+
		"\2\u050c\u00bf\3\2\2\2\u050d\u050e\7\b\2\2\u050e\u0513\5\u00c2b\2\u050f"+
		"\u0510\7\7\2\2\u0510\u0512\5\u00c2b\2\u0511\u050f\3\2\2\2\u0512\u0515"+
		"\3\2\2\2\u0513\u0511\3\2\2\2\u0513\u0514\3\2\2\2\u0514\u0517\3\2\2\2\u0515"+
		"\u0513\3\2\2\2\u0516\u0518\7\7\2\2\u0517\u0516\3\2\2\2\u0517\u0518\3\2"+
		"\2\2\u0518\u0519\3\2\2\2\u0519\u051a\7\t\2\2\u051a\u051e\3\2\2\2\u051b"+
		"\u051c\7\b\2\2\u051c\u051e\7\t\2\2\u051d\u050d\3\2\2\2\u051d\u051b\3\2"+
		"\2\2\u051e\u00c1\3\2\2\2\u051f\u0528\7\u00b0\2\2\u0520\u0528\7\u00ae\2"+
		"\2\u0521\u0528\7\u00b2\2\2\u0522\u0528\5\u00bc_\2\u0523\u0528\5\u00c0"+
		"a\2\u0524\u0528\7\34\2\2\u0525\u0528\7\35\2\2\u0526\u0528\7!\2\2\u0527"+
		"\u051f\3\2\2\2\u0527\u0520\3\2\2\2\u0527\u0521\3\2\2\2\u0527\u0522\3\2"+
		"\2\2\u0527\u0523\3\2\2\2\u0527\u0524\3\2\2\2\u0527\u0525\3\2\2\2\u0527"+
		"\u0526\3\2\2\2\u0528\u00c3\3\2\2\2\u0529\u052a\7\"\2\2\u052a\u052b\7\u00b3"+
		"\2\2\u052b\u052c\7\37\2\2\u052c\u052d\5\u00c6d\2\u052d\u052e\7 \2\2\u052e"+
		"\u00c5\3\2\2\2\u052f\u0534\5\34\17\2\u0530\u0534\5\36\20\2\u0531\u0534"+
		"\5\u00b2Z\2\u0532\u0534\5\u00caf\2\u0533\u052f\3\2\2\2\u0533\u0530\3\2"+
		"\2\2\u0533\u0531\3\2\2\2\u0533\u0532\3\2\2\2\u0534\u0537\3\2\2\2\u0535"+
		"\u0533\3\2\2\2\u0535\u0536\3\2\2\2\u0536\u00c7\3\2\2\2\u0537\u0535\3\2"+
		"\2\2\u0538\u0539\5V,\2\u0539\u00c9\3\2\2\2\u053a\u053b\7#\2\2\u053b\u053c"+
		"\7\u00b3\2\2\u053c\u053d\7p\2\2\u053d\u0549\7\u00b3\2\2\u053e\u053f\7"+
		"#\2\2\u053f\u0542\7\u00b3\2\2\u0540\u0541\7p\2\2\u0541\u0543\7\u00b3\2"+
		"\2\u0542\u0540\3\2\2\2\u0542\u0543\3\2\2\2\u0543\u0544\3\2\2\2\u0544\u0545"+
		"\7\37\2\2\u0545\u0546\5\u00ccg\2\u0546\u0547\7 \2\2\u0547\u0549\3\2\2"+
		"\2\u0548\u053a\3\2\2\2\u0548\u053e\3\2\2\2\u0549\u00cb\3\2\2\2\u054a\u0553"+
		"\5\34\17\2\u054b\u0553\5\36\20\2\u054c\u0553\5\u00ceh\2\u054d\u0553\5"+
		"\u00d0i\2\u054e\u0553\5\u00eav\2\u054f\u0553\5V,\2\u0550\u0553\5\u0134"+
		"\u009b\2\u0551\u0553\5\u00b4[\2\u0552\u054a\3\2\2\2\u0552\u054b\3\2\2"+
		"\2\u0552\u054c\3\2\2\2\u0552\u054d\3\2\2\2\u0552\u054e\3\2\2\2\u0552\u054f"+
		"\3\2\2\2\u0552\u0550\3\2\2\2\u0552\u0551\3\2\2\2\u0553\u0556\3\2\2\2\u0554"+
		"\u0552\3\2\2\2\u0554\u0555\3\2\2\2\u0555\u00cd\3\2\2\2\u0556\u0554\3\2"+
		"\2\2\u0557\u0558\7t\2\2\u0558\u0559\5\u0082B\2\u0559\u00cf\3\2\2\2\u055a"+
		"\u055b\7k\2\2\u055b\u055c\7\37\2\2\u055c\u055d\5\u00d2j\2\u055d\u055e"+
		"\7 \2\2\u055e\u00d1\3\2\2\2\u055f\u0567\5\u00d8m\2\u0560\u0567\5\u00da"+
		"n\2\u0561\u0567\5\u00dco\2\u0562\u0567\5\u00d4k\2\u0563\u0567\5\u00d6"+
		"l\2\u0564\u0567\5\u00e4s\2\u0565\u0567\5\u00e6t\2\u0566\u055f\3\2\2\2"+
		"\u0566\u0560\3\2\2\2\u0566\u0561\3\2\2\2\u0566\u0562\3\2\2\2\u0566\u0563"+
		"\3\2\2\2\u0566\u0564\3\2\2\2\u0566\u0565\3\2\2\2\u0567\u056a\3\2\2\2\u0568"+
		"\u0566\3\2\2\2\u0568\u0569\3\2\2\2\u0569\u00d3\3\2\2\2\u056a\u0568\3\2"+
		"\2\2\u056b\u056c\7\u0087\2\2\u056c\u056d\5\2\2\2\u056d\u00d5\3\2\2\2\u056e"+
		"\u056f\7\u008a\2\2\u056f\u0570\5:\36\2\u0570\u00d7\3\2\2\2\u0571\u0572"+
		"\7p\2\2\u0572\u0574\5\2\2\2\u0573\u0575\7\u009e\2\2\u0574\u0573\3\2\2"+
		"\2\u0574\u0575\3\2\2\2\u0575\u0576\3\2\2\2\u0576\u0577\5\2\2\2\u0577\u00d9"+
		"\3\2\2\2\u0578\u0579\7p\2\2\u0579\u057a\5\2\2\2\u057a\u057b\t\22\2\2\u057b"+
		"\u057c\t\23\2\2\u057c\u00db\3\2\2\2\u057d\u057f\5\u018e\u00c8\2\u057e"+
		"\u057d\3\2\2\2\u057f\u0582\3\2\2\2\u0580\u057e\3\2\2\2\u0580\u0581\3\2"+
		"\2\2\u0581\u0583\3\2\2\2\u0582\u0580\3\2\2\2\u0583\u0584\5\u018c\u00c7"+
		"\2\u0584\u0587\5\2\2\2\u0585\u0586\7u\2\2\u0586\u0588\5\2\2\2\u0587\u0585"+
		"\3\2\2\2\u0587\u0588\3\2\2\2\u0588\u0589\3\2\2\2\u0589\u058a\7l\2\2\u058a"+
		"\u058b\7\37\2\2\u058b\u058c\5\u00dep\2\u058c\u0591\7 \2\2\u058d\u058e"+
		"\7\37\2\2\u058e\u058f\5<\37\2\u058f\u0590\7 \2\2\u0590\u0592\3\2\2\2\u0591"+
		"\u058d\3\2\2\2\u0591\u0592\3\2\2\2\u0592\u00dd\3\2\2\2\u0593\u0595\5z"+
		">\2\u0594\u0593\3\2\2\2\u0595\u0598\3\2\2\2\u0596\u0594\3\2\2\2\u0596"+
		"\u0597\3\2\2\2\u0597\u00df\3\2\2\2\u0598\u0596\3\2\2\2\u0599\u059a\7\u0086"+
		"\2\2\u059a\u059b\7;\2\2\u059b\u059c\7\6\2\2\u059c\u05a1\5\2\2\2\u059d"+
		"\u059e\7\7\2\2\u059e\u05a0\5\2\2\2\u059f\u059d\3\2\2\2\u05a0\u05a3\3\2"+
		"\2\2\u05a1\u059f\3\2\2\2\u05a1\u05a2\3\2\2\2\u05a2\u05a4\3\2\2\2\u05a3"+
		"\u05a1\3\2\2\2\u05a4\u05a9\7\4\2\2\u05a5\u05a6\7\37\2\2\u05a6\u05a7\5"+
		"\34\17\2\u05a7\u05a8\7 \2\2\u05a8\u05aa\3\2\2\2\u05a9\u05a5\3\2\2\2\u05a9"+
		"\u05aa\3\2\2\2\u05aa\u00e1\3\2\2\2\u05ab\u05ac\7\u0087\2\2\u05ac\u05ad"+
		"\7;\2\2\u05ad\u05ae\7\6\2\2\u05ae\u05b3\5\2\2\2\u05af\u05b0\7\7\2\2\u05b0"+
		"\u05b2\5\2\2\2\u05b1\u05af\3\2\2\2\u05b2\u05b5\3\2\2\2\u05b3\u05b1\3\2"+
		"\2\2\u05b3\u05b4\3\2\2\2\u05b4\u05b6\3\2\2\2\u05b5\u05b3\3\2\2\2\u05b6"+
		"\u05bb\7\4\2\2\u05b7\u05b8\7\37\2\2\u05b8\u05b9\5\34\17\2\u05b9\u05ba"+
		"\7 \2\2\u05ba\u05bc\3\2\2\2\u05bb\u05b7\3\2\2\2\u05bb\u05bc\3\2\2\2\u05bc"+
		"\u00e3\3\2\2\2\u05bd\u05bf\7*\2\2\u05be\u05bd\3\2\2\2\u05be\u05bf\3\2"+
		"\2\2\u05bf\u05c0\3\2\2\2\u05c0\u05c1\7.\2\2\u05c1\u05c2\5\u018c\u00c7"+
		"\2\u05c2\u05c3\5\2\2\2\u05c3\u05c4\7\37\2\2\u05c4\u05c5\5\u00e8u\2\u05c5"+
		"\u05c6\7 \2\2\u05c6\u00e5\3\2\2\2\u05c7\u05c8\5\2\2\2\u05c8\u05c9\7\37"+
		"\2\2\u05c9\u05ca\5\u00e8u\2\u05ca\u05cb\7 \2\2\u05cb\u00e7\3\2\2\2\u05cc"+
		"\u05cf\5\34\17\2\u05cd\u05cf\5\36\20\2\u05ce\u05cc\3\2\2\2\u05ce\u05cd"+
		"\3\2\2\2\u05cf\u05d2\3\2\2\2\u05d0\u05ce\3\2\2\2\u05d0\u05d1\3\2\2\2\u05d1"+
		"\u00e9\3\2\2\2\u05d2\u05d0\3\2\2\2\u05d3\u05d4\7\64\2\2\u05d4\u05d5\7"+
		"\37\2\2\u05d5\u05d6\5\u00ecw\2\u05d6\u05d7\7 \2\2\u05d7\u00eb\3\2\2\2"+
		"\u05d8\u05da\5\u00eex\2\u05d9\u05d8\3\2\2\2\u05da\u05dd\3\2\2\2\u05db"+
		"\u05d9\3\2\2\2\u05db\u05dc\3\2\2\2\u05dc\u00ed\3\2\2\2\u05dd\u05db\3\2"+
		"\2\2\u05de\u05e0\7#\2\2\u05df\u05de\3\2\2\2\u05df\u05e0\3\2\2\2\u05e0"+
		"\u05e1\3\2\2\2\u05e1\u05e2\5\2\2\2\u05e2\u05e3\7\37\2\2\u05e3\u05e4\5"+
		"\u00f0y\2\u05e4\u05e5\7 \2\2\u05e5\u00ef\3\2\2\2\u05e6\u05e9\5\34\17\2"+
		"\u05e7\u05e9\5\36\20\2\u05e8\u05e6\3\2\2\2\u05e8\u05e7\3\2\2\2\u05e9\u05ec"+
		"\3\2\2\2\u05ea\u05e8\3\2\2\2\u05ea\u05eb\3\2\2\2\u05eb\u00f1\3\2\2\2\u05ec"+
		"\u05ea\3\2\2\2\u05ed\u05ee\7\u0090\2\2\u05ee\u05ef\5\2\2\2\u05ef\u05f0"+
		"\7\37\2\2\u05f0\u05f1\5\u00f4{\2\u05f1\u05f2\7 \2\2\u05f2\u00f3\3\2\2"+
		"\2\u05f3\u05f7\5\34\17\2\u05f4\u05f7\5\u00f6|\2\u05f5\u05f7\5\u00f8}\2"+
		"\u05f6\u05f3\3\2\2\2\u05f6\u05f4\3\2\2\2\u05f6\u05f5\3\2\2\2\u05f7\u05fa"+
		"\3\2\2\2\u05f8\u05f6\3\2\2\2\u05f8\u05f9\3\2\2\2\u05f9\u00f5\3\2\2\2\u05fa"+
		"\u05f8\3\2\2\2\u05fb\u05fc\7\u009d\2\2\u05fc\u05fd\5\2\2\2\u05fd\u00f7"+
		"\3\2\2\2\u05fe\u05ff\7\u008d\2\2\u05ff\u0600\7\u0095\2\2\u0600\u0601\5"+
		"\2\2\2\u0601\u0602\7\37\2\2\u0602\u0603\5\u00fa~\2\u0603\u0604\7 \2\2"+
		"\u0604\u00f9\3\2\2\2\u0605\u060a\5\34\17\2\u0606\u060a\5\u0110\u0089\2"+
		"\u0607\u060a\5\u0126\u0094\2\u0608\u060a\5\u00fc\177\2\u0609\u0605\3\2"+
		"\2\2\u0609\u0606\3\2\2\2\u0609\u0607\3\2\2\2\u0609\u0608\3\2\2\2\u060a"+
		"\u060d\3\2\2\2\u060b\u0609\3\2\2\2\u060b\u060c\3\2\2\2\u060c\u00fb\3\2"+
		"\2\2\u060d\u060b\3\2\2\2\u060e\u060f\7\u0091\2\2\u060f\u0610\7\37\2\2"+
		"\u0610\u0611\5\u00fe\u0080\2\u0611\u0612\7 \2\2\u0612\u00fd\3\2\2\2\u0613"+
		"\u0616\5\u0100\u0081\2\u0614\u0616\5\u0104\u0083\2\u0615\u0613\3\2\2\2"+
		"\u0615\u0614\3\2\2\2\u0616\u0619\3\2\2\2\u0617\u0615\3\2\2\2\u0617\u0618"+
		"\3\2\2\2\u0618\u00ff\3\2\2\2\u0619\u0617\3\2\2\2\u061a\u061b\7\u0093\2"+
		"\2\u061b\u061c\5\u0102\u0082\2\u061c\u061f\5\2\2\2\u061d\u061e\7\36\2"+
		"\2\u061e\u0620\5\2\2\2\u061f\u061d\3\2\2\2\u061f\u0620\3\2\2\2\u0620\u0625"+
		"\3\2\2\2\u0621\u0622\7\37\2\2\u0622\u0623\5\u0108\u0085\2\u0623\u0624"+
		"\7 \2\2\u0624\u0626\3\2\2\2\u0625\u0621\3\2\2\2\u0625\u0626\3\2\2\2\u0626"+
		"\u0101\3\2\2\2\u0627\u0628\t\23\2\2\u0628\u0103\3\2\2\2\u0629\u062a\7"+
		"\u0094\2\2\u062a\u062b\5\u0106\u0084\2\u062b\u062e\5\2\2\2\u062c\u062d"+
		"\7\36\2\2\u062d\u062f\5\2\2\2\u062e\u062c\3\2\2\2\u062e\u062f\3\2\2\2"+
		"\u062f\u0634\3\2\2\2\u0630\u0631\7\37\2\2\u0631\u0632\5\u010a\u0086\2"+
		"\u0632\u0633\7 \2\2\u0633\u0635\3\2\2\2\u0634\u0630\3\2\2\2\u0634\u0635"+
		"\3\2\2\2\u0635\u0105\3\2\2\2\u0636\u0637\t\24\2\2\u0637\u0107\3\2\2\2"+
		"\u0638\u063a\5\34\17\2\u0639\u0638\3\2\2\2\u063a\u063d\3\2\2\2\u063b\u0639"+
		"\3\2\2\2\u063b\u063c\3\2\2\2\u063c\u0109\3\2\2\2\u063d\u063b\3\2\2\2\u063e"+
		"\u0640\5\34\17\2\u063f\u063e\3\2\2\2\u0640\u0643\3\2\2\2\u0641\u063f\3"+
		"\2\2\2\u0641\u0642\3\2\2\2\u0642\u010b\3\2\2\2\u0643\u0641\3\2\2\2\u0644"+
		"\u0645\7m\2\2\u0645\u064a\5\2\2\2\u0646\u0647\7\37\2\2\u0647\u0648\5\u010e"+
		"\u0088\2\u0648\u0649\7 \2\2\u0649\u064b\3\2\2\2\u064a\u0646\3\2\2\2\u064a"+
		"\u064b\3\2\2\2\u064b\u010d\3\2\2\2\u064c\u064e\5\34\17\2\u064d\u064c\3"+
		"\2\2\2\u064e\u0651\3\2\2\2\u064f\u064d\3\2\2\2\u064f\u0650\3\2\2\2\u0650"+
		"\u010f\3\2\2\2\u0651\u064f\3\2\2\2\u0652\u0653\7\u0096\2\2\u0653\u0654"+
		"\7\37\2\2\u0654\u0655\5\u0112\u008a\2\u0655\u0656\7 \2\2\u0656\u0111\3"+
		"\2\2\2\u0657\u065c\5\34\17\2\u0658\u065c\5\u010c\u0087\2\u0659\u065c\5"+
		"\u0114\u008b\2\u065a\u065c\5\u011c\u008f\2\u065b\u0657\3\2\2\2\u065b\u0658"+
		"\3\2\2\2\u065b\u0659\3\2\2\2\u065b\u065a\3\2\2\2\u065c\u065f\3\2\2\2\u065d"+
		"\u065b\3\2\2\2\u065d\u065e\3\2\2\2\u065e\u0113\3\2\2\2\u065f\u065d\3\2"+
		"\2\2\u0660\u0661\7J\2\2\u0661\u0666\t\25\2\2\u0662\u0663\7\37\2\2\u0663"+
		"\u0664\5\u0116\u008c\2\u0664\u0665\7 \2\2\u0665\u0667\3\2\2\2\u0666\u0662"+
		"\3\2\2\2\u0666\u0667\3\2\2\2\u0667\u0115\3\2\2\2\u0668\u066b\5\34\17\2"+
		"\u0669\u066b\5\u0118\u008d\2\u066a\u0668\3\2\2\2\u066a\u0669\3\2\2\2\u066b"+
		"\u066e\3\2\2\2\u066c\u066a\3\2\2\2\u066c\u066d\3\2\2\2\u066d\u0117\3\2"+
		"\2\2\u066e\u066c\3\2\2\2\u066f\u0671\7\u0099\2\2\u0670\u066f\3\2\2\2\u0670"+
		"\u0671\3\2\2\2\u0671\u0672\3\2\2\2\u0672\u0673\7\u009a\2\2\u0673\u0674"+
		"\5\u018c\u00c7\2\u0674\u0679\5\2\2\2\u0675\u0676\7\37\2\2\u0676\u0677"+
		"\5\u011a\u008e\2\u0677\u0678\7 \2\2\u0678\u067a\3\2\2\2\u0679\u0675\3"+
		"\2\2\2\u0679\u067a\3\2\2\2\u067a\u0119\3\2\2\2\u067b\u067d\5\34\17\2\u067c"+
		"\u067b\3\2\2\2\u067d\u0680\3\2\2\2\u067e\u067c\3\2\2\2\u067e\u067f\3\2"+
		"\2\2\u067f\u011b\3\2\2\2\u0680\u067e\3\2\2\2\u0681\u0686\7\u0098\2\2\u0682"+
		"\u0683\7\37\2\2\u0683\u0684\5\u011e\u0090\2\u0684\u0685\7 \2\2\u0685\u0687"+
		"\3\2\2\2\u0686\u0682\3\2\2\2\u0686\u0687\3\2\2\2\u0687\u011d\3\2\2\2\u0688"+
		"\u068d\5\34\17\2\u0689\u068d\5\u0120\u0091\2\u068a\u068d\5\u0122\u0092"+
		"\2\u068b\u068d\5\u0124\u0093\2\u068c\u0688\3\2\2\2\u068c\u0689\3\2\2\2"+
		"\u068c\u068a\3\2\2\2\u068c\u068b\3\2\2\2\u068d\u0690\3\2\2\2\u068e\u068c"+
		"\3\2\2\2\u068e\u068f\3\2\2\2\u068f\u011f\3\2\2\2\u0690\u068e\3\2\2\2\u0691"+
		"\u0692\7b\2\2\u0692\u0693\7\u00b0\2\2\u0693\u0121\3\2\2\2\u0694\u0695"+
		"\7\u0085\2\2\u0695\u0696\5\2\2\2\u0696\u0123\3\2\2\2\u0697\u0698\7j\2"+
		"\2\u0698\u0699\5\2\2\2\u0699\u0125\3\2\2\2\u069a\u069b\7\u0097\2\2\u069b"+
		"\u069c\7\37\2\2\u069c\u069d\5\u0128\u0095\2\u069d\u069e\7 \2\2\u069e\u0127"+
		"\3\2\2\2\u069f\u06a3\5\34\17\2\u06a0\u06a3\5\u0130\u0099\2\u06a1\u06a3"+
		"\5\u012c\u0097\2\u06a2\u069f\3\2\2\2\u06a2\u06a0\3\2\2\2\u06a2\u06a1\3"+
		"\2\2\2\u06a3\u06a6\3\2\2\2\u06a4\u06a2\3\2\2\2\u06a4\u06a5\3\2\2\2\u06a5"+
		"\u0129\3\2\2\2\u06a6\u06a4\3\2\2\2\u06a7\u06a8\7~\2\2\u06a8\u06a9\5\20"+
		"\t\2\u06a9\u012b\3\2\2\2\u06aa\u06ad\7\u009b\2\2\u06ab\u06ae\5\2\2\2\u06ac"+
		"\u06ae\7\u00ae\2\2\u06ad\u06ab\3\2\2\2\u06ad\u06ac\3\2\2\2\u06ae\u06b0"+
		"\3\2\2\2\u06af\u06b1\5\u012a\u0096\2\u06b0\u06af\3\2\2\2\u06b0\u06b1\3"+
		"\2\2\2\u06b1\u06b6\3\2\2\2\u06b2\u06b3\7\37\2\2\u06b3\u06b4\5\u012e\u0098"+
		"\2\u06b4\u06b5\7 \2\2\u06b5\u06b7\3\2\2\2\u06b6\u06b2\3\2\2\2\u06b6\u06b7"+
		"\3\2\2\2\u06b7\u012d\3\2\2\2\u06b8\u06ba\5\34\17\2\u06b9\u06b8\3\2\2\2"+
		"\u06ba\u06bd\3\2\2\2\u06bb\u06b9\3\2\2\2\u06bb\u06bc\3\2\2\2\u06bc\u012f"+
		"\3\2\2\2\u06bd\u06bb\3\2\2\2\u06be\u06c3\7\u0098\2\2\u06bf\u06c0\7\37"+
		"\2\2\u06c0\u06c1\5\u0132\u009a\2\u06c1\u06c2\7 \2\2\u06c2\u06c4\3\2\2"+
		"\2\u06c3\u06bf\3\2\2\2\u06c3\u06c4\3\2\2\2\u06c4\u0131\3\2\2\2\u06c5\u06ca"+
		"\5\34\17\2\u06c6\u06ca\5\u0120\u0091\2\u06c7\u06ca\5\u0122\u0092\2\u06c8"+
		"\u06ca\5\u0124\u0093\2\u06c9\u06c5\3\2\2\2\u06c9\u06c6\3\2\2\2\u06c9\u06c7"+
		"\3\2\2\2\u06c9\u06c8\3\2\2\2\u06ca\u06cd\3\2\2\2\u06cb\u06c9\3\2\2\2\u06cb"+
		"\u06cc\3\2\2\2\u06cc\u0133\3\2\2\2\u06cd\u06cb\3\2\2\2\u06ce\u06cf\7\u0090"+
		"\2\2\u06cf\u06d4\5\2\2\2\u06d0\u06d1\7\37\2\2\u06d1\u06d2\5\u0136\u009c"+
		"\2\u06d2\u06d3\7 \2\2\u06d3\u06d5\3\2\2\2\u06d4\u06d0\3\2\2\2\u06d4\u06d5"+
		"\3\2\2\2\u06d5\u0135\3\2\2\2\u06d6\u06d9\5\34\17\2\u06d7\u06d9\5\u0138"+
		"\u009d\2\u06d8\u06d6\3\2\2\2\u06d8\u06d7\3\2\2\2\u06d9\u06dc\3\2\2\2\u06da"+
		"\u06d8\3\2\2\2\u06da\u06db\3\2\2\2\u06db\u0137\3\2\2\2\u06dc\u06da\3\2"+
		"\2\2\u06dd\u06de\7\u0095\2\2\u06de\u06df\5\2\2\2\u06df\u06e0\7\37\2\2"+
		"\u06e0\u06e1\5\u0142\u00a2\2\u06e1\u06e2\7 \2\2\u06e2\u06ec\3\2\2\2\u06e3"+
		"\u06e4\7\u0095\2\2\u06e4\u06e5\5\2\2\2\u06e5\u06e6\7\u008e\2\2\u06e6\u06e7"+
		"\5\2\2\2\u06e7\u06e8\7\37\2\2\u06e8\u06e9\5\u013a\u009e\2\u06e9\u06ea"+
		"\7 \2\2\u06ea\u06ec\3\2\2\2\u06eb\u06dd\3\2\2\2\u06eb\u06e3\3\2\2\2\u06ec"+
		"\u0139\3\2\2\2\u06ed\u06f2\5\34\17\2\u06ee\u06f2\5\u013c\u009f\2\u06ef"+
		"\u06f2\5\u0110\u0089\2\u06f0\u06f2\5\u0126\u0094\2\u06f1\u06ed\3\2\2\2"+
		"\u06f1\u06ee\3\2\2\2\u06f1\u06ef\3\2\2\2\u06f1\u06f0\3\2\2\2\u06f2\u06f5"+
		"\3\2\2\2\u06f3\u06f1\3\2\2\2\u06f3\u06f4\3\2\2\2\u06f4\u013b\3\2\2\2\u06f5"+
		"\u06f3\3\2\2\2\u06f6\u06f8\7\u0091\2\2\u06f7\u06f9\5\2\2\2\u06f8\u06f7"+
		"\3\2\2\2\u06f8\u06f9\3\2\2\2\u06f9\u06fa\3\2\2\2\u06fa\u06fb\7\37\2\2"+
		"\u06fb\u06fc\5\u013e\u00a0\2\u06fc\u06fd\7 \2\2\u06fd\u013d\3\2\2\2\u06fe"+
		"\u0700\5\u0140\u00a1\2\u06ff\u06fe\3\2\2\2\u0700\u0703\3\2\2\2\u0701\u06ff"+
		"\3\2\2\2\u0701\u0702\3\2\2\2\u0702\u013f\3\2\2\2\u0703\u0701\3\2\2\2\u0704"+
		"\u0705\5\2\2\2\u0705\u070a\7\36\2\2\u0706\u070b\5\2\2\2\u0707\u070b\7"+
		"\u00b0\2\2\u0708\u070b\7\u00ae\2\2\u0709\u070b\7\u00b2\2\2\u070a\u0706"+
		"\3\2\2\2\u070a\u0707\3\2\2\2\u070a\u0708\3\2\2\2\u070a\u0709\3\2\2\2\u070b"+
		"\u0710\3\2\2\2\u070c\u070d\7\37\2\2\u070d\u070e\5\34\17\2\u070e\u070f"+
		"\7 \2\2\u070f\u0711\3\2\2\2\u0710\u070c\3\2\2\2\u0710\u0711\3\2\2\2\u0711"+
		"\u0141\3\2\2\2\u0712\u0716\5\34\17\2\u0713\u0716\5\u0110\u0089\2\u0714"+
		"\u0716\5\u0126\u0094\2\u0715\u0712\3\2\2\2\u0715\u0713\3\2\2\2\u0715\u0714"+
		"\3\2\2\2\u0716\u0719\3\2\2\2\u0717\u0715\3\2\2\2\u0717\u0718\3\2\2\2\u0718"+
		"\u0143\3\2\2\2\u0719\u0717\3\2\2\2\u071a\u071b\7@\2\2\u071b\u071c\5\2"+
		"\2\2\u071c\u071d\7\37\2\2\u071d\u071e\5\u0146\u00a4\2\u071e\u071f\7 \2"+
		"\2\u071f\u0145\3\2\2\2\u0720\u0726\5\u014e\u00a8\2\u0721\u0726\5\u0154"+
		"\u00ab\2\u0722\u0726\5\u0160\u00b1\2\u0723\u0726\5\u0186\u00c4\2\u0724"+
		"\u0726\5\u0164\u00b3\2\u0725\u0720\3\2\2\2\u0725\u0721\3\2\2\2\u0725\u0722"+
		"\3\2\2\2\u0725\u0723\3\2\2\2\u0725\u0724\3\2\2\2\u0726\u0729\3\2\2\2\u0727"+
		"\u0725\3\2\2\2\u0727\u0728\3\2\2\2\u0728\u0147\3\2\2\2\u0729\u0727\3\2"+
		"\2\2\u072a\u072b\7A\2\2\u072b\u072c\7\37\2\2\u072c\u072d\5\u014a\u00a6"+
		"\2\u072d\u072e\7 \2\2\u072e\u0149\3\2\2\2\u072f\u0731\5\u014c\u00a7\2"+
		"\u0730\u072f\3\2\2\2\u0731\u0734\3\2\2\2\u0732\u0730\3\2\2\2\u0732\u0733"+
		"\3\2\2\2\u0733\u014b\3\2\2\2\u0734\u0732\3\2\2\2\u0735\u0736\7B\2\2\u0736"+
		"\u0737\5\2\2\2\u0737\u0738\5\u00bc_\2\u0738\u014d\3\2\2\2\u0739\u073a"+
		"\t\26\2\2\u073a\u073b\5\2\2\2\u073b\u073c\7\37\2\2\u073c\u073d\5\u0150"+
		"\u00a9\2\u073d\u073e\7 \2\2\u073e\u014f\3\2\2\2\u073f\u0742\5\u0152\u00aa"+
		"\2\u0740\u0742\5\34\17\2\u0741\u073f\3\2\2\2\u0741\u0740\3\2\2\2\u0742"+
		"\u0745\3\2\2\2\u0743\u0741\3\2\2\2\u0743\u0744\3\2\2\2\u0744\u0151\3\2"+
		"\2\2\u0745\u0743\3\2\2\2\u0746\u0749\7I\2\2\u0747\u074a\7\u00b0\2\2\u0748"+
		"\u074a\5\2\2\2\u0749\u0747\3\2\2\2\u0749\u0748\3\2\2\2\u074a\u0153\3\2"+
		"\2\2\u074b\u074c\7G\2\2\u074c\u074d\7\37\2\2\u074d\u074e\5\u0156\u00ac"+
		"\2\u074e\u074f\7 \2\2\u074f\u0155\3\2\2\2\u0750\u0752\5\u015c\u00af\2"+
		"\u0751\u0750\3\2\2\2\u0751\u0752\3\2\2\2\u0752\u0754\3\2\2\2\u0753\u0755"+
		"\5\u00b8]\2\u0754\u0753\3\2\2\2\u0754\u0755\3\2\2\2\u0755\u0759\3\2\2"+
		"\2\u0756\u0758\5\u0158\u00ad\2\u0757\u0756\3\2\2\2\u0758\u075b\3\2\2\2"+
		"\u0759\u0757\3\2\2\2\u0759\u075a\3\2\2\2\u075a\u0157\3\2\2\2\u075b\u0759"+
		"\3\2\2\2\u075c\u075e\7H\2\2\u075d\u075c\3\2\2\2\u075d\u075e\3\2\2\2\u075e"+
		"\u075f\3\2\2\2\u075f\u0760\7F\2\2\u0760\u0763\5\2\2\2\u0761\u0762\7u\2"+
		"\2\u0762\u0764\7\u00b0\2\2\u0763\u0761\3\2\2\2\u0763\u0764\3\2\2\2\u0764"+
		"\u0765\3\2\2\2\u0765\u0766\7\37\2\2\u0766\u0767\5\u015a\u00ae\2\u0767"+
		"\u0768\7 \2\2\u0768\u0159\3\2\2\2\u0769\u076e\5\34\17\2\u076a\u076e\5"+
		"\36\20\2\u076b\u076e\5\u015e\u00b0\2\u076c\u076e\5\u00ba^\2\u076d\u0769"+
		"\3\2\2\2\u076d\u076a\3\2\2\2\u076d\u076b\3\2\2\2\u076d\u076c\3\2\2\2\u076e"+
		"\u0771\3\2\2\2\u076f\u076d\3\2\2\2\u076f\u0770\3\2\2\2\u0770\u015b\3\2"+
		"\2\2\u0771\u076f\3\2\2\2\u0772\u0773\7Q\2\2\u0773\u0774\7R\2\2\u0774\u0775"+
		"\5\2\2\2\u0775\u015d\3\2\2\2\u0776\u0777\7C\2\2\u0777\u0778\7\61\2\2\u0778"+
		"\u077e\5\2\2\2\u0779\u077a\7C\2\2\u077a\u077b\5\2\2\2\u077b\u077c\5\2"+
		"\2\2\u077c\u077e\3\2\2\2\u077d\u0776\3\2\2\2\u077d\u0779\3\2\2\2\u077e"+
		"\u015f\3\2\2\2\u077f\u0780\7E\2\2\u0780\u0781\5\2\2\2\u0781\u0782\7\37"+
		"\2\2\u0782\u0783\5\u0162\u00b2\2\u0783\u0784\7 \2\2\u0784\u0161\3\2\2"+
		"\2\u0785\u0787\5\u015e\u00b0\2\u0786\u0785\3\2\2\2\u0787\u078a\3\2\2\2"+
		"\u0788\u0786\3\2\2\2\u0788\u0789\3\2\2\2\u0789\u0163\3\2\2\2\u078a\u0788"+
		"\3\2\2\2\u078b\u078c\7L\2\2\u078c\u078d\5\2\2\2\u078d\u078e\7\37\2\2\u078e"+
		"\u078f\5\u0166\u00b4\2\u078f\u0790\7 \2\2\u0790\u0165\3\2\2\2\u0791\u0798"+
		"\5\34\17\2\u0792\u0798\5\u0168\u00b5\2\u0793\u0798\5\u016a\u00b6\2\u0794"+
		"\u0798\5\u016c\u00b7\2\u0795\u0798\5\u016e\u00b8\2\u0796\u0798\5\u0170"+
		"\u00b9\2\u0797\u0791\3\2\2\2\u0797\u0792\3\2\2\2\u0797\u0793\3\2\2\2\u0797"+
		"\u0794\3\2\2\2\u0797\u0795\3\2\2\2\u0797\u0796\3\2\2\2\u0798\u079b\3\2"+
		"\2\2\u0799\u0797\3\2\2\2\u0799\u079a\3\2\2\2\u079a\u0167\3\2\2\2\u079b"+
		"\u0799\3\2\2\2\u079c\u079d\7\u009d\2\2\u079d\u079e\5\2\2\2\u079e\u0169"+
		"\3\2\2\2\u079f\u07a0\7S\2\2\u07a0\u07a1\7\u00b0\2\2\u07a1\u016b\3\2\2"+
		"\2\u07a2\u07a3\7T\2\2\u07a3\u07a4\7\u00b0\2\2\u07a4\u016d\3\2\2\2\u07a5"+
		"\u07a6\7I\2\2\u07a6\u07a7\7\u00b0\2\2\u07a7\u016f\3\2\2\2\u07a8\u07a9"+
		"\7N\2\2\u07a9\u07aa\7\u00b0\2\2\u07aa\u0171\3\2\2\2\u07ab\u07ac\7P\2\2"+
		"\u07ac\u07ad\5\2\2\2\u07ad\u07ae\7\37\2\2\u07ae\u07af\5\u0174\u00bb\2"+
		"\u07af\u07b0\7 \2\2\u07b0\u0173\3\2\2\2\u07b1\u07b9\5\34\17\2\u07b2\u07b9"+
		"\5\36\20\2\u07b3\u07b9\5\u0176\u00bc\2\u07b4\u07b9\5\u0178\u00bd\2\u07b5"+
		"\u07b9\5\u017a\u00be\2\u07b6\u07b9\5\u017c\u00bf\2\u07b7\u07b9\5\u0164"+
		"\u00b3\2\u07b8\u07b1\3\2\2\2\u07b8\u07b2\3\2\2\2\u07b8\u07b3\3\2\2\2\u07b8"+
		"\u07b4\3\2\2\2\u07b8\u07b5\3\2\2\2\u07b8\u07b6\3\2\2\2\u07b8\u07b7\3\2"+
		"\2\2\u07b9\u07bc\3\2\2\2\u07ba\u07b8\3\2\2\2\u07ba\u07bb\3\2\2\2\u07bb"+
		"\u0175\3\2\2\2\u07bc\u07ba\3\2\2\2\u07bd\u07be\7t\2\2\u07be\u07bf\5\u0082"+
		"B\2\u07bf\u0177\3\2\2\2\u07c0\u07c1\7\u0092\2\2\u07c1\u07c2\5\u00bc_\2"+
		"\u07c2\u0179\3\2\2\2\u07c3\u07c4\7Q\2\2\u07c4\u07c5\5\u0184\u00c3\2\u07c5"+
		"\u07c6\7R\2\2\u07c6\u07c7\5\2\2\2\u07c7\u017b\3\2\2\2\u07c8\u07c9\7\u0086"+
		"\2\2\u07c9\u07ca\5\u0184\u00c3\2\u07ca\u07cb\7R\2\2\u07cb\u07d0\5\2\2"+
		"\2\u07cc\u07cd\7\37\2\2\u07cd\u07ce\5\u017e\u00c0\2\u07ce\u07cf\7 \2\2"+
		"\u07cf\u07d1\3\2\2\2\u07d0\u07cc\3\2\2\2\u07d0\u07d1\3\2\2\2\u07d1\u017d"+
		"\3\2\2\2\u07d2\u07d5\5\u0180\u00c1\2\u07d3\u07d5\5\u0182\u00c2\2\u07d4"+
		"\u07d2\3\2\2\2\u07d4\u07d3\3\2\2\2\u07d5\u07d8\3\2\2\2\u07d6\u07d4\3\2"+
		"\2\2\u07d6\u07d7\3\2\2\2\u07d7\u017f\3\2\2\2\u07d8\u07d6\3\2\2\2\u07d9"+
		"\u07da\7Q\2\2\u07da\u07db\7<\2\2\u07db\u07dc\5\u0184\u00c3\2\u07dc\u0181"+
		"\3\2\2\2\u07dd\u07de\7Q\2\2\u07de\u07df\7#\2\2\u07df\u07e0\5\u0184\u00c3"+
		"\2\u07e0\u0183\3\2\2\2\u07e1\u07e6\5\2\2\2\u07e2\u07e3\7\7\2\2\u07e3\u07e5"+
		"\5\2\2\2\u07e4\u07e2\3\2\2\2\u07e5\u07e8\3\2\2\2\u07e6\u07e4\3\2\2\2\u07e6"+
		"\u07e7\3\2\2\2\u07e7\u0185\3\2\2\2\u07e8\u07e6\3\2\2\2\u07e9\u07ea\7M"+
		"\2\2\u07ea\u07eb\7\37\2\2\u07eb\u07ec\5\u0188\u00c5\2\u07ec\u07ed\7 \2"+
		"\2\u07ed\u0187\3\2\2\2\u07ee\u07f3\5\u017a\u00be\2\u07ef\u07f3\5\u017c"+
		"\u00bf\2\u07f0\u07f3\5\u015e\u00b0\2\u07f1\u07f3\5\u018a\u00c6\2\u07f2"+
		"\u07ee\3\2\2\2\u07f2\u07ef\3\2\2\2\u07f2\u07f0\3\2\2\2\u07f2\u07f1\3\2"+
		"\2\2\u07f3\u07f6\3\2\2\2\u07f4\u07f2\3\2\2\2\u07f4\u07f5\3\2\2\2\u07f5"+
		"\u0189\3\2\2\2\u07f6\u07f4\3\2\2\2\u07f7\u07f8\7V\2\2\u07f8\u07f9\5\2"+
		"\2\2\u07f9\u018b\3\2\2\2\u07fa\u080b\7\u00a2\2\2\u07fb\u080b\7\u00a3\2"+
		"\2\u07fc\u080b\7\u00a6\2\2\u07fd\u080b\7\u00a7\2\2\u07fe\u080b\7\u00a1"+
		"\2\2\u07ff\u080b\7\u00a9\2\2\u0800\u080b\7\u00a8\2\2\u0801\u080b\7\u00a5"+
		"\2\2\u0802\u080b\7\u00aa\2\2\u0803\u0804\7\u00a4\2\2\u0804\u0806\7\b\2"+
		"\2\u0805\u0807\7\u00ae\2\2\u0806\u0805\3\2\2\2\u0806\u0807\3\2\2\2\u0807"+
		"\u0808\3\2\2\2\u0808\u080b\7\t\2\2\u0809\u080b\5\2\2\2\u080a\u07fa\3\2"+
		"\2\2\u080a\u07fb\3\2\2\2\u080a\u07fc\3\2\2\2\u080a\u07fd\3\2\2\2\u080a"+
		"\u07fe\3\2\2\2\u080a\u07ff\3\2\2\2\u080a\u0800\3\2\2\2\u080a\u0801\3\2"+
		"\2\2\u080a\u0802\3\2\2\2\u080a\u0803\3\2\2\2\u080a\u0809\3\2\2\2\u080b"+
		"\u018d\3\2\2\2\u080c\u080d\t\27\2\2\u080d\u018f\3\2\2\2\u080e\u080f\7"+
		"\u0080\2\2\u080f\u0810\7\37\2\2\u0810\u0811\5\u0192\u00ca\2\u0811\u0812"+
		"\7 \2\2\u0812\u0191\3\2\2\2\u0813\u0815\5\u0194\u00cb\2\u0814\u0813\3"+
		"\2\2\2\u0815\u0818\3\2\2\2\u0816\u0814\3\2\2\2\u0816\u0817\3\2\2\2\u0817"+
		"\u0193\3\2\2\2\u0818\u0816\3\2\2\2\u0819\u081c\5\2\2\2\u081a\u081b\7\u008e"+
		"\2\2\u081b\u081d\5\2\2\2\u081c\u081a\3\2\2\2\u081c\u081d\3\2\2\2\u081d"+
		"\u081e\3\2\2\2\u081e\u081f\7\37\2\2\u081f\u0820\5\u0196\u00cc\2\u0820"+
		"\u0821\7 \2\2\u0821\u0195\3\2\2\2\u0822\u0825\5\34\17\2\u0823\u0825\5"+
		"\u0198\u00cd\2\u0824\u0822\3\2\2\2\u0824\u0823\3\2\2\2\u0825\u0828\3\2"+
		"\2\2\u0826\u0824\3\2\2\2\u0826\u0827\3\2\2\2\u0827\u0197\3\2\2\2\u0828"+
		"\u0826\3\2\2\2\u0829\u082a\7\u0081\2\2\u082a\u082e\7\u00b0\2\2\u082b\u082c"+
		"\7\u0082\2\2\u082c\u082e\7\u00b2\2\2\u082d\u0829\3\2\2\2\u082d\u082b\3"+
		"\2\2\2\u082e\u0199\3\2\2\2\u082f\u0830\7V\2\2\u0830\u0831\5\2\2\2\u0831"+
		"\u0835\7\37\2\2\u0832\u0834\5\u019c\u00cf\2\u0833\u0832\3\2\2\2\u0834"+
		"\u0837\3\2\2\2\u0835\u0833\3\2\2\2\u0835\u0836\3\2\2\2\u0836\u0838\3\2"+
		"\2\2\u0837\u0835\3\2\2\2\u0838\u0839\7 \2\2\u0839\u019b\3\2\2\2\u083a"+
		"\u083b\7X\2\2\u083b\u083c\7\37\2\2\u083c\u083d\5\u019e\u00d0\2\u083d\u083e"+
		"\7 \2\2\u083e\u0866\3\2\2\2\u083f\u0840\7Z\2\2\u0840\u0866\5\2\2\2\u0841"+
		"\u0842\7[\2\2\u0842\u0846\7\37\2\2\u0843\u0845\5\u01a2\u00d2\2\u0844\u0843"+
		"\3\2\2\2\u0845\u0848\3\2\2\2\u0846\u0844\3\2\2\2\u0846\u0847\3\2\2\2\u0847"+
		"\u0849\3\2\2\2\u0848\u0846\3\2\2\2\u0849\u0866\7 \2\2\u084a\u084b\7_\2"+
		"\2\u084b\u084f\7\37\2\2\u084c\u084e\5\u01a6\u00d4\2\u084d\u084c\3\2\2"+
		"\2\u084e\u0851\3\2\2\2\u084f\u084d\3\2\2\2\u084f\u0850\3\2\2\2\u0850\u0852"+
		"\3\2\2\2\u0851\u084f\3\2\2\2\u0852\u0866\7 \2\2\u0853\u0854\7`\2\2\u0854"+
		"\u0858\7\37\2\2\u0855\u0857\5\2\2\2\u0856\u0855\3\2\2\2\u0857\u085a\3"+
		"\2\2\2\u0858\u0856\3\2\2\2\u0858\u0859\3\2\2\2\u0859\u085b\3\2\2\2\u085a"+
		"\u0858\3\2\2\2\u085b\u0866\7 \2\2\u085c\u085d\7a\2\2\u085d\u0861\7\37"+
		"\2\2\u085e\u0860\5\u01a4\u00d3\2\u085f\u085e\3\2\2\2\u0860\u0863\3\2\2"+
		"\2\u0861\u085f\3\2\2\2\u0861\u0862\3\2\2\2\u0862\u0864\3\2\2\2\u0863\u0861"+
		"\3\2\2\2\u0864\u0866\7 \2\2\u0865\u083a\3\2\2\2\u0865\u083f\3\2\2\2\u0865"+
		"\u0841\3\2\2\2\u0865\u084a\3\2\2\2\u0865\u0853\3\2\2\2\u0865\u085c\3\2"+
		"\2\2\u0866\u019d\3\2\2\2\u0867\u0869\5\u01a0\u00d1\2\u0868\u0867\3\2\2"+
		"\2\u0869\u086c\3\2\2\2\u086a\u0868\3\2\2\2\u086a\u086b\3\2\2\2\u086b\u019f"+
		"\3\2\2\2\u086c\u086a\3\2\2\2\u086d\u086e\5\u018c\u00c7\2\u086e\u0870\5"+
		"\2\2\2\u086f\u0871\7Y\2\2\u0870\u086f\3\2\2\2\u0870\u0871\3\2\2\2\u0871"+
		"\u0873\3\2\2\2\u0872\u0874\7U\2\2\u0873\u0872\3\2\2\2\u0873\u0874\3\2"+
		"\2\2\u0874\u087b\3\2\2\2\u0875\u0876\5\u018c\u00c7\2\u0876\u0878\7\u00b0"+
		"\2\2\u0877\u0879\7U\2\2\u0878\u0877\3\2\2\2\u0878\u0879\3\2\2\2\u0879"+
		"\u087b\3\2\2\2\u087a\u086d\3\2\2\2\u087a\u0875\3\2\2\2\u087b\u01a1\3\2"+
		"\2\2\u087c\u087d\t\30\2\2\u087d\u087e\7\u00b0\2\2\u087e\u01a3\3\2\2\2"+
		"\u087f\u0880\5\2\2\2\u0880\u0881\7\6\2\2\u0881\u0882\5\u018c\u00c7\2\u0882"+
		"\u0889\5\2\2\2\u0883\u0884\7\7\2\2\u0884\u0885\5\u018c\u00c7\2\u0885\u0886"+
		"\5\2\2\2\u0886\u0888\3\2\2\2\u0887\u0883\3\2\2\2\u0888\u088b\3\2\2\2\u0889"+
		"\u0887\3\2\2\2\u0889\u088a\3\2\2\2\u088a\u088c\3\2\2\2\u088b\u0889\3\2"+
		"\2\2\u088c\u088d\7\4\2\2\u088d\u088e\7\u00b0\2\2\u088e\u01a5\3\2\2\2\u088f"+
		"\u0893\5\2\2\2\u0890\u0892\7\u00b0\2\2\u0891\u0890\3\2\2\2\u0892\u0895"+
		"\3\2\2\2\u0893\u0891\3\2\2\2\u0893\u0894\3\2\2\2\u0894\u01a7\3\2\2\2\u0895"+
		"\u0893\3\2\2\2\u0896\u0897\7W\2\2\u0897\u0898\7\u00b1\2\2\u0898\u01a9"+
		"\3\2\2\2\u00ee\u01ac\u01b5\u01b7\u01c0\u01cc\u01d2\u01e2\u020a\u0211\u0213"+
		"\u021c\u0222\u022f\u0232\u023f\u0241\u0249\u024c\u0256\u025c\u026a\u026c"+
		"\u0274\u0277\u027a\u027f\u0283\u028e\u029a\u029c\u02ab\u02b6\u02bc\u02c3"+
		"\u02c8\u02ca\u02d0\u02d7\u02d9\u02e1\u02f0\u02f2\u02fd\u0304\u030a\u030d"+
		"\u0310\u0315\u0318\u031b\u0321\u0323\u0326\u0331\u0341\u0343\u0347\u0355"+
		"\u0357\u0361\u0363\u036d\u036f\u037f\u038e\u0393\u0396\u039a\u03a0\u03a2"+
		"\u03a7\u03ad\u03b0\u03b5\u03bc\u03c4\u03cd\u03d3\u03db\u03dd\u03e8\u03ec"+
		"\u03ee\u03fb\u03fd\u0408\u040c\u040e\u041b\u042f\u0431\u0439\u043e\u0451"+
		"\u0453\u045d\u045f\u0465\u0472\u0474\u047d\u0484\u048b\u048d\u0497\u0499"+
		"\u04a6\u04ae\u04b6\u04b8\u04ce\u04d8\u04eb\u04ed\u04fd\u0501\u0507\u0513"+
		"\u0517\u051d\u0527\u0533\u0535\u0542\u0548\u0552\u0554\u0566\u0568\u0574"+
		"\u0580\u0587\u0591\u0596\u05a1\u05a9\u05b3\u05bb\u05be\u05ce\u05d0\u05db"+
		"\u05df\u05e8\u05ea\u05f6\u05f8\u0609\u060b\u0615\u0617\u061f\u0625\u062e"+
		"\u0634\u063b\u0641\u064a\u064f\u065b\u065d\u0666\u066a\u066c\u0670\u0679"+
		"\u067e\u0686\u068c\u068e\u06a2\u06a4\u06ad\u06b0\u06b6\u06bb\u06c3\u06c9"+
		"\u06cb\u06d4\u06d8\u06da\u06eb\u06f1\u06f3\u06f8\u0701\u070a\u0710\u0715"+
		"\u0717\u0725\u0727\u0732\u0741\u0743\u0749\u0751\u0754\u0759\u075d\u0763"+
		"\u076d\u076f\u077d\u0788\u0797\u0799\u07b8\u07ba\u07d0\u07d4\u07d6\u07e6"+
		"\u07f2\u07f4\u0806\u080a\u0816\u081c\u0824\u0826\u082d\u0835\u0846\u084f"+
		"\u0858\u0861\u0865\u086a\u0870\u0873\u0878\u087a\u0889\u0893";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}