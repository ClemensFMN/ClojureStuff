
; in order to calculate the difference between consecutive list elements,
; we first partition it into overlapping segments of length 2
(defn create-part [x]
  (vec ( partition 2 1 x)))

; next we calculate the absolute difference between consecutive list elements
(defn calc-abs-diff [x]
  (map #(Math/abs (- (first %1) (second %1))) x))


; jolly-jumper
(def lst-1 [1 4 2 3])
; not a jolly jumper
(def lst-2 [1 6 2 3])
; not a jolly jumper
(def lst-3 [1 4 2 -1 6])
; jolly-jumper
(def lst-4 [1 2 4 7 11])



; this one is a bit tricky
; if a length-n seq is a jolly jumper, it contains absolute differences with
; values from 1 to n-1
; first we define sd as the set of absoute differences (we need sets
; here in order to weed out duplicates of the absolute differences)
; then we compare the sd with a range from 1...n-1 converted into a
; set - this does not change the content of the range (it contains no
; duplicates anyway) but otherwise the comparison does not
; work (compare range with set)
(defn jolly-jumper? [x]
  (let [sd (set (calc-abs-diff (create-part x)))]
    (= sd (set (range 1 (count x))))))
