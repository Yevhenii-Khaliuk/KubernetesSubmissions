package dev.khaliuk.todoapp.controller;

import dev.khaliuk.todoapp.service.ImageService;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.MalformedURLException;

@Controller
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/images/image.jpeg")
    public ResponseEntity<Resource> getImage() throws MalformedURLException {
        var imageResource = imageService.getImageResource();
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageResource);
    }
}
