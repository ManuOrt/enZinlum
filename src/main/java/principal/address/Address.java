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

    /*
     * generateKeyPair //fácil
     * ToString
     * addEzi(Double EZI) // este de aqui
     * transferEzi(Double EZI)
     * send (TokenContract contract, Double EZI)
     * */

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

    private void addEzi(Double EZI){
        this.bEnzinium = EZI;
    }

    public void transferEZI(Double EZI){

    }

    public void generateKeyPair()
    {
         KeyPair key = GenSig.generateKeyPair();

         this.myPublicK = key.getPublic();
         this.myPrivateK = key.getPrivate();
    }

    private void send(TokenContract contract, Double EZI)
    {

    }
}
