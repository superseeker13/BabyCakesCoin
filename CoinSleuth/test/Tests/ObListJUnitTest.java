package Tests;
import coinsleuth.CoinList;
import coinsleuth.ArticleList;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Edward Conn
 */
public class ObListJUnitTest{
    
    public ObListJUnitTest() {}
    
    @Test
    public void initializeCoinListTest() {
        final CoinList CL = new CoinList();
        Assert.assertTrue(!CL.getObservableList().isEmpty());
        Assert.assertTrue(CL.getObservableList().get(0).getTICKER() != null);
        System.out.println(CL.getObservableList().get(0).getPrice());
        Assert.assertTrue(CL.getObservableList().get(0).getPrice()!= 0.0);
    }
    
    @Test
    public void initializeArticleListTest() {    
        final ArticleList AL = new ArticleList();
        Assert.assertTrue(!AL.getObservableList().isEmpty());
        System.out.println(AL.getObservableList().get(0).getTitle());
        System.out.println(AL.getObservableList().get(0).getURL());
        Assert.assertTrue(AL.getObservableList().get(0).getURL() != null);
    }
}