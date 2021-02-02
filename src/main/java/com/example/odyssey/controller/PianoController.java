package com.example.odyssey.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.odyssey.models.Piano;
import com.example.odyssey.models.Rating;
import com.example.odyssey.models.User;
import com.example.odyssey.repositories.PianoRepository;
import com.example.odyssey.services.PianoService;
import com.example.odyssey.services.UserService;
import com.example.odyssey.services.RatingService;

@Controller
public class PianoController {
    private final PianoService pianoService;
    private final UserService userService;
    private final PianoRepository pianoRepository;
    private final RatingService ratingService ;
    
    public PianoController(PianoService pianoService, UserService userService, RatingService ratingService, PianoRepository pianoRepository) {
        this.pianoRepository = pianoRepository;
        this.pianoService = pianoService;
        this.userService  = userService;
        this.ratingService  = ratingService;
    }
	
	
	@RequestMapping("/pianos/listAll")
    public String listAll( HttpSession session, Model model) {
    	List<Piano> pianos = pianoRepository.findAll();

        model.addAttribute("pianos", pianos);
    	return "listPianos";
    }
	
	@RequestMapping("/pianos/listAll/new")
    public String listAllNew( HttpSession session, Model model) {
    	List<Piano> pianos = pianoRepository.findByCategory("New");
    	
        model.addAttribute("pianos", pianos);
    	return "listPianos";
    }
	
	@RequestMapping("/pianos/listAll/used")
    public String listAllUsed( HttpSession session, Model model) {
    	List<Piano> pianos = pianoRepository.findByCategory("Used");
    	
        model.addAttribute("pianos", pianos);
    	return "listPianos";
    }
	
	@RequestMapping("/pianos/listAll/digital")
    public String listAllDigital( HttpSession session, Model model) {
    	List<Piano> pianos = pianoRepository.findByDigital("Yes");
    	
        model.addAttribute("pianos", pianos);
    	return "listPianos";
    }
	
	@RequestMapping("/pianos/listAll/rent")
    public String listAllRent( HttpSession session, Model model) {
    	List<Piano> pianos = pianoRepository.findByRent_sale("Rent");
    	
        model.addAttribute("pianos", pianos);
    	return "listPianos";
    }
	 
	@RequestMapping("/pianos/listAll/ajax/search")
    public String listAllAjaxSearch(Model model, @RequestParam(value="sortVal", required = false, defaultValue = "") String sortVal, @RequestParam(value="category", required = false, defaultValue = "") String category, @RequestParam(value="make", required = false, defaultValue = "") String make, @RequestParam(value="grand_upright", required = false, defaultValue = "") String grand_upright, @RequestParam("in_stock") String in_stock) {
		List<Piano> pianos;
    	String new_used  = "";
    	String rent_sale = "";
    	String digital   = "";
    	
    	switch (category) {
	        case "digital" :  digital   = "Yes";  break;              
	        case "new"     :  new_used  = "New";  break;
	        case "used"    :  new_used  = "Used"; break;
	        case "rent"    :  rent_sale = "Rent"; break;
	        case "sale"    :  rent_sale = "Sale"; break;
	        default:  break;
    	}
    	
    	switch (sortVal) {
    		case "created_desc" :  pianos = in_stock == "on" ?  pianoRepository.findAllSortByCreatedDesc(new_used, make, rent_sale, digital, grand_upright, true)      : pianoRepository.findAllSortByCreatedDesc(new_used, make, rent_sale, digital, grand_upright);      break;              
	        case "price_asc"    :  pianos = in_stock == "on" ?  pianoRepository.findAllSortByPriceAsc(new_used, make, rent_sale, digital, grand_upright, true)         : pianoRepository.findAllSortByPriceAsc(new_used, make, rent_sale, digital, grand_upright);         break;              
	        case "price_dsc"    :  pianos = in_stock == "on" ?  pianoRepository.findAllSortByPriceDesc(new_used, make, rent_sale, digital, grand_upright, true)        : pianoRepository.findAllSortByPriceDesc(new_used, make, rent_sale, digital, grand_upright);        break;
	        case "make_asc"     :  pianos = in_stock == "on" ?  pianoRepository.findAllSortByMakeAsc(new_used, make, rent_sale, digital, grand_upright, true)          : pianoRepository.findAllSortByMakeAsc(new_used, make, rent_sale, digital, grand_upright);          break;
	        case "make_dsc"     :  pianos = in_stock == "on" ?  pianoRepository.findAllSortByMakeDesc(new_used, make, rent_sale, digital, grand_upright, true)         : pianoRepository.findAllSortByMakeDesc(new_used, make, rent_sale, digital, grand_upright);         break;
	        case "totalsold"    :  pianos = in_stock == "on" ?  pianoRepository.findAllSortByQuantitySoldDesc(new_used, make, rent_sale, digital, grand_upright, true) : pianoRepository.findAllSortByQuantitySoldDesc(new_used, make, rent_sale, digital, grand_upright); break;
	      //case "ratings_asc"  :  pianos = in_stock == "on" ?  pianoRepository.findAllSortByRatingsDesc(new_used, make, rent_sale, digital, grand_upright, true)      : pianoRepository.findAllSortByRatingsDesc(new_used, make, rent_sale, digital, grand_upright);      break;
	        case ""    			:  ;
	        default             :  pianos = in_stock == "on" ?  pianoRepository.findAll(new_used, make, rent_sale, digital, grand_upright, true)                       : pianoRepository.findAll(new_used, make, rent_sale, digital, grand_upright);                       break;
    	}
		
        model.addAttribute("pianos", pianos);
    	return "listPianos::result-table";
    }
	
	@RequestMapping("/pianos/show/{pid}")
    public String showEvent( HttpSession session, @PathVariable("pid") Long pid, Model model) {
		System.out.println(session.getAttribute("userId"));
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/myAccount/login";
		} else {
	  	    Piano piano = pianoService.findPiano(pid);
	  	  
	  	    int qty = piano.getQuantity();
	  	    List<Integer> qtyList = IntStream.rangeClosed(1, qty)
	  		    .boxed().collect(Collectors.toList());
	  	  
	  	    model.addAttribute("piano",piano);
	  	    model.addAttribute("qtyList",qtyList);
	  	
	  	    return "/showPiano";
		}
   }
	  
   @RequestMapping(value="/search", method=RequestMethod.POST)
   public String PianoSearch(HttpSession session, Model model, @RequestParam("searchKeyword") String searchKeyword) {	    	
		List<Piano> pianos = pianoRepository.findAllByKeyword(searchKeyword);
		    	
        model.addAttribute("pianos", pianos);
	    return "listPianos";
   }
	
   @RequestMapping("/user/pianos/new/rating/{pid}")
   public String showAddRating(@PathVariable("pid") Long pid, Model model, @ModelAttribute("rating") Rating rating) {
	    Piano piano = pianoService.findPiano(pid);
	    model.addAttribute("piano", piano);
	    return "/addRating";
   }

   @RequestMapping(value="/user/pianos/new/rating/{pid}", method=RequestMethod.POST)
   public String addRating(HttpSession session, @PathVariable("pid") Long pid, @Valid @ModelAttribute("rating") Rating rating,
			  BindingResult result) {
	    if (result.hasErrors()) {
	        return "/addRating";
	    } else {
	    	Long id = (Long)session.getAttribute("userId");
	        User rated_by_user = userService.findUserById(id);
	      	  
	    	Piano piano = pianoService.findPiano(pid);
	    	  
	        List <Rating> ratings = piano.getRatings();
	          
	        Rating ratingNew = ratingService.createRating(rating);
	          
	        ratingNew.setRated_by_user(rated_by_user);
	        ratingNew.setPiano(piano);
	        ratings.add(0,ratingNew);	        
	        piano.setRatings(ratings);
	        pianoService.updatePiano(piano);

	        return "redirect:/pianos/show/{pid}";
	    }
   }
	  
   @RequestMapping(value="/pianos/{pid}", method=RequestMethod.DELETE)
   public String destroy(@PathVariable("pid") Long pid) {
		Piano piano = pianoService.findPiano(pid);
		  
	    List <Rating> ratings = piano.getRatings();
	      
	    for (Rating rating: ratings) {
	    	ratingService.deleteRating(rating.getId());
	    }
	    pianoService.deletePiano(pid);
	    return "redirect:/admin/pianos/listAllPianos";
   }
	  
   @RequestMapping("/email/{pid}")
   public String email(HttpSession session,  Model model, @PathVariable("pid") Long pid) {
	    Long id = (Long)session.getAttribute("userId");
	   	User user = userService.findUserById(id);
	   	model.addAttribute("user",user);
	    	
	   	Piano piano = pianoService.findPiano(pid);
	    model.addAttribute("piano",piano);
	    	
	    return "sendMailtoSupport";
   }
    
}
