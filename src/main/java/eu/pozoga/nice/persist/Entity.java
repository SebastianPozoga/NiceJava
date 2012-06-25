package eu.pozoga.nice.persist;

import eu.pozoga.nice.code.helper.LoadHelper;
import java.util.Date;
import java.util.Map;
import javax.persistence.*;

/**
 *
 * @author Sebastian Pożoga
 */
@MappedSuperclass
public abstract class Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(unique = false, nullable = false, name = "ADD_DATE")
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date addDate;
    
    @Column(unique = false, nullable = true, name = "LAST_UPDATE")
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date lastUpdate;
    
    @ManyToOne
    private Long owner;

    
    public long getId() {
        return id;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    protected void initAddDate() {
        this.addDate = new Date();
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    protected void initLastUpdate() {
        this.lastUpdate = new Date();
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        //checking type here
        if (!getClass().isInstance(obj)) {
            return false;
        }
        final Entity other = (Entity) obj;
        if (this.id != other.getId()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = this.getClass().getName().hashCode();
        hash = hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    public void load(Object o) throws Exception {
        LoadHelper.load(this, o);
    }

    public void load(Map<String, Object> m) throws Exception {
        LoadHelper.load(this, m);
    }
}
