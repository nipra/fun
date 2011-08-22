(ns web.routes
  (:use [compojure.core])
  (:use [ring.adapter.jetty])
  (:require [compojure.route :as route])
  (:require [compojure.handler :as handler])
  (:require [clj-http.client :as client])
  (:require [ring.middleware.reload :as reload]))


(defroutes routes1
  (GET "/" [] "<h1>Hello World</h1>"))

(defroutes routes2
  (GET "/other" [] (fn [req]
                     (println req)
                     "<h1>Hello World2</h1>")))

(defn middleware1 [app]
  (fn [req]
    (println "MIDDLEWARE3")
    (app req)))


(comment
  (do
    (.stop *server)
    (def *routes (-> (handler/api (routes routes1 routes2))
                     middleware1
                     (reload/wrap-reload '[web.routes])))
    (def *server (run-jetty #'*routes {:port 8080 :join? false}))))
