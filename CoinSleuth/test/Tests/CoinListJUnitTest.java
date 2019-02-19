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
        Assert.assertTrue(!CL.coinList.isEmpty());
        Assert.assertTrue(CL.coinList.get(0).getTICKER() != null);
        System.out.println(CL.coinList.get(0).getPrice());
        Assert.assertTrue(CL.coinList.get(0).getPrice()!= 0.0);
    }
}
