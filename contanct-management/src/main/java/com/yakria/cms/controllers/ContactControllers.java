package com.yakria.cms.controllers;

import com.yakria.cms.dtos.ContactDTO;
import com.yakria.cms.dtos.SearchNameDTO;
import com.yakria.cms.dtos.UpdateContactDTO;
import com.yakria.cms.models.Contact;
import com.yakria.cms.services.ContactServices;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "http://localhost:5173")
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

        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ContactDTO addContact(@RequestBody @Valid ContactDTO contactDTO){
        return contactServices.addContact(contactDTO);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Contact>> searchContact(@RequestBody SearchNameDTO param){
        log.info("Search Parameter: "+param);

        return ResponseEntity.ok(contactServices.searchContact(param));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        contactServices.deleteContactById(id);
        return ResponseEntity.ok("Contact deleted");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAll(){
        boolean flag = contactServices.deleteAllContact();
        if(flag){
            return new ResponseEntity<>("Contact deleted", HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>("Failure occured", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateContact(@PathVariable Long id, @RequestBody UpdateContactDTO updateContactDTO){
        Contact contact = contactServices.updateContact(id, updateContactDTO);
        if(contact != null){
            return new ResponseEntity<>(contact, HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>("Error occured", HttpStatus.BAD_REQUEST);
    }
}
