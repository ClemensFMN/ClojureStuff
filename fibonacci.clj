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


(defn -fib-list-1-helper 
  "helper func: take a list and return the sum of the first two elements"
  [acc]
  (+ (first acc) (first (rest acc))))

(-fib-list-1-helper [2 1 1])

(defn fib-list-1 
  "return a list of the first N Fibonacci numbers (N>1)"
  [N]
  (reverse
    (reduce (fn [acc x] ; append to the list using reduce
      (cons (-fib-list-1-helper acc) acc)) ; we calc the next fib number
    [1 1] ; init with a_0=a_1=1
    (range (- N 2))))) ; and use a "dummy sequence" of length N-2 to get the right number of elements
  
  
  (fib-list-1 10)
