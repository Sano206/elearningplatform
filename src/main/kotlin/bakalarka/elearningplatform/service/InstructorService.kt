package bakalarka.elearningplatform.service

import bakalarka.elearningplatform.db.InstructorRepository
import bakalarka.elearningplatform.db.UserRepository
import bakalarka.elearningplatform.model.Instructor
import bakalarka.elearningplatform.model.User
import bakalarka.elearningplatform.request.AddUserRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class InstructorService(
        var instructorRepository: InstructorRepository,
        var userRepository: UserRepository,
        var userService: UserService) {

    fun getAll() = instructorRepository.findAll().toList()

    fun add(request: AddUserRequest): Instructor {
        val (name, surname, email, password, introduction, qualification) = request
        val user = userRepository.save(User(name = name, surname = surname, email = email, password = password))
        return instructorRepository.save(Instructor(
                user = user,
                introduction = introduction,
                qualification = qualification))
    }

    fun get(id: Long) = instructorRepository.findById(id)

    fun update(request: AddUserRequest, instructorId: Long): Instructor? {
        val instructor = instructorRepository.findByIdOrNull(instructorId)
                ?: throw Exception("Instructor doesn't exist!")
        return run {
            instructor.user.id?.let { userService.update(request, it) }
            instructorRepository.save(Instructor(
                    id = instructorId,
                    user = instructor.user,
                    introduction = request.introduction,
                    qualification = request.qualification))
        }
    }
}
