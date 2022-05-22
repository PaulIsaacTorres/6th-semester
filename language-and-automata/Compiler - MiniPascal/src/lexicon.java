import java.io.RandomAccessFile;

public class lexicon {
    public static Node head = null, p;
    int state = 0, column, valueTM, line = 1;
    int character = 0;
    String lexeme = "";
    boolean errorFound = false;

    String textEditor = "C:/Users/PaulI/Desktop/schoolar-projects/language-and-automata/Compiler - MiniPascal/src/miniPascalCode.txt";

    int matrix[][] = {
                    //   l       d      +      -      *      /      >      <      =      :      (      )      .      ,      ;      '      "     oc     eb    tab    ent   eof     nl
                    // [0]     [1]    [2]    [3]    [4]    [5]    [6]    [7]    [8]    [9]   [10]   [11]   [12]   [13]   [14]   [15]   [16]   [17]   [18]   [19]   [20]  [21]   [22]
            /* q00 */{   1,     1,   103,   104,   105,     5,     8,     9,    10,    11,   114,   115,   116,   117,   118,    12,    14,   503,   122,   123,   124,   125,   126}, // [0]
            /* q01 */{   1,     1,   100,   100,   100,   100,   100,   100,   100,   100,   100,   100,   100,   100,   100,   100,   100,   100,   100,   100,   100,   100,   100}, // [1]
            /* q02 */{ 101,     2,   101,   101,   101,   101,   101,   101,   101,   101,   101,   101,     3,   101,   101,   101,   101,   101,   101,   101,   101,   101,   101}, // [2]
            /* q03 */{ 500,     4,   500,   500,   500,   500,   500,   500,   500,   500,   500,   500,   500,   500,   500,   500,   500,   500,   500,   500,   500,   500,   500}, // [3]
            /* q04 */{ 102,     4,   102,   102,   102,   102,   102,   102,   102,   102,   102,   102,   102,   102,   102,   102,   102,   102,   102,   102,   102,   102,   102}, // [4]
            /* q05 */{ 106,   106,   106,   106,     6,   106,   106,   106,   106,   106,   106,   106,   106,   106,   106,   106,   106,   106,   106,   106,   106,   106,   106}, // [5]
            /* q06 */{   6,     6,     6,     6,     7,     6,     6,     6,     6,     6,     6,     6,     6,     6,     6,     6,     6,     6,     6,     6,     6,     6,     6}, // [6]
            /* q07 */{ 504,   504,   504,   504,   504,     0,   504,   504,   504,   504,   504,   504,   504,   504,   504,   504,   504,   504,   504,   504,   504,   504,   504}, // [7]
            /* q08 */{ 108,   108,   108,   108,   108,   108,   108,   108,   110,   108,   108,   108,   108,   108,   108,   108,   108,   108,   108,   108,   108,   108,   108}, // [8]
            /* q09 */{ 107,   107,   107,   107,   107,   107,   111,   107,   109,   107,   107,   107,   107,   107,   107,   107,   107,   107,   107,   107,   107,   107,   107}, // [9]
            /* q10 */{ 501,   501,   501,   501,   501,   501,   501,   501,   112,   501,   501,   501,   501,   501,   501,   501,   501,   501,   501,   501,   501,   501,   501}, // [10]
            /* q11 */{ 119,   119,   119,   119,   119,   119,   119,   119,   113,   119,   119,   119,   119,   119,   119,   119,   119,   119,   119,   119,   119,   119,   119}, // [11]
            /* q12 */{  13,    13,    13,    13,    13,    13,    13,    13,    13,    13,    13,    13,    13,    13,    13,    13,    13,    13,    13,    13,    13,    13,    13}, // [12]
            /* q13 */{ 502,   502,   502,   502,   502,   502,   502,   502,   502,   502,   502,   502,   502,   502,   502,   120,   502,   502,   502,   502,   502,   502,   502}, // [13]
            /* q14 */{  15,    15,    15,    15,    15,    15,    15,    15,    15,    15,    15,    15,    15,    15,    15,    15,    15,    15,    15,    15,    15,    15,    15}, // [14]
            /* q15 */{ 502,   502,   502,   502,   502,   502,   502,   502,   502,   502,   502,   502,   502,   502,   502,   502,   121,   502,   502,   502,   502,   502,   502}, // [15]
    };

    String reserved_Words[][] = {
                        //   0         1
            /* [00] */ { "AND",      "200" },
            /* [01] */ { "OR",       "201" },
            /* [02] */ { "NOT",      "202" },
            /* [03] */ { "TRUE",     "203" },
            /* [04] */ { "FALSE",    "204" },
            /* [05] */ { "IF",       "205" },
            /* [06] */ { "THEN",     "206" },
            /* [07] */ { "ELSE",     "207" },
            /* [08] */ { "WHILE",    "208" },
            /* [09] */ { "DO",       "209" },
            /* [10] */ { "BEGIN",    "210" },
            /* [11] */ { "END",      "211" },
            /* [12] */ { "READ",     "212" },
            /* [13] */ { "WRITE",    "213" },
            /* [14] */ { "PROGRAM",  "214" },
            /* [15] */ { "STRING",   "215" },
            /* [16] */ { "REAL",     "216" },
            /* [17] */ { "INTEGER",  "217" },
            /* [18] */ { "BOOLEAN",  "218" },
            /* [19] */ { "VAR",      "219" },
    };

    String errors[][] = {
                    //                           0                                                                1
            /*[00]*/{ "syntax error, expected digit",                                                           "500" },
            /*[01]*/{ "syntax error, syntax error, waiting for the = character for the assignment value",       "501" },
            /*[02]*/{ "syntax error, syntax error, waiting for the ' or '' character for the assignment value", "502" },
            /*[03]*/{ "syntax error, syntax error, invalid character",                                          "503" },
    };

    RandomAccessFile file = null;

    public lexicon() {
        try {
            file = new RandomAccessFile(textEditor, "r");

            while (character != -1) {
                if (Character.isLetter(((char) character))) {
                    column = 0;
                } else if (Character.isDigit(((char) character))) {
                    column = 1;
                } else {
                    switch ((char) character) {
                        case '+':
                            column = 2;
                            break;
                        case '-':
                            column = 3;
                            break;
                        case '*':
                            column = 4;
                            break;
                        case '/':
                            column = 5;
                            break;
                        case '>':
                            column = 6;
                            break;
                        case '<':
                            column = 7;
                            break;
                        case '=':
                            column = 8;
                            break;
                        case ':':
                            column = 9;
                            break;
                        case '(':
                            column = 10;
                            break;
                        case ')':
                            column = 11;
                            break;
                        case '.':
                            column = 12;
                            break;
                        case ',':
                            column = 13;
                            break;
                        case ';':
                            column = 14;
                            break;
                        case 39:
                            column = 15;
                            break;
                        case 34:
                            column = 16;
                            break;
                        case ' ':   //space
                            column = 18;
                            break;
                        case 9:     //tabulation
                            column = 19;
                            break;
                        case 13:    //enter
                            column = 20;
                            break;
                        case 10:
                            column = 22;
                            line = line + 1;
                        default:    //other character
                            column = 17;    
                            break;
                    }
                    if(character == -1){
                        column = 21; //end of file [eof]
                    }
                }

                valueTM = matrix[state][column];

                if(valueTM < 100){
                    state = valueTM;

                    if(state == 0){
                        lexeme = "";
                    }else{
                        lexeme = lexeme + (char)character;
                    }
                }else if(valueTM >= 100 && valueTM < 500){
                    if(valueTM == 100){
                        validateReservedWord();
                    }
                    if(valueTM == 100 || valueTM == 102 || valueTM == 103 || valueTM == 109 || valueTM == 111 || valueTM == 119 || valueTM >= 200){
                        file.seek(file.getFilePointer()-1);
                    }else{
                        lexeme = lexeme + (char)character;
                    }
                    insertNode();
                    state = 0;
                    lexeme = "";
                }else{
                    printErrorMessage();
                    break;
                }
            }
            printNodes();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void printNodes(){
        p = head;
        while(p != null){
            System.out.println("" + " " + valueTM + " " + line);
            
        }
    }

    private void printErrorMessage(){
        if(valueTM >= 500){
            for(String[] error : errors){
                if(valueTM == Integer.valueOf(error[1])){
                    System.out.println(error[0] + valueTM + "in line " + line + "\n");
                }
            }
            errorFound = true;
        }
    }

    private void validateReservedWord(){
        for(String[] reservedWord : reserved_Words){    //send reserved words array
            if(lexeme.equals(reservedWord[0])){
                valueTM = Integer.valueOf(reservedWord[1]);
            }
        }
    }

    private void insertNode(){
        Node node = new Node(lexeme, valueTM, line);
        if(head == null){
            head = node;
            p = head;
        }else{
            p.next = node;
            p = node;
        }
    }
}