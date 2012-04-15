package pe.edu.pucp.library;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

/**
 * @author cgavidia
 *
 */
public class LibraryApplication extends Application {

	@Override
	public Restlet createInboundRoot() {
		Router router = new Router(getContext());
		router.attach("/books", BooksResource.class);
		router.attach("/books/{" + Book.CODE_ELEMENT + "}", BookResource.class);
		return router;
	}

}
