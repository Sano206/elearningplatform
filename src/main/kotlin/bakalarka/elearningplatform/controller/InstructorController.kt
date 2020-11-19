package bakalarka.elearningplatform.controller

import org.springframework.web.bind.annotation.*
import bakalarka.elearningplatform.request.AddUserRequest
import bakalarka.elearningplatform.service.InstructorService

@RestController
@RequestMapping("/instructors")
class InstructorController(
        var instructorService: InstructorService,
) {
    // TODO: GK - formatter

    @GetMapping("")
    fun getAll() = instructorService.getAll()

    // TODO: GK - set path to ""
    @PostMapping("/add")
    fun add(@RequestBody request: AddUserRequest) = instructorService.add(request)

    // TODO: GK - follow project convention and rename id to instructorId
    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) = instructorService.get(id)

    // TODO: GK - remove the trailing /update -- set path to /{instructorId}
    @PutMapping("/{instructorId}/update")
    fun update(
            @PathVariable instructorId: Long,
            @RequestBody request: AddUserRequest) = instructorService.update(request,instructorId)
}
