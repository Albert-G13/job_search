<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="d-flex justify-content-center">
        <form id="register-form" action="/auth/register" method="post">
            <div class="mb-3">
                <label for="exampleInputEmail1" class="form-label">Email address</label>
                <input type="email" class="form-control" id="exampleInputEmail1" name="email" aria-describedby="emailHelp">
                <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
            </div>
            <div class="mb-3">
                <label for="exampleInputPassword1" class="form-label">Password</label>
                <input type="password" class="form-control" id="exampleInputPassword1" name="password">
            </div>
            <div class="mb-3">
                <label for="exampleInputPhoneNumber1" class="form-label">Phone number</label>
                <input type="number" class="form-control" id="exampleInputPhoneNumber1" name="phoneNumber">
            </div>
            <select class="form-select mb-3" name="roleId" aria-label="Default select example">
                <option value="">Choose role</option>
                <option value="1">Applicant</option>
                <option value="2">Employer</option>
            </select>
            <button type="submit" class="btn btn-primary">Register</button>
        </form>
    </div>
</@main.layout>