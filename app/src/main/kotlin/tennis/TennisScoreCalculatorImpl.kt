package tennis


class TennisScoreCalculatorImpl(private val tennisScoreNumbers: TennisScoreNumbers) : TennisScoreCalculator {

    private val isDeuce = Partial.pure(tennisScoreNumbers::isDeuce, "deuce")
    private val isEven = Partial.pure(tennisScoreNumbers::isEven) { p1, _ -> humanPoint(p1) + " all" }
    private val isAdvantagePlayer1 = Partial.pure(tennisScoreNumbers::isAdvantagePlayer1, "advantage player1")
    private val isAdvantagePlayer2 = Partial.pure(tennisScoreNumbers::isAdvantagePlayer2, "advantage player2")
    private val player1Wins = Partial.pure(tennisScoreNumbers::isPlayer1Win, "player1 wins")
    private val player2Wins = Partial.pure(tennisScoreNumbers::isPlayer2Win, "player2 wins")
    private val points = Partial.pure({ _, _ -> true }, { p1, p2 -> "${humanPoint(p1)} to ${humanPoint(p2)}" })


    override fun score(player1Points: Int, player2Points: Int): String {
        val res = isDeuce andThen
                isEven andThen
                isAdvantagePlayer1 andThen
                isAdvantagePlayer2 andThen
                player1Wins andThen
                player2Wins andThen
                points

        if (res.accept(player1Points, player2Points)) return res.format(player1Points, player2Points)
        else throw Exception("Bad algorithm")
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