package com.mavrov.entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * @author serg.mavrov@gmail.com
 */
@Entity
@Table(name = "profiles", schema = "STARTREK")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class ProfileEntity extends StandardEntity {

    @TableGenerator(name = "entity_id_generator",
            table = "entity_ids",
            schema = "STARTREK",
            pkColumnName = "gen_name",
            pkColumnValue = "profiles",
            valueColumnName = "gen_value",
            initialValue = 1,
            allocationSize = 10
    )
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "entity_id_generator")
    private BigInteger id;

    @Basic
    @Column(name = "name1", nullable = false)
    private String name1;

    @Basic
    @Column(name = "name2", nullable = false)
    private String name2;

    @Basic
    @Column(name = "position", nullable = false)
    private String position;

    @Basic
    @Column(name = "company_name", nullable = false)
    private String companyName;

    public ProfileEntity() {
    }

    public ProfileEntity(String name1, String name2, String position, String companyName) {
        this.id = id;
        this.name1 = name1;
        this.name2 = name2;
        this.position = position;
        this.companyName = companyName;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "ProfileEntity{" +
                "id=" + id +
                ", name1='" + name1 + '\'' +
                ", name2='" + name2 + '\'' +
                ", position='" + position + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
