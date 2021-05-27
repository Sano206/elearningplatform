package bakalarka.elearningplatform.controller

import bakalarka.elearningplatform.request.InstructorRequest
import bakalarka.elearningplatform.service.CourseService
import bakalarka.elearningplatform.service.InstructorService
import bakalarka.elearningplatform.service.UserService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/instructors")
class InstructorController(
    var instructorService: InstructorService,
    var courseService: CourseService
) {

/*    @GetMapping("")
    fun getAll() = instructorService.getAll()*/

    @PostMapping("")
    @PreAuthorize("hasAuthority('create:user')")
    fun add(@RequestBody request: InstructorRequest) = instructorService.add(request)

    @GetMapping("")
    @PreAuthorize("hasAuthority('get:user')")
    fun get() = instructorService.findByUserID(UserService.getUserId())

/*    @GetMapping("/daco")
    fun get() = instructorService.managementTest()*/

    @PutMapping("")
    fun update(@RequestBody request: InstructorRequest) = instructorService.update(request)
}
