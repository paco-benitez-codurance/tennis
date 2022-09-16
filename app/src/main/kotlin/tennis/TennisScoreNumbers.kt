package tennis

class TennisScoreNumbers {
    fun isEven(point1: Int, point2: Int): Boolean {
        return point1 == point2
    }

    fun isDeuce(point1: Int, point2: Int): Boolean {
        return isEven(point1, point2) && point1 > 3
    }

    fun isAdvantagePlayer1(point1: Int, point2: Int): Boolean {
        TODO("Not yet implemented")
    }
}
