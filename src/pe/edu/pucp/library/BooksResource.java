package pe.edu.pucp.library;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.ext.xml.DomRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author cgavidia
 * 
 */
public class BooksResource extends ServerResource {

	public static final String NAME_ATTRIBUTE = "nombre";
	public static final String CODE_ATTRIBUTE = "codigo";
	private static final String DUPLICATE_BOOK_CODE = "1";
	private static final String DUPLICATE_BOOK_MSG = "Ya existe un libro con el codigo enviado";
	private static final String BOOK_CREATED_MSG = "El libro se creo con éxito";

	@Post
	public Representation acceptBook(Representation entity) {
		Form form = new Form(entity);
		String bookId = form.getFirstValue(CODE_ATTRIBUTE);
		String bookName = form.getFirstValue(NAME_ATTRIBUTE);

		Representation result = null;

		if (!isInRepository()) {
			setStatus(Status.SUCCESS_CREATED);
			result = new StringRepresentation(BOOK_CREATED_MSG,
					MediaType.TEXT_PLAIN);
			result.setLocationRef(getRequest().getResourceRef().getIdentifier()
					+ "/" + bookId);
		} else {
			setStatus(Status.CLIENT_ERROR_NOT_FOUND);
			result = generateErrorRepresentation(DUPLICATE_BOOK_MSG,
					DUPLICATE_BOOK_CODE);
		}
		return result;
	}

	private Representation generateErrorRepresentation(String errorMsg,
			String errorCode) {
		DomRepresentation result = null;
		try {
			result = new DomRepresentation(MediaType.TEXT_XML);
			Document document = result.getDocument();
			Element errorElement = document.createElement("error");
			document.appendChild(errorElement);
			Element codeElement = document.createElement(CODE_ATTRIBUTE);
			codeElement.appendChild(document.createTextNode(errorCode));
			errorElement.appendChild(codeElement);

			Element messageElement = document.createElement("mensaje");
			messageElement.appendChild(document.createTextNode(errorMsg));
			errorElement.appendChild(messageElement);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;

	}

	@Get("xml")
	public Representation toXml() {
		try {
			DomRepresentation result = new DomRepresentation(MediaType.TEXT_XML);
			Document document = result.getDocument();
			Element booksElement = document.createElement("libros");
			document.appendChild(booksElement);
			for (Book book : getBooks()) {
				Element bookElement = document.createElement("libro");

				Element bookIdElement = document.createElement(CODE_ATTRIBUTE);
				bookIdElement
						.appendChild(document.createTextNode(book.getId()));
				bookElement.appendChild(bookIdElement);

				Element nameElement = document.createElement(NAME_ATTRIBUTE);
				nameElement
						.appendChild(document.createTextNode(book.getName()));
				bookElement.appendChild(nameElement);

				booksElement.appendChild(bookElement);

			}
			document.normalizeDocument();
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	private List<Book> getBooks() {
		// TODO (cgavidia): Temporal implementation
		List<Book> list = new ArrayList<Book>();
		Book book = new Book();
		book.setId("123");
		book.setName("El Ingenioso Hidalgo Don Quijote de la Mancha");
		list.add(book);
		return list;
	}

	// TODO (cgavidia): Implement with DataStore access
	private boolean isInRepository() {
		return true;
	}
}
