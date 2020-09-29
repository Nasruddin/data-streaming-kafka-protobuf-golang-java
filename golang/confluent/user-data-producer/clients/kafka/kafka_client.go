package kafka

import (
	"github.com/confluentinc/confluent-kafka-go/kafka"
)

const (
	brokers = "localhost"
)

var (
	Client kafkaClientInterface = &kafkaClient{}
)

type kafkaClientInterface interface {
	setProducer(*kafka.Producer)
}

type kafkaClient struct {
	kp *kafka.Producer
}

func Init() (*kafka.Producer, error) {
	kp, err := kafka.NewProducer(&kafka.ConfigMap{"bootstrap.servers": brokers})
	if err != nil {
		// TODO handle error
		return nil, err
	}
	return kp, nil

}

func (kc *kafkaClient) setProducer(producer *kafka.Producer) {
	kc.kp = producer
}