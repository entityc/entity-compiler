// Generated from /Users/bob/Development/Entity-Compiler/src/java/org/entityc/compiler/transform/template/TemplateGrammer.g4 by ANTLR 4.9
package org.entityc.compiler.transform.template;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TemplateGrammer extends Parser {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COMMENT=1, BlockTagStart=2, BlockEndTagStart=3, VarTagStart=4, Other=5, 
		True=6, False=7, COMMA=8, QuestionMark=9, Colon=10, HashConstant=11, LogicalNot=12, 
		LogicalAnd=13, LogicalOr=14, Plus=15, Minus=16, Multiply=17, Divide=18, 
		Modulo=19, GreaterThan=20, LessThan=21, GreaterThanEqual=22, LessThanEqual=23, 
		IsEqual=24, NotEqual=25, OpenParen=26, CloseParen=27, Pipe=28, Outputs=29, 
		ArrayOpen=30, ArrayClose=31, Dollar=32, File=33, IfDoesNotExist=34, Language=35, 
		Version=36, Install=37, Copy=38, Import=39, From=40, Function=41, Return=42, 
		Call=43, Explicit=44, Foreach=45, ForeachRange=46, In=47, As=48, By=49, 
		Continue=50, Break=51, Exit=52, If=53, Switch=54, Case=55, Default=56, 
		Elseif=57, Else=58, Capture=59, Let=60, Do=61, Load=62, Log=63, Extends=64, 
		InstanceOf=65, Receive=66, Distinct=67, Send=68, Preserve=69, Deprecates=70, 
		Publisher=71, Outlet=72, Author=73, To=74, Description=75, BOOLEAN_TYPE=76, 
		INT32_TYPE=77, INT64_TYPE=78, UUID_TYPE=79, FLOAT_TYPE=80, DOUBLE_TYPE=81, 
		STRING_TYPE=82, DATE_TYPE=83, ASSET_TYPE=84, DomainType=85, EntityType=86, 
		AttributeType=87, RelationshipType=88, EnumType=89, InterfaceType=90, 
		OperationType=91, TypedefType=92, WS=93, SpaceOrTab=94, LineBreak=95, 
		INTEGER=96, LETTER=97, DIGIT=98, VERSION_NUM=99, FLOAT=100, IDENT=101, 
		STRING=102, DOT=103, EQUALS=104, CurlyClosed=105, BlockTagEnd=106, VarInBlockStart=107, 
		BADSTUFF=108, VarTagEnd=109, VarWhitespace=110, VAR_BADSTUFF=111, ERRCHAR=112;
	public static final int
		RULE_template = 0, RULE_chunk = 1, RULE_other = 2, RULE_identifier = 3, 
		RULE_instruction = 4, RULE_block = 5, RULE_blockEnd = 6, RULE_descriptionTag = 7, 
		RULE_nodeDescription = 8, RULE_languageTag = 9, RULE_domainTag = 10, RULE_versionTag = 11, 
		RULE_importTag = 12, RULE_installTag = 13, RULE_fileTag = 14, RULE_fileArg = 15, 
		RULE_loadTag = 16, RULE_functionDeclTag = 17, RULE_functionDeclArgList = 18, 
		RULE_functionDeclArg = 19, RULE_callTag = 20, RULE_callArg = 21, RULE_inputCallArgList = 22, 
		RULE_outputCallArgList = 23, RULE_returnTag = 24, RULE_foreachTag = 25, 
		RULE_breakTag = 26, RULE_exitTag = 27, RULE_continueTag = 28, RULE_ifTag = 29, 
		RULE_elseifTag = 30, RULE_elseTag = 31, RULE_switchTag = 32, RULE_caseArg = 33, 
		RULE_caseTag = 34, RULE_defaultTag = 35, RULE_captureTag = 36, RULE_receiverTag = 37, 
		RULE_sendTag = 38, RULE_preserveTag = 39, RULE_logTag = 40, RULE_letTag = 41, 
		RULE_doTag = 42, RULE_variableTag = 43, RULE_methodCall = 44, RULE_expression = 45, 
		RULE_expressionList = 46, RULE_arraySpecifier = 47, RULE_filterParamExpression = 48, 
		RULE_primary = 49, RULE_constant = 50, RULE_typeType = 51, RULE_primitiveType = 52, 
		RULE_builtinType = 53, RULE_invocationConstant = 54, RULE_filter = 55, 
		RULE_filterOption = 56, RULE_namespaceIdent = 57, RULE_namespaceIdentList = 58, 
		RULE_publisherTag = 59, RULE_authorOption = 60, RULE_authorTag = 61, RULE_outletTag = 62;
	private static String[] makeRuleNames() {
		return new String[] {
			"template", "chunk", "other", "identifier", "instruction", "block", "blockEnd", 
			"descriptionTag", "nodeDescription", "languageTag", "domainTag", "versionTag", 
			"importTag", "installTag", "fileTag", "fileArg", "loadTag", "functionDeclTag", 
			"functionDeclArgList", "functionDeclArg", "callTag", "callArg", "inputCallArgList", 
			"outputCallArgList", "returnTag", "foreachTag", "breakTag", "exitTag", 
			"continueTag", "ifTag", "elseifTag", "elseTag", "switchTag", "caseArg", 
			"caseTag", "defaultTag", "captureTag", "receiverTag", "sendTag", "preserveTag", 
			"logTag", "letTag", "doTag", "variableTag", "methodCall", "expression", 
			"expressionList", "arraySpecifier", "filterParamExpression", "primary", 
			"constant", "typeType", "primitiveType", "builtinType", "invocationConstant", 
			"filter", "filterOption", "namespaceIdent", "namespaceIdentList", "publisherTag", 
			"authorOption", "authorTag", "outletTag"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, "'true'", "'false'", "','", "'?'", 
			"':'", null, "'!'", "'&&'", "'||'", "'+'", "'-'", "'*'", "'/'", "'%'", 
			"'>'", "'<'", "'>='", "'<='", "'=='", "'!='", "'('", "')'", "'|'", "'->'", 
			"'@['", "']@'", "'$'", "'file'", "'ifdoesnotexist'", "'language'", "'version'", 
			"'install'", "'copy'", "'import'", "'from'", "'function'", "'return'", 
			"'call'", "'explicit'", "'foreach'", "'...'", "'in'", "'as'", "'by'", 
			"'continue'", "'break'", "'exit'", "'if'", "'switch'", "'case'", "'default'", 
			"'elseif'", "'else'", "'capture'", "'let'", "'do'", "'load'", "'log'", 
			"'extends'", "'instanceof'", "'receive'", "'distinct'", "'send'", "'preserve'", 
			"'deprecates'", "'publisher'", "'outlet'", "'author'", "'to'", "'D'", 
			"'boolean'", "'int32'", "'int64'", "'uuid'", "'float'", "'double'", "'string'", 
			"'date'", "'asset'", "'domain'", "'entity'", "'attribute'", "'relationship'", 
			"'enum'", "'interface'", "'operation'", "'typedef'", null, null, null, 
			null, null, null, null, null, null, null, "'.'", "'='", "'}'", null, 
			"'{'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "COMMENT", "BlockTagStart", "BlockEndTagStart", "VarTagStart", 
			"Other", "True", "False", "COMMA", "QuestionMark", "Colon", "HashConstant", 
			"LogicalNot", "LogicalAnd", "LogicalOr", "Plus", "Minus", "Multiply", 
			"Divide", "Modulo", "GreaterThan", "LessThan", "GreaterThanEqual", "LessThanEqual", 
			"IsEqual", "NotEqual", "OpenParen", "CloseParen", "Pipe", "Outputs", 
			"ArrayOpen", "ArrayClose", "Dollar", "File", "IfDoesNotExist", "Language", 
			"Version", "Install", "Copy", "Import", "From", "Function", "Return", 
			"Call", "Explicit", "Foreach", "ForeachRange", "In", "As", "By", "Continue", 
			"Break", "Exit", "If", "Switch", "Case", "Default", "Elseif", "Else", 
			"Capture", "Let", "Do", "Load", "Log", "Extends", "InstanceOf", "Receive", 
			"Distinct", "Send", "Preserve", "Deprecates", "Publisher", "Outlet", 
			"Author", "To", "Description", "BOOLEAN_TYPE", "INT32_TYPE", "INT64_TYPE", 
			"UUID_TYPE", "FLOAT_TYPE", "DOUBLE_TYPE", "STRING_TYPE", "DATE_TYPE", 
			"ASSET_TYPE", "DomainType", "EntityType", "AttributeType", "RelationshipType", 
			"EnumType", "InterfaceType", "OperationType", "TypedefType", "WS", "SpaceOrTab", 
			"LineBreak", "INTEGER", "LETTER", "DIGIT", "VERSION_NUM", "FLOAT", "IDENT", 
			"STRING", "DOT", "EQUALS", "CurlyClosed", "BlockTagEnd", "VarInBlockStart", 
			"BADSTUFF", "VarTagEnd", "VarWhitespace", "VAR_BADSTUFF", "ERRCHAR"
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
	public String getGrammarFileName() { return "TemplateGrammer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TemplateGrammer(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class TemplateContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(TemplateGrammer.EOF, 0); }
		public List<ChunkContext> chunk() {
			return getRuleContexts(ChunkContext.class);
		}
		public ChunkContext chunk(int i) {
			return getRuleContext(ChunkContext.class,i);
		}
		public TemplateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_template; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterTemplate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitTemplate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitTemplate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TemplateContext template() throws RecognitionException {
		TemplateContext _localctx = new TemplateContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_template);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(129);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMMENT) | (1L << BlockTagStart) | (1L << BlockEndTagStart) | (1L << VarTagStart) | (1L << Other))) != 0)) {
				{
				{
				setState(126);
				chunk();
				}
				}
				setState(131);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(132);
			match(EOF);
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

	public static class ChunkContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public BlockEndContext blockEnd() {
			return getRuleContext(BlockEndContext.class,0);
		}
		public VariableTagContext variableTag() {
			return getRuleContext(VariableTagContext.class,0);
		}
		public TerminalNode COMMENT() { return getToken(TemplateGrammer.COMMENT, 0); }
		public OtherContext other() {
			return getRuleContext(OtherContext.class,0);
		}
		public ChunkContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_chunk; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterChunk(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitChunk(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitChunk(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ChunkContext chunk() throws RecognitionException {
		ChunkContext _localctx = new ChunkContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_chunk);
		try {
			setState(139);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BlockTagStart:
				enterOuterAlt(_localctx, 1);
				{
				setState(134);
				block();
				}
				break;
			case BlockEndTagStart:
				enterOuterAlt(_localctx, 2);
				{
				setState(135);
				blockEnd();
				}
				break;
			case VarTagStart:
				enterOuterAlt(_localctx, 3);
				{
				setState(136);
				variableTag();
				}
				break;
			case COMMENT:
				enterOuterAlt(_localctx, 4);
				{
				setState(137);
				match(COMMENT);
				}
				break;
			case Other:
				enterOuterAlt(_localctx, 5);
				{
				setState(138);
				other();
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

	public static class OtherContext extends ParserRuleContext {
		public List<TerminalNode> Other() { return getTokens(TemplateGrammer.Other); }
		public TerminalNode Other(int i) {
			return getToken(TemplateGrammer.Other, i);
		}
		public OtherContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_other; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterOther(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitOther(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitOther(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OtherContext other() throws RecognitionException {
		OtherContext _localctx = new OtherContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_other);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(142); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(141);
					match(Other);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(144); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(TemplateGrammer.IDENT, 0); }
		public BuiltinTypeContext builtinType() {
			return getRuleContext(BuiltinTypeContext.class,0);
		}
		public TerminalNode Language() { return getToken(TemplateGrammer.Language, 0); }
		public TerminalNode Import() { return getToken(TemplateGrammer.Import, 0); }
		public TerminalNode File() { return getToken(TemplateGrammer.File, 0); }
		public TerminalNode Install() { return getToken(TemplateGrammer.Install, 0); }
		public TerminalNode Copy() { return getToken(TemplateGrammer.Copy, 0); }
		public TerminalNode Load() { return getToken(TemplateGrammer.Load, 0); }
		public TerminalNode Function() { return getToken(TemplateGrammer.Function, 0); }
		public TerminalNode Return() { return getToken(TemplateGrammer.Return, 0); }
		public TerminalNode Call() { return getToken(TemplateGrammer.Call, 0); }
		public TerminalNode Foreach() { return getToken(TemplateGrammer.Foreach, 0); }
		public TerminalNode Continue() { return getToken(TemplateGrammer.Continue, 0); }
		public TerminalNode Break() { return getToken(TemplateGrammer.Break, 0); }
		public TerminalNode Exit() { return getToken(TemplateGrammer.Exit, 0); }
		public TerminalNode If() { return getToken(TemplateGrammer.If, 0); }
		public TerminalNode Elseif() { return getToken(TemplateGrammer.Elseif, 0); }
		public TerminalNode Else() { return getToken(TemplateGrammer.Else, 0); }
		public TerminalNode Version() { return getToken(TemplateGrammer.Version, 0); }
		public TerminalNode Capture() { return getToken(TemplateGrammer.Capture, 0); }
		public TerminalNode Log() { return getToken(TemplateGrammer.Log, 0); }
		public TerminalNode Let() { return getToken(TemplateGrammer.Let, 0); }
		public TerminalNode Do() { return getToken(TemplateGrammer.Do, 0); }
		public TerminalNode Switch() { return getToken(TemplateGrammer.Switch, 0); }
		public TerminalNode Case() { return getToken(TemplateGrammer.Case, 0); }
		public TerminalNode Default() { return getToken(TemplateGrammer.Default, 0); }
		public TerminalNode Send() { return getToken(TemplateGrammer.Send, 0); }
		public TerminalNode Preserve() { return getToken(TemplateGrammer.Preserve, 0); }
		public TerminalNode Description() { return getToken(TemplateGrammer.Description, 0); }
		public TerminalNode Publisher() { return getToken(TemplateGrammer.Publisher, 0); }
		public TerminalNode Outlet() { return getToken(TemplateGrammer.Outlet, 0); }
		public TerminalNode Author() { return getToken(TemplateGrammer.Author, 0); }
		public TerminalNode To() { return getToken(TemplateGrammer.To, 0); }
		public TerminalNode From() { return getToken(TemplateGrammer.From, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_identifier);
		try {
			setState(180);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(146);
				match(IDENT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(147);
				builtinType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(148);
				match(Language);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(149);
				match(Import);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(150);
				match(File);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(151);
				match(Install);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(152);
				match(Copy);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(153);
				match(Load);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(154);
				match(Function);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(155);
				match(Return);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(156);
				match(Call);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(157);
				match(Foreach);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(158);
				match(Continue);
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(159);
				match(Break);
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(160);
				match(Exit);
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(161);
				match(If);
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(162);
				match(Elseif);
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(163);
				match(Else);
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(164);
				match(Version);
				}
				break;
			case 20:
				enterOuterAlt(_localctx, 20);
				{
				setState(165);
				match(Capture);
				}
				break;
			case 21:
				enterOuterAlt(_localctx, 21);
				{
				setState(166);
				match(Log);
				}
				break;
			case 22:
				enterOuterAlt(_localctx, 22);
				{
				setState(167);
				match(Let);
				}
				break;
			case 23:
				enterOuterAlt(_localctx, 23);
				{
				setState(168);
				match(Do);
				}
				break;
			case 24:
				enterOuterAlt(_localctx, 24);
				{
				setState(169);
				match(Switch);
				}
				break;
			case 25:
				enterOuterAlt(_localctx, 25);
				{
				setState(170);
				match(Case);
				}
				break;
			case 26:
				enterOuterAlt(_localctx, 26);
				{
				setState(171);
				match(Default);
				}
				break;
			case 27:
				enterOuterAlt(_localctx, 27);
				{
				setState(172);
				match(Send);
				}
				break;
			case 28:
				enterOuterAlt(_localctx, 28);
				{
				setState(173);
				match(Preserve);
				}
				break;
			case 29:
				enterOuterAlt(_localctx, 29);
				{
				setState(174);
				match(Description);
				}
				break;
			case 30:
				enterOuterAlt(_localctx, 30);
				{
				setState(175);
				match(Publisher);
				}
				break;
			case 31:
				enterOuterAlt(_localctx, 31);
				{
				setState(176);
				match(Outlet);
				}
				break;
			case 32:
				enterOuterAlt(_localctx, 32);
				{
				setState(177);
				match(Author);
				}
				break;
			case 33:
				enterOuterAlt(_localctx, 33);
				{
				setState(178);
				match(To);
				}
				break;
			case 34:
				enterOuterAlt(_localctx, 34);
				{
				setState(179);
				match(From);
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

	public static class InstructionContext extends ParserRuleContext {
		public LanguageTagContext languageTag() {
			return getRuleContext(LanguageTagContext.class,0);
		}
		public DomainTagContext domainTag() {
			return getRuleContext(DomainTagContext.class,0);
		}
		public ImportTagContext importTag() {
			return getRuleContext(ImportTagContext.class,0);
		}
		public FileTagContext fileTag() {
			return getRuleContext(FileTagContext.class,0);
		}
		public InstallTagContext installTag() {
			return getRuleContext(InstallTagContext.class,0);
		}
		public LoadTagContext loadTag() {
			return getRuleContext(LoadTagContext.class,0);
		}
		public FunctionDeclTagContext functionDeclTag() {
			return getRuleContext(FunctionDeclTagContext.class,0);
		}
		public ReturnTagContext returnTag() {
			return getRuleContext(ReturnTagContext.class,0);
		}
		public CallTagContext callTag() {
			return getRuleContext(CallTagContext.class,0);
		}
		public ForeachTagContext foreachTag() {
			return getRuleContext(ForeachTagContext.class,0);
		}
		public ContinueTagContext continueTag() {
			return getRuleContext(ContinueTagContext.class,0);
		}
		public BreakTagContext breakTag() {
			return getRuleContext(BreakTagContext.class,0);
		}
		public ExitTagContext exitTag() {
			return getRuleContext(ExitTagContext.class,0);
		}
		public IfTagContext ifTag() {
			return getRuleContext(IfTagContext.class,0);
		}
		public ElseifTagContext elseifTag() {
			return getRuleContext(ElseifTagContext.class,0);
		}
		public ElseTagContext elseTag() {
			return getRuleContext(ElseTagContext.class,0);
		}
		public VersionTagContext versionTag() {
			return getRuleContext(VersionTagContext.class,0);
		}
		public CaptureTagContext captureTag() {
			return getRuleContext(CaptureTagContext.class,0);
		}
		public LogTagContext logTag() {
			return getRuleContext(LogTagContext.class,0);
		}
		public LetTagContext letTag() {
			return getRuleContext(LetTagContext.class,0);
		}
		public DoTagContext doTag() {
			return getRuleContext(DoTagContext.class,0);
		}
		public SwitchTagContext switchTag() {
			return getRuleContext(SwitchTagContext.class,0);
		}
		public CaseTagContext caseTag() {
			return getRuleContext(CaseTagContext.class,0);
		}
		public DefaultTagContext defaultTag() {
			return getRuleContext(DefaultTagContext.class,0);
		}
		public ReceiverTagContext receiverTag() {
			return getRuleContext(ReceiverTagContext.class,0);
		}
		public SendTagContext sendTag() {
			return getRuleContext(SendTagContext.class,0);
		}
		public PreserveTagContext preserveTag() {
			return getRuleContext(PreserveTagContext.class,0);
		}
		public DescriptionTagContext descriptionTag() {
			return getRuleContext(DescriptionTagContext.class,0);
		}
		public PublisherTagContext publisherTag() {
			return getRuleContext(PublisherTagContext.class,0);
		}
		public OutletTagContext outletTag() {
			return getRuleContext(OutletTagContext.class,0);
		}
		public AuthorTagContext authorTag() {
			return getRuleContext(AuthorTagContext.class,0);
		}
		public InstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitInstruction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitInstruction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstructionContext instruction() throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_instruction);
		try {
			setState(213);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Language:
				enterOuterAlt(_localctx, 1);
				{
				setState(182);
				languageTag();
				}
				break;
			case DomainType:
				enterOuterAlt(_localctx, 2);
				{
				setState(183);
				domainTag();
				}
				break;
			case Import:
				enterOuterAlt(_localctx, 3);
				{
				setState(184);
				importTag();
				}
				break;
			case File:
				enterOuterAlt(_localctx, 4);
				{
				setState(185);
				fileTag();
				}
				break;
			case Install:
				enterOuterAlt(_localctx, 5);
				{
				setState(186);
				installTag();
				}
				break;
			case Load:
				enterOuterAlt(_localctx, 6);
				{
				setState(187);
				loadTag();
				}
				break;
			case Function:
				enterOuterAlt(_localctx, 7);
				{
				setState(188);
				functionDeclTag();
				}
				break;
			case Return:
				enterOuterAlt(_localctx, 8);
				{
				setState(189);
				returnTag();
				}
				break;
			case Call:
				enterOuterAlt(_localctx, 9);
				{
				setState(190);
				callTag();
				}
				break;
			case Foreach:
				enterOuterAlt(_localctx, 10);
				{
				setState(191);
				foreachTag();
				}
				break;
			case Continue:
				enterOuterAlt(_localctx, 11);
				{
				setState(192);
				continueTag();
				}
				break;
			case Break:
				enterOuterAlt(_localctx, 12);
				{
				setState(193);
				breakTag();
				}
				break;
			case Exit:
				enterOuterAlt(_localctx, 13);
				{
				setState(194);
				exitTag();
				}
				break;
			case If:
				enterOuterAlt(_localctx, 14);
				{
				setState(195);
				ifTag();
				}
				break;
			case Elseif:
				enterOuterAlt(_localctx, 15);
				{
				setState(196);
				elseifTag();
				}
				break;
			case Else:
				enterOuterAlt(_localctx, 16);
				{
				setState(197);
				elseTag();
				}
				break;
			case Version:
				enterOuterAlt(_localctx, 17);
				{
				setState(198);
				versionTag();
				}
				break;
			case Capture:
				enterOuterAlt(_localctx, 18);
				{
				setState(199);
				captureTag();
				}
				break;
			case Log:
				enterOuterAlt(_localctx, 19);
				{
				setState(200);
				logTag();
				}
				break;
			case Let:
				enterOuterAlt(_localctx, 20);
				{
				setState(201);
				letTag();
				}
				break;
			case Do:
				enterOuterAlt(_localctx, 21);
				{
				setState(202);
				doTag();
				}
				break;
			case Switch:
				enterOuterAlt(_localctx, 22);
				{
				setState(203);
				switchTag();
				}
				break;
			case Case:
				enterOuterAlt(_localctx, 23);
				{
				setState(204);
				caseTag();
				}
				break;
			case Default:
				enterOuterAlt(_localctx, 24);
				{
				setState(205);
				defaultTag();
				}
				break;
			case Receive:
				enterOuterAlt(_localctx, 25);
				{
				setState(206);
				receiverTag();
				}
				break;
			case Send:
				enterOuterAlt(_localctx, 26);
				{
				setState(207);
				sendTag();
				}
				break;
			case Preserve:
				enterOuterAlt(_localctx, 27);
				{
				setState(208);
				preserveTag();
				}
				break;
			case Description:
				enterOuterAlt(_localctx, 28);
				{
				setState(209);
				descriptionTag();
				}
				break;
			case Publisher:
				enterOuterAlt(_localctx, 29);
				{
				setState(210);
				publisherTag();
				}
				break;
			case Outlet:
				enterOuterAlt(_localctx, 30);
				{
				setState(211);
				outletTag();
				}
				break;
			case Author:
				enterOuterAlt(_localctx, 31);
				{
				setState(212);
				authorTag();
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

	public static class BlockContext extends ParserRuleContext {
		public TerminalNode BlockTagStart() { return getToken(TemplateGrammer.BlockTagStart, 0); }
		public InstructionContext instruction() {
			return getRuleContext(InstructionContext.class,0);
		}
		public TerminalNode BlockTagEnd() { return getToken(TemplateGrammer.BlockTagEnd, 0); }
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_block);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(215);
			match(BlockTagStart);
			setState(216);
			instruction();
			setState(217);
			match(BlockTagEnd);
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

	public static class BlockEndContext extends ParserRuleContext {
		public TerminalNode BlockEndTagStart() { return getToken(TemplateGrammer.BlockEndTagStart, 0); }
		public TerminalNode BlockTagEnd() { return getToken(TemplateGrammer.BlockTagEnd, 0); }
		public TerminalNode Function() { return getToken(TemplateGrammer.Function, 0); }
		public TerminalNode Foreach() { return getToken(TemplateGrammer.Foreach, 0); }
		public TerminalNode If() { return getToken(TemplateGrammer.If, 0); }
		public TerminalNode File() { return getToken(TemplateGrammer.File, 0); }
		public TerminalNode Capture() { return getToken(TemplateGrammer.Capture, 0); }
		public TerminalNode Switch() { return getToken(TemplateGrammer.Switch, 0); }
		public TerminalNode Log() { return getToken(TemplateGrammer.Log, 0); }
		public TerminalNode Send() { return getToken(TemplateGrammer.Send, 0); }
		public TerminalNode Preserve() { return getToken(TemplateGrammer.Preserve, 0); }
		public TerminalNode Author() { return getToken(TemplateGrammer.Author, 0); }
		public TerminalNode Publisher() { return getToken(TemplateGrammer.Publisher, 0); }
		public TerminalNode Outlet() { return getToken(TemplateGrammer.Outlet, 0); }
		public BlockEndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockEnd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterBlockEnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitBlockEnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitBlockEnd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockEndContext blockEnd() throws RecognitionException {
		BlockEndContext _localctx = new BlockEndContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_blockEnd);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			match(BlockEndTagStart);
			setState(220);
			_la = _input.LA(1);
			if ( !(((((_la - 33)) & ~0x3f) == 0 && ((1L << (_la - 33)) & ((1L << (File - 33)) | (1L << (Function - 33)) | (1L << (Foreach - 33)) | (1L << (If - 33)) | (1L << (Switch - 33)) | (1L << (Capture - 33)) | (1L << (Log - 33)) | (1L << (Send - 33)) | (1L << (Preserve - 33)) | (1L << (Publisher - 33)) | (1L << (Outlet - 33)) | (1L << (Author - 33)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(221);
			match(BlockTagEnd);
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

	public static class DescriptionTagContext extends ParserRuleContext {
		public TerminalNode Description() { return getToken(TemplateGrammer.Description, 0); }
		public TerminalNode STRING() { return getToken(TemplateGrammer.STRING, 0); }
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(TemplateGrammer.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(TemplateGrammer.COMMA, i);
		}
		public DescriptionTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_descriptionTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterDescriptionTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitDescriptionTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitDescriptionTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DescriptionTagContext descriptionTag() throws RecognitionException {
		DescriptionTagContext _localctx = new DescriptionTagContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_descriptionTag);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(223);
			match(Description);
			setState(232);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << File) | (1L << Language) | (1L << Version) | (1L << Install) | (1L << Copy) | (1L << Import) | (1L << From) | (1L << Function) | (1L << Return) | (1L << Call) | (1L << Foreach) | (1L << Continue) | (1L << Break) | (1L << Exit) | (1L << If) | (1L << Switch) | (1L << Case) | (1L << Default) | (1L << Elseif) | (1L << Else) | (1L << Capture) | (1L << Let) | (1L << Do) | (1L << Load) | (1L << Log))) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & ((1L << (Send - 68)) | (1L << (Preserve - 68)) | (1L << (Publisher - 68)) | (1L << (Outlet - 68)) | (1L << (Author - 68)) | (1L << (To - 68)) | (1L << (Description - 68)) | (1L << (DomainType - 68)) | (1L << (EntityType - 68)) | (1L << (AttributeType - 68)) | (1L << (RelationshipType - 68)) | (1L << (EnumType - 68)) | (1L << (InterfaceType - 68)) | (1L << (OperationType - 68)) | (1L << (TypedefType - 68)) | (1L << (IDENT - 68)))) != 0)) {
				{
				setState(224);
				identifier();
				setState(229);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(225);
					match(COMMA);
					setState(226);
					identifier();
					}
					}
					setState(231);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(234);
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

	public static class NodeDescriptionContext extends ParserRuleContext {
		public TerminalNode Description() { return getToken(TemplateGrammer.Description, 0); }
		public TerminalNode STRING() { return getToken(TemplateGrammer.STRING, 0); }
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(TemplateGrammer.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(TemplateGrammer.COMMA, i);
		}
		public NodeDescriptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nodeDescription; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterNodeDescription(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitNodeDescription(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitNodeDescription(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NodeDescriptionContext nodeDescription() throws RecognitionException {
		NodeDescriptionContext _localctx = new NodeDescriptionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_nodeDescription);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(236);
			match(Description);
			setState(245);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << File) | (1L << Language) | (1L << Version) | (1L << Install) | (1L << Copy) | (1L << Import) | (1L << From) | (1L << Function) | (1L << Return) | (1L << Call) | (1L << Foreach) | (1L << Continue) | (1L << Break) | (1L << Exit) | (1L << If) | (1L << Switch) | (1L << Case) | (1L << Default) | (1L << Elseif) | (1L << Else) | (1L << Capture) | (1L << Let) | (1L << Do) | (1L << Load) | (1L << Log))) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & ((1L << (Send - 68)) | (1L << (Preserve - 68)) | (1L << (Publisher - 68)) | (1L << (Outlet - 68)) | (1L << (Author - 68)) | (1L << (To - 68)) | (1L << (Description - 68)) | (1L << (DomainType - 68)) | (1L << (EntityType - 68)) | (1L << (AttributeType - 68)) | (1L << (RelationshipType - 68)) | (1L << (EnumType - 68)) | (1L << (InterfaceType - 68)) | (1L << (OperationType - 68)) | (1L << (TypedefType - 68)) | (1L << (IDENT - 68)))) != 0)) {
				{
				setState(237);
				identifier();
				setState(242);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(238);
					match(COMMA);
					setState(239);
					identifier();
					}
					}
					setState(244);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(247);
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

	public static class LanguageTagContext extends ParserRuleContext {
		public TerminalNode Language() { return getToken(TemplateGrammer.Language, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public LanguageTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_languageTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterLanguageTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitLanguageTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitLanguageTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LanguageTagContext languageTag() throws RecognitionException {
		LanguageTagContext _localctx = new LanguageTagContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_languageTag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(249);
			match(Language);
			setState(250);
			identifier();
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

	public static class DomainTagContext extends ParserRuleContext {
		public TerminalNode DomainType() { return getToken(TemplateGrammer.DomainType, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public DomainTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterDomainTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitDomainTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitDomainTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DomainTagContext domainTag() throws RecognitionException {
		DomainTagContext _localctx = new DomainTagContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_domainTag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252);
			match(DomainType);
			setState(253);
			identifier();
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

	public static class VersionTagContext extends ParserRuleContext {
		public TerminalNode Version() { return getToken(TemplateGrammer.Version, 0); }
		public TerminalNode VERSION_NUM() { return getToken(TemplateGrammer.VERSION_NUM, 0); }
		public VersionTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_versionTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterVersionTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitVersionTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitVersionTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VersionTagContext versionTag() throws RecognitionException {
		VersionTagContext _localctx = new VersionTagContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_versionTag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255);
			match(Version);
			setState(256);
			match(VERSION_NUM);
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

	public static class ImportTagContext extends ParserRuleContext {
		public TerminalNode Import() { return getToken(TemplateGrammer.Import, 0); }
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public TerminalNode STRING() { return getToken(TemplateGrammer.STRING, 0); }
		public TerminalNode From() { return getToken(TemplateGrammer.From, 0); }
		public ImportTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterImportTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitImportTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitImportTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportTagContext importTag() throws RecognitionException {
		ImportTagContext _localctx = new ImportTagContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_importTag);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(258);
			match(Import);
			setState(261);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case File:
			case Language:
			case Version:
			case Install:
			case Copy:
			case Import:
			case From:
			case Function:
			case Return:
			case Call:
			case Foreach:
			case Continue:
			case Break:
			case Exit:
			case If:
			case Switch:
			case Case:
			case Default:
			case Elseif:
			case Else:
			case Capture:
			case Let:
			case Do:
			case Load:
			case Log:
			case Send:
			case Preserve:
			case Publisher:
			case Outlet:
			case Author:
			case To:
			case Description:
			case DomainType:
			case EntityType:
			case AttributeType:
			case RelationshipType:
			case EnumType:
			case InterfaceType:
			case OperationType:
			case TypedefType:
			case IDENT:
				{
				setState(259);
				identifier();
				}
				break;
			case STRING:
				{
				setState(260);
				match(STRING);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(265);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==From) {
				{
				setState(263);
				match(From);
				setState(264);
				identifier();
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

	public static class InstallTagContext extends ParserRuleContext {
		public TerminalNode Install() { return getToken(TemplateGrammer.Install, 0); }
		public List<FileArgContext> fileArg() {
			return getRuleContexts(FileArgContext.class);
		}
		public FileArgContext fileArg(int i) {
			return getRuleContext(FileArgContext.class,i);
		}
		public TerminalNode Copy() { return getToken(TemplateGrammer.Copy, 0); }
		public InstallTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_installTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterInstallTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitInstallTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitInstallTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstallTagContext installTag() throws RecognitionException {
		InstallTagContext _localctx = new InstallTagContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_installTag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(267);
			match(Install);
			setState(269);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(268);
				match(Copy);
				}
				break;
			}
			setState(271);
			fileArg();
			setState(272);
			fileArg();
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

	public static class FileTagContext extends ParserRuleContext {
		public TerminalNode File() { return getToken(TemplateGrammer.File, 0); }
		public List<FileArgContext> fileArg() {
			return getRuleContexts(FileArgContext.class);
		}
		public FileArgContext fileArg(int i) {
			return getRuleContext(FileArgContext.class,i);
		}
		public TerminalNode IfDoesNotExist() { return getToken(TemplateGrammer.IfDoesNotExist, 0); }
		public FileTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fileTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterFileTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitFileTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitFileTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FileTagContext fileTag() throws RecognitionException {
		FileTagContext _localctx = new FileTagContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_fileTag);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(274);
			match(File);
			setState(276);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IfDoesNotExist) {
				{
				setState(275);
				match(IfDoesNotExist);
				}
			}

			setState(278);
			fileArg();
			setState(279);
			fileArg();
			setState(280);
			fileArg();
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

	public static class FileArgContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public FileArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fileArg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterFileArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitFileArg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitFileArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FileArgContext fileArg() throws RecognitionException {
		FileArgContext _localctx = new FileArgContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_fileArg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282);
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

	public static class LoadTagContext extends ParserRuleContext {
		public TerminalNode Load() { return getToken(TemplateGrammer.Load, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public List<FileArgContext> fileArg() {
			return getRuleContexts(FileArgContext.class);
		}
		public FileArgContext fileArg(int i) {
			return getRuleContext(FileArgContext.class,i);
		}
		public LoadTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loadTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterLoadTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitLoadTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitLoadTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LoadTagContext loadTag() throws RecognitionException {
		LoadTagContext _localctx = new LoadTagContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_loadTag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(284);
			match(Load);
			setState(285);
			identifier();
			setState(286);
			fileArg();
			setState(287);
			fileArg();
			setState(288);
			fileArg();
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

	public static class FunctionDeclTagContext extends ParserRuleContext {
		public TerminalNode Function() { return getToken(TemplateGrammer.Function, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public List<TerminalNode> OpenParen() { return getTokens(TemplateGrammer.OpenParen); }
		public TerminalNode OpenParen(int i) {
			return getToken(TemplateGrammer.OpenParen, i);
		}
		public List<TerminalNode> CloseParen() { return getTokens(TemplateGrammer.CloseParen); }
		public TerminalNode CloseParen(int i) {
			return getToken(TemplateGrammer.CloseParen, i);
		}
		public List<NodeDescriptionContext> nodeDescription() {
			return getRuleContexts(NodeDescriptionContext.class);
		}
		public NodeDescriptionContext nodeDescription(int i) {
			return getRuleContext(NodeDescriptionContext.class,i);
		}
		public List<FunctionDeclArgListContext> functionDeclArgList() {
			return getRuleContexts(FunctionDeclArgListContext.class);
		}
		public FunctionDeclArgListContext functionDeclArgList(int i) {
			return getRuleContext(FunctionDeclArgListContext.class,i);
		}
		public TerminalNode Outputs() { return getToken(TemplateGrammer.Outputs, 0); }
		public FunctionDeclTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDeclTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterFunctionDeclTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitFunctionDeclTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitFunctionDeclTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionDeclTagContext functionDeclTag() throws RecognitionException {
		FunctionDeclTagContext _localctx = new FunctionDeclTagContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_functionDeclTag);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(290);
			match(Function);
			setState(291);
			identifier();
			setState(295);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Description) {
				{
				{
				setState(292);
				nodeDescription();
				}
				}
				setState(297);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(298);
			match(OpenParen);
			setState(300);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << File) | (1L << Language) | (1L << Version) | (1L << Install) | (1L << Copy) | (1L << Import) | (1L << From) | (1L << Function) | (1L << Return) | (1L << Call) | (1L << Foreach) | (1L << Continue) | (1L << Break) | (1L << Exit) | (1L << If) | (1L << Switch) | (1L << Case) | (1L << Default) | (1L << Elseif) | (1L << Else) | (1L << Capture) | (1L << Let) | (1L << Do) | (1L << Load) | (1L << Log))) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & ((1L << (Send - 68)) | (1L << (Preserve - 68)) | (1L << (Publisher - 68)) | (1L << (Outlet - 68)) | (1L << (Author - 68)) | (1L << (To - 68)) | (1L << (Description - 68)) | (1L << (DomainType - 68)) | (1L << (EntityType - 68)) | (1L << (AttributeType - 68)) | (1L << (RelationshipType - 68)) | (1L << (EnumType - 68)) | (1L << (InterfaceType - 68)) | (1L << (OperationType - 68)) | (1L << (TypedefType - 68)) | (1L << (IDENT - 68)))) != 0)) {
				{
				setState(299);
				functionDeclArgList();
				}
			}

			setState(302);
			match(CloseParen);
			setState(308);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Outputs) {
				{
				setState(303);
				match(Outputs);
				setState(304);
				match(OpenParen);
				setState(305);
				functionDeclArgList();
				setState(306);
				match(CloseParen);
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

	public static class FunctionDeclArgListContext extends ParserRuleContext {
		public List<FunctionDeclArgContext> functionDeclArg() {
			return getRuleContexts(FunctionDeclArgContext.class);
		}
		public FunctionDeclArgContext functionDeclArg(int i) {
			return getRuleContext(FunctionDeclArgContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(TemplateGrammer.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(TemplateGrammer.COMMA, i);
		}
		public FunctionDeclArgListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDeclArgList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterFunctionDeclArgList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitFunctionDeclArgList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitFunctionDeclArgList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionDeclArgListContext functionDeclArgList() throws RecognitionException {
		FunctionDeclArgListContext _localctx = new FunctionDeclArgListContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_functionDeclArgList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(310);
			functionDeclArg();
			setState(315);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(311);
				match(COMMA);
				setState(312);
				functionDeclArg();
				}
				}
				setState(317);
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

	public static class FunctionDeclArgContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public List<NodeDescriptionContext> nodeDescription() {
			return getRuleContexts(NodeDescriptionContext.class);
		}
		public NodeDescriptionContext nodeDescription(int i) {
			return getRuleContext(NodeDescriptionContext.class,i);
		}
		public FunctionDeclArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDeclArg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterFunctionDeclArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitFunctionDeclArg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitFunctionDeclArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionDeclArgContext functionDeclArg() throws RecognitionException {
		FunctionDeclArgContext _localctx = new FunctionDeclArgContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_functionDeclArg);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(318);
			identifier();
			setState(322);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Description) {
				{
				{
				setState(319);
				nodeDescription();
				}
				}
				setState(324);
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

	public static class CallTagContext extends ParserRuleContext {
		public TerminalNode Call() { return getToken(TemplateGrammer.Call, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public List<TerminalNode> OpenParen() { return getTokens(TemplateGrammer.OpenParen); }
		public TerminalNode OpenParen(int i) {
			return getToken(TemplateGrammer.OpenParen, i);
		}
		public List<TerminalNode> CloseParen() { return getTokens(TemplateGrammer.CloseParen); }
		public TerminalNode CloseParen(int i) {
			return getToken(TemplateGrammer.CloseParen, i);
		}
		public TerminalNode Explicit() { return getToken(TemplateGrammer.Explicit, 0); }
		public InputCallArgListContext inputCallArgList() {
			return getRuleContext(InputCallArgListContext.class,0);
		}
		public TerminalNode Outputs() { return getToken(TemplateGrammer.Outputs, 0); }
		public OutputCallArgListContext outputCallArgList() {
			return getRuleContext(OutputCallArgListContext.class,0);
		}
		public CallTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterCallTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitCallTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitCallTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CallTagContext callTag() throws RecognitionException {
		CallTagContext _localctx = new CallTagContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_callTag);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(325);
			match(Call);
			setState(327);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Explicit) {
				{
				setState(326);
				match(Explicit);
				}
			}

			setState(329);
			identifier();
			setState(330);
			match(OpenParen);
			setState(332);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << File) | (1L << Language) | (1L << Version) | (1L << Install) | (1L << Copy) | (1L << Import) | (1L << From) | (1L << Function) | (1L << Return) | (1L << Call) | (1L << Foreach) | (1L << Continue) | (1L << Break) | (1L << Exit) | (1L << If) | (1L << Switch) | (1L << Case) | (1L << Default) | (1L << Elseif) | (1L << Else) | (1L << Capture) | (1L << Let) | (1L << Do) | (1L << Load) | (1L << Log))) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & ((1L << (Send - 68)) | (1L << (Preserve - 68)) | (1L << (Publisher - 68)) | (1L << (Outlet - 68)) | (1L << (Author - 68)) | (1L << (To - 68)) | (1L << (Description - 68)) | (1L << (DomainType - 68)) | (1L << (EntityType - 68)) | (1L << (AttributeType - 68)) | (1L << (RelationshipType - 68)) | (1L << (EnumType - 68)) | (1L << (InterfaceType - 68)) | (1L << (OperationType - 68)) | (1L << (TypedefType - 68)) | (1L << (IDENT - 68)))) != 0)) {
				{
				setState(331);
				inputCallArgList();
				}
			}

			setState(334);
			match(CloseParen);
			setState(340);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Outputs) {
				{
				setState(335);
				match(Outputs);
				setState(336);
				match(OpenParen);
				setState(337);
				outputCallArgList();
				setState(338);
				match(CloseParen);
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

	public static class CallArgContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode Colon() { return getToken(TemplateGrammer.Colon, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public CallArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callArg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterCallArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitCallArg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitCallArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CallArgContext callArg() throws RecognitionException {
		CallArgContext _localctx = new CallArgContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_callArg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(342);
			identifier();
			setState(343);
			match(Colon);
			setState(344);
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

	public static class InputCallArgListContext extends ParserRuleContext {
		public List<CallArgContext> callArg() {
			return getRuleContexts(CallArgContext.class);
		}
		public CallArgContext callArg(int i) {
			return getRuleContext(CallArgContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(TemplateGrammer.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(TemplateGrammer.COMMA, i);
		}
		public InputCallArgListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inputCallArgList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterInputCallArgList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitInputCallArgList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitInputCallArgList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InputCallArgListContext inputCallArgList() throws RecognitionException {
		InputCallArgListContext _localctx = new InputCallArgListContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_inputCallArgList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(346);
			callArg();
			setState(351);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(347);
				match(COMMA);
				setState(348);
				callArg();
				}
				}
				setState(353);
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

	public static class OutputCallArgListContext extends ParserRuleContext {
		public List<CallArgContext> callArg() {
			return getRuleContexts(CallArgContext.class);
		}
		public CallArgContext callArg(int i) {
			return getRuleContext(CallArgContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(TemplateGrammer.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(TemplateGrammer.COMMA, i);
		}
		public OutputCallArgListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_outputCallArgList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterOutputCallArgList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitOutputCallArgList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitOutputCallArgList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OutputCallArgListContext outputCallArgList() throws RecognitionException {
		OutputCallArgListContext _localctx = new OutputCallArgListContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_outputCallArgList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(354);
			callArg();
			setState(359);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(355);
				match(COMMA);
				setState(356);
				callArg();
				}
				}
				setState(361);
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

	public static class ReturnTagContext extends ParserRuleContext {
		public TerminalNode Return() { return getToken(TemplateGrammer.Return, 0); }
		public ReturnTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterReturnTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitReturnTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitReturnTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnTagContext returnTag() throws RecognitionException {
		ReturnTagContext _localctx = new ReturnTagContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_returnTag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(362);
			match(Return);
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

	public static class ForeachTagContext extends ParserRuleContext {
		public TerminalNode Foreach() { return getToken(TemplateGrammer.Foreach, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode As() { return getToken(TemplateGrammer.As, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode In() { return getToken(TemplateGrammer.In, 0); }
		public TerminalNode ForeachRange() { return getToken(TemplateGrammer.ForeachRange, 0); }
		public TerminalNode By() { return getToken(TemplateGrammer.By, 0); }
		public TerminalNode If() { return getToken(TemplateGrammer.If, 0); }
		public ForeachTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_foreachTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterForeachTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitForeachTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitForeachTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForeachTagContext foreachTag() throws RecognitionException {
		ForeachTagContext _localctx = new ForeachTagContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_foreachTag);
		int _la;
		try {
			setState(389);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(364);
				match(Foreach);
				setState(365);
				expression(0);
				setState(368);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==As) {
					{
					setState(366);
					match(As);
					setState(367);
					identifier();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(370);
				match(Foreach);
				setState(371);
				identifier();
				setState(372);
				match(In);
				setState(373);
				expression(0);
				setState(380);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ForeachRange) {
					{
					setState(374);
					match(ForeachRange);
					setState(375);
					expression(0);
					setState(378);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==By) {
						{
						setState(376);
						match(By);
						setState(377);
						expression(0);
						}
					}

					}
				}

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(382);
				match(Foreach);
				setState(383);
				identifier();
				setState(384);
				match(In);
				setState(385);
				expression(0);
				setState(386);
				match(If);
				setState(387);
				expression(0);
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

	public static class BreakTagContext extends ParserRuleContext {
		public TerminalNode Break() { return getToken(TemplateGrammer.Break, 0); }
		public BreakTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterBreakTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitBreakTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitBreakTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BreakTagContext breakTag() throws RecognitionException {
		BreakTagContext _localctx = new BreakTagContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_breakTag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(391);
			match(Break);
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

	public static class ExitTagContext extends ParserRuleContext {
		public TerminalNode Exit() { return getToken(TemplateGrammer.Exit, 0); }
		public ExitTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exitTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterExitTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitExitTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitExitTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExitTagContext exitTag() throws RecognitionException {
		ExitTagContext _localctx = new ExitTagContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_exitTag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(393);
			match(Exit);
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

	public static class ContinueTagContext extends ParserRuleContext {
		public TerminalNode Continue() { return getToken(TemplateGrammer.Continue, 0); }
		public ContinueTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continueTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterContinueTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitContinueTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitContinueTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContinueTagContext continueTag() throws RecognitionException {
		ContinueTagContext _localctx = new ContinueTagContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_continueTag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(395);
			match(Continue);
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

	public static class IfTagContext extends ParserRuleContext {
		public TerminalNode If() { return getToken(TemplateGrammer.If, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public IfTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterIfTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitIfTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitIfTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfTagContext ifTag() throws RecognitionException {
		IfTagContext _localctx = new IfTagContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_ifTag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(397);
			match(If);
			setState(398);
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

	public static class ElseifTagContext extends ParserRuleContext {
		public TerminalNode Elseif() { return getToken(TemplateGrammer.Elseif, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ElseifTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elseifTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterElseifTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitElseifTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitElseifTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElseifTagContext elseifTag() throws RecognitionException {
		ElseifTagContext _localctx = new ElseifTagContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_elseifTag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(400);
			match(Elseif);
			setState(401);
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

	public static class ElseTagContext extends ParserRuleContext {
		public TerminalNode Else() { return getToken(TemplateGrammer.Else, 0); }
		public ElseTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elseTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterElseTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitElseTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitElseTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElseTagContext elseTag() throws RecognitionException {
		ElseTagContext _localctx = new ElseTagContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_elseTag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(403);
			match(Else);
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

	public static class SwitchTagContext extends ParserRuleContext {
		public TerminalNode Switch() { return getToken(TemplateGrammer.Switch, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public SwitchTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterSwitchTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitSwitchTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitSwitchTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SwitchTagContext switchTag() throws RecognitionException {
		SwitchTagContext _localctx = new SwitchTagContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_switchTag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(405);
			match(Switch);
			setState(406);
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

	public static class CaseArgContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(TemplateGrammer.INTEGER, 0); }
		public TerminalNode STRING() { return getToken(TemplateGrammer.STRING, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public PrimitiveTypeContext primitiveType() {
			return getRuleContext(PrimitiveTypeContext.class,0);
		}
		public CaseArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseArg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterCaseArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitCaseArg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitCaseArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaseArgContext caseArg() throws RecognitionException {
		CaseArgContext _localctx = new CaseArgContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_caseArg);
		try {
			setState(412);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTEGER:
				enterOuterAlt(_localctx, 1);
				{
				setState(408);
				match(INTEGER);
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(409);
				match(STRING);
				}
				break;
			case File:
			case Language:
			case Version:
			case Install:
			case Copy:
			case Import:
			case From:
			case Function:
			case Return:
			case Call:
			case Foreach:
			case Continue:
			case Break:
			case Exit:
			case If:
			case Switch:
			case Case:
			case Default:
			case Elseif:
			case Else:
			case Capture:
			case Let:
			case Do:
			case Load:
			case Log:
			case Send:
			case Preserve:
			case Publisher:
			case Outlet:
			case Author:
			case To:
			case Description:
			case DomainType:
			case EntityType:
			case AttributeType:
			case RelationshipType:
			case EnumType:
			case InterfaceType:
			case OperationType:
			case TypedefType:
			case IDENT:
				enterOuterAlt(_localctx, 3);
				{
				setState(410);
				identifier();
				}
				break;
			case BOOLEAN_TYPE:
			case INT32_TYPE:
			case INT64_TYPE:
			case UUID_TYPE:
			case FLOAT_TYPE:
			case DOUBLE_TYPE:
			case STRING_TYPE:
			case DATE_TYPE:
			case ASSET_TYPE:
				enterOuterAlt(_localctx, 4);
				{
				setState(411);
				primitiveType();
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

	public static class CaseTagContext extends ParserRuleContext {
		public TerminalNode Case() { return getToken(TemplateGrammer.Case, 0); }
		public List<CaseArgContext> caseArg() {
			return getRuleContexts(CaseArgContext.class);
		}
		public CaseArgContext caseArg(int i) {
			return getRuleContext(CaseArgContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(TemplateGrammer.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(TemplateGrammer.COMMA, i);
		}
		public CaseTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterCaseTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitCaseTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitCaseTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaseTagContext caseTag() throws RecognitionException {
		CaseTagContext _localctx = new CaseTagContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_caseTag);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(414);
			match(Case);
			setState(415);
			caseArg();
			setState(420);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(416);
				match(COMMA);
				setState(417);
				caseArg();
				}
				}
				setState(422);
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

	public static class DefaultTagContext extends ParserRuleContext {
		public TerminalNode Default() { return getToken(TemplateGrammer.Default, 0); }
		public DefaultTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterDefaultTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitDefaultTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitDefaultTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefaultTagContext defaultTag() throws RecognitionException {
		DefaultTagContext _localctx = new DefaultTagContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_defaultTag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(423);
			match(Default);
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

	public static class CaptureTagContext extends ParserRuleContext {
		public TerminalNode Capture() { return getToken(TemplateGrammer.Capture, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public CaptureTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_captureTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterCaptureTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitCaptureTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitCaptureTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaptureTagContext captureTag() throws RecognitionException {
		CaptureTagContext _localctx = new CaptureTagContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_captureTag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(425);
			match(Capture);
			setState(426);
			identifier();
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

	public static class ReceiverTagContext extends ParserRuleContext {
		public TerminalNode Receive() { return getToken(TemplateGrammer.Receive, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode Distinct() { return getToken(TemplateGrammer.Distinct, 0); }
		public List<NodeDescriptionContext> nodeDescription() {
			return getRuleContexts(NodeDescriptionContext.class);
		}
		public NodeDescriptionContext nodeDescription(int i) {
			return getRuleContext(NodeDescriptionContext.class,i);
		}
		public ReceiverTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_receiverTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterReceiverTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitReceiverTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitReceiverTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReceiverTagContext receiverTag() throws RecognitionException {
		ReceiverTagContext _localctx = new ReceiverTagContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_receiverTag);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(428);
			match(Receive);
			setState(430);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Distinct) {
				{
				setState(429);
				match(Distinct);
				}
			}

			setState(432);
			identifier();
			setState(436);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Description) {
				{
				{
				setState(433);
				nodeDescription();
				}
				}
				setState(438);
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

	public static class SendTagContext extends ParserRuleContext {
		public TerminalNode Send() { return getToken(TemplateGrammer.Send, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public SendTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sendTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterSendTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitSendTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitSendTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SendTagContext sendTag() throws RecognitionException {
		SendTagContext _localctx = new SendTagContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_sendTag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(439);
			match(Send);
			setState(440);
			identifier();
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

	public static class PreserveTagContext extends ParserRuleContext {
		public TerminalNode Preserve() { return getToken(TemplateGrammer.Preserve, 0); }
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public TerminalNode Deprecates() { return getToken(TemplateGrammer.Deprecates, 0); }
		public List<TerminalNode> COMMA() { return getTokens(TemplateGrammer.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(TemplateGrammer.COMMA, i);
		}
		public PreserveTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_preserveTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterPreserveTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitPreserveTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitPreserveTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PreserveTagContext preserveTag() throws RecognitionException {
		PreserveTagContext _localctx = new PreserveTagContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_preserveTag);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(442);
			match(Preserve);
			setState(443);
			identifier();
			setState(453);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Deprecates) {
				{
				setState(444);
				match(Deprecates);
				setState(445);
				identifier();
				setState(450);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(446);
					match(COMMA);
					setState(447);
					identifier();
					}
					}
					setState(452);
					_errHandler.sync(this);
					_la = _input.LA(1);
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

	public static class LogTagContext extends ParserRuleContext {
		public TerminalNode Log() { return getToken(TemplateGrammer.Log, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public LogTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterLogTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitLogTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitLogTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogTagContext logTag() throws RecognitionException {
		LogTagContext _localctx = new LogTagContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_logTag);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(455);
			match(Log);
			setState(457);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << File) | (1L << Language) | (1L << Version) | (1L << Install) | (1L << Copy) | (1L << Import) | (1L << From) | (1L << Function) | (1L << Return) | (1L << Call) | (1L << Foreach) | (1L << Continue) | (1L << Break) | (1L << Exit) | (1L << If) | (1L << Switch) | (1L << Case) | (1L << Default) | (1L << Elseif) | (1L << Else) | (1L << Capture) | (1L << Let) | (1L << Do) | (1L << Load) | (1L << Log))) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & ((1L << (Send - 68)) | (1L << (Preserve - 68)) | (1L << (Publisher - 68)) | (1L << (Outlet - 68)) | (1L << (Author - 68)) | (1L << (To - 68)) | (1L << (Description - 68)) | (1L << (DomainType - 68)) | (1L << (EntityType - 68)) | (1L << (AttributeType - 68)) | (1L << (RelationshipType - 68)) | (1L << (EnumType - 68)) | (1L << (InterfaceType - 68)) | (1L << (OperationType - 68)) | (1L << (TypedefType - 68)) | (1L << (IDENT - 68)))) != 0)) {
				{
				setState(456);
				identifier();
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

	public static class LetTagContext extends ParserRuleContext {
		public TerminalNode Let() { return getToken(TemplateGrammer.Let, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode EQUALS() { return getToken(TemplateGrammer.EQUALS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public LetTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_letTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterLetTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitLetTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitLetTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LetTagContext letTag() throws RecognitionException {
		LetTagContext _localctx = new LetTagContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_letTag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(459);
			match(Let);
			setState(460);
			identifier();
			setState(461);
			match(EQUALS);
			setState(462);
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

	public static class DoTagContext extends ParserRuleContext {
		public TerminalNode Do() { return getToken(TemplateGrammer.Do, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public DoTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterDoTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitDoTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitDoTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DoTagContext doTag() throws RecognitionException {
		DoTagContext _localctx = new DoTagContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_doTag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(464);
			match(Do);
			setState(465);
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

	public static class VariableTagContext extends ParserRuleContext {
		public TerminalNode VarTagStart() { return getToken(TemplateGrammer.VarTagStart, 0); }
		public TerminalNode VarTagEnd() { return getToken(TemplateGrammer.VarTagEnd, 0); }
		public TerminalNode Dollar() { return getToken(TemplateGrammer.Dollar, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VariableTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterVariableTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitVariableTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitVariableTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableTagContext variableTag() throws RecognitionException {
		VariableTagContext _localctx = new VariableTagContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_variableTag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(467);
			match(VarTagStart);
			setState(470);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Dollar:
				{
				setState(468);
				match(Dollar);
				}
				break;
			case True:
			case False:
			case HashConstant:
			case LogicalNot:
			case Plus:
			case Minus:
			case OpenParen:
			case ArrayOpen:
			case File:
			case Language:
			case Version:
			case Install:
			case Copy:
			case Import:
			case From:
			case Function:
			case Return:
			case Call:
			case Foreach:
			case Continue:
			case Break:
			case Exit:
			case If:
			case Switch:
			case Case:
			case Default:
			case Elseif:
			case Else:
			case Capture:
			case Let:
			case Do:
			case Load:
			case Log:
			case Send:
			case Preserve:
			case Publisher:
			case Outlet:
			case Author:
			case To:
			case Description:
			case DomainType:
			case EntityType:
			case AttributeType:
			case RelationshipType:
			case EnumType:
			case InterfaceType:
			case OperationType:
			case TypedefType:
			case INTEGER:
			case FLOAT:
			case IDENT:
			case STRING:
				{
				setState(469);
				expression(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(472);
			match(VarTagEnd);
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
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode OpenParen() { return getToken(TemplateGrammer.OpenParen, 0); }
		public TerminalNode CloseParen() { return getToken(TemplateGrammer.CloseParen, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public MethodCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterMethodCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitMethodCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitMethodCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodCallContext methodCall() throws RecognitionException {
		MethodCallContext _localctx = new MethodCallContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_methodCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(474);
			identifier();
			setState(475);
			match(OpenParen);
			setState(477);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << True) | (1L << False) | (1L << HashConstant) | (1L << LogicalNot) | (1L << Plus) | (1L << Minus) | (1L << OpenParen) | (1L << ArrayOpen) | (1L << File) | (1L << Language) | (1L << Version) | (1L << Install) | (1L << Copy) | (1L << Import) | (1L << From) | (1L << Function) | (1L << Return) | (1L << Call) | (1L << Foreach) | (1L << Continue) | (1L << Break) | (1L << Exit) | (1L << If) | (1L << Switch) | (1L << Case) | (1L << Default) | (1L << Elseif) | (1L << Else) | (1L << Capture) | (1L << Let) | (1L << Do) | (1L << Load) | (1L << Log))) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & ((1L << (Send - 68)) | (1L << (Preserve - 68)) | (1L << (Publisher - 68)) | (1L << (Outlet - 68)) | (1L << (Author - 68)) | (1L << (To - 68)) | (1L << (Description - 68)) | (1L << (DomainType - 68)) | (1L << (EntityType - 68)) | (1L << (AttributeType - 68)) | (1L << (RelationshipType - 68)) | (1L << (EnumType - 68)) | (1L << (InterfaceType - 68)) | (1L << (OperationType - 68)) | (1L << (TypedefType - 68)) | (1L << (INTEGER - 68)) | (1L << (FLOAT - 68)) | (1L << (IDENT - 68)) | (1L << (STRING - 68)))) != 0)) {
				{
				setState(476);
				expressionList();
				}
			}

			setState(479);
			match(CloseParen);
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
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public MethodCallContext methodCall() {
			return getRuleContext(MethodCallContext.class,0);
		}
		public TerminalNode OpenParen() { return getToken(TemplateGrammer.OpenParen, 0); }
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(TemplateGrammer.CloseParen, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Plus() { return getToken(TemplateGrammer.Plus, 0); }
		public TerminalNode Minus() { return getToken(TemplateGrammer.Minus, 0); }
		public TerminalNode LogicalNot() { return getToken(TemplateGrammer.LogicalNot, 0); }
		public ArraySpecifierContext arraySpecifier() {
			return getRuleContext(ArraySpecifierContext.class,0);
		}
		public TerminalNode Multiply() { return getToken(TemplateGrammer.Multiply, 0); }
		public TerminalNode Divide() { return getToken(TemplateGrammer.Divide, 0); }
		public TerminalNode Modulo() { return getToken(TemplateGrammer.Modulo, 0); }
		public TerminalNode LessThanEqual() { return getToken(TemplateGrammer.LessThanEqual, 0); }
		public TerminalNode GreaterThanEqual() { return getToken(TemplateGrammer.GreaterThanEqual, 0); }
		public TerminalNode GreaterThan() { return getToken(TemplateGrammer.GreaterThan, 0); }
		public TerminalNode LessThan() { return getToken(TemplateGrammer.LessThan, 0); }
		public TerminalNode IsEqual() { return getToken(TemplateGrammer.IsEqual, 0); }
		public TerminalNode NotEqual() { return getToken(TemplateGrammer.NotEqual, 0); }
		public TerminalNode LogicalAnd() { return getToken(TemplateGrammer.LogicalAnd, 0); }
		public TerminalNode LogicalOr() { return getToken(TemplateGrammer.LogicalOr, 0); }
		public TerminalNode Colon() { return getToken(TemplateGrammer.Colon, 0); }
		public TerminalNode QuestionMark() { return getToken(TemplateGrammer.QuestionMark, 0); }
		public TerminalNode DOT() { return getToken(TemplateGrammer.DOT, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public FilterContext filter() {
			return getRuleContext(FilterContext.class,0);
		}
		public TerminalNode Pipe() { return getToken(TemplateGrammer.Pipe, 0); }
		public TerminalNode InstanceOf() { return getToken(TemplateGrammer.InstanceOf, 0); }
		public TerminalNode Extends() { return getToken(TemplateGrammer.Extends, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitExpression(this);
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
		int _startState = 90;
		enterRecursionRule(_localctx, 90, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(494);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				{
				setState(482);
				primary();
				}
				break;
			case 2:
				{
				setState(483);
				methodCall();
				}
				break;
			case 3:
				{
				setState(484);
				match(OpenParen);
				setState(485);
				typeType();
				setState(486);
				match(CloseParen);
				setState(487);
				expression(13);
				}
				break;
			case 4:
				{
				setState(489);
				((ExpressionContext)_localctx).prefix = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==Plus || _la==Minus) ) {
					((ExpressionContext)_localctx).prefix = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(490);
				expression(12);
				}
				break;
			case 5:
				{
				setState(491);
				((ExpressionContext)_localctx).prefix = match(LogicalNot);
				setState(492);
				expression(11);
				}
				break;
			case 6:
				{
				setState(493);
				arraySpecifier();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(537);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(535);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(496);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(497);
						((ExpressionContext)_localctx).bop = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Multiply) | (1L << Divide) | (1L << Modulo))) != 0)) ) {
							((ExpressionContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(498);
						expression(11);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(499);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(500);
						((ExpressionContext)_localctx).bop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==Plus || _la==Minus) ) {
							((ExpressionContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(501);
						expression(10);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(502);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(503);
						((ExpressionContext)_localctx).bop = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GreaterThan) | (1L << LessThan) | (1L << GreaterThanEqual) | (1L << LessThanEqual))) != 0)) ) {
							((ExpressionContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(504);
						expression(9);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(505);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(506);
						((ExpressionContext)_localctx).bop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==IsEqual || _la==NotEqual) ) {
							((ExpressionContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(507);
						expression(8);
						}
						break;
					case 5:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(508);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(509);
						((ExpressionContext)_localctx).bop = match(LogicalAnd);
						setState(510);
						expression(5);
						}
						break;
					case 6:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(511);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(512);
						((ExpressionContext)_localctx).bop = match(LogicalOr);
						setState(513);
						expression(4);
						}
						break;
					case 7:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(514);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(515);
						((ExpressionContext)_localctx).bop = match(QuestionMark);
						setState(516);
						expression(0);
						setState(517);
						match(Colon);
						setState(518);
						expression(2);
						}
						break;
					case 8:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(520);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(521);
						((ExpressionContext)_localctx).bop = match(DOT);
						setState(524);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
						case 1:
							{
							setState(522);
							identifier();
							}
							break;
						case 2:
							{
							setState(523);
							methodCall();
							}
							break;
						}
						}
						break;
					case 9:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(526);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(527);
						((ExpressionContext)_localctx).bop = match(Pipe);
						setState(528);
						filter();
						}
						break;
					case 10:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(529);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(530);
						((ExpressionContext)_localctx).bop = match(InstanceOf);
						setState(531);
						typeType();
						}
						break;
					case 11:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(532);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(533);
						((ExpressionContext)_localctx).bop = match(Extends);
						setState(534);
						typeType();
						}
						break;
					}
					} 
				}
				setState(539);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
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

	public static class ExpressionListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(TemplateGrammer.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(TemplateGrammer.COMMA, i);
		}
		public ExpressionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterExpressionList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitExpressionList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitExpressionList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionListContext expressionList() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_expressionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(540);
			expression(0);
			setState(545);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(541);
				match(COMMA);
				setState(542);
				expression(0);
				}
				}
				setState(547);
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

	public static class ArraySpecifierContext extends ParserRuleContext {
		public TerminalNode ArrayOpen() { return getToken(TemplateGrammer.ArrayOpen, 0); }
		public TerminalNode ArrayClose() { return getToken(TemplateGrammer.ArrayClose, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public ArraySpecifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arraySpecifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterArraySpecifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitArraySpecifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitArraySpecifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArraySpecifierContext arraySpecifier() throws RecognitionException {
		ArraySpecifierContext _localctx = new ArraySpecifierContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_arraySpecifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(548);
			match(ArrayOpen);
			setState(550);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << True) | (1L << False) | (1L << HashConstant) | (1L << LogicalNot) | (1L << Plus) | (1L << Minus) | (1L << OpenParen) | (1L << ArrayOpen) | (1L << File) | (1L << Language) | (1L << Version) | (1L << Install) | (1L << Copy) | (1L << Import) | (1L << From) | (1L << Function) | (1L << Return) | (1L << Call) | (1L << Foreach) | (1L << Continue) | (1L << Break) | (1L << Exit) | (1L << If) | (1L << Switch) | (1L << Case) | (1L << Default) | (1L << Elseif) | (1L << Else) | (1L << Capture) | (1L << Let) | (1L << Do) | (1L << Load) | (1L << Log))) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & ((1L << (Send - 68)) | (1L << (Preserve - 68)) | (1L << (Publisher - 68)) | (1L << (Outlet - 68)) | (1L << (Author - 68)) | (1L << (To - 68)) | (1L << (Description - 68)) | (1L << (DomainType - 68)) | (1L << (EntityType - 68)) | (1L << (AttributeType - 68)) | (1L << (RelationshipType - 68)) | (1L << (EnumType - 68)) | (1L << (InterfaceType - 68)) | (1L << (OperationType - 68)) | (1L << (TypedefType - 68)) | (1L << (INTEGER - 68)) | (1L << (FLOAT - 68)) | (1L << (IDENT - 68)) | (1L << (STRING - 68)))) != 0)) {
				{
				setState(549);
				expressionList();
				}
			}

			setState(552);
			match(ArrayClose);
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

	public static class FilterParamExpressionContext extends ParserRuleContext {
		public TerminalNode OpenParen() { return getToken(TemplateGrammer.OpenParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(TemplateGrammer.CloseParen, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public FilterParamExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filterParamExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterFilterParamExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitFilterParamExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitFilterParamExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FilterParamExpressionContext filterParamExpression() throws RecognitionException {
		FilterParamExpressionContext _localctx = new FilterParamExpressionContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_filterParamExpression);
		try {
			setState(559);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OpenParen:
				enterOuterAlt(_localctx, 1);
				{
				setState(554);
				match(OpenParen);
				setState(555);
				expression(0);
				setState(556);
				match(CloseParen);
				}
				break;
			case File:
			case Language:
			case Version:
			case Install:
			case Copy:
			case Import:
			case From:
			case Function:
			case Return:
			case Call:
			case Foreach:
			case Continue:
			case Break:
			case Exit:
			case If:
			case Switch:
			case Case:
			case Default:
			case Elseif:
			case Else:
			case Capture:
			case Let:
			case Do:
			case Load:
			case Log:
			case Send:
			case Preserve:
			case Publisher:
			case Outlet:
			case Author:
			case To:
			case Description:
			case DomainType:
			case EntityType:
			case AttributeType:
			case RelationshipType:
			case EnumType:
			case InterfaceType:
			case OperationType:
			case TypedefType:
			case IDENT:
				enterOuterAlt(_localctx, 2);
				{
				setState(558);
				identifier();
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

	public static class PrimaryContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public TerminalNode OpenParen() { return getToken(TemplateGrammer.OpenParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(TemplateGrammer.CloseParen, 0); }
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitPrimary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitPrimary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_primary);
		try {
			setState(567);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(561);
				identifier();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(562);
				constant();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(563);
				match(OpenParen);
				setState(564);
				expression(0);
				setState(565);
				match(CloseParen);
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

	public static class ConstantContext extends ParserRuleContext {
		public Token BOOLEAN;
		public TerminalNode INTEGER() { return getToken(TemplateGrammer.INTEGER, 0); }
		public TerminalNode FLOAT() { return getToken(TemplateGrammer.FLOAT, 0); }
		public TerminalNode STRING() { return getToken(TemplateGrammer.STRING, 0); }
		public TerminalNode True() { return getToken(TemplateGrammer.True, 0); }
		public TerminalNode False() { return getToken(TemplateGrammer.False, 0); }
		public TerminalNode HashConstant() { return getToken(TemplateGrammer.HashConstant, 0); }
		public TerminalNode OpenParen() { return getToken(TemplateGrammer.OpenParen, 0); }
		public TerminalNode Colon() { return getToken(TemplateGrammer.Colon, 0); }
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(TemplateGrammer.CloseParen, 0); }
		public BuiltinTypeContext builtinType() {
			return getRuleContext(BuiltinTypeContext.class,0);
		}
		public List<TerminalNode> IDENT() { return getTokens(TemplateGrammer.IDENT); }
		public TerminalNode IDENT(int i) {
			return getToken(TemplateGrammer.IDENT, i);
		}
		public List<TerminalNode> DOT() { return getTokens(TemplateGrammer.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(TemplateGrammer.DOT, i);
		}
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitConstant(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_constant);
		int _la;
		try {
			int _alt;
			setState(590);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTEGER:
				enterOuterAlt(_localctx, 1);
				{
				setState(569);
				match(INTEGER);
				}
				break;
			case FLOAT:
				enterOuterAlt(_localctx, 2);
				{
				setState(570);
				match(FLOAT);
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(571);
				match(STRING);
				}
				break;
			case True:
			case False:
				enterOuterAlt(_localctx, 4);
				{
				setState(572);
				((ConstantContext)_localctx).BOOLEAN = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==True || _la==False) ) {
					((ConstantContext)_localctx).BOOLEAN = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case HashConstant:
				enterOuterAlt(_localctx, 5);
				{
				setState(573);
				match(HashConstant);
				}
				break;
			case OpenParen:
				enterOuterAlt(_localctx, 6);
				{
				setState(574);
				match(OpenParen);
				setState(575);
				match(HashConstant);
				setState(576);
				match(Colon);
				setState(577);
				constant();
				setState(578);
				match(CloseParen);
				}
				break;
			case Language:
			case DomainType:
			case EntityType:
			case AttributeType:
			case RelationshipType:
			case EnumType:
			case InterfaceType:
			case OperationType:
			case TypedefType:
				enterOuterAlt(_localctx, 7);
				{
				setState(580);
				builtinType();
				setState(581);
				match(Colon);
				setState(582);
				match(IDENT);
				setState(587);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,44,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(583);
						match(DOT);
						setState(584);
						match(IDENT);
						}
						} 
					}
					setState(589);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,44,_ctx);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterTypeType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitTypeType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitTypeType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeTypeContext typeType() throws RecognitionException {
		TypeTypeContext _localctx = new TypeTypeContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_typeType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(592);
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
		public TerminalNode BOOLEAN_TYPE() { return getToken(TemplateGrammer.BOOLEAN_TYPE, 0); }
		public TerminalNode INT32_TYPE() { return getToken(TemplateGrammer.INT32_TYPE, 0); }
		public TerminalNode INT64_TYPE() { return getToken(TemplateGrammer.INT64_TYPE, 0); }
		public TerminalNode FLOAT_TYPE() { return getToken(TemplateGrammer.FLOAT_TYPE, 0); }
		public TerminalNode DOUBLE_TYPE() { return getToken(TemplateGrammer.DOUBLE_TYPE, 0); }
		public TerminalNode STRING_TYPE() { return getToken(TemplateGrammer.STRING_TYPE, 0); }
		public TerminalNode UUID_TYPE() { return getToken(TemplateGrammer.UUID_TYPE, 0); }
		public TerminalNode DATE_TYPE() { return getToken(TemplateGrammer.DATE_TYPE, 0); }
		public TerminalNode ASSET_TYPE() { return getToken(TemplateGrammer.ASSET_TYPE, 0); }
		public PrimitiveTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primitiveType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterPrimitiveType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitPrimitiveType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitPrimitiveType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimitiveTypeContext primitiveType() throws RecognitionException {
		PrimitiveTypeContext _localctx = new PrimitiveTypeContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_primitiveType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(594);
			_la = _input.LA(1);
			if ( !(((((_la - 76)) & ~0x3f) == 0 && ((1L << (_la - 76)) & ((1L << (BOOLEAN_TYPE - 76)) | (1L << (INT32_TYPE - 76)) | (1L << (INT64_TYPE - 76)) | (1L << (UUID_TYPE - 76)) | (1L << (FLOAT_TYPE - 76)) | (1L << (DOUBLE_TYPE - 76)) | (1L << (STRING_TYPE - 76)) | (1L << (DATE_TYPE - 76)) | (1L << (ASSET_TYPE - 76)))) != 0)) ) {
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

	public static class BuiltinTypeContext extends ParserRuleContext {
		public TerminalNode EntityType() { return getToken(TemplateGrammer.EntityType, 0); }
		public TerminalNode AttributeType() { return getToken(TemplateGrammer.AttributeType, 0); }
		public TerminalNode RelationshipType() { return getToken(TemplateGrammer.RelationshipType, 0); }
		public TerminalNode EnumType() { return getToken(TemplateGrammer.EnumType, 0); }
		public TerminalNode DomainType() { return getToken(TemplateGrammer.DomainType, 0); }
		public TerminalNode InterfaceType() { return getToken(TemplateGrammer.InterfaceType, 0); }
		public TerminalNode OperationType() { return getToken(TemplateGrammer.OperationType, 0); }
		public TerminalNode TypedefType() { return getToken(TemplateGrammer.TypedefType, 0); }
		public TerminalNode Language() { return getToken(TemplateGrammer.Language, 0); }
		public BuiltinTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_builtinType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterBuiltinType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitBuiltinType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitBuiltinType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BuiltinTypeContext builtinType() throws RecognitionException {
		BuiltinTypeContext _localctx = new BuiltinTypeContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_builtinType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(596);
			_la = _input.LA(1);
			if ( !(((((_la - 35)) & ~0x3f) == 0 && ((1L << (_la - 35)) & ((1L << (Language - 35)) | (1L << (DomainType - 35)) | (1L << (EntityType - 35)) | (1L << (AttributeType - 35)) | (1L << (RelationshipType - 35)) | (1L << (EnumType - 35)) | (1L << (InterfaceType - 35)) | (1L << (OperationType - 35)) | (1L << (TypedefType - 35)))) != 0)) ) {
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

	public static class InvocationConstantContext extends ParserRuleContext {
		public TerminalNode HashConstant() { return getToken(TemplateGrammer.HashConstant, 0); }
		public TerminalNode Colon() { return getToken(TemplateGrammer.Colon, 0); }
		public TerminalNode STRING() { return getToken(TemplateGrammer.STRING, 0); }
		public InvocationConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_invocationConstant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterInvocationConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitInvocationConstant(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitInvocationConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InvocationConstantContext invocationConstant() throws RecognitionException {
		InvocationConstantContext _localctx = new InvocationConstantContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_invocationConstant);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(598);
			match(HashConstant);
			setState(601);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Colon) {
				{
				setState(599);
				match(Colon);
				setState(600);
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

	public static class FilterContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode Colon() { return getToken(TemplateGrammer.Colon, 0); }
		public FilterParamExpressionContext filterParamExpression() {
			return getRuleContext(FilterParamExpressionContext.class,0);
		}
		public List<FilterOptionContext> filterOption() {
			return getRuleContexts(FilterOptionContext.class);
		}
		public FilterOptionContext filterOption(int i) {
			return getRuleContext(FilterOptionContext.class,i);
		}
		public FilterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterFilter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitFilter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitFilter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FilterContext filter() throws RecognitionException {
		FilterContext _localctx = new FilterContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_filter);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(603);
			identifier();
			setState(606);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
			case 1:
				{
				setState(604);
				match(Colon);
				setState(605);
				filterParamExpression();
				}
				break;
			}
			setState(611);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,48,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(608);
					filterOption();
					}
					} 
				}
				setState(613);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,48,_ctx);
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

	public static class FilterOptionContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public TerminalNode Colon() { return getToken(TemplateGrammer.Colon, 0); }
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public FilterOptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filterOption; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterFilterOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitFilterOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitFilterOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FilterOptionContext filterOption() throws RecognitionException {
		FilterOptionContext _localctx = new FilterOptionContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_filterOption);
		try {
			setState(621);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,50,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(614);
				identifier();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(615);
				identifier();
				setState(616);
				match(Colon);
				setState(619);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,49,_ctx) ) {
				case 1:
					{
					setState(617);
					identifier();
					}
					break;
				case 2:
					{
					setState(618);
					constant();
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

	public static class NamespaceIdentContext extends ParserRuleContext {
		public List<TerminalNode> IDENT() { return getTokens(TemplateGrammer.IDENT); }
		public TerminalNode IDENT(int i) {
			return getToken(TemplateGrammer.IDENT, i);
		}
		public List<TerminalNode> DOT() { return getTokens(TemplateGrammer.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(TemplateGrammer.DOT, i);
		}
		public NamespaceIdentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namespaceIdent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterNamespaceIdent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitNamespaceIdent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitNamespaceIdent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NamespaceIdentContext namespaceIdent() throws RecognitionException {
		NamespaceIdentContext _localctx = new NamespaceIdentContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_namespaceIdent);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(623);
			match(IDENT);
			setState(628);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(624);
				match(DOT);
				setState(625);
				match(IDENT);
				}
				}
				setState(630);
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

	public static class NamespaceIdentListContext extends ParserRuleContext {
		public List<NamespaceIdentContext> namespaceIdent() {
			return getRuleContexts(NamespaceIdentContext.class);
		}
		public NamespaceIdentContext namespaceIdent(int i) {
			return getRuleContext(NamespaceIdentContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(TemplateGrammer.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(TemplateGrammer.COMMA, i);
		}
		public NamespaceIdentListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namespaceIdentList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterNamespaceIdentList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitNamespaceIdentList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitNamespaceIdentList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NamespaceIdentListContext namespaceIdentList() throws RecognitionException {
		NamespaceIdentListContext _localctx = new NamespaceIdentListContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_namespaceIdentList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(631);
			namespaceIdent();
			setState(636);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(632);
				match(COMMA);
				setState(633);
				namespaceIdent();
				}
				}
				setState(638);
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

	public static class PublisherTagContext extends ParserRuleContext {
		public TerminalNode Publisher() { return getToken(TemplateGrammer.Publisher, 0); }
		public NamespaceIdentContext namespaceIdent() {
			return getRuleContext(NamespaceIdentContext.class,0);
		}
		public List<NodeDescriptionContext> nodeDescription() {
			return getRuleContexts(NodeDescriptionContext.class);
		}
		public NodeDescriptionContext nodeDescription(int i) {
			return getRuleContext(NodeDescriptionContext.class,i);
		}
		public PublisherTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_publisherTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterPublisherTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitPublisherTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitPublisherTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PublisherTagContext publisherTag() throws RecognitionException {
		PublisherTagContext _localctx = new PublisherTagContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_publisherTag);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(639);
			match(Publisher);
			setState(640);
			namespaceIdent();
			setState(644);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Description) {
				{
				{
				setState(641);
				nodeDescription();
				}
				}
				setState(646);
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

	public static class AuthorOptionContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public TerminalNode EQUALS() { return getToken(TemplateGrammer.EQUALS, 0); }
		public AuthorOptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_authorOption; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterAuthorOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitAuthorOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitAuthorOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AuthorOptionContext authorOption() throws RecognitionException {
		AuthorOptionContext _localctx = new AuthorOptionContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_authorOption);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(647);
			identifier();
			setState(648);
			match(EQUALS);
			setState(649);
			identifier();
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

	public static class AuthorTagContext extends ParserRuleContext {
		public TerminalNode Author() { return getToken(TemplateGrammer.Author, 0); }
		public TerminalNode To() { return getToken(TemplateGrammer.To, 0); }
		public NamespaceIdentListContext namespaceIdentList() {
			return getRuleContext(NamespaceIdentListContext.class,0);
		}
		public TerminalNode Outlet() { return getToken(TemplateGrammer.Outlet, 0); }
		public TerminalNode IDENT() { return getToken(TemplateGrammer.IDENT, 0); }
		public List<AuthorOptionContext> authorOption() {
			return getRuleContexts(AuthorOptionContext.class);
		}
		public AuthorOptionContext authorOption(int i) {
			return getRuleContext(AuthorOptionContext.class,i);
		}
		public List<NodeDescriptionContext> nodeDescription() {
			return getRuleContexts(NodeDescriptionContext.class);
		}
		public NodeDescriptionContext nodeDescription(int i) {
			return getRuleContext(NodeDescriptionContext.class,i);
		}
		public AuthorTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_authorTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterAuthorTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitAuthorTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitAuthorTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AuthorTagContext authorTag() throws RecognitionException {
		AuthorTagContext _localctx = new AuthorTagContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_authorTag);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(651);
			match(Author);
			setState(652);
			match(To);
			setState(654);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENT) {
				{
				setState(653);
				namespaceIdentList();
				}
			}

			setState(670);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Outlet) {
				{
				setState(656);
				match(Outlet);
				setState(657);
				match(IDENT);
				setState(661);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(658);
						authorOption();
						}
						} 
					}
					setState(663);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
				}
				setState(667);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Description) {
					{
					{
					setState(664);
					nodeDescription();
					}
					}
					setState(669);
					_errHandler.sync(this);
					_la = _input.LA(1);
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

	public static class OutletTagContext extends ParserRuleContext {
		public TerminalNode Outlet() { return getToken(TemplateGrammer.Outlet, 0); }
		public TerminalNode IDENT() { return getToken(TemplateGrammer.IDENT, 0); }
		public List<NodeDescriptionContext> nodeDescription() {
			return getRuleContexts(NodeDescriptionContext.class);
		}
		public NodeDescriptionContext nodeDescription(int i) {
			return getRuleContext(NodeDescriptionContext.class,i);
		}
		public OutletTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_outletTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).enterOutletTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TemplateGrammerListener ) ((TemplateGrammerListener)listener).exitOutletTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TemplateGrammerVisitor ) return ((TemplateGrammerVisitor<? extends T>)visitor).visitOutletTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OutletTagContext outletTag() throws RecognitionException {
		OutletTagContext _localctx = new OutletTagContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_outletTag);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(672);
			match(Outlet);
			setState(673);
			match(IDENT);
			setState(677);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Description) {
				{
				{
				setState(674);
				nodeDescription();
				}
				}
				setState(679);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 45:
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
			return precpred(_ctx, 4);
		case 5:
			return precpred(_ctx, 3);
		case 6:
			return precpred(_ctx, 2);
		case 7:
			return precpred(_ctx, 16);
		case 8:
			return precpred(_ctx, 14);
		case 9:
			return precpred(_ctx, 6);
		case 10:
			return precpred(_ctx, 5);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3r\u02ab\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\3\2\7\2\u0082\n\2\f\2\16\2\u0085\13\2\3\2\3\2\3\3\3"+
		"\3\3\3\3\3\3\3\5\3\u008e\n\3\3\4\6\4\u0091\n\4\r\4\16\4\u0092\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u00b7\n"+
		"\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u00d8\n\6"+
		"\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\7\t\u00e6\n\t\f\t\16"+
		"\t\u00e9\13\t\5\t\u00eb\n\t\3\t\3\t\3\n\3\n\3\n\3\n\7\n\u00f3\n\n\f\n"+
		"\16\n\u00f6\13\n\5\n\u00f8\n\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\r"+
		"\3\r\3\r\3\16\3\16\3\16\5\16\u0108\n\16\3\16\3\16\5\16\u010c\n\16\3\17"+
		"\3\17\5\17\u0110\n\17\3\17\3\17\3\17\3\20\3\20\5\20\u0117\n\20\3\20\3"+
		"\20\3\20\3\20\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\7"+
		"\23\u0128\n\23\f\23\16\23\u012b\13\23\3\23\3\23\5\23\u012f\n\23\3\23\3"+
		"\23\3\23\3\23\3\23\3\23\5\23\u0137\n\23\3\24\3\24\3\24\7\24\u013c\n\24"+
		"\f\24\16\24\u013f\13\24\3\25\3\25\7\25\u0143\n\25\f\25\16\25\u0146\13"+
		"\25\3\26\3\26\5\26\u014a\n\26\3\26\3\26\3\26\5\26\u014f\n\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\5\26\u0157\n\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30"+
		"\7\30\u0160\n\30\f\30\16\30\u0163\13\30\3\31\3\31\3\31\7\31\u0168\n\31"+
		"\f\31\16\31\u016b\13\31\3\32\3\32\3\33\3\33\3\33\3\33\5\33\u0173\n\33"+
		"\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\5\33\u017d\n\33\5\33\u017f\n"+
		"\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\5\33\u0188\n\33\3\34\3\34\3\35"+
		"\3\35\3\36\3\36\3\37\3\37\3\37\3 \3 \3 \3!\3!\3\"\3\"\3\"\3#\3#\3#\3#"+
		"\5#\u019f\n#\3$\3$\3$\3$\7$\u01a5\n$\f$\16$\u01a8\13$\3%\3%\3&\3&\3&\3"+
		"\'\3\'\5\'\u01b1\n\'\3\'\3\'\7\'\u01b5\n\'\f\'\16\'\u01b8\13\'\3(\3(\3"+
		"(\3)\3)\3)\3)\3)\3)\7)\u01c3\n)\f)\16)\u01c6\13)\5)\u01c8\n)\3*\3*\5*"+
		"\u01cc\n*\3+\3+\3+\3+\3+\3,\3,\3,\3-\3-\3-\5-\u01d9\n-\3-\3-\3.\3.\3."+
		"\5.\u01e0\n.\3.\3.\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\5/\u01f1\n/"+
		"\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/"+
		"\3/\3/\3/\3/\3/\5/\u020f\n/\3/\3/\3/\3/\3/\3/\3/\3/\3/\7/\u021a\n/\f/"+
		"\16/\u021d\13/\3\60\3\60\3\60\7\60\u0222\n\60\f\60\16\60\u0225\13\60\3"+
		"\61\3\61\5\61\u0229\n\61\3\61\3\61\3\62\3\62\3\62\3\62\3\62\5\62\u0232"+
		"\n\62\3\63\3\63\3\63\3\63\3\63\3\63\5\63\u023a\n\63\3\64\3\64\3\64\3\64"+
		"\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\7\64\u024c"+
		"\n\64\f\64\16\64\u024f\13\64\5\64\u0251\n\64\3\65\3\65\3\66\3\66\3\67"+
		"\3\67\38\38\38\58\u025c\n8\39\39\39\59\u0261\n9\39\79\u0264\n9\f9\169"+
		"\u0267\139\3:\3:\3:\3:\3:\5:\u026e\n:\5:\u0270\n:\3;\3;\3;\7;\u0275\n"+
		";\f;\16;\u0278\13;\3<\3<\3<\7<\u027d\n<\f<\16<\u0280\13<\3=\3=\3=\7=\u0285"+
		"\n=\f=\16=\u0288\13=\3>\3>\3>\3>\3?\3?\3?\5?\u0291\n?\3?\3?\3?\7?\u0296"+
		"\n?\f?\16?\u0299\13?\3?\7?\u029c\n?\f?\16?\u029f\13?\5?\u02a1\n?\3@\3"+
		"@\3@\7@\u02a6\n@\f@\16@\u02a9\13@\3@\2\3\\A\2\4\6\b\n\f\16\20\22\24\26"+
		"\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|"+
		"~\2\n\n\2##++//\678==AAFGIK\3\2\21\22\3\2\23\25\3\2\26\31\3\2\32\33\3"+
		"\2\b\t\3\2NV\4\2%%W^\2\u02fc\2\u0083\3\2\2\2\4\u008d\3\2\2\2\6\u0090\3"+
		"\2\2\2\b\u00b6\3\2\2\2\n\u00d7\3\2\2\2\f\u00d9\3\2\2\2\16\u00dd\3\2\2"+
		"\2\20\u00e1\3\2\2\2\22\u00ee\3\2\2\2\24\u00fb\3\2\2\2\26\u00fe\3\2\2\2"+
		"\30\u0101\3\2\2\2\32\u0104\3\2\2\2\34\u010d\3\2\2\2\36\u0114\3\2\2\2 "+
		"\u011c\3\2\2\2\"\u011e\3\2\2\2$\u0124\3\2\2\2&\u0138\3\2\2\2(\u0140\3"+
		"\2\2\2*\u0147\3\2\2\2,\u0158\3\2\2\2.\u015c\3\2\2\2\60\u0164\3\2\2\2\62"+
		"\u016c\3\2\2\2\64\u0187\3\2\2\2\66\u0189\3\2\2\28\u018b\3\2\2\2:\u018d"+
		"\3\2\2\2<\u018f\3\2\2\2>\u0192\3\2\2\2@\u0195\3\2\2\2B\u0197\3\2\2\2D"+
		"\u019e\3\2\2\2F\u01a0\3\2\2\2H\u01a9\3\2\2\2J\u01ab\3\2\2\2L\u01ae\3\2"+
		"\2\2N\u01b9\3\2\2\2P\u01bc\3\2\2\2R\u01c9\3\2\2\2T\u01cd\3\2\2\2V\u01d2"+
		"\3\2\2\2X\u01d5\3\2\2\2Z\u01dc\3\2\2\2\\\u01f0\3\2\2\2^\u021e\3\2\2\2"+
		"`\u0226\3\2\2\2b\u0231\3\2\2\2d\u0239\3\2\2\2f\u0250\3\2\2\2h\u0252\3"+
		"\2\2\2j\u0254\3\2\2\2l\u0256\3\2\2\2n\u0258\3\2\2\2p\u025d\3\2\2\2r\u026f"+
		"\3\2\2\2t\u0271\3\2\2\2v\u0279\3\2\2\2x\u0281\3\2\2\2z\u0289\3\2\2\2|"+
		"\u028d\3\2\2\2~\u02a2\3\2\2\2\u0080\u0082\5\4\3\2\u0081\u0080\3\2\2\2"+
		"\u0082\u0085\3\2\2\2\u0083\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0086"+
		"\3\2\2\2\u0085\u0083\3\2\2\2\u0086\u0087\7\2\2\3\u0087\3\3\2\2\2\u0088"+
		"\u008e\5\f\7\2\u0089\u008e\5\16\b\2\u008a\u008e\5X-\2\u008b\u008e\7\3"+
		"\2\2\u008c\u008e\5\6\4\2\u008d\u0088\3\2\2\2\u008d\u0089\3\2\2\2\u008d"+
		"\u008a\3\2\2\2\u008d\u008b\3\2\2\2\u008d\u008c\3\2\2\2\u008e\5\3\2\2\2"+
		"\u008f\u0091\7\7\2\2\u0090\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0090"+
		"\3\2\2\2\u0092\u0093\3\2\2\2\u0093\7\3\2\2\2\u0094\u00b7\7g\2\2\u0095"+
		"\u00b7\5l\67\2\u0096\u00b7\7%\2\2\u0097\u00b7\7)\2\2\u0098\u00b7\7#\2"+
		"\2\u0099\u00b7\7\'\2\2\u009a\u00b7\7(\2\2\u009b\u00b7\7@\2\2\u009c\u00b7"+
		"\7+\2\2\u009d\u00b7\7,\2\2\u009e\u00b7\7-\2\2\u009f\u00b7\7/\2\2\u00a0"+
		"\u00b7\7\64\2\2\u00a1\u00b7\7\65\2\2\u00a2\u00b7\7\66\2\2\u00a3\u00b7"+
		"\7\67\2\2\u00a4\u00b7\7;\2\2\u00a5\u00b7\7<\2\2\u00a6\u00b7\7&\2\2\u00a7"+
		"\u00b7\7=\2\2\u00a8\u00b7\7A\2\2\u00a9\u00b7\7>\2\2\u00aa\u00b7\7?\2\2"+
		"\u00ab\u00b7\78\2\2\u00ac\u00b7\79\2\2\u00ad\u00b7\7:\2\2\u00ae\u00b7"+
		"\7F\2\2\u00af\u00b7\7G\2\2\u00b0\u00b7\7M\2\2\u00b1\u00b7\7I\2\2\u00b2"+
		"\u00b7\7J\2\2\u00b3\u00b7\7K\2\2\u00b4\u00b7\7L\2\2\u00b5\u00b7\7*\2\2"+
		"\u00b6\u0094\3\2\2\2\u00b6\u0095\3\2\2\2\u00b6\u0096\3\2\2\2\u00b6\u0097"+
		"\3\2\2\2\u00b6\u0098\3\2\2\2\u00b6\u0099\3\2\2\2\u00b6\u009a\3\2\2\2\u00b6"+
		"\u009b\3\2\2\2\u00b6\u009c\3\2\2\2\u00b6\u009d\3\2\2\2\u00b6\u009e\3\2"+
		"\2\2\u00b6\u009f\3\2\2\2\u00b6\u00a0\3\2\2\2\u00b6\u00a1\3\2\2\2\u00b6"+
		"\u00a2\3\2\2\2\u00b6\u00a3\3\2\2\2\u00b6\u00a4\3\2\2\2\u00b6\u00a5\3\2"+
		"\2\2\u00b6\u00a6\3\2\2\2\u00b6\u00a7\3\2\2\2\u00b6\u00a8\3\2\2\2\u00b6"+
		"\u00a9\3\2\2\2\u00b6\u00aa\3\2\2\2\u00b6\u00ab\3\2\2\2\u00b6\u00ac\3\2"+
		"\2\2\u00b6\u00ad\3\2\2\2\u00b6\u00ae\3\2\2\2\u00b6\u00af\3\2\2\2\u00b6"+
		"\u00b0\3\2\2\2\u00b6\u00b1\3\2\2\2\u00b6\u00b2\3\2\2\2\u00b6\u00b3\3\2"+
		"\2\2\u00b6\u00b4\3\2\2\2\u00b6\u00b5\3\2\2\2\u00b7\t\3\2\2\2\u00b8\u00d8"+
		"\5\24\13\2\u00b9\u00d8\5\26\f\2\u00ba\u00d8\5\32\16\2\u00bb\u00d8\5\36"+
		"\20\2\u00bc\u00d8\5\34\17\2\u00bd\u00d8\5\"\22\2\u00be\u00d8\5$\23\2\u00bf"+
		"\u00d8\5\62\32\2\u00c0\u00d8\5*\26\2\u00c1\u00d8\5\64\33\2\u00c2\u00d8"+
		"\5:\36\2\u00c3\u00d8\5\66\34\2\u00c4\u00d8\58\35\2\u00c5\u00d8\5<\37\2"+
		"\u00c6\u00d8\5> \2\u00c7\u00d8\5@!\2\u00c8\u00d8\5\30\r\2\u00c9\u00d8"+
		"\5J&\2\u00ca\u00d8\5R*\2\u00cb\u00d8\5T+\2\u00cc\u00d8\5V,\2\u00cd\u00d8"+
		"\5B\"\2\u00ce\u00d8\5F$\2\u00cf\u00d8\5H%\2\u00d0\u00d8\5L\'\2\u00d1\u00d8"+
		"\5N(\2\u00d2\u00d8\5P)\2\u00d3\u00d8\5\20\t\2\u00d4\u00d8\5x=\2\u00d5"+
		"\u00d8\5~@\2\u00d6\u00d8\5|?\2\u00d7\u00b8\3\2\2\2\u00d7\u00b9\3\2\2\2"+
		"\u00d7\u00ba\3\2\2\2\u00d7\u00bb\3\2\2\2\u00d7\u00bc\3\2\2\2\u00d7\u00bd"+
		"\3\2\2\2\u00d7\u00be\3\2\2\2\u00d7\u00bf\3\2\2\2\u00d7\u00c0\3\2\2\2\u00d7"+
		"\u00c1\3\2\2\2\u00d7\u00c2\3\2\2\2\u00d7\u00c3\3\2\2\2\u00d7\u00c4\3\2"+
		"\2\2\u00d7\u00c5\3\2\2\2\u00d7\u00c6\3\2\2\2\u00d7\u00c7\3\2\2\2\u00d7"+
		"\u00c8\3\2\2\2\u00d7\u00c9\3\2\2\2\u00d7\u00ca\3\2\2\2\u00d7\u00cb\3\2"+
		"\2\2\u00d7\u00cc\3\2\2\2\u00d7\u00cd\3\2\2\2\u00d7\u00ce\3\2\2\2\u00d7"+
		"\u00cf\3\2\2\2\u00d7\u00d0\3\2\2\2\u00d7\u00d1\3\2\2\2\u00d7\u00d2\3\2"+
		"\2\2\u00d7\u00d3\3\2\2\2\u00d7\u00d4\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d7"+
		"\u00d6\3\2\2\2\u00d8\13\3\2\2\2\u00d9\u00da\7\4\2\2\u00da\u00db\5\n\6"+
		"\2\u00db\u00dc\7l\2\2\u00dc\r\3\2\2\2\u00dd\u00de\7\5\2\2\u00de\u00df"+
		"\t\2\2\2\u00df\u00e0\7l\2\2\u00e0\17\3\2\2\2\u00e1\u00ea\7M\2\2\u00e2"+
		"\u00e7\5\b\5\2\u00e3\u00e4\7\n\2\2\u00e4\u00e6\5\b\5\2\u00e5\u00e3\3\2"+
		"\2\2\u00e6\u00e9\3\2\2\2\u00e7\u00e5\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8"+
		"\u00eb\3\2\2\2\u00e9\u00e7\3\2\2\2\u00ea\u00e2\3\2\2\2\u00ea\u00eb\3\2"+
		"\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00ed\7h\2\2\u00ed\21\3\2\2\2\u00ee\u00f7"+
		"\7M\2\2\u00ef\u00f4\5\b\5\2\u00f0\u00f1\7\n\2\2\u00f1\u00f3\5\b\5\2\u00f2"+
		"\u00f0\3\2\2\2\u00f3\u00f6\3\2\2\2\u00f4\u00f2\3\2\2\2\u00f4\u00f5\3\2"+
		"\2\2\u00f5\u00f8\3\2\2\2\u00f6\u00f4\3\2\2\2\u00f7\u00ef\3\2\2\2\u00f7"+
		"\u00f8\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9\u00fa\7h\2\2\u00fa\23\3\2\2\2"+
		"\u00fb\u00fc\7%\2\2\u00fc\u00fd\5\b\5\2\u00fd\25\3\2\2\2\u00fe\u00ff\7"+
		"W\2\2\u00ff\u0100\5\b\5\2\u0100\27\3\2\2\2\u0101\u0102\7&\2\2\u0102\u0103"+
		"\7e\2\2\u0103\31\3\2\2\2\u0104\u0107\7)\2\2\u0105\u0108\5\b\5\2\u0106"+
		"\u0108\7h\2\2\u0107\u0105\3\2\2\2\u0107\u0106\3\2\2\2\u0108\u010b\3\2"+
		"\2\2\u0109\u010a\7*\2\2\u010a\u010c\5\b\5\2\u010b\u0109\3\2\2\2\u010b"+
		"\u010c\3\2\2\2\u010c\33\3\2\2\2\u010d\u010f\7\'\2\2\u010e\u0110\7(\2\2"+
		"\u010f\u010e\3\2\2\2\u010f\u0110\3\2\2\2\u0110\u0111\3\2\2\2\u0111\u0112"+
		"\5 \21\2\u0112\u0113\5 \21\2\u0113\35\3\2\2\2\u0114\u0116\7#\2\2\u0115"+
		"\u0117\7$\2\2\u0116\u0115\3\2\2\2\u0116\u0117\3\2\2\2\u0117\u0118\3\2"+
		"\2\2\u0118\u0119\5 \21\2\u0119\u011a\5 \21\2\u011a\u011b\5 \21\2\u011b"+
		"\37\3\2\2\2\u011c\u011d\5\\/\2\u011d!\3\2\2\2\u011e\u011f\7@\2\2\u011f"+
		"\u0120\5\b\5\2\u0120\u0121\5 \21\2\u0121\u0122\5 \21\2\u0122\u0123\5 "+
		"\21\2\u0123#\3\2\2\2\u0124\u0125\7+\2\2\u0125\u0129\5\b\5\2\u0126\u0128"+
		"\5\22\n\2\u0127\u0126\3\2\2\2\u0128\u012b\3\2\2\2\u0129\u0127\3\2\2\2"+
		"\u0129\u012a\3\2\2\2\u012a\u012c\3\2\2\2\u012b\u0129\3\2\2\2\u012c\u012e"+
		"\7\34\2\2\u012d\u012f\5&\24\2\u012e\u012d\3\2\2\2\u012e\u012f\3\2\2\2"+
		"\u012f\u0130\3\2\2\2\u0130\u0136\7\35\2\2\u0131\u0132\7\37\2\2\u0132\u0133"+
		"\7\34\2\2\u0133\u0134\5&\24\2\u0134\u0135\7\35\2\2\u0135\u0137\3\2\2\2"+
		"\u0136\u0131\3\2\2\2\u0136\u0137\3\2\2\2\u0137%\3\2\2\2\u0138\u013d\5"+
		"(\25\2\u0139\u013a\7\n\2\2\u013a\u013c\5(\25\2\u013b\u0139\3\2\2\2\u013c"+
		"\u013f\3\2\2\2\u013d\u013b\3\2\2\2\u013d\u013e\3\2\2\2\u013e\'\3\2\2\2"+
		"\u013f\u013d\3\2\2\2\u0140\u0144\5\b\5\2\u0141\u0143\5\22\n\2\u0142\u0141"+
		"\3\2\2\2\u0143\u0146\3\2\2\2\u0144\u0142\3\2\2\2\u0144\u0145\3\2\2\2\u0145"+
		")\3\2\2\2\u0146\u0144\3\2\2\2\u0147\u0149\7-\2\2\u0148\u014a\7.\2\2\u0149"+
		"\u0148\3\2\2\2\u0149\u014a\3\2\2\2\u014a\u014b\3\2\2\2\u014b\u014c\5\b"+
		"\5\2\u014c\u014e\7\34\2\2\u014d\u014f\5.\30\2\u014e\u014d\3\2\2\2\u014e"+
		"\u014f\3\2\2\2\u014f\u0150\3\2\2\2\u0150\u0156\7\35\2\2\u0151\u0152\7"+
		"\37\2\2\u0152\u0153\7\34\2\2\u0153\u0154\5\60\31\2\u0154\u0155\7\35\2"+
		"\2\u0155\u0157\3\2\2\2\u0156\u0151\3\2\2\2\u0156\u0157\3\2\2\2\u0157+"+
		"\3\2\2\2\u0158\u0159\5\b\5\2\u0159\u015a\7\f\2\2\u015a\u015b\5\\/\2\u015b"+
		"-\3\2\2\2\u015c\u0161\5,\27\2\u015d\u015e\7\n\2\2\u015e\u0160\5,\27\2"+
		"\u015f\u015d\3\2\2\2\u0160\u0163\3\2\2\2\u0161\u015f\3\2\2\2\u0161\u0162"+
		"\3\2\2\2\u0162/\3\2\2\2\u0163\u0161\3\2\2\2\u0164\u0169\5,\27\2\u0165"+
		"\u0166\7\n\2\2\u0166\u0168\5,\27\2\u0167\u0165\3\2\2\2\u0168\u016b\3\2"+
		"\2\2\u0169\u0167\3\2\2\2\u0169\u016a\3\2\2\2\u016a\61\3\2\2\2\u016b\u0169"+
		"\3\2\2\2\u016c\u016d\7,\2\2\u016d\63\3\2\2\2\u016e\u016f\7/\2\2\u016f"+
		"\u0172\5\\/\2\u0170\u0171\7\62\2\2\u0171\u0173\5\b\5\2\u0172\u0170\3\2"+
		"\2\2\u0172\u0173\3\2\2\2\u0173\u0188\3\2\2\2\u0174\u0175\7/\2\2\u0175"+
		"\u0176\5\b\5\2\u0176\u0177\7\61\2\2\u0177\u017e\5\\/\2\u0178\u0179\7\60"+
		"\2\2\u0179\u017c\5\\/\2\u017a\u017b\7\63\2\2\u017b\u017d\5\\/\2\u017c"+
		"\u017a\3\2\2\2\u017c\u017d\3\2\2\2\u017d\u017f\3\2\2\2\u017e\u0178\3\2"+
		"\2\2\u017e\u017f\3\2\2\2\u017f\u0188\3\2\2\2\u0180\u0181\7/\2\2\u0181"+
		"\u0182\5\b\5\2\u0182\u0183\7\61\2\2\u0183\u0184\5\\/\2\u0184\u0185\7\67"+
		"\2\2\u0185\u0186\5\\/\2\u0186\u0188\3\2\2\2\u0187\u016e\3\2\2\2\u0187"+
		"\u0174\3\2\2\2\u0187\u0180\3\2\2\2\u0188\65\3\2\2\2\u0189\u018a\7\65\2"+
		"\2\u018a\67\3\2\2\2\u018b\u018c\7\66\2\2\u018c9\3\2\2\2\u018d\u018e\7"+
		"\64\2\2\u018e;\3\2\2\2\u018f\u0190\7\67\2\2\u0190\u0191\5\\/\2\u0191="+
		"\3\2\2\2\u0192\u0193\7;\2\2\u0193\u0194\5\\/\2\u0194?\3\2\2\2\u0195\u0196"+
		"\7<\2\2\u0196A\3\2\2\2\u0197\u0198\78\2\2\u0198\u0199\5\\/\2\u0199C\3"+
		"\2\2\2\u019a\u019f\7b\2\2\u019b\u019f\7h\2\2\u019c\u019f\5\b\5\2\u019d"+
		"\u019f\5j\66\2\u019e\u019a\3\2\2\2\u019e\u019b\3\2\2\2\u019e\u019c\3\2"+
		"\2\2\u019e\u019d\3\2\2\2\u019fE\3\2\2\2\u01a0\u01a1\79\2\2\u01a1\u01a6"+
		"\5D#\2\u01a2\u01a3\7\n\2\2\u01a3\u01a5\5D#\2\u01a4\u01a2\3\2\2\2\u01a5"+
		"\u01a8\3\2\2\2\u01a6\u01a4\3\2\2\2\u01a6\u01a7\3\2\2\2\u01a7G\3\2\2\2"+
		"\u01a8\u01a6\3\2\2\2\u01a9\u01aa\7:\2\2\u01aaI\3\2\2\2\u01ab\u01ac\7="+
		"\2\2\u01ac\u01ad\5\b\5\2\u01adK\3\2\2\2\u01ae\u01b0\7D\2\2\u01af\u01b1"+
		"\7E\2\2\u01b0\u01af\3\2\2\2\u01b0\u01b1\3\2\2\2\u01b1\u01b2\3\2\2\2\u01b2"+
		"\u01b6\5\b\5\2\u01b3\u01b5\5\22\n\2\u01b4\u01b3\3\2\2\2\u01b5\u01b8\3"+
		"\2\2\2\u01b6\u01b4\3\2\2\2\u01b6\u01b7\3\2\2\2\u01b7M\3\2\2\2\u01b8\u01b6"+
		"\3\2\2\2\u01b9\u01ba\7F\2\2\u01ba\u01bb\5\b\5\2\u01bbO\3\2\2\2\u01bc\u01bd"+
		"\7G\2\2\u01bd\u01c7\5\b\5\2\u01be\u01bf\7H\2\2\u01bf\u01c4\5\b\5\2\u01c0"+
		"\u01c1\7\n\2\2\u01c1\u01c3\5\b\5\2\u01c2\u01c0\3\2\2\2\u01c3\u01c6\3\2"+
		"\2\2\u01c4\u01c2\3\2\2\2\u01c4\u01c5\3\2\2\2\u01c5\u01c8\3\2\2\2\u01c6"+
		"\u01c4\3\2\2\2\u01c7\u01be\3\2\2\2\u01c7\u01c8\3\2\2\2\u01c8Q\3\2\2\2"+
		"\u01c9\u01cb\7A\2\2\u01ca\u01cc\5\b\5\2\u01cb\u01ca\3\2\2\2\u01cb\u01cc"+
		"\3\2\2\2\u01ccS\3\2\2\2\u01cd\u01ce\7>\2\2\u01ce\u01cf\5\b\5\2\u01cf\u01d0"+
		"\7j\2\2\u01d0\u01d1\5\\/\2\u01d1U\3\2\2\2\u01d2\u01d3\7?\2\2\u01d3\u01d4"+
		"\5\\/\2\u01d4W\3\2\2\2\u01d5\u01d8\7\6\2\2\u01d6\u01d9\7\"\2\2\u01d7\u01d9"+
		"\5\\/\2\u01d8\u01d6\3\2\2\2\u01d8\u01d7\3\2\2\2\u01d9\u01da\3\2\2\2\u01da"+
		"\u01db\7o\2\2\u01dbY\3\2\2\2\u01dc\u01dd\5\b\5\2\u01dd\u01df\7\34\2\2"+
		"\u01de\u01e0\5^\60\2\u01df\u01de\3\2\2\2\u01df\u01e0\3\2\2\2\u01e0\u01e1"+
		"\3\2\2\2\u01e1\u01e2\7\35\2\2\u01e2[\3\2\2\2\u01e3\u01e4\b/\1\2\u01e4"+
		"\u01f1\5d\63\2\u01e5\u01f1\5Z.\2\u01e6\u01e7\7\34\2\2\u01e7\u01e8\5h\65"+
		"\2\u01e8\u01e9\7\35\2\2\u01e9\u01ea\5\\/\17\u01ea\u01f1\3\2\2\2\u01eb"+
		"\u01ec\t\3\2\2\u01ec\u01f1\5\\/\16\u01ed\u01ee\7\16\2\2\u01ee\u01f1\5"+
		"\\/\r\u01ef\u01f1\5`\61\2\u01f0\u01e3\3\2\2\2\u01f0\u01e5\3\2\2\2\u01f0"+
		"\u01e6\3\2\2\2\u01f0\u01eb\3\2\2\2\u01f0\u01ed\3\2\2\2\u01f0\u01ef\3\2"+
		"\2\2\u01f1\u021b\3\2\2\2\u01f2\u01f3\f\f\2\2\u01f3\u01f4\t\4\2\2\u01f4"+
		"\u021a\5\\/\r\u01f5\u01f6\f\13\2\2\u01f6\u01f7\t\3\2\2\u01f7\u021a\5\\"+
		"/\f\u01f8\u01f9\f\n\2\2\u01f9\u01fa\t\5\2\2\u01fa\u021a\5\\/\13\u01fb"+
		"\u01fc\f\t\2\2\u01fc\u01fd\t\6\2\2\u01fd\u021a\5\\/\n\u01fe\u01ff\f\6"+
		"\2\2\u01ff\u0200\7\17\2\2\u0200\u021a\5\\/\7\u0201\u0202\f\5\2\2\u0202"+
		"\u0203\7\20\2\2\u0203\u021a\5\\/\6\u0204\u0205\f\4\2\2\u0205\u0206\7\13"+
		"\2\2\u0206\u0207\5\\/\2\u0207\u0208\7\f\2\2\u0208\u0209\5\\/\4\u0209\u021a"+
		"\3\2\2\2\u020a\u020b\f\22\2\2\u020b\u020e\7i\2\2\u020c\u020f\5\b\5\2\u020d"+
		"\u020f\5Z.\2\u020e\u020c\3\2\2\2\u020e\u020d\3\2\2\2\u020f\u021a\3\2\2"+
		"\2\u0210\u0211\f\20\2\2\u0211\u0212\7\36\2\2\u0212\u021a\5p9\2\u0213\u0214"+
		"\f\b\2\2\u0214\u0215\7C\2\2\u0215\u021a\5h\65\2\u0216\u0217\f\7\2\2\u0217"+
		"\u0218\7B\2\2\u0218\u021a\5h\65\2\u0219\u01f2\3\2\2\2\u0219\u01f5\3\2"+
		"\2\2\u0219\u01f8\3\2\2\2\u0219\u01fb\3\2\2\2\u0219\u01fe\3\2\2\2\u0219"+
		"\u0201\3\2\2\2\u0219\u0204\3\2\2\2\u0219\u020a\3\2\2\2\u0219\u0210\3\2"+
		"\2\2\u0219\u0213\3\2\2\2\u0219\u0216\3\2\2\2\u021a\u021d\3\2\2\2\u021b"+
		"\u0219\3\2\2\2\u021b\u021c\3\2\2\2\u021c]\3\2\2\2\u021d\u021b\3\2\2\2"+
		"\u021e\u0223\5\\/\2\u021f\u0220\7\n\2\2\u0220\u0222\5\\/\2\u0221\u021f"+
		"\3\2\2\2\u0222\u0225\3\2\2\2\u0223\u0221\3\2\2\2\u0223\u0224\3\2\2\2\u0224"+
		"_\3\2\2\2\u0225\u0223\3\2\2\2\u0226\u0228\7 \2\2\u0227\u0229\5^\60\2\u0228"+
		"\u0227\3\2\2\2\u0228\u0229\3\2\2\2\u0229\u022a\3\2\2\2\u022a\u022b\7!"+
		"\2\2\u022ba\3\2\2\2\u022c\u022d\7\34\2\2\u022d\u022e\5\\/\2\u022e\u022f"+
		"\7\35\2\2\u022f\u0232\3\2\2\2\u0230\u0232\5\b\5\2\u0231\u022c\3\2\2\2"+
		"\u0231\u0230\3\2\2\2\u0232c\3\2\2\2\u0233\u023a\5\b\5\2\u0234\u023a\5"+
		"f\64\2\u0235\u0236\7\34\2\2\u0236\u0237\5\\/\2\u0237\u0238\7\35\2\2\u0238"+
		"\u023a\3\2\2\2\u0239\u0233\3\2\2\2\u0239\u0234\3\2\2\2\u0239\u0235\3\2"+
		"\2\2\u023ae\3\2\2\2\u023b\u0251\7b\2\2\u023c\u0251\7f\2\2\u023d\u0251"+
		"\7h\2\2\u023e\u0251\t\7\2\2\u023f\u0251\7\r\2\2\u0240\u0241\7\34\2\2\u0241"+
		"\u0242\7\r\2\2\u0242\u0243\7\f\2\2\u0243\u0244\5f\64\2\u0244\u0245\7\35"+
		"\2\2\u0245\u0251\3\2\2\2\u0246\u0247\5l\67\2\u0247\u0248\7\f\2\2\u0248"+
		"\u024d\7g\2\2\u0249\u024a\7i\2\2\u024a\u024c\7g\2\2\u024b\u0249\3\2\2"+
		"\2\u024c\u024f\3\2\2\2\u024d\u024b\3\2\2\2\u024d\u024e\3\2\2\2\u024e\u0251"+
		"\3\2\2\2\u024f\u024d\3\2\2\2\u0250\u023b\3\2\2\2\u0250\u023c\3\2\2\2\u0250"+
		"\u023d\3\2\2\2\u0250\u023e\3\2\2\2\u0250\u023f\3\2\2\2\u0250\u0240\3\2"+
		"\2\2\u0250\u0246\3\2\2\2\u0251g\3\2\2\2\u0252\u0253\5j\66\2\u0253i\3\2"+
		"\2\2\u0254\u0255\t\b\2\2\u0255k\3\2\2\2\u0256\u0257\t\t\2\2\u0257m\3\2"+
		"\2\2\u0258\u025b\7\r\2\2\u0259\u025a\7\f\2\2\u025a\u025c\7h\2\2\u025b"+
		"\u0259\3\2\2\2\u025b\u025c\3\2\2\2\u025co\3\2\2\2\u025d\u0260\5\b\5\2"+
		"\u025e\u025f\7\f\2\2\u025f\u0261\5b\62\2\u0260\u025e\3\2\2\2\u0260\u0261"+
		"\3\2\2\2\u0261\u0265\3\2\2\2\u0262\u0264\5r:\2\u0263\u0262\3\2\2\2\u0264"+
		"\u0267\3\2\2\2\u0265\u0263\3\2\2\2\u0265\u0266\3\2\2\2\u0266q\3\2\2\2"+
		"\u0267\u0265\3\2\2\2\u0268\u0270\5\b\5\2\u0269\u026a\5\b\5\2\u026a\u026d"+
		"\7\f\2\2\u026b\u026e\5\b\5\2\u026c\u026e\5f\64\2\u026d\u026b\3\2\2\2\u026d"+
		"\u026c\3\2\2\2\u026e\u0270\3\2\2\2\u026f\u0268\3\2\2\2\u026f\u0269\3\2"+
		"\2\2\u0270s\3\2\2\2\u0271\u0276\7g\2\2\u0272\u0273\7i\2\2\u0273\u0275"+
		"\7g\2\2\u0274\u0272\3\2\2\2\u0275\u0278\3\2\2\2\u0276\u0274\3\2\2\2\u0276"+
		"\u0277\3\2\2\2\u0277u\3\2\2\2\u0278\u0276\3\2\2\2\u0279\u027e\5t;\2\u027a"+
		"\u027b\7\n\2\2\u027b\u027d\5t;\2\u027c\u027a\3\2\2\2\u027d\u0280\3\2\2"+
		"\2\u027e\u027c\3\2\2\2\u027e\u027f\3\2\2\2\u027fw\3\2\2\2\u0280\u027e"+
		"\3\2\2\2\u0281\u0282\7I\2\2\u0282\u0286\5t;\2\u0283\u0285\5\22\n\2\u0284"+
		"\u0283\3\2\2\2\u0285\u0288\3\2\2\2\u0286\u0284\3\2\2\2\u0286\u0287\3\2"+
		"\2\2\u0287y\3\2\2\2\u0288\u0286\3\2\2\2\u0289\u028a\5\b\5\2\u028a\u028b"+
		"\7j\2\2\u028b\u028c\5\b\5\2\u028c{\3\2\2\2\u028d\u028e\7K\2\2\u028e\u0290"+
		"\7L\2\2\u028f\u0291\5v<\2\u0290\u028f\3\2\2\2\u0290\u0291\3\2\2\2\u0291"+
		"\u02a0\3\2\2\2\u0292\u0293\7J\2\2\u0293\u0297\7g\2\2\u0294\u0296\5z>\2"+
		"\u0295\u0294\3\2\2\2\u0296\u0299\3\2\2\2\u0297\u0295\3\2\2\2\u0297\u0298"+
		"\3\2\2\2\u0298\u029d\3\2\2\2\u0299\u0297\3\2\2\2\u029a\u029c\5\22\n\2"+
		"\u029b\u029a\3\2\2\2\u029c\u029f\3\2\2\2\u029d\u029b\3\2\2\2\u029d\u029e"+
		"\3\2\2\2\u029e\u02a1\3\2\2\2\u029f\u029d\3\2\2\2\u02a0\u0292\3\2\2\2\u02a0"+
		"\u02a1\3\2\2\2\u02a1}\3\2\2\2\u02a2\u02a3\7J\2\2\u02a3\u02a7\7g\2\2\u02a4"+
		"\u02a6\5\22\n\2\u02a5\u02a4\3\2\2\2\u02a6\u02a9\3\2\2\2\u02a7\u02a5\3"+
		"\2\2\2\u02a7\u02a8\3\2\2\2\u02a8\177\3\2\2\2\u02a9\u02a7\3\2\2\2=\u0083"+
		"\u008d\u0092\u00b6\u00d7\u00e7\u00ea\u00f4\u00f7\u0107\u010b\u010f\u0116"+
		"\u0129\u012e\u0136\u013d\u0144\u0149\u014e\u0156\u0161\u0169\u0172\u017c"+
		"\u017e\u0187\u019e\u01a6\u01b0\u01b6\u01c4\u01c7\u01cb\u01d8\u01df\u01f0"+
		"\u020e\u0219\u021b\u0223\u0228\u0231\u0239\u024d\u0250\u025b\u0260\u0265"+
		"\u026d\u026f\u0276\u027e\u0286\u0290\u0297\u029d\u02a0\u02a7";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}