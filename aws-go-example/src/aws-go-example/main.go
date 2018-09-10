package main

import (
	"fmt"
	"github.com/aws/aws-lambda-go/lambda"
)

type greeterRequest struct {
	FirstName string `json:"firstName"`
	LastName  string `json:"lastName"`
}

type greeterResponse struct {
	Greeting string `json:"greeting"`
}

func greet(request greeterRequest) (greeterResponse, error) {
	greeting := fmt.Sprintf("Hello %v %v", request.FirstName, request.LastName)
	return greeterResponse{Greeting: greeting}, nil
}

func main() {
	lambda.Start(greet)
}
