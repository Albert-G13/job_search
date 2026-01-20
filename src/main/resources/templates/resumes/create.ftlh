<#import "../layout.ftlh" as main>
<#import "/spring.ftl" as spring>

<@main.layout ; spring>
    <div class="d-flex justify-content-center mt-5">
        <form action="/resumes/create/${user.id}" method="post" style="width: 100%; max-width: 500px;">
            <h3 class="mb-4">Создать резюме</h3>
            <input type="hidden" name="applicantId" value="${user.id}">
            <div class="mb-3">
                <label class="form-label">Название резюме (должность)</label>
                <@spring.formInput "resume.name" 'class="form-control" placeholder="Java Developer"' />
                <@spring.showErrors "<br>" "text-danger small" />
            </div>
            <div class="mb-3">
                <label class="form-label">Желаемая зарплата ($)</label>
                <@spring.formInput "resume.salary" 'type="number" class="form-control"' />
                <@spring.showErrors "<br>" "text-danger small" />
            </div>
            <div class="mb-3">
                <label class="form-label">Категория (ID)</label>
                <@spring.formInput "resume.categoryId" 'type="number" class="form-control"' />
                <@spring.showErrors "<br>" "text-danger small" />
            </div>
            <button type="submit" class="btn btn-primary">Опубликовать</button>
        </form>
    </div>
</@main.layout>