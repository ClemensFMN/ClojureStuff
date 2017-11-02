
; create an endless list
(def lazy-lst (range))

; take the first N elements as a list
(defn my-take [N lst]
  (cond
    (== N 0) '()
    :else (conj (my-take (- N 1) (rest lst)) (first lst))))

; (my-take 4 lazy-lst)

; build up list as long as the function returns true on the current list element
(defn my-take-while [f lst]
  (cond
    (false? (f (first lst))) '()
    :else (conj (my-take-while f (rest lst)) (first lst))))
  
; (my-take-while #(> 4 %1) lazy-lst)
