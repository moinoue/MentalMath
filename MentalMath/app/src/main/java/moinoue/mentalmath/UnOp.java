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
        if (op.getType() == POS){
            float check = right.evaluate();
            if (check <=0){
                check = check * -1;
                return check;
            }
            return check;
        }
        if (op.getType() == NEG){
            float check = right.evaluate();
            if (check >=0){
                check = check * -1;
                return check;
            }
            return check;
        }
        //TODO: add in evaluation for LOG, EXP, SQRT, and CBRT
        return 0;
    }
}
