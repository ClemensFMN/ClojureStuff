(loop [i 0]
  (println (str "i=" i))
  (if (> i 10)
    (println "finished")
    (recur (inc i))))

(defn sum-it [n]
  (loop [i n sum 0] ; create a point with bindings where the recur jumps to
    (if (= i 0)
      sum ; break-out
      (recur (dec i) (+ sum i))))) ; jump back and update bindings

(sum-it 10)

(defn my-sum [a b term next]
  (loop [i a sum 0]
    (if (> i b)
      sum
      (recur (next i) (+ sum (term i))))))

(defn term-id [x] x)
(my-sum 0 10 term-id #(inc %))

(my-sum 0 10 #(* % %) #(inc %))
(my-sum 0 10 term-id #(+ % 2))
