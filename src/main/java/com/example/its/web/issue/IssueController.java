package com.example.its.web.issue;

import com.example.its.domain.issue.IssueEntity;
import com.example.its.domain.issue.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/issues")
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;

    // GET /issues
    @GetMapping
    public String showList(Model model) {
        model.addAttribute("issueList", issueService.findAll());
        model.addAttribute("activePage", "list");
        return "issues/list";
    }

    // GET /issues/creationForm
    @GetMapping("/creationForm")
    public String showCreationForm(@ModelAttribute IssueForm form, Model model) {
        model.addAttribute("activePage", "list");
        return "issues/creationForm";
    }

    // POST /issues
    @PostMapping
    public String create(@Validated IssueForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return showCreationForm(form, model);
        }
        issueService.create(form.getSummary(), form.getDescription());
        // return "redirect:/issues";
        return "redirect:/";
    }

    // GET localhost:8080/issues/1
    @GetMapping("/{issueId}")
    public String showDetail(@PathVariable("issueId") long issueId, Model model) {
        model.addAttribute("issue", issueService.findById(issueId));
        return "issues/detail";
    }
}
