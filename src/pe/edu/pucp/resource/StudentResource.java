package pe.edu.pucp.resource;

import pe.edu.pucp.model.Student;
import pe.edu.pucp.resource.framework.LibraryServiceEntityResource;

/**
 * @author cgavidia
 * 
 */
public class StudentResource extends LibraryServiceEntityResource<Student> {

	public StudentResource() {
		super(Student.class, Student.CODE_ELEMENT);
	}

}
