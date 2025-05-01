# 🏎️ 자동차 경주 게임 (Racing Car)

자동차 경주 게임은 여러 대의 자동차가 랜덤한 조건에 따라 전진하며 경주하는 콘솔 애플리케이션입니다. 사용자는 자동차의 이름과 시도 횟수를 입력하고, 게임이 종료되면 가장 멀리 이동한 자동차가 우승자로 선정됩니다.

## 목차
- [기능 요구사항](#기능-요구사항)
- [실행 화면 예시](#실행-화면-예시)
- [프로젝트 구조](#프로젝트-구조)
- [설계 원칙](#설계-원칙)
- [사용된 디자인 패턴](#사용된-디자인-패턴)
- [테스트 전략](#테스트-전략)
- [핵심 클래스 설명](#핵심-클래스-설명)
- [개발 환경](#개발-환경)
- [실행 방법](#실행-방법)

## 기능 요구사항

1. **자동차 이름 입력**
   - 콤마(,)를 기준으로 구분하여 자동차 이름을 입력할 수 있다
   - 자동차 이름은 5자 이하만 가능하다
   - 자동차 이름은 공백일 수 없다

2. **시도 횟수 입력**
   - 자동차 경주 게임의 시도 횟수를 입력할 수 있다
   - 시도 횟수는 양의 정수만 입력 가능하다

3. **자동차 이동 규칙**
   - 0에서 9 사이의 무작위 값을 구한 후 무작위 값이 4 이상일 경우 전진
   - 4 미만일 경우 정지

4. **경주 결과 출력**
   - 각 차수별 실행 결과는 자동차 이름과 이동 거리를 출력
   - 이동 거리는 "-" 문자를 사용하여 표현
   - 자동차 경주 게임을 완료한 후 누가 우승했는지 알려준다
   - 우승자는 한 명 이상일 수 있다

5. **예외 처리**
   - 사용자 입력값에 대한 예외 처리를 수행한다
   - 예외 발생 시 적절한 에러 메시지를 출력하고, 프로그램을 종료한다

## 실행 화면 예시

```
경주할 자동차 이름을 입력하세요(이름은 쉼표(,)로 구분):
pobi,woni,jun

시도할 회수는 몇회인가요?:
5

실행 결과

pobi : -
woni : 
jun : -

pobi : --
woni : -
jun : --

pobi : ---
woni : --
jun : ---

pobi : ----
woni : ---
jun : ----

pobi : -----
woni : ----
jun : -----

최종 결과
pobi : -----
woni : ----
jun : -----

Winners : pobi, jun
```

## 프로젝트 구조

프로젝트는 클린 아키텍처와 MVC 패턴을 적용한 구조로 설계되었습니다.

```
src/
├── main/kotlin/racingcar/
│   ├── Application.kt                # 프로그램 진입점
│   ├── controller/
│   │   └── RacingController.kt       # 게임 흐름 제어
│   ├── domain/
│   │   ├── Car.kt                    # 자동차 엔티티
│   │   ├── Cars.kt                   # 자동차 컬렉션
│   │   ├── MovingStrategy.kt         # 이동 전략 인터페이스
│   │   ├── RandomMovingStrategy.kt   # 랜덤 이동 전략 구현체
│   │   └── RacingGame.kt             # 게임 로직 및 결과 클래스
│   └── view/
│       ├── InputView.kt              # 사용자 입력 처리
│       └── ResultView.kt             # 결과 출력
└── test/kotlin/racingcar/
    ├── ApplicationTest.kt            # 애플리케이션 통합 테스트
    ├── controller/
    │   └── RacingControllerTest.kt   # 컨트롤러 테스트
    ├── domain/
    │   ├── CarTest.kt                # 자동차 단위 테스트
    │   ├── CarsTest.kt               # 자동차 컬렉션 테스트
    │   └── RacingGameTest.kt         # 게임 로직 테스트
    └── view/
        ├── InputViewTest.kt          # 입력 뷰 테스트
        └── ResultViewTest.kt         # 결과 뷰 테스트
```

## 설계 원칙

이 프로젝트는 SOLID 원칙을 준수하여 설계되었습니다:

### 1. 단일 책임 원칙 (SRP, Single Responsibility Principle)
- 각 클래스는 단 하나의 책임만 가집니다
- `Car`: 자동차의 상태와 이동을 담당
- `Cars`: 여러 자동차의 관리를 담당
- `RacingGame`: 게임의 진행 로직을 담당
- `InputView`/`ResultView`: 입출력을 담당

### 2. 개방-폐쇄 원칙 (OCP, Open-Closed Principle)
- `MovingStrategy` 인터페이스를 통해 이동 전략을 확장 가능하게 설계
- 새로운 이동 전략이 필요할 경우 기존 코드 수정 없이 새 구현체 추가 가능

### 3. 리스코프 치환 원칙 (LSP, Liskov Substitution Principle)
- `MovingStrategy` 인터페이스의 모든 구현체는 서로 대체 가능
- 테스트에서는 `RandomMovingStrategy` 대신 커스텀 전략을 사용해도 동작

### 4. 인터페이스 분리 원칙 (ISP, Interface Segregation Principle)
- 이동 전략은 `MovingStrategy` 인터페이스를 통해 단일 책임으로 분리

### 5. 의존성 역전 원칙 (DIP, Dependency Inversion Principle)
- 고수준 모듈(`RacingGame`)은 저수준 모듈(`RandomMovingStrategy`)에 직접 의존하지 않고, 추상화(`MovingStrategy`)에 의존
- 의존성 주입을 통해 결합도를 낮춤

## 사용된 디자인 패턴

### 1. 전략 패턴 (Strategy Pattern)
- `MovingStrategy` 인터페이스와 `RandomMovingStrategy` 구현체를 통해 자동차 이동 전략을 캡슐화
- 런타임에 다양한 이동 전략으로 교체 가능하며, 특히 테스트에서 유용함

### 2. MVC 패턴 (Model-View-Controller)
- **Model**: `Car`, `Cars`, `RacingGame` 등 도메인 클래스
- **View**: `InputView`, `ResultView`
- **Controller**: `RacingController`

### 3. 일급 컬렉션 (First-Class Collection)
- `Cars` 클래스는 자동차 목록을 감싸는 일급 컬렉션으로 구현
- 컬렉션에 대한 비즈니스 로직을 캡슐화하고 불변성 보장

### 4. 객체 불변성 (Object Immutability)
- 가능한 객체의 상태를 불변하게 유지하여 사이드 이펙트 최소화
- `RacingResult`와 같은 데이터 클래스를 통해 결과값 전달

### 5. 정적 팩토리 메서드 (Static Factory Method)
- `Cars.from()`: 문자열 입력으로부터 Cars 객체를 생성하는 팩토리 메서드
- 생성 로직 캡슐화 및 의미 있는 이름 부여

## 테스트 전략

이 프로젝트는 다양한 수준의 테스트를 통해 코드의 품질을 보장합니다:

### 1. 단위 테스트 (Unit Tests)
- 각 도메인 클래스(`Car`, `Cars`, `RacingGame` 등)의 독립적인 기능 검증
- 모킹을 통해 외부 의존성 격리

### 2. 통합 테스트 (Integration Tests)
- `ApplicationTest`: 애플리케이션 전체 흐름 검증
- 실제 사용자 시나리오에 따른 입출력 검증

### 3. 뷰 테스트 (View Tests)
- 콘솔 입출력 리다이렉션을 통한 뷰 컴포넌트 테스트
- 사용자 입력과 출력 형식 검증

### 4. 테스트 대역 활용 (Test Doubles)
- `MovingStrategy` 인터페이스를 활용한 테스트 대역 적용
- 랜덤 요소를 제어 가능한 고정값으로 대체하여 결정적 테스트 수행

### 5. 경계값 테스트 (Boundary Testing)
- 자동차 이름 길이, 빈 입력, 잘못된 형식의 입력 등 경계값 검증
- 예외 상황에 대한 체계적인 테스트 케이스 구성

## 핵심 클래스 설명

### 도메인 (Domain)

#### 1. Car
```kotlin
class Car(val name: String) {
    private var position = 0

    fun move() {
        position++
    }

    fun getPosition(): Int {
        return position
    }

    fun showPosition(): String {
        return "-".repeat(position)
    }

    companion object {
        fun validate(name: String) {
            // 이름 길이 검증 로직
        }
    }
}
```
- 자동차의 기본 엔티티 클래스
- 이름과 현재 위치를 관리하고, 이동 기능 제공
- 정적 메서드를 통한 이름 유효성 검증

#### 2. Cars
```kotlin
class Cars(private val cars: List<Car>) {
    fun moveAll(movingStrategy: MovingStrategy) {
        cars.forEach { car ->
            if (movingStrategy.shouldMove()) {
                car.move()
            }
        }
    }

    fun getAll(): List<Car> {
        return cars.toList()
    }

    fun findWinners(): List<Car> {
        val maxPosition = cars.maxOfOrNull { it.getPosition() } ?: 0
        return cars.filter { it.getPosition() == maxPosition }
    }

    companion object {
        fun from(input: String): Cars {
            // 문자열에서 자동차 목록 생성 로직
        }
    }
}
```
- 자동차 컬렉션을 감싸는 일급 컬렉션
- 모든 자동차의 이동, 우승자 계산 등 그룹 연산 제공
- 팩토리 메서드를 통한 객체 생성

#### 3. MovingStrategy
```kotlin
interface MovingStrategy {
    fun shouldMove(): Boolean
}
```
- 자동차 이동 전략을 정의하는 인터페이스
- 전략 패턴 구현의 핵심

#### 4. RandomMovingStrategy
```kotlin
class RandomMovingStrategy : MovingStrategy {
    override fun shouldMove(): Boolean {
        return Randoms.pickNumberInRange(0, 9) >= MOVING_THRESHOLD
    }
    
    companion object {
        private const val MOVING_THRESHOLD = 4
    }
}
```
- 랜덤 값에 기반한 이동 전략 구현체
- 0-9 범위의 랜덤 값이 4 이상이면 이동

#### 5. RacingGame & RacingResult
```kotlin
class RacingGame(
    private val cars: Cars,
    private val movingStrategy: MovingStrategy
) {
    fun race(attempts: Int): RacingResult {
        repeat(attempts) {
            cars.moveAll(movingStrategy)
        }
        
        return RacingResult(cars.getAll(), cars.findWinners())
    }
}

data class RacingResult(
    val cars: List<Car>,
    val winners: List<Car>
)
```
- 게임의 핵심 로직을 담당하는 클래스
- 이동 전략 주입을 통한 의존성 역전
- 불변 데이터 클래스(RacingResult)를 통한 결과 반환

### 뷰 (View)

#### 1. InputView
```kotlin
object InputView {
    fun readCarNames(): String {
        println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)로 구분):")
        return Console.readLine()
    }
    
    fun readAttempts(): Int {
        println("시도할 회수는 몇회인가요?:")
        val input = Console.readLine()
        
        // 입력값 변환 및 검증
    }
}
```
- 사용자 입력을 담당하는 클래스
- 싱글톤 패턴 적용(object 키워드)

#### 2. ResultView
```kotlin
object ResultView {
    fun printRaceStatus(cars: List<Car>) {
        cars.forEach { printCarStatus(it) }
        println()
    }
    
    fun printRaceResult(result: RacingResult) {
        println("\n최종 결과")
        result.cars.forEach { printCarStatus(it) }
        printWinners(result.winners)
    }
    
    private fun printCarStatus(car: Car) {
        println("${car.name} : ${car.showPosition()}")
    }
    
    private fun printWinners(winners: List<Car>) {
        val winnerNames = winners.joinToString(", ") { it.name }
        println("Winners : $winnerNames")
    }
}
```
- 결과 출력을 담당하는 클래스
- 경주 상태와 최종 결과 출력 기능 제공

### 컨트롤러 (Controller)

#### RacingController
```kotlin
class RacingController {
    fun run() {
        val carNames = InputView.readCarNames()
        val cars = Cars.from(carNames)
        
        val attempts = InputView.readAttempts()
        
        val movingStrategy = RandomMovingStrategy()
        val racingGame = RacingGame(cars, movingStrategy)
        
        println("\n실행 결과")
        val result = racingGame.race(attempts)
        ResultView.printRaceResult(result)
    }
}
```
- 전체 게임 흐름을 제어하는 클래스
- 뷰와 도메인 계층 사이의 중재자 역할

## 개발 환경

- **프로그래밍 언어**: Kotlin 1.9.24
- **빌드 도구**: Gradle 8.7
- **JDK 버전**: JDK 21
- **테스트 프레임워크**: JUnit 5
- **테스트 유틸리티**: camp.nextstep.edu:mission-utils:1.2.0

## 실행 방법

### 1. 프로젝트 빌드
```
./gradlew build
```

### 2. 프로젝트 실행
```
./gradlew run
```

### 3. 테스트 실행
```
./gradlew test
```
