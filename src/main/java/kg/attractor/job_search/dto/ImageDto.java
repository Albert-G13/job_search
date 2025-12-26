package kg.attractor.job_search.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class ImageDto {
    @NotNull(message = "File is required")
    private MultipartFile file;
    @Positive(message = "Movie id must be positive")
    private long movieId;
}
