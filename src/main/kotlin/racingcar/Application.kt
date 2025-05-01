package racingcar

import racingcar.controller.RacingController

/**
 * 레이싱 카 애플리케이션의 진입점
 * - 테스트 코드와 호환되도록 수정
 */
fun main() {
    // try-catch 블록을 제거하여 예외가 테스트로 전파되도록 함
    val controller = RacingController()
    controller.run()
}