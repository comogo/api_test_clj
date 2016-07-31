(ns api.authentication
  (:require [ring.util.response :refer [response status]]))

(def secret-token "7AEpPN8TDR4mvPywJrfQXFOJY5IAPhxthc5Nlp60wWY")

(defn- authenticated? [token]
  (= token secret-token))

(defn wrap-token-authentication [handler header-name]
  (fn [request]
    (let [{{token header-name} :headers} request]
      (if (authenticated? token)
        (handler request)
        (status (response "Unauthorized") 401)))))
