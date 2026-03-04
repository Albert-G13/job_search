<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <div class="d-flex justify-content-center">
        <form id="register-form" action="/auth/register" method="post">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                       value="${(_csrf.token)!'csrf-token'}">
            </#if>
            <div class="mb-3">
                <label id="usernameLabel" for="usernameInput" class="form-label"><@spring.message "users.user.name"/></label>
                <@spring.formInput "userRegisterDto.name" 'type="text" class="form-control" id="usernameInput" name="email" aria-describedby="emailHelp"'/>
                <@spring.showErrors "<br>" "error text-danger"/>
            </div>
            <div class="mb-3">
                <label for="exampleInputEmail1" class="form-label"><@spring.message "users.user.email"/></label>
                <@spring.formInput "userRegisterDto.email" 'type="email" class="form-control" id="exampleInputEmail1" name="email" aria-describedby="emailHelp"'/>
                <@spring.showErrors "<br>" "error text-danger"/>
                <div id="emailHelp" class="form-text"><@spring.message "auth.register.emailHelp"/></div>
            </div>
            <div class="mb-3">
                <label for="exampleInputPassword1" class="form-label"><@spring.message "auth.register.password"/></label>
                <@spring.formPasswordInput "userRegisterDto.password" 'type="password" class="form-control" id="exampleInputPassword1" name="password" aria-describedby="passwordHelp"'/>
                <@spring.showErrors "<br>" "error text-danger"/>
            </div>
            <div class="mb-3">
                <label for="exampleInputPhoneNumber1" class="form-label"><@spring.message "auth.register.phone"/></label>
                <@spring.formInput "userRegisterDto.phoneNumber" 'type="number" class="form-control" id="exampleInputPhoneNumber1" name="phoneNumber" aria-describedby="phoneNumberHelp"'/>
                <@spring.showErrors "<p>" "error text-danger"/>
            </div>
            <div class="mb-3">
                <select id="roleSelect" name="roleId" class="form-control">
                    <option value=""><@spring.message "auth.register.role"/></option>
                    <#list roles as role>
                        <option value="${role.id}"> ${role.role}</option>
                    </#list>
                </select>
                <@spring.bind "userRegisterDto.roleId" />
                <@spring.showErrors "<br>" "text-danger small" />
            </div>
            <button type="submit" class="btn btn-primary"><@spring.message "auth.register.button.register"/></button>
        </form>
    </div>
    <script src="/static/js/main.js"></script>
</@main.layout>