package tennis


class TennisScoreCalculatorImpl(private val tennisScoreNumbers: TennisScoreNumbers) : TennisScoreCalculator {
    override fun score(player1Points: Int, player2Points: Int): String {
        return if (tennisScoreNumbers.isEven(player1Points,player2Points))
            humanPoint(player1Points) + " all"
        else
            "${humanPoint(player1Points)} to love"
    }

    private fun humanPoint(player1Points: Int): String {
        return when (player1Points) {
            0 -> "love"
            1 -> "fifteen"
            2 -> "thirty"
            3 -> "forty"
            else -> throw NotValidPointException()
        }
    }
}