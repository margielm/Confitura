package eu.margiel.utils;

import java.io.Serializable;

import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

public class Models {
	public static Model<String> model() {
		return new Model<String>();
	}

	public static Model<String> model(String value) {
		return new Model<String>(value);
	}

	public static <T extends Serializable> PropertyModel<T> propertyModel(Object value, String propertyName) {
		return new PropertyModel<T>(value, propertyName);
	}
}
