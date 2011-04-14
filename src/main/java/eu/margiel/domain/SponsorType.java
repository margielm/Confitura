package eu.margiel.domain;

import java.util.List;

import com.google.common.collect.Lists;

public enum SponsorType {
	GOLD("Złoty", "Partnerzy Złoci"),
	SILVER("Srebrny", "Partnerzy Srebrni"),
	BRONZ("Brązowy", "Partnerzy Brązowi"),
	MEDIA("Medialny", "Patroni Medialni");
	private String shortName;
	private String fullName;

	private SponsorType(String shortName, String fullName) {
		this.shortName = shortName;
		this.fullName = fullName;
	}

	public String getFullName() {
		return fullName;
	}

	public String getShortName() {
		return shortName;
	}

	public static SponsorType byShortName(String shortName) {
		for (SponsorType type : values()) {
			if (type.getShortName().equals(shortName))
				return type;
		}
		throw new RuntimeException("No Sponsor type for short name [" + shortName + "]");
	}

	public static List<String> allShortNames() {
		List<String> result = Lists.newArrayList();
		for (SponsorType type : values())
			result.add(type.getShortName());
		return result;
	}

	public static List<SponsorType> sponsors() {
		return Lists.newArrayList(GOLD, SILVER, BRONZ);
	}
}
