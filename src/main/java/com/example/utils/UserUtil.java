package com.example.utils;

import com.example.entry.User;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/14 9:20
 * @Version 1.0
 */
public class UserUtil {

    /**
     * 验证用户名
     * @param userName
     * @return
     */
    private static boolean validateUserName(String userName) {
        if(StringUtils.isNotBlank(userName) && userName.indexOf(" ") == -1){
            if(userName.trim().length() < 4 || userName.trim().length() > 12){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }

    /**
     * 验证密码
     * @param password
     * @return
     */
    public static boolean validatePassword(String password){
        if(password.trim().length() < 6 || password.trim().length() > 16){
            return false;
        }
        return true;
    }

    /**
     * 验证邮箱
     * @param email
     * @return
     */
    public static boolean validateEmail(String email){
        if(StringUtils.isBlank(email)){
            return false;
        }
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(email);
        return matcher.matches();
    }

    /**
     * 验证手机号码
     * @param phone
     * @return
     */
    public static boolean validatePhone(String phone){
        if(StringUtils.isBlank(phone)){
            return false;
        }
        String check = "^((13[0-9])|(15[^4,\\D])|(18[0-9])|(17[0135-9])|(14[57])|(166)|(19[89]))\\d{8}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(phone);
        boolean isMatched = matcher.matches();
        return isMatched;
    }

    //设置session信息
    public static boolean setLoginSession(HttpSession session, User user, String[] otherParaNames, String[] otherParams){
        session.setAttribute("user", user);
        session.setAttribute("loginName", user.getNickName());
        session.setAttribute("password", user.getPswd());
        session.setAttribute("userOrg", null);
        session.setMaxInactiveInterval(60*60);

        if(otherParams != null && otherParaNames != null){
            for(int i = 0; i < otherParams.length; i++){
                session.setAttribute(otherParaNames[i], otherParams[i]);
            }
        }
        return true;
    }

    public static String userPhoneFilter(String phone) {
        if(StringUtils.isBlank(phone)){
            return "";
        }
        char[] array = phone.toCharArray();
        String result = "";
        for(int i = 0; i < array.length; i++){
            if(i > 2 && i < 7){
                result += "*";
            }else{
                result += array[i];
            }
        }
        return result;
    }

    public static void main(String [] args){
        System.out.println(validateUserName("guoxiao"));

//        System.out.println(("guox iaoj").indexOf(" "));
    }

}
