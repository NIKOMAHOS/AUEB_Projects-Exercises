import java.text.DecimalFormat;
import java.util.HashMap;


public class Loan extends BankProduct {
    private static double INTEREST_RATE = 0.015;
    private double amount;
    private static int counter = 0;
    private String key = "L";
    private static final DecimalFormat df = new DecimalFormat("0.00");
    
    Loan(String code, String num, String afm, double amount){
        super(code, num, afm);
        this.amount = amount;
        counter++;
        this.key = key + counter;
    }
    
    // Setters & Getters
    
    @Override
    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public static double getInterestRate() {
        return INTEREST_RATE;
    } 
    
    @Override
    public String getKey(){
        return this.key;
    }

    //Other Methods
    public static void displayLoans(HashMap<String, BankProduct> bankProductsList){
        System.out.println("ALL LOANS: ");
        for(String key : bankProductsList.keySet()){
            if (bankProductsList.get(key) instanceof Loan){
                System.out.println(bankProductsList.get(key));
                System.out.println();
            }
        }
    }

    @Override
    public String toString() {
        return "Loan : " + '\n'+
                " Key = " + getKey() + '\n' +
                " Code = " + super.getBankProductCode() + '\n' +
                " Number = " + super.getNum() + '\n' +
                " AFM = " + super.getAfm() + '\n' +
                " Amount = " + df.format(getAmount()) + '\n' +
                " Commision Rate = " + getInterestRate() + "%" ;
    }
}
