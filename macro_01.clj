(defn test-func [x] (+ x 1))

(defmacro plus-a-b-v1 [a b]
  (list '+ a b))

(plus-a-b-v1 3 4)

(defmacro plus-a-b-v2 [a b]
  `(+ ~a ~b))

(plus-a-b-v2 3 4)

(defmacro vmap [coll f]
`(map ~f ~coll))

(macroexpand '(vmap [1 2 3] #(+ 1 %) ))

(vmap [1 2 3] #(+ 1 %))




