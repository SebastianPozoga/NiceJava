/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testedObject;

import eu.pozoga.nice.persist.Options;
import java.util.Date;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Sebastian Pozoga
 */
public class MyEntityOptions extends Options{
    
    public Long owner;
    
    @NotNull
    private Date addDate;

    public MyEntityOptions() throws Exception {
        super();
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date date) {
        this.addDate = date;
    }
    
    
    
}
