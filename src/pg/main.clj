(ns pg.main
  (:require [clojure.contrib [sql :as sql]]
            [clojure.contrib.sql [internal :as internal]]
            [clojure.pprint :as p])
  (:import [java.sql Connection DriverManager ResultSet SQLException
            Statement ResultSetMetaData]
           [org.postgresql Driver]))


(defn connect-stackoverflow []
  (let [name "jdbc:postgresql://localhost:5432/stackoverflow"]
    (DriverManager/getConnection name "postgres" "postgres")))

(defn connect-test []
  (let [name "jdbc:postgresql://localhost:5432/test"]
    (DriverManager/getConnection name "postgres" "postgres")))

(connect-stackoverflow)
(connect-test)

(def +stackoverflow+ {:classname "org.postgresql.Driver"
                      :subprotocol "postgresql"
                      :subname "//localhost/stackoverflow"
                      :user "postgres"
                      :password "postgres"})

(def +test+ {:classname "org.postgresql.Driver"
             :subprotocol "postgresql"
             :subname "//localhost/test"
             :user "postgres"
             :password "postgres"})
