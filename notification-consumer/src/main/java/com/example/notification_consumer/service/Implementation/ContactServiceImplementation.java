package com.example.notification_consumer.service.Implementation;

import com.example.notification_consumer.dto.ContactDto;
import com.example.notification_consumer.dto.DeleteResponseDto;
import com.example.notification_consumer.exception.NotFoundException;
import com.example.notification_consumer.mapper.ContactMapper;
import com.example.notification_consumer.model.Contact;
import com.example.notification_consumer.model.User;
import com.example.notification_consumer.repository.ContactRepository;
import com.example.notification_consumer.service.ContactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContactServiceImplementation implements ContactService {
    public final ContactMapper mapper;
    public final ContactRepository repository;
    private final UserService userService;

    @Override
    public ContactDto create(ContactDto contactDto) {
        Contact newContact = mapper.toEntity(contactDto);

        User user = userService.findById(contactDto.getUserId());
        newContact.setUser(user);

        Contact savedContact = repository.save(newContact);
        return mapper.toDto(savedContact);
    }

    @Override
    public ContactDto find(Long id) {
        Contact contact = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found with id: " + id));
        return mapper.toDto(contact);
    }

    @Override
    public Page<ContactDto> findAll(int page, int size, Sort.Direction direction, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<Contact> contacts = repository.findAll(pageable);
        return contacts.map(mapper::toDto);
    }

    @Override
    public ContactDto update(Long id, ContactDto contactDto) {

        Contact updatedContact = findById(id);
        mapper.updateContactFromDto(contactDto, updatedContact);

        if (contactDto.getUserId() != null) {
            User user = userService.findById(contactDto.getUserId());
            updatedContact.setUser(user);
        }
        return mapper.toDto(repository.save(updatedContact));
    }

    @Override
    public DeleteResponseDto delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Contact not found with id: " + id);
        }
        repository.deleteById(id);

        DeleteResponseDto dto = new DeleteResponseDto();
        dto.setId(id);
        dto.setMessage("Contact with id: " + id + "was deleted successfully");

        return dto;
    }

    public Contact findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Contact not found with id: " + id));
    }
}
