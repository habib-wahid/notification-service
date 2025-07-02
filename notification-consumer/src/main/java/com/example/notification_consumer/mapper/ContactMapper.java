package com.example.notification_consumer.mapper;

import com.example.notification_consumer.dto.ContactDto;
import com.example.notification_consumer.model.Contact;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring")
public interface ContactMapper {
    ContactDto toDto(Contact contact);

    Contact toEntity(ContactDto contactDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateContactFromDto(ContactDto contactDto, @MappingTarget Contact contact);
}
