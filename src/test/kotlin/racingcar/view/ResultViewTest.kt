package racingcar.view

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import racingcar.domain.Car
import racingcar.domain.RacingResult
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class ResultViewTest {

    private val standardOut = System.out
    private val outputStream = ByteArrayOutputStream()

    @BeforeEach
    fun setUp() {
        // 출력 리다이렉션 설정
        System.setOut(PrintStream(outputStream))
    }

    @AfterEach
    fun tearDown() {
        // 표준 출력 복원
        System.setOut(standardOut)
    }

    @Test
    fun `자동차 경주 상태를 출력한다`() {
        // given
        val car1 = Car("pobi")
        val car2 = Car("woni")
        repeat(2) { car1.move() }  // pobi 2칸 이동
        repeat(1) { car2.move() }  // woni 1칸 이동

        val cars = listOf(car1, car2)

        // when
        ResultView.printRaceStatus(cars)

        // then
        val output = outputStream.toString()
        assertThat(output).contains("pobi : --")
        assertThat(output).contains("woni : -")
    }

    @Test
    fun `경주 결과와 우승자를 출력한다`() {
        // given
        val car1 = Car("pobi")
        val car2 = Car("woni")
        val car3 = Car("jun")

        repeat(2) { car1.move() }  // pobi 2칸 이동
        repeat(2) { car3.move() }  // jun 2칸 이동
        repeat(1) { car2.move() }  // woni 1칸 이동

        val allCars = listOf(car1, car2, car3)
        val winners = listOf(car1, car3)  // pobi와 jun이 공동 우승

        val result = RacingResult(allCars, winners)

        // when
        ResultView.printRaceResult(result)

        // then
        val output = outputStream.toString()
        assertThat(output).contains("최종 결과")
        assertThat(output).contains("pobi : --")
        assertThat(output).contains("woni : -")
        assertThat(output).contains("jun : --")
        assertThat(output).contains("Winners : pobi, jun")
    }

    @Test
    fun `단독 우승자를 출력한다`() {
        // given
        val car1 = Car("pobi")
        val car2 = Car("woni")

        repeat(2) { car1.move() }  // pobi 2칸 이동
        repeat(1) { car2.move() }  // woni 1칸 이동

        val allCars = listOf(car1, car2)
        val winners = listOf(car1)  // pobi 단독 우승

        val result = RacingResult(allCars, winners)

        // when
        ResultView.printRaceResult(result)

        // then
        val output = outputStream.toString()
        assertThat(output).contains("Winners : pobi")
        assertThat(output).doesNotContain("Winners : pobi, ")
    }
}