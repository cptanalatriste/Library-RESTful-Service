package pe.edu.pucp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import pe.edu.pucp.model.Book;
import pe.edu.pucp.model.BookReservation;
import pe.edu.pucp.model.Student;

import com.google.appengine.api.datastore.QueryResultIterable;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.NotFoundException;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;
import com.googlecode.objectify.util.DAOBase;

/**
 * @author cgavidia
 * 
 */
public class LibraryServiceDAO<T> extends DAOBase {

	public static final Logger LOG = Logger.getLogger(LibraryServiceDAO.class
			.getName());

	static {
		ObjectifyService.register(Book.class);
		ObjectifyService.register(Student.class);
		ObjectifyService.register(BookReservation.class);
	}

	protected Class<T> clazz;

	public LibraryServiceDAO(Class<T> clazz) {
		this.clazz = clazz;
	}

	public Key<T> add(T book) {
		Key<T> key = ofy().put(book);
		return key;
	}

	public void delete(Key<T> key) {
		ofy().delete(key);
	}

	public T get(Long id) throws NotFoundException {
		return ofy().get(clazz, id);
	}

	public T get(Key<T> key) {
		return ofy().get(key);
	}

	public List<T> listByProperty(String propertyName, String propertyValue) {
		Query<T> query = ofy().query(clazz);
		if (propertyName != null && propertyValue != null) {
			query.filter(propertyName, propertyValue);

		}
		return asList(query.fetch());
	}

	private List<T> asList(QueryResultIterable<T> fetch) {
		ArrayList<T> list = new ArrayList<T>();
		for (T t : fetch) {
			list.add(t);
		}
		return list;

	}
}
