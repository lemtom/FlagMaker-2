package flagmaker.overlays;

import flagmaker.extensions.ControlExtensions;
import flagmaker.files.LocalizationHandler;
import flagmaker.overlays.attributes.Attribute;
import flagmaker.overlays.attributes.sliders.AttributeSlider;
import flagmaker.overlays.attributes.sliders.NumericAttributeSlider;
import flagmaker.overlays.overlaytypes.pathtypes.OverlayPath;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import flagmaker.MainWindowController;
import flagmaker.UI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class OverlayControl extends VBox {
	@FXML
	private Button btnOverlay;
	@FXML
	private VBox pnlSliders;
	@FXML
	private ImageView btnExpandCollapse;
	@FXML
	private ImageView btnVisibility;

	@FXML
	private Tooltip ttpExpandCollapse;
	@FXML
	private Tooltip ttpChangeType;
	@FXML
	private Tooltip ttpVisibility;
	@FXML
	private Tooltip ttpRemove;
	@FXML
	private Tooltip ttpMoveUp;
	@FXML
	private Tooltip ttpMoveDown;
	@FXML
	private Tooltip ttpClone;

	public Stage stage;

	private Overlay overlay;
	private int defaultMaximumX;
	private int defaultMaximumY;
	private boolean isFirst;
	public final MainWindowController mainWindow;

	public boolean isLoading;
	public boolean wasCanceled;

	public OverlayControl(Stage stage, MainWindowController mainWindow, int defaultMaximumX, int defaultMaximumY,
			boolean isLoading) {
		load(stage);
		loadLocalization();

		this.isLoading = isLoading;
		this.mainWindow = mainWindow;
		this.defaultMaximumX = defaultMaximumX;
		this.defaultMaximumY = defaultMaximumY;
		isFirst = true;

		if (!this.isLoading) {
			overlaySelect();
		}
	}

	public Overlay getOverlay() {
		return overlay;
	}

	public void setOverlay(Overlay value) {
		Attribute[] oldAttributes = saveOldEmblemAttributes(value);

		overlay = value;
		btnOverlay.graphicProperty().set(overlay.paneThumbnail());
		btnOverlay.tooltipProperty().set(new Tooltip(overlay.name));

		setAttributesFromSliders();
		setVisibilityButton();
		addSliders();
		copyOldEmblemAttributes(oldAttributes);

		isFirst = false;
		isLoading = false;
	}

	public void overlaySliderChanged(boolean triggeredByUser) {
		if (triggeredByUser) {
			overlay.setValues(getAttributeSliderValues());
			draw();
		}
		mainWindow.setAsUnsaved();
	}

	public void expand() {
		ControlExtensions.showControl(pnlSliders);
		setCollapseButton("flagmaker/images/collapse.png");
		ttpExpandCollapse.setText(LocalizationHandler.get("Collapse"));
	}

	public void collapse() {
		ControlExtensions.hideControl(pnlSliders);
		setCollapseButton("flagmaker/images/expand.png");
		ttpExpandCollapse.setText(LocalizationHandler.get("Expand"));
	}

	public void setMaximum(int maximumX, int maximumY) {
		defaultMaximumX = maximumX;
		defaultMaximumY = maximumY;

		overlay.setMaximum(maximumX, maximumY);

		AttributeSlider[] sliders = getAttributeSliders();
		for (AttributeSlider slider : sliders) {
			if (slider instanceof NumericAttributeSlider) {
				((NumericAttributeSlider) slider)
						.setMaximum(((NumericAttributeSlider) slider).useMaxX ? defaultMaximumX : defaultMaximumY);
			}
		}
	}

	private void addSliders() {
		pnlSliders.getChildren().clear();
		for (Attribute a : overlay.getAttributes()) {
			pnlSliders.getChildren().add(a.getSlider(this));
		}
	}

	private void setAttributesFromSliders() {
		if (!isFirst && !isLoading) {
			HashMap<String, Object> sliderValues = getAttributeSliderValues();
			if (!sliderValues.isEmpty()) {
				sliderValues.clear();
				overlay.setValues(sliderValues);
			}
		}
	}

	private Attribute[] saveOldEmblemAttributes(Overlay value) {
		if (overlay instanceof OverlayPath && value instanceof OverlayPath) {
			return overlay.getAttributes();
		}

		return null;
	}

	private void copyOldEmblemAttributes(Attribute[] oldAttributes) {
		if (oldAttributes != null) {
			for (int i = 0; i < oldAttributes.length; i++) {
				AttributeSlider slider = (AttributeSlider) pnlSliders.getChildren().get(i);
				slider.setValue(oldAttributes[i].getValue());
			}
		}
	}

	private void loadLocalization() {
		ttpExpandCollapse.setText(LocalizationHandler.get("Collapse"));
		ttpChangeType.setText(LocalizationHandler.get("OverlayChangeType"));
		ttpVisibility.setText(LocalizationHandler.get("ToggleVisibility"));
		ttpRemove.setText(LocalizationHandler.get("Remove"));
		ttpMoveUp.setText(LocalizationHandler.get("MoveUp"));
		ttpMoveDown.setText(LocalizationHandler.get("MoveDown"));
		ttpClone.setText(LocalizationHandler.get("Clone"));
	}

	@FXML
	private void overlaySelect() {
		Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(stage);
		OverlaySelector control = new OverlaySelector(dialog, defaultMaximumX, defaultMaximumY);
		Scene dialogScene = new Scene(control, 400, 300);
		dialogScene.getStylesheets().add(UI.class.getResource("Style.css").toExternalForm());
		dialog.setScene(dialogScene);
		dialog.showAndWait();

		Overlay o = control.getSelectedOverlay();
		if (o == null) {
			wasCanceled = true;
			return;
		}

		setOverlay(o);
		if (!isLoading)
			draw();
	}

	private void draw() {
		mainWindow.draw();
	}

	@FXML
	private void remove() {
		mainWindow.remove(this);
	}

	@FXML
	private void moveUp() {
		mainWindow.moveUp(this);
	}

	@FXML
	private void moveDown() {
		mainWindow.moveDown(this);
	}

	@FXML
	private void cloneThis() {
		mainWindow.clone(this);
	}

	@FXML
	private void setCollapsed() {
		if (pnlSliders.visibleProperty().get()) {
			collapse();
		} else {
			expand();
		}
	}

	@FXML
	private void setVisibility() {
		overlay.isEnabled = !overlay.isEnabled;
		setVisibilityButton();
		draw();
	}

	private void setCollapseButton(String icon) {
		btnExpandCollapse.setImage(new Image(icon));
	}

	private void setVisibilityButton() {
		btnVisibility.setImage(
				new Image(overlay.isEnabled ? "flagmaker/images/check_on.png" : "flagmaker/images/check_off.png"));
	}

	private void load(Stage stage) {
		this.stage = stage;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("OverlayControl.fxml"));
		loader.setRoot(this);
		loader.setController(this);

		try {
			loader.load();
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	private AttributeSlider[] getAttributeSliders() {
		ArrayList<AttributeSlider> list = new ArrayList<>();
		for (Node control : pnlSliders.getChildren()) {
			list.add((AttributeSlider) control);
		}

		AttributeSlider[] returnValue = new AttributeSlider[] {};
		return list.toArray(returnValue);
	}

	private HashMap<String, Object> getAttributeSliderValues() {
		int sliderCount = pnlSliders.getChildren().size();
		HashMap<String, Object> list = new HashMap<>();

		for (int i = 0; i < sliderCount; i++) {
			AttributeSlider slider = (AttributeSlider) pnlSliders.getChildren().get(i);
			list.put(slider.name, slider.getValue());
		}

		return list;
	}

	Object[] addElement(Object[] original, double added) {
		Object[] result = Arrays.copyOf(original, original.length + 1);
		result[original.length] = added;
		return result;
	}
}
