import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import principal.address.Address;
import principal.keys.GenSig;

import java.security.KeyPair;

public class AddressTest
{
    Address rick;

    @Before
    public void setUp(){
        rick = new Address();
    }

    @Test
    public void constructorTest(){
        Assert.assertNotNull(rick);
        Assert.assertEquals(5d, rick.getbEnzinium(), 5);
    }

    @Test
    public void generateKeyParTest(){
        rick.generateKeyPair();
        Assert.assertNotNull(rick.getMyPublicK());
        Assert.assertNotNull(rick.getMyPrivateK());
    }

    @Test
    public void transferEziTest(){
        rick.generateKeyPair();

        rick.transferEZI(20d);
        Assert.assertEquals(20d, rick.getbEnzinium(), 0);
    }
}
