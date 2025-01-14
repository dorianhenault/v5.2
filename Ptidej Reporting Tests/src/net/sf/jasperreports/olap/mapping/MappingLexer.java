// $ANTLR 2.7.5 (20050128): "mapping.g" -> "MappingLexer.java"$

/*
 * ============================================================================
 * GNU Lesser General Public License
 * ============================================================================
 *
 * JasperReports - Free Java report-generating library.
 * Copyright (C) 2001-2006 JasperSoft Corporation http://www.jaspersoft.com
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 * 
 * JasperSoft Corporation
 * 303 Second Street, Suite 450 North
 * San Francisco, CA 94107
 * http://www.jaspersoft.com
 */
package net.sf.jasperreports.olap.mapping;

import java.io.InputStream;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.TokenStreamRecognitionException;
import antlr.CharStreamException;
import antlr.CharStreamIOException;
import java.io.Reader;
import java.util.Hashtable;
import antlr.InputBuffer;
import antlr.ByteBuffer;
import antlr.CharBuffer;
import antlr.Token;
import antlr.RecognitionException;
import antlr.NoViableAltForCharException;
import antlr.TokenStream;
import antlr.ANTLRHashString;
import antlr.LexerSharedInputState;
import antlr.collections.impl.BitSet;

/**
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: MappingLexer.java,v 1.1 2008/09/29 16:21:01 guehene Exp $
 */
public class MappingLexer extends antlr.CharScanner implements MappingParserTokenTypes, TokenStream
 {
public MappingLexer(InputStream in) {
	this(new ByteBuffer(in));
}
public MappingLexer(Reader in) {
	this(new CharBuffer(in));
}
public MappingLexer(InputBuffer ib) {
	this(new LexerSharedInputState(ib));
}
public MappingLexer(LexerSharedInputState state) {
	super(state);
	this.caseSensitiveLiterals = true;
	setCaseSensitive(false);
	this.literals = new Hashtable();
	this.literals.put(new ANTLRHashString("Columns", this), new Integer(7));
	this.literals.put(new ANTLRHashString("Data", this), new Integer(12));
	this.literals.put(new ANTLRHashString("Chapters", this), new Integer(10));
	this.literals.put(new ANTLRHashString("Rows", this), new Integer(8));
	this.literals.put(new ANTLRHashString("Sections", this), new Integer(11));
	this.literals.put(new ANTLRHashString("FormattedData", this), new Integer(13));
	this.literals.put(new ANTLRHashString("Axis", this), new Integer(4));
	this.literals.put(new ANTLRHashString("Pages", this), new Integer(9));
}

public Token nextToken() throws TokenStreamException {
	Token theRetToken=null;
tryAgain:
	for (;;) {
		Token _token = null;
		int _ttype = Token.INVALID_TYPE;
		resetText();
		try {   // for char stream error handling
			try {   // for lexical error handling
				switch ( LA(1)) {
				case '.':
				{
					mPOINT(true);
					theRetToken=this._returnToken;
					break;
				}
				case '(':
				{
					mLPAREN(true);
					theRetToken=this._returnToken;
					break;
				}
				case ')':
				{
					mRPAREN(true);
					theRetToken=this._returnToken;
					break;
				}
				case '+':
				{
					mPLUS(true);
					theRetToken=this._returnToken;
					break;
				}
				case '-':
				{
					mMINUS(true);
					theRetToken=this._returnToken;
					break;
				}
				case '*':
				{
					mSTAR(true);
					theRetToken=this._returnToken;
					break;
				}
				case '?':
				{
					mQMARK(true);
					theRetToken=this._returnToken;
					break;
				}
				case ',':
				{
					mCOMMA(true);
					theRetToken=this._returnToken;
					break;
				}
				case '0':  case '1':  case '2':  case '3':
				case '4':  case '5':  case '6':  case '7':
				case '8':  case '9':
				{
					mINT(true);
					theRetToken=this._returnToken;
					break;
				}
				case '\t':  case '\n':  case '\r':  case ' ':
				{
					mWS(true);
					theRetToken=this._returnToken;
					break;
				}
				default:
					if ((LA(1)=='[') && (_tokenSet_0.member(LA(2)))) {
						mMONDRNAME(true);
						theRetToken=this._returnToken;
					}
					else if ((LA(1)=='[') && (LA(2)=='#')) {
						mMONDRIDX(true);
						theRetToken=this._returnToken;
					}
					else if ((_tokenSet_1.member(LA(1)))) {
						mNAME(true);
						theRetToken=this._returnToken;
					}
				else {
					if (LA(1)==EOF_CHAR) {uponEOF(); this._returnToken = makeToken(Token.EOF_TYPE);}
				else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
				}
				}
				if ( this._returnToken==null ) continue tryAgain; // found SKIP token
				_ttype = this._returnToken.getType();
				_ttype = testLiteralsTable(_ttype);
				this._returnToken.setType(_ttype);
				return this._returnToken;
			}
			catch (RecognitionException e) {
				throw new TokenStreamRecognitionException(e);
			}
		}
		catch (CharStreamException cse) {
			if ( cse instanceof CharStreamIOException ) {
				throw new TokenStreamIOException(((CharStreamIOException)cse).io);
			}
			else {
				throw new TokenStreamException(cse.getMessage());
			}
		}
	}
}

	public final void mPOINT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=this.text.length();
		_ttype = POINT;
		int _saveIndex;
		
		match('.');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(this.text.getBuffer(), _begin, this.text.length()-_begin));
		}
		this._returnToken = _token;
	}
	
	public final void mLPAREN(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=this.text.length();
		_ttype = LPAREN;
		int _saveIndex;
		
		match('(');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(this.text.getBuffer(), _begin, this.text.length()-_begin));
		}
		this._returnToken = _token;
	}
	
	public final void mRPAREN(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=this.text.length();
		_ttype = RPAREN;
		int _saveIndex;
		
		match(')');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(this.text.getBuffer(), _begin, this.text.length()-_begin));
		}
		this._returnToken = _token;
	}
	
	public final void mPLUS(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=this.text.length();
		_ttype = PLUS;
		int _saveIndex;
		
		match('+');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(this.text.getBuffer(), _begin, this.text.length()-_begin));
		}
		this._returnToken = _token;
	}
	
	public final void mMINUS(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=this.text.length();
		_ttype = MINUS;
		int _saveIndex;
		
		match('-');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(this.text.getBuffer(), _begin, this.text.length()-_begin));
		}
		this._returnToken = _token;
	}
	
	public final void mSTAR(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=this.text.length();
		_ttype = STAR;
		int _saveIndex;
		
		match('*');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(this.text.getBuffer(), _begin, this.text.length()-_begin));
		}
		this._returnToken = _token;
	}
	
	public final void mQMARK(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=this.text.length();
		_ttype = QMARK;
		int _saveIndex;
		
		match('?');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(this.text.getBuffer(), _begin, this.text.length()-_begin));
		}
		this._returnToken = _token;
	}
	
	public final void mCOMMA(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=this.text.length();
		_ttype = COMMA;
		int _saveIndex;
		
		match(',');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(this.text.getBuffer(), _begin, this.text.length()-_begin));
		}
		this._returnToken = _token;
	}
	
	public final void mINT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=this.text.length();
		_ttype = INT;
		int _saveIndex;
		
		{
		int _cnt53=0;
		_loop53:
		do {
			if (((LA(1) >= '0' && LA(1) <= '9'))) {
				mDIGIT(false);
			}
			else {
				if ( _cnt53>=1 ) { break _loop53; } else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
			}
			
			_cnt53++;
		} while (true);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(this.text.getBuffer(), _begin, this.text.length()-_begin));
		}
		this._returnToken = _token;
	}
	
	protected final void mDIGIT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=this.text.length();
		_ttype = DIGIT;
		int _saveIndex;
		
		matchRange('0','9');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(this.text.getBuffer(), _begin, this.text.length()-_begin));
		}
		this._returnToken = _token;
	}
	
	public final void mMONDRNAME(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=this.text.length();
		_ttype = MONDRNAME;
		int _saveIndex;
		
		match('[');
		matchNot('#');
		{
		_loop57:
		do {
			if ((_tokenSet_2.member(LA(1)))) {
				{
				match(_tokenSet_2);
				}
			}
			else {
				break _loop57;
			}
			
		} while (true);
		}
		match(']');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(this.text.getBuffer(), _begin, this.text.length()-_begin));
		}
		this._returnToken = _token;
	}
	
	public final void mMONDRIDX(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=this.text.length();
		_ttype = MONDRIDX;
		int _saveIndex;
		
		match("[#");
		{
		int _cnt60=0;
		_loop60:
		do {
			if (((LA(1) >= '0' && LA(1) <= '9'))) {
				mDIGIT(false);
			}
			else {
				if ( _cnt60>=1 ) { break _loop60; } else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
			}
			
			_cnt60++;
		} while (true);
		}
		match(']');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(this.text.getBuffer(), _begin, this.text.length()-_begin));
		}
		this._returnToken = _token;
	}
	
	public final void mNAME(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=this.text.length();
		_ttype = NAME;
		int _saveIndex;
		
		mLETTER(false);
		{
		_loop63:
		do {
			switch ( LA(1)) {
			case '0':  case '1':  case '2':  case '3':
			case '4':  case '5':  case '6':  case '7':
			case '8':  case '9':
			{
				mDIGIT(false);
				break;
			}
			case ' ':
			{
				match(' ');
				break;
			}
			default:
				if ((_tokenSet_1.member(LA(1)))) {
					mLETTER(false);
				}
			else {
				break _loop63;
			}
			}
		} while (true);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(this.text.getBuffer(), _begin, this.text.length()-_begin));
		}
		this._returnToken = _token;
	}
	
	protected final void mLETTER(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=this.text.length();
		_ttype = LETTER;
		int _saveIndex;
		
		if (((LA(1) >= 'a' && LA(1) <= 'z'))) {
			matchRange('a','z');
		}
		else if (((LA(1) >= '\u0080' && LA(1) <= '\ufffe'))) {
			matchRange('\u0080','\ufffe');
		}
		else {
			throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
		}
		
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(this.text.getBuffer(), _begin, this.text.length()-_begin));
		}
		this._returnToken = _token;
	}
	
	public final void mWS(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=this.text.length();
		_ttype = WS;
		int _saveIndex;
		
		{
		switch ( LA(1)) {
		case ' ':
		{
			match(' ');
			break;
		}
		case '\r':
		{
			match('\r');
			match('\n');
			break;
		}
		case '\n':
		{
			match('\n');
			break;
		}
		case '\t':
		{
			match('\t');
			break;
		}
		default:
		{
			throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
		}
		}
		}
		_ttype = Token.SKIP;
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(this.text.getBuffer(), _begin, this.text.length()-_begin));
		}
		this._returnToken = _token;
	}
	
	protected final void mMONDRCH(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=this.text.length();
		_ttype = MONDRCH;
		int _saveIndex;
		
		if ((_tokenSet_1.member(LA(1)))) {
			mLETTER(false);
		}
		else if ((LA(1)==' ')) {
			match(' ');
		}
		else {
			throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
		}
		
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(this.text.getBuffer(), _begin, this.text.length()-_begin));
		}
		this._returnToken = _token;
	}
	
	
	private static final long[] mk_tokenSet_0() {
		long[] data = new long[2048];
		data[0]=-34359738369L;
		for (int i = 1; i<=1022; i++) { data[i]=-1L; }
		data[1023]=9223372036854775807L;
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = new long[3072];
		data[1]=576460743713488896L;
		for (int i = 2; i<=1022; i++) { data[i]=-1L; }
		data[1023]=9223372036854775807L;
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = new long[2048];
		data[0]=-1L;
		data[1]=-536870913L;
		for (int i = 2; i<=1022; i++) { data[i]=-1L; }
		data[1023]=9223372036854775807L;
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	
	}
