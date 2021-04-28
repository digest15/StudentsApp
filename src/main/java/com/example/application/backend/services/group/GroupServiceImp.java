package com.example.application.backend.services.group;

import com.example.application.backend.entity.Group;
import com.example.application.backend.projections.GroupDTO;
import com.example.application.backend.repository.GroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GroupServiceImp implements GroupService {
    private GroupRepo repository;

    @Autowired
    public GroupServiceImp(GroupRepo repository) {
        this.repository = repository;
    }

    @Override
    public Group findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Group> getItems() {
        return repository.findAll();
    }

    @Override
    public List<Group> getItems(String pattern) {
        if (pattern.isEmpty()) {
            return getItems();
        }else {
            return repository.findAllForPattern(pattern);
        }
    }

    @Override
    public List<GroupDTO> getItemsForGrid() {
        return repository.getAllForDto();
    }

    @Override
    public List<GroupDTO> getItemsForGrid(String pattern) {
        if (pattern.isEmpty()) {
            return getItemsForGrid();
        } else {
            return repository.getAllForDto(pattern);
        }
    }

    @Override
    public void save(Group item) {
        repository.save(item);
    }

    @Override
    public void delete(Group item) {
        repository.delete(item);
    }
}
