import random
number=random.randint(0,100)
guess=int(input("please predict a number"))
while True:
   guess=int(input("please predict a number because your last number is wrong"))
   if guess< number :
       print("please increase your guess,you couldnt guees.") 
       continue
   elif number<guess:
       print("please decrase your guess,you couldnt guees.")
       continue
   else:
       print("congrulationss!!. your guess is right")
       break