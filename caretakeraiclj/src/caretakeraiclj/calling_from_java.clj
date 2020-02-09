(ns caretakeraiclj.calling-from-java)

(gen-class
   :name caretakeraiclj.Test1
   :methods [^:static [hello [String] void]])

(defn- -hello
  [s]
  (println (str "Hello " s)))
