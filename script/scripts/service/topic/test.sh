#!/bin/bash

## Pause and wait for any key input to continue
pause () {
  echo
	read -n 1 -s -p "Press any key to continue..."
  echo
}
#GENERATE_START


# Create topic
echo "Create topic..."

msg=$(cat ./create.json)

echo "Request message : "

pause

echo $msg

response=$(curl -s -X POST \
  http://localhost:port/api/sdk/topic/ \
  -F "resMsg=$msg")

echo "Response message : "

pause

echo $response

echo "Create topic OK!"

pause 


# Update topic

echo "Update topic..."

msg=$(cat ./update.json)

echo "Request message : "

pause

echo $msg

response=$(curl -s -X PUT \
  http://localhost:port/api/sdk/topic/ \
  -F "resMsg=$msg")

echo "Response message : "

pause

echo $response

echo "Update topic OK!"

pause


# Query topic

echo "Query topic..."

msg=$(cat ./query.json)

echo "Request message : "

pause

echo $msg

response=$(curl -s -X POST \
  http://localhost:port/api/sdk/topic/getList \
  -F "resMsg=$msg")

echo "Response message : "

pause

echo $response

echo "Query topic OK!"

pause


# Delete topic

echo "Delete topic..."

msg=$(cat ./delete.json)

echo $msg

echo "Request message : "

pause

response=$(curl -s -X DELETE \
  http://localhost:port/api/sdk/topic/ \
  -F "resMsg=$msg")

echo "Response message : "

pause

echo $response

echo "Delete topic OK!"


echo "Test finished!"
#GENERATE_END