(ns form-zip.core
  (:require [clojure.zip :as zip])
  (:import clojure.lang.MapEntry))

(defn fz-branch? [node]
  (or (map? node)
      (seq? node)
      (set? node)
      (vector? node)
      (= MapEntry (type node))))

(defn fz-children [branch]
 (cond
   (map? branch) (seq branch)
   (set? branch) (seq branch)
   (vector? branch) (seq branch)
   (= MapEntry (type branch))
   (seq branch)
   :else branch))

(defn fz-make-node [node children]
  (let [new-children
        (cond
         (map? node) (into {} children)
         (set? node) (into #{} children)
         (vector? node) (into [] children)
         (= MapEntry (type node))
         (MapEntry. (first children) (second children))
         :else children)]
    (with-meta new-children (meta node))))

(defn form-zip [root]
  (zip/zipper fz-branch?
              fz-children
              fz-make-node
              root))

(def get-node first)
(def get-path second)

(defn- not-end-loc-seq [loc]
  (not= :end (get-path loc)))

(defn fz-loc-seq [form]
  (let [loc-seq (iterate zip/next (form-zip form))]
    (->> loc-seq (take-while not-end-loc-seq))))

(defn fz-node-seq [form]
   (map zip/node (fz-loc-seq form)))
