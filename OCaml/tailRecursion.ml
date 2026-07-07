(* Author: Dylan Brooks
 * Instructor: Dr. Zoppetti
 * Date: February 16, 2024
 * Assignment: Lab 2 - Tail Recursion
 * Description: Implementing functions with tail recursion
 * CSCI 330: Lab 2
 * misc.ml
 *)

(* 
	***** PROVIDE COMMENT BLOCKS AND IMPLEMENTATIONS FOR THE FOLLOWING FUNCTIONS ***** 
	***** INCLUDE TYPE SIGNATURES ***** 
*)

(* sqrt: float -> float -> float
 * (sqrt tol x) calculates the square root of x within the given tol
 *
 * When y squared is within the given tolerance return y, otherwise call the
 * helper and decrease y.
 *)
let rec sqrt tol x =
	let rec sqrthelper tol x y =
		match y with
			| y when abs_float(((y *. y) -. x)) < tol -> y
			| _ -> sqrthelper tol x ((y +. (x /. y)) /. 2.0)
	in
	sqrthelper tol x x;;

(* Your solution for sqrt2 should not need a lambda. Replace
   everything to the right of the =. *)
let rec sqrt2 = fun x ->
	sqrt 0.00001 x;;

(* factorial1: int -> int
 * (factorial1 x) the factorial of the given number
 *
 * When x is 0 return 1 otherwise multiply x by the recursive call of 
 * factorial1 with x decremented.
 *)
let rec factorial1 x = 
	if x = 0 then 1
	else x * factorial1 (x - 1);;

(* factorial2: int -> int
 * (factorial2 x) the factorial of the given number
 *
 * When x is 0 return 1 otherwise multiply x by the recursive call of 
 * factorial1 with x decremented.
 *)
let rec factorial2 x = 
	match x with
		| 0 -> 1
		| x -> x * factorial2 (x - 1);;

(* factorial3: int -> int
 * (factorial3 x) the factorial of the given number
 *
 * Use helper function to create an accumulator called product. While x is
 * greater than 0 recursively call helper with decremented x and multiply
 * product by x. When x = 0 return the product.
 *)
let rec factorial3 x = 
	let rec facthelper x product =
		if x > 0 then facthelper (x - 1) (x * product)
		else product
	in
	facthelper x 1;;

(* fibonacci: int -> int
 * (fibonacci x) the number at position x in the fibonacci sequence
 *
 * Create a helper to keep track of the current num and previous num. When x
 * is 0 return num1 otherwise, call fibhelper with (x - 1) change num1 to num2
 * and set num2 equal to num1 + num2.
 * Index starts counting at F1 = 1
 *)
let rec fibonacci x =
	let rec fibhelper x num1 num2 =
		match x with
			| 0 -> num1
			| x -> fibhelper (x - 1) num2 (num1 + num2)
	in
	fibhelper x 0 1;;

(* rev: 'a list -> 'a list
 * (rev l) returns a reversed list
 *
 * If the list is empty then return the list, otherwise append the head
 * of the list to the result of listReverse on the tail of the list.
 *)
let rec rev l = 
	let rec revhelper acc l =
		match l with
			| [] -> []
			| hd::tl -> revhelper (hd::acc) tl
	in
	revhelper [] l;;

(* map: fun -> 'a list -> 'a list
 * (map f l) applies a function to every element of a list
 *
 * If l is empty return an empty list, otherwise apply the given function to
 * the head of the list and cons it to a recursive call of map with the
 * function and the tail of the list.
 *)
let rec map f l = 
	match l with
		| [] -> []
		| h::t -> f h::map f t;;


(* map: fun -> 'a list -> 'a list
 * (map f l) -> applies a function to every element of a list
 *
 * If l is empty return an empty list, otherwise apply the given function to
 * the head of the list and cons it to a recursive call of map with the
 * function and the tail of the list.
 *)
let rec map2 f l =
	let rec helper f l =
		match l with
			| [] -> []
			| h::t -> f h::map f t
	in
	helper f l;;


(* range: int -> int -> int list
 * (range a b) returns a list of numbers within the range a to b
 *
 * Create a helper function to make an accumulator list. While a <= b cons a to
 * the list created by recursively calling helper with incremented a, b, and
 * acc. When a > b return the accumulated list.
 *)
let rec range a b =
	let rec helper a b acc =
		if a > b then acc
		else a::helper (a + 1) b acc
	in
	helper a b [];;


let roots : float list = map sqrt2 (map float_of_int (range 1 20));;

