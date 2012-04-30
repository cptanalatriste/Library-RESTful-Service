package pe.edu.pucp.resource;

import pe.edu.pucp.model.Student;
import pe.edu.pucp.resource.framework.LibraryServiceListResource;

/**
 * @author cgavidia
 * 
 */
public class StudentsResource extends LibraryServiceListResource<Student> {

	public StudentsResource() {
		super(Student.class, new StudentSerializer());
	}

}
