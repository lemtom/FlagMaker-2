package flagmaker;

import flagmaker.about.AboutController;
import flagmaker.color.ColorButton;
import flagmaker.color.ColorButtonListener;
import flagmaker.data.Flag;
import flagmaker.data.Ratio;
import flagmaker.data.Size;
import flagmaker.divisions.*;
import flagmaker.extensions.CommonExtensions;
import flagmaker.extensions.ControlExtensions;
import flagmaker.extensions.StringExtensions;
import flagmaker.files.FileHandler;
import flagmaker.files.LocalizationHandler;
import flagmaker.overlays.Overlay;
import flagmaker.overlays.OverlayControl;
import flagmaker.overlays.OverlayFactory;
import flagmaker.overlays.overlaytypes.specialtypes.OverlayFlag;
import flagmaker.randomflag.RandomFlagFactory;

import static flagmaker.extensions.StringExtensions.isNullOrWhitespace;
import static java.lang.Boolean.FALSE;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.SubScene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Line;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainWindowController implements ColorButtonListener {
	@FXML
	private Menu mnuFile;
	@FXML
	private MenuItem mnuNew;
	@FXML
	private MenuItem mnuOpen;
	@FXML
	private MenuItem mnuSave;
	@FXML
	private MenuItem mnuSaveAs;
	@FXML
	private MenuItem mnuExportPng;
	@FXML
	private MenuItem mnuExportSvg;
	@FXML
	private MenuItem mnuBulkExportPng;
	@FXML
	private MenuItem mnuBulkExportSvg;
	@FXML
	private Menu mnuPresets;
	@FXML
	private Menu mnuLanguages;
	@FXML
	private Menu mnuHelp;
	@FXML
	private MenuItem mnuAbout;

	@FXML
	private Label lblRatio;
	@FXML
	private Label lblGridSize;
	@FXML
	private TextField txtRatioHeight;
	@FXML
	private TextField txtRatioWidth;
	@FXML
	private ComboBox<String> cmbRatio;

	@FXML
	private Tooltip ttpShowGrid;
	@FXML
	private Tooltip ttpShuffleColors;
	@FXML
	private Tooltip ttpToggleTexture;
	@FXML
	private Tooltip ttpRandomFlag;

	@FXML
	private Tooltip ttpDivisionGrid;
	@FXML
	private Tooltip ttpDivisionFesses;
	@FXML
	private Tooltip ttpDivisionPales;
	@FXML
	private Tooltip ttpDivisionBendsForward;
	@FXML
	private Tooltip ttpDivisionBendsBackward;
	@FXML
	private Tooltip ttpDivisionBendsBoth;
	@FXML
	private Label lblPresets;
	@FXML
	private Label lblOverlays;

	@FXML
	private AnchorPane leftAnchor;
	@FXML
	private StackPane leftStack;

	@FXML
	private TextField txtName;
	@FXML
	private Slider divisionSlider1;
	@FXML
	private Slider divisionSlider2;
	@FXML
	private Slider divisionSlider3;
	@FXML
	private Label divisionLabel1;
	@FXML
	private Label divisionLabel2;
	@FXML
	private Label divisionLabel3;
	@FXML
	private ColorButton divisionPicker1;
	@FXML
	private ColorButton divisionPicker2;
	@FXML
	private ColorButton divisionPicker3;
	@FXML
	private ComboBox<String> cmbPresets;

	@FXML
	private VBox lstOverlays;
	@FXML
	private Tooltip ttpOverlayAddNew;
	@FXML
	private Tooltip ttpOverlaysExpandAll;
	@FXML
	private Tooltip ttpOverlaysCollapseAll;

	@FXML
	private Label lblDivisions;

	private Stage stage;
	private SubScene subScene;
	private Pane pane;

	private SubScene gridSubScene;
	private Pane gridPane;

	private Division division;

	private boolean isLoading;
	private boolean showGrid;
	private int texture;

	private String headerText;
	private String fileName;
	private boolean isUnsaved;

	private Ratio ratio;

	public List<Color> recentColors;

	@FXML
	protected void initialize() {
		loadLocalization();
		addWorkspace();

		headerText = String.format(" - %s", CommonExtensions.titleAndVersionString(getClass()));

		setColorsAndSliders();
		loadBasicPresets();
		loadFilePresets();
		hookUpEvents();
		OverlayFactory.setUpTypeMap();
		OverlayFactory.fillCustomOverlays();
		newFile();
	}

	public void setPrimaryStage(Stage stage) {
		this.stage = stage;
	}

	private void loadLocalization() {
		loadLanguageMenu();

		LocalizationHandler.initialize();
		setWindowStrings();
	}

	private void loadLanguageMenu() {
		Locale[] locales = new Locale[] { new Locale("en", "US"), new Locale("es", "MX"), new Locale("fr", "FR"),
				new Locale("pl", "PL"), new Locale("ru", "RU") };

		for (Locale locale : locales) {
			MenuItem item = new MenuItem(locale.getDisplayName());
			item.addEventHandler(EventType.ROOT, (Event event) -> {
				LocalizationHandler.setLanguage(locale);
				setWindowStrings();
			});
			mnuLanguages.getItems().add(item);
		}
	}

	private void setWindowStrings() {
		mnuFile.setText(LocalizationHandler.get("File"));
		mnuNew.setText(LocalizationHandler.get("New"));
		mnuOpen.setText(LocalizationHandler.get("OpenMenu"));
		mnuSave.setText(LocalizationHandler.get("SaveMenu"));
		mnuSaveAs.setText(LocalizationHandler.get("SaveAs"));
		mnuExportPng.setText(LocalizationHandler.get("ExportAsPngMenu"));
		mnuExportSvg.setText(LocalizationHandler.get("ExportAsSvgMenu"));
		mnuBulkExportPng.setText(LocalizationHandler.get("ExportBulkAsPng"));
		mnuBulkExportSvg.setText(LocalizationHandler.get("ExportBulkAsSvg"));
		mnuPresets.setText(LocalizationHandler.get("WorldFlagPresets"));
		mnuLanguages.setText(LocalizationHandler.get("Language"));
		mnuHelp.setText(LocalizationHandler.get("Help"));
		mnuAbout.setText(LocalizationHandler.get("About"));

		lblRatio.setText(LocalizationHandler.get("Ratio"));
		lblGridSize.setText(LocalizationHandler.get("GridSize"));
		ttpShowGrid.setText(LocalizationHandler.get("ShowGrid"));
		ttpShuffleColors.setText(LocalizationHandler.get("ShuffleColors"));
		ttpToggleTexture.setText(LocalizationHandler.get("ToggleTexture"));
		ttpRandomFlag.setText(LocalizationHandler.get("GenerateRandomFlag"));

		lblDivisions.setText(LocalizationHandler.get("Division"));
		ttpDivisionGrid.setText(LocalizationHandler.get("DivisionGrid"));
		ttpDivisionFesses.setText(LocalizationHandler.get("DivisionFesses"));
		ttpDivisionPales.setText(LocalizationHandler.get("DivisionPales"));
		ttpDivisionBendsForward.setText(LocalizationHandler.get("DivisionBendsForward"));
		ttpDivisionBendsBackward.setText(LocalizationHandler.get("DivisionBendsBackward"));
		ttpDivisionBendsBoth.setText(LocalizationHandler.get("DivisionBendsBoth"));
		lblPresets.setText(LocalizationHandler.get("DivisionPresets"));

		lblOverlays.setText(LocalizationHandler.get("Overlays"));
		ttpOverlayAddNew.setText(LocalizationHandler.get("OverlayAdd"));
		ttpOverlaysExpandAll.setText(LocalizationHandler.get("ExpandAll"));
		ttpOverlaysCollapseAll.setText(LocalizationHandler.get("CollapseAll"));
		loadBasicPresets();
	}

	private void addWorkspace() {
		pane = new Pane();
		subScene = new SubScene(leftAnchor, 300, 200);
		subScene.setRoot(pane);
		leftStack.getChildren().add(subScene);

		gridPane = new Pane();
		gridPane.setBackground(Background.EMPTY);
		gridSubScene = new SubScene(leftAnchor, 300, 200);
		gridSubScene.setRoot(gridPane);
		leftStack.getChildren().add(gridSubScene);

		// Draw whenever the left side changes size
		leftStack.widthProperty().addListener((observable, oldValue, newValue) -> {
			if (!isLoading && !oldValue.equals(newValue))
				draw();
		});
		cmbRatio.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (!isLoading) {
				gridSizeDropdownChanged();
			}
		});

		ratio = new Ratio(2, 3);
		subScene.widthProperty().bind(Bindings.createDoubleBinding(() -> leftStack.getWidth() - 10,
				leftStack.widthProperty(), leftStack.heightProperty()));
		subScene.heightProperty()
				.bind(Bindings.createDoubleBinding(() -> (leftStack.getWidth() - 10) * ratio.height / ratio.width,
						leftStack.widthProperty(), leftStack.heightProperty(), txtRatioHeight.textProperty(),
						txtRatioWidth.textProperty()));
		gridSubScene.widthProperty().bind(Bindings.createDoubleBinding(() -> leftStack.getWidth() - 10,
				leftStack.widthProperty(), leftStack.heightProperty()));
		gridSubScene.heightProperty()
				.bind(Bindings.createDoubleBinding(() -> (leftStack.getWidth() - 10) * ratio.height / ratio.width,
						leftStack.widthProperty(), leftStack.heightProperty(), txtRatioHeight.textProperty(),
						txtRatioWidth.textProperty()));
	}

	private void hookUpEvents() {
		txtRatioHeight.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!isLoading && !newValue.equals(oldValue))
				ratioTextboxChanged();
		});
		txtRatioWidth.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!isLoading && !newValue.equals(oldValue))
				ratioTextboxChanged();
		});

		txtName.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!isLoading && !newValue.equals(oldValue))
				setAsUnsaved();
		});

		stage.setOnCloseRequest(this::onClosing);
	}

	private void setTitle() {
		String title = String
				.format("%s%s%s",
						StringExtensions.isNullOrWhitespace(fileName) ? LocalizationHandler.get("Untitled")
								: StringExtensions.getFilenameWithoutExtension(fileName),
						isUnsaved ? "*" : "", headerText);
		stage.setTitle(title);
	}

	// Division
	@Override
	public void colorChanged(Color oldval, Color newval) {
		if (!oldval.equals(newval)) {
			divisionColorChanged();
		}
	}

	private void divisionColorChanged() {
		if (isLoading)
			return;

		division.setColors(
				new Color[] { divisionPicker1.getValue(), divisionPicker2.getValue(), divisionPicker3.getValue() });
		draw();
		setAsUnsaved();
	}

	private void divisionSliderChanged() {
		if (isLoading)
			return;

		divisionLabel1.setText(String.format("%d", divisionSlider1.valueProperty().intValue()));
		divisionLabel2.setText(String.format("%d", divisionSlider2.valueProperty().intValue()));
		divisionLabel3.setText(String.format("%d", divisionSlider3.valueProperty().intValue()));
		division.setValues(new int[] { divisionSlider1.valueProperty().intValue(),
				divisionSlider2.valueProperty().intValue(), divisionSlider3.valueProperty().intValue() });
		draw();
		setAsUnsaved();
	}

	private void setDivisionVisibility() {
		divisionPicker1.setValue(division.colors[0]);
		divisionPicker2.setValue(division.colors[1]);

		if (division.colors.length > 2) {
			divisionPicker3.setValue(division.colors[2]);
			ControlExtensions.showControl(divisionPicker3);
		} else {
			ControlExtensions.hideControl(divisionPicker3);
		}

		ControlExtensions.hideControl(divisionSlider1);
		ControlExtensions.hideControl(divisionSlider2);
		ControlExtensions.hideControl(divisionSlider3);
		ControlExtensions.hideControl(divisionLabel1);
		ControlExtensions.hideControl(divisionLabel2);
		ControlExtensions.hideControl(divisionLabel3);

		if (division.values.length <= 0)
			return;
		divisionSlider1.setValue(division.values[0]);
		ControlExtensions.showControl(divisionSlider1);
		divisionLabel1.setText(String.format("%d", division.values[0]));
		ControlExtensions.showControl(divisionLabel1);

		if (division.values.length <= 1)
			return;
		divisionSlider2.setValue(division.values[1]);
		ControlExtensions.showControl(divisionSlider2);
		divisionLabel2.setText(String.format("%d", division.values[1]));
		ControlExtensions.showControl(divisionLabel2);

		if (division.values.length <= 2)
			return;
		divisionSlider3.setValue(division.values[2]);
		ControlExtensions.showControl(divisionSlider3);
		divisionLabel3.setText(String.format("%d", division.values[2]));
		ControlExtensions.showControl(divisionLabel3);
	}

	@FXML
	private void divisionGridClick() {
		division = new DivisionGrid(divisionPicker1.getValue(), divisionPicker2.getValue(),
				divisionSlider1.valueProperty().intValue(), divisionSlider2.valueProperty().intValue());
		setDivisionVisibility();
		draw();
		setAsUnsaved();
	}

	@FXML
	private void divisionFessesClick() {
		division = new DivisionFesses(divisionPicker1.getValue(), divisionPicker2.getValue(),
				divisionPicker3.getValue(), divisionSlider1.valueProperty().intValue(),
				divisionSlider2.valueProperty().intValue(), divisionSlider3.valueProperty().intValue());
		setDivisionVisibility();
		draw();
		setAsUnsaved();
	}

	@FXML
	private void divisionPalesClick() {
		division = new DivisionPales(divisionPicker1.getValue(), divisionPicker2.getValue(), divisionPicker3.getValue(),
				divisionSlider1.valueProperty().intValue(), divisionSlider2.valueProperty().intValue(),
				divisionSlider3.valueProperty().intValue());
		setDivisionVisibility();
		draw();
		setAsUnsaved();
	}

	@FXML
	private void divisionBendsForwardClick() {
		division = new DivisionBendsForward(divisionPicker1.getValue(), divisionPicker2.getValue());
		setDivisionVisibility();
		draw();
		setAsUnsaved();
	}

	@FXML
	private void divisionBendsBackwardClick() {
		division = new DivisionBendsBackward(divisionPicker1.getValue(), divisionPicker2.getValue());
		setDivisionVisibility();
		draw();
		setAsUnsaved();
	}

	@FXML
	private void divisionXClick() {
		division = new DivisionX(divisionPicker1.getValue(), divisionPicker2.getValue());
		setDivisionVisibility();
		draw();
		setAsUnsaved();
	}

	// Overlays
	@FXML
	private void overlayAdd() {
		overlayAdd(lstOverlays.getChildren().size(), null, false);
	}

	public void remove(OverlayControl overlayControl) {
		lstOverlays.getChildren().remove(overlayControl);
		draw();
		setAsUnsaved();
	}

	public void moveUp(OverlayControl overlayControl) {
		int index = lstOverlays.getChildren().indexOf(overlayControl);
		if (index == 0)
			return;

		ArrayList<OverlayControl> controls = new ArrayList<>();
		for (int i = 0; i < lstOverlays.getChildren().size(); i++) {
			if (i + 1 == index) {
				controls.add((OverlayControl) lstOverlays.getChildren().get(i + 1));
				controls.add((OverlayControl) lstOverlays.getChildren().get(i));
				i++;
			} else {
				controls.add((OverlayControl) lstOverlays.getChildren().get(i));
			}
		}

		lstOverlays.getChildren().clear();
		for (OverlayControl control : controls) {
			lstOverlays.getChildren().add(control);
		}

		draw();
		setAsUnsaved();
	}

	public void moveDown(OverlayControl overlayControl) {
		int index = lstOverlays.getChildren().indexOf(overlayControl);
		if (index == lstOverlays.getChildren().size() - 1)
			return;

		ArrayList<OverlayControl> controls = new ArrayList<>();
		for (int i = 0; i < lstOverlays.getChildren().size(); i++) {
			if (i == index) {
				controls.add((OverlayControl) lstOverlays.getChildren().get(i + 1));
				controls.add((OverlayControl) lstOverlays.getChildren().get(i));
				i++;
			} else {
				controls.add((OverlayControl) lstOverlays.getChildren().get(i));
			}
		}

		lstOverlays.getChildren().clear();
		for (OverlayControl control : controls) {
			lstOverlays.getChildren().add(control);
		}

		draw();
		setAsUnsaved();
	}

	public void clone(OverlayControl controlToClone) {
		int index = lstOverlays.getChildren().indexOf(controlToClone);
		Overlay original = controlToClone.getOverlay();
		Class<? extends Overlay> type = original.getClass();
		Overlay copy = OverlayFactory.getInstanceByLongName(type.getName(), 1, 1);

		for (int i = 0; i < original.attributes.length; i++) {
			copy.attributes[i] = original.attributes[i].clone();
		}

		if (type.isAssignableFrom(OverlayFlag.class)) {
			((OverlayFlag) copy).flag = ((OverlayFlag) original).flag;
		}

		Ratio gridSize = selectedGridSize();
		copy.setMaximum(gridSize.width, gridSize.height);

		overlayAdd(index + 1, copy, true);
	}

	private void overlayAdd(int index, Overlay overlay, boolean isLoading) {
		Ratio gridSize = selectedGridSize();
		OverlayControl control = new OverlayControl(stage, this, gridSize.width, gridSize.height, isLoading);

		if (control.wasCanceled) {
			return;
		}

		if (overlay != null) {
			control.setOverlay(overlay);
		}

		lstOverlays.getChildren().add(control);

		if (!isLoading) {
			draw();
			setAsUnsaved();
		}
	}

	@FXML
	private void overlaysExpandAll() {
		for (OverlayControl overlay : (List<OverlayControl>) (List<?>) lstOverlays.getChildren()) {
			overlay.expand();
		}
	}

	@FXML
	private void overlaysCollapseAll() {
		for (OverlayControl overlay : (List<OverlayControl>) (List<?>) lstOverlays.getChildren()) {
			overlay.collapse();
		}
	}

	// Colors
	private void setColorsAndSliders() {
		setDefaultColors();

		recentColors = new ArrayList<>();
		divisionPicker1.setListener(this, stage, this);
		divisionPicker2.setListener(this, stage, this);
		divisionPicker3.setListener(this, stage, this);

		divisionSlider1.valueProperty()
				.addListener((ObservableValue<? extends Number> ov, Number oldval, Number newval) -> {
					if (!oldval.equals(newval))
						divisionSliderChanged();
				});
		divisionSlider2.valueProperty()
				.addListener((ObservableValue<? extends Number> ov, Number oldval, Number newval) -> {
					if (!oldval.equals(newval))
						divisionSliderChanged();
				});
		divisionSlider3.valueProperty()
				.addListener((ObservableValue<? extends Number> ov, Number oldval, Number newval) -> {
					if (!oldval.equals(newval))
						divisionSliderChanged();
				});
	}

	private void setDefaultColors() {
		divisionPicker1.setValue(Color.rgb(198, 12, 48));
		divisionPicker2.setValue(Color.rgb(253, 200, 47));
		divisionPicker3.setValue(Color.rgb(0, 38, 100));
	}

	@FXML
	private void shuffleColors() {
		Flag f = flag();
		f.shuffleColors();
		loadFlag(f);
		setAsUnsaved();
	}

	// Grid
	private Ratio selectedGridSize() {
		String value = cmbRatio.getValue();
		return value == null ? new Ratio(2, 3) : new Ratio(value);
	}

	private void setRatio(int width, int height) {
		txtRatioHeight.setText(height + "");
		txtRatioWidth.setText(width + "");
		ratio = new Ratio(width, height);
		fillGridCombobox();
	}

	@FXML
	private void gridOnChanged() {
		showGrid = !showGrid;
		drawGrid();
	}

	private void drawGrid() {
		gridPane.getChildren().clear();
		if (!showGrid) {
			return;
		}
		if (cmbRatio.getItems().isEmpty()) {
			return;
		}

		Ratio gridSize = selectedGridSize();
		double intervalX = gridPane.getWidth() / gridSize.width;
		double intervalY = gridPane.getHeight() / gridSize.height;

		for (int x = 0; x <= gridSize.width; x++) {
			Line line = new Line(x * intervalX, 0, x * intervalX, gridPane.getHeight());
			line.setStrokeWidth(5);
			line.setStroke(Color.SILVER);
			gridPane.getChildren().add(line);
		}

		for (int y = 0; y <= gridSize.height; y++) {
			Line line = new Line(0, y * intervalY, gridPane.getWidth(), y * intervalX);
			line.setStrokeWidth(5);
			line.setStroke(Color.SILVER);
			gridPane.getChildren().add(line);
		}
	}

	private void fillGridCombobox() {
		cmbRatio.getItems().clear();
		for (int i = 1; i <= 20; i++) {
			int h = i * ratio.height;
			int w = i * ratio.width;
			cmbRatio.getItems().add(h + ":" + w);
		}
		cmbRatio.getSelectionModel().select(0);
	}

	@FXML
	public void ratioTextboxChanged() {
		String sheight = txtRatioHeight.getText();
		String swidth = txtRatioWidth.getText();

		int height = canParseInt(sheight) ? Integer.parseInt(sheight) : 1;
		int width = canParseInt(swidth) ? Integer.parseInt(swidth) : 1;

		ratio = new Ratio(width, height);
		leftStack.autosize();

		if (!isLoading) {
			draw();
			setAsUnsaved();
		}

		fillGridCombobox();
	}

	@FXML
	private void gridSizeDropdownChanged() {
		if (cmbRatio.getItems().isEmpty())
			return;
		Ratio gridSize = selectedGridSize();
		int sliderMax = Math.max(gridSize.width, gridSize.height);

		divisionSlider1.setMax(sliderMax);
		divisionSlider2.setMax(sliderMax);
		divisionSlider3.setMax(sliderMax);

		for (OverlayControl overlay : (List<OverlayControl>) (List<?>) lstOverlays.getChildren()) {
			overlay.setMaximum(gridSize.width, gridSize.height);
		}

		draw();
		setAsUnsaved();
	}

	// Other
	public void setAsUnsaved() {
		isUnsaved = true;
		setTitle();
	}

	public void draw() {
		if (!isLoading) {
			flag().draw(pane);
			drawTexture(pane);
			drawGrid();
		}
	}

	private void drawTexture(Pane canvas) {
		if (texture == 0)
			return;

		double width = canvas.getWidth();
		double height = canvas.getHeight();
		InputStream is = getClass().getResourceAsStream(String.format("images/texture/%d.png", texture));
		Image i = new Image(is, width, height, false, true);
		Canvas c = new Canvas(width, height);
		GraphicsContext gc = c.getGraphicsContext2D();
		gc.drawImage(i, 0, 0);
		canvas.getChildren().add(c);
	}

	@FXML
	private void toggleTexture() {
		texture = (texture + 1) % 6;
		draw();
	}

	// Export
	public void menuExportPngClick() {
		Size dimensions = getPngDimensions(true);
		if (dimensions.x == 0 || dimensions.y == 0)
			return;

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(LocalizationHandler.get("ExportAsPng"));
		fileChooser.getExtensionFilters().add(new ExtensionFilter("PNG (*.png)", "*.png"));
		File file = fileChooser.showSaveDialog(stage);

		if (file != null) {
			try {
				FileHandler.exportFlagToPng(flag(), dimensions, file);
			} catch (IOException ex) {
				new Alert(AlertType.ERROR, String.format(LocalizationHandler.get("CouldNotSaveError"), ex.getMessage()),
						ButtonType.OK).showAndWait();
			}
		}
	}

	private Size getPngDimensions(boolean constrain) {
		Dialog<Size> dialog = new Dialog<>();
		dialog.setTitle(LocalizationHandler.get("ExportAsPng"));
		dialog.setHeaderText(LocalizationHandler.get("PngSizePrompt"));
		ButtonType saveButtonType = new ButtonType(LocalizationHandler.get("Save"), ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField width = new TextField();
		width.setPromptText(LocalizationHandler.get("Width"));
		TextField height = new TextField();
		height.setPromptText(LocalizationHandler.get("Height"));

		grid.add(new Label(LocalizationHandler.get("Width")), 0, 0);
		grid.add(width, 1, 0);
		grid.add(new Label(LocalizationHandler.get("Height")), 0, 1);
		grid.add(height, 1, 1);

		Node saveButton = dialog.getDialogPane().lookupButton(saveButtonType);
		saveButton.setDisable(true);

		// If the ratio should be constrained, set the other value on blur
		width.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (FALSE.equals(newValue)) {
				String value = width.getText().trim();

				if (canParseInt(value) && constrain) {
					int w = Integer.parseInt(value);
					Ratio r = selectedGridSize();
					height.setText(Integer.toString((int) (r.height / (double) r.width * w)));
					saveButton.setDisable(value.isEmpty() || height.getText().isEmpty() || !canParseInt(value));
				}
			}
		});

		height.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (FALSE.equals(newValue)) {
				String value = height.getText().trim();

				if (canParseInt(value) && constrain) {
					int h = Integer.parseInt(value);
					Ratio r = selectedGridSize();
					width.setText(Integer.toString((int) (r.width / (double) r.height * h)));
					saveButton.setDisable(value.isEmpty() || width.getText().isEmpty() || !canParseInt(value));
				}
			}
		});

		dialog.getDialogPane().setContent(grid);

		Platform.runLater(width::requestFocus);

		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == saveButtonType) {
				return new Size(Integer.parseInt(width.getText()), Integer.parseInt(height.getText()));
			}
			return null;
		});

		Optional<Size> result = dialog.showAndWait();
		return result.orElseGet(() -> new Size(0, 0));
	}

	public void menuExportSvgClick() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(LocalizationHandler.get("ExportAsSvg"));
		fileChooser.getExtensionFilters().add(new ExtensionFilter("SVG (*.svg)", "*.svg"));
		File file = fileChooser.showSaveDialog(stage);

		if (file != null) {
			try {
				FileHandler.exportFlagToSvg(flag(), file);
			} catch (IOException ex) {
				new Alert(AlertType.ERROR, String.format(LocalizationHandler.get("CouldNotSaveError"), ex.getMessage()),
						ButtonType.OK).showAndWait();
			}
		}
	}

	@FXML
	private void menuExportBulkPngClick() {
		boolean error = false;
		List<File> files = getFlagFiles();
		if (files == null || files.isEmpty())
			return;

		Size dimensions = getPngDimensions(false);
		if (dimensions.x == 0 || dimensions.y == 0)
			return;

		File directory = getBulkSaveDirectory(files.get(0).getParentFile());
		if (!directory.exists() || !directory.canWrite())
			return;

		for (File file : files) {
			try {
				Flag flag = FileHandler.loadFlagFromFile(file);
				FileHandler.exportFlagToPng(flag, dimensions, new File(String.format("%s%s%s.png", directory,
						FileHandler.getPathSeparator(), StringExtensions.getFilenameWithoutExtension(file.getName()))));
			} catch (Exception ex) {
				error = true;
			}
		}

		exportFinished(error);
	}

	@FXML
	private void menuExportBulkSvgClick() {
		boolean error = false;
		List<File> files = getFlagFiles();
		if (files == null || files.isEmpty())
			return;

		File directory = getBulkSaveDirectory(files.get(0).getParentFile());
		if (!directory.exists() || !directory.canWrite())
			return;

		for (File file : files) {
			try {
				Flag flag = FileHandler.loadFlagFromFile(file);
				FileHandler.exportFlagToSvg(flag, new File(String.format("%s%s%s.svg", directory,
						FileHandler.getPathSeparator(), StringExtensions.getFilenameWithoutExtension(file.getName()))));
			} catch (Exception ex) {
				error = true;
			}
		}

		exportFinished(error);
	}

	private List<File> getFlagFiles() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(LocalizationHandler.get("SelectFiles"));
		fileChooser.getExtensionFilters().add(new ExtensionFilter(LocalizationHandler.get("FlagFileFilter"), "*.flag"));
		return fileChooser.showOpenMultipleDialog(stage);
	}

	private File getBulkSaveDirectory(File defaultDirectory) {
		DirectoryChooser dc = new DirectoryChooser();
		dc.setInitialDirectory(defaultDirectory);
		dc.setTitle(LocalizationHandler.get("SelectDirectory"));
		return dc.showDialog(stage);
	}

	private void exportFinished(boolean errorOccurred) {
		if (errorOccurred) {
			new Alert(AlertType.ERROR, LocalizationHandler.get("ExportBulkError"), ButtonType.OK).showAndWait();
		} else {
			new Alert(AlertType.INFORMATION, LocalizationHandler.get("ExportBulkSuccess"), ButtonType.OK).showAndWait();
		}
	}

	// Load / save
	@FXML
	private void newFile() {
		if (checkUnsaved()) {
			return;
		}
		plainPreset(2, 2);
		setDefaultColors();
		lstOverlays.getChildren().clear();
		setRatio(3, 2);
		ratioTextboxChanged();
		txtName.setText(LocalizationHandler.get("Untitled"));
		fileName = "";
		isUnsaved = false;
		setTitle();
	}

	@FXML
	private void save() {
		if (StringExtensions.isNullOrWhitespace(fileName)) {
			saveAs();
		} else {
			saveFlag();
		}
	}

	private void saveFlag() {
		try {
			FileHandler.saveFlagToFile(flag(), fileName);
		} catch (IOException ex) {
			new Alert(AlertType.ERROR, String.format(LocalizationHandler.get("CouldNotSaveError"), ex.getMessage()),
					ButtonType.OK).showAndWait();
		}

		isUnsaved = false;
		setTitle();
		loadFilePresets();
	}

	@FXML
	private void saveAs() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save");
		fileChooser.getExtensionFilters().add(new ExtensionFilter(LocalizationHandler.get("FlagFileFilter"), "*.flag"));
		File file = fileChooser.showSaveDialog(stage);

		if (file != null) {
			fileName = file.getPath();
			saveFlag();
		}
	}

	@FXML
	private void open() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(LocalizationHandler.get("Open"));
		fileChooser.getExtensionFilters().add(new ExtensionFilter(LocalizationHandler.get("FlagFileFilter"), "*.flag"));
		File file = fileChooser.showOpenDialog(stage);
		if (file != null) {
			loadFlagFromFile(file);
		}
	}

	private boolean checkUnsaved() {
		if (!isUnsaved)
			return false;

		Alert alert = new Alert(AlertType.CONFIRMATION);
		ButtonType buttonYes = new ButtonType(LocalizationHandler.get("Yes"));
		ButtonType buttonNo = new ButtonType(LocalizationHandler.get("No"));
		ButtonType buttonCancel = new ButtonType(LocalizationHandler.get("Cancel"), ButtonData.CANCEL_CLOSE);
		alert.setTitle(CommonExtensions.titleAndVersionString(getClass()));
		alert.setHeaderText(LocalizationHandler.get("NotSaved"));
		alert.setContentText(String.format(LocalizationHandler.get("SaveChangesPrompt"), txtName.getText()));
		alert.getButtonTypes().setAll(buttonYes, buttonNo, buttonCancel);

		Optional<ButtonType> result = alert.showAndWait();

		ButtonType b = result.get();
		if (b == buttonYes) {
			save();
		}

		return b == buttonCancel;
	}

	private void loadFlagFromFile(File filename) {
		try {
			loadFlag(FileHandler.loadFlagFromFile(filename));
			fileName = filename.getPath();
		} catch (Exception e) {
			new Alert(AlertType.ERROR, String.format(LocalizationHandler.get("CouldNotOpenError"), e.getMessage()),
					ButtonType.OK).showAndWait();
		}
	}

	private void loadFlag(Flag flag) {
		isLoading = true;
		ratio = flag.ratio;
		txtRatioHeight.setText(Integer.toString(flag.ratio.height));
		txtRatioWidth.setText(Integer.toString(flag.ratio.width));
		fillGridCombobox();

		for (int i = 0; i < cmbRatio.getItems().size(); i++) {
			if (new Ratio(cmbRatio.getItems().get(i)).width == flag.gridSize.width) {
				cmbRatio.getSelectionModel().select(i);
				break;
			}
		}

		division = flag.division;
		setDivisionVisibility();

		lstOverlays.getChildren().clear();
		for (Overlay overlay : flag.overlays) {
			overlayAdd(lstOverlays.getChildren().size(), overlay, true);
		}

		txtName.setText(flag.name);
		isUnsaved = false;
		isLoading = false;

		draw();

		for (Node n : lstOverlays.getChildren()) {
			((OverlayControl) n).isLoading = false;
		}
	}

	// Presets
	private void presetBlank() {
		plainPreset(1, 1);
	}

	private void presetHorizontal() {
		plainPreset(1, 2);
	}

	private void presetVertical() {
		plainPreset(2, 1);
	}

	private void presetQuad() {
		plainPreset(2, 2);
	}

	private void presetStripes() {
		for (int i = 0; i < cmbRatio.getItems().size(); i++) {
			Object item = cmbRatio.getItems().get(i);
			Ratio stripeRatio = new Ratio((String) item);
			if (stripeRatio.width >= 7) {
				cmbRatio.getSelectionModel().select(i);
				break;
			}
		}
		plainPreset(1, 7);
	}

	private void plainPreset(int slider1, int slider2) {
		divisionGridClick();
		divisionSlider1.setValue(slider1);
		divisionSlider2.setValue(slider2);
		divisionSlider3.setValue(1);
		cmbPresets.getSelectionModel().clearSelection();
	}

	private void loadBasicPresets() {
		cmbPresets.getItems().clear();
		cmbPresets.getItems().add(LocalizationHandler.get("DivisionBlank"));
		cmbPresets.getItems().add(LocalizationHandler.get("DivisionHorizontalHalves"));
		cmbPresets.getItems().add(LocalizationHandler.get("DivisionVerticalHalves"));
		cmbPresets.getItems().add(LocalizationHandler.get("DivisionQuartered"));
		cmbPresets.getItems().add(LocalizationHandler.get("DivisionStripes"));

		cmbPresets.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue == null || (oldValue != null && oldValue.equals(newValue)) || isNullOrWhitespace(newValue)) {
			} else if (newValue.equals(LocalizationHandler.get("DivisionBlank")))
				presetBlank();
			else if (newValue.equals(LocalizationHandler.get("DivisionHorizontalHalves")))
				presetHorizontal();
			else if (newValue.equals(LocalizationHandler.get("DivisionVerticalHalves")))
				presetVertical();
			else if (newValue.equals(LocalizationHandler.get("DivisionQuartered")))
				presetQuad();
			else if (newValue.equals(LocalizationHandler.get("DivisionStripes")))
				presetStripes();
		});
	}

	private void loadFilePresets() {
		mnuPresets.getItems().clear();

		try {
			File directory = new File(String.format("%s%sPresets",
					new File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath())
							.getParent(),
					FileHandler.getPathSeparator()));
			File[] files = directory.listFiles();
			if (files == null)
				return;

			Map<String, File> presets = new HashMap<>();

			for (File fileEntry : files) {
				if (fileEntry.getName().endsWith(".flag")) {
					presets.put(getPresetFlagName(fileEntry), fileEntry);
				}
			}

			SortedSet<String> names = new TreeSet<>(presets.keySet());

			for (String name : names) {
				File fileEntry = presets.get(name);
				if (!StringExtensions.isNullOrWhitespace(name)) {
					MenuItem item = new MenuItem(name);
					item.addEventHandler(EventType.ROOT, (Event event) -> loadPreset(fileEntry));
					mnuPresets.getItems().add(item);
				}
			}
		} catch (URISyntaxException ex) {
		}
	}

	private void loadPreset(File file) {
		if (checkUnsaved()) {
			return;
		}
		loadFlagFromFile(file);
		setTitle();
	}

	private String getPresetFlagName(File file) {
		try (FileReader fr = new FileReader(file); BufferedReader sr = new BufferedReader(fr)) {
			String line;
			while ((line = sr.readLine()) != null) {
				if (line.startsWith("name=")) {
					return line.split("=")[1];
				}
			}
		} catch (Exception e) {
			return "";
		}

		return "";
	}

	@FXML
	private void generateRandomFlag() {
		if (checkUnsaved()) {
			return;
		}
		Flag f = new RandomFlagFactory().generateFlag();
		loadFlag(f);
		fileName = "";
		setTitle();
	}

	private void onClosing(WindowEvent event) {
		if (checkUnsaved()) {
			event.consume();
		}
	}

	public Flag flag() {
		String name = txtName.getText();
		if (isNullOrWhitespace(name)) {
			name = isNullOrWhitespace(fileName) ? "" : Paths.get(fileName).getFileName().toString();
		}
		return new Flag(name, ratio, selectedGridSize(), division, getOverlays());
	}

	private boolean canParseInt(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private Overlay[] getOverlays() {
		ArrayList<Overlay> list = new ArrayList<>();
		for (Node control : lstOverlays.getChildren()) {
			OverlayControl oc = (OverlayControl) control;
			Overlay o = oc.getOverlay();
			list.add(o);
		}

		Overlay[] returnValue = new Overlay[] {};
		return list.toArray(returnValue);
	}

	// About
	@FXML
	private void menuAbout() {
		Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(stage);
		dialog.setResizable(false);
		AboutController control = new AboutController(dialog);
		Scene dialogScene = new Scene(control, 400, 300);
		dialogScene.getStylesheets().add(UI.class.getResource("Style.css").toExternalForm());
		dialog.setScene(dialogScene);
		dialog.showAndWait();
	}
}
