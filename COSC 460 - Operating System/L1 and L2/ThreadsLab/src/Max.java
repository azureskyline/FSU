//Catherine Austria
//Takes the max number out of a list
public class Max implements Runnable{
	private int numArr[];
	public int maxNum;
	public Max(int arr[]){
		this.numArr=arr;
	}
	@Override
	public void run() {
		maxNum=numArr[0];
		for (int i=0; i<numArr.length;i++){
			if (numArr[i]>=maxNum) maxNum=numArr[i];
		}
	}
}
