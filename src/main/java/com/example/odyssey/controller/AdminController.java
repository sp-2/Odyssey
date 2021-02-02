package com.example.odyssey.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;

import com.example.odyssey.models.Piano;
import com.example.odyssey.repositories.PianoRepository;
import com.example.odyssey.services.PianoService;


@Controller
public class AdminController {
    private final PianoService pianoService;

    @Autowired
    private PianoRepository pianoRepository;
    
    public AdminController(PianoService pianoService) {
        this.pianoService = pianoService;
    }  

	@RequestMapping("/admin/pianos/listAllPianos")
    public String listAllAdmin( HttpSession session, Model model, @RequestParam(defaultValue="0") int page) {
  
		Page<Piano> pianos = pianoRepository.findAll(PageRequest.of(page, 4, Direction.ASC, "createdAt"));
        model.addAttribute("pianos", pianos);
      
		model.addAttribute("currentPage", page);
    	return "adminListPianos";
    }
	
	@RequestMapping("/admin/pianos/add")
    public String newPiano( HttpSession session, @ModelAttribute("piano") Piano piano) {
    	return "adminNewPiano";
    }
	
    @RequestMapping(value="/admin/pianos/add", method=RequestMethod.POST)
    public String newPianoPost(@Valid @ModelAttribute("piano") Piano piano, BindingResult result, Model model, HttpSession session) {
    	
    	if (result.hasErrors()) {
    		System.out.println(result);
            return "adminNewPiano";
        } else {
        	Piano pianoSaved = pianoService.createPiano(piano);
        	
        	MultipartFile pianoImage = piano.getPianoimages();
        	
        	try {
        		byte[] bytes = pianoImage.getBytes();
        		String imageName = piano.getId() + ".png";
        		BufferedOutputStream stream = new BufferedOutputStream(
        				new FileOutputStream(new File("src/main/resources/static/image/piano/image/" + imageName)));
        		stream.write(bytes);
        		stream.close();
        	} catch (Exception e) {
        		e.printStackTrace();
        	}
            return "redirect:/admin/pianos/listAllPianos";
        } 	
    }
    
	  @RequestMapping(value="/admin/pianos/{pid}", method=RequestMethod.DELETE)
	  public String destroy(@PathVariable("pid") Long pid) {
	      pianoService.deletePiano(pid);
	      return "redirect:/admin/pianos/listAllPianos";
	  }
    
	  @RequestMapping("/admin/pianos/show/{pid}")
	  public String showEvent( HttpSession session, @PathVariable("pid") Long pid, Model model) {
	  	  Piano piano = pianoService.findPiano(pid);
	  	  model.addAttribute("piano",piano);
	  	  return "/adminShowPiano";
	  }

	  @RequestMapping("/admin/pianos/edit/{pid}")
	  public String editEvent(@PathVariable("pid") Long pid, Model model) {
		  Piano piano = pianoService.findPiano(pid);
		  model.addAttribute("piano",piano);
	      return "/adminUpdatePiano";
	  }
	  
	  @RequestMapping(value="/admin/pianos/{pid}", method=RequestMethod.PUT)
	  public String update(@PathVariable("pid") Long pid, @Valid @ModelAttribute("piano") Piano piano,
			  BindingResult result) {
	      if (result.hasErrors()) {
	          return "/adminUpdatePiano";
	      } else {
	    	  //System.out.println(pid);
	    	  //System.out.println(piano.getId());
	    	  piano.setId(pid);
	    	  //System.out.println("1111");
	    	  pianoService.updatePiano(piano);
	    	  //System.out.println("22222");
	    	  MultipartFile pianoImage = piano.getPianoimages();
	    	  System.out.println(pianoImage);
	    	  if(pianoImage != null) {
	    		  
	    		  try {
	          		byte[] bytes = pianoImage.getBytes();
	          		String imageName = piano.getId() + ".png";

	          		Files.delete(Paths.get("src/main/resources/static/image/piano/image/" + imageName));
	          		
	          		BufferedOutputStream stream = new BufferedOutputStream(
	          				new FileOutputStream(new File("src/main/resources/static/image/piano/image/" + imageName)));
	          		stream.write(bytes);
	          		stream.close();
	          	} catch (Exception e) {
	          		e.printStackTrace();
	          	}
	    	  } else {
	    		  //System.out.println("null");
	    	  }
	    	     	  
	          return "redirect:/admin/pianos/listAllPianos";          
	      }
	  }

}
