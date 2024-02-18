package ru.hogwarts.school.mapper;

import ru.hogwarts.school.dto.AvatarDTO;
import ru.hogwarts.school.model.Avatar;

public class AvatarMapper {

    public AvatarDTO mapToDTO(Avatar avatar) {
        AvatarDTO avatarDTO = new AvatarDTO();
        avatarDTO.setId(avatar.getId());
        avatarDTO.setFileSize(avatar.getFileSize());
        avatarDTO.setMediaType(avatar.getMediaType());
        avatarDTO.setStudentID(avatar.getStudent().getId());

        return avatarDTO;
    }

}
