package com.example.handson.helidon;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name = "Prefecture")
@Table(name = "PREFECTURE")
@Access(AccessType.PROPERTY)
@NamedQueries({
    @NamedQuery(name = "getPrefectures",
                query = "SELECT p FROM Prefecture p"),
    @NamedQuery(name = "getPrefectureById",
                query = "SELECT p FROM Prefecture p WHERE p.id = :id"),
    @NamedQuery(name = "getPrefectureByName",
                query = "SELECT p FROM Prefecture p WHERE p.name = :name")
})
public class Prefecture {

    private int id;

    private String name;

    @JsonbTransient
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
