public class variablesNode {
    String lexeme;
    int type;
    int asig;
    variablesNode next = null;

    variablesNode(String lexeme, int type, int asig){
        this.lexeme = lexeme;
        this.type = type;
        this.asig = asig;
    }
}
