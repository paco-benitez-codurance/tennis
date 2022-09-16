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

})