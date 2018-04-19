package com.mds.manager.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.mds.manager.model.User;

/**
 * 2017-08-11 11:06:36
 * @author huahao
 *
 */
public class ShiroSecurityHelper {
	
	/** 
     * 获得当前用户名 
     *  
     * @return 
     */  
    public static User getCurrentUser() {  
        Subject subject = SecurityUtils.getSubject();  
        PrincipalCollection collection = subject.getPrincipals();  
        if (null != collection && !collection.isEmpty()) {  
            return (User)collection.iterator().next();
        }  
        return null;  
    }

	/** 
     * 获得当前用户名 
     *  
     * @return 
     */  
    public static String getCurrentUsername() {  
        Subject subject = SecurityUtils.getSubject();  
        PrincipalCollection collection = subject.getPrincipals();  
        if (null != collection && !collection.isEmpty()) {  
            User user = (User)collection.iterator().next();
            return user.getUsername();
        }  
        return null;  
    }
    
    /**
     * 获得当前sessionId
     * @return
     */
    public static String getSessionId() {  
        Session session = getSession();
        if (null == session) {  
            return null;  
        }  
        return getSession().getId().toString();  
    }
    
    /**
     * 获得当前session
     * @return
     */
    public static Session getSession() {  
        return SecurityUtils.getSubject().getSession();  
    }
    
    /**
     * encrypted pass
     * @param pass
     * @return
     */
    public static String encryptedPass(String pass){
    	Object obj = new SimpleHash(Constant.HASHALGORITHMNAME, pass, getCurrentUsername());
    	return obj.toString();
    }
    
    public static String getInitPass(){
    	return encryptedPass("12456");
    }
    
    public static void main(String[] args) {
    	System.out.println(new SimpleHash(Constant.HASHALGORITHMNAME, "123456", "admin",1));
	}
}
