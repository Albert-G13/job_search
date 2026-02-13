<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <div class="container mt-5">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="/resumes"><@spring.message "resumes.info.allResumes"/></a></li>
                <li class="breadcrumb-item active">${resume.name}</li>
            </ol>
        </nav>
        <div class="row">
            <div class="col-lg-8">
                <div class="card shadow-sm mb-4 border-0">
                    <div class="card-body p-4">
                        <h1 class="fw-bold">${resume.name}</h1>
                        <h4 class="text-success mb-4"><@spring.message "resumes.info.salary"/>: ${resume.salary} $</h4>

                        <hr>

                        <h5 class="mt-4 mb-3 text-primary"><i class="bi bi-person-workspace"></i> <@spring.message "resumes.info.workExp"/></h5>
                        <#if resume.workExperience?has_content>
                            <#list resume.workExperience as work>
                                <div class="mb-3 ps-3 border-start border-3 border-light">
                                    <h6 class="fw-bold">${work.position} — ${work.companyName}</h6>
                                    <p class="text-muted small"><@spring.message "resumes.info.exp"/>: ${work.years} <@spring.message "vacancies.info.body.years"/></p>
                                    <p>${work.responsibilities!""}</p>
                                </div>
                            </#list>
                        <#else>
                            <p class="text-muted"><@spring.message "resumes.info.workExpNull"/></p>
                        </#if>

                        <h5 class="mt-4 mb-3 text-primary"><i class="bi bi-book"></i> <@spring.message "resumes.info.education"/></h5>
                        <#list resume.education as edu>
                            <div class="mb-3 ps-3 border-start border-3 border-light">
                                <h6 class="fw-bold">${edu.institution}</h6>
                                <p class="mb-1">${edu.degree} (${edu.program})</p>
                                <p class="text-muted small">${edu.startDate} — ${edu.endDate}</p>
                            </div>
                        </#list>
                    </div>
                </div>
            </div>

            <div class="col-lg-4">
                <div class="card shadow-sm border-0 bg-light">
                    <div class="card-body">
                        <h5 class="card-title mb-4"><@spring.message "resumes.info.contactInfo"/></h5>
                        <#list resume.contacts as contact>
                            <div class="mb-3">
                                <span class="fw-bold">${contact.contactValue}</span>
                            </div>
                        </#list>
                        <hr>
                        <p class="small text-muted text-center">
                            <@spring.message "resumes.info.lastUpdate"/>: ${resume.updateTime.toLocalDate()}
                        </p>
                        <a href="/resumes" class="btn btn-outline-dark w-100"><@spring.message "resumes.info.buttonBackToList"/></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@main.layout>