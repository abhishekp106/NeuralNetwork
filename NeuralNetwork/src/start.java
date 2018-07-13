import java.util.Scanner;

public class start 
{
	public void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("What is TP?");
		int tp = sc.nextInt();
		System.out.print("What is FP?");
		int fp = sc.nextInt();
		System.out.print("What is FN?");
		int fn = sc.nextInt();
		System.out.print("What is TN?");
		int tn = sc.nextInt();
		
		System.out.print(tp + " " + fp);
		System.out.print(fn + " " + tn);
		
	}
}
