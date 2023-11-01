# encryption using a linear feedback shift register
import bindec

#Constant variables, the difference in ascii decimals and base64 decimals for uppercase,
#lowercase, numerical, "+" and "/" characters respectively
DIFF1 = 65 
DIFF2 = 71
DIFF3 = 4
DIFF4 = 19
DIFF5 = 16

#Constant variables, the base64 decimals for the start of each set of characters,
#uppercase, lowercase, numerical, "+" and "/" characters respectively
STARTUPPER = 0
STARTLOWER = 26
STARTNUM = 52
PLUSSIGN = 62
DIVSIGN = 63

#Constant variables, true and false, 1 and 0
TRUEVAL = 0
FALSEVAL = 1

# converts a character c into a list of six 1's and 0's using Base64 encoding
##@param c, a string character from the list of characters in Base64 encoding
##@return, binary, a list of six 1's and 0's 
def charToBin(c):
    # Implement me
    asciiVal = ord(str(c))
    base64Val = _convertTo64(asciiVal)
    binary = bindec.decToBin(base64Val)
    return binary

# converts a list of six 1's and 0's into a character using Base64 encoding
##@param,b, a list of six 1's and 0's
##@return, chr(asciiVal), a string, the character represented by the
#passed binary list
def binToChar(b):
    # Implement me
    decimal = bindec.binToDec(b)
    asciiVal = _convertToAscii(decimal)
    return chr(asciiVal)

# convert a string of characters into a list of 1's and 0's using Base64 encoding
##@param s, a string
##@return binaryList, a list, the encoded characters
def strToBin(s):
    # Implement me
    binaryList = charToBin(s[0])
    for char in range(0,len(s)-1):
        binaryList += charToBin(s[char+1])
    return binaryList

# convert a list of 1's and 0's into a string of characters using Base64 encoding
##@param b_list, a list of 1's and 0's
##@return words, a string, the decoded characters
def binToStr(b_list):
    # Implement me
    words = ""
    length = len(b_list)
    base64BinLength = 6
    while length != 0:
        tempList = []
        count = 0
        while count < base64BinLength:
            tempList.append(b_list[0])
            b_list.pop(0)
            count += 1
        words += binToChar(tempList)
        length -= base64BinLength
    return words

# generates a sequence of pseudo-random numbers
##@param seed,a list, random 1's and 0's which will be used to generate a pad
##@param k,an integer, the tap on the N-bit register, denoted by [N,k] where N is the
##length of the seed
##@param length, an integer, the desired length of the one-time pad
##@return lst, a list, pseudo- random numbers which make-up the one-time pad
def generatePad(seed, k, length):
    # Implement me
    count = 1
    lst = []
    while count <= length:
        if seed[-k] == seed[0]:
            seed.append(TRUEVAL)
            lst.append(TRUEVAL)
        else:
            seed.append(FALSEVAL)
            lst.append(FALSEVAL)
        seed.pop(0)
        count += 1
    return lst

# takes a message and returns it as an encrypted string using an [N, k] LFSR
##@param message, a string, the message to be encrypted
##@param seed, a list, the list of random numbers used to generate the pad
##@param k, an integer,  the tap on the N-bit register, [N,k]
##@return cipher, a string, the encrypted message
def encrypt(message, seed, k):
    # Implement me
    binaryMessage = strToBin(message)
    pad = generatePad(seed,k,len(binaryMessage))
    tempList = []
    for i in range(len(pad)):
        if pad[i] == binaryMessage[i]:
            tempList.append(TRUEVAL)
        else:
            tempList.append(FALSEVAL)
    cipher = binToStr(tempList)
    return cipher

#helper function converts ASCII decimal to base64 decimal
#@param asciiDec, int, the ascii decimal to be converted
#@return val, int, the base64 decimal 
def _convertTo64(asciiDec):
    if asciiDec in range(ord("A"),ord("Z") + 1):val = asciiDec - DIFF1
    elif asciiDec in range(ord("a"),ord("z") + 1):val = asciiDec - DIFF2
    elif asciiDec in range(ord("0"),ord("9") + 1):val = asciiDec + DIFF3
    elif asciiDec == ord("+"):val = asciiDec + DIFF4
    elif asciiDec == ord("/"):val = asciiDec + DIFF5
    return val

#helper function converts Base64 decimal to ASCII decimal
#@param base64Dec, int, the base64 decimal to be converted
#@return val,int, the ASCII decimal
def _convertToAscii(base64Dec):
    if base64Dec in range(STARTUPPER,STARTLOWER): val = base64Dec + DIFF1
    elif base64Dec in range(STARTLOWER,STARTNUM): val = base64Dec + DIFF2
    elif base64Dec in range(STARTNUM,PLUSSIGN): val = base64Dec - DIFF3
    elif base64Dec == PLUSSIGN: val = base64Dec - DIFF4
    elif base64Dec == DIVSIGN: val = base64Dec - DIFF5
    return val
