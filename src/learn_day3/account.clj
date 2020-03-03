(ns learn-day3.account)

(require 'learn-day3.cache)
(alias 'cache 'learn-day3.cache)

(defn create [] (cache/create { :balance 0 :credit 0 :creditLimit 3000 }) )

(defn balance [acct] (cache/get acct :balance))

(defn creditDue [acct] (cache/get acct :credit))

(defn creditLimit [acct] (cache/get acct :creditLimit))

(defn balanceAfterCredit [acct] (- (balance acct) (creditDue acct)))

(defn printBalanceWarning [acct] (if (< (balanceAfterCredit acct) 0) (println "WARNING: You do not have enough money in your account to pay off your credit balance this month.")))

(defn throwIfInsufficientFunds [acct charge] (if (< (balance acct) charge) (throw (Exception. "ERROR: You do not have sufficient funds for that transaction!"))))

(defn throwIfCreditMaximum [acct amnt] (if (> (+ (creditDue acct) amnt) (creditLimit acct)) (throw (Exception. "ERROR: That charge would raise your credit above the maximum!"))))

(defn setBalance [acct, newBalance] (cache/put acct :balance newBalance))

(defn setCreditDue [acct, newCreditDue] (cache/put acct :credit newCreditDue))

(defn setCreditLimit [acct, newCreditLimit] (cache/put acct :creditLimit newCreditLimit))

(defn chargeDebit [acct amnt] (do
  (throwIfInsufficientFunds acct amnt)
  (setBalance acct (- (balance acct) amnt))
  (printBalanceWarning acct)
  @acct
))

(defn chargeCredit [acct amnt] (do
  (throwIfCreditMaximum acct amnt)
  (setCreditDue acct (+ (creditDue acct) amnt))
  (printBalanceWarning acct)
  @acct
))

(defn payOffCredit [acct] (do
  (throwIfInsufficientFunds acct (creditDue acct))
  (setBalance acct (- (balance acct) (creditDue acct)))
  (setCreditDue acct 0)
  @acct
))