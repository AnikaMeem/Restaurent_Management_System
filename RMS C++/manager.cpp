#include <iostream>
#include <cstring>
#include "manager.h"
using namespace std;



Manager::Manager(string mname="Hassan",int mid=0):Employee(mname,mid)
{

	ifstream in("Manager.txt");
	in>>ecount;
}
