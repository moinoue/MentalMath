package moinoue.mentalmath;

/**
 * Created by VidhyaLakshmi on 11/20/2016.
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
    //Used for debugging purpose
    public void check(){
        System.out.println("type = "+ type + " value = "+value);
    }
}
