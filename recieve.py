from flask import Flask, request
import os
import boto3
import random
import subprocess

s3_client = boto3.client('s3')
app = Flask(__name__)

@app.route('/upload', methods=['POST'])
def upload():
    fileName = str(random.randint(1000000000, 9999999999)) + ".png"
    if 'file' not in request.files:
        return "No file found in the request"
    
    # Scramble process
    file = request.files['file']
    file.save(fileName)
    s3_client.upload_file(fileName, "overeasy", fileName)
    subprocess.call("java Scramble " + fileName, shell=True)
    s3_client.upload_file(fileName, "scrambledlikesumeggs", fileName)
    os.remove(fileName)
    return fileName

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=5000)
