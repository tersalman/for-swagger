package ru.hogwarts.school.dto;

import java.util.Objects;

public class AvatarDTO {
    private Long id;

    private long fileSize;

    private String mediaType;

    private Long studentID;

    public AvatarDTO(Long id, long fileSize, String mediaType, Long studentID) {
        this.id = id;
        this.fileSize = fileSize;
        this.mediaType = mediaType;
        this.studentID = studentID;
    }

    public AvatarDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public Long getStudentID() {
        return studentID;
    }

    public void setStudentID(Long studentID) {
        this.studentID = studentID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvatarDTO avatarDTO = (AvatarDTO) o;
        return fileSize == avatarDTO.fileSize && Objects.equals(id, avatarDTO.id) && Objects.equals(mediaType, avatarDTO.mediaType) && Objects.equals(studentID, avatarDTO.studentID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fileSize, mediaType, studentID);
    }
}
