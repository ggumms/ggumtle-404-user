package life.ggumtle.user.common.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashMap;
import java.util.Map;

@Data
@Table(name = "survey")
public class Survey {

    @Id
    private Long id;

    @Column("user_id")
    private Long userId;

    @Column("environment")
    private Boolean environment;

    @Column("charity")
    private Boolean charity;

    @Column("relationships")
    private Boolean relationships;

    @Column("relaxation")
    private Boolean relaxation;

    @Column("romance")
    private Boolean romance;

    @Column("exercise")
    private Boolean exercise;

    @Column("travel")
    private Boolean travel;

    @Column("lang")
    private Boolean lang;

    @Column("culture")
    private Boolean culture;

    @Column("challenge")
    private Boolean challenge;

    @Column("hobby")
    private Boolean hobby;

    @Column("workplace")
    private Boolean workplace;
}
