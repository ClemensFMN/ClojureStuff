(defn vrange [N]
  (loop [i 0 v (transient [])]
    (if (< i N)
      (recur (inc i) (conj! v i))
      (persistent! v))))

(vrange 5)


(defn my-fibb [N]
  (loop [i 0 fib (transient [1 1])] ; we create a counter i and a transient vector initialized with the first two fib numbers
  	(if (< (+ i 2) N) ; correct the counter by 2
  	  (recur (inc i) (conj! fib (+ (get fib i) (get fib (inc i))))) ; inc counter + add next fibonacci number to transient
  	  (persistent! fib)))) ; done? -> make transient persistent & return it

(my-fibb 5)
