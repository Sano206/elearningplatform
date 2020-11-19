package bakalarka.elearningplatform.service

import bakalarka.elearningplatform.model.Instructor
import bakalarka.elearningplatform.db.InstructorRepository
import bakalarka.elearningplatform.db.UserRepository
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

    // TODO: GK - these functions are a bit weird
    fun add(request: AddUserRequest) : Instructor {
        val (name, surname, email, password, introduction, qualification) = request
        val user = userRepository.save(User(name = name, surname = surname, email = email, password = password))
        return instructorRepository.save(Instructor(
                user = user,
                introduction = introduction,
                qualification = qualification))
    }

    fun get(id: Long) = instructorRepository.findById(id)

    // TODO: GK - these functions are a bit weird
    fun update(request: AddUserRequest, instructorId: Long): Instructor? {
        // TODO: GK - also here you can use the "?: throw ...." syntax
        val instructor = instructorRepository.findByIdOrNull(instructorId)
        return if(instructor == null){
            null
        }else{
            instructor.user.id?.let { userService.update(request, it) }
            instructorRepository.save(Instructor(
                    id = instructorId,
                    user = instructor.user,
                    introduction = request.introduction,
                    qualification = request.qualification))
        }
    }
}
