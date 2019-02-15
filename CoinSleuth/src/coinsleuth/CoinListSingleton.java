package coinsleuth;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Observer;

/**
 *
 * @author Edward Conn
 */
public class CoinListSingleton {
    private static final CoinFetcherClient CFC = new CoinFetcherClient();
    private static List<Observer> observersList = new LinkedList<Observer>();
    
    private CoinListSingleton() {
        
    }
    
    public static CoinListSingleton getInstance() {
        return CoinListSingletonHolder.INSTANCE;
    }
    
    private static class CoinListSingletonHolder {

        private static final CoinListSingleton INSTANCE = new CoinListSingleton();
    }
    
    private static class CoinFetcherClient{
        final static String SERVERNAME = "https://api.iextrading.com/1.0";
        
    }
}

