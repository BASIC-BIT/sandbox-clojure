(ns barber.core
    (:gen-class))

(require 'barber.waiting-room)
(alias 'waiting-room 'barber.waiting-room)

(require 'barber.barber)
(alias 'barber 'barber.barber)

(require 'barber.time)
(alias 'time 'barber.time)

(defn genCustomerLoop [chair waitingRoom] (future (loop [] (do
                                                    (Thread/sleep (+ 10 (rand-int 21)))
                                                    (waiting-room/newCustomer waitingRoom)
                                                    (barber/startHaircut chair waitingRoom)
                                                    (if (time/hasBeenRunningForLessThan10Seconds?) (recur))
                                                    ))))

(defn -main
      "I don't do a whole lot ... yet."
      [& args]
      (do
        (time/start)
        (def waitingRoom (waiting-room/create))
        (def chair (barber/createChair))
        (await (genCustomerLoop chair waitingRoom))
        ))
