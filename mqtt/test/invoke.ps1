Invoke-RestMethod http://localhost:8081/publish -Method -ContentType Post -Body @{}



curl http://localhost:8081/publish -XPOST -H 'Content-Type: application/json' -d '{}' -i
