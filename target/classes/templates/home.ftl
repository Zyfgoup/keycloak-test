<#import "/spring.ftl" as spring />
<#assign xhtmlCompliant = true in spring>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <title>Home Page</title>
</head>
<body>

<header>
     <h3>Welcome, ${identity.name}</h3>

     <p><a href="/logout" id="logout">Logout</a></p>
     <p></p>

     <table style="margin-top: 20px">
          <tr><td style="margin-left: 100px">测试功能权限</td></tr>
     </table>
     <p><a id="mall-test" href="/accessMallTest">访问 http:localhost:11200/mall/test</a></p>
     <p><a id="mall-test" href="/accessIndex">访问 http:localhost:11200/index</a></p>

     <form action="/accessResourceOrg" method="post">
          <table style="margin-top: 20px">
               <tr><td style="margin-left: 100px">测试数据权限--根据机构范围</td></tr>
               <tr><td><p>接口地址 http:localhost:11200/test/data/auth/org</p>
               <tr><td>数据权限范围mallCode：</td><td><input type="text" name="mallCode"></td> <td><input type="submit" value="访问"></td></tr>
          </table>
     </form>

     <form action="/accessResourceValue" method="post">
          <table style="margin-top: 20px">
               <tr><td style="margin-left: 100px">测试数据权限--根据特定值范围</td></tr>
               <tr><td><p>接口地址 http:localhost:11200/test/data/auth/value</p>
               <tr><td>数据权限特定值：</td><td><input type="text" name="value"></td> <td><input type="submit" value="访问"></td></tr>
          </table>
     </form>



     <table style="margin-top: 20px">
          <tr><td style="margin-left: 100px">测试权限--界面按钮</td></tr>
     </table>
     <p><a id="mall-test" href="/buttons">访问test_resource.html</a></p>


     <table style="margin-top: 20px">
          <tr><td style="margin-left: 100px">测试权限--菜单</td></tr>
     </table>
     <p><a id="mall-test" href="/menus">访问</a></p>


     <table style="margin-top: 20px">
          <tr><td style="margin-left: 100px">测试通过名称登录</td></tr>
     </table>
     <p><a id="mall-test" href="/testLoginByName">访问</a></p>


</header>


</body>
</html>