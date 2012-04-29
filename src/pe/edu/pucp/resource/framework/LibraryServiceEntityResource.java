package pe.edu.pucp.resource.framework;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.ext.xml.DomRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.w3c.dom.Document;

import pe.edu.pucp.dao.LibraryServiceDAO;
import pe.edu.pucp.model.BaseEntity;

import com.googlecode.objectify.Key;

/**
 * @author cgavidia
 * 
 */
public class LibraryServiceEntityResource<T extends BaseEntity> extends
		ServerResource {

	public static final Logger LOG = Logger
			.getLogger(LibraryServiceEntityResource.class.getName());
	private static final String GENERIC_ERROR_CODE = "0";
	private static final String GENERIC_ERROR_MSG = "Ocurrió un error inesperado";

	private T currentEntity;
	private String currentId;

	private LibraryServiceDAO<T> dao;
	protected Class<T> clazz;
	private String codeElement;

	public LibraryServiceEntityResource(Class<T> clazz, String codeElement) {
		this.dao = new LibraryServiceDAO<T>(clazz);
		this.clazz = clazz;
		this.codeElement = codeElement;
	}

	@Override
	protected void doInit() throws ResourceException {
		currentId = (String) getRequest().getAttributes().get(codeElement);
		currentEntity = getEntity(currentId);
		setExisting(currentEntity != null);
	}

	@Delete
	public void removeEntity() {
		if (currentEntity != null) {
			removeEntity(currentId);
		}
		setStatus(Status.SUCCESS_NO_CONTENT);
	}

	@Put
	public void storeEntity(Representation entity) {
		try {
			currentEntity = clazz.newInstance();
			currentEntity.intializeProperties(new Form(entity));
			currentEntity.setId(Long.parseLong(currentId));
			dao.add(currentEntity);
			setStatus(Status.SUCCESS_OK);
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "Error in PUT", e);
			setStatus(Status.SERVER_ERROR_INTERNAL);
		}

	}

	@Get("xml")
	public Representation toXml() {
		try {
			DomRepresentation representation = new DomRepresentation(
					MediaType.TEXT_XML);
			Document document = representation.getDocument();
			document.appendChild(currentEntity.toXml(document));
			return representation;
		} catch (IOException e) {
			LOG.log(Level.SEVERE, "Error in GET", e);
			setStatus(Status.SERVER_ERROR_INTERNAL);
			return LibraryServiceListResource
					.generateErrorRepresentation(GENERIC_ERROR_MSG + ": "
							+ e.toString(), GENERIC_ERROR_CODE);
		}
	}

	private void removeEntity(String id) {
		dao.delete(Key.create(clazz, Long.parseLong(id)));
	}

	private T getEntity(String id) {
		try {
			return dao.get(Long.parseLong(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
