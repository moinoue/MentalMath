package moinoue.mentalmath;

import java.util.ArrayList;

/**
 * Created by Mohammed on 11/24/2016.
 */

public class ErrorHandling {
    private int errorCount;
    private ArrayList<String> errors;

    public ErrorHandling(){
        errorCount = 0;
        errors = new ArrayList<String>();
    }

    public boolean hasError(){
        System.out.println("errorcount 2: " + errorCount);
        if (errorCount > 0){
            return true;
        }
        return false;
    }

    public String getErrors(){
        String output = "";

        for (int i = 0; i < errors.size(); i++){
            output += errors.get(i);
            output += "\n";
        }

        return output;
    }

    public void errorAdd(String string){
        errorCount += 1;
        System.out.println("errorcount: " + errorCount);
        System.out.println(string);
        errors.add(string);
    }

}
