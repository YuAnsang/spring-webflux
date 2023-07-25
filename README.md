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

## Reactor

- Spring 진영에서 개발한 리액티브 스트림즈의 구현체
- Java functional API
  - Reactor에서 publisher와 subscriber 간의 상호 작용은 함수형 프로그래밍 API를 통해 이루어짐
- Flux[N]
  - Publisher 타입 중 하나 N개의 데이터를 emit한다.
- Mono[0|1]
  - Publisher 타입 중 하나 0또는 1개의 데이터를 emit
- Well-suited for microservices
- Backpressure ready network
  - Publisher로부터 전달받은 데이터를 처리하는 데 과부하가 걸리지 않도록 제어
  - Publisher로부터 전달 되는 대량의 데이터를 Subscriber가 적절하게 처리하기 위한 제어 방법을 backpressure라고 함.


## Cold Sequence, Hot Sequence
- Cold는 무언가를 새로 시작하고, Hot은 무언가 새로 시작 하지 않는것을 의미함.
- Cold Sequence는 subscribe하는 시점과 상관없이 데이터를 처음부터 다시 전달 받을 수 있음
- Hot Sequence는 구독이 발생한 시점 이전의 Publisher로부터 emit된 데이터는 Subscriber가 전달받지 못하고, 구독이 발생한 시점 이후에 emit된 데이터만 전달 받을 수 있음
- Reactor에서 Hot은 두 가지 의미가 있다고 볼 수 있음
  - 최초 구독이 발생하기 전까지는 데이터의 emit이 발생하지 않음 (warm up)
  - 구독 여부와 상관없이 데이터가 emit되는 것 (hot)
- share(), cache()등의 Operator를 사용해서 Cold Sequence를 Hot Sequence로 변환 가능

## Backpressure
- Publisher로부터 전달받은 데이터를 안정적으로 처리 하도록 사용
- Backpressure 처리 방식
  - BaseSubscriber를 통해서 데이터 요청 개수를 제한 할 수 있음
  - Strategy 
    - IGNORE : Backpressure를 사용하지 않음
    - ERROR : Downstream으로 전달할 데이터가 버퍼에 가득 찰 경우, Exception 발생
    - DROP : Downstream으로 전달할 데이터가 버퍼에 가득 찰 경우, 버퍼 밖에서 대기하는 먼저 emit된 데이터부터 DROP 시키는 전략
    - LATEST : Downstream으로 전달할 데이터가 버퍼에 가득 찰 경우, 버퍼 밖에서 대기하는 가장 나중에 emit된 데이터부터 DROP 시키는 전략 
    - BUFFER : Downstream으로 전달할 데이터가 버퍼에 가득 찰 경우, 버퍼 안에 있는 데이터부터 DROP 시키는 전략

## Sinks
- Processor의 기능을 개선한 Sinks가 Reactor 3.4.0부터 지원
- 기존 Processor 관련된 API는 3.5.0부터 완전히 제거될 예정
- 지금까지는 onNext 같은 signal을 내부적으로 전송해주는 방식이었지만, Sinks는 리액티브 스트림즈의 signal을 프로그래밍적으로 푸시 할 수 있음
- Reactor에서 프로그래밍 방식으로 signal을 전송하는 가장 일반적인 방법은 generate() Operator나 create() Operator 등을 사용하는 것 (Sinks 지원전부터 사용하던 방식)
- Sinks와 Operator를 사용하는 전통적 방식의 차이점
  - generate() Operator나 create() Operator는 싱글스레드 기반
  - Sinks는 멀티스레드 방식으로 signal을 전송해도 thread safety를 보장함.

### Sinks의 종류 및 특징
- Sinks.One
  - Sinks.one() 메서드를 사용하여 "한 건"의 데이터를 전송하는 방법을 정의
  - 아무리 많은 수의 데이터를 emit한다고 하더라도 처음 emit한 데이터를 제외한 나머지 데이터들은 drop 된다.
- Sinks.Many
  - Sinks.many() 메서드를 사용해서 여러 건의 데이터를 여러 가지 방식으로 전송하는 기능을 정의
  - Sinks.many()의 경우 Sinks.Many가 아닌 ManySpec을 리턴한다.
    - One의 경우 단순히 한건의 데이터만 emit하는 한 가지 기능만 가지기 때문에 별도로 Spec을 정의 할 필요가 없음 (DefaultSpec 사용)
  - ManySpec은 Unicast, Multicast, MulticastReplay로 정의되어 있음.
    - Unicast : 단 하나의 Subscriber에게만 데이터를 emit
    - Multicast : 하나 이상의 Subscriber에게 데이터를 emit. 기본적으로 Warm Up 특징을 가지는 Hot Sequence로 동작한다.
    - MulticastReplay : emit된ㄷ ㅔ이터 중에서 특정 시점으로 되돌린(replay) 데이터부터 emit한다.
