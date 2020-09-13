 (ns ex1.dbclient
  (:import [com.couchbase.client.java Cluster]
           [com.couchbase.client.java Bucket] 
           [com.couchbase.client.java Collection]
              
  ))
;;=>  nil  
    
(def cluster (Cluster/connect "127.0.0.1" "test" "password"))
(def bucket (. cluster bucket "b3"))
(def collection (. bucket defaultCollection))
(. collection upsert "QWERT" "testing going on hrtr")
(def result (. collection get "QWERT"))
(println result)
(. cluster disconnect)




