package bakalarka.elearningplatform.controller

import bakalarka.elearningplatform.model.User
import bakalarka.elearningplatform.service.UserService
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