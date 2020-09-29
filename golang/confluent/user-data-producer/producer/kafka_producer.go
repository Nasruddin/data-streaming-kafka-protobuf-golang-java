package producer

import (
	"encoding/json"
	"fmt"
	"github.com/bxcodec/faker/v3"
	"github.com/confluentinc/confluent-kafka-go/kafka"
	"github.com/golang/protobuf/ptypes"
	"github.com/nasruddin/kafka-client/user-data-producer/proto/protobp"
	"time"
)

func Publish(kp *kafka.Producer)  {
	fmt.Println("Starting producer")
	defer kp.Close()
	// Delivery report handler for produced messages

	go func() {
		for e := range kp.Events() {
			switch ev := e.(type) {
			case *kafka.Message:
				if ev.TopicPartition.Error != nil {
					fmt.Printf("Delivery failed: %v\n", ev.TopicPartition)
				} else {
					fmt.Printf("Delivered message to %v\n", ev.TopicPartition)
				}
			}
		}
	}()

	// Produce messages to topic (asynchronously)
	topic := "myTopic"

	for i := 0; i<=10; i++ {
		ts, err := ptypes.TimestampProto(time.Now())
		if err != nil {
			panic(err)
		}

		address := &protobp.Address{
			Latitude:  faker.Latitude(),
			Longitude: faker.Longitude(),
		}

		user := protobp.User{
			FirstName: faker.FirstName(),
			LastName:  faker.LastName(),
			FullName:  faker.Name(),
			Age:       0,
			Email:     faker.Email(),
			CreateOn:  ts,
			Address:   address,
		}


		eventBytes, _ := json.Marshal(user)
		kp.Produce(&kafka.Message{
			TopicPartition: kafka.TopicPartition{Topic: &topic, Partition: kafka.PartitionAny},
			Value:          eventBytes,
		}, nil)

		// Creating delay
		time.Sleep(2 * time.Second)
	}



	// Wait for message deliveries before shutting down
	kp.Flush(15 * 1000)
}
