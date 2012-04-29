package pe.edu.pucp.library;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import pe.edu.pucp.model.Book;
import pe.edu.pucp.model.Student;
import pe.edu.pucp.resource.BookResource;
import pe.edu.pucp.resource.BooksResource;
import pe.edu.pucp.resource.StudentResource;
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
		router.attach("/books/{" + Book.CODE_ELEMENT + "}", BookResource.class);
		router.attach("/students", StudentsResource.class);
		router.attach("/students/{" + Student.CODE_ELEMENT + "}",
				StudentResource.class);
		return router;
	}

}
