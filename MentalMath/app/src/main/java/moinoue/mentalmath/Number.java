package moinoue.mentalmath;

/**
 * Created by Mohammed on 11/23/2016.
 */

public class Number extends AST {
    private Token token;
    private float value;

    public Number(Token token){
        this.token = token;
        this.value = token.getValue();
    }
}
