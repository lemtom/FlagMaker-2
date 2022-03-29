package flagmaker.Extensions;

import javafx.scene.Node;
import javafx.scene.control.Control;

public class ControlExtensions
{
	public static void hideControl(Control object)
	{
		object.visibleProperty().set(false);
		object.managedProperty().set(false);
	}
	
	public static void showControl(Control object)
	{
		object.visibleProperty().set(true);
		object.managedProperty().set(true);
	}
	
	public static void hideControl(Node object)
	{
		object.visibleProperty().set(false);
		object.managedProperty().set(false);
	}
	
	public static void showControl(Node object)
	{
		object.visibleProperty().set(true);
		object.managedProperty().set(true);
	}
}
