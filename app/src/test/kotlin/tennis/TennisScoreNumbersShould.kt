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

})