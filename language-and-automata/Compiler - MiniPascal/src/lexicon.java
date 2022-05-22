import java.io.RandomAccessFile;

public class lexicon {
    public static node head = null, p;
    int state = 0, column, valueTM, line = 1;
    int character = 0;
    String lexeme = "";
    boolean errorFound = false;

    String textEditor = "C://Users//PaulI//Documents//schoolar-projects//language-and-automata//Compiler - MiniPascal//codigo.txt";

    int matrix[][] = {  
               //   l     d     +     -     *     /     =     <     >     (     )     .    ,      ;     :     '      "   eb   tab   ent   eof    nl    oc
                //[00]  [01]  [02]  [03]  [04]  [05]  [06]  [07]  [08]  [09]  [10]  [11]  [12]  [13]  [14]  [15]  [16]  [17]  [18]  [19]  [20]  [21]  [22]
        /* q00 */{   1,    2,  103,  104,  105,    5,    8,    9,   10,  114,  115,  116,  117,  118,   11,   12,   14,  122,  123,  124,  125,  126,  504}, // [00]
        /* q01 */{   1,    1,  100,  100,  100,  100,  100,  100,  100,  100,  100,  100,  100,  100,  100,  100,  100,  100,  100,  100,  100,  100,  100}, // [01]
        /* q02 */{ 101,    2,  101,  101,  101,  101,  101,  101,  101,  101,  101,  101,  101,  101,  101,  101,  101,  101,  101,  101,  101,  101,  101}, // [02]
        /* q03 */{ 500,  500,  500,  500,  500,  500,  500,  500,  500,  500,  500,    3,  500,  500,  500,  500,  500,  500,  500,  500,  500,  500,  500}, // [03]
        /* q04 */{ 102,    4,  102,  102,  102,  102,  102,  102,  102,  102,  102,  102,  102,  102,  102,  102,  102,  102,  102,  102,  102,  102,  102}, // [04]
        /* q05 */{ 106,  106,  106,  106,    6,  106,  106,  106,  106,  106,  106,  106,  106,  106,  106,  106,  106,  106,  106,  106,  106,  106,  106}, // [05]
        /* q06 */{   6,    6,    6,    6,    7,    6,    6,    6,    6,    6,    6,    6,    6,    6,    6,    6,    6,    6,    6,    6,    6,    6,    6}, // [06]
        /* q07 */{ 501,  501,  501,  501,  501,    0,  501,  501,  501,  501,  501,  501,  501,  501,  501,  501,  501,  501,  501,  501,  501,  501,  501}, // [07]
        /* q08 */{ 502,  502,  502,  502,  502,  502,  112,  502,  502,  502,  502,  502,  502,  502,  502,  502,  502,  502,  502,  502,  502,  502,  502}, // [08]
        /* q09 */{ 107,  107,  107,  107,  107,  107,  109,  107,  111,  107,  107,  107,  107,  107,  107,  107,  107,  107,  107,  107,  107,  107,  107}, // [09]
        /* q10 */{ 108,  108,  108,  108,  108,  108,  110,  108,  108,  108,  108,  108,  108,  108,  108,  108,  108,  108,  108,  108,  108,  108,  108}, // [10]
        /* q11 */{ 119,  119,  119,  119,  119,  119,  113,  119,  119,  119,  119,  119,  119,  119,  119,  119,  119,  119,  119,  119,  119,  119,  119}, // [11]
        /* q12 */{  13,   13,   13,   13,   13,   13,   13,   13,   13,   13,   13,   13,   13,   13,   13,   13,   13,   13,   13,   13,   13,   13,   13}, // [12]
        /* q13 */{ 503,  503,  503,  503,  503,  503,  503,  503,  503,  503,  503,  503,  503,  503,  503,  120,  503,  503,  503,  503,  503,  503,  503}, // [13]
        /* q14 */{  14,   14,   14,   14,   14,   14,   14,   14,   14,   14,   14,   14,   14,   14,   14,   14,   14,   14,   14,   14,   14,   14,   14}, // [14]
        /* q15 */{ 503,  503,  503,  503,  503,  503,  503,  503,  503,  503,  503,  503,  503,  503,  503,  503,  503,  503,  503,  503,  503,  503,  503}, // [15]
    };

    String reserved_Words[][] = {
                        //   0         1
            /* [00] */ { "and",      "200" },
            /* [01] */ { "or",       "201" },
            /* [02] */ { "not",      "202" },
            /* [03] */ { "true",     "203" },
            /* [04] */ { "false",    "204" },
            /* [05] */ { "if",       "205" },
            /* [06] */ { "then",     "206" },
            /* [07] */ { "else",     "207" },
            /* [08] */ { "while",    "208" },
            /* [09] */ { "do",       "209" },
            /* [10] */ { "begin",    "210" },
            /* [11] */ { "end",      "211" },
            /* [12] */ { "read",     "212" },
            /* [13] */ { "write",    "213" },
            /* [14] */ { "program",  "214" },
            /* [15] */ { "string",   "215" },
            /* [16] */ { "real",     "216" },
            /* [17] */ { "integer",  "217" },
            /* [18] */ { "boolean",  "218" },
            /* [19] */ { "var",      "219" },
    };

    String errors[][] = {
                    //                           0                     1
            /*[00]*/{ "syntax error, expected digit ",               "500" },
            /*[01]*/{ "syntax error, waiting for the / character",   "501" },
            /*[02]*/{ "syntax error, waiting for the = character",   "502" },
            /*[03]*/{ "syntax error, waiting for ' or '' character", "503" },
            /*[03]*/{ "syntax error, invalid character ",            "504" },
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
                        case '=':
                            column = 6;
                            break;
                        case '<':
                            column = 7;
                            break;
                        case '>':
                            column = 8;
                            break;
                        case '(':
                            column = 9;
                            break;
                        case ')':
                            column = 10;
                            break;
                        case '.':
                            column = 11;
                            break;
                        case ',':
                            column = 12;
                            break;
                        case ';':
                            column = 13;
                            break;
                        case ':':
                            column = 14;
                            break;
                        case 39:
                            column = 15;
                            break;
                        case 34:
                            column = 16;
                            break;
                        case ' ':   //space
                            column = 17;
                            break;
                        case 9:     //tabulation
                            column = 18;
                            break;
                        case 13:    //enter
                            column = 19;
                            break;
                        case 10:    //new line
                            column = 21;
                            line = line + 1;
                        default:    //other character
                            column = 22;    
                            break;
                    }
                    if(character == -1){
                        column = 20; //end of file [eof]
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
                    System.out.println(error[0] + "error " + valueTM + " character: " + character + " in line " + line + "\n");
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
        node node = new node(lexeme, valueTM, line);
        if(head == null){
            head = node;
            p = head;
        }else{
            p.next = node;
            p = node;
        }
    }
}