package jmp.dto.main.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class UserRequestDto {
    @Schema(name = "UserID", description = "Sample user id for the documentation", example = "123")
    private Long id;
    @Schema(name = "UserName", description = "Sample user name for the documentation", example = "Ivan")
    private String name;
    @Schema(name = "UserSurname", description = "Sample user surname for the documentation", example = "Ivanov")
    private String surname;
    @Schema(name = "UserBirthday", description = "Sample user birthday for the documentation", example = "2000-01-10")
    private String birthday;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "UserRequestDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }

    public UserRequestDto(Long id, String name, String surname, String birthday) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
    }

    public UserRequestDto() {
    }
}
