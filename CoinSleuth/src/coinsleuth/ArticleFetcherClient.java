package coinsleuth;

import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author Edward Conn
 */
public class ArticleFetcherClient extends JSONFetcherClient {
    private final static String SERVERNAME = "https://newsapi.org/v2/everything?q=crypto";
    private final static String APIKEY = "&apiKey=aafaf085a6aa43eb859d78bbaf509ff1";
    
    public String getArticlesJSON(){
        String result = "";
        try {
            result = getURLRequest(new URL(SERVERNAME + APIKEY));
        } catch (MalformedURLException ex) {
            System.err.println(ex);
        }
        return result;
    }
}
