//Catherine Austria
//This takes the average of an array
public class Ave implements Runnable{
	private int numArr[];
	public int ave;
	public Ave(int arr[]){
		this.numArr=arr;
	}
	@Override
	public void run() {
		ave=0;
		for (int i=0; i<numArr.length;i++){
			ave+=numArr[i];
		}
		ave=ave/numArr.length;
	}
}
