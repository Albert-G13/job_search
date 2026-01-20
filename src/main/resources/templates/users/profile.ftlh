<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container text-center">
        <#if user??>
            <#if user.role == "APPLICANT">
                <h1>Личный кабинет Соискателя</h1>
            <#else>
                <h1>Личный кабинет Работодателя</h1>
            </#if>
            <div class="row mt-3">
                <div class="col">
                    <div class="row">
                        <div class="col">  <#if user.avatar??>
                                <img src="/images/${user.avatar}"
                                     alt="Аватар"
                                     class="img-thumbnail shadow-sm">
                            <#else>
                                <img src="https://cdn-icons-png.flaticon.com/512/149/149071.png"
                                     alt="Дефолтный аватар"
                                     class="img-thumbnail">
                            </#if>
                        </div>
                        <div class="col"><#if (user.name?? && user.surname??)>
                                <h2 class="mt-3">${user.name} ${user.surname}</h2>
                            <#else>
                                <h2 class="mt-3">Неизвестно Неизвестно</h2>
                            </#if>
                            <#if (user.age??)>
                                <h4 class="mt-3">Возраст: ${user.age}</h4>
                            <#else>
                                <h4 class="mt-3">Возраст: Неизвестно</h4>
                            </#if>
                            <a href="/users/${user.id}/edit" data-mdb-button-init data-mdb-ripple-init class="btn btn-primary btn-block mb-4 mt-3">Редактировать</a>
                        </div>
                    </div>
                </div>
                <div class="col">
                </div>
            </div>
        <#else>
            <h1>Пользователь не найден</h1>
        </#if>
    </div>
</@main.layout>