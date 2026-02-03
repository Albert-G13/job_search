<#import "../layout.ftlh" as main>
<#import "/spring.ftl" as spring>

<@main.layout ; spring>
    <div class="d-flex justify-content-center mt-5">
        <form action="/resumes/create/${user.id}" method="post" style="width: 100%; max-width: 500px;">
            <h3 class="mb-4">Создать резюме</h3>
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}">
            </#if>
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
                <label class="form-label">Категория</label>
                <select name="categoryId" class="form-control">
                    <#list categories as category>
                        <option value="${category.id}"
                                <#if (resume.categoryId?? && resume.categoryId == category.id)>selected</#if>>
                            ${category.name}
                        </option>
                    </#list>
                </select>
                <@spring.bind "resume.categoryId" />
                <@spring.showErrors "<br>" "text-danger small" />
            </div>

            <h4 class="mb-3 text-secondary">Образование</h4>
            <div class="p-3 mb-3 ">
                <div class="mb-3">
                    <label>Учебное заведение</label>
                    <@spring.formInput "resume.education[0].institution" 'class="form-control"' />
                    <@spring.showErrors "<br>" "text-danger small" />
                </div>
                <div class="mb-3">
                    <label>Программа обучения</label>
                    <@spring.formInput "resume.education[0].program" 'class="form-control"' />
                    <@spring.showErrors "<br>" "text-danger small" />
                </div>
                <div class="row mb-3">
                    <div class="col">
                        <label>Начало</label>
                        <@spring.formInput "resume.education[0].startDate" 'class="form-control"' "date"/>
                        <@spring.showErrors "<br>" "text-danger small" />
                    </div>
                    <div class="col">
                        <label>Конец</label>
                        <@spring.formInput "resume.education[0].endDate" 'class="form-control"' "date" />
                        <@spring.showErrors "<br>" "text-danger small" />
                    </div>
                </div>
                <div class="mb-2">
                    <label>Степень</label>
                    <@spring.formInput "resume.education[0].degree" 'class="form-control"' />
                    <@spring.showErrors "<br>" "text-danger small" />
                </div>
            </div>

            <h4 class="mb-3 text-secondary">Опыт работы</h4>
            <div class="p-3 mb-3">
                <div class="row mb-3">
                    <div class="col-4">
                        <label>Стаж (лет)</label>
                        <@spring.formInput "resume.workExperience[0].years" 'type="number" class="form-control"' />
                        <@spring.showErrors "<br>" "text-danger small" />
                    </div>
                    <div class="col-8">
                        <label>Компания</label>
                        <@spring.formInput "resume.workExperience[0].companyName" 'class="form-control"' />
                        <@spring.showErrors "<br>" "text-danger small" />
                    </div>
                </div>
                <div class="mb-3">
                    <label>Ваша позиция</label>
                    <@spring.formInput "resume.workExperience[0].position" 'class="form-control"' />
                    <@spring.showErrors "<br>" "text-danger small" />
                </div>
                <div class="mb-2">
                    <label>Обязанности</label>
                    <@spring.formTextarea "resume.workExperience[0].responsibilities" 'class="form-control" rows="3"' />
                    <@spring.showErrors "<br>" "text-danger small" />
                </div>
            </div>

            <h4 class="mb-3 text-secondary">Контакты</h4>
            <div class="p-3 mb-3">
                <div class="row">
                    <div class="col-4">
                        <label>Тип связи</label>
                        <select name="contacts[0].typeId" class="form-control">
                            <#list contactTypes as type>
                                <option value="${type.id}">${type.type}</option>
                            </#list>
                        </select>
                        <@spring.bind "type.id" />
                        <@spring.showErrors "<br>" "text-danger small" />
                    </div>
                    <div class="col-8">
                        <label>Значение (номер/ссылка)</label>
                        <@spring.formInput "resume.contacts[0].contactValue" 'class="form-control" placeholder="996700700700"' />
                        <@spring.showErrors "<br>" "text-danger small" />
                    </div>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Опубликовать</button>
        </form>
    </div>
</@main.layout>