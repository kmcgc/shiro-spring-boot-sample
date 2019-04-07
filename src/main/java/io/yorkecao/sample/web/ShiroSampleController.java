package io.yorkecao.sample.web;

import io.yorkecao.sample.service.ShiroSampleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yorke
 */
@RestController
public class ShiroSampleController {

    @Autowired
    private ShiroSampleService shiroSampleService;

    //    @GetMapping("/login")
    public void login(String username, String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.login(token);
    }

    @GetMapping("/logout")
    public void logout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
    }

    @PostMapping("/login")
    @ResponseBody
    public String index(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        login(username, password);
        return "ok";
    }

    @GetMapping("/read")
    public String read() {
        return this.shiroSampleService.read();
    }

    @GetMapping("/write")
    public String write() {
        return this.shiroSampleService.write();
    }
}
