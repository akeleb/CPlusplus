#include<iostream>
#include<string.h>
#include<fstream>
#include<cstdlib>
#include<conio.h>
using namespace std;
int n,h;
class personnel{
	private:
	
	string name,sex;
int n_days;
double income,tax,net_salary,g_sala;		
		public:
		void search();
		void input();
		void disp();
		};personnel s[100];
int main()
{
personnel p;
	p.input();
	cout<<"press 1 to display and 2 to search";
	switch(getch()){
		case'1':
			 p.disp();
			 break;
			 case '2':
			  p.search();
			  break;	
	}
 while(true){
 {
 cout<<"if you wnat run the program again press 1 \n"<<"and any key to end the program\n";
 cin>>h;
 system("cls");
 if(h==1){
p.input();
cout<<"press a to display and b to search";
switch(getch()){

case'a':
 p.disp();
 break;
 case 'b':
 	p.search();
 	break;}
 }
 else{
 cout<<"program ended";
 exit(1);}
 }}}
void personnel::input(){
		fstream f;
f.open("akele.txt",ios::out|ios::app|ios::in);
f<<"name"<<'\t'<<"sex"<<'\t'<<"grossalary"<<'\t'<<"netincome"<<"\n\n";
		int c=0;
		cout<<"enter number of peaple"<<endl;
		cin>>n;
				cout<<"enter name and sex"<<endl;
				for(int i=0;i<n;i++)
				{
				c++;
			cout<<"name of person"<<" "<<c<<":-";cin>>s[i].name;cout<<"sex:";cin>>s[i].sex;
			
				cout<<"enter salary per day and number of days"<<endl;
			cout<<"income:";cin>>s[i].income;
			cout<<"working days:";cin>>s[i].n_days;
		system("cls");
			}
			for(int i=0;i<n;i++)
			{
			
					s[i].g_sala=s[i].income*s[i].n_days;}
					for(int i=0;i<n;i++){
					
				if(s[i].g_sala<=200)
				{
				
				s[i].tax=s[i].income*0;
					s[i].net_salary=s[i].g_sala-s[i].tax;
					}else
					
				if(s[i].g_sala>200 && s[i].g_sala<=600)
				{
						s[i].tax=200*0+(s[i].g_sala-200)*0.1;
						s[i].net_salary=s[i].g_sala-s[i].tax;
				}
					else
					if(s[i].g_sala>600&&s[i].g_sala<=1200)
					{
					
					s[i].tax=200*0+400*0.1+(s[i].g_sala-600)*0.15;
					s[i].net_salary=s[i].g_sala-s[i].tax;}
					else 
					if(s[i].g_sala>1200&&s[i].g_sala<=2000){
					
					s[i].tax=200*0+400*0.1+600*0.15+(s[i].g_sala-1200)*0.2;
					s[i].net_salary=s[i].g_sala-s[i].tax;}
					else
					if(s[i].g_sala>2000&&s[i].g_sala<=3500){
					s[i].tax=200*0+400*0.1+600*0.15+800*0.2+(s[i].g_sala-2000)*0.25;
					s[i].net_salary=s[i].g_sala-s[i].tax;}
					else									
					s[i].tax=200*0+400*0.1+600*0.15+800*0.2+1500*0.25+(s[i].g_sala-3500)*0.3;
					s[i].net_salary=s[i].g_sala-s[i].tax;
					{
							
	f<<s[i].name<<'\t'<<s[i].sex<<'\t'<<s[i].g_sala<<"/"<<s[i].n_days<<" "<<"Days";
	f<<"        "<<s[i].net_salary<<"/"<<s[i].n_days<<" "<<"Days\n";
	}} {
 system("cls");}
				}
				void personnel::disp(){
					cout<<"salary detail of entered persons is :-\n";
					cout<<"name"<<'\t'<<"sex"<<'\t'<<"grossalary"<<'\t'<<"netincome"<<"\n\n";
				
					for(int i=0;i<n;i++){
						cout<<s[i].name<<'\t'<<s[i].sex<<'\t'<<s[i].g_sala<<"/"<<s[i].n_days;
						cout<<" "<<"Days"<<"        "<<s[i].net_salary<<"/"<<s[i].n_days<<" "<<"Days\n";
					}
				}
				void personnel::search(){
					string a;
					cin>>a;
					for(int i=0;i<n;i++){
						if(a==s[i].name){
							cout<<"found";
						}
					}
				}
				
				
					
		
			
				
			
