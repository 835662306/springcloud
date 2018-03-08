package com.example.session;

import com.example.dao.UserRepository;
import com.example.entry.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * java web实现同一账号在不同浏览器不能同时登录
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/8 10:13
 * @Version 1.0
 */
@Controller
public class SessionListener implements HttpSessionListener{

    @Autowired
    private UserRepository userRepository;
    /**
     * 用户与session的绑定关系
     */
    private static final Map<String, HttpSession> USER_SESSION = new HashMap<String, HttpSession>();

    /**
     * sessionId与用户的绑定关系
     */
    private static final Map<String, String> SESSIONID_USER = new HashMap<String, String>();

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    /**
     * 清空session
     * @param sessionEvent
     */
    public void sessionDestroyed(HttpSessionEvent sessionEvent){
        String sessionId=sessionEvent.getSession().getId();
        //当前session销毁时删除当前session绑定的用户信息
        //同时删除当前session绑定用户的HttpSession
        USER_SESSION.remove(SESSIONID_USER.remove(sessionId));
    }

    /**
     * 用户登录时的处理
     * 处理一个账号同时只有一个地方登录的关键
     * @param request
     */
    public void userLoginHandle(HttpServletRequest request){
        //获取用户名
        String userName = request.getParameter("userName");
        //获取sessionId
        String sessionId = request.getSession().getId();
        //删除当前sessionId绑定的用户，用户--HttpSession
        USER_SESSION.remove(SESSIONID_USER.remove(sessionId));

        //删除当前登录用户绑定的HttpSession
        HttpSession session=USER_SESSION.remove(userName);
        if(session!=null){
            SESSIONID_USER.remove(session.getId());
            session.removeAttribute("userName");
            session.setAttribute("userMsg", "您的账号已经在另一处登录了,你被迫下线!");
        }
    }

    /**
     * 用户登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void loginService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        if(userName != null && "".equals(userName.trim())){
            if(login(userName, password)){
                HttpSession session=request.getSession();
                //处理用户登录(保持同一时间同一账号只能在一处登录)
                userLoginHandle(request);
                //添加用户与HttpSession的绑定
                USER_SESSION.put(userName.trim(), session);
                //添加sessionId和用户的绑定
                SESSIONID_USER.put(session.getId(), userName);

                session.setAttribute("userName", userName);
                session.removeAttribute("userMsg");
            }
        }
    }


    //对比用户输入的信息是否合法，从而判断是否登录成功
    private boolean login(String userName,String password){
        User userByNickNameAndPswd = userRepository.findUserByNickNameAndPswd(userName, password);
        if(userByNickNameAndPswd != null){
            return true;
        }
        return false;
    }
}
