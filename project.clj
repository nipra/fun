(defproject clj1.2 "1.0.0-SNAPSHOT"
  :description "FIXME: write"
  :dependencies [[org.clojure/clojure "1.2.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [clojureql "1.0.0-beta1-SNAPSHOT"]
                 [clj-time "0.2.0-SNAPSHOT"]
                 [compojure "0.5.2"]
                 [org.mongodb/mongo-java-driver "2.1"]
                 [ring-json-params "0.1.1"]
                 [ring "0.3.1"]
                 [clj-json "0.2.0"]
                 [midje "0.8.0"]
                 [postgresql/postgresql "9.0-801.jdbc4"]]
  :dev-dependencies [[swank-clojure "1.2.1"]
                     [lein-clojars "0.6.0"]
                     [lein-run "1.0.0"]]
  :repositories {"clojars" "http://clojars.org/repo"
                 "clojure-releases" "http://build.clojure.org/releases/"})
