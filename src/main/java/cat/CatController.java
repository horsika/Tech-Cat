package cat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CatController {

    @Autowired
    private CatRepository catRepo;

    @GetMapping("/mycat")
    public String cat(Model model){
        List<Cat> catList = catRepo.findAllCats();
        model.addAttribute("cat", catList);
        return "show-cat";
    }


    @GetMapping("/addcat")
    public String addCat(Model m){
        m.addAttribute("catform", new Cat());
        return "add-cat";
    }

    @PostMapping("/savecat")
//    @ResponseBody
    public String saveCat(@ModelAttribute Cat cat){

        Cat catInserted = catRepo.insert(cat);
//        return "Meow" + catInserted;
        return "redirect:/mycat";
    }


    @GetMapping("/catv")
    @ResponseBody
    public Cat showCat(){
        Cat cat = catRepo.findCat(1);
        return cat;
    }

    @GetMapping("/deletecat/{id}")
    public String deleteCat(@PathVariable(name = "id") int id){
        catRepo.delete(id);
        return "redirect:/mycat";
    }


    @GetMapping("/edit/{id}")
    public String editCat(Model m, @PathVariable(name = "id") int id){
        Cat catToEdit = catRepo.findCat(id);
        m.addAttribute("catform", catToEdit);
        return "edit-cat";
    }


    @PostMapping("/updatecat")
    public String saveEditCat(@ModelAttribute Cat cat){
        catRepo.update(cat);
        return "redirect:/mycat";
    }
}
