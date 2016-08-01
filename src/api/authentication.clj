(ns api.authentication
  (:require [ring.util.response :refer [response status]]
            [environ.core :refer [env]]))

(def secret-token
  (env :secret-token))

(defn- authenticated? [token]
  (= token secret-token))

(defn wrap-token-authentication [handler header-name]
  (fn [request]
    (let [{{token header-name} :headers} request]
      (if (authenticated? token)
        (handler request)
        (status (response "Unauthorized") 401)))))
