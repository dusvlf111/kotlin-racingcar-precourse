package racingcar.domain

/**
 * 자동차 목록을 관리하는 일급 컬렉션 클래스
 * - 테스트 코드와 호환되도록 수정
 */
class Cars(private val cars: List<Car>) {
    /**
     * 모든 자동차들에게 이동 조건을 검사하고 조건에 맞는 자동차만 이동시킨다.
     *
     * @param movingStrategy 이동 전략 (이동 여부를 결정하는 전략)
     */
    fun moveAll(movingStrategy: MovingStrategy) {
        cars.forEach { car ->
            if (movingStrategy.shouldMove()) {
                car.move()
            }
        }
    }

    /**
     * 모든 자동차 목록 반환
     */
    fun getAll(): List<Car> {
        return cars.toList()
    }

    /**
     * 우승자(들) 찾기 - 가장 멀리 이동한 자동차들
     */
    fun findWinners(): List<Car> {
        val maxPosition = cars.maxOfOrNull { it.getPosition() } ?: 0
        return cars.filter { it.getPosition() == maxPosition }
    }

    companion object {
        /**
         * 입력값으로부터 자동차 생성
         * - 예외를 잡지 않고 상위로 전파하도록 수정
         *
         * @param input 쉼표로 구분된 자동차 이름들
         * @return Cars 객체
         */
        fun from(input: String): Cars {
            val carNames = input.split(",").map { it.trim() }

            // 빈 이름 검사
            carNames.forEach {
                if (it.isBlank()) {
                    throw IllegalArgumentException("자동차 이름은 공백일 수 없습니다.")
                }
            }

            // 유효성 검사
            carNames.forEach { Car.validate(it) }

            // 자동차 생성
            val carList = carNames.map { Car(it) }
            return Cars(carList)
        }
    }
}