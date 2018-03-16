(for [x (range 10)]
  x)

(for [x (range 5)
      :let [y (+ 1 x)]]
      y)

(for [x (range 5)
      :let [y (+ 1 x)]
      :when (even? y)]
      y)


(for [x (range 5)
      y (range 5)
      :when (< (+ x y) 4)]
        [x y])


(for [x (range) :while (< x 10) 
             y (range) :while (<= y x)]
         [x y])

