package projectsp;

//Abstract class ShopAcc
abstract class ShopAcc {
 private int accNo;
 private String accNm;
 private float charges;

 public ShopAcc(int accNo, String accNm, float charges) {
     this.accNo = accNo;
     this.accNm = accNm;
     this.charges = charges;
 }

 public abstract void bookProduct(float price);

 @Override
 public String toString() {
     return "AccNo: " + accNo + ", AccNm: " + accNm + ", Charges: " + charges;
 }
}

//Abstract class PrimeAcc extending ShopAcc
abstract class PrimeAcc extends ShopAcc {
 private boolean isPrime;

 public PrimeAcc(int accNo, String accNm, float charges, boolean isPrime) {
     super(accNo, accNm, charges);
     this.isPrime = isPrime;
 }

 @Override
 public void bookProduct(float price) {
     // Prime accounts do not have extra delivery charges
     System.out.println("Product booked with price: " + price + ". No delivery charges for Prime accounts.");
 }

 @Override
 public String toString() {
     return super.toString() + ", isPrime: " + isPrime;
 }
}

//Abstract class NormalAcc extending ShopAcc
abstract class NormalAcc extends ShopAcc {
 private float deliveryCharge;

 public NormalAcc(int accNo, String accNm, float charges, float deliveryCharge) {
     super(accNo, accNm, charges);
     this.deliveryCharge = deliveryCharge;
 }

 @Override
 public void bookProduct(float price) {
     // Normal accounts have extra delivery charges
     System.out.println("Product booked with price: " + price + ". Delivery charges: " + deliveryCharge);
 }

 @Override
 public String toString() {
     return super.toString() + ", DeliveryCharge: " + deliveryCharge;
 }
}

//Abstract class ShopFactory
abstract class ShopFactory {
 public abstract PrimeAcc getNewPrimeAccount(int accNo, String accNm, float charges, boolean isPrime);
 public abstract NormalAcc getNewNormalAccount(int accNo, String accNm, float charges, float deliveryCharge);
}

//Concrete class GSPrimeAcc extending PrimeAcc
class GSPrimeAcc extends PrimeAcc {
 public GSPrimeAcc(int accNo, String accNm, float charges, boolean isPrime) {
     super(accNo, accNm, charges, isPrime);
 }
}

//Concrete class GSNormalAcc extending NormalAcc
class GSNormalAcc extends NormalAcc {
 public GSNormalAcc(int accNo, String accNm, float charges, float deliveryCharge) {
     super(accNo, accNm, charges, deliveryCharge);
 }
}

//Concrete class GSShopFactory extending ShopFactory
class GSShopFactory extends ShopFactory {
 @Override
 public PrimeAcc getNewPrimeAccount(int accNo, String accNm, float charges, boolean isPrime) {
     return new GSPrimeAcc(accNo, accNm, charges, isPrime);
 }

 @Override
 public NormalAcc getNewNormalAccount(int accNo, String accNm, float charges, float deliveryCharge) {
     return new GSNormalAcc(accNo, accNm, charges, deliveryCharge);
 }
}

//Main class to test the framework
public class GoShoppingApp {
 public static void main(String[] args) {
     ShopFactory factory = new GSShopFactory();

     PrimeAcc primeAcc = factory.getNewPrimeAccount(101, "John Doe", 1000.0f, true);
     NormalAcc normalAcc = factory.getNewNormalAccount(102, "Jane Doe", 500.0f, 50.0f);

     primeAcc.bookProduct(200.0f);
     System.out.println(primeAcc);

     normalAcc.bookProduct(200.0f);
     System.out.println(normalAcc);
 }
}