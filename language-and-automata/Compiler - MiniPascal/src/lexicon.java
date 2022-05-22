public class lexicon {
    node head = null, p;
    int state = 0, column, TMvalue, line =1;
    int character = 0;
    String lexem = "";
    boolean errorFound = false; 

    String file = "C:/Users/PaulI/Desktop/Lenguaje y Autómatas/Compilador - MiniPascal/src/miniPascalCode.txt";

    int matrix[][] = {
     //        l       d       +       -       *       /       >       <        =       :      (       )       .       ,       ;       '       "       oc      eb      tab     ent     eof
     //       [0]     [1]      [2]     [3]     [4]     [5]     [6]     [7]      [8]     [9]    [10]    [11]    [12]    [13]    [14]    [15]    [16]    [17]    [18]    [19]    [20]    [21]
    /*q00*/{   1,      1,      103,    104,    105,      5,      8,      9,      10,     11,   114,    115,    116,    117,    118,     12,     14,    503,    122,    123,    124,    124}, // [0]
    /*q01*/{   1,      1,      100,    100,    100,    100,    100,    100,     100,    100,   100,    100,    100,    100,    100,    100,    100,    100,    100,    100,    100,    100}, // [1]
    /*q02*/{ 101,      2,      101,    101,    101,    101,    101,    101,     101,    101,   101,    101,      3,    101,    101,    101,    101,    101,    101,    101,    101,    101}, // [2]
    /*q03*/{ 500,      4,      500,    500,    500,    500,    500,    500,     500,    500,   500,    500,    500,    500,    500,    500,    500,    500,    500,    500,    500,    500}, // [3]
    /*q04*/{ 102,      4,      102,    102,    102,    102,    102,    102,     102,    102,   102,    102,    102,    102,    102,    102,    102,    102,    102,    102,    102,    102}, // [4]
    /*q05*/{ 106,    106,      106,    106,      6,    106,    106,    106,     106,    106,   106,    106,    106,    106,    106,    106,    106,    106,    106,    106,    106,    106}, // [5]
    /*q06*/{   6,      6,        6,      6,      7,      6,      6,      6,       6,      6,     6,      6,      6,      6,      6,      6,      6,      6,      6,      6,      6,      6}, // [6]
    /*q07*/{ 504,    504,      504,    504,    504,      0,    504,    504,     504,    504,   504,    504,    504,    504,    504,    504,    504,    504,    504,    504,    504,    504}, // [7]    
    /*q08*/{ 108,    108,      108,    108,    108,    108,    108,    108,     110,    108,   108,    108,    108,    108,    108,    108,    108,    108,    108,    108,    108,    108}, // [8]
    /*q09*/{ 107,    107,      107,    107,    107,    107,    111,    107,     109,    107,   107,    107,    107,    107,    107,    107,    107,    107,    107,    107,    107,    107}, // [9]
    /*q10*/{ 501,    501,      501,    501,    501,    501,    501,    501,     112,    501,   501,    501,    501,    501,    501,    501,    501,    501,    501,    501,    501,    501}, //[10]
    /*q11*/{ 119,    119,      119,    119,    119,    119,    119,    119,     113,    119,   119,    119,    119,    119,    119,    119,    119,    119,    119,    119,    119,    119}, //[11]
    /*q12*/{  13,     13,       13,     13,     13,     13,     13,     13,      13,     13,    13,     13,     13,     13,     13,     13,     13,     13,     13,     13,     13,     13}, //[12]
    /*q13*/{ 502,    502,      502,    502,    502,    502,    502,    502,     502,    502,   502,    502,    502,    502,    502,    120,    502,    502,    502,    502,    502,    502}, //[13]
    /*q14*/{  15,     15,       15,     15,     15,     15,     15,     15,      15,     15,    15,     15,     15,     15,     15,     15,     15,     15,     15,     15,     15,     15}, //[14]
    /*q15*/{ 502,    502,      502,    502,    502,    502,    502,    502,     502,    502,   502,    502,    502,    502,    502,    502,    121,    502,    502,    502,    502,    502}, //[15]
    };
}
