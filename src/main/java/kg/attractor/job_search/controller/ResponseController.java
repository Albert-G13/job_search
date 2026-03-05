package kg.attractor.job_search.controller;

import kg.attractor.job_search.exceptions.AlreadyRespondedException;
import kg.attractor.job_search.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/responses")
@RequiredArgsConstructor
public class ResponseController {
    private final ResponseService responseService;

    @PostMapping("/apply")
    public String applyToVacancy(@RequestParam Integer vacancyId,
                                 @RequestParam Integer resumeId) {
        try {
            responseService.respond(vacancyId, resumeId);
            return "redirect:/vacancies/" + vacancyId + "?success";
        }catch (AlreadyRespondedException e){
            return "redirect:/vacancies/" + vacancyId + "?alreadyResponded";
        }
    }
}
