package flagmaker.color;

import flagmaker.MainWindowController;
import flagmaker.UI;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ColorButton extends VBox
{
	@FXML private Rectangle rect;
	private Color value;
	private MainWindowController mainWindowController;
	private Stage stage;
	private ColorButtonListener listener;
	
	public ColorButton()
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ColorButton.fxml"));
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
	
	public void setListener(MainWindowController mainWindowController, Stage stage, ColorButtonListener listener)
	{
		this.mainWindowController = mainWindowController;
		this.stage = stage;
		this.listener = listener;
	}
	
	public Color getValue()
	{
		return value;
	}
	
	public void setValue(Color value)
	{
		this.value = value;
		rect.setFill(value);
	}
	
	@FXML private void onClicked()
	{
		Color oldval = value;
		
		Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(stage);
		ColorSelector control = new ColorSelector(dialog, value, new ArrayList<>(Arrays.asList(mainWindowController.flag().colorsUsed())), mainWindowController.recentColors);
		Scene dialogScene = new Scene(control);
		dialogScene.getStylesheets().add(UI.class.getResource("Style.css").toExternalForm());
		dialog.setScene(dialogScene);
		dialog.setResizable(false);
		dialog.showAndWait();
		
		Color c = control.getSelectedColor();
		if (c != null)
		{
			setValue(c);
			
			if (mainWindowController.recentColors.contains(c))
			{
				mainWindowController.recentColors.removeIf(r -> r.equals(c));
			}
			
			mainWindowController.recentColors.add(0, c);
			
			listener.colorChanged(oldval, value);
		}
	}
}
