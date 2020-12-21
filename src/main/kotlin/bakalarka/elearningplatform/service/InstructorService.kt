package bakalarka.elearningplatform.service

import bakalarka.elearningplatform.db.InstructorRepository
import bakalarka.elearningplatform.db.UserRepository
import bakalarka.elearningplatform.model.Instructor
import bakalarka.elearningplatform.model.User
import bakalarka.elearningplatform.request.AddInstructorRequest
import bakalarka.elearningplatform.request.UpdateInstructorRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class InstructorService(
        var instructorRepository: InstructorRepository,
        var userRepository: UserRepository,
        var userService: UserService) {

    fun getAll() = instructorRepository.findAll().toList()

    fun add(request: AddInstructorRequest): Instructor {
        val (name, surname, email, password, introduction, qualification) = request
        val user = userRepository.save(User(name = name, surname = surname, email = email, password = password))
        return instructorRepository.save(Instructor(
                user = user,
                introduction = introduction,
                qualification = qualification))
    }

    fun get(id: Long) = instructorRepository.findById(id)

    fun update(request: UpdateInstructorRequest, instructorId: Long): Instructor? {
        val instructor = instructorRepository.findByIdOrNull(instructorId)
                ?: throw Exception("Instructor doesn't exist!")
        return instructorRepository.save(Instructor(
                id = instructorId,
                user = instructor.user,
                introduction = request.introduction,
                qualification = request.qualification))
    }
}

