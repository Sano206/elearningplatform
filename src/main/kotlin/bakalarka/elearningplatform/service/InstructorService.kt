package bakalarka.elearningplatform.service

import bakalarka.elearningplatform.db.InstructorRepository
import bakalarka.elearningplatform.model.Instructor
import bakalarka.elearningplatform.request.AddInstructorRequest
import bakalarka.elearningplatform.request.UpdateInstructorRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class InstructorService(
        var instructorRepository: InstructorRepository) {

    fun getAll() = instructorRepository.findAll().toList()

    fun findByUserID(userID: String) = instructorRepository.findByUserID(userID)

    fun add(request: AddInstructorRequest): Instructor {
        val (introduction, qualification) = request
        return instructorRepository.save(Instructor(
                userID = UserService.getUserId(),
                introduction = introduction,
                qualification = qualification))
    }

    fun getById(id: Long) = instructorRepository.findById(id)

    fun update(request: UpdateInstructorRequest, instructorId: Long): Instructor? {
        val instructor = instructorRepository.findByIdOrNull(instructorId)
                ?: throw Exception("Instructor doesn't exist!")
        return instructorRepository.save(Instructor(
                id = instructorId,
                userID = UserService.getUserId(),
                introduction = request.introduction,
                qualification = request.qualification))
    }
}

