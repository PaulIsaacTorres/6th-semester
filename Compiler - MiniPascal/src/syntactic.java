//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.util.ArrayList;
//import java.util.Stack;

public class syntactic {
    //variable declaration part
    node p, e;
    boolean errorFound = true;
    public static variablesNode headVariables = null, V, R;
    polishNode headPolish = null;
    polishNode v2,v3;

    public String variable;

    String lexeme = "";
    int type;
    int asig;
    int first_Column, second_Column;
    int first_Variable, second_Variable;
    int operan1 = 0, operan2 = 0;
    String op1, op2;
    String temp;
    int c1 = 0, c2 = 0;

    public String firstComprobation = "", secondComprobation = "";

    //check variable
    int assignation[][] = {
        {}
    };

    //check variable
    int sum[][] = {
        {}
    };

    //check variable
    int rmd[][] = {
        {}
    };
    
    //check variable
    int sameDif[][] = {
        {}
    };

    //check variable
    int relation[][] = {
        {}
    };
    
    //check variable
    int andOr[][] = {
        {}
    };
    
    //Stack <String> Stack = new <String> Stack();
    
    //Stack <Integer> E = new <Integer> Stack();
    //Stack <Integer> I = new <Integer> Stack();
    //Stack <Integer> P = new <Integer> Stack();
    //Stack <Integer> P3 = new <Integer> Stack();
    //Stack <Integer> S = new <Integer> Stack();
    //Stack <Integer> T = new <Integer> Stack();
    
    //Stack <Integer> Y = new <Integer> Stack();
    //Stack <Integer> C = new <Integer> Stack();
    
    //Stack <Integer> E3 = new <Integer> Stack(); 
    //Stack <Integer> I3 = new <Integer> Stack(); 
    
    //Stack <String> L = new <String> Stack();
    //Stack <String> L2 = new <String> Stack();
    //Stack <String> P2 = new <String> Stack();

    //check errors list
    String errors[][] = {
        {}
    };

    //check semantic errors list
    String semanticError[][] = {
        {}
    };

    //protected ArrayList<String> varList = new ArrayList();
    //end declaration part
}
