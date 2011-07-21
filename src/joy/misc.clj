(ns joy.misc
  (:require [clojure [string :as string] [pprint :as pprint]]))

(defmacro define-nth
  [n]
  `(def ~(symbol (string/replace (pprint/cl-format nil "~:R" n) #"[^a-z]+" "-"))
     #(nth % ~n :oops)))
