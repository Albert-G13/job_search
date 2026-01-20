<#import "../layout.ftlh" as main>
<#import "/spring.ftl" as spring />

<@main.layout ; spring>
    <div class="d-flex justify-content-center mt-5">
        <form action="/resumes/${resume.id}/edit" method="post" style="width: 100%; max-width: 500px;">
            <h2 class="mb-4">Редактировать резюме</h2>
            <div class="mb-3">
                <label class="form-label">Название</label>
                <@spring.formInput "resume.name" 'class="form-control"' />
                <@spring.showErrors "<br>" "text-danger small" />
            </div>
            <div class="mb-3">
                <label class="form-label">Зарплата</label>
                <@spring.formInput "resume.salary" 'type="number" class="form-control"' />
                <@spring.showErrors "<br>" "text-danger small" />
            </div>
            <div class="mb-3">
                <label class="form-label">Категория ID</label>
                <@spring.formInput "resume.categoryId" 'type="number" class="form-control"' />
                <@spring.showErrors "<br>" "text-danger small" />
            </div>
            <div class="form-check form-switch mb-4">
                <input class="form-check-input" type="checkbox" name="isActive" id="activeSwitch"
                        ${resume.active?string('checked', '')}>
                <label class="form-check-label" for="activeSwitch">Резюме активно</label>
            </div>
            <div class="d-flex gap-2">
                <button type="submit" class="btn btn-primary">Save</button>
                <a href="/resumes" class="btn btn-outline-secondary">Cancel</a>
            </div>
        </form>
    </div>
</@main.layout>