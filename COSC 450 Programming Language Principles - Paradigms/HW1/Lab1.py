import random

#Program one generates a random 12 element array
#and performs quicksort on the said array.
def program1():
    print("Program 1:\n")
    ranArr = random.sample(range(0,100),12)#generate random size 12 array
    #print(ranArr)
    quicksort(ranArr, 0, 11)
    print("End of Program 1")

#This method begins the quicksorting method. Algorithm was taken from Wikipedia  
def quicksort(arr, low, high):
    #termination condition
    print (arr)
    if low<high:
        pivot = partition(arr, low, high)
        quicksort(arr, low, pivot-1)
        quicksort(arr, pivot+1, high)
        
#This method determines partitions to determine the
#pivot the algorithm has to make and to sort elements
def partition(arr, lo, hi):
    pivot = lo + random.randrange( hi - lo + 1 )
    arr[pivot],arr[hi]=arr[hi],arr[pivot]
    for i in range( lo, hi ):
        if arr[i] <= arr[hi]:
            arr[i],arr[lo]=arr[lo],arr[i]
            lo += 1
    arr[lo],arr[hi]=arr[hi],arr[lo]
    return lo

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
    print("End of Program 2")

program1()    
program2()
