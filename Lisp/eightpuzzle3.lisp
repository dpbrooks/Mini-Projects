
; EightPuzzle1 Functions

(defun goal-state (lst)
    (defun helper (lst goal)                        ; Define a helper to compare elements of lst w/ goal state
    (cond
        ((null lst) T)                              ; If lst is empty every element matched return T
        ((not(equal (car lst) (car goal))) nil)     ; If first element of each list are not equal return nil
        (T (helper (cdr lst) (cdr goal)))           ; Recursive call with the rest of both lists
    )
    )
    (helper lst '(1 2 3 8 e 4 7 6 5))               ; Call helper with lst and the known goal state as parameters
)

; Access and return the first element of move
(defun get-direction (move)
    (car move)
)

; Access and return the state of the puzzle
(defun get-state (move)
    (car (cdr move))
)


(defun same-state (move1 move2)
    (defun comp-lists (lst1 lst2)                       ; Create helper to compare 2 lists
    (cond
        ((null lst1) T)                                 ; If lst1 is empty all elements are the same, return T
        ((not(equal (car lst1) (car lst2))) nil)        ; If the first element of lst1 and lst2 != return nil
        (T (comp-lists (cdr lst1) (cdr lst2)))          ; Recursive call comp-lists w/ the rest of lst1 and lst2
    )
    )
    (comp-lists (get-state move1) (get-state move2))    ; Pass the states of move1 and move2 into comp-lists
)

(defun path (lst)
    (cond
        ((equal (get-direction (car lst)) nil) '())                         ; When the start state is reached start an empty list
        (T (append (path (cdr lst)) (list (get-direction (car lst)))))      ; Combine the list created by the recursive call of path and the current move
    )
)

; Helper function for remove-redundant
(defun compare-states (ele path)    ; Returns T if ele(current state) is present in the path
(cond
    ((null path) nil)                       ; If path is null, ele is not present in it return nil
    ((equal ele (get-state(car path))) T)   ; If ele equals a state present in path return T
    (T (compare-states ele (cdr path)))     ; Recursive call compare-states w/ the rest of path
)
)

(defun remove-redundant (path1 path2)
(cond
    ((null path1) '())
    ((not(compare-states (get-state (car path1)) path2)) (cons (car path1) (remove-redundant (cdr path1) path2)))   ;If the current state is not in path2 then add to list
    (T (remove-redundant (cdr path1) path2))
)
)


; Return a list containg the legal moves and associated states given a current state
(defun legal-moves (lst)
(cond                            
    ((equal (position 'e lst) 0) (move-top-left lst '(D R)))        ; Use pos to find where e is located (list index starts at 0)
    ((equal (position 'e lst) 1) (move-top-middle lst '(D L R)))    ; Based on where e is in lst call associated function w/ lst and list of possible move directions as params
    ((equal (position 'e lst) 2) (move-top-right lst '(D L)))
    ((equal (position 'e lst) 3) (move-middle-left lst '(U D R)))
    ((equal (position 'e lst) 4) (move-middle-middle lst '(U D L R)))
    ((equal (position 'e lst) 5) (move-middle-right lst '(U D L)))
    ((equal (position 'e lst) 6) (move-bottom-left lst '(U R)))
    ((equal (position 'e lst) 7) (move-bottom-middle lst '(U L R)))
    ((equal (position 'e lst) 8) (move-bottom-right lst '(U L)))
)
)

; For when index where E is being moved to is less than its current index
(defun perform-move (state temp movelst)
    (list (car movelst) (substitute 'e temp (substitute temp 'e state) :count 1))
)

; For when index where E is being moved to is greater than its current index
(defun perform-move-from-end (state temp movelst)
    (list (car movelst) (substitute 'e temp (substitute temp 'e state) :count 1 :from-end t))
    
)

(defun move-top-left (lst movelst)
    ; Move down swap index 0 and index 3
    ; Move right swap index 0 and index 1
    ; Create an interior helper to hold the value e is being swapped w/ temporarily?
(cond
    ((null movelst) '())
    ((equal (car movelst) 'D) (cons (perform-move-from-end lst (nth 3 lst) movelst) (move-top-left lst (cdr movelst))))
    ((equal (car movelst) 'R) (cons (perform-move-from-end lst (nth 1 lst) movelst) (move-top-left lst (cdr movelst))))
)
)

(defun move-top-middle (lst movelst)
    ; Move down swap index 1 and index 4
    ; Move left swap index 1 and 0
    ; Move right swap index 1 and 2
(cond
    ((null movelst) '())
    ((equal (car movelst) 'D) (cons (perform-move-from-end lst (nth 4 lst) movelst) (move-top-middle lst (cdr movelst))))
    ((equal (car movelst) 'L) (cons (perform-move lst (nth 0 lst) movelst) (move-top-middle lst (cdr movelst))))
    ((equal (car movelst) 'R) (cons (perform-move-from-end lst (nth 2 lst) movelst) (move-top-middle lst (cdr movelst))))
)
)

(defun move-top-right (lst movelst)
    ; Move down swap index 2 with 5
    ; Move left swap index 2 with 1
(cond
    ((null movelst) '())
    ((equal (car movelst) 'D) (cons (perform-move-from-end lst (nth 5 lst) movelst) (move-top-right lst (cdr movelst))))
    ((equal (car movelst) 'L) (cons (perform-move lst (nth 1 lst) movelst) (move-top-right lst (cdr movelst))))
)  
)

(defun move-middle-left (lst movelst)
    ; Move up swap index 3 with 0
    ; Move down swap index 3 with 6
    ; Move right swap index 3 with 4
(cond
    ((null movelst) '())
    ((equal (car movelst) 'U) (cons (perform-move lst (nth 0 lst) movelst) (move-middle-left lst (cdr movelst))))
    ((equal (car movelst) 'D) (cons (perform-move-from-end lst (nth 6 lst) movelst) (move-middle-left lst (cdr movelst))))
    ((equal (car movelst) 'R) (cons (perform-move-from-end lst (nth 4 lst) movelst) (move-middle-left lst (cdr movelst))))
)
)

(defun move-middle-middle (lst movelst)
    ; Move up swap index 4 with 1
    ; Move down swap index 4 with 7
    ; Move left swap index 4 with 3
    ; Move right swap index 4 with 5
(cond
    ((null movelst) '())
    ((equal (car movelst) 'U) (cons (perform-move lst (nth 1 lst) movelst) (move-middle-middle lst (cdr movelst))))
    ((equal (car movelst) 'D) (cons (perform-move-from-end lst (nth 7 lst) movelst) (move-middle-middle lst (cdr movelst))))
    ((equal (car movelst) 'L) (cons (perform-move lst (nth 3 lst) movelst) (move-middle-middle lst (cdr movelst))))
    ((equal (car movelst) 'R) (cons (perform-move-from-end lst (nth 5 lst) movelst) (move-middle-middle lst (cdr movelst))))
)
)

(defun move-middle-right (lst movelst)
    ; Move up swap index 5 with 2
    ; Move down swap index 5 with 8
    ; Move left swap index 5 with 4
(cond
    ((null movelst) '())
    ((equal (car movelst) 'U) (cons (perform-move lst (nth 2 lst) movelst) (move-middle-right lst (cdr movelst))))
    ((equal (car movelst) 'D) (cons (perform-move-from-end lst (nth 8 lst) movelst) (move-middle-right lst (cdr movelst))))
    ((equal (car movelst) 'L) (cons (perform-move lst (nth 4 lst) movelst) (move-middle-right lst (cdr movelst))))
)
)

(defun move-bottom-left (lst movelst)
    ; Move up swap index 6 with 3
    ; Move right swap index 6 with 7
(cond
    ((null movelst) '())
    ((equal (car movelst) 'U) (cons (perform-move lst (nth 3 lst) movelst) (move-bottom-left lst (cdr movelst))))
    ((equal (car movelst) 'R) (cons (perform-move-from-end lst (nth 7 lst) movelst) (move-bottom-left lst (cdr movelst))))
)
)

(defun move-bottom-middle (lst movelst)
    ; Move up swap index 7 with 4
    ; Move left swap index 7 with 6
    ; Move right swap index 7 with 8
(cond
    ((null movelst) '())
    ((equal (car movelst) 'U) (cons (perform-move lst (nth 4 lst) movelst) (move-bottom-middle lst (cdr movelst))))
    ((equal (car movelst) 'L) (cons (perform-move lst (nth 6 lst) movelst) (move-bottom-middle lst (cdr movelst))))
    ((equal (car movelst) 'R) (cons (perform-move-from-end lst (nth 8 lst) movelst) (move-bottom-middle lst (cdr movelst))))
)
)

(defun move-bottom-right (lst movelst)
    ; Move up swap index 8 with 5
    ; Move left swap index 8 with 7
(cond
    ((null movelst) '())
    ((equal (car movelst) 'U) (cons (perform-move lst (nth 5 lst) movelst) (move-bottom-right lst (cdr movelst))))
    ((equal (car movelst) 'L) (cons (perform-move lst (nth 7 lst) movelst) (move-bottom-right lst (cdr movelst))))
)
)

; Function to return a list of possible moves from a given state
(defun moves (lst)
    (legal-moves lst)
)


; EightPuzzle2 Functions

(defun SSS (state &key (type 'BFS) (depth 7) (F #'out-of-place-f))
   (cond
   ((goal-state state) nil)
   ((equal type 'BFS) (search-BFS (make-open-init state)))
   ((equal type 'DFS) (search-DFS (make-open-init state) depth))
   ((equal type 'ID) (search-ID (make-open-init state)))
   ((equal type 'A*) (search-a* (make-open-init state) F))))

(defun search-BFS (open)
   ;; check for failure
   (cond 
   ((null open) nil)
   ;; check for goal state
   ((goal-state (get-state (caar open))) (path (car open)))
   ;; call search-BFS with new open list
   (t (search-BFS (append (cdr open)
                          (extend-path (car open))))))) 

(defun search-DFS (open depth)
   ;; check for failure
   (cond
   ((null open) nil)
   ;; check for goal state
   ((goal-state (get-state (caar open))) (path (car open)))
   ;; check if current path exceeds depth 
   ((> (length (car open)) depth) (search-DFS (rest open) depth))
   (t (search-DFS (append (extend-path (car open))
                          (cdr open)) depth))))

(defun search-ID (open &optional (curr-depth 0))
   (let ((current-dfs (search-DFS open curr-depth)))
   (cond
   ((null current-dfs) (search-ID open (+ 1 curr-depth)))
   (t current-dfs))))

(defun extend-path (path)
  (let* ((tempmoves (moves (get-state (first path))))
         (newmoves (remove-redundant tempmoves path)))
  (extend-helper newmoves path)))

(defun extend-helper (movelst path)
   (cond
   ((null movelst) nil)
   (t (cons (cons (car movelst) path) (extend-helper (cdr movelst) path)))))

(defun make-open-init (state)
   (list (list (list nil state))))




; EightPuzzle3 Functions

(defun out-of-place (state)
    (defun count-out-of-place (state goal counter)
    (cond
        ((null state) counter)
        ((equal (car state) 'E) (count-out-of-place (cdr state) (cdr goal) counter))
        ((not(equal (car state) (car goal))) (count-out-of-place (cdr state) (cdr goal) (+ counter 1)))
        (T (count-out-of-place (cdr state) (cdr goal) counter)) 
    )
    )
    (count-out-of-place state '(1 2 3 8 e 4 7 6 5) 0)
)

(defun out-of-place-f (path)
    (let ((current-misplaced (out-of-place (first (cdr (first path)))))) ; Sets current-misplaced equal to number of out of place elements in current state
    (defun count-depth (path counter)
    (cond
        ((equal (first (first path)) NIL) counter)
        (T (count-depth (cdr path) (+ counter 1)))
    )
    )
    (+ (count-depth path 0) current-misplaced)
    )
)

; Returns manhattan value based on where 1 is located in state
(defun manhattan-one (state)
(cond
    ((equal (position 1 state) 0) 0)        
    ((equal (position 1 state) 1) 1)    
    ((equal (position 1 state) 2) 2)
    ((equal (position 1 state) 3) 1)
    ((equal (position 1 state) 4) 2)
    ((equal (position 1 state) 5) 3)
    ((equal (position 1 state) 6) 2)
    ((equal (position 1 state) 7) 3)
    ((equal (position 1 state) 8) 4)
)
)

(defun manhattan-two (state)
(cond
    ((equal (position 2 state) 0) 1)
    ((equal (position 2 state) 1) 0)
    ((equal (position 2 state) 2) 1)
    ((equal (position 2 state) 3) 2)
    ((equal (position 2 state) 4) 1)
    ((equal (position 2 state) 5) 2)
    ((equal (position 2 state) 6) 3)
    ((equal (position 2 state) 7) 2)
    ((equal (position 2 state) 8) 3)
)
)

(defun manhattan-three (state)
(cond
    ((equal (position 3 state) 0) 2)
    ((equal (position 3 state) 1) 1)
    ((equal (position 3 state) 2) 0)
    ((equal (position 3 state) 3) 3)
    ((equal (position 3 state) 4) 2)
    ((equal (position 3 state) 5) 1)
    ((equal (position 3 state) 6) 4)
    ((equal (position 3 state) 7) 3)
    ((equal (position 3 state) 8) 2)
)
)

(defun manhattan-four (state)
(cond
    ((equal (position 4 state) 0) 3)
    ((equal (position 4 state) 1) 2)
    ((equal (position 4 state) 2) 1)
    ((equal (position 4 state) 3) 2)
    ((equal (position 4 state) 4) 1)
    ((equal (position 4 state) 5) 0)
    ((equal (position 4 state) 6) 3)
    ((equal (position 4 state) 7) 2)
    ((equal (position 4 state) 8) 1)
)
)

(defun manhattan-five (state)
(cond
    ((equal (position 5 state) 0) 4)
    ((equal (position 5 state) 1) 3)
    ((equal (position 5 state) 2) 2)
    ((equal (position 5 state) 3) 3)
    ((equal (position 5 state) 4) 2)
    ((equal (position 5 state) 5) 1)
    ((equal (position 5 state) 6) 2)
    ((equal (position 5 state) 7) 1)
    ((equal (position 5 state) 8) 0)
)
)

(defun manhattan-six (state)
(cond
    ((equal (position 6 state) 0) 3)
    ((equal (position 6 state) 1) 2)
    ((equal (position 6 state) 2) 3)
    ((equal (position 6 state) 3) 2)
    ((equal (position 6 state) 4) 1)
    ((equal (position 6 state) 5) 2)
    ((equal (position 6 state) 6) 1)
    ((equal (position 6 state) 7) 0)
    ((equal (position 6 state) 8) 1)
)
)

(defun manhattan-seven (state)
(cond
    ((equal (position 7 state) 0) 2)
    ((equal (position 7 state) 1) 3)
    ((equal (position 7 state) 2) 4)
    ((equal (position 7 state) 3) 1)
    ((equal (position 7 state) 4) 2)
    ((equal (position 7 state) 5) 3)
    ((equal (position 7 state) 6) 0)
    ((equal (position 7 state) 7) 1)
    ((equal (position 7 state) 8) 2)
)
)

(defun manhattan-eight (state)
(cond
    ((equal (position 8 state) 0) 1)
    ((equal (position 8 state) 1) 2)
    ((equal (position 8 state) 2) 3)
    ((equal (position 8 state) 3) 0)
    ((equal (position 8 state) 4) 1)
    ((equal (position 8 state) 5) 2)
    ((equal (position 8 state) 6) 1)
    ((equal (position 8 state) 7) 2)
    ((equal (position 8 state) 8) 3)
)
)

; Use to call the correct function to calculate manhattan value for current number
(defun determine-number (val state)
(cond
    ((equal val 1) (manhattan-one state))
    ((equal val 2) (manhattan-two state))
    ((equal val 3) (manhattan-three state))
    ((equal val 4) (manhattan-four state))
    ((equal val 5) (manhattan-five state))
    ((equal val 6) (manhattan-six state))
    ((equal val 7) (manhattan-seven state))
    ((equal val 8) (manhattan-eight state))
)
)


(defun manhattan (state)
    ; counter stores manhattan distance, inc will increment every recursion to check every value in the list
    (defun determine-distance (state counter inc)
    ; Use position to find where each number is then determine count based on that
    (cond
        ((equal inc 9) counter)
        ; Call determine-number passing inc and state adding that result to counter and add that to recursive call
        (T (+ (determine-distance state counter (+ inc 1)) (+ counter (determine-number inc state))))
    )
    )
    (determine-distance state 0 1)
)

(defun manhattan-f (path)
    (let ((current-manhattan (manhattan (get-state (first path)))))
    (defun count-depth (path counter)
    (cond
        ((equal (first (first path)) NIL) counter)
        (T (count-depth (cdr path) (+ counter 1)))
    )
    )
    (+ (count-depth path 0) current-manhattan)
    )
)

(defun better (func)
    (lambda (path1 path2)
        (let ((eval1 (funcall func path1)) (eval2 (funcall func path2))) ; Set results of calling passed function onto both paths into eval1 and eval2
        (<= eval1 eval2) ; Test if eval1 >= eval2 return T if yes, Nil otherwise
        )
    )
)

(defun search-a* (open-lst heuristic)
(cond
    ; Check for failure (empty list)
    ((null open-lst) nil)
    ; Check for goal state
    ((goal-state (get-state (caar open-lst))) (path (car open-lst)))
    ; Recursive call with new open-lst
    (T (let ((new-paths (extend-path (car open-lst))))
        (search-a* (merge 'list (sort new-paths (better heuristic)) (cdr open-lst) (better heuristic))
            heuristic)))
)
)

