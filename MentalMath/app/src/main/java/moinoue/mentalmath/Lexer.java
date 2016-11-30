package moinoue.mentalmath;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Copyright (c) 2016 Vidhya, Mohammed
 */

public class Lexer implements TokenType {
    private ArrayList<Token> tokens;
    private String input;
    private int position;

    public Lexer(String input){
        this.input = input.toLowerCase() + " EOL" ;
        this.position = 0;
        this.tokens = new ArrayList<Token>();

        constructToken();
        check();
    }


    private void constructToken(){
        String[] strArray = input.split("\\s");
        
        for (int i = 0; i<strArray.length; i++){

            if (strArray[i].startsWith("(")) {
                Token tok = new Token(LPAR, 0);
                tokens.add(tok);
                continue;
            }
            if ((strArray[i].equals("right") || strArray[i].equals("closed") || strArray[i].equals("close")) && (strArray[i+1].equals("parenthesis") || strArray[i+1].equals("parentheses") || strArray[i+1].equals("parent")|| strArray[i+1].equals("paren")|| strArray[i+1].equals("brace")|| strArray[i+1].equals("bracket"))) {
                Token tok = new Token(RPAR, 0);
                tokens.add(tok);
                continue;
            }
            if (strArray[i].endsWith(")")) {
                Token tok = new Token(RPAR, 0);
                tokens.add(tok);
                continue;
            }
            if ((strArray[i].equals("left") || strArray[i].equals("opened") || strArray[i].equals("open")) && (strArray[i+1].equals("parenthesis")|| strArray[i+1].equals("parentheses") || strArray[i+1].equals("parent")|| strArray[i+1].equals("paren")|| strArray[i+1].equals("paren")|| strArray[i+1].equals("brace")|| strArray[i+1].equals("bracket"))) {
                Token tok = new Token(LPAR, 0);
                tokens.add(tok);
                continue;
            }
            if (strArray[i].equals("plus")|| strArray[i].equals("+") || strArray[i] .equals("added") ){
 		        if(strArray[i+1].equals("by")) {
                    i++;
                }
	             Token tok = new Token(PLUS, 0);
                tokens.add(tok);
                continue;
            }
            if (strArray[i].equals("minus")|| strArray[i].equals("-") || strArray[i].equals("subtracted" )){
                if(strArray[i+1].equals("by")) {
                    i++;
                }
	        Token tok = new Token(MINUS, 0);
                tokens.add(tok);
                continue;
            }
            if (strArray[i].equals("times")|| strArray[i].equals("*") || strArray[i].equals("x") || strArray[i].equals("product") || strArray[i].equals("into") || strArray[i].equals("by") || strArray[i].equals("multiplied")){
		        if((strArray[i].equals("multiplied") || strArray[i].equals("times")) && strArray[i+1].equals("by")){
                    i++;
               }
		        Token tok = new Token(MULT, 0);
                tokens.add(tok);
                continue;
            }
            if (strArray[i].equals("divide")|| strArray[i].equals("/") || strArray[i].equals("over") || strArray[i].equals("divided" ) || strArray[i].equals("�")) {
                if (strArray[i + 1].equals("by")){
                    i++;
                }
		        Token tok = new Token(DIV, 0);
                tokens.add(tok);
                continue;
            }
            if (strArray[i].equals("mod")|| strArray[i].equals("modulus" ) || strArray[i].equals( "%" )){
                Token tok = new Token(MOD, 0);
                tokens.add(tok);
                continue;
            }
            if (strArray[i].equals("ln")){
                Token tok = new Token(LN, 0);
                tokens.add(tok);
                continue;
            }
            if (strArray[i].equals("natural") && (strArray[i+1].equals("log") || strArray[i+1].equals("logarithm"))){
                i++;
                Token tok = new Token(LN, 0);
                tokens.add(tok);
                continue;
            }

            if (strArray[i].equals("log") || strArray[i].equals("logarithm") ){
                Token tok = new Token(LOG, 0);
                tokens.add(tok);
                continue;
            }
            if (strArray[i].equals("exp" ) || strArray[i].equals( "exponent") ){
                Token tok = new Token(EXP, 0);
                tokens.add(tok);
                continue;
            }
            if (strArray[i].equals("power") || strArray[i].equals("pow") || strArray[i].equals("^")){
                Token tok = new Token(POW, 0);
                tokens.add(tok);
                continue;
            }

            if (( strArray[i].equals("square" )|| strArray[i].equals("squared"))&& (strArray[i+1].equals("root") || strArray[i+1].equals("route") || strArray[i+1].equals("rut") || strArray[i+1].equals("rat")|| strArray[i+1].equals("runt"))){
                Token tok = new Token(SQRT, 0);
                tokens.add(tok);
                i++;
                continue;
            }
            if ( (strArray[i].equals("cube") || strArray[i].equals("cubed")) && (strArray[i+1].equals("root") || strArray[i+1].equals("route") || strArray[i+1].equals("rut") || strArray[i+1].equals("rat")|| strArray[i+1].equals("runt"))){
                Token tok = new Token(CBRT, 0);
                tokens.add(tok);
                i++;
                continue;
            }
            if ( strArray[i].equals("root") || strArray[i].equals("route") || strArray[i].equals("rut") || strArray[i].equals("rat")|| strArray[i].equals("runt")){
                Token tok = new Token(ROOT, 0);
                tokens.add(tok);
                continue;
            }

            if ((strArray[i].equals("ark") || strArray[i].equals("arc") )&& (strArray[i+1].equals( "sine") || strArray[i+1].equals("sign") || strArray[i+1].equals("sin"))){
                Token tok = new Token(ASIN, 0);
                tokens.add(tok);
                i++;
                continue;
            }

            if ( strArray[i].equals( "arcsine") || strArray[i].equals("arcsign")|| strArray[i].equals("arcsin")){
                Token tok = new Token(ASIN, 0);
                tokens.add(tok);
                continue;
            }

            if ( strArray[i].equals( "sine") || strArray[i].equals("sign") || strArray[i].equals("sin")){
                Token tok = new Token(SIN, 0);
                tokens.add(tok);
                continue;
            }

            if ((strArray[i].equals("ark") || strArray[i].equals("arc") )&& (strArray[i+1].equals( "cosine") || strArray[i+1].equals("cosign") || strArray[i+1].equals("cos")|| strArray[i+1].equals("cost") )){
                Token tok = new Token(ACOS, 0);
                tokens.add(tok);
                i++;
                continue;
            }

            if ( strArray[i].equals( "arccosine") || strArray[i].equals("arccosign") || strArray[i].equals("arccosin") || strArray[i].equals("arccos") || strArray[i].equals("arccost")){
                Token tok = new Token(ACOS, 0);
                tokens.add(tok);
                continue;
            }

            if ( strArray[i].equals( "cosine") || strArray[i].equals("cosign")|| strArray[i].equals("cosin") || strArray[i].equals("cos") || strArray[i].equals("cost") || strArray[i].equals("cause")){
                Token tok = new Token(COS, 0);
                tokens.add(tok);
                continue;
            }

            if ((strArray[i].equals("ark") || strArray[i].equals("arc") )&& (strArray[i+1].equals( "tan") || strArray[i+1].equals("tangent"))){
                Token tok = new Token(ATAN, 0);
                tokens.add(tok);
                i++;
                continue;
            }

            if ( strArray[i].equals( "arctan") || strArray[i].equals("arctangent")){
                Token tok = new Token(ATAN, 0);
                tokens.add(tok);
                continue;
            }

            if ( strArray[i].equals( "tan") || strArray[i].equals("tangent")){
                Token tok = new Token(TAN, 0);
                tokens.add(tok);
                continue;
            }

            if ( strArray[i].equals("negative")){
                Token tok = new Token(NEG, 0);
                tokens.add(tok);
                continue;
            }

            if ( strArray[i].equals("positive")){
                Token tok = new Token(POS, 0);
                tokens.add(tok);
                continue;
            }

            //Numbers
            if (Pattern.matches("[-+]?[0-9]*\\.?[0-9]+", strArray[i])){
                Token tok = new Token(NUMBERS, Float.parseFloat(strArray[i]));
                tokens.add(tok);
                continue;
            }

            //Special cases
            if  (strArray[i].equals("too") || strArray[i].equals("two")){
                Token tok = new Token(NUMBERS, 2);
                tokens.add(tok);
                continue;
            }

            if  (strArray[i].equals("won") || strArray[i].equals("one")){
                Token tok = new Token(NUMBERS, 1);
                tokens.add(tok);
                continue;
            }

            //This will catch no spaces between operators edge case
            if (Pattern.matches("[0-9]*\\.?[0-9]+[+-/*][0-9]*\\.?[0-9]+", strArray[i])){
                String [] tempResult = strArray[i].split("(?<=[+-/*])|(?=[+-/*])");
                System.out.println(tempResult[0]);

                Token tok = new Token(NUMBERS, Float.parseFloat(tempResult[0]));
                tokens.add(tok);
                switch(tempResult[1]){
                    case("+"):
                        tok = new Token(PLUS,0);
                        tokens.add(tok);
                        break;
                    case("-"):
                        tok = new Token(MINUS,0);
                        tokens.add(tok);
                        break;
                    case("/"):
                        tok = new Token(DIV,0);
                        tokens.add(tok);
                        break;
                    case("*"):
                        tok = new Token(MULT,0);
                        tokens.add(tok);
                        break;
                }
                tok = new Token(NUMBERS, Float.parseFloat(tempResult[2]));
                tokens.add(tok);
                continue;
            }

            if (strArray[i].equals("EOL")){
                Token tok = new Token(EOL, 0);
                tokens.add(tok);
                break;
            }
        }
    }

    public String output(){
        String output = "";
        for (int i = 0; i<tokens.size(); i++) {
            output += tokens.get(i).output() + " ";
        }
        return output;
    }

    //Used for debugging
    private void check(){
        for (Token t: tokens){
            t.check();
        }
    }

    public int getPosition(){
        return position -1;
    }

    //Pulls the current token and moves the position marker by one
    public Token getNextToken(){
        Token tok = tokens.get(position);
        position++;
        return tok;
    }



}
