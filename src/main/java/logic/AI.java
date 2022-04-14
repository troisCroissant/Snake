package src.main.java.logic;

public class AI {

    private static AI ai;

    public static AI getInstance(){
        if (ai == null) ai = new AI();
    return ai;
    }

    private AI(){

    }



}
