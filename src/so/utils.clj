(ns so.utils
  (:require [clojure.contrib [sql :as sql]]
            [clojure.contrib.sql [internal :as internal]]
            [clojure.pprint :as p]
            [postgres :as pg]
            [clojureql.core :as cql])
  (:import [java.sql Connection DriverManager ResultSet SQLException
            Statement ResultSetMetaData]
           [org.postgresql Driver]))

