package util

import (
	"fmt"
	"log"
	"os"
	"os/exec"
	"path"
	"runtime"

	"github.com/jaczerob/madamchuckle/internal/static"
)

type Executable struct {
	Platform  string
	Directory string
	Path      string
}

func NewExecutable() *Executable {
	var platform, dir, executable string

	platform = runtime.GOOS
	if platform == static.PlatformWindows {
		arch := runtime.GOARCH
		if arch[len(arch)-2:] == "64" {
			platform = static.PlatformWindows64
		} else {
			platform = static.PlatformWindows32
		}
	}

	switch platform {
	case static.PlatformWindows32:
		dir = static.PathWindows32
	case static.PlatformWindows64:
		dir = static.PathWindows64
	case static.PlatformDarwin:
		home, err := os.UserHomeDir()
		if err != nil {
			log.Fatal(err)
		}

		dir = path.Join(home, static.PathDarwin)

	default:
		home, err := os.UserHomeDir()
		if err != nil {
			log.Fatal(err)
		}

		dir = path.Join(home, static.PathLinux)
	}

	if _, err := os.Stat(dir); os.IsNotExist(err) {
		err = os.MkdirAll(dir, 0700)
		if err != nil {
			log.Fatal(err)
		}
	}

	switch platform {
	case static.PlatformWindows32:
		executable = static.ExecutableWindows32
	case static.PlatformWindows64:
		executable = static.ExecutableWindows64
	case static.PlatformDarwin:
		executable = static.ExecutableDarwin
	default:
		executable = static.ExecutableLinux
	}

	return &Executable{
		Platform:  platform,
		Directory: dir,
		Path:      path.Join(dir, executable),
	}
}

func (e *Executable) Run(gameserver string, cookie string) (err error) {
	os.Chdir(e.Directory)

	env := os.Environ()
	env = append(env, fmt.Sprintf("TTR_GAMESERVER=%s", gameserver))
	env = append(env, fmt.Sprintf("TTR_PLAYCOOKIE=%s", cookie))

	cmd := &exec.Cmd{
		Path: e.Path,
		Env:  env,
	}

	if err = cmd.Start(); err != nil {
		return fmt.Errorf("could not start toontown rewritten: %s", err)
	}

	return
}
