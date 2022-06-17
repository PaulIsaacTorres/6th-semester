/*
Lenguaje y Aautómatas, Caso de Estudio MiniPascal

@Authors: 
Torres Cruz Janeth
Torres Enriquez Paul Isaac
*/
import java.io.IOException;

public class compiler {
    node p;

    public static void main(String[] args) throws IOException{
        lexicon lexicon = new lexicon();

        if(!lexicon.errorFound){
            System.out.println("");
            System.out.println("Analizador lexico terminado");
            syntactic syntactic = new syntactic();
            if(!syntactic.errorFound){
                System.out.println("Analizador Sintactico terminado");
            }
        }
    }
}