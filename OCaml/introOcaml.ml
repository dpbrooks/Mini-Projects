(* Author:      Dylan Brooks
 * Instructor:  Dr. Zoppetti
 * Date:        LAST MODIFICATION DATE
 * Assignment:  Lab 1 - Introduction to OCaml
 * Description: Implementing basic functions using OCaml.
 *)

(* BEGIN PROVIDED FUNCTIONS *)

(* explode : string -> char list
 * (explode s) is the list of characters in the string s in the order in
 *   which they appear
 * e.g.  (explode "Hello") is ['H';'e';'l';'l';'o']
 *)
let explode s =
        let rec _exp i =
                if i >= String.length s then [] else (s.[i])::(_exp (i+1)) in _exp 0;;

(* END PROVIDED FUNCTIONS *)


(* For ALL of the following method stubs (those with failwith "to be written"),
   add documentation comments including expected behavior *)

(* sumList int list -> int
 * (sumList l) is the sum of all elements of an int list
 *
 * If the param list is empty return zero otherwise add the head to a
 * recursive call of sumList with the tail of the list.
 *)
let rec sumList l =
        match l with
        | [] -> 0
        | hd::tl -> hd + sumList tl;;

(* digitsOfInt: int -> int list
 * (digitsOfInt) is the list of the digits that make up an int in the order
 *    that they appear
 *
 * Check if n is greater than 0, and if it is append the result of
 * digitsOfInt(n / 10) and [n mod 10]. 
 *)
let rec digitsOfInt n =
        if n > 0 then digitsOfInt(n / 10) @ [n mod 10]
        else if n < 0 then digitsOfInt(n / 10) @ [(n * -1) mod 10]
        else [];;

(* additivePersistence: int -> int
 * (additivePersistence) is the number of times the digits of an int need to
 * be added together until a single digit number is left
 * 
 * While n has at least 2 digits add one to the output and then add the output
 * of additivePersistence with the digital root of n as a parameter. When n
 * has one digit return 0.
 * ex. Number passed in = 9876
 * First Loop: 9 + 8 + 7 + 6 = 30, has 2 digits, add one to output and repeat with 30
 * Second Loop: 3 + 0 = 3, has 1 digit, add one to output and return
 *)
let rec additivePersistence n =
        if n > 9 then 1 + additivePersistence (sumList (digitsOfInt n))
        else 0;;

(* digitalRoot: int -> int
 * (digitalRoot) is the single digit number yielded when repeatedly
 * adding the digits of a number
 *
 * While n has more than 1 digit recursively call digiatlRoot with the
 * digital root of n as the parameter. If n only has one digit, return n.
 *)
let rec digitalRoot n =
        if n > 9 then digitalRoot(sumList(digitsOfInt n))
        else n;;

(* listReverse: 'a list -> 'a list
 * (lsitReverse) returns the given list in reverse order
 *
 * If the list is empty then return the list, otherwise append the head
 * of the list to the result of listReverse on the tail of the list.
 *)
let rec listReverse l =
        match l with
        | [] -> l
        | hd::tl -> listReverse tl @ [hd];;
        
(* palindrome: string -> boolean
 * (palindrome) returns true if the given word is a palindrome, false if not.
 *
 * Change the word into a list of its characters by calling explode and save
 * as word. Call listReverse on word to get the word backwards, then return
 * true if the two lists are equivalent, false otherwise.
 *)
let palindrome w =
        let word = explode w in
                let rev = listReverse word in
                        if word = rev then true
                        else false;;



(* BEGIN PROVIDED FUNCTIONS *)

(* digits : int -> int list
 * (digits n) is the list of digits of n in the order in which they appear
 * in n
 * e.g. (digits 31243) is [3,1,2,4,3]
 *      (digits (-23422) is [2,3,4,2,2]
 *)
let digits n = digitsOfInt (abs n);;

(* END PROVIDED FUNCTIONS *)

(************** Add Testing Code Here ***************)

let lst1 = [1;2;3;4];;

let lst2 = [9;8;7;6];;

let lst3 = [1;-2;3;5];;

sumList lst1;;
sumList lst2;;
sumList lst3;;

digitsOfInt 1234;;
digitsOfInt 5739185;;
digitsOfInt (-15);;

additivePersistence 9876;;
additivePersistence 1234;;
additivePersistence 3865;;

digitalRoot 9876;;
digitalRoot 1234;;
digitalRoot 3865;;

listReverse lst1;;
listReverse lst2;;
listReverse lst3;;

palindrome "racecar";;
palindrome "apple";;
palindrome "malayalam";;