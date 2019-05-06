## calculations
Calculation service


## examples
# Get payments list for new Loan
curl -i -X POST http://localhost:8080/v1/payments  -H "Content-Type: application/json" -d '{"amount":1000000,"date":"2010-01-01","currency":"RUR","term":6,"interest":300}'