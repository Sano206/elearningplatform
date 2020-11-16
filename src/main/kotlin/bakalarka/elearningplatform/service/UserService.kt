package bakalarka.elearningplatform.service

import bakalarka.elearningplatform.request.AddUserRequest
import bakalarka.elearningplatform.model.User
import bakalarka.elearningplatform.db.UserRepository
import org.springframework.stereotype.Service



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

    fun update(user: User) = userRepository.save(user)



}