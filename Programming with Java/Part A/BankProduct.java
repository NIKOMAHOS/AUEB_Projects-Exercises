import java.util.HashMap;

public class BankProduct{
    protected String bankProductCode;
    protected String num;
    protected String afm;
    
    // Default Contructor
    BankProduct(){
        this.bankProductCode = "";
        this.num = "";
        this.afm = "";
    }
    
    //Overloaded Contructor
    BankProduct(String bankCode, String num, String afm){
        this.bankProductCode = bankCode;
        this.num = num;
        this.afm = afm;
    }
    
    // Other Methods
    public static void displayBankProducts(HashMap<String, BankProduct> bankProductsList){
        System.out.println("ALL BANK PRODUCTS: ");
        for (String key : bankProductsList.keySet()) {
            System.out.println(bankProductsList.get(key));
            System.out.println();
        }
    }
    
    public static BankProduct lookupBankProduct(String key, HashMap<String, BankProduct> bankProductsList){
        for(String k: bankProductsList.keySet()){
            if (bankProductsList.containsKey(key)){
                if (key.equals(bankProductsList.get(k).getKey())){
                    return bankProductsList.get(key);
                }
            }else{
                continue;
            }
        }
        return new BankProduct();
    }

    // Setters & Getters
    
    public String getBankProductCode() {
        return this.bankProductCode;
    }

    public void setBankProductCode(String bankCode) {
        this.bankProductCode = bankCode;
    }

    public String getNum() {
        return this.num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getAfm() {
        return this.afm;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }
       
    public double getAmount(){
        return -1;
    }

    public Object getKey() {
        return -1;
    }
}