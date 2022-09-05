package tennis

import tennis.TennisScoreCalculator

private class TennisScoreCalculatorImpl: TennisScoreCalculator {
    override fun score(player1Points: Int, player2Points: Int): String {
        return "love all"
    }
}