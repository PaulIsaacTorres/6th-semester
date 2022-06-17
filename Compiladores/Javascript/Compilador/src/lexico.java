import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

public class lexico {

    public static node head = null, p;  //inicializamos objeto nodo
    
    int estado = 0, columna, valorMT, renglon = 1;
    int caracter = 0;
    String lexema = "";
    boolean errorEncontrado = false;

    String editorTexto = "Compilador/codigo.txt"; //buffer

    int matrizTransiciones[][] = {
        //     L      D     +     -     *     /     =     %     !     <     >     (     )     {     }     [     ]     .     ,     ;     :     ^     &     |     '     "     eof   tab   nl    ent   eb    oc
        //     [00]   [01]  [02]  [03]  [04]  [05]  [06]  [07]  [08]  [09]  [10]  [11]  [12]  [13]  [14]  [15]  [16]  [17]  [18]  [19]  [20]  [21]  [22]  [23]  [24]  [25]  [26]  [27]  [28]  [29]  [30]  [31]
        /*q00*/{   1,    2,    5,    6,   7,   8,   10,    9,   12,   16,   17,   127,  128,  129,  130,  131,  132,  126,  125,  123,  124,  116,    14,   15,  133,  134,  135,  136,  137,  138,  139,  503},
        /*q01*/{   1,    1, 100,  100, 100, 100, 100, 100,  100,  100,  100,  100,  100,  100,  100,  100,  100,  100,  100,  100,  100,  100,  100, 100,  100,  100,  100,  100,  100,  100,  100, 100,  100},
        /*q02*/{ 102,    2,  102,  102,  102,  102,  102,   102,  102,  102,  102,  102,  102,  102,  102,  102,  102,   3,  102,  102,  102,  102,  102,  102,  102,  102,  102,  102,   102,  102, 102,  102},
        /*q03*/{ 500,    4,  500,  500,  500,  500,  500,  500,  500,  500,  500,  500,  500,  500,  500,  500,  500,  500,  500,  500,  500 , 500,  500,  500,  500,  500,  500,  500,  500,  500,  500,  500},
        /*q04*/{ 103,    4,  103,  103,  103,  103,  103,  103,  103,  103,  103,  103,  103,  103,  103,  103,  103,  103,  103,  103,  103,  103,  103,  103,  103,  103,  103,  103,  103,  103,  103,  103},
        /*q05*/{ 104,  104,  105,  104,  104,  104,  106,  104,  104,  104,  104,  104,  104,  104,  104,  104,  104,  104,  104,  104,  104,  104,  104,  104,  104,  104,  104,  104,  104,  104,  104,  104},
        /*q06*/{ 107,  107,  107,  108,  107,  107,  109,  107,  107,  107,  107,  107,  107,  107,  107,  107,  107,  107,  107,  107,  107,  107,  107,  107,  107,  107,  107,  107,  107,  107,  107,  107},
        /*q07*/{ 110,  110,  110,  110,  110,  110,  111,  110,  110,  110,  110,  110,  110,  110,  110,  110,  110,  110,  110,  110,  110,  110,  110,  110,  110,  110,  110,  110,  110,  110,  110,  110},
        /*q08*/{ 112,  112,  112,  112,  112,   18,  113,  112,  112,  112,  112,  112,  112,  112,  112,  112,  112,  112,  112,  112,  112,  112,  112,  112,  112,  112,  112,  112,  112,  112,  112,  112},
        /*q09*/{ 114,  114,  114,  114,  114,  114,  115,  114,  114,  114,  114,  114,  114,  114,  114,  114,  114,  114,  114,  114,  114,  114,  114,  114,  114,  114,  114,  114,  114,  114,  114,  114},
        /*q10*/{ 117,  117,  117,  117,  117,  117,   11,  117,  117,  117,  117,  117,  117,  117,  117,  117,  117,  117,  117,  117,  117,  117,  117,  117,  117,  117,  117,  117,  117,  117,  117,  117},
        /*q11*/{ 118,  118,  118,  118,  118,  118,  119,  118,  118,  118,  118,  118,  118,  118,  118,  118,  118,  118,  118,  118,  118,  118,  118,  118,  118,  118,  118,  118,  118,  118,  118,  118},
        /*q12*/{ 120,  120,  120,  120,  120,  120,   13,  120,  120,  120,  120,  120,  120,  120,  120,  120,  120,  120,  120,  120,  120,  120,  120,  120,  120,  120,  120,  120,  120,  120,  120,  120},
        /*q13*/{ 122,  122,  122,  122,  122,  122,  121,  122,  122,  122,  122,  122,  122,  122,  122,  122,  122,  122,  122,  122,  122,  122,  122,  122,  122,  122,  122,  122,  122,  122,  122,  122},
        /*q14*/{ 501,  501,  501,  501,  501,  501,  501,  501,  501,  501,  501,  501,  501,  501,  501,  501,  501,  501,  501,  501,  501,  501,  140,  501,  501,  501,  501,  501,  501,  501,  501,  501},
        /*q15*/{ 502,  502,  502,  502,  502,  502,  502,  502,  502,  502,  502,  502,  502,  502,  502,  502,  502,  502,  502,  502,  502,  502,  502,  140,  502,  502,  502,  502,  502,  502,  502,  502},
        /*q16*/{ 143,  143,  143,  143,  143,  143,  142,  143,  143,  143,  143,  143,  143,  143,  143,  143,  143,  143,  143,  143,  143,  143,  143,  143,  143,  143,  143,  143,  143,  143,  143,  143},
        /*q17*/{ 145,  145,  145,  145,  145,  145, 144,  145,  145,  145,  145,  145,  145,  145,  145,  145,  145,  145,  145,  145,  145,  145,  145,  145,  145,  145,  145,  145,  145,  145,  145,  145},
        /*q18*/{  18,   18,   18,   18,   18,   18,  18,    18,  18,   18,   18,   18,   18,   18,   18,   18,   18,   18,    18,   18,   18,   18,   18,   18,   18,  18,   18,   18,    0,   0,     18,  18}
    };

    String palabrasReservadas[][] = {
        //          [00]         [01]
        /*[00]*/{"abstracto",   "200"},
        /*[01]*/{"booleano",    "201"},
        /*[02]*/{"romper",      "202"},
        /*[03]*/{"byte",        "203"},
        /*[04]*/{"caso",        "204"},
        /*[05]*/{"captura",     "205"},
        /*[06]*/{"caracter",    "206"},
        /*[07]*/{"clase",       "207"},
        /*[08]*/{"constante",   "208"},
        /*[09]*/{"continuar",   "209"},
        /*[10]*/{"defecto",     "210"},
        /*[11]*/{"haber",       "211"},
        /*[12]*/{"doble",       "212"},
        /*[13]*/{"sino",        "213"},
        /*[14]*/{"extiende",    "214"},
        /*[15]*/{"falso",       "215"},
        /*[16]*/{"final",       "216"},
        /*[17]*/{"finalmente",  "217"},
        /*[18]*/{"flotar",      "218"},
        /*[19]*/{"para",        "219"},
        /*[20]*/{"funcion",     "220"},
        /*[21]*/{"ir",          "221"},
        /*[22]*/{"si",          "222"},
        /*[23]*/{"implementos", "223"},
        /*[24]*/{"importar",    "224"},
        /*[25]*/{"en",          "225"},
        /*[26]*/{"envezde",     "226"},
        /*[27]*/{"int",         "227"},
        /*[28]*/{"interfaz",    "228"},
        /*[29]*/{"largo",       "229"},
        /*[30]*/{"nativo",      "230"},
        /*[31]*/{"nuevo",       "231"},
        /*[32]*/{"nulo",        "232"},
        /*[33]*/{"paquete",     "233"},
        /*[34]*/{"privado",     "234"},
        /*[35]*/{"protegido",   "235"},
        /*[36]*/{"publico",     "236"},
        /*[37]*/{"devolver",    "237"},
        /*[38]*/{"corta",       "238"},
        /*[39]*/{"estatica",    "239"},
        /*[40]*/{"super",       "240"},
        /*[41]*/{"cambiar",     "241"},
        /*[42]*/{"sincronizado","242"},
        /*[43]*/{"este",        "243"},
        /*[44]*/{"tirar",       "244"},
        /*[45]*/{"lanza",       "245"},
        /*[46]*/{"transeunte",  "246"},
        /*[47]*/{"verdadero",   "247"},
        /*[48]*/{"probar",      "248"},
        /*[49]*/{"var",         "249"},
        /*[50]*/{"vacio",       "250"},
        /*[51]*/{"mientras",    "251"},
        /*[52]*/{"con",         "252"}
    };

    String listaErrores[][] = {
        //              [00]                  [01]
        /*[00]*/ {"Se espera un digito, Error",    "500"},
        /*[01]*/ {"Se espera el caracter &, Error","501"},
        /*[02]*/ {"Se espera el caracter |, Error","502"},
        /*[03]*/ {"Caracter invalido, Error",      "503"}
    };
    
    RandomAccessFile archivo = null;    

    public lexico(){
        try{
            archivo = new RandomAccessFile(editorTexto, "r"); //abrir archivo (solo lectura)

            while (caracter != -1) { //leer caracter uno por uno mientras -not eof- 
                caracter = archivo.read(); 
                
                //validamos si el caracter es una letra, digito o caracter especial y asignamos a una columna
                if(Character.isLetter(((char) caracter))){
                    columna = 0;
                }else if(Character.isDigit(((char) caracter))){
                    columna = 1;
                }else{
                    switch ((char) caracter){
                        case '+':
                            columna = 2;
                            break;
                        case '-':
                            columna = 3;
                            break;
                        case '*':
                            columna = 4;
                            break;
                        case '/':
                            columna = 5;
                            break;
                        case '=':
                            columna = 6;
                            break;
                        case '%':
                        columna = 7;
                            break;
                        case '!':
                            columna = 8;
                            break;
                        case '<':
                        columna = 9;
                            break;
                            case '>':
                            columna = 10;
                            break;
                        case '(':
                            columna = 11;
                            break;
                        case ')':
                            columna = 12;
                            break;
                        case '{':
                            columna = 13;
                            break;
                        case '}':
                            columna = 14;
                            break;
                        case '[':
                            columna = 15;
                            break;
                        case ']':
                            columna = 16;
                            break;
                        case '.':
                            columna = 17;
                            break;
                        case ',':
                            columna = 18;
                            break;
                            case ';':
                            columna = 19;
                            break;
                        case ':':
                            columna = 20;
                            break;
                        case '^':
                            columna = 21;
                            break;
                        case '&':
                            columna = 22;
                            break;
                        case '|':
                            columna = 23;
                            break;
                        case 39:    //''
                            columna = 24;
                            break;
                        case 34:    //""
                            columna = 25;
                            break;
                        case 3:     //fin de archivo
                            columna = 26;
                            break;
                        case 9:     //tabulacion
                            columna = 27;
                            break;
                        case 13:    //enter
                            columna = 29;
                            break;
                        case 10:    // nueva linea
                            columna = 28;
                            renglon = renglon + 1;
                            break;
                        case 32:   //espacio en blanco
                            columna = 30;
                            break;
                        default: //otro caracter
                            columna = 31;
                            break;
                        } //fin switch
                    } //fin if
                    
                    //asignamos el valor de la matriz
                valorMT = matrizTransiciones[estado][columna];  //valorMT = 1
                if(valorMT < 100){   //estado de transición 
                    estado = valorMT;

                    if(estado == 0){
                        lexema = "";
                    }else{
                        lexema = lexema + (char) caracter; // 
                    }
                }else if(valorMT >= 100 && valorMT < 500){ //Estado final
                    if(valorMT == 100){
                        validar_palabrasReservadas();
                    }

                    if(valorMT == 100 || valorMT == 103 || valorMT == 102 || valorMT == 104 || valorMT == 107 || valorMT == 110 || valorMT == 112 ||
                    valorMT == 114 || valorMT == 118 || valorMT == 117 || valorMT == 122 || valorMT == 120 || valorMT == 143 || valorMT == 145 || valorMT >= 200){
                        archivo.seek(archivo.getFilePointer() -1);
                    }else{
                        lexema = lexema + (char)caracter;
                    }
                    insertarNodo();
                    estado = 0;
                    lexema = "";
                }else{ //estado de error
                    System.out.println("");
                    System.out.println(">>>>>>>>>>> ERROR ENCONTRADO <<<<<<<<<<<<<");
                    imprimirMensajeError();
                    break;
                }
            }
            System.out.println("");
            imprimirNodos();
            escribirFichero();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(archivo != null){
                    archivo.close();
                }
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
    
    private void escribirFichero() throws IOException{
        FileWriter fichero = new FileWriter("C:/Users/PaulI/Documents/Fichero.txt");

        p = head;
        while(p != null){
            fichero.write("Lexema: " + p.lexema + " Token: " + p.token + " Renglon: " + p.renglon + "\n");
            p = p.next;
        }
        fichero.close();
    }

    private void imprimirNodos() {
        p = head;
        while (p != null) {
            System.out.println(p.lexema + " " + p.token + " " + p.renglon);
            p = p.next;
        }
    }

    private void imprimirMensajeError() {
        if (caracter != -1 && valorMT >= 500) {
            for (String[] error : listaErrores) {
                if (valorMT == Integer.valueOf(error[1])) {
                    System.out.println(error[0] + " " + valorMT + " character: " + caracter + " en el rengloln " + renglon);
                }
            }
            errorEncontrado = true;
        }
    }

    private void validar_palabrasReservadas() {
        for (String[] palabrasReservadas : palabrasReservadas) { // send reserved words array
            if (lexema.equals(palabrasReservadas[0])) {
                valorMT = Integer.valueOf(palabrasReservadas[1]);
            }
        }
    }

    private void insertarNodo() {
        node node = new node(lexema, valorMT, renglon);

        if (head == null) {
            head = node;
            p = head;
        } else {
            p.next = node;
            p = node;
        }
    }
}
