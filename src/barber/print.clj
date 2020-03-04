(ns barber.print)

; Setup a "print lock" so that logging statements don't trample each other in threads
; Use a macro to defer execution of anything inside the string (locking starts before anything is calculated)
; Should eventually spin up a thread to perform the logging asynchronously (logging statements should not have side effects)

(def printlock (Object.))
(defmacro print [& args] `(future (locking printlock (println ~@args))))


(defn lockReducer [expr lockVar] (list 'clojure.core/locking lockVar expr))

(defn lockedOnEvaluator
  [lockVars body]
  (eval (reduce lockReducer body lockVars)))

(defmacro lockedOn [lockVars & body] `(lockedOnEvaluator ~lockVars '(do ~@body)))