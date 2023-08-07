package dev.jaczerob.madamchuckle.launcher.utils;

import java.nio.file.Path;
import java.util.Optional;

public class Platform {
    public static Optional<Path> getInstallationPath() {
        final String customPath = System.getenv("TTR_INSTALL_PATH");

        final Path installationPath;
        if (customPath != null) {
            installationPath = Path.of(customPath);
        } else if (isDarwin()) {
            installationPath = Path.of(System.getProperty("user.home"), "/Library/Application Support/Toontown Rewritten");
        } else if (isWindows() && is64Bit()) {
            installationPath =  Path.of(System.getenv("ProgramFiles"), "/Toontown Rewritten");
        } else if (isWindows()) {
            installationPath = Path.of(System.getenv("ProgramFiles(x86)"), "/Toontown Rewritten");
        } else {
            installationPath = null;
        }

        return Optional.ofNullable(installationPath);
    }

    public static String getEngine() {
        if (isDarwin()) {
            return "Toontown Rewritten";
        } else if (isWindows() && is64Bit()) {
            return "TTREngine64.exe";
        } else if (isWindows()) {
            return "TTREngine.exe";
        } else if (isLinux()) {
            return "TTREngine";
        } else {
            return "unknown";
        }
    }

    public static String getPlatform() {
        if (isDarwin()) {
            return "darwin";
        } else if (isWindows()) {
            if (is64Bit()) {
                return "win64";
            } else {
                return "win32";
            }
        } else if (isLinux()) {
            return "linux";
        } else {
            return "unknown";
        }
    }

    private static boolean isDarwin() {
        return System.getProperty("os.name").toLowerCase().contains("mac");
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }

    private static boolean isLinux() {
        return System.getProperty("os.name").toLowerCase().contains("linux");
    }

    private static boolean is64Bit() {
        if (isDarwin() || isLinux()) {
            return true;
        } else if (isWindows()) {
            return System.getenv("ProgramFiles(x86)") != null;
        } else {
            return false;
        }
    }
}
