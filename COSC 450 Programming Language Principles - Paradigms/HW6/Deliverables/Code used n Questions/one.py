i = 0
k = (j + 13) / 27
while (k < 10):
   k = k + 1
   i = 3 * k - 1


if k == 1:
    j = 2 * k - 1
elif k == 2:
    j = 2 * k - 1
elif k == 3:
	j = 3 * k + 1
elif k == 4:
    j = 4 * k - 1
elif k == 5:
	j = 3 * k + 1
elif k == 6:
    j = k - 2
elif k == 7:
	j = k - 2
elif k == 8:
	j = k - 2
else:
    #default


 

    /*
	if ((k == 1) || (k == 2)) j = 2 * k - 1
	if ((k == 3) || (k == 5)) j = 3 * k + 1
	if (k == 4) j = 4 * k - 1
	if ((k == 6) || (k == 7) || (k == 8)) j = k - 2

	*/

