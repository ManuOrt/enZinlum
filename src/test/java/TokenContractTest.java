import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import principal.address.Address;
import principal.contract.TokenContract;

public class TokenContractTest
{
    TokenContract ricknillos;
    Address morty;
    Address rick;

    @Before
    public void setUp(){
        morty = new Address();
        rick = new Address();
        ricknillos = new TokenContract(morty);
        ricknillos.addOwner(morty.getMyPublicK(), 100d);
        Assert.assertEquals(1, ricknillos.getmBalances().size());
    }

    @Test
    public void addOwner_test() {
        ricknillos.addOwner(morty.getMyPublicK(), 0d);
        Assert.assertEquals(2, ricknillos.getmBalances().size());

        Assert.assertEquals(100, ricknillos.getmBalances().get(rick.getMyPublicK()), 0d);
        ricknillos.addOwner(rick.getMyPublicK(), 500d);
        Assert.assertEquals(100, ricknillos.getmBalances().get(rick.getMyPublicK()), 0d);
    }

    @Test
    public void balanceOfTest(){

    }
}
