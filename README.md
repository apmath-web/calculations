## calculations
Calculation service


## examples
# Get payments list for new Loan
curl -i -X POST http://localhost:8080/v1/payments  -H "Content-Type: application/json" -d '{"amount":1000000,"date":"2010-01-01","term":6,"interest":300}'

# Get payments list for present Loan
curl -i -X PUT http://localhost:8080/v1/payments  -H "Content-Type: application/json" -d '{"loan": {"rounding":100,"date":"2010-01-01","interest":300,"regularPaymentAmount":338900}, "lastPayment":{"date":"2010-02-01","remainCreditBody":1000000,"body":84105}}'

# Get Loan internal Data, ensure that loan is correct  
curl -i -X POST http://localhost:8080/v1/loan  -H "Content-Type: application/json" -d '{"amount":1000000,"date":"2010-01-01","term":6,"interest":300}'

# Get Payment result for new Loan
curl -i -X POST http://localhost:8080/v1/payment  -H "Content-Type: application/json" -d '{"loan": {"amount":1000000,"date":"2010-01-01","term":6,"interest":300,"rounding":100,"regularPaymentAmount":338900}, "payment":{"date":"2010-02-01","amount":1000000}}'

# Get Payment result for present Loan
curl -i -X PUT http://localhost:8080/v1/payment  -H "Content-Type: application/json" -d '{"loan": {"date":"2010-01-01","remainingTerm":5,"interest":300,"rounding":100,"regularPaymentAmount":338900}, "payment":{"date":"2010-03-01","amount":338900}, "lastPayment":{"date":"2010-02-01","remainCreditBody":1000000,"body":84105}}'
