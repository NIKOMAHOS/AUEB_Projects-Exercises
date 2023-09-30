public class Sale {
    protected SalesPerson sp; 
    protected BankProduct bp;
    protected String reason;
    protected String key;
    private static int counter = 0;
    
    // Overloaded Constructor 
    public Sale(SalesPerson sp, BankProduct bp, String reason){ 
        this.sp = sp;                     
        this.bp = bp;                                              
        this.reason = reason;
        counter++;
        this.key = "S" + counter;
    }
           
    // Setters & Getters
    
    public SalesPerson getSalesPerson() {
        return sp;
    }

    public String getSalesPersonCode() {
        return this.sp.getSalesPersonCode();
    }

    public String getBankProductCode() {
        return this.bp.getBankProductCode();
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
    
    @Override
    public String toString() {
        return  "Sale: " + "\n" +
                " Key = " + getKey() + "\n" +
                " Bank Product Code = " + getBankProductCode() + "\n" +
                " Salesperson Code = " + getSalesPersonCode() + "\n" +
                " Reason = " + reason; 
    }
    
    
}

    
