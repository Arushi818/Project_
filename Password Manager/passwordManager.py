#use encryption.py, bindec.py to implement encryption of passwords
import encryption 
import database
import getpass
import masterPw
import string
import random


def add_pw(collection):
    website = input('Enter the Website name: ')
    username = input('Enter your Username: ')

    found = database.checkUsernameAndWebsiteMatch(collection,username,website)[0]
    if found: 
        update = input('A record has been found with the same username for the same website entered. Do you want to update or retrieve your password for that website?. Your username will remain unchanged.\
                        \n To update press "u", \n To retrieve press "r", \n To continue press "x" ').lower()
        if update == "u" : 
            updatePassword(collection,website, username)
        elif update == "r":
            retrieveAndDecryptPassword(collection, website, username)
    else:
        addNewRecord(collection, website,username)

    print("Your password has been added! \n \n")
    return 

def addNewRecord(collection, website, username):
    newPassword = getpass.getpass("Please choose a Password: ")
    print(newPassword)
    encryptedPassword = encryption.encrypt(newPassword, [1,0,1,0,0,1,0,0,1,0], 8)
    database.addRecord(collection, [website, username,encryptedPassword])

def updatePassword(collection, website, username):
    newPassword = getpass.getpass("Please choose a Password: ")
    encryptedPassword = encryption.encrypt(newPassword, [1,0,1,0,0,1,0,0,1,0], 8)
    database.updatePassword(collection, encryptedPassword, username, website)

def retrieveAndDecryptPassword(collection, website, username):
    res = database.retrieveRecord(collection,username, website)
    encryptedPassword = res["password"]
    decryptedPassword = encryption.encrypt(encryptedPassword, [1,0,1,0,0,1,0,0,1,0], 8)

    #print("Website: " + res["website"] + " " + "Username: " + res["username"] + " " + "Password: " + decryptedPassword)
    return decryptedPassword

def generate_random_alphanumeric_word(length):
    characters = string.ascii_letters + string.digits
    return ''.join(random.choice(characters) for _ in range(length))

def view_pw(collection, masterpwCollection):
    result = verifyMasterPw(masterpwCollection)

    col = database.findAll(collection)
    print(col)
    for record in col:
        if result: 
            res = retrieveAndDecryptPassword(collection, record["website"],record["username"])
        else:
            length = len(record["password"])
            res = generate_random_alphanumeric_word(length)
        print("Website: " + record["website"] + "| " + "Username: " + record["username"] + "| " + "Password: " + res)
        print()

def del_pw(masterpwCollection,collection):
    website = input('Enter the Website name: ')
    username = input('Enter your Username: ')

    found = database.checkUsernameAndWebsiteMatch(collection,username,website)[0]
    if found: 
       result = verifyMasterPw(masterpwCollection)
       if result:
           database.deleteRecord(collection,username,website)
           print("record actually deleted!")
       else:
           print("record deleted!")
           
    else:
        print("No record matching your inputs were found!")
        return 

    return 

def verifyMasterPw(masterpwCollection):
    input = getpass.getpass('Please enter the masterPassword: ')

    encryptedPw = masterPw.retrieveMasterPassword(masterpwCollection)

    result = input == encryption.encrypt(encryptedPw, [1,0,1,0,0,1,0,0,1,0], 8)
    print(result)
    return result

def change_Master(masterpwCollection):
    result = verifyMasterPw(masterpwCollection)
    newMasterPassword = getpass.getpass("Enter new master password: ")
    if result:
        encryptedMasterPw = encryption.encrypt(newMasterPassword, [1,0,1,0,0,1,0,0,1,0], 8)
        masterPw.updateMasterPassword(masterpwCollection,encryptedMasterPw)
    print('Master Password updated successfully!')

def menu():
    print("Welcome to your Password Manager \n")
    collection = database.createCollection()
    masterpwCollection = masterPw.createCollection()

    check = masterPw.checkIfMPExists(masterpwCollection)

    if not check: 
        masterPass = getpass.getpass("Please enter a MasterPassword: ")

        # Add a record to the master password collection
        encryptedMasterPw = encryption.encrypt(masterPass, [1,0,1,0,0,1,0,0,1,0], 8)
        masterPw.addRecord(masterpwCollection, encryptedMasterPw)

    mode = input\
        ("Select a Mode. \n Enter 'view' to view your passwords, 'del' to delete a password or 'add' to add a new password. \n Select 'change Master' to\
 change master password \n Select 'q' to quit. \n").lower()
    while mode != 'q':
        if mode == 'view':
            view_pw(collection, masterpwCollection)
        elif mode == 'add':
            add_pw(collection)
        elif mode == 'del':
            del_pw(masterpwCollection,collection)
        elif mode == 'change master':
            change_Master(masterpwCollection)
        else:
            print("Invalid input. Please enter a valid keyword or enter 'q' to quit.")
        mode = input\
       ("Select a Mode. \n Enter 'view' to view your passwords, 'del' to delete a password or 'add' to add a new password. \n Select 'change Master' to\
 change master password \n Select 'q' to quit. \n").lower()

def main():
    menu()

if __name__ == '__main__':
    main()






