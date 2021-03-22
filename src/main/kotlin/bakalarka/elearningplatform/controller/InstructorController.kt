package bakalarka.elearningplatform.controller

import bakalarka.elearningplatform.request.AddInstructorRequest
import bakalarka.elearningplatform.request.UpdateInstructorRequest
import bakalarka.elearningplatform.service.CourseService
import bakalarka.elearningplatform.service.InstructorService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/instructors")
class InstructorController(
        var instructorService: InstructorService,
        var courseService: CourseService
) {

    @GetMapping("")
    fun getAll() = instructorService.getAll()

    @PostMapping("")
    @PreAuthorize("hasAuthority('create:user')")
    fun add(@RequestBody request: AddInstructorRequest) = instructorService.add(request)

    @GetMapping("/{instructorId}")
    fun get(@PathVariable instructorId: Long) = instructorService.getById(instructorId)

    @GetMapping("/daco")
    fun get() = instructorService.managementTest()

    @PutMapping("/{instructorId}")
    fun update(
            @PathVariable instructorId: Long,
            @RequestBody request: UpdateInstructorRequest) = instructorService.update(request, instructorId)

}
