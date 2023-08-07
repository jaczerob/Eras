package dev.jaczerob.madamchuckle.launcher.launcher;

import dev.jaczerob.madamchuckle.launcher.utils.Platform;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class LauncherService {
    private static final Logger LOG = LoggerFactory.getLogger(LauncherService.class);

    public boolean launch(final String gameserver, final String cookie) {
        final Optional<Path> optionalInstallationPath = Platform.getInstallationPath();
        if (optionalInstallationPath.isEmpty()) {
            LOG.error("Could not get installation path");
            return false;
        }

        final Path gameDirectory = optionalInstallationPath.get().resolve(Platform.getEngine());
        if (!Files.exists(gameDirectory)) {
            LOG.error("Installation path does not exist");
            return false;
        }

        LOG.info("Launching game");

        final String[] command = new String[] {gameDirectory.toString()};
        final List<String> envList = System.getenv().entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.toList());

        envList.add("TTR_GAMESERVER=" + gameserver);
        envList.add("TTR_PLAYCOOKIE=" + cookie);

        final String[] env = envList.toArray(new String[0]);
        final File dir = gameDirectory.getParent().toFile();

        try {
            Runtime.getRuntime().exec(command, env, dir);
            return true;
        } catch (final IOException exc) {
            LOG.error("Failed to launch game", exc);
            return false;
        }
    }
}
