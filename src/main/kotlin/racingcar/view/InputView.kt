package racingcar.view

import camp.nextstep.edu.missionutils.Console

/**
 * 사용자 입력을 담당하는 뷰 클래스
 * - 테스트에서 예외가 전파되도록 수정
 */
object InputView {
    /**
     * 자동차 이름 입력 받기
     * - 예외 처리를 제거하여 예외가 main까지 전파되도록 함
     */
    fun readCarNames(): String {
        println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)로 구분):")
        return Console.readLine()
    }

    /**
     * 시도 횟수 입력 받기
     * - 예외 처리를 제거하여 예외가 main까지 전파되도록 함
     */
    fun readAttempts(): Int {
        println("시도할 회수는 몇회인가요?:")
        val input = Console.readLine()

        // 숫자 변환 시도 - 변환 실패 시 NumberFormatException이 발생하며 이를 IllegalArgumentException으로 변환
        val attempts = try {
            input.toInt()
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("시도 횟수는 숫자여야 합니다.")
        }

        // 시도 횟수 유효성 검사
        validateAttempts(attempts)

        return attempts
    }

    /**
     * 시도 횟수 유효성 검사
     */
    private fun validateAttempts(attempts: Int) {
        if (attempts <= 0) {
            throw IllegalArgumentException("시도 횟수는 0보다 커야 합니다.")
        }
    }
}