#include "triangle.h"
#include <iostream>

int main() {
    triangle t1 (3, 4, 5);    // Create a triangle with sides 3, 4, and 5
    triangle t2;              // Create a default triangle with all side lengths equal to 1
    std::cout<<"Is t1 a right triangle? "<<t1.is_right()<<std::endl;
    std::cout<<"Is t2 a right triangle? "<<t2.is_right()<<std::endl;
    std::cout<<"The perimeter of t1 is "<<t1.perimeter()<<std::endl;
    std::cout<<"The perimeter of t2 is "<<t2.perimeter()<<std::endl;

    triangle* p1 = &t1;       // Creates a pointer for the memory address of t1
    triangle* p2 = &t2;       // Creates a pointer for the memory address of t2
    std::cout<<"Is t1 a right triangle? "<<p1->is_right()<<std::endl;
    std::cout<<"Is t2 a right triangle? "<<p2->is_right()<<std::endl;
    std::cout<<"The perimeter of t1 is "<<p1->perimeter()<<std::endl;
    std::cout<<"The perimeter of t2 is "<<p2->perimeter()<<std::endl;


}
