package eu.margiel.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.joda.time.LocalDateTime;

@SuppressWarnings({ "serial", "unused" })
@Entity
public class Offer implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	@Enumerated(EnumType.STRING)
	private OfferType type;
	private BigDecimal price;
	@Lob
	private String shortDescription;
	@Lob
	private String description;
	private String coast;
	private boolean seaView;
	private boolean primaryMarket;
	private String city;
	private Integer bedrooms;
	private Integer bathrooms;
	private Date creationDate;

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Offer [id=" + id + ", title=" + title + ", category=" + type + "]";
	}

	public String getTitle() {
		return title;
	}

	public OfferType getCategory() {
		return type;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void id(Long id) {
		this.id = id;
	}

	public Offer creationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate.toDateTime().toDate();
		return this;
	}

	public LocalDateTime getCreationDate() {
		System.out.println("date " + creationDate);
		return LocalDateTime.fromDateFields(creationDate);
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public String getDescription() {
		return description;
	}
}
