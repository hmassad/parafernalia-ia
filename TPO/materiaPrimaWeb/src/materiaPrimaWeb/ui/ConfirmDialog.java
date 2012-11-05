package materiaPrimaWeb.ui;

import java.io.Serializable;

import com.vaadin.terminal.gwt.client.ui.label.ContentMode;
import com.vaadin.terminal.gwt.server.JsonPaintTarget;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Root;
import com.vaadin.ui.Window;

public class ConfirmDialog extends Window {

	private static final long serialVersionUID = -2363125714643244070L;

	public interface Factory extends Serializable {
		ConfirmDialog create(String windowCaption, String message, String okTitle, String cancelTitle);
	}

	static final String DEFAULT_WINDOW_CAPTION = "Confirm";
	static final String DEFAULT_CANCEL_CAPTION = "Cancel";
	static final String DEFAULT_OK_CAPTION = "Ok";

	public static final ContentMode CONTENT_TEXT_WITH_NEWLINES = null;
	public static final ContentMode CONTENT_TEXT = ContentMode.TEXT;
	public static final ContentMode CONTENT_PREFORMATTED = ContentMode.PREFORMATTED;
	public static final ContentMode CONTENT_HTML = ContentMode.XHTML;
	public static final ContentMode CONTENT_DEFAULT = CONTENT_TEXT_WITH_NEWLINES;

	/**
	 * Listener for dialog close events. Implement and register an instance of
	 * this interface to dialog to receive close events.
	 * 
	 * @author Sami Ekblad
	 * 
	 */
	public interface Listener extends Serializable {
		void onClose(ConfirmDialog dialog);
	}

	/**
	 * Default dialog factory.
	 * 
	 */
	private static ConfirmDialog.Factory factoryInstance;

	/**
	 * Get the ConfirmDialog.Factory used to create and configure the dialog.
	 * 
	 * By default the {@link DefaultConfirmDialogFactory} is used.
	 * 
	 * @return
	 */
	public static ConfirmDialog.Factory getFactory() {
		if (factoryInstance == null) {
			factoryInstance = new DefaultConfirmDialogFactory();
		}
		return factoryInstance;
	}

	/**
	 * Set the ConfirmDialog.Factory used to create and configure the dialog.
	 * 
	 * By default the {@link DefaultConfirmDialogFactory} is used.
	 * 
	 * @return
	 */
	public static void setFactory(final ConfirmDialog.Factory newFactory) {
		factoryInstance = newFactory;
	}

	/**
	 * Show a modal ConfirmDialog in a window.
	 * 
	 * @param root
	 * @param listener
	 */
	public static ConfirmDialog show(final Root root, final Listener listener) {
		return show(root, null, null, null, null, listener);
	}

	/**
	 * Show a modal ConfirmDialog in a window.
	 * 
	 * @param root
	 * @param messageLabel
	 * @param listener
	 * @return
	 */
	public static ConfirmDialog show(final Root root, final String message, final Listener listener) {
		return show(root, null, message, null, null, listener);
	}

	/**
	 * Show a modal ConfirmDialog in a window.
	 * 
	 * @param root
	 * @param windowCaption
	 *            Caption for the confirmation dialog window.
	 * @param message
	 *            Message to display as window content.
	 * @param okCaption
	 *            Caption for the ok button.
	 * @param cancelCaption
	 *            Caption for cancel button.
	 * @param listener
	 *            Listener for dialog result.
	 * @return
	 */
	public static ConfirmDialog show(final Root parent, final String windowCaption, final String message, final String okCaption, final String cancelCaption,
			final Listener listener) {
		ConfirmDialog d = getFactory().create(windowCaption, message, okCaption, cancelCaption);
		d.show(parent, listener, true);
		return d;
	}

	/**
	 * Shows a modal ConfirmDialog in given window and executes Runnable if OK
	 * is chosen.
	 * 
	 * @param root
	 * @param windowCaption
	 *            Caption for the confirmation dialog window.
	 * @param message
	 *            Message to display as window content.
	 * @param okCaption
	 *            Caption for the ok button.
	 * @param cancelCaption
	 *            Caption for cancel button.
	 * @param r
	 *            Runnable to be run if confirmed
	 * @return
	 */
	@SuppressWarnings("serial")
	public static ConfirmDialog show(final Root root, final String windowCaption, final String message, final String okCaption, final String cancelCaption,
			final Runnable r) {
		ConfirmDialog d = getFactory().create(windowCaption, message, okCaption, cancelCaption);
		d.show(root, new Listener() {
			public void onClose(ConfirmDialog dialog) {
				if (dialog.isConfirmed()) {
					r.run();
				}
			}
		}, true);
		return d;
	}

	private Listener confirmListener = null;
	private boolean isConfirmed = false;
	private Label messageLabel = null;
	private Button okBtn = null;
	private Button cancelBtn = null;
	private String originalMessageText;
	private ContentMode msgContentMode = CONTENT_TEXT_WITH_NEWLINES;

	/**
	 * Show confirm dialog.
	 * 
	 * @param root
	 * @param listener
	 */
	public final void show(final Root root, final Listener listener, final boolean modal) {
		confirmListener = listener;
		center();
		setModal(modal);
		root.addWindow(this);
	}

	/**
	 * Did the user confirm the dialog.
	 * 
	 * @return
	 */
	public final boolean isConfirmed() {
		return isConfirmed;
	}

	public final Listener getListener() {
		return confirmListener;
	}

	protected final void setOkButton(final Button okButton) {
		okBtn = okButton;
	}

	public final Button getOkButton() {
		return okBtn;
	}

	protected final void setCancelButton(final Button cancelButton) {
		cancelBtn = cancelButton;
	}

	public final Button getCancelButton() {
		return cancelBtn;
	}

	protected final void setMessageLabel(final Label message) {
		messageLabel = message;
	}

	public final void setMessage(final String message) {
		originalMessageText = message;
		messageLabel.setValue(CONTENT_TEXT_WITH_NEWLINES == msgContentMode ? formatDialogMessage(message) : message);
	}

	public final String getMessage() {
		return originalMessageText;
	}

	public final ContentMode getContentMode() {
		return msgContentMode;
	}

	public final void setContentMode(final ContentMode contentMode) {
		msgContentMode = contentMode;
		messageLabel.setContentMode(contentMode == CONTENT_TEXT_WITH_NEWLINES ? CONTENT_TEXT : contentMode);
		messageLabel.setValue(contentMode == CONTENT_TEXT_WITH_NEWLINES ? formatDialogMessage(originalMessageText) : originalMessageText);
	}

	/**
	 * Format the messageLabel by maintaining text only.
	 * 
	 * @param text
	 * @return
	 */
	protected final String formatDialogMessage(final String text) {
		return JsonPaintTarget.escapeXML(text).replaceAll("\n", "<br />");
	}

	/**
	 * Set the isConfirmed state.
	 * 
	 * Note: this should only be called internally by the listeners.
	 * 
	 * @param isConfirmed
	 */
	protected final void setConfirmed(final boolean confirmed) {
		isConfirmed = confirmed;
	}
}