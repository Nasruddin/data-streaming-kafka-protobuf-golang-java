package utils

import "github.com/google/uuid"

func GenerateID()  string {
	id, _ := uuid.NewUUID()
	return id.String()
}
