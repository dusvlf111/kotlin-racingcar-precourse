package racingcar.view

import racingcar.domain.Car
import racingcar.domain.RacingResult

/**
 * 경주 결과 출력을 담당하는 뷰 클래스
 */
object ResultView {
    /**
     * 각 라운드의 경주 상태 출력
     */
    fun printRaceStatus(cars: List<Car>) {
        cars.forEach { printCarStatus(it) }
        println()
    }

    /**
     * 단일 자동차 상태 출력
     */
    private fun printCarStatus(car: Car) {
        println("${car.name} : ${car.showPosition()}")
    }

    /**
     * 최종 경주 결과 출력
     */
    fun printRaceResult(result: RacingResult) {
        println("\n최종 결과")
        result.cars.forEach { printCarStatus(it) }
        printWinners(result.winners)
    }

    /**
     * 우승자 출력
     */
    private fun printWinners(winners: List<Car>) {
        val winnerNames = winners.joinToString(", ") { it.name }
        println("Winners : $winnerNames")
    }
}