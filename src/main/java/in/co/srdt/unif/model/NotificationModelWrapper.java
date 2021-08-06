package in.co.srdt.unif.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)

public class NotificationModelWrapper {
	
private List<NotificationModel> notificationModels;
	
	public NotificationModelWrapper() {
		
		notificationModels = new ArrayList<>();
		notificationModels.add(new NotificationModel());
		
	}

	public List<NotificationModel> getNotificationModels() {
		return notificationModels;
	}

	public void setNotificationModels(List<NotificationModel> notificationModels) {
		this.notificationModels = notificationModels;
	}

	@Override
	public String toString() {
		return "NotificationModelWrapper [notificationModels=" + notificationModels + "]";
	}

	

	
}
