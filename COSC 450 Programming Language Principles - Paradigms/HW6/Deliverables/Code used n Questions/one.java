
public static void main(String[] args){


	int i = 0, j = 0, k = (j+13)/27;
	while (k < 10){
		k = k + 1;
		i = 3 * k - 1;
	}
	System.out.println("k is"+ k);

	switch(k){
		case 1:
		case 2:
			j = 2 * k - 1;
			break;
		case 3:
		case 5:
			j = 3 * k + 1;
			break;
		case 4:
			j = 4 * k - 1;
			break;
		case 6:
		case 7:
		case 8:
			j = k - 2;
			break;
		default:
			/**/
	}

	boolean isAllRowZero = false;
	for (int i = 1; i <= n ; i++){
		int zeroCounter = 0;
		for (int j = 1; j <= n ; j++) {
			if (x[i][j] == 0) zeroCounter++;
			//if zeroCounter reaches row size, all zero row found
			if (zeroCounter == x[i].length) isAllRowZero = true; 
		}
		if (zeroCounter == n && isAllRowZero == true) { 
			System.out.println("First all­zero row is: "+i);
			isAllRowZero = false;
		}
	}

	/*
	boolean found = false;
	for (i = 1, i <= n; i++) {
		int counter = 0;
		for (j = 1; j <= n; j++) {
			if (x[i][j] == 0)
				counter++;
		}
		if (counter == n && found == false) {
			printf(“First all­zero row is: %d”, i);
			found = true;
		}
	}

	*/

}


