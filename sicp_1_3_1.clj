(defn sum [term a next b]
  (if (> a b)
    0
    (+ (term a)
       (sum term (next a) next b))))

; anonymuous increment function
(#(+ 1 %) 3)

; summing the integers between 1 and 10
(sum identity 1 #(+ 1 %) 10)

; summing even numbers
(sum identity 2 #(+ 2 %) 10)

; summing the squares between 1 and 10
(sum #(* % %) 1 #(+ 1 %) 10)

; the iterative version
(defn sum-v2 [term a next b]
  (defn sum-iter [term a next b acc]
    (if (> a b)
      acc
      (sum-iter term (next a) next b (+ acc (term a)))))
  (sum-iter term a next b 0))

(sum-v2 identity 1 #(+ 1 %) 10)

(defn integral [f a b dx]
  (defn add-dx [x]
    (+ x dx))
  (* (sum-v2 f (+ a (/ dx 2.0)) add-dx b)
     dx))

(integral #(* % %) 0 1 0.001)
