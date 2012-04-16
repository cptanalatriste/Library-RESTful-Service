package pe.edu.pucp.library;

import java.io.IOException;

import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.ext.xml.DomRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.w3c.dom.Document;

import com.googlecode.objectify.Key;

public class BookResource extends ServerResource {

	private Book currentBook;
	private String currentId;

	private BookDAO dao = new BookDAO();

	@Override
	protected void doInit() throws ResourceException {
		currentId = (String) getRequest().getAttributes()
				.get(Book.CODE_ELEMENT);
		currentBook = getBook(currentId);
		setExisting(currentBook != null);
	}

	@Delete
	public void removeBook() {
		if (currentBook != null) {
			removeBook(currentId);
		}
		setStatus(Status.SUCCESS_NO_CONTENT);
	}

	@Put
	public void storeBook(Representation entity) {
		currentBook = new Book(new Form(entity));
		currentBook.setId(Long.parseLong(currentId));
		dao.add(currentBook);
		setStatus(Status.SUCCESS_OK);

	}

	@Get("xml")
	public Representation toXml() {
		try {
			DomRepresentation representation = new DomRepresentation(
					MediaType.TEXT_XML);
			Document document = representation.getDocument();
			document.appendChild(currentBook.toXml(document));
			return representation;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void removeBook(String id) {
		dao.delete(Key.create(Book.class, Long.parseLong(id)));
	}

	private Book getBook(String id) {
		try {
			return dao.get(Long.parseLong(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
