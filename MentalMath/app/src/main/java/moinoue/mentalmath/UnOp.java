package moinoue.mentalmath;

/**
 * Created by Mohammed on 11/23/2016.
 * Unary Operator
 */

public class UnOp  extends AST implements TokenType{
    private Token op;
    private AST right;

    public UnOp(Token op, AST right){
        this.op = op;
        this.right = right;
    }

    public float evaluate(){
        float value = right.evaluate();
        if (op.getType() == POS){
            if (value <=0){
                value = value * -1;
                return value;
            }
            return value;
        }
        if (op.getType() == NEG){
            if (value >=0){
                value = value * -1;
                return value;
            }
            return value;
        }
        if (op.getType() == LOG){
            return (float)Math.log((double)right.evaluate());
        }
        if (op.getType() == EXP){
            return (float)Math.exp((double)right.evaluate());
        }
        if (op.getType() == SQRT){
            return (float)Math.sqrt((double)right.evaluate());
        }
        if (op.getType() == CBRT){
            return (float)Math.cbrt((double)right.evaluate());
        }
        return 0;
    }
}
