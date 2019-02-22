package coinsleuth;

import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Edward Conn
 */
public class ArticleList extends UpdatedList<CoinArticle> {
    private static final ArticleFetcherClient AFC = new ArticleFetcherClient();

    @Override
    public List<CoinArticle> updateList() {
        JSONObject currentJsObj = new JSONObject(AFC.getArticlesJSON());
        return parseJSON(currentJsObj.getJSONArray("articles"));
    }

    private List<CoinArticle> parseJSON(JSONArray jsArr) {
        List<CoinArticle> result = new LinkedList<>();
        for (Object jsObj : jsArr) {
            JSONObject currentCoinArt = (JSONObject) jsObj;
            result.add(new CoinArticle(currentCoinArt));
        }
        return result;
    }
}
