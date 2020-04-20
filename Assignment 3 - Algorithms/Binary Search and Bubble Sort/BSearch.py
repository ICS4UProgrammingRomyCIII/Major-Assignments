#! /usr/bin/env python3

import sys, random     # All of the imported modules

commands = [       # This is a list of the available commands.
    'help  ------ >  shows this help dialog.',
    'open  ------ >  opens the test case file in notepad.',
    'generate  -- >  generates a set of unsorted integers.',
    'sort  ------ >  sorts the list of integers using Insertion Sort.',
    'sort bubble  >  sorts the list of integers using Bubble Sort',
    'search  ---- >  searches for a specified number in the list.',
    'exit  ------ >  exits the program.'
]

def Invalid(code, *acc): 
    if code == 'syntax' :                                                               # If there is a syntax error...
        print('Usage: python BinarySearch.py [command] [args]') # Generic syntax error message
    elif code == 'input' :                                                              # If there is an input error...
        print('Invalid Input')                                                          # Generic input error message
    sys.exit()                                                                          # Exits the program.

def CommandOutput() :  # ... gets the user inputted command(s).
    while  True:
        commandArg = input('Please input a command (enter help for a list of commands):  ')     # Tell the user to input a command.
        if commandArg == 'help':                                                                # If the command was help... 
            Help()                                                                                  # Call the help function.
        elif commandArg == 'open':                                                              # If the command was open...
            OpenTestCaseFile()                                                                      # Call the open file function.
        elif commandArg == 'generate':                                                          # If the command was generate...
            try:                                                                                    # Try ...
                amount = int(input('How many numbers do you want to generate?..... '))                  # Ask the user to input the amount of numbers they want to generate.
                limit = int(input('What is the largest number you want to generate?..... '))            # Ask the user to input the max number they want to generate.
                if amount > limit:                                                                      # If the amount of numbers is greater than the limit...        
                    print('The number of generated numbers cannot be greater than the limit!!' + '\n')             # Tell the user they cannot do that. This is to prevent multiples of the same number in the list.
                else :                                                                                  # Otherwise...
                    GenerateNumbers(amount, limit)                                                          # Call the generate function passing the user inputs as arguments.
            except ValueError:                                                                      #If there is a Value Exception...
                print('Please ONLY enter INTEGER numbers!!')                                            # Tell the user to only input integers.
        elif commandArg == 'sort':                                                              # If the command was sort...
            SortFile()                                                                              # Call the Insertion sort function.
        elif commandArg == 'sort bubble':                                                       # If the command was bubble sort...
            BubbleSortFile()                                                                        # Call the Bubble Sort function.
        elif commandArg == 'search':                                                            # If the command was search...
            try :                                                                                   # Try ...
                val = int(input('What value do you want to search for?..... '))                         # Ask the user for the value that they are searching for.
                BinarySearch(val)                                                                       # Call the search function passing the input as an argument.
            except ValueError :                                                                     # If there is a Value Exeception...
                print('Please ONLY enter INTEGER numbers!!')                                            # Tell the user to only input integers.
        elif commandArg == 'exit':                                                              # If the command was exit...
            sys.exit(0)                                                                             # Exit the program.

def Help():
    for command in commands:    # For every command in commands...
        print(command)          # Print the command.
    print(' ')                  # print and extra space.

def OpenTestCaseFile():
    import os as opnf
    import subprocess as sb
    if sys.platform == 'win32':         # If you're using Windows.
        opnf.startfile('testCase.txt')      # Start the file in the default program.
    elif sys.platform == 'darwin':      # If you're on MacOS.
        sb.call(['open','testCase.txt']) # This is the only solution I found. I don't know if it works, cause I don't have MacOS.

def ReadTextFile():
    """ This section of the sort is to get the contents of the text file """
    f = open('testCase.txt', 'r')       # Open the text file.
    array = []                          # Create an array.
    content = f.readlines()             # Read the contents of the text file.
    for lines in content:               # For the lines the content of the text file...     
        for nums in lines.split():      # For every number in the line...
            array.append(int(nums))     # Append the number to the array.
    f.close()
    return array                        # Returns the array.

def GenerateNumbers(amount, limit):
    f = open('testCase.txt', 'r+')   # Read and write to the test case file.
    f.truncate(0)                    # Removes all of the text from the text file.
    randomList = []                         # Create a list (To store the random numbers).
    for i in range(0, amount):              # Standard for loop...
        ranInt = random.randint(1, limit)   # Generate a random int within the limit.
        while ranInt in randomList:
            ranInt = random.randint(1, limit)
        randomList.append(ranInt)           # Add the random int to the list.
        f.write(str(ranInt) + " ")          # Write the random int into the text file.
    print(randomList)                       # Print the list to the user.
    f.close()

def SortFile():
    f = open('testCase.txt', 'r+')   # Read and write to the test case file.
    """ This section is where the sorting algorithm begins, This algorithm is called Insertion sort """
    """Imagine sorting an unordered deck of cards. You go through each
        card one by one and when you see a card of a lesser value than the 
        previous, you swap them. It is similar to bubble sort, but faster.
    """
    arr = ReadTextFile()                # Reads text file into an array.
    f.truncate(0)                # Removes all of the text from the text file.
    for i in range(len(arr)):           # Standard for loop
        currentNum = arr[i]             # Get the current number.
        position = i                    # Get the current position
        while (position > 0) and (arr[position - 1] > currentNum):  # While the position is greater than 0 and the previous number is greater than the current number...
            arr[position] = arr[position - 1]                       # Set the current number to the previous number.
            position = position -1                                  # Set the current position to the previous position.
        arr[position] = currentNum                                  # Change the current number.
    for num in arr:                                                 # For every number in the sorted array...
        f.write(str(num) + " ")                              # Write the number to the text file.
    f.close()

def BubbleSortFile():
    f = open('testCase.txt', 'r+')   # Read and write to the test case file.
    """ This section is where the sorting algorithm begins, This algorithm is called Bubble sort """
    arr = ReadTextFile()                # Reads text file into an array.
    f.truncate(0)                # Removes all of the text from the text file.

    for i in range(0, len(arr)):
        for j in range(0, len(arr) - 1 - i):
            if arr[j] > arr[j+1] :
                arr[j], arr[j+1] = arr[j+1], arr[j]
    for num in arr:                                                 # For every number in the sorted array...
        f.write(str(num) + " ")                              # Write the number to the text file.
    f.close()

def BinarySearch(x):
    arr = ReadTextFile()    # Reads the text file into an array.

    """ This section is where the search algorithm begins"""
    leftPos = 0             # Gets the left most position in the array.
    rightPos = len(arr) - 1 # Gets the right most position in the array.
    index = -1              # Defines the index as -1.
    while (leftPos <= rightPos) and (index == -1):          # While the left and right position are correct(left is to the left of / is less than the right) and the index wasn't found...
        midPoint = leftPos + ((rightPos - leftPos) // 2)    # Get the mid point of the two points (Floor divide to get an integer).
        if arr[midPoint] == x:          # If the midpoint is the intended value...
            index = midPoint            # Set the index to the midpoint
        elif x < arr[midPoint]:         # If the intended value is less than the mid point...
            rightPos = midPoint - 1     # Change the right most position to the midpoint - 1.
        else:                           # Otherwise(if the intended value is greater than the mid point)...
            leftPos = midPoint + 1      # Change the left most positiopn to the midpoint + 1.
    if index == -1:
        print("The number you were searching for was not in the list!")                 # Tells the user that the number couldn't be found
    else : 
        print("The number you were searching for can be found at index :" + str(index)) # Tells the user where the number could be found

CommandOutput() # Starts the program.