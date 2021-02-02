package com.example.odyssey.services;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.odyssey.models.Piano;
import com.example.odyssey.repositories.PianoRepository;

@Service
public class PianoService {

	// adding the piano repository as a dependency
    private final PianoRepository pianoRepository;
    
    public PianoService(PianoRepository pianoRepository) {
        this.pianoRepository = pianoRepository;
    }
    
    // returns all the pianos
    public List<Piano> allPianos() {
        return pianoRepository.findAll();
    }
    
 // returns all the pianos admin
//    public List<Piano> allPianos(PageRequest pageRequest) {
//        return pianoRepository.findAll(pageRequest);
//    }
//    
    // creates a piano
    public Piano createPiano(Piano piano) {
        return pianoRepository.save(piano);
    }
    
    // retrieves a piano
    public Piano findPiano(Long id) {
        Optional<Piano> optionalPiano = pianoRepository.findById(id);
        if(optionalPiano.isPresent()) {
            return optionalPiano.get();
        } else {
            return null;
        }
    }
    
    // retrieves pianos by make
    public List<Piano> findPianoByMake(String make) {
        List<Piano> pianos = pianoRepository.findByMake(make);
        if(pianos != null) {
            return pianos;
        } else {
            return null;
        }
        
    }
    
    // retrieves pianos by model
//    public List<Piano> findPianoByModel(String model) {
//        List<Piano> pianos = pianoRepository.findByModel(model);
//        if(pianos != null) {
//            return pianos;
//        } else {
//            return null;
//        }
//        
//    }
    
    // updates a piano
    public Piano updatePiano(Long id, String title, String desc) {
        
        Optional<Piano> optionalPiano = pianoRepository.findById(id);

        if(optionalPiano.isPresent()) {
        	Piano piano = optionalPiano.get();       	
        	piano.setTitle(title);
        	piano.setDescription(desc);

        	return pianoRepository.save(piano);
        } else {
            return null;
        }  	
    	
    }
    
    // updates a piano
    public Piano updatePiano(Piano piano) {
        return pianoRepository.save(piano);
    }
    
    // deletes a piano
    public void deletePiano(Long id) {       
        Optional<Piano> optionalPiano = pianoRepository.findById(id);
        if(optionalPiano.isPresent()) {
        	pianoRepository.deleteById(id);
        } 
    }
}

