package kafka

import (
	"encoding/json"
	"fmt"
	"github.com/confluentinc/confluent-kafka-go/kafka"
	"github.com/golang/protobuf/ptypes"
	"github.com/nasruddin/kafka-client/user-data-consumer/proto/protobp"
)

var (
	Client kafkaClientInterface = &kafkaClient{}
)

type kafkaClientInterface interface {
	setConsumer(consumer *kafka.Consumer)
	Subscribe()
}

type kafkaClient struct {
	kafkaConsumer *kafka.Consumer
}

func Init() {
	kc, err := kafka.NewConsumer(&kafka.ConfigMap{
		"bootstrap.servers": "localhost",
		"group.id":          "myGroup",
		"auto.offset.reset": "earliest",
	})

	if err != nil {
		//TODO Handle error
	}
	Client.setConsumer(kc)
	Client.Subscribe()

}

func (kc *kafkaClient) setConsumer(consumer *kafka.Consumer)  {
	kc.kafkaConsumer = consumer
}

func (kc *kafkaClient) Subscribe()  {
	fmt.Println("Starting consumer")

	defer kc.kafkaConsumer.Close()

	if err := kc.kafkaConsumer.SubscribeTopics([]string{"myTopic", "^aRegex.*[Tt]opic"}, nil); err != nil {
		panic(err)
	}

	for {
		msg, err := kc.kafkaConsumer.ReadMessage(-1)
		var res protobp.User
		if err := json.Unmarshal(msg.Value, &res); err != nil {
			panic(err)
		}
		if err == nil {
			fmt.Printf("%+v published on Topic: %s with id: %s at %s\n",
				res.String(), msg.TopicPartition, res.FullName, ptypes.TimestampString(res.CreateOn))
		} else {
			// The client will automatically try to recover from all errors.
			fmt.Printf("Consumer error: %v (%v)\n", err, msg)
		}
	}
}
