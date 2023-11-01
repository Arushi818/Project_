import configparser
import os 
import bcrypt

b = 0
def createmaster():
    # User sets the master password
    master_password = input("Enter your master password: ")
    hashed_master = bcrypt.hashpw(master_password.encode('utf-8'), bcrypt.gensalt())


    # Create a configuration object
    config = configparser.ConfigParser()

    # Set the master password in the configuration file
    config['Security'] = {
        'master_password': hashed_master
    }

    # Write the configuration data to the config.ini file
    with open('config.ini', 'w') as configfile:
         config.write(configfile)

    print("Master password set and stored securely.")

 
def checkmaster():    
    # Prompt user for master password
    entered_password = input("Enter your master password to access passwords: ")
    hash_entered = hash(entered_password)
    print(hash_entered)

    # Read master password from the configuration file
    config = configparser.ConfigParser()
    config.read('config.ini')

    stored_master_password = config['Security']['master_password']


    # Compare entered password with stored password
    if bcrypt.checkpw(entered_password.encode('utf-8'), stored_master_password):
        #print("Access granted. Here are your decrypted passwords:")
        # Implement code to decrypt and display passwords from the user passwords file
        b = 1
    else:
        b = 0
        #print("Incorrect master password. Access denied.")
    return b


def ismasterfileEmpty():
    config_file_path = 'config.ini'
    # Get the size of the config file
    file_size = os.path.getsize(config_file_path)
    if file_size == 0:
        return True
    else:
        return False 
 
