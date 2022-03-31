package flagmaker.overlays;

import flagmaker.data.Flag;
import flagmaker.files.FileHandler;
import flagmaker.files.LocalizationHandler;
import flagmaker.overlays.overlaytypes.specialtypes.OverlayFlag;
import flagmaker.overlays.overlaytypes.specialtypes.OverlayImage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class OverlaySelector extends VBox {
	private Stage stage;

	@FXML
	private TabPane tabs;
	@FXML
	private Button btnCancel;

	private final int defaultMaximumX;
	private final int defaultMaximumY;
	private Overlay selectedOverlay;

	public OverlaySelector(Stage stage, int defaultMaximumX, int defaultMaximumY) {
		load(stage);

		this.defaultMaximumX = defaultMaximumX;
		this.defaultMaximumY = defaultMaximumY;
		stage.titleProperty().set(LocalizationHandler.get("Overlays"));
		stage.getIcons().add(new Image("flagmaker/images/icon.png"));
		btnCancel.setText(LocalizationHandler.get("Cancel"));
		fillOverlays();
	}

	public Overlay getSelectedOverlay() {
		return selectedOverlay;
	}

	private void setSelectedOverlay(Overlay value) {
		selectedOverlay = value;
		this.stage.close();
	}

	private void fillOverlays() {
		addTab(OverlayFactory.getShapes(), LocalizationHandler.get("Shapes"));
		addTab(OverlayFactory.getEmblems(), LocalizationHandler.get("Emblems"));
		addTab(OverlayFactory.getCustom(), LocalizationHandler.get("Custom"));
		addTab(OverlayFactory.getSpecial(), LocalizationHandler.get("Special"));
	}

	private void addTab(Overlay[] overlays, String tabName) {
		Tab tab = new Tab(tabName);
		FlowPane panel = new FlowPane(Orientation.HORIZONTAL);
		ScrollPane scrollPane = new ScrollPane(panel);
		scrollPane.setFitToWidth(true);

		panel.setPadding(new Insets(5));

		for (Overlay overlay : overlays) {
			Button b = new Button();
			b.setPrefHeight(30);
			b.setPrefWidth(30);
			b.graphicProperty().set(overlay.paneThumbnail());
			b.tooltipProperty().set(new Tooltip(overlay.name));
			b.addEventHandler(ActionEvent.ACTION, event -> {
				String name = b.getTooltip().getText();
				switch (name) {
				case "flag":
					loadFlag();
					break;
				case "image":
					loadImage();
					break;
				default:
					setSelectedOverlay(OverlayFactory.getInstanceByShortName(name, defaultMaximumX, defaultMaximumY));
					break;
				}
			});
			panel.getChildren().add(b);
		}

		tab.setContent(scrollPane);
		tabs.getTabs().add(tab);
	}

	private void loadFlag() {
		FileChooser fileChooserF = new FileChooser();
		fileChooserF.setTitle(LocalizationHandler.get("Open"));
		fileChooserF.getExtensionFilters()
				.add(new FileChooser.ExtensionFilter(LocalizationHandler.get("FlagFileFilter"), "*.flag"));
		File flagFile = fileChooserF.showOpenDialog(stage);
		if (flagFile != null) {
			Flag flag;
			try {
				flag = FileHandler.loadFlagFromFile(flagFile);
				setSelectedOverlay(new OverlayFlag(flag, flagFile, defaultMaximumX, defaultMaximumY));
			} catch (Exception ex) {
				new Alert(Alert.AlertType.ERROR,
						String.format(LocalizationHandler.get("OverlayFlagLoadError"), ex.getMessage()), ButtonType.OK)
								.showAndWait();
			}
		}
	}

	private void loadImage() {
		FileChooser fileChooserI = new FileChooser();
		fileChooserI.setTitle(LocalizationHandler.get("OpenImage"));
		fileChooserI.getExtensionFilters()
				.add(new FileChooser.ExtensionFilter(LocalizationHandler.get("ImageFileFilter"), "*.png", "*.jpg"));
		File imageFile = fileChooserI.showOpenDialog(stage);
		if (imageFile != null) {
			setSelectedOverlay(new OverlayImage(imageFile, defaultMaximumX, defaultMaximumY));
		}
	}

	@FXML
	private void cancel() {
		stage.close();
	}

	private void load(Stage stage) {
		this.stage = stage;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("OverlaySelector.fxml"));
		loader.setRoot(this);
		loader.setController(this);

		try {
			loader.load();
		} catch (Exception ex) {
		}
	}
}
