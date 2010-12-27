package eu.margiel.utils;

import java.io.Serializable;

import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

public class Models {
	public static <T extends Serializable> Model<T> model(T value) {
		return new Model<T>(value);
	}

	public static <T extends Serializable> PropertyModel<T> propertyModel(Object value, String propertyName) {
		return new PropertyModel<T>(value, propertyName);
	}
}
