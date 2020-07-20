package com.forawhile.blog.web.admin;


import com.forawhile.blog.po.Tag;
import com.forawhile.blog.po.Type;
import com.forawhile.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String tags(@PageableDefault(sort = {"id"},direction = Sort.Direction.DESC)
                                   Pageable pageable, Model model){
        model.addAttribute("page",tagService.listTag(pageable));
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags_create";
    }

    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("tag",tagService.getTag(id));
        return "admin/tags_create";
    }


    @PostMapping("/tags")
    public String post(@Valid Tag tag, BindingResult result, RedirectAttributes attributes){

        Tag t = tagService.getTagByName(tag.getName());

        if(t!=null){
            result.rejectValue("name","nameError","不能重复添加标签");
        }
        if(result.hasErrors()){
            return "admin/tags_create";
        }

        Tag tag1 = tagService.saveTag(tag);
        if(tag1 == null){
            attributes.addFlashAttribute("message","增加失败");
        }else{
            attributes.addFlashAttribute("message","增加成功");
        }
        return "redirect:/admin/tags";

    }

    @PostMapping("/tags/{id}")
    public String editPost(@Valid Tag tag, BindingResult result,@PathVariable Long id, RedirectAttributes attributes){

        Tag t = tagService.getTagByName(tag.getName());

        if(t!=null){
            result.rejectValue("name","nameError","不能重复添加标签");
        }
        if(result.hasErrors()){
            return "admin/tags_create";
        }

        Tag tag1 = tagService.updateTag(id,tag);
        if(tag1 == null){
            attributes.addFlashAttribute("message","改动失败");
        }else{
            attributes.addFlashAttribute("message","改动成功");
        }
        return "redirect:/admin/tags";

    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id , RedirectAttributes attributes){
        tagService.deleteTag(id);

        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tags";
    }
}