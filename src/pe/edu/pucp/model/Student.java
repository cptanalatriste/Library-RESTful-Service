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
public class Student extends BaseEntity implements Serializable {

	public static final String CODE_ELEMENT = "codigo";
	private static final String FIRST_NAME_ELEMENT = "nombres";
	private static final String LAST_NAME_ELEMENT = "apellidos";
	private static final String UNIVERSITY_CODE_ELEMENT = "codigoPUCP";
	private static final String MOBILE_ELEMENT = "celular";
	private static final String EMAIL_ELEMENT = "email";
	public static final String LIST_ROOT_ELEMENT = "estudiantes";
	private static final String STUDENT_ELEMENT = "estudiante";

	@Id
	private Long id;
	private String firstName;
	private String lastName;
	private String universityCode;
	private PhoneNumber mobile;
	private Email email;

	public Student() {

	}

	@Override
	public void intializeProperties(Form form) {
		if (form.getFirstValue(Book.CODE_ELEMENT) != null) {
			id = Long.parseLong(form.getFirstValue(Book.CODE_ELEMENT));
		}
		firstName = form.getFirstValue(FIRST_NAME_ELEMENT);
		lastName = form.getFirstValue(LAST_NAME_ELEMENT);
		universityCode = form.getFirstValue(UNIVERSITY_CODE_ELEMENT);
		mobile = new PhoneNumber(form.getFirstValue(MOBILE_ELEMENT));
		email = new Email(form.getFirstValue(EMAIL_ELEMENT));

	}

	@Override
	public Element toXml(Document document) {
		Element studentElement = document.createElement(STUDENT_ELEMENT);

		Element studentIdElement = document.createElement(CODE_ELEMENT);
		studentIdElement.appendChild(document
				.createTextNode(getId().toString()));
		studentElement.appendChild(studentIdElement);

		Element firstNameElement = document.createElement(FIRST_NAME_ELEMENT);
		firstNameElement.appendChild(document.createTextNode(getFirstName()));
		studentElement.appendChild(firstNameElement);

		Element lastNameElement = document.createElement(LAST_NAME_ELEMENT);
		lastNameElement.appendChild(document.createTextNode(getLastName()));
		studentElement.appendChild(lastNameElement);

		Element universityCodeElement = document
				.createElement(UNIVERSITY_CODE_ELEMENT);
		universityCodeElement.appendChild(document
				.createTextNode(getUniversityCode()));
		studentElement.appendChild(universityCodeElement);

		Element phoneElement = document.createElement(MOBILE_ELEMENT);
		phoneElement.appendChild(document.createTextNode(getMobile()
				.getNumber()));
		studentElement.appendChild(phoneElement);

		Element emailElement = document.createElement(EMAIL_ELEMENT);
		emailElement
				.appendChild(document.createTextNode(getEmail().getEmail()));
		studentElement.appendChild(emailElement);

		return studentElement;
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
