/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

grammar EntityLanguage;

MODULE       : 'module' ;

ENTITY       : 'entity' ;
PRIMARYKEY   : 'primarykey' ;
ATTRIBUTE    : 'attribute' ;
UNIQUE       : 'unique' ;
CREATION     : 'creation' ;
MODIFICATION : 'modification' ;
REQUIRED     : 'required' ;
OPTIONAL     : 'optional' ;
PARENT       : 'parent' ;
ORDERED      : 'ordered' ;
SEQUENTIAL   : 'sequential' ;
VIRTUAL      : 'virtual';

EXTERN       : 'extern';
TRANSIENT    : 'transient' ;

PRIMARY      : 'primary' ;
SECONDARY    : 'secondary' ;
FLATTEN      : 'flatten' ;

RELATIONSHIPS: 'relationships' ;
RELATIONSHIP : 'relationship' ;
ONE          : 'one' ;
MANY         : 'many' ;
TOONE        : 'to-one' ;
TOMANY       : 'to-many' ;
AS           : 'as' ;

ENTITIES     : 'entities' ;

ENUM         : 'enum' ;
ENUMITEM     : 'enumItem' ;

TYPEDEF      : 'typedef' ;
UNUSED       : 'unused' ;

CONFIGURATION: 'configuration' ;
FORMATTING   : 'formatting' ;
FORMAT       : 'format' ;
OUTPUT       : 'output' ;
DIRECTORY    : 'directory' ;
TRANSFORM    : 'transform' ;
TEMPLATE     : 'template' ;
TEMPLATES    : 'templates' ;
CONTEXTUAL   : 'contextual' ;
PATH         : 'path' ;
ENDPOINT     : 'endpoint' ;
INHERITED    : 'inherited' ;
REPOSITORY   : 'repository' ;
PROTOC       : 'protoc' ;
TAG          : 'tag' ;
VALUE        : 'value' ;
SPACE        : 'space' ;
IMPORT       : 'import' ;
FROM         : 'from' ;
ORGANIZATION : 'organization' ;
NAME         : 'name' ;
NULLABLE     : 'nullable' ;

LANGUAGE     : 'language' ;
VERSION      : 'version' ;
TYPES        : 'types' ;
REF          : '*' ;
SELF         : 'self' ;
COMMENTS     : 'comments' ;
LINE         : 'line' ;
BLOCK_START  : 'blockStart' ;
BLOCK_END    : 'blockEnd' ;
OPERATORS    : 'operators' ;
KEYWORDS     : 'keywords' ;
FUNCTIONS    : 'functions' ;

CONTENT_TYPE : 'contentType' ;

CONSTRAINT   : 'constraint' ;

HINTS        : 'hints' ;
SHORT        : 'short' ;
LONG         : 'long' ;
HUMAN        : 'human' ;
READABLE     : 'readable' ;
IDENTIFICATION : 'identification' ;

DOMAIN       : 'domain' ;
ATTRIBUTES   : 'attributes' ;
REPLACES     : 'replaces' ;
METHOD       : 'method' ;
PREFIX       : 'prefix' ;
SUFFIX       : 'suffix' ;
RENAME       : 'rename' ;
TAGGING      : 'tagging' ;
STARTSWITH   : 'startsWith' ;
NAMING       : 'naming' ;
NAMESPACE    : 'namespace' ;
IN           : 'in' ;
WITH         : 'with' ;
WITHOUT      : 'without' ;
READ         : 'read' ;
WRITE        : 'write' ;
WHEN         : 'when' ;
REQUIRES     : 'requires' ;
ROLE         : 'role' ;
USER         : 'user' ;
IF           : 'if' ;
APPLY        : 'apply' ;

UNITS        : 'units' ;
ABBR         : 'abbr' ;
MULTIPLIER   : 'multiplier' ;

VIEWS        : 'views' ;
DEFAULT      : 'default' ;
VIEW         : 'view' ;
INCLUDE      : 'include' ;
EXCLUDE      : 'exclude' ;
DISTILL      : 'distill' ;
NOPARENTS    : 'noparents' ;
ADD          : 'add' ;
AND          : 'and' ;
TAGGED       : 'tagged' ;

ABSTRACT     : 'abstract' ;
EXTENDS      : 'extends' ;
INHERITS     : 'inherits' ;
INTERFACE    : 'interface' ;
CONFIG       : 'config' ;
METADATA     : 'metadata' ;
CONTEXT      : 'context' ;
ARGUMENT     : 'argument' ;
OPERATION    : 'operation' ;
REQUEST      : 'request' ;
RESPONSE     : 'response' ;
BODY         : 'body' ;
QUERY        : 'query' ;
PARAM        : 'param' ;
STATUS       : 'status' ;
CUSTOM       : 'custom' ;
TYPE         : 'type' ;

TO           : 'to' ;
PREPEND      : 'prepend' ;
APPEND       : 'append' ;

BOOLEAN_TYPE : 'boolean' ;
INT32_TYPE   : 'int32' ;
INT64_TYPE   : 'int64' ;
BYTE_TYPE    : 'byte' ;
UUID_TYPE    : 'uuid' ;
FLOAT_TYPE   : 'float' ;
DOUBLE_TYPE  : 'double' ;
STRING_TYPE  : 'string' ;
DATE_TYPE    : 'date' ;
ASSET_TYPE   : 'asset' ;
ARRAY        : 'array' ;

DESCRIPTION  : 'D' ;
TAGS         : 'T' ;

id
  : macro
  | ident
  ;

ident
  : ID
  | BOOLEAN_TYPE | INT32_TYPE | INT64_TYPE | FLOAT_TYPE | DOUBLE_TYPE | UUID_TYPE | BYTE_TYPE
  | STRING_TYPE | DATE_TYPE | ASSET_TYPE | SELF | EXTERN
  | CONFIGURATION | OUTPUT | DIRECTORY | TEMPLATE | TRANSFORM | PATH | INHERITED | NULLABLE
  | PRIMARYKEY | UNIQUE | CREATION | MODIFICATION | SEQUENTIAL
  | PARENT | ORDERED | PRIMARY | SECONDARY | FLATTEN | TRANSIENT
  | RELATIONSHIPS | RELATIONSHIP | LANGUAGE
  | TYPEDEF | UNUSED | DEFAULT | EXTENDS | NAMESPACE
  | ENTITIES | MODULE | IF | OPERATORS | KEYWORDS | ABSTRACT
  | REPOSITORY | TAG | SPACE | IMPORT | FROM | AND | TAGGING | VALUE
  | ENTITY | ATTRIBUTE | ENUM | ENUMITEM
  | DOMAIN | RENAME | TO | PREPEND | APPEND
  | VIEW | INCLUDE | EXCLUDE | ATTRIBUTES | ATTRIBUTE | REPLACES | PREFIX | SUFFIX
  | VERSION | SHORT | LONG
  | INTERFACE | OPERATION | QUERY | STATUS | CUSTOM | PARAM | TYPE | ENDPOINT
  | CONFIG | CONTEXT | ARGUMENT | HUMAN | READABLE | IDENTIFICATION | NAME | ORGANIZATION
  | REQUIRES | ROLE | READ | WRITE | WHEN | USER | ARRAY
  | APPLY | DESCRIPTION | TAGS | METADATA | FORMATTING | FORMAT
  ;

macro
  : MACRO_START ident (':' (ident|STRING))? ')'
  ;

objid
  : ID ('.' ID)*
  ;

 // EXPRESSIONS

parExpression
    : '(' expression ')'
    ;

expressionList
    : expression (',' expression)*
    ;

methodCall
    : ID '(' expressionList? ')'
    ;

expression
    : methodCall
    | primary
    | expression bop='.' ( ID | methodCall )
    | expression '[' expression ']'
    | '(' typeType ')' expression
    | prefix=('+'|'-') expression
    | prefix=('~'|'!') expression
    | expression bop=('*'|'/'|'%') expression
    | expression bop=('+'|'-') expression
    | expression bop=('<=' | '>=' | '>' | '<') expression
    | expression bop=('==' | '!=') expression
    | expression bop='&' expression
    | expression bop='^' expression
    | expression bop='|' expression
    | expression bop='&&' expression
    | expression bop='||' expression
    | <assoc=right> expression bop='?' expression ':' expression
      expression
    ;

primary
    : '(' expression ')'
    | constant
    | ident
    ;

constant
    : INTEGER
    | FLOAT
    | STRING
    | BOOLEAN=('true'|'false')
    ;

typeType
    : primitiveType
    ;

primitiveType
    : BOOLEAN_TYPE
    | INT32_TYPE
    | INT64_TYPE
    | FLOAT_TYPE
    | DOUBLE_TYPE
    | STRING_TYPE
    | BYTE_TYPE '[' INTEGER? ']'
    ;

/*
 *
 ROOT
 *
 */
root
    :
    ( space
    | module
    | entity
    | enumStatement
    | domain
    | configuration
    | units
    | language
    | abstractInterfaceStatement
    | typedefStatement
    | formatting
    )+
    ;

descriptionStatement
    : DESCRIPTION (id (',' id)*)? STRING
    ;

tagStatement
    : TAGS tagItem (',' tagItem)*
    ;

tagItem
    : STRING ('=' STRING)?
    ;

module
    : MODULE id '{' moduleBody '}'
    ;

moduleBody
    :
    ( descriptionStatement
    | tagStatement
    | entity
    | enumStatement
    | abstractInterfaceStatement
    | typedefStatement
    )+
    ;

/*
 *
 ENTITY
 *
 */
entity
    : entityDecl '{' entityBody '}'
    ;

entityDecl
    : EXTERN? TRANSIENT? (PRIMARY|SECONDARY)? ENTITY (entityName|entityTemplateDecl) (INHERITS id)?
    ;

entityName
    : id
    ;

entityTemplateDecl
    : id '<' id (',' id)* '>'
    ;

entityBody
    :
    ( primarykeySingle
    | primarykeyMultiple
    | attributes
    | descriptionStatement
    | tagStatement
    | relationships
//    | secondaryEntities
    | enumStatement
    )*
    ;

// PRIMARY KEY
primarykeySingle
    : PRIMARYKEY attribute
    ;

primarykeyMultiple
    : PRIMARYKEY '{' attributesBody '}'
    ;

// ATTRIBUTES
attributesDecl
    : ATTRIBUTES id*
    ;

attributes
    : attributesDecl '{' attributesBody '}'
    ;

attributesBody
    :
    ( attribute
    )*
    ;

attribute
    : attributeQualifier* type id (IN id)? ('=' (constant|id))? ('{' attributeBody '}')?
    ;

attributeBody
    :
    ( descriptionStatement
    | tagStatement
    | contentType
    | bitfield
    | attributeConstraint
    )*
    ; // attributeRelationship|

attributeName
    : id ('.' id)*
    ;

//attributeRelationship: (ADDITIVE|CONSTRAINT) RELATIONSHIP 'via' attributeName ;

contentType
    : CONTENT_TYPE STRING
    ;

attributeConstraint
    : CONSTRAINT id '{' attributeConstraintBody '}'
    ;

attributeConstraintBody
    :
    ( descriptionStatement
    | tagStatement
    | expression
    )*
    ;

// RELATIONSHIPS
relationships
    : RELATIONSHIPS '{' relationshipsBody '}'
    ;

relationshipsBody
    :
    ( relationshipStatement
    )*
    ;

relationshipStatement
    : MANY relationshipTemplateAs id id? ('{' relationshipBody '}')?
    | (OPTIONAL|REQUIRED)? (PARENT)? (ONE|MANY) id (relationshipReverseName)? id? (relationshipIdName)? ('{' relationshipBody '}')?
    ;

relationshipTemplateArg
    : UNIQUE? id
    ;

relationshipTemplateAs
    : id '<' relationshipTemplateArg (',' relationshipTemplateArg)* '>' AS
    ;

relationshipReverseName
    : '(' id ')'
    ;

relationshipIdName
    : '(' id ')'
    ;

relationshipBody
    :
    ( descriptionStatement
    | tagStatement
    )*
    ;

// SECONDARY ENTITIES

//secondaryEntities
//    : (SECONDARY)? ENTITIES '{' secondaryEntitiesBody '}'
//    ;
//
//secondaryEntitiesBody
//    :
//    ( secondaryEntityInstantiation
//    )*
//    ;
//
//secondaryEntityInstantiation
//    : (ORDERED|UNIQUE|MANY)* entityName id
//    ;

// View

view
    : (DEFAULT)? VIEW id '{' viewBlock '}'
    ;

viewBlock
    :
    ( descriptionStatement
    | tagStatement
    | (INCLUDE|EXCLUDE) PRIMARYKEY
    | viewAttributes
    | viewRelationships
    )*
    ;

viewAttributes
    : ATTRIBUTES '{' viewAttributesBlock '}'
    ;

viewAttributesBlock
    :
    ( viewAttributeInclude
    | viewAttributeExclude
    )*
    ;

viewRelationships
    : RELATIONSHIPS '{' viewRelationshipsBlock '}'
    ;

viewRelationshipsBlock
    :
    ( viewRelationshipInclude
    | viewRelationshipExclude
    )*
    ;

viewAttributeInclude
    : INCLUDE
    | INCLUDE viewIdentifierList
    | INCLUDE (ARRAY|CREATION|MODIFICATION)
    | INCLUDE TAGGED viewTaggedList
    | INCLUDE SECONDARY ENTITIES TAGGED viewTaggedList
    ;

viewAttributeExclude
    : EXCLUDE
    | EXCLUDE viewIdentifierList
    | EXCLUDE (ARRAY|CREATION|MODIFICATION)
    | EXCLUDE TAGGED viewTaggedList
    | EXCLUDE SECONDARY ENTITIES TAGGED viewTaggedList
    ;

viewRelationshipInclude
    : INCLUDE (TOONE|TOMANY|PARENT) ENTITY? id? (AS id)? (WITH (PRIMARYKEY|VIEW id))?
    | INCLUDE TAGGED viewTaggedList
    ;

viewRelationshipExclude
    : EXCLUDE
    | EXCLUDE (TOONE|TOMANY|PARENT) ENTITY? id?
    | EXCLUDE TAGGED viewTaggedList
    ;

viewTaggedListItem
    : STRING (AND STRING)*
    ;

viewTaggedList
    : viewTaggedListItem (',' viewTaggedListItem)*
    ;

viewIdentifierList
    : '(' id (',' id)* ')'
    ;

/*
 *
 ENUM
 *
 */
enumStatement
    : EXTERN? ENUM id '{' (enumItem | descriptionStatement | tagStatement)+ '}'
    ;

enumItem
    : id '=' INTEGER ('{' enumItemBody '}')?
    ;

enumItemBody
    :
    ( descriptionStatement
    | tagStatement
    )*
    ;

/*
 *
 *
 TYPEDEF
 *
 */
typedefStatement
    : TYPEDEF (INT32_TYPE|INT64_TYPE) id '{' typedefBody '}'
    ;

typedefBody
    :
    ( bitfield
    | descriptionStatement
    | tagStatement
    )+
    ;

bitfield
    : bitCount UNUSED
    | bitCount id ('{' (descriptionStatement)+ '}')?
    ;

bitCount
    : '(' INTEGER ')'
    ;

/*
 *
 DOMAIN
 *
 */
domain
    : DOMAIN ID ((EXTENDS ID) | ('(' ID ')'))? '{' domainBody '}'
    ;

domainBody
    :
    ( descriptionStatement
    | tagStatement
    | domainTagging
    | domainNaming
    | domainEntity
    | domainEnum
    | domainView
    | domainModule
    | domainNamespace
    | domainAttributes
    | domainFlattenSecondaryEntities
    | domainIncludeEntities
    | domainExcludeEntities
    | domainApplyTemplate
    )*
    ;

namespaceIdent
    : id ('.' id)*
    ;

domainNamespace
    : NAMESPACE SPACE? namespaceIdent
    ;

domainFlattenSecondaryEntities
    : FLATTEN SECONDARY ENTITIES
    ;

domainEnum
    : ENUM ID '{' domainEnumBody '}'
    ;

domainEnumBody
    :
    ( descriptionStatement
    | tagStatement
    | domainEnumNamingItem
    | domainEnumItem
    | domainEnumItemRenameTo
    )*
    ;

domainEnumItem
    : ID '{' domainEnumItemBody '}'
    ;

domainEnumItemBody
    :
    ( descriptionStatement
    | tagStatement
    )*
    ;

domainEnumItemRenameTo
    : RENAME id TO? id
    ;

domainEnumNamingItem
    : NAMING ENUMITEM '{' domainEnumNamingBody '}'
    ;

domainEnumNamingBody
    :
    ( domainNamingMethod
    | domainNamingPrefix
    | domainNamingSuffix
    )*
    ;

domainTagging
    : TAGGING id '{' domainTaggingTag* '}'
    ;

domainTaggingTag
    : TAG STARTSWITH? STRING '{' (descriptionStatement | tagStatement | domainTaggingTagValue)* '}'
    ;

domainTaggingTagValue
    : VALUE domainTaggingTagValueType '{' (descriptionStatement | tagStatement)* '}'
    ;

domainTaggingTagValueType
    : INT32_TYPE
    | INT64_TYPE
    | FLOAT_TYPE
    | DOUBLE_TYPE
    | BOOLEAN_TYPE
    | STRING_TYPE
    ;

domainNaming
    : NAMING namingClass (',' namingClass)* '{' domainNamingBody '}'
    ;

namingClass
    :
    ( SPACE
    | MODULE
    | ENTITY
    | ATTRIBUTE
    | RELATIONSHIP
    | ENUM
    | ENUMITEM
    | TYPEDEF
    )?
    ;

domainNamingBody
    :
    ( domainNamingMethod
    | domainNamingPrefix
    | domainNamingSuffix
    | domainNamingPrimaryKey
    | domainNamingWithUnits
    | domainNamingWithoutUnits
    )*
    ;

domainNamingMethod
    : METHOD ID
    ;

domainNamingPrefix
    : PREFIX STRING
    ;

domainNamingSuffix
    : SUFFIX STRING
    ;

domainNamingPrimaryKey
    : PRIMARYKEY ID
    ;

domainNamingWithUnits
    : WITH UNITS
    ;

domainNamingWithoutUnits
    : WITHOUT UNITS
    ;

domainApplyTemplate
    : (DEFAULT)? APPLY TEMPLATE id '{' domainApplyTemplateBody '}'
    ;

domainModuleApplyTemplate
    : (DEFAULT)? APPLY TEMPLATE id '{' domainApplyTemplateBody '}'
    ;

domainEntityApplyTemplate
    : APPLY TEMPLATE id '{' domainApplyTemplateBody '}'
    ;

domainApplyTemplateBody
    :
    ( descriptionStatement
    | tagStatement
    | templateConfig
    )*
    ;

defaultTemplateConfig
    : DEFAULT CONFIG jsonObj
    ;

templateConfig
    : CONFIG jsonObj
    ;

jsonObj
   : '{' jsonPair (',' jsonPair)* (',')? '}'
   | '{' '}'
   ;

jsonPair
   : STRING ':' jsonValue
   ;

jsonArr
   : '[' jsonValue (',' jsonValue)* (',')? ']'
   | '[' ']'
   ;

jsonValue
   : STRING
   | INTEGER
   | FLOAT
   | jsonObj
   | jsonArr
   | 'true'
   | 'false'
   | 'null'
   ;

domainModule
    : MODULE ID '{' domainModuleBody '}'
    ;

domainModuleBody
    :
    ( descriptionStatement
    | tagStatement
    | domainModuleApplyTemplate
    | domainEntity
    )*
    ;

domainView
    : view
    ;

domainEntity
    : ENTITY ID RENAME ID
    | ENTITY ID (RENAME ID)? '{' domainEntityBody '}'
    ;

domainEntityBody
    :
    ( descriptionStatement
    | tagStatement
    | domainEntityNamespace
    | domainAttributes
    | domainRelationships
    | view
    | domainInterfaceStatement
    | domainEntityApplyTemplate
    )*
    ;

domainEntityNamespace
    : NAMESPACE namespaceIdent
    ;

domainAttributes
    : ATTRIBUTES '{' domainAttributesBody '}'
    ;

domainAttributesBody
    :
    ( domainAttributesRenameTo
    | domainAttributesRenameAppendPrepend
    | domainAttributeReplaces
    | domainAttributeExclude
    | domainAttributeAdd
    | domainVirtualAttribute
    | domainAttribute
    )*
    ;

domainAttributeExclude
    : EXCLUDE id
    ;

domainAttributeAdd
    : ADD attribute
    ;

domainAttributesRenameTo
    : RENAME id TO? id
    ;

domainAttributesRenameAppendPrepend
    : RENAME id (PREPEND|APPEND) (ENTITY|DOMAIN)
    ;

domainAttributeReplaces
    : attributeQualifier* type id (IN id)? REPLACES '{' replacesBody '}' ('{' attributeBody '}')?
    ;

replacesBody
    :
    ( bitfield
    )*
    ;

domainIncludeEntities
    : INCLUDE ENTITIES '(' id (',' id)* ')' ('{' descriptionStatement '}')?
    ;

domainExcludeEntities
    : EXCLUDE ENTITIES '(' id (',' id)* ')' ('{' descriptionStatement '}')?
    ;

domainVirtualAttribute
    : OPTIONAL? VIRTUAL type id '{' domainAttributeBody '}'
    ;


domainAttribute
    : id '{' domainAttributeBody '}'
    ;

domainAttributeBody
    :
    ( descriptionStatement
    | tagStatement
    )*
    ;

domainRelationships
    : RELATIONSHIPS '{' domainRelationshipsBody '}'
    ;

domainRelationshipsBody
    :
    ( domainRelationship
    )*
    ;

domainRelationship
    : (ENTITY)? id '{' domainRelationshipBody '}'
    ;

domainRelationshipBody
    :
    ( descriptionStatement
    | tagStatement
    )*
    ;

/*
 *
 INTERFACE
 *
 */
abstractInterfaceStatement
    : INTERFACE id '{' interfaceBody '}'
    ;

interfaceBody
    :
    ( descriptionStatement
    | interfaceType
    | operation
    )*
    ;

interfaceType
    : TYPE id
    ;

operation
    : ABSTRACT OPERATION id '{' operationBody '}'
    ;

operationBody
    :
    ( descriptionStatement
    | operationRequest
    | operationResponse
    | operationConfig
    )*
    ;

operationConfig
    : CONFIG '{' operationConfigBlock '}'
    ;

operationConfigBlock
    :
    ( operationConfigContext
    | operationConfigArgument
    )*
    ;

operationConfigContext
    : CONTEXT operationConfigContextType id ('=' id)? ('{' operationContextBlock '}')?
    ;

operationConfigContextType
    : DOMAIN
    | ENTITY
    ;

operationConfigArgument
    : ARGUMENT operationConfigArgumentType id ('=' id)? ('{' operationArgumentBlock '}')?
    ;

operationConfigArgumentType
    : DOMAIN
    | ENTITY
    | ATTRIBUTE
    | VIEW
    ;

operationContextBlock
    :
    ( descriptionStatement
    )*
    ;

operationArgumentBlock
    :
    ( descriptionStatement
    )*
    ;

operationRequestMethod
    : METHOD id ('{' operationRequestMethodBlock '}')?
    ;

operationRequestMethodBlock
    : (descriptionStatement)*
    ;

operationRequest
    : REQUEST '{' operationRequestBlock '}'
    ;

operationRequestBlock
    :
    ( descriptionStatement
    | operationRequestMethod
    | operationRequestEndpoint
    | operationRequestBody
    )*
    ;

operationRequestEndpoint
    : ENDPOINT (STRING|INHERITED) ('{' operationRequestEndpointBlock '}')?
    ;

operationRequestEndpointBlock
    :
    ( descriptionStatement
    | operationRequestEndpointParam
    )*
    ;

operationRequestEndpointParam
    : (QUERY)? PARAM type id ('{' operationRequestEndpointParamBlock '}')?
    ;

operationRequestEndpointParamBlock
    :
    ( descriptionStatement
    )*
    ;

operationRequestBody
    : BODY ('{' operationRequestBodyBlock '}')?
    ;

operationRequestBodyBlock
    :
    ( descriptionStatement
    | operationBodyContentType
    | operationBodyView
    | operationBodyDomain
    )*
    ;

operationBodyContentType
    : CONTENT_TYPE STRING
    ;

operationBodyView
    : VIEW id
    ;

operationBodyDomain
    : DOMAIN id
    ;

operationResponse
    : RESPONSE '{' operationResponseBlock '}'
    ;

operationResponseBlock
    :
    ( descriptionStatement
    | operationResponseBody
    | operationResponseStatus
    )*
    ;

operationRequestStatusExpression
    : IF expression
    ;

operationResponseStatus
    : STATUS (id|INTEGER) operationRequestStatusExpression? ('{' operationResponseStatusBlock '}')?
    ;

operationResponseStatusBlock
    :
    ( descriptionStatement
    )*
    ;

operationResponseBody
    : BODY ('{' operationResponseBodyBlock '}')?
    ;

operationResponseBodyBlock
    :
    ( descriptionStatement
    | operationBodyContentType
    | operationBodyView
    | operationBodyDomain
    )*
    ;

// Extends an abstract interface inside a particular context
domainInterfaceStatement
    : INTERFACE id ('{' domainInterfaceBody '}')?
    ;

domainInterfaceBody
    :
    ( descriptionStatement
    | domainOperation
    )*
    ;

domainOperation
    : OPERATION id '{' domainOperationBody '}'
    | OPERATION id EXTENDS id '{' extendingOperationBody '}'
    ;

extendingOperationBody
    :
    ( descriptionStatement
    | extendingOperationConfig
    | operationRequest
    | operationResponse
    )*
    ;

extendingOperationConfig
    : CONFIG (id)? '{' extendingOperationConfigBlock '}'
    ;

extendingOperationConfigBlock
    :
    ( extendingOperationAssignment
    )*
    ;

extendingOperationAssignment
    : id '=' (id | STRING | INTEGER | FLOAT) ('{' descriptionStatement '}')?
    ;

domainOperationBody
    :
    ( descriptionStatement
    | operationRequest
    | operationResponse
    )*
    ;

/*
 *
 CONFIGURATION
 *
 */
configuration
    : CONFIGURATION id '{' configurationBody '}'
    ;

configurationBody
    :
    ( directory
    | templates
    | transform
    | protoc
    | repository
    )*
    ;

formatting
    : FORMATTING '{' formattingBody '}'
    ;

formattingBody
    :
    ( formatStatement
    )*
    ;

formatStatement
    : FORMAT id jsonObj
    ;

directory
    : (OUTPUT | DIRECTORY) id '{' outputBody '}'
    ;

outputBody
    :
    ( outputPath
    | descriptionStatement
    )*
    ;

outputPath
    : PATH (STRING|id)
    ;

templates
    : TEMPLATES '{' templatesBody '}'
    ;

templatesBody
    : templatesImport? defaultTemplateConfig? template*
    ;

template
    : CONTEXTUAL? TEMPLATE id (IN STRING)? '{' templateBody '}'
    ;

templateBody
    :
    ( descriptionStatement
    | tagStatement
    | outputSpec
    | templateConfig
    )*
    ;

templatesImport
    : IMPORT FROM id
    ;

outputSpec
  : OUTPUT PRIMARY id
  | OUTPUT id id
  ;

transform
    : TRANSFORM id '{' transformBody '}'
    ;

transformBody
    :
    ( outputSpec
    )*
    ;

repository
    : REPOSITORY id '{' repositoryBody '}'
    ;

repositoryBody
    :
    ( descriptionStatement
    | repositoryType
    | repositoryOrganization
    | repositoryName
    | repositoryPath
    | repositoryTag
    )*
    ;

repositoryType
    : TYPE id
    ;

repositoryOrganization
    : ORGANIZATION STRING
    ;

repositoryName
    : NAME STRING
    ;

repositoryPath
    : PATH STRING
    ;

repositoryTag
    : TAG STRING
    ;

space
    : SPACE id '{' spaceBody '}'
    ;

spaceBody
    :
    ( descriptionStatement
    | tagStatement
    | spaceNamespace
    | spaceMetadata
    | spaceImport
    | spaceInclude
    | repository
    )*
    ;

spaceNamespace
    : NAMESPACE namespaceIdent
    ;

spaceMetadata
    : METADATA jsonObj
    ;

spaceImport
    : IMPORT idList FROM id
    ;

spaceInclude
    : INCLUDE idList FROM id ('{' spaceIncludeBody '}')?
    ;

spaceIncludeBody
    :
    ( spaceIncludeImportEnum
    | spaceIncludeImportEntity
    )*
    ;

spaceIncludeImportEnum
    : IMPORT ENUM idList
    ;

spaceIncludeImportEntity
    : IMPORT ENTITY idList
    ;

idList
    : id (',' id)*
    ;

protoc
    : PROTOC '{' protocBody '}'
    ;

protocBody
    :
    ( spaceImport
    | spaceInclude
    | outputSpec
    | protocLanguage
    )*
    ;

protocLanguage
    : LANGUAGE id
    ;

// TYPE
type
    : INT32_TYPE
    | INT64_TYPE
    | FLOAT_TYPE
    | DOUBLE_TYPE
    | BOOLEAN_TYPE
    | DATE_TYPE
    | STRING_TYPE
    | UUID_TYPE
    | ASSET_TYPE
    | BYTE_TYPE '[' INTEGER? ']'
    | id
    ;

attributeQualifier
    : UNIQUE
    | OPTIONAL
    | CREATION
    | MODIFICATION
    | MANY
    | ORDERED
    | PARENT
    | SEQUENTIAL
    | VIRTUAL
    ;

// UNIT DEFINITIONS
units
    : UNITS '{' unitsBody '}'
    ;

unitsBody
    :
    (
        unitDefinition
    )*
    ;

unitDefinition
    : id (EXTENDS id)?'{' unitDefinitionBody '}'
    ;

unitDefinitionBody
    :
    ( descriptionStatement
    | unitDefinitionField
    )*
    ;

unitDefinitionField
    : ABBR STRING
    | MULTIPLIER FLOAT
    ;

// LANGUAGE

language
    : LANGUAGE id '{' languageBody* '}'
    ;

languageBody
    : (TYPES  '{' typesBody '}') #languageTypes
    | (SELF id) #languageSelf
    | (COMMENTS '{' commentsBody* '}') #languageComments
    | (OPERATORS  '{' operatorsBody* '}') #languageOperators
    | (KEYWORDS  '{' id* '}') #languageKeywords
    | (FUNCTIONS '{' functionsBody* '}' ) #languageFunctions
    ;

typesBody
    : languageType*
    ;

languageType
    : type id REF? NULLABLE?
    | type STRING NULLABLE?
    ;

commentsBody
    : (LINE|BLOCK_START|BLOCK_END) STRING
    ;

functionsBody
    : id '(' type id (',' type id)* ')' STRING
    ;

operatorsBody
    : id STRING*
    ;

version
    : VERSION VERSIONNUM
    ;

/*
 *
 LEXER
 *
 */
//NUMBER
//   : '-'? INTEGER ('.' [0-9] +)? EXP?
//   ;
INTEGER
   : '0' | [1-9] [0-9]*
   ;
// no leading zeros
//fragment EXP
//   : [Ee] [+\-]? INTEGER
//   ;

MACRO_START  : '$(' ;

STRING
   : '"' (ESC | SAFECODEPOINT)* '"'
   ;
fragment ESC
   : '\\' (["\\/bfnrt] | UNICODE)
   ;

fragment UNICODE
   : 'u' HEX HEX HEX HEX
   ;
fragment HEX
   : [0-9a-fA-F]
   ;
fragment SAFECODEPOINT
   : ~ ["\\\u0000-\u001F]
   ;

VERSIONNUM   : INTEGER '.' INTEGER '.' INTEGER ;
FLOAT        : ([0-9]+'.'[0-9]*) | ([0-9]+'.'[0-9]+) ;
ID           : IDX|('\'' IDX '\'') ;
IDX          : (LETTER|'_') (LETTER|'0'..'9'|'_')* ;
LETTER       : [a-zA-Z] ;

LINE_COMMENT : '//' .*? [\n\r] -> channel(HIDDEN) ;
COMMENT      : '/*' .*? '*/' -> channel(HIDDEN) ;
WS           : [ \t\n\r]+ -> channel(HIDDEN) ;

// This is needed for the intellij plugin that reparses while editing.
ERRCHAR
	:	.	-> channel(HIDDEN)
	;
