package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.exceptions.AlreadyRespondedException;
import kg.attractor.job_search.exceptions.ResumeNotFoundException;
import kg.attractor.job_search.exceptions.VacancyNotFoundException;
import kg.attractor.job_search.model.RespondedApplicant;
import kg.attractor.job_search.repository.RespondedApplicantRepository;
import kg.attractor.job_search.repository.ResumeRepository;
import kg.attractor.job_search.repository.VacancyRepository;
import kg.attractor.job_search.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResponseServiceImpl implements ResponseService {
    private final RespondedApplicantRepository responseRepository;
    private final VacancyRepository vacancyRepository;
    private final ResumeRepository resumeRepository;

    @Override
    public void respond(Integer vacancyId, Integer resumeId) {
        var vacancy = vacancyRepository.findById(vacancyId)
                .orElseThrow(VacancyNotFoundException::new);
        var resume = resumeRepository.findById(resumeId)
                .orElseThrow(ResumeNotFoundException::new);

        if (responseRepository.existsByVacancy_IdAndResume_Id(vacancyId, resumeId)){
            throw new AlreadyRespondedException();
        }
        RespondedApplicant response = new RespondedApplicant();
        response.setVacancy(vacancy);
        response.setResume(resume);
        response.setConfirmation(false);

        responseRepository.save(response);
    }
}
