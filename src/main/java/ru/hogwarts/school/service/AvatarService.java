package ru.hogwarts.school.service;

import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;

import java.io.IOException;
import java.util.List;

public interface AvatarService {

    Avatar findAvatar(long studentId);

    void uploadAvatar(Long id, MultipartFile avatar) throws IOException;

    String getExtension(String fileName);

    List<Avatar> getPaginatedAvatar(int pageNumber, int pageSize);
}
