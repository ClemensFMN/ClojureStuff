; simepl let binding
(let [x 1]
  (+ x 1))

; two bindings
(let [x 1 y 10]
  (+ x y))

; destructuring in the binding
(let [[x y] [10 20]]
  (+ x y))

; a defined binding can be used in a later one
(let [x 1 y (+ x 10)]
  y)
