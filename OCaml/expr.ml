(* 
 * Name: Dylan Brooks
 * Date: March 27, 2024
 * Course: CSCI 330 - Programming Languages
 * Assignment: Variant Types
 *
 * Assignment Attribution:
 *   This lab is based on code by Chris Stone (lab from CSE 130 by Sorin Lerner at UCSD)
 *
 * Description: Use variant types to generate images.
 *)

let pi = 4.0 *. (atan 1.0)

type expr = 
    VarX
  | VarY
  | Sine     of expr
  | Cosine   of expr
  | Average  of expr * expr
  | Times    of expr * expr
  | Thresh   of expr * expr * expr * expr	
  | Half     of expr
  | Tripavg  of expr * expr * expr
(* TODO: add two new "types" of expressions *)

type rng = int * int -> int
type builder_fun = rng * int -> expr

(* exprToString: expr -> string
 * (exprToString e) converts the variant expr into a string representation
 *
 *)
let rec exprToString e =
  match e with
    | VarX -> "x"
    | VarY -> "y"
    | Sine s -> "sin(pi*" ^ (exprToString s) ^ ")"
    | Cosine c -> "cos(pi*" ^ (exprToString c) ^ ")"
    | Average (a1, a2) -> "((" ^ (exprToString a1) ^ "+"
                          ^ (exprToString a2) ^ ")/2)"
    | Times (t1, t2) -> (exprToString t1) ^ "*" ^ (exprToString t2)
    | Thresh (e1, e2, e3, e4) -> "(" ^ (exprToString e1) ^ "<" ^ (exprToString e2)
                                 ^ "?" ^ (exprToString e3) ^ ":"
                                 ^ (exprToString e4) ^ ")"
    | Half h -> "(" ^ (exprToString h) ^ " / 2)"
    | Tripavg(e1, e2, e3) -> "((" ^ (exprToString e1) ^ " + " ^
                              (exprToString e1) ^ " + " ^ (exprToString e3) ^ ") / 3)"

(* build functions:
     Use these helper functions to generate elements of the expr
     datatype rather than using the constructors directly.  This
     provides a little more modularity in the design of your program *)

let buildX()                       = VarX
let buildY()                       = VarY
let buildSine(e)                   = Sine(e)
let buildCosine(e)                 = Cosine(e)
let buildAverage(e1,e2)            = Average(e1,e2)
let buildTimes(e1,e2)              = Times(e1,e2)
let buildThresh(a,b,a_less,b_less) = Thresh(a,b,a_less,b_less)
let buildHalf(e)                   = Half(e)
let buildTripavg(e1, e2, e3)       = Tripavg(e1,e2,e3)

(* TODO: add two new buildXXXXXXX functions *)

(* eval: expr * float * float -> float
 * (eval(e, x, y)) evaluates a given expression e by translating the parts
 * of the variant into the correct operations and plugging in x for Varx
 * and y for VarY
 *)
let rec eval (e, x, y) =
  match e with
    | VarX -> x
    | VarY -> y
    | Sine s -> sin (pi *. eval (s, x, y))
    | Cosine c -> cos (pi *. eval (c, x, y))
    | Average (a1, a2) -> (eval (a1, x, y) +. eval (a2, x, y)) /. 2.0
    | Times (t1, t2) -> eval (t1, x, y) *. eval (t2, x, y)
    | Half h -> (eval (h, x, y) /. 2.0)
    | Tripavg (e1, e2, e3) -> ((eval (e1, x, y) +. eval (e2, x, y) +.
                              eval (e3, x, y)) /. 3.0)
    | Thresh (e1, e2, e3, e4) -> match (eval (e1, x, y) < eval (e2, x, y)) with
                                  | true -> eval (e3, x, y)
                                  | false -> eval (e4, x, y)

(* (eval_fn e (x,y)) evaluates the expression e at the point (x,y) and then
 * verifies that the result is between -1 and 1.  If it is, the result is returned.  
 * Otherwise, an exception is raised.
 *)
let eval_fn e (x,y) = 
  let rv = eval (e,x,y) in
  assert (-1.0 <= rv && rv <= 1.0);
  rv

let sampleExpr =
      buildCosine(buildSine(buildTimes(buildCosine(buildAverage(buildCosine(
      buildX()),buildTimes(buildCosine (buildCosine (buildAverage
      (buildTimes (buildY(),buildY()),buildCosine (buildX())))),
      buildCosine (buildTimes (buildSine (buildCosine
      (buildY())),buildAverage (buildSine (buildX()), buildTimes
      (buildX(),buildX()))))))),buildY())))

let sampleExpr2 =
  buildThresh(buildX(),buildY(),buildSine(buildX()),buildCosine(buildY()))




(******************* Functions you need to write **********)

(* build: (int*int->int) * int -> Expr 
   Build an expression tree.  The second argument is the depth, 
   the first is a random function.  A call to rand(2,5) will give
   you a random number in the range [2,5)  
   (2 inclusive, and 5 exclusive).

   Your code should call buildX, buildSine, etc. to construct
   the expression.
*)

(* build: (int * int -> int) * int -> expr
 * (build(rand, depth)) Generates a random expression using the given depth
 * and seeds which can then be used to create an image
 *)
let rec build (rand,depth) = (*failwith "to be implemented"*)
  if depth = 0 then
    let r = rand (0, 2) in
    match r with
      | 0 -> buildX()
      | _ -> buildY()
    else
      let r = rand(0,5) in
      match r with
        | 0 -> buildTimes(build(rand,depth - 1), build(rand,depth - 1))
        | 1 -> buildAverage(build(rand,depth - 1), build(rand,depth - 1))
        | 2 -> buildCosine(build(rand,depth - 1))
        | 3 -> buildSine(build(rand,depth - 1))
        | _ -> buildThresh(build (rand,depth - 1), build(rand,depth - 1), 
             build(rand,depth - 1), build(rand,depth - 1))

(* build2: (int * int -> int) * int -> expr
 * (build2 (rand, depth)) Generates a random expression using the given depth
 * and seeds which can then be used to create an image (Includes new
 * expression types)
 *)
let rec build2 (rand,depth) = (*failwith "to be implemented"*)
  if depth = 0 then
      let r = rand (0, 2) in
      match r with
        | 0 -> buildX()
        | _ -> buildY()
  else
    let r = rand(0,7) in
    match r with
      | 0 -> buildTimes(build(rand,depth - 1), build(rand,depth - 1))
      | 1 -> buildAverage(build(rand,depth - 1), build(rand,depth - 1))
      | 2 -> buildCosine(build(rand,depth - 1))
      | 3 -> buildSine(build(rand,depth - 1))
      | 4 -> buildThresh(build (rand,depth - 1), build(rand,depth - 1), 
             build(rand,depth - 1), build(rand,depth - 1))
      | 5 -> buildHalf(build (rand,depth - 1))
      | _ -> buildTripavg(build (rand,depth - 1), build (rand,depth - 1),
             build (rand,depth - 1))

(* g1,c1 : unit -> ((int*int->int) * int -> Expr) * int * int * int
 * these functions should return the parameters needed to create your 
 * top color / grayscale pictures.
 * they should return (function,depth,seed1,seed2)
 * Function should be build or build2 (whichever you used to create
 * the image)
 *)

let g1 () = (build2, 12, 60, 150);;

let c1 () = (build, 8, 15, 33);;