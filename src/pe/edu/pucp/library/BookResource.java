package pe.edu.pucp.library;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class BookResource extends ServerResource {

	@Get
	public String represent() {
		return "Hello, I'm a Book!";
	}

}
