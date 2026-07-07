/**
 * Print all possible anagrams of the entered string
 * capital letters and special characters must remain in
 * place
 */
#include <iostream>
#include <cctype>
#include <string>
#include <vector>
#include <algorithm>

/**
 * Generates and prints unique anagrams of the string
 */
void generateAnagrams(std::string str, std::vector<int> changeableIndecies, std::vector<char> changeableChars) {
    // Use next_permutation to track anagrams that were already printed to avoid duplicates
    do {
        std::string temp = str;
        for (int i = 0; i < changeableIndecies.size(); ++i) {
            int index = changeableIndecies[i];
            char ch = changeableChars[i];
            temp[index] = ch;
        }
        std::cout << temp << "\n";
    } while (next_permutation(changeableChars.begin(), changeableChars.end()));
}

int main() {
    std::string str;
    std::cout << "Enter Word: ";
    std::cin >> str;
    // Store indecies and chars that can be changes (i.e. all lowercase chars)
    std::vector<int> changeableIndecies;
    std::vector<char> changeableChars;
    int index = 0;
    for (char c : str) {
        if (!std::isupper(c) && std::isalpha(c)) {
            // c is lowercase
            changeableIndecies.push_back(index);
            changeableChars.push_back(c); 
        }
        index++;
    }
    generateAnagrams(str, changeableIndecies, changeableChars);
}