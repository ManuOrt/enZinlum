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

    /*
    * Listado de métodos necesarios:
    * setName x
    * setSymbol x
    * setTotalSupply x
    * setTokenPrice x
    * getName x
    * getSymbol x
    * getTotalSupply x
    * addOwner(PublicKey PK, Double units) añade un propietario a balances. x
    * numOwners() devuelve el numero de propietarios en balances x
    * balanceOf(PublicKey owner) devuelve el numero de tokens de un propietario. X
    * transfer(PublicKey recipient, Double units) transfiere tokens del propietario del contrato a la dirección de destino.
    * transfer(PublicKey sender, PublicKey recipient, Double units) transfiere tokens del sender al recipient.
    * require(Boolean holds) throws Exception lanza una excepción cuando holds es false.
    * owners() mueestra en consola todos los propietarios.
    * payable(PublicKey recipient, Double EnZinIums) envia los tokens a la direccion recipient y envia los EnZinIums al propietario del contrato.
    *   */

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
        /*Pasos a seguir:
        * 1. Mirar si el public key esta dentro del mapa
        * 2. Si esta, recorro el mapa y devuelvo el valor
        * */

        if (mBalances.containsKey(owner)){
            for (Map.Entry<PublicKey,Double> checkOwner : mBalances.entrySet()){
                if (checkOwner.getKey() == owner){
                    return checkOwner.getValue();
                }
            }
        }else {
            System.out.println("Este propietario no tiene ningun token");
        }

        return 0d;
    }


    public double returnQuantity(PublicKey recipient){
        return mBalances.get(recipient) != null ? mBalances.get(recipient) : 0d;
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
            mBalances.put(myPublicK, cantidad-units);
            cantidad = returnQuantity(recipient);
            mBalances.put(recipient, cantidad+units);
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
        /*
        * 1. Mirar si el dinero que llega basta para comprar alguna entrada
        * 2. Miramos cuantas entradas puede comprar con el dinero que tiene
        * 3. Compramos la entrada
        * 4. Dentro del mapa meter a la persona y poner la cantidad de entradas.
        * 5. Añadirle el dinero al propietario del token contract
        * */
        Double cantidadEntradasRestantes = mBalances.get(myPublicK);// 500

        // 100 = enziniums  a 25 = tokenPrice  cada     una -> cantidad -> 4    transfer(recipient,4)

        if (enziniums >= tokenPrice && cantidadEntradasRestantes > 0)
        {
            double cantidadEntradasCompra = Math.floor(enziniums/tokenPrice);
            transfer(recipient,cantidadEntradasCompra);
            addressOwner.transferEZI(tokenPrice * cantidadEntradasCompra);
        }
    }
}
