package bakalarka.elearningplatform.controller

import bakalarka.elearningplatform.model.Topic
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TopicController() {

    @GetMapping("/topics")
    fun getTopics() = Topic.values()
}
