package com.microservices.studies;

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.router
import reactor.kotlin.core.publisher.toMono

@Component
public class CustomerRouter {

  @Bean
  fun customerRoutes(): RouterFunction<*> = router {
    "/functional".nest {
      "/customers".nest {
        GET("/") {
          ok().body("hello world".toMono(),
            Customer::class.java)
        }
      }
    }
  }

}
