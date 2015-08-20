package pl.spring.demo.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import pl.spring.demo.entity.AuthorEntity;
import pl.spring.demo.to.AuthorTo;

public class AuthorMapper {
	public static AuthorTo map(AuthorEntity AuthorEntity) {
        if (AuthorEntity != null) {
            return new AuthorTo(AuthorEntity.getId(), AuthorEntity.getFirstName(), AuthorEntity.getLastName());
        }
        return null;
    }

    public static AuthorEntity map(AuthorTo AuthorTo) {
        if (AuthorTo != null) {
            return new AuthorEntity(AuthorTo.getId(), AuthorTo.getFirstName(), AuthorTo.getLastName());
        }
        return null;
    }

    public static Set<AuthorTo> map2To(Set<AuthorEntity> authorEntities) {
        return authorEntities.stream().map(AuthorMapper::map).collect(Collectors.toSet());
    }

    public static Set<AuthorEntity> map2Entity(Set<AuthorTo> authorEntities) {
        return authorEntities.stream().map(AuthorMapper::map).collect(Collectors.toSet());
    }
}
