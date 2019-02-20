package coinsleuth;
import org.json.JSONObject;

/**
 *
 * @author Edward Conn
 */
public class CoinArticle {   
    String Author;
    String Title;
    String Description;
    String URL;
        
    public CoinArticle() {
    }

    public CoinArticle(final JSONObject jsonObj) {

    }

    public JSONObject toJSON() {
        return new JSONObject(String.format(""));
    }
}

