import random
#Created by Catherine Austria 9/9/2016
#Program generates a random 12 element array
#and performs quicksort on the said array.
def program1():
    print("The original array: ")
    ranArr = random.sample(range(0,100),12)#generate random size 12 array
    print(ranArr)
    print("\nStarting Quickort:")
    quicksort(ranArr, 0, 11)
    print("End of Program.")

#This method begins the quicksorting method. Algorithm was taken from Wikipedia  
def quicksort(arr, low, high):
    print (arr)
    #termination condition
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
program1() 
