(ns blog.session
  (require [noir.session :as session]
           [noir.cookies :as cookies]))



(defn set-user [id]
  (session/put! :user id)
  (session/get :user))

(defn remove-user []
  (session/remove! :user)
  (session/get :user))

(defn set-user-if-nil [id]
  (session/get :user id))


(defn clear-session []
  (session/clear!))

(defroutes app-routes
           (GET "/login/:id" [id] (set-user id))
           (GET "/remove" [] (remove-user))
           (GET "/set-if-nil/:id" [id] (set-user-if-nil id))
           (GET "/logout" [] (clear-session)))

(defn set-user-cookie [id]
  (cookies/put! :user id)
  (cookies/get :user))

(defn set-user-if-nil [id]
  (cookies/get :user id))

(cookies/put! :track
              {:value (str (java.util.UUID/randomUUID))
               :path "/"
               :expires "1"})
