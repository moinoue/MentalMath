package moinoue.mentalmath;

/**
 * Created by VidhyaLakshmi on 11/20/2016.
 */
/*
    //Token type "int"
    numbers 0
    plus    1
    minus   2
    pdt     3
    divide  4
    mod     5
    log     6
    exp     7
    sqrt    8


 */
public class Token {
    private float value;
    private int type;

    public Token(int type, float value){
        this.value = value;
        this.type = type;
    }

    public float getValue(){
        return this.value;
    }
    public int getType(){
        return this.type;
    }
    public void check(){
        System.out.println("type = "+ type + " value = "+value);
    }
}
