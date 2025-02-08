
l="."
m="@"
x=str(input("please enter your string"))
def email_checker(x):
    while True:
        x=str(input("please enter your string ?"))
        if l and m in x:
                print("your e mail is right.Thank you foryour rıght")
                break
        else:
            print("you didnt enter valid mail.please try again")
            continue

email_checker(x)


 