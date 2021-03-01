package app.exam_t.util;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class ModelValidationCheckerImpl implements ModelValidationChecker {

    public <T> String checkErrors(T model, BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes, String redirectString) {
        String modelName = Character.toLowerCase(model.getClass().getSimpleName().charAt(0)) +
                model.getClass().getSimpleName().substring(1);

        redirectAttributes.addFlashAttribute(String.format("%s", modelName), model);
        redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult." + modelName,
                bindingResult);

        return "redirect:" + redirectString;
    }
}
