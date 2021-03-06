import java.beans.Expression;
import java.io.RandomAccessFile;

public class lexicon {
    public static node head = null, p;
    int state = 0, column, valueTM, line = 1;
    int character = 0;
    String lexeme = "";
    boolean errorFound = false;

    String textEditor = "C:/Users/PaulI/Desktop/6th-semester/Compiler - MiniPascal/code.txt"; // Buffer

    int matrix[][] = {
            //         l d + - * / = < > ( ) . , ; : ' " eb tab ent eof nl oc
            //         [00] [01] [02] [03] [04] [05] [06] [07] [08] [09] [10] [11] [12] [13] [14] [15] [16] [17] [18] [19] [20] [21] [22]
            /* q00 */{ 1, 2, 103, 104, 105, 5, 8, 9, 10, 114, 115, 116, 117, 118, 11, 12, 14, 0, 0, 0, 0, 0, 504 }, // [00]
            /* q01 */{ 1, 1, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100 }, // [01]
            /* q02 */{ 101, 2, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101 }, // [02]
            /* q03 */{ 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 3, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500 }, // [03]
            /* q04 */{ 102, 4, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102 }, // [04]
            /* q05 */{ 106, 106, 106, 106, 6, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106 }, // [05]
            /* q06 */{ 6, 6, 6, 6, 7, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6 }, // [06]
            /* q07 */{ 501, 501, 501, 501, 501, 0, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501 }, // [07]
            /* q08 */{ 502, 502, 502, 502, 502, 502, 112, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502 }, // [08]
            /* q09 */{ 107, 107, 107, 107, 107, 107, 109, 107, 111, 107, 107, 107, 107, 107, 107, 107, 107, 107, 107, 107, 107, 107, 107 }, // [09]
            /* q10 */{ 108, 108, 108, 108, 108, 108, 110, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108 }, // [10]
            /* q11 */{ 119, 119, 119, 119, 119, 119, 113, 119, 119, 119, 119, 119, 119, 119, 119, 119, 119, 119, 119, 119, 119, 119, 119 }, // [11]
            /* q12 */{ 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13 }, // [12]
            /* q13 */{ 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 120, 503, 503, 503, 503, 503, 503, 503 }, // [13]
            /* q14 */{ 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14 }, // [14]
            /* q15 */{ 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503 }, // [15]
    };

    String reserved_Words[][] = {
            // 0 1
            /* [00] */ { "and", "200" },
            /* [01] */ { "or", "201" },
            /* [02] */ { "not", "202" },
            /* [03] */ { "true", "203" },
            /* [04] */ { "false", "204" },
            /* [05] */ { "if", "205" },
            /* [06] */ { "then", "206" },
            /* [07] */ { "else", "207" },
            /* [08] */ { "while", "208" },
            /* [09] */ { "do", "209" },
            /* [10] */ { "begin", "210" },
            /* [11] */ { "end", "211" },
            /* [12] */ { "read", "212" },
            /* [13] */ { "write", "213" },
            /* [14] */ { "program", "214" },
            /* [15] */ { "string", "215" },
            /* [16] */ { "real", "216" },
            /* [17] */ { "int", "217" },
            /* [18] */ { "boolean", "218" },
            /* [19] */ { "var", "219" },
    };

    String errorsList[][] = {
            // 0 1
            /* [00] */{ "syntax error, expected digit ", "500" },
            /* [01] */{ "syntax error, waiting for the / character", "501" },
            /* [02] */{ "syntax error, waiting for the = character", "502" },
            /* [03] */{ "syntax error, waiting for ' or '' character", "503" },
            /* [03] */{ "syntax error, invalid character", "504" },
    };

    RandomAccessFile file = null;

    public lexicon() {
        try {
            file = new RandomAccessFile(textEditor, "r"); // open file [only read]

            while (character != -1) { // read character one by one while -not eof-
                character = file.read();

                // check if character is a letter, digit or special and assign a column
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
                        case ' ': // space
                            column = 17;
                            break;
                        case 9: // tabulation
                            column = 18;
                            break;
                        case 13: // enter
                            column = 19;
                            break;
                        case 3: // end of file [eof]
                            column = 20;
                            break;
                        case 10: // new line
                            column = 21;
                            line = line + 1;
                            break;
                        default: // other character
                            column = 22;
                            break;
                    } // close switch
                } // end if

                // assign the matrix value
                valueTM = matrix[state][column];

                if (valueTM < 100) { // transitional state
                    state = valueTM;

                    if (state == 0) {
                        lexeme = "";
                    } else {
                        lexeme = lexeme + (char) character;
                    }

                } else if (valueTM >= 100 && valueTM < 500) { // final state
                    if (valueTM == 100) {
                        validateReservedWord();
                    }

                    if (valueTM == 100 || valueTM == 101 || valueTM == 102 || valueTM == 106 || valueTM == 107
                            || valueTM == 108 || valueTM == 108 || valueTM >= 200) {
                        file.seek(file.getFilePointer() - 1);
                    } else {
                        lexeme = lexeme + (char) character;
                    }
                    insertNode();
                    state = 0;
                    lexeme = "";
                } else { // Error state
                    printErrorMessage();
                    break;
                }
            }
            printNodes();
            syntactic();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (file != null) {
                    file.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void printNodes() {
        p = head;
        while (p != null) {
            System.out.println(p.lexeme + " " + p.token + " " + p.line);
            p = p.next;
        }
    }

    private void printErrorMessage() {
        if (character != -1 && valueTM >= 500) {
            for (String[] error : errorsList) {
                if (valueTM == Integer.valueOf(error[1])) {
                    System.out.println(error[0] + valueTM + " character: " + character + " in line " + line);
                }
            }
            errorFound = true;
        }
    }

    private void validateReservedWord() {
        for (String[] reservedWord : reserved_Words) { // send reserved words array
            if (lexeme.equals(reservedWord[0])) {
                valueTM = Integer.valueOf(reservedWord[1]);
            }
        }
    }

    private void insertNode() {
        node node = new node(lexeme, valueTM, line);
        if (head == null) {
            head = node;
            p = head;
        } else {
            p.next = node;
            p = node;
        }
    }

    /* START SYNTACTIC ANALIZER */
    private void syntactic() {
        p = head;
        while (p != null) {
            if (p.token == 214) {
                p = p.next;
                if (p.token == 100) {
                    p = p.next;
                    if (p.token == 118) {
                        p = p.next;
                        block();
                        if (p.token == 116) {
                            p = p.next;
                        } else {
                            System.out.println("Se espera el caracter .");
                        }
                    } else {
                        System.out.println("Se espera el caracter ;");
                    }
                } else {
                    System.out.println("Se espera un identificador");
                }
            } else {
                System.out.println("Se espera la palabra program");
            }
            break;
        }
    }

    private void block() {
        variableDeclarationPart();
        statementPart();
    }

    private void variableDeclarationPart() {
        if (p.token == 219) {
            p = p.next;
            while (p.token != 210) {
                variableDeclaration();
                if (p.token == 118) {
                    p = p.next;
                } else {
                    System.out.println("Se espera el caracter ;");
                }
            }
        } else {
            System.out.println("Se espera la palabra var");
        }
    }

    private void variableDeclaration() {
        while ((p.token == 100 || p.token == 117) && p.token != 118) {
            if (p.token == 100) {
                p = p.next;
                if (p.token == 117) {
                    p = p.next;
                    if (p.next.token == 119 && p.token == 100) {
                        p = p.next;
                        break;
                    }
                } 
                else {
                    System.out.println("Se espera el caracter ,");
                }
            } else {
                System.out.println("Se espera un identificador");
            }
        }
        if (p.token == 119) {
            p = p.next;
            type();
        } else {
            System.out.println("Se espera el caracter :");
        }
    }

    private void type() {
        if (p.token == 215 || p.token == 216 || p.token == 217 || p.token == 218) {
            p = p.next;
        } else {
            System.out.println("Se espera el tipo de dato");
        }
    }

    private void statementPart() {
        if(p.token == 210){
            p = p.next;
            while(p.token != 211){
                statement();
            }
                if (p.token == 211) {
                    p = p.next;
                }else{
                    System.out.println("Se espera la palabra end");
                }
        }else{
            System.out.println("Se espera la palabra begin");
        }
    }

    private void statement() {
        simpleStatement(); // or structuredStatement();
        if(p.token == 205 || p.token == 208){
            structuredStatement();
        }
    }

    private void simpleStatement() {
        if (p.token == 100 && p.next.token == 113) {
            if(p.token == 100){
                p = p.next;
                if(p.token == 113){
                    p = p.next;
                    expression();
                    if(p.token == 118){
                        p = p.next;
                    }
                }
            }
        }
        
        if (p.token == 212) {
            readStatement();
        }
        
        if (p.token == 213) {
            writeStatement();
        }
    }

    private void structuredStatement() {
        if(p.token == 205){
            ifStatement();
        }
        
        if(p.token == 208){
            whileStatement();
        }
    }
    
    private void whileStatement() {
        if(p.token == 208){
            p = p.next;
            while(p.token != 209){
                expression();
                if(p.token == 209){
                    p = p.next;
                    statement();
                    if(p.token == 211){
                        break;
                    }
                }
            }
        }
    }

    private void ifStatement() {
        if(p.token == 205){
            p = p.next;
            while(p.token != 206 && p.token != 209){
                expression();
            }
            if(p.token == 206){
                p = p.next;
                statement();
                if(p.token == 207){
                    p = p.next;
                    statement();
                }
            }
        }
    }
    
    private void expression() {
        while(p.token != 118 && p.token != 206){
            if(p.token == 100 || p.token == 101){
                if(p.token == 100){
                    p = p.next;
                }
                
                //multiplyng operator
                while(p.token == 105 || p.token == 106){                
                    if(p.token == 105){
                        p = p.next;
                    }
                    if(p.token == 106){
                        p = p.next;
                    }else{
                        break;
                    }
                }
                
                //relational operator 
                if(p.token == 107 || p.token == 109 || p.token == 110 || p.token == 108 || p.token == 112 || p.token == 111 || p.token == 201 || p.token == 200){
                    if(p.token == 107){
                        p = p.next;
                    }
                    if(p.token == 108){
                        p = p.next;
                    }
                    if(p.token == 109){
                        p = p.next;
                    }
                    if(p.token == 110){
                        p = p.next;
                    }
                    if(p.token == 111){
                        p = p.next;
                    }
                    if(p.token == 112){
                        p = p.next;
                    }
                    if(p.token == 201){
                        p = p.next;
                    }
                    if(p.token == 200){
                        p = p.next;
                    }else{
                        break;
                    } 
                }
                
                if(p.token == 101){
                    p = p.next;
                }
                
                if(p.token == 209){
                    break;
                }
            }
        }
    }

    private void readStatement() {
        if (p.token == 212) { // READ
            p = p.next;
            if (p.token == 114) { // (
                p = p.next;
                while (p.token != 115) {
                    if (p.token == 100) { // IDENTIFIER
                        p = p.next;
                        if (p.token == 117 && p.next.token != 115) { // ; IF NEXT TOKEN IS DIFFERENT TO )
                            p = p.next;
                        } else {
                            break;
                        }
                    } else {
                        System.out.println("Se espera un identificador");
                    }
                }
                if (p.token == 115) { // )
                    p = p.next;
                    if (p.token == 118) {
                        p = p.next;
                    } else {
                        System.out.println("Se espera el caracter ;");
                    }
                } else {
                    System.out.println("Se espera el caracter )");
                }
            } else {
                System.out.println("Se espera el caracter (");
            }
        } else {
            System.out.println("Se espera la palabra read");
        }
    }

    private void writeStatement() {
        if (p.token == 213) { // WRITE
            p = p.next;
            if (p.token == 114) { // (
                p = p.next;
                while (p.token != 115) {
                    if (p.token == 100) { // IDENTIFIER
                        p = p.next;
                        if (p.token == 117 && p.next.token != 115) { // ; IF NEXT TOKEN IS DIFFERENT TO )
                            p = p.next;
                        } else {
                            break;
                        }
                    } else {
                        System.out.println("Se espera un identificador");
                    }
                }

                if (p.token == 115) { // )
                    p = p.next;
                    if (p.token == 118) {
                        p = p.next;
                    }
                    else {
                        System.out.println(" >> " + p.lexeme + " | " + p.token + " | " + p.line + "<w> $ token");
                        System.out.println("Se espera el caracter ;");
                    }
                } else {
                    System.out.println("Se espera el caracter )");
                }
            } else {
                System.out.println("Se espera el caracter (");
            }
        } else {
            System.out.println("Se espera la palabra read");
        }
    }

}