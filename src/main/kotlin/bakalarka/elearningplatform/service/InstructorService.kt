package bakalarka.elearningplatform.service

import bakalarka.elearningplatform.db.InstructorRepository
import bakalarka.elearningplatform.model.Instructor
import bakalarka.elearningplatform.request.InstructorRequest
import bakalarka.elearningplatform.request.UpdateUserRequest
import bakalarka.elearningplatform.security.Management
import org.springframework.stereotype.Service
import com.auth0.exception.Auth0Exception

import com.auth0.exception.APIException


@Service
class InstructorService(
        var instructorRepository: InstructorRepository,
        var management: Management,
        var userService: UserService
) {

/*    fun managementTest(): RequestEntity<String> {
        management.managementApi.users()
        val filter = UserFilter()

        try {

        } catch (exception: APIException) {
            // api error
        } catch (exception: Auth0Exception) {
            // request error
        }
        val userId = UserService.getUserId()


        return response

    }*/

    fun getAll() = instructorRepository.findAll().toList()

    fun findByUserID(userID: String) = instructorRepository.findByUserID(userID)

    fun add(request: InstructorRequest): Instructor {
        val (name, surname, introduction, qualification) = request
        val userId = UserService.getUserId()
        val userList = mutableListOf<String>()
        userList.add(userId)
        val roleRequest = management.managementApi.roles().assignUsers("rol_MGuvMqKSv1o2GXJ6",userList)
        userService.update(UpdateUserRequest(name, surname))
        try {
            roleRequest.execute()
        } catch (exception: APIException) {
            // api error
            println(exception)
        } catch (exception: Auth0Exception) {
            println(exception)
            // request error
        }
        /*val filter = UserFilter()
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
        }*/


        val instructor = findByUserID(userId)
        if(instructor != null){
            return instructorRepository.save(Instructor(
                    id = instructor.id,
                    userID = userId,
                    introduction = introduction,
                    qualification = qualification))
        }
        return instructorRepository.save(Instructor(
                userID = userId,
                introduction = introduction,
                qualification = qualification))
    }

    fun getById(id: Long) = instructorRepository.findById(id)

    fun update(request: InstructorRequest): Instructor? {
        val userId = UserService.getUserId()
        val instructor = findByUserID(userId) ?: return null
        userService.update(UpdateUserRequest(request.name, request.surname))
        return instructorRepository.save(Instructor(
                id = instructor.id,
                userID = userId,
                introduction = request.introduction,
                qualification = request.qualification))
    }
}

