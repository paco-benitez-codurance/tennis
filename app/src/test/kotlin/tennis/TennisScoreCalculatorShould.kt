package tennis

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import io.mockk.mockk

class TennisScoreCalculatorShould : FreeSpec({
    lateinit var tennisScoreCalculator: TennisScoreCalculator
    lateinit var tennisScoreNumbers: TennisScoreNumbers

    beforeContainer {
        tennisScoreNumbers = mockk()
        tennisScoreCalculator = TennisScoreCalculator.newInstance(tennisScoreNumbers)
        every { tennisScoreNumbers.isAdvantagePlayer1(any(), any()) } returns false
    }

    "Invalid Inputs" - {
        "should not allow negative numbers" {
            every { tennisScoreNumbers.isEven(any(), any()) } returns false
            every { tennisScoreNumbers.isDeuce(any(), any()) } returns false
            shouldThrow<NotValidPointException> {
                tennisScoreCalculator.score(-1, 0)
            }
        }
    }

    "return score all when both players having same score below forty" - {
        every { tennisScoreNumbers.isEven(any(), any()) } returns true
        every { tennisScoreNumbers.isDeuce(any(), any()) } returns false
        listOf(0 to "love", 1 to "fifteen", 2 to "thirty")
            .forEach { (score, expected) ->
                "checking score $score" {
                    tennisScoreCalculator.score(score, score) shouldBe "$expected all"
                }
            }
    }

    "return human readable scores when player1 have score distinct below forty and over 0 and player2 have score 0" - {
        every { tennisScoreNumbers.isEven(any(), any()) } returns false
        every { tennisScoreNumbers.isDeuce(any(), any()) } returns false
        listOf(
            (1 to 0) to "fifteen to love",
            (2 to 0) to "thirty to love",
            (3 to 0) to "forty to love",
        ).forEach { (scores, expected) ->
            "$scores should be $expected" {
                tennisScoreCalculator.score(scores.first, scores.second) shouldBe expected
            }
        }
    }

    "method call should be symmetric" - {
        every { tennisScoreNumbers.isEven(any(), any()) } returns false
        every { tennisScoreNumbers.isDeuce(any(), any()) } returns false

        fun reverseOrderOfWords(s: String) = s.split(" ").reversed().joinToString(" ")

        listOf(
            (1 to 0),
            (2 to 0),
            (3 to 0)
        ).forEach { (player1Points, player2Points) ->
            "$player1Points vs $player2Points should be symmetric" {
                tennisScoreCalculator.score(
                    player1Points,
                    player2Points
                ) shouldBe reverseOrderOfWords(tennisScoreCalculator.score(player2Points, player1Points))
            }
        }
    }

    "If at least three points have been scored by each player and the scores are equal, the score is “deuce" - {
        every { tennisScoreNumbers.isEven(any(), any()) } returns true
        every { tennisScoreNumbers.isDeuce(any(), any()) } returns true
        tennisScoreCalculator.score(
            3,
            3
        ) shouldBe "deuce"
    }

    "If at least three points have been scored by each player and a player has one more point than his opponent, the score is “advantage” for the player in the lead." - {
        every { tennisScoreNumbers.isEven(any(), any()) } returns false
        every { tennisScoreNumbers.isDeuce(any(), any()) } returns false
        every { tennisScoreNumbers.isAdvantagePlayer1(any(), any()) } returns true
        tennisScoreCalculator.score(
            3,
            3
        ) shouldBe "advantage player1"
    }

})