package vue;

import java.awt.Font;

public class Police {
	
	private static Font fontTitle = new Font("Century Gothic",Font.PLAIN,25);
	private static Font fontSubtitle = new Font("Century Gothic",Font.PLAIN,17);
	private static Font fontDefault = new Font("Century Gothic",Font.PLAIN,14);
	
	
	public static Font getFontTitle(){
		return fontTitle;
	}

	public static Font getFontDefault() {
		return fontDefault;
	}

	public static Font getFontSubtitle() {
		return fontSubtitle;
	}

	//fontTitle = new font(" TimesRoman ",Font.BOLD,30);

	
}
