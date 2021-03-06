package bakalarka.elearningplatform.service

import bakalarka.elearningplatform.db.InstructorRepository
import bakalarka.elearningplatform.exceptions.ResourceNotFoundException
import bakalarka.elearningplatform.exceptions.ServerException
import bakalarka.elearningplatform.model.Instructor
import bakalarka.elearningplatform.model.Roles
import bakalarka.elearningplatform.request.InstructorRequest
import bakalarka.elearningplatform.request.UpdateUserRequest
import com.auth0.exception.APIException
import com.auth0.exception.Auth0Exception
import org.springframework.stereotype.Service

@Service
class InstructorService(
    var instructorRepository: InstructorRepository,
    var userService: UserService
) {

    fun getAll() = instructorRepository.findAll().toList()

    fun findByUserID(userID: String): Instructor = instructorRepository.findByUserID(userID).orElseThrow { ResourceNotFoundException("Instructor was not found.") }

    fun add(request: InstructorRequest): Instructor {
        val (name, surname, introduction, qualification) = request
        val userId = UserService.getUserId()
        userService.update(UpdateUserRequest(name, surname))
        try {
            userService.addUserRole(userId, Roles.INSTRUCTOR)
        } catch (exception: APIException) {
            println(exception)
            throw ServerException()
        } catch (exception: Auth0Exception) {
            println(exception)
            throw ServerException()
        }
        return instructorRepository.save(
            Instructor(
                userID = userId,
                name = name,
                surname = surname,
                introduction = introduction,
                qualification = qualification
            )
        )
    }

    fun getById(id: Long) = instructorRepository.findById(id)

    fun update(request: InstructorRequest): Instructor {
        val userId = UserService.getUserId()
        val instructor = findByUserID(userId)
        userService.update(UpdateUserRequest(request.name, request.surname))
        return instructorRepository.save(
            Instructor(
                id = instructor.id,
                userID = userId,
                name = request.name,
                surname = request.surname,
                introduction = request.introduction,
                qualification = request.qualification
            )
        )
    }
}
