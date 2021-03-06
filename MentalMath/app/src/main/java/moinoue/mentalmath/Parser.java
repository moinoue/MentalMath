package moinoue.mentalmath;


/**
 * Copyright (c) 2016 Vidhya, Mohammed
 */

public class Parser implements TokenType {
    private Lexer lexer;
    private Token currentToken;
    private ErrorHandling errorHandling;

    public Parser(Lexer lexer, ErrorHandling errorHandling)
    {
        this.lexer = lexer;
        this.errorHandling = errorHandling;
        currentToken = lexer.getNextToken();
    }

    private void eat(Integer tokenType){
            if (currentToken.getType() == tokenType) {
                currentToken = lexer.getNextToken();
            }else {
                Token temp = new Token(tokenType,0);
                errorHandling.errorAdd("Expected a token '" + temp.output() + "' at position: " + lexer.getPosition());
            }
    }

    private AST factor(){
        Token token = currentToken;
        if (token.getType() == LN){
            eat(LN);
            return new UnOp(token,factor());
        }
        if (token.getType() == LOG){
            eat(LOG);
            return new UnOp(token,factor());
        }
        if (token.getType() == SQRT){
            eat(SQRT);
            return new UnOp(token,factor());
        }
        if (token.getType() == CBRT){
            eat(CBRT);
            return new UnOp(token,factor());
        }
        if (token.getType() == EXP){
            eat(EXP);
            return new UnOp(token,factor());
        }
        if (token.getType() == NEG){
            eat(NEG);
            return new UnOp(token,factor());
        }
        if (token.getType() == POS){
            eat(POS);
            return new UnOp(token,factor());
        }
        if (token.getType() == SIN){
            eat(SIN);
            return new UnOp(token,factor());
        }
        if (token.getType() == COS){
            eat(COS);
            return new UnOp(token,factor());
        }
        if (token.getType() == TAN){
            eat(TAN);
            return new UnOp(token,factor());
        }
        if (token.getType() == ASIN){
            eat(ASIN);
            return new UnOp(token,factor());
        }
        if (token.getType() == ACOS){
            eat(ACOS);
            return new UnOp(token,factor());
        }
        if (token.getType() == ATAN){
            eat(ATAN);
            return new UnOp(token,factor());
        }
        if (token.getType() == NUMBERS){
            eat(NUMBERS);
            return new Number(token);
        }
        if (token.getType() == LPAR){
            eat(LPAR);
            AST result = expr();
            eat(RPAR);
            return result;
        }
        errorHandling.errorAdd("Expected a literal at position: " + lexer.getPosition());
        return new Number(new Token(0,0));
    }

    private AST term(){
        AST result = factor();

        while  ((currentToken.getType() == MULT) || (currentToken.getType() == DIV) || (currentToken.getType() == MOD) || (currentToken.getType() == POW) || (currentToken.getType() == ROOT) || (currentToken.getType() == PAR)) {
            Token token = currentToken;
            if (token.getType() == MULT) {
                eat(MULT);
            }
            if (token.getType() == DIV) {
                eat(DIV);
            }
            if (token.getType() == MOD) {
                eat(MOD);
            }
            if (token.getType() == POW) {
                eat(POW);
            }
            if (token.getType() == ROOT) {
                eat(ROOT);
            }
            if (token.getType() == PAR){
                eat(PAR);
            }
            result = new BinOp(result, token, factor());
        }
        return result;
    }

    private AST expr(){
        AST result = term();

        while ((currentToken.getType() == PLUS) || (currentToken.getType() == MINUS)){
            Token token = currentToken;
            if (token.getType() == PLUS) {
                eat(PLUS);
            }
            if (token.getType() == MINUS) {
                eat(MINUS);

            }
            result = new BinOp(result, token, term());
        }

        return result;
    }

    public String print(){
        float result = expr().evaluate();
        //Check if there is still tokens left
        if (currentToken.getType() != EOL){
            errorHandling.errorAdd("Unexpected token '" + currentToken.output() + "' at position: " + lexer.getPosition());
        }
        return Float.toString(result);
    }
}
