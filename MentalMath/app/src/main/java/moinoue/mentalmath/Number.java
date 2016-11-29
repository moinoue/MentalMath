package moinoue.mentalmath;

/**
 * Copyright (c) 2016 Vidhya, Mohammed
 */

public class Number extends AST {
    private Token token;
    private float value;

    public Number(Token token){
        this.token = token;
        this.value = token.getValue();
    }

    public float evaluate(){
        return value;
    }
}
