package moinoue.mentalmath;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by VidhyaLakshmi on 11/20/2016.
 */

public class Lexer implements TokenType {
    ArrayList<Token> tokens;
    String input;
    int position;

    public Lexer(String input){
        this.input = input+= " EOL" ;
        this.position = 0;
        this.tokens = new ArrayList<Token>();
        constructToken();
        check();
    }


    private void constructToken(){
        String[] strArray = input.split("\\s");
        
        for (int i = 0; i<strArray.length; i++){
            /*switch(str){
                case "log":

            }*/

            if (strArray[i].equals("plus")|| strArray[i].equals("+") || strArray[i] .equals("added") ){
                Token tok = new Token(PLUS, 0);
                tokens.add(tok);
                continue;
            }
            if (strArray[i].equals("minus")|| strArray[i].equals("-") || strArray[i].equals("subtracted" )){
                Token tok = new Token(MINUS, 0);
                tokens.add(tok);
                continue;
            }
            if (strArray[i].equals("times")|| strArray[i].equals("*") || strArray[i].equals("x") || strArray[i].equals("product") || strArray[i].equals("into")){
                Token tok = new Token(PRODUCT, 0);
                tokens.add(tok);
                continue;
            }
            if (strArray[i].equals("divide")|| strArray[i].equals("/") || strArray[i].equals("over") || strArray[i].equals("divided" )){
                //TODO: if we want to add divided by, make the change later
                Token tok = new Token(DIVIDE, 0);
                tokens.add(tok);
                continue;
            }
            if (strArray[i].equals("mod")|| strArray[i].equals("modulus" ) || strArray[i].equals( "%" )){
                Token tok = new Token(MOD, 0);
                tokens.add(tok);
                continue;
            }
            if (strArray[i].equals("log") || strArray[i].equals("logrithm") ){
                Token tok = new Token(LOG, 0);
                tokens.add(tok);
                continue;
            }
            if (strArray[i].equals("exp" ) || strArray[i].equals( "exponent") ){
                Token tok = new Token(EXP, 0);
                tokens.add(tok);
                continue;
            }
            if (strArray[i].equals("power") || strArray[i].equals("pow") ){
                Token tok = new Token(POWER, 0);
                tokens.add(tok);
                continue;
            }

            if ( strArray[i].equals("square" )&& strArray[i+1].equals( "root") ){
                    Token tok = new Token(SQRT, 0);
                    tokens.add(tok);
                    System.out.println("initial i = "+ i);
                    i++;
                    System.out.println("final i = "+ i);
                    continue;

            }
            if ( strArray[i].equals("cube") && strArray[i+1].equals( "root") ){
                Token tok = new Token(CBRT, 0);
                tokens.add(tok);
                System.out.println("initial i = "+ i);
                i++;
                System.out.println("final i = "+ i);
                continue;
            }
            if ( strArray[i].equals("root" )){
                    Token tok = new Token(ROOT, 0);
                    tokens.add(tok);
                    System.out.println("initial root i = "+ i);
                    continue;
            }
            //Numbers
            if (Pattern.matches("[0-9]*.[0-9]+", strArray[i])){
                Token tok = new Token(NUMBERS, Float.parseFloat(strArray[i]));
                tokens.add(tok);
                continue;
            }

            if (Pattern.matches("[0-9]+", strArray[i])){
                Token tok = new Token(NUMBERS, Float.parseFloat(strArray[i]));
                tokens.add(tok);
                continue;
            }

            if (strArray[i].equals("EOL")){
                break;
            }

        }


    }

    private void check(){
        for (Token t: tokens){
            t.check();
        }
    }

    public Token getNextToken(){
        Token tok = tokens.get(position);
        position++;
        return tok;
    }



}
