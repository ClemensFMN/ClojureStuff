(defn my-contains? [x lst]
  (some #(= x %) lst))

(my-contains? 1 [1,2,3])
(my-contains? 2 [1,2,3])
(my-contains? 10 [1,2,3])




(defn set? [lst]
  (cond (empty? lst) :true
        (my-contains? (first lst) (rest lst)) :false
        :else (set? (rest lst))))

(set? [1,2,3])
(set? [1,1,2,3])


(defn make-set [lst]
  (cond (empty? lst) '()
        (my-contains? (first lst) (rest lst)) (make-set (rest lst))
        :else (cons (first lst)
                    (make-set (rest lst)))))

(make-set [1,2,3])
(make-set [1,2,3,1])
(make-set [1,2,3,2,3,1])

