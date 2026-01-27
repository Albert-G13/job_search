<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="row">
        <#list resumes.getContent() as resume>
            <div class="card col-3 m-3" style="width: 18rem;">
                <div class="card-body">
                    <h5 class="card-title">${resume.name}</h5>
                    <h6 class="card-subtitle mb-2 text-body-secondary">${resume.salary}$</h6>
                    <p class="card-text">Дата обновления: ${resume.updateTime.toLocalDate()}</p>
                </div>
            </div>
        </#list>
    </div>
</@main.layout>