package racingcar.domain

/**
 * 레이싱 게임 클래스
 * - 경주 로직을 관리한다.
 */
class RacingGame(
    private val cars: Cars,
    private val movingStrategy: MovingStrategy
) {
    /**
     * 경주 실행
     *
     * @param attempts 시도 횟수
     * @return 최종 경주 결과 (우승자 목록)
     */
    fun race(attempts: Int): RacingResult {
        repeat(attempts) {
            cars.moveAll(movingStrategy)
        }

        return RacingResult(cars.getAll(), cars.findWinners())
    }
}

/**
 * 경주 결과를 담는 데이터 클래스
 */
data class RacingResult(
    val cars: List<Car>,
    val winners: List<Car>
)