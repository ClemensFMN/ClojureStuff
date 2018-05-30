(def inp-string 
  (clojure.string/split-lines 
    (slurp "/home/cnovak/Desktop/p/src/clojure/ClojureStuff/AoC_2017-P12-data.txt")))


(defn trim-and-parse 
  "takes a string, trims white space and parses it to an integer"
  [s]
  (Integer. (clojure.string/trim s)))


(trim-and-parse " 4 ")

(defn prcs-inp
  "takes a complete filestring and parses it into a map :left :right"
  [s]
  (for [itm s]
    (let [[lft rght] (clojure.string/split itm #"<->") ; split at <->
          rght-split (clojure.string/split rght #",") ; split the right part at ,
          rght-split-prsd (map trim-and-parse rght-split)] ; clean-up the right side
          {:left (trim-and-parse lft) :right rght-split-prsd}))) ; and build a map


(prcs-inp inp-string)



(defn add-lst-to-set
  "takes a set adds a list of elements to it"
  [st lst]
  (reduce conj st lst))

(add-lst-to-set #{0} '(1 2)) ; -> #{0 1 2}

(defn update-lst
  "process one line: take a set and change it "
  [st line-map]
  (println st line-map)
  (let [from-node (line-map :left)
        to-nodes (line-map :right)]
          (if (st from-node)
            (add-lst-to-set st to-nodes)
            st)))

(update-lst #{0} {:left 0 :right '(1 2)}) ; -> #{0 1 2}
(update-lst #{0} {:left 1 :right '(4 2)}) ; -> #{0}


(defn solve-it [s]
  (let [inp (prcs-inp s)]
    (reduce update-lst #{0} inp)))

(solve-it inp-string)



