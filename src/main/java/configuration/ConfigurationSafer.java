package src.main.java.configuration;

public class ConfigurationSafer {

    private static ConfigurationSafer configSafer;
    private int speed = 100;

    public ConfigurationSafer getInstance(){
        if (configSafer == null) configSafer = new ConfigurationSafer();
        return  configSafer;
    }

    private ConfigurationSafer(){

    }

    public void setSpeed(int speed){
        this.speed = speed;
    }

}
