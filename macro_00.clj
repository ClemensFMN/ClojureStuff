(let [e (list + 1 2)]
  (eval e))
; returns 3

(let [e (list 1 + 2)]
  (eval ((second e) (first e) (last e))))
; returns 3



(defmacro postfix-notation
  "I'm too indie for prefix notation"
  [expression]
  (conj (butlast expression) (last expression)))

; this works
; (postfix-notation (1 1 +))

(macroexpand '(postfix-notation (1 1 +)))


(defmacro chg-order [expr]
  (list (second expr) (first expr) (last expr)))

(chg-order (1 + 5))

(macroexpand '(chg-order (1 + 2)))


(defmacro ignore-last [expr]
  (butlast expr))

(ignore-last (+ 4 5 6))

(macroexpand '(ignore-last (+ 4 5 6)))

(defn my-if [pred v1 v2]
  (cond
    (= pred true) v1
    (= pred false) v2))

(defmacro add-ten [x]
  (list '+ 10 x))

(add-ten 5)

(macroexpand '(add-ten 5))

