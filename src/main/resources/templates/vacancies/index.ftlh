<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="row">
        <#list vacancies as vacancy>
            <div class="card col-3 m-3" style="width: 18rem;">
                <div class="card-body">
                    <h5 class="card-title">${vacancy.name}</h5>
                    <h6 class="card-subtitle mb-2 text-body-secondary">${vacancy.salary}$</h6>
                    <p class="card-text">${vacancy.description}</p>
                    <a href="#" class="card-link">Info</a>
                </div>
            </div>
        </#list>
    </div>
</@main.layout>