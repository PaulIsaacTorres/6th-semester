import java.io.IOException;

/*
Lenguaje y Automatas, Case de Estudio Javascript

@Author: Rivera Yanes Julio Cesar, 19330657
@Descripción: Analizador Léxico sobre el caso de estudio Javascript desarrollado en el idioma Español.
*/
public class compilador {
    node p;

    public static void main(String[] args) throws IOException{
        lexico lexico = new lexico();

        if(!lexico.errorEncontrado){
            System.out.println("Analizador Lexico Terminado");
        }
    }
}
