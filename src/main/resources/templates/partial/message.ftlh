<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <div class="text-container">
        <h2><@spring.message "partial.message.header"/></h2>
        <b class="text-success">${message}</b>
    </div>
</@main.layout>