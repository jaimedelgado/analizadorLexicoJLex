package alex;


class AnalizadorLexicoTiny {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 65536;
	private final int YY_EOF = 65537;

  private ALexOperations ops;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	AnalizadorLexicoTiny (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	AnalizadorLexicoTiny (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private AnalizadorLexicoTiny () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;

  ops = new ALexOperations(this);
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NOT_ACCEPT,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NOT_ACCEPT,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NOT_ACCEPT,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NOT_ACCEPT,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NOT_ACCEPT,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NOT_ACCEPT,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NOT_ACCEPT,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NOT_ACCEPT,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NOT_ACCEPT,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NOT_ACCEPT,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NOT_ACCEPT,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NOT_ACCEPT,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NO_ANCHOR,
		/* 101 */ YY_NO_ANCHOR,
		/* 102 */ YY_NO_ANCHOR,
		/* 103 */ YY_NO_ANCHOR,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NO_ANCHOR,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NO_ANCHOR,
		/* 109 */ YY_NO_ANCHOR,
		/* 110 */ YY_NO_ANCHOR,
		/* 111 */ YY_NO_ANCHOR,
		/* 112 */ YY_NO_ANCHOR,
		/* 113 */ YY_NO_ANCHOR,
		/* 114 */ YY_NO_ANCHOR,
		/* 115 */ YY_NO_ANCHOR,
		/* 116 */ YY_NO_ANCHOR,
		/* 117 */ YY_NO_ANCHOR,
		/* 118 */ YY_NO_ANCHOR,
		/* 119 */ YY_NO_ANCHOR,
		/* 120 */ YY_NO_ANCHOR,
		/* 121 */ YY_NO_ANCHOR,
		/* 122 */ YY_NO_ANCHOR,
		/* 123 */ YY_NO_ANCHOR,
		/* 124 */ YY_NO_ANCHOR,
		/* 125 */ YY_NO_ANCHOR,
		/* 126 */ YY_NO_ANCHOR,
		/* 127 */ YY_NO_ANCHOR,
		/* 128 */ YY_NO_ANCHOR,
		/* 129 */ YY_NO_ANCHOR,
		/* 130 */ YY_NO_ANCHOR,
		/* 131 */ YY_NO_ANCHOR,
		/* 132 */ YY_NO_ANCHOR,
		/* 133 */ YY_NO_ANCHOR,
		/* 134 */ YY_NO_ANCHOR,
		/* 135 */ YY_NO_ANCHOR,
		/* 136 */ YY_NO_ANCHOR,
		/* 137 */ YY_NO_ANCHOR,
		/* 138 */ YY_NO_ANCHOR,
		/* 139 */ YY_NO_ANCHOR,
		/* 140 */ YY_NO_ANCHOR,
		/* 141 */ YY_NO_ANCHOR,
		/* 142 */ YY_NO_ANCHOR,
		/* 143 */ YY_NO_ANCHOR,
		/* 144 */ YY_NO_ANCHOR,
		/* 145 */ YY_NO_ANCHOR,
		/* 146 */ YY_NO_ANCHOR,
		/* 147 */ YY_NO_ANCHOR,
		/* 148 */ YY_NO_ANCHOR,
		/* 149 */ YY_NO_ANCHOR,
		/* 150 */ YY_NO_ANCHOR,
		/* 151 */ YY_NO_ANCHOR,
		/* 152 */ YY_NO_ANCHOR,
		/* 153 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,65538,
"3:8,4:2,1,3:2,4,3:18,4,43,3,32,3,48,36,3,37,38,46,45,49,31,29,47,28,27:9,3," +
"33,40,42,41,3,2,25:4,30,25:21,34,3,35,39,25,3,12,16,14,15,11,20,26,23,6,17," +
"26,13,22,9,8,7,26,10,19,5,21,26,24,18,26:2,3,44,3:65411,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,154,
"0,1:2,2,3,4,1:6,5,6,1:2,7,8,9,10,1:6,11,12:2,1:6,12:3,13,12,14,12:12,1,12,1" +
"5,12:2,1,12:5,16,1,17,18,1,19,20,21,22,23,24,25,26,27,28,29,30,31,32,16,33," +
"34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58," +
"59,60,61,12,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82," +
"83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,12,98,99,100")[0];

	private int yy_nxt[][] = unpackFromString(101,50,
"1,2,3,65,2,4,66,143,107,148,108,109,149,150:2,71,150:3,151,110,150,152,150," +
"153,150:2,5,67,6,150,7,8,9,10,11,12,13,14,15,16,17,18,19,70,20,21,22,23,24," +
"-1:52,25:48,-1:5,150,111,150:16,112,150,113,150,113:2,-1,113,-1:30,64,-1:15" +
",5:2,69,64,-1:55,29,-1:19,73,-1:3,75,-1:81,30,-1:49,31,-1:49,32,-1:49,33,-1" +
":12,35,150:19,113,150,113:2,-1,113,-1:24,150:20,113,150,113:2,-1,113,-1:24," +
"150,94,150,132,150,133,150:13,147,113,150,113:2,-1,113,-1:46,40:2,-1:26,150" +
":14,60,150:5,113,150,113:2,-1,113,-1:46,40,68,-1:2,77,-1:23,150:4,26,150:10" +
",27,150:4,113,150,113:2,-1,113,-1:30,64,-1:17,69,64,-1:46,72:2,-1:65,34,-1:" +
"10,150:3,28,150:16,113,150,113:2,-1,113,-1:30,83,-1:15,72,85,-1,83,-1:28,79" +
",-1:45,36,150:19,113,150,113:2,-1,113,-1:30,81,-1:43,124,150:6,90,150,37,15" +
"0:10,113,150,113:2,-1,113,-1:46,40,68,-1:26,150:10,38,150:9,113,150,113:2,-" +
"1,113,-1:24,87,-1:49,150:4,39,150:15,113,150,113:2,-1,113,-1:31,89,-1:42,15" +
"0:3,41,150:16,113,150,113:2,-1,113,-1:24,150:14,42,150:5,113,150,113:2,-1,1" +
"13,-1:46,72,85,-1:26,150:4,43,150:15,113,150,113:2,-1,113,-1:57,53,-1:16,15" +
"0:8,44,150:11,113,150,113:2,-1,113,-1:32,91,-1:41,150:8,45,150:11,113,150,1" +
"13:2,-1,113,-1:57,58,-1:16,150,95,150:4,46,150:13,113,150,113:2,-1,113,-1:2" +
"4,150:6,47,150:13,113,150,113:2,-1,113,-1:24,150:15,48,150:4,113,150,113:2," +
"-1,113,-1:24,150:15,49,150:4,113,150,113:2,-1,113,-1:24,150:9,50,150:10,113" +
",150,113:2,-1,113,-1:24,150:5,51,150:14,113,150,113:2,-1,113,-1:24,150:6,52" +
",150:13,113,150,113:2,-1,113,-1:24,54,150:19,113,150,113:2,-1,113,-1:24,150" +
":4,55,150:15,113,150,113:2,-1,113,-1:24,150:9,56,150:10,113,150,113:2,-1,11" +
"3,-1:24,150:10,57,150:9,113,150,113:2,-1,113,-1:24,150:5,59,150:14,113,150," +
"113:2,-1,113,-1:24,150:14,61,150:5,113,150,113:2,-1,113,-1:24,150:6,62,150:" +
"13,113,150,113:2,-1,113,-1:24,63,150:19,113,150,113:2,-1,113,-1:24,150:11,1" +
"15,150:4,74,150:3,113,150,113:2,-1,113,-1:24,150:6,76,150:13,113,150,113:2," +
"-1,113,-1:24,150:4,78,150:3,117,150:4,118,150:6,113,150,113:2,-1,113,-1:24," +
"150:5,121,150:10,80,150:3,113,150,113:2,-1,113,-1:24,150:2,82,150:17,113,15" +
"0,113:2,-1,113,-1:24,150,84,150:4,86,150:13,113,150,113:2,-1,113,-1:24,150," +
"122,150:18,113,150,113:2,-1,113,-1:24,150:12,123,150:7,113,150,113:2,-1,113" +
",-1:24,150:8,88,150:11,113,150,113:2,-1,113,-1:24,150:14,92,150:5,113,150,1" +
"13:2,-1,113,-1:24,146,150:19,113,150,113:2,-1,113,-1:24,150:8,125,150:11,11" +
"3,150,113:2,-1,113,-1:24,150:2,126,150:17,113,150,113:2,-1,113,-1:24,150:6," +
"93,150:13,113,150,113:2,-1,113,-1:24,150:4,129,150:15,113,150,113:2,-1,113," +
"-1:24,150:6,130,150:13,113,150,113:2,-1,113,-1:24,150:16,131,150:3,113,150," +
"113:2,-1,113,-1:24,150:3,96,150:16,113,150,113:2,-1,113,-1:24,150:6,97,150:" +
"13,113,150,113:2,-1,113,-1:24,150:18,135,150,113,150,113:2,-1,113,-1:24,150" +
":8,98,150:11,113,150,113:2,-1,113,-1:24,136,150:19,113,150,113:2,-1,113,-1:" +
"24,150:9,99,150:10,113,150,113:2,-1,113,-1:24,150:5,100,150:14,113,150,113:" +
"2,-1,113,-1:24,150:11,137,150:8,113,150,113:2,-1,113,-1:24,150:6,101,150:13" +
",113,150,113:2,-1,113,-1:24,150:4,139,150:15,113,150,113:2,-1,113,-1:24,150" +
":3,102,150:16,113,150,113:2,-1,113,-1:24,150:6,103,150:13,113,150,113:2,-1," +
"113,-1:24,150:12,140,150:7,113,150,113:2,-1,113,-1:24,150,141,150:18,113,15" +
"0,113:2,-1,113,-1:24,150:10,104,150:9,113,150,113:2,-1,113,-1:24,150:6,142," +
"150:13,113,150,113:2,-1,113,-1:24,150:8,105,150:11,113,150,113:2,-1,113,-1:" +
"24,150:9,106,150:10,113,150,113:2,-1,113,-1:24,150:3,114,150:16,113,150,113" +
":2,-1,113,-1:24,150,128,150:18,113,150,113:2,-1,113,-1:24,127,150:19,113,15" +
"0,113:2,-1,113,-1:24,150:6,134,150:13,113,150,113:2,-1,113,-1:24,150:18,138" +
",150,113,150,113:2,-1,113,-1:24,150:16,116,150:3,113,150,113:2,-1,113,-1:24" +
",150:8,119,150:11,113,150,113:2,-1,113,-1:24,150:16,120,150:3,113,150,113:2" +
",-1,113,-1:24,150:6,145,150:13,113,150,113:2,-1,113,-1:24,150:18,144,150,11" +
"3,150,113:2,-1,113,-1:19");

	public UnidadLexica yylex ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

  return ops.unidadEof();
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{}
					case -3:
						break;
					case 3:
						{ops.error();}
					case -4:
						break;
					case 4:
						{return ops.unidadId();}
					case -5:
						break;
					case 5:
						{return ops.unidadNumEntero();}
					case -6:
						break;
					case 6:
						{return ops.unidadPunto();}
					case -7:
						break;
					case 7:
						{return ops.unidadMenos();}
					case -8:
						break;
					case 8:
						{return ops.unidadSep();}
					case -9:
						break;
					case 9:
						{return ops.unidadPycoma();}
					case -10:
						break;
					case 10:
						{return ops.unidadCapertura();}
					case -11:
						break;
					case 11:
						{return ops.unidadCcierre();}
					case -12:
						break;
					case 12:
						{return ops.unidadReferencia();}
					case -13:
						break;
					case 13:
						{return ops.unidadPap();}
					case -14:
						break;
					case 14:
						{return ops.unidadPcierre();}
					case -15:
						break;
					case 15:
						{return ops.unidadApostrofe();}
					case -16:
						break;
					case 16:
						{return ops.unidadMenor();}
					case -17:
						break;
					case 17:
						{return ops.unidadMayor();}
					case -18:
						break;
					case 18:
						{return ops.unidadAsig();}
					case -19:
						break;
					case 19:
						{return ops.unidadNot();}
					case -20:
						break;
					case 20:
						{return ops.unidadMas();}
					case -21:
						break;
					case 21:
						{return ops.unidadPor();}
					case -22:
						break;
					case 22:
						{return ops.unidadDiv();}
					case -23:
						break;
					case 23:
						{return ops.unidadMod();}
					case -24:
						break;
					case 24:
						{return ops.unidadComa();}
					case -25:
						break;
					case 25:
						{}
					case -26:
						break;
					case 26:
						{return ops.unidadIn();}
					case -27:
						break;
					case 27:
						{return ops.unidadIf();}
					case -28:
						break;
					case 28:
						{return ops.unidadDo();}
					case -29:
						break;
					case 29:
						{return ops.unidadAnd();}
					case -30:
						break;
					case 30:
						{return ops.unidadMenorIgual();}
					case -31:
						break;
					case 31:
						{return ops.unidadMayorIgual();}
					case -32:
						break;
					case 32:
						{return ops.unidadIgual();}
					case -33:
						break;
					case 33:
						{return ops.unidadDistinto();}
					case -34:
						break;
					case 34:
						{return ops.unidadOr();}
					case -35:
						break;
					case 35:
						{return ops.unidadInt();}
					case -36:
						break;
					case 36:
						{return ops.unidadOut();}
					case -37:
						break;
					case 37:
						{return ops.unidadRec();}
					case -38:
						break;
					case 38:
						{return ops.unidadEnd();}
					case -39:
						break;
					case 39:
						{return ops.unidadFun();}
					case -40:
						break;
					case 40:
						{return ops.unidadNumReal();}
					case -41:
						break;
					case 41:
						{return ops.unidadTipo();}
					case -42:
						break;
					case 42:
						{return ops.unidadThis();}
					case -43:
						break;
					case 43:
						{return ops.unidadThen();}
					case -44:
						break;
					case 44:
						{return ops.unidadNull();}
					case -45:
						break;
					case 45:
						{return ops.unidadReal();}
					case -46:
						break;
					case 46:
						{return ops.unidadElse();}
					case -47:
						break;
					case 47:
						{return ops.unidadFree();}
					case -48:
						break;
					case 48:
						{return ops.unidadEndif();}
					case -49:
						break;
					case 49:
						{return ops.unidadElsif();}
					case -50:
						break;
					case 50:
						{return ops.unidadAlloc();}
					case -51:
						break;
					case 51:
						{return ops.unidadSuper();}
					case -52:
						break;
					case 52:
						{return ops.unidadWhile();}
					case -53:
						break;
					case 53:
						{return ops.unidadCastInt();}
					case -54:
						break;
					case 54:
						{return ops.unidadObject();}
					case -55:
						break;
					case 55:
						{return ops.unidadReturn();}
					case -56:
						break;
					case 56:
						{return ops.unidadEndrec();}
					case -57:
						break;
					case 57:
						{return ops.unidadMethod();}
					case -58:
						break;
					case 58:
						{return ops.unidadCastReal();}
					case -59:
						break;
					case 59:
						{return ops.unidadPointer();}
					case -60:
						break;
					case 60:
						{return ops.unidadReturns();}
					case -61:
						break;
					case 61:
						{return ops.unidadExtends();}
					case -62:
						break;
					case 62:
						{return ops.unidadEndwhile();}
					case -63:
						break;
					case 63:
						{return ops.unidadEndobject();}
					case -64:
						break;
					case 65:
						{ops.error();}
					case -65:
						break;
					case 66:
						{return ops.unidadId();}
					case -66:
						break;
					case 67:
						{return ops.unidadNumEntero();}
					case -67:
						break;
					case 68:
						{return ops.unidadNumReal();}
					case -68:
						break;
					case 70:
						{ops.error();}
					case -69:
						break;
					case 71:
						{return ops.unidadId();}
					case -70:
						break;
					case 72:
						{return ops.unidadNumReal();}
					case -71:
						break;
					case 74:
						{return ops.unidadId();}
					case -72:
						break;
					case 76:
						{return ops.unidadId();}
					case -73:
						break;
					case 78:
						{return ops.unidadId();}
					case -74:
						break;
					case 80:
						{return ops.unidadId();}
					case -75:
						break;
					case 82:
						{return ops.unidadId();}
					case -76:
						break;
					case 84:
						{return ops.unidadId();}
					case -77:
						break;
					case 86:
						{return ops.unidadId();}
					case -78:
						break;
					case 88:
						{return ops.unidadId();}
					case -79:
						break;
					case 90:
						{return ops.unidadId();}
					case -80:
						break;
					case 92:
						{return ops.unidadId();}
					case -81:
						break;
					case 93:
						{return ops.unidadId();}
					case -82:
						break;
					case 94:
						{return ops.unidadId();}
					case -83:
						break;
					case 95:
						{return ops.unidadId();}
					case -84:
						break;
					case 96:
						{return ops.unidadId();}
					case -85:
						break;
					case 97:
						{return ops.unidadId();}
					case -86:
						break;
					case 98:
						{return ops.unidadId();}
					case -87:
						break;
					case 99:
						{return ops.unidadId();}
					case -88:
						break;
					case 100:
						{return ops.unidadId();}
					case -89:
						break;
					case 101:
						{return ops.unidadId();}
					case -90:
						break;
					case 102:
						{return ops.unidadId();}
					case -91:
						break;
					case 103:
						{return ops.unidadId();}
					case -92:
						break;
					case 104:
						{return ops.unidadId();}
					case -93:
						break;
					case 105:
						{return ops.unidadId();}
					case -94:
						break;
					case 106:
						{return ops.unidadId();}
					case -95:
						break;
					case 107:
						{return ops.unidadId();}
					case -96:
						break;
					case 108:
						{return ops.unidadId();}
					case -97:
						break;
					case 109:
						{return ops.unidadId();}
					case -98:
						break;
					case 110:
						{return ops.unidadId();}
					case -99:
						break;
					case 111:
						{return ops.unidadId();}
					case -100:
						break;
					case 112:
						{return ops.unidadId();}
					case -101:
						break;
					case 113:
						{return ops.unidadId();}
					case -102:
						break;
					case 114:
						{return ops.unidadId();}
					case -103:
						break;
					case 115:
						{return ops.unidadId();}
					case -104:
						break;
					case 116:
						{return ops.unidadId();}
					case -105:
						break;
					case 117:
						{return ops.unidadId();}
					case -106:
						break;
					case 118:
						{return ops.unidadId();}
					case -107:
						break;
					case 119:
						{return ops.unidadId();}
					case -108:
						break;
					case 120:
						{return ops.unidadId();}
					case -109:
						break;
					case 121:
						{return ops.unidadId();}
					case -110:
						break;
					case 122:
						{return ops.unidadId();}
					case -111:
						break;
					case 123:
						{return ops.unidadId();}
					case -112:
						break;
					case 124:
						{return ops.unidadId();}
					case -113:
						break;
					case 125:
						{return ops.unidadId();}
					case -114:
						break;
					case 126:
						{return ops.unidadId();}
					case -115:
						break;
					case 127:
						{return ops.unidadId();}
					case -116:
						break;
					case 128:
						{return ops.unidadId();}
					case -117:
						break;
					case 129:
						{return ops.unidadId();}
					case -118:
						break;
					case 130:
						{return ops.unidadId();}
					case -119:
						break;
					case 131:
						{return ops.unidadId();}
					case -120:
						break;
					case 132:
						{return ops.unidadId();}
					case -121:
						break;
					case 133:
						{return ops.unidadId();}
					case -122:
						break;
					case 134:
						{return ops.unidadId();}
					case -123:
						break;
					case 135:
						{return ops.unidadId();}
					case -124:
						break;
					case 136:
						{return ops.unidadId();}
					case -125:
						break;
					case 137:
						{return ops.unidadId();}
					case -126:
						break;
					case 138:
						{return ops.unidadId();}
					case -127:
						break;
					case 139:
						{return ops.unidadId();}
					case -128:
						break;
					case 140:
						{return ops.unidadId();}
					case -129:
						break;
					case 141:
						{return ops.unidadId();}
					case -130:
						break;
					case 142:
						{return ops.unidadId();}
					case -131:
						break;
					case 143:
						{return ops.unidadId();}
					case -132:
						break;
					case 144:
						{return ops.unidadId();}
					case -133:
						break;
					case 145:
						{return ops.unidadId();}
					case -134:
						break;
					case 146:
						{return ops.unidadId();}
					case -135:
						break;
					case 147:
						{return ops.unidadId();}
					case -136:
						break;
					case 148:
						{return ops.unidadId();}
					case -137:
						break;
					case 149:
						{return ops.unidadId();}
					case -138:
						break;
					case 150:
						{return ops.unidadId();}
					case -139:
						break;
					case 151:
						{return ops.unidadId();}
					case -140:
						break;
					case 152:
						{return ops.unidadId();}
					case -141:
						break;
					case 153:
						{return ops.unidadId();}
					case -142:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
