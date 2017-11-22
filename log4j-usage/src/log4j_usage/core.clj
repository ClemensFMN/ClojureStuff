(ns log4j-usage.core
  (:import (org.apache.logging.log4j LogManager Logger) ; the easiest is to imprt to the "righ" level and then list all clases to be imported...
  (:gen-class)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!")
  (let [log (LogManager/getLogger "main")] ; then we can directly use the corresponding class names
    (.error log "An error msg from log4j")))
