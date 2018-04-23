; write a simple do-like macro

; Part 1: 1-element Forms
(defmacro my-do-single [form]
  form)

(my-do-single (printf "Hello"))

(defmacro my-do-single-v2 [form]
  `~form)

(my-do-single-v2 (printf "kdxldkj"))

; an enhanced version which prints the form and executes it afterwards...
(defmacro my-do-single-log [form]
  `(do
    (printf "Form to evaluate: %s%nValue: " '~form)
    ~form))

(macroexpand '(my-do-single-log (+ 1 1)))
(my-do-single-log (+ 1 1))


; Now to Part 2 - multi-element forms
; we want to do something like this:
(do
  (printf "hello")
  (+ 4 5))
; where every form is printed and then executed

; this works, but does not print the expression to execute
(defmacro my-do [& forms]
  `(do ~@forms))
; as can be seen here
(macroexpand '(my-do (printf "kjjh\n") (printf "hello\n")))
; and here
(my-do (printf "kjjh\n") (printf "hello\n"))


; now on to printing the expression and executing it afterwards...
(defmacro my-do-multi-1 [& forms]
  forms)
(macroexpand-1 '(my-do-multi-1 (+ 1 2) (printf "hello\n"))) ; -> ((+ 1 2) (printf "hello\n"))

(defmacro my-do-multi-2 [& forms]
  `(let [elist# ~forms]
    '~elist#))

(macroexpand-1 '(my-do-multi-2 (+ 1 2) (printf "hello\n")))
(my-do-multi-2 (+ 1 2) (printf "hello\n"))


(defmacro my-do-multi-3 [& forms]
  `(let [elist# ~forms]
    (doseq [e# elist#]
      (printf "%s%n" 'e#))))


(macroexpand-1 '(my-do-multi-3 (+ 1 2) (printf "hello\n")))
(my-do-multi-3 (+ 1 2) (printf "hello\n"))



; different approach (provide a vector of expressions) - I have no idea why this does not work...
(defmacro my-do-vec-1 [lst-e]
  `(doseq [e# ~lst-e]
    (printf "%s%n" '~e#)))

(macroexpand-1 '(my-do-vec-1 [(+ 1 2) (printf "hello")]))
(my-do-vec-1 [(+ 1 2) (printf "hello")])

; now something more complex. We have a function (taking one arg) and want to execute it with a list of args

; Mastering Clojure Macros, p.20
(defmacro my-squares [xs]
  `(map #(* % %) ~xs))

(macroexpand '(my-squares [1 2 3]))
(my-squares [1 2 3])

; and on we go
(defmacro my-exec [f & vals]
  `(do
    (map #(~f %) ~vals)))

(defn f1 [x] (+ 1 x))

(macroexpand '(my-exec f1 [1 2 3]))
(my-exec f1 [1 2 3])

