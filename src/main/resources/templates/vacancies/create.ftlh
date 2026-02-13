<#import "../layout.ftlh" as main>
<#import "/spring.ftl" as spring>

<@main.layout ; spring>
    <div class="d-flex justify-content-center mt-5">
        <form action="/vacancies/create/${user.id}" method="post" style="width: 100%; max-width: 600px;">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                       value="${(_csrf.token)!'csrf-token'}">
            </#if>
            <h3 class="mb-4"><@spring.message "vacancies.create.header"/></h3>
            <div class="mb-3">
                <label class="form-label"><@spring.message "vacancies.create.title"/></label>
                <@spring.formInput "vacancy.name" 'class="form-control" placeholder="Java Developer"'/>
                <@spring.showErrors "<br>" "text-danger small"/>
            </div>
            <div class="mb-3">
                <label class="form-label"><@spring.message "vacancies.create.description"/></label>
                <@spring.formTextarea "vacancy.description" 'class="form-control" rows="5"'/>
                <@spring.showErrors "<br>" "text-danger small"/>
            </div>
            <div class="row mb-3">
                <div class="col-md-6">
                    <label class="form-label"><@spring.message "vacancies.create.expFrom"/></label>
                    <@spring.formInput "vacancy.expFrom" 'type="number" class="form-control" min="0"' />
                    <@spring.showErrors "<br>" "text-danger small" />
                </div>
                <div class="col-md-6">
                    <label class="form-label"><@spring.message "vacancies.create.expTo"/></label>
                    <@spring.formInput "vacancy.expTo" 'type="number" class="form-control" min="0"' />
                    <@spring.showErrors "<br>" "text-danger small" />
                </div>
            </div>
            <div class="row mb-3">
                <div class="col-md-6">
                    <label class="form-label"><@spring.message "vacancies.create.salary"/></label>
                    <@spring.formInput "vacancy.salary" 'type="number" class="form-control"'/>
                    <@spring.showErrors "<br>" "text-danger small" />
                </div>
                <div class="col-md-6">
                    <div class="mb-3">
                        <label class="form-label"><@spring.message "vacancies.create.category"/></label>
                        <select name="categoryId" class="form-control">
                            <#list categories as category>
                                <option value="${category.id}"
                                        <#if (vacancy.categoryId?? && vacancy.categoryId == category.id)>selected</#if>>
                                    ${category.name}
                                </option>
                            </#list>
                        </select>
                        <@spring.bind "vacancy.categoryId" />
                        <@spring.showErrors "<br>" "text-danger small" />
                    </div>
                </div>
            </div>
            <#if error??>
                <div class="alert alert-danger py-2 small">${error}</div>
            </#if>
                <div class="d-flex gap-2 mt-4">
                    <button type="submit" class="btn btn-primary"><@spring.message "vacancies.create.button.post"/></button>
                    <a href="/users/#{user.id}/profile" class="btn btn-outline-secondary"><@spring.message "vacancies.create.button.cancel"/></a>
                </div>
        </form>
    </div>
</@main.layout>