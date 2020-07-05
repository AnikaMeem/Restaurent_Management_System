#include<iostream>
#include<cstring>
#ifndef EMPLOYEE_H
#define EMPLOYEE_H

using namespace std;

class Employee
{
	protected:
		
		string name;
		int id;
		double pay;
		int attendance;
		
	public:
		Employee(string,int,double,int);
		void getValues();
		void Display();
		void setName(string);
		string getName();
		void setId(int);
		int getId();
		void setPay(double);
		double getPay();
		void setAttendance(int);
		int getAttendance();
};
#endif
