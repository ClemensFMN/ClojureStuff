(ns http-client.core
  (:require [clj-http.client :as client]
  	        [cheshire.core :refer :all])
  (:gen-class))

; on the repl do
; (require '[clj-http.client :as client])
; (client/get "http://www.httpbin.org/ip")


(defn -main []
  (let [dta (client/get "http://httpbin.org/ip")]
  	(parse-string "{:request-time 567}")))
  		;(println dta)));(parse-string dta))))

; (parse-string "{\"foo\":\"bar\"}")
