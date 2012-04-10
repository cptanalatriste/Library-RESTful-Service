package pe.edu.pucp.library;

import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class BookResource extends ServerResource {

	private Book currentBook;
	private String currentId;

	@Override
	protected void doInit() throws ResourceException {
		currentId = (String) getRequest().getAttributes().get(
				BooksResource.CODE_ATTRIBUTE);
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
		if (currentBook == null) {
			currentBook = new Book();
			currentBook.setId(currentId);
		}

		Form form = new Form();
		currentBook.setName(form.getFirstValue(BooksResource.NAME_ATTRIBUTE));

		if (!isInRepository()) {
			setStatus(Status.SUCCESS_CREATED);
		} else {
			setStatus(Status.SUCCESS_OK);
		}
	}

	private boolean isInRepository() {
		// TODO Auto-generated method stub
		return false;
	}

	private void removeBook(String currentId2) {
		// TODO Auto-generated method stub

	}

	private Book getBook(String currentId2) {
		return null;
	}

	@Get
	public String represent() {
		return "Hello, I'm a Book!";
	}

}
