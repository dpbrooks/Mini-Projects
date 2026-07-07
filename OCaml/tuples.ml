 (* Author: Dylan Brooks
 * Instructor: Dr. Zoppetti
 * Date: March 20, 2024
 * Assignment: Lab 3 - Tuples and HOF
 * Description: Implementing functions using tuples
 * CSCI 330: OcamlLab 3
 * misc3.ml
 *)

(* ***** DOCUMENT ALL FUNCTIONS YOU WRITE OR COMPLETE ***** *)

(* assoc: int * string * (string * int) list -> int
 * (assoc (d,k,l)) returns a value associated with the key in the list or
 * a default value if the key value does not exist
 *)
let rec assoc (d,k,l) =
  match l with
    | [] -> d
    | hd::tl -> 
      let (x, y) = hd in
      if x = k then y else assoc (d, k, tl)
;;

(* fill in the code wherever it says : failwith "to be written" *)

(* removeDuplicates: int list -> int list
 * (removeDuplicates l) returns a list in the same order as the original
 * with all duplicates removed
 *)
let removeDuplicates l = 
  let rec helper (seen,rest) = 
      match rest with 
        [] -> seen
      | h::t -> 
        let seen' = if List.mem h seen then seen else h::seen in
        let rest' = t in 
	  helper (seen',rest') 
  in
      List.rev (helper ([],l))


(* Small hint: see how ffor is implemented below *)

(* wwhile: (int -> int * bool) * int -> int
 * (wwhile (f,b)) applies the function f to b while the bool returned by
 * the function is true
 *)
let rec wwhile (f,b) =
  let (x, y) = f b in
  if y then wwhile (f,x) else x
;;

(* fill in the code wherever it says : failwith "to be written" *)

(* fixpoint: (int -> int) * int -> int
 * (fixpoint (f,b)) applies the function f to b until f b = b 
 *
 *)
let fixpoint (f,b) = wwhile ((fun x -> if f x = x then (x, false) else (f x, true)),b)


(* ffor: int * int * (int -> unit) -> unit
   Applies the function f to all the integers between low and high
   inclusive; the results get thrown away.
 *)

let rec ffor (low,high,f) = 
  if low>high 
  then () 
  else let _ = f low in ffor (low+1,high,f)


  (**Testing Code**)

  let f x =
    let xx = x * x * x
    in (xx, xx < 100);;

  let g x = truncate (1e6 *. cos (1e-6 *. float x));;

  let h x = x * x;;

  assoc (-1,"jeff", [("sorin",85);("jeff",23);("moose",44)]);;

  assoc (-1,"bob",[("mary",77);("bob",33);("bob",44)]);;
  
  assoc (1337,"alice",[("bob",1);("charlie",2)]);;

  removeDuplicates [1;6;2;4;12;2;13;6;9];;

  wwhile (f, 2);;

  fixpoint (g,0);;

  fixpoint (h,0);;

  fixpoint (h,1);;
