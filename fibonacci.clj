(declare fib1-mem) ; we need to declare the fnction, otherwise we canot use it below

(defn fib1 [n]
  (if (< n 2)
    1
    (+ (fib1-mem (- n 1)) (fib1-mem (- n 2)))))

(def fib1-mem (memoize fib1))

(time (fib1 31))

(defn fib [n]
  (if (< n 2)
    1
    (+ (fib (- n 1)) (fib (- n 2)))))

(time (fib 30))


(defn fib2 [n]
  (loop [xn 1 xnp1 1 num 1]
    (if (= num n)
      xnp1
      (recur xnp1 (+ xn xnp1) (inc num)))))

(time (fib2 30))

