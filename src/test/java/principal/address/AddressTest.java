package principal.address;

import org.junit.Assert;
import org.junit.Test;

public class AddressTest
{
    @Test
    public void constructor_test() {
        Address address = new Address();
        Assert.assertNotNull(address);
        Assert.assertEquals(0d, address.getbEnzinium(), 0d);
    }

    @Test
    public void generate_key_pair_test() {
        Address address = new Address();
        Assert.assertNotNull(address);
        address.generateKeyPair();
        Assert.assertNotNull(address.getMyPrivateK());
    }


    @Test
    public void transferEZI_test() {

        Address rick = new Address();
        rick.generateKeyPair();

        rick.transferEZI(20d);
        rick.transferEZI(20d);

        Assert.assertEquals(40d, rick.getbEnzinium(), 0d);
    }
}
