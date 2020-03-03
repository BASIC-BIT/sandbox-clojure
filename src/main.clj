;(def characters #{:luke :jar-jar :hansolo})
;(def findperson :hansolo)
;(def findperson :leia)
;(if (not= (characters findperson) nil)
;  (println (str "Found " findperson "!"))
;  (println "Not found, sorry!"))

;
;(def jabba {:name "Jabba", :profession "Gangster"})
;(def hansolo {:name "Han Solo", :profession "Smuggler"})
;(def luke {:name "Luke Skywalker", :profession "Jedi"})
;
;(def people [jabba, hansolo, luke])
;
;(defn
;  introduce
;  "Introduces a person."
;  [person]
;  (let
;    [{name :name profession :profession} person]
;    (println (str "Hello, I'm " name " the " profession))))
;
;(introduce (first people))
;(map introduce people)
;
;(def isOdd? (fn [val] (= (mod val 2) 1)))
;
;(println (str "Is the number 24 odd? " (isOdd? 24)))
;
;(def numbers [1, 2, 5, 27, 42])
;
;(defn big "Takes two arguments - string and number.  Returns true if string is longer than number"
;      [string, checkLength]
;      (> (count string) checkLength))
;
;
;(defn
;  testStringLength
;  [val length]
;  (println (str "Is \"" val "\" longer than " length " characters? " (big val length))))
;
;
;(testStringLength "small" 6)
;(testStringLength "The quick brown fox jumps over the lazy dog" 6)

;(defmacro unless [test body elsebody] (list 'if (list 'not test) body elsebody))
;(println (macroexpand unless))