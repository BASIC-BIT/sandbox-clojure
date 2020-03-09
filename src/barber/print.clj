(ns barber.print)

; Setup a "print lock" so that logging statements don't trample each other in threads
; Use a macro to defer execution of anything inside the string (locking starts before anything is calculated)
; Should eventually spin up a thread to perform the logging asynchronously (logging statements should not have side effects)

(def printlock (Object.))
(defmacro print [& args] `(locking printlock (println ~@args)))


(defn lockReducer [expr lockVar] (list 'clojure.core/locking lockVar expr))

(defn lockedOnEvaluator
  [lockVars body]
  (eval (reduce lockReducer body lockVars)))

(defmacro lockedOn [lockVars & body] `(lockedOnEvaluator ~lockVars '(do ~@body)))
;
;
;(lockedOn ['chair, 'room] (print "Hello!"))
;
;(lockedOn [chair, room] (print "Hello!")) -> (locking chair (locking room (print "Hello")))
;
;room => 1
;
;[chair, room] -> ['chair, 'room]
;
;
;
;(def lock 1)
;
;[lock] !-> [1]
;[lock] -> ['lock]
;
;(defn quoteArray [list] (map quote list))

(macrofirst [foo, bar]) -> (quote foo)


(defmacro firstmacro [list] '(first list))