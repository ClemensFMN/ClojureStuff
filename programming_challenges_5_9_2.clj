
; the "clean" way to reverse an integer number
; returns a list of the numbers digits in reverse order
(defn reverse-int-to-vec [x]
  (loop [xint x acc []]
    (if (< xint 10)
      (conj acc xint)
    (recur (/ (- xint (rem xint 10)) 10) (conj acc (rem xint 10))))))

; this generates a reversed number in=345 -> out = 543
(defn reverse-int [x]
  (reduce #(+ (* 10 %1) %2) (reverse-int-to-vec x)))

(defn reverse-int-v2 [x]
  (loop [xint x acc 0]
    (if (< xint 10)
      (+ (* 10 acc) xint)
      (let [first-digit (rem xint 10)]
        (recur (/ (- xint first-digit) 10) (+ (* acc 10) first-digit))))))



; check whether a number is a palindrome
(defn palindrome? [x]
  (= x (reverse-int x)))

; not used here - get the length (number of digits) of x
(defn get-int-length [x]
  (loop [xint x len 1]
    (if (< (quot xint 10) 10)
      (inc len)
    (recur (quot xint 10) (inc len)))))
    

; the "not-so-clean" version of a palindrome check by converting int to string
; check whether the provided input is a palindrome
;(defn palindrome? [x]
;  (= (seq (str x)) (reverse (str x))))

; we add a number and its reverse, check for palindrome -> if not, continue
(defn make-palindrome [x]
  (loop [xint x num 0]
    (if (palindrome? xint)
      [num xint]
      (recur (+ xint (reverse-int xint)) (inc num)))))



