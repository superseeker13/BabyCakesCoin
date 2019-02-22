package coinsleuth;
import org.json.JSONObject;

/**
 *
 * @author Edward Conn
 */
public class CoinArticle {
    final String Title;
    final String Desc; // Description
    final String URL;

    public CoinArticle(final JSONObject jsonObj) {
        this.Title = jsonObj.getString("title");
        this.Desc = jsonObj.getString("description");
        this.URL = jsonObj.getString("url");
    }

    public JSONObject toJSON() {
        return new JSONObject(String.format("{%s,%s,%s}", Title,Desc,URL));
    }

    public String getTitle() {
        return Title;
    }

    public String getDesc() {
        return Desc;
    }

    public String getURL() {
        return URL;
    }
}