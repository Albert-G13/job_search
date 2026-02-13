<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <div class="row">
        <#list resumes.getContent() as resume>
            <div class="card col-3 m-3" style="width: 100%;">
                <a href="/resumes/${resume.id}" class="text-reset text-decoration-none"">
                    <div class="card-body">
                        <h5 class="card-title">${resume.name}</h5>
                        <h6 class="card-subtitle mb-2 text-body-secondary">${resume.salary}$</h6>
                        <p class="card-text"><@spring.message "resumes.resume.updatedDate"/>: ${resume.updateTime.toLocalDate()}</p>
                    </div>
                </a>
            </div>
        </#list>
    </div>
    <nav aria-label="Page navigation" class="mt-5">
        <ul class="pagination justify-content-center">

            <#assign url = "?size=${resumes.size}">
            <#if RequestParameters.sort??>
                <#assign url = url + "&sort=" + RequestParameters.sort>
            </#if>

            <li class="page-item ${resumes.isFirst()?string('disabled', '')}">
                <a class="page-link" href="${url}&page=0">« <@spring.message "users.profile.button.first"/></a>
            </li>

            <li class="page-item ${resumes.hasPrevious()?string('', 'disabled')}">
                <a class="page-link" href="${url}&page=${resumes.number - 1}"><@spring.message "users.profile.button.back"/></a>
            </li>

            <#assign current = resumes.number>
            <#assign total = resumes.totalPages>
            <#assign begin = (current - 2 > 0)?string(current - 2, 0)?number>
            <#assign end = (current + 2 < total - 1)?string(current + 2, total - 1)?number>

            <#list begin..end as i>
                <li class="page-item ${(current == i)?string('active', '')}">
                    <a class="page-link" href="${url}&page=${i}">${i + 1}</a>
                </li>
            </#list>

            <li class="page-item ${resumes.hasNext()?string('', 'disabled')}">
                <a class="page-link" href="${url}&page=${resumes.number + 1}"><@spring.message "users.profile.button.next"/></a>
            </li>

            <li class="page-item ${resumes.isLast()?string('disabled', '')}">
                <a class="page-link" href="${url}&page=${resumes.totalPages - 1}"><@spring.message "users.profile.button.last"/> »</a>
            </li>

        </ul>
    </nav>
</@main.layout>