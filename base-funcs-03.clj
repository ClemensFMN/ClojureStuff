
; zip coll with index
(defn my-zip [coll]
  (map vector (range) coll))

(def my-lst '(1 2 5 6 3 2 1 3))

(def res1 (my-zip my-lst))


; return the indices of e in a list by using a for comprehension (the
; :when takes care that only matches are considered)
(defn findi [e coll]
  (for [
        [i v] (my-zip coll) :when (= e v)
        ]
    i))

(findi 3 my-lst)

(defn findip [f coll]
  "return all indices of collection elements where f (accepting index and value) returns true"
  (for [
        [i v] (my-zip coll) :when (f i v)
        ]
    i))

; same as above
(findip (fn [i v] (= v 3) ) my-lst)
(findip #(= %2 3) my-lst) ; using anonymuous function instead of the fn construct
(findip (fn [i v] (> v 2) ) my-lst)

(defn findivp [f coll]
  "return all indices and values of collection elements where f (accepting index and value) returns true"
  (for [
        [i v] (my-zip coll) :when (f i v)
        ]
    [i v]))

; same as above
(findivp (fn [i v] (= v 3) ) my-lst)
(findivp (fn [i v] (> v 2) ) my-lst)



; return the keys of e in a map
(defn findim [e coll]
  (for [[i v] (seq coll) :when (= e v)] i))

(def my-map {:a 3 :c 2 :f 3 :z 4})
(findim 3 my-map)


;=================================================

; achieving the same using the clojure function keep-indexed
; keep-indexed f coll: Returns a lazy sequence of the non-nil results of (f index item)
(defn positions [e coll]
  (keep-indexed (fn [idx item]
                  (when (= item e) ; check for item match
                    idx)) ; and return the corresponding index
                coll))

(positions 3 my-lst)

(defn find-ind-val [f coll]
  (keep-indexed #(if (f %1 %2) %1) coll))

(find-ind-val #(= %2 3) my-lst)


(defn find-ind-val-2 [f coll]
  (keep-indexed #(if (f %1 %2)
                   %1)
                coll))

(find-ind-val-2 (fn[i v] (= v 3)) my-lst)
(find-ind-val-2 (fn[i v] (> v 3)) my-lst)
(find-ind-val-2 (fn[i v] (< i 3)) my-lst)
