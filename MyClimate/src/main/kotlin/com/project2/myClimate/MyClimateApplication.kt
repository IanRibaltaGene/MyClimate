package com.project2.myClimate

import com.project2.myClimate.dao.HomeRepository
import com.project2.myClimate.dao.UserRepository
import com.project2.myClimate.model.Home
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

	override fun run(vararg args: String?) {
		val userFake = User("user", "user")

		userRepository.save(userFake)

		val homeFake = Home("home", "John Doe st. num. 1", "first home", userFake)

		homeRepository.save(homeFake)


	}

}

fun main(args: Array<String>) {
	runApplication<MyClimateApplication>(*args)
}
