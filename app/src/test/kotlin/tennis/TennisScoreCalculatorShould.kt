package tennis

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class TennisScoreCalculatorShould: StringSpec({
    "initial state should be love all" {
        val tennisScoreCalculator: TennisScoreCalculator = TennisScoreCalculator.newInstance()
        tennisScoreCalculator.score(0, 0) shouldBe "love all"
    }
})