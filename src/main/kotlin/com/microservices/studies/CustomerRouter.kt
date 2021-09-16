package com.microservices.studies

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

/**
 * ROUTERS handle the paths and verbs that are reactive service will answer
 */

@Component
class CustomerRouter(private val customerHandler: CustomerHandler) {

  @Bean
  fun customerRoutes() = router {
    "/functional".nest {
      "/customers".nest {
        GET("/{id}", customerHandler::get)
        POST("/", customerHandler::get)
      }
      "/customers".nest {
        GET("/", customerHandler::get)
      }
    }
  }

}