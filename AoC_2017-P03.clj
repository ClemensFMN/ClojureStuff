(defn evnnums [N]
  (range 0 N 2))

(defn gen-timeseq [rng]
  (flatten (map #(vec [% % (inc %) (inc %)]) rng)))


(gen-timeseq (evnnums 5))

(defn gen-mvmt-time [N]
  (map vector (cycle [:L :D :R :U]) (gen-timeseq (evnnums N))))

(gen-mvmt-time 5)

; this one creates a sequence of L(eft), R(ight), U(p), and D(own) steps along the spiral
(defn create-mvmt-seq [N]
  (flatten (map (fn [[mvmt times]] (repeat times mvmt)) (gen-mvmt-time N))))


(create-mvmt-seq 5)

; calculate the coordinate update according to the step
(defn update-coord [step]
  (cond
    (= :R step) [1 0]
    (= :L step) [-1 0]
    (= :U step) [0 1]
    (= :D step) [0 -1]
    :else [0 0]))

(update-coord :R)

(defn convert-length-to-coord-int [N]
  (let [mvmt-seq (create-mvmt-seq N) ; this sequence is longer than N
        coord-deltas (take N (map update-coord mvmt-seq))] ; but here we cut it down to N...
        (reduce (fn [[xsum ysum] [deltax deltay]] (vec [(+ xsum deltax) (+ ysum deltay)])) [0 0] coord-deltas)))

; the numbers start at 1 - so we need to correct by 1
(defn convert-length-to-coord [N]
  (convert-length-to-coord-int (dec N)))

(convert-length-to-coord 5)
