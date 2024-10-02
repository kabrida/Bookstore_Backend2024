package syksy2024.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.validation.Valid;
import syksy2024.bookstore.model.Book;
import syksy2024.bookstore.model.BookRepository;
import syksy2024.bookstore.model.CategoryRepository;



@Controller
public class BookController {
    @Autowired
    private BookRepository repository;

    @Autowired
    private CategoryRepository cRepository;

    @RequestMapping({"/", "/books"})
    public String showBooks(Model model) {
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }

    @RequestMapping(value = "/add")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", cRepository.findAll());
        return "addbook";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveBook(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println("Error: " + book);
            model.addAttribute("book", book);
            model.addAttribute("category", cRepository.findAll());
            return "addbook";        
        }
        repository.save(book);
        return "redirect:books";
    }    

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long id, Model model) {
    	repository.deleteById(id);
        return "redirect:/books";
    }   

    
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("edit/{id}")
    public String editBook(@PathVariable("id") Long id, Model model) {
        model.addAttribute("edit", repository.findById(id));
        model.addAttribute("categories", cRepository.findAll());
        return "editbook";
    }

    
    

}
