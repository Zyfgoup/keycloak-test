package com.zyfgoup.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zyfgoup.adapter.AdapterService;
import com.zyfgoup.adapter.AuthTokenRequest;
import com.zyfgoup.adapter.IntrospectRequest;
import com.zyfgoup.adapter.KeyCloakService;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class ApplicationController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private AdapterService adapterService;

    @Autowired
    private KeyCloakService keyCloakService;

    @RequestMapping(value = "/accessMallTest", method = RequestMethod.GET)
    public String handleProtected(Model model) {
        String token  = getToken();

        try {
            ApiRespJsonObj apiRespJsonObj = adapterService.mallTest("Bearer " + token);
            if (apiRespJsonObj.isSuccess()) {
                model.addAttribute("msg", apiRespJsonObj.getData());
            } else {
                log.error(apiRespJsonObj.toString());
                model.addAttribute("msg", apiRespJsonObj.getMsg());
            }
        }catch (Exception e){
            model.addAttribute("msg",e.getMessage());
        }

        return "protected";
    }

    @RequestMapping(value = "/accessIndex", method = RequestMethod.GET)
    public String accessIndex(Model model) {
        String token  = getToken();

        try {
            ApiRespJsonObj apiRespJsonObj = adapterService.index("Bearer " + token);
            if (apiRespJsonObj.isSuccess()) {
                model.addAttribute("msg", apiRespJsonObj.getData());
            } else {
                log.error(apiRespJsonObj.toString());
                model.addAttribute("msg", apiRespJsonObj.getMsg());
            }
        }catch (Exception e){
            model.addAttribute("msg",e.getMessage());
        }

        return "protected";
    }

    @RequestMapping(value = "/accessResourceOrg", method = RequestMethod.POST)
    public String accessResourceOrg(@RequestParam("mallCode")String mallCode,Model model) {
        String token  = getToken();

        try {
            ApiRespJsonObj apiRespJsonObj = adapterService.dataAuthOrg("Bearer " + token,mallCode);
            if (apiRespJsonObj.isSuccess()) {
                model.addAttribute("msg","access successful");
                model.addAttribute("data", apiRespJsonObj.getData());
            } else {
                log.error(apiRespJsonObj.toString());
                model.addAttribute("msg", apiRespJsonObj.getMsg());
            }
        }catch (Exception e){
            model.addAttribute("msg",e.getMessage());
            model.addAttribute("data","");
        }
        return "premium";
    }



    @RequestMapping(value = "/accessResourceValue", method = RequestMethod.POST)
    public String accessResourceValue(@RequestParam("value")String value,Model model) {
        String token  = getToken();

        try {
            ApiRespJsonObj apiRespJsonObj = adapterService.dataAuthValue("Bearer " + token,value);
            if (apiRespJsonObj.isSuccess()) {
                model.addAttribute("msg","access successful");
                model.addAttribute("data", apiRespJsonObj.getData());
            } else {
                log.error(apiRespJsonObj.toString());
                model.addAttribute("msg", apiRespJsonObj.getMsg());
            }
        }catch (Exception e){
            model.addAttribute("msg",e.getMessage());
            model.addAttribute("data","");
        }
        return "premium";
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String handleLogoutt() throws ServletException {
        request.logout();
        return "redirect:/";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String handleHome(Model model) throws ServletException {
        configCommonAttributes(model);
        return "home";
    }

    @RequestMapping(value = "/access-denied", method = RequestMethod.GET)
    public String handleAccessDenied() throws ServletException {
        return "access-denied";
    }


    @RequestMapping(value = "/menus", method = RequestMethod.GET)
    public String getMenus(Model model) throws ServletException {
        String token  = getToken();

        try {
            ApiRespJsonObj apiRespJsonObj = adapterService.testMenu("Bearer " + token);
            if (apiRespJsonObj.isSuccess()) {
                model.addAttribute("msg","access successful");
                model.addAttribute("data", apiRespJsonObj.getData());
            } else {
                log.error(apiRespJsonObj.toString());
                model.addAttribute("msg", apiRespJsonObj.getMsg());
            }
        }catch (Exception e){
            model.addAttribute("msg",e.getMessage());
            model.addAttribute("data","");
        }

        return "menus";
    }

    @RequestMapping(value = "/buttons", method = RequestMethod.GET)
    public String getButtons(Model model) throws ServletException {
        AuthTokenRequest request = new AuthTokenRequest();
       request.setGrant_type("urn:ietf:params:oauth:grant-type:uma-ticket");
        request.setAudience("test-client");
        request.setPermission("test_resource.html");
        String accessToken  = "Bearer "+getToken();
        JSONObject rpt = keyCloakService.getRPT(accessToken, request);

        String rptToken = (String)rpt.get("access_token");


        IntrospectRequest request1 = new IntrospectRequest();
        request1.setClient_id("test-client");
        request1.setClient_secret("J3wg0LQyH9YwW82mLhkFcC1GULQqs0nx");
        request1.setToken(rptToken);
        request1.setToken_type_hint("requesting_party_token");
        JSONObject instrspect = keyCloakService.instrspect(request1);
        JSONArray permissions = instrspect.getJSONArray("permissions");
        JSONArray scopes = permissions.getJSONObject(0).getJSONArray("scopes");
        List<String> buttons = new ArrayList<>();
        for (int i  = 0; i <scopes.size() ; i++) {
            buttons.add(scopes.getString(i).substring(scopes.getString(i).indexOf("_")+1));
        }

        model.addAttribute("msg","access successful");
        model.addAttribute("buttonResources",buttons);
        return "button";
    }


    @RequestMapping(value = "/testLoginByName", method = RequestMethod.GET)
    public String testLoginByName(Model model) {

        LoginRequest request = new LoginRequest("test02","test-client","J3wg0LQyH9YwW82mLhkFcC1GULQqs0nx","password");

        String jsonParam = JSON.toJSONString(request);

        Map<String,Object> map = JSON.parseObject(jsonParam, Map.class);


        String s = HttpClientUtil.sendPostUrlEncoded("http://47.102.192.4:8080/realms/SCPG/protocol/openid-connect/token", map);

        JSONObject jsonObject = JSONObject.parseObject(s);

        return jsonObject.getString("access_token");
    }




    private void configCommonAttributes(Model model) {
        model.addAttribute("identity", new Identity(getKeycloakSecurityContext()));
    }

    private KeycloakSecurityContext getKeycloakSecurityContext() {
        KeycloakSecurityContext  attribute = (KeycloakSecurityContext)request.getAttribute(KeycloakSecurityContext.class.getName());
        return attribute;
    }

    private String getToken(){
        return getKeycloakSecurityContext().getTokenString();
    }

}
