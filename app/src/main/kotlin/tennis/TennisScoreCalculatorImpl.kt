package tennis


class TennisScoreCalculatorImpl: TennisScoreCalculator {
    override fun score(player1Points: Int, player2Points: Int): String {
        return humanPoint(player1Points) + " all"
    }

    private fun humanPoint(player1Points: Int): String {
        return when (player1Points) {
            0 -> "love"
            1 -> "fifteen"
            2 -> "thirty"
            else -> throw NotValidPointException()
        }
    }
}