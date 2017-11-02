; a simple structure to hold account information; i.e. a mapping between user and value
(def accounts {:user1 10, :user2 70, :user4 50})

; a function which transfers money
(defn transfer [accts src dest val]
  (assoc accts src (- (get accts src) val) dest (+ (get accts dest) val)))

(def acct2 (transfer accounts :user1 :user2 10))


; a function which transfers money - with safety nets
(defn transfer-v2 [accts src dest val]
  {:pre [
         (contains? accounts src) ; check that src exists
         (contains? accounts dest)] ; check that dest exists
   :post [(= (reduce + (vals accts)) (reduce + (vals %)))] ; check that total money 
                                                           ; before = total money after - can be 
                                                           ; checked by "wrong implementation only"; 
                                                           ; e.g.   (assoc accts src (- (get accts src) (* val 2)) dest (+ (get accts dest) val)))
  }
  (assoc accts src (- (get accts src) val) dest (+ (get accts dest) val)))

(def acct3 (transfer-v2 accounts :user1 :user2 10))
; does NOT work
; (def acct4 (transfer-v2 accounts :user1 :user3 10))


; using partial
(defn create-adder [x]
  (partial + x))

(def add-4 (create-adder 4))

(def v1 (add-4 10))

; define a closure as a function which returns a function (fn)
; the fn captures a parameter from outside the fn definition -> clojure
(defn times-n [x]
  (fn [y] (* x y)))

; we can create a function
(def times-4 (times-n 4))

; and use it like this
(def v2 (times-4 10))

; using with filter
(def my-lst (range 1 10))

(defn divisible [x y]
  (zero?(rem x y)))

(defn filter-by-rem [r]
  (filter #(divisible %1 r) my-lst))

(def my-lst-3 (filter-by-rem 3))
