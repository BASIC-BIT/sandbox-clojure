(ns barber.waiting-room)

(defn create
      ([] (atom {:capacity 3 :current 0}))
      ([capacity] (atom {:capacity capacity :current 0}))
      )

(defn getCapacity [room] (@room :capacity))

(defn getCurrent [room] (@room :current))

(defn newCustomer [room] (if
                           (< (getCurrent room) (getCapacity room))
                           (do (
                                (swap! room assoc :current (+ 1 (getCurrent room)))
                                (println "Ding!  Customer has sat down in the waiting room.")
                                ))
                           (println "Customer has been turned away.")
                           ))

(defn fetchCustomer [room] (if
                             (> (getCurrent room) 0)
                             (do
                               (println "Customer is being fetched for haircut")
                               (swap! room assoc :current (- (getCurrent room) 1))
                               true
                               )
                             false
                             ))


