package moinoue.mentalmath;

/**
 * Copyright (c) 2016 Vidhya, Mohammed
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
        if (op.getType() == SIN){
            return (float)Math.sin((double)right.evaluate());
        }
        if (op.getType() == COS){
            return (float)Math.cos((double)right.evaluate());
        }
        if (op.getType() == TAN){
            return (float)Math.tan((double)right.evaluate());
        }
        if (op.getType() == ASIN){
            return (float)Math.asin((double)right.evaluate());
        }
        if (op.getType() == ACOS){
            return (float)Math.acos((double)right.evaluate());
        }
        if (op.getType() == ATAN){
            return (float)Math.atan((double)right.evaluate());
        }
        //Return an error if the code reaches here
        return 0;
    }
}
