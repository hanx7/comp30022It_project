from flask import Flask
from flask import request
import pymongo as pm
import json

# re module provides support
# for regular expressions
import re

# Make a regular expression
# for validating an Email
regex = '^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$'

# database connectivity
DB_URL = "mongodb://localhost:27017/"
DB_NAME = "mem_db"
# database tables
USER_TABLE = "user"
ITEM_TABLE = "items"

# constants
USER_NAME = "user_name"
USER_PWD = "user_pwd"
USER_FIRST_NAME = "user_first_name"
USER_LAST_NAME = "user_last_name"
USER_EMAIL = "user_email"
USER_DOB = "user_dob"
ITEM_NAME = "item_name"
DESCRIPTION = "description"
IMAGE_STRING = "image_string"

# return values
USER_LOGIN_SUCCESS = "###USER_LOGIN_SUCCESS###"
USER_LOGIN_FAILED = "###USER_LOGIN_FAILED###"

USER_REG_SUCCESS = "###USER_REG_SUCCESS###"
# register failed when user name already in use
USER_REG_FAILED = "###USER_REG_FAILED###"
# register failed when not enough information
USER_REG_FAILED_2 = "###USER_REG_FAILED_2###"
# register failed when email is non-valid
NON_VALID_EMAIL = "###NON_VALID_EMAIL###"

ADD_ITEM_SUCCESS = "###ADD_ITEM_SUCCESS###"
ADD_ITEM_FAILED = "###ADD_ITEM_FAILED###"

# charset encoding
CHARSET_ENCODE = "utf-8"

# splitors
IMAGE_SPLITOR = "%%IMAGE_SPLITOR%%"
INFO_SPLITOR = "%%INFO_SPLITOR%%"


app = Flask(__name__)

# mongo db connectivity
mongo_client = pm.MongoClient(DB_URL)
mongo_db_list = mongo_client.list_database_names()
print(mongo_db_list)
# create or get mongo_db
mongo_db = mongo_client[DB_NAME]
mongo_db_list = mongo_client.list_database_names()
print(mongo_db_list)


def dbInsertUser(db, user_name, user_pwd,first_name, last_name, email, dob):
    table = db[USER_TABLE]
    new_entry = {USER_NAME: user_name, USER_PWD: user_pwd ,USER_FIRST_NAME:first_name, USER_LAST_NAME :last_name,
        USER_EMAIL:email, USER_DOB:dob}
    print(table.insert_one(new_entry))


def dbCheckUserExistence(db, user_name):
    table = db[USER_TABLE]
    table_entries = list(table.find())
    for entry in table_entries:
        entry_user_name = entry[USER_NAME]
        # entry_user_pwd = entry[USER_PWD]
        if entry_user_name == user_name :
            return True
    return False

def dbInsertItem(db, user_name, item_name, image_string, description):
    table = db[ITEM_TABLE]
    new_entry = {USER_NAME: user_name, ITEM_NAME: item_name, DESCRIPTION: description, IMAGE_STRING: image_string}
    print(table.insert_one(new_entry))


def dbCheckUserLogin(db, user_name, user_pwd):
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


def check_email_valid(email):
    # pass the regular expression
    # and the string in search() method
    if re.search(regex, email):
        return 1

    else:
        return 0


def dbCheckItemExistence(db, user_name, item_name):
    table = db[ITEM_TABLE]
    table_entries = list(table.find())
    for entry in table_entries:
        entry_user_name = entry[USER_NAME]
        entry_item_name = entry[ITEM_NAME]
        # entry_user_pwd = entry[USER_PWD]
        if entry_user_name == user_name and entry_item_name == item_name :
            return True
    return False



##### API Definitions #####
##### API Definitions #####
##### API Definitions #####
@app.route('/login', methods=["GET"])
def login():
    user_name = request.args.get(USER_NAME)
    user_pwd = request.args.get(USER_PWD)

    viewItem()



    if dbCheckUserLogin(mongo_db, user_name, user_pwd):
        return USER_LOGIN_SUCCESS
    else:
        return USER_LOGIN_FAILED


@app.route('/register', methods=["GET"])
def register():
    user_name = request.args.get(USER_NAME)
    user_pwd = request.args.get(USER_PWD)
    user_first_name = request.args.get(USER_FIRST_NAME)
    user_last_name = request.args.get(USER_LAST_NAME)
    user_email = request.args.get(USER_EMAIL)
    user_dob = request.args.get(USER_DOB)

    if user_name == "" or user_pwd == "":
        return USER_REG_FAILED_2
    elif check_email_valid(user_email) == 0:
        return NON_VALID_EMAIL
    elif dbCheckUserExistence(mongo_db, user_name):
        return USER_REG_FAILED
    else:
        dbInsertUser(mongo_db, user_name, user_pwd, user_first_name, user_last_name , user_email, user_dob)
        return USER_REG_SUCCESS


@app.route('/addItem', methods=["GET", "POST"])
def upload():
    user_name = request.args.get(USER_NAME)
    user_pwd = request.args.get(USER_PWD)
    item_name = request.args.get(ITEM_NAME)
    description = request.args.get(DESCRIPTION)
    data = request.get_data().decode(CHARSET_ENCODE)
    print(data)

    if dbCheckUserLogin(mongo_db, user_name, user_pwd):
        if (item_name == "") or (data == "null"):
            print("add failed")
            return ADD_ITEM_FAILED
        if (dbCheckItemExistence(mongo_db,user_name,item_name)):
            return ADD_ITEM_FAILED
        else:
            dbInsertItem(mongo_db, user_name, item_name, data, description)
            print("add success")
            return ADD_ITEM_SUCCESS
    else:
        return ADD_ITEM_FAILED


@app.route('/viewItem', methods=["GET"])
def viewItem():
    user_name = request.args.get(USER_NAME)
    user_password = request.args.get(USER_PWD)
    table = mongo_db[ITEM_TABLE]
    table_entries = list(table.find())

    res = ""
    if dbCheckUserLogin(mongo_db,user_name,user_password):
        for entry in table_entries:
            print("debug")
            print(entry[ITEM_NAME])
            res += entry[ITEM_NAME]
            res += INFO_SPLITOR
            res += entry[IMAGE_STRING]
            res += INFO_SPLITOR
            res += entry[DESCRIPTION]
            res += INFO_SPLITOR
            res += entry[USER_NAME]
            res += IMAGE_SPLITOR
        return res


@app.route('/get_user_info', methods=["GET"])
def get_user_info():
    user_name = request.args.get(USER_NAME)
    user_password = request.args.get(USER_PWD)
    table = mongo_db[USER_TABLE]
    table_entries = list(table.find())

    res = ""
    if dbCheckUserLogin(mongo_db,user_name,user_password):
        for entry in table_entries:
            if entry[USER_NAME] == user_name and entry[USER_PWD] == user_password:

                res += entry[USER_FIRST_NAME]
                res += " "
                res += entry[USER_LAST_NAME]

        return res


if __name__ == "__main__":
    app.run()