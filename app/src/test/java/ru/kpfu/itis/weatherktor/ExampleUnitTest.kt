package ru.kpfu.itis.weatherktor

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest : BehaviorSpec({
    Given("one and two") {
        When("adding numbers") {
            Then("result should be 3") {
                1 + 2 shouldBe 5
            }
        }
    }
})
