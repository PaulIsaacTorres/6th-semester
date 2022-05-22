/*
Lenguaje y Aautómatas, Caso de Estudio MiniPascal

@Authors: 
Torres Cruz Janeth
Torres Enriquez Paul Isaac
*/
import java.io.IOException;

public class compiler {
    Node p;

    public static void main(String[] args) throws IOException{
        lexicon lexicon = new lexicon();

        if(!lexicon.errorFound){
            System.out.println("Analizador léxico terminado");
        }
    }
}