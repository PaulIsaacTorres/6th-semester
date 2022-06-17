public class node{
    String lexema;
    int token;
    int renglon;
    node next = null;

    node(String lexema, int token, int renglon){
        this.lexema = lexema;
        this.token = token;
        this.renglon = renglon;
    }
}