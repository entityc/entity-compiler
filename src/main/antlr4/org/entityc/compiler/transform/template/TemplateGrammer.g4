/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

parser grammar TemplateGrammer;

options {
  tokenVocab=TemplateLexer;
}

template
  : chunk* EOF
  ;

chunk
  : block
  | blockEnd
  | variableTag
  | COMMENT
  | other
  ;

other
  : Other+
  ;

identifier
  : IDENT
  | builtinType
  | Language
  | Import
  | File
  | Install
  | Copy
  | Load
  | Function
  | Return
  | Call
  | Foreach
  | Continue
  | Break
  | Exit
  | If
  | Elseif
  | Else
  | Version
  | Capture
  | Log
  | Let
  | Do
  | Switch
  | Case
  | Default
  | Send
  | Preserve
  | Description
  | Publisher
  | Outlet
  | Author
  | To
  | From
  ;

instruction
  : languageTag
  | domainTag
  | importTag
  | fileTag
  | installTag
  | loadTag
  | functionDeclTag
  | returnTag
  | callTag
  | foreachTag
  | continueTag
  | breakTag
  | exitTag
  | ifTag
  | elseifTag
  | elseTag
  | versionTag
  | captureTag
  | logTag
  | letTag
  | doTag
  | switchTag
  | caseTag
  | defaultTag
  | receiverTag
  | sendTag
  | preserveTag
  | descriptionTag
  | publisherTag
  | outletTag
  | authorTag
  ;

block
  : BlockTagStart instruction BlockTagEnd
  ;

blockEnd
  : BlockEndTagStart (Function|Foreach|If|File|Capture|Switch|Log|Send|Preserve|Author|Publisher|Outlet) BlockTagEnd
  ;

descriptionTag
  : Description (identifier (',' identifier)*)? STRING
  ;

nodeDescription
  : Description (identifier (',' identifier)*)? STRING
  ;

languageTag
  : Language identifier
  ;

domainTag
  : DomainType identifier
  ;

versionTag
  : Version VERSION_NUM
  ;

importTag
  : Import (identifier|STRING) (From identifier)?
  ;

installTag
  : Install Copy? fileArg fileArg
  ;

fileTag
  : File IfDoesNotExist? fileArg fileArg fileArg
  ;

fileArg
  : expression
  ;

loadTag
  : Load identifier fileArg fileArg fileArg
  ;

functionDeclTag
  : Function identifier nodeDescription* '(' functionDeclArgList? ')' ('->' '(' functionDeclArgList ')' )?
  ;

functionDeclArgList
  : functionDeclArg (',' functionDeclArg )*
  ;

functionDeclArg
  : identifier nodeDescription*
  ;

callTag
  : Call Explicit? identifier '(' inputCallArgList? ')' ('->' '(' outputCallArgList ')' )?
  ;

callArg
  : identifier ':' expression
  ;

inputCallArgList
  : callArg (',' callArg)*
  ;

outputCallArgList
  : callArg (',' callArg)*
  ;

returnTag
  : Return
  ;

foreachTag
  : Foreach expression (As identifier)?
  | Foreach identifier In expression ('...' expression (By expression)?)?
  | Foreach identifier In expression If expression
  ;

breakTag
  : Break
  ;

exitTag
  : Exit
  ;

continueTag
  : Continue
  ;

ifTag
  : If expression
  ;

elseifTag
  : Elseif expression
  ;

elseTag
  : Else
  ;

switchTag
  : Switch expression
  ;

caseArg
  : INTEGER | STRING | identifier | primitiveType
  ;

caseTag
  : Case caseArg (COMMA caseArg)*
  ;

defaultTag
  : Default
  ;

captureTag
  : Capture identifier
  ;

receiverTag
  : Receive (Distinct)? identifier nodeDescription*
  ;

sendTag
  : Send identifier
  ;

preserveTag
  : Preserve identifier (Deprecates identifier (',' identifier)*)?
  ;

logTag
  : Log identifier?
  ;

letTag
  : Let identifier EQUALS expression
  ;

doTag
  : Do expression
  ;

variableTag
  : VarTagStart ('$'|expression) VarTagEnd
  ;

methodCall
    : identifier '(' expressionList? ')'
    ;

expression
    : primary
    | expression bop='.' (identifier | methodCall)
    | methodCall
    | expression bop='|' filter
    | '(' typeType ')' expression
    | prefix=('+'|'-') expression
    | prefix='!' expression
    | expression bop=('*'|'/'|'%') expression
    | expression bop=('+'|'-') expression
    | expression bop=('<=' | '>=' | '>' | '<') expression
    | expression bop=('==' | '!=') expression
    //| expression bop=TYPEOF (ENTITY|DOMAIN|VIEW|ENUM|INTERFACE|OPERATION|NATIVE)
    | expression bop=InstanceOf typeType
    | expression bop=Extends typeType
    | expression bop='&&' expression
    | expression bop='||' expression
    | <assoc=right> expression bop='?' expression Colon expression
    | arraySpecifier
    ;

expressionList
    : expression (',' expression)*
    ;

arraySpecifier
    : '@[' expressionList? ']@'
    ;

filterParamExpression
    : OpenParen expression CloseParen
    | identifier
    ;

primary
    : identifier
    | constant
    | '(' expression ')'
    ;

constant
    : INTEGER
    | FLOAT
    | STRING
    | BOOLEAN=('true'|'false')
    | HashConstant
    | OpenParen HashConstant Colon constant CloseParen
    | builtinType Colon IDENT ('.' IDENT)*
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
    | UUID_TYPE
    | DATE_TYPE
    | ASSET_TYPE
    ;

builtinType
    : EntityType
    | AttributeType
    | RelationshipType
    | EnumType
    | DomainType
    | InterfaceType
    | OperationType
    | TypedefType
    | Language
    ;

invocationConstant
  : HashConstant (Colon STRING)?
  ;

filter
  : identifier (Colon filterParamExpression)? filterOption*
  ;

filterOption
  : identifier
  | identifier ':' (identifier|constant)
  ;

namespaceIdent
    : IDENT ('.' IDENT)*
    ;

namespaceIdentList
   : namespaceIdent (',' namespaceIdent)*
   ;

publisherTag
  : Publisher namespaceIdent nodeDescription*
  ;

authorOption
  : identifier '=' identifier
  ;

authorTag
  : Author To namespaceIdentList? (Outlet IDENT authorOption* nodeDescription*)?
  ;

outletTag
  : Outlet IDENT nodeDescription*
  ;
