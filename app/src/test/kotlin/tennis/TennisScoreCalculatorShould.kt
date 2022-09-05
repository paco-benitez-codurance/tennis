package tennis

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class TennisScoreCalculatorShould : StringSpec({
    lateinit var tennisScoreCalculator: TennisScoreCalculator
    lateinit var tennisScoreNumbers: TennisScoreNumbers

    beforeEach {
        tennisScoreNumbers = mockk()
        tennisScoreCalculator = TennisScoreCalculator.newInstance(tennisScoreNumbers)
    }

    "should not allow negative numbers" {
        every { tennisScoreNumbers.isEven(any(), any()) } returns false
        shouldThrow<NotValidPointException> {
            tennisScoreCalculator.score(-1, 0)
        }
    }

    "return score all when both players having same score below forty" {
        every { tennisScoreNumbers.isEven(any(), any()) } returns true
        listOf(0 to "love", 1 to "fifteen", 2 to "thirty")
            .map { (score, expected) ->
                tennisScoreCalculator.score(score, score) shouldBe "$expected all"
            }
    }

    "return human readable scores when player1 have score distinct below forty and over 0 and player2 have score 0" {
        every { tennisScoreNumbers.isEven(any(), any()) } returns false
        listOf(
            (1 to 0) to "fifteen to love",
            (2 to 0) to "thirty to love",
            (3 to 0) to "forty to love",
        ).map { (scores, expected) -> tennisScoreCalculator.score(scores.first, scores.second) shouldBe expected }
    }

    "method call should be symmetric" {
        every { tennisScoreNumbers.isEven(any(), any()) } returns false

        fun reverseOrderOfWords(s: String) = s.split(" ").reversed().joinToString(" ")

        listOf(
            (1 to 0),
            (2 to 0),
            (3 to 0)
        ).map { (player1Points, player2Points) ->
            tennisScoreCalculator.score(
                player1Points,
                player2Points
            ) shouldBe reverseOrderOfWords(tennisScoreCalculator.score(player2Points, player1Points))
        }

    }

})