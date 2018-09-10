curl -X POST --data '{"firstName":"John", "lastName": "Doe"}'  https://4mz0ifhrh7.execute-api.us-east-1.amazonaws.com/dev/greeter

ab -p greeter.json -T application/json -c 10 -n 100 https://4mz0ifhrh7.execute-api.us-east-1.amazonaws.com/dev/greeter 
