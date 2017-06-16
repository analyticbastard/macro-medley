(ns macro-medley.core-test
  (:require
    [clojure.test :refer :all]
    [macro-medley.core :refer :all]))

(deftest test-selfmap-all-variants
  (let [a 1 b 2 c 3]

    (testing "selfmapk"
      (is (= (selfmapk a) {:a a}))
      (is (= (selfmapk a b) {:a a :b b}))
      (is (= (selfmapk a b c) {:a a :b b :c c}))
      (is (= (selfmapk [a]) {:a a}))
      (is (= (selfmapk [a b]) {:a a :b b}))
      (is (= (selfmapk [a b c]) {:a a :b b :c c})))

    (testing "selfmapq"
      (is (= (selfmapq a) {:macro-medley.core-test/a a}))
      (is (= (selfmapq a b) {:macro-medley.core-test/a a :macro-medley.core-test/b b}))
      (is (= (selfmapq a b c) {:macro-medley.core-test/a a :macro-medley.core-test/b b :macro-medley.core-test/c c}))
      (is (= (selfmapq [a]) {:macro-medley.core-test/a a}))
      (is (= (selfmapq [a b]) {:macro-medley.core-test/a a :macro-medley.core-test/b b}))
      (is (= (selfmapq [a b c]) {:macro-medley.core-test/a a :macro-medley.core-test/b b :macro-medley.core-test/c c})))

    (testing "selfmaps"
      (is (= (selfmaps a) {"a" a}))
      (is (= (selfmaps a b) {"a" a "b" b}))
      (is (= (selfmaps a b c) {"a" a "b" b "c" c}))
      (is (= (selfmaps [a]) {"a" a}))
      (is (= (selfmaps [a b]) {"a" a "b" b}))
      (is (= (selfmaps [a b c]) {"a" a "b" b "c" c})))

    (testing "selfmapb"
      (is (= (selfmapb a) {'a a}))
      (is (= (selfmapb a b) {'a a 'b b}))
      (is (= (selfmapb a b c) {'a a 'b b 'c c}))
      (is (= (selfmapb [a]) {'a a}))
      (is (= (selfmapb [a b]) {'a a 'b b}))
      (is (= (selfmapb [a b c]) {'a a 'b b 'c c})))))

(deftest test-when-let
  (testing "Testing when-let"
    (is (= (when-let* [a 1] a) 1))
    (is (= (when-let* [a 1 b 2] [a b]) [1 2]))
    (is (= (when-let* [a 1 b nil] a) nil))))

(deftest test-if-let
  (testing "Testing when-let"
    (is (= (if-let* [a 1] a -1) 1))
    (is (= (if-let* [a 1 b 2] [a b] -1) [1 2]))
    (is (= (if-let* [a 1 b nil] a -1) -1))
    (is (= (if-let* [a 1 b nil] a) nil))))
