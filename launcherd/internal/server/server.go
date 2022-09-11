package server

import (
	"log"
	"net/http"

	"github.com/gorilla/websocket"
	"github.com/jaczerob/madamchuckle/internal/update"
	"github.com/jaczerob/madamchuckle/internal/util"
	"github.com/labstack/echo/v4"
	"github.com/labstack/echo/v4/middleware"
)

var (
	upgrader = websocket.Upgrader{
		CheckOrigin: func(r *http.Request) bool {
			return true
		},
	}

	executable = util.NewExecutable()
	updater    = update.NewUpdateClient(executable)
)

func launch(c echo.Context) error {
	ws, err := upgrader.Upgrade(c.Response(), c.Request(), nil)
	if err != nil {
		log.Println(err)
		return err
	}

	defer ws.Close()

	log.Println("reading json")
	var payload Payload
	if err = ws.ReadJSON(&payload); err != nil {
		log.Println(err)
		return err
	}

	log.Println("updating")
	if err = updater.Update(); err != nil {
		log.Println(err)
		return err
	}

	log.Println("running")
	return executable.Run(payload.Gameserver, payload.Cookie)
}

func NewServer() *Server {
	e := echo.New()

	e.Use(middleware.LoggerWithConfig(middleware.LoggerConfig{
		Format: "[${method}] ${uri} ${status}\n",
	}))
	e.Use(middleware.CORSWithConfig(middleware.CORSConfig{
		AllowOrigins: []string{"http://localhost:4200"},
	}))

	e.GET("/", launch)

	return &Server{
		e: e,
	}
}

func (s *Server) Start() error {
	return s.e.Start(":3000")
}
