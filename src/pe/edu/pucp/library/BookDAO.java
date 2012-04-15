package pe.edu.pucp.library;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.appengine.api.datastore.QueryResultIterable;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.NotFoundException;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;
import com.googlecode.objectify.util.DAOBase;

public class BookDAO extends DAOBase {

	public static final Logger LOG = Logger.getLogger(BookDAO.class.getName());
	
	static {
		ObjectifyService.register(Book.class);
	}

	public Key<Book> add(Book book) {
		Key<Book> key = ofy().put(book);
		return key;
	}

	public void delete(Key<Book> key) {
		ofy().delete(key);
	}

	public Book get(Long id) throws NotFoundException {
		return ofy().get(Book.class, id);
	}

	public List<Book> listByProperty(String propertyName, String propertyValue) {
		Query<Book> query = ofy().query(Book.class);
		if (propertyName != null && propertyValue != null) {
			query.filter(propertyName, propertyValue);

		}
		return asList(query.fetch());
	}

	private List<Book> asList(QueryResultIterable<Book> fetch) {
		ArrayList<Book> list = new ArrayList<Book>();
		for (Book t : fetch) {
			list.add(t);
		}
		return list;

	}
}
