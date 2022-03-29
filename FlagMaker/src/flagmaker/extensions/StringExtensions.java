package flagmaker.extensions;

public class StringExtensions
{
	public static String getFilenameWithoutExtension(String fname)
	{
		int period = fname.lastIndexOf(".");
		int slash = Math.max(fname.lastIndexOf("/"), fname.lastIndexOf("\\"));
		if (period > 0)
		{
			fname = fname.substring(0, period);
		}
		if (slash > 0)
		{
			fname = fname.substring(slash + 1, fname.length());
		}
		return fname;
	}
	
	public static String getFilenameExtension(String fname)
	{
		int period = fname.lastIndexOf(".");
		if (period > 0)
		{
			return fname.substring(period + 1, fname.length());
		}
		return "";
	}

	public static boolean isNullOrWhitespace(String s)
	{
		return s == null || isWhitespace(s);
	}

	private static boolean isWhitespace(String s)
	{
		int length = s.length();
		if (length > 0)
		{
			for (int i = 0; i < length; i++)
			{
				if (!Character.isWhitespace(s.charAt(i)))
				{
					return false;
				}
			}
		}
		return true;
	}
}
