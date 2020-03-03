(ns learn-day3.cache)

(defn create
      ([] (atom {}))
      ([initial] (atom initial))
      )

(defn get [cache key] (@cache key))

(defn put
  ([cache value-map] (swap! cache merge value-map))
  ([cache key value] (swap! cache assoc key value))
)