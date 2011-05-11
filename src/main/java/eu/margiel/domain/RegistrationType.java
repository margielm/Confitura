package eu.margiel.domain;

public enum RegistrationType {
	NEW("Nowa"),
	CONFIRMED("Potwierdzona"),
	FINAL_CONFIRMED("Finalna"),
	CANCELED("Anulowana");
	private final String name;

	private RegistrationType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
