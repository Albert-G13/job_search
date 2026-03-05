<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <div class="container mt-5">
        <#if RequestParameters.success??>
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                Вы успешно откликнулись на вакансию!
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </#if>

        <#if RequestParameters.alreadyResponded??>
            <div class="alert alert-warning alert-dismissible fade show" role="alert">
                Вы уже откликались на эту вакансию с этим резюме.
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </#if>
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="/vacancies"><@spring.message "vacancies.info.nav.allVacancies"/></a></li>
                <li class="breadcrumb-item active">${vacancy.name}</li>
            </ol>
        </nav>

        <div class="card shadow-sm border-0">
            <div class="card-body p-4">
                <div class="d-flex justify-content-between align-items-start mb-4">
                    <div>
                        <h1 class="display-5 fw-bold">${vacancy.name}</h1>
                        <h3 class="text-primary fw-bold">${vacancy.salary} $</h3>
                    </div>
                    <div class="text-end">
                        <span class="badge bg-info text-dark"><@spring.message "vacancies.info.body.exp"/>: ${vacancy.expFrom} - ${vacancy.expTo} <@spring.message "vacancies.info.body.years"/></span>
                        <p class="text-muted small mt-2"><@spring.message "vacancies.info.body.post"/>: ${vacancy.createdDate.toLocalDate()}</p>
                    </div>
                </div>

                <hr>

                <div class="mt-4">
                    <h4 class="mb-3"><@spring.message "vacancies.info.body.description"/></h4>
                    <div style="white-space: pre-wrap;" class="lead">
                        ${vacancy.description}
                    </div>
                </div>

                <div class="mt-5 d-flex gap-2">
                    <a href="/vacancies" class="btn btn-outline-secondary btn-lg"><@spring.message "vacancies.info.button.backToList"/></a>
                </div>
            </div>
        </div>
    </div>

    <div class="mt-5 d-flex gap-2">
        <a href="/vacancies" class="btn btn-outline-secondary btn-lg"><@spring.message "vacancies.info.button.backToList"/></a>

        <#if user?? && user.role.role == "APPLICANT">
            <button type="button" class="btn btn-primary btn-lg" data-bs-toggle="modal" data-bs-target="#respondModal">
                <@spring.message "vacancies.info.button.respond"/>
            </button>
        </#if>
    </div>

    <div class="modal fade" id="respondModal" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Выберите резюме для отклика</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form action="/responses/apply" method="post">
                    <div class="modal-body">
                        <input type="hidden" name="vacancyId" value="${vacancy.id}">
                        <#if _csrf??>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        </#if>

                        <div class="mb-3">
                            <label class="form-label">Ваши резюме:</label>
                            <select name="resumeId" class="form-select" required>
                                <#if myResumes?? && (myResumes?size > 0)>
                                    <#list myResumes as resume>
                                        <option value="${resume.id}">${resume.name}</option>
                                    </#list>
                                <#else>
                                    <option value="" disabled>У вас пока нет резюме</option>
                                </#if>
                            </select>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary" <#if !(myResumes?? && (myResumes?size > 0))>disabled</#if>>
                            Откликнуться
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</@main.layout>