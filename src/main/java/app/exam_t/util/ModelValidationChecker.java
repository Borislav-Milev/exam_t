package app.exam_t.util;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface ModelValidationChecker {
    <T> String checkErrors(T model, BindingResult bindingResult,
                           RedirectAttributes redirectAttributes, String redirectString);
}
