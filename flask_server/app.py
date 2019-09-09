from flask import Flask
from flask import request

app = Flask(__name__)

@app.route('/login')
def login():
    user_name = request.args.get("user_name")
    user_pwd = request.args.get("user_pwd")
    user_email = request.args.get("user_email")

    return user_name + " " + user_pwd + " "+ user_email

if __name__ == '__main__':
    app.run(debug=True)