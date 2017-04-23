//Catherine Austria
//This takes the minimum value out of an array
public class Min implements Runnable{
	private int numArr[];
	public int minNum;
	public Min(int arr[]){
		this.numArr=arr;
	}
	@Override
	public void run() {
		minNum=numArr[0];
		for (int i=0; i<numArr.length;i++){
			if (numArr[i]<=minNum) minNum=numArr[i];
		}
		
	}
}
