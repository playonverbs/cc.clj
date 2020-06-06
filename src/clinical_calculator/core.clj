(ns clinical-calculator.core
  (:require [clinical-calculator.calc :as calc]
            [org.httpkit.server :as server]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer :all]
            [clojure.data.json :as json]
            [clojure.pprint :as pp]
            [clojure.string :as str])
  (:gen-class))

(defn simple-body-page
  ([] (simple-body-page {}))
  ([req]
   {:status 200
    :headers {"Content-Type" "text/html"}
    :body "Hello World!"}))

(defn request-example [req]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (->> (pp/pprint req)
              (str "Request Object: " req))})

(defn get-calc [req]
  (let [cval (:calcid (:params req))]
    (println cval))
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (->> (pp/pprint req)
              (str "Request Object: " str))})

(defroutes app-routes
  (GET "/" [] simple-body-page)
  (GET "/request" [] request-example)
  (GET "/calc/:calcid" [calcid] get-calc)

  (route/not-found "Error, page not found!"))

(+ 1 2)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [port 3000]
    (server/run-server
     (wrap-defaults #'app-routes site-defaults)
     {:port port})
    ; Run server w/o ring defaults
    (server/run-server #'app-routes {:port port})
    (println (str "Running webserver at http://localhost:" port)))
  (println "Hello, World!")
  (calc/chello))
