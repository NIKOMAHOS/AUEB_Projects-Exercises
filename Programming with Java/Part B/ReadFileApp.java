import java.util.*;
import java.io.*;

public class ReadFileApp {

    protected HashMap<String, Object> bankProducts = new HashMap<String, Object>();
    protected HashMap<String, Object> salespeople = new HashMap<String, Object>();
    protected HashMap<String, Object> sales = new HashMap<String, Object>();
    protected HashMap<String, Object> transactions = new HashMap<String, Object>();
    
	public HashMap<String, Object> ReadFile(String pathname){
		
		BufferedReader reader = null;
        String line;
        try {
			reader = new BufferedReader(new FileReader(new File(pathname)));
			
			if (pathname == "BANKITEMS_LIST.txt"){
			    System.out.println("\n ---------> Adding Bank Products from " + pathname + " to the Bank Products List."); // Print Corresponding Message
			    BankProduct bp = null;
                line = reader.readLine();
                if (line.trim().equals("BANKITEMS_LIST")) {
                    line = reader.readLine();
                    if (line.trim().equals("{")) {
                        line = reader.readLine();
                        while (!line.equals("}")) {
                            if (line.trim().equals("BANKITEM")) {
                                line = reader.readLine();
                                if (line.trim().equals("{")) {
                                    line = reader.readLine();
                                    if (line.trim().substring(4).trim().equals("Loan")) { // TYPE = 'Loan'
                                        bp = new Loan();
                                        line = reader.readLine();
                                        if (line.trim().startsWith("KEY ")){
                                            bp.setKey(line.trim().substring(4).trim());}
                                        line = reader.readLine();
                                        if (line.trim().startsWith("CODE "))
                                            bp.setBankProductCode((line.substring(6).trim()));
                                        line = reader.readLine();
                                        if (line.trim().startsWith("NUMBER "))
                                            ((Loan) bp).setNum(line.trim().substring(7).trim());
                                        line = reader.readLine();
                                        if (line.trim().startsWith("AFM "))
                                            ((Loan) bp).setAfm(line.substring(5).trim());
                                        line = reader.readLine();
                                        if (line.trim().startsWith("AMOUNT "))
                                            ((Loan) bp).setAmount(Double.parseDouble(line.substring(8).trim()));
                                        line = reader.readLine();
                                        if (line.trim().equals("}"))
                                            bankProducts.put((String) bp.getKey(), bp);
                                        line = reader.readLine();
                                        Loan.counter++;
                                    } // Loan 
        							else if (line.trim().substring(5).trim().equals("CreditCard")) { // TYPE = 'Credit Card'
        								    bp = new CreditCard();
                                        line = reader.readLine();
                                        if (line.trim().startsWith("KEY ")){
                                            bp.setKey(line.trim().substring(4).trim());}
                                        line = reader.readLine();
                                        if (line.trim().startsWith("CODE "))
                                            bp.setBankProductCode(line.substring(6).trim());
                                        line = reader.readLine();
                                        if (line.trim().startsWith("NUMBER "))
                                            ((CreditCard) bp).setNum(line.trim().substring(7).trim());
                                        line = reader.readLine();
        								if (line.trim().startsWith("AFM "))
                                            ((CreditCard) bp).setAfm(line.trim().substring(5).trim());
                                        line = reader.readLine();
                                        if (line.trim().startsWith("AMOUNT SPENT "))
                                            ((CreditCard)bp).setTotal(Double.parseDouble(line.substring(14).trim()));
                                        line = reader.readLine();
                                        if (line.trim().equals("}"))
                                            bankProducts.put((String) bp.getKey(), bp);
                                        line = reader.readLine();
                                        CreditCard.counter++;
                                    }// CreditCard
                                    
                                }
                            }//BANKITEM
                                
                        }//while
                        reader.close();
                            
                    } 
			    
		        }//BANKITEMS_LIST
                                
            }// file: BANKITEMS_LIST.txt
            
            else if (pathname == "SALESMAN_LIST.txt"){
                System.out.println("\n >>>>>>> Adding SalesPeople from " + pathname + " to the SalesPeople List."); // Print Corresponding Message
                    SalesPerson sp = null;
                    line = reader.readLine();
                    if (line.trim().equals("SALESMAN_LIST")) {
                        line = reader.readLine();
                        if (line.trim().equals("{")) {
                            line = reader.readLine();
                            while (!line.equals("}")) {
                                if (line.trim().equals("SALESMAN")) {
                                    sp = new SalesPerson();
                                    line = reader.readLine();
                                     if (line.trim().equals("{")) {
                                        line = reader.readLine();
                                        if (line.trim().startsWith("KEY ")){
                                            sp.setKey(line.trim().substring(4).trim());}
                                        line = reader.readLine();
                                        if (line.trim().startsWith("CODE "))
                                            sp.setsalesPersonCode((line.trim().substring(5).trim()));
                                        line = reader.readLine();
                                        if (line.trim().startsWith("SURNAME "))
                                            sp.setLastName((line.trim()).substring((line.trim()).indexOf("\"") + 1, (line.trim()).indexOf("\"", (line.trim()).indexOf("\"") + 1)));
                                        line = reader.readLine();
                                        if (line.trim().startsWith("FIRSTNAME "))
                                            sp.setFirstName((line.trim()).substring((line.trim()).indexOf("\"") + 1, (line.trim()).indexOf("\"", (line.trim()).indexOf("\"") + 1)));
                                        line = reader.readLine();
                                        if (line.trim().startsWith("AFM "))
                                            sp.setAfm((line.trim().substring(4).trim()));
                                        line = reader.readLine();
                                        if (line.trim().equals("}"))
                                            salespeople.put((String) sp.getKey(), sp);
                                        line = reader.readLine();
                                        SalesPerson.counter++;
                                        }
                                    }// SALESMAN   
                                
                                }// while    
                                reader.close();
                            
                        }
                        
                        }// SALESMAN_LIST
                        
                    }// file : "SALESMAN_LIST.txt"
            
            else if (pathname == "SALES_LIST.txt"){
                System.out.println("\n >>>>>>> Adding Sales from " + pathname + " to the Sales List."); // Print Corresponding Message
                Sale s = null;
                line = reader.readLine();
                if (line.trim().equals("SALES_LIST")) {
                    line = reader.readLine();                   
                    if (line.trim().equals("{")) {
                        line = reader.readLine();
                        while (!line.equals("}")) {
                            if (line.trim().equals("SALE")) {
                                s = new Sale();
                                line = reader.readLine();
                                if (line.trim().equals("{")) {
                                    line = reader.readLine();
                                    if (line.trim().startsWith("KEY ")){
                                        s.setKey(line.trim().substring(4).trim());}
                                    line = reader.readLine();
                                    if (line.trim().startsWith("SALESMAN_CODE "))
                                        s.setSalesPersonCode(line.trim().substring(14).trim());
                                        for (Object sp: salespeople.values()){
                                            if (((SalesPerson)sp).getSalesPersonCode().equals(s.getSalesPersonCode())){
                                                (s.sp).setKey((String) (((SalesPerson) sp).getKey()));
                                                s.sp = (SalesPerson)sp;

                                            }else{ continue;}
                                        }
                                       
                                    line = reader.readLine();
                                    if (line.trim().startsWith("BANKITEM_TYPE "))
                                        if (((line.trim().substring(14).trim()).equals("Loan"))){
                                            s.bp = new Loan();
                                        }else if (((line.trim().substring(14).trim()).equals("CreditCard"))){
                                            s.bp = new CreditCard();
                                        }
                                    line = reader.readLine();
                                    if (line.trim().startsWith("BANKITEM_CODE "))
                                        s.setBankProductCode(line.trim().substring(14).trim());
                                        for (Object bp: bankProducts.values()){
                                            if (((BankProduct)bp).getBankProductCode().equals(s.getBankProductCode())){
                                                (s.bp).setKey((String) (((BankProduct) bp).getKey()));
                                                s.bp = (BankProduct)bp;
                                            }else{ continue;}
                                        }
                                            
                                    line = reader.readLine();
                                    if (line.trim().startsWith("JUSTIFICATION "))
                                        s.setReason((line.trim()).substring((line.trim()).indexOf("\"") + 1, (line.trim()).indexOf("\"", (line.trim()).indexOf("\"") + 1)));
                                    line = reader.readLine();
                                    if (line.trim().equals("}"))
                                        sales.put((String) s.getKey(), s);
                                    line = reader.readLine();
                                    Sale.counter++;
                                }
                                    
                                }// SALE
                                
                        }// while
                        reader.close();
                    }    
                            
                }// SALES_LIST
                        
                    
            }// file: "SALES_LIST.txt"
                
            else if (pathname == "TRANSACTIONS_LIST.txt"){
                System.out.println("\n >>>>>>> Adding Transactions from " + pathname + " to the Transactions List."); // Print Corresponding Message
                Transaction tr = null;
                line = reader.readLine();
                if (line.trim().equals("TRANSACTIONS_LIST")) {
                    line = reader.readLine();
                    if (line.trim().equals("{")) {
                        line = reader.readLine();
                        while (!line.equals("}")) {
                            if (line.trim().equals("TRANSACTION")) {
                                tr = new Transaction();
                                line = reader.readLine();
                                if (line.trim().equals("{")) {
                                    line = reader.readLine();
                                    if (line.trim().startsWith("KEY ")){
                                        tr.setKey(line.trim().substring(4).trim());
                                    }
                                    line = reader.readLine();
                                    if (line.trim().startsWith("BANKITEM_CODE ")){
                                        tr.setCardCode(line.trim().substring(14).trim());
                                        for (Object bp: bankProducts.values()){
                                            if (((BankProduct)bp).getBankProductCode().equals(tr.getCardCode())){
                                                (tr.cc).setKey((String) (((BankProduct) bp).getKey()));
                                                tr.cc = (CreditCard) bp;
                                            }else{ continue;}
                                        }
                                    }
                                    line = reader.readLine();
                                    if (line.trim().startsWith("VALUE ")){
                                        tr.setAmount(Double.parseDouble(line.trim().substring(6).trim()));
                                        //tr.getCreditCard().total += tr.getAmount(); 
                                    }
                                    line = reader.readLine();
                                    if (line.trim().startsWith("JUSTIFICATION ")){           
                                    tr.setReason((line.trim()).substring((line.trim()).indexOf("\"") + 1, (line.trim()).indexOf("\"", (line.trim()).indexOf("\"") + 1)));
                                    }
                                    line = reader.readLine();
                                    if (line.trim().equals("}")){
                                        transactions.put((String) tr.getKey(), tr);
                                    }
                                    line = reader.readLine();
                                    Transaction.counter++;
                                }   
                                    
                            }// TRANSACTION
                                
                        }// while
                        reader.close();
                            
                    }
                        
                }// TRANSACTIONS_LIST 
                    
            }// file: "TRANSACTIONS_LIST.txt"
        
        }//try
        

		catch (IOException e) {
            System.out.println	("Error reading file/line ...");
		}
		
		//if (eof == true){
        	if (pathname == "BANKITEMS_LIST.txt"){
                return bankProducts;
            }
            else if (pathname == "SALESMAN_LIST.txt"){
                return salespeople;
            }
            else if (pathname == "SALES_LIST.txt"){
                return sales;
            }
            else if (pathname == "TRANSACTIONS_LIST.txt"){
                return transactions;
            }
            else {return null;}
	} // Read File
	
	public void PrintList (HashMap<String, Object> list){
		System.out.println("\n { Printing List... } \n");
		for (String key: list.keySet()){
		    System.out.println("----------------------------------------------------------");
			System.out.println(list.get(key));
        }
        System.out.println("----------------------------------------------------------");
	} // PrintList
}