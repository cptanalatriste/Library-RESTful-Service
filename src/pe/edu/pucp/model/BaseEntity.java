package pe.edu.pucp.model;

import org.restlet.data.Form;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author cgavidia
 * 
 */
public abstract class BaseEntity {

	public abstract void intializeProperties(Form form);

	public abstract Element toXml(Document document);

	public abstract void setId(Long id);

}
