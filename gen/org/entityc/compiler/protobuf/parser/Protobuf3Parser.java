// Generated from /Users/bob/Development/Entity-Compiler/src/java/org/entityc/compiler/protobuf/Protobuf3.g4 by ANTLR 4.9
package org.entityc.compiler.protobuf.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class Protobuf3Parser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, BOOL=3, BYTES=4, DOUBLE=5, ENUM=6, FIXED32=7, FIXED64=8, 
		FLOAT=9, IMPORT=10, INT32=11, INT64=12, MAP=13, MESSAGE=14, ONEOF=15, 
		OPTION=16, PACKAGE=17, PROTO3_DOUBLE=18, PROTO3_SINGLE=19, PUBLIC=20, 
		REPEATED=21, OPTIONAL=22, REQUIRED=23, RESERVED=24, RETURNS=25, RPC=26, 
		SERVICE=27, SFIXED32=28, SFIXED64=29, SINT32=30, SINT64=31, STREAM=32, 
		STRING=33, SYNTAX=34, TO=35, UINT32=36, UINT64=37, WEAK=38, Ident=39, 
		IntLit=40, FloatLit=41, BoolLit=42, StrLit=43, Quote=44, LPAREN=45, RPAREN=46, 
		LBRACE=47, RBRACE=48, LBRACK=49, RBRACK=50, LCHEVR=51, RCHEVR=52, SEMI=53, 
		COMMA=54, DOT=55, MINUS=56, PLUS=57, LECD=58, RECD=59, ASSIGN=60, WS=61, 
		COMMENT=62, LINE_COMMENT=63;
	public static final int
		RULE_proto = 0, RULE_syntax = 1, RULE_importStatement = 2, RULE_packageStatement = 3, 
		RULE_option = 4, RULE_optionName = 5, RULE_topLevelDef = 6, RULE_message = 7, 
		RULE_messageBody = 8, RULE_enumDefinition = 9, RULE_enumBody = 10, RULE_enumField = 11, 
		RULE_enumValueOption = 12, RULE_service = 13, RULE_rpc = 14, RULE_reserved = 15, 
		RULE_ranges = 16, RULE_range = 17, RULE_fieldNames = 18, RULE_type = 19, 
		RULE_fieldNumber = 20, RULE_field = 21, RULE_fieldOptions = 22, RULE_ecFieldData = 23, 
		RULE_fieldOption = 24, RULE_oneof = 25, RULE_oneofField = 26, RULE_mapField = 27, 
		RULE_keyType = 28, RULE_fullIdent = 29, RULE_messageName = 30, RULE_enumName = 31, 
		RULE_messageOrEnumName = 32, RULE_fieldName = 33, RULE_oneofName = 34, 
		RULE_mapName = 35, RULE_serviceName = 36, RULE_rpcName = 37, RULE_messageType = 38, 
		RULE_messageOrEnumType = 39, RULE_emptyStatement = 40, RULE_constant = 41;
	private static String[] makeRuleNames() {
		return new String[] {
			"proto", "syntax", "importStatement", "packageStatement", "option", "optionName", 
			"topLevelDef", "message", "messageBody", "enumDefinition", "enumBody", 
			"enumField", "enumValueOption", "service", "rpc", "reserved", "ranges", 
			"range", "fieldNames", "type", "fieldNumber", "field", "fieldOptions", 
			"ecFieldData", "fieldOption", "oneof", "oneofField", "mapField", "keyType", 
			"fullIdent", "messageName", "enumName", "messageOrEnumName", "fieldName", 
			"oneofName", "mapName", "serviceName", "rpcName", "messageType", "messageOrEnumType", 
			"emptyStatement", "constant"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'\"proto2\"'", "''proto2''", "'bool'", "'bytes'", "'double'", 
			"'enum'", "'fixed32'", "'fixed64'", "'float'", "'import'", "'int32'", 
			"'int64'", "'map'", "'message'", "'oneof'", "'option'", "'package'", 
			"'\"proto3\"'", "''proto3''", "'public'", "'repeated'", "'optional'", 
			"'required'", "'reserved'", "'returns'", "'rpc'", "'service'", "'sfixed32'", 
			"'sfixed64'", "'sint32'", "'sint64'", "'stream'", "'string'", "'syntax'", 
			"'to'", "'uint32'", "'uint64'", "'weak'", null, null, null, null, null, 
			null, "'('", "')'", "'{'", "'}'", "'['", "']'", "'<'", "'>'", "';'", 
			"','", "'.'", "'-'", "'+'", "'/*['", "']*/'", "'='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, "BOOL", "BYTES", "DOUBLE", "ENUM", "FIXED32", "FIXED64", 
			"FLOAT", "IMPORT", "INT32", "INT64", "MAP", "MESSAGE", "ONEOF", "OPTION", 
			"PACKAGE", "PROTO3_DOUBLE", "PROTO3_SINGLE", "PUBLIC", "REPEATED", "OPTIONAL", 
			"REQUIRED", "RESERVED", "RETURNS", "RPC", "SERVICE", "SFIXED32", "SFIXED64", 
			"SINT32", "SINT64", "STREAM", "STRING", "SYNTAX", "TO", "UINT32", "UINT64", 
			"WEAK", "Ident", "IntLit", "FloatLit", "BoolLit", "StrLit", "Quote", 
			"LPAREN", "RPAREN", "LBRACE", "RBRACE", "LBRACK", "RBRACK", "LCHEVR", 
			"RCHEVR", "SEMI", "COMMA", "DOT", "MINUS", "PLUS", "LECD", "RECD", "ASSIGN", 
			"WS", "COMMENT", "LINE_COMMENT"
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
	public String getGrammarFileName() { return "Protobuf3.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public Protobuf3Parser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProtoContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(Protobuf3Parser.EOF, 0); }
		public List<SyntaxContext> syntax() {
			return getRuleContexts(SyntaxContext.class);
		}
		public SyntaxContext syntax(int i) {
			return getRuleContext(SyntaxContext.class,i);
		}
		public List<ImportStatementContext> importStatement() {
			return getRuleContexts(ImportStatementContext.class);
		}
		public ImportStatementContext importStatement(int i) {
			return getRuleContext(ImportStatementContext.class,i);
		}
		public List<PackageStatementContext> packageStatement() {
			return getRuleContexts(PackageStatementContext.class);
		}
		public PackageStatementContext packageStatement(int i) {
			return getRuleContext(PackageStatementContext.class,i);
		}
		public List<OptionContext> option() {
			return getRuleContexts(OptionContext.class);
		}
		public OptionContext option(int i) {
			return getRuleContext(OptionContext.class,i);
		}
		public List<TopLevelDefContext> topLevelDef() {
			return getRuleContexts(TopLevelDefContext.class);
		}
		public TopLevelDefContext topLevelDef(int i) {
			return getRuleContext(TopLevelDefContext.class,i);
		}
		public List<EmptyStatementContext> emptyStatement() {
			return getRuleContexts(EmptyStatementContext.class);
		}
		public EmptyStatementContext emptyStatement(int i) {
			return getRuleContext(EmptyStatementContext.class,i);
		}
		public ProtoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_proto; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Protobuf3Visitor ) return ((Protobuf3Visitor<? extends T>)visitor).visitProto(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProtoContext proto() throws RecognitionException {
		ProtoContext _localctx = new ProtoContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_proto);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ENUM) | (1L << IMPORT) | (1L << MESSAGE) | (1L << OPTION) | (1L << PACKAGE) | (1L << PROTO3_DOUBLE) | (1L << PROTO3_SINGLE) | (1L << SERVICE) | (1L << SYNTAX) | (1L << SEMI))) != 0)) {
				{
				setState(90);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case PROTO3_DOUBLE:
				case PROTO3_SINGLE:
				case SYNTAX:
					{
					setState(84);
					syntax();
					}
					break;
				case IMPORT:
					{
					setState(85);
					importStatement();
					}
					break;
				case PACKAGE:
					{
					setState(86);
					packageStatement();
					}
					break;
				case OPTION:
					{
					setState(87);
					option();
					}
					break;
				case ENUM:
				case MESSAGE:
				case SERVICE:
					{
					setState(88);
					topLevelDef();
					}
					break;
				case SEMI:
					{
					setState(89);
					emptyStatement();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(94);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(95);
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

	public static class SyntaxContext extends ParserRuleContext {
		public TerminalNode SYNTAX() { return getToken(Protobuf3Parser.SYNTAX, 0); }
		public TerminalNode ASSIGN() { return getToken(Protobuf3Parser.ASSIGN, 0); }
		public TerminalNode SEMI() { return getToken(Protobuf3Parser.SEMI, 0); }
		public TerminalNode PROTO3_DOUBLE() { return getToken(Protobuf3Parser.PROTO3_DOUBLE, 0); }
		public TerminalNode PROTO3_SINGLE() { return getToken(Protobuf3Parser.PROTO3_SINGLE, 0); }
		public SyntaxContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_syntax; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Protobuf3Visitor ) return ((Protobuf3Visitor<? extends T>)visitor).visitSyntax(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SyntaxContext syntax() throws RecognitionException {
		SyntaxContext _localctx = new SyntaxContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_syntax);
		int _la;
		try {
			setState(102);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SYNTAX:
				enterOuterAlt(_localctx, 1);
				{
				setState(97);
				match(SYNTAX);
				setState(98);
				match(ASSIGN);
				setState(99);
				_la = _input.LA(1);
				if ( !(_la==T__0 || _la==T__1) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case PROTO3_DOUBLE:
			case PROTO3_SINGLE:
				enterOuterAlt(_localctx, 2);
				{
				setState(100);
				_la = _input.LA(1);
				if ( !(_la==PROTO3_DOUBLE || _la==PROTO3_SINGLE) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(101);
				match(SEMI);
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

	public static class ImportStatementContext extends ParserRuleContext {
		public TerminalNode IMPORT() { return getToken(Protobuf3Parser.IMPORT, 0); }
		public TerminalNode StrLit() { return getToken(Protobuf3Parser.StrLit, 0); }
		public TerminalNode SEMI() { return getToken(Protobuf3Parser.SEMI, 0); }
		public TerminalNode WEAK() { return getToken(Protobuf3Parser.WEAK, 0); }
		public TerminalNode PUBLIC() { return getToken(Protobuf3Parser.PUBLIC, 0); }
		public ImportStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Protobuf3Visitor ) return ((Protobuf3Visitor<? extends T>)visitor).visitImportStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportStatementContext importStatement() throws RecognitionException {
		ImportStatementContext _localctx = new ImportStatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_importStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(IMPORT);
			setState(106);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PUBLIC || _la==WEAK) {
				{
				setState(105);
				_la = _input.LA(1);
				if ( !(_la==PUBLIC || _la==WEAK) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(108);
			match(StrLit);
			setState(109);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PackageStatementContext extends ParserRuleContext {
		public TerminalNode PACKAGE() { return getToken(Protobuf3Parser.PACKAGE, 0); }
		public FullIdentContext fullIdent() {
			return getRuleContext(FullIdentContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(Protobuf3Parser.SEMI, 0); }
		public PackageStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_packageStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Protobuf3Visitor ) return ((Protobuf3Visitor<? extends T>)visitor).visitPackageStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PackageStatementContext packageStatement() throws RecognitionException {
		PackageStatementContext _localctx = new PackageStatementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_packageStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			match(PACKAGE);
			setState(112);
			fullIdent();
			setState(113);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OptionContext extends ParserRuleContext {
		public TerminalNode OPTION() { return getToken(Protobuf3Parser.OPTION, 0); }
		public OptionNameContext optionName() {
			return getRuleContext(OptionNameContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(Protobuf3Parser.ASSIGN, 0); }
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(Protobuf3Parser.SEMI, 0); }
		public OptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Protobuf3Visitor ) return ((Protobuf3Visitor<? extends T>)visitor).visitOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OptionContext option() throws RecognitionException {
		OptionContext _localctx = new OptionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_option);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			match(OPTION);
			setState(116);
			optionName();
			setState(117);
			match(ASSIGN);
			setState(118);
			constant();
			setState(119);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OptionNameContext extends ParserRuleContext {
		public List<TerminalNode> Ident() { return getTokens(Protobuf3Parser.Ident); }
		public TerminalNode Ident(int i) {
			return getToken(Protobuf3Parser.Ident, i);
		}
		public TerminalNode LPAREN() { return getToken(Protobuf3Parser.LPAREN, 0); }
		public FullIdentContext fullIdent() {
			return getRuleContext(FullIdentContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(Protobuf3Parser.RPAREN, 0); }
		public List<TerminalNode> DOT() { return getTokens(Protobuf3Parser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(Protobuf3Parser.DOT, i);
		}
		public OptionNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_optionName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Protobuf3Visitor ) return ((Protobuf3Visitor<? extends T>)visitor).visitOptionName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OptionNameContext optionName() throws RecognitionException {
		OptionNameContext _localctx = new OptionNameContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_optionName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Ident:
				{
				setState(121);
				match(Ident);
				}
				break;
			case LPAREN:
				{
				setState(122);
				match(LPAREN);
				setState(123);
				fullIdent();
				setState(124);
				match(RPAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(132);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(128);
				match(DOT);
				setState(129);
				match(Ident);
				}
				}
				setState(134);
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

	public static class TopLevelDefContext extends ParserRuleContext {
		public MessageContext message() {
			return getRuleContext(MessageContext.class,0);
		}
		public EnumDefinitionContext enumDefinition() {
			return getRuleContext(EnumDefinitionContext.class,0);
		}
		public ServiceContext service() {
			return getRuleContext(ServiceContext.class,0);
		}
		public TopLevelDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_topLevelDef; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Protobuf3Visitor ) return ((Protobuf3Visitor<? extends T>)visitor).visitTopLevelDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TopLevelDefContext topLevelDef() throws RecognitionException {
		TopLevelDefContext _localctx = new TopLevelDefContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_topLevelDef);
		try {
			setState(138);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MESSAGE:
				enterOuterAlt(_localctx, 1);
				{
				setState(135);
				message();
				}
				break;
			case ENUM:
				enterOuterAlt(_localctx, 2);
				{
				setState(136);
				enumDefinition();
				}
				break;
			case SERVICE:
				enterOuterAlt(_localctx, 3);
				{
				setState(137);
				service();
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

	public static class MessageContext extends ParserRuleContext {
		public TerminalNode MESSAGE() { return getToken(Protobuf3Parser.MESSAGE, 0); }
		public MessageNameContext messageName() {
			return getRuleContext(MessageNameContext.class,0);
		}
		public MessageBodyContext messageBody() {
			return getRuleContext(MessageBodyContext.class,0);
		}
		public MessageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_message; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Protobuf3Visitor ) return ((Protobuf3Visitor<? extends T>)visitor).visitMessage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MessageContext message() throws RecognitionException {
		MessageContext _localctx = new MessageContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_message);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			match(MESSAGE);
			setState(141);
			messageName();
			setState(142);
			messageBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MessageBodyContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(Protobuf3Parser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(Protobuf3Parser.RBRACE, 0); }
		public List<FieldContext> field() {
			return getRuleContexts(FieldContext.class);
		}
		public FieldContext field(int i) {
			return getRuleContext(FieldContext.class,i);
		}
		public List<EnumDefinitionContext> enumDefinition() {
			return getRuleContexts(EnumDefinitionContext.class);
		}
		public EnumDefinitionContext enumDefinition(int i) {
			return getRuleContext(EnumDefinitionContext.class,i);
		}
		public List<MessageContext> message() {
			return getRuleContexts(MessageContext.class);
		}
		public MessageContext message(int i) {
			return getRuleContext(MessageContext.class,i);
		}
		public List<OptionContext> option() {
			return getRuleContexts(OptionContext.class);
		}
		public OptionContext option(int i) {
			return getRuleContext(OptionContext.class,i);
		}
		public List<OneofContext> oneof() {
			return getRuleContexts(OneofContext.class);
		}
		public OneofContext oneof(int i) {
			return getRuleContext(OneofContext.class,i);
		}
		public List<MapFieldContext> mapField() {
			return getRuleContexts(MapFieldContext.class);
		}
		public MapFieldContext mapField(int i) {
			return getRuleContext(MapFieldContext.class,i);
		}
		public List<ReservedContext> reserved() {
			return getRuleContexts(ReservedContext.class);
		}
		public ReservedContext reserved(int i) {
			return getRuleContext(ReservedContext.class,i);
		}
		public List<EmptyStatementContext> emptyStatement() {
			return getRuleContexts(EmptyStatementContext.class);
		}
		public EmptyStatementContext emptyStatement(int i) {
			return getRuleContext(EmptyStatementContext.class,i);
		}
		public MessageBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_messageBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Protobuf3Visitor ) return ((Protobuf3Visitor<? extends T>)visitor).visitMessageBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MessageBodyContext messageBody() throws RecognitionException {
		MessageBodyContext _localctx = new MessageBodyContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_messageBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			match(LBRACE);
			setState(155);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << BYTES) | (1L << DOUBLE) | (1L << ENUM) | (1L << FIXED32) | (1L << FIXED64) | (1L << FLOAT) | (1L << INT32) | (1L << INT64) | (1L << MAP) | (1L << MESSAGE) | (1L << ONEOF) | (1L << OPTION) | (1L << REPEATED) | (1L << OPTIONAL) | (1L << REQUIRED) | (1L << RESERVED) | (1L << SFIXED32) | (1L << SFIXED64) | (1L << SINT32) | (1L << SINT64) | (1L << STRING) | (1L << UINT32) | (1L << UINT64) | (1L << Ident) | (1L << SEMI) | (1L << DOT))) != 0)) {
				{
				setState(153);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case BOOL:
				case BYTES:
				case DOUBLE:
				case FIXED32:
				case FIXED64:
				case FLOAT:
				case INT32:
				case INT64:
				case REPEATED:
				case OPTIONAL:
				case REQUIRED:
				case SFIXED32:
				case SFIXED64:
				case SINT32:
				case SINT64:
				case STRING:
				case UINT32:
				case UINT64:
				case Ident:
				case DOT:
					{
					setState(145);
					field();
					}
					break;
				case ENUM:
					{
					setState(146);
					enumDefinition();
					}
					break;
				case MESSAGE:
					{
					setState(147);
					message();
					}
					break;
				case OPTION:
					{
					setState(148);
					option();
					}
					break;
				case ONEOF:
					{
					setState(149);
					oneof();
					}
					break;
				case MAP:
					{
					setState(150);
					mapField();
					}
					break;
				case RESERVED:
					{
					setState(151);
					reserved();
					}
					break;
				case SEMI:
					{
					setState(152);
					emptyStatement();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(157);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(158);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnumDefinitionContext extends ParserRuleContext {
		public TerminalNode ENUM() { return getToken(Protobuf3Parser.ENUM, 0); }
		public EnumNameContext enumName() {
			return getRuleContext(EnumNameContext.class,0);
		}
		public EnumBodyContext enumBody() {
			return getRuleContext(EnumBodyContext.class,0);
		}
		public EnumDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumDefinition; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Protobuf3Visitor ) return ((Protobuf3Visitor<? extends T>)visitor).visitEnumDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnumDefinitionContext enumDefinition() throws RecognitionException {
		EnumDefinitionContext _localctx = new EnumDefinitionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_enumDefinition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
			match(ENUM);
			setState(161);
			enumName();
			setState(162);
			enumBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnumBodyContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(Protobuf3Parser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(Protobuf3Parser.RBRACE, 0); }
		public List<OptionContext> option() {
			return getRuleContexts(OptionContext.class);
		}
		public OptionContext option(int i) {
			return getRuleContext(OptionContext.class,i);
		}
		public List<EnumFieldContext> enumField() {
			return getRuleContexts(EnumFieldContext.class);
		}
		public EnumFieldContext enumField(int i) {
			return getRuleContext(EnumFieldContext.class,i);
		}
		public List<EmptyStatementContext> emptyStatement() {
			return getRuleContexts(EmptyStatementContext.class);
		}
		public EmptyStatementContext emptyStatement(int i) {
			return getRuleContext(EmptyStatementContext.class,i);
		}
		public EnumBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Protobuf3Visitor ) return ((Protobuf3Visitor<? extends T>)visitor).visitEnumBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnumBodyContext enumBody() throws RecognitionException {
		EnumBodyContext _localctx = new EnumBodyContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_enumBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			match(LBRACE);
			setState(170);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPTION) | (1L << Ident) | (1L << SEMI))) != 0)) {
				{
				setState(168);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case OPTION:
					{
					setState(165);
					option();
					}
					break;
				case Ident:
					{
					setState(166);
					enumField();
					}
					break;
				case SEMI:
					{
					setState(167);
					emptyStatement();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(172);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(173);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnumFieldContext extends ParserRuleContext {
		public TerminalNode Ident() { return getToken(Protobuf3Parser.Ident, 0); }
		public TerminalNode ASSIGN() { return getToken(Protobuf3Parser.ASSIGN, 0); }
		public TerminalNode IntLit() { return getToken(Protobuf3Parser.IntLit, 0); }
		public TerminalNode SEMI() { return getToken(Protobuf3Parser.SEMI, 0); }
		public TerminalNode MINUS() { return getToken(Protobuf3Parser.MINUS, 0); }
		public TerminalNode LBRACK() { return getToken(Protobuf3Parser.LBRACK, 0); }
		public List<EnumValueOptionContext> enumValueOption() {
			return getRuleContexts(EnumValueOptionContext.class);
		}
		public EnumValueOptionContext enumValueOption(int i) {
			return getRuleContext(EnumValueOptionContext.class,i);
		}
		public TerminalNode RBRACK() { return getToken(Protobuf3Parser.RBRACK, 0); }
		public List<TerminalNode> COMMA() { return getTokens(Protobuf3Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(Protobuf3Parser.COMMA, i);
		}
		public EnumFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumField; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Protobuf3Visitor ) return ((Protobuf3Visitor<? extends T>)visitor).visitEnumField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnumFieldContext enumField() throws RecognitionException {
		EnumFieldContext _localctx = new EnumFieldContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_enumField);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			match(Ident);
			setState(176);
			match(ASSIGN);
			setState(178);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==MINUS) {
				{
				setState(177);
				match(MINUS);
				}
			}

			setState(180);
			match(IntLit);
			setState(192);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACK) {
				{
				setState(181);
				match(LBRACK);
				setState(182);
				enumValueOption();
				setState(187);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(183);
					match(COMMA);
					setState(184);
					enumValueOption();
					}
					}
					setState(189);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(190);
				match(RBRACK);
				}
			}

			setState(194);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnumValueOptionContext extends ParserRuleContext {
		public OptionNameContext optionName() {
			return getRuleContext(OptionNameContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(Protobuf3Parser.ASSIGN, 0); }
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public EnumValueOptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumValueOption; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Protobuf3Visitor ) return ((Protobuf3Visitor<? extends T>)visitor).visitEnumValueOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnumValueOptionContext enumValueOption() throws RecognitionException {
		EnumValueOptionContext _localctx = new EnumValueOptionContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_enumValueOption);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(196);
			optionName();
			setState(197);
			match(ASSIGN);
			setState(198);
			constant();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ServiceContext extends ParserRuleContext {
		public TerminalNode SERVICE() { return getToken(Protobuf3Parser.SERVICE, 0); }
		public ServiceNameContext serviceName() {
			return getRuleContext(ServiceNameContext.class,0);
		}
		public TerminalNode LBRACE() { return getToken(Protobuf3Parser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(Protobuf3Parser.RBRACE, 0); }
		public List<OptionContext> option() {
			return getRuleContexts(OptionContext.class);
		}
		public OptionContext option(int i) {
			return getRuleContext(OptionContext.class,i);
		}
		public List<RpcContext> rpc() {
			return getRuleContexts(RpcContext.class);
		}
		public RpcContext rpc(int i) {
			return getRuleContext(RpcContext.class,i);
		}
		public List<EmptyStatementContext> emptyStatement() {
			return getRuleContexts(EmptyStatementContext.class);
		}
		public EmptyStatementContext emptyStatement(int i) {
			return getRuleContext(EmptyStatementContext.class,i);
		}
		public ServiceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_service; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Protobuf3Visitor ) return ((Protobuf3Visitor<? extends T>)visitor).visitService(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ServiceContext service() throws RecognitionException {
		ServiceContext _localctx = new ServiceContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_service);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(200);
			match(SERVICE);
			setState(201);
			serviceName();
			setState(202);
			match(LBRACE);
			setState(208);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPTION) | (1L << RPC) | (1L << SEMI))) != 0)) {
				{
				setState(206);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case OPTION:
					{
					setState(203);
					option();
					}
					break;
				case RPC:
					{
					setState(204);
					rpc();
					}
					break;
				case SEMI:
					{
					setState(205);
					emptyStatement();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(210);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(211);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RpcContext extends ParserRuleContext {
		public TerminalNode RPC() { return getToken(Protobuf3Parser.RPC, 0); }
		public RpcNameContext rpcName() {
			return getRuleContext(RpcNameContext.class,0);
		}
		public List<TerminalNode> LPAREN() { return getTokens(Protobuf3Parser.LPAREN); }
		public TerminalNode LPAREN(int i) {
			return getToken(Protobuf3Parser.LPAREN, i);
		}
		public List<MessageTypeContext> messageType() {
			return getRuleContexts(MessageTypeContext.class);
		}
		public MessageTypeContext messageType(int i) {
			return getRuleContext(MessageTypeContext.class,i);
		}
		public List<TerminalNode> RPAREN() { return getTokens(Protobuf3Parser.RPAREN); }
		public TerminalNode RPAREN(int i) {
			return getToken(Protobuf3Parser.RPAREN, i);
		}
		public TerminalNode RETURNS() { return getToken(Protobuf3Parser.RETURNS, 0); }
		public TerminalNode SEMI() { return getToken(Protobuf3Parser.SEMI, 0); }
		public List<TerminalNode> STREAM() { return getTokens(Protobuf3Parser.STREAM); }
		public TerminalNode STREAM(int i) {
			return getToken(Protobuf3Parser.STREAM, i);
		}
		public TerminalNode LBRACE() { return getToken(Protobuf3Parser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(Protobuf3Parser.RBRACE, 0); }
		public List<OptionContext> option() {
			return getRuleContexts(OptionContext.class);
		}
		public OptionContext option(int i) {
			return getRuleContext(OptionContext.class,i);
		}
		public List<EmptyStatementContext> emptyStatement() {
			return getRuleContexts(EmptyStatementContext.class);
		}
		public EmptyStatementContext emptyStatement(int i) {
			return getRuleContext(EmptyStatementContext.class,i);
		}
		public RpcContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rpc; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Protobuf3Visitor ) return ((Protobuf3Visitor<? extends T>)visitor).visitRpc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RpcContext rpc() throws RecognitionException {
		RpcContext _localctx = new RpcContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_rpc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(213);
			match(RPC);
			setState(214);
			rpcName();
			setState(215);
			match(LPAREN);
			setState(217);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STREAM) {
				{
				setState(216);
				match(STREAM);
				}
			}

			setState(219);
			messageType();
			setState(220);
			match(RPAREN);
			setState(221);
			match(RETURNS);
			setState(222);
			match(LPAREN);
			setState(224);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STREAM) {
				{
				setState(223);
				match(STREAM);
				}
			}

			setState(226);
			messageType();
			setState(227);
			match(RPAREN);
			setState(238);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACE:
				{
				{
				setState(228);
				match(LBRACE);
				setState(233);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==OPTION || _la==SEMI) {
					{
					setState(231);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case OPTION:
						{
						setState(229);
						option();
						}
						break;
					case SEMI:
						{
						setState(230);
						emptyStatement();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(235);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(236);
				match(RBRACE);
				}
				}
				break;
			case SEMI:
				{
				setState(237);
				match(SEMI);
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

	public static class ReservedContext extends ParserRuleContext {
		public TerminalNode RESERVED() { return getToken(Protobuf3Parser.RESERVED, 0); }
		public TerminalNode SEMI() { return getToken(Protobuf3Parser.SEMI, 0); }
		public RangesContext ranges() {
			return getRuleContext(RangesContext.class,0);
		}
		public FieldNamesContext fieldNames() {
			return getRuleContext(FieldNamesContext.class,0);
		}
		public ReservedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reserved; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Protobuf3Visitor ) return ((Protobuf3Visitor<? extends T>)visitor).visitReserved(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReservedContext reserved() throws RecognitionException {
		ReservedContext _localctx = new ReservedContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_reserved);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240);
			match(RESERVED);
			setState(243);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IntLit:
				{
				setState(241);
				ranges();
				}
				break;
			case StrLit:
				{
				setState(242);
				fieldNames();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(245);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RangesContext extends ParserRuleContext {
		public List<RangeContext> range() {
			return getRuleContexts(RangeContext.class);
		}
		public RangeContext range(int i) {
			return getRuleContext(RangeContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(Protobuf3Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(Protobuf3Parser.COMMA, i);
		}
		public RangesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ranges; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Protobuf3Visitor ) return ((Protobuf3Visitor<? extends T>)visitor).visitRanges(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RangesContext ranges() throws RecognitionException {
		RangesContext _localctx = new RangesContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_ranges);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			range();
			setState(252);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(248);
				match(COMMA);
				setState(249);
				range();
				}
				}
				setState(254);
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

	public static class RangeContext extends ParserRuleContext {
		public List<TerminalNode> IntLit() { return getTokens(Protobuf3Parser.IntLit); }
		public TerminalNode IntLit(int i) {
			return getToken(Protobuf3Parser.IntLit, i);
		}
		public TerminalNode TO() { return getToken(Protobuf3Parser.TO, 0); }
		public RangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_range; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Protobuf3Visitor ) return ((Protobuf3Visitor<? extends T>)visitor).visitRange(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RangeContext range() throws RecognitionException {
		RangeContext _localctx = new RangeContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_range);
		try {
			setState(259);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(255);
				match(IntLit);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(256);
				match(IntLit);
				setState(257);
				match(TO);
				setState(258);
				match(IntLit);
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

	public static class FieldNamesContext extends ParserRuleContext {
		public List<TerminalNode> StrLit() { return getTokens(Protobuf3Parser.StrLit); }
		public TerminalNode StrLit(int i) {
			return getToken(Protobuf3Parser.StrLit, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(Protobuf3Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(Protobuf3Parser.COMMA, i);
		}
		public FieldNamesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldNames; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Protobuf3Visitor ) return ((Protobuf3Visitor<? extends T>)visitor).visitFieldNames(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldNamesContext fieldNames() throws RecognitionException {
		FieldNamesContext _localctx = new FieldNamesContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_fieldNames);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(261);
			match(StrLit);
			setState(266);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(262);
				match(COMMA);
				setState(263);
				match(StrLit);
				}
				}
				setState(268);
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

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode DOUBLE() { return getToken(Protobuf3Parser.DOUBLE, 0); }
		public TerminalNode FLOAT() { return getToken(Protobuf3Parser.FLOAT, 0); }
		public TerminalNode INT32() { return getToken(Protobuf3Parser.INT32, 0); }
		public TerminalNode INT64() { return getToken(Protobuf3Parser.INT64, 0); }
		public TerminalNode UINT32() { return getToken(Protobuf3Parser.UINT32, 0); }
		public TerminalNode UINT64() { return getToken(Protobuf3Parser.UINT64, 0); }
		public TerminalNode SINT32() { return getToken(Protobuf3Parser.SINT32, 0); }
		public TerminalNode SINT64() { return getToken(Protobuf3Parser.SINT64, 0); }
		public TerminalNode FIXED32() { return getToken(Protobuf3Parser.FIXED32, 0); }
		public TerminalNode FIXED64() { return getToken(Protobuf3Parser.FIXED64, 0); }
		public TerminalNode SFIXED32() { return getToken(Protobuf3Parser.SFIXED32, 0); }
		public TerminalNode SFIXED64() { return getToken(Protobuf3Parser.SFIXED64, 0); }
		public TerminalNode BOOL() { return getToken(Protobuf3Parser.BOOL, 0); }
		public TerminalNode STRING() { return getToken(Protobuf3Parser.STRING, 0); }
		public TerminalNode BYTES() { return getToken(Protobuf3Parser.BYTES, 0); }
		public MessageOrEnumTypeContext messageOrEnumType() {
			return getRuleContext(MessageOrEnumTypeContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Protobuf3Visitor ) return ((Protobuf3Visitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_type);
		int _la;
		try {
			setState(271);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOL:
			case BYTES:
			case DOUBLE:
			case FIXED32:
			case FIXED64:
			case FLOAT:
			case INT32:
			case INT64:
			case SFIXED32:
			case SFIXED64:
			case SINT32:
			case SINT64:
			case STRING:
			case UINT32:
			case UINT64:
				enterOuterAlt(_localctx, 1);
				{
				setState(269);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << BYTES) | (1L << DOUBLE) | (1L << FIXED32) | (1L << FIXED64) | (1L << FLOAT) | (1L << INT32) | (1L << INT64) | (1L << SFIXED32) | (1L << SFIXED64) | (1L << SINT32) | (1L << SINT64) | (1L << STRING) | (1L << UINT32) | (1L << UINT64))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case Ident:
			case DOT:
				enterOuterAlt(_localctx, 2);
				{
				setState(270);
				messageOrEnumType();
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

	public static class FieldNumberContext extends ParserRuleContext {
		public TerminalNode IntLit() { return getToken(Protobuf3Parser.IntLit, 0); }
		public FieldNumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldNumber; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Protobuf3Visitor ) return ((Protobuf3Visitor<? extends T>)visitor).visitFieldNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldNumberContext fieldNumber() throws RecognitionException {
		FieldNumberContext _localctx = new FieldNumberContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_fieldNumber);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273);
			match(IntLit);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public FieldNameContext fieldName() {
			return getRuleContext(FieldNameContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(Protobuf3Parser.ASSIGN, 0); }
		public FieldNumberContext fieldNumber() {
			return getRuleContext(FieldNumberContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(Protobuf3Parser.SEMI, 0); }
		public TerminalNode LBRACK() { return getToken(Protobuf3Parser.LBRACK, 0); }
		public FieldOptionsContext fieldOptions() {
			return getRuleContext(FieldOptionsContext.class,0);
		}
		public TerminalNode RBRACK() { return getToken(Protobuf3Parser.RBRACK, 0); }
		public EcFieldDataContext ecFieldData() {
			return getRuleContext(EcFieldDataContext.class,0);
		}
		public TerminalNode REPEATED() { return getToken(Protobuf3Parser.REPEATED, 0); }
		public TerminalNode OPTIONAL() { return getToken(Protobuf3Parser.OPTIONAL, 0); }
		public TerminalNode REQUIRED() { return getToken(Protobuf3Parser.REQUIRED, 0); }
		public FieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Protobuf3Visitor ) return ((Protobuf3Visitor<? extends T>)visitor).visitField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldContext field() throws RecognitionException {
		FieldContext _localctx = new FieldContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_field);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(276);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << REPEATED) | (1L << OPTIONAL) | (1L << REQUIRED))) != 0)) {
				{
				setState(275);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << REPEATED) | (1L << OPTIONAL) | (1L << REQUIRED))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(278);
			type();
			setState(279);
			fieldName();
			setState(280);
			match(ASSIGN);
			setState(281);
			fieldNumber();
			setState(286);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACK) {
				{
				setState(282);
				match(LBRACK);
				setState(283);
				fieldOptions();
				setState(284);
				match(RBRACK);
				}
			}

			setState(289);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LECD) {
				{
				setState(288);
				ecFieldData();
				}
			}

			setState(291);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldOptionsContext extends ParserRuleContext {
		public List<FieldOptionContext> fieldOption() {
			return getRuleContexts(FieldOptionContext.class);
		}
		public FieldOptionContext fieldOption(int i) {
			return getRuleContext(FieldOptionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(Protobuf3Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(Protobuf3Parser.COMMA, i);
		}
		public FieldOptionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldOptions; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Protobuf3Visitor ) return ((Protobuf3Visitor<? extends T>)visitor).visitFieldOptions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldOptionsContext fieldOptions() throws RecognitionException {
		FieldOptionsContext _localctx = new FieldOptionsContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_fieldOptions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			fieldOption();
			setState(298);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(294);
				match(COMMA);
				setState(295);
				fieldOption();
				}
				}
				setState(300);
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

	public static class EcFieldDataContext extends ParserRuleContext {
		public TerminalNode LECD() { return getToken(Protobuf3Parser.LECD, 0); }
		public List<TerminalNode> Ident() { return getTokens(Protobuf3Parser.Ident); }
		public TerminalNode Ident(int i) {
			return getToken(Protobuf3Parser.Ident, i);
		}
		public TerminalNode ASSIGN() { return getToken(Protobuf3Parser.ASSIGN, 0); }
		public TerminalNode RECD() { return getToken(Protobuf3Parser.RECD, 0); }
		public EcFieldDataContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ecFieldData; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Protobuf3Visitor ) return ((Protobuf3Visitor<? extends T>)visitor).visitEcFieldData(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EcFieldDataContext ecFieldData() throws RecognitionException {
		EcFieldDataContext _localctx = new EcFieldDataContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_ecFieldData);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(301);
			match(LECD);
			setState(302);
			match(Ident);
			setState(303);
			match(ASSIGN);
			setState(304);
			match(Ident);
			setState(305);
			match(RECD);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldOptionContext extends ParserRuleContext {
		public OptionNameContext optionName() {
			return getRuleContext(OptionNameContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(Protobuf3Parser.ASSIGN, 0); }
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public FieldOptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldOption; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Protobuf3Visitor ) return ((Protobuf3Visitor<? extends T>)visitor).visitFieldOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldOptionContext fieldOption() throws RecognitionException {
		FieldOptionContext _localctx = new FieldOptionContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_fieldOption);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(307);
			optionName();
			setState(308);
			match(ASSIGN);
			setState(309);
			constant();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OneofContext extends ParserRuleContext {
		public TerminalNode ONEOF() { return getToken(Protobuf3Parser.ONEOF, 0); }
		public OneofNameContext oneofName() {
			return getRuleContext(OneofNameContext.class,0);
		}
		public TerminalNode LBRACE() { return getToken(Protobuf3Parser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(Protobuf3Parser.RBRACE, 0); }
		public List<OneofFieldContext> oneofField() {
			return getRuleContexts(OneofFieldContext.class);
		}
		public OneofFieldContext oneofField(int i) {
			return getRuleContext(OneofFieldContext.class,i);
		}
		public List<EmptyStatementContext> emptyStatement() {
			return getRuleContexts(EmptyStatementContext.class);
		}
		public EmptyStatementContext emptyStatement(int i) {
			return getRuleContext(EmptyStatementContext.class,i);
		}
		public OneofContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_oneof; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Protobuf3Visitor ) return ((Protobuf3Visitor<? extends T>)visitor).visitOneof(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OneofContext oneof() throws RecognitionException {
		OneofContext _localctx = new OneofContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_oneof);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(311);
			match(ONEOF);
			setState(312);
			oneofName();
			setState(313);
			match(LBRACE);
			setState(318);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << BYTES) | (1L << DOUBLE) | (1L << FIXED32) | (1L << FIXED64) | (1L << FLOAT) | (1L << INT32) | (1L << INT64) | (1L << SFIXED32) | (1L << SFIXED64) | (1L << SINT32) | (1L << SINT64) | (1L << STRING) | (1L << UINT32) | (1L << UINT64) | (1L << Ident) | (1L << SEMI) | (1L << DOT))) != 0)) {
				{
				setState(316);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case BOOL:
				case BYTES:
				case DOUBLE:
				case FIXED32:
				case FIXED64:
				case FLOAT:
				case INT32:
				case INT64:
				case SFIXED32:
				case SFIXED64:
				case SINT32:
				case SINT64:
				case STRING:
				case UINT32:
				case UINT64:
				case Ident:
				case DOT:
					{
					setState(314);
					oneofField();
					}
					break;
				case SEMI:
					{
					setState(315);
					emptyStatement();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(320);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(321);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OneofFieldContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public FieldNameContext fieldName() {
			return getRuleContext(FieldNameContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(Protobuf3Parser.ASSIGN, 0); }
		public FieldNumberContext fieldNumber() {
			return getRuleContext(FieldNumberContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(Protobuf3Parser.SEMI, 0); }
		public TerminalNode LBRACK() { return getToken(Protobuf3Parser.LBRACK, 0); }
		public FieldOptionsContext fieldOptions() {
			return getRuleContext(FieldOptionsContext.class,0);
		}
		public TerminalNode RBRACK() { return getToken(Protobuf3Parser.RBRACK, 0); }
		public OneofFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_oneofField; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Protobuf3Visitor ) return ((Protobuf3Visitor<? extends T>)visitor).visitOneofField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OneofFieldContext oneofField() throws RecognitionException {
		OneofFieldContext _localctx = new OneofFieldContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_oneofField);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(323);
			type();
			setState(324);
			fieldName();
			setState(325);
			match(ASSIGN);
			setState(326);
			fieldNumber();
			setState(331);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACK) {
				{
				setState(327);
				match(LBRACK);
				setState(328);
				fieldOptions();
				setState(329);
				match(RBRACK);
				}
			}

			setState(333);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MapFieldContext extends ParserRuleContext {
		public TerminalNode MAP() { return getToken(Protobuf3Parser.MAP, 0); }
		public TerminalNode LCHEVR() { return getToken(Protobuf3Parser.LCHEVR, 0); }
		public KeyTypeContext keyType() {
			return getRuleContext(KeyTypeContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(Protobuf3Parser.COMMA, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode RCHEVR() { return getToken(Protobuf3Parser.RCHEVR, 0); }
		public MapNameContext mapName() {
			return getRuleContext(MapNameContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(Protobuf3Parser.ASSIGN, 0); }
		public FieldNumberContext fieldNumber() {
			return getRuleContext(FieldNumberContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(Protobuf3Parser.SEMI, 0); }
		public TerminalNode LBRACK() { return getToken(Protobuf3Parser.LBRACK, 0); }
		public FieldOptionsContext fieldOptions() {
			return getRuleContext(FieldOptionsContext.class,0);
		}
		public TerminalNode RBRACK() { return getToken(Protobuf3Parser.RBRACK, 0); }
		public MapFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mapField; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Protobuf3Visitor ) return ((Protobuf3Visitor<? extends T>)visitor).visitMapField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MapFieldContext mapField() throws RecognitionException {
		MapFieldContext _localctx = new MapFieldContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_mapField);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(335);
			match(MAP);
			setState(336);
			match(LCHEVR);
			setState(337);
			keyType();
			setState(338);
			match(COMMA);
			setState(339);
			type();
			setState(340);
			match(RCHEVR);
			setState(341);
			mapName();
			setState(342);
			match(ASSIGN);
			setState(343);
			fieldNumber();
			setState(348);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACK) {
				{
				setState(344);
				match(LBRACK);
				setState(345);
				fieldOptions();
				setState(346);
				match(RBRACK);
				}
			}

			setState(350);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KeyTypeContext extends ParserRuleContext {
		public TerminalNode INT32() { return getToken(Protobuf3Parser.INT32, 0); }
		public TerminalNode INT64() { return getToken(Protobuf3Parser.INT64, 0); }
		public TerminalNode UINT32() { return getToken(Protobuf3Parser.UINT32, 0); }
		public TerminalNode UINT64() { return getToken(Protobuf3Parser.UINT64, 0); }
		public TerminalNode SINT32() { return getToken(Protobuf3Parser.SINT32, 0); }
		public TerminalNode SINT64() { return getToken(Protobuf3Parser.SINT64, 0); }
		public TerminalNode FIXED32() { return getToken(Protobuf3Parser.FIXED32, 0); }
		public TerminalNode FIXED64() { return getToken(Protobuf3Parser.FIXED64, 0); }
		public TerminalNode SFIXED32() { return getToken(Protobuf3Parser.SFIXED32, 0); }
		public TerminalNode SFIXED64() { return getToken(Protobuf3Parser.SFIXED64, 0); }
		public TerminalNode BOOL() { return getToken(Protobuf3Parser.BOOL, 0); }
		public TerminalNode STRING() { return getToken(Protobuf3Parser.STRING, 0); }
		public KeyTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Protobuf3Visitor ) return ((Protobuf3Visitor<? extends T>)visitor).visitKeyType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeyTypeContext keyType() throws RecognitionException {
		KeyTypeContext _localctx = new KeyTypeContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_keyType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(352);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << FIXED32) | (1L << FIXED64) | (1L << INT32) | (1L << INT64) | (1L << SFIXED32) | (1L << SFIXED64) | (1L << SINT32) | (1L << SINT64) | (1L << STRING) | (1L << UINT32) | (1L << UINT64))) != 0)) ) {
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

	public static class FullIdentContext extends ParserRuleContext {
		public List<TerminalNode> Ident() { return getTokens(Protobuf3Parser.Ident); }
		public TerminalNode Ident(int i) {
			return getToken(Protobuf3Parser.Ident, i);
		}
		public List<TerminalNode> DOT() { return getTokens(Protobuf3Parser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(Protobuf3Parser.DOT, i);
		}
		public FullIdentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fullIdent; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Protobuf3Visitor ) return ((Protobuf3Visitor<? extends T>)visitor).visitFullIdent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FullIdentContext fullIdent() throws RecognitionException {
		FullIdentContext _localctx = new FullIdentContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_fullIdent);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(354);
			match(Ident);
			setState(359);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(355);
				match(DOT);
				setState(356);
				match(Ident);
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

	public static class MessageNameContext extends ParserRuleContext {
		public TerminalNode Ident() { return getToken(Protobuf3Parser.Ident, 0); }
		public MessageNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_messageName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Protobuf3Visitor ) return ((Protobuf3Visitor<? extends T>)visitor).visitMessageName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MessageNameContext messageName() throws RecognitionException {
		MessageNameContext _localctx = new MessageNameContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_messageName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(362);
			match(Ident);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnumNameContext extends ParserRuleContext {
		public TerminalNode Ident() { return getToken(Protobuf3Parser.Ident, 0); }
		public EnumNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Protobuf3Visitor ) return ((Protobuf3Visitor<? extends T>)visitor).visitEnumName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnumNameContext enumName() throws RecognitionException {
		EnumNameContext _localctx = new EnumNameContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_enumName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(364);
			match(Ident);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MessageOrEnumNameContext extends ParserRuleContext {
		public TerminalNode Ident() { return getToken(Protobuf3Parser.Ident, 0); }
		public MessageOrEnumNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_messageOrEnumName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Protobuf3Visitor ) return ((Protobuf3Visitor<? extends T>)visitor).visitMessageOrEnumName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MessageOrEnumNameContext messageOrEnumName() throws RecognitionException {
		MessageOrEnumNameContext _localctx = new MessageOrEnumNameContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_messageOrEnumName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(366);
			match(Ident);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldNameContext extends ParserRuleContext {
		public TerminalNode Ident() { return getToken(Protobuf3Parser.Ident, 0); }
		public FieldNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Protobuf3Visitor ) return ((Protobuf3Visitor<? extends T>)visitor).visitFieldName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldNameContext fieldName() throws RecognitionException {
		FieldNameContext _localctx = new FieldNameContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_fieldName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(368);
			match(Ident);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OneofNameContext extends ParserRuleContext {
		public TerminalNode Ident() { return getToken(Protobuf3Parser.Ident, 0); }
		public OneofNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_oneofName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Protobuf3Visitor ) return ((Protobuf3Visitor<? extends T>)visitor).visitOneofName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OneofNameContext oneofName() throws RecognitionException {
		OneofNameContext _localctx = new OneofNameContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_oneofName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(370);
			match(Ident);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MapNameContext extends ParserRuleContext {
		public TerminalNode Ident() { return getToken(Protobuf3Parser.Ident, 0); }
		public MapNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mapName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Protobuf3Visitor ) return ((Protobuf3Visitor<? extends T>)visitor).visitMapName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MapNameContext mapName() throws RecognitionException {
		MapNameContext _localctx = new MapNameContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_mapName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(372);
			match(Ident);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ServiceNameContext extends ParserRuleContext {
		public TerminalNode Ident() { return getToken(Protobuf3Parser.Ident, 0); }
		public ServiceNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_serviceName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Protobuf3Visitor ) return ((Protobuf3Visitor<? extends T>)visitor).visitServiceName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ServiceNameContext serviceName() throws RecognitionException {
		ServiceNameContext _localctx = new ServiceNameContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_serviceName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(374);
			match(Ident);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RpcNameContext extends ParserRuleContext {
		public TerminalNode Ident() { return getToken(Protobuf3Parser.Ident, 0); }
		public RpcNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rpcName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Protobuf3Visitor ) return ((Protobuf3Visitor<? extends T>)visitor).visitRpcName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RpcNameContext rpcName() throws RecognitionException {
		RpcNameContext _localctx = new RpcNameContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_rpcName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(376);
			match(Ident);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MessageTypeContext extends ParserRuleContext {
		public MessageNameContext messageName() {
			return getRuleContext(MessageNameContext.class,0);
		}
		public List<TerminalNode> DOT() { return getTokens(Protobuf3Parser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(Protobuf3Parser.DOT, i);
		}
		public List<TerminalNode> Ident() { return getTokens(Protobuf3Parser.Ident); }
		public TerminalNode Ident(int i) {
			return getToken(Protobuf3Parser.Ident, i);
		}
		public MessageTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_messageType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Protobuf3Visitor ) return ((Protobuf3Visitor<? extends T>)visitor).visitMessageType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MessageTypeContext messageType() throws RecognitionException {
		MessageTypeContext _localctx = new MessageTypeContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_messageType);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(379);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOT) {
				{
				setState(378);
				match(DOT);
				}
			}

			setState(385);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(381);
					match(Ident);
					setState(382);
					match(DOT);
					}
					} 
				}
				setState(387);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
			}
			setState(388);
			messageName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MessageOrEnumTypeContext extends ParserRuleContext {
		public MessageOrEnumNameContext messageOrEnumName() {
			return getRuleContext(MessageOrEnumNameContext.class,0);
		}
		public List<TerminalNode> DOT() { return getTokens(Protobuf3Parser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(Protobuf3Parser.DOT, i);
		}
		public List<TerminalNode> Ident() { return getTokens(Protobuf3Parser.Ident); }
		public TerminalNode Ident(int i) {
			return getToken(Protobuf3Parser.Ident, i);
		}
		public MessageOrEnumTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_messageOrEnumType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Protobuf3Visitor ) return ((Protobuf3Visitor<? extends T>)visitor).visitMessageOrEnumType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MessageOrEnumTypeContext messageOrEnumType() throws RecognitionException {
		MessageOrEnumTypeContext _localctx = new MessageOrEnumTypeContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_messageOrEnumType);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(391);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOT) {
				{
				setState(390);
				match(DOT);
				}
			}

			setState(397);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,38,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(393);
					match(Ident);
					setState(394);
					match(DOT);
					}
					} 
				}
				setState(399);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,38,_ctx);
			}
			setState(400);
			messageOrEnumName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EmptyStatementContext extends ParserRuleContext {
		public TerminalNode SEMI() { return getToken(Protobuf3Parser.SEMI, 0); }
		public EmptyStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_emptyStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Protobuf3Visitor ) return ((Protobuf3Visitor<? extends T>)visitor).visitEmptyStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EmptyStatementContext emptyStatement() throws RecognitionException {
		EmptyStatementContext _localctx = new EmptyStatementContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_emptyStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(402);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
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
		public FullIdentContext fullIdent() {
			return getRuleContext(FullIdentContext.class,0);
		}
		public TerminalNode IntLit() { return getToken(Protobuf3Parser.IntLit, 0); }
		public TerminalNode MINUS() { return getToken(Protobuf3Parser.MINUS, 0); }
		public TerminalNode PLUS() { return getToken(Protobuf3Parser.PLUS, 0); }
		public TerminalNode FloatLit() { return getToken(Protobuf3Parser.FloatLit, 0); }
		public TerminalNode StrLit() { return getToken(Protobuf3Parser.StrLit, 0); }
		public TerminalNode BoolLit() { return getToken(Protobuf3Parser.BoolLit, 0); }
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Protobuf3Visitor ) return ((Protobuf3Visitor<? extends T>)visitor).visitConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_constant);
		int _la;
		try {
			setState(414);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(404);
				fullIdent();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(406);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINUS || _la==PLUS) {
					{
					setState(405);
					_la = _input.LA(1);
					if ( !(_la==MINUS || _la==PLUS) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
				}

				setState(408);
				match(IntLit);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(410);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINUS || _la==PLUS) {
					{
					setState(409);
					_la = _input.LA(1);
					if ( !(_la==MINUS || _la==PLUS) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
				}

				setState(412);
				match(FloatLit);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(413);
				_la = _input.LA(1);
				if ( !(_la==BoolLit || _la==StrLit) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3A\u01a3\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\7\2]\n\2\f\2\16\2`\13\2\3\2\3\2\3\3\3\3\3\3\3\3"+
		"\3\3\5\3i\n\3\3\4\3\4\5\4m\n\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\5\7\u0081\n\7\3\7\3\7\7\7\u0085\n\7\f"+
		"\7\16\7\u0088\13\7\3\b\3\b\3\b\5\b\u008d\n\b\3\t\3\t\3\t\3\t\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u009c\n\n\f\n\16\n\u009f\13\n\3\n\3\n\3"+
		"\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\7\f\u00ab\n\f\f\f\16\f\u00ae\13\f\3"+
		"\f\3\f\3\r\3\r\3\r\5\r\u00b5\n\r\3\r\3\r\3\r\3\r\3\r\7\r\u00bc\n\r\f\r"+
		"\16\r\u00bf\13\r\3\r\3\r\5\r\u00c3\n\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\7\17\u00d1\n\17\f\17\16\17\u00d4\13\17\3\17"+
		"\3\17\3\20\3\20\3\20\3\20\5\20\u00dc\n\20\3\20\3\20\3\20\3\20\3\20\5\20"+
		"\u00e3\n\20\3\20\3\20\3\20\3\20\3\20\7\20\u00ea\n\20\f\20\16\20\u00ed"+
		"\13\20\3\20\3\20\5\20\u00f1\n\20\3\21\3\21\3\21\5\21\u00f6\n\21\3\21\3"+
		"\21\3\22\3\22\3\22\7\22\u00fd\n\22\f\22\16\22\u0100\13\22\3\23\3\23\3"+
		"\23\3\23\5\23\u0106\n\23\3\24\3\24\3\24\7\24\u010b\n\24\f\24\16\24\u010e"+
		"\13\24\3\25\3\25\5\25\u0112\n\25\3\26\3\26\3\27\5\27\u0117\n\27\3\27\3"+
		"\27\3\27\3\27\3\27\3\27\3\27\3\27\5\27\u0121\n\27\3\27\5\27\u0124\n\27"+
		"\3\27\3\27\3\30\3\30\3\30\7\30\u012b\n\30\f\30\16\30\u012e\13\30\3\31"+
		"\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33"+
		"\7\33\u013f\n\33\f\33\16\33\u0142\13\33\3\33\3\33\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\5\34\u014e\n\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\5\35\u015f\n\35\3\35\3\35\3\36"+
		"\3\36\3\37\3\37\3\37\7\37\u0168\n\37\f\37\16\37\u016b\13\37\3 \3 \3!\3"+
		"!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\5(\u017e\n(\3(\3(\7(\u0182"+
		"\n(\f(\16(\u0185\13(\3(\3(\3)\5)\u018a\n)\3)\3)\7)\u018e\n)\f)\16)\u0191"+
		"\13)\3)\3)\3*\3*\3+\3+\5+\u0199\n+\3+\3+\5+\u019d\n+\3+\3+\5+\u01a1\n"+
		"+\3+\2\2,\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\66"+
		"8:<>@BDFHJLNPRT\2\n\3\2\3\4\3\2\24\25\4\2\26\26((\b\2\5\7\t\13\r\16\36"+
		"!##&\'\3\2\27\31\b\2\5\5\t\n\r\16\36!##&\'\3\2:;\3\2,-\2\u01b1\2^\3\2"+
		"\2\2\4h\3\2\2\2\6j\3\2\2\2\bq\3\2\2\2\nu\3\2\2\2\f\u0080\3\2\2\2\16\u008c"+
		"\3\2\2\2\20\u008e\3\2\2\2\22\u0092\3\2\2\2\24\u00a2\3\2\2\2\26\u00a6\3"+
		"\2\2\2\30\u00b1\3\2\2\2\32\u00c6\3\2\2\2\34\u00ca\3\2\2\2\36\u00d7\3\2"+
		"\2\2 \u00f2\3\2\2\2\"\u00f9\3\2\2\2$\u0105\3\2\2\2&\u0107\3\2\2\2(\u0111"+
		"\3\2\2\2*\u0113\3\2\2\2,\u0116\3\2\2\2.\u0127\3\2\2\2\60\u012f\3\2\2\2"+
		"\62\u0135\3\2\2\2\64\u0139\3\2\2\2\66\u0145\3\2\2\28\u0151\3\2\2\2:\u0162"+
		"\3\2\2\2<\u0164\3\2\2\2>\u016c\3\2\2\2@\u016e\3\2\2\2B\u0170\3\2\2\2D"+
		"\u0172\3\2\2\2F\u0174\3\2\2\2H\u0176\3\2\2\2J\u0178\3\2\2\2L\u017a\3\2"+
		"\2\2N\u017d\3\2\2\2P\u0189\3\2\2\2R\u0194\3\2\2\2T\u01a0\3\2\2\2V]\5\4"+
		"\3\2W]\5\6\4\2X]\5\b\5\2Y]\5\n\6\2Z]\5\16\b\2[]\5R*\2\\V\3\2\2\2\\W\3"+
		"\2\2\2\\X\3\2\2\2\\Y\3\2\2\2\\Z\3\2\2\2\\[\3\2\2\2]`\3\2\2\2^\\\3\2\2"+
		"\2^_\3\2\2\2_a\3\2\2\2`^\3\2\2\2ab\7\2\2\3b\3\3\2\2\2cd\7$\2\2de\7>\2"+
		"\2ei\t\2\2\2fg\t\3\2\2gi\7\67\2\2hc\3\2\2\2hf\3\2\2\2i\5\3\2\2\2jl\7\f"+
		"\2\2km\t\4\2\2lk\3\2\2\2lm\3\2\2\2mn\3\2\2\2no\7-\2\2op\7\67\2\2p\7\3"+
		"\2\2\2qr\7\23\2\2rs\5<\37\2st\7\67\2\2t\t\3\2\2\2uv\7\22\2\2vw\5\f\7\2"+
		"wx\7>\2\2xy\5T+\2yz\7\67\2\2z\13\3\2\2\2{\u0081\7)\2\2|}\7/\2\2}~\5<\37"+
		"\2~\177\7\60\2\2\177\u0081\3\2\2\2\u0080{\3\2\2\2\u0080|\3\2\2\2\u0081"+
		"\u0086\3\2\2\2\u0082\u0083\79\2\2\u0083\u0085\7)\2\2\u0084\u0082\3\2\2"+
		"\2\u0085\u0088\3\2\2\2\u0086\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\r"+
		"\3\2\2\2\u0088\u0086\3\2\2\2\u0089\u008d\5\20\t\2\u008a\u008d\5\24\13"+
		"\2\u008b\u008d\5\34\17\2\u008c\u0089\3\2\2\2\u008c\u008a\3\2\2\2\u008c"+
		"\u008b\3\2\2\2\u008d\17\3\2\2\2\u008e\u008f\7\20\2\2\u008f\u0090\5> \2"+
		"\u0090\u0091\5\22\n\2\u0091\21\3\2\2\2\u0092\u009d\7\61\2\2\u0093\u009c"+
		"\5,\27\2\u0094\u009c\5\24\13\2\u0095\u009c\5\20\t\2\u0096\u009c\5\n\6"+
		"\2\u0097\u009c\5\64\33\2\u0098\u009c\58\35\2\u0099\u009c\5 \21\2\u009a"+
		"\u009c\5R*\2\u009b\u0093\3\2\2\2\u009b\u0094\3\2\2\2\u009b\u0095\3\2\2"+
		"\2\u009b\u0096\3\2\2\2\u009b\u0097\3\2\2\2\u009b\u0098\3\2\2\2\u009b\u0099"+
		"\3\2\2\2\u009b\u009a\3\2\2\2\u009c\u009f\3\2\2\2\u009d\u009b\3\2\2\2\u009d"+
		"\u009e\3\2\2\2\u009e\u00a0\3\2\2\2\u009f\u009d\3\2\2\2\u00a0\u00a1\7\62"+
		"\2\2\u00a1\23\3\2\2\2\u00a2\u00a3\7\b\2\2\u00a3\u00a4\5@!\2\u00a4\u00a5"+
		"\5\26\f\2\u00a5\25\3\2\2\2\u00a6\u00ac\7\61\2\2\u00a7\u00ab\5\n\6\2\u00a8"+
		"\u00ab\5\30\r\2\u00a9\u00ab\5R*\2\u00aa\u00a7\3\2\2\2\u00aa\u00a8\3\2"+
		"\2\2\u00aa\u00a9\3\2\2\2\u00ab\u00ae\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ac"+
		"\u00ad\3\2\2\2\u00ad\u00af\3\2\2\2\u00ae\u00ac\3\2\2\2\u00af\u00b0\7\62"+
		"\2\2\u00b0\27\3\2\2\2\u00b1\u00b2\7)\2\2\u00b2\u00b4\7>\2\2\u00b3\u00b5"+
		"\7:\2\2\u00b4\u00b3\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6"+
		"\u00c2\7*\2\2\u00b7\u00b8\7\63\2\2\u00b8\u00bd\5\32\16\2\u00b9\u00ba\7"+
		"8\2\2\u00ba\u00bc\5\32\16\2\u00bb\u00b9\3\2\2\2\u00bc\u00bf\3\2\2\2\u00bd"+
		"\u00bb\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\u00c0\3\2\2\2\u00bf\u00bd\3\2"+
		"\2\2\u00c0\u00c1\7\64\2\2\u00c1\u00c3\3\2\2\2\u00c2\u00b7\3\2\2\2\u00c2"+
		"\u00c3\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c5\7\67\2\2\u00c5\31\3\2\2"+
		"\2\u00c6\u00c7\5\f\7\2\u00c7\u00c8\7>\2\2\u00c8\u00c9\5T+\2\u00c9\33\3"+
		"\2\2\2\u00ca\u00cb\7\35\2\2\u00cb\u00cc\5J&\2\u00cc\u00d2\7\61\2\2\u00cd"+
		"\u00d1\5\n\6\2\u00ce\u00d1\5\36\20\2\u00cf\u00d1\5R*\2\u00d0\u00cd\3\2"+
		"\2\2\u00d0\u00ce\3\2\2\2\u00d0\u00cf\3\2\2\2\u00d1\u00d4\3\2\2\2\u00d2"+
		"\u00d0\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\u00d5\3\2\2\2\u00d4\u00d2\3\2"+
		"\2\2\u00d5\u00d6\7\62\2\2\u00d6\35\3\2\2\2\u00d7\u00d8\7\34\2\2\u00d8"+
		"\u00d9\5L\'\2\u00d9\u00db\7/\2\2\u00da\u00dc\7\"\2\2\u00db\u00da\3\2\2"+
		"\2\u00db\u00dc\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd\u00de\5N(\2\u00de\u00df"+
		"\7\60\2\2\u00df\u00e0\7\33\2\2\u00e0\u00e2\7/\2\2\u00e1\u00e3\7\"\2\2"+
		"\u00e2\u00e1\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e5"+
		"\5N(\2\u00e5\u00f0\7\60\2\2\u00e6\u00eb\7\61\2\2\u00e7\u00ea\5\n\6\2\u00e8"+
		"\u00ea\5R*\2\u00e9\u00e7\3\2\2\2\u00e9\u00e8\3\2\2\2\u00ea\u00ed\3\2\2"+
		"\2\u00eb\u00e9\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00ee\3\2\2\2\u00ed\u00eb"+
		"\3\2\2\2\u00ee\u00f1\7\62\2\2\u00ef\u00f1\7\67\2\2\u00f0\u00e6\3\2\2\2"+
		"\u00f0\u00ef\3\2\2\2\u00f1\37\3\2\2\2\u00f2\u00f5\7\32\2\2\u00f3\u00f6"+
		"\5\"\22\2\u00f4\u00f6\5&\24\2\u00f5\u00f3\3\2\2\2\u00f5\u00f4\3\2\2\2"+
		"\u00f6\u00f7\3\2\2\2\u00f7\u00f8\7\67\2\2\u00f8!\3\2\2\2\u00f9\u00fe\5"+
		"$\23\2\u00fa\u00fb\78\2\2\u00fb\u00fd\5$\23\2\u00fc\u00fa\3\2\2\2\u00fd"+
		"\u0100\3\2\2\2\u00fe\u00fc\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff#\3\2\2\2"+
		"\u0100\u00fe\3\2\2\2\u0101\u0106\7*\2\2\u0102\u0103\7*\2\2\u0103\u0104"+
		"\7%\2\2\u0104\u0106\7*\2\2\u0105\u0101\3\2\2\2\u0105\u0102\3\2\2\2\u0106"+
		"%\3\2\2\2\u0107\u010c\7-\2\2\u0108\u0109\78\2\2\u0109\u010b\7-\2\2\u010a"+
		"\u0108\3\2\2\2\u010b\u010e\3\2\2\2\u010c\u010a\3\2\2\2\u010c\u010d\3\2"+
		"\2\2\u010d\'\3\2\2\2\u010e\u010c\3\2\2\2\u010f\u0112\t\5\2\2\u0110\u0112"+
		"\5P)\2\u0111\u010f\3\2\2\2\u0111\u0110\3\2\2\2\u0112)\3\2\2\2\u0113\u0114"+
		"\7*\2\2\u0114+\3\2\2\2\u0115\u0117\t\6\2\2\u0116\u0115\3\2\2\2\u0116\u0117"+
		"\3\2\2\2\u0117\u0118\3\2\2\2\u0118\u0119\5(\25\2\u0119\u011a\5D#\2\u011a"+
		"\u011b\7>\2\2\u011b\u0120\5*\26\2\u011c\u011d\7\63\2\2\u011d\u011e\5."+
		"\30\2\u011e\u011f\7\64\2\2\u011f\u0121\3\2\2\2\u0120\u011c\3\2\2\2\u0120"+
		"\u0121\3\2\2\2\u0121\u0123\3\2\2\2\u0122\u0124\5\60\31\2\u0123\u0122\3"+
		"\2\2\2\u0123\u0124\3\2\2\2\u0124\u0125\3\2\2\2\u0125\u0126\7\67\2\2\u0126"+
		"-\3\2\2\2\u0127\u012c\5\62\32\2\u0128\u0129\78\2\2\u0129\u012b\5\62\32"+
		"\2\u012a\u0128\3\2\2\2\u012b\u012e\3\2\2\2\u012c\u012a\3\2\2\2\u012c\u012d"+
		"\3\2\2\2\u012d/\3\2\2\2\u012e\u012c\3\2\2\2\u012f\u0130\7<\2\2\u0130\u0131"+
		"\7)\2\2\u0131\u0132\7>\2\2\u0132\u0133\7)\2\2\u0133\u0134\7=\2\2\u0134"+
		"\61\3\2\2\2\u0135\u0136\5\f\7\2\u0136\u0137\7>\2\2\u0137\u0138\5T+\2\u0138"+
		"\63\3\2\2\2\u0139\u013a\7\21\2\2\u013a\u013b\5F$\2\u013b\u0140\7\61\2"+
		"\2\u013c\u013f\5\66\34\2\u013d\u013f\5R*\2\u013e\u013c\3\2\2\2\u013e\u013d"+
		"\3\2\2\2\u013f\u0142\3\2\2\2\u0140\u013e\3\2\2\2\u0140\u0141\3\2\2\2\u0141"+
		"\u0143\3\2\2\2\u0142\u0140\3\2\2\2\u0143\u0144\7\62\2\2\u0144\65\3\2\2"+
		"\2\u0145\u0146\5(\25\2\u0146\u0147\5D#\2\u0147\u0148\7>\2\2\u0148\u014d"+
		"\5*\26\2\u0149\u014a\7\63\2\2\u014a\u014b\5.\30\2\u014b\u014c\7\64\2\2"+
		"\u014c\u014e\3\2\2\2\u014d\u0149\3\2\2\2\u014d\u014e\3\2\2\2\u014e\u014f"+
		"\3\2\2\2\u014f\u0150\7\67\2\2\u0150\67\3\2\2\2\u0151\u0152\7\17\2\2\u0152"+
		"\u0153\7\65\2\2\u0153\u0154\5:\36\2\u0154\u0155\78\2\2\u0155\u0156\5("+
		"\25\2\u0156\u0157\7\66\2\2\u0157\u0158\5H%\2\u0158\u0159\7>\2\2\u0159"+
		"\u015e\5*\26\2\u015a\u015b\7\63\2\2\u015b\u015c\5.\30\2\u015c\u015d\7"+
		"\64\2\2\u015d\u015f\3\2\2\2\u015e\u015a\3\2\2\2\u015e\u015f\3\2\2\2\u015f"+
		"\u0160\3\2\2\2\u0160\u0161\7\67\2\2\u01619\3\2\2\2\u0162\u0163\t\7\2\2"+
		"\u0163;\3\2\2\2\u0164\u0169\7)\2\2\u0165\u0166\79\2\2\u0166\u0168\7)\2"+
		"\2\u0167\u0165\3\2\2\2\u0168\u016b\3\2\2\2\u0169\u0167\3\2\2\2\u0169\u016a"+
		"\3\2\2\2\u016a=\3\2\2\2\u016b\u0169\3\2\2\2\u016c\u016d\7)\2\2\u016d?"+
		"\3\2\2\2\u016e\u016f\7)\2\2\u016fA\3\2\2\2\u0170\u0171\7)\2\2\u0171C\3"+
		"\2\2\2\u0172\u0173\7)\2\2\u0173E\3\2\2\2\u0174\u0175\7)\2\2\u0175G\3\2"+
		"\2\2\u0176\u0177\7)\2\2\u0177I\3\2\2\2\u0178\u0179\7)\2\2\u0179K\3\2\2"+
		"\2\u017a\u017b\7)\2\2\u017bM\3\2\2\2\u017c\u017e\79\2\2\u017d\u017c\3"+
		"\2\2\2\u017d\u017e\3\2\2\2\u017e\u0183\3\2\2\2\u017f\u0180\7)\2\2\u0180"+
		"\u0182\79\2\2\u0181\u017f\3\2\2\2\u0182\u0185\3\2\2\2\u0183\u0181\3\2"+
		"\2\2\u0183\u0184\3\2\2\2\u0184\u0186\3\2\2\2\u0185\u0183\3\2\2\2\u0186"+
		"\u0187\5> \2\u0187O\3\2\2\2\u0188\u018a\79\2\2\u0189\u0188\3\2\2\2\u0189"+
		"\u018a\3\2\2\2\u018a\u018f\3\2\2\2\u018b\u018c\7)\2\2\u018c\u018e\79\2"+
		"\2\u018d\u018b\3\2\2\2\u018e\u0191\3\2\2\2\u018f\u018d\3\2\2\2\u018f\u0190"+
		"\3\2\2\2\u0190\u0192\3\2\2\2\u0191\u018f\3\2\2\2\u0192\u0193\5B\"\2\u0193"+
		"Q\3\2\2\2\u0194\u0195\7\67\2\2\u0195S\3\2\2\2\u0196\u01a1\5<\37\2\u0197"+
		"\u0199\t\b\2\2\u0198\u0197\3\2\2\2\u0198\u0199\3\2\2\2\u0199\u019a\3\2"+
		"\2\2\u019a\u01a1\7*\2\2\u019b\u019d\t\b\2\2\u019c\u019b\3\2\2\2\u019c"+
		"\u019d\3\2\2\2\u019d\u019e\3\2\2\2\u019e\u01a1\7+\2\2\u019f\u01a1\t\t"+
		"\2\2\u01a0\u0196\3\2\2\2\u01a0\u0198\3\2\2\2\u01a0\u019c\3\2\2\2\u01a0"+
		"\u019f\3\2\2\2\u01a1U\3\2\2\2,\\^hl\u0080\u0086\u008c\u009b\u009d\u00aa"+
		"\u00ac\u00b4\u00bd\u00c2\u00d0\u00d2\u00db\u00e2\u00e9\u00eb\u00f0\u00f5"+
		"\u00fe\u0105\u010c\u0111\u0116\u0120\u0123\u012c\u013e\u0140\u014d\u015e"+
		"\u0169\u017d\u0183\u0189\u018f\u0198\u019c\u01a0";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}