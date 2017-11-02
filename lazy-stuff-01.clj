; returns 1/2 * (x + N/x) wich has sqrt(N) as fixed point
(defn sqrt-iter [x N]
  (/ (+ x (/ N x)) 2))

; build an infinite seq based on sqrt-iter
(defn sqrt-seq [N]
  (iterate #(sqrt-iter %1 N) ; iterate sqrt-iter
           (/ N 2.0))) ; with N/2 as starting point...


; some demo...
(take 10 (sqrt-seq 100))


; this does not work -> the thing does not stop...
;(defn sqrt-fin-2 [N]
;  (let [lst (sqrt-seq N)]
;       take 5 lst))

; calculate the error between x & y^2
(defn abs-error [x y]
  (Math/abs (- x (* y y))))

; takes the number to calculate the square root from and a sequence approximating the square root
; returns the first element of the sequence sufficiently approximating the square root
(defn sqrt-temp [N sqrt-seq]
  (cond 
    (> 0.001 (abs-error N (first sqrt-seq))) (first sqrt-seq)
    :else (sqrt-temp N (rest sqrt-seq))))

(defn sqrt-fin [N]
  (sqrt-temp N (sqrt-seq N)))

