package marko.ip.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import marko.ip.beans.UserBean;

@WebListener
public class LogutListener implements HttpSessionListener{
	
	@Override
	public void sessionDestroyed(final HttpSessionEvent event) {
		UserBean userBean = (UserBean) event.getSession().getAttribute("userBean");
		if(userBean != null) {
			userBean.logut();
		}
	}

}
