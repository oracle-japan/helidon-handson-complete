
package com.example.handson.helidon;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity(name = "PrefectureArea")
@Table(name = "PREFECTUREAREA")
@Access(AccessType.FIELD)
@NamedQueries({
        @NamedQuery(name = "getPrefectureAreas", query = "SELECT a FROM PrefectureArea a"),
        @NamedQuery(name = "getPrefectureAreaByArea", query = "SELECT a FROM PrefectureArea a WHERE a.area = :area")
})
public class PrefectureArea {
    @Id
    @Column(name = "ID", nullable = false, updatable = false)
    private int id;

    @Basic(optional = false)
    @Column(name = "AREA")
    private String area;

    public PrefectureArea() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

}
