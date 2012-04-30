package pe.edu.pucp.library;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import pe.edu.pucp.resource.BookReservationResource;
import pe.edu.pucp.resource.BookReservationSerializer;
import pe.edu.pucp.resource.BookReservationsResource;
import pe.edu.pucp.resource.BookResource;
import pe.edu.pucp.resource.BookSerializer;
import pe.edu.pucp.resource.BooksResource;
import pe.edu.pucp.resource.StudentResource;
import pe.edu.pucp.resource.StudentSerializer;
import pe.edu.pucp.resource.StudentsResource;

/**
 * @author cgavidia
 * 
 */
public class LibraryApplication extends Application {

	@Override
	public Restlet createInboundRoot() {
		Router router = new Router(getContext());
		router.attach("/books", BooksResource.class);
		router.attach("/books/{" + BookSerializer.CODE_ELEMENT + "}",
				BookResource.class);
		router.attach("/students", StudentsResource.class);
		router.attach("/students/{" + StudentSerializer.CODE_ELEMENT + "}",
				StudentResource.class);
		router.attach("/reservations", BookReservationsResource.class);
		router.attach("/reservations/{"
				+ BookReservationSerializer.CODE_ELEMENT + "}",
				BookReservationResource.class);
		return router;
	}

}
