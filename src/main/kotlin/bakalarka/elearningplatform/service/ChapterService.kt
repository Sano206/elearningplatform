package bakalarka.elearningplatform.service

import bakalarka.elearningplatform.db.CourseChapterRepository
import bakalarka.elearningplatform.db.CourseRepository
import bakalarka.elearningplatform.model.Course
import bakalarka.elearningplatform.model.CourseChapter
import bakalarka.elearningplatform.request.AddCourseChapterRequest
import bakalarka.elearningplatform.security.Management
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ChapterService(
    var courseChapterRepository: CourseChapterRepository,
    var courseRepository: CourseRepository,
    var userService: UserService,
    var management: Management
) {

    fun addChapter(request: AddCourseChapterRequest, courseId: Long): CourseChapter {
        val (chapterTitle, description, content, position) = request
        val course = courseRepository.findById(courseId)
        val chapter = courseChapterRepository.save(
            CourseChapter(
                chapterTitle = chapterTitle,
                description = description,
                content = content,
                position = position,
                course = courseRepository.findByIdOrNull(courseId)
            )
        )
        if (course.isPresent) {
            course.get().courseChapters.add(position, chapter)
            course.get().courseChapters.removeLast()
            updateOrder(course.get(), position, chapter)
        }
        return chapter
    }

    fun updateOrder(course: Course, position: Int, chapter: CourseChapter) {
        course.courseChapters.forEachIndexed { index, courseChapter ->
            run {
                courseChapter.position = index
                courseChapterRepository.save(courseChapter)
            }
        }
    }

    fun updateChapter(chapterId: Long, request: AddCourseChapterRequest): CourseChapter? {
        val (chapterTitle, description, content, position) = request
        val course = courseChapterRepository.findByIdOrNull(chapterId)?.course
        if (course != null) {
            val instructor = course.id?.let { courseRepository.findByIdOrNull(it)?.instructor } ?: return null
            if (!userService.isOwnerOrAdmin(instructor)) return null
            val chapter = courseChapterRepository.save(
                CourseChapter(
                    id = chapterId,
                    chapterTitle = chapterTitle,
                    description = description,
                    content = content,
                    position = position,
                    course = course
                )
            )
            course.courseChapters.removeAt(course.courseChapters.indexOf(chapter))
            course.courseChapters.add(position, chapter)
            updateOrder(course, position, chapter)
            return chapter
        }
        return null
    }
}
