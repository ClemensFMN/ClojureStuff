; Interop with Java

;; probably the simplest task is to call static methods
(.toUpperCase "By Bluebeard's bananas!")

(java.lang.Math/abs -3)
(java.lang.Math/sin 1.2)
(java.lang.Integer/valueOf "5")


;; create a new class and use it afterwards (seems that java.util stuff is already on the classpath and can be used
(def rnd (new java.util.Random))
(. rnd nextInt)

;; we can pack the whole stuff into a let and execute it in a do block
(let [rnd (new java.util.Random)]
  (do
    (println (. rnd nextInt))
    (println (. rnd nextInt))))

;; use a let to hold a java object
(let [stack (java.util.Stack.)]
  (do
    (.push stack "Latest episode of Game of Thrones, ho!")
    (.push stack "dkdklgj"))
    stack) ; return stack

(let [ar (java.util.ArrayList.)]
  (do
    (.add ar 3)
    (.add ar 5)
    (println (.contains ar 3)))
  ar)


;; using the doto macro: It creates an (anonymous) java object, executes all following calls in a do environment on this object, and returnx the object
(doto (java.util.Stack.)
  (.push "Latest episode of Game of Thrones, ho!")
  (.push "Whoops, I meant 'Land, ho!'")
  (.pop))

(doto (java.util.ArrayList.)
  (.add 3)
  (.add 5))




