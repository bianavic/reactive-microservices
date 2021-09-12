package com.microservices.studies;

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router
import reactor.kotlin.core.publisher.toMono

@Component
public class CustomerRouter {

  @Bean // /functional/customer/
  fun customerRoutes() = router {
    "/functional".nest {
      "/customer".nest {
        GET("/") {
          ok().body(Customer(1, "functional web").toMono(),
            Customer::class.java)
        }
      }
    }
  }

}
