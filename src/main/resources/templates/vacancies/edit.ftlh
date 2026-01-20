<#import "../layout.ftlh" as main>
<#import "/spring.ftl" as spring />

<@main.layout ; spring>
    <div class="d-flex justify-content-center mt-5">
        <form action="/vacancies/${vacancy.id}/edit" method="post" style="width: 100%; max-width: 700px;">
            <h2 class="mb-4">Редактирование вакансии #${vacancy.id}</h2>
            <div class="mb-3">
                <label class="form-label">Название вакансии</label>
                <@spring.formInput "vacancy.name" 'class="form-control"' />
                <@spring.showErrors "<br>" "text-danger small" />
            </div>
            <div class="row">
                <div class="col-md-6 mb-3">
                    <label class="form-label">Зарплата</label>
                    <@spring.formInput "vacancy.salary" 'type="number" class="form-control"' />
                    <@spring.showErrors "<br>" "text-danger small" />
                </div>
                <div class="col-md-6 mb-3">
                    <label class="form-label">Категория (ID)</label>
                    <@spring.formInput "vacancy.categoryId" 'type="number" class="form-control"' />
                </div>
            </div>
            <div class="row mb-3">
                <div class="col-md-6">
                    <label class="form-label">Опыт от</label>
                    <@spring.formInput "vacancy.expFrom" 'type="number" class="form-control"' />
                </div>
                <div class="col-md-6">
                    <label class="form-label">Опыт до</label>
                    <@spring.formInput "vacancy.expTo" 'type="number" class="form-control"' />
                </div>
            </div>
            <div class="mb-3">
                <label class="form-label">Описание</label>
                <@spring.formTextarea "vacancy.description" 'class="form-control" rows="5"' />
                <@spring.showErrors "<br>" "text-danger small" />
            </div>
            <div class="d-flex gap-2 mt-4">
                <button type="submit" class="btn btn-primary">Сохранить</button>
                <a href="/vacancies" class="btn btn-outline-secondary">Отмена</a>
            </div>
        </form>
    </div>
</@main.layout>