package pe.edu.pucp.resource;

import java.util.Arrays;

import org.restlet.data.Form;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import pe.edu.pucp.model.BaseSerializer;
import pe.edu.pucp.model.Book;

/**
 * @author carlos
 * 
 */
public class BookSerializer extends BaseSerializer<Book> {

	private static final String BOOK_ELEMENT = "libro";
	public static final String TITLE_ELEMENT = "titulo";
	public static final String CODE_ELEMENT = "codigo";
	private static final String AUTHOR_ELEMENT = "autor";
	private static final String SUMMARY_ELEMENT = "resumen";
	private static final String EDITION_ELEMENT = "edicion";
	private static final String PUBLISHER_ELEMENT = "editorial";
	private static final String ISBN_ELEMENT = "ISBN";
	private static final String STATE_ELEMENT = "estado";
	private static final String SUBJECT_ELEMENT = "categoria";
	private static final String YEAR_ELEMENT = "agno";
	private static final String COPIES_ELEMENT = "copias";
	private static final String ISBNS_ELEMENT = "ISBNs";
	private static final String SUBJECTS_ELEMENT = "categorias";
	public static final String LIST_ROOT_ELEMENT = "libros";

	private Book entity;

	public BookSerializer() {

	}

	public BookSerializer(Book entity) {
		this.entity = entity;
	}

	@Override
	public void intializeProperties(Form form) {
		if (form.getFirstValue(CODE_ELEMENT) != null) {
			entity.setId(Long.parseLong(form.getFirstValue(CODE_ELEMENT)));
		}
		entity.setTitle(form.getFirstValue(TITLE_ELEMENT));
		entity.setAuthor(form.getFirstValue(AUTHOR_ELEMENT));
		entity.setSummary(form.getFirstValue(SUMMARY_ELEMENT));
		entity
				.setEdition(Integer.parseInt(form
						.getFirstValue(EDITION_ELEMENT)));
		entity.setPublisher(form.getFirstValue(PUBLISHER_ELEMENT));
		entity.setISBN(Arrays.asList(form.getFirstValue(ISBNS_ELEMENT).split(
				",")));
		entity.setState(form.getFirstValue(STATE_ELEMENT));
		entity.setSubjects(Arrays.asList(form.getFirstValue(SUBJECTS_ELEMENT)
				.split(",")));
		entity.setYear(Integer.parseInt(form.getFirstValue(YEAR_ELEMENT)));
		entity.setNumberOfCopies(Integer.parseInt(form
				.getFirstValue(COPIES_ELEMENT)));

	}

	@Override
	public Element toXml(Document document) {
		Element bookElement = document.createElement(BOOK_ELEMENT);

		Element bookIdElement = document.createElement(CODE_ELEMENT);
		bookIdElement.appendChild(document.createTextNode(entity.getId()
				.toString()));
		bookElement.appendChild(bookIdElement);

		Element nameElement = document.createElement(TITLE_ELEMENT);
		nameElement.appendChild(document.createTextNode(entity.getName()));
		bookElement.appendChild(nameElement);

		Element authorElement = document.createElement(AUTHOR_ELEMENT);
		authorElement.appendChild(document.createTextNode(entity.getAuthor()));
		bookElement.appendChild(authorElement);

		Element summaryElement = document.createElement(SUMMARY_ELEMENT);
		summaryElement
				.appendChild(document.createTextNode(entity.getSummary()));
		bookElement.appendChild(summaryElement);

		Element editionElement = document.createElement(EDITION_ELEMENT);
		editionElement.appendChild(document.createTextNode(String
				.valueOf(entity.getEdition())));
		bookElement.appendChild(editionElement);

		Element publisherElement = document.createElement(PUBLISHER_ELEMENT);
		publisherElement.appendChild(document.createTextNode(entity
				.getPublisher()));
		bookElement.appendChild(publisherElement);

		Element ISBNsElements = document.createElement(ISBNS_ELEMENT);
		if (entity.getISBN() != null) {
			for (String ISBN : entity.getISBN()) {
				Element ISBNElement = document.createElement(ISBN_ELEMENT);
				ISBNElement.appendChild(document.createTextNode(ISBN));
				ISBNsElements.appendChild(ISBNElement);
			}
		}
		bookElement.appendChild(ISBNsElements);

		Element stateElement = document.createElement(STATE_ELEMENT);
		stateElement.appendChild(document.createTextNode(entity.getState()));
		bookElement.appendChild(stateElement);

		Element categoriesElement = document.createElement(SUBJECTS_ELEMENT);
		if (entity.getSubjects() != null) {
			for (String subject : entity.getSubjects()) {
				Element subjectElement = document
						.createElement(SUBJECT_ELEMENT);
				subjectElement.appendChild(document.createTextNode(subject));
				categoriesElement.appendChild(subjectElement);
			}
		}
		bookElement.appendChild(categoriesElement);

		Element yearElement = document.createElement(YEAR_ELEMENT);
		yearElement.appendChild(document.createTextNode(String.valueOf(entity
				.getYear())));
		bookElement.appendChild(yearElement);

		Element copiesElement = document.createElement(COPIES_ELEMENT);
		copiesElement.appendChild(document.createTextNode(String.valueOf(entity
				.getNumberOfCopies())));
		bookElement.appendChild(copiesElement);

		return bookElement;
	}

	public Book getEntity() {
		return entity;
	}

	public void setEntity(Book entity) {
		this.entity = entity;
	}

	@Override
	public String getRootListElement() {
		return LIST_ROOT_ELEMENT;
	}

	@Override
	public void setId(Long id) {
		entity.setId(id);
	}

	@Override
	public String getCodeElement() {
		return CODE_ELEMENT;
	}

}
