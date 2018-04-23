(defn factorial [n]
  (if (< n 1)
    1
    (* n (factorial (dec n)))))

(factorial 5)

; for this to work, we need to add a dependency to _all_ leiningen projects
; this is done via touching .lein/profiles.clj
(use 'clojure.tools.trace)
; switch on var tracing for factorial globally
(trace-vars factorial)
; and do it!
(factorial 5)

; we can also trace directly
(trace-vars (factorial 7))

; defining a function which traces input / output parameters
(deftrace f1 [n] (+ n 1))
(f1 4)


; basic methods
; stick in a printf
(defn factorial
  [n]
  (printf "called factorial: %d%n" n)
  (if (< n 1)
    1
    (* n (factorial (- n 1)))))


(defn factorial
  [n]
  (printf "called factorial: %d%n" n)
  (if (< n 1)
    1
    (do ; here we need to wrap the next 2 statements in a do
      (printf "continue with n=%s%n" n)
      (* n (factorial (- n 1))))))

(factorial 5)



; taken from loop-1.clj and added debugging printfs...
(defn sum-it [n]
  (loop [i n sum 0] ; create a point with bindings where the recur jumps to
    (printf "i=%s, sum=%s%n" i sum)
    (if (= i 0)
      sum ; break-out
      (recur (dec i) (+ sum i))))) ; jump back and update bindings

(sum-it 5)

; debugging parts of expressions
;  https://stackoverflow.com/questions/2352020/debugging-in-clojure?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
(defmacro dbg[x] 
  `(let [x# ~x]
      (println "dbg:" '~x "=" x#)
      x#))

; look into this
(+ (* 2 3) (* 8 9))
; via this...
(+ (dbg(* 2 3)) (dbg (* 8 9)))

(defn factorial[n] 
  (if (= n 0)
    1 
    (* n (dbg (factorial (dec n))))))

(factorial 8)

(filter #(< % 30) (map #(dbg(* % %)) (range 10)))

