; playing with let
(let [x 2 y 3]
  * x y)

; shadowing works as expected...
(let [x 2]
  (let [x 3]
    x))
