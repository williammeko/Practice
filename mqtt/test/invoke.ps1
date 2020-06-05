Invoke-RestMethod http://localhost:8081/publish -Method Post -ContentType application/json -Body '{"a":1,"b":3}'

0.99 | foreach {
    Invoke-RestMethod http://localhost:8081/publish -Method Post -ContentType application/json -Body '{"a":1,"b":3}'
}

curl http://localhost:8081/publish -XPOST -H 'Content-Type: application/json' -d '{"a":1,"b":3}' -i
