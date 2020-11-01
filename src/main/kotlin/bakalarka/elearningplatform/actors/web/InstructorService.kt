package bakalarka.elearningplatform.actors.web

import bakalarka.elearningplatform.actors.domain.AddInstructorRequest
import bakalarka.elearningplatform.actors.domain.Instructor
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