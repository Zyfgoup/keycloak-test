<#import "/spring.ftl" as spring />
<#assign xhtmlCompliant = true in spring>
<!DOCTYPE html>
<html>
<head>
    <title>menus Page</title>
</head>
<body>

<header>
    <a href="/logout" id="logout">Logout</a>
</header>



<h1>${msg}</h1>

<#list data.children as item>
    <ul>
        <li>${item.name}</li>
        <#if item.children?? && (item.children?size > 0)>
            <#list item.children as leaf>
            <ul>
                <li>${leaf.name}</li>
            </ul>
            </#list>
        </#if>
    </ul>
</#list>

</body>
</html>
