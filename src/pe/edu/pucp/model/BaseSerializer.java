package pe.edu.pucp.model;

import org.restlet.data.Form;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author cgavidia
 * 
 */
public abstract class BaseSerializer<T> {

	public abstract void intializeProperties(Form form);

	public abstract Element toXml(Document document);

	public abstract void setEntity(T entity);

	public abstract T getEntity();

	public abstract String getRootListElement();

	public abstract void setId(Long id);

	public abstract String getCodeElement();
}
