package racingcar.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CarTest {
    @Test
    fun `자동차는 move 메서드 호출 시 위치가 증가한다`() {
        // given
        val car = Car("pobi")
        val initialPosition = car.getPosition()

        // when
        car.move()

        // then
        assertThat(car.getPosition()).isEqualTo(initialPosition + 1)
    }

    @Test
    fun `자동차 위치는 대시(-)로 표현된다`() {
        // given
        val car = Car("pobi")

        // when
        repeat(3) { car.move() }

        // then
        assertThat(car.showPosition()).isEqualTo("---")
    }

    @ParameterizedTest
    @ValueSource(strings = ["", " ", "  "])
    fun `자동차 이름이 공백인 경우 예외가 발생한다`(name: String) {
        // when & then
        assertThrows<IllegalArgumentException> {
            Car.validate(name)
        }
    }

    @Test
    fun `자동차 이름이 5자를 초과하는 경우 예외가 발생한다`() {
        // when & then
        assertThrows<IllegalArgumentException> {
            Car.validate("javaji")
        }
    }
}