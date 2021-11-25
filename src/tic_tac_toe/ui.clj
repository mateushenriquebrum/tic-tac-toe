(ns tic-tac-toe.ui
  (:require [tic-tac-toe.core :refer :all]
            [clojure.string :as string]))

(def welcome "Welcome to Tic-tac-toe")

(defn turn-of [player]
  (str "Player " player " please make your move!"))

(defn board-ui [board]
  (string/join "\n" board))

(defn read-cord []
  (map #(Integer/parseInt %) (string/split (read-line) #" ")))

(defn start-tic-toc-toe [board player]
  (println (turn-of player))
  (println (board-ui board))
  (let [winner? (winner-in-board? board)
        cords (read-cord)]
    (if (> winner? 0)
      {:winner winner?
       :board board}
      (recur (play-in-board board player cords) (if (= 1 player) 2 1)))))