////////////////////////////////////////////////////////////////////////////////
// Name: Dylan Brooks
// Date: September 19, 2023
// Assignment: Josephus
// Description: A program to store a user specefied number of people (N) in a 
// circle and kill every k'th person (k is user input) until one remains and 
// then list the order of execution and the survivor.
//
////////////////////////////////////////////////////////////////////////////////

#include <list>
// TODO: add additional includes

#include "Josephus.h"
#include <iterator>
#include <iostream>

/* Time Complexity, Derivation, and Explanation
 *
 * Derivation:
 * T(N, k)                              N * k
 * T(5, 2) = 4 traversals             5 * 2 = 10
 * T(5, 3) = 8 traversals             5 * 3 = 15
 * T(5, 4) = 12 traversals            5 * 4 = 20
 * When N remains constant at 5 and k changes by 1 the number of traversals changes by 4 (N - 1).
 * T(6, 2) = 5 traversals             6 * 2 = 12
 * T(6, 3) = 10 traversals            6 * 3 = 18
 * T(6, 4) = 15 traversals            6 * 4 = 24
 * When N remains constant at 6 and k changes by 1 the number of traversals changes by 5 (N - 1).
 * T(4, 2) = 3 traversals             4 * 2 = 8
 * T(4, 3) = 6 traversals             4 * 3 = 12
 * T(4, 4) = 9 traversals             4 * 4 = 16
 * When N remains constant at 4 and k changes by 1 the number of traversals changes by 3 (N - 1).
 *
 * When k remains constant at 2 and N changes by 1, the number of traversals changes by 1 (k - 1).
 * When k remains constant at 3 and N changes by 1, the number of traversals changes by 2 (k - 1).
 * When k remains constant at 4 and N changes by 1, the number of traversals changes by 3 (k - 1).
 *
 * All traversal totals are under N * k so use that as a starting point for your equation. Subtract number of
 * traversals from N * k to find the difference and look for a pattern to finish the equation.
 * T(N, k) -> Nk - traversals
 * T(5, 2) -> 10 - 4 = 6
 * Difference between 10 and 6 is equal to (5 + 2 - 1) = 7 - 1 = 6.
 * T(5, 3) -> 15 - 8 = 7
 * Difference between 15 and 8 is equal to (5 + 3 - 1) = 8 - 1 = 7.
 * T(5, 4) -> 20 - 12 = 8
 * Difference between 20 and 12 is equal to (5 + 4 - 1) = 9 - 1 = 8.
 * T(6, 2) -> 12 - 5 = 7
 * Difference between 12 and 5 is equal to (6 + 2 - 1) = 8 - 1 = 7.
 * T(6, 3) -> 18 - 10 = 8
 * Difference between 18 and 10 is equal to (6 + 3 - 1) = 9 - 1 = 8.
 * T(6, 4) -> 24 - 15 = 9
 * Difference between 24 and 15 is equal to (6 + 4 - 1) = 10 - 1 = 9.
 * T(4, 2) -> 8 - 3 = 5
 * Difference between 8 and 3 is equal to (4 + 2 - 1) = 6 - 1 = 5.
 * T(4, 3) -> 12 - 6 = 6
 * Difference between 12 and 6 is equal to (4 + 3 - 1) = 7 - 1 = 6.
 * T(4, 4) -> 16 - 9 = 7
 * Difference between 16 and 9 is equal to (4 + 4 - 1) = 8 - 1 = 7.
 *
 * The difference between N * k and the actual number of traversals in consistently N + k - 1. So,
 * you can write (N * k) - traversals = (N + k - 1) which can be rewritten as
 * traversals = (N * k) - (N + k - 1)
 *
 * Equation to get the number of traversals:
 * T(N, k) = (N x k) - (N + k - 1)
 *
 * Complexity - The Big-O notation of this program is O(Nk)
 * because Nk is the highest ordered term in the equation
 * so the actual speed of the equation from the function
 * T(N, k) will never be larger than the output of N * k.
 * 
 *
*/

/* Simulate the Josephus problem modeled as a std::list.
 * This function will modify the passed list with only a
 * single survivor remaining.
 *
 * @param circle -- the linked list of people
 * @param k -- skip amount. NOTE: k > 0
 *
 * @return a list of those who are executed in chronological order
 */
template <typename T>
std::list<T>
execute (std::list<T>& circle, int k)
{
  std::list<int> killed;
  std::list<int>::iterator it = circle.begin();
  int count = 0;
  // TODO :)
  if (circle.size() == 1) {
    return killed;
  } else {
    while (circle.size() > 1) {
      for (int i = 1; i < k; ++i) {
        if (it == circle.end()) it = circle.begin();
        ++it;
        if (it == circle.end()) it = circle.begin();
        ++count;
      }
      killed.push_back(*it);
      it = circle.erase(it);
    }
    //std::cout<<"Traversal count: "<< count << " ";
    return killed;
  }
}

/* entry point to the Josephus problem from the autograder / main
 *
 * @param n -- number of people in the circle
 * @param k -- skip amount. NOTE: k > 0
 */
int
josephus (int n, int k)
{
  // 1. make a list
  std::list<int> circle;
  //std::list<int> killed;
  //std::list<int>::iterator iter;
  // 2. populate it with values [1, 2, 3, ... , N]
  for (int i = 1; i <= n; ++i) {
    circle.push_back(i);
  }
  // 3. call execute
  //killed = execute(circle, k);
  //iter = killed.begin();
  // 4. return the lone survivor
  //std::cout<<"Kill Order: ";
  //while (iter != killed.end()) {
   // std::cout<<*iter<<" ";
   // ++iter;
 // }
 execute(circle, k);
 return *circle.begin();
  // HINT: While working on this lab, you may also find
  //       it useful to print out the "kill" order.
}
