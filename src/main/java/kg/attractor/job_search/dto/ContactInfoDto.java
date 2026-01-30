package kg.attractor.job_search.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactInfoDto {
    private Integer id;
    @NotNull(message = "Выберите тип контакта")
    private Integer typeId;
    private String contactValue;
}
