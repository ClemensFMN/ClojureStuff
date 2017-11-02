
; create one step of the 3n+1 sequence
(defn one-step [n]
  (cond
    (even? n) (/ n 2)        ; n even -> n/2
    (odd? n) (+ (* 3 n) 1))) ; n odd -> 3n+1

; create the 3n+1 sequence for a starting value n
(defn create-seq [n]
  (loop [nold n acc [n]]
    (cond
      (= 1 nold) acc ; we are finished -> return the accumulated sequence
      :else (let [nnew (one-step nold)] ; continue by calculating the next value
              (recur nnew (conj acc nnew)))))) ; and jump to the beginning

; length of the 3n+1 sequence with starting value n
(defn len-seq [n]
  (count (create-seq n)))

; obtain lengths of all 3n+1 seqs between start and (including) stop
(defn len-all-seqs [start stop]
  (map len-seq (range start (inc stop))))

(defn max-len [start stop]
  (reduce max (len-all-seqs start stop)))
