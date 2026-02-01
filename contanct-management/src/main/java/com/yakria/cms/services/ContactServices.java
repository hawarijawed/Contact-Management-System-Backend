package com.yakria.cms.services;

import com.yakria.cms.dtos.ContactDTO;
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

    public List<Contact> searchContact(String first, String last, String email, List<String> tags, String company){
        Set<Contact> results = new HashSet<>();

        if (first != null && !first.isBlank()) {
            results.addAll(contactRepository.findByFirstNameIgnoreCaseContaining(first));
        }

        if (last != null && !last.isBlank()) {
            results.addAll(contactRepository.findByLastNameIgnoreCaseContaining(last));
        }

        if(email != null && !email.isBlank()){
            results.add(contactRepository.findByEmailContainingIgnoreCase(email));
        }

        if(company != null && !company.isBlank()){
            results.addAll(contactRepository.findByCompanyContainingIgnoreCase(company));
        }

        if(!tags.isEmpty()){
            String[] tagsArray = tags.toArray(new String[0]);
            results.addAll(contactRepository.findByTags(tagsArray));
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

}
