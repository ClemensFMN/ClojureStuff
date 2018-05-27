; convert this
(reduce + (take 4 (filter even? (map #(* % %) (range)))))

; into this...
(->> (range)
     (map #(* % %))
     (filter even?)
     (take 4)
     (reduce +))


