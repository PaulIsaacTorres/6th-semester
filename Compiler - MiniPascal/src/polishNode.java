public class polishNode {
    String lexeme;
    int token;
    polishNode next = null;

    polishNode(String lexeme, int token){
        this.lexeme = lexeme;
        this.token = token;
    }
}
