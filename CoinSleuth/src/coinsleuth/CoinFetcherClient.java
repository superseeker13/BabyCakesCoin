package coinsleuth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Edward Conn
 */
public class CoinFetcherClient {

        //Base url string.
        final static String SERVERNAME = "https://min-api.cryptocompare.com/data";        
        
        //Creates a url to pull the top 15 crypto coins
        //Then calls url request to grab the data.
        
        public String getAllCoinsJSON(){
            final String tickerSymbols = "BTC,ETH,XRP,LTC,EOS,BCH,USDT,"; 
                    //+  "TRX,XLM,BNB,BSV,ADA,XMR,MIOTA,DASH";
            final String ALLCOINOPTION = "/pricemultifull?fsyms=" 
                    + tickerSymbols +"&tsyms=USD";
            String coinsString 
                    =  getURLRequest(SERVERNAME + ALLCOINOPTION);
            return coinsString;
            //.substring(0, coinsString.indexOf(",\"DISPLAY"));
        }
        
        //data: The unix timestamp of interest 
        //Return the price of a given crypto on a given day.
        
        public String getCoinsJSONHistoric(String Tickers, long date){
            if(date > 0 && Tickers.length() > 1 && Tickers.length() < 6){
                String coinOption = "/pricehistorical?fsym=" + Tickers + "&tsyms=USD&ts=" + date;
                return getURLRequest(SERVERNAME + coinOption);    
            }
            return null;
        }
        
        //Fetches the data from the given url/
        public synchronized String getURLRequest(String urlString){
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
                
                String allCoinString = copyInputStream(streamReader).toString();
                con.disconnect();
                return allCoinString;
                
            } catch (IOException e) {
                System.err.println(e);
            }
            return null;
        }
        
        
        //Copies the inputstream created by the get request.
        private synchronized StringBuilder copyInputStream(Reader streamReader){
            try{
                StringBuilder content;
                try (BufferedReader in = new BufferedReader(streamReader)) {
                    String inputLine;
                    content = new StringBuilder();
                    while ((inputLine = in.readLine()) != null ) {
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
