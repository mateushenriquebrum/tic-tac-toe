(ns tic-tac-toe.core
  (:gen-class))

(defn -main [& args])

(def initial-board
  (vec (repeat 3
               (vec (repeat 3 0)))))

(defn line [board cords]
  (vec (map #(get-in board %) cords)))

(defn lines [board]
  (let [cords [[[0 0] [0 1] [0 2]]
               [[1 0] [1 1] [1 2]]
               [[2 0] [2 1] [2 2]]
               [[0 0] [1 0] [2 0]]
               [[0 1] [1 1] [2 1]]
               [[0 2] [1 2] [2 2]]
               [[0 0] [1 1] [2 2]]
               [[0 2] [1 1] [2 0]]]]
    (map (partial line board) cords)))

(defn winner? [marks]
  (let [tries (apply * marks)]
    (cond
      (= tries 1) 1
      (= tries 8) 2
      :else 0)))

(defn winner-in-board? [board]
  (reduce + (map winner? (lines board))))

(defn play-in-board [board player cords]
  (assoc-in board cords player))

(defn play [board moves]
  (let [winner? (winner-in-board? board)
        next-move (first moves)
        player (first next-move)
        cords (second next-move)]
    (if (> winner? 0)
      {:winner winner?
       :board board}
      (recur (play-in-board board player cords) (rest moves)))))
