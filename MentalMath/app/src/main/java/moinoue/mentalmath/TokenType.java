package moinoue.mentalmath;

import java.util.ArrayList;
import java.util.regex.Pattern;

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

    int EOL     = 100; //end of line

}