(defproject log4j-usage "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.apache.logging.log4j/log4j-api "2.9.1"]
                 [org.apache.logging.log4j/log4j-core "2.9.1"]]
  :main ^:skip-aot log4j-usage.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
