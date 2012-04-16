package pe.edu.pucp.library;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.restlet.data.Form;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.google.common.base.Strings;

@SuppressWarnings("serial")
@Entity
public class Book implements Serializable {

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

	@Id
	private Long id;
	private String title;
	private String author;
	private String summary;
	private int edition;
	private String publisher;
	private List<String> ISBN;
	private String state;
	private List<String> subjects;
	private int year;
	private int numberOfCopies;

	public Book() {

	}

	public Book(Form form) {
		if (form.getFirstValue(Book.CODE_ELEMENT) != null) {
			id = Long.parseLong(form.getFirstValue(Book.CODE_ELEMENT));
		}
		title = form.getFirstValue(Book.TITLE_ELEMENT);
		author = form.getFirstValue(Book.AUTHOR_ELEMENT);
		summary = form.getFirstValue(Book.SUMMARY_ELEMENT);
		edition = Integer.parseInt(form.getFirstValue(Book.EDITION_ELEMENT));
		publisher = form.getFirstValue(Book.PUBLISHER_ELEMENT);
		ISBN = Arrays.asList(form.getFirstValue(Book.ISBNS_ELEMENT).split(","));
		state = form.getFirstValue(Book.STATE_ELEMENT);
		subjects = Arrays.asList(form.getFirstValue(Book.SUBJECTS_ELEMENT)
				.split(","));
		year = Integer.parseInt(form.getFirstValue(Book.YEAR_ELEMENT));
		numberOfCopies = Integer.parseInt(form
				.getFirstValue(Book.COPIES_ELEMENT));

	}

	public Element toXml(Document document) {
		Element bookElement = document.createElement(BOOK_ELEMENT);

		Element bookIdElement = document.createElement(CODE_ELEMENT);
		bookIdElement.appendChild(document.createTextNode(getId().toString()));
		bookElement.appendChild(bookIdElement);

		Element nameElement = document.createElement(TITLE_ELEMENT);
		nameElement.appendChild(document.createTextNode(getName()));
		bookElement.appendChild(nameElement);

		Element authorElement = document.createElement(AUTHOR_ELEMENT);
		authorElement.appendChild(document.createTextNode(getAuthor()));
		bookElement.appendChild(authorElement);

		Element summaryElement = document.createElement(SUMMARY_ELEMENT);
		summaryElement.appendChild(document.createTextNode(getSummary()));
		bookElement.appendChild(summaryElement);

		Element editionElement = document.createElement(EDITION_ELEMENT);
		editionElement.appendChild(document.createTextNode(String
				.valueOf(getEdition())));
		bookElement.appendChild(editionElement);

		Element publisherElement = document.createElement(PUBLISHER_ELEMENT);
		publisherElement.appendChild(document.createTextNode(getPublisher()));
		bookElement.appendChild(publisherElement);

		Element ISBNsElements = document.createElement(ISBNS_ELEMENT);
		if (getISBN() != null) {
			for (String ISBN : getISBN()) {
				Element ISBNElement = document.createElement(ISBN_ELEMENT);
				ISBNElement.appendChild(document.createTextNode(ISBN));
				ISBNsElements.appendChild(ISBNElement);
			}
		}
		bookElement.appendChild(ISBNsElements);

		Element stateElement = document.createElement(STATE_ELEMENT);
		stateElement.appendChild(document.createTextNode(getState()));
		bookElement.appendChild(stateElement);

		Element categoriesElement = document.createElement(SUBJECTS_ELEMENT);
		if (getSubjects() != null) {
			for (String subject : getSubjects()) {
				Element subjectElement = document
						.createElement(SUBJECT_ELEMENT);
				subjectElement.appendChild(document.createTextNode(subject));
				categoriesElement.appendChild(subjectElement);
			}
		}
		bookElement.appendChild(categoriesElement);

		Element yearElement = document.createElement(YEAR_ELEMENT);
		yearElement.appendChild(document.createTextNode(String
				.valueOf(getYear())));
		bookElement.appendChild(yearElement);

		Element copiesElement = document.createElement(COPIES_ELEMENT);
		copiesElement.appendChild(document.createTextNode(String
				.valueOf(getNumberOfCopies())));
		bookElement.appendChild(copiesElement);

		return bookElement;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return Strings.nullToEmpty(title);
	}

	public void setTitle(String name) {
		this.title = name;
	}

	public String getAuthor() {
		return Strings.nullToEmpty(author);
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSummary() {
		return Strings.nullToEmpty(summary);
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public int getEdition() {
		return edition;
	}

	public void setEdition(int edition) {
		this.edition = edition;
	}

	public String getPublisher() {
		return Strings.nullToEmpty(publisher);
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public List<String> getISBN() {
		return ISBN;
	}

	public void setISBN(List<String> iSBN) {
		ISBN = iSBN;
	}

	public String getState() {
		return Strings.nullToEmpty(state);
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<String> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<String> subjects) {
		this.subjects = subjects;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getNumberOfCopies() {
		return numberOfCopies;
	}

	public void setNumberOfCopies(int numberOfCopies) {
		this.numberOfCopies = numberOfCopies;
	}

	public String getTitle() {
		return Strings.nullToEmpty(title);
	}

}
