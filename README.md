# Viren's Maze solution

## Introduction
For this problems, I have used Eclipse to create and run the source code found within this folder. 
The program was written in Java and was able to read and solve all input files provided, as well as an extra titled "small_wrap_input2.txt".
This program used the Trémaux's algorithm which uses a series of marking, allowing it to find its way from start to end. 

## Method
1. To run this program, you must first open the program through an IDE, such as "Eclipse" or "NetBeans".
2. Run the solution sources code. This file will contain the main methods to run the software within.
3. When running the code, you should see a message say "Hello and welcome to maze solver. Please insert a file:". To insert the maze you would like to solve, you could:
	a. Drag the input file in to the "MazeSolution" folder, rerun the source code again, then type in the name of the file e.g. "small_input.txt". (Recommended)
	b. Or type in the path of the file e.g. "C:\Users\virom\Downloads\Gentrack Maze Technical Test V5\Gentrack Maze Technical Test".(Will also work 100%)
4. Once you have inserted the name of the input file, press enter and you should get something like this:

a. If solvable, you get:

``` 
Solved Maze
# X # # # # # # # # 
# S #               
#   #   # # # # # # 
# # #             # 
#   # #   #   # # # 
#   #     #   #   # 
#   #             # 
#   # # #   # # # # 
# X X X X X X X E # 
# X # # # # # # # # 
```

b. If not solvable, you get:

```
I am unable to solve the maze
```
