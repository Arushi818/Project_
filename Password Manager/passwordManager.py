#use encryption.py, bindec.py to implement encryption of passwords
import encryption 
import masterPw



def add_pw():
    username = input('Enter your username: ')
    password = input('Enter your password: ')
    with open('passwords.txt', 'a') as f:
        f.write(username + "|" + encryption.encrypt(password,[1,0,1,0,0,1,0,0,1,0], 8) + "\n")
    print("Your password has been added! \n \n")
    return 

def view_pw():
    access = masterPw.checkmaster()
    with open('passwords.txt', 'r') as f:
        for line in f.readlines():
            info = line.strip().split("|")
            #print(info)
            if len(info) > 1:
                username = info[0]
                enc_password = info[1]
    
                if access == 1: 
                    decrypted_pw =  encryption.encrypt(enc_password, [1,0,1,0,0,1,0,0,1,0],8)
                else:
                    decrypted_pw =  encryption.encrypt(enc_password, [1,0,1,0,0,1,0,0,1,0,1,1],10)
    
                print(username  + ": " + decrypted_pw + "\n")

    return 

def menu():
    print("Welcome to your Password Manager \n")
    masterPw_empty = masterPw.ismasterfileEmpty()
    if masterPw_empty:
        masterPw.createmaster()
    mode = input\
        ("Select a Mode. \n Enter 'view' to view your passwords or 'add' to add a new password. \n Select 'q' to quit. \n")
    while mode != 'q':
        if mode == 'view':
            view_pw()
        elif mode == 'add':
            add_pw()
        else:
            print("Invalid input. Please enter a valid keyword or enter 'q' to quit.")
        mode = input\
        ("Select a Mode. \n Enter 'view' to view your passwords or 'add' to add a new password. \n Select 'q' to quit. \n")




def main():
    menu()

    

main()




