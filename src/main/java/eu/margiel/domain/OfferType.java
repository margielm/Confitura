package eu.margiel.domain;

public enum OfferType {
	BEST_PRICE("Najtańsze"),
	BEST("Najbardziej korzystne"),
	EXCLUSIVE("Najbardziej ekskluzywne");

	private String name;

	private OfferType(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
