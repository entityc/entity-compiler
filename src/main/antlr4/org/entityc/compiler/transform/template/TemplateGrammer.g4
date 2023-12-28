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
  | Assert
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
  | assertTag
  | promptTag
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
  | endTag
  ;

endTag
  : EndFunction
  | EndForeach
  | EndIf
  | EndFile
  | EndCapture
  | EndSwitch
  | EndLog
  | EndAssert
  | EndPrompt
  | EndSend
  | EndPreserve
  | EndAuthor
  | EndPublisher
  | EndOutlet
  ;

blockComment
  : BLOCK_COMMENT
  ;

lineComment
  : LINE_COMMENT
  ;

filler
  : LineBreak
  | lineComment
  | blockComment
  ;

instructionSequence
  : filler* instruction filler*
  | instructionSequence filler+ instructionSequence
  | filler*
  ;

block
  : BlockTagStart instructionSequence BlockTagEnd
  ;

blockEnd
  : BlockEndTagStart (Function|Foreach|If|File|Capture|Switch|Log|Assert|Prompt|Send|Preserve|Author|Publisher|Outlet) BlockTagEnd
  ;

descriptionTag
  : Description (identifier (',' identifier)*)? STRING
  ;

nodeDescription
  : Description (identifier (',' identifier)*)? STRING LineBreak*
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
  : Function identifier LineBreak* nodeDescription* LineBreak* '(' LineBreak* functionDeclArgList? ')' LineBreak* ('->' LineBreak* '(' LineBreak* functionDeclArgList LineBreak* ')'  LineBreak* )?
  ;

functionDeclArgList
  : functionDeclArg (',' LineBreak* functionDeclArg )*
  ;

functionDeclArg
  : identifier LineBreak* nodeDescription* LineBreak*
  ;

callTag
  : Call Explicit? identifier '(' inputCallArgList? LineBreak* ')' LineBreak* ('->' LineBreak* '(' outputCallArgList ')' )?
  ;

callArg
  : identifier ':' expression
  ;

inputCallArgList
  : callArg (',' LineBreak* callArg)*
  ;

outputCallArgList
  : callArg (',' LineBreak* callArg)*
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

assertTag
  : Assert expression identifier
  ;

promptTag
  : Prompt identifier (':' primitiveType)?
  ;

letTag
  : Let identifier (EQUALS|PlusEquals|MinusEquals|MultiplyEquals|DivideEquals) expression
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
    | mapSpecifier
    ;

expressionList
    : expression (',' expression)*
    ;

arraySpecifier
    : '@[' expressionList? ']@'
    ;

mapItem
    : expression ':' expression
    ;

mapItemList
    : mapItem (',' mapItem)*
    ;

mapSpecifier
    : MapOpen mapItemList? MapClose
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
  : Publisher namespaceIdent  LineBreak* nodeDescription*  LineBreak*
  ;

authorOption
  : identifier '=' identifier
  ;

authorTag
  : Author To namespaceIdentList? (Outlet IDENT authorOption*  LineBreak* nodeDescription* LineBreak*)?
  ;

outletTag
  : Outlet IDENT  LineBreak* nodeDescription* LineBreak*
  ;
