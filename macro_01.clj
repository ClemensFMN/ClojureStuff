(defn test-func [x] (+ x 1))

(defmacro plus-a-b [a b]
(list '+ a b))

(defmacro vmap [coll f]
`(map ~f ~coll))
