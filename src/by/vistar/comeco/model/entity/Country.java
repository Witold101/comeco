package by.vistar.comeco.model.entity;

import lombok.Getter;
import lombok.Setter;

public class Country {
    private Long id;
    private String name;
    private String fullName;
    private String codLetter;
    private String pathFlag;
    private Integer phoneCode;

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCodLetter() {
        return codLetter;
    }

    public void setCodLetter(String codLetter) {
        this.codLetter = codLetter;
    }

    public String getPathFlag() {
        return pathFlag;
    }

    public void setPathFlag(String pathFlag) {
        this.pathFlag = pathFlag;
    }

    public Integer getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(Integer phoneCode) {
        this.phoneCode = phoneCode;
    }
}
