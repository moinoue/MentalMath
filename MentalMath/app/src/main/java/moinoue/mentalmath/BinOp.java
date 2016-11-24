package moinoue.mentalmath;

/**
 * Created by Mohammed on 11/23/2016.
 */

public class BinOp extends AST implements TokenType {
    private AST left;
    private Token op;
    private AST right;

    public BinOp(AST left, Token op, AST right){
        this.left = left;
        this.op = op;
        this.right = right;
    }

    public float evaluate(){
        if (op.getType() == PLUS){
            return left.evaluate() + right.evaluate();
        }
        if(op.getType() == MINUS){
            return left.evaluate() - right.evaluate();
        }
        if (op.getType() == MULT){
            return left.evaluate() * right.evaluate();
        }
        if(op.getType() == DIV){
            return left.evaluate() / right.evaluate();
        }

        //Should not reach this code
        return 0;
    }
}
