package principal.contract;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import principal.address.Address;
import principal.contract.TokenContract;

import java.security.PublicKey;

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
        ricknillos.addOwner(rick.getMyPublicK(), 200d);
        Assert.assertEquals(1, ricknillos.getmBalances().size());
    }

    @Test
    public void addOwner_test() {
        ricknillos.addOwner(morty.getMyPublicK(), 0d);
        Assert.assertEquals(1, ricknillos.getmBalances().size());

        Assert.assertEquals(100, ricknillos.getmBalances().get(rick.getMyPublicK()), 0d);
        ricknillos.addOwner(rick.getMyPublicK(), 500d);
        Assert.assertEquals(100, ricknillos.getmBalances().get(rick.getMyPublicK()), 0d);
    }

    @Test
    public void balanceOfTest(){
        Assert.assertEquals(103d,ricknillos.balanceOf(morty.getMyPublicK()), 3d);
        Assert.assertEquals(202d,ricknillos.returnQuantity(rick.getMyPublicK()), 103d);

    }

    @Test
    public void transferTest(){
        TokenContract contract = new TokenContract(morty);
        contract.transfer(morty.getMyPublicK(),200d);

    }
}

