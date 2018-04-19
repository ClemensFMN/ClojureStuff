(= (+ 1 2) 3)


; the most simple version - but tedious to maintain...
(defn test-1 []
  (printf "%s: %s %n" (= 3 (+ 1 2)) '(= 3 (+ 1 2))))

; factoring out the stuff
(defn report-result [result form]
  (printf "%s: %s %n" result form))

; moves the tedious duplication somewhere else, but does not help otherwise
(report-result (= 3 (+ 1 2)) '(= 3 (+ 1 2)))

; let's define a macro which handels the duplication
(defmacro check [form]
  `(report-result ~form '~form))

; test via macroexpansion
(macroexpand '(check (= 3 (+ 1 2))))

; and in real-life
(check (= 3 (+ 1 2)))

; it's not so nice that we need to repeat check for every test...
(do
  (check (= 3 (+ 1 2)))
  (check (= 6 (* 2 3))))

; try to provide a list of expressions to test and call report-result for each of them
; does not work...
(defmacro check-v2 [& forms]
  `(do ~@(map #(apply report-result ~% '~%))))

(macroexpand '(check-v2 (= 3 (+1 2 )) (= 6 (* 2 3))))

(check-v2 (= 3 (+1 2 )) (= 6 (* 2 3)))


