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


; transducer stuff https://clojure.org/reference/transducers
(def transducer1 (map #(+ % 1)))
(def transducer2 (filter #(< % 5)))

(def transducer-comb (comp transducer1 transducer2)) ;mapping before filtering

(def coll (range 10))

(eduction transducer-comb coll)

(into [] transducer1 coll)
(into [] transducer-comb coll)



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



; new trial with a bit array (more or less a direct translation from julia)
; does not work
(defn primes-v2 [N]
  (let [ba (boolean-array (+ N 1))]
    (aset ba 1 true)
    (loop [i 1]
      (if (aget ba i)
        (let [j (* 2 i)]
          (if (> j N)
          ba
          (loop [j (* 2 i)]
            (aset ba j true)
            (recur (+ j i)))))))))


; this works :-)
(defn p1 [i primes N] ; this function si kick-started by primes-v4 below
  (if (some #(= i %) primes) ; we want to check whether i is in primes; contains? does not work (see clojure help)
      (p1 (inc i) primes N)  ; increment i and start again
      (if (> i N) ; are we finished?
        primes    ; yes -> return primes; actually it's composite numbers
        (p1 (inc i) (concat primes (range (* 2 i) N i)) N)))) ; not finished: start again, increment i and add to primes list

; kickstart for function above
(defn primes-v4 [N]
  (let [comp (into #{} (p1 2 (range 2 N 2) N))]  ; 2 is a prime and primes = 2,4,6,8... N
    (sort (clojure.set/difference (set (range 1 N)) comp))))

(primes-v4 20)


; the latest and greatest...
(defn my-contains? [val lst] ; own function which checks whether val is contained in lst -the std function contains? does NOT work
  (if (some #(= val %) lst)
    true
    false))

(my-contains? 4 '(1 2 3))
(my-contains? 4 '(1 2 3 5 4))

(defn invert-set [N s]  ; remove the values s from (range 1 N)
  (let [composites (set s)]
    (sort (clojure.set/difference (set (range 1 N)) composites))))

(invert-set 10 '(1 3 5))

(defn p-v5 [N]
  (loop [i 2 composites (range 2 N 2)]
    (if (my-contains? i composites) ; if i is in composites...
      (recur (inc i) composites)    ; ... increment i and start again
      (if (> i N) ; are we finished?
        (invert-set N composites)    ; yes -> invert the set to get primes
        (recur (inc i) (concat composites (range (* 2 i) N i))))))) ; not finished: start again, increment i and add to composites list

(p-v5 20)