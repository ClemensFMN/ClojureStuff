; Project Euler Problem #59 - XOR-"Encryption" with a key-phrase 


(def mystring "Hallo Welt, wie geht es Dir?")

(def key-phrase "asdf")

; repeat the key-phrase indefintely
(defn key-phrase-rep [key-phrase]
  (flatten (repeat (seq (char-array key-phrase)))))

; zip the repeated key-phrase with the string to encrypt -> the repeated key-phrase is trimmed to the length of the string
(defn mystring-key-phrase [s kp]
  (map vector s (key-phrase-rep kp)))

; encryption using xor
(defn encrypt [s kp]
 (map #(bit-xor (int (first %)) (int (second %))) (mystring-key-phrase s kp)))


(encrypt mystring key-phrase)

(map char (encrypt mystring key-phrase))

(map char (encrypt (encrypt mystring key-phrase) key-phrase))

