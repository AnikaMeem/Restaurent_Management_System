#include<iostream>
#include<cstring>
#include"employee.h"
#include <fstream>

using namespace std;





Employee::Employee(string ename="Employee",int eid=0,double epay=0.00,int eattendance=0)
{
	
	ifstream in("Employee.txt");
	
	name=ename;
	id=eid;
	pay=epay;
	attendance=eattendance;
	in>>name;
	in>>id;
	in>>pay;
}

void Employee::Display()
{
    cout<<"Name: "<<name<<endl;
    cout<<"ID: "<<id<<endl<<"Pay: "<<pay<<endl;
}
void Employee::setName(string a)
{
	name=a;
}
string Employee::getName()
{
	return name;
}
void Employee::setId(int id)
{
	this->id=id;
}
int Employee::getId()
{
	return id;
}
void Employee::setPay(double pay)
{
	this->pay=pay;
}
double Employee::getPay()
{
	return pay;
}
void Employee::setAttendance(int attendance)
{
	this->attendance=attendance;
}
int Employee::getAttendance()
{
	return attendance;
}
void Employee::getValues()
{
	ofstream out("Employee.txt",ios::out);
	
	cout<<"Enter employee's name: "<<endl;
	cin>>name;
	cout<<"Enter employee's pay: "<<endl;
	cin>>pay;
	cout<<"Enter employee's ID: "<<endl;
	cin>>id;
	out<<name<<" "<<pay<<" "<<id<<" "<<endl;
}
