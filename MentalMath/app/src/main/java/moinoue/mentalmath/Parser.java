package moinoue.mentalmath;


/**
 * Created by Mohammed on 11/21/2016.
 */

public class Parser implements TokenType {
    Lexer lexer;
    Token currentToken;

    public Parser(Lexer lexer)
    {
        this.lexer = lexer;
        currentToken = lexer.getNextToken();
        print();
    }

    private void eat(Integer tokenType){
        if (currentToken.getType() == tokenType){
            currentToken = lexer.getNextToken();
        }
    }

    private AST factor(){
        Token token = currentToken;
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
        return null;
    }

    private AST term(){
        AST result = factor();

        while ((currentToken.getType() == MULT) || (currentToken.getType() == DIV) || (currentToken.getType() == MOD) || (currentToken.getType() == POW) || (currentToken.getType() == ROOT)) {
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

    //This function exist for testing purposes
    public void print(){
        System.out.println(expr().evaluate());
    }

    public AST Parsing(){
        return expr();
    }
}