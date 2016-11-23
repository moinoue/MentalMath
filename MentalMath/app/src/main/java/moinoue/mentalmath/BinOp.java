package moinoue.mentalmath;

/**
 * Created by Mohammed on 11/23/2016.
 */

public class BinOp extends AST {
    private AST left;
    private Token op;
    private AST right;

    public BinOp(AST left, Token op, AST right){
        this.left = left;
        this.op = op;
        this.right = right;
    }
}
