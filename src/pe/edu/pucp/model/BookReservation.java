package pe.edu.pucp.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.googlecode.objectify.Key;

/**
 * @author carlos
 * 
 */
@Entity
public class BookReservation {

	@Id
	private Long id;
	private Key<Student> student;
	private Key<Book> book;
	private Date date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Key<Student> getStudent() {
		return student;
	}

	public void setStudent(Key<Student> student) {
		this.student = student;
	}

	public Key<Book> getBook() {
		return book;
	}

	public void setBook(Key<Book> book) {
		this.book = book;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
