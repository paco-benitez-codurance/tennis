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

    "both players having same score below forty should return score all" {
        listOf(0 to "love", 1 to "fifteen", 2 to "thirty")
            .map { (score, expected) ->
                tennisScoreCalculator.score(score, score) shouldBe "$expected all"
            }
    }


})