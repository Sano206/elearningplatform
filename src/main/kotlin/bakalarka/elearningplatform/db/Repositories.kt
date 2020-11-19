package bakalarka.elearningplatform.db

import bakalarka.elearningplatform.model.*
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

// TODO: GK - this should be in separate files

@Repository
interface UserRepository : CrudRepository<User, Long>

@Repository
interface InstructorRepository : CrudRepository<Instructor, Long>

@Repository
interface CourseRepository : CrudRepository<Course, Long>{

    fun findByTitle(title: String) : MutableSet<Course>
}

@Repository
interface CourseChapterRepository : CrudRepository<CourseChapter, Long>

@Repository
interface EnrollmentRepository : CrudRepository<Enrollment, Long>{

    fun findByUserId(id: Long) : MutableSet<Enrollment>
}
