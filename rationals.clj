; rational number stuff according to SICP

(defn gcd [a b]
  (if (= b 0)
    a
    (gcd b (rem a b))))


(defn make-rat [n d]
  (let [g (gcd n d)]
    {:n (/ n g) :d (/ d g)}))

(def r1 (make-rat 2 3))
(def r2 (make-rat 1 6))

(defn numer [rat]
  (rat :n))

(defn denom [rat]
  (rat :d))

(defn print-rat [rat]
  (println (numer rat) "/" (denom rat)))

(print-rat r1)

(defn add-rat [r1 r2]
  (make-rat (+ (* (numer r1) (denom r2))
               (* (numer r2) (denom r1)))
            (* (denom r1) (denom r2))))

(add-rat r1 r2)

         
