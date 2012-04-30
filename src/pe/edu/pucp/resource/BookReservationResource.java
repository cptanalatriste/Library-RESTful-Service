package pe.edu.pucp.resource;

import pe.edu.pucp.model.BookReservation;
import pe.edu.pucp.resource.framework.LibraryServiceEntityResource;

/**
 * @author cgavidia
 * 
 */
public class BookReservationResource extends
		LibraryServiceEntityResource<BookReservation> {

	public BookReservationResource() {
		super(BookReservation.class, new BookReservationSerializer());
	}

}
