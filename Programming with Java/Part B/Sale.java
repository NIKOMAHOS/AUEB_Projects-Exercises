import java.util.HashMap;

public class Sale {
    protected SalesPerson sp; 
    protected BankProduct bp;
    protected String reason;
    protected String key;
    protected static int counter = 0;
    
    // Default Constructor
    public Sale() {
        this.sp = new SalesPerson();
        this.bp = new BankProduct();
        this.reason = "";
        this.key = "S" + counter;}
    
    // Overloaded Constructor 
    public Sale(SalesPerson sp, BankProduct bp, String reason){ 
        this.sp = sp;                     
        this.bp = bp;                                              
        this.reason = reason;
        counter++;
        this.key = "S" + counter;
    }
    
    // Others Methods
    
    public static Sale lookupSale(String key, HashMap<String, Object> sales){
        for(String k: sales.keySet()){
            if (sales.containsKey(key)){
                if (key.equals(((Sale) sales.get(k)).getKey())){
                    return (Sale) sales.get(key);
                }
            }else{
                continue;
            }
        }
        return new Sale();
    }
           
    // Setters & Getters

    public SalesPerson getSalesPerson() {
        return sp;
    }

    public String getSalesPersonCode() {
        return this.sp.getSalesPersonCode();
    }
    
    public void setSalesPersonCode(String newCode) {
        this.sp.setsalesPersonCode(newCode);
    }

    public String getBankProductCode() {
        return this.bp.getBankProductCode();
    }
    
    public void setBankProductCode(String newCode) {
        this.bp.setBankProductCode(newCode);
    }

    public BankProduct getBankProduct(){
        return bp;
    }
        
    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    
    public String getKey() {
        return this.key;
    }
    
    public void setKey(String newKey) {
        this.key = newKey;
    }
    
    @Override
    public String toString() {
        return  "Sale: " + "\n" +
                " Key = " + getKey() + "\n" +
                " Bank Product Code = " + getBankProductCode() + "\n" +
                " Salesperson Code = " + getSalesPersonCode() + "\n" +
                " Reason = " + reason; 
    }
    
}

    
