; we want to generate a letter counting function
; main function is count-letters
; (count-letters "susi")
; the thing is based on the idea in https://clojuredocs.org/clojure.core/reduce

(defn add-to-map [mp x]
"Takes a map and a key; if the map contains the key, the value 
is incremented, otherwise the key with value 1 is added to the
map" 
  (cond
   (contains? mp x)  (assoc mp x (inc (mp x)))
   :else (assoc mp x 1)))

; this is simpler but tricky :-)
; having a map mp, we can perform a lookup like a function call
; (define mp {:a 1 :b 2})
; mp :a --> 1
; mp :c --> nil
; asking for a non-existing key returns nil
; but: we can provide a "default" return value (when the key is not found)
; mp :c 0 --> 0
; here we lookup x in mp; if it's not found, we return 0 (the letter has not yet occured
; we increment the lookup value
; and return a new association
; with the updated value
(defn add-to-map-v2 [mp x]
  (assoc mp x (inc (mp x 0))))

; we reduce over s and start with an empty map
; the function takes the map and the current item
; and updates the letter count

(defn count-letters [s]
"Takes a seq of items an counts their occurence frequency"
  (reduce add-to-map-v2 {} s))

; ==============================================================================
; having fun with some database-like stuff...

(def orders
  [{:product "The Joy of Clojure", :customer "Mr Smith", :qty 4, :price 50}
   {:product "Kindle", :customer "Clemens", :qty 1, :price 70}
   {:product "Notebook", :customer "Susi", :qty 1, :price 1000}
   {:product "Macbook", :customer "Susi", :qty 2, :price 1200}
   {:product "USB Stick, 1GB", :customer "Clemens", :qty 2, :price 8}])


; test with "grp-customer" on the REPL
(def grp-customer (group-by :customer orders))

(def grp-aggr-customer 
  (into {} (for [[k v] (group-by :customer orders)]
             [k (count v)])))

; sum :qty values of one customer
(defn sum-qty [x]
  (reduce + (map :qty x)))

; this can become arbitrarily complex -> here we iterate over the orders per customer
; and calculate price x quantity which we sum over all orders (per customer)
(defn total-price [x]
  (reduce + (map #(* (%1 :qty) (%1 :price)) x)))

(def sum-qty-customer (into {} (for[[k v] grp-customer] [k (sum-qty v)])))

(def sum-price-customer (into {} (for[[k v] grp-customer] [k (total-price v)])))



; the whole story a bit simpler
; consider a list of maps
(def list-of-maps [{:a 4, :b 10}, {:a 1, :b 7}, {:a 3, :b 30}])

; we can map over the list items - %1 is now one map -> %1 :a retrieves the value belonging to key :a
; in the map we ca
(def mpd (map #(* (%1 :a) (%1 :b)) list-of-maps))
(def aggrgt (reduce + mpd))
