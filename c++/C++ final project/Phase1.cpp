#include <iostream>
#include <string.h>
#include <cmath>
#include <conio.h>
#include <windows.h>

using namespace std;

typedef struct{
   string name;
   string code;
   string category;
   int quantity;
   double ucost;
   double revenue;
   double tax;
   double profit;
}product;

product p[100];

int counter = 0;
string Ascname[100];
string Asccode[100];
string Dscname[100];
string Dsccode[100];
double totalrevenue = 0;
double totaltax = 0;
double totalprofit = 0;

void init();
void main_menu();
void input();
void search();
void modify();
void display();
void report();
void sort_menu();


int main(){
   bool exit = false;
   init();
   while(true){
      main_menu();
      switch(getch()){
      case '1':
         do{
            input();
            counter++;
            cout<<"Press...\n";
            cout<<"  'r' to Register another product.\n";
            cout<<"  Any Key to Main Menu.\n";
         }while(getch()=='r');
         break;
      if(counter > 0){
         case '2': search(); break;
         case '3': sort_menu(); break;
         case '4': modify(); break;
         case '5': display(); break;
         case '6': report(); break;
      }
      default:
         exit = true;
         break;
      }
      if(exit) break;
   }
   cout<<"\n\nThank You For Coming...\n\n\n\n\n";
}

void init(){
   system("color f0");
   for(int i=0;i<100;i++){
      p[i].name = "\0";
      p[i].code = "\0";
      p[i].category = "\0";
      p[i].quantity = 0;
      p[i].ucost = 0;
      p[i].tax = 0;
      Ascname[i] = "\0";
      Asccode[i] = "\0";
      Dscname[i] = "\0";
      Dsccode[i] = "\0";
   }
}
void main_menu(){
   system("cls");
   cout<<"Press..\n";
   cout<<"  1.To Register a Product.\n";
   if(counter > 0){
      cout<<"  2.To Search a Product.\n";
      cout<<"  3.To Sorted Output.\n";
      cout<<"  4.To Modify a Product.\n";
      cout<<"  5.To Display all Products.\n";
      cout<<"  6.To Report.\n";
   }
   cout<<"Any key To Exit.\n";
}
void input(){
   system("cls");
   cout<<"Product "<<counter+1<<endl;
   cout<<"Name: ";cin>>p[counter].name; Ascname[counter] = p[counter].name; Dscname[counter] = p[counter].name;
   cout<<"Category: ";cin>>p[counter].category;
   cout<<"PCode: ";cin>>p[counter].code; Asccode[counter] = p[counter].code; Dsccode[counter] = p[counter].code;
   cout<<"UnitCost: ";cin>>p[counter].ucost;
   cout<<"Quantity: ";cin>>p[counter].quantity;
   p[counter].revenue = (p[counter].ucost * p[counter].quantity);
   p[counter].tax = (p[counter].revenue * 0.15);
   totalrevenue += p[counter].revenue;
   totalprofit += (p[counter].revenue - p[counter].tax);
   totaltax += p[counter].tax;
}

void search_name(){
   system("cls");
   string namee;bool pass = false;
   cout<<"PName: ";cin>>namee;
   for(int i=0;i<counter;i++){
      if(p[i].name == namee){
         if(!pass) cout<<"Name  Category  PCode  UnitCost  Quantity\n";
         cout<<p[i].name<<"  "<<p[i].category<<"  ";
         cout<<p[i].code<<"  "<<p[i].ucost<<"  "<<p[i].quantity<<endl;
         pass = true;
      }
   }
   if(!pass) cout<<"Not Found!!\n";
   getch();
}
void search_code(){
   system("cls");
   string codee;bool elef = false;
   cout<<"PCod: ";cin>>codee;
   for(int i=0;i<counter;i++){
      if(p[i].code == codee){
         if(!elef) cout<<"Name  Category  PCode  UnitCost  Quantity\n";
         cout<<p[i].name<<"  "<<p[i].category<<"  ";
         cout<<p[i].code<<"  "<<p[i].ucost<<"  "<<p[i].quantity<<endl;
         elef = true;
      }
   }
   if(!elef) cout<<"Not Found!!\n";
   getch();
}
void search_category(){
   system("cls");
   string categorye;bool elef = false;
   cout<<"PCategory: ";cin>>categorye;
   for(int i=0;i<counter;i++){
      if(p[i].category == categorye){
         if(!elef) cout<<"Name  Category  PCode  UnitCost  Quantity\n";
         cout<<p[i].name<<"  "<<p[i].category<<"  ";
         cout<<p[i].code<<"  "<<p[i].ucost<<"  "<<p[i].quantity<<endl;
         elef = true;
      }
   }
   if(!elef) cout<<"Not Found!!\n";
   getch();
}
void search(){
   system("cls");
   cout<<"Press...\n";
   cout<<"  1.By Name.\n";
   cout<<"  2.By Pcode.\n";
   cout<<"  3.By Category.\n";
   cout<<"Any Key to Exit.\n";
   switch(getch()){
      case '1': search_name();  break;
      case '2': search_code(); break;
      case '3': search_category(); break;
      default: break;
   }
}
void modify(){
   system("cls");
   string codem;bool found = false;
   cout<<"PCode: ";cin>>codem;
   for(int i=0;i<counter;i++){
      if(p[i].code == codem){
            totalrevenue -= p[i].revenue;
            totalprofit -= (p[i].revenue - p[i].tax);
            cout<<"Product "<<i+1<<endl;
            cout<<"  Name: ";cin>>p[i].name;
            cout<<"  Categoty: ";cin>>p[i].category;
            cout<<"  ProductCode: ";cin>>p[i].code;
            cout<<"  UnitCost: ";cin>>p[i].ucost;
            cout<<"  Quantity: ";cin>>p[i].quantity;
            p[i].revenue = (p[i].ucost * p[i].quantity);
            p[i].tax = (p[i].revenue * 0.15);
            totalrevenue += p[i].revenue;
            totalprofit += (p[i].revenue - p[i].tax);
            found = true;
            break;
      }
   }
   if(!found) cout<<"PCode Not Found!!\n";
   getch();
}
void display(){
   system("cls");
   bool elef = false;
   for(int i=0;i<counter;i++){
         if(!elef) cout<<"Name  Category  PCode  UnitCost  Quantity\n";
         cout<<p[i].name<<"  "<<p[i].category<<"  \t";
         cout<<p[i].code<<"  \t\t"<<p[i].ucost<<"  \t"<<p[i].quantity<<endl;
         elef = true;
   }
   getch();
}
void report(){
   system("cls");
   cout<<"From "<<counter<<" Products.\n";
   cout<<"Total Tax: "<<totaltax<<endl;
   cout<<"Total Reveue: "<<totalrevenue<<endl;
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
      system("cls");sortND();
      if(getch()=='d'){
         for(int i=0;i<counter;i++){
            cout<<Dscname[i]<<endl;
         }
      }
      break;
   case '2':
      cout<<endl;sortCA();
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

