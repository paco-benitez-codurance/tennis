package tennis


class TennisScoreCalculatorImpl: TennisScoreCalculator {
    override fun score(player1Points: Int, player2Points: Int): String {
        if (player1Points == 1)
            return "fifteen all"

        return "love all"
    }
}