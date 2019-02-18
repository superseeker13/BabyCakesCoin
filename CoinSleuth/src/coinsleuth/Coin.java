package coinsleuth;

import org.json.JSONObject;

/**
 *
 * @author Edward Conn
 */
public class Coin {

    private final String NAME;
    private final String TICKER;
    private final long VOLUMEDAY;
    private final double PRICE;
    private final long SUPPLY;

    public Coin(String Name, String Ticker,
            long volume, double price, long supply) {
        this.NAME = Name;
        this.TICKER = Ticker;
        this.VOLUMEDAY = volume;
        this.PRICE = price;
        this.SUPPLY = supply;
    }

    public Coin(JSONObject jsonObj) {
        assert jsonObj != null;
        this.NAME = jsonObj.getString("NAME");
        
        this.TICKER = jsonObj.getString("TICKER");
        this.VOLUMEDAY = jsonObj.getLong("VOLUMEDAY");
        this.PRICE = jsonObj.getDouble("PRICE");
        this.SUPPLY = jsonObj.getLong("SUPPLY");
    }

    @Override
    public String toString() {
        return this.NAME + " " + this.TICKER;
    }

    public JSONObject toJSON() {
        return new JSONObject(String.format("{NAME:%s,TICKER:%s,VOLUMEDAY:%d,PRICE:%.4f,SUPPLY%d}",
                 NAME, TICKER, VOLUMEDAY, PRICE, SUPPLY));
    }

    public String getNAME() {
        return NAME;
    }

    public String getTICKER() {
        return TICKER;
    }

    public long getVolume() {
        return VOLUMEDAY;
    }

    public double getPrice() {
        return PRICE;
    }

    public long getSupply() {
        return SUPPLY;
    }
}
