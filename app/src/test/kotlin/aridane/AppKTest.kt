package aridane

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class AppKTest: StringSpec({
    "App has a Greetings" {
        val classUnderTest = App()
        classUnderTest.greeting shouldBe "Hello World!"
    }
})