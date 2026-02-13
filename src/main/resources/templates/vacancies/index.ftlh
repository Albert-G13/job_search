<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <div class="d-flex justify-content-start mb-4">
        <form method="get" class="d-flex gap-2">
            <select name="sort" onchange="this.form.submit()" class="form-select w-auto">
                <#assign s = RequestParameters.sort!"" >
                <option value="createdDate,desc" ${(s == "createdDate,desc")?string('selected','')}><@spring.message "vacancies.index.sort.createDate"/>
                </option>
                <option value="responds,desc" ${(s == "responds,desc")?string('selected','')}><@spring.message "vacancies.index.sort.respondsCount"/>
                </option>
            </select>
            <input type="hidden" name="size" value="3">
        </form>
    </div>
    <div class="row">
        <#list vacancies.getContent() as vacancy>
            <div class="card col-3 m-3" style="width: 100%;">
                <a href="/vacancies/${vacancy.id}" class="text-reset text-decoration-none">
                    <div class="card-body">
                        <h5 class="card-title">${vacancy.name}</h5>
                        <h6 class="card-subtitle mb-2 text-body-secondary"><@spring.message"vacancies.index.body.salary"/>: ${vacancy.salary}$</h6>
                        <p class="card-text"><@spring.message"vacancies.index.body.description"/>: ${vacancy.description}</p>
                        <p class="card-text"><@spring.message"vacancies.index.body.postDate"/>: ${vacancy.createdDate.toLocalDate()}</p>
                    </div>
                </a>
            </div>
        </#list>
    </div>
    <nav aria-label="Page navigation" class="mt-5">
        <ul class="pagination justify-content-center">

            <#assign url = "?size=${vacancies.size}">
            <#if RequestParameters.sort??>
                <#assign url = url + "&sort=" + RequestParameters.sort>
            </#if>

            <li class="page-item ${vacancies.isFirst()?string('disabled', '')}">
                <a class="page-link" href="${url}&page=0">« <@spring.message"vacancies.index.button.first"/></a>
            </li>

            <li class="page-item ${vacancies.hasPrevious()?string('', 'disabled')}">
                <a class="page-link" href="${url}&page=${vacancies.number - 1}"><@spring.message"vacancies.index.button.back"/></a>
            </li>

            <#assign current = vacancies.number>
            <#assign total = vacancies.totalPages>
            <#assign begin = (current - 2 > 0)?string(current - 2, 0)?number>
            <#assign end = (current + 2 < total - 1)?string(current + 2, total - 1)?number>

            <#list begin..end as i>
                <li class="page-item ${(current == i)?string('active', '')}">
                    <a class="page-link" href="${url}&page=${i}">${i + 1}</a>
                </li>
            </#list>

            <li class="page-item ${vacancies.hasNext()?string('', 'disabled')}">
                <a class="page-link" href="${url}&page=${vacancies.number + 1}"><@spring.message"vacancies.index.button.next"/></a>
            </li>

            <li class="page-item ${vacancies.isLast()?string('disabled', '')}">
                <a class="page-link" href="${url}&page=${vacancies.totalPages - 1}"><@spring.message"vacancies.index.button.last"/> »</a>
            </li>

        </ul>
    </nav>
</@main.layout>