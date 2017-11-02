; define some function
(defn some-func [n]
  (/ n (+ n 1)))

; construct an endless sequence of function values
(def some-func-nums (map some-func (iterate inc 1)))

; take the first 10
(def nums1 (take 10 some-func-nums))

; the the first 10 larger than 0.75
(def nums2 (take 10 (filter #(> %1 0.75) some-func-nums))) 


(defn let-fun-1 [x]
  (let [x-temp-1 (* x x), x-temp-2 (- x 10)]
    (+ x-temp-1 x-temp-2))) 

(def v1 (let-fun-1 3))

; destructuring the hard way
(defn let-fun-2 [x]
  (let [ [x1 [x2 x3]] x]
    (vec [x1 (+ x2 x3)])))

(def v2 (let-fun-2 [3 [5 8]]))

; this should work??
;(defn let-fun-3 [x1 [x2 x3]]
;  x1)

;(def v3 (let-fun-3 [3 [5 8]]))


; an example showing that let can not only create local bindings but also
; define functions as local bindungs
; smaller? is defined via let & "parametrized" by an outside value
(defn t-fun [x lst]
  (let [smaller? #(< % x)]
        (filter smaller? lst)))

(def l1 (t-fun 5 (range 10)))
