package users

import (
	"github.com/bxcodec/faker/v3"
	"github.com/golang/protobuf/ptypes/timestamp"
)

type User struct {
	FirstName string `json:"first_name"`
	LastName string `json:"last_name"`
	FullName string `json:"full_name"`
	Age int `json:"age"`
	Email string `json:"email"`
	CreateOn timestamp.Timestamp `json:"create_on"`
	Address Address `json:"address"`
 }

type Address struct {
	Latitude float64 `json:"latitude"`
	Longitude float64 `json:"longitude"`
}

func (u *User) Setter() *User {
	return &User{
		FirstName: faker.FirstName(),
		LastName:  faker.LastName(),
		FullName:  faker.Name(),
		Age:       0,
		Email:     faker.Email(),
		Address:   Address{
			Latitude: faker.Latitude(),
			Longitude: faker.Longitude(),
		},
	}
}