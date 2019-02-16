package coinsleuth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;

/**
 *
 * @author Edward Conn
 */
class CoinFetcherClient {

        final static String SERVERNAME = "https://min-api.cryptocompare.com/data";
        final static String APIKEY = "&api_key=1fe77f4b6080bbc249c389035283479" 
                + "bdcfbe5551ef3ebd230ec00cd1951b2c8";
        
        protected JSONArray getAllCoinsJSON(){
            final String tickerSymbols = "BTC,ETH,XRP,LTC,EOS,BCH,USDT," 
                    +  "TRX,XLM,BNB,BSV,ADA,XMR,MIOTA,DASH";
            final String ALLCOINOPTION = "/pricemultifull?fsyms=" 
                    + tickerSymbols +"&tsyms=USD";
            StringBuilder coinsStringBuilder 
                    =  getURLRequest(SERVERNAME + ALLCOINOPTION + APIKEY);
            try{
                PrintWriter pw = new PrintWriter("allCoinInfo.txt");
                pw.print(coinsStringBuilder.toString());
                pw.close();
            }catch(IOException e){
                System.err.print(e);
            }
            return new JSONArray(coinsStringBuilder);
        }
        
        //data: The unix timestamp of interest 
        
        protected JSONArray getSingleCoinJSONHistoric(String Ticker, long date){
            if(date > 0 && Ticker.length() > 1){
                String coinOption = "pricehistorical?fsym="+ Ticker 
                        + "&tsyms=USD&ts=" + date;
                return new JSONArray(getURLRequest(SERVERNAME + coinOption + APIKEY));
            }
            return null;
        }
        
        protected StringBuilder getURLRequest(String urlString){
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
                
                StringBuilder allCoinString = copyInputStream(streamReader); 
                con.disconnect();
                return allCoinString;
                
            } catch (IOException e) {
                System.err.println(e);
            }
            return null;
        }
        
        protected StringBuilder copyInputStream(Reader streamReader){
            
            try{
                StringBuilder content;
                try (BufferedReader in = new BufferedReader(streamReader)) {
                    String inputLine;
                    content = new StringBuilder();
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
