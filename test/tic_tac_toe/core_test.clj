(ns tic-tac-toe.core-test
  (:require [clojure.test :refer :all]
            [tic-tac-toe.core :refer :all]))

(deftest a-winner
  (testing "There is no winner"
    (is (= 0 (winner? [0 0 0])))
    (is (= 0 (winner? [1 0 2])))
    (is (= 0 (winner? [0 1 2]))))
  (testing "There are winneres"
    (is (= 1 (winner? [1 1 1])))
    (is (= 2 (winner? [2 2 2])))))

(deftest the-crossing
  (let [board [[1 2 3]
               [4 5 6]
               [7 8 9]]
        lines (lines board)]
    (testing "Get crossing lines"
      (is (= (nth lines 0) [1 2 3]))
      (is (= (nth lines 1) [4 5 6]))
      (is (= (nth lines 2) [7 8 9]))
      (is (= (nth lines 3) [1 4 7]))
      (is (= (nth lines 4) [2 5 8]))
      (is (= (nth lines 5) [3 6 9]))
      (is (= (nth lines 6) [1 5 9]))
      (is (= (nth lines 7) [3 5 7])))))

(deftest a-board-winner?
  (testing "Play 1 wins"
    (let [board [[1 1 1]
                 [1 1 2]
                 [2 2 0]]]
      (is (= 1 (winner-in-board? board)))))
  (testing "Play 2 wins"
    (let [board [[2 2 2]
                 [1 1 2]
                 [2 2 0]]]
      (is (= 2 (winner-in-board? board)))))
  (testing "No winners"
    (let [board [[1 2 2]
                 [2 1 1]
                 [2 1 2]]]
      (is (= 0 (winner-in-board? board))))))

(deftest play-and-winner 
  (testing "Sequence of moves resuting in a winner")
  (let [moves [[1 [0 0]] [1 [0 1]] [1 [0 2]] [2 [1 0]] [2 [1 1]]]]
    (is (=  1 (:winner (play initial-board moves))))))