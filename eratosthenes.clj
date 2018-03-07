; taken from https://rosettacode.org/wiki/Sieve_of_Eratosthenes#Clojure
(defn primes< [n]
  (if(<= n 2)
    ()
    (remove (into #{} ; into #{}
                  (mapcat #(range (* % %) n %)) ; -> no collection provided -> this is a transducer
                  (range 3 (Math/sqrt n) 2)) ; 3 5 7 9 ... sqrt(n)
            (cons 2 (range 3 n 2)))))     ; this conses 2 onto 3 5 7 ... n


(into #{} (range 1 5))

(into #{}
     (mapcat #(range (* % %) 30 %)) ; input 3 -> mapcat #(range 9 30 3) input 5 -> mapcat #(range 25 30 5)
     (range 3 (Math/sqrt 30) 2) ; -> 3 5
     )


(mapcat #(range (* % %) 50 %)) ; i think that's a transducer

(range 3 (Math/sqrt 200) 2)


(defn primes-to
  "Computes lazy sequence of prime numbers up to a given number using sieve of Eratosthenes"
  [n]
  (let [root (-> n Math/sqrt long),
        cmpsts (boolean-array (inc n)),
        cullp (fn [p]
                (loop [i (* p p)]
                  (if (<= i n)
                    (do (aset cmpsts i true)
                        (recur (+ i p))))))]
    (do (dorun (map #(cullp %) (filter #(not (aget cmpsts %))
                                       (range 2 (inc root)))))
        (filter #(not (aget cmpsts %)) (range 2 (inc n))))))

(def *ba* (boolean-array 10))
(vec *ba*)
(aset *ba* 3 true)
(aget *ba* 3)
(filter #(not (aget *ba* %)) (range 1 10))