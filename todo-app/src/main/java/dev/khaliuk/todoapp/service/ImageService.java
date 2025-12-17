package dev.khaliuk.todoapp.service;

import dev.khaliuk.todoapp.client.ImageClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

@Service
public class ImageService {
    private static final int REFRESH_PERIOD_MINUTES = 10;

    private final ImageClient imageClient;

    @Value("${todo-app.images-dir-path}")
    private String imagesDirPath;

    private long fileUpdated;
    private boolean updateIsNeeded = true;

    public ImageService(ImageClient imageClient) {
        this.imageClient = imageClient;
    }

    public synchronized void prepareImage() {
        if (updateIsNeeded) {
            fetchAndSaveImage();
            fileUpdated = System.currentTimeMillis();
            updateIsNeeded = false;
            return;
        }

        var currentTime = System.currentTimeMillis();
        var timePassed = Duration.ofMillis(currentTime - fileUpdated);

        if (timePassed.toMinutes() >= REFRESH_PERIOD_MINUTES) {
            updateIsNeeded = true;
        }
    }

    public Resource getImageResource() throws MalformedURLException {
        var imagePath = getImagePath();
        return new UrlResource(imagePath.toUri());
    }

    private void fetchAndSaveImage() {
        var filePath = getImagePath();
        var image = imageClient.getNewImage();

        try (var fileOutputStream = Files.newOutputStream(filePath)) {
            fileOutputStream.write(image);
        } catch (IOException e) {
            System.out.println("Error during image saving: " + e.getMessage());
            e.printStackTrace();
            // TODO: throw an exception
            return;
        }

        fileUpdated = System.currentTimeMillis();
    }

    private Path getImagePath() {
        return Paths.get(imagesDirPath, "/image.jpeg");
    }
}
