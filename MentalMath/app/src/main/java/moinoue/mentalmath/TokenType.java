package moinoue.mentalmath;

public interface TokenType{

    int NUMBERS = 0;  //numbers (integers/floats)
    int PLUS    = 1;  //addition
    int MINUS   = 2;  //minus
    int MULT    = 3;  //multiply
    int DIV     = 4;  //division
    int MOD     = 5;  //modulus
    int LOG     = 6;  //logarithmic
    int EXP     = 7;  //exponent
    int POW     = 8;  //power
    int SQRT    = 9;  //square root
    int CBRT    = 10; //cube root
    int ROOT    = 11; //root
    int RPAR    = 12; //Right Parenthesis
    int LPAR    = 13; //Left Parenthesis
    int NEG     = 14; //Negative unary
    int POS     = 15; //Positive unary
    int SIN     = 16; //Sine
    int COS     = 17; //Cosine
    int TAN     = 18; //Tangent
    int ASIN     = 16; //Arc Sine
    int ACOS     = 17; //Arc Cosine
    int ATAN     = 18; //Arc Tangent

    int EOL     = 100; //end of line

}