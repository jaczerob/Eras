package static

var Headers = map[string]string{
	"User-Agent": "MadamChuckle (https://github.com/jaczerob/madamchuckle)",
}

const (
	PlatformWindows   = "windows"
	PlatformWindows32 = "win32"
	PlatformWindows64 = "win64"
	PlatformDarwin    = "darwin"

	PathWindows32 = "C:\\Program Files\\Toontown Rewritten"
	PathWindows64 = "C:\\Program Files (x86)\\Toontown Rewritten"
	PathDarwin    = "/Library/Application Support/Toontown Rewritten"
	PathLinux     = "/Toontown Rewritten"

	ExecutableWindows32 = "TTREngine.exe"
	ExecutableWindows64 = "TTREngine64.exe"
	ExecutableDarwin    = "Toontown Rewritten"
	ExecutableLinux     = "TTREngine"
)
