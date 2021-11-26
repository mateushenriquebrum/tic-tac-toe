(ns tic-tac-toe.ui
  (:require [tic-tac-toe.core :refer :all]
            [clojure.string :as string]))

(defn turn-of [player]
  (str "Player " player " please make your move!"))

(defn board-ui [board]
  (string/join "\n" board))

(defn board-print [message]
  (println message))

(defn keyboard-input []
  (map #(Integer/parseInt %) (string/split (read-line) #" ")))

(defn start-tic-toc-toe [board player position]
  (board-print (board-ui board))
  (board-print (turn-of player))
  (let [winner? (winner-in-board? board)
        cords (position)]
    (if (> winner? 0)
      (board-print (str "Player " winner? " won!"))
      (recur (play-in-board board player cords) (if (= 1 player) 2 1) position))))

;;(start-tic-toc-toe initial-board 1 keyboard-input)

