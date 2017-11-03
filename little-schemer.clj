(defn member?
  "check if the element x is contained in the list lst;
   There are 3 things to check:

1. if the list is empty -> stop and return false
2. if the head of the list contains x => finished, the element is found
3. in all other cases: continue with the remainder of the list
"
  [x lst]
  ( cond (empty? lst) false
         (= (first lst) x) true
         :else (member? x (rest lst))))


(defn rember
  "Remove only the first occurence element x from the list lst.

Three conditions:

1. The list is empty => return the empty list
2. The list head equals x => return the rest of the list
3. otherwise: call the function again with the rest of the list

"
  [x lst]
  ( cond (empty? lst) '()
         (= (first lst) x) (rest lst)
         :else (rember x (rest lst))))


(defn multi-rember
  "Remove all occurences of the element x from the list lst."
  [x lst]
  ( cond (empty? lst) '()
         (= (first lst) x) (multi-rember x (rest lst))
             :else (cons (first lst)
                         (multi-rember x (rest lst)))))

(defn insertL
  "insert the element new in list lst only before the first occurence of the element old"
  [old new lst]
  ( cond (empty? lst) '()
         (= (first lst) old) (cons new
                                   (cons old
                                         (rest lst)))
         :else (cons (first lst)
                     (insertL old new (rest lst)))))


(defn multiinsertL
  "insert the element new in list lst before the element(s) old"
  [old new lst]
  ( cond (empty? lst) '()
         (= (first lst) old) (cons new
                                   (cons old
                                         (multiinsertL old new (rest lst))))
         :else (cons (first lst)
                     (multiinsertL old new (rest lst)))))
