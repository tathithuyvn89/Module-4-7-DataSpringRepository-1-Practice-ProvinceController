package com.codegym.controller;

import com.codegym.model.Province;
import com.codegym.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProvinceController {
    @Autowired
    private IProvinceService provinceService;
    @GetMapping("/provinces")
    private ModelAndView listProvince(){
        Iterable<Province> provinces= provinceService.findAll();
        ModelAndView modelAndView = new ModelAndView("provinces/list");
        modelAndView.addObject("provinces",provinces);
        return modelAndView;
    }
    @GetMapping("/create-province")
    private ModelAndView showFromCreate(){
        ModelAndView modelAndView = new ModelAndView("provinces/create");
        modelAndView.addObject("province", new Province());
        return modelAndView;
    }
    @PostMapping("/create-province")
    private ModelAndView saveProvince(@ModelAttribute("province") Province province){
        provinceService.save(province);
        ModelAndView modelAndView = new ModelAndView("provinces/create");
        modelAndView.addObject("province",new Province());
        modelAndView.addObject("messager","Create new Province successfully");
        return modelAndView;

    }
    @GetMapping("/edit-province/{id}")
    private String showFormEdit(@PathVariable("id") Long id, Model model){
        Province province = provinceService.findById(id);
        if(province!=null){
            model.addAttribute("province",province);
            return "provinces/edit";
        } else {
            return "error.404";
        }
    }
    @PostMapping("/edit-province")
    private String updateProvince(@ModelAttribute("province") Province province, Model model){
        provinceService.save(province);
        model.addAttribute("province",province);
        model.addAttribute("messager","Update new infomation successfully");
        return "provinces/edit";

    }
    @GetMapping("/delete-province/{id}")
    private String showFormDelete(@PathVariable("id") Long id, Model model){
        Province province = provinceService.findById(id);
        if(province!=null){
            model.addAttribute("province",province);
            return "provinces/delete";
        } else {
            return "error.404";
        }
    }
    @PostMapping("/delete-province")
    public String deleteProvince(@ModelAttribute("province") Province province){
        provinceService.remove(province.getId());
        return "redirect:/provinces";
    }

}
