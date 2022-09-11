package server

import "github.com/labstack/echo/v4"

type Payload struct {
	Gameserver string `json:"gameserver"`
	Cookie     string `json:"cookie"`
}

type Server struct {
	e *echo.Echo
}
