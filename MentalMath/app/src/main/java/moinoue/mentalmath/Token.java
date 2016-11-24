package moinoue.mentalmath;

import android.media.session.MediaSession;

/**
 * Created by VidhyaLakshmi on 11/20/2016.
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
