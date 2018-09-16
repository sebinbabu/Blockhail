import java.util.Scanner;

public class Main
{
	private static Scanner sc = new Scanner(System.in);
	private static String open = "({[<";
	private static String close = ")}]>";
	
	public static boolean checkbrackets(String str , int beg, int end)
	{
		
		if (beg > end) 
			return true;
		if (end == 1 || close.contains(Character.toString(str.charAt(0)))) 
			return false;
		String brac = Character.toString(str.charAt(beg)); 
		
		if (open.contains(brac)) 
		{  
			int check = open.indexOf(brac); 
			String brac_close = Character.toString(close.charAt(check)); 
			if (!str.contains(brac_close)) 
				return false;
		}
		
		if (close.contains(brac)) {
			int t = beg-1;
			int check = close.indexOf(brac);
			String brac_str = Character.toString(str.charAt(t));
			String brac_open = Character.toString(open.charAt(check));
			
			while (!open.contains(brac_str)) 
			{ 
				t--;
				brac_str = Character.toString(str.charAt(t));
			}
			
			if (!brac_str.equals(brac_open)) 
				return false;
			else
				return true;
		} 
		
		return checkbrackets(str,++beg,end);
	}
	public static void main(String[] args) {
		System.out.print("Enter a string : ");
		String s = sc.nextLine();
		System.out.println("Is string is balanced : " + checkbrackets(s,0,s.length()));
	}
	
}