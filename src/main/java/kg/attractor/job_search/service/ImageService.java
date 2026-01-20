package kg.attractor.job_search.service;

import kg.attractor.job_search.dto.ImageDto;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    @SneakyThrows
    String saveUploadFile(MultipartFile file, String subDir);

    ResponseEntity<?> downloadFile(String fileName, String subDir, MediaType mediaType);

    ResponseEntity<?> getById(String fileName);

    void create(ImageDto imageDto);
}
