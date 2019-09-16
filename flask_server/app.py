from flask import Flask
from flask import request
import pymongo as pm

# database connectivity
DB_URL = "mongodb://localhost:27017/"
DB_NAME = "mem_db"
# database tables
USER_TABLE = "user"

#constants
USER_NAME = "user_name"
USER_PWD = "user_pwd"

# return values
USER_LOGIN_SUCCESS = "###USER_LOGIN_SUCCESS###"
USER_LOGIN_FAILED = "###USER_LOGIN_FAILED###"

USER_REG_SUCCESS = "###USER_REG_SUCCESS###"
USER_REG_FAILED = "###USER_REG_FAILED###"

app = Flask(__name__)

# mongo db connectivity
mongo_client = pm.MongoClient(DB_URL)
mongo_db_list = mongo_client.list_database_names()
print(mongo_db_list)
# create or get mongo_db
mongo_db = mongo_client[DB_NAME]
mongo_db_list = mongo_client.list_database_names()
print(mongo_db_list)

def dbInsertUser(db, user_name, user_pwd):
    table = db[USER_TABLE]
    new_entry = {USER_NAME: user_name, USER_PWD: user_pwd}
    print(table.insert_one(new_entry))

def dbCheckUserExistence(db, user_name, user_pwd):
    table = db[USER_TABLE]
    table_entries = list(table.find())
    for entry in table_entries:
        entry_user_name = entry[USER_NAME]
        entry_user_pwd = entry[USER_PWD]
        if entry_user_name == user_name and entry_user_pwd == user_pwd:
            return True
    return False
    # search_res = filter(lambda u_p: u_p[USER_NAME] == user_name and u_p[USER_PWD] == user_pwd, list(table.find()))
    # return (list(search_res) != [])

# API definitions
@app.route('/login', methods=["GET"])
def login():
    user_name = request.args.get(USER_NAME)
    user_pwd = request.args.get(USER_PWD)
    if dbCheckUserExistence(mongo_db, user_name, user_pwd):
        return USER_LOGIN_SUCCESS
    else:
        return USER_LOGIN_FAILED

@app.route('/register', methods=["GET"])
def register():
    user_name = request.args.get(USER_NAME)
    user_pwd = request.args.get(USER_PWD)
    if dbCheckUserExistence(mongo_db, user_name, user_pwd):
        return USER_REG_FAILED
    else:
        dbInsertUser(mongo_db, user_name, user_pwd)
        return USER_REG_SUCCESS

if __name__ == "__main__":
    app.run()
