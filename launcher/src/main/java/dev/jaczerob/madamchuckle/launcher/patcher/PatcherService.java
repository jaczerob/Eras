package dev.jaczerob.madamchuckle.launcher.patcher;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.jaczerob.madamchuckle.launcher.utils.Platform;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import jakarta.inject.Singleton;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Singleton
public class PatcherService {
    private static final Logger LOG = LoggerFactory.getLogger(PatcherService.class);

    private static final String MANIFEST = "https://cdn.toontownrewritten.com/content/patchmanifest.txt";
    private static final String DOWNLOADS = "https://download.toontownrewritten.com/patches/";

    private static final int BUFFER_SIZE = 10 * 1024 * 1024;

    private final BlockingHttpClient httpClient;
    private final ObjectMapper objectMapper;

    public PatcherService(final HttpClient httpClient, final ObjectMapper objectMapper) {
        this.httpClient = httpClient.toBlocking();
        this.objectMapper = objectMapper;
    }

    public void patch() {
        final Map<String, ManifestItem> manifest = this.getManifest();
        LOG.info("Got manifest items: {}", manifest.size());

        final Map<Path, ManifestItem> patchCandidates = this.getPatchCandidates(manifest);
        LOG.info("Patching {} files", patchCandidates.size());

        patchCandidates.forEach(this::downloadPatch);
    }

    private void downloadPatch(final Path path, final ManifestItem manifestItem) {
        final String dl = manifestItem.getDl();
        final String url = DOWNLOADS + dl;
        LOG.info("Downloading {} to {}", dl, path);

        final String response = this.httpClient.retrieve(url);

        try (
            final var bin = new ByteArrayInputStream(response.getBytes());
            final var in = new BufferedInputStream(bin);
            final var out = Files.newOutputStream(path);
            final var bzIn = new BZip2CompressorInputStream(in);
        ) {
            final byte[] buffer = new byte[BUFFER_SIZE];

            int n;
            while (-1 != (n = bzIn.read(buffer)))
                out.write(buffer, 0, n);

            LOG.info("Downloaded {} to {}", dl, path);
        } catch (final IOException exc) {
            LOG.error("Error downloading patch", exc);
        }
    }

    private Map<Path, ManifestItem> getPatchCandidates(final Map<String, ManifestItem> manifest) {
        final Map<Path, ManifestItem> patchCandidates = new LinkedHashMap<>();

        final Optional<Path> optionalInstallationPath = Platform.getInstallationPath();
        if (optionalInstallationPath.isEmpty()) {
            LOG.error("Could not get installation path");
            return patchCandidates;
        }

        final Path gameDirectory = optionalInstallationPath.get();
        if (!Files.exists(gameDirectory)) {
            LOG.error("Installation path does not exist");
            return patchCandidates;
        }

        manifest.forEach((fileName, manifestItem) -> {
            final String platform = Platform.getPlatform();
            if (manifestItem.getOnly().contains(platform)) {
                LOG.info("Skipping file {} because it is not for {}", fileName, platform);
                return;
            }

            final Path installationPath = gameDirectory.resolve(fileName);
            if (!Files.exists(installationPath)) {
                LOG.info("File {} does not exist, queuing for update", fileName);
                patchCandidates.put(installationPath, manifestItem);
                return;
            }

            final Optional<String> optionalInstalledFileHash = this.calculateHashOfFile(installationPath);
            if (optionalInstalledFileHash.isEmpty()) {
                LOG.error("Could not get hash of file {}", installationPath);
                return;
            }

            final String installedFileHash = optionalInstalledFileHash.get();
            if (!installedFileHash.equals(manifestItem.getHash())) {
                LOG.info("File {} outdated, queuing for update", fileName);
                patchCandidates.put(installationPath, manifestItem);
            }
        });

        return patchCandidates;
    }

    private Optional<String> calculateHashOfFile(final Path path) {
        final byte[] bytes;

        try {
            bytes = Files.readAllBytes(path);
        } catch (final IOException exc) {
            LOG.error("Error reading file", exc);
            return Optional.empty();
        }

        return Optional.of(DigestUtils.sha1Hex(bytes));
    }

    private Map<String, ManifestItem> getManifest() {
        final HttpResponse<String> response = httpClient.exchange(MANIFEST);
        if (response.getStatus().getCode() != 200 || response.body() == null) {
            LOG.error("Error getting manifest: {}", response.getStatus().getCode());
            return Map.of();
        }
        final var type = new TypeReference<Map<String, ManifestItem>>() {};

        try {
            return this.objectMapper.readValue(response.body(), type);
        } catch (final IOException exc) {
            LOG.error("Error parsing manifest", exc);
            return Map.of();
        }
    }
}
