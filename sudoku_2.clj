; SUDOKU Solver
; like in sudoku_1, we model the board as vector and not as map
; this allows simpler debugging as a map is not sorted

(def all-puzzle-coords
  (for [row (range 0 9)
        col (range 0 9)]
    [col row]))

(defn convert-puzzle-to-map
  "convert a puzzle in string presentation to a map col/val -> value(s)"
  [puzzle]
  (into {} 
    (for [row (range 0 9) ; run over all rows
          col (range 0 9)] ; and cols
             (let [value (puzzle (puzzle-index col row))]
                (if (= 0 value)
                  [[col row] [1 2 3 4 5 6 7 8 9]]
                  [[col row] [value]])))))
  
(def test-puzzle-map (convert-puzzle-to-map test-puzzle))
(test-puzzle-map [0 0])
(test-puzzle-map [2 0])


(defn row-coords
  "generate coords of all row elements at col/row, excluding [col row]"
  [col row]
  (remove
    #{[col row]}
    (for [c (range 0 9)]
      [c row])))

(row-coords 0 0) ; -> ([1 0] [2 0] [3 0] [4 0] [5 0] [6 0] [7 0] [8 0])

(defn col-coords
  "generate coords of all column elements at col/row, excluding [col row]"
  [col row]
  (remove
    #{[col row]}
    (for [r (range 0 9)]
      [col r])))

(col-coords 0 0) ; -> ([0 1] [0 2] [0 3] [0 4] [0 5] [0 6] [0 7] [0 8])


(defn subgrid-coords
  "convert col/row to subgrid number"
  [col row]
  (map (fn [n] (int (/ n 3)))
       [col row]))

(defn grid-coords
  "generate coords of 3x3 subgrid elements corresponding col/row, excluding [col row]"
  [col row]
  (let [[c r] (subgrid-coords col row)] ; find subgrid number
    (let [start-col (* 3 c)
           start-row (* 3 r)]
      (remove
        #{[col row]}
        (for [row (range start-row (+ 3 start-row)) ; iterate over corresponding row
              col (range start-col (+ 3 start-col))] ; and cols
         [col row])))))

(grid-coords 0 0) ; -> ([1 0] [2 0] [0 1] [1 1] [2 1] [0 2] [1 2] [2 2])
(map #(test-puzzle-map %) (grid-coords 0 0)) ; -> ([1 2 3 4 5 6 7 8 9] [3] [9] [1 2 3 4 5 6 7 8 9] [1 2 3 4 5 6 7 8 9] [1 2 3 4 5 6 7 8 9] [1 2 3 4 5 6 7 8 9] [1])
(filter #(= 1 (count %)) (map #(test-puzzle-map %) (grid-coords 0 0))) ; -> ([3] [9] [1])


(defn get-relevant-neighbours
  "for constraint propagation relevant neighbours are ones with one allowed value"
  [puzzle col row]
  (distinct
    (flatten
      (concat
        (filter #(= 1 (count %)) (map #(test-puzzle-map %) (row-coords col row)))
        (filter #(= 1 (count %)) (map #(test-puzzle-map %) (col-coords col row)))
        (filter #(= 1 (count %)) (map #(test-puzzle-map %) (grid-coords col row)))))))

(get-relevant-neighbours test-puzzle-map 0 0)

(defn constraint-prop-position
  "constraint propagation of puzzle for a specific col/row position - returns an updated puzzle!"
  [puzzle col row]
  (assoc
    puzzle
    [col row]
    (remove
      (set (get-relevant-neighbours puzzle col row)) ; a bit of magic required...
      (puzzle [col row]))))

(def new-puzzle (constraint-prop-position test-puzzle-map 0 0))
(new-puzzle [0 0]) ; -> (4 5)
(= new-puzzle test-puzzle-map)

(defn constraint-prop-round
  "one round of constraint propagation; i.e. going over all positions once"
  [puzzle]
  (loop [pos all-puzzle-coords p puzzle]


    )

  )







(defn puzzle-index
  "convert col/row value to vector index"
  [col row]
  (+ col
     (* row 9)))

(defn puzzle-row
  "get values from puzzle in row"
  [puzzle row]
  (for [col (range 0 9)] ; run over cols
    (puzzle (puzzle-index col row)))) ; and collect the puzzle values

(defn puzzle-column
  "get values from puzzles in column"
  [puzzle col]
  (for [row (range 0 9)] ; run over rows
    (puzzle (puzzle-index col row)))) ; and collect the puzzle values

(defn subgrid-coords
  "convert col/row to subgrid number"
  [col row]
  (map (fn [n] (int (/ n 3)))
       [col row]))

(defn puzzle-subgrid
  "get values from puzzle's 3x3 subgrid belonging to col/row"
  [puzzle col row]
  (let [[c r] (subgrid-coords col row)] ; find subgrid number
    (let [start-col (* 3 c)
           start-row (* 3 r)]
       (for [row (range start-row (+ 3 start-row)) ; iterate over corresponding row
             col (range start-col (+ 3 start-col))] ; and cols
         (puzzle (puzzle-index col row)))))) ; and collect puzzle values

(puzzle-subgrid almost-complete-puzzle 0 0) ; -> (0 0 3 9 6 7 2 5 1)
(puzzle-subgrid almost-complete-puzzle 0 1) ; -> (0 0 3 9 6 7 2 5 1)
(puzzle-subgrid almost-complete-puzzle 0 3) ; -> (5 4 8 7 2 9 1 3 6)
(puzzle-subgrid almost-complete-puzzle 1 0) ; -> (0 0 3 9 6 7 2 5 1)
(puzzle-subgrid almost-complete-puzzle 3 0) ; -> (9 2 1 3 4 5 8 7 6)

(defn constraint-prop-position
  "Get all allowed values for a col and row index on a puzzle."
  [puzzle col row]
  (apply disj
         (set (range 1 10))
         (concat (puzzle-column puzzle col)
                 (puzzle-row puzzle row)
                 (puzzle-subgrid puzzle col row))))

(allowed-values test-puzzle 0 0) ; -> #{4 5}
(allowed-values test-puzzle 1 1) ; -> #{7 4 6 2 8}


(defn allowed-values-map
  "takes a puzzle and returns a map (col/row) -> allowed values"
  [puzzle]
  (into {} 
    (for [row (range 0 9) ; run over all rows
                 col (range 0 9)] ; and cols
             (let [values (allowed-values puzzle col row)] ; get the allowed values
                 [[col row] values])))) ; and stuff them into a map

(defn const-prop-pos
  "perform constraint propagation for a specific position"
  )



(defn print-puzzle
  "print puzzle to screen"
  [puzzle]
  (doseq [[col row] all-puzzle-coords]
    (cond  (= 0 col row) (print "[")
           (= 0 col) (print " "))
    (print (puzzle (puzzle-index col row)))
    (if-not (= 8 col) (print " "))
    (if  (= 8 col row) (print "]"))
    (if (= 8 col) (print "\n"))))



(def test-puzzle
  [0 0 3 0 2 0 6 0 0
   9 0 0 3 0 5 0 0 1
   0 0 1 8 0 6 4 0 0
   0 0 8 1 0 2 9 0 0
   7 0 0 0 0 0 0 0 8
   0 0 6 7 0 8 2 0 0
   0 0 2 6 0 9 5 0 0
   8 0 0 2 0 3 0 0 9
   0 0 5 0 1 0 3 0 0])

(def almost-complete-puzzle
  [0 0 3 9 2 1 6 5 7
   9 6 7 3 4 5 8 2 1
   2 5 1 8 7 6 4 9 3
   5 4 8 1 3 2 9 7 6
   7 2 9 5 6 4 1 3 8
   1 3 6 7 9 8 2 4 5
   3 7 2 6 8 9 5 1 4
   8 1 4 2 5 3 7 6 9
   6 9 5 4 1 7 3 8 2])
