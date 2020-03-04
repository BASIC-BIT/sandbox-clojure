(ns barber.barber)

(require 'barber.waiting-room)
(alias 'waiting-room 'barber.waiting-room)

(require 'barber.time)
(alias 'time 'barber.time)

(require 'barber.print)
(alias 'print 'barber.print)

(defn createChair [] (atom {:filled false :cut-count 0}))

(defn fillChair [chair] (swap! chair assoc :filled true))
(defn emptyChair [chair] (swap! chair assoc :filled false))

(defn getCutCount [chair] (@chair :cut-count))
(defn cutCount++ [chair] (swap! chair assoc :cut-count (+ 1 (getCutCount chair))))

(defn isChairFilled [chair] (@chair :filled))
(defn isChairEmpty [chair] (not (isChairFilled chair)))

(defn canAcceptCustomer [chair waitingRoom] (and (isChairEmpty chair) (waiting-room/fetchCustomer waitingRoom) (time/hasBeenRunningForLessThan10Seconds?)))

(defn startHaircut
      [chair waitingRoom]
      (future
        (loop []
              (if (canAcceptCustomer chair waitingRoom)
                (do
                  (print/print "Starting haircut")
                  (locking chair
                           (fillChair chair)
                           (Thread/sleep 20)
                           (emptyChair chair)
                           (cutCount++ chair)
                           (print/print "Haircut finished!  Total customer count: " (getCutCount chair) " - Total Waiting: " (waiting-room/getCurrent waitingRoom))
                           )
                  (recur)
                )
              )
)))

