(ns barber.core
    (:gen-class))

(require 'barber.waiting-room)
(alias 'waiting-room 'barber.waiting-room)

(require 'barber.barber)
(alias 'barber 'barber.barber)


(defn getCurMs [] (inst-ms (java.util.Date.)))
(defn start [] (def startTime (getCurMs)))
(defn getRuntimeMs [] (- (getCurMs) startTime))
(defn hasBeenRunningFor10Seconds? [] (> (getRuntimeMs) 10000))
(defn hasBeenRunningForLessThan10Seconds? [] (not (hasBeenRunningFor10Seconds?)))

(defn genCustomerLoop [chair waitingRoom] (future (loop [] (do
                                                    (Thread/sleep (+ 10 (rand-int 21)))
                                                    (waiting-room/newCustomer waitingRoom)
                                                    (barber/startHaircut chair waitingRoom)
                                                    (if (hasBeenRunningForLessThan10Seconds?) (recur))
                                                    ))))

(defn -main
      "I don't do a whole lot ... yet."
      [& args]
      (do
        (start)
        (def waitingRoom (waiting-room/create))
        (def chair (barber/createChair))
        (genCustomerLoop chair waitingRoom)
        ()
        ))
