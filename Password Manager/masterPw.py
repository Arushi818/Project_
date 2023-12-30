import pymongo

def createCollection(data = ""):
   client = pymongo.MongoClient("localhost", 27017)
   db = client["MasterPassword"]
   mycol = db["MasterPassword"]
   if len(data) > 0:
       addRecord(mycol,data)
   return mycol

def addRecord(collection, data):
    if collection.count_documents({}) == 0:
        collection.insert_one({"id": 1, "masterPassword": data})
    else:
        # Update the existing record
        query = {"id": 1}
        newvalues = {"$set": {"masterPassword": data}}
        collection.update_one(query, newvalues)
    return

def checkIfMPExists(collection):
    if len(list(collection.find())) > 0:
       return True
    else:
       return False

def updateMasterPassword(collection,newPassword):
   query = { "id": 1}
   newvalues = { "$set": { "masterPassword": newPassword } }

   collection.update_one(query, newvalues)

def retrieveMasterPassword(collection):
   result = collection.find_one()
   password = result["masterPassword"]
   return password

def main():
   collection = createCollection()
   addRecord(collection,"Markey2000")
   addRecord(collection,"Markey4")
   print(retrieveMasterPassword(collection))

   
if __name__ == '__main__':
   main()


