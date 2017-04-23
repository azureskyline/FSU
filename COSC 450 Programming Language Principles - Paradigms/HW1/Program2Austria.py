import random
#Created by Catherine Austria 9/9/2016
#Program generates two random element generated 2D arrays/matrices and
#performs the matrix multiplication of both arrays.
def program2():
    #These first two lines generates an x (4x3) and  y(3x4) matrix,
    #each filled with random-generated elements ranging from 0 to 100.
    x = [[random.randint(0,100) for e in range(3)] for e in range(4)]
    y = [[random.randint(0,100) for e in range(4)] for e in range(3)]
    result = [[0 for i in range(3)] for j in range(4)] #initializes an empty 3x4 array full of 0's.
    print ("\nProgram 2:\nMatrix X:")
    print (x) 
    print ("\nMatrix Y:")
    print (y) 
    print ("\nMatrix X*Y:")
    #start multiplication using the zip() to pick out the i-tuples and multiply them using * operator
    #zip is an inbuilt python function. This definition is taken from python documentation:
    #zip (*iterables) Returns an iterator of tuples, where the i-th tuple contains the i-th 
    #element from each of the argument sequences or iterables. The iterator stops when the 
    #shortest input iterable is exhausted. With a single iterable argument, it returns an
    #iterator of 1-tuples. In otherwords, they act like nested for-loops.
    result = [[sum(a*b for a,b in zip(xrow,ycol)) for ycol in zip(*y)] for xrow in x]
    #prints the x*y matrix result
    for r in result:
        print(r)
    print("End of Program.")
program2()
