package moinoue.mentalmath;

import android.media.session.MediaSession;

/**
 * Copyright (c) 2016 Vidhya, Mohammed
 */

public class Token implements TokenType {
    private float value;
    private int type;

    public Token(int type, float value){
        this.value = value;
        this.type = type;
    }

    public String output(){
        if (type == NUMBERS){
            return Float.toString(value);
        }
        if (type == PLUS){
            return "+";
        }
        if (type == MINUS){
            return "-";
        }
        if (type == MULT){
            return "*";
        }
        if (type == DIV){
            return "/";
        }
        if (type == MOD){
            return "%";
        }
        if (type == LOG){
            return "Log";
        }
        if (type == EXP){
            return "exp";
        }
        if (type == POW){
            return "^";
        }
        if (type == SQRT){
            return "sqrt";
        }
        if (type == CBRT){
            return "cbrt";
        }
        if (type == ROOT){
            return "root";
        }
        if (type == RPAR){
            return ")";
        }
        if (type == LPAR){
            return "(";
        }
        if (type == NEG){
            return "-";
        }
        if (type == POS){
            return "+";
        }
        if (type == SIN){
            return "sin";
        }
        if (type == COS){
            return "cos";
        }
        if (type == TAN){
            return "tan";
        }
        if (type == ASIN){
            return "arc sin";
        }
        if (type == ACOS){
            return "arc cos";
        }
        if (type == ATAN){
            return "arc tan";
        }
        if (type == LN){
            return "ln";
        }
        if (type == PAR){
            return "percent of";
        }
        return "";
    }

    public float getValue(){
        return this.value;
    }
    public int getType(){
        return this.type;
    }
    //Used for debugging purpose
    public void check(){
        System.out.println("type = "+ type + " value = "+value);
    }
}
