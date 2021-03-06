package com.example.application.backend.dao.group;

import com.example.application.backend.dto.group.GroupDto;
import com.example.application.backend.dto.group.GroupDtoForGrid;
import com.example.application.backend.dto.group.GroupDtoOnlyName;
import com.example.application.backend.dto.student.StudentDto;
import com.example.application.backend.entity.Group;
import com.example.application.backend.entity.Student;
import com.example.application.backend.repository.GroupRepo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class GroupDaoImp implements GroupDao {
    private GroupRepo repository;
    private GroupMapper mapper;

    @Autowired
    public GroupDaoImp(GroupRepo repository, GroupMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public GroupDto findById(Long id) {
        return mapper.fromGroup(repository.findById(id).orElse(null));
    }

    @Override
    public List<GroupDto> getItems() {
        return mapper.fromGroups(repository.findAll());
    }

    @Override
    public List<GroupDto> getItems(String pattern) {
        if (pattern.isEmpty()) {
            return getItems();
        }else {
            return mapper.fromGroups(repository.findBySpecialtyNameContaining(pattern));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<GroupDtoForGrid> getItemsForGrid() {
        return mapper.fromGroupsForGrid(repository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public List<GroupDtoForGrid> getItemsForGrid(String pattern) {
        if (pattern.isEmpty()) {
            return getItemsForGrid();
        } else {
            return mapper.fromGroupsForGrid(repository.findBySpecialtyNameContaining(pattern));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<GroupDtoOnlyName> getItemsOnlyName() {
        return mapper.fromGroupsToGroupsDtoOnlyName(repository.findAll());
    }

    @Override
    public GroupDtoOnlyName findByIdOnlyName(Long id) {
        return mapper.fromGroupToGroupDtoOnlyName(repository.findById(id).orElse(null));
    }

    @Override
    public void save(GroupDto item) {
        repository.save(mapper.toGroup(item));
    }

    @Override
    public void delete(GroupDto item) {
        repository.delete(mapper.toGroup(item));
    }

    @Mapper
    public interface GroupMapper {
        List<GroupDtoForGrid> fromGroupsForGrid(List<Group> sourceCollection);

        List<GroupDto> fromGroups(List<Group> sourceCollection);

        GroupDto fromGroup(Group group);

        List<Group> toGroups(List<GroupDto> dtoCollection);

        Group toGroup(GroupDto dto);

        List<StudentDto> fromStudents(List<Student> sourceCollection);

        @Mapping(target = "group", ignore = true)
        StudentDto fromStudent(Student student);

        List<GroupDtoOnlyName> fromGroupsToGroupsDtoOnlyName(List<Group> sourceCollection);

        GroupDtoOnlyName fromGroupToGroupDtoOnlyName(Group source);
    }
}
