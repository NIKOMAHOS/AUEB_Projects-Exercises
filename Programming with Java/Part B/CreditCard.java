import java.text.DecimalFormat;
import java.util.HashMap;

public class CreditCard extends BankProduct {
    private static double COMMISION_RATE = 0.03;
    private static double MAX_AMOUNT = 3000;
    private static double YEARLY_MAX_AMOUNT = 25000;
    protected double total;
    protected static int counter = 0;
    private String key = "C";
    private static final DecimalFormat df = new DecimalFormat("0.00");
    
    // Default Contructor
    CreditCard(){
        super();
        this.total = 0;
        counter++;
        this.key = key + counter;
    }
        
    // Overloaded Contructor
    CreditCard(String bankCode, String num, String afm){
        super(bankCode, num, afm);
        this.total = 0;
        counter++;
        this.key = key + counter;
    }
    
    // Other Methods
    public void doTransaction(CreditCard this, double amount, String reason, HashMap<String, Object> transactions){
        try{
            Transaction tran = new Transaction(this, amount, reason); // Checking if the transaction is valid inside of the constructor of Transaction Object
            transactions.put(tran.getKey(), tran);
            }catch(NullPointerException e){
                System.out.println("The credit card key you have entered does not match any of the keys of the credit cards in the list.");
            }
    }
    
    public static void displayTransactions(HashMap<String, Transaction> transactionsList){
        System.out.println("ALL TRANSACTIONS: ");
        for (String key : transactionsList.keySet()) {
            System.out.println(transactionsList.get(key));
            System.out.println();
        }
    }
    
    public static void displayCreditCards(HashMap<String, Object> bankProducts){
        System.out.println("ALL CREDIT CARDS: ");
        for(String key : bankProducts.keySet()){
            if (bankProducts.get(key) instanceof CreditCard){
                System.out.println(bankProducts.get(key));
                System.out.println();
            }
        }
    }
    
    public CreditCard lookUpSoldCreditCard(CreditCard this, SalesPerson sp,HashMap<String, Object> sales){
        for(Object s: sales.values()){
            try{ 
                if (((Sale) s).getSalesPerson().getKey().equals(sp.getKey()) && ((Sale) s).getBankProduct().getKey().equals(this.getKey())){
                    return (CreditCard) ((Sale) sales.get(((Sale) s).getKey())).getBankProduct();
                }
            }catch(NullPointerException e){
                continue;
            }
        }
        return new CreditCard();
    }
    
    // Setters & Getters

    public double getCommisionRate() {
        return COMMISION_RATE;
    }

    public static double getMaxAmount() {
        return MAX_AMOUNT;
    }

    public static double getYearlyMaxAmount() {
        return YEARLY_MAX_AMOUNT;
    }
    
    public double getTotal() {
        return total;
    }
    
    public void setTotal(double newTotal) {
        this.total = newTotal;
    }
    
    @Override
    public void setKey(String newKey) {
        this.key = newKey;
    }
    
    @Override
    public String getKey(){
        return this.key;
    }

    @Override
    public String toString() { 
        return "CreditCard : " +  "\n"+
                " Key = " + getKey() +  "\n" +
                " Code = " + super.getBankProductCode() +  "\n" +
                " Commision Rate per transaction = " + "3,0%" +  "\n" +
                " Max Amount (One Time) = " + df.format(getMaxAmount()) +  "\n" +
                " Yearly Max Amount = " + df.format(getYearlyMaxAmount()) + "\n" +
                " Total amount spent = " + df.format(getTotal());
    }
    
}
