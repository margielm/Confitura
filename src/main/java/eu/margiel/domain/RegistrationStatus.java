package eu.margiel.domain;

public enum RegistrationStatus {
	NEW("Nowa"),
	CONFIRMED("Potwierdzona"),
	FINAL_CONFIRMED("Finalna"),
	CANCELED("Anulowana"),
	PARTICIPATED("Był");
	private final String name;

	private RegistrationStatus(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
}
