package com.example.handson.helidon;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity(name = "Prefecture")
@Table(name = "PREFECTURE")
@Access(AccessType.PROPERTY)
@NamedQueries({
        @NamedQuery(name = "getPrefectures", query = "SELECT p FROM Prefecture p"),
        @NamedQuery(name = "getPrefectureById", query = "SELECT p FROM Prefecture p WHERE p.id = :id"),
        @NamedQuery(name = "getPrefectureByName", query = "SELECT p FROM Prefecture p WHERE p.name = :name")
})
public class Prefecture {

    private int id;

    private String name;

    @JsonIgnore
    private PrefectureArea prefectureArea;

    private String area;

    @Id
    @Column(name = "ID", nullable = false, updatable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic(optional = false)
    @Column(name = "NAME", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    public PrefectureArea getPrefectureArea() {
        return prefectureArea;
    }

    public void setPrefectureArea(PrefectureArea prefectureArea) {
        this.prefectureArea = prefectureArea;
        this.area = prefectureArea.getArea();
    }

    @Transient
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

}
