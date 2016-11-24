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
        Parsing();
    }

    private void eat(Integer tokenType){
        if (currentToken.getType() == tokenType){
            currentToken = lexer.getNextToken();
        }
    }

    private AST factor(){
        Token token = currentToken;
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

        while ((currentToken.getType() == MULT) || (currentToken.getType() == DIV)) {
            Token token = currentToken;
            if (token.getType() == MULT) {
                eat(MULT);
            }
            if (token.getType() == DIV) {
                eat(DIV);
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

    public AST Parsing(){
        return expr();
    }
}