package eu.margiel.utils;

import java.io.Serializable;
import java.util.List;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import wicket.contrib.tinymce.TinyMceBehavior;
import wicket.contrib.tinymce.settings.Button;
import wicket.contrib.tinymce.settings.ContextMenuPlugin;
import wicket.contrib.tinymce.settings.DateTimePlugin;
import wicket.contrib.tinymce.settings.DirectionalityPlugin;
import wicket.contrib.tinymce.settings.EmotionsPlugin;
import wicket.contrib.tinymce.settings.FullScreenPlugin;
import wicket.contrib.tinymce.settings.IESpellPlugin;
import wicket.contrib.tinymce.settings.MediaPlugin;
import wicket.contrib.tinymce.settings.PastePlugin;
import wicket.contrib.tinymce.settings.PreviewPlugin;
import wicket.contrib.tinymce.settings.PrintPlugin;
import wicket.contrib.tinymce.settings.SavePlugin;
import wicket.contrib.tinymce.settings.SearchReplacePlugin;
import wicket.contrib.tinymce.settings.SpellCheckPlugin;
import wicket.contrib.tinymce.settings.TablePlugin;
import wicket.contrib.tinymce.settings.TinyMCESettings;
import eu.margiel.components.RedirectLink;

public class Components {
	public static <T extends Serializable> TextField<T> textField(String id, IModel<T> model) {
		return new TextField<T>(id, model);
	}

	public static <T extends Serializable> TextField<T> textField(String id, IModel<T> model, boolean required) {
		TextField<T> textField = new TextField<T>(id, model);
		textField.setRequired(required);
		return textField;
	}

	@SuppressWarnings("unchecked")
	public static <T extends Serializable> PasswordTextField passwordField(String id, IModel<T> model, boolean required) {
		return (PasswordTextField) new PasswordTextField(id, (IModel<String>) model).setRequired(required);
	}

	public static <T extends Serializable> TextField<T> textField(String id) {
		return new TextField<T>(id);
	}

	@SuppressWarnings("unchecked")
	public static TextArea<String> richEditor(String id) {
		return (TextArea<String>) new TextArea<String>(id).add(configureFullRichClient());
	}

	@SuppressWarnings("unchecked")
	public static TextArea<String> richEditorSimple(String id) {
		return (TextArea<String>) new TextArea<String>(id).add(configureSimpleRichClient());
	}

	@SuppressWarnings("unchecked")
	public static <T extends Serializable> TextArea<T> richEditorSimple(String id, IModel<T> model) {
		TextArea<T> richEditor = (TextArea<T>) richEditorSimple(id);
		richEditor.setModel(model);
		return richEditor;
	}

	@SuppressWarnings("unchecked")
	public static <T extends Serializable> TextArea<T> richEditorSimple(String id, IModel<T> model, boolean required) {
		TextArea<T> richEditor = (TextArea<T>) richEditorSimple(id);
		richEditor.setModel(model);
		richEditor.setRequired(required);
		return richEditor;
	}

	@SuppressWarnings("unchecked")
	public static <T extends Serializable> TextArea<T> richEditor(String id, IModel<T> model) {
		TextArea<T> richEditor = (TextArea<T>) richEditor(id);
		richEditor.setModel(model);
		return richEditor;
	}

	public static <T extends Serializable> TextArea<T> richEditor(String id, IModel<T> model, boolean required) {
		TextArea<T> richEditor = richEditor(id, model);
		richEditor.setRequired(required);
		return richEditor;
	}

	private static TinyMceBehavior configureFullRichClient() {
		TinyMCESettings settings = new TinyMCESettings(TinyMCESettings.Theme.advanced);

		ContextMenuPlugin contextMenuPlugin = new ContextMenuPlugin();
		settings.register(contextMenuPlugin);

		// first toolbar
		SavePlugin savePlugin = new SavePlugin();
		settings.add(savePlugin.getSaveButton(), TinyMCESettings.Toolbar.first, TinyMCESettings.Position.before);
		settings.add(Button.newdocument, TinyMCESettings.Toolbar.first, TinyMCESettings.Position.before);
		settings.add(Button.separator, TinyMCESettings.Toolbar.first, TinyMCESettings.Position.before);
		settings.add(Button.fontselect, TinyMCESettings.Toolbar.first, TinyMCESettings.Position.after);
		settings.add(Button.fontsizeselect, TinyMCESettings.Toolbar.first, TinyMCESettings.Position.after);

		// second toolbar
		PastePlugin pastePlugin = new PastePlugin();
		SearchReplacePlugin searchReplacePlugin = new SearchReplacePlugin();
		DateTimePlugin dateTimePlugin = new DateTimePlugin();
		dateTimePlugin.setDateFormat("Date: %m-%d-%Y");
		dateTimePlugin.setTimeFormat("Time: %H:%M");
		PreviewPlugin previewPlugin = new PreviewPlugin();
		settings.add(Button.cut, TinyMCESettings.Toolbar.second, TinyMCESettings.Position.before);
		settings.add(Button.copy, TinyMCESettings.Toolbar.second, TinyMCESettings.Position.before);
		settings.add(pastePlugin.getPasteButton(), TinyMCESettings.Toolbar.second, TinyMCESettings.Position.before);
		settings.add(pastePlugin.getPasteTextButton(), TinyMCESettings.Toolbar.second, TinyMCESettings.Position.before);
		settings.add(pastePlugin.getPasteWordButton(), TinyMCESettings.Toolbar.second, TinyMCESettings.Position.before);
		settings.add(Button.separator, TinyMCESettings.Toolbar.second, TinyMCESettings.Position.before);
		settings.add(searchReplacePlugin.getSearchButton(), TinyMCESettings.Toolbar.second,
					TinyMCESettings.Position.before);
		settings.add(searchReplacePlugin.getReplaceButton(), TinyMCESettings.Toolbar.second,
					TinyMCESettings.Position.before);
		settings.add(Button.separator, TinyMCESettings.Toolbar.second, TinyMCESettings.Position.before);
		settings.add(Button.separator, TinyMCESettings.Toolbar.second, TinyMCESettings.Position.after);
		settings.add(dateTimePlugin.getDateButton(), TinyMCESettings.Toolbar.second, TinyMCESettings.Position.after);
		settings.add(dateTimePlugin.getTimeButton(), TinyMCESettings.Toolbar.second, TinyMCESettings.Position.after);
		settings.add(Button.separator, TinyMCESettings.Toolbar.second, TinyMCESettings.Position.after);
		settings.add(previewPlugin.getPreviewButton(), TinyMCESettings.Toolbar.second, TinyMCESettings.Position.after);
		settings.add(Button.separator, TinyMCESettings.Toolbar.second, TinyMCESettings.Position.after);
		settings.add(Button.forecolor, TinyMCESettings.Toolbar.second, TinyMCESettings.Position.after);
		settings.add(Button.backcolor, TinyMCESettings.Toolbar.second, TinyMCESettings.Position.after);

		// third toolbar
		TablePlugin tablePlugin = new TablePlugin();
		EmotionsPlugin emotionsPlugin = new EmotionsPlugin();
		IESpellPlugin iespellPlugin = new IESpellPlugin();
		MediaPlugin mediaPlugin = new MediaPlugin();
		PrintPlugin printPlugin = new PrintPlugin();
		FullScreenPlugin fullScreenPlugin = new FullScreenPlugin();
		DirectionalityPlugin directionalityPlugin = new DirectionalityPlugin();
		settings.add(tablePlugin.getTableControls(), TinyMCESettings.Toolbar.third, TinyMCESettings.Position.before);
		settings.add(emotionsPlugin.getEmotionsButton(), TinyMCESettings.Toolbar.third, TinyMCESettings.Position.after);
		settings.add(iespellPlugin.getIespellButton(), TinyMCESettings.Toolbar.third, TinyMCESettings.Position.after);
		settings.add(mediaPlugin.getMediaButton(), TinyMCESettings.Toolbar.third, TinyMCESettings.Position.after);
		settings.add(Button.separator, TinyMCESettings.Toolbar.third, TinyMCESettings.Position.after);
		settings.add(printPlugin.getPrintButton(), TinyMCESettings.Toolbar.third, TinyMCESettings.Position.after);
		settings.add(Button.separator, TinyMCESettings.Toolbar.third, TinyMCESettings.Position.after);
		settings
					.add(directionalityPlugin.getLtrButton(), TinyMCESettings.Toolbar.third,
							TinyMCESettings.Position.after);
		settings
					.add(directionalityPlugin.getRtlButton(), TinyMCESettings.Toolbar.third,
							TinyMCESettings.Position.after);
		settings.add(Button.separator, TinyMCESettings.Toolbar.third, TinyMCESettings.Position.after);
		settings.add(fullScreenPlugin.getFullscreenButton(), TinyMCESettings.Toolbar.third,
					TinyMCESettings.Position.after);

		// fourth toolbar
		SpellCheckPlugin spellCheckPlugin = new SpellCheckPlugin();
		settings.add(spellCheckPlugin.getSpellCheckButton(), TinyMCESettings.Toolbar.fourth,
					TinyMCESettings.Position.after);

		// other settings
		settings.setToolbarAlign(TinyMCESettings.Align.left);
		settings.setToolbarLocation(TinyMCESettings.Location.top);
		settings.setStatusbarLocation(TinyMCESettings.Location.bottom);
		settings.setResizing(true);
		return new TinyMceBehavior(settings);
	}

	private static TinyMceBehavior configureSimpleRichClient() {
		TinyMCESettings settings = new TinyMCESettings(TinyMCESettings.Theme.advanced);

		ContextMenuPlugin contextMenuPlugin = new ContextMenuPlugin();
		settings.register(contextMenuPlugin);

		// first toolbar
		settings.add(Button.fontselect, TinyMCESettings.Toolbar.first, TinyMCESettings.Position.after);
		settings.add(Button.fontsizeselect, TinyMCESettings.Toolbar.first, TinyMCESettings.Position.after);

		// second toolbar
		PreviewPlugin previewPlugin = new PreviewPlugin();
		settings.add(Button.cut, TinyMCESettings.Toolbar.second, TinyMCESettings.Position.before);
		settings.add(Button.copy, TinyMCESettings.Toolbar.second, TinyMCESettings.Position.before);
		settings.add(Button.separator, TinyMCESettings.Toolbar.second, TinyMCESettings.Position.before);
		settings.add(Button.separator, TinyMCESettings.Toolbar.second, TinyMCESettings.Position.after);
		settings.add(Button.separator, TinyMCESettings.Toolbar.second, TinyMCESettings.Position.after);
		settings.add(previewPlugin.getPreviewButton(), TinyMCESettings.Toolbar.second, TinyMCESettings.Position.after);
		settings.add(Button.separator, TinyMCESettings.Toolbar.second, TinyMCESettings.Position.after);
		settings.add(Button.forecolor, TinyMCESettings.Toolbar.second, TinyMCESettings.Position.after);
		settings.add(Button.backcolor, TinyMCESettings.Toolbar.second, TinyMCESettings.Position.after);

		// third toolbar
		TablePlugin tablePlugin = new TablePlugin();
		MediaPlugin mediaPlugin = new MediaPlugin();
		PrintPlugin printPlugin = new PrintPlugin();
		FullScreenPlugin fullScreenPlugin = new FullScreenPlugin();
		DirectionalityPlugin directionalityPlugin = new DirectionalityPlugin();
		settings.add(tablePlugin.getTableControls(), TinyMCESettings.Toolbar.third, TinyMCESettings.Position.before);
		settings.add(mediaPlugin.getMediaButton(), TinyMCESettings.Toolbar.third, TinyMCESettings.Position.after);
		settings.add(Button.separator, TinyMCESettings.Toolbar.third, TinyMCESettings.Position.after);
		settings.add(printPlugin.getPrintButton(), TinyMCESettings.Toolbar.third, TinyMCESettings.Position.after);
		settings.add(Button.separator, TinyMCESettings.Toolbar.third, TinyMCESettings.Position.after);
		settings.add(directionalityPlugin.getLtrButton(), TinyMCESettings.Toolbar.third, TinyMCESettings.Position.after);
		settings.add(directionalityPlugin.getRtlButton(), TinyMCESettings.Toolbar.third, TinyMCESettings.Position.after);
		settings.add(Button.separator, TinyMCESettings.Toolbar.third, TinyMCESettings.Position.after);
		settings.add(fullScreenPlugin.getFullscreenButton(), TinyMCESettings.Toolbar.third,
				TinyMCESettings.Position.after);

		// other settings
		settings.setToolbarAlign(TinyMCESettings.Align.left);
		settings.setToolbarLocation(TinyMCESettings.Location.top);
		settings.setStatusbarLocation(TinyMCESettings.Location.bottom);
		settings.setResizing(true);
		return new TinyMceBehavior(settings);
	}

	public static Label label(String id, String content) {
		return new Label(id, content);
	}

	public static <T extends Serializable> DropDownChoice<T> dropDown(String id, Model<T> model,
			List<? extends T> values) {
		return new DropDownChoice<T>(id, model, values);
	}

	public static <T extends Serializable> DropDownChoice<T> dropDown(String id, PropertyModel<T> model,
			List<? extends T> values) {
		return new DropDownChoice<T>(id, model, values);
	}

	public static Label richLabel(String id, String content) {
		return (Label) new Label(id, content).setEscapeModelStrings(false);
	}

	public static RedirectLink redirectLink(String id, Class<? extends Page> page) {
		return new RedirectLink(id, page);
	}

	public static RedirectLink cancelLink(Class<? extends Page> page) {
		return new RedirectLink("cancel", page);
	}

	public static <T extends FormComponent<?>> T withLabel(String label, T comp) {
		comp.setLabel(new Model<String>(label));
		return comp;
	}

}
