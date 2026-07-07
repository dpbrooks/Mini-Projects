(* CSCI 330: Programming Assignment 5
 * misc5.ml
 *)

(* For this assignment, you may use the following library functions:

   List.map
   List.fold_left
   List.fold_right
   List.split
   List.combine
   List.length
   List.append
   List.rev

   See http://caml.inria.fr/pub/docs/manual-ocaml/libref/List.html for
   documentation.
*)



(* Do not change the skeleton code! The point of this assignment is to figure
 * out how the functions can be written this way (using fold). You may only
 * replace the   failwith "to be implemented"   part. *)



(*****************************************************************)
(******************* 1. Warm Up   ********************************)
(*****************************************************************)

(* sqsum: int list -> int
 * (sqsum xs) returns the sum of all elements of the list
 * after squaring them
 *)
let sqsum xs = 
  let f a x = a + x * x in
  let base = 0 in
    List.fold_left f base xs

(* pipe: *'a -> 'a) list -> 'a -> 'a
 * (pipe fs s) Applies every function in list fs on s
 *
 *)
let pipe fs s = 
  let f a x = x a in
  let base = s in
    List.fold_left f base fs

(*pipec: ('a -> 'a) list -> ('a -> 'a)
 * (pipec fs) Creates a function that does every function of the
 * operations in list fs and expects input for this new function
 *)
let pipec fs = 
  let f a x = fun y -> x(a y) in
  let base = fun x -> x in
    List.fold_left f base fs

(* sepConcat: string -> string list -> string
 * (sepConcat sep s1) Returns the strings of a list seperated
 * by the sep
 *)
let rec sepConcat sep sl = match sl with 
  | [] -> ""
  | h :: t -> 
      let f a x = a ^ sep ^ x in
      let base = h in
      let l = t in
        List.fold_left f base l

(* stringOfList: ('a -> string) -> 'a list -> string
 * (stringOfList f l) creates a string representation of a list
 *
 *)
let stringOfList f l = "[" ^ sepConcat "; " (List.map f l) ^ "]"

(* prodLists: int list -> int list -> int list
 * (prodLists l1 l2) Creates a list of the products of the elements
 * of the two lists passed to the function
 *)
let prodLists l1 l2 =
  let f a x =
    let (x1, x2) = x in
      List.append a [(x1 * x2)] in
  let base = [] in
  let args = List.combine l1 l2 in
    List.fold_left f base args

(*****************************************************************)
(******************* 2. Big Numbers ******************************)
(*****************************************************************)

(* clone : 'a -> int -> 'a list 

clone takes as input x and an integer n. The result is a list of length n, where each element is x. 
If n is 0 or negative, clone will return the empty list. 

# clone 3 5;;
- : int list = [3; 3; 3; 3; 3] 
# clone "foo" 2;;
- : string list = ["foo"; "foo"]
# clone clone (-3);;
- : ('_a -> int -> '_a list) list = [])
*)
let rec clone x n = if (n <= 0) then [] else x::(clone x (n - 1))

(*
padZero : int list -> int list -> int list * int list 

padZero takes two lists: [x1,...,xn] [y1,...,ym] and adds zeros in front to make the lists equal in length. 

# padZero [9;9] [1;0;0;2];;
- : int list * int list = ([0;0;9;9],[1;0;0;2]) 
# padZero [1;0;0;2] [9;9];;
- : int list * int list = ([1;0;0;2],[0;0;9;9]) 
*)
let rec padZero l1 l2 = 
   let l1len = List.length l1 in
   let l2len = List.length l2 in
    ((clone 0 (l2len-l1len)@l1), (clone 0 (l1len-l2len)@l2))

(*
removeZero : int list -> int list 

removeZero takes a list and removes a prefix of trailing zeros. 

# removeZero [0;0;0;1;0;0;2];;
- : int list = [1;0;0;2] 
# removeZero [9;9];;
- : int list = [9;9] 
# removeZero [0;0;0;0];;
- : int list = [] 
*)

let rec removeZero l = 
  match l with
  | 0::t -> removeZero t
  | _ -> l

(* bigAdd: int list -> int list -> int list
 * (bigAdd l1 l2) Adds large numbers represented by lists together
 * and returns a list representing the sum
 *)
let bigAdd l1 l2 = 
  let add (l1, l2) = 
    let f a x = 
      let (cur, rest) = a in
        let (val1, val2) = x in
          let s = val1 + val2 + cur in
          (s / 10, [s mod 10]@rest) in
    let base = (0, []) in
    let args = List.rev (List.combine l1 l2) in
    let (carry, res) = List.fold_left f base args in
      carry::res
  in 
    removeZero (add (padZero l1 l2))

(* EXTRA CREDIT BELOW *)

let rec mulByDigit i l =
  let mult (l1, l2) = 
    let f a x = failwith "to be implemented" in
    let base = failwith "to be implemented" in
    let args = failwith "to be implemented" in
    let (carry, res) = List.fold_left f base args in
      carry::res
  in 
    failwith "to be implemented"

let bigMul l1 l2 = 
  let f a x = failwith "to be implemented" in
  let base = failwith "to be implemented" in
  let args = failwith "to be implemented" in
  let (_, res) = List.fold_left f base args in
    res
