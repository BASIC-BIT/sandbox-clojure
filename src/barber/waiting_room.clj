(ns barber.waiting-room)

(require 'barber.print)
(alias 'print 'barber.print)

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
                                (print/print "Ding!  Customer has sat down in the waiting room.")
                                ))
                           (print/print "Customer has been turned away.")
                           ))

(defn fetchCustomer [room] (if
                             (> (getCurrent room) 0)
                             (do
                               (print/print "Customer is being fetched for haircut")
                               (swap! room assoc :current (- (getCurrent room) 1))
                               true
                               )
                             false
                             ))


