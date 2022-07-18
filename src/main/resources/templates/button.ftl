<#import "/spring.ftl" as spring />
<#assign xhtmlCompliant = true in spring>
<!DOCTYPE html>
<html>
<head>
    <title>Button Page</title>
</head>
<body>

<header>
    <a href="/logout" id="logout">Logout</a>
</header>

<h1>${msg}</h1>
<#list buttonResources as item>
    <p><input type="submit" value="${item}"></p>
</#list>


</body>
</html>
