(ns misc.utils)

(defn fac
  [n]
  (loop [result 1
         m n]
    (if (<= (dec m) 1)
      (* m result)
      (recur (* result m) (dec m)))))
