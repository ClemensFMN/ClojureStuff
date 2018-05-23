(def mystring "Hallo Welt, wie geht es Dir?")

(def key-phrase "asdf")



(def key-phrase-rep (flatten (repeat (seq (char-array key-phrase)))))

(def mystring-key-phrase (map vector mystring key-phrase-rep))


(def encrypted-string (map #(bit-xor (int (first %)) (int (second %))) mystring-key-phrase))

(map char encrypted-string)

