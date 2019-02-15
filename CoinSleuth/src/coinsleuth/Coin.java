package coinsleuth;

/**
 *
 * @author Edward Conn
 */

public class Coin {
    private final String NAME;
    private final String TICKER;
    private long volume;
    private double price;
    private long supply;
    private double twentyFourHourChange;
    private double twelvehrChange;
    private double threeDayChange;
    private double sevenDayChange;
    private double monthChange;
    
    public Coin(String Name, String Ticker){
        this.NAME = Name;
        this.TICKER = Ticker;
        
    }
    
    @Override
    public String toString(){
        return NAME;
    }
    
}
