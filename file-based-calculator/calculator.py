import sys

dosya = sys.argv[1]
f1 = open(sys.argv[2], "a")

while True:
    if len(sys.argv) != 3:
        f1.write("ERROR: This program needs two command line arguments to run, where the first one is the input file and the second one is the output file!")
        f1.write("\n")
        sys.exit()

    if len(sys.argv) == 3:
        try:
            with open(dosya, 'r') as file:
                mylines = []
                for line in file:
                    a = line.strip("\n").strip()
                    mylines.append(a)
        except FileNotFoundError:
            f1.write(f"ERROR: There is either no such file namely {dosya} or this program does not have permission to read it!")
            f1.write("\n")
        while "" in mylines:
            mylines.remove("")

        islemlistesi = []
        for i in range(len(mylines)):
            islemlistesi.append(mylines[i].split())

        for k in range(len(islemlistesi)):
            if len(islemlistesi[k]) != 3:
                f1.write(mylines[k])
                f1.write("\n")
                f1.write("ERROR: Line format is erroneous!")
                f1.write("\n")
            if len(islemlistesi[k]) == 3:
                try:
                    number1 = float(islemlistesi[k][0])
                    operand = islemlistesi[k][1]
                    number2 = float(islemlistesi[k][2])

                    if operand == "+" and k!=len(islemlistesi)-1:
                        f1.write(mylines[k])
                        f1.write("\n")
                        f1.write("={:.2f}".format(number1 + number2))
                        f1.write("\n")
                    elif operand == "+" and k==len(islemlistesi)-1:
                        f1.write(mylines[k])
                        f1.write("\n")
                        f1.write("={:.2f}".format(number1 + number2))
                        

                    elif operand == "-"and k!=len(islemlistesi)-1:
                        f1.write(mylines[k])
                        f1.write("\n")
                        f1.write("={:.2f}".format(number1 - number2))
                        f1.write("\n")
                    elif operand == "-"and k==len(islemlistesi)-1:
                        f1.write(mylines[k])
                        f1.write("\n")
                        f1.write("={:.2f}".format(number1 - number2))
                    

                    elif operand == "/"and k!=len(islemlistesi)-1:
                        f1.write(mylines[k])
                        f1.write("\n")
                        f1.write("={:.2f}".format(number1 / number2))
                        f1.write("\n")
                    elif operand == "/" and k==len(islemlistesi)-1:
                        f1.write(mylines[k])
                        f1.write("\n")
                        f1.write("={:.2f}".format(number1 / number2))


                    elif operand == "*" and k!=len(islemlistesi)-1:
                        f1.write(mylines[k])
                        f1.write("\n")
                        f1.write("={:.2f}".format(number1 * number2))
                        f1.write("\n")
                    elif operand == "*" and k==len(islemlistesi)-1:
                        f1.write(mylines[k])
                        f1.write("\n")

                    else:
                        f1.write(mylines[k])
                        f1.write("\n")
                        f1.write("ERROR: There is no such operator!")
                        f1.write("\n")

                except ValueError:
                    if not islemlistesi[k][0].replace('.', '').isdigit():
                        f1.write(mylines[k])
                        f1.write("\n")
                        f1.write("ERROR: First operand is not a number!")
                        f1.write("\n")
                    elif not islemlistesi[k][2].replace('.', '').isdigit():
                        f1.write(mylines[k])
                        f1.write("\n")
                        f1.write("ERROR: Second operand is not a number!")
                        f1.write("\n")
                    else:
                        f1.write(mylines[k])
                        f1.write("\n")
                        f1.write("SANA SÖZ YİNE BAHARLAR GELECEKKKK SANA SÖZ UMUT DİNMEYECEEEEEKK!!!")
                        f1.write("\n")

                         
    break
