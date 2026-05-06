package h2o.tz.lyapanov.tz_lyapanov_aa.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.ToString;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Data
@MappedSuperclass
@ToString(of = "id")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericEntity implements Serializable {

    private static final long serialVersionUID = 7219760563483429945L;

    @Id
    @GeneratedValue(generator = "uuidGenerator")
    @Column(name = "id", length = 36)
    @NotNull
    private String id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GenericEntity that = (GenericEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return getClass().hashCode(); }
}

