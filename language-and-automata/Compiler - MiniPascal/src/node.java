public class node {
    String lexeme;
    int token;
    int line;
    node next = null;

    node(String lexeme, int token, int line){
        this.lexeme = lexeme;
        this.token = token;
        this.line = line;
    }
}
