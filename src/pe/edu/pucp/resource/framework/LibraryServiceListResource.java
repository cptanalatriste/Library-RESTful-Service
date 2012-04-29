package pe.edu.pucp.resource.framework;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.ext.xml.DomRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import pe.edu.pucp.dao.LibraryServiceDAO;
import pe.edu.pucp.model.BaseEntity;

import com.googlecode.objectify.Key;

/**
 * @author cgavidia
 * 
 */
public class LibraryServiceListResource<T extends BaseEntity> extends
		ServerResource {

	public static final Logger LOG = Logger
			.getLogger(LibraryServiceListResource.class.getName());

	private static final String POST_ERROR_CODE = "1";
	private static final String POST_ERROR_MSG = "No ha sido posible registrar la entidad";
	private static final String ENTITY_CREATED_MSG = "La entidad se creo con éxito";

	private static final String ERROR_CODE_ELEMENT = "codigo";

	protected LibraryServiceDAO<T> dao;

	protected Class<T> clazz;

	private String rootListElement;

	public LibraryServiceListResource(Class<T> clazz, String rootListElement) {
		this.dao = new LibraryServiceDAO<T>(clazz);
		this.clazz = clazz;
		this.rootListElement = rootListElement;
	}

	@Post
	public Representation accept(Representation representation) {
		Form form = new Form(representation);
		Representation result = null;

		try {
			setStatus(Status.SUCCESS_CREATED);
			T entity = clazz.newInstance();
			entity.intializeProperties(form);
			Key<T> key = dao.add(entity);
			result = new StringRepresentation(ENTITY_CREATED_MSG,
					MediaType.TEXT_PLAIN);
			result.setLocationRef(getRequest().getResourceRef().getIdentifier()
					+ "/" + key.getId());
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "Error in POST", e);
			setStatus(Status.CLIENT_ERROR_NOT_FOUND);
			result = generateErrorRepresentation(POST_ERROR_MSG + ": "
					+ e.toString(), POST_ERROR_CODE);
		}

		return result;
	}

	public static Representation generateErrorRepresentation(String errorMsg,
			String errorCode) {
		DomRepresentation result = null;
		try {
			result = new DomRepresentation(MediaType.TEXT_XML);
			Document document = result.getDocument();
			Element errorElement = document.createElement("error");
			document.appendChild(errorElement);
			Element codeElement = document.createElement(ERROR_CODE_ELEMENT);
			codeElement.appendChild(document.createTextNode(errorCode));
			errorElement.appendChild(codeElement);

			Element messageElement = document.createElement("mensaje");
			messageElement.appendChild(document.createTextNode(errorMsg));
			errorElement.appendChild(messageElement);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;

	}

	@Get("xml")
	public Representation toXml() {
		try {
			DomRepresentation result = new DomRepresentation(MediaType.TEXT_XML);
			Document document = result.getDocument();
			Element entitiesElement = document.createElement(rootListElement);
			document.appendChild(entitiesElement);
			for (T entity : listAllEntities()) {
				entitiesElement.appendChild(entity.toXml(document));
			}
			document.normalizeDocument();
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	private List<T> listAllEntities() {
		return dao.listByProperty(null, null);
	}

}
