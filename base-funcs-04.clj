(def my-lst '(1 2 3 4 5 2 1 6 3))

; rotate a list lst by n positions
(defn rot-lst [n lst]
  (let [[newtail newhead] (split-at n lst)]
       (concat newhead newtail)))

(rot-lst 3 my-lst)



; rotate a list lst by n positions; allow negative n
(defn rot-lst-neg [n lst]
  (let [at (mod n (count lst))
        [newtail newhead] (split-at at lst)]
       (concat newhead newtail)))


(rot-lst-neg -3 my-lst)


(defn insert-at [n elem lst]
  (cond
    (== n 0) (conj lst elem)
    :else (conj (insert-at (- n 1) elem (rest lst)) (first lst))))

(insert-at 4 "xxx" my-lst)


(defn remove-at [n lst]
  (cond
    (== n 0) (rest lst)
    :else (conj (remove-at (- n 1) (rest lst)) (first lst))))

(remove-at 4 my-lst)
