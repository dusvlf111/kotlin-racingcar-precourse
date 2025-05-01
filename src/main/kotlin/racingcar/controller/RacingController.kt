package racingcar.controller

import racingcar.domain.Cars
import racingcar.domain.RacingGame
import racingcar.domain.RandomMovingStrategy
import racingcar.view.InputView
import racingcar.view.ResultView

/**
 * 레이싱 게임 전체 흐름을 제어하는 컨트롤러 클래스
 * - 테스트 코드와 호환되도록 수정
 */
class RacingController {
    /**
     * 게임 실행
     * - 예외 처리를 제거하여 예외가 main()까지 전파되도록 함
     */
    fun run() {
        // 1. 자동차 이름 입력 받기
        val carNames = InputView.readCarNames()
        val cars = Cars.from(carNames)

        // 2. 시도 횟수 입력 받기
        val attempts = InputView.readAttempts()

        // 3. 게임 생성 및 실행
        val movingStrategy = RandomMovingStrategy()
        val racingGame = RacingGame(cars, movingStrategy)

        // 4. 결과 출력
        println("\n실행 결과")
        val result = racingGame.race(attempts)
        ResultView.printRaceResult(result)
    }
}