(.toUpperCase "By Bluebeard's bananas!")

(java.lang.Math/abs -3)
(java.lang.Math/sin 1.2)

; use a let to hold a java object
(let [stack (java.util.Stack.)]
  (.push stack "Latest episode of Game of Thrones, ho!")
  (.push stack "dkdklgj")
;  (.empty stack)) ; using java methods requires a dot before the method
;  (.pop stack))
  stack)

; using the doto macro to operate on a java object
(doto (java.util.Stack.)
  (.push "Latest episode of Game of Thrones, ho!")
  (.push "Whoops, I meant 'Land, ho!'")
  (.pop))

(let [my-s (java.lang.String. "Hello")]
      (.length my-s))

; calling a static method
(java.lang.Integer/valueOf "5")
