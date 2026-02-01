package com.yakria.cms.services;

import com.yakria.cms.dtos.ContactDTO;
import com.yakria.cms.dtos.SearchNameDTO;
import com.yakria.cms.dtos.UpdateContactDTO;
import com.yakria.cms.models.Contact;
import com.yakria.cms.repositories.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ContactServices {
    private final ContactRepository contactRepository;

    public ContactServices(ContactRepository contactRepository){
        this.contactRepository = contactRepository;
    }

    public ContactDTO addContact(ContactDTO contactDTO){
        Contact contact = new Contact();
        contact.setFirstName(contactDTO.getFirstName());
        contact.setLastName(contactDTO.getLastName());
        contact.setEmail(contactDTO.getEmail());
        contact.setContact(contactDTO.getContact());
        contact.setCompany(contactDTO.getCompany());
        contact.setNotes(contactDTO.getNotes());

        List<String> tags = new ArrayList<>();
        if(!contactDTO.getTags().isEmpty()){
            for(int i=0; i<contactDTO.getTags().size(); i++){
                tags.add(contactDTO.getTags().get(i));
            }
            contact.setTags(tags);
        }
        contactRepository.save(contact);
        return contactDTO;
    }

    public List<Contact> getAllContact(){
        return contactRepository.findAll();
    }

    public List<Contact> searchContact(SearchNameDTO searchNameDTO){
        Set<Contact> results = new HashSet<>();

        if (searchNameDTO.getFirstName() != null && !searchNameDTO.getFirstName().isBlank()) {
            results.addAll(contactRepository.findByFirstNameIgnoreCaseContaining(searchNameDTO.getFirstName()));
        }

        if (searchNameDTO.getLastName() != null && !searchNameDTO.getLastName().isBlank()) {
            results.addAll(contactRepository.findByLastNameIgnoreCaseContaining(searchNameDTO.getLastName()));
        }

        if(searchNameDTO.getEmail() != null && !searchNameDTO.getEmail().isBlank()){
            results.add(contactRepository.findByEmailContainingIgnoreCase(searchNameDTO.getEmail()));
        }

        if(searchNameDTO.getCompany() != null && !searchNameDTO.getCompany().isBlank()){
            results.addAll(contactRepository.findByCompanyContainingIgnoreCase(searchNameDTO.getCompany()));
        }

        if(searchNameDTO.getContact() != null && !searchNameDTO.getContact().isBlank() && searchNameDTO.getContact().length() == 10){
            results.add(contactRepository.findByContact(searchNameDTO.getContact()));
        }
        if (searchNameDTO.getTags() != null && !searchNameDTO.getTags().isEmpty()) {
            String[] tagsArray = searchNameDTO.getTags().toArray(new String[0]);
            return contactRepository.findByTags(tagsArray);
        }

        return new ArrayList<>(results);

    }

    public boolean deleteContactById(Long ID){
        try {
            if(contactRepository.existsById(ID)){
                contactRepository.deleteById(ID);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteAllContact(){

        contactRepository.deleteAll();
        return true;
    }

    public Contact updateContact(Long id, UpdateContactDTO updateContactDTO){
        try{
            Contact contact = contactRepository.findById(id).orElse(null);
            if(contact == null){
                return null;
            }
            if(updateContactDTO.getFirstName() != null && !updateContactDTO.getFirstName().isBlank()){
                contact.setFirstName(updateContactDTO.getFirstName());
            }

            if(updateContactDTO.getLastName() != null && !updateContactDTO.getLastName().isBlank()){
                contact.setLastName(updateContactDTO.getFirstName());
            }

            if(updateContactDTO.getEmail() != null && !updateContactDTO.getEmail().isBlank()){
                contact.setEmail(updateContactDTO.getEmail());
            }

            if(updateContactDTO.getCompany() != null && !updateContactDTO.getCompany().isBlank()){
                contact.setCompany(updateContactDTO.getCompany());
            }

            if(updateContactDTO.getContact() != null && !updateContactDTO.getContact().isBlank()){
                contact.setContact(updateContactDTO.getContact());
            }

            if(updateContactDTO.getTags() != null && !updateContactDTO.getTags().isEmpty()){
                HashSet<String> tags = new HashSet<>(updateContactDTO.getTags());
                tags.addAll(contact.getTags());
                contact.setTags(new ArrayList<>(tags));
            }

            contactRepository.save(contact);
            return contact;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
