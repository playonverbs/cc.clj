(ns clinical-calculator.calc
  (:require [clojure.data.json :as json]
            [clojure.pprint :as pp]
            [clojure.string :as str]))

(def calc-list (atom []))

;; define an example schema for a calculator.
;; maybe use plumatic/schema or metosin/malli instead.
(def calc-example 
  {:calc-name "bmi"
   :inputs [{:iname :mass
             :idesc "weight in kg"
             :lower 0.0
             :upper 300.0
             :type java.lang.Number}
            {:iname :height
             :idesc "height in meters"
             :lower 0.0
             :upper 2.0
             :type java.lang.Number}]
   :reductor '(fn [mass height] (/ mass (* height height)))})

(defn get-spec [calc]
  (:inputs calc))

(defn chello []
  (println "Hello from calc.clj"))
