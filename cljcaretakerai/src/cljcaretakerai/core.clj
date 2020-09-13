(ns cljcaretakerai.core
  (:require [clojure.string :as str]))

(defn -main 
  "I don't do a whole lot."
  [& args]
  (def str1 "This is my first string")
  (str/blank? str1)
  (println "Hello, World, how are you?"))
;; => #'ex1.core/-main

(-main)
