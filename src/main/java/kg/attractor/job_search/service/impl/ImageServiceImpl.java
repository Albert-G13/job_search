package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dto.ImageDto;
import kg.attractor.job_search.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    @Override
    public ResponseEntity<?> getById(String fileName){
        return downloadFile(fileName, "images", MediaType.IMAGE_JPEG);
    }

    @Override
    public void create(ImageDto imageDto){

        String fileName = saveUploadFile(imageDto.getFile(), "images");
        System.out.println(fileName);
    }
}
