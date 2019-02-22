package coinsleuth;

import org.json.JSONObject;

/**
 *
 * @author Edward Conn
 */
public class Coin {

    private final String TICKER;
    private final double VOLUMEDAY;
    private final double PRICE;
    private final double SUPPLY;

    public Coin(String Ticker, double volume, double price, double supply) {
        this.TICKER = Ticker;
        this.VOLUMEDAY = volume;
        this.PRICE = price;
        this.SUPPLY = supply;
    }

    public Coin(final String TICKER, final JSONObject jsonObj) {
        this.TICKER = TICKER;
        this.VOLUMEDAY = jsonObj.getDouble("VOLUMEDAY");
        this.PRICE = jsonObj.getDouble("PRICE");
        this.SUPPLY = jsonObj.getDouble("SUPPLY");
    }

    public JSONObject toJSON() {
        return new JSONObject(String.format("{TICKER:%s,VOLUMEDAY:%d,PRICE:%.4f,SUPPLY%d}",
                TICKER, VOLUMEDAY, PRICE, SUPPLY));
    }

    public String getTICKER() {
        return TICKER;
    }

    public double getVolume() {
        return VOLUMEDAY;
    }

    public double getPrice() {
        return PRICE;
    }

    public double getSupply() {
        return SUPPLY;
    }
}
