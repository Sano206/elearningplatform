package bakalarka.elearningplatform.db

import bakalarka.elearningplatform.model.CourseChapter
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseChapterRepository : CrudRepository<CourseChapter, Long>