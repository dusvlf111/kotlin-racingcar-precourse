package racingcar.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RacingGameTest {
    @Test
    fun `경주는 주어진 횟수만큼 진행된다`() {
        // given
        val cars = Cars.from("pobi,woni")

        // 3번 중 첫 번째만 이동하는 전략
        val onceMovingStrategy = object : MovingStrategy {
            private var count = 0

            override fun shouldMove(): Boolean {
                count++
                return count == 1
            }
        }

        val racingGame = RacingGame(cars, onceMovingStrategy)

        // when
        val result = racingGame.race(3)

        // then
        // 모든 자동차가 1번씩만 이동했으므로 position은 1이 되어야 함
        assertThat(result.cars.all { it.getPosition() == 1 }).isTrue()
    }

    @Test
    fun `경주 결과에는 우승자 정보가 포함된다`() {
        // given
        val cars = Cars.from("pobi,woni,jun")

        // 인덱스가 짝수인 자동차만 이동하는 전략 (pobi, jun만 이동)
        val alternateMovingStrategy = object : MovingStrategy {
            private var index = -1

            override fun shouldMove(): Boolean {
                index = (index + 1) % 3
                return index % 2 == 0
            }
        }

        val racingGame = RacingGame(cars, alternateMovingStrategy)

        // when
        val result = racingGame.race(3) // 3번 반복

        // then
        assertThat(result.winners).hasSize(2)
        assertThat(result.winners.map { it.name }).containsExactlyInAnyOrder("pobi", "jun")
    }
}