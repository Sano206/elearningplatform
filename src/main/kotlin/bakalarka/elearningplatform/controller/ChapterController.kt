package bakalarka.elearningplatform.controller

import bakalarka.elearningplatform.request.AddCourseChapterRequest
import bakalarka.elearningplatform.service.ChapterService
import bakalarka.elearningplatform.service.CourseService
import org.springframework.web.bind.annotation.*

@RestController
class ChapterController(var chapterService: ChapterService){
    // TODO: GK - read https://stackoverflow.blog/2020/03/02/best-practices-for-rest-api-design/
    // TOOD: GK - read https://restfulapi.net/resource-naming/

    // TODO: GK - start using formatter

    // TODO: GK - Move to course controller and set path to /courses/{couseId}/chapters
    @PostMapping("/courses/{courseId}/addChapter")
    fun addChapter(
            @PathVariable courseId: Long,
            @RequestBody request: AddCourseChapterRequest) = chapterService.addChapter(request, courseId)

    // TODO: GK - set path to /chapters/{chapterId}
    @PutMapping("/courses/{courseId}/{chapterId}/update")
    fun updateChapter(
            @PathVariable courseId: Long,
            @PathVariable chapterId: Long,
            @RequestBody request: AddCourseChapterRequest) = chapterService.updateChapter(courseId, chapterId, request)
}
