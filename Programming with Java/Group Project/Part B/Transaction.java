import java.text.DecimalFormat;
import java.util.HashMap;

public class Transaction {
    protected CreditCard cc;
    protected String cardCode;
    protected double amount;
    protected String reason;
    public String key = "T";
    protected static int counter = 0;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    
        
    // Default Constructor
    public Transaction() {
        this.cc = new CreditCard();
        this.cardCode = cc.getBankProductCode();
        this.reason = "";
        this.amount = 0;
        this.cc.total += amount;//
        
    }
    
    // Overloaded Contructor
    public Transaction(CreditCard card, double amount, String reason){
        
        try{
            
            if (amount > 0){
                try{
                
                    if (amount <= CreditCard.getMaxAmount()) {
                        
                        try{
                            if(card.total <= CreditCard.getYearlyMaxAmount()){
                                this.cc = card;
                                this.cardCode = cc.getBankProductCode();
                                this.amount = amount;
                                this.reason = reason;
                                card.total+= amount;
                                counter++;
                                this.key = key + counter;
                                System.out.println("The transaction was successful !");
                                                     
                                
                        }else{throw new IllegalArgumentException();   
                            }
                        
                        }catch(IllegalArgumentException e3){System.out.println("Yearly maximum transaction amount has been reached.\n" + "Yearly maximum transaction amount cannot be more than " + CreditCard.getYearlyMaxAmount());
                        }
                        
                    }else{throw new IllegalArgumentException();
                    }
                    
                }catch (IllegalArgumentException e2){
                        System.out.println("Transaction amount is too high!\n" + "Amount cannot be more than " + CreditCard.getMaxAmount());
                    }
            }
            else{throw new IllegalArgumentException();
            }
            
        }catch(IllegalArgumentException e1){
                System.out.println("Invalid transaction amount.\n" + "Amount must be higher than 0");
            }
            
    }  

    // Other Methods
    public void displayTransactionsList(HashMap<String, Transaction> transactionsList){
        for (Transaction t : transactionsList.values()){
            System.out.println(t);
        }
    }
    
    public static Transaction lookUpTransactions(String key, HashMap<String, Transaction> transactionsList){
        for(String k: transactionsList.keySet()){
            if (transactionsList.containsKey(key)){
                if (key.equals(transactionsList.get(k).getKey())){
                    return transactionsList.get(key);
                }
            }else{
            }
        }
        return new Transaction();
    }
        
    // (Setters &) Getters

    public double getAmount() {
        return this.amount;
    }
    
    public void setAmount(double newAmount) {
        this.amount = newAmount;
    }
    
    public void setCardCode(String newCode) {
        this.cardCode = newCode;
    }
    
    public String getCardCode(){
        return this.cardCode;
    }
    
    public String getReason() {
        return this.reason;
    }
    
    public void setReason(String newReason) {
        this.reason = newReason;
    }
    
    public CreditCard getCreditCard() {
        return cc;
    }
    
    public String getKey() {
        return this.key;
    }
    
    public void setKey(String newKey) {
        this.key = newKey;
    }

    // toString()
    @Override
    public String toString() {
        return "Transaction: " + "\n" +
                " Key: " + getKey() + "\n" +
                " Credit Card Code: " + cardCode + "\n" +
                " Value: " + df.format(getAmount()) + "\n" +
                " Reason: " + reason + "\n" +
                " Interest Rate: " + df.format(getCreditCard().getCommisionRate()) + "%" + "\n" +
                " Total Amount Spent with this card: " + df.format(getCreditCard().getTotal());
    }
    
}

