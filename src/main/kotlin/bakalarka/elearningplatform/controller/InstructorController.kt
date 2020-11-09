package bakalarka.elearningplatform.controller

import org.springframework.web.bind.annotation.*
import bakalarka.elearningplatform.request.AddInstructorRequest
import bakalarka.elearningplatform.model.Instructor
import bakalarka.elearningplatform.service.InstructorService

@RestController
@RequestMapping("/instructors")
class InstructorController(
        var instructorService: InstructorService,
        private val instructors: MutableList<Instructor> = mutableListOf()
) {

    @GetMapping("")
    fun getAll() = instructorService.getAll()

    @PostMapping("/add")
    fun add(@RequestBody request: AddInstructorRequest) = instructorService.add(request)

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) = instructorService.get(id)

    @PutMapping("/update")
    fun update(@RequestBody request: Instructor) = instructorService.update(request)
}