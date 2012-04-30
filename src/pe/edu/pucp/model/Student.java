package pe.edu.pucp.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.restlet.data.Form;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.google.appengine.api.datastore.Email;
import com.google.appengine.api.datastore.PhoneNumber;

/**
 * @author cgavidia
 * 
 */
@SuppressWarnings("serial")
@Entity
public class Student implements Serializable {

	@Id
	private Long id;
	private String firstName;
	private String lastName;
	private String universityCode;
	private PhoneNumber mobile;
	private Email email;

	public Student() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUniversityCode() {
		return universityCode;
	}

	public void setUniversityCode(String universityCode) {
		this.universityCode = universityCode;
	}

	public PhoneNumber getMobile() {
		return mobile;
	}

	public void setMobile(PhoneNumber mobile) {
		this.mobile = mobile;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

}
