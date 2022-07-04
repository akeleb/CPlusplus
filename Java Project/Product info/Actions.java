package product_info;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
class Actions {
	
	

	Wel welobj = new Wel();
	boolean checkdata = false;
	Scanner inp = new Scanner(System.in);
	public double validation() {//validation if the inputs are not correct()
		String value=inp.next();
		if(value.matches("[0-9.]*"))
		return Double.parseDouble(value);
		System.err.println("PLEASE ENTER VALID PRICE.");
		return validation();
	}

	public void add_new(ArrayList<Prod> prodobj ) {
		checkdata = true;
		
		System.out.println("HOW MANY PRODUCTS?");
		int num = inp.nextInt();
		DateFormat Date_format = new SimpleDateFormat("dd/MM/yyyy");
		for (int i = 0; i < num; i++) {
			
			Prod tempobj2 = new Prod();
			Date date = null;
			
			Scanner input = new Scanner(System.in);
			System.out.println("PRODUCT NAME-->");
			String name=input.nextLine();
			tempobj2.setName(name);
			System.out.println("QUANTITY-->");
			int qnt=input.nextInt();
			input.nextLine();
			tempobj2.setQt(qnt);
			tempobj2.setSn(qnt);//Adds either multiple or Single for the provided product
			tempobj2.setPr(tempobj2.calcprof());
			tempobj2.calcTax();
			tempobj2.calcProfit();
			System.out.println("EXPIRY DATE-->");
			while (date == null) {
				String new_date = input.nextLine();
				try {
					date = Date_format.parse(new_date);

					tempobj2.setExp_date(date);
				} catch (java.text.ParseException e) {

					System.err.println("INVALID DATE. ENTER AGAIN.");
				}

			}
			System.out.println("BUYING PRICE-->");
			tempobj2.setBp(validation());
			System.out.println("SELLING PRICE-->");
			tempobj2.setSp(validation());
			tempobj2.setRevenue(tempobj2.calcrev());
			
			prodobj.add(tempobj2);

		}

	}

	public void display(ArrayList<Prod> prodobj ) {
		if (checkdata == true) {
			
			System.out.println();
			System.out
					.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.printf("%10s %20s %20s %20s %20s %20s %20s %20s ",
					"PRODUCT NAME", " SELLING PRICE", " BUYING PRICE",
					" SERIAL NUM ", " REVENUE", " PROFIT ", " QUANTITY",
					" TAX ");
			System.out.println();
			System.out
					.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			for (Prod pr : prodobj) {
                      for(int i=0;i<pr.getQt();i++){
				System.out.printf("%10s %20f %20f %20s %20f %20f %20d %25f ",
						pr.getName(), pr.getSp(), pr.getBp(), pr.getSn(i),
						pr.getRv(), pr.getPr(), pr.getQt(), pr.getTax());
				System.out.println();
				System.out.println();
			}}
			System.out
					.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("\n\nTOTAL PROFIT:" + totProfit(prodobj));
		} else
			System.err.println("\nTHE STOCK IS EMPTY.PLEASE ADD PRODUCTS.\n");

	}

	public double totProfit(ArrayList<Prod> prodobj ) {
		int total = 0;
		for (Prod pr : prodobj) {
			total += pr.getPr();
		}
		return total;
	}

	public void view_single_prod_data(ArrayList<Prod> prodobj ) {
		boolean empty = true;
		
		String key;
		if (checkdata == true) {
			Scanner input3 = new Scanner(System.in);
			System.out.println("ENTER THE PRODUCT NAME:");
			key = input3.nextLine();
			for (Prod temp : prodobj) {
				if (temp.getName().equals(key)) {
					System.out.println("PRODUCT NAME:-->" + temp.getName());
					System.out.println("SELLING PRICE:-->" + temp.getSp());
					System.out.println("BUYING PRICE:-->" + temp.getBp());
					//System.out.println("SERIAL NUMBER:-->" + temp.getSn());
					System.out.println("REVENUE:-->" + temp.getRv());
					System.out.println("PROFIT:-->" + temp.getPr());
					System.out.println("QUANTITY:-->" + temp.getQt());
					empty = false;
					
				}
				
			}
			
			if (empty == true)
				System.err
						.println("THE REQUIRED PRODUCT IS NOT AVAILABLE IN THE STOCK.\n");

		} else
			System.err.println("\nTHE STOCK IS EMPTY.PLEASE ADD PRODUCTS\n\n");

	}

	public void sort_by_name(ArrayList<Prod> prodobj ) {
		ArrayList<Prod> tempobj = new ArrayList<Prod>();
		tempobj=prodobj;
		if (checkdata == true) {
			String tempvar;

			for (int i = 0; i < prodobj.size(); i++) {
				tempobj.add(prodobj.get(i));
			}
			int compare;
			for (int i = 0; i < tempobj.size(); i++) {
				for (int j = 0; j < tempobj.size() - i - 1; j++) {
					compare = tempobj.get(j).getName()
							.compareToIgnoreCase(tempobj.get(j + 1).getName());
					if (compare > 0) {
						tempvar = tempobj.get(j + 1).getName();
						tempobj.get(j + 1).prod_name = tempobj.get(j).getName();
						tempobj.get(j).prod_name = tempvar;
					}

				}
			}
			System.out.println();
			System.out
					.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.printf("%10s %20s %20s %20s %20s %20s %20s %20s ",
					"PRODUCT NAME", " SELLING PRICE", " BUYING PRICE",
					" SERIAL NUM ", " REVENUE", " PROFIT ", " QUANTITY",
					" TAX ");
			System.out.println();
			System.out
					.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			for (Prod temp : tempobj) {
                      for(int i=0;i<temp.getQt();i++){
				System.out.printf("%10s %20f %20f %20s %20f %20f %20d %25f ",
						temp.getName(), temp.getSp(), temp.getBp(), temp.getSn(i),
						temp.getRv(), temp.getPr(), temp.getQt(), temp.getTax());
				System.out.println();
				System.out.println();
			}}
			System.out
					.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		} else
			System.err.println("\nTHE STOCK IS EMPTY.PLEASE ADD PRODUCTS\n\n");

	}

	public void delete_product(ArrayList<Prod> prodobj ) {
		if (checkdata == true) {
			String specify;
			int x = 0;
			Scanner input2 = new Scanner(System.in);
			System.out.println("ENTER THE PRODUCT NAME YOU WANT TO DELETE.");
			specify = input2.nextLine();

			for (int j = 0; j < prodobj.size(); j++) {
				if (prodobj.get(j).getName().equals(specify)) {
					prodobj.remove(j);
					x = 1;

				}
			}
			if (x == 1)
				System.out.println("PRODUCT SUCCESFULLY DELETED.");
			if (prodobj.isEmpty()) {
				checkdata = false;
			}
		} else
			System.err.println("\nTHE STOCK IS EMPTY.PLEASE ADD PRODUCTS\n\n");

	}

	public void about_us() {
		System.out.println("\t \t Group Members(Group S3V3N) ");
		System.out.println("NAME\t\t\tID ");
		System.out.println("AKLESIYA MEKONNEN\t\tETS ");
		System.out.println("BETHELHEM SEIFU\t\tETS ");
		System.out.println("MELKAMSIRA ANTEHUN\t\tETS ");
		System.out.println("NATNAEL MESFIN\t\tETS ");
		System.out.println("TESFAHUN KEBEDE\t\tETS ");


	}
}
