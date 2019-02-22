package coinsleuth;

import java.util.Iterator;
import org.json.JSONObject;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Edward Conn
 */
public class CoinList extends UpdatedList<Coin> {
    private static final CoinFetcherClient CFC = new CoinFetcherClient();

    @Override
    public List<Coin> updateList() {
        String str = CFC.getAllCoinsJSON();
        JSONObject raw = new JSONObject(str).getJSONObject("RAW");
        return parseJSON(raw);
    }

    private List<Coin> parseJSON(JSONObject jsObj) {
        List<Coin> result = new LinkedList<Coin>();
        Iterator<String> keyIter = jsObj.keys();
        while (keyIter.hasNext()) {
            String key = keyIter.next();
            JSONObject currentCoin = jsObj.getJSONObject(key);
            JSONObject currentUSDCOIN = currentCoin.getJSONObject("USD");
            System.out.println(key);
            //System.out.println(currentCoin.toString());
            Coin newCoin = new Coin(key, currentUSDCOIN);
            result.add(newCoin);
        }
        return result;
    }
}
