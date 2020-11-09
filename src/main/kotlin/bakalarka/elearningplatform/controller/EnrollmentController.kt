package bakalarka.elearningplatform.controller

import bakalarka.elearningplatform.model.Enrollment
import bakalarka.elearningplatform.service.EnrollmentService
import org.springframework.data.web.JsonPath
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/enrollments")
class EnrollmentController(
        var enrollmentService: EnrollmentService,
        var enrollments: MutableSet<Enrollment> = mutableSetOf()
) {

    @GetMapping("/{userId}") //
    fun getAll(@PathVariable id: Long) = enrollmentService.getAll(id)

    @PostMapping("/add/{userId}/{courseId}") //TODO: vymysliet pridanie enrollmentu
    fun add(@RequestBody requestBody: RequestBody){


    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) = enrollmentService.get(id)
}