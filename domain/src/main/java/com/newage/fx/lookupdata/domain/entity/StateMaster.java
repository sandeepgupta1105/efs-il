package com.newage.fx.lookupdata.domain.entity;


import com.newage.fx.lookupdata.domain.enums.LovStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "efs_state_master", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id", "country_id"}, name = "UK_COUNTRY_STATE")})

public class StateMaster extends Auditable<String> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @Column(name = "code", nullable = false, length = 10)
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Special characters are not allowed")
    private String code;

    @NotNull
    @NotEmpty
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id", foreignKey = @ForeignKey(name = "FK_STATE_COUNTRY_ID"))
    CountryMaster country;

    @Column(name = "country_code")
    private String countryCode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "state")
    private Set<CityMaster> cities;

    @NotNull(message = "Accepted Values (Active, Disabled, InActive)")
    @Column(name = "status", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private LovStatus status;

    @Version
    @Column(name = "version")
    Long version;


}
