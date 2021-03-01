package app.exam_t.web.controllers;

import app.exam_t.services.contracts.AlbumService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final AlbumService albumService;

    public HomeController(AlbumService albumService) {
        this.albumService = albumService;
    }


    @GetMapping("/")
    public ModelAndView index(HttpSession httpSession, ModelAndView modelAndView) {
        if (httpSession.getAttribute("user") == null) {
            modelAndView.setViewName("index");
        } else {
            modelAndView.addObject("albums", this.albumService.getAllAlbums());
            modelAndView.addObject("totalSold", this.albumService.totalAmountOfSoldCopies());
            modelAndView.setViewName("home");
        }
        return modelAndView;
    }

}
