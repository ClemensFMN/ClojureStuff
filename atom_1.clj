; define an atom
(def a1 (atom {:name "Clemens" :age 34}))

; use it
(@a1 :name)

(swap! a1
       (fn [current-state]
         (merge-with + current-state {:age 4})))

@a1


; short merge-with detour
(let [mp {:a 1}]
  ;  (merge-with + mp {:a 1})) ; modify existing values
  (merge-with + mp {:b 2})) ; or add new key/value pairs

; having fun with update-in
(update-in [1 2 3] [0] inc)
(update-in {:age 10} [:age] + 10)

(swap! a1
       update-in [:age] + 10)

