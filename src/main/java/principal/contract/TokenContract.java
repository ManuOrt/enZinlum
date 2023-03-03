package principal.contract;

import principal.address.Address;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.IllegalFormatCodePointException;
import java.util.Map;

public class TokenContract
{
    private String name;
    private String symbol;
    private PublicKey myPublicK;
    private Address addressOwner;

    private Map<PublicKey, Double> mBalances = new HashMap<>(); //devuelve como llave publickey y como valor unidades de tokens

    public Map<PublicKey, Double> getmBalances()
    {
        return mBalances;
    }

    private int totalSupply;
    private double tokenPrice;

    private double tokenSolds = 0d;

    public TokenContract(Address owner)
    {
        this.myPublicK = owner.getMyPublicK();//guardamos la llave publica del propietario
        this.addressOwner = owner;//guardamos el propietario por si nos hace falta más adelante
    }

    /* GETTERS & SETTERS */


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSymbol()
    {
        return symbol;
    }

    public void setSymbol(String symbol)
    {
        this.symbol = symbol;
    }

    public double getTotalSupply()
    {
        return totalSupply;
    }

    public void setTotalSupply(int totalSupply)
    {
        this.totalSupply = totalSupply;
    }

    public double getPriceT()
    {
        return tokenPrice;
    }

    public void setPriceT(double priceT)
    {
        this.tokenPrice = priceT;
    }

    public void addOwner(PublicKey PK, Double units){
        mBalances.putIfAbsent(PK, units); //Mirar si ya existe para no sobreescribir.
    }

    public int numOwners(){
        return mBalances.size();
    }

    //* balanceOf(PublicKey owner) devuelve el numero de tokens de un propietario.
    public Double balanceOf(PublicKey owner){
        /*
        * 1. Mirar si existe el propietario y si no devolver 0
        * 2. Devolver la cantidad de tokens
        * */
        if (mBalances.containsKey(owner)){
            return returnQuantity(owner);
        }else{
            return 0d;
        }
    }

    public double returnQuantity(PublicKey recipient){
        return mBalances.get(recipient) == null ? 0d : mBalances.get(recipient);
    }

    //transfer(PublicKey recipient, Double units) transfiere tokens del propietario del contrato a la dirección de destino.
    public void transfer(PublicKey recipient, Double units){
        /*
        * 1. Mirar si recipient esta en el mapa
        * 2. Si no esta, no lo podemos enviar
        * 3. Comprobamos que dispone de la cantidad de units
        * 4. Actualizamos los units.
        * */

        double cantidad = 0d;
        cantidad = returnQuantity(myPublicK);

        if (cantidad >= units){
            mBalances.put(myPublicK, cantidad - units);
            cantidad = returnQuantity(recipient);
            mBalances.put(recipient, cantidad + units);
        }else
            System.out.println("no tienes suficientes EZIS");
    }

    //* transfer(PublicKey sender, PublicKey recipient, Double units) transfiere tokens del sender al recipient.
    /*
    * 1. Mirar si sender esta en el mapa
    * 2. Comprobar las units que tiene y mirar si puede enviar la cantidad de units que le llegan
    * 3. Si las puede enviar, meter a recipient en el mapa
    * */

    public void transfer(PublicKey sender, PublicKey recipient, Double units){
        double cantidad = 0d;
        if (mBalances.containsKey(sender)){
            cantidad = returnQuantity(sender);
            if (cantidad >= units){
                mBalances.put(sender, cantidad - units);
                cantidad = returnQuantity(recipient);
                mBalances.put(recipient, cantidad + units);
            }
        }
    }

    //require(Boolean holds) throws Exception lanza una excepción cuando holds es false.
    // owners() mueestra en consola todos los propietarios.
    public void owners(){
        System.out.println("Ventas del concierto "+ name);
        for (Map.Entry<PublicKey, Double> mOwners : mBalances.entrySet()){
            if (mOwners.getKey() != myPublicK)
            {
                System.out.println("Public key del comprador : " + mOwners.getKey().getFormat() + " cantidad de entradas: "+ mOwners.getValue());
            }
        }
    }

    public double totalTokensSold(){
        return tokenSolds;
    }

    public double totalTokenSold(){
        //tienes que reutilizar el tokensolds
        for (Map.Entry<PublicKey,Double> balance : mBalances.entrySet())
        {
               if (balance.getKey() != myPublicK){
                   tokenSolds += balance.getValue();
               }
        }
        return tokenSolds;
    }

    public void payable(PublicKey recipient, Double enziniums){

        // 100 = enziniums  a 25 = tokenPrice  cada     una -> cantidad -> 4    transfer(recipient,4)
        double cantidadEntradasRestantes = mBalances.get(myPublicK);
        if (enziniums >= tokenPrice && cantidadEntradasRestantes > 0)
        {
            double cantidadEntradasCompra = Math.floor(enziniums/tokenPrice);
            transfer(recipient,cantidadEntradasCompra);
            addressOwner.transferEZI(tokenPrice * cantidadEntradasCompra);
        }
    }
}
