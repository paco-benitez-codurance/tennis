package tennis

class TennisScoreNumbers {
    fun isEven(point1: Int, point2: Int): Boolean {
        return point1 == point2
    }

    fun isDeuce(point1: Int, point2: Int): Boolean {
        return isEven(point1, point2) && point1 > 3
    }

    fun isAdvantagePlayer1(point1: Int, point2: Int): Boolean {
        return point1 > 3 && point2 > 3 && point1 == point2 + 1
    }

    fun isAdvantagePlayer2(point1: Int, point2: Int): Boolean {
        return isAdvantagePlayer1(point2, point1)
    }

    fun isPlayer1Win(point1: Int, point2: Int): Boolean {
        return point1 >= 4 && point1 - point2 >= 2
    }

    fun isPlayer2Win(point1: Int, point2: Int): Boolean {
        return isPlayer1Win(point2, point1)
    }
}