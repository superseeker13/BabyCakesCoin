package Tests;
import coinsleuth.CoinList;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Edward Conn
 */
public class CoinListJUnitTest{
    private static final CoinList CL = new CoinList();
    
    public CoinListJUnitTest() {}
    
    @Test
    public void initializeListTest() {
        System.out.print(CL.coinList);
        Assert.assertTrue(!CL.coinList.isEmpty());
    }
}
