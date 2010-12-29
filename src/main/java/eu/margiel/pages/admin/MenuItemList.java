package eu.margiel.pages.admin;

import static com.google.common.collect.Lists.*;
import static com.google.common.collect.Maps.*;

import java.util.List;
import java.util.Map;

import org.apache.wicket.Page;

import eu.margiel.pages.javarsovia.ViewNewsPage;

public class MenuItemList {
	private static Map<String, Class<?>> items = newHashMap();

	public static void add(String item, Class<ViewNewsPage> clazz) {
		items.put(item, clazz);
	}

	public static List<String> all() {
		return newArrayList(items.keySet());
	}

	public static Page getPageFor(String linkTo) {
		try {
			return (Page) items.get(linkTo).newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Can not create page for " + items.get(linkTo));
		}
	}

}
