(defn vrange [N]
  (loop [i 0 v (transient [])]
    (if (< i N)
      (recur (inc i) (conj! v i))
      (persistent! v))))

(vrange 5)
