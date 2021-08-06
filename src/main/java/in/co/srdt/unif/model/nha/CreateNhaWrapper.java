package in.co.srdt.unif.model.nha;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CreateNhaWrapper {

	private List<CreateNha> createnha;

	public CreateNhaWrapper() {
		createnha = new ArrayList<>();
		createnha.add(new CreateNha());
	}

	public List<CreateNha> getCreatenha() {
		return createnha;
	}

	public void setCreatenha(List<CreateNha> createnha) {
		this.createnha = createnha;
	}

	@Override
	public String toString() {
		return "CreateNhaWrapper [createnha=" + createnha + "]";
	}
	
	
	
}
