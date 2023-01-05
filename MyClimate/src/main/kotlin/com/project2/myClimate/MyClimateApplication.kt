package com.project2.myClimate

import com.project2.myClimate.dao.HomeRepository
import com.project2.myClimate.dao.SensorRepository
import com.project2.myClimate.dao.UserRepository
import com.project2.myClimate.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MyClimateApplication: CommandLineRunner {

	@Autowired
	lateinit var userRepository: UserRepository
	@Autowired
	lateinit var homeRepository: HomeRepository
	@Autowired
	lateinit var sensorRepository: SensorRepository

	override fun run(vararg args: String?) {
		val userFake = User("user1", "user1")
		userRepository.save(userFake)

	}

}

fun main(args: Array<String>) {
	runApplication<MyClimateApplication>(*args)
}
