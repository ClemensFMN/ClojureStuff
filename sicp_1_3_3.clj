(defn average [a b]
  (/ (+ a b) 2))

(defn close-enough? [a b]
  (< (Math/abs (- a b)) 0.01))
  

(defn search [f neg-p pos-p]
  (let [mid-p (average neg-p pos-p)]
        (if (close-enough? neg-p pos-p)
          mid-p
          (let [test-value (f mid-p)]
                (cond (pos? test-value)
                      (search f neg-p mid-p)
                      (neg? test-value)
                      (search f mid-p pos-p)
                      :else mid-p)))))

(search #(- % 1) 0 2)
;I don't understand why this does not work...
(search Math/sin 4 2)
