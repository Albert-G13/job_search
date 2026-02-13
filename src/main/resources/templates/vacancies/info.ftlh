<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <div class="container mt-5">
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
</@main.layout>