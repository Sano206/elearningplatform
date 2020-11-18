package bakalarka.elearningplatform.service

import bakalarka.elearningplatform.db.CourseChapterRepository
import bakalarka.elearningplatform.db.CourseRepository
import bakalarka.elearningplatform.model.CourseChapter
import bakalarka.elearningplatform.request.AddCourseChapterRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ChapterService(
        var courseChapterRepository: CourseChapterRepository,
        var courseRepository: CourseRepository
        ) {

    fun addChapter(request: AddCourseChapterRequest, courseId: Long) : CourseChapter {
        val(chapterTitle, description, content) = request
        return courseChapterRepository.save(CourseChapter(
                chapterTitle = chapterTitle,
                description = description,
                content = content,
                course = courseRepository.findByIdOrNull(courseId)))
    }

    fun updateChapter(courseId: Long, chapterId: Long, request: AddCourseChapterRequest): CourseChapter? {
        val(chapterTitle, description, content) = request
        val course = courseRepository.findByIdOrNull(courseId) ?: return null
        return courseChapterRepository.save(CourseChapter(
                id = chapterId,
                chapterTitle = chapterTitle,
                description = description,
                content = content,
                course = course
        ))

    }
}