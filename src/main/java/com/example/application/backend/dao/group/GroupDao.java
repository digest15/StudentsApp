package com.example.application.backend.dao.group;

import com.example.application.backend.dto.group.GroupDto;
import com.example.application.backend.dto.group.GroupDtoForGrid;
import com.example.application.backend.dao.base.IdEntityDao;
import com.example.application.backend.dao.base.StandartEntityDao;
import com.example.application.backend.dto.group.GroupDtoOnlyName;

import java.util.List;

public interface GroupDao extends StandartEntityDao<GroupDto, GroupDtoForGrid>, IdEntityDao<GroupDto, Long> {
    List<GroupDtoOnlyName> getItemsOnlyName();

    GroupDtoOnlyName findByIdOnlyName(Long id);
}
