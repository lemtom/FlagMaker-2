package flagmaker.Files;

import flagmaker.Extensions.ColorExtensions;
import flagmaker.Divisions.Division;
import flagmaker.Divisions.DivisionBendsBackward;
import flagmaker.Divisions.DivisionBendsForward;
import flagmaker.Divisions.DivisionFesses;
import flagmaker.Divisions.DivisionGrid;
import flagmaker.Divisions.DivisionPales;
import flagmaker.Divisions.DivisionX;
import flagmaker.Data.Flag;
import flagmaker.Overlays.Overlay;
import flagmaker.Overlays.OverlayTypes.PathTypes.OverlayPath;
import flagmaker.Data.Ratio;
import flagmaker.Data.Size;
import flagmaker.Extensions.StringExtensions;
import flagmaker.Data.Vector;
import flagmaker.Extensions.CommonExtensions;
import flagmaker.Overlays.OverlayTypes.RepeaterTypes.OverlayRepeater;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javax.imageio.ImageIO;

public class FileHandler
{
	public static void saveFlagToFile(Flag flag, String path) throws IOException
	{
		try (FileWriter writer = new FileWriter(path, false); PrintWriter printLine = new PrintWriter(writer))
		{
			printLine.printf(flag.exportToString());
		}
	}
	
	public static void exportFlagToSvg(Flag flag, File file) throws IOException
	{
		final int width = 600;
		int height = (int)(((double)flag.ratio.height / flag.ratio.width) * width);
		
		try (FileWriter writer = new FileWriter(file, false); PrintWriter printLine = new PrintWriter(writer))
		{
			printLine.printf("<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?>\n");
			printLine.printf("<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n");
			printLine.printf("<svg xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" version=\"1.1\" width=\"%s\" height=\"%s\">\n", width, height);
			
			printLine.printf("%s\n", flag.division.exportSvg(width, height));
			
			flag.setRepeaterOverlays();
			
			for (int i = 0; i < flag.overlays.length; i++)
			{
				if (i > 0 && flag.overlays[i - 1] instanceof OverlayRepeater) continue;
				
				Overlay overlay = flag.overlays[i];
				if (!overlay.isEnabled) continue;
				printLine.printf(overlay.exportSvg(width, height));
			}
			
			printLine.printf("</svg>\n");
		}
	}
	
	public static void exportFlagToPng(Flag flag, Size size, File path) throws IOException
	{
		AnchorPane a = new AnchorPane();
		Scene s = new Scene(a, size.x, size.y);
		Rectangle clip = new Rectangle(size.x, size.y);
		Pane p = new Pane();
		p.setClip(clip);
		s.setRoot(p);
		
		flag.draw(p);
		
		WritableImage snapshot = p.snapshot(new SnapshotParameters(), null);
		ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", path);
	}
	
	public static Flag loadFlagFromFile(File file) throws Exception
	{
		ArrayList<String> lines = readAllLines(file);
		ArrayList<ArrayList<String>> groups = splitLines(lines);
		
		String name = getValue(groups.get(0), "name", "name= ");
		Ratio ratio = new Ratio(getValue(groups.get(0), "ratio", "ratio=2:3"));
		Ratio gridRatio = new Ratio(getValue(groups.get(0), "gridSize", "ratio=2:3"));
		
		Division division = readDivision(groups.get(1));
		
		ArrayList<Overlay> overlays = new ArrayList<>();
		for (int i = 2; i < groups.size(); i++)
		{
			overlays.add(readOverlay(groups.get(i), gridRatio.width, gridRatio.height, file.getParent()));
		}

		Overlay[] finalOverlays = new Overlay[]{};
		return new Flag(name, ratio, gridRatio, division, overlays.toArray(finalOverlays));
	}
	
	public static OverlayPath loadOverlayFromFile(File file) throws Exception
	{
		ArrayList<String> lines = readAllLines(file);
		String name = getValue(lines, "name", "name= ");
		int width = Integer.parseInt(getValue(lines, "width", "width=1"));
		int height = Integer.parseInt(getValue(lines, "height", "height=1"));
		String path = getValue(lines, "path", "path= ");
		
		return new OverlayPath(name, path, new Vector(width, height));
	}
	
	public static File getFilePossiblyRelative(File file, String directory)
	{
		if (file.exists()) return file;
		
		File absolute = new File(String.format("%s%s%s", directory, getPathSeparator(), file.getPath()));
		if (absolute.exists()) return absolute;
		
		return null;
	}
	
	public static String getPathSeparator()
	{
		return CommonExtensions.isWindows()
			? "\\"
			: "/";
	}
	
	private static ArrayList<String> readAllLines(File file) throws Exception
	{
		ArrayList<String> lines = new ArrayList<>();
		String line = "";
		try (FileReader fr = new FileReader(file); BufferedReader sr = new BufferedReader(fr))
		{
			while ((line = sr.readLine()) != null)
			{
				lines.add(line);
			}
		}
		catch (Exception ex)
		{
			throw new Exception(line, ex);
		}
		
		return lines;
	}
	
	private static ArrayList<ArrayList<String>> splitLines(ArrayList<String> lines)
	{
		ArrayList<ArrayList<String>> returnValue = new ArrayList<>();
		
		ArrayList<String> currentSection = new ArrayList<>();
		for (String line : lines)
		{
			if (StringExtensions.isNullOrWhitespace(line) && !currentSection.isEmpty())
			{
				returnValue.add((ArrayList<String>)currentSection.clone());
				currentSection.clear();
			}
			else
			{
				currentSection.add(line);
			}
		}
		
		returnValue.add((ArrayList<String>)currentSection.clone());
		return returnValue;
	}
	
	private static Division readDivision(ArrayList<String> lines)
	{
		String type = getValue(lines, "type", "type=grid");
		
		Color color1 = ColorExtensions.parseColor(getValue(lines, "color1", "color1=ffffff"));
		Color color2 = ColorExtensions.parseColor(getValue(lines, "color2", "color2=ffffff"));
		Color color3 = ColorExtensions.parseColor(getValue(lines, "color3", "color3=ffffff"));
		
		int divisionVal1 = Integer.parseInt(getValue(lines, "size1", "size1=1"));
		int divisionVal2 = Integer.parseInt(getValue(lines, "size2", "size2=1"));
		int divisionVal3 = Integer.parseInt(getValue(lines, "size3", "size3=1"));
		
		switch (type)
		{
			case "fesses":
				return new DivisionFesses(color1, color2, color3, divisionVal1, divisionVal2, divisionVal3);
			case "pales":
				return new DivisionPales(color1, color2, color3, divisionVal1, divisionVal2, divisionVal3);
			case "bends forward":
				return new DivisionBendsForward(color1, color2);
			case "bends backward":
				return new DivisionBendsBackward(color1, color2);
			case "bends both":
				return new DivisionX(color1, color2);
			default:
				return new DivisionGrid(color1, color2, divisionVal1, divisionVal2);
		}
	}
	
	private static Overlay readOverlay(ArrayList<String> lines, int maximumX, int maximumY, String directory) throws Exception
	{
		TempOverlay t = new TempOverlay();
		t.type = getValue(lines, "type", "type=grid");
		
		for (String line : lines)
		{
			String[] data = line.split("=");
			if (data[0].equals("overlay") || data[0].equals("type")) continue;
			t.values.put(data[0], data[1]);
		}
		
		return t.toOverlay(maximumX, maximumY, directory);
	}
		
	private static String getValue(ArrayList<String> data, String fieldName, String defaultValue)
	{
		return data.stream().filter(s -> s.toLowerCase().replace("\uFEFF", "").startsWith(fieldName.toLowerCase())).findFirst().orElse(defaultValue).split("=")[1];
	}
}
