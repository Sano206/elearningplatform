package bakalarka.elearningplatform.service

import bakalarka.elearningplatform.db.UserRepository
import bakalarka.elearningplatform.model.User
import bakalarka.elearningplatform.request.AddUserRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class UserService(
        var userRepository: UserRepository) {

    fun getAll() = userRepository.findAll().toList()

    fun add(request: AddUserRequest): User {
        val (name, surname, email, password) = request
        return userRepository.save(User(
                name = name,
                surname = surname,
                email = email,
                password = password))
    }

    fun get(id: Long) = userRepository.findById(id)

    fun update(request: AddUserRequest, userId: Long): User? {
        val (name, surname, email, password) = request
        val user = userRepository.findByIdOrNull(userId) ?: throw Exception("User doesn't exist!")
        return userRepository.save(User(
                id = userId,
                name = name,
                surname = surname,
                email = email,
                password = password))
    }
}
