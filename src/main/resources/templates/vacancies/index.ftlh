<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="d-flex justify-content-start mb-4">
        <form method="get" class="d-flex gap-2">
            <select name="sort" onchange="this.form.submit()" class="form-select w-auto">
                <#assign s = RequestParameters.sort!"" >
                <option value="" disabled ${ (s == "")?string('selected', '')}>Выберите сортировку</option>
                <option value="createdDate,desc" ${(s == "createdDate,desc")?string('selected','')}>По дате публикации (сначала новые)</option>
                <option value="responds,desc" ${(s == "responds,desc")?string('selected','')}>По количеству откликов (по убыванию)</option>
            </select>
            <input type="hidden" name="size" value="16">
        </form>
    </div>
    <div class="row">
        <#list vacancies.getContent() as vacancy>
            <div class="card col-3 m-3" style="width: 18rem;">
                <div class="card-body">
                    <h5 class="card-title">${vacancy.name}</h5>
                    <h6 class="card-subtitle mb-2 text-body-secondary">${vacancy.salary}$</h6>
                    <p class="card-text">${vacancy.description}</p>
                    <a href="#" class="card-link">Info</a>
                </div>
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
                <a class="page-link" href="${url}&page=0">« Первая</a>
            </li>

            <li class="page-item ${vacancies.hasPrevious()?string('', 'disabled')}">
                <a class="page-link" href="${url}&page=${vacancies.number - 1}">Назад</a>
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
                <a class="page-link" href="${url}&page=${vacancies.number + 1}">Вперед</a>
            </li>

            <li class="page-item ${vacancies.isLast()?string('disabled', '')}">
                <a class="page-link" href="${url}&page=${vacancies.totalPages - 1}">Последняя »</a>
            </li>

        </ul>
    </nav>
</@main.layout>