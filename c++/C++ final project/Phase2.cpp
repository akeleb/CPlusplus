#include <iostream>
#include <string.h>
#include <cmath>
#include <conio.h>
#include <windows.h>

using namespace std;

int counter = 0;   //intiger value to count the
                   //products (increment after a registration)

typedef class{
private:          //The private values of a product.
   string name;
   string code;
   string category;
   int quantity;
   double ucost;
   double revenue;
   double tax;
   double profit;
public:            //The public functions of the product.
   string get_name()
   {
       return name;
   }
   void set_name(string namee)
   {
       name = namee;
   }
   string get_code()
   {
       return code;
   }
   void set_code(string codee)
   {
       code = codee;
   }
   string get_category()
   {return category;}
   void set_category(string categorye)
   {category = categorye;}
   double get_quantity()
   {return quantity;}
   void set_quantity(double quantitye)
   {quantity = quantitye;}
   double get_ucost()
   {return ucost;}
   void set_ucost(double ucoste)
   {ucost = ucoste;}
   double get_tax()
   {return tax;}
   void set_tax(double taxe)
   {tax = taxe;}
   double get_revenue(){
       return revenue;}
   double get_profit(){
       return profit;}

   void init(){           //Function called for initializing the product attributes.
      name = "\0";
      code = "\0";
      category = "\0";
      quantity = 0;
      ucost = 0;
      revenue = 0;
      tax = 0;
      profit = 0;
   }
   void get_all(){            //Function called for registering all the product attributes.
      cout<<"Name: ";cin>>name;
      cout<<"Pcode: ";cin>>code;
      cout<<"category: ";cin>>category;
      cout<<"UnitCost: ";cin>>ucost;
      cout<<"Quantity: ";cin>>quantity;
      revenue = ucost * quantity;
      tax = revenue * 0.15;
      profit = revenue - tax;
   }
   void print_ths(){               //Function for printing a details of a product.
      cout<<name<<"  "<<category<<"  ";
      cout<<code<<"  "<<ucost<<"  "<<quantity<<endl;
   }

}product;

string Ascname[100];             //A string array to hold ascendingly ordered product names.
string Asccode[100];             //A string array to hold ascendingly ordered product codes.
string Dscname[100];             //A string array to hold descendingly ordered product names.
string Dsccode[100];             //A string array to hold descendingly ordered product codes.
double totalprofit;
double totalrevenue;
double totaltax;

product p[100];                 //initializing an array of products.

void main_menu();                //function for menu output.
void input();                    //function for accepting details
void search();                   //function for searching a product
void modify();                   //function to modify products
void display();                  //function to display all products
void report();                   //function to show the report
void sort_menu();                //function to show sorted output

int main(){
   system("color f0");
   bool exit = false;
   while(true){
      main_menu();
      switch(getch()){
      case '1':
         START:
         input();
         counter++;
         cout<<"Press 'r' to register another Product.\n";
         if(getch()=='r')
             goto START;
         break;
      if(counter > 0){
         case '2': search(); break;
         case '3': sort_menu(); break;
         case '4': modify(); break;
         case '5': display(); break;
         case '6': report(); break;
      }
      default: exit = true; break;
      }
      if(exit) break;
   }
   cout<<"  Thank You for Coming.\n\n\n\n";
}
void main_menu(){
   system("cls");
   cout<<"Press...\n";
   cout<<"  1.To Register a Product.\n";
   if(counter > 0){
      cout<<"  2.To Search a Product.\n";
      cout<<"  3.To Sorted OutPut.\n";
      cout<<"  4.To Modify a Product;.\n";
      cout<<"  5.To Display all Products.\n";
      cout<<"  6.To Report.\n";
   }
   cout<<"Any Key to Exit.\n";
   getch();
}
void input(){
    system("cls");
    p[counter].init();
    cout<<"Product "<<counter+1<<endl;
    p[counter].get_all();
    totalrevenue += p[counter].get_revenue();
    totaltax += p[counter].get_tax();
    totalprofit += p[counter].get_profit();
    Ascname[counter] = p[counter].get_name();
    Asccode[counter] = p[counter].get_name();
    Dscname[counter] = p[counter].get_name();
    Dsccode[counter] = p[counter].get_name();

}

void searchname(){
   system("cls");
   string namee;
   bool pass = false;
   cout<<"Name: ";cin>>namee;
   for(int i=0;i<counter;i++){
      if(p[i].get_name() == namee){
         if(!pass)
             cout<<"Name  Category  PCode  UnitCost  Quantity\n";
         p[i].print_ths();
         pass = true;
      }
   }
   if(!pass) cout<<"Not Found!!\n";
}
void searchcode(){
   system("cls");
   string codee;
   bool pass = false;
   cout<<"Code: ";cin>>codee;
   for(int i=0;i<counter;i++){
      if(p[i].get_code() == codee){
         if(!pass) cout<<"Name  Category  PCode  UnitCost  Quantity\n";
         p[i].print_ths();
         pass = true;
      }
   }
   if(!pass) cout<<"Not Found!!\n";
}
void searchcategory(){
   system("cls");
   string categorye;bool pass = false;
   cout<<"Category: ";cin>>categorye;
   for(int i=0;i<counter;i++){
      if(p[i].get_category() == categorye){
         if(!pass) cout<<"Name  Category  PCode  UnitCost  Quantity\n";
         p[i].print_ths();
         pass = true;
      }
   }
   if(!pass) cout<<"Not Found!!\n";
}
void search(){
   system("cls");
   cout<<"Press...\n";
   cout<<"  1.By Name.\n";
   cout<<"  2.By Pcode.\n";
   cout<<"  3.By Category.\n";
   cout<<"Any Key to Exit.\n";
   switch(getch()){
      case '1': searchname(); break;
      case '2': searchcode(); break;
      case '3': searchcategory(); break;
      default: break;
   }
   getch();
}
void modify(){
   system("cls");
   string codee;bool test = false;
   cout<<"Pcode: ";cin>>codee;
   for(int i=0;i<counter;i++){
      if(p[i].get_code()==codee){
         totalprofit -= p[i].get_profit();
         totalrevenue -= p[i].get_revenue();
         totaltax -= p[i].get_tax();
         cout<<"Product "<<i<<endl;
         p[i].get_all();
         totalrevenue += p[i].get_revenue();
         totaltax += p[i].get_tax();
         totalprofit += p[i].get_profit();
         cout<<"Modification Succesfull.\n";
         test = true;
         break;
      }
   }
   if(!test) cout<<"Not Found!!\n";
}
void display(){
   system("cls");
   bool elef = false;
   for(int i=0;i<counter;i++){
         if(!elef) cout<<"Name  Category  PCode  UnitCost  Quantity\n";
         p[i].print_ths();
         elef = true;
   }
}
void report(){
   system("cls");
   cout<<"From "<<counter<<" Products.\n";
   cout<<"Total Reveue: "<<totalrevenue<<endl;
   cout<<"Total Tax: "<<totaltax<<endl;
   cout<<"Total Profit: "<<totalprofit<<endl;
   cout<<"Press Any Key to Main Menu.";
   getch();
}
void sortNA(){
   bool go = true;
   while(true){
      for(int i=1;i<counter;i++){
         if(Ascname[i-1].compare(Ascname[i])== 1){
            Ascname[i-1].swap(Ascname[i]);
            go = false;
         }
      }
      if(go) break;
   }
}
void sortND(){
   bool go = true;
   while(true){
      for(int i=1;i<counter;i++){
         if(Dscname[i-1].compare(Dscname[i])== -1){
            Dscname[i-1].swap(Dscname[i]);
            go = false;
         }
      }
      if(go) break;
   }
}
void sortCA(){
   bool go = true;
   while(true){
      for(int i=1;i<counter;i++){
         if(Asccode[i-1].compare(Asccode[i])== 1){
            Asccode[i-1].swap(Asccode[i]);
            go = false;
         }
      }
      if(go) break;
   }
}
void sortCD(){
   bool go = true;
   while(true){
      for(int i=1;i<counter;i++){
         if(Dsccode[i-1].compare(Dsccode[i])== -1){
            Dsccode[i-1].swap(Dsccode[i]);
            go = false;
         }
      }
      if(go) break;
   }
}
void sort_menu(){
   system("cls");
   cout<<"Press...\n";
   cout<<"  1.By Name.\n";
   cout<<"  2.By Code.\n";
   cout<<"Any Key To Exit.\n";
   switch(getch()){
   case '1':
      cout<<endl; sortNA();
      for(int i=0;i<counter;i++){
         cout<<Ascname[i]<<endl;
      }
      cout<<"Press 'd' for descending.\n";
      system("cls"); sortND();
      if(getch()=='d'){
         for(int i=0;i<counter;i++){
            cout<<Dscname[i]<<endl;
         }
      }
      break;
   case '2':
      cout<<endl; sortCA();
      for(int i=0;i<counter;i++){
         cout<<Asccode[i]<<endl;
      }
      cout<<"Press 'd' for descending.\n";
      if(getch()=='d'){
         system("cls");sortCD();
         for(int i=0;i<counter;i++){
            cout<<Dsccode[i]<<endl;
         }
      }
      break;
   default:
      break;
   }
}
