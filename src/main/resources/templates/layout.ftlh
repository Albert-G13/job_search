<#import "spring.ftl" as spring>
<#macro layout>
    <!doctype html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <meta name="_csrf_header" content="${_csrf.headerName}">
        <meta name="_csrf_token" content="${_csrf.token}">
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
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo01"
                    aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
                <a class="navbar-brand" href="/">Job search</a>
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <#if user??>
                            <a class="nav-link active" aria-current="page"
                               href="/users/${user.id}/profile"><@spring.message "layout.nav.profile"/></a>
                        </#if>
                    </li>
                    <#if user??>
                        <#if user.role.role == "APPLICANT">
                            <li class="nav-item">
                                <a class="nav-link" href="/vacancies"><@spring.message "layout.nav.vacancies"/></a>
                            </li>
                        <#else>
                            <li class="nav-item">
                                <a class="nav-link" href="/vacancies"><@spring.message "layout.nav.vacancies"/></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/resumes"><@spring.message "layout.nav.resumes"/></a>
                            </li>
                        </#if>
                    </#if>
                </ul>
                <div class="dropdown mx-1">
                    <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown"
                            aria-expanded="false">
                        <@spring.message "layout.nav.lang.change"/>
                    </button>
                    <#assign savedToken = (RequestParameters.token)!(resetPassword.token)!(token)!"" />

                    <ul class="dropdown-menu">
                        <#assign cleanQuery = (springMacroRequestContext.queryString!'')?replace('lang=[a-z]{2}', '', 'r')?replace('(^&|&$|(?<=&)&)', '', 'r')>
                        <#assign finalParams = cleanQuery>
                        <#if !finalParams?contains("token=") && savedToken?has_content>
                            <#assign finalParams = "token=" + savedToken + (finalParams?has_content?then("&" + finalParams, ""))>
                        </#if>

                        <li>
                            <a class="dropdown-item"
                               href="?${finalParams}<#if finalParams?has_content>&</#if>lang=en"><@spring.message "layout.nav.lang_en"/></a>
                        </li>
                        <li>
                            <a class="dropdown-item"
                               href="?${finalParams}<#if finalParams?has_content>&</#if>lang=ru"><@spring.message "layout.nav.lang_ru"/></a>
                        </li>
                    </ul>
                </div>
                <#if user??><a href="/auth/logout" type="button"
                               class="btn btn-outline-danger"><@spring.message "layout.nav.logout"/></a>
                <#else>
                    <a href="/auth/register" type="button"
                       class="btn btn-outline-info mx-3"><@spring.message "layout.nav.register"/></a>
                    <a href="/auth/login" type="button"
                       class="btn btn-outline-success"><@spring.message "layout.nav.login"/></a>
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