package lasa.urbanheatisland;

/**
 * Created by S2095786 on 4/24/2017.
 */
public class DataPoint {
    String id;
    String shadeType;
    Boolean isshade;
    String groundType;
    Double temperatureInput;

    public DataPoint(){


    }

    public DataPoint(String id, String shade ,Boolean isShade, String ground, Double temp){

        this.id = id;
        this.shadeType = shade;
        this.isshade = isShade;
        this.groundType = ground;
        this.temperatureInput = temp;



    }
}
