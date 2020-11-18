package bakalarka.elearningplatform.controller

import bakalarka.elearningplatform.request.AddCourseChapterRequest
import bakalarka.elearningplatform.service.ChapterService
import bakalarka.elearningplatform.service.CourseService
import org.springframework.web.bind.annotation.*

@RestController
class ChapterController(var chapterService: ChapterService){

    @PostMapping("/courses/{courseId}/addChapter")
    fun addChapter(
            @PathVariable courseId: Long,
            @RequestBody request: AddCourseChapterRequest) = chapterService.addChapter(request, courseId)

    @PutMapping("/courses/{courseId}/{chapterId}/update")
    fun updateChapter(
            @PathVariable courseId: Long,
            @PathVariable chapterId: Long,
            @RequestBody request: AddCourseChapterRequest) = chapterService.updateChapter(courseId, chapterId, request)
}