package product_info;

import java.util.ArrayList;
import java.util.Scanner;

public class Welcome {
	public ArrayList<Prod> prodobj = new ArrayList<Prod>();
	public static void main(String[] args) {
		
		Log_in.log();
		Options opt = new Options();
		
	}
}

class Wel {

	private String choice;

	public void menu() {
		Scanner input2 = new Scanner(System.in);

		boolean over;

		System.out.println("===============================================");
		System.out.println("                MAIN MENU                     =");
		System.out.println("===============================================");
		System.out.println("1. ADD A NEW PRODUCT.                         =");
		System.out.println("2. DISPLAY ALL PRODUCTS IN STORE              =");
		System.out.println("3. VIEW SINGLE PRODUCT DATA                   =");
		System.out.println("4. SORT PRODUCTS BY NAME                      =");
		System.out.println("5. DELETE A PRODUCT                           =");
		System.out.println("6. ABOUT US                                   =");
		System.out.println("7. EXIT                                       =");
		System.out.println("===============================================");
		System.out.println("\nCHOOSE AN OPTION::");
		over = false;

		while (!over) {

			String op = input2.next();
			if (op.length() != 1) {
				System.out.print("INVALID INPUT. CHOOSE AGAIN.\n");
				
			}

			switch (op.charAt(0)) {
			case '1': {
				choice = "1";
				over = true;
				break;
			}
			case '2': {
				choice = "2";
				over = true;
				break;
			}
			case '3': {
				choice = "3";
				over = true;
				break;
			}
			case '4': {
				choice = "4";
				over = true;
				break;
			}
			case '5':
				choice = "5";
				over = true;
				break;
			case '6':
				choice = "6";
				over = true;
				break;
			case '7':
				System.exit(0);
			default:
				System.err.println("INVALID INPUT. CHOOSE AGAIN.\n");

			}
		}

	}

	public String getChoice() {
		return choice;
	}

}
