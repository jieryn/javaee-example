#! /bin/bash

export base=http://localhost:8080/javaee-1-SNAPSHOT

for i in {1..3}; do
  curl -i -H "Accept: application/json" -X PUT "$base/api/user/create?name=Wizard$i&pwd=simplest&&mail=supername$i@supercompany.com"
done

for i in {1..30}; do
  curl -i -H "Accept: application/json" -X PUT "$base/api/post/create?title=Title$i&content=Content&userId=$((RANDOM % 3 + 1 ))"
done

for i in {1..100}; do
  curl -i -H "Accept: application/json" -X PUT "$base/api/comment/create?author=Author$i&content=Content$i&postId=$((RANDOM % 30 + 51 ))"
done

