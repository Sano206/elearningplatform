package bakalarka.elearningplatform.service

import bakalarka.elearningplatform.db.UserRepository
import bakalarka.elearningplatform.model.Course
import bakalarka.elearningplatform.model.User
import bakalarka.elearningplatform.request.AddUserRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URISyntaxException


@Service
class UserService(
        var userRepository: UserRepository) {

    //user methods
    fun getAll() = userRepository.findAll().toList()

    fun add(request: AddUserRequest) : User {
        val (name, surname, email, password) = request
        return userRepository.save(User(
                name = name,
                surname = surname,
                email = email,
                password = password))
    }



    fun get(id: Long) = userRepository.findById(id)

    fun update(request: AddUserRequest, userId: Long): User? {
        val(name, surname, email, password) = request
        val user = userRepository.findByIdOrNull(userId) ?: return null
        return userRepository.save(User(
                id = userId,
                name = name,
                surname = surname,
                email = email,
                password = password))
    }



}