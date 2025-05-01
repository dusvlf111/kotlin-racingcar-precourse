package racingcar.domain

import camp.nextstep.edu.missionutils.Randoms

/**
 * 랜덤 값에 기반한 자동차 이동 전략 구현체
 */
class RandomMovingStrategy : MovingStrategy {
    /**
     * 0-9 사이의 랜덤 값이 4 이상일 경우 이동
     */
    override fun shouldMove(): Boolean {
        return Randoms.pickNumberInRange(0, 9) >= MOVING_THRESHOLD
    }

    companion object {
        // 이동 기준값
        private const val MOVING_THRESHOLD = 4
    }
}