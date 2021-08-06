package in.co.srdt.unif.model.lwp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)

public class CreateLwpWrapper {
	
private List<CreateLwp> createlwp;
	
	public CreateLwpWrapper() {
		
		createlwp = new ArrayList<>();
		createlwp.add(new CreateLwp());
		
	}

	public List<CreateLwp> getCreatelwp() {
		return createlwp;
	}

	public void setCreatelwp(List<CreateLwp> createlwp) {
		this.createlwp = createlwp;
	}

	@Override
	public String toString() {
		return "CreateLwpWrapper [createlwp=" + createlwp + "]";
	}

	
}
