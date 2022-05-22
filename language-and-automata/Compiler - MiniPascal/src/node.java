public class Node {
    String lexeme;
    int token;
    int line;
    Node next = null;

    Node(String lexeme, int token, int line){
        this.lexeme = lexeme;
        this.token = token;
        this.line = line;
    }
}
