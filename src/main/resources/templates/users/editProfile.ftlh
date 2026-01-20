<#import "../layout.ftlh" as main>
<#import "/spring.ftl" as spring />

<@main.layout ; spring>
    <div class="d-flex justify-content-center">
        <form action="/users/${user.id}/edit" method="post" enctype="multipart/form-data">
            <div class="mb-3">
                <label for="name" class="form-label">Имя</label>
                <@spring.formInput "userDto.name" 'class="form-control" id="name"' />
                <@spring.showErrors "<br>" "error text-danger"/>
            </div>
            <div class="mb-3">
                <label for="surname" class="form-label">Фамилия</label>
                <@spring.formInput "userDto.surname" 'class="form-control" id="surname"' />
                <@spring.showErrors "<br>" "error text-danger"/>
            </div>
            <div class="mb-3">
                <label for="age" class="form-label">Возраст</label>
                <@spring.formInput "userDto.age" 'type="number" class="form-control" id="age"' />
                <@spring.showErrors "<br>" "error text-danger"/>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <@spring.formInput "userDto.email" 'type="email" class="form-control" id="email"' />
                <@spring.showErrors "<br>" "error text-danger"/>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Пароль (оставьте поле пустым чтобы не менять)</label>
                <@spring.formPasswordInput "userDto.password" 'class="form-control" id="password"' />
                <@spring.showErrors "<br>" "error text-danger"/>
            </div>
            <div class="mb-3">
                <label for="phoneNumber" class="form-label">Номер телефона</label>
                <@spring.formInput "userDto.phoneNumber" 'class="form-control" id="phoneNumber"' />
                <@spring.showErrors "<br>" "error text-danger"/>
            </div>
            <div class="mb-3">
                <label class="form-label">Аватар</label>
                <input type="file" name="file" class="form-control" accept="image/*">
                <#if user.avatar??>
                    <div class="mt-2">
                        <img src="/images/${user.avatar}" alt="Avatar" style="width: 100px; height: 100px; object-fit: cover;">
                    </div>
                </#if>
            </div>
            <button type="submit" class="btn btn-primary">Сохранить</button>
            <a href="/users/${user.id}/profile" class="btn btn-outline-secondary">Отмена</a>
        </form>
    </div>
</@main.layout>