
(def all-chars (map char (range (int \a) (inc (int \z)))))

(def all-chars-shuffled (shuffle all-chars))

(def encode-map (zipmap all-chars all-chars-shuffled))

(def decode-map (zipmap all-chars-shuffled all-chars))

(def input-string "hello world")

(def output-string (map #(encode-map %1) input-string))

(def input-string-test (map #(decode-map %1) output-string))
