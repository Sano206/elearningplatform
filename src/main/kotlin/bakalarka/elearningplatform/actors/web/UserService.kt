package bakalarka.elearningplatform.actors.web

import bakalarka.elearningplatform.actors.domain.AddUserRequest
import bakalarka.elearningplatform.actors.domain.User
import org.springframework.stereotype.Service



@Service
class UserService(
        var userRepository: UserRepository) {

    //user methods
    fun getAll() = userRepository.findAll().toList()

    fun add(request: AddUserRequest) : User {
        val (name, surname, email, password, numberOfCoursesEnrolled, numberOfCoursesFinished) = request
        return userRepository.save(User(name, surname, email, password, numberOfCoursesEnrolled, numberOfCoursesFinished))
    }

    fun get(id: Long) = userRepository.findById(id)

    fun update(user: User) = userRepository.save(user)



}