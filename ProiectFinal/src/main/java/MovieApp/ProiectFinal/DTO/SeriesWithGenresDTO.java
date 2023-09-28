package MovieApp.ProiectFinal.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeriesWithGenresDTO {
    private Long id;
    private String title;
    private String description;
    private Integer releaseYear;
    private Double rating;
    private String imageurl;
    private List<String> genres;
}
