(ns pg.utils
  (:require [clojure.contrib [sql :as sql]]
            [clojure.contrib.sql [internal :as internal]]
            [clojure.pprint :as p]
            [pg.main :as main]
            [clojureql.core :as cql]
            [clj-time [core :as ctc] [coerce :as ctco] [format :as ctf]])
  (:import [java.sql Connection DriverManager ResultSet SQLException
            Statement ResultSetMetaData]
           [org.postgresql Driver]))

;;; 
(defn get-databases
  [db]
  (sql/with-connection db
    (sql/with-query-results res
      ["SELECT datname FROM pg_database;"]
      (sort (map :datname (vec res))))))

(defn get-databases+
  [db]
  (let [robj (cql/table db :pg_database)
        res @(-> (cql/select robj true)
                 (cql/project #{:datname}))]
    (sort (map :datname res))))

;;; 
(defn server-uptime
  [db]
  (sql/with-connection db
    (sql/with-query-results res
      ["SELECT date_trunc('second', (current_timestamp - pg_postmaster_start_time())) as uptime"]
      (:uptime (first (vec res))))))

;;; 
(defn get-no-of-tables
  [db]
  (sql/with-connection db
    (sql/with-query-results res
      ["SELECT count(*) FROM information_schema.tables
        WHERE table_schema NOT IN ('information_schema', 'pg_catalog')"]
      (vec res))))

(defn get-no-of-tables+
  [db]
  (let [robj (cql/table db :information_schema.tables)]
    @(-> (cql/select robj (cql/where "table_schema NOT IN ('information_schema', 'pg_catalog')"))
         (cql/aggregate [:count/*]))))

(defn get-no-of-tables*
  [db]
  (let [robj (cql/table db :information_schema.tables)]
    @(-> (cql/select robj (cql/where (and (!= :table_schema "information_schema")
                                          (!= :table_schema "pg_catalog"))))
         (cql/aggregate [:count/*]))))

;;;
(defn get-tables+
  [db]
  (let [robj (cql/table db :information_schema.tables)
        res @(-> (cql/select robj (cql/where (and (!= :table_schema "information_schema")
                                                  (!= :table_schema "pg_catalog"))))
                 (cql/project #{:table_name}))]
    (sort (map :table_name res))))

;;;
(defn get-biggest-tables
  [db]
  (sql/with-connection db
    (sql/with-query-results res
      ["SELECT table_name, pg_relation_size(table_name) as size
        FROM information_schema.tables
        WHERE table_schema NOT IN ('information_schema', 'pg_catalog')
        ORDER BY size DESC
        LIMIT 10;"]
      (map (fn [x]
             (update-in x [:size] (fn [y]
                                    (float (/ y 1024 1024)))))
           (vec res)))))
