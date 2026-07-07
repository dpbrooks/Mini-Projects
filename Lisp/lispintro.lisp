
(defun my-rotate (lst)
(append (cdr lst) (list (car lst)))
)


(defun my-rotate-n (num lst)
(cond
    ((equal num 0) lst)
    (T (my-rotate-n (- num 1) (my-rotate lst)))
)
)

(defun first-sat (lst1 lst2 foo)
(cond
    ((null lst1) nil)
    ((funcall foo (car lst1) (car lst2)) (list (car lst1) (car lst2)))
    (T (first-sat (cdr lst1) (cdr lst2) foo))
)
)


(defun my-remove (rem lst)
(cond 
    ((null lst) '())
    ((listp (car lst)) (cons (my-remove rem (car lst)) (my-remove rem (cdr lst))))
    ((equal rem (car lst)) (my-remove rem (cdr lst)))
    (T (cons (car lst) (my-remove rem (cdr lst))))
)
)


(defun palindromep (lst)
(cond
    ((null lst) T)
    ((equal (car lst) (car (last lst))) (palindromep (cdr (butlast lst))))
    (T NIL)
)
)
