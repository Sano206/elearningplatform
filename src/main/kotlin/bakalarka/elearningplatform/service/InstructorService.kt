package bakalarka.elearningplatform.service

import bakalarka.elearningplatform.request.AddInstructorRequest
import bakalarka.elearningplatform.model.Instructor
import bakalarka.elearningplatform.db.InstructorRepository
import bakalarka.elearningplatform.db.UserRepository
import bakalarka.elearningplatform.model.User
import org.springframework.stereotype.Service

@Service
class InstructorService(
        var instructorRepository: InstructorRepository,
        var userRepository: UserRepository) {

    fun getAll() = instructorRepository.findAll().toList()

    fun add(request: AddInstructorRequest) : Instructor {
        val (name, surname, email, password, introduction, qualification) = request
        val user = userRepository.save(User(name = name, surname = surname, email = email, password = password))
        return instructorRepository.save(Instructor(
                user = user,
                introduction = introduction,
                qualification = qualification))
    }

    fun get(id: Long) = instructorRepository.findById(id)

    fun update(instructor: Instructor) = instructorRepository.save(instructor)
}