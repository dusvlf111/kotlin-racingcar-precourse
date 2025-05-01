package racingcar.view

import camp.nextstep.edu.missionutils.Console
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class InputViewTest {

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
        // Console 클래스의 입력 스트림 초기화
        Console.close()
    }

    @Test
    fun `자동차 이름을 입력받는다`() {
        // given
        val input = "pobi,woni,jun"
        val inputStream = ByteArrayInputStream(input.toByteArray())
        System.setIn(inputStream)

        // when
        val result = InputView.readCarNames()

        // then
        assertThat(result).isEqualTo("pobi,woni,jun")
        assertThat(outputStream.toString()).contains("경주할 자동차 이름을 입력하세요")
    }

    @Test
    fun `시도 횟수를 입력받는다`() {
        // given
        val input = "5"
        val inputStream = ByteArrayInputStream(input.toByteArray())
        System.setIn(inputStream)

        // when
        val result = InputView.readAttempts()

        // then
        assertThat(result).isEqualTo(5)
        assertThat(outputStream.toString()).contains("시도할 회수는 몇회인가요?")
    }

    @Test
    fun `시도 횟수가 숫자가 아닌 경우 예외가 발생한다`() {
        // given
        val input = "abc"
        val inputStream = ByteArrayInputStream(input.toByteArray())
        System.setIn(inputStream)

        // when & then
        assertThrows<IllegalArgumentException> {
            InputView.readAttempts()
        }
    }

    @Test
    fun `시도 횟수가 0 이하인 경우 예외가 발생한다`() {
        // given
        val input = "0"
        val inputStream = ByteArrayInputStream(input.toByteArray())
        System.setIn(inputStream)

        // when & then
        assertThrows<IllegalArgumentException> {
            InputView.readAttempts()
        }
    }
}