<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container text-center">
        <#if user??>
            <#if user.role.role == "APPLICANT">
                <h1>Личный кабинет Соискателя</h1>
            <#else>
                <h1>Личный кабинет Работодателя</h1>
            </#if>
            <div class="row mt-3">
                <div class="col">
                    <div class="row">
                        <div class="col">  <#if userDto.avatar??>
                                <img src="/images/${userDto.avatar}"
                                     alt="Аватар"
                                     class="img-thumbnail shadow-sm">
                            <#else>
                                <img src="https://cdn-icons-png.flaticon.com/512/149/149071.png"
                                     alt="Дефолтный аватар"
                                     class="img-thumbnail">
                            </#if>
                        </div>
                        <div class="col"><#if (userDto.name?? && userDto.surname??)>
                                <h2 class="mt-3">${userDto.name} ${userDto.surname}</h2>
                            <#else>
                                <h2 class="mt-3">Неизвестно Неизвестно</h2>
                            </#if>
                            <#if (userDto.age??)>
                                <h4 class="mt-3">Возраст: ${userDto.age}</h4>
                            <#else>
                                <h4 class="mt-3">Возраст: Неизвестно</h4>
                            </#if>
                            <a href="/users/${user.id}/edit" data-mdb-button-init data-mdb-ripple-init class="btn btn-primary btn-block mb-4 mt-3">Редактировать</a>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <#if user.role.role == "APPLICANT">
                        <a href="/resumes/create/${user.id}" data-mdb-button-init data-mdb-ripple-init class="btn btn-success btn-block mb-4 mt-3">Создать резюме</a>
                    <#else>
                        <a href="/vacancies/create/${user.id}" data-mdb-button-init data-mdb-ripple-init class="btn btn-success btn-block mb-4 mt-3">Создать вакансию</a>
                    </#if>
                </div>
            </div>
            <#if user.role.role == "APPLICANT">
                <div class="row">
                    <h2>Мои Резюме</h2>
                    <div class="d-flex justify-content-end mb-3">
                        <form method="get" action="">
                            <select name="size" onchange="this.form.submit()" class="form-select form-select-sm" style="width: auto;">
                                <option>Количество отображаемых карточек</option>
                                <option value="8" ${ (resumes.size == 8)?string('selected', '') }>Отображается 8 карточек</option>
                                <option value="12" ${ (resumes.size == 12)?string('selected', '') }>Отображается 12 карточек</option>
                                <option value="16" ${ (resumes.size == 16)?string('selected', '') }>Отображается 16 карточек</option>
                            </select>
                            <input type="hidden" name="page" value="0">
                            <#if RequestParameters.sort??>
                                <input type="hidden" name="sort" value="${RequestParameters.sort}">
                            </#if>
                        </form>
                    </div>
                    <#list resumes.getContent() as resume>
                        <div class="card col-3 m-3" style="width: 18rem;">
                            <div class="card-body">
                                <h5 class="card-title">${resume.name}</h5>
                                <h6 class="card-subtitle mb-2 text-body-secondary">${resume.salary}$</h6>
                                <p class="card-text">Дата публикации: ${resume.createdDate.toLocalDate()}</p>
                                <p class="card-text">Дата обновления: ${resume.updateTime.toLocalDate()}</p>
                                <form action="/resumes/${resume.id}/update" method="post">
                                    <button type="submit" class="btn btn-info mb-2">Обновить дату</button>
                                </form>
                                <a href="/resumes/#{resume.id}/edit" class="btn btn-success">Редактировать</a>
                            </div>
                        </div>
                    </#list>
                </div>
                <nav aria-label="Page navigation" class="mt-5">
                    <ul class="pagination justify-content-center">
                        <#assign url = "?size=${resumes.size}">
                        <#if RequestParameters.sort??>
                            <#assign url = url + "&sort=" + RequestParameters.sort>
                        </#if>
                        <li class="page-item ${resumes.isFirst()?string('disabled', '')}">
                            <a class="page-link" href="${url}&page=0">« Первая</a>
                        </li>

                        <li class="page-item ${resumes.hasPrevious()?string('', 'disabled')}">
                            <a class="page-link" href="${url}&page=${resumes.number - 1}">Назад</a>
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
                            <a class="page-link" href="${url}&page=${resumes.number + 1}">Вперед</a>
                        </li>

                        <li class="page-item ${resumes.isLast()?string('disabled', '')}">
                            <a class="page-link" href="${url}&page=${resumes.totalPages - 1}">Последняя »</a>
                        </li>

                    </ul>
                </nav>
            <#else>
                <div class="row">
                    <h2>Мои Вакансии</h2>
                    <div class="d-flex justify-content-end mb-3">
                        <form method="get" action="">
                            <select name="size" onchange="this.form.submit()" class="form-select form-select-sm" style="width: auto;">
                                <option>Количество отображаемых карточек</option>
                                <option value="8" ${ (vacancies.size == 8)?string('selected', '') }>Отображается 8 карточек</option>
                                <option value="12" ${ (vacancies.size == 12)?string('selected', '') }>Отображается 12 карточек</option>
                                <option value="16" ${ (vacancies.size == 16)?string('selected', '') }>Отображается 16 карточек</option>
                            </select>
                            <input type="hidden" name="page" value="0">
                            <#if RequestParameters.sort??>
                                <input type="hidden" name="sort" value="${RequestParameters.sort}">
                            </#if>
                        </form>
                    </div>
                    <#list vacancies.getContent() as vacancy>
                        <div class="card col-3 m-3" style="width: 18rem;">
                            <div class="card-body">
                                <h5 class="card-title">${vacancy.name}</h5>
                                <h6 class="card-subtitle mb-2 text-body-secondary">${vacancy.salary}$</h6>
                                <p class="card-text">${vacancy.description}</p>
                                <p class="card-text">Дата обновления: ${vacancy.updateTime.toLocalDate()}</p>
                                <form action="/vacancies/${vacancy.id}/update" method="post">
                                    <button type="submit" class="btn btn-info mb-2">Обновить</button>
                                </form>
                                <a href="/vacancies/#{vacancy.id}/edit" class="btn btn-success">Редактировать</a>
                            </div>
                        </div>
                    </#list>
                </div>
                <nav aria-label="Page navigation" class="mt-5">
                    <ul class="pagination justify-content-center">

                        <#assign url = "?size=${vacancies.size}">
                        <#if RequestParameters.sort??>
                            <#assign url = url + "&sort=" + RequestParameters.sort>
                        </#if>

                        <li class="page-item ${vacancies.isFirst()?string('disabled', '')}">
                            <a class="page-link" href="${url}&page=0">« Первая</a>
                        </li>

                        <li class="page-item ${vacancies.hasPrevious()?string('', 'disabled')}">
                            <a class="page-link" href="${url}&page=${vacancies.number - 1}">Назад</a>
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
                            <a class="page-link" href="${url}&page=${vacancies.number + 1}">Вперед</a>
                        </li>

                        <li class="page-item ${vacancies.isLast()?string('disabled', '')}">
                            <a class="page-link" href="${url}&page=${vacancies.totalPages - 1}">Последняя »</a>
                        </li>

                    </ul>
                </nav>
            </#if>
        <#else>
            <h1>Пользователь не найден</h1>
        </#if>
    </div>
</@main.layout>