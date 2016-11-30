package moinoue.mentalmath;

/**
 * Copyright (c) 2016 Vidhya, Mohammed
 * Binary Operators
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
        if (op.getType() == MOD){
            return left.evaluate() % right.evaluate();
        }
        if (op.getType() == POW){
            return (float)(Math.pow((double)left.evaluate(),(double)right.evaluate()));
        }
        if (op.getType() == ROOT){
            return (float)(Math.pow(Math.abs((double)right.evaluate()),(1/(double)left.evaluate())));
        }
        //Should not reach this code bring up error if you do
        return 0;
    }
}
