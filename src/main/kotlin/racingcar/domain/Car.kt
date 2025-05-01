package racingcar.domain

/**
 * 자동차 클래스
 * - 자동차 이름과 위치 정보를 관리한다.
 * - 자동차 이름은 5자 이하여야 한다.
 */
class Car(val name: String) {
    private var position = 0

    /**
     * 전진 기능
     * - 실제 위치를 증가시킨다.
     */
    fun move() {
        position++
    }

    /**
     * 현재 위치 반환
     */
    fun getPosition(): Int {
        return position
    }

    /**
     * 현재 위치를 시각적으로 표현한 문자열 반환
     */
    fun showPosition(): String {
        return "-".repeat(position)
    }

    companion object {
        /**
         * 자동차 이름 유효성 검사
         * @param name 검사할 자동차 이름
         * @throws IllegalArgumentException 이름이 5자를 초과하거나 공백일 경우
         */
        fun validate(name: String) {
            if (name.length > 5) {
                throw IllegalArgumentException("자동차 이름은 5자 이하만 가능합니다.")
            }

            if (name.isBlank()) {
                throw IllegalArgumentException("자동차 이름은 공백일 수 없습니다.")
            }
        }
    }
}