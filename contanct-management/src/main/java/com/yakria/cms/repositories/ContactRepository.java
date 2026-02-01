package com.yakria.cms.repositories;

import com.yakria.cms.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
//    @Query("""
//        SELECT c FROM Contact c
//        WHERE
//            LOWER(c.name) LIKE LOWER(CONCAT('%', :search, '%'))
//            OR LOWER(c.email) LIKE LOWER(CONCAT('%', :search, '%'))
//            OR LOWER(c.company) LIKE LOWER(CONCAT('%', :search, '%'))
//            OR EXISTS (
//                SELECT t FROM c.tags t
//                WHERE LOWER(t) LIKE LOWER(CONCAT('%', :search, '%'))
//            )
//    """)
//    List<Contact> searchContacts(@Param("search") String search);

    Contact findByEmailContainingIgnoreCase(String email);
    List<Contact> findByCompanyContainingIgnoreCase(String company);

    @Query(
            value = "SELECT * FROM contacts c WHERE c.tags && :tags",
            nativeQuery = true
    )
    List<Contact> findByTags(@Param("tags") String[] tags);

    List<Contact> findByFirstNameIgnoreCaseContaining(String firstName);
    List<Contact> findByLastNameIgnoreCaseContaining(String lastName);

//    void deleteByEmail(String email);
}
