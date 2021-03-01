package app.exam_t.web.controllers;

import app.exam_t.models.models.AlbumAddBindingModel;
import app.exam_t.models.models.UserLoginBindingModel;
import app.exam_t.models.services.AlbumServiceModel;
import app.exam_t.services.contracts.AlbumService;
import app.exam_t.util.ModelValidationChecker;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/albums")
public class AlbumController {

    private final ModelValidationChecker modelValidationChecker;
    private final AlbumService albumService;
    private final ModelMapper modelMapper;

    @Autowired
    public AlbumController(ModelValidationChecker modelValidationChecker,
                           AlbumService albumService, ModelMapper modelMapper) {
        this.modelValidationChecker = modelValidationChecker;
        this.albumService = albumService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add")
    public String addAlbumConfirm(@Valid AlbumAddBindingModel albumAddBindingModel,
                                  RedirectAttributes redirectAttributes,
                                  BindingResult bindingResult,
                                  HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return this.modelValidationChecker.checkErrors(albumAddBindingModel, bindingResult, redirectAttributes,
            "add");
        }
        AlbumServiceModel albumServiceModel = this.modelMapper.map(albumAddBindingModel, AlbumServiceModel.class);
        UserLoginBindingModel userLoginBindingModel = (UserLoginBindingModel) httpSession.getAttribute("user");
        albumServiceModel.setAddedFrom(userLoginBindingModel.getUsername());
        this.albumService.addAlbum(albumServiceModel);
        return "redirect:/";
    }

    @GetMapping("/add")
    public String addAlbum(Model model, HttpSession httpSession) {
        if (null == httpSession.getAttribute("user")) {
            return "redirect:/";
        }
        if (!model.containsAttribute("albumAddBindingModel")) {
            model.addAttribute("albumAddBindingModel", new AlbumAddBindingModel());
        }
        return "add-album";
    }

    @GetMapping("/delete/{id}")
    public String buyById(@PathVariable String id) {
        this.albumService.deleteById(id);
        return "redirect:/";
    }
}
