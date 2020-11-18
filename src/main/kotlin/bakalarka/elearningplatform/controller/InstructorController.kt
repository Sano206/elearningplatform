package bakalarka.elearningplatform.controller

import org.springframework.web.bind.annotation.*
import bakalarka.elearningplatform.request.AddUserRequest
import bakalarka.elearningplatform.service.InstructorService

@RestController
@RequestMapping("/instructors")
class InstructorController(
        var instructorService: InstructorService,
) {

    @GetMapping("")
    fun getAll() = instructorService.getAll()

    @PostMapping("/add")
    fun add(@RequestBody request: AddUserRequest) = instructorService.add(request)

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) = instructorService.get(id)

    @PutMapping("/{instructorId}/update")
    fun update(
            @PathVariable instructorId: Long,
            @RequestBody request: AddUserRequest) = instructorService.update(request,instructorId)
}