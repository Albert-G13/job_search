<#import "../layout.ftlh" as main>
<#import "/spring.ftl" as spring>

<@main.layout ; spring>
    <div class="d-flex justify-content-center mt-5">
        <form action="/vacancies/create/${user.id}" method="post" style="width: 100%; max-width: 600px;">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}">
            </#if>
            <h3 class="mb-4">Создать новую вакансию</h3>
            <div class="mb-3">
                <label class="form-label">Название вакансии</label>
                <@spring.formInput "vacancy.name" 'class="form-control" placeholder="Напр: Java Developer"'/>
                <@spring.showErrors "<br>" "text-danger small"/>
            </div>
            <div class="mb-3">
                <label class="form-label">Описание</label>
                <@spring.formTextarea "vacancy.description" 'class="form-control" rows="5"'/>
                <@spring.showErrors "<br>" "text-danger small"/>
            </div>
            <div class="row mb-3">
                <div class="col-md-6">
                    <label class="form-label">Опыт работы от (лет)</label>
                    <@spring.formInput "vacancy.expFrom" 'type="number" class="form-control" min="0"' />
                    <@spring.showErrors "<br>" "text-danger small" />
                </div>
                <div class="col-md-6">
                    <label class="form-label">Опыт работы до (лет)</label>
                    <@spring.formInput "vacancy.expTo" 'type="number" class="form-control" min="0"' />
                    <@spring.showErrors "<br>" "text-danger small" />
                </div>
            </div>
            <div class="row mb-3">
                <div class="col-md-6">
                    <label class="form-label">Зарплата</label>
                    <@spring.formInput "vacancy.salary" 'type="number" class="form-control"'/>
                    <@spring.showErrors "<br>" "text-danger small" />
                </div>
                <div class="col-md-6">
                    <label class="form-label">Категория (ID)</label>
                    <@spring.formInput "vacancy.categoryId" 'type="number" class="form-control"'/>
                    <@spring.showErrors "<br>" "text-danger small" />
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Опубликовать вакансию</button>
        </form>
    </div>
</@main.layout>