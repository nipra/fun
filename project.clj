(defproject fun "1.0.0-SNAPSHOT"
  :description "FIXME: write"
  :dependencies [[org.clojure/clojure "1.2.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [clojureql/clojureql "1.0.0"]
                 [clj-time "0.3.0"]
                 [compojure "0.6.4"]
                 [org.mongodb/mongo-java-driver "2.6.3"]
                 [ring-json-params "0.1.1"]
                 [ring "0.3.11"]
                 [clj-json "0.2.0"]
                 [midje "0.8.0"]
                 [postgresql/postgresql "9.0-801.jdbc4"]
                 [org.apache.lucene/lucene-core "3.0.3"]
                 [org.apache.lucene/lucene-highlighter "3.0.3"]
                 [clj-http "0.1.3"]
                 [javax.mail/mail "1.4.1"]]
  :dev-dependencies [[swank-clojure "1.3.2"]
                     [lein-clojars "0.6.0"]
                     [lein-run "1.0.0"]]
  :repositories {"clojars" "http://clojars.org/repo"
                 "clojure-releases" "http://build.clojure.org/releases/"}
  :multi-deps {"lucene2" [[org.clojure/clojure "1.2.1"]
                          [org.apache.lucene/lucene-core "2.9.2"]
                          [org.apache.lucene/lucene-highlighter "2.9.2"]]})

