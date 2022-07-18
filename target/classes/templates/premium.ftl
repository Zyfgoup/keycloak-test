<#import "/spring.ftl" as spring />
<#assign xhtmlCompliant = true in spring>
<!DOCTYPE html>
<html>
<head>
    <title>Premium Page</title>
</head>
<body>

<header>
     <a href="/logout" id="logout">Logout</a>
</header>

<h1>${msg}</h1>
<h5>${data}</h5>

</body>
</html>
