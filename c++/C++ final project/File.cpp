/*************************
******Libraries USED******
**************************/

#include <iostream>   //FOR CONSOLE INPUT/OUTPUT.
#include <fstream>    //FOR FILE INPUT/OUTPUT.
#include <string.h>   //FOR USING STRING TYPE.
#include <vector>     //FOR USING VECTOR TEMPLATE.
#include <conio.h>    //FOR USING FUNCTIONS LIKE getch().
#include <windows.h>  //FOR USING WINDOWS SPECIFIC FUNCTIONS.

using namespace std;

/*******************************
******Function Decleration******
********************************/

void Mmenu();        //THE MAIN MENU.
void input();        //FOR INSERTING DETAILS.
void display();      //FOR DISPLAYING ALL DATA STORED.
void sorted();       //FOR GETTING A SORTED OUTPUT.
void search();       //FOR SEARCHING A PRODUCT.
void clear_dbase();  //FOR CLEARING THE WHOLE DATA STORED.
void del();          //FOR DELETING A SPECIFIC PRODUCT.

/*************************************
******The Main Program Goes Here******
**************************************/

int main(){
    system("color 3F");
    bool exit=false;
    do{
        Mmenu();
        switch(getch()){
        case '1': input(); break;
        case '2': search(); break;
        case '3': sorted(); break;
        case '4': del(); break;
        case '5': display(); break;
        case '6': clear_dbase(); ;break;
        default:
            system("title Group 7 - Product Information: GOOD BYE");
            exit=true;
            cout<<"Thank You for Visiting us.";
            Sleep(1000);
            system("cls");
            system("color 0F");
        }
    }while(!exit);
    delete &exit;
}

/******************************
******Function Definition******
*******************************/

void capitalize(string& x){
   for(int i=0;i<(int)x.size();i++){
      if(x[i] >= 'a' && x[i] <= 'z'){
         x[i] += 'A'-'a';
      }
      if(x[i] == '\0') break;
   }
}
void Mmenu(){
    system("title Group 7 - Product Information: Main Menu");
    system("cls");
    cout<<"Press...\n";
    cout<<"  1.For Registering a product.\n";
    
    cout<<"  2.For Searching a product.\n";
    cout<<"  3.For Sorted Output of registerd products.\n";
    cout<<"  4.For Deleting A specfic Product.\n";
    cout<<"  5.To Display all product.\n";
    cout<<"  6.To Clear the Database(all saved data).\n";
    cout<<"press Any Key exept the above to Exit.\n\n\n\n";
}
void input(){
    int quantity;
    string name,code,category;
    float ucost,tcost,tax,profit;
    system("title Group 7 - Product Information: Registration");
    system("cls");
    fstream f;
    f.open("aastu.db",ios::in|ios::app|ios::out);
    cout<<"NEW PRODUCT...\n";
    cout<<"Name: ";     cin>>name;     capitalize(name);
    cout<<"Code: ";     cin>>code;     capitalize(code);
    cout<<"Category: "; cin>>category; capitalize(category);
    cout<<"Ucost: ";    cin>>ucost;
    cout<<"Quantity: "; cin>>quantity;
    tcost = ucost*quantity;
    tax = tcost*0.15;
    profit = tcost-tax;
    f<<name<<'\t'<<code<<'\t'<<category<<'\t'<<ucost<<'\t'<<quantity<<'\t'<<tcost<<'\t'<<tax<<'\t'<<profit<<endl;
    f.close();
    f.open("aastu_code.db",ios::app|ios::out);
    f<<code<<'\t'<<name<<'\t'<<category<<'\t'<<ucost<<'\t'<<quantity<<'\t'<<tcost<<'\t'<<tax<<'\t'<<profit<<endl;
    f.close();
    cout<<"Registration Succesful.";
    getch();
}
void display(){
    system("title Group 7 - Product Information: All Products");
    system("cls");
    string line;bool one=false;
    fstream f;
    f.open("aastu.db",ios::app|ios::in|ios::out);
    while(getline(f,line)){
        if(!one) cout<<"Name   Code   Category   Ucost   Quantity   Revenue   Tax   Profit"<<endl;
        cout<<line<<endl;
        one = true;
    }
    if(!one) cout<<"\n\n\nNothing Found,Please Register to SEE Something.\n";
    f.close();
    getch();
}
void clear_dbase(){
    system("cls");
    fstream f;
    f.open("aastu.db",ios::out);
    f.close();
    f.open("aastu_code.db",ios::out);
    f.close();
    system("cls");
    cout<<"\nDatabase cleared.\n";
    getch();
}
void search(){
    system("cls");
    string kwrd,line;
    bool one=false;
    bool exist = false;
    fstream f;
    f.open("aastu.db",ios::in);
    cout<<"Enter KeyWord from the product: ";
    cin>>kwrd; capitalize(kwrd);
    cout<<"\n\n\n";
    while(getline(f,line)){
        if(line.find(kwrd)!= line.npos){
            if(!one) cout<<"NAME   CODE   CATEGORY   UCOST   QUANTITY   REVENUE   TAX   PROFIT"<<endl;
            cout<<line<<endl;
            one = true;
        }
        exist = true;
    }
    if(!one && exist) cout<<"Product Not Found.\n";
    if(!exist) { system("cls");
        cout<<"\n\n\nNothing Found, Please Register to SEE Something.\n";
    }

    f.close();
    getch();
}
void del(){
    system("cls"); bool exist = true;
    vector<string> vv; string line,kwrd;
    cout<<"Key Word: "; cin>>kwrd; capitalize(kwrd);
    fstream f;
    f.open("aastu.db",ios::in);
    while(getline(f,line)){
        vv.push_back(line);
    }
    f.close();
    for(int i=0;i<(int)vv.size();i++){
        if(vv[i].find(kwrd)!=vv[i].npos){
            cout<<"\nNAME   CODE   CATEGORY   UCOST   QUANTITY   COST   TAX   PROFIT"<<endl;
            cout<<endl<<vv[i]<<endl;
            cout<<"Press any key to approve.\n";
            getch();
            vv[i] = vv[(int)vv.size()-1];
            vv.pop_back();
            exist = false;
            cout<<"\nDelete Successful.\n";
            break;
        }
    }
    if(exist){system("cls");
        cout<<"Keyword not found.\n";
    }
    f.open("aastu.db",ios::out);
    for(int i=0;i<(int)vv.size();i++){
        f<<vv[i]<<endl;
    }
    f.close();
    getch();
}
void srt(vector<string>& pro){
    bool exit = true;
    while(true){
        exit = true;
        for(int i=1;i<(int)pro.size();i++){
            if(pro[i-1]>pro[i]){
                string temp = pro[i-1];
                pro[i-1] = pro[i];
                pro[i] = temp;
                exit = false;
            }
        }
        if(exit) break;
    }
}
void sorted(){
    fstream f1;string line;
    vector<string> str_name;
    vector<string> str_code;
    f1.open("aastu.db",ios::in);
    while(getline(f1,line)){
        str_name.push_back(line);
    }
    f1.close();
    if(str_name.size()!=0){
        bool pass = true;
        f1.open("aastu_code.db",ios::in);
        while(getline(f1,line)){
            str_code.push_back(line);
        }
        f1.close();
        srt(str_name);
        srt(str_code);
        system("cls");
        cout<<"Name:-\n  1.Asc\n  2.Dsc\n";
        cout<<"Code:-\n  3.Asc\n  4.Dsc\n";
        cout<<"Any Key To Main Menu.\n";
        switch(getch()){
        case '1':
            system("cls");
            cout<<"ASCENDING BY NAME...\n";
            for(int i=0;i<(int)str_name.size();i++){
                if(pass) cout<<"\nNAME   CODE   CATEGORY   UCOST   QUANTITY   COST   TAX   PROFIT"<<endl;
                cout<<str_name[i]<<endl;
                pass = false;
            }
            pass = true;
            break;
        case '2':
            system("cls");
            cout<<"DESCENDING BY NAME...\n";
            for(int i=(int)str_name.size()-1;i>=0;i--){
                if(pass) cout<<"\nNAME   CODE   CATEGORY   UCOST   QUANTITY   COST   TAX   PROFIT"<<endl;
                cout<<str_name[i]<<endl;
                pass = false;
            }
            pass = true;
            break;
        case '3':
            system("cls");
            cout<<"ASCENDING BY PRODUCT CODE...\n";
            for(int i=0;i<(int)str_code.size();i++){
                if(pass) cout<<"\nCODE   NAME   CATEGORY   UCOST   QUANTITY   COST   TAX   PROFIT"<<endl;
                cout<<str_code[i]<<endl;
                pass = false;
            }
            pass = true;
            break;
        case '4':
            system("cls");
            cout<<"DESCENDING BY PRODUCT CODE...\n";
            for(int i=(int)str_code.size()-1;i>=0;i--){
                if(pass) cout<<"\nCODE   NAME   CATEGORY   UCOST   QUANTITY   COST   TAX   PROFIT"<<endl;
                cout<<str_code[i]<<endl;
                pass = false;
            }
            pass = true;
            break;
        default: break;

        }
    }
    else {system("cls");
        cout<<"\n\n\nNo product Found. Please Register to see something.\n";
    }
    getch();
}


