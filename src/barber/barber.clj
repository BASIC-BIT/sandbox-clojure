(ns barber.barber)

(require 'barber.waiting-room)
(alias 'waiting-room 'barber.waiting-room)

(require 'barber.time)
(alias 'time 'barber.time)

(require 'barber.print)
(alias 'print 'barber.print)

(defn createChair [] (ref {:filled false :cut-count 0}))

(defn fillChair [chair] (alter chair assoc :filled true))
(defn emptyChair [chair] (alter chair assoc :filled false))

(defn getCutCount [chair] (@chair :cut-count))
(defn cutCount++ [chair] (alter chair assoc :cut-count (+ 1 (getCutCount chair))))

(defn isChairFilled [chair] (@chair :filled))
(defn isChairEmpty [chair] (not (isChairFilled chair)))

(defn canAcceptCustomer [chair waitingRoom] (and (isChairEmpty chair) (time/hasBeenRunningForLessThan10Seconds?) (waiting-room/fetchCustomer waitingRoom)))

(defn startHaircut
      [chair waitingRoom]
      (future
        (loop []
              (if (canAcceptCustomer chair waitingRoom)
                (do
                  (dosync
                    (print/print "Starting haircut")
                    (fillChair chair)
                    )
                  (Thread/sleep 20)
                  (dosync
                    (cutCount++ chair)
                    (print/print "Haircut finished!  Total customer count: " (getCutCount chair) " - Total Waiting: " (waiting-room/getCurrent waitingRoom))
                    (emptyChair chair)
                    )
                  (recur)
                  )
                )
              )))

