/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

lexer grammar TemplateLexer;

COMMENT     : '$[*' .*? '*]' '\n'? ;

BlockTagStart
  : ( '$['
    ) -> pushMode(IN_BLOCK_TAG)
  ;

BlockEndTagStart
  : ( '$[/'
    ) -> pushMode(IN_BLOCK_TAG)
  ;

VarTagStart
  : ( '${'
    ) -> pushMode(IN_VAR_TAG)
  ;

Other
  : .
  ;

mode IN_BLOCK_TAG;

True             : 'true' ;
False            : 'false' ;
COMMA            : ',' ;

QuestionMark     : '?' ;
Colon            : ':' ;
HashConstant     : '#' IDENT ;
LogicalNot       : '!' ;
LogicalAnd       : '&&' ;
LogicalOr        : '||' ;
Plus             : '+' ;
PlusEquals       : '+=' ;
Minus            : '-' ;
MinusEquals      : '-=' ;
Multiply         : '*' ;
MultiplyEquals   : '*=' ;
Divide           : '/' ;
DivideEquals     : '/=' ;
Modulo           : '%' ;
GreaterThan      : '>' ;
LessThan         : '<' ;
GreaterThanEqual : '>=' ;
LessThanEqual    : '<=' ;
IsEqual          : '==' ;
NotEqual         : '!=' ;
OpenParen        : '(' ;
CloseParen       : ')' ;
Pipe             : '|' ;
Outputs          : '->' ;
ArrayOpen        : '@[' ;
ArrayClose       : ']@' ;
MapOpen          : '@{' ;
MapClose         : '}@' ;
Dollar           : '$' ;
File             : 'file' ;
IfDoesNotExist   : 'ifdoesnotexist' ;
Language         : 'language' ;
Version          : 'version' ;
Install          : 'install' ;
Copy             : 'copy' ;

// Procedural
Import           : 'import' ;
From             : 'from' ;
Function         : 'function' ;
EndFunction      : 'endfunction' ;
Return           : 'return' ;
Call             : 'call' ;
Explicit         : 'explicit' ; // option for call
Foreach          : 'foreach' ;
EndForeach       : 'endforeach' ;
ForeachRange     : '...' ;
In               : 'in' ;
As               : 'as' ;
By               : 'by' ;
Continue         : 'continue' ;
Break            : 'break' ;
Exit             : 'exit' ;
If               : 'if' ;
EndIf            : 'endif' ;
Switch           : 'switch' ;
EndSwitch        : 'endswitch' ;
Case             : 'case' ;
Default          : 'default' ;
Elseif           : 'elseif' ;
Else             : 'else' ;
Capture          : 'capture' ;
EndCapture       : 'endcapture' ;
Let              : 'let' ;
Do               : 'do' ;
Load             : 'load' ;
Log              : 'log' ;
EndLog           : 'endlog' ;
Assert           : 'assert' ;
EndAssert        : 'endassert' ;
Prompt           : 'prompt' ;
EndPrompt        : 'endprompt' ;
Extends          : 'extends' ;
InstanceOf       : 'instanceof' ;
Receive          : 'receive' ;
Distinct         : 'distinct' ;
Send             : 'send' ;
EndSend          : 'endsend' ;
Preserve         : 'preserve' ;
EndPreserve      : 'endpreserve' ;
Deprecates       : 'deprecates' ;

Publisher        : 'publisher' ;
EndPublisher     : 'endpublisher' ;
Outlet           : 'outlet' ;
EndOutlet        : 'endoutlet' ;
Author           : 'author' ;
EndAuthor        : 'endauthor' ;
To               : 'to' ;

Description      : 'D' ;
Test      : 'Test' ;

BOOLEAN_TYPE     : 'boolean' ;
INT32_TYPE       : 'int32' ;
INT64_TYPE       : 'int64' ;
UUID_TYPE        : 'uuid' ;
FLOAT_TYPE       : 'float' ;
DOUBLE_TYPE      : 'double' ;
STRING_TYPE      : 'string' ;
DATE_TYPE        : 'date' ;
ASSET_TYPE       : 'asset' ;

DomainType       : 'domain' ;
EntityType       : 'entity' ;
AttributeType    : 'attribute' ;
RelationshipType : 'relationship' ;
EnumType         : 'enum' ;
InterfaceType    : 'interface' ;
OperationType    : 'operation' ;
TypedefType      : 'typedef' ;

WS               : ([ \t]|('\\'[\n\r]))+ -> channel(HIDDEN) ;
SpaceOrTab       : [ \t] ;
LineBreak        : [\n\r] ;
INTEGER          : [0-9]+ ;
LETTER           : [a-zA-Z] ;
DIGIT            : [0-9];
VERSION_NUM      : (INTEGER '.' INTEGER '.' INTEGER) ;
FLOAT            : (INTEGER '.' DIGIT*) ;
IDENT            : (LETTER|'_') (LETTER|DIGIT|'_'|'#')* ;
//FILTER_IDENT     : (LETTER) (LETTER|'_')* ; we should use this for filters but have to wait until I sort out issue with intellij plugin
STRING           : '"' (ESC | ~["\\])* '"' ;
fragment ESC     : '\\' ["\bfnrt] ;
DOT              : '.' ;
EQUALS           : '=' ;
CurlyClosed      : '}' ;

BlockTagEnd
  : (']'  LineBreak?
  | '-]') -> popMode ;

VarInBlockStart
  : '{' -> pushMode(IN_VAR_TAG)
;

BADSTUFF
	:	.	-> pushMode(DEAD)
	;

mode IN_VAR_TAG;

VarTagEnd
  : CurlyClosed -> popMode ;

VarLanguage      : Language -> type(Language) ;

VarWhitespace    : [ \t\n\r]+ -> channel(HIDDEN) ;
VarInteger       : INTEGER -> type(INTEGER) ;
VarLetter        : LETTER -> type(LETTER) ;
VarDigit         : DIGIT -> type(DIGIT);
VarIdent         : IDENT -> type(IDENT) ;
VarPipe          : Pipe -> type(Pipe) ;
VarString        : STRING -> type(STRING) ;
VarDot           : DOT -> type(DOT) ;

VarCurlyClosed : CurlyClosed -> type(CurlyClosed) ;

VarTrue             : True -> type(True) ;
VarFalse            : False -> type(False) ;

VarQuestionMark     : QuestionMark -> type(QuestionMark) ;
VarColon            : Colon -> type(Colon) ;
VarHashConstant     : HashConstant -> type(HashConstant) ;
VarLogicalNot       : LogicalNot -> type(LogicalNot) ;
VarLogicalAnd       : LogicalAnd -> type(LogicalAnd) ;
VarLogicalOr        : LogicalOr -> type(LogicalOr) ;
VarPlus             : Plus -> type(Plus) ;
VarPlusEquals       : PlusEquals -> type(PlusEquals) ;
VarMinus            : Minus -> type(Minus) ;
VarMinusEquals      : MinusEquals -> type(MinusEquals) ;
VarMultiply         : Multiply -> type(Multiply) ;
VarMultiplyEquals   : MultiplyEquals -> type(MultiplyEquals) ;
VarDivide           : Divide -> type(Divide) ;
VarDivideEquals     : DivideEquals -> type(DivideEquals) ;
VarModulo           : Modulo -> type(Modulo) ;
VarGreaterThan      : GreaterThan -> type(GreaterThan) ;
VarLessThan         : LessThan -> type(LessThan) ;
VarGreaterThanEqual : GreaterThanEqual -> type(GreaterThanEqual) ;
VarLessThanEqual    : LessThanEqual -> type(LessThanEqual) ;
VarIsEqual          : IsEqual -> type(IsEqual) ;
VarNotEqual         : NotEqual -> type(NotEqual) ;
VarArrayOpen        : ArrayOpen -> type(ArrayOpen) ;
VarArrayClose       : ArrayClose -> type(ArrayClose) ;
VarMapOpen          : MapOpen -> type(MapOpen) ;
VarMapClose         : MapClose -> type(MapClose) ;

VarDomainType       : DomainType -> type(DomainType) ;
VarEntityType       : EntityType -> type(EntityType) ;
VarAttributeType    : AttributeType -> type(AttributeType) ;
VarRelationshipType : RelationshipType -> type(RelationshipType) ;
VarEnumType         : EnumType -> type(EnumType) ;
VarInterfaceType    : InterfaceType -> type(InterfaceType) ;
VarOperationType    : OperationType -> type(OperationType) ;
VarTypedefType      : TypedefType -> type(TypedefType) ;
VarOpenParen        : OpenParen -> type(OpenParen) ;
VarCloseParen       : CloseParen -> type(CloseParen) ;
VarDollar           : Dollar -> type(Dollar) ;

VAR_BADSTUFF
	:	.	-> pushMode(DEAD)
	;

mode DEAD;

ERRCHAR
	:	.	-> channel(HIDDEN)
	;
