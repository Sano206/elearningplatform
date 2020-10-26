package bakalarka.elearningplatform

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class DakyController {

    @GetMapping("/")
    @ResponseBody
    fun home(): String? {
        return "Hello World!"
    }

}