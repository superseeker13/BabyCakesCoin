package coinsleuth;

import java.io.UnsupportedEncodingException;
import org.json.JSONObject;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

/**
 *
 * @author Edward Conn
 */
public class CoinFetcherClient extends JSONFetcherClient {

    private final static String CRYPTOURLString = "https://min-api.cryptocompare.com/data";
    private final static String CAPURLString = "https://pro-api.coinmarketcap.com/"
            + "v1/cryptocurrency/listings/latest";
    private final static String CAPAPIKEY = "b8a79d5d-5160-4442-9e58-a3bd5c32ef97";
    private static ArrayList<String> NameList = new ArrayList<>();
    private static ArrayList<String> TickerList = new ArrayList<>();

    public static List<String> getNameList() {
        return NameList;
    }

    public static List<String> getTickerList() {
        return TickerList;
    }

    public CoinFetcherClient() {
        String jsString = "";
        try {
            jsString = getURLRequest(new URL(CAPURLString), CAPAPIKEY);
            //System.out.println(jsString);//For testing
        } catch (MalformedURLException ex) {
            System.err.println(ex);
        }
        JSONArray coinInfo = new JSONObject(jsString).getJSONArray("data");
        Iterator JSONIter = coinInfo.iterator();
        while(JSONIter.hasNext()){
            JSONObject jsObj = (JSONObject) JSONIter.next();
            String name = jsObj.getString("name");
            String symbol = jsObj.getString("symbol");
            System.out.println(jsObj.getInt("id") + " " + name + " " + symbol);
            NameList.add(name);
            TickerList.add(symbol);
        }
    }

    private String TickerListToString() {
        StringBuilder result = new StringBuilder();
        TickerList.forEach((ticker) -> {
            result.append(ticker).append(",");
        });
        return result.toString();
    }

    //Returns a string contianing the json info of all coins
    public String getAllCoinsJSON() {
        String TickerSymbols = TickerListToString();
        final String ALLCOINOPTION = "/pricemultifull?fsyms="
                + TickerSymbols + "&tsyms=USD";
        try {
            return getURLRequest(new URL(CRYPTOURLString + ALLCOINOPTION));
        } catch (MalformedURLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    //Returns a string contianing the json price of one coin
    //data: The unix timestamp of interest 
    public String getCoinsJSONHistoric(String Tickers, long date) {
        if (date > 0 && Tickers.length() > 1 && Tickers.length() < 6) {
            String coinOption = "/pricehistorical?fsym=" + Tickers
                    + "&tsyms=USD&ts=" + date;
            try {
                return getURLRequest(new URL(CRYPTOURLString + coinOption));
            } catch (MalformedURLException ex) {
                System.out.println(ex);
            }
        }
        return null;
    }
}
