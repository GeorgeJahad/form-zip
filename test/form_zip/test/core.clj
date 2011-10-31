(ns form-zip.test.core
  (:use [form-zip.core] :reload)
  (:use [clojure.test])
  (:require [clojure.zip :as zip]))

(deftest basic-test
  (is (= {3 4}
         (-> '{1 2 3 4} form-zip  zip/next zip/remove zip/root)))
  
  (is (= clojure.lang.MapEntry
         (-> '{1 2 3 4} form-zip  zip/next zip/node type)))

  (is (= '((defchk a :> integer? [b [c :< map?]] (println "hi") (prn b c))
           defchk a :> integer? [b [c :< map?]] b [c :< map?] c :< map?
           (println "hi") println "hi" (prn b c) prn b c)
       
         (fz-node-seq
          '(defchk a :> integer? [b [c :< map?]] (println "hi") (prn b c))))))
