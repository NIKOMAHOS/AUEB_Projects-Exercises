import java.util.HashMap;

public class SalesPerson  {
    protected String salesPersonCode;
    protected String firstName;
    protected String lastName;
    protected String afm;
    protected String key;
    private static int counter = 0;
    
    // Default Contructor
    SalesPerson(){
        this.salesPersonCode = "";
        this.lastName = "";
        this.firstName = "";
        this.afm = "";
        counter++;
        this.key = "SP" + counter;
    }
    
    // Overloaded Contructor
    SalesPerson(String salesPersonCode, String lastName, String firstName, String afm){
        this.salesPersonCode = salesPersonCode;
        this.lastName = lastName;
        this.firstName = firstName;
        this.afm = afm;
        counter++;
        this.key = "SP" + counter;
    }
    
    // Other Methods    
    public void doSale(SalesPerson this, BankProduct bp, String reason, HashMap<String, Sale> salesList){
        Sale sale = new Sale(this, bp, reason);
        salesList.put(sale.getKey(), sale);
    }
    
    public static void displaySales(HashMap<String, Sale> salesList){
        System.out.println("ALL SALES: ");
        for (String key : salesList.keySet()) {
            System.out.println(salesList.get(key));
            System.out.println();
        }
    }
    
    public static void displaySalesPeople(HashMap<String, SalesPerson> salespeopleList){
        System.out.println("ALL SALESPERSONS: ");
        for (String key : salespeopleList.keySet()) {
            System.out.println(salespeopleList.get(key));
            System.out.println();
        }
    }
    
    public static SalesPerson lookupSalesPerson(String key, HashMap<String, SalesPerson> salespeopleList){
        for(String k: salespeopleList.keySet()){
            if (salespeopleList.containsKey(key)){
                if (key.equals(salespeopleList.get(k).getKey())){
                    return salespeopleList.get(key);
                }
            }else{
                continue;
            }
        }
        return new SalesPerson();
    }
        
    public static void displaySoldLoans(HashMap<String, Sale> salesList){
        System.out.println("ALL SOLD LOANS: ");
        for(String key : salesList.keySet()){
            BankProduct bp = salesList.get(key).getBankProduct();
            if (bp instanceof Loan){
                System.out.println(bp);
                System.out.println();
            }else{
                continue;}
        }
    }
    
    // 7th selection in menu in mainApp.java
    public static void displayTransactionsOfSalesPerson(SalesPerson sp, HashMap<String, Transaction> transactionsList, HashMap<String, Sale> salesList){
        System.out.println("ALL TRANSACTIONS OF SALESPERSON WITH KEY = " + sp.getKey() + " : ");
        for (String key : transactionsList.keySet()) {
            if (transactionsList.get(key).getCreditCard().getKey().equals((transactionsList.get(key).getCreditCard().lookUpSoldCreditCard(sp, salesList)).getKey())){  
                System.out.println(transactionsList.get(key)); // ^ IF IT'S EQUAL WITH THE KEY OF A CREDIT CARD THAT WAS SOLD BY THE SPECIFIC SALESPERSON.
                System.out.println();
            }else{
                continue;
            }
        }
    }
    
    // 6th selection in menu in mainApp.java Part A).
    public double calculateLoanComission(SalesPerson this, HashMap<String, Sale> salesList, HashMap<String, Transaction> transactionsList){
        double totalLoanComission = 0;
        double loanAmount = 0;
        double maxLoanComission = 0;
        for(String key: salesList.keySet()){                          // Scanning all the sales from the salesList
            String spCode = salesList.get(key).getSalesPersonCode();  // Getting the salesperson's code of the one that made the sale and checking 
            if (spCode.equals(this.getSalesPersonCode())){            // if it is the same as the salesperson's code of the desired salesperson
                BankProduct bp = salesList.get(key).getBankProduct(); // aquiring the bankproduct of the sale
                if (bp instanceof Loan){                              // checking if the bankproduct is a loan
                    loanAmount = loanAmount + bp.getAmount();         // if it is a loan, the amount of the loan is added to the total amount of sold loans
                }else{
                    continue;
                } 
            }
        }
        maxLoanComission = loanAmount * Loan.getInterestRate();                      // the maximum loan comission is the amount of the loan times the Interest Rate of the loans, wich is 0.5%
        
        if (loanAmount< 500000){
            totalLoanComission = loanAmount/100; // 1% ~> 1/100 ~~> /100
        }else if (500000 <= loanAmount && loanAmount < 2000000){
            totalLoanComission = loanAmount/50; // 2% ~> 2/100 = 1/50 ~~> /50
        }else if (loanAmount >= 2000000){
            totalLoanComission = loanAmount/40; // 2,5% ~> 2.5/100 = 5/200 = 1/40 ~~> /50
        }else{
            // This SalesPerson did not sell any loans.
            return 0;
        }
        //Checkin to see if the total loan comission is greater than the maximum loan comission.    
        if(totalLoanComission > maxLoanComission){
            return maxLoanComission;
        }else{
            return totalLoanComission;
        }
    }
    
    public void analyzedLoanComission(SalesPerson this, HashMap<String, Sale> salesList, HashMap<String, Transaction> transactionsList){
        System.out.println();
        System.out.println("All loans sold by the salesperson with key " + this.getKey() + ": ");
        for(Sale s : salesList.values()){
            if (s.getSalesPersonCode().equals(this.getSalesPersonCode()) && s.getBankProduct() instanceof Loan){
                        System.out.println(s.getBankProduct());
                        System.out.println();
                    }else{
                        continue;}
                }
        System.out.println("Total Loan Comission: " + this.calculateLoanComission(salesList, transactionsList));
    }
    
    public void analyzedCreditCardComission(SalesPerson this, HashMap<String, Sale> salesList, HashMap<String, Transaction> transactionsList){
        double transactionAmount = 0;
        double com = 0;
        System.out.println();
        System.out.println("All credit cards sold by the salesperson with key " + this.getKey() + ": ");
        for(Sale s : salesList.values()){  
            String spCode = s.getSalesPersonCode();
            if (spCode.equals(this.getSalesPersonCode()) && s.getBankProduct() instanceof CreditCard){
                    CreditCard bp = (CreditCard) s.getBankProduct();
                    String ccCode = bp.getBankProductCode();
                    for (String key : transactionsList.keySet()) {
                        if (transactionsList.get(key).getCreditCard().getBankProductCode().equals(ccCode)){
                            transactionAmount += transactionsList.get(key).getAmount(); 
                            com += (((transactionAmount)*((transactionsList.get(key).getCreditCard().getCommisionRate()))));
                        }else{
                            continue;
                        }
                    }
                    System.out.println(s.getBankProduct());
                    System.out.println("Comission: " + com);  
                }
        }
        System.out.println();
        System.out.println("Total Credit Card Comission: " + this.calculateCreditCardComission(salesList, transactionsList));
        }
    
    // 6th selection in menu in mainApp.java Part B).     
    public double calculateCreditCardComission(SalesPerson this, HashMap<String, Sale> salesList, HashMap<String, Transaction> transactionsList){
        boolean noCC = false;
        boolean notrans = false;
        double totalCreditCardComission = 0;
        double transactionAmount = 0;
        for(String key: salesList.keySet()){                          // Scanning all the sales from the salesList
            String spCode = salesList.get(key).getSalesPersonCode();  // Getting the salesperson's code of the one that made the sale and checking 
            if (spCode.equals(this.getSalesPersonCode())){            // if it is the same as the salesperson's code of the desired salesperson
                BankProduct bp = salesList.get(key).getBankProduct(); // aquiring the bankproduct of the sale    
                if (bp instanceof CreditCard){                        // checking if the bankproduct is a creditcard
                        for (String new_key: transactionsList.keySet()){ // Scanning all the transactions made with a specific Credti Card from the transactionsList
                            String ccCode = transactionsList.get(new_key).getCardCode();
                            if (ccCode.equals(bp.getBankProductCode())){    // checking if the credit card's code is equal to the sold bank product's code from the list of sales
                                transactionAmount = transactionsList.get(new_key).getAmount(); // aquiring the amount of the transaction
                                totalCreditCardComission = totalCreditCardComission + (((transactionAmount)*((transactionsList.get(new_key).getCreditCard().getCommisionRate())))); // calculating the comission from the transaction));
                                // ^ the total credit card comission is the amount of the transaction times the credit card's comission rate divided by 100.
                            }else{
                                continue; // Not the same bank product code. Scan Next.
                            }
                        }
                    }else{
                        continue; // Not a credit card. Scan Next.
                    }
            }else{
                continue; // Not the desired salesperson. Scan Next.
            }
        }    
        if (totalCreditCardComission == 0 && noCC == true){
            // This SalesPerson did not sell any credit cards.
            return 0;
        }else if (totalCreditCardComission == 0 && notrans == true){
            // This SalesPerson's sold credit cards were not involved in any transactions. So there is no comission from any of them.
            return 0;
        }else {
            return totalCreditCardComission;
        }
    }
    
    // Setters & Getters
    
    public String getSalesPersonCode() {
        return this.salesPersonCode;
    }

    public void setsalesPersonCode(String salesPersonCode) {
        this.salesPersonCode = salesPersonCode;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAfm() {
        return this.afm;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }
    
    public String getKey() {
        return key;
    }
    
    @Override
    public String toString() {
        return "SalesPerson: " + "\n" +
               " Key = " + getKey() + "\n" +
               " Code = " + getSalesPersonCode() + "\n" +
               " LastName =  " + getLastName() + "\n" +
               " FirstName = " + getFirstName() + "\n" +
               " AFM = " + getAfm();
    }      

}