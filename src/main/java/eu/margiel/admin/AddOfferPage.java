package eu.margiel.admin;

import static com.google.common.collect.Lists.*;
import static eu.margiel.utils.Components.*;
import static eu.margiel.utils.Models.*;

import java.math.BigDecimal;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.MinimumValidator;
import org.joda.time.LocalDateTime;

import eu.margiel.components.UploadPhotosPanel;
import eu.margiel.domain.Offer;
import eu.margiel.domain.OfferType;
import eu.margiel.services.OfferService;
import eu.margiel.utils.OfferPhotosProvider;

@SuppressWarnings("serial")
public class AddOfferPage extends AdminBasePage {
	@SpringBean
	private OfferService service;

	public AddOfferPage() {
		this(new Offer().creationDate(new LocalDateTime()));
	}

	public AddOfferPage(Offer offer) {
		System.out.println("offer " + offer.getId() + " | " + offer.getCreationDate());
		add(new FeedbackPanel("feedback"));
		add(new AddOfferForm(offer));
		add(new UploadPhotosPanel("upload_photos", new OfferPhotosProvider(offer)));
	}

	private class AddOfferForm extends Form<Offer> {
		private final Offer offer;

		private AddOfferForm(Offer offer) {
			super("form", new CompoundPropertyModel<Offer>(offer));
			this.offer = offer;
			add(textField("title").setRequired(true));
			add(doubleField("price"));
			add(textField("city"));
			add(integerField("bathrooms"));
			add(integerField("bedrooms"));
			add(new CheckBox("seaView"));
			add(new CheckBox("primaryMarket"));
			add(textField("coast"));
			add(chooseOfferType("type", offer).setRequired(true));
			add(richEditorSimple("shortDescription").setRequired(true));
			add(richEditorSimple("description").setRequired(true));

		}

		private Component integerField(String id) {
			TextField<Integer> textField = textField(id);
			textField.setRequired(true);
			return textField.add(new MinimumValidator<Integer>(0));
		}

		private Component doubleField(String id) {
			TextField<BigDecimal> textField = textField(id);
			textField.setRequired(true);
			return textField.add(new MinimumValidator<BigDecimal>(BigDecimal.ZERO));
		}

		private DropDownChoice<OfferType> chooseOfferType(String id, Offer offer) {
			PropertyModel<OfferType> model = propertyModel(offer, "type");
			return dropDown(id, model, newArrayList(OfferType.values()));
		}

		@Override
		protected void onSubmit() {
			service.save(offer);
			setResponsePage(OfferListPage.class);
		}
	}
}
