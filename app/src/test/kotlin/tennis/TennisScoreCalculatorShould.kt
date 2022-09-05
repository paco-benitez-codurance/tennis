package tennis

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class TennisScoreCalculatorShould : StringSpec({
    lateinit var tennisScoreCalculator: TennisScoreCalculator

    beforeEach {
        tennisScoreCalculator = TennisScoreCalculator.newInstance()
    }

    "should not allow negative numbers" {
        shouldThrow<NotValidPointException> {
            tennisScoreCalculator.score(-1, 0)
        }
    }

    "return score all when both players having same score below forty" {
        listOf(0 to "love", 1 to "fifteen", 2 to "thirty")
            .map { (score, expected) ->
                tennisScoreCalculator.score(score, score) shouldBe "$expected all"
            }
    }

    "return human readable scores when player1 have score distinct below forty and over 0 and player2 have score 0" {
        listOf(
            (1 to 0) to "fifteen to love",
            (2 to 0) to "thirty to love",
            (3 to 0) to "forty to love",
        ).map { (scores, expected) -> tennisScoreCalculator.score(scores.first, scores.second) shouldBe expected }
    }
})