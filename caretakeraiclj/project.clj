(defproject caretakeraiclj "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :java-source-paths ["src/java"]
  :aot [caretakeraiclj.calling-from-java]
  :prep-tasks [
               ["compile" "caretakeraiclj.calling-from-java"]
               "javac" "compile" ]
  :profiles
  {:uberjar {:omit-source true
             :aot :all
             :source-paths ["caretakeraiclj"]}
  } 
  :dependencies [[org.clojure/clojure "1.6.0"]])
