<#import "../layout.ftlh" as main>
<#import "/spring.ftl" as spring>

<@main.layout ; spring>
    <div class="d-flex justify-content-center mt-5">
        <form action="/resumes/create/${user.id}" method="post" style="width: 100%; max-width: 500px;">
            <h3 class="mb-4"><@spring.message "resumes.create.header"/></h3>
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                       value="${(_csrf.token)!'csrf-token'}">
            </#if>
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

            <h4 class="mb-3 text-secondary"><@spring.message "resumes.info.education"/></h4>

            <div class="p-3 mb-3 ">
                <button type="button" class="btn btn-success my-3" id="educationAddButton">Добавить ещё</button>
                <div id="educationContainer"></div>
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

            <h4 class="mb-3 text-secondary"><@spring.message "resumes.info.workExp"/></h4>

            <div class="p-3 mb-3">
                <button type="button" class="btn btn-success  my-3" id="workExpAddButton">Добавить</button>
                <div id="workExpContainer"></div>
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

            <h4 class="mb-3 text-secondary"><@spring.message "resumes.edit.contacts"/></h4>
            <div class="p-3 mb-3">
                <div class="row">
                    <div class="col-4">
                        <label><@spring.message "resumes.edit.contactType"/></label>
                        <select name="contacts[0].typeId" class="form-control">
                            <#list contactTypes as type>
                                <option value="${type.id}">${type.type}</option>
                            </#list>
                        </select>
                        <@spring.bind "resume.contacts[0].typeId" />
                        <@spring.showErrors "<br>" "text-danger small" />
                    </div>
                    <div class="col-8">
                        <label><@spring.message "resumes.edit.contactValue"/></label>
                        <@spring.formInput "resume.contacts[0].contactValue" 'class="form-control" placeholder="996700700700"' />
                        <@spring.showErrors "<br>" "text-danger small" />
                    </div>
                </div>
            </div>
            <div class="d-flex gap-2 mt-4">
                <button type="submit" class="btn btn-primary"><@spring.message "resumes.create.buttonPost"/></button>
                <a href="/users/#{user.id}/profile" class="btn btn-outline-secondary"><@spring.message "resumes.edit.buttonCancel"/></a>
            </div>
        </form>
    </div>

    <script>

        let addEducationButton = document.getElementById("educationAddButton");
        let educationFormContainer = document.getElementById("educationContainer");

        let addWorkExpButton = document.getElementById("workExpAddButton");
        let workExpFormContainer = document.getElementById("workExpContainer");

        let eduCounter = 0;
        let workExpCounter = 0;

        addEducationButton.addEventListener("click", () => {
            eduCounter++;
            console.log(eduCounter);
            let newEduForm = document.createElement("div");
            newEduForm.className = "mt-3 position-relative";
            newEduForm.innerHTML = `
                        <div class="p-3 mb-3">
                            <div class="mb-3">
                                <label>Учебное заведение</label>
                                <input type="text" name="education[` + eduCounter + `].institution" class="form-control">
                            </div>
                            <div class="mb-3">
                                <label>Программа обучения</label>
                                <input type="text" name="education[` + eduCounter + `].program" class="form-control">
                            </div>
                            <div class="row mb-3">
                                <div class="col">
                                    <label>Дата начала</label>
                                    <input type="date" name="education[` + eduCounter + `].startDate" class="form-control">
                                </div>
                                <div class="col">
                                    <label>Дата окончания</label>
                                    <input type="date" name="education[` + eduCounter + `].endDate" class="form-control">
                                </div>
                            </div>
                            <div class="mb-2">
                                <label>Степень</label>
                                <input type="text" name="education[` + eduCounter + `].degree" class="form-control">
                            </div>
                        <button type="button" class="btn-close remove-button position-absolute end-0 top-0 m-2" aria-label="Close"></button>
                        </div>
                        `;
            educationFormContainer.appendChild(newEduForm);
            newEduForm.querySelector(".remove-button").addEventListener("click", () =>{
                educationFormContainer.removeChild(newEduForm)
            });
        });

        addWorkExpButton.addEventListener("click", () => {
            workExpCounter++;
            console.log(workExpCounter);
            let newWorkExpForm = document.createElement("div");
            newWorkExpForm.className = "mt-3 position-relative";
            newWorkExpForm.innerHTML = `
                        <div class="p-3 mb-3">
                            <div class="row mb-3">
                                <div class="col-4">
                                    <label>Опыт работы (лет)</label>
                                    <input type="number" name="workExperience[` + workExpCounter + `].years" class="form-control">
                                </div>
                                <div class="col-8">
                                    <label>Компания</label>
                                    <input type="text" name="workExperience[` + workExpCounter + `].companyName" class="form-control">
                                </div>
                            </div>
                            <div class="mb-3">
                                <label>Должность</label>
                                <input type="text" name="workExperience[` + workExpCounter + `].position" class="form-control">
                            </div>
                            <div class="mb-2">
                                <label>Обязанности</label>
                                <textarea name="workExperience[` + workExpCounter + `].responsibilities" class="form-control" rows="3"></textarea>
                            </div>
                            <button type="button" class="btn-close remove-button position-absolute end-0 top-0 m-2" aria-label="Close"></button>
                        </div>
                        `;
            workExpFormContainer.appendChild(newWorkExpForm);
            newWorkExpForm.querySelector(".remove-button").addEventListener("click", () =>{
                workExpFormContainer.removeChild(newWorkExpForm)
            });
        });
    </script>
</@main.layout>