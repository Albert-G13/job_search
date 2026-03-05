<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <div class="d-flex justify-content-start mb-4">
        <form method="get" class="d-flex gap-2" id="sort-resumes">
            <input type="text" name="name" id="search-input" class="form-control"
                   placeholder="<@spring.message "vacancies.index.search"/>" value="${searchName!''}">

            <select name="categoryId" class="form-select w-auto" onchange="this.form.submit()">
                <option value=""><@spring.message "resumes.edit.category"/></option>
                <#list categories as cat>
                    <option value="${cat.id}" ${(selectedCategory?? && selectedCategory == cat.id)?string('selected','')}>
                        ${cat.name}
                    </option>
                </#list>
            </select>

            <select name="sort" class="form-select w-auto" onchange="this.form.submit()">
                <#assign s = RequestParameters.sort!"" >
                <option value="updateTime,desc" ${(s == "updateTime,desc")?string('selected','')}><@spring.message "resumes.resume.sort.updatedDate"/></option>
                <option value="salary,desc" ${(s == "salary,desc")?string('selected','')}><@spring.message "vacancies.index.sort.salaryMax"/></option>
                <option value="salary,asc" ${(s == "salary,asc")?string('selected','')}><@spring.message "vacancies.index.sort.salaryMin"/></option>
            </select>
            <input type="hidden" name="size" value="${resumes.size}">
        </form>
    </div>

    <div class="row" id="resumes-container">
        <#list resumes.getContent() as resume>
            <div class="card col-3 m-3" style="width: 100%;">
                <a href="/resumes/${resume.id}" class="text-reset text-decoration-none">
                    <div class="card-body">
                        <div class="d-flex justify-content-between">
                            <h5 class="card-title">${resume.name}</h5>
                        </div>
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
            <#if RequestParameters.name?? && RequestParameters.name?has_content>
                <#assign url = url + "&name=" + RequestParameters.name>
            </#if>
            <#if RequestParameters.categoryId?? && RequestParameters.categoryId?has_content>
                <#assign url = url + "&categoryId=" + RequestParameters.categoryId>
            </#if>
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
                <a class="page-link" href="${url}&page=${resumes.totalPages - 1} »"> <@spring.message "users.profile.button.last"/></a>
            </li>
        </ul>
    </nav>
</@main.layout>