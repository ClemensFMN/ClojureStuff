(def testvec [1 4 3 6 2 9])

(def x1 (testvec 0))

; this throws an exception
; (def x2 (testvec 10))

(def x2 (get testvec 0))

; this is ok and returns nil
(def x3 (get testvec 10))

; get allows to specify a default fail value
(def x4 (get testvec 10 :not-found))

; replacement within the vector
(def x5 (assoc testvec 3 :another-element))

; replacement allowed after the last element
(def x6 (assoc testvec 6 :another-element))

; -> of course, this does not change the original vector testvec

(def testvec-2d [[2 3][4 5]])

; get-in retrieves values from nested vecs -> from the 0th list, the first entry
(def x12 (get-in testvec-2d [0 1]))

; also allows to define a default fail value
(def xxx (get-in testvec-2d [3 3] :not-found))


(defn my-map [coll f]
  (loop [int-coll coll acc []]
    (if (empty? int-coll)
      acc
      (recur (next int-coll) (conj acc (f (first int-coll)))))))

(def l1 (my-map testvec #(* 2 %1)))


(defn my-filter [coll f]
  (loop [int-coll coll acc []]
    (if (empty? int-coll)
      acc
      (recur (next int-coll) 
             (if (f (first int-coll))
                    (conj acc (first int-coll))
                    acc)))))

(def l2 (my-filter testvec even?))

; f takes an element of the collection and the accumulator and produces the new
; accumulator value
(defn my-reduce [coll f init]
  (loop [int-coll coll acc init]
    (if (empty? int-coll)
      acc
      (recur (next int-coll) (f (first int-coll) acc)))))

(def v1 (my-reduce testvec #(+ %1 %2) 0))
(def v2 (my-reduce testvec #(* %1 %2) 1))
(def v3 (my-reduce testvec #(max %1 %2) 0))




(def test-map {:width 10, :height 20, :depth 6})

; use a foor loop to iterate over the key-val pairs of a map
; note the nice restructuring
; and the conversion from a symbol to a string via (name key)
(def l3 (for [[key val] test-map] (str (name key) ":" val)))

