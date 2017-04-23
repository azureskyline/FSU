import java.util.Random;
//This program's point is to run different thread operations on the same numbers
//Catherine Austria
public class Driver {
	public static void main (String[] args){
		int num[] = new int[5];
		Random rand = new Random();
		for(int i=0;i<num.length;i++) {
			int n = rand.nextInt(20);
			num[i]=n; //fill array with random numbers
			System.out.print(n+" ");
		}
		Min xr=new Min(num);
		Max yr=new Max(num);
		Ave zr=new Ave(num);
		Thread x = new Thread(xr);
		Thread y = new Thread(yr);
		Thread z = new Thread(zr);
		x.start();
		y.start();
		z.start();
		try{
			x.join();
			y.join();
			z.join();
			System.out.println("The Min, Max and Ave respectively is: "+xr.minNum+" "+yr.maxNum+" "+zr.ave);
		}catch(InterruptedException e){}
	}
}
