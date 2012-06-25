package eu.pozoga.nice.code.helper.converter;

/**
 *
 * @author Sebastian Pożoga
 */
public abstract class AbstractConverter<FROMT, TOTYPE>  {

    public abstract TOTYPE convert(FROMT object);
    
}
