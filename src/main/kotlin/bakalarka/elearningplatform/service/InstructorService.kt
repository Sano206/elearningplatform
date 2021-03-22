package bakalarka.elearningplatform.service

import bakalarka.elearningplatform.db.InstructorRepository
import bakalarka.elearningplatform.model.Instructor
import bakalarka.elearningplatform.request.AddInstructorRequest
import bakalarka.elearningplatform.request.UpdateInstructorRequest
import bakalarka.elearningplatform.security.Management
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import com.auth0.exception.Auth0Exception

import com.auth0.exception.APIException

import com.auth0.client.mgmt.filter.UserFilter
import com.auth0.json.mgmt.users.User
import com.auth0.net.Request


@Service
class InstructorService(
        var instructorRepository: InstructorRepository,
        var management: Management) {

    fun managementTest(): MutableMap<String, Any>? {
        management.managementApi.users()
        val filter = UserFilter()

        val request: Request<User> = management.managementApi.users().get("google-oauth2|102356123786588847342", filter)
/*        try {

        } catch (exception: APIException) {
            // api error
        } catch (exception: Auth0Exception) {
            // request error
        }*/
        val response: User = request.execute()
/*        val data = User()
        val userId = UserService.getUserId()
        val request: Request<User> = management.managementApi.users().update(userId, data)*/
        response.appMetadata
        response.appMetadata["roles"] = arrayOf("instructor", "user", "admin")

        println(response.appMetadata)
        return         response.appMetadata

    }

    fun getAll() = instructorRepository.findAll().toList()

    fun findByUserID(userID: String) = instructorRepository.findByUserID(userID)

    fun add(request: AddInstructorRequest): Instructor {
        val (name, surname, introduction, qualification) = request
        val userId = UserService.getUserId()
        val filter = UserFilter()
        var request: Request<User> = management.managementApi.users().get(userId, filter)
        var response: User = request.execute()
        val data = User()
        data.givenName = name
        data.familyName = surname
        data.appMetadata = response.appMetadata
        data.appMetadata["roles"] = arrayOf("instructor", "user")
        request = management.managementApi.users().update(userId, data)
        try {
            val response: User = request.execute()
            println(response)
        } catch (exception: APIException) {
            // api error
            println(exception)
        } catch (exception: Auth0Exception) {
            println(exception)
            // request error
        }
        val instructor = findByUserID(userId)
        if(instructor != null){
            return instructorRepository.save(Instructor(
                    id = instructor.id,
                    userID = UserService.getUserId(),
                    introduction = introduction,
                    qualification = qualification))
        }
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

