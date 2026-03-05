<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <div class="container text-center">
        <#if user??>
            <#if user.role.role == "APPLICANT">
                <h1><@spring.message "users.profile.cabinetOfApplicant"/></h1>
            <#else>
                <h1><@spring.message "users.profile.cabinetOfEmployer"/></h1>
            </#if>
            <div class="row mt-3">
                <div class="col">
                    <div class="row">
                        <div class="col">  <#if userDto.avatar??>
                                <img src="/images/${userDto.avatar}"
                                     alt="<@spring.message "users.profile.altImg"/>"
                                     class="img-thumbnail shadow-sm">
                            <#else>
                                <img src="https://cdn-icons-png.flaticon.com/512/149/149071.png"
                                     alt="<@spring.message "users.profile.defaultAltImg"/>"
                                     class="img-thumbnail">
                            </#if>
                        </div>
                        <div class="col"><#if (userDto.name?? && userDto.surname??)>
                                <h2 class="mt-3">${userDto.name} ${userDto.surname}</h2>
                            <#else>
                                <h2 class="mt-3"><@spring.message "users.profile.unknown"/> <@spring.message "users.profile.unknown"/></h2>
                            </#if>
                            <#if (userDto.age?? && userDto.age != 0)>
                                <h4 class="mt-3"><@spring.message "users.profile.age"/>: ${userDto.age}</h4>
                            <#else>
                                <h4 class="mt-3"><@spring.message "users.profile.age"/>: <@spring.message "users.profile.unknown"/></h4>
                            </#if>
                            <button type="button" class="btn btn-primary btn-block mb-4 mt-3" data-bs-toggle="modal" data-bs-target="#editProfileModal">
                                <@spring.message "users.profile.button.edit"/>
                            </button>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <#if user.role.role == "APPLICANT">
                        <a href="/resumes/create/${user.id}" data-mdb-button-init data-mdb-ripple-init
                           class="btn btn-success btn-block mb-4 mt-3"><@spring.message "users.profile.button.createResume"/></a>
                    <#else>
                        <a href="/vacancies/create/${user.id}" data-mdb-button-init data-mdb-ripple-init
                           class="btn btn-success btn-block mb-4 mt-3"><@spring.message "users.profile.button.createVacancy"/></a>
                    </#if>
                </div>
            </div>
            <#if user.role.role == "APPLICANT">
                <div class="row">
                    <h2><@spring.message "users.profile.myResumes"/></h2>

                    <div class="d-flex justify-content-end mb-4">
                        <form method="get" action="">
                            <select name="size" onchange="this.form.submit()" class="form-select form-select-sm"
                                    style="width: auto;">
                                <option><@spring.message "users.profile.cards.displayedHeader"/></option>
                                <option value="8" ${ (resumes.size == 8)?string('selected', '') }>8 <@spring.message "users.profile.cards.displayed"/>
                                </option>
                                <option value="12" ${ (resumes.size == 12)?string('selected', '') }>12 <@spring.message "users.profile.cards.displayed"/>
                                </option>
                                <option value="16" ${ (resumes.size == 16)?string('selected', '') }>16 <@spring.message "users.profile.cards.displayed"/>
                                </option>
                            </select>
                            <input type="hidden" name="page" value="0">
                            <#if RequestParameters.sort??>
                                <input type="hidden" name="sort" value="${RequestParameters.sort}">
                            </#if>
                        </form>
                    </div>
                    <div class="row row-cols-1 row-cols-md-2 row-cols-lg-4 g-4">
                        <#list resumes.getContent() as resume>
                            <div class="col">
                                <div class="card h-100">
                                    <div class="card-body d-flex flex-column">
                                        <h5 class="card-title">${resume.name}</h5>
                                        <h6 class="card-subtitle mb-2 text-primary font-weight-bold">${resume.salary}
                                            $</h6>

                                        <p class="card-text"><@spring.message "users.profile.cards.postDate"/>: ${resume.createdDate.toLocalDate()}</p>
                                        <p class="card-text mt-auto mb-3 text-muted small"><@spring.message "users.profile.cards.updatedDate"/>: ${resume.updateTime.toLocalDate()}</p>
                                        <div class="d-grid gap-2">
                                            <form action="/resumes/${resume.id}/update" method="post" class="m-0">
                                                <button type="submit" class="btn btn-outline-info btn-sm w-100"><@spring.message "users.profile.cards.updateDate"/>
                                                </button>
                                            </form>
                                            <a href="/resumes/#{resume.id}/edit" class="btn btn-success btn-sm"><@spring.message "users.profile.button.edit"/></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </#list>
                    </div>
                </div>
                <nav aria-label="Page navigation" class="mt-5">
                    <ul class="pagination justify-content-center">
                        <#assign url = "?size=${resumes.size}">
                        <#if RequestParameters.sort??>
                            <#assign url = url + "&sort=" + RequestParameters.sort>
                        </#if>
                        <li class="page-item ${resumes.isFirst()?string('disabled', '')}">
                            <a class="page-link" href="${url}&page=0">« <@spring.message "users.profile.button.first"/></a>
                        </li>

                        <li class="page-item ${resumes.hasPrevious()?string('', 'disabled')}">
                            <a class="page-link" href="${url}&page=${resumes.number - 1}"><@spring.message "users.profile.button.back"/></a>
                        </li>

                        <#assign current = resumes.number>
                        <#assign total = resumes.totalPages>
                        <#assign begin = (current - 2 > 0)?string(current - 2, 0)?number>
                        <#assign end = (current + 2 < total - 1)?string(current + 2, total - 1)?number>

                        <#list begin..end as i>
                            <li class="page-item ${(current == i)?string('active', '')}">
                                <a class="page-link" href="${url}&page=${i}">${i + 1}</a>
                            </li>
                        </#list>

                        <li class="page-item ${resumes.hasNext()?string('', 'disabled')}">
                            <a class="page-link" href="${url}&page=${resumes.number + 1}"><@spring.message "users.profile.button.next"/></a>
                        </li>

                        <li class="page-item ${resumes.isLast()?string('disabled', '')}">
                            <a class="page-link" href="${url}&page=${resumes.totalPages - 1}"><@spring.message "users.profile.button.last"/> »</a>
                        </li>

                    </ul>
                </nav>
            <#else>
                <div class="row">
                    <h2><@spring.message "users.profile.myVacancies"/></h2>

                    <div class="d-flex justify-content-end mb-4">
                        <form method="get" action="">
                            <select name="size" onchange="this.form.submit()" class="form-select form-select-sm"
                                    style="width: auto;">
                                <option><@spring.message "users.profile.cards.displayedHeader"/></option>
                                <option value="8" ${ (resumes.size == 8)?string('selected', '') }>8 <@spring.message "users.profile.cards.displayed"/>
                                </option>
                                <option value="12" ${ (resumes.size == 12)?string('selected', '') }>12 <@spring.message "users.profile.cards.displayed"/>
                                </option>
                                <option value="16" ${ (resumes.size == 16)?string('selected', '') }>16 <@spring.message "users.profile.cards.displayed"/>
                                </option>
                            </select>
                            <input type="hidden" name="page" value="0">
                            <#if RequestParameters.sort??>
                                <input type="hidden" name="sort" value="${RequestParameters.sort}">
                            </#if>
                        </form>
                    </div>

                    <div class="row row-cols-1 row-cols-md-2 row-cols-lg-4 g-4">
                        <#list vacancies.getContent() as vacancy>
                            <div class="col">
                                <div class="card h-100">
                                    <div class="card-body d-flex flex-column">
                                        <h5 class="card-title">${vacancy.name}</h5>
                                        <h6 class="card-subtitle mb-2 text-primary font-weight-bold">${vacancy.salary}
                                            $</h6>

                                        <p class="card-text">${vacancy.description}</p>
                                        <p class="card-text mt-auto mb-3 text-muted small">
                                            <@spring.message "users.profile.cards.updatedDate"/>: ${vacancy.updateTime.toLocalDate()}
                                        </p>

                                        <div class="d-grid gap-2">
                                            <form action="/vacancies/${vacancy.id}/update" method="post" class="m-0">
                                                <button type="submit" class="btn btn-outline-info btn-sm w-100">
                                                    <@spring.message "users.profile.cards.updateDate"/>
                                                </button>
                                            </form>
                                            <a href="/vacancies/${vacancy.id}/edit" class="btn btn-success btn-sm"><@spring.message "users.profile.button.edit"/></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </#list>
                    </div>
                </div>

                <nav aria-label="Page navigation" class="mt-5">
                    <ul class="pagination justify-content-center">

                        <#assign url = "?size=${vacancies.size}">
                        <#if RequestParameters.sort??>
                            <#assign url = url + "&sort=" + RequestParameters.sort>
                        </#if>

                        <li class="page-item ${vacancies.isFirst()?string('disabled', '')}">
                            <a class="page-link" href="${url}&page=0">« <@spring.message "users.profile.button.first"/></a>
                        </li>

                        <li class="page-item ${vacancies.hasPrevious()?string('', 'disabled')}">
                            <a class="page-link" href="${url}&page=${vacancies.number - 1}"><@spring.message "users.profile.button.back"/></a>
                        </li>

                        <#assign current = vacancies.number>
                        <#assign total = vacancies.totalPages>
                        <#assign begin = (current - 2 > 0)?string(current - 2, 0)?number>
                        <#assign end = (current + 2 < total - 1)?string(current + 2, total - 1)?number>

                        <#list begin..end as i>
                            <li class="page-item ${(current == i)?string('active', '')}">
                                <a class="page-link" href="${url}&page=${i}">${i + 1}</a>
                            </li>
                        </#list>

                        <li class="page-item ${vacancies.hasNext()?string('', 'disabled')}">
                            <a class="page-link" href="${url}&page=${vacancies.number + 1}"><@spring.message "users.profile.button.next"/></a>
                        </li>

                        <li class="page-item ${vacancies.isLast()?string('disabled', '')}">
                            <a class="page-link" href="${url}&page=${vacancies.totalPages - 1}"><@spring.message "users.profile.button.last"/> »</a>
                        </li>

                    </ul>
                </nav>
            </#if>
        <#else>
            <h1><@spring.message "users.profile.userNotFound"/></h1>
        </#if>
    </div>

    <div class="modal fade" id="editProfileModal" tabindex="-1" aria-labelledby="editProfileModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editProfileModalLabel"><@spring.message "users.profile.button.edit"/></h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form action="/users/${user.id}/edit" method="post" enctype="multipart/form-data">
                    <div class="modal-body text-start">
                        <#if _csrf??>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}">
                        </#if>

                        <div class="mb-3">
                            <label for="name" class="form-label"><@spring.message "users.user.name"/></label>
                            <@spring.formInput "userDto.name" 'class="form-control" id="name"' />
                            <@spring.showErrors "<br>" "text-danger small"/>
                        </div>
                        <div class="mb-3">
                            <label for="surname" class="form-label"><@spring.message "users.user.surname"/></label>
                            <@spring.formInput "userDto.surname" 'class="form-control" id="surname"' />
                            <@spring.showErrors "<br>" "text-danger small"/>
                        </div>
                        <div class="mb-3">
                            <label for="age" class="form-label"><@spring.message "users.user.age"/></label>
                            <@spring.formInput "userDto.age" 'type="number" class="form-control" id="age"' />
                            <@spring.showErrors "<br>" "text-danger small"/>
                        </div>
                        <div class="mb-3">
                            <label for="phoneNumber" class="form-label"><@spring.message "users.user.phone"/></label>
                            <@spring.formInput "userDto.phoneNumber" 'class="form-control" id="phoneNumber"' />
                            <@spring.showErrors "<br>" "text-danger small"/>
                        </div>
                        <div class="mb-3">
                            <label class="form-label"><@spring.message "users.user.avatar"/></label>
                            <input type="file" name="file" class="form-control" accept="image/*">
                        </div>

                        <#if errorMessage??>
                            <div class="alert alert-danger">${errorMessage}</div>
                        </#if>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><@spring.message "users.user.button.cancel"/></button>
                        <button type="submit" class="btn btn-primary"><@spring.message "users.user.button.save"/></button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <#if bindingResult?? && bindingResult.hasErrors() || errorMessage??>
        <script>
            document.addEventListener("DOMContentLoaded", function() {
                var myModal = new bootstrap.Modal(document.getElementById('editProfileModal'));
                myModal.show();
            });
        </script>
    </#if>
</@main.layout>