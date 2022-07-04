package product_info;

import java.util.*;
import java.io.IOException;

public class Log_in {

	public static void log() {

		Scanner input = new Scanner(System.in);
		String username;
		String password;
		String userName = "Admin";
		String passWord = "12345";
		System.out.println("===============================");
		System.out.println("  STOCK MANAGMENT SYSTEM ");
		System.out.println("===============================\n");
		System.out.println("\n\tLOG IN TO YOUR ACCOUNT.\n\n");
		boolean stop = false;
		while (!stop) {
			System.out.print("USER NAME:");
			username = input.next();
			System.out.print("\nPASSWORD:");
			password = input.next();
			if (username.equals(userName) && password.equals(passWord)) {
				System.out.println("\nYOU ARE LOGGED IN.");
				System.out.println("\n\nPlease press enter to continue..");
				try {
					System.in.read();
				} catch (IOException e) {
					e.printStackTrace();
				}
				stop = true;
				break;
			} else {
				System.err
						.println("\nINVALID USER NAME OR PASSWORD. TRY AGAIN.\n\n");
				
			}

		}

	}

}
