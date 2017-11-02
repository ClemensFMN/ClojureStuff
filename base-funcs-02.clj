(def my-lst '(1 2 3 4 5 6 7 8 9 10))

; map can also take a function with n arguments (in this case 2) and n seqs
; range produces an lazy seq of nums
; and vec combines the current value of the range with the current list element
; -> we get a list of vectors of the form [index element]
(def my-lst-ind (map vector (range) my-lst))

; another option is to use map-indexed f coll, which returns a lazy sequence
; consisting of the result of applying f to 0
; and the first item of coll, followed by applying f to 1 and the second
; item in coll, etc, until coll is exhausted.
(def my-lst-ind2 (map-indexed (fn [idx itm] [idx itm]) my-lst))

; Task: removing every third item from a list

; return false in case the index is a multiple of three
(defn flt-func [x]
  (not= 2 (rem (first x) 3)))

; apply above filter function to the indexed list
(def my-lst-drpd (filter flt-func my-lst-ind))

; and remove the index
(def my-lst-drpd-fin (map second my-lst-drpd))
