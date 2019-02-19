package Tests;

import coinsleuth.CoinFetcherClient;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Edward Conn
 */
public final class NetworkJUnitTests {
    CoinFetcherClient cfc = new CoinFetcherClient();
    
    public NetworkJUnitTests() {}

    @Test
    public void httpUrlTest() {
        String coinStrOne = cfc.getURLRequest("https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=USD");
        System.out.println(coinStrOne);
        Assert.assertTrue("{\"USD\"".equals(coinStrOne.substring(0,6)));
    }
    
    @Test
    public void getallCoinUrlTest() {
        String coinStrTwo = cfc.getAllCoinsJSON();
        System.out.println(coinStrTwo);
        Assert.assertTrue("{\"RAW\"".equals(coinStrTwo.substring(0,6)));
    }
    
    
    @Test
    public void getCoinHistoricUrlTest() {
        String coinStrThree = cfc.getCoinsJSONHistoric("BTC", System.currentTimeMillis());
        System.out.println(coinStrThree);
        System.out.flush();
        Assert.assertTrue("{\"BTC\"".equals(coinStrThree.substring(0,6)));
    }
}
