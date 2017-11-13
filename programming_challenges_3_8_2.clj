; that's the string of the board
; it needs to be broken in new lines every X chars
(def s "abcDEFGhigghEbkWalDorkFtyAwaldORmFtsimrLqsrcbyoArBeDeyvKlcbqwikomkstrEBGadhrbyUiqlxcnBjf")

; let's number the chars of the string -> we get a list of vectors [ind char]
(def zipped-s (map vector (range) s))

; next we transform the indices in the above list
; let's assume we have a char at pos X
; if our board has 10 columns, the row a char is in is (quot N 10)
; and the corresponding column is (rem N 10)
(def ind-s (map (fn [[ind c]] ; we map over all [ind char] vectors; notice the argument destruction!
       (vector ; and build a nested vector
        (vector (quot ind 11) (rem ind 11)) ; here we transform the linear index into a row/column pair
        c)) ; and add the char to the outer vector
       zipped-s))

; all that is left is to convert the list above into a map...
(def board
  (reduce (fn [new-mp [ind c]]
          (assoc new-mp ind c))
        {}
        ind-s))

; we can now use (board [1 2]) to retrieve the char in row 1 and column 2

; let's build a sequence of positions we build up a string from
(defn x-seq [N]
  (for [ind (range N)]
             [ind 0]))

(defn y-seq [N]
  (for [ind (range N)]
             [0 ind]))

(defn xy-seq [N]
  (for [ind (range N)]
             [ind ind]))

(defn shift-seq [delta-x delta-y sq]
  (map (fn [[x y]]
         (vector (+ delta-x x) (+ delta-y y))) sq))


(def waldorf (shift-seq 1 4 (xy-seq 7)))


; select the chars from the board according to the index sequence and build them into a string
(defn get-string [sq board]
  (apply str (map #(board %) sq)))

(get-string waldorf board)


(defn search-string [s board]
  (let [len (count s) xmax 11 ymax 8]
    (for [x (range xmax) y (range ymax)]
      (let [s-x (get-string (shift-seq x y (x-seq len)) board)
            s-y (get-string (shift-seq x y (y-seq len)) board)
            s-xy (get-string (shift-seq x y (xy-seq len)) board)]
        (if (or
             (= s s-x)
             (= s s-y)
             (= s s-xy))
          (println x y))))))

(search-string "WaLDorf" board)

(defn search-string-v2 [s board]
  (let [len (count s) xmax 11 ymax 8]
    (for [x (range xmax)
          y (range ymax)
          :let [s-x (get-string (shift-seq x y (x-seq len)) board)
                s-y (get-string (shift-seq x y (y-seq len)) board)
                s-xy (get-string (shift-seq x y (xy-seq len)) board)]
          :when (or
                 (= s s-x)
                 (= s s-y)
                 (= s s-xy))]
      [x y])))

(search-string-v2 "WaLDorf" board)


(for [x (range 5)
      y (range 4)
      :let [sum (+ x y)
            prod (* x y)]
      :when (or (< x y)
                 (> (+ x y) 4))]
      [x y sum prod])
    
  
