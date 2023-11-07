package aoc2022

import java.io.File

typealias Game = Pair<Day2.MOVE, Day2.MOVE>;

class Day2 {
    private var games: ArrayList<Game> = ArrayList<Game>();

    enum class MOVE {
        ROCK, PAPER, SCISSORS;

        companion object {
            fun getValueFromEncoding(value: String): MOVE? {
                return when(value) {
                    "A","X" -> ROCK;
                    "B","Y" -> PAPER;
                    "C","Z" -> SCISSORS;
                    else -> null;
                }
            }

            fun getScoreForMove(move: MOVE): Int {
                return when(move) {
                    ROCK -> 1;
                    PAPER -> 2;
                    SCISSORS -> 3;
                }
            }
        }
    }

    enum class RESULT {
        WIN, LOSS, TIE;

        companion object {
            fun getResultForEncoding(result: String): RESULT {
                return when(result) {
                    "X" -> LOSS;
                    "Y" -> TIE;
                    "Z" -> WIN;
                    else -> WIN;
                }
            }

            fun getMoveForResult(result: RESULT, move: MOVE): MOVE {
                return when(result) {
                    TIE -> move;
                    LOSS -> {
                        when(move) {
                            MOVE.ROCK -> MOVE.SCISSORS
                            MOVE.PAPER -> MOVE.ROCK
                            MOVE.SCISSORS -> MOVE.PAPER
                        }
                    }
                    WIN -> {
                        when(move) {
                            MOVE.ROCK -> MOVE.PAPER
                            MOVE.PAPER -> MOVE.SCISSORS
                            MOVE.SCISSORS -> MOVE.ROCK
                        }
                    }
                }
            }
        }

    }

    init {
        File("src/main/resources/input/02.txt").useLines() { lines ->
            lines.forEach { l ->
                val moves = l.split(" ")
                val opponentMove = MOVE.getValueFromEncoding(moves[0])!!
                val neededResult = RESULT.getResultForEncoding(moves[1])
                val neededMove = RESULT.getMoveForResult(neededResult, opponentMove)
                val game = Pair(
                    opponentMove,
                    neededMove
               );
                games.add(game)
           }
        }
    }

    private fun getGameResultScore(game: Game): Int {
        return if (game.first == game.second) {
            3
        } else if ((game.second == MOVE.ROCK && game.first == MOVE.SCISSORS) ||
            (game.second == MOVE.SCISSORS && game.first == MOVE.PAPER) ||
            (game.second == MOVE.PAPER && game.first == MOVE.ROCK)) {
            6;
        } else {
            0;
        }
    }

    private fun calculateGameScore(game: Game): Int {
        var score = 0;
        score += MOVE.Companion.getScoreForMove(game.second);
        score += getGameResultScore(game)
        return score;
    }

    fun calculateTotalScore(): Int {
        return games.fold(0) { acc, pair ->
           acc + calculateGameScore(pair);
        }
    }
}