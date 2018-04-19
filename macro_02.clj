; write a simple do-like macro

(defmacro my-do-single [form]
  form)

(my-do-single (printf "Hello"))

(defmacro my-do-single-v2 [form]
  `~form)

(my-do-single-v2 (printf "kdxldkj"))

(do
  (printf "kjjh\n")
  (printf "hello\n"))


(defmacro my-do [& forms]
  `(do ~@forms))

(macroexpand '(my-do (printf "kjjh\n") (printf "hello\n")))

(my-do (printf "kjjh\n") (printf "hello\n"))


; now something more complex. We have a function (taking one arg) and want to execute it with a list of args
(defmacro my-exec [f & vals]
  '(map #(f %)))

(defn f1 [x] (+ 1 x))

(macroexpand '(my-exec f1 [1 2 3]))

