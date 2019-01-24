(future (Thread/sleep 4000)
        (println "Hello, I'm back"))

; define a future which sleeps a bit and then provides a value
(let [result (future (Thread/sleep 1000)
                     (println "back")
                     (+ 1 1))]
  (println "realized? " (realized? result)) ; check whether done
  (Thread/sleep 1200) ; wait
  (println "realized? " (realized? result)) ; check again
  (println "deref: " @result)) ; we are done -> deref the future
