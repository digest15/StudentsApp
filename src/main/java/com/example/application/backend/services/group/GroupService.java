package com.example.application.backend.services.group;

import com.example.application.backend.entity.Group;
import com.example.application.backend.projections.GroupDTO;
import com.example.application.backend.services.base.IdEntityService;
import com.example.application.backend.services.base.StandartEntityService;

public interface GroupService extends StandartEntityService<Group, GroupDTO>, IdEntityService<Group, Long> {

}
