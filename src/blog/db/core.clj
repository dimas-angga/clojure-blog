(ns blog.db.core
  (:require [com.ashafa.clutch :as clutch]))

(def cldb ( clutch/get-database "blog") )

(defn get-docs [db cldb-type]
  (let [all-data (map :doc (clutch/all-documents db {:include_docs true}))]
    (filter #(= cldb-type (:cldbType %))
            all-data)))

(defn save-post
  [db data]
  (clutch/put-document db (assoc data :cldbType "blogpost")))

(defn get-post [db]
  (get-docs db "blogpost"))
