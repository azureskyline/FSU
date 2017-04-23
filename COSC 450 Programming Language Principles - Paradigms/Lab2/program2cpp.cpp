// This shows the value of a and b variables in their prospective for loops
// a is not visible outside of its for
// b is because it was first declared outside of its for
#include <iostream>
using namespace std;
        int main (){
        for( int a = 10; a < 20; a = a + 1 ){
                cout << "value of a: " << a << endl;
        }
        int b;
        for (b = 10; b < 20; b = b + 1 ){
                cout << "value of b: " << b << endl;
        }
        cout << "End-of-forloop value of b: " << b << endl;

        return 0;
}