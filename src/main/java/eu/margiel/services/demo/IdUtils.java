package eu.margiel.services.demo;

public class IdUtils {

	private static Long id = 0L;

	public static Long nextId() {
		return id--;
	}

}
