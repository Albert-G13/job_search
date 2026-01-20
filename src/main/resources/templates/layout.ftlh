<#import "spring.ftl" as spring>
<#macro layout>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
            crossorigin="anonymous"></script>
    <title>Job search</title>
</head>
<body style="display: flex; flex-direction: column; min-height: 100vh;">

<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
            <a class="navbar-brand" href="/">Job search</a>
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <#if user??>
                        <a class="nav-link active" aria-current="page" href="/users/${user.id}/profile">Profile</a>
                    </#if>
                </li>
                <li class="nav-item">
                    <#if user??>
                        <#if user.role == "APPLICANT">
                            <a class="nav-link" href="/vacancies">Vacancies</a>
                        <#else>
                            <a class="nav-link" href="/resumes">Resumes</a>
                        </#if>
                    </#if>
                </li>
            </ul>
            <#if user??><a href="/auth/logout" type="button" class="btn btn-outline-danger">Logout</a>
            <#else>
                <a href="/auth/register" type="button" class="btn btn-outline-info mx-3">Register</a>
                <a href="/auth/login" type="button" class="btn btn-outline-success">Login</a>
            </#if>

        </div>
    </div>
</nav>

<div class="container py-5">
    <#nested spring>
</div>

<footer class="footer py-3 mt-auto bg-body-tertiary">
    <div class="container">
        <p>Job Search</p>
    </div>
</footer>

</body>
</html>
</#macro>