package pe.edu.pucp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.google.common.base.Strings;

/**
 * @author cgavidia
 * 
 */
@SuppressWarnings("serial")
@Entity
public class Book implements Serializable {

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
