; taken from https://rosettacode.org/wiki/Sieve_of_Eratosthenes#Clojure
(defn primes< [n]
  (if(<= n 2)
    ()
    (remove (into #{}
                  (mapcat #(range (* % %) n %)) ; -> no collection provided -> this is a transducer?
                  (range 3 (Math/sqrt n) 2)) ; 3 5 7 9 ... sqrt(n)
            (cons 2 (range 3 n 2)))))     ; this conses 2 onto 3 5 7 ... n


(into #{}
     (mapcat #(range (* % %) 50 %))
     (range 3 (Math/sqrt 50) 2))


(range 3 (Math/sqrt 200) 2)
