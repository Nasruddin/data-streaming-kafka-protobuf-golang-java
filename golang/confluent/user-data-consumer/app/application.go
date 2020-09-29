package app

import (
	"fmt"
	"github.com/nasruddin/kafka-client/user-data-consumer/clients/kafka"
)

func StartApp()  {
	fmt.Print("Starting application")
	kafka.Init()
}
