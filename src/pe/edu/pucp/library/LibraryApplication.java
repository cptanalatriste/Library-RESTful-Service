package pe.edu.pucp.library;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class LibraryApplication extends Application {

	@Override
	public Restlet createInboundRoot() {
		Router router = new Router(getContext());
		router.attach("/books", BooksResource.class);
		router.attach("/books/{bookId}", BookResource.class);
		return router;
	}

}
