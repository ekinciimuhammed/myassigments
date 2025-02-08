# This game like candy crush saga

import sys # sys modulethe user gıve me ınput fıle thanks to sys module
    
file=sys.argv[1]

with open(file, "r") as file: # ı open file and do some procces
     a = file.read()
     liste = a.split()
     c = a.split("\n")
     maxrow = len(c) # find row
     maxcolumn = int(len(liste) / maxrow)#find column
     score = 0

     for i in range(len(liste)):
          liste[i] = int(liste[i]) # convert string to integer

     for i in range(len(liste)): #print liste like sudoku table
          if i % maxcolumn == 0:
               print("\n", end="") 
          print(liste[i], end=" ")

     print("\n")
     print(f"Your score is: {score}")
     print("\n")

     while True: # whilw loop ı take some ınputs
          statement = False
          girdi = input("Please enter a row and column number: ")
          
          values = girdi.split()
          row = int(values[0])
          column = int(values[1])

          if 1 <= row <= maxrow and 1 <= column <= maxcolumn:
               break
          else:
               print("Please enter a correct size!")

     indexnumber = (row - 1) * maxcolumn + (column - 1)


def columnup(indexnumber): # it is a functıon that you can fınd a column up
     upcolumn = []

     while indexnumber > -1 and liste[indexnumber] == liste[indexnumber - maxcolumn]:
          upcolumn.append(indexnumber)
          upcolumn.append(indexnumber - maxcolumn)
          indexnumber = indexnumber - maxcolumn

     return sorted(set(upcolumn))


def columndown(indexnumber):  # it is a functıon that you can fınd a column down
     downcolumn = []

     while (
          indexnumber < maxcolumn * (maxrow - 1)
          and liste[indexnumber] == liste[indexnumber + maxcolumn]
     ):
          downcolumn.append(indexnumber)
          downcolumn.append(indexnumber + maxcolumn)
          indexnumber = indexnumber + maxcolumn

     return sorted(set(downcolumn))


def rowright(indexnumber):  # it is a functıon that you can fınd a row right
     rightrows = []

     while (
          indexnumber < (((indexnumber // maxcolumn) + 1) * maxcolumn) - 1
          and liste[indexnumber] == liste[indexnumber + 1]
     ):
          rightrows.append(indexnumber)
          rightrows.append(indexnumber + 1)
          indexnumber = indexnumber + 1

     return sorted(set(rightrows))


def rowleft(indexnumber): # it is a functıon that you can fınd a row left
     leftrow = []

     while (
          indexnumber >= (indexnumber // maxcolumn) * maxcolumn
          and indexnumber > 0
          and liste[indexnumber] == liste[indexnumber - 1]
     ):
          leftrow.append(indexnumber)
          leftrow.append(indexnumber - 1)
          indexnumber = indexnumber - 1

     return sorted(set(leftrow))


def allneighbors(indexnumber): # it is a functıon that you can fınd neighboards
     neighbors = set()
     neighbors.update(columnup(indexnumber))
     neighbors.update(columndown(indexnumber))
     neighbors.update(rowleft(indexnumber))
     neighbors.update(rowright(indexnumber))

     ex = 0
     while len(neighbors) > ex:
          ex = len(neighbors)
          newneighbors = set()
          for gundi in neighbors:
               newneighbors.update(columnup(gundi))
               newneighbors.update(columndown(gundi))
               newneighbors.update(rowleft(gundi))
               newneighbors.update(rowright(gundi))
          neighbors.update(newneighbors)

     return sorted(neighbors)


while len(allneighbors(indexnumber)) >= 0: # is is a while loop you can play thanks to this loop
     neighborsall = allneighbors(indexnumber)
     current = liste[indexnumber]

     if current == " ":
          print("Please enter a row and column number:")
          while True:
               girdi = input("Please enter a row and column number: ")
               values = girdi.split()
               row = int(values[0])
               column = int(values[1])

               if 1 <= row <= maxrow and 1 <= column <= maxcolumn:
                    break
               else:
                    print("Please enter a correct size!")

          indexnumber = (row - 1) * maxcolumn + (column - 1)
          continue

     exliste = liste.copy()
     for i in neighborsall:
          liste[i] = " "

     while True:
          statement = False
          for i in range(len(liste) - maxcolumn):
               if liste[i + maxcolumn] == " " and liste[i] != " ":
                    statement = True
               if statement:
                    liste[i], liste[i + maxcolumn] = liste[i + maxcolumn], liste[i]
               if i == len(liste) - maxcolumn - 1:
                    i = 0
                    continue
               if not statement:
                    continue

          if not statement:
               break

     while True:
          durum = False
          for t in range(((maxrow - 1) * maxcolumn) + 1, len(liste)):
               for k in range(maxrow ):
                    if liste[t - 1] == " " and liste[t] != " ":
                         durum = True
                    if durum:
                         liste[t - k * maxcolumn], liste[t - k * maxcolumn - 1] = (
                         liste[t - k * maxcolumn - 1],
                         liste[t - k * maxcolumn],
                         )
                    if t < maxcolumn:
                         t = len(liste)
                         continue
                    if not durum:
                         continue

          if not durum:
               break

     if exliste.count(" ") == liste.count(" "):
          print("No movement. Please try again.")
          while True:
               girdi = input("Please enter a row and column number: ")
               values = girdi.split()
               row = int(values[0])
               column = int(values[1])

               if 1 <= row <= maxrow and 1 <= column <= maxcolumn:
                    break
               else:
                    print("Please enter a correct size!")

          indexnumber = (row - 1) * maxcolumn + (column - 1)
          continue

     for k in range(maxcolumn):
          col = [liste[i] for i in range(k, len(liste), maxcolumn)]
          if col.count(" ") == maxrow:
               for i in range(k, len(liste), maxcolumn):
                    liste[i] = "y"
               maxcolumn = maxcolumn - 1

     while "y" in liste:
          liste.remove("y")
     if len(liste)==0:
          indexnumber = (row - 1) * maxcolumn + (column - 1)
          score = int(score + (len(neighborsall) * current)/2)
          print()
          print(f"Your score is: {score}")
          print()
          print("Game Over")
          break        


     for r in range(0, len(liste), maxcolumn):
          rowa = liste[r : r + maxcolumn]
          if rowa.count(" ") == maxcolumn:
               for k in range(r, r + maxcolumn):
                    liste[k] = "t"
               maxrow = maxrow - 1

     while "t" in liste:
          liste.remove("t")

     indexnumber = (row - 1) * maxcolumn + (column - 1)
     score = score + (len(neighborsall) * current)

     for i in range(len(liste)):
          if i % maxcolumn == 0:
               print("\n", end="")
          print(liste[i], end=" ")

     print("\n")
     print(f"Your score is: {score}")
     print("\n")

     stopper = []
     for i in range(len(liste)):
          if liste[i] != " ":
               k = allneighbors(i)
               stopper = list(set(stopper))
               stopper.extend(k)

     if len(stopper) == 0:
          indexnumber = (row - 1) * maxcolumn + (column - 1)
          score = score + int((len(neighborsall) * current)/2)
          print()
          print(f"Your score is: {score}")
          print()
          print("Game Over")
          break

     while True:
          girdi = input("Please enter a row and column number: ")
          values = girdi.split()
          row = int(values[0])
          column = int(values[1])

          if 1 <= row <= maxrow and 1 <= column <= maxcolumn:
               break
          else:
               print("Please enter a correct size!")

     indexnumber = (row - 1) * maxcolumn + (column - 1)