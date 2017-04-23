# The main method is the driver of the operation.
# We take the input file from the user : COSC450_P2_Data.txt
# We poputale our matrices
# We solve for the product of the two matrices
# We print the results to an output file
def main():
    #______________________Scan Input Data___________________________
    inList = input("Type the filename: ") #enter file name of the input text file
    infile = open(inList, "r")  #open file
    nums = list(infile) # list of lines from the input text
    splitLine = []  #initiate list
    allSize = 0
    for x in nums:  #for every line in the file
        for y in x.split(): #split the line into ints
            splitLine.append(y) #add to initiated list splitLine for processing
            allSize+=1
    #print ("The size of the data is: "+str(allSize)+" numbers.")
    matrixSize = allSize/5
    #print("The matrix size needed is: "+str(matrixSize))
    #___________________________Populate_____________________________
    X = populate(splitLine, 5, matrixSize) # 5 x matrixSize matrix
    Y = populate(splitLine, matrixSize, 5) # matrixSize x 5 matrix
    result = [[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]] # result is 5 x 5
    p = open("COSC450_P2_Output.txt", 'w')
    #print the two matrices
    printMatrix(X, "X", p)
    printMatrix(Y, "Y", p)

    #______________________Get Product_______________________________
    #Take product of two matrices
    for i in range(len(X)): # iterate through rows of X
       for j in range(len(Y[0])): # iterate through columns of Y
           for k in range(len(Y)): # iterate through rows of Y
               result[i][j] += int(X[i][k]) * int(Y[k][j])
    #print result list
    printMatrix(result, "the Result", p)
    infile.close()  #close the file
    
#This method simply prints the matrix passed to it. Prints to COSC450_P2_Output.txt
def printMatrix(numList, nameOfList, outFile):
    print ("\nThis is "+str(nameOfList)+" :\n")
    outFile.write("\n\nThis is "+str(nameOfList)+" :\n\n")
    for num in numList:
        print (num)
        outFile.write(str(num))

#This method aims to populate the matrix it is assigned to.
#Returns a matrix filled with values based on the list passed to it.
def populate(numList, row, col):
    Mrow = []
    Mlist = []
    numCount = 0
    #print ("This is the col: "+str(col)+" This is the row: "+str(row)) #test    
    for i in range(0, len(numList)): #go through all elements of the numList
        #for j in range(0, col):
        Mrow.append(numList[numCount]) # append number from numList to row element
        numCount+=1
        if numCount%col == 0: #if the whole row reaches col # of elements
            Mlist.append(Mrow) #append the row to the matrix
            Mrow = [] #reinitialize the row to zero elements for the next row of the matrix
    return Mlist   # return the matrix

main()
