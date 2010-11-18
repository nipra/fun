(ns pg.utils
  (:require [clojure.contrib [sql :as sql]]
            [clojure.contrib.sql [internal :as internal]]
            [clojure.pprint :as p]
            [pg.main :as main]
            [clojureql.core :as cql])
  (:import [java.sql Connection DriverManager ResultSet SQLException
            Statement ResultSetMetaData]
           [org.postgresql Driver]))


(defn get-databases
  [db]
  (sql/with-connection db
    (sql/with-query-results res
      ["select datname from pg_database;"]
      (sort (map :datname (vec res))))))

(defn get-databases+
  [db]
  (let [cur (cql/table db :pg_database)
        res @(-> (cql/select cur true)
                 (cql/project #{:datname}))]
    (sort (map :datname res))))
