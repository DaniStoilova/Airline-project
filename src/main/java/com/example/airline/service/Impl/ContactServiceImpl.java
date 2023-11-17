package com.example.airline.service.Impl;

import com.example.airline.model.dto.ContactUsDto;
import com.example.airline.model.entity.Contact;
import com.example.airline.repository.ContactRepository;
import com.example.airline.service.ContactService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {


    private final ContactRepository contactRepository;



    private final ModelMapper modelMapper;

    public ContactServiceImpl(ContactRepository contactRepository, ModelMapper modelMapper) {
        this.contactRepository = contactRepository;

        this.modelMapper = modelMapper;
    }


    @Override
    public void addContact(ContactUsDto contactUsDto) {

        Contact contact = modelMapper.map(contactUsDto,Contact.class);


        contactRepository.save(contact);


    }
}
