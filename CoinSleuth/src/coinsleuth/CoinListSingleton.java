package coinsleuth;

import org.json.JSONArray;

import java.util.LinkedList;
import java.util.List;
import java.util.Observer;
import java.util.Observable;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Edward Conn
 */
public class CoinListSingleton extends Observable {

    private static final CoinFetcherClient CFC = new CoinFetcherClient();
    private static List<Coin> coinList = new LinkedList<>();

    private CoinListSingleton() {
        updateCoinList();
        
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                updateCoinList();
            }
        }, 0, 60, TimeUnit.SECONDS);
    }

    public static CoinListSingleton getInstance() {
        return CoinListSingletonHolder.INSTANCE;
    }

    private static class CoinListSingletonHolder {
        private static final CoinListSingleton INSTANCE = new CoinListSingleton();
    }

    private static List<Coin> updateCoinList() {
        JSONArray coinArray = CFC.getAllCoinsJSON();
        List<Coin> result = new LinkedList<>();

        return result;
    }
}
