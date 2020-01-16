package com.woniuxy.listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.woniuxy.entities.LoginCache;

/**
 * Application Lifecycle Listener implementation class LoginListener
 *
 */
@WebListener
public class LoginListener implements HttpSessionAttributeListener {

    /**
     * Default constructor. 
     */
    public LoginListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent event)  { 
    	String name = event.getName();
    	
    	
    	if (name.equals("userName")) {
    		String value = (String)event.getValue();
			LoginCache lc = LoginCache.getInstance();
			HttpSession oldSession = lc.getSession(value);
			System.out.println(oldSession);
			if (oldSession!=null) {
				
				oldSession.removeAttribute(name);
				
			}else {
				lc.setMap(value, event.getSession());
			}
		}

    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent event)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent event)  { 
         // TODO Auto-generated method stub
    }
	
}
