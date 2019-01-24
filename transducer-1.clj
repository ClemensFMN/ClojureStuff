; this defines a function which returns a transducer
(defn simple-transform [fac]
  (map #(* fac %)))

; which can be applied as follows
; yielding a list
(sequence (simple-transform 3) [1 2 3])
; yielding arrays
(into [] (simple-transform 3) [1 2 3])
(into [] (simple-transform 3) (range 11)) ; -> [0 3 6 9 12 15 18 21 24 27 30]


(defn filter-transform [thrshld]
  (filter #(< % thrshld)))

; comp combines several transducers into one (the combination starts with the left-most function first)
(def transducer1 (comp (simple-transform 3) (filter-transform 10)))
; which can be applied afterwards...
(into [] transducer1 (range 11))


