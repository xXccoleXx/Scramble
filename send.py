import requests

# Send a GET request to a URL
url = 'http://54.211.46.55:5000/upload'
message = 'Hello, server!'

files = {'file': open('5165935091.png', 'rb')}

response = requests.post(url, files=files)


# Print the response
print('Response Code:', response.status_code)
print('Response Content:', response.text)
