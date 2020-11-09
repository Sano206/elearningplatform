package bakalarka.elearningplatform.service

import bakalarka.elearningplatform.request.AddInstructorRequest
import bakalarka.elearningplatform.model.Instructor
import bakalarka.elearningplatform.db.InstructorRepository
import org.springframework.stereotype.Service

@Service
class InstructorService(var instructorRepository: InstructorRepository) {

    fun getAll() = instructorRepository.findAll().toList()

    fun add(request: AddInstructorRequest) : Instructor {
        val (name, surname, email, password, introduction, qualification) = request
        return instructorRepository.save(Instructor(name, surname, email, password, introduction, qualification))
    }

    fun get(id: Long) = instructorRepository.findById(id)

    fun update(instructor: Instructor) = instructorRepository.save(instructor)
}