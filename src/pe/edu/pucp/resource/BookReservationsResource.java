package pe.edu.pucp.resource;

import pe.edu.pucp.model.BookReservation;
import pe.edu.pucp.resource.framework.LibraryServiceListResource;

/**
 * @author cgavidia
 * 
 */
public class BookReservationsResource extends
		LibraryServiceListResource<BookReservation> {

	public BookReservationsResource() {
		super(BookReservation.class, new BookReservationSerializer());
	}

}
