<#import "../layout.ftlh" as main>
<#import "/spring.ftl" as spring />

<@main.layout ; spring>
    <div class="d-flex justify-content-center mt-5">
        <form action="/vacancies/${vacancy.id}/edit" method="post" style="width: 100%; max-width: 700px;">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                       value="${(_csrf.token)!'csrf-token'}">
            </#if>
            <h2 class="mb-4"><@spring.message "vacancies.edit.header"/></h2>
            <div class="mb-3">
                <label class="form-label"><@spring.message "vacancies.edit.title"/></label>
                <@spring.formInput "vacancy.name" 'class="form-control"' />
                <@spring.showErrors "<br>" "text-danger small" />
            </div>
            <div class="mb-3">
                <label class="form-label"><@spring.message "vacancies.edit.description"/></label>
                <@spring.formTextarea "vacancy.description" 'class="form-control" rows="5"' />
                <@spring.showErrors "<br>" "text-danger small" />
            </div>
            <div class="row mb-3">
                <div class="col-md-6">
                    <label class="form-label"><@spring.message "vacancies.edit.expFrom"/></label>
                    <@spring.formInput "vacancy.expFrom" 'type="number" class="form-control"' />
                    <@spring.showErrors "<br>" "text-danger small" />
                </div>
                <div class="col-md-6">
                    <label class="form-label"><@spring.message "vacancies.edit.expTo"/></label>
                    <@spring.formInput "vacancy.expTo" 'type="number" class="form-control"' />
                    <@spring.showErrors "<br>" "text-danger small" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-6 mb-3">
                    <label class="form-label"><@spring.message "vacancies.edit.salary"/></label>
                    <@spring.formInput "vacancy.salary" 'type="number" class="form-control"' />
                    <@spring.showErrors "<br>" "text-danger small" />
                </div>
                <div class="col-md-6 mb-3">
                    <div class="mb-3">
                        <label class="form-label"><@spring.message "vacancies.edit.category"/></label>
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
            <@spring.formCheckbox "vacancy.active", "class='form-check-input' id='activeSwitch'" />
            <label class="form-check-label" for="activeSwitch"><@spring.message "vacancies.edit.active"/></label>
            <div class="d-flex gap-2 mt-4">
                <button type="submit" class="btn btn-primary"><@spring.message "vacancies.edit.button.save"/></button>
                <a href="/users/#{user.id}/profile" class="btn btn-outline-secondary"><@spring.message "vacancies.edit.button.cancel"/></a>
            </div>
        </form>
    </div>
</@main.layout>