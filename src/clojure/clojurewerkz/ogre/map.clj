(ns clojurewerkz.ogre.map
  (:refer-clojure :exclude [map])
  (:import (com.tinkerpop.gremlin.process Traversal)
           (com.tinkerpop.gremlin.process.graph GraphTraversal)
           (com.tinkerpop.gremlin.structure Order))
  (:require [clojurewerkz.ogre.util :refer (f-to-function fs-to-function-array keywords-to-str-array f-to-bifunction typed-traversal)]))

(defn back
  "Goes back to the results of a named step."
  ([^Traversal t step-label] (typed-traversal .back t step-label)))

;; flatMap
;; fold(BiFunction)

(defn fold
  "Collects all objects up to the current step."
  ([^Traversal t] (typed-traversal .fold t)))

(defn id
  "Gets the unique identifier of the element."
  ([^GraphTraversal t] (.id t)))

;; hiddens
;; hiddenmMap
;; hiddenValue
;; hiddenValueMap
;; identity
;; key

(defn label
  "Gets the label of an element."
  ([^Traversal t] (typed-traversal .label t)))

(defn map
  "Gets the property map of an element."
  ([^Traversal t f]
    (typed-traversal .map t (f-to-function f))))

;; match

;; todo: how best to resolve varargs overload to order
(defn order
  "Orders the items in the traversal according to the specified comparator
  or the default order if not specified."
  ([^Traversal t] (order t #(compare %1 %2)))
  ([^Traversal t c] (typed-traversal .order t (into-array [c]))))

;; orderBy
;; otherV

(defn path
  "Gets the path through the traversal up to the current step. If functions are provided
  they are applied round robin to each of the objects in the path."
  [^Traversal t & fns]
    (typed-traversal .path t (fs-to-function-array fns)))

(defn properties
  "Gets the properties of an element."
  ([^Traversal t & keys]
    (typed-traversal .properties t (keywords-to-str-array keys))))

;; propertyMap

;; select overloads

(defn select
  "Get a list of named steps, with optional functions for post processing round robin style."
  ([^Traversal t]
    (select t #(identity %)))
  ([^Traversal t & f]
    (typed-traversal .select t (fs-to-function-array f))))

(defn select-only
  "Select the named steps to emit, with optional functions for post processing round robin style."
  ([^Traversal t cols]
    (select-only t cols identity))
  ([^Traversal t ^java.util.List cols & fs]
    (typed-traversal .select t cols (fs-to-function-array fs))))

;; shuffle
;; to

(defn unfold
  "Unroll all objects in the iterable at the current step."
  ([^Traversal t] (typed-traversal .unfold t)))

;; value
;; valueMap

(defn values
  "Gets the property values of an element."
  ([^Traversal t & keys]
    (typed-traversal .values t (keywords-to-str-array keys))))
