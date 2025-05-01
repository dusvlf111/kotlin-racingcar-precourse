package racingcar.domain

/**
 * 자동차 이동 전략을 정의하는 인터페이스
 * - 전략 패턴을 통해 이동 조건 결정 방식을 유연하게 변경할 수 있다.
 * - 테스트 시 랜덤값 대신 고정값으로 대체 가능하다.
 */
interface MovingStrategy {
    /**
     * 자동차 이동 여부 결정
     *
     * @return 이동 여부 (true: 이동, false: 정지)
     */
    fun shouldMove(): Boolean
}