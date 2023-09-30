import java.util.*;
import java.io.*;

public class CreateFileApp {

	public void CreateFile(HashMap<String, Object> List, String pathname) {

		FileWriter writer = null;

		try {
			if (pathname == "BANKITEMS_LIST.txt") {
				System.out.println(" >>>>>>> Writing data from the BankProducts List to file with name: BANKITEMS_LIST.txt ...");
				writer = new FileWriter(new File("BANKITEMS_LIST.txt"));
				writer.write("BANKITEMS_LIST\n{\n");
				for (String key : List.keySet()) {
					BankProduct bp = (BankProduct) List.get(key);

					if (bp instanceof Loan) {
						writer.write("   BANKITEM" + "\n" + "   {" + "\n" + "\tTYPE Loan "
								+ "\n" + "\t" + "KEY " + bp.getKey()
								+ "\n" + "\t" + "CODE " + ((Loan) bp).getBankProductCode()
								+ "\n" + "\t" + "NUMBER " + bp.getNum()
								+ "\n" + "\t" + "AFM " + bp.getAfm()
								+ "\n" + "\t" + "AMOUNT " + ((Loan) bp).getAmount()
								+ "\n" + "   }" + "\n");
					} // LOAN
					else if (bp instanceof CreditCard) {
						writer.write("   BANKITEM" + "\n" + "   {" + "\n" + "\t" + "TYPE CreditCard "
								+ "\n" + "\t" + "KEY " + bp.getKey()
								+ "\n" + "\t" + "CODE " + ((CreditCard) bp).getBankProductCode()
								+ "\n" + "\t" + "NUMBER " + bp.getNum()
								+ "\n" + "\t" + "AFM " + bp.getAfm()
								+ "\n" + "\t" + "AMOUNT SPENT " + ((CreditCard) bp).getTotal()
								+ "\n" + "   }" + "\n");
					} // CREDIT CARD

				} // BANKITEM
				writer.write("}");

			} else if (pathname == "SALESMAN_LIST.txt") {
				System.out.println(" >>>>>>> Writing data from the SalesPeople List to file with name: SALESMAN_LIST.txt ...");
				writer = new FileWriter(new File("SALESMAN_LIST.txt"));
				writer.write("SALESMAN_LIST\n{\n");
				for (String key : List.keySet()) {
					SalesPerson sp = (SalesPerson) List.get(key);
					writer.write("   SALESMAN" + "\n" + "   {"
							+ "\n" + "\t" + "KEY " + sp.getKey()
							+ "\n" + "\t" + "CODE " + sp.getSalesPersonCode()
							+ "\n" + "\t" + "SURNAME " + "\"" + sp.getLastName() + "\""
							+ "\n" + "\t" + "FIRSTNAME " + "\"" + sp.getFirstName() + "\"" 
							+ "\n" + "\t" + "AFM " + sp.getAfm()
							+ "\n" + "   }" + "\n");
				}
				writer.write("}");

			} else if (pathname == "SALES_LIST.txt") {
				System.out.println(" >>>>>>> Writing data from the Sales List to file with name: SALES_LIST.txt ...");
				writer = new FileWriter(new File("SALES_LIST.txt"));
				writer.write("SALES_LIST\n{\n");
				for (String key : List.keySet()) {
					Sale sl = (Sale) List.get(key);
					writer.write("   SALE" + "\n" + "   {"
							+ "\n" + "\t" + "KEY " + sl.getKey()
							+ "\n" + "\t" + "SALESMAN_CODE " + sl.getSalesPersonCode()
							+ "\n" + "\t" + "BANKITEM_TYPE " + (sl.getBankProduct()).getClass().getName()
							+ "\n" + "\t" + "BANKITEM_CODE " + sl.getBankProductCode()
							+ "\n" + "\t" + "JUSTIFICATION " + "\"" + sl.getReason() + "\""
							+ "\n" + "   }" + "\n");
				}
				writer.write("}");

			} else if (pathname == "TRANSACTIONS_LIST.txt") {
				System.out.println(" >>>>>>> Writing data from the Transactions List to file with name: TRANSACTIONS_LIST.txt ...");
				writer = new FileWriter(new File("TRANSACTIONS_LIST.txt"));
				writer.write("TRANSACTIONS_LIST\n{\n");
				for (String key : List.keySet()) {
					Transaction tr = (Transaction) List.get(key);
					writer.write("   TRANSACTION" + "\n" + "   {"
							+ "\n" + "\t" + "KEY " + tr.getKey()
							+ "\n" + "\t" + "BANKITEM_CODE " + tr.getCardCode()
							+ "\n" + "\t" + "VALUE " + tr.getAmount()
							+ "\n" + "\t" + "JUSTIFICATION " + "\"" + tr.getReason() + "\"" 
							+ "\n" + "   }" + "\n");

				}
				writer.write("}");
			}
			writer.close();
			System.out.println("Writing proccess finished succefully.");
		} // try

		catch (IOException e) {
			System.err.println("Error writing file.");
		}
	}

}
