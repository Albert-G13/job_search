<#import "../layout.ftlh" as main>
<#import "/spring.ftl" as spring />

<@main.layout ; spring>
    <div class="d-flex justify-content-center">
        <form action="/users/${user.id}/edit" method="post" enctype="multipart/form-data">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                       value="${(_csrf.token)!'csrf-token'}">
            </#if>
            <div class="mb-3">
                <label for="name" class="form-label"><@spring.message "users.user.name"/></label>
                <@spring.formInput "userDto.name" 'class="form-control" id="name"' />
                <@spring.showErrors "<br>" "error text-danger"/>
            </div>
            <div class="mb-3">
                <label for="surname" class="form-label"><@spring.message "users.user.surname"/></label>
                <@spring.formInput "userDto.surname" 'class="form-control" id="surname"' />
                <@spring.showErrors "<br>" "error text-danger"/>
            </div>
            <div class="mb-3">
                <label for="age" class="form-label"><@spring.message "users.user.age"/></label>
                <@spring.formInput "userDto.age" 'type="number" class="form-control" id="age"' />
                <@spring.showErrors "<br>" "error text-danger"/>
            </div>
            <div class="mb-3">
                <label for="phoneNumber" class="form-label"><@spring.message "users.user.phone"/></label>
                <@spring.formInput "userDto.phoneNumber" 'class="form-control" id="phoneNumber"' />
                <@spring.showErrors "<br>" "error text-danger"/>
            </div>
            <div class="mb-3">
                <label class="form-label"><@spring.message "users.user.avatar"/></label>
                <input type="file" name="file" class="form-control" accept="image/*">
                <#if userDto.avatar??>
                    <div class="mt-2">
                        <img src="/images/${userDto.avatar}" alt="<@spring.message "users.user.avatar"/>"
                             style="width: 100px; height: 100px; object-fit: cover;">
                    </div>
                </#if>
            </div>
            <button type="submit" class="btn btn-primary"><@spring.message "users.user.button.save"/></button>
            <a href="/users/${user.id}/profile" class="btn btn-outline-secondary"><@spring.message "users.user.button.cancel"/></a>
        </form>
    </div>
</@main.layout>