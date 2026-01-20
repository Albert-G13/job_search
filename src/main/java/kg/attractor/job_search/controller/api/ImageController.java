package kg.attractor.job_search.controller.api;


import jakarta.validation.Valid;
import kg.attractor.job_search.dto.ImageDto;
import kg.attractor.job_search.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/{filename}")
    public ResponseEntity<?> getImage(@PathVariable("filename") String filename){
        return imageService.getById(filename);
    }

    @PostMapping
    public HttpStatus create(@ModelAttribute @Valid ImageDto imageDto){
        imageService.create(imageDto);
        return HttpStatus.CREATED;
    }
}
