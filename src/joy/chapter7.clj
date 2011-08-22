(ns joy.chapter7
  (:require [clojure.contrib [repl-utils :as repl]])
  (:require [clojure.stacktrace :as stacktrace])
  (:require [clojure.pprint :as p]))

(def bearings [{:x 0 :y 1}              ; north
               {:x 1 :y 0}              ; east
               {:x 0 :y -1}             ; south
               {:x -1 :y 0}])           ; west


(defn bot [x y bearing-num]
  {:coords [x y]
   :bearing ([:north :east :south :west] bearing-num)
   :forward (fn []
              (bot (+ x (:x (bearings bearing-num)))
                   (+ y (:y (bearings bearing-num)))
                   bearing-num))
   :turn-right (fn []
                 (bot x y (mod (+ 1 bearing-num) 4)))
   :turn-left (fn []
                (bot x y (mod (- 1 bearing-num) 4)))})


