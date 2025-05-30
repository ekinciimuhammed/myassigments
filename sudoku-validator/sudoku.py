"""
Sudoku is a popular number puzzle game that involves filling a 9x9 grid with digits so that each column, each row, and each of the nine 3x3 subgrids that compose the grid (also called "boxes," "blocks," or "regions") contain all of the digits from 1 to 9. The puzzle starts with some cells already filled with numbers, and the goal is to fill in the remaining cells.
Here are the basic rules of Sudoku:
Grid: The standard Sudoku grid is a 9x9 grid, divided into nine 3x3 subgrids.
Numbers: Use the numbers 1 through 9. Each row, column, and 3x3 subgrid must contain all of the digits from 1 to 9.
Initial Setup: Some numbers are already provided in the puzzle as clues. These are the numbers you start with.
No Repetition: In each row, column, and 3x3 subgrid, a number can only appear once.
Logic and Deduction: The game is solved through logic and deduction. You need to use the given numbers to figure out the placement of the remaining numbers.
Uniqueness: There should be only one solution to the puzzle. A proper Sudoku puzzle should not have multiple solutions.
To play Sudoku, you can start by looking for numbers that are already placed and figuring out where other numbers can go based on the rules of the game. As you fill in more numbers, the puzzle becomes progressively easier to solve. Sudoku is a game that requires both logical reasoning and patience. It has become a popular pastime and is often found in newspapers, puzzle books, and online puzzle platforms.
"""
import sys #We can use the sys module to get input from the user.
def column_maker(table):#we need a column maker to make a list of columns
        columns=[table[i::9] for i in range(9)]#it is a for loop that gıves me list of columns
        return columns # ıf you want to use list of columns you have to return ıt
def square_maker(table):#we need a square(ı saıd square normally ıts name subgrıd) maker to make a list of squares
    squares = [table[i+3*j:i+3*j+3]+table[i+3*j+9:i+3*j+12]+table[i+3*j+18:i+3*j+21] for i in range(0,81,27) for j in range(0,3)]#it is a for loop that gıves me subgrids(ı call ıt squares)
    return squares# ıf you want to use  list of squares, you have to return ıt
def row_maker(table):#we need a row maker to make a list of rows
        rows = [table[i:i + 9] for i in range(0, 81, 9)]
        return rows 
def allposibilities(table,rows,columns,squares):#we need a allposıbılıtıes functıon to calculate posıbılıtıes, ıf he calculate posıbılıtıes we can solve thıs problem 
    li = [1, 2, 3, 4, 5, 6, 7, 8, 9]#it is a list that ı wıll use ıt to calculate allposıbılıtes of one cell 
    cellposibilities = []#it is a empyt list 
    for i in range(0, 81): #ıt ıs a loop
        whıchrow = i // 9#you can calculate row number of any ındexes
        whıchcolumn = i % 9#you can calculate column number of any ındexes
        boxnumber = int((whıchcolumn//3)) + ((whıchrow // 3) * 3)#you can calculate square number of any ındexes
        ola = rows[whıchrow] + columns[whıchcolumn] + squares[boxnumber]#we wıll use ıt to calculate all posıbılıtıes
        if table[i] == 0:#when you use functıon arguments
            cellposibilities.append(list(set(li) - set(ola)))# ı am calculatıng all posıbılıtıes of 0 cells
        else:# ıf there ıs a number , ı add empty lıst 
            cellposibilities.append([])# ı added a empty lıst
    return cellposibilities #ıf you want to use  cellposibilities, you have to return ıt
def solver(table):# ı have a solver functıon to solve a problem
    rows = row_maker(table)#ı call row_maker
    columns = column_maker(table)#ı call column_maker
    squares = square_maker(table)#ı call square_maker
    steps = []#it is a steps list and ı will use it to print my steps
    indexstepsnumber = []#it is a index list and ı will use it to calculate @R{}C{}
    olası_degerler = allposibilities(table,rows,columns,squares)#ı call allposibilities
    for i in range(81):# it is a loop for solving sudoku puzzle        
        if table[i] == 0 and len(olası_degerler[i]) == 1:# it is a consıtıon for solvıng problem             
            table[i] = olası_degerler[i][0]#solvıng steps
            indexstepsnumber.append(i)#ı added a ındex on index list
            steps.append(table.copy())# ı added a table's copy to steps list
            break# ı ı fınıshed loops
    return steps, indexstepsnumber # ıf you want to use (steps and ındex numbers) list , you must return them

def main():#ıt ıs a maın functıon. ı did it because teacher wanted from me:)
    f1=open(sys.argv[2],"a")#ı can wrıte output thanks to "a" and open functıon
    table = sys.argv[1]# ı can get a sudoku table from user
    with open(table, 'r') as file:# ı used my table fıle thanks to open functıon
        table = file.read()#ı read table fıle
        table = table.split()# ı removed spaces and ı make a list every strings(actually all number ıs a strıngs)
    for i in range(len(table)):# ı converted every strings to integer thanks to my loop
        table[i] = int(table[i])  # convertıng steps
    realsteps = []# ı wıll use ıt to wrıte my functıon
    index_steps_numbers = []#ı wıll use ıt to wrıte my functıon
    while 0 in table: # ıt ıs a while loop for calling and calculatıng new thıngs     
        current_steps, current_indexstepsnumber = solver(table)#ıcalled ıt 
        realsteps.extend(current_steps)#ı extendend my lıst
        index_steps_numbers.extend(current_indexstepsnumber)# ı extended my list
    for murat in range(len(realsteps)):# it is a loop for writing my lists. murat is my bestt friends and ı use him name.
        for abide in range(len(table)):# abide is murats girlfriend 
            if abide == 0:#ıt ıs a condutıon for wrıtıng list
                f1.write(18 * "-")# ı wıll wrıte 18 times -
                f1.write("\n")# ı have a newline
                f1.write(f"Step {murat+1} - {table[index_steps_numbers[murat]]} @ R{(index_steps_numbers[murat]//9)+1}C{(index_steps_numbers[murat]%9)+1}")#I wanted to write a text on the table indicating the step number and the location of the cell.
                f1.write("\n")# ı have a newline
                f1.write(18 * "-")# ı wıll wrıte 18 times -
                f1.write("\n")# ı have a newline
            if abide % 9 == 0 and abide != 0:#it is a condutıon for writing files
                f1.write("\n")# ı have a newline
            f1.write(f"{realsteps[murat][abide]} ")#ı am writing my table in steps
            if abide % 80 == 0 and abide != 0:# it is a condıtıon for havıng a newline
                f1.write("\n")# ı have a newline
    f1.write(18 * "-")# ı wıll wrıte 18 times -
if __name__ == '__main__':#ı did it because my teacher want ıt from me
    main()# ı called main functıon
    
