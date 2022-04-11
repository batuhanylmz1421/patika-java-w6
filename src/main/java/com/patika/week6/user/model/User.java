package com.patika.week6.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class User {

    private final UUID id;

    @NotBlank
    private final String name;

    public User(@JsonProperty("id") UUID id,
                @NonNull @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }


    @Override
    public boolean equals(Object obj) {
        if(obj instanceof User)
            return ((User) obj).id.equals(this.id);
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
