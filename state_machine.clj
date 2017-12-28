(defn state_progr [old_s inp]
  (cond (and (= old_s :s1) (= inp :i1)) :s2
	(and (= old_s :s1) (= inp :i2)) :s1
        (and (= old_s :s2) (= inp :i1)) :s2
        (and (= old_s :s2) (= inp :i2)) :s2))


(def i-seq [:i1 :i2 :i2])


(def s (reduce state_progr :s1 i-seq))
g
