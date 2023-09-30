
/*
@ author: Nikos Mitsakis, AM: 3210122
@ author: Panagiotis Moschos, AM: 3210127
*/
import java.text.DecimalFormat;
import java.util.*;

public class mainApp {
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("0.00");
        HashMap<String, SalesPerson> salespeople = new HashMap<String, SalesPerson>();
        HashMap<String, BankProduct> bankProducts = new HashMap<String, BankProduct>();
        HashMap<String, Sale> sales = new HashMap<String, Sale>();
        HashMap<String, Transaction> transactions = new HashMap<String, Transaction>();

        // Creating salespeople
        SalesPerson sp1 = new SalesPerson("SP1", "Panagiotis", "Mavridis", "123456789");
        SalesPerson sp2 = new SalesPerson("SP2", "Dimitris", "Katsifodis", "777654321");
        SalesPerson sp3 = new SalesPerson("SP3", "Giorgos", "Papadopoulos", "888654321");
        SalesPerson sp4 = new SalesPerson("SP4", "Nikos", "Gianniotis", "999654321");

        // Adding them to the SalesPerson HashMap
        salespeople.put(sp1.getKey(), sp1);
        salespeople.put(sp2.getKey(), sp2);
        salespeople.put(sp3.getKey(), sp3);
        salespeople.put(sp4.getKey(), sp4);

        // Creating BankProducts - > Here Loans
        Loan l1 = new Loan("l1", "123456789", "123456789", 2000);
        Loan l2 = new Loan("l2", "123456789", "123456789", 2000);
        Loan l3 = new Loan("l3", "123456789", "123456789", 2000);
        Loan l4 = new Loan("l4", "123456789", "123456789", 2000);

        // Adding them to the BankProducts HashMap
        bankProducts.put(l1.getKey(), l1);
        bankProducts.put(l2.getKey(), l2);
        bankProducts.put(l3.getKey(), l3);
        bankProducts.put(l4.getKey(), l4);

        // Creating BankProducts - > Here Credit Cards
        CreditCard cc1 = new CreditCard("c1", "123456789", "123456789");
        CreditCard cc2 = new CreditCard("c2", "123456789", "123456789");
        CreditCard cc3 = new CreditCard("c3", "123456789", "123456789");
        CreditCard cc4 = new CreditCard("c4", "123456789", "123456789");

        // Adding them to the BankProducts HashMap
        bankProducts.put(cc1.getKey(), cc1);
        bankProducts.put(cc2.getKey(), cc2);
        bankProducts.put(cc3.getKey(), cc3);
        bankProducts.put(cc4.getKey(), cc4);

        // Making some Sales
        sp1.doSale(l1, "Student Loan", sales);
        sp1.doSale(cc1, "Fun", sales);
        sp2.doSale(l2, "Home Loan", sales);
        sp2.doSale(cc2, "Shopping", sales);
        sp3.doSale(l3, "Car Loan", sales);
        sp3.doSale(cc3, "Travelling", sales);
        sp4.doSale(l4, "Business Loan", sales);
        sp4.doSale(cc4, "Business", sales);

        // Making some Transactions using the sold Credit Cards
        cc1.doTransaction(200, "Food", transactions);
        cc1.doTransaction(200, "Food", transactions);
        cc1.doTransaction(200, "Food", transactions);
        cc1.doTransaction(200, "Food", transactions);
        cc2.doTransaction(200, "Food", transactions);
        cc2.doTransaction(200, "Food", transactions);
        cc2.doTransaction(200, "Food", transactions);
        cc2.doTransaction(200, "Food", transactions);
        cc3.doTransaction(200, "Food", transactions);
        cc3.doTransaction(200, "Food", transactions);
        cc3.doTransaction(200, "Food", transactions);
        cc3.doTransaction(200, "Food", transactions);
        cc4.doTransaction(200, "Food", transactions);
        cc4.doTransaction(200, "Food", transactions);
        cc4.doTransaction(200, "Food", transactions);
        cc4.doTransaction(200, "Food", transactions);

        // Menu
        boolean done = false;
        Scanner sc = new Scanner(System.in);
        while (!done) {
            System.out.println("\n1. New Salesperson");
            System.out.println("2. New Bankproduct");
            System.out.println("3. New Sale");
            System.out.println("4. New Credit Card Transaction");
            System.out.println("5. Display Loan List");
            System.out.println("6. Salesperson's Comission");
            System.out.println("7. Salesperson's Credit Card Transactions");
            System.out.println("8. Comission of every Salesperson");
            System.out.println("9. Total comission of all Salespeople");
            System.out.println("0. Exit");

            int answear = sc.nextInt();
            if (answear == 1) {
                boolean finish = false;
                while (!finish) {
                    System.out.println("Please enter the saleperson's code");
                    String spcode = sc.next();
                    System.out.println("Please enter the saleperson's surname");
                    String spsurname = sc.next();
                    System.out.println("Please enter the saleperson's name");
                    String spname = sc.next();
                    System.out.println("Please enter the saleperson's registration number");
                    String sprn = sc.next();

                    SalesPerson sp = new SalesPerson(spcode, spsurname, spname, sprn);
                    salespeople.put(sp.getKey(), sp);
                    System.out.println("**********************************************************");
                    System.out.println(
                            "If you would like to add another salesperson on the list: Press 1\nIf you would like to return to the main menu: Press 2 ");
                    int nav = sc.nextInt();
                    if (nav == 2) {
                        break;
                    } else if (nav == 1) {

                    } else {
                        System.out.println();
                        System.out.println("Wrong input please try again.");
                        break;
                    }
                }

            } else if (answear == 2) {
                boolean finish1 = false;
                while (!finish1) {
                    System.out.println("If you want to enter a loan: Press 1\nIf you want to enter a credit card: Press 2");
                    int nav1 = sc.nextInt();
                    if (nav1 == 1) {
                        System.out.println();
                        System.out.println("Please enter the product's bankcode: ");
                        String lncode = sc.next();
                        System.out.println("Please enter the product's number: ");
                        String lnnum = sc.next();
                        System.out.println("Please enter the client's AFM: ");
                        String lnafm = sc.next();
                        System.out.println("Please enter the amount of the loan: ");
                        double lnamount = sc.nextDouble();
                        Loan l = new Loan(lncode, lnnum, lnafm, lnamount);
                        bankProducts.put(l.getKey(), l);
                        break;
                    } else if (nav1 == 2) {
                        System.out.println();
                        System.out.println("Please enter the product's bankcode: ");
                        String CCcode = sc.next();
                        System.out.println("Please enter the product's number: ");
                        String CCnum = sc.next();
                        System.out.println("Please enter the client's AFM: ");
                        String CCafm = sc.next();
                        CreditCard cc = new CreditCard(CCcode, CCnum, CCafm);
                        bankProducts.put(cc.getKey(), cc);
                        break;
                    } else {
                        System.out.println();
                        System.out.println("Wrong input.\nPlease try again");
                        break;
                    }

                }
            } else if (answear == 3) {
                System.out.println();
                System.out.println("Here is the list of all the salespeople: ");
                SalesPerson.displaySalesPeople(salespeople);
                System.out.println();
                System.out.println("Please enter the key of the saleperson that sold the product: ");
                String salepersonKey = sc.next();
                SalesPerson sp = SalesPerson.lookupSalesPerson(salepersonKey, salespeople);
                System.out.println();
                System.out.println("Here is the list of all the bank products: ");
                BankProduct.displayBankProducts(bankProducts);
                System.out.println();
                System.out.println("Please enter the key of the product that got sold: ");
                String bpKey = sc.next();
                BankProduct bp = BankProduct.lookupBankProduct(bpKey, bankProducts);

                if (sp == null | bp == null) {
                    System.out.println();
                    System.out.println(
                            "One or both of the keys you have entered do not match any of the keys in the Lists.");
                    System.out.println("Please try again.");
                } else {
                    System.out.println();
                    System.out.println("Please enter the reason for the sale of the product: ");
                    String bpreason = sc.next();
                    sp.doSale(bp, bpreason, sales);
                    System.out.println();
                    System.out.println("The sale was sucessfull.");

                }

            } else if (answear == 4) {
                Boolean finish2 = false;
                while (!finish2) {
                    System.out.println();
                    System.out.println("Here is the list of all the credit cards: ");
                    CreditCard.displayCreditCards(bankProducts);
                    System.out.println();
                    System.out
                            .println("Please enter the key of the credit card that you want to do the transaction with:");
                    String ccKey = sc.next();
                    BankProduct cc = CreditCard.lookupBankProduct(ccKey, bankProducts);
                    System.out.println("Please enter the amount of the transaction: ");
                    double ccamount = sc.nextDouble();
                    System.out.println("Please enter the reason for the transaction: ");
                    String ccreason = sc.next();
                    if (bankProducts.containsValue(cc)) {
                        System.out.println();
                        ((CreditCard) cc).doTransaction(ccamount, ccreason, transactions);
                    } else {
                        System.out.println();
                        System.out.println("The transaction was unsucessfull, because of a wrong credit card key.\nPlease try again.");
                    }
                    System.out.println();
                    System.out.println(
                            "If you would like to do another transaction: Press 1\nIf you would like to return to the main menu: Press 2 ");
                    int nav3 = sc.nextInt();
                    if (nav3 == 2) {
                        break;
                    } else if (nav3 == 1) {

                    } else {
                        System.out.println();
                        System.out.println("Wrong input.\nPlease try again.");
                        break;
                    }
                }

            } else if (answear == 5) {
                System.out.println();
                System.out.println("Here is the list of all the sold loans: ");
                Loan.displayLoans(bankProducts);
            } else if (answear == 6) {
                Boolean finish3 = false;
                while (!finish3) {
                    System.out.println();
                    System.out.println("Here is the list of all the salespeople: ");
                    SalesPerson.displaySalesPeople(salespeople);
                    System.out.println();
                    System.out
                            .println("Please enter the key of the salesperson that you want to calculate his/her comission: ");
                    String SpKey = sc.next();
                    SalesPerson sp = SalesPerson.lookupSalesPerson(SpKey, salespeople);
                    
                    sp.analyzedCreditCardComission(sales, transactions);
                    sp.analyzedLoanComission(sales, transactions);
                    double loancom = sp.calculateLoanComission(sales, transactions);
                    double cccom = sp.calculateCreditCardComission(sales, transactions);
                    double totalcom = loancom + cccom;
                    if (totalcom == 0 | sp == null) {
                        System.out.println(totalcom);
                        System.out.println("Invalid Salesperson key.\nPlease try again.");
                    } else {
                        System.out.println();
                        System.out.println("The total comission of the salesperson with key " + sp.getKey() + " is: " + totalcom);
                    }
                    System.out.println();
                    System.out.println(
                            "If you would like to calculate another salesperson's comission: Press 1\nIf you would like to return to the main menu: Press 2 ");
                    int nav4 = sc.nextInt();
                    if (nav4 == 2) {
                        break;
                    } else if (nav4 == 1) {

                    } else {
                        System.out.println();
                        System.out.println("Wrong Input.\nPlease try again");
                        break;
                    }
                }
            } else if (answear == 7) {
                Boolean finish4 = false;
                while (!finish4) {
                    System.out.println();
                    System.out.println("Here is the list of all the salespeople: ");
                    SalesPerson.displaySalesPeople(salespeople);
                    System.out.println();
                    System.out.println(
                            "Please enter the key of the salesperson that you wish to see the transactions from his/hers sold cards: ");
                    String spkey = sc.next();
                    SalesPerson sp = SalesPerson.lookupSalesPerson(spkey, salespeople);
                    if (sp == null){
                        System.out.println("Invalid Salesperson key.\nPlease try again.");
                    }else{
                    SalesPerson.displayTransactionsOfSalesPerson(sp, transactions, sales);
                    System.out.println();
                    }
                    System.out.println(
                            "If you want to see another salesperson's transactions from his/hers sold cards: Press 1\nIf you want to return to the main menu: Press 2");
                    int nav4 = sc.nextInt();
                    if (nav4 == 2) {
                        break;
                    } else if (nav4 == 1) {

                    } else {
                        System.out.println();
                        System.out.println("Wrong input.\nPlease try again.");
                        break;
                    }

                }
            } else if (answear == 8) {
                for (String key : salespeople.keySet()) {
                    SalesPerson sp = salespeople.get(key);
                    double loancom = sp.calculateLoanComission(sales, transactions);
                    double cccom = sp.calculateCreditCardComission(sales, transactions);
                    double total = loancom + cccom;
                    System.out.println();
                    System.out
                            .println("Salesperson's comission with key " + sp.getKey() + " is: " + total);
                }
            } else if (answear == 9) {
                double allcom = 0;
                for (String key : salespeople.keySet()) {
                    SalesPerson sp = salespeople.get(key);
                    double loancom = sp.calculateLoanComission(sales, transactions);
                    double cccom = sp.calculateCreditCardComission(sales, transactions);
                    double total = loancom + cccom;
                    System.out.println("Code = " + sp.getSalesPersonCode() + "\n" + " Lastname = " + sp.getLastName()
                            + "\n" + " Firstname = " + sp.getFirstName());
                    System.out.println(total + "\n" + "***********************");
                    allcom += total;
                }
                System.out.println();
                System.out.println("The total comission of all salespeople is: " + df.format(allcom));
            } else if (answear == 0) {
                System.out.println();
                System.out.println("Thank you for using our app !");
                done = true;
            }

        }
        sc.close();
    }
}
