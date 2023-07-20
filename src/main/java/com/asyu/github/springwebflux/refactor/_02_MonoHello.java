package com.asyu.github.springwebflux.refactor;

import reactor.core.publisher.Mono;

public class _02_MonoHello {

    public static void main(String[] args) {
        Mono.empty()
                .subscribe(
                        none -> System.out.println("# emitted onNExt signal"),  // Publisher가 onNext Signal을 전송하면 실행 (데이터를 전달 받기 위해 사용)
                        error -> {},  // Publisher가 데이터를 전송하는 도중에 에러가 발생할 경우
                        () -> System.out.println("# emitted onComplete signal")  // Publisher가 OnComplete Signal을 전송하면 실행 (Publisher의 데이터 emit이 종료됨)
                );
    }

}
