<#import "../layout.ftlh" as main>
<#import "/spring.ftl" as spring>

<@main.layout ; spring>
    <div class="d-flex justify-content-center mt-5">
        <form action="/resumes/${resume.id}/edit" method="post" style="width: 100%; max-width: 500px;">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                       value="${(_csrf.token)!'csrf-token'}">
            </#if>
            <h3 class="mb-4"><@spring.message "resumes.edit.header"/></h3>
            <input type="hidden" name="applicantId" value="${user.id}">
            <div class="mb-3">
                <label class="form-label"><@spring.message "resumes.edit.title"/></label>
                <@spring.formInput "resume.name" 'class="form-control" placeholder="Java Developer"' />
                <@spring.showErrors "<br>" "text-danger small" />
            </div>
            <div class="mb-3">
                <label class="form-label"><@spring.message "resumes.info.salary"/> ($)</label>
                <@spring.formInput "resume.salary" 'type="number" class="form-control"' />
                <@spring.showErrors "<br>" "text-danger small" />
            </div>
            <div class="mb-3">
                <label class="form-label"><@spring.message "resumes.edit.category"/></label>
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

            <h4 class="mb-4"><@spring.message "resumes.info.education"/></h4>
            <div class="p-3 mb-3">
                <div class="mb-3">
                    <label><@spring.message "resumes.edit.institution"/></label>
                    <@spring.formInput "resume.education[0].institution" 'class="form-control"' />
                    <@spring.showErrors "<br>" "text-danger small" />
                </div>
                <div class="mb-3">
                    <label><@spring.message "resumes.edit.eduProgram"/></label>
                    <@spring.formInput "resume.education[0].program" 'class="form-control"' />
                    <@spring.showErrors "<br>" "text-danger small" />
                </div>
                <div class="row mb-3">
                    <div class="col">
                        <label><@spring.message "resumes.edit.esuStart"/></label>
                        <@spring.formInput "resume.education[0].startDate" 'class="form-control"' "date"/>
                        <@spring.showErrors "<br>" "text-danger small" />
                    </div>
                    <div class="col">
                        <label><@spring.message "resumes.edit.esuEnd"/></label>
                        <@spring.formInput "resume.education[0].endDate" 'class="form-control"' "date" />
                        <@spring.showErrors "<br>" "text-danger small" />
                    </div>
                </div>
                <div class="mb-2">
                    <label><@spring.message "resumes.edit.degree"/></label>
                    <@spring.formInput "resume.education[0].degree" 'class="form-control"' />
                    <@spring.showErrors "<br>" "text-danger small" />
                </div>
            </div>

            <h4 class="mb-4"><@spring.message "resumes.info.workExp"/></h4>
            <div class="p-3 mb-3">
                <div class="row mb-3">
                    <div class="col-4">
                        <label><@spring.message "resumes.info.exp"/> (<@spring.message "vacancies.info.body.years"/>)</label>
                        <@spring.formInput "resume.workExperience[0].years" 'type="number" class="form-control"' />
                        <@spring.showErrors "<br>" "text-danger small" />
                    </div>
                    <div class="col-8">
                        <label><@spring.message "resumes.edit.company"/></label>
                        <@spring.formInput "resume.workExperience[0].companyName" 'class="form-control"' />
                        <@spring.showErrors "<br>" "text-danger small" />
                    </div>
                </div>
                <div class="mb-3">
                    <label><@spring.message "resumes.edit.position"/></label>
                    <@spring.formInput "resume.workExperience[0].position" 'class="form-control"' />
                    <@spring.showErrors "<br>" "text-danger small" />
                </div>
                <div class="mb-2">
                    <label><@spring.message "resumes.edit.responsibilities"/></label>
                    <@spring.formTextarea "resume.workExperience[0].responsibilities" 'class="form-control" rows="3"' />
                    <@spring.showErrors "<br>" "text-danger small" />
                </div>
            </div>

            <h4 class="mb-4"><@spring.message"resumes.edit.contacts"/></h4>
            <div class="p-3 mb-3">
                <div class="row">
                    <div class="col-4">
                        <label><@spring.message"resumes.edit.contactType"/></label>
                        <select name="contacts[0].typeId" class="form-control">
                            <#list contactTypes as type>
                                <option value="${type.id}">${type.type}</option>
                            </#list>
                        </select>
                    </div>
                    <div class="col-8">
                        <label><@spring.message "resumes.edit.contactValue"/></label>
                        <@spring.formInput "resume.contacts[0].contactValue" 'class="form-control" placeholder="996700700700"' />
                        <@spring.showErrors "<br>" "text-danger small" />
                    </div>
                </div>
            </div>
            <@spring.formCheckbox "resume.active", "class='form-check-input' id='activeSwitch'" />
            <label class="form-check-label" for="activeSwitch"><@spring.message "resumes.edit.active"/></label>
            <div class="d-flex gap-2 mt-4">
                <button type="submit" class="btn btn-primary"><@spring.message "resumes.edit.buttonSave"/></button>
                <a href="/users/#{user.id}/profile" class="btn btn-outline-secondary"><@spring.message "resumes.edit.buttonCancel"/></a>
            </div>
        </form>
    </div>
</@main.layout>