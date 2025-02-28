package com.todo.deoji

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class DeojiApplication

fun main(args: Array<String>) {
	runApplication<DeojiApplication>(*args)
}
