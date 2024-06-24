package com.project.ohflix.domain;


import com.project.ohflix._core.utils.EnumEditor;
import com.project.ohflix.domain._enums.Reason;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/")
    public String getMainPage() {
        return "main-page";
    }
    @GetMapping("/outer-page")
    public String getOuterPage() {
        return "outer-page";
    }


    @GetMapping("/api/info-copy")
    public String getInfoCopyPage() {
        return "admin/info-copy";
    }


}
