#include <iostream>
#include <cstring>
#include"employee.h"
#ifndef MANAGER_H
#define MANAGER_H
#include<vector>

using namespace std;

class Manager:public Employee
{
	protected:
		string type;


	public:
		int ecount;
		Employee emp[10];
		Manager(string,int);

};
#endif
