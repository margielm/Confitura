package eu.margiel.pages.javarsovia.c4p;

import org.wicketstuff.annotation.mount.MountPath;

@SuppressWarnings("serial")
@MountPath(path = "c4p/speaker/edit")
public class EditSpeakerPage extends SpeakerBasePage {

	public EditSpeakerPage() {
		add(new EditSpeakerPanel("speaker", getSession().getSpeaker()) {

			@Override
			public void onCancelOrSubmit() {
				setResponsePage(ViewSpeakerPage.class);
			}
		});

	}

}
