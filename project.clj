(defproject api "0.1.0-SNAPSHOT"
  :description "Demo Clojure web API"
  :url "http://example.com/FIXME"
  :min-lein-version "2.6.1"
  :uberjar-name "api-standalone.jar"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [environ "1.0.3"]
                 [compojure "1.5.1"]
                 [ring-logger "0.7.6"]
                 [ring/ring-json "0.4.0"]
                 [ring/ring-headers "0.2.0"]]
  :plugins [[lein-ring "0.9.7"]]
  :ring {:handler api.handler/app}
  :profiles {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                 [ring/ring-mock "0.3.0"]]}
             :production {:env {:production true}}})
