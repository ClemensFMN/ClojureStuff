
; combinations of k distinct objects chosen from a list of n elements are the 
; first element of the list conjoined to the combinations of k-1 distinct 
; objects from the rest of the list, plus all combinations of k distinct objects 
; from the list without its first element (again: the rest or tail of the list).
(defn combinations [k n]
  "P26 (**) Generate the combinations of K distinct objects chosen
  from the N elements of a list."
  (cond (= k 0) '(nil)
        (empty? n) nil
        :else (concat (map #(conj % (first n))
                           (combinations (dec k) (rest n)))
                      (combinations k (rest n)))))


