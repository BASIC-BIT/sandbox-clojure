(ns learn-day3.agent)

(defn twice [x] (* 2 x))
(defn slow-twice [x] (do (Thread/sleep 5000) (twice x)))

(def tribbles (agent 1))

