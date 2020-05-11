(def my-lst '(1 1 2 2 2 4 3 4 4 4 5 5 1 1 2 1))

; group-by f x: Returns a map of the elements of coll keyed by the result of f
; on each element. The value at each key will be a vector of the corresponding
; elements, in the order they appeared in coll.
(group-by #(mod % 2) my-lst)

; partition-by f coll: apply f to each value in coll, splitting it each time f
; returns a new value
(partition-by #(mod % 2) my-lst)


; RLE
; ============

; we can use partition to perform run length encoding (RLE)
; lets partition using the identity function -> every "new" value in the list
; splits the list
(defn pid [lst]
  (partition-by identity my-lst))

(pid my-lst)

; return a list with the #elements and the first element
(defn rle-cnt [x]
  (list (count x) (first x)))

(rle-cnt '(1 1 1 1)) ; -> (4,1)

; and map the rle function over the partitioned list => this yields the run-length encoded list
(defn my-rle [lst]
  (map rle-cnt (pid lst)))

(def rle-res (my-rle my-lst))

; Now go backwards

; repeat the second element of x the first element of x times  
(defn rep [x]
  (repeat (first x) (second x)))

(rep '(2 4)) ; -> (4,4)

(defn dec-rle [r]
  (flatten ; and flatten the thing afterwards
   (map rep r))) ; repeat each element of the RLE lst

; instead of the nested, inside-out thing, we can use the ->> macro
(defn dec-rle2 [r]
  (->> rle-res
       (map rep)
       flatten))

(dec-rle rle-res)
(dec-rle2 rle-res)

; ========================

; "duplicate" items
(defn dbl [x] (list x x))

; map this duplicate function over each list element
(def dbl-my-lst (flatten (map dbl my-lst)))

; another (more flexible) option is to use repeat
(defn n-my-lst [n]
  (flatten (map #(repeat n %1) my-lst)))

(n-my-lst 3)
