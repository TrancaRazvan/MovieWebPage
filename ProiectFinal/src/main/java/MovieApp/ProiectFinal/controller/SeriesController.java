package MovieApp.ProiectFinal.controller;

import MovieApp.ProiectFinal.service.SeriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class SeriesController {
    private  final SeriesService seriesService;

}
