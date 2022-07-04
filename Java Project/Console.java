
public class Console{
	/**Custom class that handles our methods acting on the console.*/
	private static final String PROJECT_TITLE = "Group 5 Voting System - ";//Group title template string
	

	public static void clear(){
		/**Method for clearing the console*/
		IO.sys("cls"); //cls is windows specific command for clearing the console
	}
	public static void setTitle(String title){
		/**Method for giving the console a title*/
		IO.sys("title " + PROJECT_TITLE + title);//title is windows specific command for changing the title
	}

	//below the two overloaded methods
	//for changing console color
	/*This list represent the color codes
		    0 = Black       8 = Gray
		    1 = Blue        9 = Light Blue
		    2 = Green       A = Light Green
		    3 = Aqua        B = Light Aqua
		    4 = Red         C = Light Red
		    5 = Purple      D = Light Purple
		    6 = Yellow      E = Light Yellow
		    7 = White       F = Bright White
    */
	public static void setColor(String colHex){
		/**this method works in case the color hex is given by one string*/
		IO.sys("color " + colHex);//color is windows specific command for changing color
	}
	public static void setColor(char backGnd,char forGnd){
		/**this method works in case the color hex is given by two chars
			for each (background and foreground)*/
		IO.sys("color " + Character.toString(backGnd) + Character.toString(forGnd));
	}
}