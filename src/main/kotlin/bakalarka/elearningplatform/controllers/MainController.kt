package bakalarka.elearningplatform.controllers

import bakalarka.elearningplatform.actors.domain.User
import bakalarka.elearningplatform.actors.web.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MainController(
        var userService: UserService,
        private val users: MutableList<User> = mutableListOf()
) {

    @GetMapping("/")
    fun index() : String{

        return "hello"
    }




}