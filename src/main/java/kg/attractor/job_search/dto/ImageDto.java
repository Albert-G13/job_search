package kg.attractor.job_search.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {
    @NotNull(message = "File is required")
    private MultipartFile file;
    @Positive(message = "Movie id must be positive")
    private long movieId;
}
