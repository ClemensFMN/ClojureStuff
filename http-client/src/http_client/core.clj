(ns http-client.core
  (:require [clj-http.client :as client]
  	        [cheshire.core :refer :all])
  (:gen-class))


; if a lein repl is executed, we seem to be in the http-client.core namespace
; and can execute commands (including the ones from the imported libraries)
; printing the current namespace works via (ns-name *ns*) , *ns* denotes the current namespace
; changing namespaces works via (ns namespace):
; (ns user) -> here we cannot call (client/get "http://httpbin.org/ip") because the lib is not importe din this namespace
; (ns http-client.core) -> here we can call (client/get ...) because we import the lib into this namespace
; when we edit the file, we can reload it into the REPL via
; (use 'http-client.core :reload) or (use 'http-client.core :reload-all)

(defn f1 [x] (+ x 2))


(defn -main []
  (let [dta (:body (client/get "http://httpbin.org/ip"))
        prsd (parse-string dta true)]
  	(do
      (println prsd)
      (get prsd :origin))))

; (println (parse-string "{\"age\":45}" true))
; (get (parse-string "{\"age\":45, \"name\":\"dkdjf\", \"lst\":[1,3,4]}" true) :lst)