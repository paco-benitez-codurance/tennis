package tennis



interface TennisScoreCalculator {
    fun score(player1Points: Int, player2Points: Int): String

    companion object {
        fun newInstance() : TennisScoreCalculator = TennisScoreCalculatorImpl()

    }

}