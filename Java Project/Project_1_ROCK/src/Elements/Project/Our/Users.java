package Elements.Project.Our;

import java.util.Scanner;

public abstract class Users {
    
    String  name, userType, password;
    int     id, creditNum;
    double  initialAmount;
    Address addr;

    Users(String name, int creditNum, int id, Address addr, String userType, double initialAmount, String password) {
        this.name          = name;
        this.creditNum     = creditNum;
        this.id            = id;
        this.addr          = addr;
        this.userType      = userType;
        this.initialAmount = initialAmount;
        this.password = password;
    }
    
    abstract int menu();                         //All users(Buyer and Seller) have Menu;
    abstract void display();                //All users(Buyer and Seller) have Display;
    abstract void Profile();
    
    public static int homePage(){
        
        Scanner input = new Scanner(System.in);        
        int option;
        
        System.out.println("\n--------------------------------------------------------------------------------------------------------------");
        System.out.println("                                                    Home Page");
        System.out.println(    "--------------------------------------------------------------------------------------------------------------\n\n");
        
        System.out.println("______________________________________________________________________________________________________________");
            System.out.println("\t                         ()                                          ()         ");
            System.out.println("\t                         ()                 Home Page                ()         ");
            System.out.println("\t                  _______()__________________________________________()________ ");
            System.out.println("\t                 [                                                             ]");
            System.out.println("\t                 [           PRESS       TO                                    ]");
            System.out.println("\t                 [_____                                                   _____]");
            System.out.println("\t                 [_____                                                   _____]");	
            System.out.println("\t                 [ ]___        1        Create A New Account              ___[ ]");	
            System.out.println("\t                 [ ]___        2        Login With An Existing Account    ___[ ]");	
            System.out.println("\t                 [ ]___        3        display all Sellers               ___[ ]");
            System.out.println("\t                 [ ]___        4        Share Our Site                    ___[ ]");
            System.out.println("\t                 [ ]___        5        About                             ___[ ]");
            System.out.println("\t                 [ ]___        0        EXIT THE PROGRAM                  ___[ ]");
            System.out.println("\t                 [ ]___                                                   ___[ ]");
            System.out.println("\t                 [_____________________________________________________________]");
            System.out.println("                                                                  ");
            System.out.println("                                                                  ");
        System.out.println("______________________________________________________________________________________________________________\n");
        
        System.out.print("\t\t Plz enter a choice:  ");
	option = SegnoGebeya.validateNumber();
        
        return option;
    }    
}
class Address{
    int houseNum;
    String subCity;
}
