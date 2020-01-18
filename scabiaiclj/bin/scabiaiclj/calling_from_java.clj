(ns scabiaiclj.calling-from-java)

(gen-class
   :name scabiaiclj.Test1
   :methods [^:static [hello [String] void]])

(defn- -hello
  [s]
  (println (str "Hello " s)))
