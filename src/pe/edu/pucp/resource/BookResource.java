package pe.edu.pucp.resource;

import pe.edu.pucp.model.Book;
import pe.edu.pucp.resource.framework.LibraryServiceEntityResource;

/**
 * @author cgavidia
 * 
 */
public class BookResource extends LibraryServiceEntityResource<Book> {

	public BookResource() {
		super(Book.class, new BookSerializer());
	}

}
