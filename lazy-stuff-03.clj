
; integer exponentiation see http://stackoverflow.com/questions/5057047/how-to-do-exponentiation-in-clojure
(defn exp-int [x n]
  (reduce * (repeat n x)))

; define a function
(defn f [n]
  (* n (exp-int 3 n)))

; create an infinite sequence from this function f(0), f(1), f(2)...
(def f-seq (map f (range)))

; take 10 elements from the sequence
(take 10 f-seq)


; make some "interesting" queries
; 10 even ones
(take 10 (filter even? f-seq))

; first two larger than 1000
(take 2 (drop-while #(< % 1000) f-seq))

; does not work (overflow) - we use the rest of the list...
; (def intmdt (map vector (butlast f-seq) (rest f-seq)))

;(defn quotnt [coll]
;  (lazy-seq
;   (if (empty? coll)
;     '()
;     (conj (quotnt (rest coll)) (/ (first coll) (second coll))))))

;(take 5 (quotnt f-seq))

(def s '(1 2 3 4))

(def quot-seq-in (partition 2 1 f-seq))
(def quot-seq (map #(/ (first %) (second %)) quot-seq-in))
(take 10 quot-seq)

