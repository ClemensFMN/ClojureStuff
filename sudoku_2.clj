; SUDOKU Solver
; like in sudoku_1, we model the board as vector and not as map
; this allows simpler debugging as a map is not sorted

; solvable via CP alone
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

; not solvable via CP alone...
(def test-puzzle-2
  [0 0 0 0 0 0 6 0 0
   0 0 0 3 0 5 0 0 1
   0 0 1 8 0 6 4 0 0
   0 0 8 1 0 2 9 0 0
   7 0 0 0 0 0 0 0 8
   0 0 6 7 0 8 2 0 0
   0 0 2 6 0 9 5 0 0
   8 0 0 2 0 3 0 0 9
   0 0 5 0 1 0 3 0 0])



(def all-puzzle-coords
  (for [row (range 0 9)
        col (range 0 9)]
    [col row]))

(defn puzzle-index [col row]
  (+ col
     (* row 9)))

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
(test-puzzle-map [0 0]) ; -> [1 2 3 4 5 6 7 8 9]
(test-puzzle-map [2 0]) ; -> [3]


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
        (filter #(= 1 (count %)) (map #(puzzle %) (row-coords col row)))
        (filter #(= 1 (count %)) (map #(puzzle %) (col-coords col row)))
        (filter #(= 1 (count %)) (map #(puzzle %) (grid-coords col row)))))))

(get-relevant-neighbours test-puzzle-map 0 0) ; -> (3 2 6 9 7 8 1)

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
  (reduce (fn [board [col row]]
    ;(println col row board)
    (constraint-prop-position board col row))
    puzzle
    all-puzzle-coords))

(def one-round (constraint-prop-round test-puzzle-map))
(filter #(= 1 (count (second %))) one-round)

; it is weird, that (one-round [6 7] is still (1 7) as (one-round [6 1]) = (7 8) & (one-round [6 4]) became 1 in the last round of constraint prop
; all other vales in this column are fixed - why isn't 6 7 updated??

(constraint-prop-position one-round 6 7)
(get-relevant-neighbours one-round 6 7)

; FOUND - there was an ugly bug in get-relevant-neighbours & constraint-prop-round...

((constraint-prop-round test-puzzle-map) [0 0])

(= test-puzzle-map (constraint-prop-round test-puzzle-map))
(= (constraint-prop-round test-puzzle-map) (constraint-prop-round (constraint-prop-round test-puzzle-map)))

(defn constraint-prop
  "constraint propagation until nothing changes anymore"
  [puzzle]
  (loop [i 0 pzl puzzle]
    (let [new-pzl (constraint-prop-round pzl)]
      (if (= pzl new-pzl)
        ; {:sol new-pzl :iters i}
        new-pzl
      (recur (inc i) new-pzl)))))

(def cp-complete (constraint-prop test-puzzle-map))

(defn puzzle-solved?
  "check whether a puzle is solved; three outcomes: :done, :ambiguous (at least one cell not fix), :stuck (no options in at least one cell)"
  [puzzle]
  (cond
    (every?
      #(= 1 %)
      (map #(count (val %)) puzzle)) :done
    (not-any?
      #(= 0 %)
      (map #(count (val %)) puzzle)) :ambiguous
    (not-any?
      #(> 1 %)
      (map #(count (val %)) puzzle)) :stuck))

(puzzle-solved? test-puzzle-map)
;(puzzle-solved? (cp-complete :sol))
(puzzle-solved? cp-complete)

(def test-puzzle-map-2 (convert-puzzle-to-map test-puzzle-2))
(puzzle-solved? (constraint-prop test-puzzle-map-2))

(defn solve
  "the master solve function"
  [puzzle]
  ; a bit tired at the moment
  ; basicaly, run a round of CP
  ; then check for the three cases:
  ; :done -> done
  ; :stuck -> do nothing
  ; :ambiguous -> go over all cells with > 1 values
  ;                  go over all values
  ;                     call solve

  )



