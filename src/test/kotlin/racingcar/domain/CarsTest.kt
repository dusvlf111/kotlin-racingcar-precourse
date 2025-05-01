package racingcar.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CarsTest {
    @Test
    fun `쉼표로 구분된 문자열로부터 자동차 목록을 생성한다`() {
        // when
        val cars = Cars.from("pobi,woni,jun")

        // then
        assertThat(cars.getAll()).hasSize(3)
        assertThat(cars.getAll().map { it.name }).containsExactly("pobi", "woni", "jun")
    }

    @Test
    fun `자동차 이름이 유효하지 않으면 예외가 발생한다`() {
        // when & then
        assertThrows<IllegalArgumentException> {
            Cars.from("pobi,javaji,woni")
        }
    }

    @Test
    fun `이동 전략에 따라 자동차가 움직인다`() {
        // given
        val cars = Cars.from("pobi,woni")
        val alwaysMoveStrategy = object : MovingStrategy {
            override fun shouldMove() = true
        }

        // when
        cars.moveAll(alwaysMoveStrategy)

        // then
        assertThat(cars.getAll().all { it.getPosition() == 1 }).isTrue()
    }

    @Test
    fun `여러 자동차가 같은 위치에 있으면 모두 우승자가 된다`() {
        // given
        val cars = Cars.from("pobi,woni,jun")
        val alternateStrategy = object : MovingStrategy {
            private var toggle = true

            override fun shouldMove(): Boolean {
                toggle = !toggle
                return toggle
            }
        }

        // when
        // pobi와 jun만 이동하고 woni는 이동하지 않는 패턴
        repeat(2) {
            cars.moveAll(alternateStrategy)
        }

        // then
        val winners = cars.findWinners()
        assertThat(winners).hasSize(2)
        assertThat(winners.map { it.name }).containsExactlyInAnyOrder("pobi", "jun")
    }
}