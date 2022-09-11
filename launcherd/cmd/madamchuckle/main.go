package main

import (
	"log"

	"github.com/jaczerob/madamchuckle/internal/server"
)

func main() {
	server := server.NewServer()
	log.Fatal(server.Start())
}
