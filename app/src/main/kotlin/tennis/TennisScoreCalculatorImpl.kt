package tennis


class TennisScoreCalculatorImpl(private val tennisScoreNumbers: TennisScoreNumbers) : TennisScoreCalculator {
    override fun score(player1Points: Int, player2Points: Int): String {
        return if (tennisScoreNumbers.isDeuce(player1Points, player2Points))
            "deuce"
        else if (tennisScoreNumbers.isEven(player1Points, player2Points))
            humanPoint(player1Points) + " all"
        else if (tennisScoreNumbers.isAdvantagePlayer1(player1Points, player2Points))
            "advantage player1"
        else if (tennisScoreNumbers.isAdvantagePlayer2(player1Points, player2Points))
            "advantage player2"
        else if (tennisScoreNumbers.isPlayer1Win(player1Points, player2Points))
            "player1 wins"
        else if (tennisScoreNumbers.isPlayer2Win(player1Points, player2Points))
            "player2 wins"
        else
            "${humanPoint(player1Points)} to ${humanPoint(player2Points)}"
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