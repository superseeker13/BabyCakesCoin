package coinsleuth;

import java.net.HttpURLConnection;
import java.net.URL;

import java.io.Reader;
import java.io.InputStreamReader;
import java.io.BufferedReader;
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

    private static final class CoinFetcherClient {

        final static String SERVERNAME = "https://min-api.cryptocompare.com/data";
        final static String APIKEY = "1fe77f4b6080bbc249c389035283479bdcfbe5551ef3ebd230ec00cd1951b2c8";

        CoinFetcherClient() {
            getCoinJSON(SERVERNAME + "/pricemultifull?fsyms=BTC&tsyms=USD");
        }
        
        /*
            @return StringBuffer of JSON from url.
        */
        StringBuffer getCoinJSON(String urlString){
            try {
                URL url = new URL(urlString);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");

                int status = con.getResponseCode();
                Reader streamReader;
                
                if (status > 299) {
                    streamReader = new InputStreamReader(con.getErrorStream());
                } else {
                    streamReader = new InputStreamReader(con.getInputStream());
                }
                
                StringBuffer strBuff = readHTTPInput(streamReader);
                con.disconnect();
                return strBuff;
                
            } catch (IOException e) {
                System.err.println(e);
            }
            return null;
        }
        
        /*
            Copies the input stream from the url.
        */
        StringBuffer readHTTPInput(Reader streamReader){
            try{
                StringBuffer content;
                try (BufferedReader in = new BufferedReader(streamReader)) {
                    String inputLine;
                    content = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine);
                    }
                }
                return content;
            }catch(IOException e){
                System.err.println("IOException: " + e);
            }
            return null;
        }

    }
}
