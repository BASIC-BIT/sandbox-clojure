(ns barber.time)

(defn getCurMs [] (inst-ms (java.util.Date.)))
(defn start [] (def startTime (getCurMs)))
(defn getRuntimeMs [] (- (getCurMs) startTime))
(defn hasBeenRunningFor10Seconds? [] (> (getRuntimeMs) 10000))
(defn hasBeenRunningForLessThan10Seconds? [] (not (hasBeenRunningFor10Seconds?)))