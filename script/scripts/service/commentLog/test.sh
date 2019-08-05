#!/bin/bash

## Pause and wait for any key input to continue
pause () {
  echo
	read -n 1 -s -p "Press any key to continue..."
  echo
}
#GENERATE_START


# Create commentLog
echo "Create commentLog..."

msg=$(cat ./create.json)

echo "Request message : "

pause

echo $msg

response=$(curl -s -X POST \
  http://localhost:port/api/sdk/comment_log/ \
  -F "resMsg=$msg")

echo "Response message : "

pause

echo $response

echo "Create commentLog OK!"

pause 


# Update commentLog

echo "Update commentLog..."

msg=$(cat ./update.json)

echo "Request message : "

pause

echo $msg

response=$(curl -s -X PUT \
  http://localhost:port/api/sdk/comment_log/ \
  -F "resMsg=$msg")

echo "Response message : "

pause

echo $response

echo "Update commentLog OK!"

pause


# Query commentLog

echo "Query commentLog..."

msg=$(cat ./query.json)

echo "Request message : "

pause

echo $msg

response=$(curl -s -X POST \
  http://localhost:port/api/sdk/comment_log/getList \
  -F "resMsg=$msg")

echo "Response message : "

pause

echo $response

echo "Query commentLog OK!"

pause


# Delete commentLog

echo "Delete commentLog..."

msg=$(cat ./delete.json)

echo $msg

echo "Request message : "

pause

response=$(curl -s -X DELETE \
  http://localhost:port/api/sdk/comment_log/ \
  -F "resMsg=$msg")

echo "Response message : "

pause

echo $response

echo "Delete commentLog OK!"


echo "Test finished!"
#GENERATE_END