package tennis

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class TennisScoreNumbersShould : FreeSpec({
    lateinit var tennisScoreNumbers: TennisScoreNumbers

    beforeEach {
        tennisScoreNumbers = TennisScoreNumbers()
    }

    "IsEvent" - {
        "should be true if both numbers are equal" {
            tennisScoreNumbers.isEven(3, 3) shouldBe true
        }
        "should be false if both numbers are not equal" {
            tennisScoreNumbers.isEven(3, 4) shouldBe false
        }
    }

    "IsDuece" - {
        "should not be if are less than three" {
            tennisScoreNumbers.isDeuce(2, 2) shouldBe false
        }
        "should not be if are not equal" {
            tennisScoreNumbers.isDeuce(4, 5) shouldBe false
        }
        "should true if are equal and more than three" {
            tennisScoreNumbers.isDeuce(4, 4) shouldBe true
            tennisScoreNumbers.isDeuce(5, 5) shouldBe true
        }
    }

    "IsAdvantagePlayer1" - {
        "should be true if more than three for each player" {
            tennisScoreNumbers.isAdvantagePlayer1(5, 4) shouldBe true
        }

        "should be false if less than three for each player" {
            tennisScoreNumbers.isAdvantagePlayer1(3, 2) shouldBe false
        }

        "should be true if player has one more point than his opponent" {
            tennisScoreNumbers.isAdvantagePlayer1(5, 4) shouldBe true
        }

        "should be false if player has two more point than his opponent" {
            tennisScoreNumbers.isAdvantagePlayer1(5, 3) shouldBe false
        }

        "should be false if player has same point than his opponent" {
            tennisScoreNumbers.isAdvantagePlayer1(5, 5) shouldBe false
        }
    }

    "IsAdvantagePlayer2 should be same than IsAdvantagePlayer1" - {
        listOf(
            (5 to 4),
            (3 to 2),
            (5 to 4),
            (5 to 3),
            (5 to 5)
        ).forEach { (player1Points, player2Points) ->
            "$player1Points vs $player2Points should be symmetric" {
                tennisScoreNumbers.isAdvantagePlayer1(
                    player1Points,
                    player2Points
                ) shouldBe tennisScoreNumbers.isAdvantagePlayer2(player2Points, player1Points)
            }
        }
    }


    "isPlayer1Win" - {
        "should be true if first player won at least 4 points" {
            tennisScoreNumbers.isPlayer1Win(4, 0) shouldBe true
        }

        "should be false if first player had not won at least 4 points" {
            tennisScoreNumbers.isPlayer1Win(3, 0) shouldBe false
        }

        "should be true if has two point more than his opponent" {
            tennisScoreNumbers.isPlayer1Win(4, 0) shouldBe true
        }

        "should be false if has less than two point more than his opponent" {
            tennisScoreNumbers.isPlayer1Win(4, 3) shouldBe false
        }

    }

})