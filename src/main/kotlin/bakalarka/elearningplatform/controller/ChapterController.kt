package bakalarka.elearningplatform.controller

import bakalarka.elearningplatform.request.AddCourseChapterRequest
import bakalarka.elearningplatform.service.ChapterService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ChapterController(var chapterService: ChapterService) {

    @PutMapping("/chapters/{chapterId}")
    fun updateChapter(
            @PathVariable chapterId: Long,
            @RequestBody request: AddCourseChapterRequest) = chapterService.updateChapter(chapterId, request)
}