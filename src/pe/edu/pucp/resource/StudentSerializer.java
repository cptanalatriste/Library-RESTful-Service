package pe.edu.pucp.resource;

import org.restlet.data.Form;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import pe.edu.pucp.model.BaseSerializer;
import pe.edu.pucp.model.Student;

import com.google.appengine.api.datastore.Email;
import com.google.appengine.api.datastore.PhoneNumber;

/**
 * @author carlos
 * 
 */
public class StudentSerializer extends BaseSerializer<Student> {

	public static final String CODE_ELEMENT = "codigo";
	private static final String FIRST_NAME_ELEMENT = "nombres";
	private static final String LAST_NAME_ELEMENT = "apellidos";
	private static final String UNIVERSITY_CODE_ELEMENT = "codigoPUCP";
	private static final String MOBILE_ELEMENT = "celular";
	private static final String EMAIL_ELEMENT = "email";
	private static final String PASSWORD_ELEMENT = "contrasegna";
	public static final String LIST_ROOT_ELEMENT = "estudiantes";
	private static final String STUDENT_ELEMENT = "estudiante";

	public Student entity;

	public StudentSerializer() {

	}

	public StudentSerializer(Student entity) {
		this.entity = entity;
	}

	@Override
	public Student getEntity() {
		return entity;
	}

	@Override
	public String getRootListElement() {
		return LIST_ROOT_ELEMENT;
	}

	@Override
	public void intializeProperties(Form form) {
		if (form.getFirstValue(CODE_ELEMENT) != null) {
			entity.setId(Long.parseLong(form.getFirstValue(CODE_ELEMENT)));
		}
		entity.setFirstName(form.getFirstValue(FIRST_NAME_ELEMENT));
		entity.setLastName(form.getFirstValue(LAST_NAME_ELEMENT));
		entity.setUniversityCode(form.getFirstValue(UNIVERSITY_CODE_ELEMENT));
		entity.setMobile(new PhoneNumber(form.getFirstValue(MOBILE_ELEMENT)));
		entity.setEmail(new Email(form.getFirstValue(EMAIL_ELEMENT)));
		entity.setPassword(form.getFirstValue(PASSWORD_ELEMENT));

	}

	@Override
	public void setEntity(Student entity) {
		this.entity = entity;
	}

	@Override
	public void setId(Long id) {
		entity.setId(id);
	}

	@Override
	public Element toXml(Document document) {
		Element studentElement = document.createElement(STUDENT_ELEMENT);

		Element studentIdElement = document.createElement(CODE_ELEMENT);
		studentIdElement.appendChild(document.createTextNode(entity.getId()
				.toString()));
		studentElement.appendChild(studentIdElement);

		Element firstNameElement = document.createElement(FIRST_NAME_ELEMENT);
		firstNameElement.appendChild(document.createTextNode(entity
				.getFirstName()));
		studentElement.appendChild(firstNameElement);

		Element lastNameElement = document.createElement(LAST_NAME_ELEMENT);
		lastNameElement.appendChild(document.createTextNode(entity
				.getLastName()));
		studentElement.appendChild(lastNameElement);

		Element passwordElement = document.createElement(PASSWORD_ELEMENT);
		passwordElement.appendChild(document.createTextNode(entity
				.getPassword() == null ? "" : entity.getPassword()));
		studentElement.appendChild(passwordElement);

		Element universityCodeElement = document
				.createElement(UNIVERSITY_CODE_ELEMENT);
		universityCodeElement.appendChild(document.createTextNode(entity
				.getUniversityCode()));
		studentElement.appendChild(universityCodeElement);

		Element phoneElement = document.createElement(MOBILE_ELEMENT);
		phoneElement.appendChild(document.createTextNode(entity.getMobile()
				.getNumber()));
		studentElement.appendChild(phoneElement);

		Element emailElement = document.createElement(EMAIL_ELEMENT);
		emailElement.appendChild(document.createTextNode(entity.getEmail()
				.getEmail()));
		studentElement.appendChild(emailElement);

		return studentElement;
	}

	@Override
	public String getCodeElement() {
		return CODE_ELEMENT;
	}

}
