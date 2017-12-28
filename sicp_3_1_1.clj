;(def global-bal (atom 100))

;(defn withdraw [amount]
;  (swap! global-bal #(- % amount)))
    
;(withdraw 10)
;@global-bal

(defn make-accumulator [start]
  (let [acc (atom start)]
    (fn [x]
      (swap! acc + x))))
 
(def A (make-accumulator 5))
(A 1)


(defn make-withdraw [balance]
  (let [bal (atom balance)]
    (fn [amount]
      (swap! bal - amount)))) ; be careful with the swap! f & args command: it updates an atom calling f on the curent atom value plus the arguments

(def b1 (make-withdraw 100))
(b1 1)
  

; we create and return a function
(defn make-adder [x]
  #(+ % x)) ; be careful; no ( before the #

(def add-3 (make-adder 3))
(add-3 1)

(defn make-adder-v2 [x]
  (fn [y] ; here we have a ( before the fn
    (+ x y)))

(def add-4 (make-adder-v2 4))
(add-4 1)


(defn make-acc [val]
  (let [acc (atom val)]
    (defn add [amount]
      (swap! acc + amount))
    (defn sub [amount]
      (swap! acc - amount))
    (fn [arg]
      (condp = arg
        'add add
        'sub sub))))

(def b1 (make-acc 100))
((b1 'add) 10)
((b1 'sub) 20)
                
