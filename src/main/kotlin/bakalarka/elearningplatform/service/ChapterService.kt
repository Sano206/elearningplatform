package bakalarka.elearningplatform.service

import bakalarka.elearningplatform.db.CourseChapterRepository
import bakalarka.elearningplatform.db.CourseRepository
import bakalarka.elearningplatform.exceptions.AuthorizationException
import bakalarka.elearningplatform.exceptions.ResourceNotFoundException
import bakalarka.elearningplatform.model.Course
import bakalarka.elearningplatform.model.CourseChapter
import bakalarka.elearningplatform.request.AddCourseChapterRequest
import org.springframework.stereotype.Service

@Service
class ChapterService(
    var courseChapterRepository: CourseChapterRepository,
    var courseRepository: CourseRepository,
    var userService: UserService,
) {

    fun addChapter(request: AddCourseChapterRequest, courseId: Long): CourseChapter {
        val (chapterTitle, description, content, position) = request
        val course = courseRepository.findById(courseId).orElseThrow { ResourceNotFoundException("Course with ID $courseId doesn't exist.") }
        val chapter = courseChapterRepository.save(
            CourseChapter(
                title = chapterTitle,
                description = description,
                content = content,
                position = position,
                course = course
            )
        )
        incrementPositions(course, position, chapter)
        return chapter
    }

    fun incrementPositions(course: Course, position: Int, chapter: CourseChapter) {
        for (courseChapter in course.courseChapters) {
            if (courseChapter.position >= position && courseChapter.id != chapter.id) {
                courseChapter.position++
                courseChapterRepository.save(courseChapter)
            }
        }
    }

    fun updateOrder(course: Course, position: Int, chapter: CourseChapter) {
        course.courseChapters.forEachIndexed { index, courseChapter ->
            run {
                courseChapter.position = index
                courseChapterRepository.save(courseChapter)
            }
        }
    }

    fun updateChapter(chapterId: Long, request: AddCourseChapterRequest): CourseChapter {
        val (chapterTitle, description, content, position) = request
        val course = courseChapterRepository.findById(chapterId).orElseThrow { ResourceNotFoundException() }.course
        val instructor = course.instructor
        if (!userService.isOwnerOrAdmin(instructor)) throw AuthorizationException()
        else {
            val chapter = courseChapterRepository.save(
                CourseChapter(
                    id = chapterId,
                    title = chapterTitle,
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
    }
}
