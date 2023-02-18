package principal.address;

import principal.contract.TokenContract;
import principal.keys.GenSig;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class Address
{
    private final String SYMBOL = "EZI";
    private double bEnzinium = 0d;
    private PublicKey myPublicK;
    private PrivateKey myPrivateK;

    public Address()
    {}
    public String getSYMBOL()
    {
        return SYMBOL;
    }

    public double getbEnzinium()
    {
        return bEnzinium;
    }

    public void setbEnzinium(double bEnzinium)
    {
        this.bEnzinium = bEnzinium;
    }

    public void setMyPublicK(PublicKey myPublicK)
    {
        this.myPublicK = myPublicK;
    }

    public PrivateKey getMyPrivateK()
    {
        return myPrivateK;
    }

    public void setMyPrivateK(PrivateKey myPrivateK)
    {
        this.myPrivateK = myPrivateK;
    }



    /*
     * generateKeyPair
     * ToString
     * addEzi(Double EZI)
     * transferEzi(Double EZI)
     * send (TokenContract contract, Double EZI)
     * */

    public PublicKey getMyPublicK(){
        return this.myPublicK;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Cantidad de Enzinium: ")
                .append(bEnzinium)
                .append(SYMBOL)
                .append("\nLa clave publica es: ")
                .append(myPublicK.getFormat())
                .append("\nLa clave privada es: ")
                .append(myPrivateK.getFormat());
        return sb.toString();
    }

    private void addEzi(Double ezi){
        this.bEnzinium = ezi;
    }

    public void transferEZI(Double ezi){
        this.bEnzinium += ezi;
    }

    public void generateKeyPair()
    {
         KeyPair key = GenSig.generateKeyPair();

         this.myPublicK = key.getPublic();
         this.myPrivateK = key.getPrivate();
    }

    public void send(TokenContract contract, Double enziniums)
    {
        if (getbEnzinium() >= enziniums){
            contract.payable(getMyPublicK(), enziniums);
            bEnzinium -= enziniums;
        }
    }
}
