import pymongo 

exists = False

def createCollection(data = []):
   client = pymongo.MongoClient("mongodb://localhost:27017/")
   db = client["passwords"]
   mycol = db["Regularpasswords"]
   if len(data) > 0:
       addRecord(mycol,data)
   return mycol

def addRecord(collection, data):
   collection.insert_one({"website": data[0], "username": data[1], "password": data[2]})

def checkUsernameAndWebsiteMatch(collection,username, website):
    for record in collection.find({},{ "website": 1, "username": 1, "password": 1 }):
      if record["website"] == website and record["username"] == username:
          return True,record
    return False,None

def updatePassword(collection,newPassword,username,website):
   query = { "website": website, "username": username }
   newvalues = { "$set": { "password": newPassword } }

   collection.update_one(query, newvalues)

def retrievePassword(collection,username,website):
   query = { "website": website, "username": username }
   result = collection.find_one(query)
   return result["password"]

def retrieveRecord(collection,username,website):
   query = { "website": website, "username": username }
   result = collection.find_one(query)
   return result

def deleteRecord(collection, username,website):
   password = retrievePassword(collection,username,website)
   query = { "website": website, "username": username, "password": password }

   collection.delete_one(query) 

def findAll(collection):
   return collection.find()


def main():
   collection = createCollection()
   print(checkUsernameAndWebsiteMatch(collection,"Arushi","youtube.com"))
   print(retrievePassword(collection,"Arushi","youtube.com"))
   
if __name__ == '__main__':
   main()


