package coinsleuth;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;


/**
 *
 * @author Edward Conn
 */
public abstract class JSONFetcherClient {
            
    public static String getURLRequest(URL url) {
        try {
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setReadTimeout(60 * 1000);
            con.setConnectTimeout(60 * 1000);

            int status = con.getResponseCode();
            Reader streamReader;

            if (status > 299) {
                streamReader = new InputStreamReader(con.getErrorStream());
            } else {
                streamReader = new InputStreamReader(con.getInputStream());
            }

            String jsonAsString = copyInputStream(streamReader).toString();
            con.disconnect();
            return jsonAsString;

        } catch (IOException e) {
            System.err.println(e);
        }
        return null;
    }

    public static String getURLRequest(URL url, String header) {
        try {
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-CMC_PRO_API_KEY", header);
            
            con.setReadTimeout(10000);
            con.setConnectTimeout(10000 * 6);

            int status = con.getResponseCode();
            Reader streamReader;

            if (status > 299) {
                streamReader = new InputStreamReader(con.getErrorStream());
            } else {
                streamReader = new InputStreamReader(con.getInputStream());
            }
            String jsonAsString = copyInputStream(streamReader).toString();
            con.disconnect();
            return jsonAsString;

        } catch (IOException e) {
            System.err.println(e);
        }
        return null;
    }
    
    private static StringBuilder copyInputStream(Reader streamReader) {
        try {
            StringBuilder content;
            try (BufferedReader in = new BufferedReader(streamReader)) {
                String inputLine;
                content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
            }
            return content;
        } catch (IOException e) {
            System.err.println("IOException: " + e);
        }
        return null;
    }
}