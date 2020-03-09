(ns barber.waiting-room)

(require 'barber.print)
(alias 'print 'barber.print)

(defn create
      ([] (ref {:capacity 3 :current 0}))
      ([capacity] (ref {:capacity capacity :current 0}))
      )

(defn getCapacity [room] (@room :capacity))

(defn getCurrent [room] (@room :current))

(defn newCustomer
      [room]
      (dosync (if
        (< (getCurrent room) (getCapacity room))
        (do (
             (alter room assoc :current (+ 1 (getCurrent room)))
             (print/print "Ding!  Customer has sat down in the waiting room.")
             ))
        (print/print "Customer has been turned away.")
        )))

(defn fetchCustomer
      [room]
      (dosync (if
        (> (getCurrent room) 0)
        (do
          (print/print "Customer is being fetched for haircut")
          (alter room assoc :current (- (getCurrent room) 1))
          true
          )
        false
        )))
