package app

import (
	"fmt"
	"github.com/nasruddin/kafka-client/user-data-producer/clients/kafka"
	"github.com/nasruddin/kafka-client/user-data-producer/producer"
)

func StartApp()  {
	fmt.Print("Starting application")
	startKafkaProducer()
}

func startKafkaProducer()  {
	kp, err := kafka.Init()
	if err != nil {
		// Handle error
	}

	producer.Publish(kp)
}
