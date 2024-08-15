package com.bigcorp.booking.projet.service;

import com.bigcorp.booking.projet.dao.ClientsDao;
import com.bigcorp.booking.projet.model.Clients;
import com.bigcorp.booking.projet.rest.ClientsDto;
import com.bigcorp.booking.rest.RestaurantTypeDto;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class ClientsService {
    private static final Logger LOGGER = Logger.getLogger(ClientsService.class);

    @Inject
    private ClientsDao clientsDao;

    //method to find a client by id
    public ClientsDto findClientById(Integer id) {
        return toDto(this.clientsDao.findClientById(id));
    }

    //method to create and save a client
    @TransactionAttribute
    public ClientsDto save(ClientsDto clientsDto) {
        LOGGER.info("sauvegarde du client " + clientsDto);
        Clients clientSaved = this.clientsDao.save(toEntity(clientsDto));
        return toDto(clientSaved);
    }

    //method to find all clients
    public List<ClientsDto> findAll() {
        List<Clients> clientsRegistred = this.clientsDao.findAll();
        return toDtos(clientsRegistred);
    }

    //method to delete a client by id
    public void deleteById(Integer id) {
        this.clientsDao.deleteClientById(id);
    }

    //method to find a client by name
    public List<ClientsDto> findName(String clientName) {
        List<Clients> clientsRegistred = this.clientsDao.findClientByName(clientName);
        return toDtos(clientsRegistred);
    }

    //method to find a client by name Like
    public List<ClientsDto> findLikeName(String clientNameFilter) {
        List<Clients> clientsRegistred = this.clientsDao.findClientByNameLike(clientNameFilter);
        return toDtos(clientsRegistred);
    }

    //method to transform an entity to a DTO
    private ClientsDto toDto(Clients entity) {
        if(entity == null) {
            return null;
        }
        ClientsDto dto = new ClientsDto();
        dto.setId(entity.getId());
        dto.setLastName(entity.getLastName());
        dto.setFirstName(entity.getFirstName());
        dto.setEmail(entity.getEmail());
        return dto;
    }

    //method to transform a DTO to an entity
    private Clients toEntity(ClientsDto dto) {
        if(dto == null) {
            return null;
        }
        Clients entity = new Clients();
        entity.setId(dto.getId());
        entity.setLastName(dto.getLastName());
        entity.setFirstName(dto.getFirstName());
        entity.setEmail(dto.getEmail());
        return entity;
    }

    //method to transform a list of entities to a list od DTOs
    private List<ClientsDto> toDtos(List<Clients> entities) {
        List<ClientsDto> dtos = new ArrayList<>();
        for(Clients entity : entities) {
            dtos.add(toDto(entity));
        }
        return dtos;
    }
}