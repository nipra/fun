(ns misc.webapps-mark
  (:require [ring.adapter [jetty :as jetty]]
            ;; [ring.adapter.httpcore]
            [ring.handler [dump :as dump]]
            [ring.middleware [stacktrace :as st] [reload :as reload]]))

(defn app [req]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (str "Hi From " (:server-name req))})

(defn app2 [req]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "o noes"})

#_(def *server* (jetty/run-jetty
                 (-> #'app2
                     (reload/wrap-reload '(misc.webapps-mark))
                     st/wrap-stacktrace)
                 {:port 9083 :join? false}))
