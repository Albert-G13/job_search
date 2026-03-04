<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <div class="d-flex justify-content-start mb-4">
        <form method="get" class="d-flex gap-2" id="sort">
            <input type="text" name="name" id="search-input" class="form-control"
                   placeholder="<@spring.message "vacancies.index.search"/>" value="${searchName!''}">
            <select name="categoryId" class="form-select w-auto" onchange="this.form.submit()">
                <option value=""><@spring.message "vacancies.create.category"/></option>
                <#list categories as cat>
                    <option value="${cat.id}" ${(selectedCategory?? && selectedCategory == cat.id)?string('selected','')}>
                        ${cat.name}
                    </option>
                </#list>
            </select>
            <select name="sort" class="form-select w-auto" onchange="this.form.submit()">
                <#assign s = RequestParameters.sort!"" >
                <option value="createdDate,desc" ${(s == "createdDate,desc")?string('selected','')}><@spring.message "vacancies.index.sort.createDate"/>
                </option>
                <option value="responds,desc" ${(s == "responds,desc")?string('selected','')}><@spring.message "vacancies.index.sort.respondsCount"/>
                </option>
                <option value="salary,desc" ${(s == "salary,desc")?string('selected','')}><@spring.message "vacancies.index.sort.salaryMax"/>
                </option>
                <option value="salary,asc" ${(s == "salary,asc")?string('selected','')}><@spring.message "vacancies.index.sort.salaryMin"/>
                </option>
            </select>
            <input type="hidden" name="size" value="3">
        </form>
    </div>

    <div id="vacancies-container">
        <#include "partial_vacancies.ftlh">
    </div>
    <script src="/static/js/main.js"></script>
</@main.layout>