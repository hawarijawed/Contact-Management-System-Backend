package com.yakria.cms.controllers;

import com.yakria.cms.dtos.ContactDTO;
import com.yakria.cms.dtos.SearchNameDTO;
import com.yakria.cms.models.Contact;
import com.yakria.cms.services.ContactServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/contact")
public class ContactControllers {
    private final ContactServices contactServices;

    public ContactControllers(ContactServices contactServices){
        this.contactServices = contactServices;
    }

    @GetMapping("/get")
    public ResponseEntity<List<Contact>> getAllContact(){
        List<Contact> contacts = contactServices.getAllContact();
        if(contacts.isEmpty()){
            return new ResponseEntity<>(contacts, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(contacts, HttpStatus.FOUND);
    }

    @PostMapping("/add")
    public ContactDTO addContact(@Validated @RequestBody ContactDTO contactDTO){
        return contactServices.addContact(contactDTO);
    }

    @GetMapping("/search/")
    public ResponseEntity<List<Contact>> searchContact(@RequestBody SearchNameDTO param){
        log.info("Search Parameter: "+param);

        List<Contact> contacts = contactServices.searchContact(param.getFirstName(), param.getLastName(), param.getEmail(), param.getTags(), param.getCompany());
        if(contacts.isEmpty()){
            return new ResponseEntity<>(contacts, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(contacts, HttpStatus.FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        boolean flag = contactServices.deleteContactById(id);
        if(flag){
            return new ResponseEntity<>("Contact deleted", HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>("Failure occured", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAll(){
        boolean flag = contactServices.deleteAllContact();
        if(flag){
            return new ResponseEntity<>("Contact deleted", HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>("Failure occured", HttpStatus.BAD_REQUEST);
    }
}
