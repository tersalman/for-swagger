package ru.hogwarts.school.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class StudentServiceImpl implements StudentService {

    @Value("${path.to.avatars.folder}")
    private String avatarsDir;

    private final StudentRepository studentRepository;
    private final AvatarRepository avatarRepository;



    public StudentServiceImpl(StudentRepository studentRepository, AvatarRepository avatarRepository) {
        this.studentRepository = studentRepository;
        this.avatarRepository = avatarRepository;
    }

    @Override
    public Student add(Student student) {
        studentRepository.save(student);
        return student;

    }

    @Override
    public Student get(Long id) {
       return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student update(Long id, Student student) {
        return studentRepository.findById(id).map(studentFromDb -> {
            studentFromDb.setName(student.getName());
            studentFromDb.setAge(student.getAge());
            return studentRepository.save(studentFromDb);
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);

    }

    @Override
    public List<Student> getByAge(int age) {
        return studentRepository.findAll().stream()
                .filter(it->it.getAge()==age)
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> findByAgeBetween(int ageFrom, int ageTo) {
        return studentRepository.findByAgeBetween(ageFrom, ageTo);
    }

    @Override
    public Faculty getFaculty(Long id) {
        return studentRepository.findById(id)
                .map(Student::getFaculty).orElse(null);
    }
    @Override
    public Avatar findAvatar(long studentId) {
        return avatarRepository.findByStudentId(studentId).orElseThrow();
    }
    @Override
    public void uploadAvatar(Long studentId, MultipartFile file) throws IOException {
        Student student = get(studentId);

        Path filePath = Path.of(avatarsDir, studentId + "." + getExtension(Objects.requireNonNull(file.getOriginalFilename())));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }  Avatar avatar = avatarRepository.findByStudentId(studentId).orElseGet(Avatar::new);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setData(file.getBytes());

        avatarRepository.save(avatar);
    }

    public String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
