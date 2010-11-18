(ns postgres
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

(defn create-fruit
  "Create a table"
  []
  (sql/create-table
   :fruit
   [:name "varchar(32)" "PRIMARY KEY"]
   [:appearance "varchar(32)"]
   [:cost :int]
   [:grade :real]))

(comment
  (sql/with-connection +db+
    (sql/with-query-results res
      ["select count(*) from users"]
      (into [] res)))

  (sql/with-connection +stackoverflow+
    (sql/with-query-results res
      ["SELECT table_name, pg_relation_size(table_name) as size 
                FROM information_schema.tables
                WHERE table_schema NOT IN ('information_schema', 'pg_catalog')
                ORDER BY size DESC
                LIMIT 10;"]
      (into [] res)))

  (sql/with-connection +test+
    (sql/transaction
     (create-fruit)))

  (sql/with-connection +test+
    (sql/transaction
     (sql/insert-records
      :fruit
      {:name "Pomegranate" :appearance "fresh" :cost 585}
      {:name "Kiwifruit" :grade 93})))

  (sql/with-connection +test+
    (sql/with-query-results res
      ["SELECT * FROM fruit"]
      (into [] res))))
