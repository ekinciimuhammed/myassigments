import sys
def main():
    def maymun():
        liste = []
        liste1=[1,2]
        structure=[]
        filewriter=open(sys.argv[3],"a")
        with open(sys.argv[1], "r") as file:
            content = file.read().strip()
            words = content.split()
            liste.extend(words)
        for i in range(len(liste)):
            liste[i]=int(liste[i])
        liste1=fiboprint(liste[0],liste,liste1,filewriter)
        liste1=sorted(liste1)
        for x in liste1:
            structure.append(fibonacci(x))
        filewriter.write("--------------------------------\n")
        filewriter.write("Structure for the eager solution:\n")
        filewriter.write("[")
        for x in range(len(structure)):
            if x==(len(structure)-1):
                filewriter.write(f"{structure[x]}")
            else:
                filewriter.write(f"{structure[x]}, ")
        filewriter.write("]\n")
        filewriter.write("--------------------------------")
    def fiboprint(num,liste,liste1,filewriter):
        mex=max(liste1)
        filewriter.write('--------------------------------\n')
        filewriter.write(f"Calculating {num}. Fibonacci number:\n")
        if num>0:
            if num in liste1:
                filewriter.write(f"fib({num}) = {fibonacci(num)}\n")
                liste.pop(0)
                printt(num,filewriter)
                if len(liste)>0:
                    fiboprint(liste[0],liste,liste1,filewriter)
            elif num-1 in liste1:
                filewriter.write(f"fib({num}) = fib({num-1}) + fib({num-2})\n")
                filewriter.write(f"fib({num-1}) = {fibonacci(num-1)}\n")
                filewriter.write(f"fib({num-2}) = {fibonacci(num-2)}\n")
                liste1.append(num)
                liste.pop(0)
                printt(num,filewriter)
                if len(liste)>0:
                    fiboprint(liste[0],liste,liste1,filewriter)
            else:
                for x in range(num-mex):
                    filewriter.write(f"fib({num-x}) = fib({num-x-1}) + fib({num-x-2})\n")
                    liste1.append(num-x)
                filewriter.write(f"fib({num-1-x}) = {fibonacci(num-1-x)}\n")
                filewriter.write(f"fib({num-2-x}) = {fibonacci(num-2-x)}\n")
                for x in range(num-mex-1):
                    filewriter.write(f"fib({mex+x}) = {fibonacci(mex+x)}\n")
                liste.pop(0)
                printt(num,filewriter)
                if len(liste)>0:
                    fiboprint(liste[0],liste,liste1,filewriter)
        else:
            filewriter.write("ERROR: Fibonacci cannot be calculated for the non-positive numbers!\n")
            filewriter.write(f"{num}. Fibonacci number is: nan\n")
            liste.pop(0)
            if len(liste)>0:
                fiboprint(liste[0],liste,liste1,filewriter)
        return liste1
    def printt(num,filewriter):
        filewriter.write(f"{num}. Fibonacci number is: {fibonacci(num)}\n")
    def fibonacci(n):
        if n<1:
            return "nan"
        elif n==1 or n==2:
            return 1
        else:
            return fibonacci(n-1)+fibonacci(n-2)   
    def maymun1():
        liste=[]
        filewriter=open(sys.argv[2],'a')
        with open(sys.argv[1], "r") as file:
            content = file.read().strip()
            words = content.split()
            liste.extend(words)
        for i in range(len(liste)):
            liste[i]=int(liste[i])
        for x in range(len(liste)):
            krinter(liste[x],filewriter)
            fibbo(liste[x],filewriter)
            krinter3(liste[x],filewriter)
        krinterend(filewriter)
    def fibbo(num,filewriter):
        if num>0:
            if num==2:
                krinter2(num,filewriter)
            elif num==1:
                krinter2(num,filewriter)
            else:
                num1=num
                for x in range(num-2):
                    krinter1(num,filewriter)
                    num-=1
                krinter2(2,filewriter)
                krinter2(1,filewriter)
                for x in range(num1-3):
                    fibbo(2+x,filewriter)
        else:
            filewriter.write("ERROR: Fibonacci cannot be calculated for the non-positive numbers!\n")
    def krinter(num,filewriter):
        filewriter.write("--------------------------------\n")
        filewriter.write(f"Calculating {num}. Fibonacci number:\n")
    def krinter1(num,filewriter):
        filewriter.write(f"fib({num}) = fib({num-1}) + fib({num-2})\n")
    def krinter2(num,filewriter):
        filewriter.write(f"fib({num}) = 1\n")
    def krinter3(num,filewriter):
        filewriter.write(f"{num}. Fibonacci number is: {fibonacci(num)}\n")
    def krinterend(filewriter):
        filewriter.write("--------------------------------")
    maymun1()
    maymun()       
main()