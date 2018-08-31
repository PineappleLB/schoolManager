package club.pinea.school.config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationShutdownUp implements ApplicationListener<ContextClosedEvent> {

	
	@Override
	public void onApplicationEvent(ContextClosedEvent evt) {
	}

}
