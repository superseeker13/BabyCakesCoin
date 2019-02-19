package coinsleuth;

import java.util.Iterator;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Edward Conn
 */
public class CoinList extends Observable {

    private static final CoinFetcherClient CFC = new CoinFetcherClient();
    public List<Coin> coinList; //Hack fix.

    
//Initializer establishes update thread.  Updates every 60 seconds.
    public CoinList() {
        coinList = updateCoinList();
        //Updates the List every 60 seconds.
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(() -> {
            coinList = updateCoinList();
            setChanged();
            notifyObservers();
        }, -1L, 60L, TimeUnit.SECONDS);

    }

    //Calls the client and populates the 
    //coinlist with updated values
    
    private List<Coin> updateCoinList() {
        List<Coin> result = new LinkedList<>();
        String str = CFC.getAllCoinsJSON();
        //System.out.println(str);
        JSONObject raw = new JSONObject(str).getJSONObject("RAW");

        Iterator<String> keyIter = raw.keys();
        while (keyIter.hasNext()) {
            String key = keyIter.next();
            JSONObject currentCoin = raw.getJSONObject(key);
            JSONObject currentUSDCOIN = currentCoin.getJSONObject("USD");
            System.out.println(key);
            System.out.println(currentCoin.toString());
            result.add(new Coin(key, currentUSDCOIN));
        }
        return result;
    }
}
