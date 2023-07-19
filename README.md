# 리액티브 프로그래밍

## 특징
- 클라이언트의 요청에 즉각적으로 응답함으로써 지연 시간을 최소화한다.
- 메시지 기반 통신
  - 구성 요소들간의 느슨한 결합, 격리성, 위치투명성 보장
- 탄력성
  - 시스템의 작업량이 변화하더라도 일정한 응답을 유지
- 회복성
  - 시스템에 장애가 발생하더라도 응답성을 유지
- 빠른 응답성을 바탕으로 유지보수와 확장이 용이한 시스템을 구축 가능
- 선언형 프로그래밍
  - 동작을 구체적으로 명시하지 않고 이러이러한 동작을 하겠다는 목표만 선언
- data streams & propagation of change
  - 데이터가 지속적으로 발생
  - 지속적으로 데이터가 발생할 때마다 이것을 변화하는 이벤트로 보고 데이터를 계속적으로 전달함

## Reactive Streams
- 데이터 스트림을 Non-Blocking이면서 비동기적인 방식으로 처리하기 위한 리액티브 라이브러리 표준 사양
- Publisher
  - 데이터를 생성하고 발행하는 역할을 함
- Subscriber
  - 구독한 Publisher로부터 데이터를 전달받아서 처리하는 역할
- Subscription
  - Publisher에 요청할 데이터의 개수를 지정하고 구독을 취소하는 역할
- Processor
  - Publisher와 Subscriber의 기능을 모두 가지고 있음.

### 리액티브 스트림즈 관련 용어
- Signal
  - Publisher와 SUbscriber간에 주고 받은 상호작용
  - 리액티브 스트림즈의  인터페이스 코드에서 볼 수 이쓴ㄴ onSubscribe, onNext, onComplete 등을 리액티브 스트림즈에서 Signal이라고 표현함
- Demand
  - Subscriber가 Publisher에게 요청하는 데이터를 의미
  - Publisher가 아직 Subscriber에게 전달하지 않은 Subscriber가 요청한 데이터
- Emit
  - Publisher가 데이터를 전달하는것을 Emit이라고 표현함.
  - "데이터를 내보내다"정도의 의미
- Upstream/Downstream
  - 현재 호출한 메서드에서 반환된 Flux(예시)의 위치에서 자신보다 더 상위에 있는 Flux는 Upstream, 하위에 있는 Flux는 Downstream이 된다.
- Sequence
  - Publisher가 emit하는 데이터의 연속적인 흐름을 정의해 놓은 것
  - Sequence는 Operator 체인 형태로 정의된다.
- Operator
  - 연산자(just, filter, map 등)을 의미함.
  - 리액티브 프로그래밍은 Operator로 시작해서 Operator로 끝난다고 할정도로 리액티브 프로그래밍의 핵심
- Source
  - "최초의"라는 의미로 사용
  - Original이라고도 사용됨
  - 최로에 가장 먼저 생성된 무언가 또는 원본

