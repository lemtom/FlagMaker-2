package flagmaker.Color;

import flagmaker.Files.LocalizationHandler;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ColorSelector extends VBox
{
	private Stage stage;

	@FXML private TabPane tabs;
	@FXML private Tab tabStandard;
	
	@FXML private Label labelFotw;
	@FXML private FlowPane paneFotw;
	@FXML private Label labelFoan;
	@FXML private FlowPane paneFoan;
	@FXML private Label labelRecent;
	@FXML private FlowPane paneRecent;
	@FXML private Label labelUsed;
	@FXML private FlowPane paneUsed;
	
	@FXML private Tab tabAdvanced;
	private boolean shouldTrigger;
	@FXML private Slider sldR;
	@FXML private Label lblR;
	@FXML private Slider sldG;
	@FXML private Label lblG;
	@FXML private Slider sldB;
	@FXML private Label lblB;
	@FXML private Slider sldA;
	@FXML private Label lblA;
	@FXML private Button btnSaveAdvanced;
	@FXML private Canvas cnvSatLight;
	@FXML private Canvas cnvHue;
	@FXML private Rectangle rctPreview;
	
	@FXML private Button btnCancelStandard;
	@FXML private Button btnCancelAdvanced;
	
	private Image colorCircle;
	private Image colorTriangles;
	private Color color;
	private Color tempColor;
	
	public ColorSelector(Stage stage, Color currentColor, List<Color> usedColors, List<Color> recentColors)
	{
		load(stage);
		setWindowStrings();
		fillNamedColorList(paneFotw, ColorList.flagsOfTheWorld());
		fillNamedColorList(paneFoan, ColorList.flagsOfAllNations());
		fillColorList(paneUsed, usedColors);
		fillColorList(paneRecent, new ArrayList<Color>(new ArrayList<Color>(recentColors).subList(0, Math.min(recentColors.size(), 10))));
		
		setAdvanced(currentColor);
		
		stage.titleProperty().set(LocalizationHandler.get("Color"));
		stage.getIcons().add(new Image("flagmaker/Images/icon.png"));
	}

	private void setWindowStrings()
	{
		btnCancelStandard.setText(LocalizationHandler.get("Cancel"));
		btnCancelAdvanced.setText(LocalizationHandler.get("Cancel"));
		tabStandard.setText(LocalizationHandler.get("Standard"));
		tabAdvanced.setText(LocalizationHandler.get("Advanced"));
		labelFotw.setText(LocalizationHandler.get("LargePaletteName"));
		labelFoan.setText(LocalizationHandler.get("SmallPaletteName"));
		labelRecent.setText(LocalizationHandler.get("RecentPaletteName"));
		labelUsed.setText(LocalizationHandler.get("UsedPaletteName"));
		btnSaveAdvanced.setText(LocalizationHandler.get("Save"));
	}
	
	private void load(Stage stage)
	{
		this.stage = stage;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ColorSelector.fxml"));
		loader.setRoot(this);
		loader.setController(this);

		try
		{
			loader.load();
		}
		catch (Exception ex)
		{
			String s = ex.getMessage();
		}
	}

	private void fillNamedColorList(FlowPane pane, ArrayList<NamedColor> colors)
	{
		for (NamedColor c : colors)
		{
			Button b = new Button();
			pane.getChildren().add(b);
			Rectangle r = new Rectangle(20, 20);
			r.setFill(c.color);
			r.setStroke(Color.SILVER);
			Pane p = new Pane();
			p.getChildren().add(r);
			b.setGraphic(p);
			b.setTooltip(new Tooltip(c.name));
			b.setOnAction(o -> { color = c.color; stage.close(); });
		}
	}
	
	private void fillColorList(FlowPane pane, List<Color> colors)
	{
		for (Color c : colors)
		{
			Button b = new Button();
			pane.getChildren().add(b);
			Rectangle r = new Rectangle(20, 20);
			r.setFill(c);
			r.setStroke(Color.SILVER);
			Pane p = new Pane();
			p.getChildren().add(r);
			b.setGraphic(p);
			b.setOnAction(o -> { color = c; stage.close(); });
		}
	}

	private void setAdvanced(Color currentColor)
	{
		tempColor = currentColor;
		shouldTrigger = true;
		colorCircle = new Image("flagmaker/Images/color-circle.png");
		colorTriangles = new Image("flagmaker/Images/color-triangles.png");
		fillSatLightCanvas(tempColor);
		setColorCircle(tempColor);
		setColorTriangles(tempColor);
		
		lblR.setText(Integer.toString((int)(tempColor.getRed() * 255)));
		lblG.setText(Integer.toString((int)(tempColor.getGreen() * 255)));
		lblB.setText(Integer.toString((int)(tempColor.getBlue() * 255)));
		lblA.setText(Integer.toString((int)(tempColor.getOpacity() * 255)));
		
		sldR.setValue(tempColor.getRed() * 255);
		sldG.setValue(tempColor.getGreen() * 255);
		sldB.setValue(tempColor.getBlue() * 255);
		sldA.setValue(tempColor.getOpacity() * 255);
		
		sldR.valueProperty().addListener((ObservableValue<? extends Number> ov, Number oldval, Number newval) ->
		{
			if (shouldTrigger && !oldval.equals(newval)) sliderChanged(sldR, lblR);
		});
		sldG.valueProperty().addListener((ObservableValue<? extends Number> ov, Number oldval, Number newval) ->
		{
			if (shouldTrigger && !oldval.equals(newval)) sliderChanged(sldG, lblG);
		});
		sldB.valueProperty().addListener((ObservableValue<? extends Number> ov, Number oldval, Number newval) ->
		{
			if (shouldTrigger && !oldval.equals(newval)) sliderChanged(sldB, lblB);
		});
		sldA.valueProperty().addListener((ObservableValue<? extends Number> ov, Number oldval, Number newval) ->
		{
			if (shouldTrigger && !oldval.equals(newval)) setNumberLabel(sldA, lblA);
		});
	}
	
	private void sliderChanged(Slider slider, Label label)
	{
		setNumberLabel(slider, label);
		setColorFromSliders();
		setColorTriangles(tempColor);
		fillSatLightCanvas(tempColor);
		setColorCircle(tempColor);
	}
	
	private void setColorCircle(Color color)
	{
		double brightness = color.getBrightness();
		double y = (1 - brightness) * cnvSatLight.getHeight();
		
		double saturation = color.getSaturation();
		double x = saturation * cnvSatLight.getWidth();
		
		GraphicsContext gc = cnvSatLight.getGraphicsContext2D();
		gc.drawImage(colorCircle, x - 5, y - 5);
		
		rctPreview.setFill(color);
	}
	
	private void setColorTriangles(Color color)
	{
		fillHueCanvas();
		double hue = color.getHue();
		double y = hue / 360 * cnvHue.getHeight();
		
		GraphicsContext gc = cnvHue.getGraphicsContext2D();
		gc.drawImage(colorTriangles, 0, y - 4);
	}
	
	@FXML private void cnvSatLightDrag(MouseEvent e)
	{
		shouldTrigger = false;
		double x = e.getX() / cnvSatLight.getWidth();
		double y = 1 - (e.getY() / cnvSatLight.getHeight());
		tempColor = Color.hsb(tempColor.getHue(), clamp(x), clamp(y));
		fillSatLightCanvas(tempColor);
		setColorCircle(tempColor);
		setSlidersFromColor();
		shouldTrigger = true;
	}
	
	@FXML private void cnvHueDrag(MouseEvent e)
	{
		shouldTrigger = false;
		double y = 360 * (e.getY() / cnvSatLight.getHeight());
		tempColor = Color.hsb(y, tempColor.getSaturation(), tempColor.getBrightness());
		fillSatLightCanvas(tempColor);
		setColorTriangles(tempColor);
		setColorCircle(tempColor);
		setSlidersFromColor();
		shouldTrigger = true;
	}

	private void fillHueCanvas()
	{
		int width = (int)cnvHue.getWidth();
		int height = (int)cnvHue.getHeight();
		WritableImage img = new WritableImage(width, height);
		PixelWriter pw = img.getPixelWriter();
		
		for (int y = 0; y < height; y++)
		{
			Color newColor = Color.hsb(360.0 * y / height, 1, 1);
			for (int x = 0; x < width; x++)
			{
				pw.setColor(x, y, newColor);
			}
		}
		
		GraphicsContext gc = cnvHue.getGraphicsContext2D();
		gc.drawImage(img, 0, 0);
	}
	
	private void fillSatLightCanvas(Color input)
	{
		int width = (int)cnvSatLight.getWidth();
		int height = (int)cnvSatLight.getHeight();
		WritableImage img = new WritableImage(width, height);
		PixelWriter pw = img.getPixelWriter();
		
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				Color newColor = Color.hsb(input.getHue(), x / (double)width, 1 - y / (double)height);
				pw.setColor(x, y, newColor);
			}
		}
		
		GraphicsContext gc = cnvSatLight.getGraphicsContext2D();
		gc.drawImage(img, 0, 0);
	}
	
	private void setNumberLabel(Slider slider, Label label)
	{
		label.setText(Integer.toString((int)slider.getValue()));
	}
	
	public Color getSelectedColor()
	{
		return color;
	}
	
	@FXML
	private void saveAdvanced()
	{
		setColorFromSliders();
		color = tempColor;
		stage.close();
	}

	private void setColorFromSliders()
	{
		tempColor = new Color(sldR.getValue() / 255, sldG.getValue() / 255, sldB.getValue() / 255, sldA.getValue() / 255);
	}
	
	private void setSlidersFromColor()
	{
		sldR.setValue(tempColor.getRed() * 255);
		sldG.setValue(tempColor.getGreen() * 255);
		sldB.setValue(tempColor.getBlue() * 255);
		sldA.setValue(tempColor.getOpacity() * 255);
		
		setNumberLabel(sldR, lblR);
		setNumberLabel(sldG, lblG);
		setNumberLabel(sldB, lblB);
		setNumberLabel(sldA, lblA);
	}
	
	@FXML
	private void cancel()
	{
		stage.close();
	}
	
	private double clamp(double input)
	{
		return Math.max(0, Math.min(1, input));
	}
}
