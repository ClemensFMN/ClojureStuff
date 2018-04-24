(defn vrange [N]
  (loop [i 0 v (transient [])]
    (if (< i N)
      (recur (inc i) (conj! v i))
      (persistent! v))))

(vrange 5)


(defn my-fibb [N]
  (loop [i 0 fib (transient [1 1])] ; we create a counter i and a transient vector initialized with the first two fib numbers
  	(if (< (+ i 2) N) ; correct the counter by 2
  	  (recur (inc i) (conj! fib (+ (get fib i) (get fib (inc i))))) ; inc counter + add next fibonacci number to transient
  	  (persistent! fib)))) ; done? -> make transient persistent & return it

(my-fibb 5)

; the 3n+1 sequence
; create one step of the 3n+1 sequence
(defn one-step [n]
  (cond
    (even? n) (/ n 2)        ; n even -> n/2
    (odd? n) (+ (* 3 n) 1))) ; n odd -> 3n+1

; create the sequence with start value x0
(defn three-n-plus-one [x0]
   (loop [i 0 sq (transient []) xn x0] ; setup a loop with zero length, (transient) empty seq  and val = x0
      (if (= xn 1) ; one?
         {:runs (inc i) :sequence (persistent! (conj! sq 1))} ; -> yes, return map with sequence (persistent!) and length
         (recur (inc i) (conj! sq xn) (one-step xn))))) ; otherwise, loop with increased length and next val conj'd to sq

(three-n-plus-one 15)

; make a transient vector filled with N zeros
(defn make-transient-vec [N]
  (transient (vec (repeat N 0))))

; using assoc! we can set the val of a transient vector at index
(defn my-seq [N]
  (loop [i 0 sq (make-transient-vec N)]
    (if (< i N)
      (recur (inc i) (assoc! sq i (* i i))) ; not done -> increment loop counter, set vector at position i with value i*i
      (persistent! sq)))) ; done -> make persistent and return

(my-seq 10)


