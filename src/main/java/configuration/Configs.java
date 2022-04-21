package src.main.java.configuration;

public class Configs {

    private static Configs configs;
    private int speed = 100;

    public static Configs getInstance(){
        if (configs == null) configs = new Configs();
        return configs;
    }

    private Configs(){

    }

    public void setSpeed(int speed){
        this.speed = speed;
    }

    public int getSpeed(){
        return this.speed;
    }
}
