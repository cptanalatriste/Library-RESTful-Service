package pe.edu.pucp.resource;

import pe.edu.pucp.model.Book;
import pe.edu.pucp.resource.framework.LibraryServiceListResource;

/**
 * @author cgavidia
 * 
 */
public class BooksResource extends LibraryServiceListResource<Book> {

	public BooksResource() {
		super(Book.class, Book.LIST_ROOT_ELEMENT);
	}

}
