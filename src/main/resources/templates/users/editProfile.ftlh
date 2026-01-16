<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="d-flex justify-content-center">
        <form action="/users/${user.id}/edit" method="post">
            <div class="mb-3">
                <label for="name" class="form-label">Name</label>
                <input type="text" class="form-control" id="name" name="name" aria-describedby="emailHelp" placeholder="${user.name!''}">
            </div>
            <div class="mb-3">
                <label for="surname" class="form-label">Surname</label>
                <input type="text" class="form-control" id="surname" name="surname" placeholder="${user.surname!''}">
            </div>
            <div class="mb-3">
                <label for="age" class="form-label">Age</label>
                <input type="number" class="form-control" id="age" name="age" placeholder="${user.age!''}">
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email" placeholder="${user.email!''}">
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password">
            </div>
            <div class="mb-3">
                <label for="phoneNumber" class="form-label">Phone</label>
                <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" placeholder="${user.phoneNumber!''}">
            </div>
            <div class="mb-3">
                <label for="avatar" class="form-label">Avatar</label>
                <input type="text" class="form-control" id="avatar" name="avatar" placeholder="${user.avatar!''}">
            </div>
            <button type="submit" class="btn btn-primary">Save</button>
        </form>
    </div>
</@main.layout>
