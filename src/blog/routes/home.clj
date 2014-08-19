(ns blog.routes.home
  (:require [compojure.core :refer :all]
            [blog.layout :as layout]
            [blog.util :as util]
            [noir.response :as resp]
            [blog.db.core :as db]))

(defn home-page []
  (layout/render
    "home.html"
          {:posts (db/get-post db/cldb)}))

(defn about-page []
  (layout/render "about.html"
                 {:post db/get-post}))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/about" [] (about-page))
  (POST "/action-post"
        request
        (let [blog-title (:title (:params request))
              blog-content (:content (:params request))
               ]
          (do
            (db/save-post db/cldb {:title blog-title :content blog-content})
            (resp/redirect "/")))))
