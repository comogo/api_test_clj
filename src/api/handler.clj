(ns api.handler
  (:require [compojure.core :refer :all]
            [compojure.route :refer [not-found]]
            [api.authentication :refer [wrap-token-authentication]]
            [ring.logger :refer [wrap-with-logger]]
            [ring.util.response :refer [response]]
            [ring.middleware.content-type :refer [wrap-content-type]]
            [ring.middleware.default-charset :refer [wrap-default-charset]]
            [ring.middleware.not-modified :refer [wrap-not-modified]]
            [ring.middleware.json :refer [wrap-json-response wrap-json-params]]))

(defroutes app-routes
  (GET "/" [] (response {:message "Hello World"}))
  (not-found "Not Found"))

(def app
  (-> app-routes
    (wrap-with-logger)
    (wrap-token-authentication "x-store-token")
    (wrap-default-charset "utf-8")
    (wrap-json-params)
    (wrap-not-modified)
    (wrap-json-response {:pretty false})
    (wrap-content-type "application/json")))
