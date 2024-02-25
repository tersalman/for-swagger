package ru.hogwarts.school.mapper;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.dto.AvatarDTO;
import ru.hogwarts.school.model.Avatar;
@Service
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
