(ns macro-medley.core)

(defmacro selfmapk
  "Self-maps a collection to a hash map of keywords identical
   to the variables or collection of variables at the input"
  ([coll-or-single-var]
   (let [coll (if (coll? coll-or-single-var) coll-or-single-var [coll-or-single-var])]
     (let [ks [(map keyword coll)]]
       `(let [vs# ~coll]
          (zipmap '~@ks vs#)))))
  ([v1 v2 & coll]
   `(selfmapk [~v1 ~v2 ~@coll])))

(defmacro selfmapq
  "Self-maps a collection to a hash map of fully-qualified keywords
   identical to the variables or collection of variables at the input"
  ([coll-or-single-var]
   (let [coll (if (coll? coll-or-single-var) coll-or-single-var [coll-or-single-var])]
     (let [ks [(map #(keyword (str *ns*) (str %)) coll)]]
       `(let [vs# ~coll]
          (zipmap '~@ks vs#)))))
  ([v1 v2 & coll]
   `(selfmapq [~v1 ~v2 ~@coll])))

(defmacro selfmaps
  "Self-maps a collection to a hash map of strings identical
   to the variables or collection of variables at the input"
  ([coll-or-single-var]
   (let [coll (if (coll? coll-or-single-var) coll-or-single-var [coll-or-single-var])]
     (let [ks [(map name coll)]]
       `(let [vs# ~coll]
          (zipmap '~@ks vs#)))))
  ([v1 v2 & coll]
   `(selfmaps [~v1 ~v2 ~@coll])))

(defmacro selfmapb
  "Self-maps a collection to a hash map of symbols keys identical
   to the variables or collection of variables at the input"
  ([coll-or-single-var]
   (let [coll (if (coll? coll-or-single-var) coll-or-single-var [coll-or-single-var])]
     `(zipmap '~coll ~coll)))
  ([v1 v2 & coll]
   `(selfmapb [~v1 ~v2 ~@coll])))

;; As per https://stackoverflow.com/questions/11676120/why-dont-when-let-and-if-let-support-multiple-bindings-by-default
;; by user https://stackoverflow.com/users/2204772/ertu%C4%9Frul-%C3%87etin
(defmacro when-let*
  "Multiple bindings when-let, evaluates only if all conditions are not falsey,
   nil otherwise"
  ([bindings & body]
   (if (seq bindings)
     `(when-let [~(first bindings) ~(second bindings)]
        (when-let* ~(drop 2 bindings) ~@body))
     `(do ~@body))))

;; As per https://stackoverflow.com/questions/11676120/why-dont-when-let-and-if-let-support-multiple-bindings-by-default
;; by user https://stackoverflow.com/users/2204772/ertu%C4%9Frul-%C3%87etin
(defmacro if-let*
  "Multiple bindings when-let, evaluates only if all conditions are not falsey
   or else evaluates else body form, or nil if not present"
  ([bindings then]
   `(if-let* ~bindings ~then nil))
  ([bindings then else]
   (if (seq bindings)
     `(if-let [~(first bindings) ~(second bindings)]
        (if-let* ~(drop 2 bindings) ~then ~else)
        ~(if-not (second bindings) else))
     then)))
